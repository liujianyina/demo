package com.rx.demo.constant;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.omg.CORBA.PUBLIC_MEMBER;

public class STATUS {

    public enum PRODUCT_STATUS {
        /**
         * 开市
         */
        OPEN(1),

        /**
         * 休市
         */
        CLOSE(0);

        private int status;

        PRODUCT_STATUS(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    public enum ORDER_INX{

        /**
         * 买涨
         */
        UP(1),

        /**
         * 买跌
         */
        DOWN(2);


        private Integer inx;

        ORDER_INX(Integer inx) {
            this.inx = inx;
        }

        public Integer getInx() {
            return inx;
        }
    }

    /**
     * 订单状态
     */
    public enum ORDER_STATE{
        LOSE(0),

        PING(1),

        PROFIT(2);

        private Integer state;

        ORDER_STATE(Integer state) {
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }

    public enum DEAL{
        NOT_DEAL(0),

        ALREADY_DEAL(1),

        AUTO_DEAL(2);

        private Integer deal;

        DEAL(Integer deal) {
            this.deal = deal;
        }

        public Integer getDeal() {
            return deal;
        }
    }

    public enum SUCCESS{
        NOT_SUCCESS(0),

        ALREADY_SUCCESS(1);

        private Integer success;

        SUCCESS(Integer success) {
            this.success = success;
        }

        public Integer getSuccess() {
            return success;
        }
    }

    public enum WITHDRAW{
        REQUEST(0),

        PASS(1),

        REFUSE(2);

        private Integer state;

        WITHDRAW(Integer state) {
            this.state = state;
        }

        public Integer getState() {
            return state;
        }
    }


}
