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
public class tbl_roomDetailItem implements Serializable{
    private float hourPrice;
    private float dayPrice;
    private int quantiy;
    private boolean isHourPrice;

    public tbl_roomDetailItem() {
    }

    public tbl_roomDetailItem(float hourPrice, float dayPrice, int quantiy, boolean isHourPrice) {
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
        this.quantiy = quantiy;
        this.isHourPrice = isHourPrice;
    }

    /**
     * @return the hourPrice
     */
    public float getHourPrice() {
        return hourPrice;
    }

    /**
     * @param hourPrice the hourPrice to set
     */
    public void setHourPrice(float hourPrice) {
        this.hourPrice = hourPrice;
    }

    /**
     * @return the dayPrice
     */
    public float getDayPrice() {
        return dayPrice;
    }

    /**
     * @param dayPrice the dayPrice to set
     */
    public void setDayPrice(float dayPrice) {
        this.dayPrice = dayPrice;
    }

    /**
     * @return the quantiy
     */
    public int getQuantiy() {
        return quantiy;
    }

    /**
     * @param quantiy the quantiy to set
     */
    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    /**
     * @return the isHourPrice
     */
    public boolean isIsHourPrice() {
        return isHourPrice;
    }

    /**
     * @param isHourPrice the isHourPrice to set
     */
    public void setIsHourPrice(boolean isHourPrice) {
        this.isHourPrice = isHourPrice;
    }

    
    
}
