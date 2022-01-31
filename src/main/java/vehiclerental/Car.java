package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Car implements Rentable {

    private String id;
    private LocalTime rentingTime;
    private int pricePerMinutes;

    public Car(String id, int pricePerMinutes) {
        this.id = id;
        this.pricePerMinutes = pricePerMinutes;
    }


    @Override
    public int calculateSumPrice(long minutes) {
        return (int) pricePerMinutes * (int) minutes;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
