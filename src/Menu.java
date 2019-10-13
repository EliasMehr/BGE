import javax.swing.*;

class Menu {

    static final String TITLE_WELCOME = "BESTGYMEVER - MENY";

    static void menuSelectionAlternative() {
        String[] menuAlternatives = {"1.Sök kunder", "2.Läs kund aktivitet", "3.Avsluta programmet"};
        Object menuInput = JOptionPane.showInputDialog(null, "Vänligen välj ett val", TITLE_WELCOME, JOptionPane.INFORMATION_MESSAGE, null, menuAlternatives, "1.Sök kunder");

        if (menuInput == menuAlternatives[0])
            GateKeeper.runApplication();

        else if (menuInput == menuAlternatives[1])
            GateKeeper.searchCustomerActivity();
        else {
            System.out.println("Terminating application");
            System.exit(0);
        }
    }
}