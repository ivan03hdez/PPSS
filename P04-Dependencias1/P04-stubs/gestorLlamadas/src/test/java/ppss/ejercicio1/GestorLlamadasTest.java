package ppss.ejercicio1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest{
    /*@BeforeEach void inicializar(){

    }
    @BeforeAll void inicializarAntesdeTodo(){

    }*/
    @Test
    public void testCalculaConsumoC1() {
        GestorLlamadasTestable gestor = new GestorLlamadasTestable();
        int minutos = 10;
        gestor.setHora(15);
        double real = gestor.calculaConsumo(minutos);
        double esperado = 208;
        assertEquals(esperado,real);
    }
    @Test
    public void testCalculaConsumoC2() {
        GestorLlamadasTestable gestor = new GestorLlamadasTestable();
        int minutos = 10;
        gestor.setHora(22);
        double real = gestor.calculaConsumo(minutos);
        double esperado = 105;
        assertEquals(esperado,real);
    }
}
