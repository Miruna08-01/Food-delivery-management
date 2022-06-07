package BLL;

import java.io.Serializable;
import java.util.Objects;

public class MenuItem implements Serializable {

    private String title;
    private float rating;
    private int calories;
    private int proteins;
    private int fat;
    private int sodium;
    private float price;

    public MenuItem(String title, float rating, int calories, int proteins, int fat, int sodium, float price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }
    public MenuItem(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  title +String.valueOf(rating)+" "+String.valueOf(calories)+" "+String.valueOf(proteins)+" "+
                String.valueOf(fat)+" "+String.valueOf(sodium)+" "+String.valueOf(price)+"\n";
    }

    public boolean equalsByRating(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Float.compare(menuItem.getRating(), getRating()) == 0;
    }
    public String getByIndex(int index){
        if(index==0){
            return this.getTitle();
        }
        if(index==1){
            return String.valueOf(this.getRating());
        }
        if(index==2){
            return String.valueOf(this.getCalories());
        }
        if(index==3){
            return String.valueOf(this.getProteins());
        }
        if(index==4){
            return String.valueOf(this.getFat());
        }
        if(index==5){
            return String.valueOf(this.getSodium());
        }
        if(index==6){
            return String.valueOf(this.getPrice());
        }
        return null;
    }

    public void getByIndexValue(int index,String value){
        if(index==0){
            this.setTitle(String.valueOf(value));
        }
        if(index==1){
            this.setRating((Float.parseFloat( value)));

        }
        if(index==2){
            this.setCalories(Integer.parseInt(value));

        }
        if(index==3){
            this.setProteins(Integer.parseInt(value));

        }
        if(index==4){
            this.setFat(Integer.parseInt(value));

        }
        if(index==5){
            this.setSodium(Integer.parseInt(value));

        }
        if(index==6){
            this.setPrice(Float.parseFloat(value));

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Float.compare(menuItem.getRating(), getRating()) == 0 && getCalories() == menuItem.getCalories() && getProteins() == menuItem.getProteins() && getFat() == menuItem.getFat() && getSodium() == menuItem.getSodium() && Float.compare(menuItem.getPrice(), getPrice()) == 0 && Objects.equals(getTitle(), menuItem.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getRating(), getCalories(), getProteins(), getFat(), getSodium(), getPrice());
    }
}
