package org.nutricraft.Services;


import org.nutricraft.Model.CreatorLevels;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService
public interface LevelCreatorServices {
    @WebMethod
    public List<CreatorLevels> getAllLevels();

    @WebMethod
    public int getExp(String id);

    @WebMethod
    public String addExp(String id, int exp);

    @WebMethod
    public String substractExp(String id, int exp);

    @WebMethod
    public String deleteExp(String id);
}
