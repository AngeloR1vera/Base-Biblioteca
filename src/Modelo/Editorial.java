package Modelo;

public class Editorial {
    private int editorialId;
    private String nombreEditorial;


    public Editorial(int editorialId, String nombreEditorial) {
        this.editorialId = editorialId;
        this.nombreEditorial = nombreEditorial;
    }


    public int getEditorialId() { return editorialId; }
    public void setEditorialId(int editorialId) { this.editorialId = editorialId; }

    public String getNombreEditorial() { return nombreEditorial; }
    public void setNombreEditorial(String nombreEditorial) { this.nombreEditorial = nombreEditorial; }


    public String toString() {
        return "Editorial [ID=" + editorialId + ", Nombre=" + nombreEditorial + "]";
    }
}