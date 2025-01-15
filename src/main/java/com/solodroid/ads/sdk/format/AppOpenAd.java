package com.solodroid.ads.sdk.format;

import static com.solodroid.ads.sdk.util.Constant.ADMOB;
import static com.solodroid.ads.sdk.util.Constant.AD_STATUS_ON;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_ADMOB;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.GOOGLE_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.IRONSOURCE;
import static com.solodroid.ads.sdk.util.Constant.WORTISE;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
import com.solodroid.ads.sdk.helper.Log;
//import com.google.android.gms.ads.AdError;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.solodroid.ads.sdk.helper.Log;
import com.solodroid.ads.sdk.util.OnShowAdCompleteListener;

@SuppressLint("StaticFieldLeak")
public class AppOpenAd {
//    public static com.google.android.gms.ads.appopen.AppOpenAd appOpenAd = null;
    public static MaxAppOpenAd maxAppOpenAd = null;
    public static boolean isAppOpenAdLoaded = false;

    public static class Builder {

        private static final String TAG = "AppOpenAd";
        private final Activity activity;
        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobAppOpenId = "";
        private String adManagerAppOpenId = "";
        private String applovinAppOpenId = "";
        private String wortiseAppOpenId = "";

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadAppOpenAd();
            return this;
        }

        public Builder build(OnShowAdCompleteListener onShowAdCompleteListener) {
            loadAppOpenAd(onShowAdCompleteListener);
            return this;
        }

        public Builder show() {
            showAppOpenAd();
            return this;
        }

