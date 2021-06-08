package ppss;

public class ReservaTestable extends Reserva{
    private IOperacionBO op;
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
            return login.equals("ppss") && password.equals("ppss");
    }
    public ReservaTestable(IOperacionBO b){
        op = b;
    }
    @Override
    public IOperacionBO getOperacion() {
        return op;
    }
}
