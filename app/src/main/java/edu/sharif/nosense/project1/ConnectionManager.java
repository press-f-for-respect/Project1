package edu.sharif.nosense.project1;

import android.util.Log;

import java.util.ArrayList;

public class ConnectionManager {
    public ArrayList<Integer> load(int start){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(start + i);
        }
        Log.i("check thread", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}
