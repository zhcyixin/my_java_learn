package com.zhc.constant;

/**
 * 返回状态码
 *
 * @author ruoyi
 */
public class HttpStatus {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 20000;

    /**
     * 对象创建成功
     */
    public static final int CREATED = 20001;

    /**
     * 请求已经被接受
     */
    public static final int ACCEPTED = 20002;

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    public static final int NO_CONTENT = 20004;

    /**
     * 资源已被移除
     */
    public static final int MOVED_PERM = 30001;

    /**
     * 重定向
     */
    public static final int SEE_OTHER = 30003;

    /**
     * 资源没有被修改
     */
    public static final int NOT_MODIFIED = 30004;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 40000;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 40001;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 40003;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 40004;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 40005;

    /**
     * 资源冲突，或者资源被锁
     */
    public static final int CONFLICT = 40009;

    /**
     * 不支持的数据，媒体类型
     */
    public static final int UNSUPPORTED_TYPE = 40015;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 50000;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 50001;
}
