package klab.dale.fingerprintauthy.models;

import java.io.Serializable;

/**
 * Created by John Dale Julian on 3/29/2016.
 */
public class SensitiveInfo implements Serializable {

    private static final long serialVersionUID = 8331197936200053548L;

    private String name;

    public SensitiveInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setString(String name) {
        this.name = name;
    }
}
