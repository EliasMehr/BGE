import com.sun.source.tree.NewArrayTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilityTest {

    Person person = new Person();
    Person person2 = new Person();

    @Test
    void fetchCustomerFromFile() {
        person.setName("Bear Belle");
        person.setPersonID("8104021234");
        person.setMembershipPaymentDate(LocalDate.of(2018, 12, 02));
        List<Person> personList = SystemUtility.fetchCustomerFromFile("test//customers.txt");

        assertEquals(14, personList.size());
        assertEquals(personList.get(1).getName(), "Bear Belle");
        assertEquals(personList.get(1).getMembershipPaymentDate().getYear(), 2018);
        assertEquals(personList.get(1).getMembershipPaymentDate().getMonth(), Month.DECEMBER);
        assertEquals(personList.get(1).getMembershipPaymentDate().getDayOfMonth(), 02);
        assertFalse(personList.get(1).getName().equalsIgnoreCase("Bear Belles"));
    }


    @Test
    void searchCustomerFromList() {

    }

    @Test
    void validateCustomerBeforeWriteToFile() {
        person.setMembershipPaymentDate(LocalDate.of(2017, 12, 02));
        person.setMembershipActive(person.verifyMembership());

        person2.setMembershipPaymentDate(LocalDate.of(2019, 02, 25));
        person2.setMembershipActive(person2.verifyMembership());


        assertFalse(person.getIsMembershipActive());
        assertTrue(person2.getIsMembershipActive());

    }

    @Test
    void writeCustomerToFile() {
        Person testPerson = new Person();
        testPerson.setName("Bear Belle");
        testPerson.setPersonID("Bark1337");
        testPerson.setMembershipPaymentDate(LocalDate.of(2019, 02, 15));
        testPerson.setMembershipActive(testPerson.verifyMembership());
        SystemUtility.writeCustomerToFile(testPerson);
        List<History> customerHistory = SystemUtility.fetchCustomerHistoryFromFile("src//db//customerActivity.txt");

        assertTrue(customerHistory.get(0).getName().equalsIgnoreCase("Bear belle"));

    }

}