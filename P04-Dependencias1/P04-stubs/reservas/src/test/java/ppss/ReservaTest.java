package ppss;

import ppss.excepciones.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class ReservaTest {

    @Test
    void testRealizarReservaC1() throws Exception {
        ReservaTestable r = new ReservaTestable(new OperacionDoble());
        String isbns[] = new String[] {"11111"};
        ReservaException e = assertThrows(ReservaException.class,() -> r.realizaReserva("xxxx","xxxx","Luis",isbns));
        assertEquals("ERROR de permisos; ",e.getMessage());
    }
    @Test
    void testRealizarReservaC2() throws Exception{
        ReservaTestable r = new ReservaTestable(new OperacionDoble());
        String isbns[] = new String[] {"11111","22222"};
        assertDoesNotThrow(() -> r.realizaReserva("ppss","ppss","Luis",isbns));
    }
    @Test
    void testRealizarReservaC3() throws Exception{
        ReservaTestable r = new ReservaTestable(new OperacionDoble());
        String isbns[] = new String[] {"33333"};
        ReservaException e = assertThrows(ReservaException.class,() -> r.realizaReserva("ppss","ppss","Luis",isbns));
        assertEquals("ISBN invalido:33333; ",e.getMessage());
    }@Test
    void testRealizarReservaC4() throws Exception{
        ReservaTestable r = new ReservaTestable(new OperacionDoble());
        String isbns[] = new String[] {"11111"};
        ReservaException e =assertThrows(ReservaException.class,() -> r.realizaReserva("ppss","ppss","Pepe",isbns));
        assertEquals("SOCIO invalido; ",e.getMessage());
    }
    @Test
    void testRealizarReservaC5() throws Exception{
        ReservaTestable r = new ReservaTestable(new OperacionDobleBBDD());
        String isbns[] = new String[] {"11111"};
        ReservaException e = assertThrows(ReservaException.class,() -> r.realizaReserva("ppss","ppss","Luis",isbns));
        assertEquals("CONEXION invalida; ",e.getMessage());
    }

}