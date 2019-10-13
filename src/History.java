public class History {
    private String name;
    private String personID;
    private String latestActivity;
    private String memberStatus;
    private String paymentOfMembership;

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getPaymentOfMembership() {
        return paymentOfMembership;
    }

    public void setPaymentOfMembership(String paymentOfMembership) {
        this.paymentOfMembership = paymentOfMembership;
    }

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

    public String getLatestActivity() {
        return latestActivity;
    }

    public void setLatestActivity(String latestActivity) {
        this.latestActivity = latestActivity;
    }

    @Override
    public String toString() {
        return "Namn: " + this.name + "\nPerson ID: " + this.personID + "\nSenaste aktivitet: " + this.latestActivity;
    }
}
