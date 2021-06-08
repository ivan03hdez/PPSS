package ppss;


public class Ticket {
    private float precio_final;
    public float getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(float precio_final) {
        this.precio_final = precio_final;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Float.compare(ticket.precio_final, precio_final) == 0;
    }
}
