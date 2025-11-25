package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class EmploymentAgency implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(JobPost job) {
        for (Observer observer : observers) {
            observer.onJobPosted(job);
        }
    }

    public void addJob(JobPost jobPosting) {
        notifyObservers(jobPosting);
    }
}
