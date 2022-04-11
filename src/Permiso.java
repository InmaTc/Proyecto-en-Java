import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Permiso extends JDialog {

    public Permiso(Frame owner) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super(owner, true);

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField(20);

        JLabel lblClave = new JLabel("Contraseña:");
        JPasswordField txtClave = new JPasswordField(20);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setEnabled(false);
        this.getRootPane().setDefaultButton(btnIngresar);

        JButton btnCancelar = new JButton("Cancelar");

        DocumentListener documentListener = new DocumentListener() {
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
                btnIngresar.setEnabled(txtUsuario.getText().length() > 0 && txtClave.getPassword().length > 0);
            }
        };

        txtUsuario.getDocument().addDocumentListener(documentListener);
        txtClave.getDocument().addDocumentListener(documentListener);

        JDialog dialog = this;

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().equals("usuario") &&
                        String.valueOf(txtClave.getPassword()).equals("oretania")) {
                    JOptionPane.showMessageDialog(dialog, "¡Bienvenido/a a la aplicación para empleados! \n" +
                            "Ya puedes realizar modificaciones en la base de datos.", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Usuario/contraseña incorrectos", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setLayout(new FlowLayout());
        this.add(lblUsuario);
        this.add(txtUsuario);
        this.add(lblClave);
        this.add(txtClave);
        this.add(btnIngresar);
        this.add(btnCancelar);

        this.setSize(280, 180);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}