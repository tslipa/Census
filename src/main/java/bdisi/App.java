package bdisi;

import bdisi.gui.Logger;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.*;


public class App {
    public static void main(String[] args) {

            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SpisPowszechny", "root", "pepet");
            /*PreparedStatement stmt = conn.prepareStatement("CALL displayYearStats(?, ?)");
            stmt.setInt(1, 2000);
            int d = 0;
            stmt.setInt(2, d);
            stmt.executeUpdate();*/

            /*Statement stmt = conn.createStatement();
            String query = "CALL displayYearStats(2000)";
            System.out.println("Połączyłem się, próbuję wykonać query.");
            ResultSet res = stmt.executeQuery(query);
            System.out.println("Wykonałem query");
            while(res.next()) {
                int quantity = res.getInt("quantity");
                System.out.println("Dupa" + quantity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Session session = HibernateUtil.getSessionFactory().openSession(); */

        Logger logger = new Logger();
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdisi");
        EntityManager em = emf.createEntityManager();
        StoredProcedureQuery query = em.createStoredProcedureQuery("displayYearStats");
        query.registerStoredProcedureParameter("syear", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
        query.setParameter("syear", 2000);
        query.execute();

        Integer result = (Integer) query.getOutputParameterValue("result");
        System.out.println(result);

        session.close();

        System.out.println( "Hello World!" );*/
    }
}