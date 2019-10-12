import java.time.LocalDate;
import java.time.LocalDate;

public class Person {

    private String name;
    private String personID;
    private LocalDate membershipPaymentDate;
    private boolean isMembershipActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public LocalDate getMembershipPaymentDate() {
        return membershipPaymentDate;
    }

    public void setMembershipPaymentDate(LocalDate membershipPaymentDate) {
        this.membershipPaymentDate = membershipPaymentDate;
    }

    public boolean getIsMembershipActive() {
        return isMembershipActive;
    }

    public void setMembershipActive(boolean membershipActive) {
        isMembershipActive = membershipActive;
    }

    public boolean verifyMembership() {
        boolean isMembershipActive = false;

        LocalDate dateToday = LocalDate.now();
        LocalDate expiredMembership = this.membershipPaymentDate.plusYears(1);

        if (dateToday.isBefore(expiredMembership))
            isMembershipActive = true;

        return isMembershipActive;
    }

    @Override
    public String toString() {
        String checkStatus;

        if (this.isMembershipActive)
            checkStatus = "Aktivt";
        else
            checkStatus = "Upphört";

        return "Namn: " + this.name + "\nPerson ID: " + this.personID + "\nSenast betald avgift: " + this.membershipPaymentDate + "\nMedlems status: " + checkStatus;
    }
}

