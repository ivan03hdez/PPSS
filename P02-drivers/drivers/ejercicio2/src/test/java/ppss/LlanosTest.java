package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays; ///para usar Arrays.asList()

import static org.junit.jupiter.api.Assertions.*;
@Tag("noParametrizados")
class LlanosTest {
    Tramo expected,actual;
    ArrayList<Integer> entrada;
    @Test
    void C1A_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(3));
        expected = new Tramo();
        expected.setDuracion(0);
        expected.setOrigen(0);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        //assertEquals(expected,actual);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }
    @Test
    void C1B_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(-1));
        expected = new Tramo();
        expected.setDuracion(0);
        expected.setOrigen(0);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        //assertEquals(expected,actual);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }
    @Test
    void C2A_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(100,100));
        expected = new Tramo();
        expected.setDuracion(1);
        expected.setOrigen(0);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        //assertEquals(expected,actual);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }
    @Test
    void C2B_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(-1,-1,-1,-1));
        expected = new Tramo();
        expected.setDuracion(3);
        expected.setOrigen(0);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        //assertEquals(expected,actual);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }
    @Test
    void C3A_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(180,180,180));
        expected = new Tramo();
        expected.setDuracion(2);
        expected.setOrigen(0);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        //assertEquals(expected,actual);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }
    @Test
    void C3B_buscarTramoLlanoMasLargo() {
        entrada = new ArrayList(Arrays.asList(120,140,-10,-10,-10));
        expected = new Tramo();
        expected.setDuracion(2);
        expected.setOrigen(2);
        Llanos llanos = new Llanos();
        actual = llanos.buscarTramoLlanoMasLargo(entrada);
        assertAll(()->assertEquals(expected.getOrigen(),actual.getOrigen()),
                  ()->assertEquals(expected.getDuracion(),actual.getDuracion()));
    }



}