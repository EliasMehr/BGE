import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatCodePointException;
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



    public static List<History> fetchCustomerHistoryFromFile(String Path) {
        List<History> customerData = new LinkedList<>();

        try (Scanner sc = new Scanner(new FileReader(Path))) {
            while (sc.hasNextLine()) {

                History acitivityLogg = new History();
                String readCustomerFromFilePath = sc.nextLine();
                acitivityLogg.setName(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(':') + 2));

                readCustomerFromFilePath = sc.nextLine();
                acitivityLogg.setPersonID(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(':') + 2));

                readCustomerFromFilePath = sc.nextLine();
                acitivityLogg.setPaymentOfMembership(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(':') + 2));

                readCustomerFromFilePath = sc.nextLine();
                acitivityLogg.setMemberStatus(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(':') + 2));

                readCustomerFromFilePath = sc.nextLine();
                acitivityLogg.setLatestActivity(readCustomerFromFilePath.substring(readCustomerFromFilePath.indexOf(':') + 2));

                customerData.add(acitivityLogg);

                sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customerData;
    }

    public static String searchCustomerFromList(List<Person> customerList) {
        while (true) {
            boolean doesCustomerExist = false;

            String searchInput = JOptionPane.showInputDialog(null, "Sök kund..", TITLE_SEARCH, JOptionPane.INFORMATION_MESSAGE);
            if (searchInput == null) {
                Menu.menuSelectionAlternative();
            } else if (searchInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ogiltig inmatning!");
                continue;
            }

            for (Person customer : customerList)
                if (searchInput.equalsIgnoreCase(customer.getName()) || searchInput.equals(customer.getPersonID())) {
                    JOptionPane.showMessageDialog(null, customer.toString());
                    doesCustomerExist = true;
                    validateCustomerBeforeWriteToFile(customer);
                    Menu.menuSelectionAlternative();
                    return customer.getName();
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
            } else {

            }
        }
        return validateInData;
    }

    public static void writeCustomerToFile(Person saveObject) {
        try (PrintWriter writeToFile = new PrintWriter(new BufferedWriter(new FileWriter("src//db//" + saveObject.getName() + ".txt", true)))) {

            String timeStampForCustomer = "\nSenaste aktivitet: " + LocalDateTime.now().withNano(0);
            writeToFile.write(String.valueOf(saveObject));
            writeToFile.write(timeStampForCustomer.replace('T', ' ') + "\n");
            writeToFile.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kan inte spara användare");
        }
    }

    public static void viewCustomerActivity(List<History> customerHistory) {
        Object[] customers = customerHistory.toArray();
        Object menuInput = JOptionPane.showInputDialog(null, "Vänligen välj kund att följa upp", null, JOptionPane.INFORMATION_MESSAGE, null, customers, "null" );

        for (int i = 0; i < customerHistory.size(); i++) {
            if (menuInput == customers[i])
                JOptionPane.showMessageDialog(null, customerHistory.get(i));
        }
        Menu.menuSelectionAlternative();
    }
}