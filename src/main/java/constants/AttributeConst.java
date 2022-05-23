package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 *
 */


public enum AttributeConst {


    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力ファーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),
    EMP_COL_ORGANIZATION_ID("organization_id"),

    //管理者フラグ
    ROLE_DIRECTOR(3),//部長
    ROLE_MANAGER(2),//課長
    ROLE_ADMIN(1),//管理者
    ROLE_GENERAL(0),//一般


    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //日報管理
    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content"),
    REP_ATTEND_AT("attend_at"),//出勤時刻
    REP_LEAVING_AT("leaving_at"),//退勤時刻
    REP_CORRECTION_POINT("correction_point"),//日報の修正点

    REP_REPORT_FLG("report_flag"),

    //組織管理
    ORGANIZATION("organization"),
    ORGANIZATIONS("organizations"),
    ORG_DEP_NAME("department"),

    //報告書削除フラグ
    DEL_FLAG_UNAPPROVED(0),
    DEL_FLAG_APPROVED(1),
    DEL_FLAG_NON_APPROVAL(2);






    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text=text;
        this.i=null;
    }

    private AttributeConst(final Integer i) {
        this.text=null;
        this.i=i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}
