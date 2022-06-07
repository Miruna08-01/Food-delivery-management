package DAO;

import BLL.MenuItem;
import BLL.Order;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class FileWriter {
    private File f;
    private FileWriter fr;

    public  void generateBill(Order order, List<MenuItem> products) {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("bill.txt");
            writer.write(order.toString());
            writer.write("\n"+"Price:" + order.getTotalPrice()+"\n");
            writer.write("Products: "+ products+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }


    }
    public  void generateTimeInterval( List<Order> products) {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("reportTimeInterval.txt");
            for(Order m:products)
            {
                writer.write(m.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }


    }

    public  void generateDay( List<MenuItem> products) {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("reportDayInterval.txt");
            for(MenuItem m:products)
            {
                writer.write(m.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }


    }

    public  void generateLikedProd( List<MenuItem> products) {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("reportLikedProd.txt");
            for(MenuItem m:products)
            {
                writer.write(m.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }


    }

    public  void generateLoyalClients( HashSet<Client> products) {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("reportLoyalClients.txt");
            for(Client m:products)
            {
                writer.write(m.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error at generating bill");
        }


    }


}
