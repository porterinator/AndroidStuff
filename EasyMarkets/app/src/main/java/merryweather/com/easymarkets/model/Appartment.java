package merryweather.com.easymarkets.model;

import java.util.Date;
import java.util.Objects;

public class Appartment {

    public int numberOfBedrooms;

    public Date availableStart;
    public Date availableEnd;

    public Appartment(int numberOfBedrooms, Date availableStart, Date availableEnd) {
        this.numberOfBedrooms = numberOfBedrooms;
        this.availableStart = availableStart;
        this.availableEnd = availableEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appartment that = (Appartment) o;
        return numberOfBedrooms == that.numberOfBedrooms &&
                Objects.equals(availableStart, that.availableStart) &&
                Objects.equals(availableEnd, that.availableEnd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberOfBedrooms, availableStart, availableEnd);
    }
}
