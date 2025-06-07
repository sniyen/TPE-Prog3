import java.util.ArrayList;

public class Greedy {
    private int accesos;
    public Greedy(){
        this.accesos=0;
    }
    // ordenar el arreglo, agarrar el primero y ver cuantas veces nos sirve, cuando deja de servir, movemos el cursor al siguiente elemento de la lista

    public void resolver(ArrayList<Maquina> maquinas, int piezasTotales){
       // ArrayList<Maquina> listaOrdenada= this.ordenarArreglo(maquinas);
        int piezasCreadas=0;
        ArrayList<Maquina> solucion= new ArrayList<Maquina>();
        for (Maquina maquina : maquinas) {
                while(maquina.getPiezas()+piezasCreadas <= piezasTotales){
                    accesos++;
                    solucion.add(maquina);
                    piezasCreadas=piezasCreadas+maquina.getPiezas();
                    System.out.println(maquina);
                }
        }
    }

/*     public ArrayList<Maquina> ordenarArreglo(ArrayList<Maquina> maquinas){
        return null;
    }
*/

public int getAccesos(){
    return accesos;
}

public static void main(String[] args) {
            LectorMaquinas lector= new LectorMaquinas("Maquinas.txt");
        ArrayList<Maquina> maquinas= lector.getMaquinas();
        Integer piezasTotales= lector.getPiezasTotales();
        Fabrica fabrica= new Fabrica(new Greedy());
        fabrica.añadirMaquinas(maquinas);
        fabrica.setPiezasTot(piezasTotales);
        fabrica.ejecutarGreedy();
        System.out.println(fabrica.getAccesosGreedy());
    }
}