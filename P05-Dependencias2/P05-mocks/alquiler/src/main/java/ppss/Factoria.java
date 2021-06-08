package ppss;

public class Factoria {
    private static  IService servicio = null;
    public static void setService(IService s){
        servicio = s;
    }
    public static IService create(){
        if(servicio == null)
            return new Servicio();
        else
            return servicio;
    }
}
