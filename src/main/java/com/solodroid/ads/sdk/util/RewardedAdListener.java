package com.solodroid.ads.sdk.util;

public interface RewardedAdListener {
    void onRewardedAdLoaded();
    void onRewardedAdComplete();
    void onRewardedAdError();
    void onRewardedAdDismissed();
}
