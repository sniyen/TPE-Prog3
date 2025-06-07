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
        //En esta linea se llama al backtracking con un estado inicial
        backtrack(maquinas, piezasTotales, 0, new ArrayList<Maquina>());
        //despues imprimimos la lista de soluciones, usando los valores de el minimo numero de encendidos y la solucion correspondiente
        System.out.println("Mejor solución con " + minEncendidos + " encendidos:");
        for (Maquina m : soluciones) {
            System.out.println(m);
    }
    }

    private void backtrack(List<Maquina> maquinas, int piezasRestantes, int encendidos, List<Maquina> actual) {
        //incrementamos la cantidad de accesos en uno cada vez que entramos en el metodo backtrack ya sea en estado inicial o de forma recursiva
        accesos++;  
        System.out.println(accesos); 
        //aca nos hacemos las preguntas, ¿nos queda alguna pieza para hacer?, ¿El numero de encendidos actual es menor que el de la mejor solucion encontrada hasta ahora?                
        if (piezasRestantes == 0) {
            if (encendidos < minEncendidos) {
                //En caso afirmativo, la solucion y el minEncendidos toman los valores actuales con la mejor solucion encontrada hasta el momento
                minEncendidos = encendidos;
                //ver si en lugar de crear uno nuevo se puede pisar la lista anterior
                //por eficiencia nada mas xd
                soluciones = new ArrayList<>(actual);
            }
            //en caso negativo (o sea, que no estemos en una solucion válida completa)
        } else{
            //recorremos una por una la lista de maquinas
            for (Maquina m : maquinas) {
                //y nos hacemos la pregunta, ¿La maquina actual sería un elemento valido del conjunto de maquinas para resolver el problema?, este if retira un conjunto de soluciones inválidas como estratégia de poda
            if (piezasRestantes-m.getPiezas() >= 0 && encendidos < minEncendidos){
                //en caso afirmativo, se añade la maquina a la lista de maquinas actuales
                actual.add(m);
                //y se llama al backtrack de forma recursiva, dando nuevos valores a la lista actual, el numero de encendidos y la cantidad de piezas restantes
                backtrack(maquinas, piezasRestantes - m.getPiezas(), encendidos + 1, actual);
                //cuando el programa termina con un backtrack, el código vuelve a esta linea, y se remueve la última solución para poner otra maquina en esa posición
                actual.removeLast();
            } 
            
            }
        }
    }
    }        


    // public static void main(String[] args) {
       
    //     LectorMaquinas lector= new LectorMaquinas("Maquinas.txt");
    //     ArrayList<Maquina> maquinas= lector.getMaquinas();
    //     Integer piezasTotales= lector.getPiezasTotales();
    //     Fabrica fabrica= new Fabrica(new Backtracking());
    //     fabrica.añadirMaquinas(maquinas);
    //     fabrica.setPiezasTot(piezasTotales);
    //     fabrica.ejecutarBacktracking();
      
    //     System.out.println("accesos recursivos: "+ fabrica.getAccesosBacktracking());
    // }