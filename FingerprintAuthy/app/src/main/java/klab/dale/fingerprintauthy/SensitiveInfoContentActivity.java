package klab.dale.fingerprintauthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import klab.dale.fingerprintauthy.models.CreditCardInfo;
import klab.dale.fingerprintauthy.models.LoginCredentialsInfo;
import klab.dale.fingerprintauthy.models.SensitiveInfo;
import klab.dale.fingerprintauthy.models.TextInfo;

/**
 * Created by Johndale Alfred Julian on 5/24/2016.
 */
public class SensitiveInfoContentActivity extends AppCompatActivity {
    public static final String SENSITIVE_INFO_CONTENT_KEY = "sensitive_info_content_key";

    private SensitiveInfo mSensitiveInfo;

    private TextView content1, content2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_info);

        content1 = (TextView) findViewById(R.id.content1);
        content2 = (TextView) findViewById(R.id.content2);

        mSensitiveInfo = (SensitiveInfo) getIntent().getSerializableExtra(SENSITIVE_INFO_CONTENT_KEY);

        setTitle(mSensitiveInfo.getName());

        if (mSensitiveInfo instanceof CreditCardInfo) {
            CreditCardInfo cc = (CreditCardInfo )mSensitiveInfo;
            content1.setText("Credit card number: " + cc.getCreditCardNumber());
            content2.setText("Security number: " + cc.getSecurityNumber());
        } else if (mSensitiveInfo instanceof LoginCredentialsInfo) {
            LoginCredentialsInfo login = (LoginCredentialsInfo)mSensitiveInfo;
            content1.setText("Username: " + login.getUserName());
            content2.setText("Password: " + login.getPassword());
        } else if (mSensitiveInfo instanceof TextInfo) {
            TextInfo text = (TextInfo) mSensitiveInfo;
            content1.setText("Info: " + text.getPrivateText());
        }

    }

}
