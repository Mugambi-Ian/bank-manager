package bank.manager.ui.account_edit; 

import javax.swing.*;
import bank.manager.data.models.Account;
import java.util.Map;

public class EditScreen extends JPanel {

    private JButton btn_close, btn_save;
    private JComboBox<String> cmb_type;
    private JLabel lbl_email,lbl_id,lbl_name,lbl_phone,lbl_title,lbl_type,lbl_deposit;
    private JTextField txt_email, txt_id, txt_name,txt_phone,txt_deposit;
    private JLabel txt_status;
    public Map<String,Account> database;
    private Account user;
    

    public EditScreen(EditInterface editInterFace, Map<String, Account> database) {
        this.database = database;
        this.editInterFace = editInterFace;
        initComponents();
    }
    
    public void setAccount(Account x){
        user = x;
        txt_email.setText(user.email);
        txt_name.setText(user.fullName);
        txt_phone.setText(user.phoneNumber);
        cmb_type.setSelectedItem(user.accountType);
        txt_id.setText(user.idNumber);
        btn_close.addActionListener((java.awt.event.ActionEvent evt) -> {
            editInterFace.onClose();
        });
        txt_deposit.setVisible(false);
    }
                        
    public final void initComponents() {
        lbl_title = new JLabel();
        txt_name = new JTextField();
        lbl_name = new JLabel();
        txt_id = new JTextField();
        lbl_id = new JLabel();
        cmb_type = new JComboBox<>();
        lbl_type = new JLabel();
        txt_phone = new JTextField();
        lbl_phone = new JLabel();
        txt_email = new JTextField();
        lbl_email = new JLabel();
        txt_status = new JLabel();
        btn_close = new JButton();
        btn_save = new JButton();
        txt_deposit = new JTextField();
        lbl_deposit = new JLabel();
        
        lbl_title.setFont(new java.awt.Font("Tahoma", 1, 25)); 
        lbl_title.setText("        Edit Account");
        lbl_name.setText("Full Name");
        lbl_id.setText("Id Number");
        cmb_type.setModel(new DefaultComboBoxModel<>(new String[] {"-----", "Savings", "Checking" }));
        lbl_type.setText("Account Type");
        lbl_phone.setText("Phone Number");
        lbl_email.setText("Email");
        txt_deposit.setText("0");
        btn_close.setText("Close");
        btn_save.setText("Save");
        
       

        btn_save.addActionListener((java.awt.event.ActionEvent evt) -> {
            saveAccount();
        });


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_deposit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_deposit, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_type, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(lbl_title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_email, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_email, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addComponent(txt_status, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_close, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_save, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(lbl_title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_name, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_id, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_type, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_type, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_email, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_deposit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_deposit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_status, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_close)
                    .addComponent(btn_save))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }                      

    private void saveAccount(){
        int balance = Integer.parseInt(txt_deposit.getText());
	String fullName=txt_name.getText(),
               idNumber=txt_id.getText(),
               accountType=String.valueOf(cmb_type.getSelectedItem()),
               phoneNumber=txt_phone.getText(),
               emai1=txt_email.getText();
        if(fullName==null){
            JOptionPane.showMessageDialog(this.getParent(),"Full Name Required");
        }   else if(idNumber == null){
            JOptionPane.showMessageDialog(this.getParent(),"Id Number Required");
        }  else if(accountType.equals("-----")){
            JOptionPane.showMessageDialog(this.getParent(),"Select Account Type");
        }  else if(phoneNumber == null){
            JOptionPane.showMessageDialog(this.getParent(),"Phone Number Required");
        }  else if(emai1 == null){
            JOptionPane.showMessageDialog(this.getParent(),"Valid Email Required");
        }else{
            
            Account account = user;
            account.accountType =accountType;
            account.email =emai1;
            account.fullName = fullName;
            account.idNumber= idNumber;
            
            int reply = JOptionPane.showConfirmDialog(null, "Update account for " +fullName, "Confirm Action", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this.getParent(),"Account Updated Succesfully");
                editInterFace.onSave(account);
            }
            
        }
        
        
    }

    

    private final EditInterface editInterFace;
    
    public interface EditInterface{
        void onSave(Account account);
        void onClose();
    }
    
                             
}
