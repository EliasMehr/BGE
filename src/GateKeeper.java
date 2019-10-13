import java.text.NumberFormat;
import java.util.Formatter;
import java.util.List;

public class GateKeeper {

    public static void runApplication() {
        List<Person> customer = SystemUtility.fetchCustomerFromFile("src//db//customers.txt");
        SystemUtility.searchCustomerFromList(customer);
    }

    public static void searchCustomerActivity() {
        List<History> customerHistory = SystemUtility.fetchCustomerHistoryFromFile("src//db//customerActivity.txt");
        SystemUtility.viewCustomerActivity(customerHistory);
    }
}
