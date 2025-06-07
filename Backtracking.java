import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {
    private ArrayList<Maquina> soluciones;
    private int minEncendidos = Integer.MAX_VALUE;
    private int accesos;
    public int getAccesos() {
        return accesos;
    }
    public void setAccesos(int accesos) {
        this.accesos = accesos;
    }
    public Backtracking(){
        this.soluciones= new ArrayList<Maquina>();
        this.accesos=0;
    }
    public ArrayList<Maquina> getListaMaquinas(HashMap<Integer, String> m, int piezasTot) {
        return null;
    }
    // private ArrayList<Maquina> getListaMaquinasRecursivo(HashMap<Integer, String> m, int piezasTot){
    //     return null;
    // }
    public void getListaMaquinas(ArrayList<Maquina> Maquinas, int piezasTot){
        int piezasCreadas=0;
        int puestasEnFuncionamiento=0;
        while(piezasCreadas!=piezasTot){
            puestasEnFuncionamiento++;
            soluciones.add(this.getMaquinaMasCercana(Maquinas, piezasTot, piezasCreadas));
            piezasCreadas= piezasCreadas + soluciones.get(puestasEnFuncionamiento-1).getPiezas();
        }
    }
    public Maquina getMaquinaMasCercana(ArrayList<Maquina> maquinas, int piezasTot, int piezasCreadas){
        Maquina solucion= new Maquina("null", 0);
        for (Maquina m : maquinas) {
            if(m.getPiezas()<piezasTot-piezasCreadas && m.getPiezas()>solucion.getPiezas()){
                solucion= m;
            }
        }
        return solucion;
    }
    
    //le da el estado inicial al backtracking para buscar soluciones
    public void resolver(List<Maquina> maquinas, int piezasTotales) {
        backtrack(maquinas, piezasTotales, 0, new ArrayList<Maquina>());
        System.out.println("Mejor solución con " + minEncendidos + " encendidos:");
        for (Maquina m : soluciones) {
            System.out.println(m);
    }
    }

    private void backtrack(List<Maquina> maquinas, int piezasRestantes, int encendidos, List<Maquina> actual) {
        accesos++;  
        System.out.println(accesos);                 
        if (piezasRestantes == 0) {
            if (encendidos < minEncendidos) {
                minEncendidos = encendidos;
                //ver si en lugar de crear uno nuevo se puede pisar la lista anterior
                //por eficiencia nada mas xd
                soluciones = new ArrayList<>(actual);
            }
            
        } else{
            for (Maquina m : maquinas) {
            if (piezasRestantes-m.getPiezas() >= 0 && encendidos < minEncendidos){
                actual.add(m);
                backtrack(maquinas, piezasRestantes - m.getPiezas(), encendidos + 1, actual);
                actual.removeLast();
            } 
            
            }
        }
    }
    }        


    public static void main(String[] args) {
       
        LectorMaquinas lector= new LectorMaquinas("Maquinas.txt");
        ArrayList<Maquina> maquinas= lector.getMaquinas();
        Integer piezasTotales= lector.getPiezasTotales();
        Fabrica fabrica= new Fabrica(new Backtracking());
        fabrica.añadirMaquinas(maquinas);
        fabrica.setPiezasTot(piezasTotales);
        fabrica.ejecutarBacktracking();
      
        System.out.println("accesos recursivos: "+ fabrica.getAccesos());
    }