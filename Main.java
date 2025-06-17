import java.util.ArrayList;

public class Main {
    
        public static void main(String[] args) {
       
        LectorMaquinas lector= new LectorMaquinas("Maquinas.txt");
        ArrayList<Maquina> maquinas= lector.getMaquinas();
        Integer piezasTotales= lector.getPiezasTotales();
        Fabrica fabrica= new Fabrica();
        fabrica.agregarMaquinas(maquinas);
        fabrica.setPiezasTot(piezasTotales);
        System.out.println("Piezas totales: " + fabrica.getPiezasTot());
        System.out.println("Maquinas disponibles: \n" + fabrica.getMaquinasDisponibles());
        /* Explicacion backtracking */
        fabrica.ejecutarBacktracking();
        System.out.println("Backtracking\n" +
                   "Solución obtenida: " + fabrica.getMaquinasSolucionBacktracking() + "\n" +
                   "Cantidad de piezas producidas: " +  fabrica.getPiezasCreadasBacktracking() + "\n" + 
                   "Puestas en funcionamiento: " + fabrica.getMinEncendidosBactracking() + "\n" +
                   "Estados generados: " + fabrica.getAccesosBacktracking());
        
        /* Explicacion greedy */
        fabrica.ejecutarGreedy();
        System.out.println("Greedy\n" +
                   "Solución obtenida: " + fabrica.getMaquinasSolucionGreedy() + "\n" +
                   "Cantidad de piezas producidas: " + fabrica.getPiezasCreadasGreedy() + "\n" +
                   "Puestas en funcionamiento: " + fabrica.getMaquinasSolucionGreedy().size() + "\n" +
                   "Estados generados: " + fabrica.getAccesosGreedy());
    }
}
