package com.solodroid.ads.sdk.format;

import static com.solodroid.ads.sdk.util.Constant.ADMOB;
import static com.solodroid.ads.sdk.util.Constant.AD_STATUS_ON;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_DISCOVERY;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_ADMOB;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_IRONSOURCE;
import static com.solodroid.ads.sdk.util.Constant.GOOGLE_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.IRONSOURCE;
import static com.solodroid.ads.sdk.util.Constant.MOPUB;
import static com.solodroid.ads.sdk.util.Constant.NONE;
import static com.solodroid.ads.sdk.util.Constant.WORTISE;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;
import com.solodroid.ads.sdk.R;
//import com.solodroid.ads.sdk.helper.AppLovinCustomEventBanner;
import com.solodroid.ads.sdk.helper.Log;
import com.unity3d.mediation.LevelPlayAdError;
import com.unity3d.mediation.LevelPlayAdInfo;
import com.unity3d.mediation.LevelPlayAdSize;
import com.unity3d.mediation.banner.LevelPlayBannerAdView;
import com.unity3d.mediation.banner.LevelPlayBannerAdViewListener;

public class BannerAd {

    /** @noinspection deprecation*/
    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
        private AppLovinAdView appLovinAdView;
        FrameLayout ironSourceBannerView;
        private IronSourceBannerLayout ironSourceBannerLayout;
        FrameLayout wortiseBannerView;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobBannerId = "";
        private String googleAdManagerBannerId = "";
        private String fanBannerId = "";
        private String unityBannerId = "";
        MaxAdView maxAdView;
        private String appLovinBannerId = "";
        private String appLovinBannerZoneId = "";
        private String mopubBannerId = "";
        private String ironSourceBannerId = "";
        private String wortiseBannerId = "";
        private String alienAdsBannerId = "";
        private int placementStatus = 1;
        private boolean darkTheme = false;
        private boolean legacyGDPR = false;

        LevelPlayBannerAdView levelPlayBanner;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadBannerAd();
            return this;
        }

        public Builder setAdStatus(String adStatus) {
            this.adStatus = adStatus;
            return this;
        }

        public Builder setAdNetwork(String adNetwork) {
            this.adNetwork = adNetwork;
            return this;
        }

        public Builder setBackupAdNetwork(String backupAdNetwork) {
            this.backupAdNetwork = backupAdNetwork;
            return this;
        }

        public Builder setAdMobBannerId(String adMobBannerId) {
            this.adMobBannerId = adMobBannerId;
            return this;
        }

        public Builder setGoogleAdManagerBannerId(String googleAdManagerBannerId) {
            this.googleAdManagerBannerId = googleAdManagerBannerId;
            return this;
        }

        public Builder setFanBannerId(String fanBannerId) {
            this.fanBannerId = fanBannerId;
            return this;
        }

        public Builder setUnityBannerId(String unityBannerId) {
            this.unityBannerId = unityBannerId;
            return this;
        }

        public Builder setAppLovinBannerId(String appLovinBannerId) {
            this.appLovinBannerId = appLovinBannerId;
            return this;
        }

        public Builder setAppLovinBannerZoneId(String appLovinBannerZoneId) {
            this.appLovinBannerZoneId = appLovinBannerZoneId;
            return this;
        }

        public Builder setMopubBannerId(String mopubBannerId) {
            this.mopubBannerId = mopubBannerId;
            return this;
        }

        public Builder setIronSourceBannerId(String ironSourceBannerId) {
            this.ironSourceBannerId = ironSourceBannerId;
            return this;
        }

        public Builder setWortiseBannerId(String wortiseBannerId) {
            this.wortiseBannerId = wortiseBannerId;
            return this;
        }

        public Builder setAlienAdsBannerId(String alienAdsBannerId) {
            this.alienAdsBannerId = alienAdsBannerId;
            return this;
        }

        public Builder setPlacementStatus(int placementStatus) {
            this.placementStatus = placementStatus;
            return this;
        }

        public Builder setDarkTheme(boolean darkTheme) {
            this.darkTheme = darkTheme;
            return this;
        }

        public Builder setLegacyGDPR(boolean legacyGDPR) {
            this.legacyGDPR = legacyGDPR;
            return this;
        }
        LinearLayout mbannerAdView;
        public Builder setbannerAdView(LinearLayout bannerAdView){
            this.mbannerAdView = bannerAdView;
            return this;
        }

        public void loadBannerAd() {
            RelativeLayout appLovinAdView = activity.findViewById(R.id.applovin_banner_view_container);
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        maxAdView = new MaxAdView(appLovinBannerId, activity);
                        maxAdView.setListener(new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdCollapsed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                appLovinAdView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                appLovinAdView.setVisibility(View.GONE);
                                loadBackupBannerAd();
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {

                            }
                        });

                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                        int heightPx = activity.getResources().getDimensionPixelSize(R.dimen.applovin_banner_height);
                        maxAdView.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));
                        if (darkTheme) {
                            maxAdView.setBackgroundColor(activity.getResources().getColor(R.color.color_native_background_dark));
                        } else {
                            maxAdView.setBackgroundColor(activity.getResources().getColor(R.color.color_native_background_light));
                        }
                        if (appLovinAdView != null) {
                            appLovinAdView.addView(maxAdView);
                        }else
                            Log.d(TAG, "appLovinAdView is null");
