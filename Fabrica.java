import java.util.ArrayList;
import java.util.HashMap;

public class Fabrica {
    private int piezasTot;
    private Backtracking backtracking;
    private Greedy greedy;
    private ArrayList<Maquina> listaMaquinas;

    public Fabrica() {
        this.backtracking = new Backtracking();
        this.greedy = new Greedy();
        this.listaMaquinas = new ArrayList<>();
    }

    public int getPiezasTot() {
        return piezasTot;
    }

    public void setPiezasTot(int piezasTot) {
        this.piezasTot = piezasTot;
    }

    public void agregarMaquinas(ArrayList<Maquina> m){
        this.listaMaquinas.addAll(m);
    }

    public ArrayList<Maquina> getMaquinasDisponibles (){
        return (ArrayList<Maquina>)listaMaquinas.clone();
    }

    // metodos de backtracking

    public void ejecutarBacktracking(){
        this.backtracking.resolver(listaMaquinas, piezasTot);
    }

    public ArrayList<Maquina> getMaquinasSolucionBacktracking (){
        return this.backtracking.getSoluciones();
    }

    public int getMinEncendidosBactracking(){
        return this.backtracking.getMinEncendidos();
    }

    public int getAccesosBacktracking(){
        return this.backtracking.getAccesos();
    }

    public int getPiezasCreadasBacktracking (){
        return this.backtracking.getPiezasCreadas();
    }

    // metodos de greedy
    public void ejecutarGreedy(){
        this.greedy.resolver(listaMaquinas, piezasTot);
    }

    public HashMap<Maquina, Integer> getMaquinasSolucionGreedy (){
        return this.greedy.getSoluciones();
    }

    public int getAccesosGreedy(){
        return this.greedy.getAccesos();
    }

    public int getPiezasCreadasGreedy (){
        return this.greedy.getPiezasCreadas();
    }

}