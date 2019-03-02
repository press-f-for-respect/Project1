package edu.sharif.nosense.project1;

import java.util.ArrayList;

public class ConnectionManager {
    public ArrayList<Integer> load(int start){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(start + i);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}
