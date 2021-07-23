package com.rsd_movieshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Cart {

    @Id
    @Column(name = "cartid")
    private int cartID;

    public Cart() {
    }

    public Cart(int cartID) {
        this.cartID = cartID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }


    @Override
    public String toString() {
        return "Cart{" +
                ", cartID=" + cartID +
                '}';
    }
}
