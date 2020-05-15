package com.xl.aspectjplugindemo.aop;

import android.os.Looper;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ViewAop {
    @Around("execution(void android.view.View+.on*(..))")
    public void getSetContentViewTimeByCustomeView(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) { //主线程
            Log.i("xiongliang", name + " cost " + (System.currentTimeMillis() - time));
        }
    }
}
