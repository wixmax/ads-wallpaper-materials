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
import static com.solodroid.ads.sdk.util.Constant.STARTAPP;
import static com.solodroid.ads.sdk.util.Constant.UNITY;
import static com.solodroid.ads.sdk.util.Constant.WORTISE;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
//import com.google.android.gms.ads.AdError;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
//import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//import com.solodroid.ads.sdk.helper.AppLovinCustomEventInterstitial;
import com.solodroid.ads.sdk.helper.Log;
import com.solodroid.ads.sdk.util.OnInterstitialAdDismissedListener;
import com.solodroid.ads.sdk.util.OnInterstitialAdShowedListener;
import com.unity3d.mediation.LevelPlayAdError;
import com.unity3d.mediation.LevelPlayAdInfo;
import com.unity3d.mediation.interstitial.LevelPlayInterstitialAd;
import com.unity3d.mediation.interstitial.LevelPlayInterstitialAdListener;

import java.util.concurrent.TimeUnit;

public class InterstitialAd {

    /** @noinspection deprecation*/
    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
//        private com.google.android.gms.ads.interstitial.InterstitialAd adMobInterstitialAd;
//        private AdManagerInterstitialAd adManagerInterstitialAd;
        private MaxInterstitialAd maxInterstitialAd;
        public AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        public AppLovinAd appLovinAd;
        private int retryAttempt;
        private int counter = 1;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobInterstitialId = "";
        private String googleAdManagerInterstitialId = "";
        private String fanInterstitialId = "";
        private String unityInterstitialId = "";
        private String appLovinInterstitialId = "";
        private String appLovinInterstitialZoneId = "";
        private String mopubInterstitialId = "";
        private String ironSourceInterstitialId = "";
        LevelPlayInterstitialAd mInterstitialAd;
        private String wortiseInterstitialId = "";
        private String alienAdsInterstitialId = "";
        private int placementStatus = 1;
        private int interval = 3;
        private boolean legacyGDPR = false;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadInterstitialAd();
            return this;
        }

        public Builder build(OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
            loadInterstitialAd(onInterstitialAdDismissedListener);
            return this;
        }

        public void show() {
            showInterstitialAd();
        }

        public void show(OnInterstitialAdShowedListener onInterstitialAdShowedListener, OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
            showInterstitialAd(onInterstitialAdShowedListener, onInterstitialAdDismissedListener);
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

        public Builder setAdMobInterstitialId(String adMobInterstitialId) {
            this.adMobInterstitialId = adMobInterstitialId;
            return this;
        }

        public Builder setGoogleAdManagerInterstitialId(String googleAdManagerInterstitialId) {
            this.googleAdManagerInterstitialId = googleAdManagerInterstitialId;
            return this;
        }

        public Builder setFanInterstitialId(String fanInterstitialId) {
            this.fanInterstitialId = fanInterstitialId;
            return this;
        }

        public Builder setUnityInterstitialId(String unityInterstitialId) {
            this.unityInterstitialId = unityInterstitialId;
            return this;
        }

        public Builder setAppLovinInterstitialId(String appLovinInterstitialId) {
            this.appLovinInterstitialId = appLovinInterstitialId;
            return this;
        }

        public Builder setAppLovinInterstitialZoneId(String appLovinInterstitialZoneId) {
            this.appLovinInterstitialZoneId = appLovinInterstitialZoneId;
            return this;
        }

        public Builder setMopubInterstitialId(String mopubInterstitialId) {
            this.mopubInterstitialId = mopubInterstitialId;
            return this;
        }

        public Builder setIronSourceInterstitialId(String ironSourceInterstitialId) {
            this.ironSourceInterstitialId = ironSourceInterstitialId;
            return this;
        }

        public Builder setWortiseInterstitialId(String wortiseInterstitialId) {
            this.wortiseInterstitialId = wortiseInterstitialId;
            return this;
        }

        public Builder setAlienAdsInterstitialId(String alienAdsInterstitialId) {
            this.alienAdsInterstitialId = alienAdsInterstitialId;
            return this;
        }

        public Builder setPlacementStatus(int placementStatus) {
            this.placementStatus = placementStatus;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setLegacyGDPR(boolean legacyGDPR) {
            this.legacyGDPR = legacyGDPR;
            return this;
        }

        public void loadInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (adNetwork) {
                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        mInterstitialAd = new LevelPlayInterstitialAd(ironSourceInterstitialId);
                        mInterstitialAd.setListener(new LevelPlayInterstitialAdListener() {
                            @Override
                            public void onAdLoaded(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was loaded successfully
                                Log.d(TAG, "onInterstitialAdReady");
                            }

                            @Override
                            public void onAdLoadFailed(LevelPlayAdError levelPlayAdError) {
                                // Ad load failed
                                Log.d(TAG, "onAdLoadFailed "+ levelPlayAdError.getErrorMessage());
                                loadBackupInterstitialAd();
                            }

                            @Override
                            public void onAdDisplayed(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was displayed and visible on screen
                                Log.d(TAG, "onInterstitialAdShowSucceeded");

                            }
                            @Override
                            public void onAdClosed(@NonNull LevelPlayAdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdClosed");
                                loadInterstitialAd();
                            }
                        });
                        mInterstitialAd.loadAd();












                        // Create the interstitial ad object
//                        mInterstitialAd = new LevelPlayInterstitialAd(ironSourceInterstitialId);
//                        mInterstitialAd.setListener(new LevelPlayInterstitialAdListener() {
//                            @Override
//                            public void onAdLoaded(LevelPlayAdInfo levelPlayAdInfo) {
//                                // Ad was loaded successfully
//                                log.d(TAG, "onInterstitialAdReady");
//                            }
//                            @Override
//                            public void onAdLoadFailed(LevelPlayAdError levelPlayAdError) {
//                                // Ad load failed
//                                log.d(TAG, "onAdLoadFailed "+ levelPlayAdError.getErrorMessage());
//                                loadBackupInterstitialAd();
//                            }
//                            @Override
//                            public void onAdDisplayed(LevelPlayAdInfo levelPlayAdInfo) {
//                                // Ad was displayed and visible on screen
//                                log.d(TAG, "onInterstitialAdShowSucceeded");
//                            }
//                            @Override
//                            public void onAdDisplayFailed(LevelPlayAdError levelPlayAdError, LevelPlayAdInfo levelPlayAdInfo) {
//                                // Ad fails to be displayed
//                                // Optional
//                                log.d(TAG, "onInterstitialAdShowFailed" + " " + levelPlayAdError + " " + levelPlayAdInfo);
//                                loadBackupInterstitialAd();
//                            }
//                            @Override
//                            public void onAdClicked(LevelPlayAdInfo levelPlayAdInfo) {
//                                // Ad was clicked
//                                // Optional
//                                log.d(TAG, "onInterstitialAdClicked");
//                            }
//                            @Override
//                            public void onAdClosed(LevelPlayAdInfo levelPlayAdInfo) {
//                                // Ad was closed
//                                // Optional
//                                log.d(TAG, "onInterstitialAdClosed");
//                                loadInterstitialAd();
//                            }
//                            @Override
//                            public void onAdInfoChanged(LevelPlayAdInfo levelPlayAdInfo) {
//                                // Called after the ad info is updated. Available when another interstitial ad has loaded, and includes a higher CPM/Rate
//                                // Optional
//                                log.d(TAG, "another interstitial ad has loaded with higher CPM/Rate");
//                            }
//                        });
//                        mInterstitialAd.loadAd();

                        break;
                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        maxInterstitialAd = new MaxInterstitialAd(appLovinInterstitialId, activity);
                        maxInterstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                retryAttempt = 0;
                                Log.d(TAG, "AppLovin Interstitial Ad loaded...");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxInterstitialAd.loadAd();
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                retryAttempt++;
                                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));
                                new Handler().postDelayed(() -> maxInterstitialAd.loadAd(), delayMillis);
                                loadBackupInterstitialAd();
                                Log.d(TAG, "failed to load AppLovin Interstitial");
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxInterstitialAd.loadAd();
                            }
                        });

                        // Load the first ad
                        maxInterstitialAd.loadAd();
                        break;

                    case APPLOVIN_DISCOVERY:
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle interstitialExtras = new Bundle();
                        interstitialExtras.putString("zone_id", appLovinInterstitialZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinAd = ad;
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                loadBackupInterstitialAd();
                            }
                        });
                        appLovinInterstitialAdDialog = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                        break;

                    case MOPUB:
                    case WORTISE:
                        break;
                }
            }
        }

        public void loadBackupInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (backupAdNetwork) {
                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:

                        // Create the interstitial ad object
                        mInterstitialAd = new LevelPlayInterstitialAd("DefaultInterstitial");
                        mInterstitialAd.setListener(new LevelPlayInterstitialAdListener() {
                            @Override
                            public void onAdLoaded(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was loaded successfully
                                Log.d(TAG, "onInterstitialAdReady");
                            }
                            @Override
                            public void onAdLoadFailed(LevelPlayAdError levelPlayAdError) {
                                // Ad load failed
                                loadBackupInterstitialAd();
                            }
                            @Override
                            public void onAdDisplayed(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was displayed and visible on screen
                                Log.d(TAG, "onInterstitialAdShowSucceeded");
                            }
                            @Override
                            public void onAdDisplayFailed(LevelPlayAdError levelPlayAdError, LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad fails to be displayed
                                // Optional
                                Log.d(TAG, "onInterstitialAdShowFailed" + " " + levelPlayAdError + " " + levelPlayAdInfo);
                                loadBackupInterstitialAd();
                            }
                            @Override
                            public void onAdClicked(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was clicked
                                // Optional
                                Log.d(TAG, "onInterstitialAdClicked");
                            }
                            @Override
                            public void onAdClosed(LevelPlayAdInfo levelPlayAdInfo) {
                                // Ad was closed
                                // Optional
                                Log.d(TAG, "onInterstitialAdClosed");
//                                loadInterstitialAd();
                            }
                            @Override
                            public void onAdInfoChanged(LevelPlayAdInfo levelPlayAdInfo) {
                                // Called after the ad info is updated. Available when another interstitial ad has loaded, and includes a higher CPM/Rate
                                // Optional
                                Log.d(TAG, "another interstitial ad has loaded with higher CPM/Rate");
                            }
                        });
                        mInterstitialAd.loadAd();

                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        maxInterstitialAd = new MaxInterstitialAd(appLovinInterstitialId, activity);
                        maxInterstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                retryAttempt = 0;
                                Log.d(TAG, "AppLovin Interstitial Ad loaded...");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxInterstitialAd.loadAd();
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                retryAttempt++;
                                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));
                                new Handler().postDelayed(() -> maxInterstitialAd.loadAd(), delayMillis);
                                Log.d(TAG, "failed to load AppLovin Interstitial");
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxInterstitialAd.loadAd();
                            }
                        });

                        // Load the first ad
                        maxInterstitialAd.loadAd();
                        break;

                    case APPLOVIN_DISCOVERY:
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle interstitialExtras = new Bundle();
                        interstitialExtras.putString("zone_id", appLovinInterstitialZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinAd = ad;
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                            }
                        });
                        appLovinInterstitialAdDialog = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                        break;

                    case MOPUB:
                    case WORTISE:
                    case NONE:
                        //do nothing
                        break;
                }
            }
        }

        public void showInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (counter == interval) {
                    switch (adNetwork) {
                        case IRONSOURCE:
                        case FAN_BIDDING_IRONSOURCE:
                            if (mInterstitialAd.isAdReady()) {
                                mInterstitialAd.showAd(activity);
                            } else {
                                showBackupInterstitialAd();
                            }
                            break;

                        case APPLOVIN:
                        case APPLOVIN_MAX:
                        case FAN_BIDDING_APPLOVIN_MAX:
                            if (maxInterstitialAd != null && maxInterstitialAd.isReady()) {
                                Log.d(TAG, "ready : " + counter);
                                maxInterstitialAd.showAd();
                                Log.d(TAG, "show ad");
                            } else {
                                showBackupInterstitialAd();
                            }
                            break;

                        case APPLOVIN_DISCOVERY:
                            if (appLovinInterstitialAdDialog != null) {
                                appLovinInterstitialAdDialog.showAndRender(appLovinAd);
                            }
                            break;

                        case ADMOB:
                        case FAN_BIDDING_ADMOB:
                        case GOOGLE_AD_MANAGER:
                        case FAN_BIDDING_AD_MANAGER:
                        case MOPUB:
                        case WORTISE:
                            break;
                    }
                    counter = 1;
                } else {
                    counter++;
                }
                Log.d(TAG, "Current counter : " + counter);
            }
        }

        public void showBackupInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                Log.d(TAG, "Show Backup Interstitial Ad [" + backupAdNetwork.toUpperCase() + "]");
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                    case STARTAPP:
                    case UNITY:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        if (maxInterstitialAd != null && maxInterstitialAd.isReady()) {
                            maxInterstitialAd.showAd();
                        }
                        break;

                    case APPLOVIN_DISCOVERY:
                        if (appLovinInterstitialAdDialog != null) {
                            appLovinInterstitialAdDialog.showAndRender(appLovinAd);
                        }
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        if (mInterstitialAd.isAdReady()) {
                            mInterstitialAd.showAd(activity);
                        }
                        break;

                    case MOPUB:
                    case WORTISE:
                    case NONE:
                        //do nothing
                        break;
                }
            }
        }

        public void loadInterstitialAd(OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
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
                        maxInterstitialAd = new MaxInterstitialAd(appLovinInterstitialId, activity);
                        maxInterstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                retryAttempt = 0;
                                Log.d(TAG, "AppLovin Interstitial Ad loaded...");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxInterstitialAd.loadAd();
                                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                retryAttempt++;
                                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));
                                new Handler().postDelayed(() -> maxInterstitialAd.loadAd(), delayMillis);
                                loadBackupInterstitialAd(onInterstitialAdDismissedListener);
                                Log.d(TAG, "failed to load AppLovin Interstitial");
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxInterstitialAd.loadAd();
                            }
                        });

                        // Load the first ad
                        maxInterstitialAd.loadAd();
                        break;

                    case APPLOVIN_DISCOVERY:
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle interstitialExtras = new Bundle();
                        interstitialExtras.putString("zone_id", appLovinInterstitialZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinAd = ad;
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                                loadBackupInterstitialAd(onInterstitialAdDismissedListener);
                            }
                        });
                        appLovinInterstitialAdDialog = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        IronSource.setLevelPlayInterstitialListener(new LevelPlayInterstitialListener() {
                            @Override
                            public void onAdReady(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdReady");
                            }

                            @Override
                            public void onAdLoadFailed(IronSourceError ironSourceError) {
                                Log.d(TAG, "onInterstitialAdLoadFailed" + " " + ironSourceError);
                                loadBackupInterstitialAd(onInterstitialAdDismissedListener);
                            }

                            @Override
                            public void onAdOpened(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdOpened");
                            }

                            @Override
                            public void onAdShowSucceeded(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdShowSucceeded");
                            }

                            @Override
                            public void onAdShowFailed(IronSourceError ironSourceError, AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdShowFailed" + " " + ironSourceError);
                                loadBackupInterstitialAd(onInterstitialAdDismissedListener);
                            }

                            @Override
                            public void onAdClicked(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdClicked");
                            }

                            @Override
                            public void onAdClosed(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdClosed");
                                loadInterstitialAd(onInterstitialAdDismissedListener);
                                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                            }
                        });
                        IronSource.loadInterstitial();
                        break;

                    case MOPUB:
                    case WORTISE:
                        break;
                }
            }
        }

        public void loadBackupInterstitialAd(OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
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
                        maxInterstitialAd = new MaxInterstitialAd(appLovinInterstitialId, activity);
                        maxInterstitialAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                retryAttempt = 0;
                                Log.d(TAG, "AppLovin Interstitial Ad loaded...");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxInterstitialAd.loadAd();
                                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                retryAttempt++;
                                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));
                                new Handler().postDelayed(() -> maxInterstitialAd.loadAd(), delayMillis);
                                Log.d(TAG, "failed to load AppLovin Interstitial");
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxInterstitialAd.loadAd();
                            }
                        });

                        // Load the first ad
                        maxInterstitialAd.loadAd();
                        break;

                    case APPLOVIN_DISCOVERY:
