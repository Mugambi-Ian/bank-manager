package bank.manager.data.util;

import bank.manager.data.models.Account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {
    public Map<String, Account> database;

    public DatabaseHandler() {
        initApp();
    }

    private void initApp() {
        database = new HashMap<>();
        File f = new File(Constants.DB_PATH);
        if (f.exists() && !f.isDirectory()) {
            loadDb();
        } else {
            f.getParentFile().mkdirs();
            writeDb();
        }
    }

    private void loadDb() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Constants.DB_PATH));
            String line = reader.readLine();
            int id = 0;
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    String d[] = line.split("<#>");
                    Account account = new Account();
                    account.init(d[0], d[1], d[2], d[3], d[4], d[5], Integer.parseInt(d[6]), d[7]);
                    database.put(d[0], account);
                    id = id + 1;
                }
            }
            reader.close();
            database = sortbykey();
            sortbykey();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecord(Account ac) {
        ac.userId = "c-" + database.size() + '_';
        database.put(ac.userId, ac);
        writeDb();
    }

    public void removeRecord(Account ac) {
        database.remove(ac.userId);
        writeDb();
    }

    public void updateRecord(Account[] ac) {
        for (Account ac1 : ac) {
            database.put(ac1.userId, ac1);
        }
        writeDb();
    }

    private ArrayList<String> getDb() {
        ArrayList<String> list = new ArrayList();
        database.keySet().forEach(k -> {
            Account x = database.get(k);
            list.add(x.userId + x.toString());
        });
        Collections.sort(list, (String o1, String o2) -> o2.compareTo(o1));
        return list;
    }

    private Integer id(String c) {
        c = c.replace("c-", "");
        c = c.replace("_", "");
        return Integer.parseInt(c);
    }

    public Map<String, Account> sortbykey() {
        ArrayList<Integer> list = new ArrayList();
        database.keySet().forEach(k -> {
            Account x = database.get(k);
            list.add(id(x.userId));

        });
        Collections.sort(list, (Integer o1, Integer o2) -> o2.compareTo(o1));
        Map<String, Account> acs = new TreeMap<>();
        list.forEach(i -> {
            acs.put("c-" + i + "_", database.get("c-" + i + "_"));
        });
        return acs;
    }

    private void writeDb() {
        try (PrintWriter writer = new PrintWriter(Constants.DB_PATH, "UTF-8")) {
            writer.println(Constants.DB_HEADER);
            for (String k_ : getDb()) {
                writer.println(k_);
            }
            writer.close();
        } catch (UnsupportedEncodingException | FileNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
