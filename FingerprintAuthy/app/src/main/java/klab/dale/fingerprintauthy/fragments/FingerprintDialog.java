package klab.dale.fingerprintauthy.fragments;

import android.Manifest;
import android.app.DialogFragment;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.SensitiveInfo;
import klab.dale.fingerprintauthy.models.SecurityManager;


public class FingerprintDialog extends DialogFragment {

    public static final String SENSITIVE_INFO_BUNDLE_KEY = "sensitive_info_bundle_key69";

    private FingerprintManager mFingerprintManager;
    private KeyguardManager mKeyguardManager;

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

        mFingerprintManager = (FingerprintManager) getActivity().getSystemService(Context.FINGERPRINT_SERVICE);
        mKeyguardManager = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);

        validateSecurityMeasures();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentContent = inflater.inflate(R.layout.fragment_fingerprint_dialog, container, false);
        Bundle sensitiveInfoBundle = getArguments();
        SensitiveInfo sensitiveInfo = (SensitiveInfo) sensitiveInfoBundle.getSerializable(SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) fragmentContent.findViewById(R.id.sensitive_info_name)).setText("Attempting to open " + sensitiveInfo.getName() + " info");

        return fragmentContent;
    }

    private void validateSecurityMeasures() {
        if (SecurityManager.get(getActivity()).isKeyguardSecure()) {
            //// TODO: 3/31/2016  "Lock screen security not enabled in Settings"
            Log.i("Dale", "iskeyguardsecure");
        }

        if (SecurityManager.get(getActivity()).isFingerprintPermissionGranted()) {
            ////// TODO: 3/31/2016 "Fingerprint authentication permission not enabled"
            Log.i("Dale", "manifest permission for use fingerprint is granted");
        }

        if (SecurityManager.get(getActivity()).hasEnrolledFingerprints()) {
            // TODO: "Register at least one fingerprint in Settings"
            Log.i("Dale", "fingerprints are present");
        }
    }
}
