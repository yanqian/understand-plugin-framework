package com.weishu.upf.dynamic_proxy_hook.app2.hook;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yanqiang on 2017/7/24.
 */

public class ActivityHookHelper {

    public static void hookInstrumentation(Activity activity) throws Exception{

        Field mInstrumentationField = activity.getClass().getSuperclass().getDeclaredField("mInstrumentation");
        // 拿到原始的 mInstrumentation字段
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(activity);

        // 创建代理对象
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(activity, evilInstrumentation);
    }
}
