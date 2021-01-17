package bdisi;

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

    private static final Random generator = new Random();;
    private static final String[] maleNames = {"Jacek", "Placek", "Marek", "Adam", "Dominik", "Krzysztof", "Mateusz", "Marcin", "Sebastian", "Jonatan", "Wiktor", "Janusz",
            "Jan", "Grzegorz", "Jerzy", "Kazimierz", "Zbigniew", "Aegon", "Jeremi", "Dobromir", "Mieszko", "Ziemowit"};
    private static final String[] femaleNames = {"Ada", "Magda", "Magdalena", "Maria", "Monika", "Marika", "Manuela", "Anna", "Michalina", "Ela", "Ola", "Aleksandra",
            "Hanna", "Barbara", "Kamila", "Celina", "Helena", "Zenobia", "Daenerys", "Anastazja", "Karolina", "Jadwiga"};
    private static final String[] surnames = {"Kot", "Pies", "Niezgoda", "Macyna", "Bojko", "Zawada", "Syga", "Cichoń", "Kuchta", "Kukos", "Mulak", "Majcher",
            "Bujkiewicz", "Kapelko", "Lauks", "Dutka", "Błaśkiewicz", "Kozieł", "Żebrowicz", "Kutyłowicz", "Kobylenko", "Krupienko", "Łaźniak", "Guz",
            "Urban", "Malta", "Siegieńczuk", "Drabik"};
    private static final String[] streets = {"Warszawska", "Prusa", "Mickiewicza", "Sienkiewicza", "Cichonia", "Kononowicza", "Suchodolskiego", "Klocucha",
            "Główna", "Wiejska", "Aleja Politechniki", "Rynek", "Wrocławska", "Lipna", "Bazodanowa", "Procesorska"};
    private static final String[] cities = {"Wrocław", "Warszawa", "Łódź", "Kielce", "Rzeszów", "Kraków", "Gdańsk", "Toruń", "Poznań", "Gdynia", "Sopot",
            "Opole", "Zielona Góra", "Gorzów Wielkopolski", "Katowice", "Sosnowiec", "Radom", "Bydgoszcz", "Suwałki", "Bolec", "Legnica", "Terespol",
            "Wałbrzych", "Solec Kujawski", "Kozia Wólka"};

    private static String randomPesel() {
        int year = generator.nextInt(100);
        String yearStr = Integer.toString(year);
        if (year < 10) {
            yearStr = "0" + yearStr;
        }

        int month = generator.nextInt(12) + 1;
        if (year < 21 && generator.nextBoolean()) {
            month += 20;
        }
        String monthStr = Integer.toString(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }

        int day = generator.nextInt(28) + 1;
        String dayStr = Integer.toString(day);
        if (day < 10) {
            dayStr = "0" + dayStr;
        }

        StringBuilder result = new StringBuilder(yearStr + monthStr + dayStr);

        for (int i = 0; i < 5; i++) {
            int next = generator.nextInt(10);
            result.append(next);
        }

        return result.toString();
    }

    private static String randomPassword() {
        int password = generator.nextInt(9000000) + 1000000;
        return Integer.toString(password);
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
            double isBureaucrat = generator.nextDouble();
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
        for (int i = 0; i < 1000; i++) {
            if (addRandom() == 1) {
                System.out.println("Added successfully.");
            } else {
                System.out.println("Got some problems.");
            }
        }
    }
}
