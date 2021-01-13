package bdisi;

import bdisi.gui.Logger;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.*;


public class App {
    public static void main(String[] args) {
        Logger logger = new Logger();
        /*Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SpisPowszechny", "root", "pepet");
        CallableStatement cstmt = conn.prepareCall("{CALL displayYearStats(?, ?)}");
        cstmt.setInt("syear", 2010);
        cstmt.registerOutParameter("result", Types.INTEGER);
        cstmt.execute();
        cstmt.close();
        int result = cstmt.getInt("result");
        System.out.println("Dupa: " + result);*/

    }
}