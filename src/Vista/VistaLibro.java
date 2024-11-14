package Vista;

import Controlador.LibroControlador;
import Modelo.Categoria;
import Modelo.Libro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VistaLibro extends JFrame {
    private JTextField txtElementoId;
    private JTextField txtTitulo;
    private JTextField txtISBN;
    private JTextField txtAnio;
    private JTextField txtAutor;
    private JComboBox<Categoria> comboCategoria;
    private JTable tableLibros;
    private DefaultTableModel tableModel;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private LibroControlador libroControlador;


    public VistaLibro() {
        libroControlador = new LibroControlador();
        initComponents();
        cargarCategoriasEnComboBox();
    }

    private void initComponents() {
        setTitle("Gestión de Libros");
        setLayout(new BorderLayout());


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(33, 37, 41));


        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());


        txtElementoId = new JTextField(20);
        txtTitulo = new JTextField(20);
        txtISBN = new JTextField(20);
        txtAnio = new JTextField(20);
        txtAutor = new JTextField(20);
        comboCategoria = new JComboBox<>();


        btnAgregar = createCustomButton("Agregar");
        btnEliminar = createCustomButton("Eliminar");
        btnBuscar = createCustomButton("Buscar");


        tableModel = new DefaultTableModel(new String[]{"ID", "Título", "ISBN", "Año", "Autor"}, 0);
        tableLibros = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLibros);


        JPanel panelInput = new JPanel(new GridLayout(6, 2, 10, 10));
        panelInput.setBackground(new Color(54, 57, 63));
        panelInput.add(createLabel("Elemento ID"));
        panelInput.add(txtElementoId);
        panelInput.add(createLabel("Categoría"));
        panelInput.add(comboCategoria);
        panelInput.add(createLabel("Título"));
        panelInput.add(txtTitulo);
        panelInput.add(createLabel("ISBN"));
        panelInput.add(txtISBN);
        panelInput.add(createLabel("Año Publicación"));
        panelInput.add(txtAnio);
        panelInput.add(createLabel("Autor"));
        panelInput.add(txtAutor);


        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(33, 37, 41));
        panelButtons.add(btnAgregar);
        panelButtons.add(btnEliminar);
        panelButtons.add(btnBuscar);


        gradientPanel.add(panelInput, BorderLayout.NORTH);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);
        gradientPanel.add(panelButtons, BorderLayout.SOUTH);

        mainPanel.add(gradientPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);


        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        btnAgregar.addActionListener(e -> {
            try {
                agregarLibro();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnBuscar.addActionListener(e -> buscarLibro());
    }


    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }


    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(33, 150, 243), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
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


    private void agregarLibro() throws SQLException {
        try {
            int elementoId = Integer.parseInt(txtElementoId.getText());
            String titulo = txtTitulo.getText();
            String isbn = txtISBN.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            String autor = txtAutor.getText();

            Categoria categoriaSeleccionada = (Categoria) comboCategoria.getSelectedItem();

            if (categoriaSeleccionada != null) {
                int categoriaId = categoriaSeleccionada.getCategoriaId();

                Libro libro = new Libro(elementoId, 0, titulo, isbn, anio, autor, categoriaId);
                libroControlador.agregarLibro(libro);
                actualizarTabla();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void eliminarLibro() {
        int selectedRow = tableLibros.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            libroControlador.eliminarLibro(id);
            actualizarTabla();
        }
    }


    private void buscarLibro() {
        String criterio = JOptionPane.showInputDialog(this, "Ingrese el criterio de búsqueda:");
        List<Libro> libros = libroControlador.buscarLibro(criterio);
        actualizarTabla(libros);
    }


    private void actualizarTabla() {
        List<Libro> libros = libroControlador.obtenerTodosLosLibros();
        actualizarTabla(libros);
    }

    private void actualizarTabla(List<Libro> libros) {
        tableModel.setRowCount(0);
        for (Libro libro : libros) {
            tableModel.addRow(new Object[]{libro.getId(), libro.getTitulo(), libro.getIsbn(), libro.getAnioPublicacion(), libro.getAutor()});
        }
    }


    private void cargarCategoriasEnComboBox() {
        comboCategoria.removeAllItems();

        List<Categoria> categorias = libroControlador.obtenerCategorias();
        for (Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }
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
}