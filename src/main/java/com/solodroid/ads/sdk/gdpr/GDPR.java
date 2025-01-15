//package com.solodroid.ads.sdk.gdpr;
//
//import static com.solodroid.ads.sdk.util.Constant.ADMOB;
//import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_DISCOVERY;
//import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_MAX;
//import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_IRONSOURCE;
//import static com.solodroid.ads.sdk.util.Constant.GOOGLE_AD_MANAGER;
//import static com.solodroid.ads.sdk.util.Constant.IRONSOURCE;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.provider.Settings;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//
//import com.applovin.sdk.AppLovinCmpError;
//import com.applovin.sdk.AppLovinPrivacySettings;
//import com.applovin.sdk.AppLovinSdk;
//import com.applovin.sdk.AppLovinCmpService;
////import com.google.android.gms.ads.MobileAds;
//import com.google.android.ump.ConsentDebugSettings;
//import com.google.android.ump.ConsentForm;
//import com.google.android.ump.ConsentInformation;
//import com.google.android.ump.ConsentRequestParameters;
//import com.google.android.ump.UserMessagingPlatform;
//
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class GDPR {
//
//    ConsentInformation consentInformation;
//    ConsentDebugSettings debugSettings;
//    ConsentRequestParameters params;
//    AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
//    ConsentForm consentForm;
//    Activity activity;
//
//    public GDPR(Activity activity) {
//        this.activity = activity;
//    }
//
//    public void updateGDPRConsentStatus() {
//        ConsentRequestParameters params = new ConsentRequestParameters.Builder().build();
//        consentInformation = UserMessagingPlatform.getConsentInformation(activity);
//        consentInformation.requestConsentInfoUpdate(activity, params, () -> {
//                    if (consentInformation.isConsentFormAvailable()) {
//                        loadForm(activity);
//                    }
//                },
//                formError -> {
//                });
//        Log.d("GDPR", "AdMob GDPR is selected");
//    }
//
//    @SuppressLint("HardwareIds")
//    public void updateGDPRConsentStatus(String adType, boolean isDebug, boolean childDirected) {
//        switch (adType) {
//            case ADMOB:
//            case GOOGLE_AD_MANAGER:
//                break;
//            case APPLOVIN_MAX:
//                AppLovinSdk.initializeSdk(activity, configuration -> {
//
//                });
//                AppLovinPrivacySettings.setHasUserConsent(true, activity);
////                AppLovinPrivacySettings.setIsAgeRestrictedUser(childDirected, activity);
//                break;
//            case APPLOVIN_DISCOVERY:
////                AppLovinPrivacySettings.setIsAgeRestrictedUser(childDirected, activity);
//                AppLovinPrivacySettings.setHasUserConsent(true, activity);
//                break;
//            case IRONSOURCE:
//            case FAN_BIDDING_IRONSOURCE:
//                showAppLovinConsentFlow();
////                AppLovinSdk appLovinSdk = AppLovinSdk.getInstance(this);
////
////                appLovinSdk.showConsentDialogIfNeeded(this, (success, error) -> {
////                    if (success) {
////                        // Consent dialog was shown successfully
////                    } else if (error != null) {
////                        // Handle error
////                        error.printStackTrace();
////                    }
////                });
//                break;
//        }
//    }
//
////    private void initializeMobileAdsSdk() {
////        if (isMobileAdsInitializeCalled.getAndSet(true)) {
////            return;
////        }
////        MobileAds.initialize(activity);
////    }
//
//    public void loadForm(Activity activity) {
//        UserMessagingPlatform.loadConsentForm(activity, consentForm -> {
//                    this.consentForm = consentForm;
//                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
//                        consentForm.show(activity, formError -> {
//                            loadForm(activity);
//                        });
//                    }
//                },
//                formError -> {
//                }
//        );
//    }
//
//
//    private void showAppLovinConsentFlow()
//    {
//        AppLovinCmpService cmpService = AppLovinSdk.getInstance( activity ).getCmpService();
//
//        cmpService.showCmpForExistingUser( activity, new AppLovinCmpService.OnCompletedListener()
//        {
//
//            @Override
//            public void onCompleted(@Nullable AppLovinCmpError appLovinCmpError) {
//                if ( null == appLovinCmpError )
//                {
//                    Log.e("faissal","The CMP alert was shown successfully.");
//                    // The CMP alert was shown successfully.
//                }
//                else
//                    Log.e("faissal"," "+appLovinCmpError);
//            }
//        } );
//    }
//
//    public static String md5(final String s) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            digest.update(s.getBytes());
//            byte[] messageDigest = digest.digest();
//            StringBuffer hexString = new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++) {
//                String h = Integer.toHexString(0xFF & messageDigest[i]);
//                while (h.length() < 2)
//                    h = "0" + h;
//                hexString.append(h);
//            }
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            //Logger.logStackTrace(TAG,e);
//        }
//        return "";
//    }
//
//}
