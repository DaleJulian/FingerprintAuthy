package klab.dale.fingerprintauthy.fragments;

import android.app.DialogFragment;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.*;
import klab.dale.fingerprintauthy.models.Callbacks.FingerprintHandler;
import klab.dale.fingerprintauthy.models.SecurityManager;


public class FingerprintDialog extends DialogFragment implements FingerprintHandler.OnFinishedAuthenticationListener {

    public static final String SENSITIVE_INFO_BUNDLE_KEY = "sensitive_info_bundle_key69";

    private SecurityManager mFingerprintSecurityManager;

    private int triesLeft;

    private TextView triesLeftVal;

    public FingerprintDialog() {
    }

    // TODO: Rename and change types and number of parameters
    public static FingerprintDialog newInstance(String param1, String param2) {
        FingerprintDialog fragment = new FingerprintDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mFingerprintSecurityManager = new SecurityManager(getActivity());
        validateSecurityMeasures();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentContent = inflater.inflate(R.layout.fragment_fingerprint_dialog, container, false);
        Bundle sensitiveInfoBundle = getArguments();
        SensitiveInfo sensitiveInfo = (SensitiveInfo) sensitiveInfoBundle.getSerializable(SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) fragmentContent.findViewById(R.id.sensitive_info_name)).setText("Attempting to open " + sensitiveInfo.getName() + " info");

        if (mFingerprintSecurityManager.isCipherInitialized()) {
            mFingerprintSecurityManager.setCryptoObject(new FingerprintManager.CryptoObject(mFingerprintSecurityManager.getCipher()));
            FingerprintHandler helper = new FingerprintHandler(getActivity(), mFingerprintSecurityManager, this);
            helper.expectFingerprintAuthentication(mFingerprintSecurityManager.getFingerprintManager(), mFingerprintSecurityManager.getCryptoObject());
        }

        triesLeftVal = ((TextView) fragmentContent.findViewById(R.id.tries_left));
        triesLeft = 3;
        triesLeftVal.setText(String.valueOf(triesLeft));

        return fragmentContent;
    }

    private void validateSecurityMeasures() {
        if (mFingerprintSecurityManager.isKeyguardSecure()) {
            //// TODO: 3/31/2016  "Lock screen security not enabled in Settings"
            Log.i("Dale", "iskeyguardsecure");
        }

        if (mFingerprintSecurityManager.isFingerprintPermissionGranted()) {
            ////// TODO: 3/31/2016 "Fingerprint authentication permission not enabled"
            Log.i("Dale", "manifest permission for use fingerprint is granted");
        }

        if (mFingerprintSecurityManager.hasEnrolledFingerprints()) {
            // TODO: "Register at least one fingerprint in Settings"
            Log.i("Dale", "fingerprints are present");
        }
    }

    @Override
    public void onAuthenticationSuccess() {
        Log.i("Dale", "success");
        ((ImageView) getView().findViewById(R.id.fingerprint_icon)).setImageResource(R.drawable.check);
    }

    @Override
    public void onAuthenticationFailed() {
        triesLeft--;
        triesLeftVal.setText(String.valueOf(triesLeft));
        if(triesLeft <= 1) {
            triesLeftVal.setTextColor(Color.RED);

            if(triesLeft <= 0) {
            }
        }
    }

    @Override
    public void onAuthenticationHelp() {
        Log.i("Dale", "help");
    }

    @Override
    public void onAuthenticationError() {
        Log.i("Dale", "error");
    }

    private void displayAuthenticationSuccessPopup () {

    }
}
