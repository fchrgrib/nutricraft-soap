package org.nutricraft.Services;


import org.nutricraft.Model.CreatorLevels;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService
public interface LevelCreatorServices {

    @WebMethod
    public String newCreator(String id);

    @WebMethod
    public List<CreatorLevels> getAllLevelCreator();

    @WebMethod
    public Integer getExpCreator(String id);

    @WebMethod
    public String addExpCreator(String id, int exp);

    @WebMethod
    public String substractExpCreator(String id, int exp);

    @WebMethod
    public String deleteExpCreator(String id);
}
