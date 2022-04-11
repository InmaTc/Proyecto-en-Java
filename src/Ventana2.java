import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana2 extends JDialog {
    private final JTextField txtPrecio;
    private final JTextField txtPeso;
    private final JTextField txtStock;
    private final JTextField txtCantidad;

    private int stockActual;
    private JLabel lblNomProd;
    private JLabel lblPrecio;
    private JLabel lblPeso;
    private JLabel lblStock;
    private JLabel lblCantidad;
    private JButton btnComprar;
    private int cantidad;
    private int stockOld;
    private int idProducto;
    private String nombreSelec;

    public Ventana2(Connection conexion, int idCategoria, String nombreLista) throws HeadlessException, SQLException ,UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Ventana2 ventana = this;
        JPanel contentPane;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 500, 500);
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

        lblNomProd = new JLabel("Productos");
        lblNomProd.setFont(new Font("Calibri", Font.BOLD, 28));
        lblNomProd.setForeground(new Color(153, 0, 204));
        GridBagConstraints gbc_lblNomProd = new GridBagConstraints();
        gbc_lblNomProd.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNomProd.insets = new Insets(0, 0, 5, 5);
        gbc_lblNomProd.gridx = 2;
        gbc_lblNomProd.gridy = 1;
        contentPane.add(lblNomProd, gbc_lblNomProd);

        lblPrecio = new JLabel("Precio/Unidad (\u20AC):");
        GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
        gbc_lblPrecio.anchor = GridBagConstraints.EAST;
        gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
        gbc_lblPrecio.gridx = 1;
        gbc_lblPrecio.gridy = 3;
        contentPane.add(lblPrecio, gbc_lblPrecio);

        txtPrecio = new JTextField();
        GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
        gbc_txtPrecio.insets = new Insets(0, 0, 5, 5);
        gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrecio.gridx = 2;
        gbc_txtPrecio.gridy = 3;
        contentPane.add(txtPrecio, gbc_txtPrecio);
        txtPrecio.setColumns(10);
        txtPrecio.setEditable(false);


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
        txtPeso.setEditable(false);

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
        txtStock.setEditable(false);

        lblCantidad = new JLabel("Cantidad");
        GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
        gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
        gbc_lblCantidad.anchor = GridBagConstraints.EAST;
        gbc_lblCantidad.gridx = 1;
        gbc_lblCantidad.gridy = 6;
        contentPane.add(lblCantidad, gbc_lblCantidad);

        //Documento para ver el texto que hay en cantidad

        txtCantidad = new JTextField();
        GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
        gbc_txtCantidad.insets = new Insets(0, 0, 5, 5);
        gbc_txtCantidad.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCantidad.gridx = 2;
        gbc_txtCantidad.gridy = 6;
        contentPane.add(txtCantidad, gbc_txtCantidad);
        txtCantidad.setColumns(10);

        Document documentoC = txtCantidad.getDocument();

        //Botón COMPRAR
        btnComprar = new JButton("Comprar");
        GridBagConstraints gbc_btnComprar = new GridBagConstraints();
        gbc_btnComprar.insets = new Insets(0, 0, 5, 5);
        gbc_btnComprar.gridx = 1;
        gbc_btnComprar.gridy = 8;
        contentPane.add(btnComprar, gbc_btnComprar);
        btnComprar.setEnabled(false);

        //Botón CANCERLAR
        JButton btnCancelar = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 8;
        contentPane.add(btnCancelar, gbc_btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ventana.dispose();
            }
        });

        documentoC.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                comprobar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                comprobar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comprobar();
            }
            private void comprobar() {

                try {
                    stockOld = Integer.parseInt(txtStock.getText());
                    cantidad = Integer.parseInt(txtCantidad.getText());

                    if (cantidad > 0 && cantidad <= stockOld)
                        btnComprar.setEnabled(documentoC.getLength() > 0);
                } catch (Exception e) {
                    btnComprar.setEnabled(false);
                }
            }
        });

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nombreSelec = lblNomProd.getText();

                    PreparedStatement s = conexion.prepareStatement("SELECT precio, idProducto, stock FROM producto WHERE nombre = ?");
                    s.setString(1, nombreSelec);
                    ResultSet resultado = s.executeQuery();
                    while (resultado.next()) {
                        double precio = resultado.getDouble("precio");
                        double pago = precio * (double) cantidad;
                        idProducto = resultado.getInt("idProducto");

                        stockActual = stockOld - cantidad;

                        s = conexion.prepareStatement("UPDATE producto SET stock = ? WHERE idProducto = ?");
                        s.setInt(1, stockActual);
                        s.setInt(2, idProducto);
                        s.executeUpdate();

                        //System.out.println("Se han actualizado " + s.executeUpdate() + " registros.");

                        //actualizarStock();
                        txtStock.setText(String.valueOf(stockActual));

                        new VentanaDialogo(conexion, pago);

                        txtCantidad.setText("");
                    }

                } catch (SQLException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        PreparedStatement s = conexion.prepareStatement("SELECT idproducto,nombre,idCategoria,precio,peso,stock FROM producto WHERE idCategoria = ? and nombre = ?");
        s.setInt(1, idCategoria);
        s.setString(2, nombreLista);
        ResultSet resultado = s.executeQuery();
        if (resultado.next()) {
            lblNomProd.setText(resultado.getString("nombre"));
            txtPrecio.setText(String.valueOf(resultado.getDouble("precio")));
            txtPeso.setText(String.valueOf(resultado.getInt("peso")));
            txtStock.setText(String.valueOf(resultado.getInt("stock")));
        }

        ventana.setVisible(true);
    }

}
