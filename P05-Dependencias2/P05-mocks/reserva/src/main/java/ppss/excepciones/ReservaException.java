package ppss.excepciones;

public class ReservaException extends Exception{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ReservaException(String message){
        this.message = message;
    }
}
