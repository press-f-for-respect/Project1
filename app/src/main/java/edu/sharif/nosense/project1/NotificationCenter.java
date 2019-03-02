package edu.sharif.nosense.project1;

import java.util.ArrayList;
import java.util.List;

public class NotificationCenter {
    private List<Observer> observers = new ArrayList<>();
    private boolean dataLoaded = false;

    public void register(Observer observer){
        observers.add(observer);
    }

    public void unRegister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
