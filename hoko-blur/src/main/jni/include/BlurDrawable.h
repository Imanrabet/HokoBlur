//
// Created by 橡皮 on 16/11/9.
//
#include <jni.h>
#include "DrawGlInfo.h"


#ifndef DYNAMICBLUR_BLURDRAWABLE_H
#define DYNAMICBLUR_BLURDRAWABLE_H

#ifdef __cplusplus
extern "C" {
#endif

using namespace android;
using namespace uirenderer;

JavaVM *javaVM;
jclass mFunctorClazz;
jclass mGlInfoClazz;

jobject mWeakRefFunctor;

JNIEXPORT jlong JNICALL
        Java_com_hoko_blurlibrary_opengl_functor_DrawFunctor_createNativeFunctor(JNIEnv *env, jobject clazz,
                                                                          jobject functor);
void postEventFromNativeC(int mode, void *info);

jobject * copyGlInfo(jobject * j_info, DrawGlInfo *c_info);

#ifdef __cplusplus
}
#endif

#endif //DYNAMICBLUR_BLURDRAWABLE_H
