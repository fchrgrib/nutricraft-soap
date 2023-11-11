package org.nutricraft.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreatorLevels {

    private String id_creator;
    private int exp;

    public CreatorLevels(){
        this.id_creator="";
        this.exp=0;
    }
    public CreatorLevels(String id_creator, int exp){
        this.id_creator = id_creator;
        this.exp = exp;
    }
    @XmlElement
    public int getExp(){
        return this.exp;
    }
    @XmlElement
    public String getId(){
        return this.id_creator;
    }
}
