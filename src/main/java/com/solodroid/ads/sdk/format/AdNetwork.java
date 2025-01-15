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
import static com.solodroid.ads.sdk.util.Constant.NONE;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkInitializationConfiguration;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.solodroid.ads.sdk.helper.Log;
import com.unity3d.mediation.LevelPlay;
import com.unity3d.mediation.LevelPlayConfiguration;
import com.unity3d.mediation.LevelPlayInitError;
import com.unity3d.mediation.LevelPlayInitListener;
import com.unity3d.mediation.LevelPlayInitRequest;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.AdapterStatus;

import java.util.Arrays;
import java.util.List;

public class AdNetwork {

    public static class Initialize {

        private static final String TAG = "AdNetwork";
        Activity activity;
        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobAppId = "";
        private String startappAppId = "0";
        private String unityGameId = "";
        private String appLovinSdkKey = "";
        private String mopubBannerId = "";
        private String ironSourceAppKey = "";
        private String wortiseAppId = "";
        private String[] Gaid;
        private boolean debug = true;

        public interface InitCallback {
            void onInitSuccess();
            void onInitFailed(String error);
        }
        public Initialize(Activity activity) {
            this.activity = activity;
        }

        public Initialize build(final InitCallback callback) {
            initAds(callback);
            initBackupAds(callback);
            return this;
        }
        public Initialize build() {
            initAds(null);
            initBackupAds(null);
            return this;
        }

        public Initialize setAdStatus(String adStatus) {
            this.adStatus = adStatus;
            return this;
        }

        public Initialize setAdNetwork(String adNetwork) {
            this.adNetwork = adNetwork;
            return this;
        }

        public Initialize setBackupAdNetwork(String backupAdNetwork) {
            this.backupAdNetwork = backupAdNetwork;
            return this;
        }

        public Initialize setAdMobAppId(String adMobAppId) {
            this.adMobAppId = adMobAppId;
            return this;
        }

        public Initialize setStartappAppId(String startappAppId) {
            this.startappAppId = startappAppId;
            return this;
        }

        public Initialize setUnityGameId(String unityGameId) {
            this.unityGameId = unityGameId;
            return this;
        }

        public Initialize setAppLovinSdkKey(String appLovinSdkKey) {
            this.appLovinSdkKey = appLovinSdkKey;
            return this;
        }

        public Initialize setMopubBannerId(String mopubBannerId) {
            this.mopubBannerId = mopubBannerId;
            return this;
        }

        public Initialize setIronSourceAppKey(String ironSourceAppKey) {
            this.ironSourceAppKey = ironSourceAppKey;
            return this;
        }

        public Initialize setWortiseAppId(String wortiseAppId) {
            this.wortiseAppId = wortiseAppId;
            return this;
        }

        public Initialize setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Initialize getGaid(String[] Gaid) {
            this.Gaid = Gaid;
            return this;
        }


        void InitIrounSource(InitCallback callback,Boolean isBackup){
            if (debug)
                IronSource.setMetaData("is_test_suite", "enable");
            // Init the SDK when implementing the Multiple Ad Units Interstitial and Banner APIs, and Rewarded using legacy APIs
            List<LevelPlay.AdFormat> legacyAdFormats = List.of(LevelPlay.AdFormat.BANNER, LevelPlay.AdFormat.INTERSTITIAL, LevelPlay.AdFormat.REWARDED, LevelPlay.AdFormat.NATIVE_AD);

            LevelPlayInitRequest initRequest = new LevelPlayInitRequest.Builder(ironSourceAppKey)
                    .withLegacyAdFormats(legacyAdFormats)
                    .build();
            LevelPlayInitListener initListener = new LevelPlayInitListener() {
                @Override
                public void onInitFailed(@NonNull LevelPlayInitError error) {
                    Log.d(TAG,"onInitFailed: " + error.getErrorMessage());
                    if (!isBackup) {
                        //Recommended to initialize again
                        callback.onInitFailed(error.getErrorMessage());
                    }

                }
                @Override
                public void onInitSuccess(LevelPlayConfiguration configuration) {
                    Log.d(TAG,"onInitSuccess: " + configuration);
                    if (!isBackup) {
                        //Create ad objects and load ads
                        if (debug) {
//                        IronSource.launchTestSuite(activity);
                            IntegrationHelper.validateIntegration(activity);
                        }
                        callback.onInitSuccess();
                    }

                }
            };
            LevelPlay.init(activity, initRequest, initListener);
        }




        void InitApplovin(InitCallback callback,Boolean isBackup) {
            AppLovinSdkInitializationConfiguration initConfig;
            if (Gaid != null && Gaid.length > 0) {
                initConfig = AppLovinSdkInitializationConfiguration.builder(appLovinSdkKey, activity)
                        .setMediationProvider(AppLovinMediationProvider.MAX)
                        .setTestDeviceAdvertisingIds( Arrays.asList(Gaid) )
                        .build();
            }else {
                initConfig = AppLovinSdkInitializationConfiguration.builder(appLovinSdkKey, activity)
                        .setMediationProvider(AppLovinMediationProvider.MAX)
                        .build();
            }

            AppLovinSdk.getInstance(activity).initialize(initConfig, config -> {
                Log.d(TAG, "AppLovin SDK initialized: " + config);

                    if (AppLovinSdk.getInstance(activity).isInitialized()) {
                        if (!isBackup) {
                            callback.onInitSuccess();
                        }
                    } else {
                        Log.d(TAG, "AppLovin SDK initialization failed");
                        if (!isBackup) {
                            callback.onInitFailed("AppLovin SDK initialization failed");
                        }
                    }

            });


        }

        public void initAds(final InitCallback callback) {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_ADMOB:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        InitApplovin(callback,false);
                        break;

                    case APPLOVIN_DISCOVERY:
                        AppLovinSdk.initializeSdk(activity);
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        InitIrounSource(callback,false);
                        break;

                    default:
                    break;
                }
                Log.d(TAG, "[" + adNetwork + "] is selected as Primary Ads");
            }
        }

        public void initBackupAds(final InitCallback callback) {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_ADMOB:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        InitApplovin(callback,true);
                        break;

                    case APPLOVIN_DISCOVERY:
                        AppLovinSdk.initializeSdk(activity);
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:

                       InitIrounSource(callback,true);
                        break;

                    case NONE:
                        //do nothing
                        break;

                    default:
                        break;
                }
                Log.d(TAG, "[" + backupAdNetwork + "] is selected as Backup Ads");
            }
        }

    }

}
