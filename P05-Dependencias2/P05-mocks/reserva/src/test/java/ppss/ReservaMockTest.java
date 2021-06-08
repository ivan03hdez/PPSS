package ppss;


import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaMockTest {
    String login,password,socio;
    Usuario u;
    String[] isbns;
    @Test
    void testRealizaReservaC1(){
        login = "xxxx";
        password = "xxxx";
        socio = "pepe";
        u = Usuario.BIBLIOTECARIO;
        isbns = new String[]{"11111"};
        IMocksControl ctrl = EasyMock.createStrictControl();
        IOperacionBO o = ctrl.createMock(IOperacionBO.class);
        Reserva v = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").createMock(ctrl);
        EasyMock.expect(v.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn( (login.equals("ppss") && password.equals("ppss")) );
        //EasyMock.expect(v.getOperacion()).andReturn(o);
        /*for(String isbn:isbns)
            assertThrows(SocioInvalidoException.class,EasyMock.expect(o.operacionReserva("pepe",isbn)).andThrows(new SocioInvalidoException("")));*/
        ctrl.replay();
        ReservaException error = assertThrows(ReservaException.class,() -> v.realizaReserva(login,password,socio,isbns));
        assertEquals("ERROR de permisos; ",error.getMessage());
        ctrl.verify();
    }
    @Test
    void testRealizaReservaC2(){
        login = "ppss";
        password = "ppss";
        socio = "pepe";
        u = Usuario.BIBLIOTECARIO;
        isbns = new String[]{"22222","33333"};
        IMocksControl ctrl = EasyMock.createStrictControl();
        FactoriaBOs f = ctrl.createMock(FactoriaBOs.class);
        IOperacionBO o = ctrl.createMock(IOperacionBO.class);
        Reserva v = EasyMock.partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").createMock(ctrl);
        EasyMock.expect(v.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn( (login.equals("ppss") && password.equals("ppss")) );
        EasyMock.expect(v.getFactoria()).andReturn(f);
        EasyMock.expect(f.getOperacionBO()).andReturn(o);
        for(String isbn:isbns) {///automatizacion de las expectativas del metodo void operacionReserva
            assertDoesNotThrow(() -> o.operacionReserva(socio, isbn));
            if (socio.equals("pepe") && (isbn.equals("22222") || isbn.equals("33333")))
                expectLastCall();///para este tipo de metodos void, no funciona el metodo expect() hay que usar expectLastCall();
            else if (socio.equals("luis")) {
                expectLastCall().andThrow(new SocioInvalidoException(""));
            } else {
                expectLastCall().andThrow(new IsbnInvalidoException(""));
            }
        }
        ctrl.replay();
        ReservaException error = assertThrows(ReservaException.class,() -> v.realizaReserva(login,password,socio,isbns));
        assertEquals("ERROR de permisos;",error.getMessage());
        ctrl.verify();
    }
}