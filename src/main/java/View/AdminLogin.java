package View;

import BLL.DeliveryService;
import DAO.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin {

    JFrame frame=new JFrame();
    JPanel panelPrincipal=new JPanel();
    JButton login=new JButton("Log In");
    JButton signin=new JButton("Sign In");

    JLabel password=new JLabel("Password:");
    JTextField passwordTxt=new JTextField(100);

    JLabel name=new JLabel("Name:");
    JTextField nameTxt=new JTextField(100);
    DeliveryService del;


    public AdminLogin(DeliveryService del){
        this.del=del;
        this.frame.setSize(500, 300);
        this.frame.setTitle("Admin");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        name.setBounds(120,50,100,20);
        nameTxt.setBounds(200,50,100,20);
        password.setBounds(120,80,100,20);
        passwordTxt.setBounds(200,80,100,20);
        login.setBounds(160,110,100,20);
        signin.setBounds(160,130,100,20);
        panelPrincipal.add(password);
        panelPrincipal.add(passwordTxt);
        panelPrincipal.add(login);
        panelPrincipal.add(name);
        panelPrincipal.add(nameTxt);
        panelPrincipal.add(signin);
        frame.getContentPane().add(panelPrincipal);
        panelPrincipal.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                del.getClients();
                if(del.validateAdmin(nameTxt.getText(), passwordTxt.getText()))
                {
                    frame.dispose();
                   AdminView adminView=new AdminView(del);
                }
                else
                    JOptionPane.showMessageDialog(frame, "Wrong username or password!",
                            "Client", JOptionPane.ERROR_MESSAGE);

            }
        });

        signin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (del.validateNewAdmin(nameTxt.getText(),passwordTxt.getText())) {
                    frame.dispose();
                    AdminView adminView = new AdminView(del);

                } else
                    JOptionPane.showMessageDialog(frame, "Wrong username or password!",
                            "Admin", JOptionPane.ERROR_MESSAGE);
            }
        });
        }
    }

