package klab.dale.fingerprintauthy.models;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by John Dale Julian on 3/31/2016.
 */
public class SecurityManager {

    private static SecurityManager sSecurityManager;
    private Context mAppContext;

    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;

    private SecurityManager(Context appContext) {
        mAppContext = appContext;

        mFingerprintManager = (FingerprintManager) mAppContext.getSystemService(Context.FINGERPRINT_SERVICE);
        mKeyguardManager = (KeyguardManager) mAppContext.getSystemService(Context.KEYGUARD_SERVICE);
    }

    public static SecurityManager get(Context context) {
        if(sSecurityManager == null) {
            sSecurityManager = new SecurityManager(context.getApplicationContext());
        }
        return sSecurityManager;
    }

    public boolean isKeyguardSecure() {
        return mKeyguardManager.isKeyguardSecure();
    }

    public boolean isFingerprintPermissionGranted() {
        return ActivityCompat.checkSelfPermission(mAppContext, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasEnrolledFingerprints() {
        try {
            return mFingerprintManager.hasEnrolledFingerprints();
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return false;
    }
}
