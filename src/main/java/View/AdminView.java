package View;

import BLL.*;
import BLL.MenuItem;
import DAO.Client;
import DAO.CustomComparator;
import DAO.Serialize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class AdminView {
    JFrame frame=new JFrame();
    JPanel panelPrincipal=new JPanel();
    JButton back=new JButton("back");
    JButton addProduct=new JButton("Add Product");
    JButton removeProduct=new JButton("Remove Product");
    JButton updateProduct=new JButton("Update Product");
    JTextField name=new JTextField(30);
    JTextField rating=new JTextField(30);
    JTextField calories=new JTextField(30);
    JTextField proteins=new JTextField(30);
    JTextField fats=new JTextField(30);
    JTextField sodium=new JTextField(30);
    JTextField price=new JTextField(30);
    JLabel start=new JLabel("Start Hour");
    JLabel end=new JLabel("End Hour");
    JTextField startTxt=new JTextField(30);
    JTextField endTxt=new JTextField(30);
    JButton generateTimeInterval=new JButton("Generate Time Interval");
    JLabel day=new JLabel("Day");
    JLabel month=new JLabel("Month");
    JTextField dayTxt=new JTextField(30);
    JTextField monthTxt=new JTextField(30);
    JButton generateProductsDay=new JButton("Generate Day Products");
    JLabel times=new JLabel("Nr Times");
    JTextField timesTxt=new JTextField(30);
    JLabel value=new JLabel("Value");
    JLabel count=new JLabel("0");
    JTextField valueTxt=new JTextField(30);
    JButton generateLoyalClients=new JButton("Generate Loyal Clients");
    JLabel menuItemName=new JLabel("Menu Item");
    JTextField menuItem=new JTextField(20);
    JButton createMenu=new JButton("Create Menu");
    JButton search=new JButton("Search");
    JButton generatePopularProd=new JButton("Generate Popular Products");
    JLabel a=new JLabel("Name");
    JLabel b=new JLabel("Rating");
    JLabel c=new JLabel("Calories");
    JLabel d=new JLabel("Proteins");
    JLabel e=new JLabel("Fats");
    JLabel f=new JLabel("Sodium");
    JLabel g=new JLabel("Price");
    Object[] columnNames={"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
    ArrayList<MenuItem> ord = new ArrayList<MenuItem>();
    DeliveryService items;
    ArrayList<MenuItem>item=new ArrayList<>();
    JTextArea area=new JTextArea();
    Serialize sr=new Serialize();
    HashSet<MenuItem>products;
    SelectedValJTable sel;


    public AdminView(DeliveryService items){
        this.items=items;
        this.frame=new JFrame();
        this.frame.setSize(1200, 1000);
        this.frame.setTitle("Admin");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.setPreferredSize(new Dimension(500, 500));
        panelPrincipal.setMaximumSize(new Dimension(500, 500));
        back.setBounds(20,20,100,20);
        a.setBounds(20,50,100,20);
        name.setBounds(80,50,100,20);
        b.setBounds(20,80,100,20);
        rating.setBounds(80,80,100,20);
        c.setBounds(20,110,100,20);
        calories.setBounds(80,110,100,20);
        d.setBounds(20,140,100,20);
        proteins.setBounds(80,140,100,20);
        e.setBounds(20,170,100,20);
        fats.setBounds(80,170,100,20);
        f.setBounds(20,200,100,20);
        sodium.setBounds(80,200,100,20);
        g.setBounds(20,230,100,20);
        price.setBounds(80,230,100,20);
        addProduct.setBounds(20,260,150,20);
        removeProduct.setBounds(20,290,150,20);
        updateProduct.setBounds(20,320,150,20);
        start.setBounds(20,350,100,20);
        startTxt.setBounds(80,350,100,20);
        end.setBounds(20,380,100,20);
        endTxt.setBounds(80,380,100,20);
        generateTimeInterval.setBounds(20,410,250,20);
        day.setBounds(20,440,100,20);
        dayTxt.setBounds(80,440,100,20);
        month.setBounds(20,470,100,20);
        monthTxt.setBounds(80,470,100,20);
        generateProductsDay.setBounds(20,500,250,20);
        times.setBounds(20,530,100,20);
        timesTxt.setBounds(80,530,100,20);
        value.setBounds(20,560,100,20);
        valueTxt.setBounds(80,560,100,20);
        generateLoyalClients.setBounds(20,590,250,20);
        menuItemName.setBounds(200,20,100,20);
        menuItem.setBounds(280,20,100,20);
        createMenu.setBounds(360,20,100,20);
        search.setBounds(360,50,100,20);
        generatePopularProd.setBounds(20,620,250,20);
        panelPrincipal.add(back);
        panelPrincipal.add(a);
        panelPrincipal.add(name);
        panelPrincipal.add(b);
        panelPrincipal.add(rating);
        panelPrincipal.add(c);
        panelPrincipal.add(calories);
        panelPrincipal.add(d);
        panelPrincipal.add(proteins);
        panelPrincipal.add(e);
        panelPrincipal.add(fats);
        panelPrincipal.add(f);
        panelPrincipal.add(sodium);
        panelPrincipal.add(g);
        panelPrincipal.add(price);
        panelPrincipal.add(addProduct);
        panelPrincipal.add(removeProduct);
        panelPrincipal.add(start);
        panelPrincipal.add(startTxt);
        panelPrincipal.add(end);
        panelPrincipal.add(endTxt);
        panelPrincipal.add(day);
        panelPrincipal.add(dayTxt);
        panelPrincipal.add(month);
        panelPrincipal.add(monthTxt);
        panelPrincipal.add(generateProductsDay);
        panelPrincipal.add(generateTimeInterval);
        panelPrincipal.add(times);
        panelPrincipal.add(timesTxt);
        panelPrincipal.add(value);
        panelPrincipal.add(valueTxt);
        panelPrincipal.add(generateLoyalClients);
        panelPrincipal.add(menuItemName);
        panelPrincipal.add(menuItem);
        panelPrincipal.add(createMenu);
        panelPrincipal.add(generatePopularProd);
        panelPrincipal.add(updateProduct);
        panelPrincipal.add(search);
        frame.getContentPane().add(panelPrincipal);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    MainView mainView=new MainView();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                items.addMenuItem(p);
                item.add(p);
                JOptionPane.showMessageDialog(null, "SUCCESS");
            }
        });

        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                items.deleteBaseProduct(name.getText());
                JOptionPane.showMessageDialog(null, "SUCCESS");
            }
        });
        updateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                MenuItem m=new MenuItem(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                items.updateBaseProduct(m);
                JOptionPane.showMessageDialog(null, "SUCCESS");
            }
        });
        generateTimeInterval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(null);
                //BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                //JTextArea area=new JTextArea();
                area.setBounds(300,200,300,500);
                List<Order> orders=items.generateReportInterval(Integer.parseInt(startTxt.getText()),Integer.parseInt(endTxt.getText()));
                String[] ord=new String[4];
                JScrollPane scrollPane = new JScrollPane(area);

                scrollPane.setBounds(300,200,300,500);
                for(int j=0;j< orders.size();j++){
                    for(int i=0;i<4;i++){
                        ord[i]=String.valueOf(orders.get(j).getByIndex(i));
                        area.append(ord[i]+" ");
                        area.append("\n");
                    }
                    area.append("\n");
                }
                panelPrincipal.add(scrollPane);
            }
        });
        generateProductsDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(null);
                area.removeAll();
                area.setBounds(300,200,300,500);
                JScrollPane scrollPane = new JScrollPane(area);
                scrollPane.setBounds(300,200,300,500);
                LocalDate time= LocalDate.of(2022,Integer.parseInt(monthTxt.getText()),Integer.parseInt(dayTxt.getText()));
               List<MenuItem> menu=items.generateReportDay(time);
                TreeMap<String,Integer> products=new TreeMap<>();
                for(MenuItem m:menu){
                    if(products.size()==0){
                        products.put(m.getTitle(),1);
                        continue;
                    }
                        if(products.containsKey(m.getTitle())){
                            products.replace(m.getTitle(),products.get(m.getTitle())+1);

                        }
                        else
                            products.put(m.getTitle(),1);
                }
                TreeMap<String, Integer> sortedMap = sortMapByValue(products);
               for(Map.Entry<String,Integer>entry: sortedMap.entrySet()){
                   area.append(entry.getKey()+ " "+String.valueOf(entry.getValue())+"\n");
               }

                panelPrincipal.add(scrollPane);
            }
        });

        generateLoyalClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(null);
                area.removeAll();
                //BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
                HashSet<Client>clients=items.generateLoyalClients(Integer.parseInt(timesTxt.getText()),Integer.parseInt(valueTxt.getText()));
                //JTextArea area=new JTextArea();
                area.setBounds(300,200,300,500);
                String[] ord=new String[2];
                JScrollPane scrollPane = new JScrollPane(area);
                scrollPane.setBounds(300,200,300,500);
                ArrayList<Client> cl=new ArrayList<>(clients);
                for(int j=0;j< clients.size();j++){
                    for(int i=0;i<2;i++){
                        ord[i]=String.valueOf(cl.get(j).getByIndex(i));
                        area.append(ord[i]+" ");
                        area.append("\n");
                    }
                    area.append("\n");
                }
                panelPrincipal.add(scrollPane);

            }
        });

        createMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //BaseProduct p=new BaseProduct(name.getText(),Float.parseFloat(rating.getText()),Integer.parseInt(calories.getText()),Integer.parseInt(proteins.getText()),Integer.parseInt(fats.getText()),Integer.parseInt(sodium.getText()),Float.parseFloat(price.getText()));
              items.createMenuItem(item,menuItem.getText());
                JOptionPane.showMessageDialog(null, "SUCCESS");
            }
        });

        generatePopularProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(null);
