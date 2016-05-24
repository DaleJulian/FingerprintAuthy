package klab.dale.fingerprintauthy.models;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/**
 * Created by John Dale Julian on 3/31/2016.
 */
public class SecurityManager {
    private Context mAppContext;

    private static final String KEY_NAME = "example_key";
    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;
    private KeyStore mKeyStore;
    private KeyGenerator mKeyGenerator;
    private Cipher mCipher;
    private CryptoObject mCryptoObject;

    public SecurityManager(Context context) {
        mAppContext = context;

        mFingerprintManager = (FingerprintManager) mAppContext.getSystemService(Context.FINGERPRINT_SERVICE);
        mKeyguardManager = (KeyguardManager) mAppContext.getSystemService(Context.KEYGUARD_SERVICE);

        if (isSecurityMeasuresValidated()) {
            generateKey();
        }
    }

    private void generateKey() {
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mKeyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException |
                NoSuchProviderException e) {
            throw new RuntimeException(
                    "Failed to get KeyGenerator instance", e);
        }

        try {
            mKeyStore.load(null);
            mKeyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            mKeyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCipherInitialized() {
        try {
            mCipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME,
                    null);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    public Cipher getCipher() {
        return mCipher;
    }

    public void setCipher(Cipher mCipher) {
        this.mCipher = mCipher;
    }

    public CryptoObject getCryptoObject() {
        return mCryptoObject;
    }

    public void setCryptoObject(CryptoObject mCryptoObject) {
        this.mCryptoObject = mCryptoObject;
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

    public FingerprintManager getFingerprintManager() {
        return mFingerprintManager;
    }

    private boolean isSecurityMeasuresValidated() {
        if (isKeyguardSecure()) {
            if (isFingerprintPermissionGranted()) {
                if (hasEnrolledFingerprints()) {
                    return true;
                } else {
                    showToast("No enrolled fingerprints");
                }
            } else {
                showToast("Fingerprint Permission not granted");
            }
        } else {
            showToast("Lock screen security not enabled in settings");
        }
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(mAppContext, message, Toast.LENGTH_LONG).show();
    }
}
