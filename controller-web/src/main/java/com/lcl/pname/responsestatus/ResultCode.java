package com.lcl.pname.responsestatus;

import lombok.Getter;

/**
 * 统一的结果业务状态码定义枚举类
 */
public enum ResultCode {
    SUCCESS(20000,"成功"),
    UNKNOWN(20001, "未知错误"),
    NOT_FOUND(20002, "找不到资源"),
    PARAM_VALID_ERROR(20003, "请求参数校验错误"),
    METHOD_NOT_SUPPORTED(20004, "不支持的请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(20005, "不支持的媒体类型"),

    BAD_SQL_GRAMMAR(21001, "sql语法错误"),
    JSON_PARSE_ERROR(21002, "json解析异常"),
    PARAM_ERROR(21003, "参数不正确"),

    FILE_UPLOAD_ERROR(21004, "文件上传错误"),
    FILE_DELETE_ERROR(21005, "文件刪除错误"),
    EXCEL_DATA_IMPORT_ERROR(21006, "Excel数据导入错误"),

    VIDEO_UPLOAD_ALIYUN_ERROR(22001, "视频上传至阿里云失败"),
    VIDEO_UPLOAD_TOMCAT_ERROR(22002, "视频上传至业务服务器失败"),
    VIDEO_DELETE_ALIYUN_ERROR(22003, "阿里云视频文件删除失败"),
    FETCH_VIDEO_UPLOADAUTH_ERROR(22004, "获取上传地址和凭证失败"),
    REFRESH_VIDEO_UPLOADAUTH_ERROR(22005, "刷新上传地址和凭证失败"),
    FETCH_PLAYAUTH_ERROR(22006, "获取播放凭证失败"),

    URL_ENCODE_ERROR(23001, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(23002, "非法回调请求"),
    FETCH_ACCESSTOKEN_FAILD(23003, "获取accessToken失败"),
    FETCH_USERINFO_ERROR(23004, "获取用户信息失败"),
    LOGIN_ERROR(23005, "登录失败"),

    COMMENT_EMPTY(24006, "评论内容必须填写"),

    PAY_RUN(25000, "支付中"),
    PAY_UNIFIEDORDER_ERROR(25001, "统一下单错误"),
    PAY_ORDERQUERY_ERROR(25002, "查询支付结果错误"),
    PAY_TRANSFER_ERROR(25004,"转账失败"),

    ORDER_EXIST_ERROR(25003, "课程已购买"),

    GATEWAY_ERROR(26000, "服务不能访问"),

    CODE_ERROR(28000, "验证码错误"),
    LOGIN_PHONE_ERROR(28009, "手机号码不正确"),
    LOGIN_MOBILE_ERROR(28001, "账号不正确"),
    LOGIN_PASSWORD_ERROR(28008, "密码不正确"),
    LOGIN_DISABLED_ERROR(28002, "该用户已被禁用"),
    REGISTER_MOBLE_ERROR(28003, "手机号已被注册"),
    LOGIN_AUTH(28004, "需要登录"),
    LOGIN_ACL(28005, "没有权限"),
    SMS_SEND_ERROR(28006, "短信发送失败"),
    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL(28007, "短信发送过于频繁")
    ;

    final int code;
    final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}