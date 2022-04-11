
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaPrincipal extends JFrame {
    private Connection conexion;
    private JComboBox comboBoxNomProd;
    private JCheckBox chckbxSinGluten;
    private JCheckBox chckbxIntegral;
    private JList<String> lstNombres;
    private JButton btnSelec;
    private JButton btnSelecProd;
    private ResultSet resultado1;
    private ResultSet resultado2;
    private int idCategoria;
    private DefaultComboBoxModel<String> lstNombreCateg;


    public VentanaPrincipal(Connection conexion) throws HeadlessException, SQLException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame ventana = this;
        JPanel contentPane;

        this.conexion = conexion;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 449);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{30, 0, 20, 0, 0, 0, 68, 0, 27, 0, -24, 0, 0, 0, 17, 0, 0, 0, 0, 0, 40, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 30, 70, 20, 31, 30, 0, 23, 22, 22, 35, 0, 20, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblTitulo = new JLabel("Confiter\u00EDa");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(128, 0, 0));
        lblTitulo.setBackground(new Color(51, 204, 204));
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.gridheight = 2;
        gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
        gbc_lblTitulo.gridwidth = 9;
        gbc_lblTitulo.gridx = 7;
        gbc_lblTitulo.gridy = 0;
        contentPane.add(lblTitulo, gbc_lblTitulo);

        JLabel lblProducto = new JLabel("B\u00FAsqueda por categor\u00EDa");
        GridBagConstraints gbc_lblProducto = new GridBagConstraints();
        gbc_lblProducto.gridwidth = 4;
        gbc_lblProducto.insets = new Insets(0, 0, 5, 5);
        gbc_lblProducto.gridx = 3;
        gbc_lblProducto.gridy = 3;
        contentPane.add(lblProducto, gbc_lblProducto);

        //Lista de los nombres de categorías
        comboBoxNomProd = new JComboBox();
        GridBagConstraints gbc_comboBoxNomProd = new GridBagConstraints();
        gbc_comboBoxNomProd.gridwidth = 5;
        gbc_comboBoxNomProd.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxNomProd.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxNomProd.gridx = 8;
        gbc_comboBoxNomProd.gridy = 3;
        contentPane.add(comboBoxNomProd, gbc_comboBoxNomProd);
        comboBoxNomProd.setEditable(false);
        listarComboBox(conexion);

        //FILTROS
        JLabel lblFiltros = new JLabel("FILTROS");
        GridBagConstraints gbc_lblFiltros = new GridBagConstraints();
        gbc_lblFiltros.insets = new Insets(0, 0, 5, 5);
        gbc_lblFiltros.gridx = 7;
        gbc_lblFiltros.gridy = 5;
        contentPane.add(lblFiltros, gbc_lblFiltros);

        //Filtro SIN GLUTEN
        chckbxSinGluten = new JCheckBox("Sin gluten");
        GridBagConstraints gbc_chckbxSinGluten = new GridBagConstraints();
        gbc_chckbxSinGluten.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxSinGluten.gridx = 9;
        gbc_chckbxSinGluten.gridy = 5;
        contentPane.add(chckbxSinGluten, gbc_chckbxSinGluten);

        //Filtro integral
        chckbxIntegral = new JCheckBox("Integral");
        GridBagConstraints gbc_chckbxIntegral = new GridBagConstraints();
        gbc_chckbxIntegral.insets = new Insets(0, 0, 5, 5);
        gbc_chckbxIntegral.gridx = 11;
        gbc_chckbxIntegral.gridy = 5;
        contentPane.add(chckbxIntegral, gbc_chckbxIntegral);

        //Botón seleccionar CATEGORÍA
        btnSelec = new JButton("Seleccionar");
        GridBagConstraints gbc_btnSelec = new GridBagConstraints();
        gbc_btnSelec.insets = new Insets(0, 0, 5, 5);
        gbc_btnSelec.gridx = 12;
        gbc_btnSelec.gridy = 5;
        contentPane.add(btnSelec, gbc_btnSelec);

        btnSelec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreSelec = (String) lstNombreCateg.getSelectedItem();

                    PreparedStatement s = conexion.prepareStatement("SELECT idCategoria FROM categoria WHERE nombre = ?");
                    s.setString(1, nombreSelec);
                    ResultSet resultado = s.executeQuery();
                    if (resultado.next())
                        idCategoria = resultado.getInt("idCategoria");

                    listarNombres(conexion);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        lstNombres = new JList<>();
        lstNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Lista de nombres por categorias y filtros
        JScrollPane scrollPane = new JScrollPane(lstNombres);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 4;
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 9;
        gbc_scrollPane.gridy = 7;
        contentPane.add(scrollPane, gbc_scrollPane);

        //SELECCIONAR PRODUCTO
        btnSelecProd = new JButton("Seleccionar producto");
        GridBagConstraints gbc_btnSelec_1 = new GridBagConstraints();
        gbc_btnSelec_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnSelec_1.gridx = 9;
        gbc_btnSelec_1.gridy = 12;
        contentPane.add(btnSelecProd, gbc_btnSelec_1);
        btnSelecProd.setEnabled(false);


        btnSelecProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreSelec = lstNombres.getSelectedValue();

                    PreparedStatement s = conexion.prepareStatement("SELECT idCategoria, nombre FROM producto WHERE nombre = ?");
                    s.setString(1, nombreSelec);
                    ResultSet resultado = s.executeQuery();

                    if (resultado.next()) {
                        idCategoria = resultado.getInt("idCategoria");
                        new Ventana2(conexion, idCategoria, nombreSelec);
                        lstNombres.clearSelection();
                    }

                } catch (SQLException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //Casilla marcar que pueda modificarse
        JCheckBox cbSelecModif = new JCheckBox("Activar permisos");
        GridBagConstraints gbc_btnSelecModif = new GridBagConstraints();
        gbc_btnSelec_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnSelec_1.gridx = 6;
        gbc_btnSelec_1.gridy = 10;
        cbSelecModif.setSelected(false);

        contentPane.add(cbSelecModif, gbc_btnSelecModif);

        cbSelecModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Permiso(ventana);
                } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                    unsupportedLookAndFeelException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });

        JButton btnModifica = new JButton("Modificar");
        GridBagConstraints gbc_btnModifica = new GridBagConstraints();
        gbc_btnSelec.insets = new Insets(0, 0, 5, 5);
        gbc_btnSelec.gridx = 6;
        gbc_btnSelec.gridy = 14;
        contentPane.add(btnModifica, gbc_btnModifica);

        btnModifica.setEnabled(false);

        btnModifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Ventana3(conexion);
                    cbSelecModif.setSelected(false);
                    btnModifica.setEnabled(false);
                } catch (SQLException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //ACTIVAR PERMISOS
        cbSelecModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnModifica.setEnabled(true);
            }
        });

        JButton btnSalir = new JButton("Salir");
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
        gbc_btnSalir.gridx = 12;
        gbc_btnSalir.gridy = 12;
        contentPane.add(btnSalir, gbc_btnSalir);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        ventana.setVisible(true);
    }

    private void listarComboBox(Connection conexion) throws SQLException {
        Statement s = conexion.createStatement();
        resultado1 = s.executeQuery("SELECT nombre FROM categoria ORDER BY idCategoria");

        lstNombreCateg = new DefaultComboBoxModel<>();

        while (resultado1.next()) {
            lstNombreCateg.addElement(resultado1.getString("nombre"));

        }

        comboBoxNomProd.setModel(lstNombreCateg);
    }

    private void listarNombres(Connection conexion) throws SQLException {
        String sql = "SELECT nombre FROM producto WHERE idCategoria = ? AND 1 = 1 ";

        if (chckbxIntegral.isSelected()) {
            sql = sql + " AND integral = 1";
        }

        if (chckbxSinGluten.isSelected()) {
            sql = sql + " AND sinGluten = 1";
        }

        PreparedStatement s = conexion.prepareStatement(sql);
        s.setInt(1, idCategoria);
        resultado2 = s.executeQuery();

        DefaultListModel<String> elementos = new DefaultListModel<>();
        lstNombres.setModel(elementos);

        while (resultado2.next()) {
            elementos.addElement(resultado2.getString("nombre"));
        }
        lstNombres.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnSelecProd.setEnabled(lstNombres.getSelectedIndex() > -1);
            }
        });
    }

}
