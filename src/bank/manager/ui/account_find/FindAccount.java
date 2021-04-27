package bank.manager.ui.account_find;

import javax.swing.*;
import bank.manager.data.models.Account;
import java.util.Map;

public class FindAccount extends JPanel {

    private JButton btn_close, btn_open;
    private JButton btn_search;
    private JLabel lbl_name, lbl_title;
    private JList<String> lst_results;
    private JScrollPane scr_results;
    private JTextField txt_name;
    public Map<String, Account> database;

    public FindAccount(SearchAccountInterface searchAccountInterface, Map<String, Account> database) {
        this.searchAccountInterface = searchAccountInterface;
        this.database = database;
        initComponents();
    }

    private DefaultListModel<String> getSortedList(String s) {
        DefaultListModel<String> model = new DefaultListModel<>();
        final int[] i = new int[1];
        i[0] = 0;
        database.keySet().forEach(k -> {
            Account ac = database.get(k);
            if (ac.fullName.toLowerCase().contains(s.toLowerCase()) == true) {
                model.add(i[0], ac.userId + ac.shortString());
                i[0] = i[0]++;
            }
        });
        if (model.size() == 0) {
            JOptionPane.showMessageDialog(this.getParent(), "No Result");
        }
        return model;
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
        lbl_name = new JLabel();
        txt_name = new JTextField();
        scr_results = new JScrollPane();
        lst_results = new JList<>(getCustomerList());
        btn_close = new JButton();
        btn_search = new JButton();
        btn_open = new JButton();

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25));
        lbl_title.setText("         Search Account");

        lbl_name.setText("Full Name");

        lst_results.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lst_results.setToolTipText("");
        scr_results.setViewportView(lst_results);

        btn_close.setText("Close");
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
            searchAccountInterface.onClose();
        });

        btn_search.setText("Search");
        btn_search.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (txt_name.getText().equals("") != true) {
                lst_results.setModel(getSortedList(txt_name.getText()));
            } else {
                lst_results.setModel(getCustomerList());
            }
        });

        btn_open.setText("Open Account");
        btn_open.addActionListener((java.awt.event.ActionEvent evt) -> {
            String x = lst_results.getSelectedValue();
            String cc[] = x.split("<#>");
            String id = cc[0];
            searchAccountInterface.onOpen(database.get(id));
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_search, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btn_open,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scr_results))
                .addContainerGap(100, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(lbl_title, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scr_results, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btn_close)
                                .addComponent(btn_search).addComponent(btn_open))
                        .addContainerGap(50, Short.MAX_VALUE)));

        btn_search.getAccessibleContext().setAccessibleDescription("");
    }

    private final SearchAccountInterface searchAccountInterface;

    public interface SearchAccountInterface {
        void onClose();

        void onOpen(Account ac);
    }

}
