package edu.sharif.nosense.project1;

import java.util.ArrayList;

public class MessageController {
    private NotificationCenter notificationCenter;
    private StorageManager storageManager = new StorageManager();
    private ConnectionManager connectionManager = new ConnectionManager();
    private ArrayList<Integer> listOfNumbers = new ArrayList<>();

    public MessageController(NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
    }

    public void fetch(boolean fromCache) {
        if (fromCache) {
            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            storage.start();
        } else {
            Thread cloud = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> loadedList = connectionManager.load(listOfNumbers.get(listOfNumbers.size() - 1));
                }
            });
            cloud.start();
        }
    }
}
