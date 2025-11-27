package org.example.observer.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Observable {
    private final List<Observer> subscribers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers(Sensor sensor) {
        for (Observer subscriber : subscribers) {
            subscriber.update(sensor);
        }
    }

    public void updateMeasure(Sensor sensor, int measure) {
        sensor.setMeasure(measure);
        notifyObservers(sensor);
    }
}
