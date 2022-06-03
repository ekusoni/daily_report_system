package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.OrganizationView;
import constants.MessageConst;
import services.OrganizationService;

/**
 *組織インスタンスに設定されている値のバリテーションを行うクラス
 */

public class OrganizationValidator {

    /**
     * 組織インスタンスの各項目についてバリテーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(OrganizationService service,OrganizationView ov){
        List<String> errors=new ArrayList<String>();

        //部署記入欄のチェック
        String departmentError=validateDepartment(service,ov.getDepartmentName());
        if(!departmentError.equals("")) {
            errors.add(departmentError);
        }

        return errors;
    }

    public static String validateDepartment(OrganizationService service,String departmentName) {

        long organizationsCount=isDuplicateOrganization(service,departmentName);
        if(departmentName == null ||departmentName.equals("")){
            return MessageConst.E_NODEPARTMENT.getMessage();
        }else if(organizationsCount > 0) {
            return MessageConst.E_ORG_NAME_EXIST.getMessage();
        }

        //入力値がある場合は空文字を返却する
        return "";
    }

    /**
     * @param service OrganizationServiceのインスタンス
     * @param name 部署名
     * @return 組織テーブルに登録されている同一部署のデータ件数
     */
    private static long isDuplicateOrganization(OrganizationService service,String name) {

        long organizationsCount=service.countByName(name);
        return organizationsCount;
    }

}
