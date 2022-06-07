package BLL;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private ArrayList<MenuItem> items;
    private String title;
    private float rating;
    private int calories;
    private int proteins;
    private int fat;
    private int sodium;
    private float price;

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public CompositeProduct( ArrayList<MenuItem> items, String title1, float rating1, int calories1, int proteins1, int fat1, int sodium1, float price1) {
        this.items = items;
        this.title = title1;
        this.rating = rating1;
        this.calories = calories1;
        this.proteins = proteins1;
        this.fat = fat1;
        this.sodium = sodium1;
        this.price = price1;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public float getRating() {
        return rating;
    }

    @Override
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public int getProteins() {
        return proteins;
    }

    @Override
    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    public CompositeProduct(ArrayList<MenuItem> items, String title) {
        this.items = items;
        this.title = title;
        for(MenuItem i:this.items){
            this.rating+=i.getRating();
            this.calories+=i.getCalories();
            this.proteins+=i.getProteins();
            this.fat+=i.getFat();
            this.sodium+=i.getSodium();
            this.price+=i.getPrice();
        }
        this.rating=this.rating/this.items.size();
        this.price=this.price-25;
    }

    @Override
    public String toString() {
        return  title+"\n"+
                items +"\n+"+
                "rating "+ rating + "\n"+
                 "calories "+calories + "\n"+
                 "proteins "+proteins +"\n"+
                "fat "+fat +"\n"+
                "sodium "+sodium +"\n"+
                 "price "+price
                ;
    }

}
