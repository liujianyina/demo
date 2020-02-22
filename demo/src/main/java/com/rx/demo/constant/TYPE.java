package com.rx.demo.constant;

public class TYPE {

    public enum TURNOVER_TYPE{

        ADD(0),

        BUY(1),

        WITHDRAW(2);

        private Integer type;

        TURNOVER_TYPE(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }
    }

}
