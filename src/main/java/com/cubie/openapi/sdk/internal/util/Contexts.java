package com.cubie.openapi.sdk.internal.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;

import com.cubie.openapi.sdk.internal.intent.SdkConstants;

public class Contexts {
  public static boolean existsPackage(final Context context, final String packageName) {
    try {
      context.getPackageManager().getPackageInfo(packageName, 0);
      return true;
    } catch (final NameNotFoundException e) {
      return false;
    }
  }

  public static String getAppKey(final Context context) {
    return getAppKey(context, context.getPackageName());
  }

  public static String getAppKey(final Context context, final String packageName) {
    try {
      final ApplicationInfo applicationInfo = context.getPackageManager()
          .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
      if (applicationInfo == null) {
        return null;
      }
      final Bundle metaData = applicationInfo.metaData;
      if (metaData == null) {
        return null;
      }
      return metaData.getString(SdkConstants.APP_KEY);
    } catch (final NameNotFoundException e) {
      return null;
    }
  }

  public static Signature[] getSignatures(final Context context, final String packageName) {
    PackageInfo packageInfo;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(packageName,
          PackageManager.GET_SIGNATURES);
      if (packageInfo.signatures != null) {
        return packageInfo.signatures;
      }
    } catch (final NameNotFoundException e) {}
    return new Signature[0];
  }

  public static int getVersionCode(final Context context) {
    return getVersionCode(context, context.getPackageName());
  }

  public static int getVersionCode(final Context context, final String packageName) {
    PackageInfo packageInfo;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
    } catch (final NameNotFoundException e) {
      return -1;
    }
    return packageInfo.versionCode;
  }

  public static int getVersionName(final Context context) {
    return getVersionCode(context, context.getPackageName());
  }

  public static String getVersionName(final Context context, final String packageName) {
    PackageInfo packageInfo;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
    } catch (final NameNotFoundException e) {
      return "";
    }
    return Strings.trimToEmpty(packageInfo.versionName);
  }

  public static String resolvePackageNameForIntent(final Context context, final Intent intent) {
    final ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, 0);
    if (resolveInfo == null) {
      return null;
    }

    if (resolveInfo.activityInfo == null) {
      return null;
    }

    return resolveInfo.activityInfo.packageName;
  }
}
