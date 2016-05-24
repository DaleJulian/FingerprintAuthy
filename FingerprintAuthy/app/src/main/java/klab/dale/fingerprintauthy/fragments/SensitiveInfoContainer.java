package klab.dale.fingerprintauthy.fragments;

import java.util.ArrayList;
import java.util.List;

import klab.dale.fingerprintauthy.models.CreditCardInfo;
import klab.dale.fingerprintauthy.models.LoginCredentialsInfo;
import klab.dale.fingerprintauthy.models.SensitiveInfo;
import klab.dale.fingerprintauthy.models.TextInfo;

/**
 * Created by Johndale Alfred Julian on 5/24/2016.
 */
public class SensitiveInfoContainer {

    private List<SensitiveInfo> sensitiveInfos;

    public SensitiveInfoContainer() {
        //samples only
        CreditCardInfo unionBankCreditCard = new CreditCardInfo("Unionbank Credit Card Info");
        unionBankCreditCard.setCreditCardNumber("1111 2222 3333 4444");
        unionBankCreditCard.setSecurityNumber(420);

        LoginCredentialsInfo mFacebookAccountInfo = new LoginCredentialsInfo("Facebook Account Info");
        mFacebookAccountInfo.setUserName("johndalealfred");
        mFacebookAccountInfo.setPassword("lodsforever");

        TextInfo mCrushInfo = new TextInfo("Crush Info");
        mCrushInfo.setPrivateText("Loyal to gf");

        LoginCredentialsInfo mPlayStationNetworkInfo = new LoginCredentialsInfo("PSN Account Info");
        mPlayStationNetworkInfo.setUserName("johndalealfred@gmail.com");
        mPlayStationNetworkInfo.setPassword("lodsforever");

        LoginCredentialsInfo mTwitterAccountInfo = new LoginCredentialsInfo("Twitter Account Info");
        mTwitterAccountInfo.setUserName("johndalealfred");
        mTwitterAccountInfo.setPassword("lodsforever");

        sensitiveInfos = new ArrayList<SensitiveInfo>();
        sensitiveInfos.add(unionBankCreditCard);
        sensitiveInfos.add(mFacebookAccountInfo);
        sensitiveInfos.add(mCrushInfo);
        sensitiveInfos.add(mPlayStationNetworkInfo);
        sensitiveInfos.add(mTwitterAccountInfo);
    }

    public List<SensitiveInfo> getSensitiveInfos() {
        return sensitiveInfos;
    }
}
