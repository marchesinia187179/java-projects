package org.example.observer.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Sensor {
    private String name;
    private Integer measure;
    private String unit;

    public Sensor() {
        this.measure = 0;
    }
}
