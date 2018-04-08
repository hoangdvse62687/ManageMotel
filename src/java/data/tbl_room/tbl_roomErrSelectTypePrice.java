/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_room;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class tbl_roomErrSelectTypePrice implements Serializable{
    private String notSelectTypePrice;

    public tbl_roomErrSelectTypePrice() {
    }

    public tbl_roomErrSelectTypePrice(String notSelectTypePrice) {
        this.notSelectTypePrice = notSelectTypePrice;
    }

    /**
     * @return the notSelectTypePrice
     */
    public String getNotSelectTypePrice() {
        return notSelectTypePrice;
    }

    /**
     * @param notSelectTypePrice the notSelectTypePrice to set
     */
    public void setNotSelectTypePrice(String notSelectTypePrice) {
        this.notSelectTypePrice = notSelectTypePrice;
    }
    
    
}
