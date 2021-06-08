package ppss;

import java.util.ArrayList;

public class AlquilaCochesTestable extends AlquilaCoches{
    @Override
    public IService getServicio(){
        return new ServiceDoble();
    }
    public AlquilaCochesTestable(ArrayList<Boolean> f, ArrayList<Integer> d){
        super();
        calendario = new CalendarioDoble(f,d); //esto actualiza el atributo calendario para apuntarlo al doble
    }
}
