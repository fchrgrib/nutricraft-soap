package org.nutricraft.Services;


import org.nutricraft.Model.UserLevels;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface LevelUserServices {

    @WebMethod
    public List<UserLevels> getAllLevel();

    @WebMethod
    public Integer getExp(int id);

    @WebMethod
    public String addExp(int id, int exp);

    @WebMethod
    public String substractExp(int id, int exp);

    @WebMethod
    public String deleteExp(int id);
}
