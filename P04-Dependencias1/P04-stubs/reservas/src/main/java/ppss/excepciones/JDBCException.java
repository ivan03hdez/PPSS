package ppss.excepciones;

public class JDBCException extends Exception{
    private String message;
    public JDBCException(String message){
        this.message = message;
    }
}
