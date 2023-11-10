package org.nutricraft.Services;


import org.nutricraft.Model.Coins;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CoinServices {

    @WebMethod
    public List<Coins> getAllCoins();

    @WebMethod
    public Integer getCoins(String id);

    @WebMethod
    public String addCoins(String id, int coins);

    @WebMethod
    public String substractCoins(String id, int coins);

    @WebMethod
    public String deleteCoins(String id);


}