//                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle interstitialExtras = new Bundle();
                        interstitialExtras.putString("zone_id", appLovinInterstitialZoneId);
//                        builder.addCustomEventExtrasBundle(AppLovinCustomEventInterstitial.class, interstitialExtras);
                        AppLovinSdk.getInstance(activity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                appLovinAd = ad;
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {
                            }
                        });
                        appLovinInterstitialAdDialog = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        IronSource.setLevelPlayInterstitialListener(new LevelPlayInterstitialListener() {
                            @Override
                            public void onAdReady(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdReady");
                            }

                            @Override
                            public void onAdLoadFailed(IronSourceError ironSourceError) {
                                Log.d(TAG, "onInterstitialAdLoadFailed" + " " + ironSourceError);
                            }

                            @Override
                            public void onAdOpened(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdOpened");
                            }

                            @Override
                            public void onAdShowSucceeded(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdShowSucceeded");
                            }

                            @Override
                            public void onAdShowFailed(IronSourceError ironSourceError, AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdShowFailed" + " " + ironSourceError);
                            }

                            @Override
                            public void onAdClicked(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdClicked");
                            }

                            @Override
                            public void onAdClosed(AdInfo adInfo) {
                                Log.d(TAG, "onInterstitialAdClosed");
                                loadInterstitialAd(onInterstitialAdDismissedListener);
                                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                            }
                        });
                        IronSource.loadInterstitial();
                        break;

                    case MOPUB:
                    case WORTISE:
                    case NONE:
                        //do nothing
                        break;
                }
            }
        }

        public void showInterstitialAd(OnInterstitialAdShowedListener onInterstitialAdShowedListener, OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (counter == interval) {
                    switch (adNetwork) {
                        case ADMOB:
                        case FAN_BIDDING_ADMOB:
                        case GOOGLE_AD_MANAGER:
                        case FAN_BIDDING_AD_MANAGER:
                            break;

                        case APPLOVIN:
                        case APPLOVIN_MAX:
                        case FAN_BIDDING_APPLOVIN_MAX:
                            if (maxInterstitialAd != null && maxInterstitialAd.isReady()) {
                                Log.d(TAG, "ready : " + counter);
                                maxInterstitialAd.showAd();
                                onInterstitialAdShowedListener.onInterstitialAdShowed();
                                Log.d(TAG, "show ad");
                            } else {
                                showBackupInterstitialAd(onInterstitialAdShowedListener, onInterstitialAdDismissedListener);
                            }
                            break;

                        case APPLOVIN_DISCOVERY:
                            if (appLovinInterstitialAdDialog != null) {
                                appLovinInterstitialAdDialog.setAdDisplayListener(new AppLovinAdDisplayListener() {
                                    @Override
                                    public void adDisplayed(AppLovinAd appLovinAd) {
                                        onInterstitialAdShowedListener.onInterstitialAdShowed();
                                    }

                                    @Override
                                    public void adHidden(AppLovinAd appLovinAd) {
                                        onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                    }
                                });
                                appLovinInterstitialAdDialog.showAndRender(appLovinAd);
                            }
                            break;

                        case IRONSOURCE:
                        case FAN_BIDDING_IRONSOURCE:
                            if (IronSource.isInterstitialReady()) {
                                IronSource.showInterstitial(ironSourceInterstitialId);
                                onInterstitialAdShowedListener.onInterstitialAdShowed();
                            } else {
                                showBackupInterstitialAd(onInterstitialAdShowedListener, onInterstitialAdDismissedListener);
                            }
                            break;

                        case MOPUB:
                        case WORTISE:
                            break;
                    }
                    counter = 1;
                } else {
                    onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                    counter++;
                }
                Log.d(TAG, "Current counter : " + counter);
            } else {
                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
            }
        }

        public void showBackupInterstitialAd(OnInterstitialAdShowedListener onInterstitialAdShowedListener, OnInterstitialAdDismissedListener onInterstitialAdDismissedListener) {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                Log.d(TAG, "Show Backup Interstitial Ad [" + backupAdNetwork.toUpperCase() + "]");
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        if (maxInterstitialAd != null && maxInterstitialAd.isReady()) {
                            maxInterstitialAd.showAd();
                            onInterstitialAdShowedListener.onInterstitialAdShowed();
                        }
                        break;

                    case APPLOVIN_DISCOVERY:
                        if (appLovinInterstitialAdDialog != null) {
                            appLovinInterstitialAdDialog.setAdDisplayListener(new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {
                                    onInterstitialAdShowedListener.onInterstitialAdShowed();
                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    onInterstitialAdDismissedListener.onInterstitialAdDismissed();
                                }
                            });
                            appLovinInterstitialAdDialog.showAndRender(appLovinAd);
                        }
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial(ironSourceInterstitialId);
                            onInterstitialAdShowedListener.onInterstitialAdShowed();
                        }
                        break;

                    case MOPUB:
                    case WORTISE:
                    case NONE:
                        //do nothing
                        break;
                }
            } else {
                onInterstitialAdDismissedListener.onInterstitialAdDismissed();
            }
        }

    }

}
