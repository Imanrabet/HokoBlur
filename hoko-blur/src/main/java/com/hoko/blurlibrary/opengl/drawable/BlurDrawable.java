package com.hoko.blurlibrary.opengl.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.hoko.blurlibrary.Blur;
import com.hoko.blurlibrary.api.IBlur;
import com.hoko.blurlibrary.api.IScreenBlur;
import com.hoko.blurlibrary.opengl.functor.DrawFunctor;
import com.hoko.blurlibrary.opengl.functor.ScreenBlurRenderer;
import com.hoko.blurlibrary.util.BlurUtil;

/**
 * Created by xiangpi on 16/11/23.
 */
public class BlurDrawable extends Drawable implements IBlur{

    private DrawFunctor mDrawFunctor;

    private IScreenBlur mBlurRenderer;

    private int alpha;

    private Paint mPaint;

    public BlurDrawable() {
        mBlurRenderer = new ScreenBlurRenderer();
        mDrawFunctor = new DrawFunctor(mBlurRenderer);
        mPaint = new Paint();
        mPaint.setColor(Color.TRANSPARENT);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (canvas.isHardwareAccelerated()) {
            mDrawFunctor.doDraw(canvas);
        } else {
            canvas.drawRect(getBounds(), mPaint);
        }
    }

    @Override
    @Deprecated
    public void setAlpha(int alpha) {
        // TODO: 2017/2/3
        this.alpha = alpha;
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // TODO: 2017/1/25  
    }

    @Override
    public int getOpacity() {
        return alpha == 255 ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setBlurMode(@Blur.BlurMode int mode) {
        if (mBlurRenderer != null) {
            mBlurRenderer.setBlurMode(mode);
            invalidateSelf();
        }
    }

    @Override
    public void setBlurRadius(int radius) {
        if (mBlurRenderer != null) {
            mBlurRenderer.setBlurRadius(BlurUtil.checkRadius(radius));
            invalidateSelf();
        }
    }

    @Override
    public void setSampleFactor(float factor) {
        if (mBlurRenderer != null) {
            mBlurRenderer.setSampleFactor(factor);
        }
        invalidateSelf();
    }

    @Override
    public int getBlurMode() {
        if (mBlurRenderer != null) {
            return mBlurRenderer.getBlurMode();
        }
        return Blur.MODE_BOX;
    }

    @Override
    public int getBlurRadius() {
        if (mBlurRenderer != null) {
            return mBlurRenderer.getBlurRadius();
        }
        return 0;
    }

    @Override
    public float getSampleFactor() {
        if (mBlurRenderer != null) {
            return mBlurRenderer.getSampleFactor();
        }
        return 1.0f;
    }

    public void freeGLResource() {
        if (mBlurRenderer != null) {
            mBlurRenderer.free();
        }
    }
}
