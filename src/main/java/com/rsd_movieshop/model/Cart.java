package com.rsd_movieshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private List<CartItem> movieOrder;
    private int cartID;

    public List<CartItem> getMovieOrder() {
        return movieOrder;
    }

    public void setMovieOrder(List<CartItem> movieOrder) {
        this.movieOrder = movieOrder;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Cart(List<CartItem> movieOrder, int cartID) {
        this.movieOrder = movieOrder;
        this.cartID = cartID;
    }
}
