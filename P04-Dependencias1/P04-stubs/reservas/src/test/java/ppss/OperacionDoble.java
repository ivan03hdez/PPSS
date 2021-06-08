package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionDoble extends Operacion{
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(socio.equals("Luis")){
            if(isbn.equals("11111") || isbn.equals("22222")){

            }
            else throw new IsbnInvalidoException("Isbn Invalido");
        }
        else throw new SocioInvalidoException("Socio invalido");
    }
}
