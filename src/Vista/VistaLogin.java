package Vista;

import Controlador.UsuarioControlador;
import Modelo.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VistaLogin extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;

    private UsuarioControlador usuarioControlador;

    public VistaLogin() {
        usuarioControlador = new UsuarioControlador();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.setBackground(new Color(33, 37, 41));

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        loginPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(33, 150, 243), 2), "User Login",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), new Color(33, 150, 243)
        ));
        loginPanel.setBackground(new Color(54, 57, 63));

        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setBorder(new TitledBorder("Username"));
        txtUsername = new JTextField(10);
        usernamePanel.add(txtUsername, BorderLayout.CENTER);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBorder(new TitledBorder("Password"));
        txtPassword = new JPasswordField(10);
        passwordPanel.add(txtPassword, BorderLayout.CENTER);

        loginPanel.add(usernamePanel);
        loginPanel.add(passwordPanel);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        btnLogin = createCustomButton("Login");
        btnRegister = createCustomButton("Register");
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gradientPanel.add(loginPanel, BorderLayout.CENTER);
        gradientPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(gradientPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAction();
            }
        });
    }

    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(33, 150, 243), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 182, 246));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 150, 243));
            }
        });

        return button;
    }


    class GradientPanel extends JPanel {
        private final Color startColor = new Color(33, 37, 41);
        private final Color endColor = new Color(54, 57, 63);


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;


            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void loginAction() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Usuario user = usuarioControlador.verificarCredenciales(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();


                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        VistaLibro vistaLibro = new VistaLibro();
                        vistaLibro.setVisible(true);
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registerAction() {

        VistaRegistro vistaRegistro = new VistaRegistro();

        vistaRegistro.setVisible(true);
    }
}