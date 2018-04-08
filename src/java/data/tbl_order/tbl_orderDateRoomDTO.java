/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_order;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class tbl_orderDateRoomDTO implements Serializable{
    private String roomID;
    private String fromDate;
    private String toDate;
    private float price;
    private float total;
    private boolean isHourPrice;

    public tbl_orderDateRoomDTO() {
    }

    public tbl_orderDateRoomDTO(String roomID, String fromDate, String toDate, float price, float total, boolean isHourPrice) {
        this.roomID = roomID;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.total = total;
        this.isHourPrice = isHourPrice;
    }

    /**
     * @return the roomID
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
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
