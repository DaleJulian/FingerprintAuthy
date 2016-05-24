package klab.dale.fingerprintauthy.models;

/**
 * Created by John Dale Julian on 3/29/2016.
 */
public class LoginCredentialsInfo extends SensitiveInfo {
    private static final long serialVersionUID = 3288369282339712648L;

    private String mUserName;

    private String mPassword;

    public LoginCredentialsInfo(String name) {
        super(name);
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
