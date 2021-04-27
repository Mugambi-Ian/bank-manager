
package bank.manager.ui.account_remove;

import javax.swing.*;
import bank.manager.data.models.Account;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class RemoveAccount extends JPanel {
        private JButton btn_close, btn_remove;
        private JComboBox<String> cmb_id;
        private JLabel lbl_balance, lbl_email, lbl_id, lbl_name, lbl_phone, lbl_title, lbl_type, txt_balance, txt_email,
                        txt_phone, txt_type, txt_name;
        private Map<String, Account> database;
        private Map<String, Account> list;
        private Account current;

        public RemoveAccount(RemoveInterface removeInterface, Map<String, Account> database) {
                this.removeInterface = removeInterface;
                this.database = database;
                this.list = new HashMap<>();
                initComponents();
        }

        private DefaultComboBoxModel<String> getCustomerId() {
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                final int p[] = new int[1];
                p[0] = 0;
                database.keySet().forEach(k -> {
                        Account x = database.get(k);
                        if (current == null) {
                                current = x;
                                updateCurrent();
                        }
                        model.addElement(x.userId + "/" + x.idNumber);
                        list.put(x.userId + "/" + x.idNumber, x);
                        p[0] = p[0]++;

                });
                return model;
        }

        private void updateCurrent() {
                txt_name.setText(current.fullName);
                txt_type.setText(current.accountType);
                txt_email.setText(current.email);
                txt_phone.setText(current.phoneNumber);
        }

        public void initComponents() {
                lbl_title = new JLabel();
                lbl_id = new JLabel();
                cmb_id = new JComboBox<>();
                lbl_name = new JLabel();
                txt_name = new JLabel();
                lbl_type = new JLabel();
                txt_type = new JLabel();
                lbl_phone = new JLabel();
                txt_phone = new JLabel();
                lbl_email = new JLabel();
                txt_email = new JLabel();
                lbl_balance = new JLabel();
                txt_balance = new JLabel();
                btn_close = new JButton();
                btn_remove = new JButton();

                txt_balance.setVisible(false);
                lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25));
                lbl_title.setText("         Remove Account");
                lbl_id.setText("Id Number");
                cmb_id.setModel(getCustomerId());
                lbl_name.setText("Full Name");
                lbl_type.setText("Account Type");
                lbl_phone.setText("Phone Number");
                lbl_email.setText("Email");

                btn_close.setText("Close");
                btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
                        removeInterface.onClose();
                });

                btn_remove.setText("Remove");
                btn_remove.addActionListener((java.awt.event.ActionEvent evt) -> {
                        if (current.getBalance() <= 0) {
                                int reply = JOptionPane.showConfirmDialog(null,
                                                "Remove account for " + current.fullName, "Confirm Action",
                                                JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) {
                                        JOptionPane.showMessageDialog(this.getParent(), "Account Removed Succesfully.");
                                        removeInterface.onRemove(current);
                                }
                        } else {
                                JOptionPane.showMessageDialog(this.getParent(), "Account has a positive balance.");
                        }
                });

                cmb_id.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                current = list.get(e.getItem());
                                updateCurrent();
                        }
                });

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                                .createSequentialGroup().addContainerGap(100, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 120,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(10, 10, 10).addComponent(cmb_id,
                                                                                GroupLayout.PREFERRED_SIZE, 180,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_balance, GroupLayout.PREFERRED_SIZE,
                                                                                120, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txt_balance, GroupLayout.PREFERRED_SIZE,
                                                                                180, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_email, GroupLayout.PREFERRED_SIZE,
                                                                                120, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txt_email, GroupLayout.PREFERRED_SIZE,
                                                                                180, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE,
                                                                                120, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE,
                                                                                180, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 120,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txt_type, GroupLayout.PREFERRED_SIZE, 180,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                                                false)
                                                                .addGroup(layout.createSequentialGroup().addComponent(
                                                                                lbl_name, GroupLayout.PREFERRED_SIZE,
                                                                                120, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(txt_name,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                180,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup().addComponent(
                                                                                btn_close, GroupLayout.PREFERRED_SIZE,
                                                                                151, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(btn_remove,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))))
                                .addContainerGap(100, Short.MAX_VALUE)));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
                                                .addComponent(lbl_title)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cmb_id, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txt_type, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_email, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txt_email, GroupLayout.PREFERRED_SIZE, 25,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lbl_balance, GroupLayout.PREFERRED_SIZE,
                                                                                25, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txt_balance, GroupLayout.PREFERRED_SIZE,
                                                                                25, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(btn_close).addComponent(btn_remove))
                                                .addContainerGap(65, Short.MAX_VALUE)));
        }

        private final RemoveInterface removeInterface;

        public interface RemoveInterface {
                void onRemove(Account ac);

                void onClose();
        }
}
