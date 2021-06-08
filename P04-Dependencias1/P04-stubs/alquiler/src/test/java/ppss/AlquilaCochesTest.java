package ppss;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    @Test
    void TestcalculaPrecioC1(){
       try {
           ArrayList<Integer> dias = new ArrayList<>();
           dias.add(18);dias.add(19);dias.add(20);dias.add(21);dias.add(22);dias.add(23);dias.add(22);dias.add(24);dias.add(25);dias.add(26);
           ArrayList<Boolean> festivos = new ArrayList<>();
           festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);festivos.add(false);
           AlquilaCochesTestable alquila = new AlquilaCochesTestable(festivos,dias);
           Ticket real;
           real = alquila.calculaPrecio(TipoCoche.DEPORTIVO, LocalDate.of(2021, Month.MAY, 18), 10);
           assertNotNull(real);
           Ticket esperado = new Ticket();
           esperado.setPrecio_final(75);
           assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
       }
       catch(MensajeException e){
           e.getMessage();
       }

    }

}