
package bank.manager.ui.account_open;

import javax.swing.*;
import bank.manager.data.models.Account;
import java.util.Map;

public class OpenAccount extends JPanel {

    private JButton btn_close, btn_open;
    private JLabel lbl_title;
    private JList<String> lst_results;
    private JScrollPane scr_results;
    public Map<String, Account> database;

    public OpenAccount(OpenAccountInterface openAccountInterface, Map<String, Account> database) {
        this.openAccountInterface = openAccountInterface;
        this.database = database;
        initComponents();
    }

    private DefaultListModel<String> getCustomerList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        final int[] i = new int[1];
        i[0] = 0;
        database.keySet().forEach(k -> {
            Account ac = database.get(k);
            model.add(i[0], ac.userId + ac.shortString());
            i[0] = i[0]++;
        });
        return model;
    }

    public void initComponents() {
        lbl_title = new JLabel();
        scr_results = new JScrollPane();
        lst_results = new JList<>(getCustomerList());
        btn_close = new JButton();
        btn_open = new JButton();

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25));
        lbl_title.setText("           Select Account");

        lst_results.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lst_results.setToolTipText("");
        scr_results.setViewportView(lst_results);

        btn_close.setText("Close");
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
            openAccountInterface.onClose();
        });

        btn_open.setText("Open Account");
        btn_open.addActionListener((java.awt.event.ActionEvent evt) -> {
            String x = lst_results.getSelectedValue();
            String cc[] = x.split("<#>");
            String id = cc[0];
            openAccountInterface.onOpen(database.get(id));
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(btn_open, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scr_results))
                .addContainerGap(100, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(lbl_title, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scr_results, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_close).addComponent(btn_open))
                                .addContainerGap(50, Short.MAX_VALUE)));
    }

    private final OpenAccountInterface openAccountInterface;

    public interface OpenAccountInterface {
        void onOpen(Account ac);

        void onClose();
    }

}
