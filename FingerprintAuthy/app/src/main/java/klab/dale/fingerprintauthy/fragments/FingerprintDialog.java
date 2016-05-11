package klab.dale.fingerprintauthy.fragments;

import android.animation.Animator;
import android.app.DialogFragment;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    SensitiveInfo sensitiveInfo;

    private ImageView mFingerprintIcon;

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
        mFingerprintSecurityManager = new SecurityManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentContent = inflater.inflate(R.layout.fragment_fingerprint_dialog, container, false);
        Bundle sensitiveInfoBundle = getArguments();
        sensitiveInfo = (SensitiveInfo) sensitiveInfoBundle.getSerializable(SENSITIVE_INFO_BUNDLE_KEY);
        ((TextView) fragmentContent.findViewById(R.id.sensitive_info_name)).setText("Attempting to open " + sensitiveInfo.getName() + " info");

        triesLeftVal = ((TextView) fragmentContent.findViewById(R.id.tries_left));
        triesLeft = 3;
        triesLeftVal.setText(String.valueOf(triesLeft));

        return fragmentContent;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mFingerprintSecurityManager.isCipherInitialized()) {
            mFingerprintSecurityManager.setCryptoObject(new FingerprintManager.CryptoObject(mFingerprintSecurityManager.getCipher()));
            FingerprintHandler helper = new FingerprintHandler(getActivity(), mFingerprintSecurityManager, this);
            helper.expectFingerprintAuthentication(mFingerprintSecurityManager.getFingerprintManager(), mFingerprintSecurityManager.getCryptoObject());
        }
    }

    @Override
    public void onAuthenticationSuccess() {
        Log.i("Dale", "success");
//        ((ImageView) getView().findViewById(R.id.fingerprint_icon)).setImageResource(R.drawable.check);
        displayAuthenticationSuccessPopup(sensitiveInfo);
    }

    @Override
    public void onAuthenticationFailed() {
        mFingerprintIcon = (ImageView) getView().findViewById(R.id.fingerprint_icon);

        triesLeft--;
        triesLeftVal.setText(String.valueOf(triesLeft));
        if(triesLeft <= 1) {
            triesLeftVal.setTextColor(Color.RED);
        }

        Animation shakeAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.shake);
        mFingerprintIcon.setAnimation(shakeAnimation);
        mFingerprintIcon.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("Dale", "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("Dale", "onAnimationEnd");
                if(triesLeft == 0) {
                    displayAuthenticationFailurePopup(sensitiveInfo);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("Dale", "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("Dale", "onAnimationRepeat");
            }
        }).start();


    }

    @Override
    public void onAuthenticationHelp() {
        Log.i("Dale", "help");
    }

    @Override
    public void onAuthenticationError() {
        Log.i("Dale", "error");
    }

    private void displayAuthenticationSuccessPopup (SensitiveInfo sensitiveInfo) {
        FingerprintAuthSuccess authSuccessPopup = new FingerprintAuthSuccess();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SENSITIVE_INFO_BUNDLE_KEY, sensitiveInfo);
        authSuccessPopup.setArguments(bundle);
        authSuccessPopup.show(getActivity().getFragmentManager(), "myFrag");
        dismiss();
    }

    private void displayAuthenticationFailurePopup (SensitiveInfo sensitiveInfo) {
        FingerprintAuthFailure authFailurePopup= new FingerprintAuthFailure();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SENSITIVE_INFO_BUNDLE_KEY, sensitiveInfo);
        authFailurePopup.setArguments(bundle);
        authFailurePopup.show(getActivity().getFragmentManager(), "myFrag");
        dismiss();
    }
}
