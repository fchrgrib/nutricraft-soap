package org.nutricraft.Services;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EmailServices {

    @WebMethod
    public String sendEmail(String to, String from, String Password);
}
