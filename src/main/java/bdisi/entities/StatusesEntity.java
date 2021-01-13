package bdisi.entities;

import javax.persistence.*;

@Entity
@Table(name = "statuses", schema = "spispowszechny", catalog = "")
public class StatusesEntity {
    private String pesel;
    private String status;

    @Id
    @Column(name = "pesel")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusesEntity that = (StatusesEntity) o;

        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
