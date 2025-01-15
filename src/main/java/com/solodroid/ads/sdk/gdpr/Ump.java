package com.solodroid.ads.sdk.gdpr;

import android.app.Activity;
import android.content.Context;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.UserMessagingPlatform;

public class Ump {
    private ConsentInformation consentInformation;
    private ConsentForm consentForm;

    public Ump(ConsentInformation consentInformation) {
        this.consentInformation = consentInformation;
    }


}
