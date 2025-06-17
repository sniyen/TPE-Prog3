import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {
    private ArrayList<Maquina> solucion;
    private int accesos;

    public Greedy(){
        this.accesos=0;
        this.solucion = new ArrayList<Maquina>();
    }
    
    public int getAccesos(){
        return accesos;
    }

    public ArrayList<Maquina> getSoluciones (){
        return (ArrayList<Maquina>)solucion.clone();
    }

    public int getPiezasCreadas (){
        int piezasCreadas = 0;
        for (Maquina m : solucion){
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
        //creamos una lista ordenada de maquinas de forma decreciente, y una variable piezas creadas iniciada en cero
        ArrayList<Maquina> listaOrdenada = this.ordenarArreglo(maquinas);
        int piezasCreadas=0;
        //también creamos el arreglo soluciones, donde se guardara la combinación de maquinas que den menor cantidad de encendidos
        this.solucion= new ArrayList<Maquina>();
        //Recorremos todas las maquinas una por una
        for (Maquina maquina : listaOrdenada) {
        /*cuando agarramos una maquina hacemos una pregunta, ¿genera una cantidad de piezas que me sirva?
        si esta respuesta es afirmativa, agrega la maquina tantas veces como se pueda sin pasarse de piezas.
        Cuando sumar las piezas de esa maquina implica pasarse del total, la iteración condicional se corta, y se analiza la proxima maquina 
        que al ser un arreglo ordenado va dando maquinas que generan cada vez menos piezas al avanzar) */
                while(maquina.getPiezas()+piezasCreadas <= piezasTotales){
                    // se incrementa la cantidad de accesos para analizar junto a la solucion
                    accesos++; 
                    // se añade la maquina a la solución
                    this.solucion.add(maquina); 
                    //agregamos las piezas creadas por la maquina actual para que el while vuelva a validar la condición
                    piezasCreadas=piezasCreadas+maquina.getPiezas();
                }
        }
    }
}