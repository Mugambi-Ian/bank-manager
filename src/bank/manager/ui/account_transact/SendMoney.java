package bank.manager.ui.account_transact;

import bank.manager.data.models.Account;
import bank.manager.data.models.BadValueException;
import bank.manager.data.models.InsufficientFundsException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class SendMoney extends JPanel {
    private JButton btn_close, btn_proceed;
    private JComboBox<String> cmb_id;
    private JLabel lbl_id, lbl_name, lbl_phone, lbl_send, lbl_title, lbl_type, txt_name, txt_phone, txt_type;
    private JTextField txt_send;
    private Map<String, Account> db;
    private Map<String, Account> list;
    private Account current, main;

    public SendMoney(SendInterface sendInterface, Map<String, Account> db) {
        initComponents();
        this.db = db;
        this.sendInterface = sendInterface;
        this.list = new HashMap<>();
    }

    public void setCurrentAccount(Account a) {
        cmb_id.setModel(getCustomerList(a));
        main = a;
    }

    private ComboBoxModel<String> getCustomerList(Account a) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        final int p[] = new int[1];
        p[0] = 0;
        db.keySet().forEach(k -> {
            Account x = db.get(k);
            if (a.userId.equals(x.userId) != true) {
                if (current == null) {
                    current = x;
                    updateCurrent();
                }
                ;
                model.addElement(x.userId + "/" + x.idNumber);
                list.put(x.userId + "/" + x.idNumber, x);
                p[0] = p[0]++;
            }
        });
        return model;
    }

    private void updateCurrent() {
        txt_name.setText(current.fullName);
        txt_type.setText(current.accountType);
        txt_phone.setText(current.phoneNumber);
    }

    private void initComponents() {
        lbl_title = new JLabel();
        lbl_send = new JLabel();
        txt_send = new JTextField();
        lbl_id = new JLabel();
        lbl_name = new JLabel();
        lbl_type = new JLabel();
        lbl_phone = new JLabel();
        cmb_id = new JComboBox<>();
        txt_name = new JLabel();
        txt_type = new JLabel();
        txt_phone = new JLabel();
        btn_close = new JButton();
        btn_proceed = new JButton();

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25));
        lbl_title.setText("             Send Money");
        lbl_send.setText("Send Amount");
        lbl_id.setText("Recipient Id");
        lbl_name.setText("Recipient Name");
        lbl_type.setText("Account Type");
        lbl_phone.setText("Phone Number");

        cmb_id.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                current = list.get(e.getItem());
                updateCurrent();
            }
        });

        btn_close.setText("Close");
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
            sendInterface.onClose();
        });

        btn_proceed.setText("Proceed");
        btn_proceed.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (current == null) {
                JOptionPane.showMessageDialog(this.getParent(), "Recipient Required");
            } else if (txt_send.getText() == null) {
                JOptionPane.showMessageDialog(this.getParent(), "Valid Amount Required");
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Send amount " + txt_send.getText() + " money from "
                        + main.fullName + " to " + current.fullName, "Confirm Action", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        main.withdrawal(Double.parseDouble(txt_send.getText()));
                        current.deposit(Double.parseDouble(txt_send.getText()));
                        sendInterface.onProceed(main, current);
                        JOptionPane.showMessageDialog(this.getParent(), "Sent Succesfully.");
                    } catch (BadValueException ex) {
                        JOptionPane.showMessageDialog(this.getParent(), "Valid Amount Required");
                    } catch (InsufficientFundsException ex) {
                        JOptionPane.showMessageDialog(this.getParent(), "Insufficent Funds");
                    }
                }
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_type, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 100,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112).addComponent(btn_proceed, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_send, GroupLayout.PREFERRED_SIZE, 120,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18).addComponent(txt_send, GroupLayout.PREFERRED_SIZE, 180,
                                                GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_id, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(lbl_title, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_send, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_send, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_id, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_type, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_close).addComponent(btn_proceed))
                        .addContainerGap(50, Short.MAX_VALUE)));
    }

    private SendInterface sendInterface;

    public interface SendInterface {
        void onProceed(Account ac1, Account ac2);

        void onClose();
    }

}
