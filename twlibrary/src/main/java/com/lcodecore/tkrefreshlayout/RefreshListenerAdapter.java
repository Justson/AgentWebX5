package com.lcodecore.tkrefreshlayout;

public abstract class RefreshListenerAdapter implements PullListener {
        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
        }

        @Override
        public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
        }

        @Override
        public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
        }

        @Override
        public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
        }

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
        }

        @Override
        public void onFinishRefresh() {

        }

        @Override
        public void onFinishLoadMore() {

        }

        @Override
        public void onRefreshCanceled() {

        }

        @Override
        public void onLoadmoreCanceled() {

        }
}