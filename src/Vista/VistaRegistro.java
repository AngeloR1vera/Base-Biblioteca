package Vista;

import Controlador.UsuarioControlador;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegistrar;
    private UsuarioControlador usuarioControlador;

    public VistaRegistro() {
        usuarioControlador = new UsuarioControlador();
        initComponents();
    }

    private void initComponents() {
        setTitle("Register");
        setLayout(new GridLayout(3, 2));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnRegistrar = new JButton("Register");

        add(new JLabel("Username:"));
        add(txtUsername);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel(""));
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarUsuario());

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void registrarUsuario() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        Usuario usuario = new Usuario(0, username, password);
        if (usuarioControlador.registrarUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "User Registered!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}