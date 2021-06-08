package ppss;
import ppss.excepciones.*;

import java.util.ArrayList;

public class Reserva {

    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public IOperacionBO getOperacion(){
        return new Operacion();
    }
    public void realizaReserva(String login, String password,
                               String socio, String [] isbns) throws Exception {

        ArrayList<String> errores = new ArrayList();
        if(!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) {////////////////primera dependencia externa////////////////////
            errores.add("ERROR de permisos");
        } else {
            IOperacionBO io = getOperacion();//////////////REFACTORIZAR de new Operacion() a getOperacion()///////////////////
            try {
                for(String isbn: isbns) {
                    try {
                        io.operacionReserva(socio, isbn);////////////////segunda dependencia externa///////////////////////////
                    } catch (IsbnInvalidoException iie) {
                        errores.add("ISBN invalido" + ":" + isbn);////catch se ejecuta siempre, pero cuando no sabe que tipo de error le viene hace un throw de ese error
                    }
                }
            } catch (SocioInvalidoException sie) {
                errores.add("SOCIO invalido");
            } catch (JDBCException je) {
                errores.add("CONEXION invalida");
            }
        }
        if (errores.size() > 0) {
            String mensajeError = "";
            for(String error: errores) {
                mensajeError += error + "; ";
            }
            throw new ReservaException(mensajeError);
        }
    }
}
