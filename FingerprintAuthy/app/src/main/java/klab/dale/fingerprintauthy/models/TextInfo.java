package klab.dale.fingerprintauthy.models;

/**
 * Created by John Dale Julian on 3/29/2016.
 */
public class TextInfo extends SensitiveInfo {
    private static final long serialVersionUID = -1199118214536225964L;

    private String mPrivateText;

    public TextInfo(String name) {
        super(name);
    }

    public String getPrivateText() {
        return mPrivateText;
    }

    public void setPrivateText(String mPrivateText) {
        this.mPrivateText = mPrivateText;
    }
}
