package App;

import BLL.DeliveryService;
import DAO.Serialize;
import View.Controller;
import View.EmployeeLogIn;
import View.EmployeeView;
import View.MainView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DeliveryService d=new DeliveryService();
        //d.importProducts();
        EmployeeView s=new EmployeeView(d);
        d.addObserver(s);
        MainView m=new MainView();
        Controller v=new Controller(m,d);
        System.out.println(d.getOrders());
    }
}
