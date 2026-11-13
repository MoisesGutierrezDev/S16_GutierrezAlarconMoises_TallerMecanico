package vallegrande.edu.pe.tallerpoo1.model;

public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String propietario;
    private String problema;

    // Constructor vacío
    public Vehiculo() {
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }

    public String getProblema() { return problema; }
    public void setProblema(String problema) { this.problema = problema; }
}