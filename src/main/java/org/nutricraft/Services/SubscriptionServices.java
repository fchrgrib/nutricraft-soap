package org.nutricraft.Services;


import org.nutricraft.Model.Subscibers;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SubscriptionServices {
    @WebMethod
    public String newSubscription(String idCreator, int idSubscriber);

    @WebMethod
    public Boolean checkSubscription(String idCreator, int idSubscriber);

    @WebMethod
    public List<Integer> getSubscribers(String idCreator);

    @WebMethod
    public List<String> getCreators (int idSubscriber);

    @WebMethod
    public List<Subscibers> getAllSubscription();

    @WebMethod
    public String unsubscribe(String idCreator, int idSubscriber);
}
