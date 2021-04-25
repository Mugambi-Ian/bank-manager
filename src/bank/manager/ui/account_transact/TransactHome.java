package bank.manager.ui.account_transact;

import bank.manager.data.models.Account;
import javax.swing.*;


public class TransactHome extends JPanel {
    private JButton btn_withdraw,btn_deposit,btn_send,btn_close;
    private JLabel lbl_hmeTitle,lbl_name,lbl_date,lbl_balance;
    public Account currentAccount;
    
    public TransactHome(TransactInterface transactInterface) {
        this.transactInterface =transactInterface;
        initComponents();
    }

    public void setCurrentAccount(Account ac){
        lbl_name.setText("Customer Name: "+ac.fullName);
        lbl_date.setText("Created On: "+ac.dateOpened);
        lbl_balance.setText("Account Balance: "+ac.getBalance());
        currentAccount = ac;
    }
                             
    private void initComponents() {
        lbl_hmeTitle = new JLabel();
        btn_withdraw = new JButton();
        btn_deposit = new JButton();
        btn_send = new JButton();
        btn_close = new JButton();
        lbl_name = new JLabel();
        lbl_date = new JLabel();
        lbl_balance = new JLabel();

        lbl_hmeTitle.setFont(new java.awt.Font("Tahoma", 1, 25));
        lbl_hmeTitle.setLabelFor(this);
        lbl_hmeTitle.setText("             Transact");

        btn_withdraw.setText("Withdraw Money");
        btn_withdraw.addActionListener((java.awt.event.ActionEvent evt) -> {
            transactInterface.onWithdraw();
        });

        btn_deposit.setText("Deposit Money");
        btn_deposit.addActionListener((java.awt.event.ActionEvent evt) -> {
            transactInterface.onDeposit();
        });

        btn_send.setText("Send Money");
        btn_send.addActionListener((java.awt.event.ActionEvent evt) -> {
            transactInterface.onSend();
        });

        btn_close.setText("Close");
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
           transactInterface.onClose();
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_close, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(lbl_hmeTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_deposit, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btn_withdraw, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btn_send, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(lbl_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_date, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_balance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_hmeTitle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_withdraw, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btn_deposit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_send, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_date, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbl_balance, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }                   

    private TransactInterface transactInterface;
    public interface TransactInterface{
        void onClose();
        void onDeposit();
        void onWithdraw();
        void onSend();
    }
}

