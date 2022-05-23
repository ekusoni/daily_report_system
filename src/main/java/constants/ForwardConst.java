package constants;

/**
 * リクエストパラメータの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */

public enum ForwardConst {


    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_REP("Report"),
    ACT_ORG("Organization"),
    ACT_AUTH("Auth"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_APPROVAL("approval"),//承認
    CMD_NON_APPROVAL("nonApproval"),//非承認
    CMD_THIS_APPROVAL("thisApproval"),//承認済み
    CMD_NON_APPROVAL_REASON("nonReason"),//非承認の理由
    CMD_DEPARTMENT("department"),//部署

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_EMP_INDEX("employees/index"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_EDIT("employees/edit"),
    FW_REP_INDEX("reports/index"),
    FW_REP_SHOW("reports/show"),
    FW_REP_NEW("reports/new"),
    FW_REP_EDIT("reports/edit"),
    FW_REP_APPROVAL("reports/approval"),//承認
    FW_ORG_DEPARTMENT("organizations/new"),//部署登録画面
    FW_ORG_SHOW("organizations/show");//部署関係者一覧

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text=text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
