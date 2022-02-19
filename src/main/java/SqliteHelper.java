import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqliteHelper {
    // Setup
    static final String URL = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";
    Connection conn = null;


    //---reading all customers from database
    public ArrayList<Customer> selectAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---Read a specific customer based on ID
    public Customer selectSpecificCustomerID(String customerId){
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email " +
                            "FROM Customer WHERE CustomerId = ?");
            preparedStatement.setString(1, customerId); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    //---Read a specific customer based on name
    public ArrayList<Customer>  selectSpecificCustomersName(String customerFirstName){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email " +
                            "FROM Customer WHERE FirstName = ?");
            preparedStatement.setString(1, customerFirstName); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                        ));
            }

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---Choose a subset of customers
    public ArrayList<Customer> selectSubsetOfCustomers(int offset,int limit){
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE CustomerId >= ? LIMIT ? ");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---reading all user invoice from database
    public ArrayList<CustomerInvoice> selectAllInvoiceByID(String id){
        ArrayList<CustomerInvoice> customerInvoices = new ArrayList<CustomerInvoice>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT InvoiceId,CustomerId FROM Invoice WHERE CustomerId = ?");

            preparedStatement.setString(1, id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customerInvoices.add(
                        new CustomerInvoice(
                                resultSet.getString("InvoiceId"),
                                resultSet.getString("CustomerId")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customerInvoices;
        }
    }

    //---reading invoicelines from database for userinvoice
    public ArrayList<InvoiceLine> selectUserInvoiceLines(String id) {
        ArrayList<InvoiceLine> UserInvoiceLines = new ArrayList<InvoiceLine>();

            try {
                // Open Connection
                conn = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");

                // Prepare Statement
                PreparedStatement preparedStatement =
                        conn.prepareStatement("SELECT InvoiceId,TrackId FROM InvoiceLine WHERE InvoiceId = ?");
                preparedStatement.setString(1, id);
                // Execute Statement
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process Results
                while (resultSet.next()) {
                    UserInvoiceLines.add(
                            new InvoiceLine(
                                    resultSet.getString("InvoiceId"),
                                    resultSet.getString("TrackId")
                            ));
                }
            } catch (Exception ex) {
                System.out.println("Something went wrong...");
                System.out.println(ex.toString());
            } finally {
                try {
                    // Close Connection
                    conn.close();
                } catch (Exception ex) {
                    System.out.println("Something went wrong while closing connection.");
                    System.out.println(ex.toString());
                }
            }

        return UserInvoiceLines;
    }
    //---reading all tracks from database
    public ArrayList<Track> selectTracks(String trackID){
        ArrayList<Track> Tracks = new ArrayList<Track>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,TrackId FROM Track WHERE TrackId=?");

            preparedStatement.setString(1, trackID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                Tracks.add(
                        new Track(
                                resultSet.getString("GenreId"),
                                resultSet.getString("TrackId")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return Tracks;
        }
    }

    //---reading all genre from database
    public Genre selectFavGenre(String genreID){
        Genre genre = null;

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre WHERE GenreId = ?");
            preparedStatement.setString(1, genreID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                genre = new Genre(
                                resultSet.getString("GenreId"),
                                resultSet.getString("Name")
                        );
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return genre;
        }
    }
}
