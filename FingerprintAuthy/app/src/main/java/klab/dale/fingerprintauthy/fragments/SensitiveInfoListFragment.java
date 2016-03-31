package klab.dale.fingerprintauthy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.adapters.SensitiveInfoAdapter;
import klab.dale.fingerprintauthy.models.SensitiveInfo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensitiveInfoListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensitiveInfoListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensitiveInfoListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView mSensitiveInfoList;
    private SensitiveInfoAdapter mSensitiveInfoAdapter;

    private void setupSensitiveInfoList(View view) {
        mSensitiveInfoList = (ListView) view.findViewById(R.id.dataListView);
        mSensitiveInfoAdapter = new SensitiveInfoAdapter(getActivity(), mClickListener);
        mSensitiveInfoList.setAdapter(mSensitiveInfoAdapter);

        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe1"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe2"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe3"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe4"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe5"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe6"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe7"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe8"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe9"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe10"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe11"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe12"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Hehe"));
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.sensitive_info_entry):
                    Log.i("Dale", "tapped");
                    break;
            }
        }
    };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SensitiveInfoListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensitiveInfoListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensitiveInfoListFragment newInstance(String param1, String param2) {
        SensitiveInfoListFragment fragment = new SensitiveInfoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_info, container, false);
        setupSensitiveInfoList(view);

        mSensitiveInfoList.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SensitiveInfo sensitiveInfo = (SensitiveInfo) view.getTag(R.id.TAG_SENSITIVE_INFO_ENTRY);
        Log.i("Dale", sensitiveInfo.getName());

        showFingerprintDialogFragment(sensitiveInfo);
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

    private void showFingerprintDialogFragment(SensitiveInfo infoToBePassed) {
        FingerprintDialog fingerprintDialog = new FingerprintDialog();
        Bundle sensitiveInfoBundle = new Bundle();
        sensitiveInfoBundle.putSerializable(FingerprintDialog.SENSITIVE_INFO_BUNDLE_KEY, infoToBePassed);
        fingerprintDialog.setArguments(sensitiveInfoBundle);
        fingerprintDialog.show(getActivity().getFragmentManager(), "FingerprintDialogFragment");
    }
}