import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
public class Greedy {
    //este Map sera una maquina y la cantidad de encendidos.
    private Map <Maquina, Integer> solucion;
    //cantidad de estados generados
    private int accesos;
    
    public Greedy() {
        this.solucion= new HashMap<>();
        this.accesos = 0;
    }

    public int getAccesos(){
        return accesos;
    }

    public HashMap<Maquina, Integer> getSoluciones (){        
        return new HashMap<>(solucion);
    }

    public int getPiezasCreadas (){
        int piezasCreadas = 0;
        int encendidos = 0;
        for (Maquina m : solucion.keySet()){
            encendidos = solucion.get(m);
            piezasCreadas += m.getPiezas()*encendidos;
        }
        return piezasCreadas;
    }

    public ArrayList<Maquina> ordenarMaquinas (ArrayList<Maquina> maquinas){
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
     *      la cantidad total de piezas a producir. Se calcula cuantas veces se puede encender la misma debido a que no hay restricciones
     *      sobre usar una maquina de manera consecutiva, este resultado se guarda en el hashmap solucion siendo una estructura Maquina-Encendidos.
     *      Cuando la maquina deja de ser un candidato valido, se intenta seguir con la proxima maquina en la lista.
     * 
     * - Consideraciones respecto a encontrar o no solución:
     *      Esta estrategia no garantiza que se encuentre la solucion optima, ya que puede que no se produzca la cantidad exacta
     *      de piezas requeridas, si no un numero aproximado (siempre sin excederse).
     */

    public void resolver(ArrayList<Maquina> maquinas, int piezasTotales){
        //creamos una lista ordenada de maquinas de forma decreciente, y una variable piezas creadas iniciada en cero
        ArrayList<Maquina> listaOrdenada = this.ordenarMaquinas(maquinas);
        int piezasCreadas = 0;
        //también creamos el Hashmap soluciones, donde se guardara la combinación de maquinas-encendidos que den menor cantidad total de encendidos 
        this.solucion = new HashMap<Maquina, Integer>();
        //Recorremos todas las maquinas una por una
        for (Maquina maquina : listaOrdenada) {
            accesos++;
            int pRestantes = piezasTotales - piezasCreadas;
            int pMaquina = maquina.getPiezas();
            if (pMaquina <= pRestantes){
                int div = pRestantes/pMaquina;
                //vemos si la maquina es candidata valida y cuantas veces.
                if (div >= 1){
                    solucion.put(maquina, div);
                    piezasCreadas += pMaquina * div;
                }
            }
        }
        //si no encuentra una solucion optima (no se crea la cantidad necesaria de piezas) 
        //elimina el contenido de solucion para evitar devolver una solucion parcial.
        if (piezasCreadas < piezasTotales){
            solucion.clear();
            accesos = 0;
            piezasCreadas = 0;
        }
    }
}