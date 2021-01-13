package bdisi.entities;

import javax.persistence.*;

@Entity
@Table(name = "passwords", schema = "spispowszechny", catalog = "")
public class PasswordsEntity {
    private String pesel;
    private String password;

    @Id
    @Column(name = "pesel")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordsEntity that = (PasswordsEntity) o;

        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
