package View;

import BLL.DeliveryService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import DAO.Serialize;

public class MainView extends JFrame implements  Serializable {
    JFrame frame=new JFrame();
    JPanel panelPrincipal=new JPanel();
    JButton client=new JButton("Client");
    JButton admin=new JButton("Admin");
    JButton employee=new JButton("Employee");



    public MainView() throws IOException {

        this.frame=new JFrame();
        this.frame.setSize(800, 500);
        this.frame.setTitle("Food Delivery");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BufferedImage img= ImageIO.read(new File("wel.jpg"));
        JLabel pic=new JLabel((new ImageIcon(img)));
        panelPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.setPreferredSize(new Dimension(800, 500));
        panelPrincipal.setMaximumSize(new Dimension(800, 500));
        panelPrincipal.add(client);
        panelPrincipal.add(admin);
        panelPrincipal.add(employee);
        panelPrincipal.add(pic);
        frame.getContentPane().add(panelPrincipal);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getClient() {
        return client;
    }

    public void setClient(JButton client) {
        this.client = client;
    }

    public JButton getAdmin() {
        return admin;
    }

    public void setAdmin(JButton admin) {
        this.admin = admin;
    }

    public JButton getEmployee() {
        return employee;
    }

    public void setEmployee(JButton employee) {
        this.employee = employee;
    }
    void clientButonListener(ActionListener mal) {
        client.addActionListener(mal);
    }
    void adminButonListener(ActionListener mal) {admin.addActionListener(mal);
    }
    void employeeButonListener(ActionListener mal) {employee.addActionListener(mal);
    }
}
