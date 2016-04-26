//
// Created by yyty2 on 2016/4/6.
//

#include <jni.h>
#include <string.h>

 jstring  Java_com_yyty_hao_studyproject_activity_FirstJniActivity_printJNI(JNIEnv* env, jobject thiz,jstring strings){
         return (*env)->NewStringUTF(env,strings);
}

