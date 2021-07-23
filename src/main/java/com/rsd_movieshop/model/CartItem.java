package com.rsd_movieshop.model;

import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @Column(name = "itemid")
    private int itemID;

    @OneToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartid")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderid")
    private Order order;

    private int quantity;

    public CartItem() {
    }

    public CartItem(Movie movie, Cart cart, int itemID, int quantity) {
        this.movie = movie;
        this.cart = cart;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "quantity=" + quantity +
                ", movie=" + movie +
                ", itemID=" + itemID +
                '}';
    }
}
