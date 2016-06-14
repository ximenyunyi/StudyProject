package com.yyty.hao.studyproject.business;


import com.yyty.hao.studyproject.interfaces.ILawsuit;

/**
 * 需要辩护人
 */
public class XiaoMing implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("提交申请");
    }

    @Override
    public void burden() {
        System.out.println("提交申请");
    }

    @Override
    public void defend() {
        System.out.println("开始辩护");
    }

    @Override
    public void finish() {
        System.out.println("完成诉讼");
    }


}
