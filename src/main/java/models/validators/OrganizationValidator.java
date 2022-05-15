package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.OrganizationView;
import constants.MessageConst;

/**
 *組織インスタンスに設定されている値のバリテーションを行うクラス
 */

public class OrganizationValidator {

    /**
     * 組織インスタンスの各項目についてバリテーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(OrganizationView ov){
        List<String> errors=new ArrayList<String>();

        //部署記入欄のチェック
        String departmentError=validateDepartment(ov.getDepartmentName());
        if(!departmentError.equals("")) {
            errors.add(departmentError);
        }

        return errors;
    }

    public static String validateDepartment(String departmentName) {
        if(departmentName == null ||departmentName.equals("")){
            return MessageConst.E_NODEPARTMENT.getMessage();
        }

        //入力値がある場合は空文字を返却する
        return "";
    }

}
