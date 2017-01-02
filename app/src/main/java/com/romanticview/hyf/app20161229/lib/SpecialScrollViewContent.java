package com.romanticview.hyf.app20161229.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.romanticview.hyf.app20161229.R;

/**
 * Created by Administrator on 2016/12/29.
 */
public class SpecialScrollViewContent extends LinearLayout{

    public SpecialScrollViewContent(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public SpecialScrollViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public SpecialScrollViewContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(asSpscrollvable(child, (SpSclayoutParams) params), index, params);
    }

    private View asSpscrollvable(View child, SpSclayoutParams params) {

        //如果 不是自定义的参数，直接返回
        if(!isSpscrollvable(params)){
            return child;
        }

        SpScrollableView spScrollableView = new SpScrollableView(getContext());
        spScrollableView.setmSpscrollvewAlpha(params.isAlpha);
        spScrollableView.setmSpscrollvewScaleX(params.isScaleX);
        spScrollableView.setmSpscrollvewScaleY(params.isScaleY);

        spScrollableView.setmSpscrollvewFromBgColor(params.spscrollvew_fromBgColor);
        spScrollableView.setmSpscrollvewToBgColor(params.spscrollvew_toBgColor);
        spScrollableView.setmSpscrollvewThreshold(params.spscrollvew_threshold);
        spScrollableView.setmSpscrollvewTranslation(params.spscrollvew_translation);

        spScrollableView.addView(child);
        return spScrollableView;
    }

    private boolean isSpscrollvable(SpSclayoutParams params) {

        return params.isScaleY||
                params.isScaleX||
                params.isScaleY||
                params.spscrollvew_translation!=-1||
                (params.spscrollvew_fromBgColor!=-1&&params.spscrollvew_toBgColor!=-1);
    }


    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof SpSclayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new SpSclayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new SpSclayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new SpSclayoutParams(p.width,p.height);
    }

    /**
    * 自定义的layoutparams
    * */
    class SpSclayoutParams extends LinearLayout.LayoutParams{


        private boolean isAlpha ;
        private boolean isScaleX ;
        private boolean isScaleY ;

        private float spscrollvew_threshold ;
        private int spscrollvew_fromBgColor;
        private int spscrollvew_toBgColor ;
        private int spscrollvew_translation ;



        public SpSclayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //从属性集合中获取属性
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.SpScrollView_LayoutParams);
            try {


            isAlpha = typedArray.getBoolean(R.styleable.SpScrollView_LayoutParams_spscrollvew_alpha,false);
            isScaleX = typedArray.getBoolean(R.styleable.SpScrollView_LayoutParams_spscrollvew_scaleX,false);
            isScaleY = typedArray.getBoolean(R.styleable.SpScrollView_LayoutParams_spscrollvew_scaleY,false);

            spscrollvew_threshold = typedArray.getFloat(R.styleable.
                    SpScrollView_LayoutParams_spscrollvew_threshold, 0.0f);
            spscrollvew_fromBgColor = typedArray.getInt(R.styleable.
                    SpScrollView_LayoutParams_spscrollvew_fromBgColor, -1);
            spscrollvew_toBgColor = typedArray.getInt(R.styleable.
                    SpScrollView_LayoutParams_spscrollvew_toBgColor,-1);
            spscrollvew_translation = typedArray.getInt(R.styleable.
                    SpScrollView_LayoutParams_spscrollvew_translation,-1);
            }finally {
                //重复显示时，则需要再次加载资源数据
                typedArray.recycle();
            }
        }

        public SpSclayoutParams(int width,int height){
            super(width,height);
        }


    }
}
