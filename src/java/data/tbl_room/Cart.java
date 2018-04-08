/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_room;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
public class Cart implements Serializable{
    private Map<String,tbl_roomDetailItem> items;
    private float totalPrice;

    public Cart() {
    }

    public Cart(Map<String, tbl_roomDetailItem> items, float totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Map<String, tbl_roomDetailItem> getItems() {
        return items;
    }

    public void setItems(Map<String, tbl_roomDetailItem> items) {
        this.items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void addItemToCart(String title,tbl_roomDetailItem item){
        if(this.items == null){
            this.items = new HashMap<>();
        }
        if(!this.items.containsKey(title)){
            float Price ;
            if(item.isIsHourPrice()){
                Price = item.getHourPrice() * item.getQuantiy();
            }else{
                Price = item.getDayPrice() * item.getQuantiy();
            }
            this.items.put(title, item);
            this.totalPrice += Price;
        }
    }
    public void removeItemFromCart(String title){
        if(this.items == null){
            return;
        }
        if(this.items.containsKey(title)){
            tbl_roomDetailItem item = items.get(title);
            if(item.isIsHourPrice()){
                totalPrice -= item.getHourPrice() * item.getQuantiy();
            }else{
                totalPrice -= item.getDayPrice() * item.getQuantiy();
            }
            this.items.remove(title);
        }
    }
}
