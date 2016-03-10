package com.yyty.hao.studyproject.ImplA;

import com.yyty.hao.studyproject.abstracts.AFactory;
import com.yyty.hao.studyproject.abstracts.APattern;

/**
 * @author 周祥浩
 * @version V1.0  设计模式工厂类
 * @Description
 * @e-mail yyty208@gmail.com
 */

public class PatternFactory extends AFactory<APattern> {

    @Override
    public <T extends APattern> T creatSpecificObject(Class<T> c) {
        APattern pattern=null;
        try {
            pattern = (APattern) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)pattern;
    }
}
