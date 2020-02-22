package com.rx.demo.utils;


public class AjaxResult {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * layui分页插件的总数据
     */
    private Long count;

    /**
     * 数据
     */
    private Object data;

    public AjaxResult(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    private AjaxResult(AjaxResult_TYPE type, Long count, Object data) {
        this.code = type.code;
        this.msg = type.msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum AjaxResult_TYPE {

        SUCCESS(0, "成功"),

        FAIL(500, "失败"),

        UNLOGIN(399,"请您先登陆再进行操作"),

        UNAUTHORIZED(401, "无权限访问此数据");

        /**
         * 状态码
         */
        private Integer code;

        /**
         * 消息
         */
        private String msg;

        AjaxResult_TYPE(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static AjaxResult success(Long count, Object obj) {
        return new AjaxResult(AjaxResult_TYPE.SUCCESS, count, obj);
    }

    public static AjaxResult success(Object obj) {
        return success(0L, obj);
    }

    public static AjaxResult success() {
        return success(0L, null);
    }

    public static AjaxResult error(Object obj) {
        return new AjaxResult(AjaxResult_TYPE.FAIL, 0L, obj);
    }

    public static AjaxResult error() {
        return error(null);
    }

    public static AjaxResult unlogin(){
        return new AjaxResult(AjaxResult_TYPE.UNLOGIN,0L,null);
    }

    public static AjaxResult unauthorized() {
        return new AjaxResult(AjaxResult_TYPE.UNAUTHORIZED, 0L, null);
    }

    public static AjaxResult customize(Integer code, String msg, Long count, Object data) {
        return new AjaxResult(code, msg, count, data);
    }

}