package org.nutricraft.Services;


import org.nutricraft.Model.UserLevels;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface LevelUserServices {

    @WebMethod
    public String newUser(int id);

    @WebMethod
    public List<UserLevels> getAllLevelUser();

    @WebMethod
    public Integer getExpUser(int id);

    @WebMethod
    public String addExpUser(int id, int exp);

    @WebMethod
    public String substractExpUser(int id, int exp);

    @WebMethod
    public String deleteExpUser(int id);
}
