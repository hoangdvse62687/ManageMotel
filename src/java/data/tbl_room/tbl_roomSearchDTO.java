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
public class tbl_roomSearchDTO implements Serializable{
    private String roomID;
    private String description;
    private float hourPrice;
    private float dayPrice;
    private int floor;
    private boolean isBook;
    public tbl_roomSearchDTO() {
    }

    public tbl_roomSearchDTO(String roomID, String description, float hourPrice, float dayPrice, int floor, boolean isBook) {
        this.roomID = roomID;
        this.description = description;
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
        this.floor = floor;
        this.isBook = isBook;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * @return the isBook
     */
    public boolean isIsBook() {
        return isBook;
    }

    /**
     * @param isBook the isBook to set
     */
    public void setIsBook(boolean isBook) {
        this.isBook = isBook;
    }
    
    
}
