package ppss.ejercicio2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest{
    @Test
    void testcalculaConsumoC1() {
        calendarioDoble calendario = new calendarioDoble();
        calendario.setHora(15);
        GestorLlamadas gestor = new GestorLlamadas();
        double esperado=208.0f,real=gestor.calculaConsumo(10,calendario);
        assertEquals(esperado,real);
    }
    @Test
    void testcalculaConsumoC2() {
        calendarioDoble calendario = new calendarioDoble();
        calendario.setHora(22);
        GestorLlamadas gestor = new GestorLlamadas();
        double esperado=105.0f,real=gestor.calculaConsumo(10,calendario);
        assertEquals(esperado,real);
    }
}