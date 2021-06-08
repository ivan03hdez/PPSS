package ppss;


import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;

class GestorLlamadasTest {
    IMocksControl ctrl;
    GestorLlamadas g;
    /*/programamos las expectativas
    CADA vez que nuestro SUT invoque a servicio4 con un 12, devolverá 25
    independientemente del número de invocaciones porque se usa andStubReturn
    -----EasyMock.expect(dep.servicio4(12)).andStubReturn(25);
    si nuestro SUT invoca a servicio4 con cualquier otro valor, devolverá 30
    independientemente del número de veces que se invoque
    ------EasyMock.expect(dep.servicio4(EasyMock.not(EasyMock.eq(12)))).andStubReturn(30);
    si nuestro SUT invoca servicio5(8) siempre -> el stub devolverá null todas las veces
    null es el valor por defecto para los Strings (Objects)
    si nuestra SUT no invoca nunca servicio5(3), el test NO fallará
    -----EasyMock.expect(dep.servicio5(3)).andStubReturn("pepe");*/
    @Test
    void testCalculaConsumoC1() {
        double esperado = 457.6;
        ctrl = EasyMock.createStrictControl();
        g = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock(ctrl);
        Premio c = ctrl.createMock(Premio.class);
        EasyMock.expect(g.getCalendario()).andReturn(c);
        EasyMock.expect(c.getHoraActual()).andReturn(10);
        ctrl.replay();
        assertEquals(esperado,g.calculaConsumo(22));
        ctrl.verify();

    }
    @Test
    void testCalculaConsumoC2() {
        double esperado = 136.5;
        ctrl = EasyMock.createStrictControl();
        g = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock(ctrl);
        Premio c = ctrl.createMock(Premio.class);
        EasyMock.expect(g.getCalendario()).andReturn(c);
        EasyMock.expect(c.getHoraActual()).andReturn(21);
        ctrl.replay();
        assertEquals(esperado,g.calculaConsumo(13));
        ctrl.verify();
    }
}