        public Builder show(OnShowAdCompleteListener onShowAdCompleteListener) {
            showAppOpenAd(onShowAdCompleteListener);
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

        public Builder setAdMobAppOpenId(String adMobAppOpenId) {
            this.adMobAppOpenId = adMobAppOpenId;
            return this;
        }

        public Builder setAdManagerAppOpenId(String adManagerAppOpenId) {
            this.adManagerAppOpenId = adManagerAppOpenId;
            return this;
        }

        public Builder setApplovinAppOpenId(String applovinAppOpenId) {
            this.applovinAppOpenId = applovinAppOpenId;
            return this;
        }

        public Builder setWortiseAppOpenId(String wortiseAppOpenId) {
            this.wortiseAppOpenId = wortiseAppOpenId;
            return this;
        }

        public void destroyOpenAd() {
            AppOpenAd.isAppOpenAdLoaded = false;
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                        if (maxAppOpenAd != null) {
                            maxAppOpenAd = null;
                        }
                        break;

                    case WORTISE:

                        break;

                    default:
                        //do nothing
                        break;
                }
            }
        }

        //main ads
        public void loadAppOpenAd(OnShowAdCompleteListener onShowAdCompleteListener) {
            Log.d(TAG, "[" + adNetwork + "] " + "[on start] load app open ad "+adStatus.equals(AD_STATUS_ON));
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                        if (!applovinAppOpenId.equals("0")) {
                            maxAppOpenAd = new MaxAppOpenAd(applovinAppOpenId, activity);
                            maxAppOpenAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(@NonNull MaxAd ad) {
                                    showAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + adNetwork + "] " + "[on start] app open ad loaded");
                                }

                                @Override
                                public void onAdDisplayed(@NonNull MaxAd ad) {
                                }

                                @Override
                                public void onAdHidden(@NonNull MaxAd ad) {
                                    maxAppOpenAd = null;
                                    showAppOpenAd(onShowAdCompleteListener);
                                }

                                @Override
                                public void onAdClicked(@NonNull MaxAd ad) {
                                }

                                @Override
                                public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                    maxAppOpenAd = null;
                                    loadBackupAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + adNetwork + "] " + "[on start] failed to load app open ad: " + error.getMessage());
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                    maxAppOpenAd = null;
                                    loadBackupAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + adNetwork + "] " + "[on start] failed to display app open ad: " + error.getMessage());
                                }
                            });
                            maxAppOpenAd.loadAd();
                        } else {
                            loadBackupAppOpenAd(onShowAdCompleteListener);
                        }
                        break;

                    case IRONSOURCE:
                        loadBackupAppOpenAd(onShowAdCompleteListener);
                        break;

                    default:
                        onShowAdCompleteListener.onShowAdComplete();
                        break;
                }
            } else {
                onShowAdCompleteListener.onShowAdComplete();
            }
        }

        public void showAppOpenAd(OnShowAdCompleteListener onShowAdCompleteListener) {
            switch (adNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    break;

                case APPLOVIN:
                case APPLOVIN_MAX:
                    if (maxAppOpenAd != null) {
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                                Log.d(TAG, "[" + adNetwork + "] " + "[on start] show app open ad");
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on start] close app open ad");
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on start] app open ad load failed: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on start] app open ad display failed: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.showAd();
                    } else {
                        onShowAdCompleteListener.onShowAdComplete();
                    }
                    break;

                default:
                    onShowAdCompleteListener.onShowAdComplete();
                    break;
            }
        }

        public void loadAppOpenAd() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                        Log.d(TAG, "[" + adNetwork + "] " + "[on resume] load app open ad");
                        maxAppOpenAd = new MaxAppOpenAd(applovinAppOpenId, activity);
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                isAppOpenAdLoaded = true;
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] app open ad loaded");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] failed to load app open ad: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] failed to display app open ad: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.loadAd();
                        break;

                    case WORTISE:

                        break;
                    case IRONSOURCE:
                        loadBackupAppOpenAd();
                        break;

                    default:
                        //do nothing
                        break;
                }
            }
        }

        public void showAppOpenAd() {
            switch (adNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    break;

                case APPLOVIN:
                case APPLOVIN_MAX:
                    if (maxAppOpenAd != null) {
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] show app open ad");
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxAppOpenAd = null;
                                loadAppOpenAd();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] close app open ad");
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                loadAppOpenAd();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] app open ad load failed: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                loadAppOpenAd();
                                Log.d(TAG, "[" + adNetwork + "] " + "[on resume] app open ad display failed: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.showAd();
                    } else {
                        showBackupAppOpenAd();
                    }
                    break;
                case IRONSOURCE:
                    showBackupAppOpenAd();
                    break;

                default:
                    //do nothing
                    break;
            }
        }

        //backup ads
        public void loadBackupAppOpenAd(OnShowAdCompleteListener onShowAdCompleteListener) {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                        if (!applovinAppOpenId.equals("0")) {
                            maxAppOpenAd = new MaxAppOpenAd(applovinAppOpenId, activity);
                            maxAppOpenAd.setListener(new MaxAdListener() {
                                @Override
                                public void onAdLoaded(@NonNull MaxAd ad) {
                                    showBackupAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] app open ad loaded");
                                }

                                @Override
                                public void onAdDisplayed(@NonNull MaxAd ad) {

                                }

                                @Override
                                public void onAdHidden(@NonNull MaxAd ad) {
                                    maxAppOpenAd = null;
                                    showBackupAppOpenAd(onShowAdCompleteListener);
                                }

                                @Override
                                public void onAdClicked(@NonNull MaxAd ad) {

                                }

                                @Override
                                public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                    maxAppOpenAd = null;
                                    showBackupAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] failed to load app open ad: " + error.getMessage());
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                    maxAppOpenAd = null;
                                    showBackupAppOpenAd(onShowAdCompleteListener);
                                    Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] failed to display app open ad: " + error.getMessage());
                                }
                            });
                            maxAppOpenAd.loadAd();
                        } else {
                            showBackupAppOpenAd(onShowAdCompleteListener);
                        }
                        break;

                    default:
                        onShowAdCompleteListener.onShowAdComplete();
                        break;
                }
            } else {
                onShowAdCompleteListener.onShowAdComplete();
            }
        }

        public void showBackupAppOpenAd(OnShowAdCompleteListener onShowAdCompleteListener) {
            switch (backupAdNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    break;

                case APPLOVIN:
                case APPLOVIN_MAX:
                    if (maxAppOpenAd != null) {
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] show app open ad");
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] close app open ad");
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] app open ad load failed: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                onShowAdCompleteListener.onShowAdComplete();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on start] [backup] app open ad display failed: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.showAd();
                    } else {
                        onShowAdCompleteListener.onShowAdComplete();
                    }
                    break;

                default:
                    onShowAdCompleteListener.onShowAdComplete();
                    break;
            }
        }

        public void loadBackupAppOpenAd() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                        maxAppOpenAd = new MaxAppOpenAd(applovinAppOpenId, activity);
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {
                                isAppOpenAdLoaded = true;
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] app open ad loaded");
                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] failed to load app open ad: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                isAppOpenAdLoaded = false;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] failed to display app open ad: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.loadAd();
                        break;

                    default:
                        //do nothing
                        break;
                }
            }
        }

        public void showBackupAppOpenAd() {
            switch (backupAdNetwork) {
                case ADMOB:
                case FAN_BIDDING_ADMOB:
                case GOOGLE_AD_MANAGER:
                case FAN_BIDDING_AD_MANAGER:
                    break;

                case APPLOVIN:
                case APPLOVIN_MAX:
                    if (maxAppOpenAd != null) {
                        maxAppOpenAd.setListener(new MaxAdListener() {
                            @Override
                            public void onAdLoaded(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdDisplayed(@NonNull MaxAd ad) {
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] show app open ad");
                            }

                            @Override
                            public void onAdHidden(@NonNull MaxAd ad) {
                                maxAppOpenAd = null;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] close app open ad");
                            }

                            @Override
                            public void onAdClicked(@NonNull MaxAd ad) {

                            }

                            @Override
                            public void onAdLoadFailed(@NonNull String adUnitId, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] app open ad load failed: " + error.getMessage());
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull MaxAd ad, @NonNull MaxError error) {
                                maxAppOpenAd = null;
                                loadBackupAppOpenAd();
                                Log.d(TAG, "[" + backupAdNetwork + "] " + "[on resume] [backup] app open ad display failed: " + error.getMessage());
                            }
                        });
                        maxAppOpenAd.showAd();
                    }
                    break;

                default:
                    //do nothing
                    break;
            }
        }
//        void log(String message) {
//            Log.d(TAG,message);
//        }

    }
    
    

}
