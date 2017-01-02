package com.romanticview.hyf.app20161229.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/1/2.
 */
public class SpecialScrollView extends ScrollView {

    private SpecialScrollViewContent mContent ;

    public SpecialScrollView(Context context) {
        super(context);
    }

    public SpecialScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecialScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setupFirstView();
    }

    private void setupFirstView() {
        View first = mContent.getChildAt(0);
        if(first!=null){
            //设置第一个控件的高度
            first.getLayoutParams().height=getHeight() ;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(getChildCount()!=1){
            throw new IllegalStateException("SpecialScrollView must host one view");
        }

        View content = getChildAt(0);
        if(!(content instanceof SpecialScrollViewContent)){
            throw new IllegalStateException("SpecialScrollView must host a SpecialScrollViewContent");
        }

        mContent = (SpecialScrollViewContent) content;
        if(mContent.getChildCount() < 2) {
            throw new IllegalStateException("SpecialScrollView must have at least 2 children.");
        }

    }

    /**
     * l - Current horizontal scroll origin.
     t - Current vertical scroll origin.
     oldl - Previous horizontal scroll origin.
     oldt - Previous vertical scroll origin.
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        onScrollChanged(t);
    }

    private void onScrollChanged(int top) {
        int scrollViewHeight = getHeight();
        int scrollViewBottom = getAbsoluteButtom();
        int scrollViewHalfHeight = scrollViewHeight/2 ;

        for (int index=1;index<mContent.getChildCount();index++){
            View child = mContent.getChildAt(index);

            if(!(child instanceof SpScrollviewAble)){
                continue;
            }

            SpScrollviewAble spScrollviewAble = (SpScrollviewAble)child;
            int discrollvableTop = child.getTop();
            int discrollvableHeight = child.getHeight();
            int discrollvableAbsoluteTop = discrollvableTop - top;
            //这个view的下半部分
            if(scrollViewBottom - child.getBottom() < discrollvableHeight+scrollViewHalfHeight){
                //子view显示的时候执行
                if(discrollvableAbsoluteTop <= scrollViewHeight){
                    int visibleGap = scrollViewHeight - discrollvableAbsoluteTop;
                    spScrollviewAble.onSpscrollvew(clamp(visibleGap / (float) discrollvableHeight, 0.0f, 1.0f));
                }else {
                    //子view还没显示的时候
                    spScrollviewAble.onResetSpscrollvew();
                }
            }else {
                if (discrollvableAbsoluteTop <= scrollViewHalfHeight) {
                    int visibleGap = scrollViewHalfHeight - discrollvableAbsoluteTop;
                    spScrollviewAble.onSpscrollvew(clamp(visibleGap / (float) discrollvableHeight, 0.0f, 1.0f));
                } else {
                    spScrollviewAble.onResetSpscrollvew();
                }
            }
        }
    }

    private float clamp(float v, float max, float min) {
        return Math.max(Math.min(v,min),max);
    }

    private int getAbsoluteButtom() {
        View lastView = getChildAt(getChildCount()-1);
        if(lastView==null){
        return 0 ;
        }
        return lastView.getBottom();
    }
}
