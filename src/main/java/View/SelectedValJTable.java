package View;

import BLL.CompositeProduct;
import BLL.MenuItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;



public class SelectedValJTable implements ListSelectionListener {
    JTable table;
    Object[] columns;
    Object[][] data;
    JLabel count;

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    ArrayList<MenuItem> row;
    JScrollPane scroll;
   int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SelectedValJTable(Object[] columns, Object[][] data, JFrame frame, JPanel p, ArrayList<MenuItem> row, int number,JLabel count) {
       this.columns=columns;
       this.data=data;
       this.row=row;
       this.number=number;
       this.count=count;
        TableModel model = new AbstractTableModel() {
            public int getColumnCount() {
                return columns.length;
            }
            public int getRowCount() {
                return data.length;
            }
            public Object getValueAt(int row, int col) {
                return data[row][col];
            }
            public String getColumnName(int column) {
                return (String) columns[column];
            }
            public Class getColumnClass(int col) {
                return getValueAt(0, col).getClass();
            }
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };
        for(Component c:p.getComponents()){
            if(c instanceof JButton || c instanceof JLabel || c instanceof JTextField ){
                continue;
            }
            else
                p.remove(c);
        }
        table = new JTable(model);
        ListSelectionModel listModel = table.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listModel.addListSelectionListener(this);
      scroll = new JScrollPane(table);
        //scroll.setPreferredSize(new Dimension(300, 300));
        scroll.setBounds(300,70,800,700);
        p.add(scroll);
        frame.setVisible(true);
    }

    public void valueChanged(ListSelectionEvent e) {
        int[] sel;
        String value;
        MenuItem m=new MenuItem();
        if (!e.getValueIsAdjusting()) {
            sel = table.getSelectedRows();
            if (sel.length > 0) {

                for (int i = 0; i < columns.length; i++) {
                    // get data from JTable
                    TableModel tm = table.getModel();
                    value = (String) tm.getValueAt(sel[0], i);
                    m.getByIndexValue(i,value);
                }

                count.setText(String.valueOf(Float.parseFloat(count.getText())+m.getPrice()));
                for(int j=0;j<number;j++){
                    row.add(m);
                }
                System.out.println();
            }
        }
    }
    public ArrayList<MenuItem> getRow(){
        return row;
    }

    public SelectedValJTable(String s, JFrame frame, JPanel p){
        JTextArea area=new JTextArea();
        area.setText(s);
        scroll = new JScrollPane(area);
        //scroll.setPreferredSize(new Dimension(300, 300));
        scroll.setBounds(300,30,800,700);
        p.add(scroll);
        frame.setVisible(true);
    }
}
