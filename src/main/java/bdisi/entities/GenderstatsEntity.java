package bdisi.entities;

import javax.persistence.*;

@Entity
@Table(name = "genderstats", schema = "spispowszechny", catalog = "")
public class GenderstatsEntity {
    private String gender;
    private int quantity;

    @Id
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

        GenderstatsEntity that = (GenderstatsEntity) o;

        if (quantity != that.quantity) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gender != null ? gender.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
