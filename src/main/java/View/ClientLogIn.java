package View;

import BLL.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientLogIn {

    JFrame frame=new JFrame();
    JPanel panelPrincipal=new JPanel();
    JButton client=new JButton("Log In");
    JButton clientNou=new JButton("Sign In");
    JButton back=new JButton("Back");
    JLabel username=new JLabel("Username:");
    JLabel password=new JLabel("Password:");
    JTextField usernameTxt=new JTextField(100);
    //JTextField passwordTxt=new JTextField(100);
    JPasswordField passwordTxt=new JPasswordField(100);
    DeliveryService del;

    public ClientLogIn( DeliveryService del) {
        this.del=del;
        this.frame.setSize(500, 300);
        this.frame.setTitle("Client");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        username.setBounds(120,50,100,20);
        usernameTxt.setBounds(200,50,100,20);
        password.setBounds(120,80,100,20);
        passwordTxt.setBounds(200,80,100,20);
        client.setBounds(120,110,100,20);
        clientNou.setBounds(250,110,100,20);
        back.setBounds(50,10,100,20);
        panelPrincipal.add(username);
        panelPrincipal.add(usernameTxt);
        panelPrincipal.add(password);
        panelPrincipal.add(passwordTxt);
        panelPrincipal.add(client);
        panelPrincipal.add(clientNou);
        panelPrincipal.add(back);
        frame.getContentPane().add(panelPrincipal);
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
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                del.getClients();
                if(del.validateClient(usernameTxt.getText(), passwordTxt.getText()))
                {
                    int id=del.getId(usernameTxt.getText());
                    frame.dispose();
                    ClientView cl=new ClientView(del,id);
                }
                else
                    JOptionPane.showMessageDialog(frame, "Wrong username or password!",
                            "Client", JOptionPane.ERROR_MESSAGE);

            }
        });
        clientNou.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(del.validateNewClient(usernameTxt.getText())){
                    int id=del.adaugaClient(usernameTxt.getText(),passwordTxt.getText());
                    frame.dispose();
                    ClientView cl=new ClientView(del,id);

                }
                else
                    JOptionPane.showMessageDialog(frame, "Wrong username or password!",
                            "Client", JOptionPane.ERROR_MESSAGE);

            }
        });
    }
}
