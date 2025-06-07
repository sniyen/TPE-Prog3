import java.util.ArrayList;

public class Greedy {
    private int accesos;
    public Greedy(){
        this.accesos=0;
    }
    // ordenar el arreglo, agarrar el primero y ver cuantas veces nos sirve, cuando deja de servir, movemos el cursor al siguiente elemento de la lista

    public void resolver(ArrayList<Maquina> maquinas, int piezasTotales){
        //creamos una lista ordenada de maquinas de forma decreciente, y una variable piezas creadas iniciada en cero
       // ArrayList<Maquina> listaOrdenada= this.ordenarArreglo(maquinas); 
        int piezasCreadas=0;
        //también creamos el arreglo solucion, donde se van a guardar la combinación de maquinas que den menor cantidad de encendidos
        ArrayList<Maquina> solucion= new ArrayList<Maquina>();
        //Recorremos todas las maquinas una por una
        for (Maquina maquina : maquinas) {
            //cuando hagarramos una maquina hacemos una pregunta, ¿genera una cantidad de piezas que me sirva?
            //si esta respuesta es afirmativa, agrega la maquina tantas veces como se pueda sin pasarse de piezas
            //cuando sumar las piezas de esa maquina implica pasarse del total, la iteración condicional se corta, y se analiza la proxima maquina 
            //(que al ser un arreglo ordenado va dando maquinas que generan cada vez menos piezas al avanzar)
                while(maquina.getPiezas()+piezasCreadas <= piezasTotales){
                    accesos++; // se incrementa la cantidad de accesos para analizar junto a la solucion
                    solucion.add(maquina);// se añade la maquina a la solución
                    piezasCreadas=piezasCreadas+maquina.getPiezas();//añadimos las piezas creadas por la maquina actual para que el while vuelva a validar la condición
                    System.out.println(maquina); // y mostramos las maquinas que estan dentro de esta solucion
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