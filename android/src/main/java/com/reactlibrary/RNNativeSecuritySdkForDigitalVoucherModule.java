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
	  //helper.registNewKey(did);
  @ReactMethod
  public void show(String text, final Callback cb) {
	final Activity activity = getCurrentActivity();
	//final Callback ccb = cb;
      SecureWalletHelper helper = new SecureWalletHelper(activity);
	  String did = "did:bnk:8a77459d24eaf4b3ae2b238a2027b74389cd2830";
	  byte[] privateKey = "yyyyyyyyy".getBytes();

	  try {
		helper.registNewKey(did, privateKey, new SecureWalletHelperCallback.RegistNewKeyCallback() {
			@Override
			public void onSuccess(String s) {
				Log.d("sig", "key registration result: " + s);
			}

			@Override
			public void onFailure(int i) {
				Log.d("sig", "failed to register a new key : " + i);
			}
		});
	  } catch(Exception e) {
		e.printStackTrace();
	  }

	  String needEnc = "20032405007740";
	  byte[] needbytes = needEnc.getBytes();
	  final byte[] sigbyte;

	  try {
		//helper.sign(message, base64EncodedPrivateKey, new SecureWalletHelperCallback.SignCallback() {
		helper.sign(needbytes, new SecureWalletHelperCallback.SignCallback() {
			@Override
			public void onSuccess(byte[] bytes) {
				Log.d("sig", "signature : " + Arrays.toString(bytes));
				cb.invoke(bytes);
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