import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    private ArrayList<Maquina> soluciones;
    //puestas en funcionamiento
    private int minEncendidos = Integer.MAX_VALUE;
    //cantidad de estados generados
    private int accesos;

    public Backtracking(){
        this.soluciones = new ArrayList<Maquina>();
        this.accesos = 0;
    }
    
    public void setAccesos(int accesos) {
        this.accesos = accesos;
    }
    
    public int getAccesos() {
        return accesos;
    }

    public int getMinEncendidos(){
        return minEncendidos;
    }

    //arreglo de maquinas que forman la solucion
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


    /* Explicacion de estrategia:
     * -Arbol de exploracion:
     *  El arbol de exploracion se genera a partir todas las posibles combinaciones de maquinas capaces de producir
     *  un numero determinado de piezas. Teniendo en cuenta que solo pueden encenderse de a una, en cada paso se
     *  selecciona una maquina, verificando la cantidad de piezas que puede producir la misma. Esto se resta a la cantidad total
     *  de piezas que debe producir la fabrica hasta que se complete el pedido. En caso de que no se llegue a la cantidad exacta,
     *  podran faltar piezas, pero nunca se produciran de mas.
     * 
     * -Estrategia de poda:
     *  Lo hemos pensado basandonos en dos cosas:   
     *      1) Cantidad de veces que se han encendido las maquinas.
     *      Se lleva la cuenta de cada vez que una maquina se enciende. Si una posible solucion A tiene n encendidos, y la siguiente solucion B
     *      tiene n+1 encendidos, automaticamente podamos esa rama de posibilidades, ya que no es mejor solucion que la que hemos encontrado primero.
     * 
     *      2) Cantidad de piezas producidas.
     *      Aclaracion: Nosotros hemos supuesto que la cantidad podia ser menor o igual a las piezas totales a fabricar, mas no mayor.
     *      En cada iteracion se resta la cantidad de piezas que la maquina seleccionada produce a la cantidad total de piezas que debe producir 
     *      la fabrica. Si al hacer esta resta nuestra posible solucion da un resultado negativo (es decir que produce piezas sobrantes) automaticamente
     *      podamos esa rama de posibilidades, ya que produce perdidas a la fabrica.
     * 
     * -Estados solucion:
     *  Aquellos que produzcan la cantidad exacta de piezas totales a fabricar. De ellos se busca el que tenga la menor cantidad de encendidos.
     * 
     * -Estados posibles:
     *  Aquellos que produzcan una cantidad parcial de piezas, sin superar la cantidad de piezas totales a fabricar.
     */


    //le da el estado inicial al backtracking para buscar soluciones
    public void resolver(List<Maquina> maquinas, int piezasTotales) {
        //En esta linea se llama al backtracking con un estado inicial
        backtrack(maquinas, piezasTotales, 0, new ArrayList<Maquina>(), 0);
    }

    private void backtrack(List<Maquina> maquinas, int piezasRestantes, int encendidos, List<Maquina> actual, int i) {             
        //incrementamos la cantidad de accesos en uno cada vez que entramos en el metodo backtrack ya sea en estado inicial o de forma recursiva
        accesos++;  
        //aca nos hacemos las preguntas, ¿nos queda alguna pieza para hacer?, ¿El numero de encendidos actual es menor que el de la mejor solucion encontrada hasta ahora?                
        if (piezasRestantes == 0) {
            if (encendidos < minEncendidos) {
                //En caso afirmativo, la solucion y el minEncendidos toman los valores actuales con la mejor solucion encontrada hasta el momento
                minEncendidos = encendidos;
                soluciones = new ArrayList<>(actual);
            }
            //en caso negativo (es decir, que no estemos en una solucion válida completa)
        } else{
            //recorremos una por una la lista de maquinas, de manera ascendente para no generar permutaciones
            for ( int j = i ; j < maquinas.size() ; j++) {
                //y nos hacemos la pregunta, ¿La maquina actual sería un elemento valido del conjunto de maquinas para resolver el problema?, este if retira un conjunto de soluciones inválidas como estratégia de poda
                if (piezasRestantes-maquinas.get(j).getPiezas() >= 0 && encendidos < minEncendidos){
                    //en caso afirmativo, se añade la maquina a la lista de maquinas actuales
                    actual.add(maquinas.get(j));
                    //y se llama al backtrack de forma recursiva, dando nuevos valores a la lista actual, el numero de encendidos, la cantidad de piezas restantes y el indice en el que quedo el for.
                    backtrack(maquinas, piezasRestantes - maquinas.get(j).getPiezas(), encendidos + 1, actual, j);
                    //cuando el programa termina con un backtrack, el código vuelve a esta linea, y se remueve la última solución para poner otra maquina en esa posición
                    actual.removeLast();
                }                
            }
        }
    }
}