package org.example.observer.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Subscriber implements Observer {
    private String name;

    @Override
    public void update(Sensor sensor) {
        System.out.println(name
                + " the " + sensor.getName()
                + " sensor measured " + sensor.getMeasure() + sensor.getUnit());
    }
}
