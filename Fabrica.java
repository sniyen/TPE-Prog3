import java.util.ArrayList;
// import java.util.HashMap;

public class Fabrica {
    // private HashMap<Integer ,String> maquinas;
    private int piezasTot;
    private Backtracking backtracking;
    private ArrayList<Maquina> listaMaquinas;

    public Fabrica(Backtracking backtracking) {
        // this.maquinas = this.leerTexto();
        // this.piezasTot = this.sacarPiezasTot();
        this.backtracking = backtracking;
        this.listaMaquinas= new ArrayList<Maquina>();
    }

    // private int sacarPiezasTot() {
    //     // TODO Auto-generated method stub
    //     HashMap<Integer, String> piezas=this.leerTexto();
        
    //     throw new UnsupportedOperationException("Unimplemented method 'sacarPiezasTot'");
    // }

    // private HashMap<Integer, String> leerTexto() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'leerTexto'");
    // }
     public int getPiezasTot() {
        return piezasTot;
    }

    public void setPiezasTot(int piezasTot) {
        this.piezasTot = piezasTot;
    }

    public void añadirMaquina(Maquina m){
        this.listaMaquinas.add(m);
    }
    public void añadirMaquinas(ArrayList<Maquina> m){
        this.listaMaquinas.addAll(m);
    }
    public void ejecutarBacktracking(){
        this.backtracking.resolver(listaMaquinas, piezasTot);
    }
    
public int getAccesos(){
    return this.backtracking.getAccesos();
}
}