package org.nutricraft.Services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SubscriptionServicesImplement implements SubscriptionServices{
    @WebMethod
    public String newSubscription(){
        return "newSubs";
    }

    @WebMethod
    public String checkSubscription(){
        return "checkSubs";
    }
}
