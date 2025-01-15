package com.solodroid.ads.sdk.helper;

public class Log {

    static boolean isDebug;

    public Log setDebug(boolean isDebug) {
        Log.isDebug = isDebug;
        return this;
    }
//    public log(boolean isDebug) {
//        this.isDebug = isDebug;
//    }



    // return Log.d(TAG, value)
    public static void d(String TAG, String value) {
        if (isDebug)
            android.util.Log.d(TAG, value);
    }
    public static void w(String TAG, String value) {
        if (isDebug)
            android.util.Log.w(TAG, value);
    }
    public static void e(String TAG, String value) {
        if (isDebug)
            android.util.Log.e(TAG, value);
    }
}
