//package com.solodroid.ads.sdk.gdpr;
//
//import android.app.Activity;
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//
//import com.inmobi.cmp.ChoiceCmp;
//import com.inmobi.cmp.ChoiceCmpCallback;
//import com.inmobi.cmp.core.model.ACData;
//import com.inmobi.cmp.core.model.GDPRData;
//import com.inmobi.cmp.core.model.gbc.GoogleBasicConsents;
//import com.inmobi.cmp.core.model.mspa.USRegulationData;
//import com.inmobi.cmp.data.model.ChoiceStyle;
//import com.inmobi.cmp.model.ChoiceError;
//import com.inmobi.cmp.model.DisplayInfo;
//import com.inmobi.cmp.model.NonIABData;
//import com.inmobi.cmp.model.PingReturn;
//import com.solodroid.ads.sdk.helper.Log;
//
//public class inmobicmpNew {
//    private static final String TAG = "Consentinmobi";
//    Application application;
//    ChoiceCmp choiceCmp;
//    public inmobicmpNew(Application application) {
//        this.application = application;
//    }
//
//    public interface ConsentCallback {
//        void onConsentResult(boolean consentGiven);
////        void DisplayInfo(Boolean DisplayInfo);
//    }
//
//
//    public void init(String packageId, String pcode) {
//        ChoiceCmp.startChoice(
//                application,
//                packageId,
//                pcode,
//                choiceCmpCallback,
//                new ChoiceStyle()
//        );
//    }
//    ChoiceCmpCallback choiceCmpCallback;
//    public void ConsentCallBack(ConsentCallback callback) {
//        choiceCmpCallback = new ChoiceCmpCallback(){
//            @Override
//            public void onUserMovedToOtherState() {
//                Log.d(TAG, "onUserMovedToOtherState: ");
//            }
//
//            @Override
//            public void onReceiveUSRegulationsConsent(@NonNull USRegulationData usRegulationData) {
//                Log.d(TAG, "onReceiveUSRegulationsConsent: " + usRegulationData);
//            }
//
//            @Override
//            public void onGoogleBasicConsentChange(@NonNull GoogleBasicConsents googleBasicConsents) {
//                Log.d(TAG, "onGoogleBasicConsentChange: " + googleBasicConsents);
//            }
//
//            @Override
//            public void onGoogleVendorConsentGiven(@NonNull ACData acData) {
//                Log.d(TAG, "onGoogleVendorConsentGiven: " + acData);
//                callback.onConsentResult(true);
//            }
//
//            @Override
//            public void onCmpError(@NonNull ChoiceError choiceError) {
//                Log.e(TAG, "onCmpError: " + choiceError);
//                callback.onConsentResult(true);
//            }
//
//            @Override
//            public void onNonIABVendorConsentGiven(@NonNull NonIABData nonIABData) {
//                Log.d(TAG, "onNonIABVendorConsentGiven: " + nonIABData);
//            }
//
//            @Override
//            public void onIABVendorConsentGiven(@NonNull GDPRData gdprData) {
//                Log.d(TAG, "onIABVendorConsentGiven: " + gdprData);
//                callback.onConsentResult(true);
//            }
//
//            @Override
//            public void onCMPUIStatusChanged(@NonNull DisplayInfo displayInfo) {
//                Log.d(TAG, "onCMPUIStatusChanged: " + displayInfo);
//                if (displayInfo.getDisplayStatus().name().equals("HIDDEN")) {
////                    callback.DisplayInfo(true);
//                    callback.onConsentResult(true);
//                }
////                else if (displayInfo.getDisplayStatus().name().equals("VISIBLE")){
////                    callback.DisplayInfo(true);
////                }
//            }
//
//            @Override
//            public void onCmpLoaded(@NonNull PingReturn pingReturn) {
//                Log.d(TAG, "onCmpLoaded: " + pingReturn);
//            }
//
//            @Override
//            public void onCCPAConsentGiven(@NonNull String s) {
//                Log.d(TAG, "onCCPAConsentGiven: " + s);
//                callback.onConsentResult(true);
//            }
//        };
//    }
//
//    public void forceDisplayUI(Activity activity) {
//        if (activity == null) {
//            Log.e(TAG, "Activity is null in forceDisplayUI!");
//            return;
//        }
//        ChoiceCmp.forceDisplayUI(activity);
//    }
//
//}
