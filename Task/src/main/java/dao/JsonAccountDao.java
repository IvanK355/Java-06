package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/** Реализация CRUD для JSON хранилища*/
public class JsonAccountDao implements Dao<Account> {

    private static final String filePath = "./Task/src/main/resources/accounts.json";
    private static final Type itemsMapType = new TypeToken<HashMap<Integer, Account>>() {
    }.getType();
    private ArrayList<Account> accounts = new ArrayList<>();

    @Override
    public void createNewTable() throws IOException {

        Gson gson = new Gson();
        FileWriter fw = new FileWriter(filePath);
        HashMap<Integer, Account> mapItems = new HashMap<>();
        for (int i = 0; i < 11; i++) {
            accounts.add(new Account(i, "Holder" + i, 500));
            mapItems.put(i, accounts.get(i));
        }
        gson.toJson(mapItems, fw);
        fw.close();
    }

    public Account create(Account account) throws IOException {

        HashMap<Long, Account> balanceHashMap = readJson();
        balanceHashMap.put(account.getId(), account);
        writeJson(balanceHashMap);
        return read(account.getId());
    }

    @Override
    public Account read(long id) throws IOException, NullPointerException {

        HashMap<Long, Account> balanceHashMap = readJson();
        String holder = balanceHashMap.get(id).getName();
        int amount = balanceHashMap.get(id).getAccountAmount();
        return new Account(id, holder, amount);

    }

    @Override
    public Account update(Account account) throws IOException {
        HashMap<Long, Account> balanceHashMap = readJson();
        balanceHashMap.put(account.getId(), account);
        writeJson(balanceHashMap);
        return account;
    }

    @Override
    public Account delete(Account account) throws IOException {
        HashMap<Long, Account> balanceHashMap = readJson();
        balanceHashMap.put(account.getId(), null);
        writeJson(balanceHashMap);
        return null;
    }

    private void writeJson(HashMap<Long, Account> mapItems) throws IOException {

        Gson gson = new Gson();
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(mapItems, fw);
        fw.close();
    }

    private HashMap<Long, Account> readJson() throws IOException {

        Gson gson = new Gson();
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fr);
        HashMap<Long, Account> mapItemsDes = gson.fromJson(bufferedReader, itemsMapType);
        fr.close();
        return mapItemsDes;
    }
}
