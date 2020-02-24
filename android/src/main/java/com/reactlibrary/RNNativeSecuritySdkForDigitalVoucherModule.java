
package com.reactlibrary;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.kt.sw.SecureWalletHelper;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNNativeSecuritySdkForDigitalVoucherModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNNativeSecuritySdkForDigitalVoucherModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNNativeSecuritySdkForDigitalVoucher";
  }

  @ReactMethod
  public void show(String text) {
    final Activity activity = getCurrentActivity();
      SecureWalletHelper helper = new SecureWalletHelper(activity);
      
   }
}