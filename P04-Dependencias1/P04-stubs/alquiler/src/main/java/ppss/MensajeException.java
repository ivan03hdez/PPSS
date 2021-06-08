package ppss;

public class MensajeException extends Exception {
    private String mensaje;
    public MensajeException(String observ){
        mensaje = observ;
    }
}
