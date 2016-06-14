#include <jni.h>
#include<string.h>
JNIEXPORT jstring JNICALL Java_com_yyty_hao_studyproject_jni_JniTest_test (JNIEnv* env, jobject obj){
      return (*env)->NewStringUTF(env, "Hello world from C!");

}