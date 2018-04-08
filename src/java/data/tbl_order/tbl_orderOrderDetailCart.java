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
public class tbl_orderOrderDetailCart implements Serializable{
    private String customerName;
    private String orderDate;
    private String fromDate;
    private String toDate;

    public tbl_orderOrderDetailCart(String customerName, String orderDate, String fromDate, String toDate) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public tbl_orderOrderDetailCart() {
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
    
    
}
