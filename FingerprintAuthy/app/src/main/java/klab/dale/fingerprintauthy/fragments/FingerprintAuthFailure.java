package klab.dale.fingerprintauthy.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.SensitiveInfo;

public class FingerprintAuthFailure extends DialogFragment {

    public FingerprintAuthFailure() {
        // Required empty public constructor
    }

    public static FingerprintAuthFailure newInstance(String param1, String param2) {
        FingerprintAuthFailure fragment = new FingerprintAuthFailure();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fingerprint_auth_failure, container, false);

        Bundle bundle = getArguments();
        SensitiveInfo sensitiveInfo = (SensitiveInfo) bundle.getSerializable(FingerprintDialog.SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) view.findViewById(R.id.additional_info)).setText(sensitiveInfo.getName() + " will remain locked.");

        return view;
    }
}
