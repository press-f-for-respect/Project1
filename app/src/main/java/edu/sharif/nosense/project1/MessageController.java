package edu.sharif.nosense.project1;

import android.content.Context;

import java.util.ArrayList;

public class MessageController {
    private NotificationCenter notificationCenter;
    private StorageManager storageManager;
    private ConnectionManager connectionManager = new ConnectionManager();
    private ArrayList<Integer> listOfNumbers = new ArrayList<>();
    private DispatchQueue cloud = new DispatchQueue("cloud");
    private DispatchQueue storage = new DispatchQueue("storage");

    public MessageController(NotificationCenter notificationCenter, Context context) {
        this.notificationCenter = notificationCenter;
        this.storageManager = new StorageManager(context);
    }

    public ArrayList<Integer> getListOfNumbers() {
        return listOfNumbers;
    }

    public void fetch(boolean fromCache) {
        if (fromCache) {
            storage.postRunnable(new Runnable() {
                @Override
                public void run() {

                }
            });
            storage.start();
        } else {
            cloud.postRunnable(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> loadedList = connectionManager.load(0);
                    updateList(loadedList);
                }
            }, 5000);
        }
    }

    private synchronized void updateList(ArrayList<Integer> toAppendList) {
        listOfNumbers.addAll(toAppendList);
        notificationCenter.dataLoaded();
    }
}
