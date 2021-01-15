package bdisi.gui;

import java.sql.*;
import java.util.Random;

public class RandomAdder {
    private static Connection connection;

    private static void setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Census?user=root&password=pepet&noAccessToProcedureBodies=true");
            System.out.println("Connected successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static Random generator = new Random();;
    private static String[] maleNames = {"Jacek", "Placek", "Marek", "Adam", "Dominik", "Krzysztof", "Mateusz", "Marcin", "Sebastian","Jonatan", "Wiktor", "Janusz", "Jan"};
    private static String[] femaleNames = {"Ada", "Magda", "Magdalena", "Maria", "Monika", "Marika", "Manuela", "Anna", "Michalina", "Ela", "Ola", "Aleksandra", "Hanna"};
    private static String[] surnames = {"Kot", "Pies", "Niezgoda", "Macyna", "Bojko", "Zawada", "Syga", "Cichoń", "Kuchta", "Kukos"};
    private static String[] streets = {"Warszawska", "Prusa", "Mickiewicza", "Sienkiewicza", "Cichonia", "Kononowicza", "Suchodolskiego", "Klocucha"};
    private static String[] cities = {"Wrocław", "Warszawa", "Łódź", "Kielce", "Rzeszów", "Kraków", "Gdańsk", "Toruń", "Poznań", "Gdynia", "Sopot"};

    private static String randomPesel() {
        Integer year = generator.nextInt(100);
        String yearStr = year.toString();
        if (year < 10) {
            yearStr = "0" + yearStr;
        }

        Integer month = generator.nextInt(12) + 1;
        if (year < 21 && generator.nextBoolean()) {
            month += 20;
        }
        String monthStr = month.toString();
        if (month < 10) {
            monthStr = "0" + monthStr;
        }

        Integer day = generator.nextInt(28) + 1;
        String dayStr = day.toString();
        if (day < 10) {
            dayStr = "0" + dayStr;
        }

        String result = yearStr + monthStr + dayStr;

        for (int i = 0; i < 5; i++) {
            Integer next = generator.nextInt(10);
            result += next.toString();
        }

        return result;
    }

    private static String randomPassword() {
        Integer password = generator.nextInt(9000000) + 1000000;
        return password.toString();
    }

    private static String randomName(String pesel) {
        int isMale = Integer.parseInt(pesel.substring(10)) % 2;
        if (isMale == 1) {
            return maleNames[generator.nextInt(maleNames.length)];
        } else {
          return femaleNames[generator.nextInt(femaleNames.length)];
        }
    }

    private static String randomSurname() {
        return surnames[generator.nextInt(surnames.length)];
    }

    private static String randomCity() {
        return cities[generator.nextInt(cities.length)];
    }

    private static String randomStreet() {
        return streets[generator.nextInt(streets.length)];
    }

    private static int addRandom() {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL addCitizen(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            String pesel = randomPesel();
            cstmt.setString(1, pesel);
            cstmt.setString(2, randomPassword());
            Double isBureaucrat = generator.nextDouble();
            String status = "Citizen";
            if (isBureaucrat < 0.05) {
                status = "Bureaucrat";
            }
            cstmt.setString(3, status);
            cstmt.setString(4, randomName(pesel));
            cstmt.setString(5, randomSurname());
            cstmt.setString(6, randomCity());
            cstmt.setString(7, randomStreet());
            cstmt.setInt(8, generator.nextInt(100) + 1);
            cstmt.setInt(9, generator.nextInt(10) + 1);
            cstmt.registerOutParameter(10, Types.INTEGER);

            cstmt.execute();
            int result = cstmt.getInt(10);
            cstmt.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        setConnection();
        for (int i = 0; i < 100; i++) {
            if (addRandom() == 1) {
                System.out.println("Added successfully.");
            } else {
                System.out.println("Got some problems.");
            }
        }
    }
}
