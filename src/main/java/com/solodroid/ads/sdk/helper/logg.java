package com.solodroid.ads.sdk.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class logg {

    private static final String TAG = "DebugUtils";

    public static void d(Context context, String message) {
        if (isDebugBuild(context)) {
            Log.d(TAG, message);
        }
    }

    private static boolean isDebugBuild(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return (0 != (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to determine debug mode", e);
            return false;
        }
    }
}