package Modelo;

import java.sql.Date;

public class Prestamo {
    private int prestamoId;
    private Date fechaSolicitud;
    private Date fechaPlazoEntrega;
    private Date fechaDevolucion;
    private int estadoPrestamoId;
    private int personaId;
    private int elementoId;


    public Prestamo(int prestamoId, Date fechaSolicitud, Date fechaPlazoEntrega, Date fechaDevolucion,
                    int estadoPrestamoId, int personaId, int elementoId) {
        this.prestamoId = prestamoId;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaPlazoEntrega = fechaPlazoEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoPrestamoId = estadoPrestamoId;
        this.personaId = personaId;
        this.elementoId = elementoId;
    }


    public int getPrestamoId() { return prestamoId; }
    public void setPrestamoId(int prestamoId) { this.prestamoId = prestamoId; }

    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public Date getFechaPlazoEntrega() { return fechaPlazoEntrega; }
    public void setFechaPlazoEntrega(Date fechaPlazoEntrega) { this.fechaPlazoEntrega = fechaPlazoEntrega; }

    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public int getEstadoPrestamoId() { return estadoPrestamoId; }
    public void setEstadoPrestamoId(int estadoPrestamoId) { this.estadoPrestamoId = estadoPrestamoId; }

    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }

    public int getElementoId() { return elementoId; }
    public void setElementoId(int elementoId) { this.elementoId = elementoId; }


    public String toString() {
        return "Prestamo [ID=" + prestamoId + ", Fecha Solicitud=" + fechaSolicitud +
                ", Fecha Plazo Entrega=" + fechaPlazoEntrega + ", Fecha Devolución=" + fechaDevolucion +
                ", Estado Prestamo ID=" + estadoPrestamoId + ", Persona ID=" + personaId +
                ", Elemento ID=" + elementoId + "]";
    }
}