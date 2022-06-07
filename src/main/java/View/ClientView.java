package View;

import BLL.*;
import BLL.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;

public class ClientView {
    JFrame frame=new JFrame();
    JPanel panelPrincipal=new JPanel();
    JButton back=new JButton("Back");
    JButton viewMenu=new JButton("View Menu");
    JLabel keyword=new JLabel("Keyword");
    JButton selectByName=new JButton("Select By Name");
    JButton selectByRating=new JButton("Select By Rating");
    JButton selectByPrice=new JButton("Select By Price");
    JButton selectByCalories=new JButton("Select By Calories");
    JButton selectByProteins=new JButton("Select By Proteins");
    JButton selectBySodium=new JButton("Select By Sodium");
    JButton selectByFats=new JButton("Select By Fats");
    JTextField nrPieces=new JTextField(20);
    JLabel pieces=new JLabel("Number Pieces");
    JTextField keytext=new JTextField(20);
    JLabel count=new JLabel("0");
    JButton MeniulZilei=new JButton("View Specialities");
    JButton orderMeniulZilei=new JButton("Order Meniul Zilei");
    JButton placeOrder=new JButton("Order");
    JLabel sumar=new JLabel("Total price:");
    DeliveryService items;
    HashSet<MenuItem> products;
    int id;
    ArrayList<MenuItem> ord = new ArrayList<MenuItem>();
    CompositeProduct p;
    Object[] columnNames={"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
    JTextArea textArea=new JTextArea();
    SelectedValJTable sel;


    public ClientView(DeliveryService items,int id) {
        this.id=id;
        this.items=items;
        this.frame.setSize(1200, 800);
        this.frame.setTitle("Client ");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        back.setBounds(100,20,100,30);
        viewMenu.setBounds(100,60,100,30);
        keyword.setBounds(30,110,50,30);
        keytext.setBounds(90,120,100,20);
        selectByRating.setBounds(100,160,150,30);
        selectByPrice.setBounds(100,200,150,30);
        selectByCalories.setBounds(100,240,150,30);
        selectByProteins.setBounds(100,280,150,30);
        selectBySodium.setBounds(100,320,150,30);
        selectByFats.setBounds(100,360,150,30);
        selectByName.setBounds(100,400,150,30);
        pieces.setBounds(50,450,100,20);
        nrPieces.setBounds(140,450,100,20);
        placeOrder.setBounds(100,520,150,30);
        MeniulZilei.setBounds(100,560,150,30);
        orderMeniulZilei.setBounds(100,600,150,30);
        count.setBounds(390,20,100,20);
        sumar.setBounds(300,20,200,20);
        panelPrincipal.add(back);
        panelPrincipal.add(viewMenu);
        panelPrincipal.add(keyword);
        panelPrincipal.add(keytext);
        panelPrincipal.add(selectByRating);
        panelPrincipal.add(selectByPrice);
        panelPrincipal.add(selectByCalories);
        panelPrincipal.add(selectByProteins);
        panelPrincipal.add(selectBySodium);
        panelPrincipal.add(selectByFats);
        panelPrincipal.add(selectByName);
        panelPrincipal.add(pieces);
        panelPrincipal.add(nrPieces);
        panelPrincipal.add(placeOrder);
        panelPrincipal.add(MeniulZilei);
        panelPrincipal.add(count);
        panelPrincipal.add(sumar);
        frame.getContentPane().add(panelPrincipal);

        textArea.setBounds(400,20,300,300);
        nrPieces.setText("1");
        nrPieces.setEditable(true);
        panelPrincipal.setVisible(true);
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
        viewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products=items.viewMenu();
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];
                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                       Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }


                 sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
//                ord=s.getRow();
            }
        });
        selectByRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchByRating(Float.parseFloat(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }



                 sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
                //ord=s.getRow();

            }
        });
        selectByPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchByPrice(Float.parseFloat(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }



                sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
                //ord=s.getRow();

            }
        });

        selectByCalories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                    products= items.searchByCalories(Integer.parseInt(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }



              sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
                //ord=s.getRow();
            }
        });

        selectByProteins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchByProteins(Integer.parseInt(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }



                 sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
               // ord=s.getRow();

            }
        });

        selectByFats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchByFat(Integer.parseInt(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }



                sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
               // ord=s.getRow();

            }
        });
        selectBySodium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchBySodium(Integer.parseInt(keytext.getText()));
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }

                sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
                //ord=s.getRow();

            }
        });


        selectByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products= items.searchByName(keytext.getText());
                MenuItem[] itemProduct=new MenuItem[products.size()];
                String[][] Product=new String[products.size()][7];

                ArrayList<MenuItem>p=new ArrayList<>(products);
                itemProduct=  p.toArray(itemProduct);
                for(int i=0;i< products.size();i++){
                    for(int j=0;j<7;j++){
                        Product[i][j]=String.valueOf(itemProduct[i].getByIndex(j));
                    }
                }
                 sel=new SelectedValJTable(columnNames,Product,frame,panelPrincipal,ord,Integer.parseInt(nrPieces.getText()),count);
                //ord=s.getRow();


            }
        });


        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idOrder= 0;
                try {
                    idOrder = items.getOrderNumber();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                LocalDateTime today = LocalDateTime.now();
                double total=0;
                items.createOrder(id,idOrder,today,ord.size(),ord,total);
                ArrayList<MenuItem>ord1=new ArrayList<>(ord);
                ord.removeAll(ord1);

            }
        });


        MeniulZilei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                products=items.viewMenu();
                ArrayList<CompositeProduct>composite=new ArrayList<>();
                for(MenuItem p:products){
                    if (p instanceof CompositeProduct)
                        composite.add((CompositeProduct) p);
                }
                String s="";
                for(CompositeProduct p:composite){
                    s+=p.getTitle()+"\n";
                    s+="Rating "+p.getRating()+"\n";
                    s+="Calories "+p.getCalories()+"\n";
                    s+="Proteins "+p.getProteins()+"\n";
                    s+="Fat "+p.getFat()+"\n";
                    s+="Sodium "+p.getSodium()+"\n";
                    s+="Price "+p.getPrice()+"\n";
                    s+="The product contains:"+"\n";
                    for(MenuItem m:p.getItems()){
                        s+=m.getTitle()+"\n";
                    }
                    s+="\n";
                }

//                textArea.setText(s);
//                JScrollPane myScrollPane = new JScrollPane(textArea);
//                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//                myScrollPane.setBounds(300,60,800,390);
//                panelPrincipal.add(myScrollPane);
                sel=new SelectedValJTable(s,frame,panelPrincipal);





            }
        });

    }
    public void reset() {
        for (MenuItem m : ord) {
            ord.remove(m);
        }
    }

}
