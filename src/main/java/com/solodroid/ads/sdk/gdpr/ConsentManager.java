package com.solodroid.ads.sdk.gdpr;

import android.app.Activity;

import com.google.android.ump.*;
import com.ironsource.mediationsdk.IronSource;
import com.solodroid.ads.sdk.helper.Log;

public class ConsentManager {

    private static final String TAG = "ConsentManager";
    private ConsentInformation consentInformation;
    private ConsentForm consentForm;

    public interface ConsentCallback {
        void onConsentResult(boolean consentGiven);
    }

    public ConsentManager(Activity activity) {
        consentInformation = UserMessagingPlatform.getConsentInformation(activity);
    }

    public void IrLaunchTestSuite(Activity activity) {
        IronSource.launchTestSuite(activity);
    }
    public void resetConsentInformation() {
        // Call this only when explicitly required
        consentInformation.reset();
    }

    public void requestConsent(final Activity activity, final ConsentCallback callback,String testDeviceIds) {
        ConsentRequestParameters params = new ConsentRequestParameters.Builder().build();


        // Debug settings (REMOVE IN PRODUCTION)
        if (testDeviceIds == null) {
            Log.w(TAG, "No test device IDs provided. Ads may not be shown during testing.");
//            params = new ConsentRequestParameters.Builder().setTagForUnderAgeOfConsent(false).build();
            params = new ConsentRequestParameters.Builder().build();
        }else {
            ConsentDebugSettings debugSettings =
                    new ConsentDebugSettings.Builder(activity)
                            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                            .addTestDeviceHashedId(testDeviceIds)
                            .build();
            params = new ConsentRequestParameters.Builder().setConsentDebugSettings(debugSettings).build();
        }


        consentInformation.requestConsentInfoUpdate(
                activity,
                params,
                () -> {
                    int consentStatus = consentInformation.getConsentStatus();
                    if (consentStatus == ConsentInformation.ConsentStatus.OBTAINED) {
                        Log.d(TAG, "Consent already obtained. Showing ads.");
                        callback.onConsentResult(true);
                    } else if (consentStatus == ConsentInformation.ConsentStatus.REQUIRED &&
                            consentInformation.isConsentFormAvailable()) {
                        showConsentForm(activity, callback);
                    } else {
                        Log.d(TAG, "Consent not required. Showing ads.");
                        callback.onConsentResult(true);
                    }
                },
                formError -> {
                    Log.e(TAG, "Error requesting consent info: " + formError.getErrorCode());
                    callback.onConsentResult(true); // Assume ads can be shown in case of error
                });
    }

    private void showConsentForm(final Activity activity, final ConsentCallback callback) {
        UserMessagingPlatform.loadConsentForm(
                activity,
                consentForm -> {
                    this.consentForm = consentForm;
                    consentForm.show(
                            activity,
                            formError -> {
                                int consentStatus = consentInformation.getConsentStatus();
                                if (consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
                                    Log.d(TAG, "Consent required and not given. Not showing ads.");
                                    callback.onConsentResult(false);
                                } else {
                                    Log.d(TAG, "Consent given or not required. Showing ads.");
                                    callback.onConsentResult(true);
                                }
                            });
                },
                formError -> {
                    Log.e(TAG, "Error loading consent form: " + formError.getErrorCode());
                    callback.onConsentResult(true); // Assume ads can be shown in case of error
                });
    }
}
