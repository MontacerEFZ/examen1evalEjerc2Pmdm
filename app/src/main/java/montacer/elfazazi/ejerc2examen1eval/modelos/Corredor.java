package montacer.elfazazi.ejerc2examen1eval.modelos;

import java.io.Serializable;

public class Corredor implements Serializable {
    private String nombre;
    private float tiempo;
    private float distancia;

    public Corredor() {
    }

    public Corredor(String nombre, float tiempo, float distancia) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                ", distancia=" + distancia +
                '}';
    }
}
