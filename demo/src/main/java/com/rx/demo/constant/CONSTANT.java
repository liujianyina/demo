package com.rx.demo.constant;

public class CONSTANT {

    /**
     * 表名前缀
     */
    public final static String TABLE_PREFIX = "demo_";

    /**
     * 请求头的信息
     */
    public final static String AUTH = "Authorization";

    /**
     * 默认长度
     */
    public static final int DEFAULT_CODE_LENGTH = 4;

    /**
     *验证码key
     */
    public static final String CODE_KEY = "CODE_KEY";

    public static final class TURNOVER{
        public static final String TOP_UP = "充值金额：%s";
        public static final String BUY = "购买%s,订单金额：%s,%s金额：%s";
        public static final String TI = "提现金额%s，手续费：%s";
    }
}
