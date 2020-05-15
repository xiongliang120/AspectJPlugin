package com.xl.aspectjplugindemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomeTextView extends androidx.appcompat.widget.AppCompatTextView {
    public CustomeTextView(Context context) {
        super(context);
    }

    public CustomeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("xiongliang","CustomeTextView onDraw");
    }
}
