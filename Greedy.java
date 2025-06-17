import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {
    private ArrayList<Maquina> soluciones;
    private int accesos;

    public Greedy(){
        this.accesos=0;
        this.soluciones = new ArrayList<Maquina>();
    }
    
    public int getAccesos(){
        return accesos;
    }

    public ArrayList<Maquina> getSoluciones (){
        return (ArrayList<Maquina>)soluciones.clone();
    }

    public int getPiezasCreadas (){
        int piezasCreadas = 0;
        for (Maquina m : soluciones){
            piezasCreadas += m.getPiezas();
        }
        return piezasCreadas;
    }

    public ArrayList<Maquina> ordenarArreglo(ArrayList<Maquina> maquinas){
        //Se ordena el arreglo de manera descendente segun las piezas que produce cada maquina
        ArrayList<Maquina> retorno = new ArrayList<>(maquinas);
        Collections.sort(retorno, new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                return Integer.compare(m2.getPiezas(), m1.getPiezas());
            }
        });
        return retorno;
    }


    // ordenar el arreglo, agarrar el primero y ver cuantas veces nos sirve, cuando deja de servir, movemos el cursor al siguiente elemento de la lista

    /* Explicacion de estrategia:
     * -Candidatos:
     *      Los posibles candidatos seran las maquinas disponibles para ser encendidas.      
     *
     * -Estrategia de seleccion de candidatos:
     *      Se tiene una lista de maquinas disponibles, las cuales se ordenan de manera descendente segun el numero
     *      de piezas que producen. Se selecciona la maquina con mayor capacidad siempre que no supere
     *      la cantidad total de piezas a producir. Mientras la suma de las piezas producidas mas las piezas que la 
     *      maquina es capaz de producir no supere la cantidad total de las piezas requeridas, se sigue encendiendo la 
     *      misma maquina. Cuando esta deje de ser un candidato valido, se intenta seguir con la proxima maquina en la lista.
     * 
     * - Consideraciones respecto a encontrar o no solución:
     *      Esta estrategia no garantiza que se encuentre la solucion optima, ya que puede que no se produzca la cantidad exacta
     *      de piezas requeridas, si no un numero aproximado (siempre sin excederse).
     */

    public void resolver(ArrayList<Maquina> maquinas, int piezasTotales){
        ArrayList<Maquina> listaOrdenada = this.ordenarArreglo(maquinas);
        int piezasCreadas=0;
        /* ArrayList<Maquina> soluciones= new ArrayList<Maquina>(); */
        for (Maquina maquina : listaOrdenada) {
            while(maquina.getPiezas()+piezasCreadas <= piezasTotales){
                accesos++;
                this.soluciones.add(maquina);
                piezasCreadas=piezasCreadas+maquina.getPiezas();
            }
        }
    }


}