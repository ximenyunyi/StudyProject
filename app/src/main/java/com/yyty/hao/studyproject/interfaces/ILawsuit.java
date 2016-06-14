package com.yyty.hao.studyproject.interfaces;


/**
 * 律师代理类
 */
public interface ILawsuit {
    /**
     * 提交申请
     */
    public void submit();

    /**
     * 进行举证
     */
    public void burden();

    /**
     * 开始辩护
     */
    public void defend();

    /**
     * 完成诉讼
     */
    public void finish();
}
