package klab.dale.fingerprintauthy.models.Callbacks;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.os.CancellationSignal;
import android.widget.Toast;

import klab.dale.fingerprintauthy.models.SecurityManager;

/**
 * Created by John Dale Julian on 3/31/2016.
 */
public class FingerprintHandler extends AuthenticationCallback {

    private CancellationSignal mCancellationSignal;
    private Context mAppContext;
    private SecurityManager mSecurityManager;
    private OnFinishedAuthenticationListener mAuthenticationListener;

    public FingerprintHandler(Context appContext, SecurityManager securityManager, OnFinishedAuthenticationListener authenticationListener) {
        mAppContext = appContext;
        mSecurityManager = securityManager;
        mAuthenticationListener = authenticationListener;
    }

    public void expectFingerprintAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        mCancellationSignal = new CancellationSignal();

        if(!mSecurityManager.isFingerprintPermissionGranted()) {
            return;
        }

        try {
            fingerprintManager.authenticate(cryptoObject, mCancellationSignal, 0, this, null);
        } catch (SecurityException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void onAuthenticationError(int errMsgId,
                                      CharSequence errString) {
        mAuthenticationListener.onAuthenticationError();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId,
                                     CharSequence helpString) {
        mAuthenticationListener.onAuthenticationHelp();
    }

    @Override
    public void onAuthenticationFailed() {
        mAuthenticationListener.onAuthenticationFailed();
    }

    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
        mAuthenticationListener.onAuthenticationSuccess();
    }

    public interface OnFinishedAuthenticationListener {
        void onAuthenticationSuccess();
        void onAuthenticationFailed();
        void onAuthenticationHelp();
        void onAuthenticationError();
    }
}
