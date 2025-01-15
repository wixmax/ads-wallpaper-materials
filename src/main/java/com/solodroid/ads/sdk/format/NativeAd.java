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
import static com.solodroid.ads.sdk.util.Constant.UNITY;
import static com.solodroid.ads.sdk.util.Constant.WORTISE;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.ironsource.mediationsdk.ads.nativead.LevelPlayMediaView;
import com.ironsource.mediationsdk.ads.nativead.LevelPlayNativeAd;
import com.ironsource.mediationsdk.ads.nativead.LevelPlayNativeAdListener;
import com.ironsource.mediationsdk.ads.nativead.NativeAdLayout;
import com.ironsource.mediationsdk.ads.nativead.interfaces.NativeAdDataInterface;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.solodroid.ads.sdk.R;
//import com.solodroid.ads.sdk.helper.AppLovinCustomEventBanner;
//import com.solodroid.ads.sdk.util.AdManagerTemplateView;
import com.solodroid.ads.sdk.helper.Log;
import com.solodroid.ads.sdk.util.Constant;

import java.util.ArrayList;
import java.util.List;
//import com.solodroid.ads.sdk.util.TemplateView;


public class NativeAd {

    /** @noinspection deprecation*/
    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
        LinearLayout nativeAdViewContainer;

//        MediaView mediaView;
//        TemplateView admobNativeAd;
        LinearLayout admobNativeBackground;

//        MediaView adManagerMediaView;
//        AdManagerTemplateView adManagerNativeAd;
        LinearLayout adManagerNativeBackground;

        View startappNativeAd;
        ImageView startappNativeImage;
        ImageView startappNativeIcon;
        TextView startappNativeTitle;
        TextView startappNativeDescription;
        Button startappNativeButton;
        LinearLayout startappNativeBackground;

        MaxAd maxAd;
        FrameLayout applovinNativeAd;

        MaxNativeAdLoader nativeAdLoader;

        LinearLayout appLovinDiscoveryMrecAd;
        private AppLovinAdView appLovinAdView;

        FrameLayout ironsourceNativeAd;
        LevelPlayNativeAd nativeAd;
        private List<LevelPlayNativeAd> mNativeAds = new ArrayList<>();

        FrameLayout wortiseNativeAd;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobNativeId = "";
        private String adManagerNativeId = "";
        private String fanNativeId = "";
        private String appLovinNativeId = "";
        private String appLovinDiscMrecZoneId = "";
        private String wortiseNativeId = "";
        private String alienAdsNativeId = "";
        private int placementStatus = 1;
        private boolean darkTheme = false;
        private boolean legacyGDPR = false;
        private String ironsourceNativeId = "";
        private Boolean isIronsourceNativeExitDialog;

