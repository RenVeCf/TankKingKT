package com.ipd.tankking.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ipd.tankking.R;

import java.util.ArrayList;
import java.util.List;


public class ProgressLayout extends RelativeLayout {

    private static final String LOADING_TAG = "ProgressLayout.LOADING_TAG";
    private static final String ERROR_TAG = "ProgressLayout.ERROR_TAG";
    private static final String EMPTY_TAG = "ProgressLayout.EMPTY_TAG";

    private LayoutInflater inflater;
    private LayoutParams layoutParams;
    private View loadingGroup;
    private View errorGroup;
    private View emptyGroup;

    private List<View> contentViews = new ArrayList<>();

    private enum State {
        LOADING, CONTENT, ERROR, EMPTY
    }

    private State currentState = State.LOADING;

    public ProgressLayout(Context context) {
        super(context);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || (!child.getTag().equals(LOADING_TAG) && !child.getTag()
                .equals(ERROR_TAG) && !child.getTag().equals(EMPTY_TAG))) {
            contentViews.add(child);
        }
    }

    private int loadingViewRes = R.layout.layout_progress;
    private int errorViewRes = R.layout.layout_error;
    private int emptyViewRes = R.layout.empty_base;

    public void setLoadingViewRes(int resId) {
        loadingViewRes = resId;
    }

    public void setErrorViewRes(int resId) {
        errorViewRes = resId;
    }

    public void setEmptyViewRes(int resId) {
        emptyViewRes = resId;
    }

    public void setEmptyViewRes(View emptyView) {
        emptyGroup = emptyView;
    }

    public View getEmptyViewRes(int resId) {
        emptyGroup = getView(resId, EMPTY_TAG);
        return emptyGroup;
    }

    public View getEmptyView() {
        if (emptyGroup == null) emptyGroup = getView(emptyViewRes, EMPTY_TAG);
        return this.emptyGroup;
    }


    public void showLoading() {

        ProgressLayout.this.showLoadingView();
        ProgressLayout.this.hideErrorView();
        ProgressLayout.this.hideEmptyView();
        ProgressLayout.this.setContentVisibility(false);
        currentState = State.LOADING;
    }

    public void showContent() {

        ProgressLayout.this.hideLoadingView();
        ProgressLayout.this.hideErrorView();
        ProgressLayout.this.hideEmptyView();
        ProgressLayout.this.setContentVisibility(true);
        currentState = State.CONTENT;
    }

    public void showEmpty() {

        ProgressLayout.this.hideLoadingView();
        ProgressLayout.this.hideErrorView();
        ProgressLayout.this.setContentVisibility(false);
        ProgressLayout.this.showEmptyView();
        currentState = State.EMPTY;
    }

    public void showError(@StringRes int stringId, @NonNull OnClickListener onClickListener) {
        showError(getResources().getString(stringId), onClickListener);
    }

    public void showError(String str, @NonNull OnClickListener onClickListener) {

        ProgressLayout.this.hideLoadingView();
        ProgressLayout.this.hideEmptyView();
        ProgressLayout.this.showErrorView();

        ((TextView) errorGroup.findViewById(R.id.tv_error_hint)).setText(str);
        errorGroup.setOnClickListener(onClickListener);
        ProgressLayout.this.setContentVisibility(false);
        currentState = State.ERROR;
    }

    public State getCurrentState() {
        return currentState;
    }

    public boolean isContent() {
        return currentState == State.CONTENT;
    }

    public boolean isLoading() {
        return currentState == State.LOADING;
    }

    public boolean isError() {
        return currentState == State.ERROR;
    }

    public boolean isEmpty() {
        return currentState == State.EMPTY;
    }

    private void showLoadingView() {
        if (loadingGroup == null) {
            loadingGroup = getView(loadingViewRes, LOADING_TAG);
        } else {
            loadingGroup.setVisibility(VISIBLE);
        }
    }

    private void hideLoadingView() {
        if (loadingGroup != null && loadingGroup.getVisibility() != GONE) {
            loadingGroup.setVisibility(GONE);
        }
    }

    private void showErrorView() {
        if (errorGroup == null) {
            errorGroup = getView(errorViewRes, ERROR_TAG);
        } else {
            errorGroup.setVisibility(VISIBLE);
        }
    }

    private void hideErrorView() {
        if (errorGroup != null && errorGroup.getVisibility() != GONE) {
            errorGroup.setVisibility(GONE);
        }
    }

    public void showEmptyView() {
        if (emptyGroup == null) {
            emptyGroup = getView(emptyViewRes, EMPTY_TAG);
        } else {
            emptyGroup.setVisibility(VISIBLE);
        }
        currentState = State.EMPTY;
    }

    public void hideEmptyView() {
        if (emptyGroup != null && emptyGroup.getVisibility() != GONE) {
            emptyGroup.setVisibility(GONE);
        }
    }

    private View getView(int viewRes, String tag) {
        View stateView = inflater.inflate(viewRes, null);
        stateView.setTag(tag);

        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(CENTER_IN_PARENT);

        ProgressLayout.this.addView(stateView, layoutParams);
        return stateView;
    }


    private void setContentVisibility(boolean visible) {
        for (View contentView : contentViews) {
            contentView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }


}