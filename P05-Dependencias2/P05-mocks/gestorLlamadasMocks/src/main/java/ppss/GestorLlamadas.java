package ppss;

public class GestorLlamadas {
    static double TARIFA_NOCTURNA=10.5;
    static double TARIFA_DIURNA=20.8;

    public Premio getCalendario() {
        Premio c = new Premio();
        return c;
    }

    public double calculaConsumo(int minutos) {
        Premio c = getCalendario();
        int hora = c.getHoraActual();
        if(hora < 8 || hora > 20) {
            return minutos * TARIFA_NOCTURNA;
        } else {
            return minutos * TARIFA_DIURNA;
        }
    }
}
