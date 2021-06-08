package ppss.ejercicio1;

public class GestorLlamadasTestable extends GestorLlamadas {
    private int hora = 0;
    public void setHora(int hora){this.hora = hora;}
    @Override
    public int getHoraActual() {
        return hora;
    }
}
