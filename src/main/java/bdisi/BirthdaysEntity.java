package bdisi;

import javax.persistence.*;

@Entity
@Table(name = "birthdays", schema = "spispowszechny", catalog = "")
public class BirthdaysEntity {
    private String pesel;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;

    @Id
    @Column(name = "pesel")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "dayOfBirth")
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Basic
    @Column(name = "monthOfBirth")
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    @Basic
    @Column(name = "yearOfBirth")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BirthdaysEntity that = (BirthdaysEntity) o;

        if (dayOfBirth != that.dayOfBirth) return false;
        if (monthOfBirth != that.monthOfBirth) return false;
        if (yearOfBirth != that.yearOfBirth) return false;
        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pesel != null ? pesel.hashCode() : 0;
        result = 31 * result + dayOfBirth;
        result = 31 * result + monthOfBirth;
        result = 31 * result + yearOfBirth;
        return result;
    }
}
