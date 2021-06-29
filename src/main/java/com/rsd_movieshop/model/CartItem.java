package com.rsd_movieshop.model;

public class CartItem {
    private int amount;
    private Movie movie;
    private int itemID;

    public CartItem() {
    }

    public CartItem(int amount, Movie movie ) {
        this.amount = amount;
        this.movie = movie;

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
                "amount=" + amount +
                ", movie=" + movie +
                ", itemID=" + itemID +
                '}';
    }
}
