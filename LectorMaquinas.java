import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class LectorMaquinas {
private String rutaArchivo;

public String getRutaArchivo() {
    return rutaArchivo;
}

public void setRutaArchivo(String rutaArchivo) {
    this.rutaArchivo = rutaArchivo;
}

public LectorMaquinas(String rutaArchivo) {
    this.rutaArchivo = rutaArchivo;
}
public Integer getPiezasTotales(){
    try(BufferedReader br= new BufferedReader(new FileReader(rutaArchivo))) {
        Integer totalPiezas=0;
        totalPiezas= Integer.parseInt(br.readLine().trim());
        return totalPiezas;
    } catch (Exception e) {
        System.out.println("error de archivo "+ e);
        return null;
    }
}

public ArrayList<Maquina> getMaquinas(){
    try( BufferedReader br= new BufferedReader(new FileReader(rutaArchivo));) {       
        String linea= br.readLine();
        ArrayList<Maquina> maquinas = new ArrayList<>();
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.trim().split(",");
            if (partes.length == 2) {
            String nombre = partes[0].trim();
            int piezas = Integer.parseInt(partes[1].trim());
            maquinas.add(new Maquina(nombre, piezas));
        }
    }
    return maquinas;
    } catch (Exception e) {
        System.out.println("Error de archivo "+ e.getMessage());
        return null;
    }
     
}

}
