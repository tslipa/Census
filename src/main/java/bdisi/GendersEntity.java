package bdisi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genders", schema = "spispowszechny", catalog = "")
public class GendersEntity {
    private String pesel;

    @Id
    @Column(name = "pesel")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GendersEntity that = (GendersEntity) o;

        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pesel != null ? pesel.hashCode() : 0;
    }
}
