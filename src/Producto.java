import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private int idCategoria;
    private boolean sinGluten;
    private boolean integral;
    private double precio;
    private int peso;
    private int stock;


    public Producto(String nombre, int idCategoria, boolean sinGluten, boolean integral, double precio, int peso, int stock) {
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.sinGluten = sinGluten;
        this.integral = integral;
        this.precio = precio;
        this.peso = peso;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isSinGluten() {
        return sinGluten;
    }

    public void setSinGluten(boolean sinGluten) {
        this.sinGluten = sinGluten;
    }

    public boolean isIntegral() {
        return integral;
    }

    public void setIntegral(boolean integral) {
        this.integral = integral;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria +
                ", sinGluten=" + sinGluten +
                ", integral=" + integral +
                ", precio=" + precio +
                ", peso=" + peso +
                ", stock=" + stock +
                '}';
    }
}
