package com.reactlibrary;

import android.content.Context;
import android.widget.Toast;
//import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.kt.sw.SecureWalletHelper;
import com.kt.sw.SecureWalletHelperCallback;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.util.Base64;

import android.util.Log;
import java.util.Arrays;

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
  public void getSignature(String did, String needEnc, final Callback cb) {
		final Activity activity = getCurrentActivity();
		final SecureWalletHelper helper = new SecureWalletHelper(activity);
		//final Handler handler = new Handler();

		byte[] privateKey = "yyyyyyyyy".getBytes();
		final byte[] needbytes = needEnc.getBytes();

	  try {
			helper.registNewKey(did, new SecureWalletHelperCallback.RegistNewKeyCallback() {
				@Override
				public void onSuccess(String s) {
					Log.d("sig", "key registration result: " + s);

					/* handler.post(new Runnable() {
						@Override
						public void run() { */
							final SecureWalletHelper signHelper = new SecureWalletHelper(activity);

							signHelper.sign(needbytes, new SecureWalletHelperCallback.SignCallback() {
								@Override
								public void onSuccess(byte[] bytes) {
									String SigStr = Base64.encodeToString(bytes, Base64.NO_WRAP);
		
									Log.d("sig", "signature : " + Arrays.toString(bytes));
									Log.d("sig", "signature str : " + SigStr);
									cb.invoke(false, SigStr);
								}
		
								@Override
								public void onFailure(int i) {
									Log.d("sig", "failed to sign : " + i);
									cb.invoke(true, i);
								}
							});
						//}
					//});					
				}

				@Override
				public void onFailure(int i) {
					Log.d("sig", "failed to register a new key : " + i);
					cb.invoke(true, i);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}