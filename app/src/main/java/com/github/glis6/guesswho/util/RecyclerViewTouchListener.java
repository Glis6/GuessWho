package com.github.glis6.guesswho.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Glis
 */
public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    /**
     * The gesture detector to use.
     */
    private final GestureDetector gestureDetector;

    /**
     * The listener that will accept the press.
     */
    private final OnPressListener listener;

    /**
     * @param listener The listener that will accept the press.
     */
    public RecyclerViewTouchListener(final Context context, final OnPressListener listener) {
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (child != null && listener != null && gestureDetector.onTouchEvent(e)) {
            listener.onPress(child, recyclerView.getChildLayoutPosition(child));
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        //Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //Do nothing
    }
}
