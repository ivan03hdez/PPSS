package ppss;

import java.util.Objects;

public class Tramo {
    private int origen;
    private int duracion;
    public Tramo(){}
    public Tramo(int or,int dur){
        this.origen = or;
        this.duracion = dur;
    }
    public int getOrigen() {
        return origen;
    }
    public int getDuracion(){
        return duracion;
    }

    public void setOrigen(int origen_tramoMax) {
        this.origen = origen_tramoMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tramo tramo = (Tramo) o;
        return origen == tramo.origen && duracion == tramo.duracion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origen, duracion);
    }

    public void setDuracion(int longitudMax_tramo) {
        this.duracion = longitudMax_tramo;
    }
}
