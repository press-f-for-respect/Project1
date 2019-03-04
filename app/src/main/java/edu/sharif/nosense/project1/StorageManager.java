package edu.sharif.nosense.project1;

import java.util.ArrayList;

public class StorageManager {

    public ArrayList<Integer> load(){
        ArrayList<Integer> list = new ArrayList<>();
        int numberInTheFile = 0;
        //TODO Read From File
        for (int i = 1; i <= 10; i++) {
            list.add(numberInTheFile + i);
        }
        return list;
    }

    public void save(){

    }
}
