import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana3 extends JDialog {
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtPeso;
    private final JTextField txtGluten;
    private final JTextField txtIntegral;
    private final JTextField txtCodigo;
    private final JTextField txtStock;
    private final JTextField txtCategoria;

    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblPeso;
    private JLabel lblStock;
    private JLabel lblCantegoria;
    private JLabel lblGluten;
    private JLabel lblCodigo;
    private JLabel lblIntegral;
    private JButton btnEliminar;


    public Ventana3(Connection conexion) throws HeadlessException, SQLException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Ventana3 ventana = this;
        JPanel contentPane;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 500, 350);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 255, 153));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{30, 0, 64, 30, 0};
        gbl_contentPane.rowHeights = new int[]{40, 0, 30, 30, 0, 31, 0, 30, 0, 30, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);


        lblCodigo = new JLabel("Código: ");
        GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
        gbc_lblCodigo.anchor = GridBagConstraints.EAST;
        gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCodigo.gridx = 1;
        gbc_lblCodigo.gridy = 2;
        contentPane.add(lblCodigo, gbc_lblCodigo);

        txtCodigo = new JTextField();
        GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
        gbc_txtCodigo.insets = new Insets(0, 0, 5, 5);
        gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCodigo.gridx = 2;
        gbc_txtCodigo.gridy = 2;
        contentPane.add(txtCodigo, gbc_txtCodigo);
        txtCodigo.setColumns(10);


        lblNombre = new JLabel("Nombre: ");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 3;
        contentPane.add(lblNombre, gbc_lblNombre);

        txtNombre = new JTextField();
        GridBagConstraints gbc_txtNombre = new GridBagConstraints();
        gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
        gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNombre.gridx = 2;
        gbc_txtNombre.gridy = 3;
        contentPane.add(txtNombre, gbc_txtNombre);
        txtNombre.setColumns(10);

        lblPrecio = new JLabel("Precio/Unidad (\u20AC):");
        GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
        gbc_lblPrecio.anchor = GridBagConstraints.EAST;
        gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrecio.gridx = 3;
        gbc_lblPrecio.gridy = 3;
        contentPane.add(lblPrecio, gbc_lblPrecio);

        txtPrecio = new JTextField();
        GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
        gbc_txtPrecio.insets = new Insets(0, 0, 5, 5);
        gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrecio.gridx = 4;
        gbc_txtPrecio.gridy = 3;
        contentPane.add(txtPrecio, gbc_txtPrecio);
        txtPrecio.setColumns(10);

        lblGluten = new JLabel("Sin gluten:");
        GridBagConstraints gbc_lblGluten = new GridBagConstraints();
        gbc_lblGluten.anchor = GridBagConstraints.EAST;
        gbc_lblGluten.insets = new Insets(0, 0, 5, 5);
        gbc_lblGluten.gridx = 3;
        gbc_lblGluten.gridy = 4;
        contentPane.add(lblGluten, gbc_lblGluten);

        txtGluten = new JTextField();
        GridBagConstraints gbc_txtGluten = new GridBagConstraints();
        gbc_txtGluten.insets = new Insets(0, 0, 5, 5);
        gbc_txtGluten.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtGluten.gridx = 4;
        gbc_txtGluten.gridy = 4;
        contentPane.add(txtGluten, gbc_txtGluten);
        txtGluten.setColumns(10);

        lblIntegral = new JLabel("Integral:");
        GridBagConstraints gbc_lblIntegral = new GridBagConstraints();
        gbc_lblIntegral.anchor = GridBagConstraints.EAST;
        gbc_lblIntegral.insets = new Insets(0, 0, 5, 5);
        gbc_lblIntegral.gridx = 3;
        gbc_lblIntegral.gridy = 5;
        contentPane.add(lblIntegral, gbc_lblIntegral);

        txtIntegral = new JTextField();
        GridBagConstraints gbc_txtIntegral = new GridBagConstraints();
        gbc_txtIntegral.insets = new Insets(0, 0, 5, 5);
        gbc_txtIntegral.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtIntegral.gridx = 4;
        gbc_txtIntegral.gridy = 5;
        contentPane.add(txtIntegral, gbc_txtIntegral);
        txtIntegral.setColumns(10);

        lblPeso = new JLabel("Peso (g):");
        GridBagConstraints gbc_lblPeso = new GridBagConstraints();
        gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
        gbc_lblPeso.anchor = GridBagConstraints.EAST;
        gbc_lblPeso.gridx = 1;
        gbc_lblPeso.gridy = 4;
        contentPane.add(lblPeso, gbc_lblPeso);

        txtPeso = new JTextField();
        GridBagConstraints gbc_txtPeso = new GridBagConstraints();
        gbc_txtPeso.insets = new Insets(0, 0, 5, 5);
        gbc_txtPeso.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPeso.gridx = 2;
        gbc_txtPeso.gridy = 4;
        contentPane.add(txtPeso, gbc_txtPeso);
        txtPeso.setColumns(10);

        lblStock = new JLabel("Stock");
        GridBagConstraints gbc_lblStock = new GridBagConstraints();
        gbc_lblStock.insets = new Insets(0, 0, 5, 5);
        gbc_lblStock.anchor = GridBagConstraints.EAST;
        gbc_lblStock.gridx = 1;
        gbc_lblStock.gridy = 5;
        contentPane.add(lblStock, gbc_lblStock);

        txtStock = new JTextField();
        GridBagConstraints gbc_txtStock = new GridBagConstraints();
        gbc_txtStock.insets = new Insets(0, 0, 5, 5);
        gbc_txtStock.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtStock.gridx = 2;
        gbc_txtStock.gridy = 5;
        contentPane.add(txtStock, gbc_txtStock);
        txtStock.setColumns(10);

        lblCantegoria = new JLabel("Categoría");
        GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
        gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
        gbc_lblCantidad.anchor = GridBagConstraints.EAST;
        gbc_lblCantidad.gridx = 1;
        gbc_lblCantidad.gridy = 6;
        contentPane.add(lblCantegoria, gbc_lblCantidad);

        //Documento para ver el texto que hay en cantidad

        txtCategoria = new JTextField();
        GridBagConstraints gbc_txtCantegoria = new GridBagConstraints();
        gbc_txtCantegoria.insets = new Insets(0, 0, 5, 5);
        gbc_txtCantegoria.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCantegoria.gridx = 2;
        gbc_txtCantegoria.gridy = 6;
        contentPane.add(txtCategoria, gbc_txtCantegoria);
        txtCategoria.setColumns(10);


        //Botón INSERTAR/MODIFICAR
        JButton btnInsertar = new JButton("Insertar/Modificar");
        GridBagConstraints gbc_btnInsertar = new GridBagConstraints();
        gbc_btnInsertar.insets = new Insets(0, 0, 5, 5);
        gbc_btnInsertar.gridx = 1;
        gbc_btnInsertar.gridy = 8;
        contentPane.add(btnInsertar, gbc_btnInsertar);
        //btnInsertar.setEnabled(false);

        btnEliminar = new JButton("Eliminar");
        GridBagConstraints gbc_btnComprar = new GridBagConstraints();
        gbc_btnComprar.insets = new Insets(0, 0, 5, 5);
        gbc_btnComprar.gridx = 2;
        gbc_btnComprar.gridy = 8;
        contentPane.add(btnEliminar, gbc_btnComprar);
        //btnEliminar.setEnabled(false);
       // System.out.println("longitud " + txtCodigo.getText().length());


        //Botón CANCERLAR
        JButton btnCancelar = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancelar.gridx = 3;
        gbc_btnCancelar.gridy = 8;
        contentPane.add(btnCancelar, gbc_btnCancelar);


        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }
        });

       btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(txtCodigo.getText());
                if (idProducto > 0) {
                    Producto nuevoProducto = new Producto(txtNombre.getText(),
                            Integer.parseInt(txtCategoria.getText()),
                            Boolean.parseBoolean(txtGluten.getText()),
                            Boolean.parseBoolean(txtIntegral.getText()),
                            Double.parseDouble(txtPrecio.getText()),
                            Integer.parseInt(txtPeso.getText()),
                            Integer.parseInt(txtStock.getText()));

                    try {
                        PreparedStatement s = conexion.prepareStatement("SELECT idProducto FROM producto WHERE idProducto = ?");
                        s.setInt(1, idProducto);
                        ResultSet resultado = s.executeQuery();

                        if (resultado.next()) {
                            s = conexion.prepareStatement("UPDATE producto SET nombre = ?, idCAtegoria = ?, sinGluten = ?, integral = ?, precio = ?, peso = ?, stock = ? WHERE idProducto = ?");
                            s.setString(1, nuevoProducto.getNombre());
                            s.setInt(2, nuevoProducto.getIdCategoria());
                            s.setBoolean(3, nuevoProducto.isSinGluten());
                            s.setBoolean(4, nuevoProducto.isIntegral());
                            s.setDouble(5, nuevoProducto.getPrecio());
                            s.setInt(6, nuevoProducto.getPeso());
                            s.setInt(7, nuevoProducto.getStock());
                            s.setInt(8, idProducto);
                            s.executeUpdate();
                        } else {
                            s = conexion.prepareStatement("INSERT INTO producto (idProducto, nombre, idCategoria, sinGluten, integral, precio, peso, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                            s.setInt(1, idProducto);
                            s.setString(2, nuevoProducto.getNombre());
                            s.setInt(3, nuevoProducto.getIdCategoria());
                            s.setBoolean(4, nuevoProducto.isSinGluten());
                            s.setBoolean(5, nuevoProducto.isIntegral());
                            s.setDouble(6, nuevoProducto.getPrecio());
                            s.setInt(7, nuevoProducto.getPeso());
                            s.setInt(8, nuevoProducto.getStock());
                            s.execute();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(ventana, "Se ha insertado/modificado el producto correctamente.", "Operación realizada", JOptionPane.INFORMATION_MESSAGE);
                ventana.dispose();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(txtCodigo.getText());
                if (idProducto > 0) {
                    try {
                        PreparedStatement s = conexion.prepareStatement("DELETE FROM producto WHERE idProducto = ?");
                        s.setInt(1, idProducto);
                        s.execute();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    vaciar();
                }
                JOptionPane.showMessageDialog(ventana, "Se ha eliminado el producto correctamente.", "Operación realizada", JOptionPane.INFORMATION_MESSAGE);
                ventana.dispose();
            }
        });

        //Autocompletado si existe el idCodigo en la base de datos
        txtCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idProducto = Integer.parseInt(txtCodigo.getText());
                if (idProducto > 0) {
                    try {
                        PreparedStatement s = conexion.prepareStatement("SELECT * FROM producto WHERE idProducto = ?");
                        s.setInt(1, idProducto);
                        ResultSet resultado = s.executeQuery();

                        if (!resultado.next()) {
                            JOptionPane.showMessageDialog(ventana, "El código no se ha encontrado. \nRealiza una inserción.", "¡FALLO!", JOptionPane.ERROR_MESSAGE);

                        } else {
                            Producto empresa = new Producto(
                                    resultado.getString("nombre"),
                                    resultado.getInt("idCategoria"),
                                    resultado.getBoolean("sinGluten"),
                                    resultado.getBoolean("integral"),
                                    resultado.getDouble("precio"),
                                    resultado.getInt("peso"),
                                    resultado.getInt("stock")
                            );

                            txtNombre.setText(empresa.getNombre());
                            txtCategoria.setText(String.valueOf(empresa.getIdCategoria()));
                            txtGluten.setText(String.valueOf(empresa.isSinGluten()));
                            txtIntegral.setText(String.valueOf(empresa.isIntegral()));
                            txtPrecio.setText(String.valueOf(empresa.getPrecio()));
                            txtPeso.setText(String.valueOf(empresa.getPeso()));
                            txtStock.setText(String.valueOf(empresa.getStock()));
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
            //PARA ACTIVAR Y DESCACTIVAR BOTONES INSERT Y DELETE
        /*Document documentoID = txtCodigo.getDocument();

        documentoID.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                longitud();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                longitud();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                longitud();
            }
            private void longitud() {
                try {
                    stock = Integer.parseInt(txtStock.getText());
                    idProducto = Integer.parseInt(txtCodigo.getText());
                    int peso = Integer.parseInt(txtPeso.getText());
                    int idCategoria = Integer.parseInt(txtStock.getText());
                    double precio = Double.parseDouble(txtPrecio.getText());
                    boolean gluten = Boolean.parseBoolean(txtGluten.getText());
                    boolean integral = Boolean.parseBoolean(txtIntegral.getText());

                    PreparedStatement s = conexion.prepareStatement("SELECT idProducto FROM producto WHERE idProducto = ?");
                    s.setInt(1, idProducto);
                    ResultSet resultado = s.executeQuery();
                    if (resultado.next()) {
                        btnEliminar.setEnabled(true);
                    }

                    btnInsertar.setEnabled(idProducto > 0 && stock >= 0 && peso > 0 && idCategoria > 0 && idCategoria < 7 && precio > 0 &&
                            (gluten == true || gluten == false) && (integral == true || integral == false));

                } catch (Exception e) {
                    e.printStackTrace();
                    btnInsertar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }
            }
        });*/

        ventana.setVisible(true);
    }

    private void vaciar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCategoria.setText("");
        txtGluten.setText("");
        txtIntegral.setText("");
        txtPrecio.setText("");
        txtPeso.setText("");
        txtStock.setText("");
    }
   /* private void comprobar(JButton btnModificar, JButton btnEliminar) throws SQLException {
        boolean idValido = txtCodigo.getText().length() > 0;
        boolean precioValido = txtPrecio.getText().length() > 0;
        boolean pesoValido = txtPeso.getText().length() > 0;
        boolean stockValido = txtStock.getText().length() > 0;

        try {
            Integer.parseInt(txtCodigo.getText());
            Integer.parseInt(txtPeso.getText());
            Integer.parseInt(txtStock.getText());
            Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException ignore) {
            idValido = false;
            precioValido = false;
            pesoValido = false;
            stockValido = false;
        }
       int idProducto = Integer.parseInt(txtCodigo.getText());
        btnModificar.setEnabled(idProducto > 0 &&
                txtCodigo.getText().length() > 0 &&
                txtPeso.getText().length() > 0 &&
                txtNombre.getText().length() > 0 &&
                txtPrecio.getText().length() > 0 &&
                txtStock.getText().length() > 0 &&
                idValido && precioValido && pesoValido && stockValido
        );

    }*/

}
