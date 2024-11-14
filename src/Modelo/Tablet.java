package Modelo;

public class Tablet {
    private int elementoId;
    private int tabletId;
    private double tamanoPantalla;
    private String nroSerie;
    private String nroPlaca;
    private int marcaId;


    public Tablet(int elementoId, int tabletId, double tamanoPantalla, String nroSerie, String nroPlaca, int marcaId) {
        this.elementoId = elementoId;
        this.tabletId = tabletId;
        this.tamanoPantalla = tamanoPantalla;
        this.nroSerie = nroSerie;
        this.nroPlaca = nroPlaca;
        this.marcaId = marcaId;
    }


    public int getElementoId() { return elementoId; }
    public void setElementoId(int elementoId) { this.elementoId = elementoId; }

    public int getTabletId() { return tabletId; }
    public void setTabletId(int tabletId) { this.tabletId = tabletId; }

    public double getTamanoPantalla() { return tamanoPantalla; }
    public void setTamanoPantalla(double tamanoPantalla) { this.tamanoPantalla = tamanoPantalla; }

    public String getNroSerie() { return nroSerie; }
    public void setNroSerie(String nroSerie) { this.nroSerie = nroSerie; }

    public String getNroPlaca() { return nroPlaca; }
    public void setNroPlaca(String nroPlaca) { this.nroPlaca = nroPlaca; }

    public int getMarcaId() { return marcaId; }
    public void setMarcaId(int marcaId) { this.marcaId = marcaId; }


    public String toString() {
        return "Tablet [ID=" + tabletId + ", Tamaño Pantalla=" + tamanoPantalla +
                ", Número Serie=" + nroSerie + ", Número Placa=" + nroPlaca + ", Marca ID=" + marcaId + "]";
    }
}