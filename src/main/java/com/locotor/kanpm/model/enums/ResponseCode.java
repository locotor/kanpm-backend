package com.locotor.kanpm.model.enums;

public enum ResponseCode {
    /**
     * 权限 10000-10020
     */
    SYS_ERROR("10000", "系统错误"), NOT_OPT_AUTH("10001", "无操作权限"), NOT_VISIT_AUTH("10002", "无访问权限"),
    HAS_VISIT_AUTH("10003", "有访问权限"), OPT_FAIL("10004", "操作失败"),
    /**
     * 文件类 10020-10040
     */
    FILE_UPLOAD_FAIL("10020", "文件上传失败"), FILE_NOT_EXIST("10021", "文件不存在"), FILE_EXIST("10022", "文件已存在"),
    FILE_CREATE_FAIL("10023", "文件创建失败"),

    /**
     * 数据类 10040-10060
     */
    DATA_NOT_EXIST("10040", "数据不存在"), DATA_EXIST("10041", "数据已存在"),
    /**
     * 日期类 10060-10080
     */
    DATE_DIFFER("10060", "指定的年份和导入的年份不一致"), DATE_EXIST("10061", "日期已存在"),

    /**
     * 模板 10080-10100
     */
    TEMPLATE_EXPORT_FAIL("10080", "模板导出失败"), TEMPLATE_NOT_CORRECT("10081", "上传文件不符合要求请下载模板重新上传"),

    /**
     * 参数类提示 10100-100120
     */
    PARAM_IS_NULL("10100", "必填参数未填"), PARAM_IS_ERROR("10101", "参数不符合规定"),

    /**
     * 登录和用户 10120-10250
     */
    USER_NOT_LOGIN("10120", "未登录或登录超时"), USER_EMPTY("10121", "用户名或密码为空"), AUTH_ERROR("10122", "用户名或密码错误"),
    USER_NOT_EXIST("10123", "用户不存在"), USER_ALREADY_EXIST("10124", "用户名已存在"), USER_DISABLE("10125", "用户被禁用或被注销"),
    USER_IS_RIGHT("10126", "合法用户"), USER_NOT_RIGHT("10127", "用户不合法"), CREDENTIAL_NOT_RIGHT("10131", "密码已过期"),
    USER_LOGOUT("10128", "已注销"), USER_IS_LOCK("10129", "登录次数过多，用户被锁定"), USER_PWD_EQUAL("10130", "原始密码和新密码相同"),
    CAPTCHA_IS_NULL("10131", "验证码不能为空"), CAPTCHA_NOT_RIGHT("10132", "验证码错误"),

    /**
     * 小组 10150-10160
     */
    TEAM_NOT_EXIST("10150", "小组不存在"), TEAM_ALREADY_EXIST("10151", "小组名已经存在"), TEAM_EMPTY("10152", "小组名为空"),

    /**
     * 项目 10160-10170
     */
    PROJECT_NOT_EXIST("10160", "项目不存在"), PROJECT_ALREADY_EXIST("10161", "项目名已经存在"), PROJECT_EMPTY("10162", "项目名为空"),
    PROJECT_ID_EMPTY("10163", "项目ID为空"),

    /**
     * 任务列表 10170-10180
     */
    TASK_STACK_NOT_EXIST("10170", "任务列表不存在"), TASK_STACK_ALREADY_EXIST("10171", "任务列表名已经存在"),
    TASK_STACK_EMPTY("10172", "任务列表名为空"), TASK_STACK_ID_EMPTY("10173", "任务列表ID为空"),

    /**
     * 任务列表 10180-10190
     */
    TASK_NOT_EXIST("10180", "任务不存在"), TASK_EMPTY("10171", "任务描述为空"), TASK_ID_EMPTY("10172", "任务ID为空"),

    /**
     * 服务器异常 10250-10280
     */
    RUNTIME_EXCEPTION("10250", "[服务器]运行时异常"), NULL_POINTER_EXCEPTION("10251", "[服务器]空值异常"),
    CLASS_CAST_EXCEPTION("10252", "[服务器]数据类型转换异常"), IO_EXCEPTION("10253", "[服务器]IO异常"),
    NO_SUCH_METHOD_EXCEPTION("10254", "[服务器]未知方法异常"), INDEX_OUT_OF_BOUNDS_EXCEPTION("10255", "[服务器]数组越界异常"),
    SOCKET_EXCEPTION("10256", "[服务器]网络异常"), CONNECT_EXCEPTION("10257", "[服务器]连接异常"),
    SQL_EXCEPTION("10258", "[服务器]数据库异常"),

    /**
     * 异常10410-10420
     */
    SQL_FAIL("10410", "Sql异常"), EXCEPTION_FAIL("10411", "exception异常"),

    /**
     * 提示类 20000-20020
     */
    SUCCESS("20000", "操作成功"),

    FAIL("20001", "操作失败");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
