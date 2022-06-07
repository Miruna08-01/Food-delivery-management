package DAO;

import BLL.CompositeProduct;
import BLL.MenuItem;
import BLL.Order;
import com.sun.source.tree.Tree;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Serialize implements Serializable {

    public void serializeMap(Map<Order, ArrayList<MenuItem>> items, String path) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(items);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<Order, ArrayList<MenuItem>> deserializeMap(Map<Order, ArrayList<MenuItem>> items, String path) {
        Map<Order,ArrayList<MenuItem>> list=new TreeMap<>();
        try {
            FileInputStream file = new FileInputStream(path);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                list = (Map<Order, ArrayList<MenuItem>>) in.readObject();
                in.close();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void serializeCollection(Collection collection, String path) {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(collection);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<?> deserializeCollection(Collection<?> items, String path) {
        Collection<?>array=null;
        int ok=0;
        try {
            FileInputStream file = new FileInputStream(path);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                if(path.equals("orders.ser")){
                    array=new HashSet<Order>();
                    array = (HashSet<Order>) in.readObject();
                    ok=0;
                }
                if(path.equals("Clients.ser")){

                    array=new ArrayList<Client>();
                    array = (List<Client>) in.readObject();
                    ok=0;
                }
                if(path.equals("Employees")){

                    array=new ArrayList<Employee>();
                    array = (List<Employee>) in.readObject();
                    ok=0;
                }
                if(path.equals("Admins")){

                    array=new ArrayList<Admin>();
                    array = (List<Admin>) in.readObject();
                    ok=0;
                }
                if(path.equals("Composite.ser")){

                    array=new ArrayList<CompositeProduct>();
                    array = (List<CompositeProduct>) in.readObject();
                    ok=0;
                }

                if(path.equals( "DeliveryService.ser")){

                    array=new HashSet<MenuItem>();
                    array = (Set<MenuItem>) in.readObject();
                    ok=1;
                }
                in.close();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }
//
//
//    public void serializeOrderList(List<Order> items,String path) {
//        try {
//            FileOutputStream file = new FileOutputStream(path);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(items);
//            out.close();
//            file.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @SuppressWarnings("unchecked")
//    public List<Order> deserializeOrderList(List<Order> items, String path) {
//        List<Order> list=new ArrayList<>();
//        try {
//            FileInputStream file = new FileInputStream(path);
//            if (file.available() > 0) {
//                ObjectInputStream in = new ObjectInputStream(file);
//                list = (List<Order>) in.readObject();
//                in.close();
//                file.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public void serializeClients(ArrayList<Client>items, String path) {
//        try {
//            FileOutputStream file = new FileOutputStream(path);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(items);
//            out.close();
//            file.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<Client> deserializeClients(ArrayList<Client> items, String path) {
//       List<Client>list=new ArrayList<>();
//        try {
//            FileInputStream file = new FileInputStream(path);
//            if (file.available() > 0) {
//                ObjectInputStream in = new ObjectInputStream(file);
//                list = (List<Client>) in.readObject();
//                in.close();
//                file.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//
//    public void serializeClients(ArrayList<Client>items, String path) {
//        try {
//            FileOutputStream file = new FileOutputStream(path);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(items);
//            out.close();
//            file.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<CompositeProduct> deserializeComposites(ArrayList<CompositeProduct> items, String path) {
//        List<Client>list=new ArrayList<>();
//        try {
//            FileInputStream file = new FileInputStream(path);
//            if (file.available() > 0) {
//                ObjectInputStream in = new ObjectInputStream(file);
//                list = (List<Client>) in.readObject();
//                in.close();
//                file.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


}
