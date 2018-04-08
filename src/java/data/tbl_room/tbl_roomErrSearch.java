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
public class tbl_roomErrSearch implements Serializable{
    private String errNumberFloor;

    public tbl_roomErrSearch() {
    }

    public tbl_roomErrSearch(String errNumberFloor) {
        this.errNumberFloor = errNumberFloor;
    }

    

    /**
     * @return the errNumberFloor
     */
    public String getErrNumberFloor() {
        return errNumberFloor;
    }

    /**
     * @param errNumberFloor the errNumberFloor to set
     */
    public void setErrNumberFloor(String errNumberFloor) {
        this.errNumberFloor = errNumberFloor;
    }
    
    
}
