package com.yyty.hao.studyproject.enums;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description
 * @e-mail yyty208@gmail.com
 */

public enum  JudgmentEnum {
    TRUE(true),FALSE(false);

    private boolean value;

    private  JudgmentEnum(boolean value){
        this.value=value;
    }
    public boolean getValue() {
        return value;
    }
}
