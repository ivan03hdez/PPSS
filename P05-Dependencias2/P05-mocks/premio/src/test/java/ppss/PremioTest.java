package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {
    IMocksControl ctrl;
    Premio p;
    ClienteWebService c;
    @Test
    public void testC1(){
        String esperado = "Premiado con entrada final champions";
        float numAleat = 0.07f;
        ctrl = EasyMock.createStrictControl();
        p = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createMock(ctrl);
        c = ctrl.createMock(ClienteWebService.class);
        EasyMock.expect(p.generaNumero()).andReturn(numAleat).times(1);
        p.cliente = c; ///podemos hacerlo desde aqui por que es publico el atributo sino tendriamos que refactorizar
        assertDoesNotThrow(() -> EasyMock.expect(c.obtenerPremio()).andReturn("entrada final champions"));
        ctrl.replay();
        String actual = p.compruebaPremio();
        assertEquals(esperado,actual);
        ctrl.verify();
    }
    //////////NO HE CREADO EL IMockControl en esta parte de los test para ver como funcionaban otras partes de la libreria sin IMockControl///////
    @Test
    public void testC2(){
        float numAleat = 0.03f;
        Premio p = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createStrictMock();
        ClienteWebService c = EasyMock.createStrictMock(ClienteWebService.class);
        p.cliente = c; ///podemos hacerlo desde aqui por que es publico el atributo sino tendriamos que refactorizar
        assertDoesNotThrow(() -> EasyMock.expect(c.obtenerPremio()).andThrow(new ClienteWebServiceException("error")));
        EasyMock.expect(p.generaNumero()).andReturn(numAleat);
        EasyMock.replay(c,p);//SI no creamos un IMockControl, el EasyMock.verify lo único que hace es verificar que se han llamado a las expectativas, y cuantas veces, pero no el orden entre expectativas
        String esperado = "No se ha podido obtener el premio";
        String actual = p.compruebaPremio();
        EasyMock.verify(c,p);
        assertEquals(esperado,actual);
    }
    @Test
    public void testC3(){
        float numAleat = 0.3f;
        Premio p = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createStrictMock();
        EasyMock.expect(p.generaNumero()).andReturn(numAleat);
        EasyMock.replay(p);
        String esperado = "Sin premio";
        String actual = p.compruebaPremio();
        EasyMock.verify(p);
        assertEquals(esperado,actual);
    }
}
