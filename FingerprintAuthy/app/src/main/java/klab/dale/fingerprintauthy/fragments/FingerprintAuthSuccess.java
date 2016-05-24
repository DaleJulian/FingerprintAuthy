package klab.dale.fingerprintauthy.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.SensitiveInfoContentActivity;
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
        final SensitiveInfo sensitiveInfo = (SensitiveInfo) bundle.getSerializable(FingerprintDialog.SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) view.findViewById(R.id.sensitive_info_name)).setText("Attempting to open " + sensitiveInfo.getName() + " info");

        Handler handler = new Handler();
        Runnable runner = new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), SensitiveInfoContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SensitiveInfoContentActivity.SENSITIVE_INFO_CONTENT_KEY, sensitiveInfo);
                intent.putExtras(bundle);
                startActivity(intent);
                dismiss();
            }
        };
        handler.postDelayed(runner, 1000);

        return view;
    }
}
