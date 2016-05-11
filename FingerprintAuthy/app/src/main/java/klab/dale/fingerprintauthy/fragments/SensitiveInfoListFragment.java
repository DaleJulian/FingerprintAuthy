package klab.dale.fingerprintauthy.fragments;

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

public class SensitiveInfoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mSensitiveInfoList;
    private SensitiveInfoAdapter mSensitiveInfoAdapter;

    private void setupSensitiveInfoList(View view) {
        mSensitiveInfoList = (ListView) view.findViewById(R.id.dataListView);
        mSensitiveInfoAdapter = new SensitiveInfoAdapter(getActivity(), mClickListener);
        mSensitiveInfoList.setAdapter(mSensitiveInfoAdapter);

        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Unionbank Credit Card"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Facebook account"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Twitter account"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("PlayStation Network account"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("BPI Credit Card"));
        mSensitiveInfoAdapter.addSensitiveInfo(new SensitiveInfo("Crush name"));
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.sensitive_info_entry):
                    break;
            }
        }
    };

    public SensitiveInfoListFragment() {
        // Required empty public constructor
    }

    public static SensitiveInfoListFragment newInstance(String param1, String param2) {
        SensitiveInfoListFragment fragment = new SensitiveInfoListFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_info, container, false);
        setupSensitiveInfoList(view);

        mSensitiveInfoList.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SensitiveInfo sensitiveInfo = (SensitiveInfo) view.getTag(R.id.TAG_SENSITIVE_INFO_ENTRY);
        Log.i("Dale", sensitiveInfo.getName());

        showFingerprintDialogFragment(sensitiveInfo);
    }

    private void showFingerprintDialogFragment(SensitiveInfo infoToBePassed) {
        FingerprintDialog fingerprintDialog = new FingerprintDialog();
        Bundle sensitiveInfoBundle = new Bundle();
        sensitiveInfoBundle.putSerializable(FingerprintDialog.SENSITIVE_INFO_BUNDLE_KEY, infoToBePassed);
        fingerprintDialog.setArguments(sensitiveInfoBundle);
        fingerprintDialog.show(getActivity().getFragmentManager(), "FingerprintDialogFragment");
    }
}
