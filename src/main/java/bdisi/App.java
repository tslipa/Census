package bdisi;

import bdisi.gui.Logger;
import org.hibernate.Session;

import javax.persistence.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Logger logger = new Logger();

        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdisi");
        EntityManager em = emf.createEntityManager();
        StoredProcedureQuery query = em.createStoredProcedureQuery("displayYearStats");
        query.registerStoredProcedureParameter("syear", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
        query.setParameter("syear", 2000);
        query.execute();

        Integer result = (Integer) query.getOutputParameterValue("result");
        System.out.println(result);*/

        session.close();

        System.out.println( "Hello World!" );
    }
}
