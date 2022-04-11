import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class VentanaDialogo extends JDialog {

    private Connection conexion;
    private final JPanel contentPanel = new JPanel();

    public VentanaDialogo (Connection conexion, double pago) throws HeadlessException, SQLException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        VentanaDialogo ventana = this;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        JPanel contentPane;
        this.conexion = conexion;

        getContentPane().setBackground(new Color(255, 102, 153));
        setBounds(100, 100, 816, 485);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(102, 255, 255));
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(0, 0, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        {
            JLabel lblTexto = new JLabel("\u00A1Operaci\u00F3n realizada con \u00E9xito! Gracias por su compra.");
            lblTexto.setFont(new Font("Calibri", Font.BOLD, 26));
            contentPanel.add(lblTexto);

        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnOK = new JButton("OK");
               // btnOK.setActionCommand("OK");
                buttonPane.add(btnOK);
                getRootPane().setDefaultButton(btnOK);

                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ventana.dispose();
                    }
                });
            }
        }
        {
            JLabel lblCompra = new JLabel("Total a pagar: " + pago + "€");
            lblCompra.setFont(new Font("Calibri", Font.BOLD, 26));
            getContentPane().add(lblCompra, BorderLayout.CENTER);
        }

        ventana.setVisible(true);
    }
}
