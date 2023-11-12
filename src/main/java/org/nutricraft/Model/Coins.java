package org.nutricraft.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coins {
    private String id;
    private int coins;

    public Coins() {
        this.id = "";
        this.coins = 0;
    }

    public Coins(String id, int coins) {
        this.id = id;
        this.coins = coins;
    }

    @XmlElement
    public String getId() {
        return this.id;
    }
    @XmlElement
    public int getCoins() {
        return this.coins;
    }
}
