package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.easymock.EasyMock.anyFloat;
import static org.easymock.EasyMock.anyObject;
import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {

    @Test
    void testCalculaPrecioC1() {
        TipoCoche coche = TipoCoche.TURISMO;
        IMocksControl ctrl = EasyMock.createStrictControl();
        AlquilaCoches a = new AlquilaCoches();
        Calendario c = ctrl.createMock(Calendario.class);
        Servicio s = ctrl.createMock(Servicio.class);
        a.calendario = c;
        EasyMock.expect(s.consultaPrecio(anyObject())).andReturn(10.0f);
        assertDoesNotThrow(() -> EasyMock.expect(c.es_festivo(anyObject())).andReturn(false).times(10));
        ctrl.replay();
        LocalDate fechaInicio = LocalDate.of(2021, Month.MAY, 18);
        int dias = 10;
        Factoria.setService(s);
        assertDoesNotThrow(()-> assertEquals(75,a.calculaPrecio(coche,fechaInicio,dias).getPrecio_final()));
        ctrl.verify();
    }
    @Test
    void testCalculaPrecioC2() {
        TipoCoche coche = TipoCoche.TURISMO;
        IMocksControl ctrl = EasyMock.createStrictControl();
        AlquilaCoches a = new AlquilaCoches();
        Calendario c = ctrl.createMock(Calendario.class);
        Servicio s = ctrl.createMock(Servicio.class);
        a.calendario = c;
        EasyMock.expect(s.consultaPrecio(anyObject())).andReturn(10.0f);
        assertDoesNotThrow(() -> EasyMock.expect(c.es_festivo(anyObject())).andReturn(false).andReturn(true).andReturn(false).times(4).andReturn(true));
        ctrl.replay();
        LocalDate fechaInicio = LocalDate.of(2021, Month.JUNE, 19);
        int dias = 7;
        Factoria.setService(s);
        assertDoesNotThrow(()-> assertEquals(62.5,a.calculaPrecio(coche,fechaInicio,dias).getPrecio_final()));
        ctrl.verify();
    }
    @Test
    void testCalculaPrecioC3() {
        TipoCoche coche = TipoCoche.TURISMO;
        IMocksControl ctrl = EasyMock.createStrictControl();
        AlquilaCoches a = new AlquilaCoches();
        Calendario c = ctrl.createMock(Calendario.class);
        Servicio s = ctrl.createMock(Servicio.class);
        a.calendario = c;
        EasyMock.expect(s.consultaPrecio(anyObject())).andReturn(10.0f);
        assertDoesNotThrow(() -> EasyMock.expect(c.es_festivo(anyObject())).andReturn(false).andThrow(new CalendarioException("Error en dia: 18-04-2021")).andReturn(false).times(2).andThrow(new CalendarioException("")).times(2).andReturn(false).anyTimes());
        ctrl.replay();
        LocalDate fechaInicio = LocalDate.of(2021, Month.APRIL, 17);
        int dias = 8;
        Factoria.setService(s);
        MensajeException error = assertThrows(MensajeException.class,() -> a.calculaPrecio(coche,fechaInicio,dias).getPrecio_final());
        String expected = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";
        assertEquals(expected,error.getMessage());
        ctrl.verify();
    }
    @Test
    void testCalculaPrecioC3A() { ////NO PODEMOS USAR anyObject() en expectativas
        TipoCoche coche = TipoCoche.TURISMO;
        int dias = 8;
        IMocksControl ctrl = EasyMock.createStrictControl();
        AlquilaCoches a = new AlquilaCoches();
        Calendario c = ctrl.createMock(Calendario.class);
        Servicio s = ctrl.createMock(Servicio.class);
        a.calendario = c;
        EasyMock.expect(s.consultaPrecio(anyObject())).andReturn(10.0f);
        for(int i = 0; i < dias;i++){////Le pasamos a .es_festivo los parametros exactos
            LocalDate local = LocalDate.of(2021,Month.APRIL,17 + i);
            if (local.getDayOfMonth() == 18 || local.getDayOfMonth() == 21 || local.getDayOfMonth() == 22)
                assertDoesNotThrow(() -> EasyMock.expect(c.es_festivo(local)).andThrow(new CalendarioException("Error en dia: " + local.toString())));
            else
                assertDoesNotThrow(() -> EasyMock.expect(c.es_festivo(local)).andReturn(false));

        }
        ctrl.replay();
        LocalDate fechaInicio = LocalDate.of(2021, Month.APRIL, 17);
        Factoria.setService(s);
        MensajeException error = assertThrows(MensajeException.class,() -> a.calculaPrecio(coche,fechaInicio,dias).getPrecio_final());
        String expected = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";
        assertEquals(expected,error.getMessage());
        ctrl.verify();
    }
}