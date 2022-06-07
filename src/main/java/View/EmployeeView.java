package View;

import App.Main;
import BLL.BaseProduct;
import BLL.DeliveryService;
import BLL.MenuItem;
import BLL.Order;

import javax.management.Notification;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class EmployeeView implements Observer {
    JFrame frame = new JFrame();
    JPanel panelPrincipal = new JPanel();
    JButton back = new JButton("Back");
    DeliveryService del;
    private JTable table;
    private LocalDateTime logInMoment;
    JTextArea area;
    private JScrollPane scroll;
    String s=new String();
    public EmployeeView(DeliveryService del) {
        this.del=del;
        this.frame.setSize(500, 500);
        this.frame.setTitle("Employee ");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        area=new JTextArea();
        area.setEditable(true);
        frame.getContentPane().add(panelPrincipal);
    }
    @Override
    public void update(Observable o, Object arg) {
       Order order=(Order)arg;
        DeliveryService sr=(DeliveryService) o;
        ArrayList<MenuItem>list=del.getOrders().get(order);
        String s="";
        for(int i=0;i<4;i++)
        {
            s+=order.getByIndex(i);
            s+="\n";
        }
        for(MenuItem m:list)
        {
            s+=m.toString();
            s+="\n";

        }
        area.setText(s);
        scroll = new JScrollPane(area);
        scroll.setBounds(40,100,400,350);
        scroll.setViewportView(area);
        panelPrincipal.add(scroll);
        this.frame.setVisible(true);
    }
   public EmployeeView(DeliveryService del, int nr){
        this.del=del;
        TreeMap<Order,ArrayList<MenuItem>>map=new TreeMap<>();
        for(Order o:del.getOrders().keySet()){
            map.put(o,del.getOrders().get(o));
        }
        Order ord=map.firstKey();
        String s="";
       for(int i=0;i<4;i++)
       {
           s+=ord.getByIndex(i);
           s+="\n";
       }
       for(MenuItem m:map.get(ord)){
           s+=m.toString();
           s+="\n";
       }
       this.frame.setSize(500, 500);
       this.frame.setTitle("Employee ");
       this.frame.setLocationRelativeTo(null);
       panelPrincipal.setLayout(null);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       area=new JTextArea();
       area.setEditable(true);
       area.setText(s);
       scroll = new JScrollPane(area);
       scroll.setBounds(40,100,400,350);
       scroll.setViewportView(area);
       frame.getContentPane().add(panelPrincipal);
       panelPrincipal.add(scroll);
       this.frame.setVisible(true);

   }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
