package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.OrganizationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.EmployeeService;
import services.OrganizationService;

public class OrganizationAction extends ActionBase {

    private OrganizationService service;
    private EmployeeService serviceEmp;


    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException{

        service = new OrganizationService();
        serviceEmp =new EmployeeService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * throws IOEception
     */
    public void entryNew() throws ServletException, IOException{


        putRequestScope(AttributeConst.ORGANIZATION, new OrganizationView());



        //新規登録画面を表示
        forward(ForwardConst.FW_ORG_DEPARTMENT);
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException{

        OrganizationView ov=new OrganizationView(
                null,
                getRequestParam(AttributeConst.ORG_DEP_NAME));

        //部署情報を登録
        List<String> errors=service.create(ov);

        if(errors.size()>0) {

            putRequestScope(AttributeConst.ERR,errors);

            //新規登録画面を再表示
            forward(ForwardConst.FW_ORG_DEPARTMENT);
        }else {

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH,MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_EMP,ForwardConst.CMD_INDEX);
        }
    }

    /**
     * 部署関係者一覧を表示する
     * @throws ServletException
     * @throws IOException
     */

    public void show() throws  ServletException, IOException{

        ////idを条件に従業員データを取得する
        EmployeeView ev= serviceEmp.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));
        //従業員の一覧データを取得する
        List<EmployeeView> employees=serviceEmp.getPage();
        //部署の一覧データを取得する
        List<OrganizationView> organizations=service.getPage();

        putRequestScope(AttributeConst.EMPLOYEE,ev);
        putRequestScope(AttributeConst.EMPLOYEES,employees);
        putRequestScope(AttributeConst.ORGANIZATIONS,organizations);

        forward(ForwardConst.FW_ORG_SHOW);


    }




}


