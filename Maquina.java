public class Maquina {
    private String id;
    private Integer piezas;
    private boolean estado;
    
    public Maquina(String id, int piezas) {
        this.id = id;
        this.piezas = piezas;
        this.estado = false;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getPiezas() {
        return piezas;
    }
    
    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }
    
    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String toString(){
        return (this.getId() + " - " + this.getPiezas() + " pieza/s" );
    }
   
}