package klab.dale.fingerprintauthy.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.SensitiveInfo;


public class FingerprintDialog extends DialogFragment {

    public static final String SENSITIVE_INFO_BUNDLE_KEY = "sensitive_info_bundle_key69";

    public FingerprintDialog() {
        // Required empty public constructor
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }   

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
