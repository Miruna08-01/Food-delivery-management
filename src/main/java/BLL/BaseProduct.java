package BLL;

import java.io.Serializable;

public class BaseProduct extends MenuItem {
    public BaseProduct(String title, float rating, int calories, int proteins, int fat, int sodium, float price) {
        super(title, rating, calories, proteins, fat, sodium, price);
    }
}
