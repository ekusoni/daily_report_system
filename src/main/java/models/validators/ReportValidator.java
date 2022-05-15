package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリテーションを行うクラス
 */
public class ReportValidator {

    /**
     * 日報インスタンスの各項目についてバリテーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ReportView rv) {
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(rv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        //出勤時刻のチェック
        String attendError = validateAttend(rv.getAttendAt());
        if (!attendError.equals("")) {
            errors.add(attendError);
        }

        //退勤時刻のチェック
        String leavingError = validateLeaving(rv.getLeavingAt());
        if (!leavingError.equals("")) {
            errors.add(leavingError);
        }

        //出勤時刻と退勤時刻が違うかのチェック
        String attendLeavingSameError = validateAttendLeavingSame(rv.getAttendAt(), rv.getLeavingAt());
        if (!attendLeavingSameError.equalsIgnoreCase("")) {
            errors.add(attendLeavingSameError);
        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 出勤時刻に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param attend 出勤時刻
     * @return エラーメッセージ
     */
    private static String validateAttend(String attend) {
        if (attend == null || attend.equals("")) {
            return MessageConst.E_NOATTEND.getMessage();
        }

        //入力値がある場合は空文字を返却する
        return "";
    }

    /**
     * 退勤時刻に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param leaving 退勤時刻
     * @return エラーメッセージ
     */
    private static String validateLeaving(String leaving) {
        if (leaving == null || leaving.equals("")) {
            return MessageConst.E_NOLEAVING.getMessage();
        }

        //入力値がある場合は空文字を返却する
        return "";
    }

    /**
     * 出勤時刻と退勤時刻が同じ時間でないかをチェックし、同時刻の場合エラーメッセージを返却
     * @param attend leaving 出勤時刻 退勤時刻
     * @return エラーメッセージ
     */
    private static String validateAttendLeavingSame(String attend, String leaving) {
        if (attend != null && !attend.equals("") && leaving.equals(attend)) {
            return MessageConst.E_NO_SAMETIME.getMessage();
        }

        //出勤時刻と退勤時刻が違う場合は空文字を返却する
        return "";
    }

}
