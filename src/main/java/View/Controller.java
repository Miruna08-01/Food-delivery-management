package View;

import BLL.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    MainView v;
    DeliveryService del;

    public Controller(MainView v, DeliveryService del) {
        this.v = v;
        v.clientButonListener(new ClientButonListener());
        v.adminButonListener(new AdminButonListener());
        v.employeeButonListener(new EmployeeButonListener());
        this.del=del;
    }

    class ClientButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();
            //ClientView client = new ClientView();
            ClientLogIn cl=new ClientLogIn(del);

        }
    }
    class AdminButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();

            AdminLogin client = new AdminLogin(del);

        }
    }
    class EmployeeButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();

            EmployeeLogIn emp = new EmployeeLogIn(del);



        }
    }
}
