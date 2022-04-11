import javax.swing.*;
import java.sql.*;

public class Principal {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Proyecto", "root", "oretania");
            System.out.println("Conexión abierta con la base de datos");
            new VentanaPrincipal(conexion);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
