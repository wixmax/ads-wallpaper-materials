package com.solodroid.ads.sdk.format;

import static com.solodroid.ads.sdk.util.Constant.ADMOB;
import static com.solodroid.ads.sdk.util.Constant.AD_STATUS_ON;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_DISCOVERY;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_ADMOB;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.GOOGLE_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.NONE;
import static com.solodroid.ads.sdk.util.Constant.STARTAPP;
import static com.solodroid.ads.sdk.util.Constant.UNITY;
import static com.solodroid.ads.sdk.util.Constant.WORTISE;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.nativead.MediaView;
import com.solodroid.ads.sdk.R;
//import com.solodroid.ads.sdk.helper.AppLovinCustomEventBanner;
//import com.solodroid.ads.sdk.util.AdManagerTemplateView;
import com.solodroid.ads.sdk.helper.Log;
import com.solodroid.ads.sdk.util.Constant;
//import com.solodroid.ads.sdk.util.TemplateView;


public class NativeAdView {

    /** @noinspection deprecation*/
    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
        View view;

        LinearLayout nativeAdViewContainer;

//        MediaView mediaView;
//        TemplateView admobNativeAd;
//        LinearLayout admobNativeBackground;

//        MediaView adManagerMediaView;
//        AdManagerTemplateView adManagerNativeAd;
//        LinearLayout adManagerNativeBackground;

        View startappNativeAd;
        ImageView startappNativeImage;
        ImageView startappNativeIcon;
        TextView startappNativeTitle;
        TextView startappNativeDescription;
        Button startappNativeButton;
        LinearLayout startappNativeBackground;

        FrameLayout applovinNativeAd;
        MaxNativeAdLoader nativeAdLoader;
        MaxAd nativeAd;
        LinearLayout appLovinDiscoveryMrecAd;
        private AppLovinAdView appLovinAdView;

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

        public Builder setView(View view) {
            this.view = view;
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

                nativeAdViewContainer = view.findViewById(R.id.native_ad_view_container);

//                admobNativeAd = view.findViewById(R.id.admob_native_ad_container);
//                mediaView = view.findViewById(R.id.media_view);
//                admobNativeBackground = view.findViewById(R.id.background);

//                adManagerNativeAd = view.findViewById(R.id.google_ad_manager_native_ad_container);
//                adManagerMediaView = view.findViewById(R.id.ad_manager_media_view);
//                adManagerNativeBackground = view.findViewById(R.id.ad_manager_background);

//                startappNativeAd = view.findViewById(R.id.startapp_native_ad_container);
//                startappNativeImage = view.findViewById(R.id.startapp_native_image);
//                startappNativeIcon = view.findViewById(R.id.startapp_native_icon);
//                startappNativeTitle = view.findViewById(R.id.startapp_native_title);
//                startappNativeDescription = view.findViewById(R.id.startapp_native_description);
//                startappNativeButton = view.findViewById(R.id.startapp_native_button);
//                startappNativeButton.setOnClickListener(v -> startappNativeAd.performClick());
//                startappNativeBackground = view.findViewById(R.id.startapp_native_background);

                applovinNativeAd = view.findViewById(R.id.applovin_native_ad_container);
                appLovinDiscoveryMrecAd = view.findViewById(R.id.applovin_discovery_mrec_ad_container);

//                wortiseNativeAd = view.findViewById(R.id.wortise_native_ad_container);

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
                                    if (nativeAd != null) {
                                        nativeAdLoader.destroy(nativeAd);
                                    }

                                    // Save ad for cleanup.
                                    nativeAd = ad;

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
                                }

                                @Override
                                public void onNativeAdLoadFailed(@NonNull final String adUnitId, @NonNull final MaxError error) {
                                    // We recommend retrying with exponentially higher delays up to a maximum delay
                                    loadBackupNativeAd();
                                }

