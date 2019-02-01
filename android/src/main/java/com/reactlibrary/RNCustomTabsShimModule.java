package com.reactlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RNCustomTabsShimModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNCustomTabsShimModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNCustomTabsShim";
    }

    @ReactMethod
    public void hasPackagesForUrl(String url, Promise promise) {
        try {
            Boolean hasPackages = !getNativeAppPackage(this.reactContext, url).isEmpty();
            promise.resolve(hasPackages);
        } catch (Exception e) {
            promise.resolve(false);
        }
    }

    private static Set<String> getNativeAppPackage(Context context, String url) {
        PackageManager pm = context.getPackageManager();

        //Get all Apps that resolve a generic url
        Intent browserActivityIntent
                = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));
        Set<String> genericResolvedList
                = extractPackagenames(pm.queryIntentActivities(browserActivityIntent, 0));

        //Get all apps that resolve the specific Url
        Intent specializedActivityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Set<String> resolvedSpecializedList
                = extractPackagenames(pm.queryIntentActivities(specializedActivityIntent, 0));

        //Keep only the Urls that resolve the specific, but not the generic urls
        resolvedSpecializedList.removeAll(genericResolvedList);

        return resolvedSpecializedList;
    }

    private static Set<String> extractPackagenames(List<ResolveInfo> resolveInfos) {
        Set<String> packageNameSet = new HashSet<>();
        for (ResolveInfo ri: resolveInfos) {
            packageNameSet.add(ri.activityInfo.packageName);
        }
        return packageNameSet;
    }
}