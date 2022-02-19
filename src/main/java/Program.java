import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        SqliteHelper sqliteHelper = new SqliteHelper();
        //ArrayList<Customer> customers = sqliteHelper.selectAllCustomers();
        //ArrayList<Customer> customers = sqliteHelper.selectSpecificCustomersName("Mark");
        ArrayList<Customer> customers = sqliteHelper.selectSubsetOfCustomers(12,4);
        //Customer customer = sqliteHelper.selectSpecificCustomerID("55");
        printCustomers(customers);
        //printCustomer(customer);
    }


    public static void printCustomers(ArrayList<Customer> customers) {
        if(customers.size() != 0) {
            for (Customer c : customers) {
                System.out.println("-------------------------------");
                System.out.println(c.getCustomerId());
                System.out.println(c.getCustomerFirstname());
                System.out.println(c.getCustomerLastname());
                System.out.println(c.getCustomerCountry());
                System.out.println(c.getCustomerPostalCode());
                System.out.println(c.getCustomerPhoneNumber());
                System.out.println(c.getCustomerEmail());
            }
        } else {
            System.out.println("No customers returned");
        }
    }
    public static void printCustomer(Customer customer) {
        if(customer != null){
            System.out.println("-------------------------------");
            System.out.println(customer.getCustomerId());
            System.out.println(customer.getCustomerFirstname());
            System.out.println(customer.getCustomerLastname());
            System.out.println(customer.getCustomerCountry());
            System.out.println(customer.getCustomerPostalCode());
            System.out.println(customer.getCustomerPhoneNumber());
            System.out.println(customer.getCustomerEmail());
        } else {
            System.out.println("No customer with that ID exists");
        }

    }
}