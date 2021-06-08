package ppss;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.spi.CalendarDataProvider;

public class CalendarioDoble extends Calendario{
    private ArrayList<Boolean> esFestivo;
    private ArrayList<Integer> dias;
    protected Calendario calendario;
    public CalendarioDoble(ArrayList<Boolean> f,ArrayList<Integer> d){
        esFestivo = f;
        dias = d;
    }
    @Override
    public boolean es_festivo(LocalDate fecha){
        int i = 0;
        boolean esFest = false;
        for(int dia: dias){
            if (fecha.getDayOfMonth() == dia) {
                esFest = esFestivo.get(i);
                break;
            }
            else i++;
        }
        return esFest;
    }

}
