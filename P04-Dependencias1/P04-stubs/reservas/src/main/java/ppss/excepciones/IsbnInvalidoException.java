package ppss.excepciones;

public class IsbnInvalidoException extends Exception{
    private String message;
    public IsbnInvalidoException(String message){
        this.message = message;
    }
}
