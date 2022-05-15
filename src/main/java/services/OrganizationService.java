package services;

import java.util.List;

import actions.views.OrganizationConverter;
import actions.views.OrganizationView;
import constants.JpaConst;
import models.Organization;
import models.validators.OrganizationValidator;

/**
 * 組織テーブルの操作に関わる処理を行うクラス
 */


public class OrganizationService extends ServiceBase {
    /**
     * 新規従業員の登録ページで表示する組織データを取得し、OrganizationViewのリストで返却する
     *@return表示するデータのリスト
     */
    public List<OrganizationView> getPage(){
        List<Organization> organization=em.createNamedQuery(JpaConst.Q_ORG_GET_ALL,Organization.class)
                .getResultList();

        return OrganizationConverter.toViewList(organization);

    }

    /**
     * 組織テーブルのデータの件数を取得し、返却する
     * @return 組織テーブルのデータの件数
     */
    public long countAll() {
        long orgCount=(long) em.createNamedQuery(JpaConst.Q_ORG_COUNT,Long.class)
                .getSingleResult();
        return orgCount;
    }

    /**
     * idを条件に取得したデータをOrganizationViewのインスタンスで返却する
     * @param id
     * @return 取得したデータのインスタンス
     */
    public OrganizationView findOne(int id) {
        Organization o=findOneInternal(id);
        return OrganizationConverter.toView(o);
    }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、組織テーブルに登録する
     * @param ov 日報の登録内容
     * @return バリテーションで発生したエラーのリスト
     */
    public List<String> create(OrganizationView ov){
        List<String> errors=OrganizationValidator.validate(ov);
        if(errors.size()==0) {
            createInternal(ov);
        }


        //バリテーションで発生したエラーを返却(エラーがなければ0件の空リスト)
        return errors;
    }

    /**
     * 部署データを1件登録する
     * @param ov
     */
    private void createInternal(OrganizationView ov) {

        em.getTransaction().begin();
        em.persist(OrganizationConverter.toModel(ov));
        em.getTransaction().commit();
    }

    /**
     * idを条件にデータを1件取得し、Organizationのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Organization findOneInternal(int id) {
        Organization o=em.find(Organization.class, id);

        return o;
    }






}
