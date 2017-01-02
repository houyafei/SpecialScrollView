package com.romanticview.hyf.app20161229.lib;

/**
 * Created by Administrator on 2017/1/2.
 */
public interface SpScrollviewAble {

    /**
     * Called to discrollve the View.
     * @param ratio discrollve ratio between 0.0 and 1.0.
     *              1.0 means the View is totally discrollved
     */
    public void onSpscrollvew(float ratio);

    /**
     * Called to reset the discrollvation of the View.
     * In this method, you have to reset the View in order
     * to be discrollved again.
     */
    public void onResetSpscrollvew();
}