//                panelPrincipal.add(scrollPane);
                List<MenuItem>it=items.generateMostLikedProducts(Float.parseFloat(timesTxt.getText()));
                area.setBounds(300,200,300,500);
                String[] ord=new String[7];
                JScrollPane scrollPane = new JScrollPane(area);
                scrollPane.setBounds(300,200,300,500);
                for(int j=0;j< it.size();j++){
                    for(int i=0;i<7;i++){
                        ord[i]=String.valueOf(it.get(j).getByIndex(i));
                        area.append(ord[i]+" ");
                        area.append("\n");
                    }
                    area.append("\n");
                }
                panelPrincipal.add(scrollPane);

            }
        });


        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(name.getText().equals("") && rating.getText().equals("")
               && calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
               && sodium.getText().equals("") && price.getText().equals("")){
                   products=items.viewMenu();
               }
                if(!name.getText().equals("") && rating.getText().equals("")
                        && calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
                        && sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchByName(name.getText());
                }
                if(name.getText().equals("") && !rating.getText().equals("")
                        && calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
                        && sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchByRating(Float.parseFloat(rating.getText()));
                }
                if(name.getText().equals("") && rating.getText().equals("")
                        && !calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
                        && sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchByCalories(Integer.parseInt(calories.getText()));
                }
                if(name.getText().equals("") && rating.getText().equals("")
                        && calories.getText().equals("") && !proteins.getText().equals("") && fats.getText().equals("")
                        && sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchByProteins(Integer.parseInt(proteins.getText()));
                }
                if(name.getText().equals("") && rating.getText().equals("")
                        && calories.getText().equals("") && proteins.getText().equals("") && !fats.getText().equals("")
                        && sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchByFat(Integer.parseInt(fats.getText()));
                }

                if(name.getText().equals("") && rating.getText().equals("")
                        && calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
                        && !sodium.getText().equals("") && price.getText().equals("")){
                    products=items.searchBySodium(Integer.parseInt(sodium.getText()));
                }
                if(name.getText().equals("") && rating.getText().equals("")
                        && calories.getText().equals("") && proteins.getText().equals("") && fats.getText().equals("")
                        && sodium.getText().equals("") && !price.getText().equals("")){
                    products=items.searchByPrice(Float.parseFloat(price.getText()));
                }
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];
                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }


                sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,item,1,count);

            }
        });

    }

    public static TreeMap<String, Integer> sortMapByValue(TreeMap<String, Integer> map){
        Comparator<String> comparator = new CustomComparator(map);

        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    }
}
