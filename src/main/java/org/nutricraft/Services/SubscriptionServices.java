package org.nutricraft.Services;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriptionServices {
    @WebMethod
    public String newSubscription(String idCreator, int idSubscriber);

    @WebMethod
    public Boolean checkSubscription(String idCreator, int idSubscriber);

    @WebMethod
    public String getSubscribers(String idCreator);

    @WebMethod
    public String getCreators(int idSubscriber);

    @WebMethod
    public String getAllSubscription();
}
