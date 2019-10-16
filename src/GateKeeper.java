import java.text.NumberFormat;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

public class GateKeeper {

    public static void runApplication() {
        List<Person> customer = SystemUtility.fetchCustomerFromFile("src//db//customers.txt");
        SystemUtility.searchCustomerFromList(customer);
    }

    public static void searchCustomerActivity() {
        History p = new History();
        p.setName("Bear Belle");
        List<History> customerHistory = SystemUtility.fetchCustomerHistoryFromFile("src//db//" + p.getName() + ".txt");
        SystemUtility.viewCustomerActivity(customerHistory);
    }
}
