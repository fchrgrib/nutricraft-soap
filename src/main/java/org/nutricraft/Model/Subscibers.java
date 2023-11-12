package org.nutricraft.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscibers {
    private int id;
    private String id_creator;
    private int id_user;

    public Subscibers() {
        this.id = 0;
        this.id_creator = "";
        this.id_user = 0;
    }
    public Subscibers(int id, String id_creator, int id_user){
        this.id = id;
        this.id_creator = id_creator;
        this.id_user = id_user;
    }

    @XmlElement
    public int getId(){
        return this.id;
    }
    @XmlElement
    public String getId_creator(){
        return this.id_creator;
    }
    @XmlElement
    public int getId_user(){
        return this.id_user;
    }


}
