package bank.manager;

import bank.manager.ui.home.HomeScreen;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import bank.manager.data.models.Account;
import bank.manager.data.util.DatabaseHandler;
import bank.manager.ui.account_find.FindAccount;
import bank.manager.ui.account_open.OpenAccount;
import bank.manager.ui.account_remove.RemoveAccount;
import bank.manager.ui.account_edit.EditScreen;
import bank.manager.ui.account_create.CreateScreen;
import bank.manager.ui.account_transact.DepositMoney;
import bank.manager.ui.account_transact.SendMoney;
import bank.manager.ui.account_transact.TransactHome;
import bank.manager.ui.account_transact.WithdrawMoney;

public class Index {

    private JFrame frame;
    private JPanel activeScreen;
    private HomeScreen homeScreen;
    private EditScreen editScreen;
    private RemoveAccount removeAccount;
    private FindAccount searchAccount;
    private OpenAccount openAccount;
    private CreateScreen createScreen;
    private TransactHome transactHome;
    private SendMoney sendMoney;
    private DepositMoney depositMoney;
    private WithdrawMoney withdrawMoney;
    private DatabaseHandler dbHandler;

    private void syncDb() {
        frame.dispose();
        Index.main(new String[] { "Index" });
    }

    public Index() {
        dbHandler = new DatabaseHandler();
        homeScreen = new HomeScreen(new HomeScreen.HomeInterface() {
            @Override
            public void createAccount() {
                switchScreen(createScreen);
            }

            @Override
            public void removeAccount() {
                switchScreen(removeAccount);

            }

            @Override
            public void findAccount() {
                switchScreen(searchAccount);

            }

            @Override
            public void openAccount() {
                switchScreen(openAccount);

            }
        });
        editScreen = new EditScreen(new EditScreen.EditInterface() {
            @Override
            public void onClose() {
                switchScreen(searchAccount);

            }

            @Override
            public void onSave(Account account) {
                dbHandler.updateRecord(new Account[] { account });
                syncDb();

            }
        }, dbHandler.database);
        createScreen = new CreateScreen(new CreateScreen.EditInterFace() {
            @Override
            public void onClose() {
                switchScreen(homeScreen);

            }

            @Override
            public void onSave(Account account) {
                dbHandler.addRecord(account);
                syncDb();
            }
        }, dbHandler.database);
        removeAccount = new RemoveAccount(new RemoveAccount.RemoveInterface() {
            @Override
            public void onClose() {
                switchScreen(homeScreen);

            }

            @Override
            public void onRemove(Account account) {
                dbHandler.removeRecord(account);
                syncDb();

            }
        }, dbHandler.database);
        searchAccount = new FindAccount(new FindAccount.SearchAccountInterface() {
            @Override
            public void onClose() {
                switchScreen(homeScreen);
            }

            @Override
            public void onOpen(Account account) {
                editScreen.setAccount(account);
                switchScreen(editScreen);
            }
        }, dbHandler.database);
        openAccount = new OpenAccount(new OpenAccount.OpenAccountInterface() {
            @Override
            public void onClose() {
                switchScreen(homeScreen);
            }

            @Override
            public void onOpen(Account account) {
                transactHome.setCurrentAccount(account);
                switchScreen(transactHome);
            }
        }, dbHandler.database);
        transactHome = new TransactHome(new TransactHome.TransactInterface() {
            @Override
            public void onClose() {
                transactHome.currentAccount = null;
                switchScreen(openAccount);
            }

            @Override
            public void onDeposit() {
                depositMoney.setCurrentAccount(transactHome.currentAccount);
                switchScreen(depositMoney);
            }

            @Override
            public void onWithdraw() {
                withdrawMoney.setCurrentAccount(transactHome.currentAccount);
                switchScreen(withdrawMoney);
            }

            @Override
            public void onSend() {
                sendMoney.setCurrentAccount(transactHome.currentAccount);
                switchScreen(sendMoney);
            }
        });
        sendMoney = new SendMoney(new SendMoney.SendInterface() {
            @Override
            public void onProceed(Account ac1, Account ac2) {
                dbHandler.updateRecord(new Account[] { ac1, ac2 });
                syncDb();
            }

            @Override
            public void onClose() {
                switchScreen(transactHome);
            }
        }, dbHandler.database);
        withdrawMoney = new WithdrawMoney(new WithdrawMoney.WithdrawInterface() {
            @Override
            public void onClose() {
                switchScreen(transactHome);
            }

            @Override
            public void onWithdraw(Account current) {
                dbHandler.updateRecord(new Account[] { current });
                syncDb();
            }
        });
        depositMoney = new DepositMoney(new DepositMoney.DepositInterface() {
            @Override
            public void onClose() {
                switchScreen(transactHome);
            }

            @Override
            public void onDeposit(Account current) {
                dbHandler.updateRecord(new Account[] { current });
                syncDb();
            }
        });
        editScreen.setVisible(false);
        removeAccount.setVisible(false);
        searchAccount.setVisible(false);
        openAccount.setVisible(false);
        createScreen.setVisible(false);
        transactHome.setVisible(false);
        sendMoney.setVisible(false);
        depositMoney.setVisible(false);
        withdrawMoney.setVisible(false);
        initialize();
    }

    private void switchScreen(JPanel jPanel) {
        activeScreen.setVisible(false);
        jPanel.setVisible(true);
        activeScreen = jPanel;
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        frame.getContentPane().add(homeScreen);
        frame.getContentPane().add(editScreen);
        frame.getContentPane().add(removeAccount);
        frame.getContentPane().add(searchAccount);
        frame.getContentPane().add(openAccount);
        frame.getContentPane().add(createScreen);
        frame.getContentPane().add(transactHome);
        frame.getContentPane().add(sendMoney);
        frame.getContentPane().add(depositMoney);
        frame.getContentPane().add(withdrawMoney);
        activeScreen = homeScreen;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Index window = new Index();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
