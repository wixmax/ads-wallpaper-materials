# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


### InMobi CMP
#-keep class com.inmobi.cmp.ChoiceCmp {public protected *;}
#-keep interface com.inmobi.cmp.ChoiceCmpCallback {public protected *;}
#-keep class com.inmobi.cmp.model.ChoiceError {public protected *;}
#-keep class com.inmobi.cmp.model.NonIABData {public protected *;}
#-keep class com.inmobi.cmp.model.PingReturn {public protected *;}
#-keep class com.inmobi.cmp.core.model.ACData {public protected *;}
#-keep class com.inmobi.cmp.core.model.GDPRData {public protected *;}
#-keep class com.inmobi.cmp.core.model.gbc.GoogleBasicConsents {public protected *;}
#-keep class com.inmobi.cmp.core.model.Vector {public protected *;}
#-keep class com.inmobi.cmp.core.model.mspa.USRegulationData {public protected *;}
#-keep class com.inmobi.cmp.data.model.ChoiceStyle {public protected *;}
#-keep class com.inmobi.cmp.data.model.ChoiceColor {public protected *;}
#-keep class com.inmobi.cmp.model.DisplayInfo {public protected *;}
#
###---------------Begin: proguard configuration for Gson  ----------
## Gson uses generic type information stored in a class file when working with fields. Proguard
## removes such information by default, so configure it to keep all of it.
#-keepattributes Signature
#
## For using GSON @Expose annotation
#-keepattributes *Annotation*
#
## Gson specific classes
#-dontwarn sun.misc.**
##-keep class com.google.gson.stream.** { *; }
#
## Application classes that will be serialized/deserialized over Gson
#-keep class com.google.gson.examples.android.model.** { <fields>; }
#
## Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
## JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
#-keep class * extends com.google.gson.TypeAdapter
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
## Prevent R8 from leaving Data object members always null
#-keepclassmembers,allowobfuscation class * {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#
## Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
#-keep class com.google.gson.reflect.TypeToken
#-keep class * extends com.google.gson.reflect.TypeToken
#-keep public class * implements java.lang.reflect.Type
#
###---------------End: proguard configuration for Gson  ----------
#
### AndroidX
#-dontwarn com.google.android.material.**
#-keep class com.google.android.material.** { *; }
#
#-dontwarn androidx.**
#-keep class androidx.** { *; }
#-keep interface androidx.* { *; }