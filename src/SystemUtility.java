import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SystemUtility {

    public static final String TITLE_SEARCH = "BESTGYMEVER - Sök kund";
    public static final String TITLE_CHECK_IN = "BESTGYMEVER - Instämpling";

    public static List<Person> fetchCustomerFromFile(String Path) {
        List<Person> customerData = new LinkedList<>();

        try (Scanner sc = new Scanner(new File(Path))) {
            while (sc.hasNext()) {
                Person customer = new Person();
                String readCustomerFromFilePath = sc.nextLine();

                customer.setPersonID(readCustomerFromFilePath.substring(0, readCustomerFromFilePath.indexOf(',')));
                customer.setName(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(',') + 2));

                readCustomerFromFilePath = sc.nextLine();

                customer.setMembershipPaymentDate(LocalDate.parse(readCustomerFromFilePath, DateTimeFormatter.ISO_LOCAL_DATE));
                customer.setMembershipActive(customer.verifyMembership());
                customerData.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customerData;
    }

    public static void searchCustomerFromList(List<Person> customerList) {
        while (true) {
            boolean doesCustomerExist = false;

            String searchInput = JOptionPane.showInputDialog(null, "Sök kund..", TITLE_SEARCH, JOptionPane.INFORMATION_MESSAGE);
            if (searchInput == null) {
                JOptionPane.showMessageDialog(null, "Avslutar programmet...");
                System.exit(0);
            } else if (searchInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ogiltig inmatning!");
                continue;
            }

            for (Person customer : customerList)
                if (searchInput.equalsIgnoreCase(customer.getName()) || searchInput.equals(customer.getPersonID())) {
                    JOptionPane.showMessageDialog(null, customer.toString());
                    doesCustomerExist = true;
                    validateCustomerBeforeWriteToFile(customer);
                }
            if (!doesCustomerExist) {
                JOptionPane.showMessageDialog(null, searchInput + " är inte medlemm hos oss!");
            }
        }
    }

    public static boolean validateCustomerBeforeWriteToFile(Person person) {
        boolean validateInData = false;

        if (person.getIsMembershipActive()) {
            validateInData = true;

            int confirmAlternative = JOptionPane.showConfirmDialog(null, "Vill du träna?", TITLE_CHECK_IN, JOptionPane.YES_NO_OPTION);
            if (confirmAlternative == JOptionPane.YES_OPTION) {
                writeCustomerToFile(person);
                JOptionPane.showMessageDialog(null, person.getName() + ", du är nu instämplad");
            } else
                return validateInData = false;
        }
        return validateInData;
    }

    public static void writeCustomerToFile(Person saveObject) {
        try (PrintWriter writeToFile = new PrintWriter(new BufferedWriter(new FileWriter("src//db//customerActivity.txt", true)))) {

            //String timeStampForCustomer = "\nSenaste aktivitet: " + LocalDate.now() + "  " + LocalTime.now().withNano(0);
            String timeStampForCustomer = "\nSenaste aktivitet: " + LocalDateTime.now().withNano(0);
            writeToFile.write(String.valueOf(saveObject));
            writeToFile.write(timeStampForCustomer.replace('T', ' ') + "\n");
            writeToFile.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kan inte spara användare");
        }
    }
}