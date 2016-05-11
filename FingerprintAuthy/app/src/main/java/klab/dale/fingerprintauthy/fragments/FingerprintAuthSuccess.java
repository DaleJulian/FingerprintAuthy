package klab.dale.fingerprintauthy.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.SensitiveInfo;

public class FingerprintAuthSuccess extends DialogFragment {

    public FingerprintAuthSuccess() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FingerprintAuthSuccess newInstance(String param1, String param2) {
        FingerprintAuthSuccess fragment = new FingerprintAuthSuccess();
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
        View view = inflater.inflate(R.layout.fragment_fingerprint_auth_success, container, false);

        Bundle bundle = getArguments();
        SensitiveInfo sensitiveInfo = (SensitiveInfo) bundle.getSerializable(FingerprintDialog.SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) view.findViewById(R.id.sensitive_info_name)).setText("Attempting to open " + sensitiveInfo.getName() + " info");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
