import java.util.List;

public class GateKeeper {

    public static void runApplication() {
        List<Person> customer = SystemUtility.fetchCustomerFromFile("src//db//customers.txt");
        SystemUtility.searchCustomerFromList(customer);
    }
}
