import java.util.HashMap;

public class Fabrica {
    private HashMap<Integer ,String> maquinas;
    private int piezasTot;
    private Backtracking backtracking;

    public Fabrica(Backtracking backtracking) {
        this.maquinas = this.leerTexto();
        this.piezasTot = this.sacarPiezasTot();
        this.backtracking = backtracking;
    }

    private int sacarPiezasTot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sacarPiezasTot'");
    }

    private HashMap<Integer, String> leerTexto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leerTexto'");
    }
     public int getPiezasTot() {
        return piezasTot;
    }

    public void setPiezasTot(int piezasTot) {
        this.piezasTot = piezasTot;
    }
}