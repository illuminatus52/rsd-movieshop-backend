package com.rsd_movieshop.model;

public class CartItem {
    private int quantity;
    private Movie movie;
    private int itemID;

    public CartItem() {
    }

    public CartItem(int quantity, Movie movie ) {
        this.quantity = quantity;
        this.movie = movie;

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
