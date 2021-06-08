package ppss.ejercicio2;

public class calendarioDoble extends Calendario{
    private int hora;
    public void setHora(int hor){hora = hor;}
    @Override
    public int getHoraActual() {
        return hora;
    }
}
