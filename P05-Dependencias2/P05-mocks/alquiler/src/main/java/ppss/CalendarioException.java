package ppss;

public class CalendarioException extends Exception{
    private String mensaje;
    public CalendarioException(String message){
        mensaje = message;
    }
    public String getMessage(){
        return mensaje;
    }
}
