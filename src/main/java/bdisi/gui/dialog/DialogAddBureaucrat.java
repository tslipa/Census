package bdisi.gui.dialog;

import javax.swing.*;
import java.sql.Connection;

public class DialogAddBureaucrat extends DialogAddCitizen {
    public DialogAddBureaucrat(Connection connection) {
        super(connection);
    }

    @Override
    protected void initUI() {
        this.setTitle("Census [add new bureaucrat]");
        this.setSize(500, 520);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    protected boolean addCitizen(String pesel, String password, String name, String surname, String city, String street, int house, int flat) {
        //TODO: check if pesel already exists in database
        //TODO: take care of the "null" flat
        //the same as in DialogAddCitizen but with bureaucrat
        return true;
    }
}
