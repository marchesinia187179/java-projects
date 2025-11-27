package org.example.observer.weather;

public class WeatherObserverMain {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Observer mickeyMouse = new Subscriber("Michael (Mickey) Theodore Mouse");
        Observer donaldDuck = new Subscriber("Donald Fauntleroy Duck");

        weatherStation.attach(mickeyMouse);
        weatherStation.attach(donaldDuck);

        Sensor temperature = new TemperatureSensor();
        Sensor humidity = new HumiditySensor();

        weatherStation.updateMeasure(temperature, 50);

        weatherStation.detach(donaldDuck);
        weatherStation.updateMeasure(humidity, 10);
    }
}
