package bank.manager.ui.account_transact;

import bank.manager.data.models.Account;
import bank.manager.data.models.BadValueException;
import bank.manager.data.models.InsufficientFundsException;
import javax.swing.*;

public class DepositMoney extends JPanel {

    private JButton btn_close;
    private JButton btn_proceed;
    private JLabel lbl_title;
    private JLabel lbl_deposit;
    private JTextField txt_deposit;
    private Account current;
    
    public DepositMoney(DepositInterface depositInterface) {
        this.depositInterface = depositInterface;
        initComponents();
    }
    
    private void initComponents() {
        lbl_title = new JLabel();
        lbl_deposit = new JLabel();
        txt_deposit = new JTextField();
        btn_close = new JButton();
        btn_proceed = new JButton();

        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25));
        lbl_title.setText("       Deposit Money");

        lbl_deposit.setText("Deposit Amount");
        
        btn_close.setText("Close");
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
            depositInterface.onClose();
        });

        btn_proceed.setText("Proceed");
        btn_proceed.addActionListener((java.awt.event.ActionEvent evt) -> {
            if(txt_deposit.getText() == null){
                JOptionPane.showMessageDialog(this.getParent(),"Valid Amount Required");
            }else{
                int reply = JOptionPane.showConfirmDialog(null, "Deposit amount "+txt_deposit.getText(),
                        "Confirm Action", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        current.deposit(Double.parseDouble(txt_deposit.getText()));
                        JOptionPane.showMessageDialog(this.getParent(),"Deposit Succesful.");
                        depositInterface.onDeposit(current);
                    } catch (BadValueException ex) {
                        JOptionPane.showMessageDialog(this.getParent(),"Valid Amount Required");
                    }
                }
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(btn_proceed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_deposit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_deposit, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lbl_title, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_deposit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_deposit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_close)
                    .addComponent(btn_proceed))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }                  
    private final DepositInterface depositInterface;

    public void setCurrentAccount(Account currentAccount) {
        current =currentAccount;
    }
    
    public interface DepositInterface{
        
        void onClose();
        void onDeposit(Account current);
    }
                
}

