package edu.sharif.nosense.project1;

import android.content.Context;

import java.util.ArrayList;

public class MessageController {
    private NotificationCenter notificationCenter;
    private StorageManager storageManager;
    private ConnectionManager connectionManager = new ConnectionManager();
    private ArrayList<Integer> listOfNumbers = new ArrayList<>();
    private static MessageController messageController;
    private DispatchQueue cloud = new DispatchQueue("cloud");
    private DispatchQueue storage = new DispatchQueue("storage");

    public static MessageController getInstance(NotificationCenter notificationCenter, Context context){
        if(messageController == null)
            messageController = new MessageController(notificationCenter, context);
        return messageController;

    }

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
                    ArrayList<Integer> loadedList = storageManager.load();
                    updateList(loadedList);
                }
            });
        } else {
            cloud.postRunnable(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> loadedList = connectionManager.load(0);
                    updateList(loadedList);
                }
            });
        }
    }

    private synchronized void updateList(ArrayList<Integer> toAppendList) {
        listOfNumbers.addAll(toAppendList);
        notificationCenter.dataLoaded();
    }

    public void refresh(){
        int lastNum = storageManager.read();
        int lastNumInList;
        if(listOfNumbers.size() != 0)
            lastNumInList = listOfNumbers.get(listOfNumbers.size()-1);
        else
            lastNumInList = 0;
        if(lastNumInList >= lastNum || lastNum == -1)
            return;
        ArrayList<Integer> toAppendList = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            toAppendList.add(i+lastNumInList);
        updateList(toAppendList);
    }
}
