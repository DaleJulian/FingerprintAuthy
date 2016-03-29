package klab.dale.fingerprintauthy.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import klab.dale.fingerprintauthy.R;
import klab.dale.fingerprintauthy.models.SensitiveInfo;

/**
 * Created by John Dale Julian on 3/29/2016.
 */
public class SensitiveInfoAdapter extends BaseAdapter {

    private Activity mActivity;
    private View.OnClickListener mClickListener;
    private List<SensitiveInfo> mSensitiveInfoList;

    public SensitiveInfoAdapter(Activity activity, View.OnClickListener onClickListener) {
        mActivity = activity;
        mClickListener = onClickListener;
        mSensitiveInfoList = new ArrayList<SensitiveInfo>();
    }

    public void addSensitiveInfo(SensitiveInfo sensitiveInfo) {
        mSensitiveInfoList.add(sensitiveInfo);
    }

    public void clearSensitiveInfoList() {
        mSensitiveInfoList.clear();
    }

    @Override
    public int getCount() {
        return mSensitiveInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.sensitive_data_entry, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SensitiveInfo sensitiveInfo = mSensitiveInfoList.get(position);

        viewHolder.name.setText(sensitiveInfo.getName());
        return convertView;
    }

    public static class ViewHolder {
        @Nullable
        @Bind(R.id.sensitive_info_name) TextView name;

        @Nullable
        @Bind(R.id.sensitive_info_bg) ImageView background;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
