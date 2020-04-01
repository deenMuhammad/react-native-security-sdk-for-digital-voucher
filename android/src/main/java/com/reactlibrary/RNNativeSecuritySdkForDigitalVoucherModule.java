package com.reactlibrary;

import android.content.Context;
import android.widget.Toast;

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

	  //helper.registNewKey(did);
  @ReactMethod
  public void getSignature(String did, String needEnc, final Callback cb) {
		final Activity activity = getCurrentActivity();
		SecureWalletHelper helper = new SecureWalletHelper(activity);

		/* helper.newKey(new SecureWalletHelperCallback.NewKeyCallback() {
			@Override
			public void onSuccess(Collection<? extends byte[]> bytes) {
				byte[][] keys = bytes.toArray(new byte[0][]);
	
				Log.d("sig", "private key  : " + Arrays.toString( keys[0] ));
				Log.d("sig", "public key  : " + Arrays.toString( keys[1] ));
			}
			@Override
			public void onFailure(int i) {
				Log.d("sig", "failed to get new key : " + i);
			}
		}); */
      
	  //String did = "did:bnk:7a26eb80e6da9c6e61c59f81ed09bde4e3930c8f";
	  byte[] privateKey = "yyyyyyyyy".getBytes();

	  try {
			helper.registNewKey(did, new SecureWalletHelperCallback.RegistNewKeyCallback() {
				@Override
				public void onSuccess(String s) {
					Log.d("sig", "key registration result: " + s);
				}

				@Override
				public void onFailure(int i) {
					Log.d("sig", "failed to register a new key : " + i);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	  //String needEnc = "20032405007740";
	  byte[] needbytes = needEnc.getBytes();
	  final byte[] sigbyte;

	  try {
		//helper.sign(message, base64EncodedPrivateKey, new SecureWalletHelperCallback.SignCallback() {
		helper.sign(needbytes, new SecureWalletHelperCallback.SignCallback() {
			@Override
			public void onSuccess(byte[] bytes) {
				String SigStr = Base64.encodeToString(bytes, Base64.NO_WRAP);

				Log.d("sig", "signature : " + Arrays.toString(bytes));
				Log.d("sig", "signature str : " + SigStr);
				cb.invoke(SigStr);
			}
			@Override
			public void onFailure(int i) {
				Log.d("sig", "failed to sign : " + i);
			}
		} );
	} catch (Exception e) {
		e.printStackTrace();
	}
	

	  /* String needEnc = "20032405007740";
	  byte[] needbytes = needEnc.getBytes();

      byte[] signature = helper.sign(needbytes);
	  Boolean result = helper.verifyByResolver(needbytes, signature, did);

	  String s = new String(signature);
	  Log.d("sig", Arrays.toString(signature));
	  cb.invoke(s); */
	  //cb.invoke(sigbyte);
   }
}