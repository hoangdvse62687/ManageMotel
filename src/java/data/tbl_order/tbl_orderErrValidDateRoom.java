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
public class tbl_orderErrValidDateRoom implements Serializable{
    private String errSetDateduplicate;
    private String errSetDateInPast;
    private String errDateFormFormat;
    
    public tbl_orderErrValidDateRoom() {
    }

    public tbl_orderErrValidDateRoom(String errSetDateduplicate, String errSetDateInPast, String errDateFormFormat) {
        this.errSetDateduplicate = errSetDateduplicate;
        this.errSetDateInPast = errSetDateInPast;
        this.errDateFormFormat = errDateFormFormat;
    }

    /**
     * @return the errSetDateduplicate
     */
    public String getErrSetDateduplicate() {
        return errSetDateduplicate;
    }

    /**
     * @param errSetDateduplicate the errSetDateduplicate to set
     */
    public void setErrSetDateduplicate(String errSetDateduplicate) {
        this.errSetDateduplicate = errSetDateduplicate;
    }

    /**
     * @return the errSetDateInPast
     */
    public String getErrSetDateInPast() {
        return errSetDateInPast;
    }

    /**
     * @param errSetDateInPast the errSetDateInPast to set
     */
    public void setErrSetDateInPast(String errSetDateInPast) {
        this.errSetDateInPast = errSetDateInPast;
    }

    /**
     * @return the errDateFormFormat
     */
    public String getErrDateFormFormat() {
        return errDateFormFormat;
    }

    /**
     * @param errDateFormFormat the errDateFormFormat to set
     */
    public void setErrDateFormFormat(String errDateFormFormat) {
        this.errDateFormFormat = errDateFormFormat;
    }

    
    
}
