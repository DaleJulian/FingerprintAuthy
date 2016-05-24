package klab.dale.fingerprintauthy.models;

/**
 * Created by John Dale Julian on 3/29/2016.
 */
public class CreditCardInfo extends SensitiveInfo {
    private static final long serialVersionUID = -8217084019025070106L;

    private String mCreditCardNumber;

    private int mSecurityNumber;

    public CreditCardInfo(String name) {
        super(name);
    }

    public String getCreditCardNumber() {
        return mCreditCardNumber;
    }

    public int getSecurityNumber() {
        return mSecurityNumber;
    }


    public void setCreditCardNumber(String creditCardNumber) {
        this.mCreditCardNumber = creditCardNumber;
    }

    public void setSecurityNumber(int securityNumber) {
        this.mSecurityNumber = securityNumber;
    }
}
