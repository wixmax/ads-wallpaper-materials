//package com.solodroid.ads.sdk.gdpr;
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Context;
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
//import com.solodroid.ads.sdk.util.mChoiceCmpCallback;
//public class inmobicmp {
//    private static final String TAG = "Consentinmobi";
//
//    Context context;
//    public inmobicmp(Context context) {
//        this.context = context;
//    }
//
//    ChoiceCmpCallback choiceCmpCallback;
//
//
//    public void init(Application application, String packageId, String pcode) {
//        Log.d(TAG, "init: "+application+" "+ packageId+" "+ pcode+" is choiceCmpCallback null :"+(choiceCmpCallback==null));
//
////        if (choiceCmpCallback == null) {
////            Log.e(TAG, "ChoiceCmpCallback is not initialized!");
////            return;
////        }
//
////        ChoiceCmpCallback();
//        ChoiceCmp.startChoice(
//                application,
//                packageId,
//                pcode,
//                choiceCmpCallback,
//                new ChoiceStyle()
//        );
//    }
//
//    //ChoiceCmpCallback
//    public void ChoiceCmpCallback(mChoiceCmpCallback callback) {
////        if (choiceCmpCallback != null) {
////            choiceCmpCallback = null;
////        }
//        choiceCmpCallback = new ChoiceCmpCallback(){
//            @Override
//            public void onUserMovedToOtherState() {
//                Log.d(TAG, "onUserMovedToOtherState: ");
////                callback.onUserMovedToOtherState();
//            }
//
//            @Override
//            public void onReceiveUSRegulationsConsent(@NonNull USRegulationData usRegulationData) {
//                Log.d(TAG, "onReceiveUSRegulationsConsent: " + usRegulationData);
//
//            }
//
//            @Override
//            public void onGoogleBasicConsentChange(@NonNull GoogleBasicConsents googleBasicConsents) {
//                Log.d(TAG, "onGoogleBasicConsentChange: " + googleBasicConsents);
////                callback.onGoogleBasicConsentChange(googleBasicConsents.toString());
//            }
//
//            @Override
//            public void onGoogleVendorConsentGiven(@NonNull ACData acData) {
//                Log.d(TAG, "onGoogleVendorConsentGiven: " + acData);
//                callback.onGoogleVendorConsentGiven(acData.toString());
//            }
//
//            @Override
//            public void onCmpError(@NonNull ChoiceError choiceError) {
//                Log.e(TAG, "onCmpError: " + choiceError);
//                callback.onCmpError(choiceError.toString());
//            }
//
//            @Override
//            public void onNonIABVendorConsentGiven(@NonNull NonIABData nonIABData) {
//                Log.d(TAG, "onNonIABVendorConsentGiven: " + nonIABData);
////                callback.onNonIABVendorConsentGiven(nonIABData.toString());
//            }
//
//            @Override
//            public void onIABVendorConsentGiven(@NonNull GDPRData gdprData) {
//                Log.d(TAG, "onIABVendorConsentGiven: " + gdprData);
//                callback.onIABVendorConsentGiven(gdprData.toString());
//            }
//
//            @Override
//            public void onCMPUIStatusChanged(@NonNull DisplayInfo displayInfo) {
//                Log.d(TAG, "onCMPUIStatusChanged: " + displayInfo);
//                callback.onCMPUIStatusChanged(displayInfo.getDisplayStatus().name());
//            }
//
//            @Override
//            public void onCmpLoaded(@NonNull PingReturn pingReturn) {
//                Log.d(TAG, "onCmpLoaded: " + pingReturn);
////                callback.onCmpLoaded(pingReturn.toString(),pingReturn.getGdprApplies(),pingReturn.getUsRegulationApplies());
//            }
//
//            @Override
//            public void onCCPAConsentGiven(@NonNull String s) {
//                Log.d(TAG, "onCCPAConsentGiven: " + s);
//                callback.onCCPAConsentGiven(s);
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
////        ChoiceCmp.showUSRegulationScreen(activity);
//    }
//}
