import java.util.ArrayList;
// import java.util.HashMap;

public class Fabrica {
    // private HashMap<Integer ,String> maquinas;
    private int piezasTot;
    private Backtracking backtracking;
    private Greedy greedy;
    private ArrayList<Maquina> listaMaquinas;

    public Fabrica(Backtracking backtracking) {
        // this.maquinas = this.leerTexto();
        // this.piezasTot = this.sacarPiezasTot();
        this.backtracking = backtracking;
        this.listaMaquinas= new ArrayList<Maquina>();
    }
    public Fabrica(Greedy greedy) {
        // this.maquinas = this.leerTexto();
        // this.piezasTot = this.sacarPiezasTot();
        this.greedy =greedy;
        this.listaMaquinas= new ArrayList<Maquina>();
    }
    // private int sacarPiezasTot() {
    //     
    //     HashMap<Integer, String> piezas=this.leerTexto();
        
    //     throw new UnsupportedOperationException("Unimplemented method 'sacarPiezasTot'");
    // }

    // private HashMap<Integer, String> leerTexto() {
    //     
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

    public void ejecutarGreedy(){
        this.greedy.resolver(listaMaquinas, piezasTot);
    }
    
    public int getAccesosBacktracking(){
    return this.backtracking.getAccesos();
    }
    public int getAccesosGreedy(){
        return this.greedy.getAccesos();
    }
    public static void main(String[] args) {
        LectorMaquinas lector =  new LectorMaquinas("Maquinas.txt");
        Fabrica fabrica= new Fabrica(new Backtracking());
        fabrica.añadirMaquinas(lector.getMaquinas());
        fabrica.setPiezasTot(lector.getPiezasTotales());
        fabrica.ejecutarBacktracking();
    }
}