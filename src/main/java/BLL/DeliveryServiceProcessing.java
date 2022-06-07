package BLL;

import DAO.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface DeliveryServiceProcessing {

    void deleteBaseProduct(String name);
    void updateBaseProduct(MenuItem item);
    void addMenuItem(BaseProduct item);
   void createMenuItem(ArrayList<MenuItem>items ,String name);
    void importProducts();

     void createOrder(int orderId, int clientId, LocalDateTime date, int number, ArrayList<MenuItem> items, double total);
    List<Order> generateReportInterval(int start, int finish);
   List<MenuItem> generateReportDay(LocalDate date);
   HashSet<Client> generateLoyalClients(int times,int amount);
    List<MenuItem> generateMostLikedProducts(float rating);
    HashSet<MenuItem> searchByName(String name);
    HashSet<MenuItem> searchByPrice(float price);
   HashSet<MenuItem> searchByRating(float rating);
    HashSet<MenuItem> searchByProteins(int proteins);
    HashSet<MenuItem> searchByFat(int fat);
    HashSet<MenuItem> searchByCalories(int calories);
    HashSet<MenuItem> searchBySodium(int sodium);
    public HashSet<MenuItem> viewMenu();

  int adaugaClient( String nume, String password);


}
