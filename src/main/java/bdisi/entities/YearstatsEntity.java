package bdisi.entities;

import javax.persistence.*;

@Entity
@Table(name = "yearstats", schema = "spispowszechny", catalog = "")
public class YearstatsEntity {
    private int year;
    private int quantity;

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YearstatsEntity that = (YearstatsEntity) o;

        if (year != that.year) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + quantity;
        return result;
    }
}
