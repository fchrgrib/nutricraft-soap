package org.nutricraft.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserLevels {
    private int exp;
    private int id_user;

    public UserLevels(){
        this.exp=0;
        this.id_user=0;
    }
    public UserLevels(int id_user, int exp){
        this.id_user = id_user;
        this.exp = exp;
    }
    @XmlElement
    public int getExp(){
        return this.exp;
    }
    @XmlElement
    public int getId(){
        return this.id_user;
    }
}
