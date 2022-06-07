package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RowSelectionListener implements ListSelectionListener {
JTable table;


    public RowSelectionListener() {

    }

    @Override
    public void valueChanged(ListSelectionEvent event) {

        int viewRow = table.getSelectedRow();

        if (!event.getValueIsAdjusting() && viewRow != -1) {

            int columnIndex = 1;

            // Better to access table row using modelRow rather than viewRow
            int modelRow = table.convertRowIndexToModel(viewRow);

            // Access value at selected row at the second column (columnIndex = 1)
            Object modelvalue = table.getModel().getValueAt(modelRow, columnIndex);

            // Not recommended: same as above but access row using viewRow
            Object tablevalue = table.getValueAt(viewRow, columnIndex);

            // Print cell value
            System.out.println(modelvalue + "=" + tablevalue);
        }
    }

}
