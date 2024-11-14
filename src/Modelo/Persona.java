package Modelo;

public class Persona {
    private int personaId;
    private String nombre;
    private String apellidos;


    public Persona(int personaId, String nombre, String apellidos) {
        this.personaId = personaId;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }


    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }


    public String toString() {
        return "Persona [ID=" + personaId + ", Nombre=" + nombre + ", Apellidos=" + apellidos + "]";
    }
}