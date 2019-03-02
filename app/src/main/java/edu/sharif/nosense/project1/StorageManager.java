package edu.sharif.nosense.project1;

import java.util.ArrayList;

public class StorageManager {

    public ArrayList<Integer> load(){
        int numberInTheFile = 0;
        //TODO Read From File
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(numberInTheFile + i);
        }
        return list;
    }

    public void save(){

    }
}