        private String nativeAdStyle = "";
        private int nativeBackgroundLight = R.color.color_native_background_light;
        private int nativeBackgroundDark = R.color.color_native_background_dark;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadNativeAd();
            return this;
        }

        public Builder setPadding(int left, int top, int right, int bottom) {
            setNativeAdPadding(left, top, right, bottom);
            return this;
        }

        public Builder setMargin(int left, int top, int right, int bottom) {
            setNativeAdMargin(left, top, right, bottom);
            return this;
        }

        public Builder setBackgroundResource(int drawableBackground) {
            setNativeAdBackgroundResource(drawableBackground);
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

        public Builder setAdMobNativeId(String adMobNativeId) {
            this.adMobNativeId = adMobNativeId;
            return this;
        }

        public Builder setAdManagerNativeId(String adManagerNativeId) {
            this.adManagerNativeId = adManagerNativeId;
            return this;
        }

        public Builder setFanNativeId(String fanNativeId) {
            this.fanNativeId = fanNativeId;
            return this;
        }

        public Builder setAppLovinNativeId(String appLovinNativeId) {
            this.appLovinNativeId = appLovinNativeId;
            return this;
        }

        public Builder setIronsourceNativeId(String ironsourceNativeId) {
            this.ironsourceNativeId = ironsourceNativeId;
            return this;
        }

        public Builder setIsIronsourceNativeExitDialog(boolean isIronsourceNativeExitDialog) {
            this.isIronsourceNativeExitDialog = isIronsourceNativeExitDialog;
            return this;
        }

        public Builder setAppLovinDiscoveryMrecZoneId(String appLovinDiscMrecZoneId) {
            this.appLovinDiscMrecZoneId = appLovinDiscMrecZoneId;
            return this;
        }

        public Builder setWortiseNativeId(String wortiseNativeId) {
            this.wortiseNativeId = wortiseNativeId;
            return this;
        }

        public Builder setAlienAdsNativeId(String alienAdsNativeId) {
            this.alienAdsNativeId = alienAdsNativeId;
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

        public Builder setNativeAdStyle(String nativeAdStyle) {
            this.nativeAdStyle = nativeAdStyle;
            return this;
        }

        public Builder setNativeAdBackgroundColor(int colorLight, int colorDark) {
            this.nativeBackgroundLight = colorLight;
            this.nativeBackgroundDark = colorDark;
            return this;
        }

        public void loadNativeAd() {

            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {

                nativeAdViewContainer = activity.findViewById(R.id.native_ad_view_container);

//                admobNativeAd = activity.findViewById(R.id.admob_native_ad_container);
//                mediaView = activity.findViewById(R.id.media_view);
//                admobNativeBackground = activity.findViewById(R.id.background);

//                adManagerNativeAd = activity.findViewById(R.id.google_ad_manager_native_ad_container);
//                adManagerMediaView = activity.findViewById(R.id.ad_manager_media_view);
//                adManagerNativeBackground = activity.findViewById(R.id.ad_manager_background);

//                startappNativeAd = activity.findViewById(R.id.startapp_native_ad_container);
//                startappNativeImage = activity.findViewById(R.id.startapp_native_image);
//                startappNativeIcon = activity.findViewById(R.id.startapp_native_icon);
//                startappNativeTitle = activity.findViewById(R.id.startapp_native_title);
//                startappNativeDescription = activity.findViewById(R.id.startapp_native_description);
//                startappNativeButton = activity.findViewById(R.id.startapp_native_button);
//                startappNativeButton.setOnClickListener(v -> startappNativeAd.performClick());
//                startappNativeBackground = activity.findViewById(R.id.startapp_native_background);

                applovinNativeAd = activity.findViewById(R.id.applovin_native_ad_container);
                appLovinDiscoveryMrecAd = activity.findViewById(R.id.applovin_discovery_mrec_ad_container);

//                wortiseNativeAd = activity.findViewById(R.id.wortise_native_ad_container);

                ironsourceNativeAd = activity.findViewById(R.id.ironsource_native_ad_container);

                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        if (applovinNativeAd.getVisibility() != View.VISIBLE) {
                            nativeAdLoader = new MaxNativeAdLoader(appLovinNativeId, activity);
                            nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                @Override
                                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, @NonNull final MaxAd ad) {
                                    // Clean up any pre-existing native ad to prevent memory leaks.
                                    if (maxAd != null) {
                                        nativeAdLoader.destroy(maxAd);
                                    }

                                    // Save ad for cleanup.
                                    maxAd = ad;

                                    // Add ad view to view.
                                    applovinNativeAd.removeAllViews();
                                    applovinNativeAd.addView(nativeAdView);
                                    applovinNativeAd.setVisibility(View.VISIBLE);
                                    nativeAdViewContainer.setVisibility(View.VISIBLE);

                                    LinearLayout applovinNativeBackground = nativeAdView.findViewById(R.id.applovin_native_background);
                                    if (darkTheme) {
                                        applovinNativeBackground.setBackgroundResource(nativeBackgroundDark);
                                    } else {
                                        applovinNativeBackground.setBackgroundResource(nativeBackgroundLight);
                                    }

                                    Log.d(TAG, "Max Native Ad loaded successfully");
                                }

                                @Override
                                public void onNativeAdLoadFailed(@NonNull final String adUnitId, @NonNull final MaxError error) {
                                    // We recommend retrying with exponentially higher delays up to a maximum delay
                                    loadBackupNativeAd();
                                    Log.d(TAG, "failed to load Max Native Ad with message : " + error.getMessage() + " and error code : " + error.getCode());
                                }

                                @Override
                                public void onNativeAdClicked(@NonNull final MaxAd ad) {
                                    // Optional click callback
                                }
                            });
                            if (darkTheme) {
                                nativeAdLoader.loadAd(createNativeAdViewDark());
                            } else {
                                nativeAdLoader.loadAd(createNativeAdView());
                            }
                        } else {
                            Log.d(TAG, "AppLovin Native Ad has been loaded");
                        }
                        break;

                    case APPLOVIN_DISCOVERY:
                        if (appLovinDiscoveryMrecAd.getVisibility() != View.VISIBLE) {
//                            AdRequest.Builder builder = new AdRequest.Builder();
                            Bundle bannerExtras = new Bundle();
                            bannerExtras.putString("zone_id", appLovinDiscMrecZoneId);
//                            builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                            AppLovinAdSize adSize = AppLovinAdSize.MREC;
                            this.appLovinAdView = new AppLovinAdView(adSize, activity);
                            this.appLovinAdView.setAdLoadListener(new AppLovinAdLoadListener() {
                                @Override
                                public void adReceived(AppLovinAd ad) {
                                    appLovinDiscoveryMrecAd.setVisibility(View.VISIBLE);
                                    nativeAdViewContainer.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void failedToReceiveAd(int errorCode) {
                                    appLovinDiscoveryMrecAd.setVisibility(View.GONE);
                                    nativeAdViewContainer.setVisibility(View.GONE);
                                    loadBackupNativeAd();
                                }
                            });
                            appLovinDiscoveryMrecAd.addView(this.appLovinAdView);
                            int padding = activity.getResources().getDimensionPixelOffset(R.dimen.gnt_default_margin);
                            appLovinDiscoveryMrecAd.setPadding(0, padding, 0, padding);
                            if (darkTheme) {
                                appLovinDiscoveryMrecAd.setBackgroundResource(nativeBackgroundDark);
                            } else {
                                appLovinDiscoveryMrecAd.setBackgroundResource(nativeBackgroundLight);
                            }
                            this.appLovinAdView.loadNextAd();
                        } else {
                            Log.d(TAG, "AppLovin Discovery Mrec Ad has been loaded");
                        }
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        if (isIronsourceNativeExitDialog) {
                            LoadIronsourceNative(ironsourceNativeAd,true);
                        } else {
                            loadBackupNativeAd();
                        }
//                        loadBackupNativeAd();
//                        List<LevelPlayNativeAd> mNativeAds = new ArrayList<>();
//                        nativeAd = new LevelPlayNativeAd.Builder()
//                                .withPlacementName("0ryvtrgxts16b3x7")
//                                .withListener(new LevelPlayNativeAdListener() {
//                                    @Override
//                                    public void onAdClicked(LevelPlayNativeAd levelPlayNativeAd, AdInfo adInfo) {
//                                        log.e("faissal", "onAdClicked "+adInfo);
//                                    }
//
//                                    @Override
//                                    public void onAdImpression(LevelPlayNativeAd levelPlayNativeAd, AdInfo adInfo) {
//                                        log.e("faissal", "onAdImpression "+adInfo);
//                                    }
//
//                                    @Override
//                                    public void onAdLoaded(LevelPlayNativeAd levelPlayNativeAd, AdInfo adInfo) {
//                                        log.e("faissal", "onAdLoaded "+adInfo);
//                                        createNativeAdLayoutDark(ironsourceNativeAd);
//                                        nativeAdViewContainer.setVisibility(View.VISIBLE);
//                                    }
//
//                                    @Override
//                                    public void onAdLoadFailed(LevelPlayNativeAd levelPlayNativeAd, IronSourceError ironSourceError) {
//                                        log.e("faissal", "onAdLoadFailed "+ironSourceError);
//                                        loadBackupNativeAd();
//                                    }
//                                })
//                                .build();
//
////                        mNativeAds.add(nativeAd);
//                        nativeAd.loadAd();
                        break;

                    case WORTISE:
                        break;

                    case UNITY:
                        //do nothing
                        break;
                }

            }

        }


        void LoadIronsourceNative(FrameLayout ironsourceNativeAd,Boolean backup) {
            Log.e(TAG, "LoadIronsourceNative: ");
            nativeAd = new LevelPlayNativeAd.Builder()
                    .withPlacementName(ironsourceNativeId)
                    .withListener(new LevelPlayNativeAdListener() {

                        @Override
                        public void onAdClicked(@Nullable LevelPlayNativeAd levelPlayNativeAd, @Nullable AdInfo adInfo) {
                            // Invoked when end user clicked on the native ad
                            Log.e(TAG, "Native Ad Clicked: " + adInfo.getAdNetwork());
                        }

                        @Override
                        public void onAdImpression(@Nullable LevelPlayNativeAd levelPlayNativeAd, @Nullable AdInfo adInfo) {
                            // Invoked each time the first pixel is visible on the screen
                            Log.e(TAG, "Native Ad Impression: " + adInfo.getAdNetwork());
                        }

                        @Override
                        public void onAdLoaded(@Nullable LevelPlayNativeAd levelPlayNativeAd, @Nullable AdInfo adInfo) {
                            // Invoked each time a native ad was loaded.
                            Log.e(TAG, "Native Ad Loaded: " + adInfo.getAdNetwork());
//                            ironsourceNativeAd.setVisibility(View.VISIBLE);
                            createNativeAdLayout(ironsourceNativeAd, levelPlayNativeAd);

                        }

                        @Override
                        public void onAdLoadFailed(@Nullable LevelPlayNativeAd levelPlayNativeAd, @Nullable IronSourceError ironSourceError) {
                            // Invoked when the native ad loading process has failed.
                            Log.e(TAG, "Native Ad Load Failed: " + ironSourceError.getErrorMessage());
                            if (backup){
                                loadBackupNativeAd();
                            }
                        }
                    })
                    .build();
            mNativeAds.add(nativeAd);
            nativeAd.loadAd();
        }

        public void loadBackupNativeAd() {

            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {

                nativeAdViewContainer = activity.findViewById(R.id.native_ad_view_container);

//                admobNativeAd = activity.findViewById(R.id.admob_native_ad_container);
//                mediaView = activity.findViewById(R.id.media_view);
//                admobNativeBackground = activity.findViewById(R.id.background);

//                adManagerNativeAd = activity.findViewById(R.id.google_ad_manager_native_ad_container);
//                adManagerMediaView = activity.findViewById(R.id.ad_manager_media_view);
//                adManagerNativeBackground = activity.findViewById(R.id.ad_manager_background);

//                startappNativeAd = activity.findViewById(R.id.startapp_native_ad_container);
//                startappNativeImage = activity.findViewById(R.id.startapp_native_image);
//                startappNativeIcon = activity.findViewById(R.id.startapp_native_icon);
//                startappNativeTitle = activity.findViewById(R.id.startapp_native_title);
//                startappNativeDescription = activity.findViewById(R.id.startapp_native_description);
//                startappNativeButton = activity.findViewById(R.id.startapp_native_button);
//                startappNativeButton.setOnClickListener(v -> startappNativeAd.performClick());
//                startappNativeBackground = activity.findViewById(R.id.startapp_native_background);

                applovinNativeAd = activity.findViewById(R.id.applovin_native_ad_container);
                appLovinDiscoveryMrecAd = activity.findViewById(R.id.applovin_discovery_mrec_ad_container);

                ironsourceNativeAd = activity.findViewById(R.id.ironsource_native_ad_container);

//                wortiseNativeAd = activity.findViewById(R.id.wortise_native_ad_container);



                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        Log.e("faissal","load native applovine "+(applovinNativeAd.getVisibility() != View.VISIBLE));
                        if (applovinNativeAd.getVisibility() != View.VISIBLE) {
                            Log.e("faissal","load native applovine");
                            nativeAdLoader = new MaxNativeAdLoader(appLovinNativeId, activity);
                            nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                @Override
                                public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, @NonNull final MaxAd ad) {
                                    // Clean up any pre-existing native ad to prevent memory leaks.
                                    if (maxAd != null) {
                                        nativeAdLoader.destroy(maxAd);
                                    }

                                    // Save ad for cleanup.
                                    maxAd = ad;

                                    // Add ad view to view.
                                    applovinNativeAd.removeAllViews();
                                    applovinNativeAd.addView(nativeAdView);
                                    applovinNativeAd.setVisibility(View.VISIBLE);
                                    nativeAdViewContainer.setVisibility(View.VISIBLE);

                                    LinearLayout applovinNativeBackground = nativeAdView.findViewById(R.id.applovin_native_background);
                                    if (darkTheme) {
                                        applovinNativeBackground.setBackgroundResource(nativeBackgroundDark);
                                    } else {
                                        applovinNativeBackground.setBackgroundResource(nativeBackgroundLight);
                                    }
                                    Log.e("faissal", "Max Native Ad loaded successfully "+ad);

                                }

                                @Override
                                public void onNativeAdLoadFailed(@NonNull final String adUnitId, @NonNull final MaxError error) {
                                    // We recommend retrying with exponentially higher delays up to a maximum delay
                                    Log.e("faissal", "Max Native Ad Failed "+error);
                                }

                                @Override
                                public void onNativeAdClicked(@NonNull final MaxAd ad) {
                                    // Optional click callback
                                }
                            });
                            if (darkTheme) {
                                nativeAdLoader.loadAd(createNativeAdViewDark());
                            } else {
                                nativeAdLoader.loadAd(createNativeAdView());
                            }
                        } else {
                            Log.d(TAG, "AppLovin Native Ad has been loaded");
                        }
                        break;

                    case APPLOVIN_DISCOVERY:
                        if (appLovinDiscoveryMrecAd.getVisibility() != View.VISIBLE) {
//                            AdRequest.Builder builder = new AdRequest.Builder();
                            Bundle bannerExtras = new Bundle();
                            bannerExtras.putString("zone_id", appLovinDiscMrecZoneId);
//                            builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                            AppLovinAdSize adSize = AppLovinAdSize.MREC;
                            this.appLovinAdView = new AppLovinAdView(adSize, activity);
                            this.appLovinAdView.setAdLoadListener(new AppLovinAdLoadListener() {
                                @Override
                                public void adReceived(AppLovinAd ad) {
                                    appLovinDiscoveryMrecAd.setVisibility(View.VISIBLE);
                                    nativeAdViewContainer.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void failedToReceiveAd(int errorCode) {
                                    appLovinDiscoveryMrecAd.setVisibility(View.GONE);
                                    nativeAdViewContainer.setVisibility(View.GONE);
                                }
                            });
                            appLovinDiscoveryMrecAd.addView(this.appLovinAdView);
                            int padding = activity.getResources().getDimensionPixelOffset(R.dimen.gnt_default_margin);
                            appLovinDiscoveryMrecAd.setPadding(0, padding, 0, padding);
                            if (darkTheme) {
                                appLovinDiscoveryMrecAd.setBackgroundResource(nativeBackgroundDark);
                            } else {
                                appLovinDiscoveryMrecAd.setBackgroundResource(nativeBackgroundLight);
                            }
                            this.appLovinAdView.loadNextAd();
                        } else {
                            Log.d(TAG, "AppLovin Discovery Mrec Ad has been loaded");
                        }
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        LoadIronsourceNative(ironsourceNativeAd,false);
                        break;

                    case WORTISE:
                        break;

                    case UNITY:
                    case NONE:
                        nativeAdViewContainer.setVisibility(View.GONE);
                        break;
                }

            }

        }

        public void setNativeAdPadding(int left, int top, int right, int bottom) {
            nativeAdViewContainer = activity.findViewById(R.id.native_ad_view_container);
            nativeAdViewContainer.setPadding(left, top, right, bottom);
            if (darkTheme) {
                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundDark));
            } else {
                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundLight));
            }
        }

        public void setNativeAdMargin(int left, int top, int right, int bottom) {
            nativeAdViewContainer = activity.findViewById(R.id.native_ad_view_container);
            setMargins(nativeAdViewContainer, left, top, right, bottom);
        }

        public void setMargins(View view, int left, int top, int right, int bottom) {
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                p.setMargins(left, top, right, bottom);
                view.requestLayout();
            }
        }

        public void setNativeAdBackgroundResource(int drawableBackground) {
            nativeAdViewContainer = activity.findViewById(R.id.native_ad_view_container);
            nativeAdViewContainer.setBackgroundResource(drawableBackground);
        }

        public MaxNativeAdView createNativeAdView() {
            MaxNativeAdViewBinder binder;
            switch (nativeAdStyle) {
                case Constant.STYLE_NEWS:
                case Constant.STYLE_MEDIUM:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_news_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_RADIO:
                case Constant.STYLE_SMALL:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_radio_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_VIDEO_LARGE:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_video_large_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_VIDEO_SMALL:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_video_small_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                default:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_medium_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
            }
            return new MaxNativeAdView(binder, activity);

        }

        public MaxNativeAdView createNativeAdViewDark() {
            MaxNativeAdViewBinder binder;
            switch (nativeAdStyle) {
                case Constant.STYLE_NEWS:
                case Constant.STYLE_MEDIUM:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_dark_news_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_RADIO:
                case Constant.STYLE_SMALL:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_dark_radio_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_VIDEO_LARGE:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_dark_video_large_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                case Constant.STYLE_VIDEO_SMALL:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_dark_video_small_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
                default:
                    binder = new MaxNativeAdViewBinder.Builder(R.layout.gnt_applovin_dark_medium_template_view)
                            .setTitleTextViewId(R.id.title_text_view)
                            .setBodyTextViewId(R.id.body_text_view)
                            .setAdvertiserTextViewId(R.id.advertiser_textView)
                            .setIconImageViewId(R.id.icon_image_view)
                            .setMediaContentViewGroupId(R.id.media_view_container)
                            .setOptionsContentViewGroupId(R.id.ad_options_view)
                            .setCallToActionButtonId(R.id.cta_button)
                            .build();
                    break;
            }
            return new MaxNativeAdView(binder, activity);
        }
        void createNativeAdLayout(FrameLayout ironsourceNativeAd, LevelPlayNativeAd nativeAd) {

                LayoutInflater inflater = LayoutInflater.from(activity);
                NativeAdLayout nativeAdLayout;

                switch (nativeAdStyle) {
                    case Constant.STYLE_NEWS:
                    case Constant.STYLE_MEDIUM:
                        nativeAdLayout = (NativeAdLayout) inflater.inflate(R.layout.gnt_ironsource_news_template_view, ironsourceNativeAd, false);
                        break;
                    case Constant.STYLE_RADIO:
                    case Constant.STYLE_SMALL:
                        nativeAdLayout = (NativeAdLayout) inflater.inflate(R.layout.gnt_ironsource_radio_template_view, ironsourceNativeAd, false);
                        break;
                    case Constant.STYLE_VIDEO_LARGE:
                        nativeAdLayout = (NativeAdLayout) inflater.inflate(R.layout.gnt_ironsource_video_large_template_view, ironsourceNativeAd, false);
                        break;
                    case Constant.STYLE_VIDEO_SMALL:
                        nativeAdLayout = (NativeAdLayout) inflater.inflate(R.layout.gnt_ironsource_video_small_template_view, ironsourceNativeAd, false);
                        break;
                    default:
                        nativeAdLayout = (NativeAdLayout) inflater.inflate(R.layout.gnt_ironsource_medium_template_view, ironsourceNativeAd, false);
                        break;
                }
//                LevelPlayNativeAd nativeAd = mNativeAds.get(0);



                // ربط عناصر واجهة المستخدم
                TextView titleView = nativeAdLayout.findViewById(R.id.ad_title);
                ImageView iconView = nativeAdLayout.findViewById(R.id.ad_app_icon);
                LevelPlayMediaView mediaView = nativeAdLayout.findViewById(R.id.ad_media_view);
                Button ctaButton = nativeAdLayout.findViewById(R.id.ad_call_to_action);
                TextView body = nativeAdLayout.findViewById(R.id.ad_body);
                TextView advertiser = nativeAdLayout.findViewById(R.id.ad_advertiser);

                // Populate the views
                String title = nativeAd.getTitle();
                if (title != null) {
                    titleView.setText(title);
                }
                NativeAdDataInterface.Image icon = nativeAd.getIcon();
                if (icon != null) {
                    if (icon.getDrawable() != null) {
                        iconView.setImageDrawable(icon.getDrawable());
                    }
                }
                String bodyText = nativeAd.getBody();
                if (bodyText != null) {
                    body.setText(bodyText);
                }
                String advertiserText = nativeAd.getAdvertiser();
                if (advertiserText != null) {
                    advertiser.setText(advertiserText);
                }


                // Register the views to be bound
                nativeAdLayout.setTitleView(titleView);
                nativeAdLayout.setIconView(iconView);
                nativeAdLayout.setMediaView(mediaView);
                nativeAdLayout.setCallToActionView(ctaButton);
                nativeAdLayout.setBodyView(body);
                nativeAdLayout.setAdvertiserView(advertiser);

                // Setting the native ads
                nativeAdLayout.registerNativeAdViews(nativeAd);


                // إضافة تخطيط الإعلان إلى ironsourceNativeAd
                ironsourceNativeAd.removeAllViews(); // إزالة أي إعلانات سابقة
                ironsourceNativeAd.addView(nativeAdLayout);
                ironsourceNativeAd.setVisibility(View.VISIBLE);
                nativeAdViewContainer.setVisibility(View.VISIBLE);

            LinearLayout NativeBackground = nativeAdLayout.findViewById(R.id.ironsource_native_background);
            if (darkTheme) {
                NativeBackground.setBackgroundResource(nativeBackgroundDark);
            } else {
                NativeBackground.setBackgroundResource(nativeBackgroundLight);
            }
        }

        // destroy ironsorce native ad
        public void destroyAndDetachIrNative() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (adNetwork.equals(IRONSOURCE) || backupAdNetwork.equals(IRONSOURCE)) {
                    if (nativeAd != null && mNativeAds != null) {
                        Log.d(TAG, "ironSource native is not null, ready to destroy");
                        nativeAd.destroyAd();
                        mNativeAds = null;
                        nativeAd = null;

                    } else {
                        Log.d(TAG, "ironSource native is null");
                    }
                }
                if (adNetwork.equals(APPLOVIN) || backupAdNetwork.equals(APPLOVIN)) {
                    if (nativeAdLoader != null) {
                        Log.d(TAG, "applovin native is not null, ready to destroy");
                        nativeAdLoader.destroy(maxAd);
                        nativeAdLoader = null;

                    } else {
                        Log.d(TAG, "ironSource native is null");
                    }
                }
            }
        }


    }

}
