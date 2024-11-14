package Modelo;

public class Libro {
    private int elementoId;
    private int libroId;
    private String titulo;
    private int anioPublicacion;
    private String nroISBN;
    private int categoriaId;
    private int editorialId;
    private int id;
    private String isbn;
    private String autor;


    public Libro(int elementoId, int libroId, String titulo, String isbn, int anioPublicacion, String autor) {
        this.elementoId = elementoId;
        this.libroId = libroId;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.nroISBN = isbn;
        this.autor = autor;
    }

    public Libro(int elementoId, String titulo, String isbn, int anio, String autor, int categoriaId) {
    }

    public Libro(int elementoId, int i, String titulo, String isbn, int anio, String autor, int categoriaId) {
    }

    // Getters y Setters
    public int getElementoId() { return elementoId; }
    public void setElementoId(int elementoId) { this.elementoId = elementoId; }

    public int getLibroId() { return libroId; }
    public void setLibroId(int libroId) { this.libroId = libroId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String getNroISBN() { return nroISBN; }
    public void setNroISBN(String nroISBN) { this.nroISBN = nroISBN; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public int getEditorialId() { return editorialId; }
    public void setEditorialId(int editorialId) { this.editorialId = editorialId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }


    public String toString() {
        return "Libro [ID=" + libroId + ", Título=" + titulo + ", Año de publicación=" + anioPublicacion +
                ", ISBN=" + nroISBN + "]";
    }
}