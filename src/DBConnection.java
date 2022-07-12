import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    public static void main(String[] args) {

        Connection connection = null;
        ArrayList<Student> italians = new ArrayList<>();
        ArrayList<Student> germans = new ArrayList<>();

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb","developer","16014481alpha");


            Statement statement = connection.createStatement();
            statement.execute("CREATE VIEW Italian_students AS " +
                "SELECT firstname, lastname " +
                "FROM students " +
                "WHERE country = 'Italy';");

            statement.execute("CREATE VIEW German_students AS " +
                "SELECT firstname, lastname " +
                "FROM students " +
                "WHERE country = 'Germany';");

            String ItalianStudentsView = "SELECT * from Italian_students;";
            String GermanStudentsView = "SELECT * from German_students;";
            ResultSet italianRes = statement.executeQuery(ItalianStudentsView);

            while(italianRes.next()) {
                italians.add(new Student(italianRes.getString("firstname"), italianRes.getString("lastname")));
            }
            ResultSet germanRes = statement.executeQuery(GermanStudentsView);

            while(germanRes.next()) {
                germans.add(new Student(germanRes.getString("firstname"), germanRes.getString("lastname")));
            }

            italians.forEach((italian) -> {
                PrintStream printItalians = System.out;
                String italianName = italian.getName();
                printItalians.println(" 'Italian Student' - Name: " + italianName + ", Surname: " + italian.getSurname());
            });
            germans.forEach((german) -> {
                PrintStream printGermans = System.out;
                String germanName = german.getName();
                printGermans.println(" 'German Student' - Name: " + germanName + ", Surname: " + german.getSurname());
            });


        /**ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

         while (resultSet.next()){
            int i = 1;
            while(i <= columnCount){
            System.out.println(resultSet.getString(i++));}
            if (resultSet.getString("country").equals("Italy")){
                italians.add(resultSet.getString("firstname"));
            }else {germans.add(resultSet.getString("firstname"));}
         }**/

        }catch (SQLException SQLex){
            SQLex.printStackTrace();
        }finally {
            try{
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException SQLex1) {
            SQLex1.printStackTrace();
        }
        }
    }
}
