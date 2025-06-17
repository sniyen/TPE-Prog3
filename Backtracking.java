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

    //le da el estado inicial al backtracking para buscar soluciones
    public void resolver(List<Maquina> maquinas, int piezasTotales) {
        backtrack(maquinas, piezasTotales, 0, new ArrayList<Maquina>());
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
    private void backtrack(List<Maquina> maquinas, int piezasRestantes, int encendidos, List<Maquina> actual) {
        accesos++;                  
        if (piezasRestantes == 0) {
            if (encendidos < minEncendidos) {
                minEncendidos = encendidos;
                soluciones = new ArrayList<>(actual);
            }
        } else {
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