                                @Override
                                public void onNativeAdClicked(@NonNull final MaxAd ad) {
                                    // Optional click callback
                                }
                            });
                            if (darkTheme) {
                                nativeAdLoader.loadAd(createNativeAdViewDark(nativeAdStyle));
                            } else {
                                nativeAdLoader.loadAd(createNativeAdView(nativeAdStyle));
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
                            this.appLovinAdView.loadNextAd();
                        } else {
                            Log.d(TAG, "AppLovin Discovery Mrec Ad has been loaded");
                        }
                        break;

                    case WORTISE:
                        break;

                    case UNITY:
                        //do nothing
                        break;

                }

            }

        }

        public void loadBackupNativeAd() {

            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {

                nativeAdViewContainer = view.findViewById(R.id.native_ad_view_container);

//                admobNativeAd = view.findViewById(R.id.admob_native_ad_container);
////                mediaView = view.findViewById(R.id.media_view);
//                admobNativeBackground = view.findViewById(R.id.background);
//
//                adManagerNativeAd = view.findViewById(R.id.google_ad_manager_native_ad_container);
////                adManagerMediaView = view.findViewById(R.id.ad_manager_media_view);
//                adManagerNativeBackground = view.findViewById(R.id.ad_manager_background);

//                startappNativeAd = view.findViewById(R.id.startapp_native_ad_container);
//                startappNativeImage = view.findViewById(R.id.startapp_native_image);
//                startappNativeIcon = view.findViewById(R.id.startapp_native_icon);
//                startappNativeTitle = view.findViewById(R.id.startapp_native_title);
//                startappNativeDescription = view.findViewById(R.id.startapp_native_description);
//                startappNativeButton = view.findViewById(R.id.startapp_native_button);
//                startappNativeButton.setOnClickListener(v -> startappNativeAd.performClick());
//                startappNativeBackground = view.findViewById(R.id.startapp_native_background);

                applovinNativeAd = view.findViewById(R.id.applovin_native_ad_container);
                appLovinDiscoveryMrecAd = view.findViewById(R.id.applovin_discovery_mrec_ad_container);

//                wortiseNativeAd = view.findViewById(R.id.wortise_native_ad_container);

                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case STARTAPP:
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
                                    if (nativeAd != null) {
                                        nativeAdLoader.destroy(nativeAd);
                                    }

                                    // Save ad for cleanup.
                                    nativeAd = ad;

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
                                }

                                @Override
                                public void onNativeAdLoadFailed(@NonNull final String adUnitId, @NonNull final MaxError error) {
                                    // We recommend retrying with exponentially higher delays up to a maximum delay
                                }

                                @Override
                                public void onNativeAdClicked(@NonNull final MaxAd ad) {
                                    // Optional click callback
                                }
                            });
                            if (darkTheme) {
                                nativeAdLoader.loadAd(createNativeAdViewDark(nativeAdStyle));
                            } else {
                                nativeAdLoader.loadAd(createNativeAdView(nativeAdStyle));
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
                            this.appLovinAdView.loadNextAd();
                        } else {
                            Log.d(TAG, "AppLovin Discovery Mrec Ad has been loaded");
                        }
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
            nativeAdViewContainer = view.findViewById(R.id.native_ad_view_container);
            nativeAdViewContainer.setPadding(left, top, right, bottom);
            if (darkTheme) {
                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundDark));
            } else {
                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundLight));
            }
        }

        public void setNativeAdMargin(int left, int top, int right, int bottom) {
            nativeAdViewContainer = view.findViewById(R.id.native_ad_view_container);
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
            nativeAdViewContainer = view.findViewById(R.id.native_ad_view_container);
            nativeAdViewContainer.setBackgroundResource(drawableBackground);
        }

        public MaxNativeAdView createNativeAdView(String nativeAdStyle) {
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

        public MaxNativeAdView createNativeAdViewDark(String nativeAdStyle) {
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

//        @SuppressWarnings("ConstantConditions")
//        public void populateNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView nativeAdView) {
//
//            if (darkTheme) {
//                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundDark));
//                nativeAdView.findViewById(R.id.background).setBackgroundResource(nativeBackgroundDark);
//            } else {
//                nativeAdViewContainer.setBackgroundColor(ContextCompat.getColor(activity, nativeBackgroundLight));
//                nativeAdView.findViewById(R.id.background).setBackgroundResource(nativeBackgroundLight);
//            }
//
//            nativeAdView.setMediaView(nativeAdView.findViewById(R.id.media_view));
//            nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.primary));
//            nativeAdView.setBodyView(nativeAdView.findViewById(R.id.body));
//            nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.cta));
//            nativeAdView.setIconView(nativeAdView.findViewById(R.id.icon));
//
//            ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
//            nativeAdView.getMediaView().setMediaContent(nativeAd.getMediaContent());
//
//            if (nativeAd.getBody() == null) {
//                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
//            } else {
//                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
//                ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
//            }
//
//            if (nativeAd.getCallToAction() == null) {
//                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
//            } else {
//                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
//                ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
//            }
//
//            if (nativeAd.getIcon() == null) {
//                nativeAdView.getIconView().setVisibility(View.GONE);
//            } else {
//                ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
//                nativeAdView.getIconView().setVisibility(View.VISIBLE);
//            }
//
//            nativeAdView.setNativeAd(nativeAd);
//        }

    }

}