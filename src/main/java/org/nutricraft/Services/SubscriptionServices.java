package org.nutricraft.Services;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriptionServices {
    @WebMethod
    public String newSubscription();

    @WebMethod
    public String checkSubscription();
}