//                        appLovinAdView.addView(maxAdView);
                        maxAdView.loadAd();
                        Log.d(TAG, adNetwork + " Banner Ad unit Id : " + appLovinBannerId);
                        break;

                    case APPLOVIN_DISCOVERY:
                        RelativeLayout appLovinDiscoveryAdView = activity.findViewById(R.id.applovin_discovery_banner_view_container);
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", appLovinBannerZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        this.appLovinAdView = new AppLovinAdView(adSize, activity);
                        this.appLovinAdView.setAdLoadListener(new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinDiscoveryAdView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                appLovinDiscoveryAdView.setVisibility(View.GONE);
                                loadBackupBannerAd();
                            }
                        });
                        appLovinDiscoveryAdView.addView(this.appLovinAdView);
                        this.appLovinAdView.loadNextAd();
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
//                        View rootView = LayoutInflater.from(activity).inflate(R.layout.view_banner_ad, null);
                        ironSourceBannerView = activity.findViewById(R.id.ironsource_banner_view_container);


                        levelPlayBanner = new LevelPlayBannerAdView(activity, ironSourceBannerId);
                        LevelPlayAdSize size = LevelPlayAdSize.createAdaptiveAdSize(activity);
                        if (size != null) {
                            levelPlayBanner.setAdSize(size);
                        }
                        ironSourceBannerView.addView(levelPlayBanner);
                        levelPlayBanner.setBannerListener(new LevelPlayBannerAdViewListener() {
                                @Override
                                public void onAdLoaded(@NonNull LevelPlayAdInfo adInfo) {
                                    // Ad was loaded successfully
                                    Log.w(TAG, "onAdLoaded = " + adInfo);
                                    ironSourceBannerView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAdLoadFailed(@NonNull LevelPlayAdError error) {
                                    // Ad load failed
                                    Log.w(TAG, "onAdLoadFailed = " + error.getErrorMessage());
                                    loadBackupBannerAd();
                                }

                                @Override
                                public void onAdDisplayed(@NonNull LevelPlayAdInfo adInfo) {
                                    // Ad was displayed and visible on screen
                                    Log.w(TAG, "onAdDisplayed = " + adInfo);
                                    if (maxAdView != null) {
                                        maxAdView.destroy();
                                        if (appLovinAdView != null) {
                                            appLovinAdView.setVisibility(View.GONE);
                                        }
                                    }
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull LevelPlayAdInfo adInfo, @NonNull LevelPlayAdError error) {
                                    // Ad failed to be displayed on screen
                                    Log.w(TAG, "onAdDisplayFailed = " + adInfo + " adIError = " + error.getErrorMessage());
//                                    loadBackupBannerAd();
                                }

                                @Override
                                public void onAdClicked(@NonNull LevelPlayAdInfo adInfo) {
                                    // Ad was clicked
                                }

                                @Override
                                public void onAdExpanded(@NonNull LevelPlayAdInfo adInfo) {
                                    // Ad is opened on full screen
                                    Log.w(TAG, "onAdExpanded = " + adInfo);
                                }

                                @Override
                                public void onAdCollapsed(@NonNull LevelPlayAdInfo adInfo) {
                                    // Ad is restored to its original size
                                    Log.w(TAG, "onAdCollapsed = " + adInfo);
                                }

                                @Override
                                public void onAdLeftApplication(@NonNull LevelPlayAdInfo adInfo) {
                                    Log.w(TAG, "onAdLeftApplication = " + adInfo);
                                }
                                // User pressed on the ad and was navigated out of the app
                        });
                        levelPlayBanner.loadAd();
                        break;

                    case MOPUB:
                    case WORTISE:
                    case NONE:
                        //do nothing
                        break;
                }
                Log.d(TAG, "Banner Ad is enabled");
            } else {
                Log.d(TAG, "Banner Ad is disabled");
            }
        }

        public void loadBackupBannerAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        RelativeLayout appLovinAdView = activity.findViewById(R.id.applovin_banner_view_container);
                        maxAdView = new MaxAdView(appLovinBannerId, activity);
                        maxAdView.setListener(new MaxAdViewAdListener() {
                            @Override
                            public void onAdExpanded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdCollapsed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                appLovinAdView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                appLovinAdView.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {

                            }
                        });

                        int width = ViewGroup.LayoutParams.MATCH_PARENT;
                        int heightPx = activity.getResources().getDimensionPixelSize(R.dimen.applovin_banner_height);
                        maxAdView.setLayoutParams(new FrameLayout.LayoutParams(width, heightPx));
                        if (darkTheme) {
                            maxAdView.setBackgroundColor(activity.getResources().getColor(R.color.color_native_background_dark));
                        } else {
                            maxAdView.setBackgroundColor(activity.getResources().getColor(R.color.color_native_background_light));
                        }
                        appLovinAdView.addView(maxAdView);
                        maxAdView.loadAd();
                        Log.d(TAG, adNetwork + " Banner Ad unit Id : " + appLovinBannerId);
                        break;

                    case APPLOVIN_DISCOVERY:
                        RelativeLayout appLovinDiscoveryAdView = activity.findViewById(R.id.applovin_discovery_banner_view_container);
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", appLovinBannerZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        this.appLovinAdView = new AppLovinAdView(adSize, activity);
                        this.appLovinAdView.setAdLoadListener(new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinDiscoveryAdView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                appLovinDiscoveryAdView.setVisibility(View.GONE);
                            }
                        });
                        appLovinDiscoveryAdView.addView(this.appLovinAdView);
                        this.appLovinAdView.loadNextAd();
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        ironSourceBannerView = activity.findViewById(R.id.ironsource_banner_view_container);


                        levelPlayBanner = new LevelPlayBannerAdView(activity, ironSourceBannerId);
                        LevelPlayAdSize size = LevelPlayAdSize.createAdaptiveAdSize(activity);
                        if (size != null) {
                            levelPlayBanner.setAdSize(size);
                        }
                        ironSourceBannerView.addView(levelPlayBanner);
                        levelPlayBanner.setBannerListener(new LevelPlayBannerAdViewListener() {
                            @Override
                            public void onAdLoaded(@NonNull LevelPlayAdInfo adInfo) {
                                // Ad was loaded successfully
                                Log.w(TAG, "onAdLoaded = " + adInfo);
                                ironSourceBannerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdLoadFailed(@NonNull LevelPlayAdError error) {
                                // Ad load failed
                                Log.w(TAG, "onAdLoadFailed = " + error.getErrorMessage());
                                ironSourceBannerView.setVisibility(View.GONE);
//                                loadBackupBannerAd();
                            }

                            @Override
                            public void onAdDisplayed(@NonNull LevelPlayAdInfo adInfo) {
                                // Ad was displayed and visible on screen
                                Log.w(TAG, "onAdDisplayed = " + adInfo);
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull LevelPlayAdInfo adInfo, @NonNull LevelPlayAdError error) {
                                // Ad failed to be displayed on screen
                                Log.w(TAG, "onAdDisplayFailed = " + adInfo + " adIError = " + error.getErrorMessage());
//                                    loadBackupBannerAd();
                            }

                            @Override
                            public void onAdClicked(@NonNull LevelPlayAdInfo adInfo) {
                                // Ad was clicked
                            }

                            @Override
                            public void onAdExpanded(@NonNull LevelPlayAdInfo adInfo) {
                                // Ad is opened on full screen
                                Log.w(TAG, "onAdExpanded = " + adInfo);
                            }

                            @Override
                            public void onAdCollapsed(@NonNull LevelPlayAdInfo adInfo) {
                                // Ad is restored to its original size
                                Log.w(TAG, "onAdCollapsed = " + adInfo);
                            }

                            @Override
                            public void onAdLeftApplication(@NonNull LevelPlayAdInfo adInfo) {
                                Log.w(TAG, "onAdLeftApplication = " + adInfo);
                            }
                            // User pressed on the ad and was navigated out of the app
                        });
                        levelPlayBanner.loadAd();
                        break;

                    case MOPUB:
                    case WORTISE:
                        break;
                }
                Log.d(TAG, "Banner Ad is enabled");
            } else {
                Log.d(TAG, "Banner Ad is disabled");
            }
        }

        public void destroyAndDetachBanner() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (adNetwork.equals(IRONSOURCE) || backupAdNetwork.equals(IRONSOURCE)) {
                    if (ironSourceBannerView != null && levelPlayBanner != null) {
                        Log.d(TAG, "ironSource banner is not null, ready to destroy");
//                        IronSource.destroyBanner(ironSourceBannerLayout);
                        levelPlayBanner.destroy();
                        levelPlayBanner = null;
                        ironSourceBannerView.removeView(ironSourceBannerLayout);
                    } else {
                        Log.d(TAG, "ironSource banner is null");
                    }
                }
                if (adNetwork.equals(APPLOVIN) || backupAdNetwork.equals(APPLOVIN)) {
                    if (maxAdView != null) {
                        Log.d(TAG, "appLovinAdView is not null, ready to destroy");
                        maxAdView.destroy();
                        maxAdView = null;
                    } else {
                        Log.d(TAG, "appLovinAdView is null");
                    }
                }
            }
        }

        public void onPauseBanner(){
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (adNetwork.equals(IRONSOURCE) || backupAdNetwork.equals(IRONSOURCE)) {
                    if (ironSourceBannerView != null && levelPlayBanner != null) {
                        Log.d(TAG, "ironSource banner is not null, Pause refresh");
//                        IronSource.destroyBanner(ironSourceBannerLayout);
                        levelPlayBanner.pauseAutoRefresh();
                    } else {
                        Log.d(TAG, "ironSource banner is null");
                    }
                }
            }
        }
        public void onResumeBanner(){
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (adNetwork.equals(IRONSOURCE) || backupAdNetwork.equals(IRONSOURCE)) {
                    if (ironSourceBannerView != null && levelPlayBanner != null) {
                        Log.d(TAG, "ironSource banner is not null, Resume refresh");
//                        IronSource.destroyBanner(ironSourceBannerLayout);
                        levelPlayBanner.resumeAutoRefresh();
                    } else {
                        Log.d(TAG, "ironSource banner is null");
                    }
                }
            }
        }

    }

}
