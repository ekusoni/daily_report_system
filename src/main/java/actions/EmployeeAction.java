package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.OrganizationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.EmployeeService;
import services.OrganizationService;

/**
 * 従業員に関わる処理を行うActionクラス
 *
 *
 */

public class EmployeeAction extends ActionBase {

    private EmployeeService service;
    private OrganizationService serviceOrg;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new EmployeeService();
        serviceOrg= new OrganizationService();

        //メソッドを実行
        invoke();

        serviceOrg.close();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOEception
     */
    public void index() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

            //指定されたページ数の一覧画面に表示するデータを取得
            int page = getPage();
            List<EmployeeView> employees = service.getPerPage(page);

            //全ての従業員データの件数を取得
            long employeeCount = service.countAll();

            putRequestScope(AttributeConst.EMPLOYEES, employees);//取得した従業員データ
            putRequestScope(AttributeConst.EMP_COUNT, employeeCount);//全ての従業員データの件数
            putRequestScope(AttributeConst.PAGE, page);//ページ数
            putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);//1ページに表示するレコードの数

            //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

            //一覧画面を表示
            forward(ForwardConst.FW_EMP_INDEX);
        }
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {

            //部署の一覧データをを取得する
            List<OrganizationView> organizations = serviceOrg.getPage();
            putRequestScope(AttributeConst.TOKEN, getTokenId());//CSRF対策用トークン
            putRequestScope(AttributeConst.EMPLOYEE, new EmployeeView());//空の従業員インスタンス
            putRequestScope(AttributeConst.ORGANIZATIONS, organizations);//取得した部署データ

            //新規登録画面を表示
            forward(ForwardConst.FW_EMP_NEW);
        }
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOEception
     */
    public void create() throws ServletException, IOException {

        //CSRF対策tokenのチェック
        if (checkAdmin() && checkToken()) {

            //パラメータの値を元に従業員情報のインスタンスを作成する
            EmployeeView ev = new EmployeeView(
                    null,
                    getRequestParam(AttributeConst.EMP_CODE),
                    getRequestParam(AttributeConst.EMP_NAME),
                    getRequestParam(AttributeConst.EMP_PASS),
                    toNumber(getRequestParam(AttributeConst.EMP_ADMIN_FLG)),
                    toNumber(getRequestParam(AttributeConst.EMP_COL_ORGANIZATION_ID)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //従業員情報登録
            List<String> errors = service.create(ev, pepper);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                //部署の一覧データをを取得する
                List<OrganizationView> organizations = serviceOrg.getPage();
                putRequestScope(AttributeConst.TOKEN, getTokenId());//CSRF対策トークン
                putRequestScope(AttributeConst.EMPLOYEE, ev);//入力された従業員情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト
                putRequestScope(AttributeConst.ORGANIZATIONS,organizations);//取得した部署データ

                //新規登録画面を再表示
                forward(ForwardConst.FW_EMP_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOEception
     */
    public void show() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {
            //idを条件に従業員データを取得する
            EmployeeView ev = service.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));
            OrganizationView ov =serviceOrg.findOne(ev.getOrganizationId());

            if (ev == null || ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                //データを取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }

            putRequestScope(AttributeConst.EMPLOYEE, ev);//取得した従業員情報
            putRequestScope(AttributeConst.ORGANIZATION,ov);//取得した部署情報

            //詳細画面を表示
            forward(ForwardConst.FW_EMP_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //管理者かどうかのチェック
        if (checkAdmin()) {
            //id条件に従業員データを取得する
            EmployeeView ev = service.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));
            //id条件に部署データを取得
            OrganizationView ov=serviceOrg.findOne(ev.getOrganizationId());
            //部署の一覧データをを取得する
            List<OrganizationView> organizations = serviceOrg.getPage();


            if (ev == null || ev.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

                //データが取得できなかった、または論理削除されている場合はエラー画面を表示
                forward(ForwardConst.FW_ERR_UNKNOWN);
                return;
            }

            putRequestScope(AttributeConst.TOKEN, getTokenId());//CSRF対策用トークン
            putRequestScope(AttributeConst.EMPLOYEE, ev);//取得した従業員情報
            putRequestScope(AttributeConst.ORGANIZATION,ov);//取得した部署データ
            putRequestScope(AttributeConst.ORGANIZATIONS,organizations);//部署の一覧データ

            //編集画面を表示する
            forward(ForwardConst.FW_EMP_EDIT);
        }
    }

    /**
     * 更新
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策tokenのチェック
        if (checkAdmin() && checkToken()) {

            //パラメータの値を元に従業員情報のインスタンスを作成する
            EmployeeView ev = new EmployeeView(
                    toNumber(getRequestParam(AttributeConst.EMP_ID)),
                    getRequestParam(AttributeConst.EMP_CODE),
                    getRequestParam(AttributeConst.EMP_NAME),
                    getRequestParam(AttributeConst.EMP_PASS),
                    toNumber(getRequestParam(AttributeConst.EMP_ADMIN_FLG)),
                    toNumber(getRequestParam(AttributeConst.EMP_COL_ORGANIZATION_ID)),
                    null,
                    null,
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //従業員情報更新
            List<String> errors = service.update(ev, pepper);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                //部署の一覧データをを取得する
                List<OrganizationView> organizations = serviceOrg.getPage();
                putRequestScope(AttributeConst.TOKEN, getTokenId());//CSRF対策用トークン
                putRequestScope(AttributeConst.EMPLOYEE, ev);//入力された従業員情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト
                putRequestScope(AttributeConst.ORGANIZATIONS,organizations);//取得した部署一覧

                //編集画面を再表示
                forward(ForwardConst.FW_EMP_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 論理削除を行う
     * @throws ServletException
     * @throws IOException
     */
    public void destroy() throws ServletException, IOException {

        //CSRF対策tokenのチェック
        if (checkAdmin() && checkToken()) {

            //idを条件に従業員データを論理削除する
            service.destroy(toNumber(getRequestParam(AttributeConst.EMP_ID)));

            //セッションに削除完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
        }
    }




}
