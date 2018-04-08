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
public class tbl_orderErrConfirmForm implements Serializable{
    private String errCustomerNameLength;
    private String errCarDIDLength;
    private String errCardIDformat;
    public tbl_orderErrConfirmForm() {
    }

    public tbl_orderErrConfirmForm(String errCustomerNameLength, String errCarDIDLength, String errCardIDformat) {
        this.errCustomerNameLength = errCustomerNameLength;
        this.errCarDIDLength = errCarDIDLength;
        this.errCardIDformat = errCardIDformat;
    }

    /**
     * @return the errCustomerNameLength
     */
    public String getErrCustomerNameLength() {
        return errCustomerNameLength;
    }

    /**
     * @param errCustomerNameLength the errCustomerNameLength to set
     */
    public void setErrCustomerNameLength(String errCustomerNameLength) {
        this.errCustomerNameLength = errCustomerNameLength;
    }

    /**
     * @return the errCarDIDLength
     */
    public String getErrCarDIDLength() {
        return errCarDIDLength;
    }

    /**
     * @param errCarDIDLength the errCarDIDLength to set
     */
    public void setErrCarDIDLength(String errCarDIDLength) {
        this.errCarDIDLength = errCarDIDLength;
    }

    /**
     * @return the errCardIDformat
     */
    public String getErrCardIDformat() {
        return errCardIDformat;
    }

    /**
     * @param errCardIDformat the errCardIDformat to set
     */
    public void setErrCardIDformat(String errCardIDformat) {
        this.errCardIDformat = errCardIDformat;
    }

    

    
    
}
