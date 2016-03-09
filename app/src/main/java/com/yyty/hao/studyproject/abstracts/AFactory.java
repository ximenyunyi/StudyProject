package com.yyty.hao.studyproject.abstracts;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description 抽象工厂类
 * @e-mail yyty208@gmail.com
 */

public abstract  class AFactory<E>{

    public abstract <T extends E> T creatSpecificObject(Class<T> c) ;
}
