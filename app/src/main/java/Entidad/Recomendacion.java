package Entidad;

public class Recomendacion {
    private String recomendado;
    private String direccion;
    public Recomendacion() {
        // Constructor vacío requerido por algunas bibliotecas de serialización
    }

    public Recomendacion(String recomendado, String direccion) {
        this.recomendado = recomendado;
        this.direccion = direccion;
    }

    public String getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(String recomendado) {
        this.recomendado = recomendado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
