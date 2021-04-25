package bank.manager.ui.home; 


import javax.swing.*;

public class HomeScreen extends JPanel {

    private JButton btn_crtAcc,btn_fndAcc,btn_opnAcc,btn_rmvAcc;
    private JLabel lbl_hmeTitle;            

    public HomeScreen(HomeInterface homeInterface) {
        this.homeInterface =homeInterface;
        initComponents();
    }

     @SuppressWarnings("unchecked")                      
    private void initComponents() {
        lbl_hmeTitle = new JLabel();
        btn_rmvAcc = new JButton();
        btn_crtAcc = new JButton();
        btn_opnAcc = new JButton();
        btn_fndAcc = new JButton();
        
        lbl_hmeTitle.setFont(new java.awt.Font("Tahoma", 1, 23)); 
        lbl_hmeTitle.setLabelFor(this);
        lbl_hmeTitle.setText("     Bank Of Mugambi");
        lbl_hmeTitle.setVerticalAlignment(SwingConstants.TOP);
        lbl_hmeTitle.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn_rmvAcc.setText("Remove Account");
        btn_rmvAcc.addActionListener((java.awt.event.ActionEvent evt) -> {
            homeInterface.removeAccount();
        });

        btn_crtAcc.setText("Create Account");
        btn_crtAcc.addActionListener((java.awt.event.ActionEvent evt) -> {
            homeInterface.createAccount();
        });

        btn_opnAcc.setText("Transact");
        btn_opnAcc.addActionListener((java.awt.event.ActionEvent evt) -> {
            homeInterface.openAccount();
        });

        btn_fndAcc.setText("Edit Account");
        btn_fndAcc.addActionListener((java.awt.event.ActionEvent evt) -> {
            homeInterface.findAccount();
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_fndAcc, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_opnAcc, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_crtAcc, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_rmvAcc, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_hmeTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(lbl_hmeTitle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_crtAcc, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rmvAcc, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_fndAcc, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_opnAcc, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }                       

   private HomeInterface homeInterface;
  
    public interface HomeInterface{
      void createAccount();
      void removeAccount();
      void findAccount();
      void openAccount();
    }
}

