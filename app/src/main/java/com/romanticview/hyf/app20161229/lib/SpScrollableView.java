package com.romanticview.hyf.app20161229.lib;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/1/2.
 */
public class SpScrollableView extends FrameLayout implements SpScrollviewAble {


    private static final int TRANSLATIONFROMTOP = 0x01 ;
    private static final int TRANSLATIONFROMBUTTOM = 0x02 ;
    private static final int TRANSLATIONFROMLEFT = 0x04 ;
    private static final int TRANSLATIONFROMRIGHT = 0x08 ;

    //色彩渐变动画 估算器
    private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();

    //视图属性值
    private float mSpscrollvewThreshold;
    private int mSpscrollvewFromBgColor;
    private int mSpscrollvewToBgColor;
    private boolean mSpscrollvewAlpha;
    private int mSpscrollvewTranslation;
    private boolean mSpscrollvewScaleX;
    private boolean mSpscrollvewScaleY;

    //视图宽高
    private int mWidth;
    private int mHeight;


    public SpScrollableView(Context context) {
        super(context);
    }

    public SpScrollableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpScrollableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w ;
        mHeight = h ;
    }

    public void setmSpscrollvewAlpha(boolean mSpscrollvewAlpha) {
        this.mSpscrollvewAlpha = mSpscrollvewAlpha;
    }

    public void setmSpscrollvewTranslation(int mSpscrollvewTranslation) {
        this.mSpscrollvewTranslation = mSpscrollvewTranslation;
//        if(){
//
//        }
    }

    public void setmSpscrollvewThreshold(float mSpscrollvewThreshold) {
        this.mSpscrollvewThreshold = mSpscrollvewThreshold;
        if(this.mSpscrollvewThreshold<0.0f||this.mSpscrollvewThreshold>1.0f){
            throw new IllegalStateException("SpscrollvewThreshold must be between 0.0f and 1.0f");
        }
    }

    public void setmSpscrollvewFromBgColor(int mSpscrollvewFromBgColor) {
        this.mSpscrollvewFromBgColor = mSpscrollvewFromBgColor;
    }

    public void setmSpscrollvewToBgColor(int mSpscrollvewToBgColor) {
        this.mSpscrollvewToBgColor = mSpscrollvewToBgColor;
    }


    public void setmSpscrollvewScaleX(boolean mSpscrollvewScaleX) {
        this.mSpscrollvewScaleX = mSpscrollvewScaleX;
    }

    public void setmSpscrollvewScaleY(boolean mSpscrollvewScaleY) {
        this.mSpscrollvewScaleY = mSpscrollvewScaleY;
    }

    @Override
    public void onSpscrollvew(float ratio) {
        if (ratio>=mSpscrollvewThreshold){
            ratio = withThread(ratio);

            float ratioInverse = 1-ratio ;

            if(mSpscrollvewAlpha){
                setAlpha(ratio);
            }
            if(isDiscrollveTranslationFrom(TRANSLATIONFROMBUTTOM)){
                setTranslationX(mHeight * ratioInverse);
            }

            if (isDiscrollveTranslationFrom(TRANSLATIONFROMTOP)){
                setTranslationY(-mHeight * ratioInverse);
            }

            if(isDiscrollveTranslationFrom(TRANSLATIONFROMLEFT)){
                setTranslationX(-mWidth*ratioInverse);
            }

            if(isDiscrollveTranslationFrom(TRANSLATIONFROMRIGHT)){
                setTranslationX(mWidth*ratioInverse);
            }

            if(mSpscrollvewScaleX){
                setScaleX(ratio);
            }

            if(mSpscrollvewScaleY){
                setScaleY(ratio);
            }

            if(mSpscrollvewFromBgColor!=-1 && mSpscrollvewToBgColor!=-1){
                setBackgroundColor((Integer) sArgbEvaluator.evaluate(ratio,
                        mSpscrollvewFromBgColor, mSpscrollvewToBgColor));
            }

        }
    }

    private float withThread(float ratio) {

        return (ratio-mSpscrollvewThreshold)/(1-mSpscrollvewThreshold);

    }

    private boolean isDiscrollveTranslationFrom(int translationMask) {
        if(mSpscrollvewTranslation == -1) {
            return false;
        }
        return (mSpscrollvewTranslation & translationMask) == translationMask;
    }

    @Override
    public void onResetSpscrollvew() {
        if(mSpscrollvewAlpha) {
            setAlpha(0.0f);
        }
        if(isDiscrollveTranslationFrom(TRANSLATIONFROMBUTTOM)) {
            setTranslationY(mHeight);
        }
        if(isDiscrollveTranslationFrom(TRANSLATIONFROMTOP)) {
            setTranslationY(-mHeight);
        }
        if(isDiscrollveTranslationFrom(TRANSLATIONFROMLEFT)) {
            setTranslationX(-mWidth);
        }
        if(isDiscrollveTranslationFrom(TRANSLATIONFROMRIGHT)) {
            setTranslationX(mWidth);
        }
        if(mSpscrollvewScaleX) {
            setScaleX(0.0f);
        }
        if(mSpscrollvewScaleY) {
            setScaleY(0.0f);
        }
        if(mSpscrollvewFromBgColor != -1 && mSpscrollvewToBgColor != -1) {
            setBackgroundColor(mSpscrollvewFromBgColor);
        }
    }
}
