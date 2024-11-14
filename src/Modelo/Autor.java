package Modelo;

public class Autor {
    private int autorId;
    private String nombres;
    private String apellidos;

    public Autor(int autorId, String nombres, String apellidos) {
        this.autorId = autorId;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public int getAutorId() { return autorId; }
    public void setAutorId(int autorId) { this.autorId = autorId; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }


    public String toString() {
        return "Autor [ID=" + autorId + ", Nombres=" + nombres + ", Apellidos=" + apellidos + "]";
    }
}