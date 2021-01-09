package bdisi;

import javax.persistence.*;

@Entity
@Table(name = "addresses", schema = "spispowszechny", catalog = "")
public class AddressesEntity {
    private String pesel;
    private String street;
    private int house;
    private Integer flat;

    @Id
    @Column(name = "pesel")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "house")
    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Basic
    @Column(name = "flat")
    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressesEntity that = (AddressesEntity) o;

        if (house != that.house) return false;
        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + house;
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        return result;
    }
}
