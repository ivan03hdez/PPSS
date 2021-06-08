package ppss.excepciones;

public class SocioInvalidoException extends Exception{
    private String message;
    public SocioInvalidoException(String message){
        this.message = message;
    }
}
