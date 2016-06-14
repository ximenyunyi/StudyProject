package com.yyty.hao.studyproject.jni;

/**
 * @author 周祥浩
 * @version V1.0
 * @Description
 * @e-mail yyty208@gmail.com
 */

public class JniTest {
    static {
        System.loadLibrary("hello-jni");
    }
    public native String test();
}
