import Vista.VistaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaLogin vistaLogin = new VistaLogin();
            vistaLogin.setVisible(true);
        });
    }
}