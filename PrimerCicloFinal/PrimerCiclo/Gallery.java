import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.*;
/**
 * Galeria en la cual se van a exhibir todas las habitaciones.
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */


public class Gallery
{
    private int length;
    private int width;
    private HashMap<String, Room> rooms;
    private boolean proceso = false;
    
    /**
     * Constructor de la clase Gallery
     * @param length, altura de la galeria.
     * @param width, anchura de la galeria.
     */
   public Gallery(int length, int width){
       Canvas.getCanvas(width + 8, length + 8);
       this.length = length;
       this.width = width;
       rooms = new HashMap<String, Room>();
   }
   
   /**
    * Metodo para crear un nuevo cuarto.
    * @param color, color con el cual se va a identificar el cuarto.
    * @param polygon, matriz n x 2, que contiene las cordenadas de los vertices del poligono.
    */
   
   public void buildRoom(String color, int[][] polygon){
       if (polygon[0].length == polygon[1].length){
           for (int i = 0; i < polygon[1].length; i++){
            polygon[1][i] = Math.abs(polygon[1][i] - length);
            System.out.println(polygon[1][i]);
        }
  
        if(!rooms.containsKey(color)){
           rooms.put(color, new Room(color, polygon));
           rooms.get(color).makeVisible();
           proceso = true;
       } else{
           proceso = false;
       }
       
     
        } else{
            proceso = false;
        }
       
       
       
   }
   
   /**
    * Metodo para posicionar una escultura dentro de un cuarto.
    * @param room, cuarto al cual va a pertenecer la escultura.
    * @param x, posición x (latitud) de la escultura.
    * @param y, posición y (longitud) de la escultura.
    */
   
   public void displaySculpture(String room, int x, int y){
       if(rooms.containsKey(room) && rooms.get(room).displaySculpture(x,Math.abs(y - length ))) {
           proceso = true;
       }
       else {
           proceso = false;
       }
   }
   
   /**
    * Metodo para posicionar al guardia en la puerta del cuarto.
    * @param room, cuarto al cual va a llegar el guardia.
    */
   
   public void arriveGuard(String room){
       if(rooms.containsKey(room)){
           rooms.get(room).arriveGuard();
           proceso = true;
           
       } else{
           proceso = false;
       }
       
       
   }
   
   /**
    * Metodo para mover un guardia dentro de un cuarto.
    * @param room, cuarto al cual pertenece el guardia.
    * @param x, posición x (latitud) a la cual se quiere mover el guardia.
    * @param y, posición y (longitud) a la cual se quiere mover el guardia.
    */
   public void moveGuard(String room, int x, int y){
       if(rooms.containsKey(room) && rooms.get(room).moveGuard(x,Math.abs(y - length ))) {
           proceso = true;
       }
       else {
           proceso = false;
       }
   }
   /**
    * Metodo para prender o apagar la alarma de un cuarto en especifico.
    * @param room, cuarto al cual se le va a ejecutar el metodo.
    * @param on, bandera que indica si la alarma esta apagada o prendida.
    */
   
   public void alarm(String room, boolean on){
       if (rooms.containsKey(room) && rooms.get(room).getAlarm() != on){
           rooms.get(room).setAlarm(on);
           proceso = true;
       } else{
           proceso = false;
       }
   }
   
   /**
    * Función que indica los cuartos que pertenecen a la galeria
    */
   
   public String[] rooms(){
       ArrayList<String> cuartosGaleria = new ArrayList<String>();
       for (String r : rooms.keySet()){
           cuartosGaleria.add(r);
       }
       Collections.sort(cuartosGaleria);
       String[] cuartos = new String[cuartosGaleria.size()];
       cuartos = cuartosGaleria.toArray(cuartos);
       return cuartos;
   }
   
   /**
    * Función que indica los cuartos de la galeria cuyas alarmas estan prendidas.
    */
    public String[] roomsOnAlert(){
        ArrayList<String> cuartosAlerta = new ArrayList<String>();
       for (String r : rooms.keySet()){
           if (rooms.get(r).getAlarm()) cuartosAlerta.add(r);
       }
       Collections.sort(cuartosAlerta);
       String[] cuartos = new String[cuartosAlerta.size()];
       cuartos = cuartosAlerta.toArray(cuartos);
       return cuartos;
    }
    
    /**
     * Función para saber en que posición esta la escultura de un cuarto.
     * @param room, cuarto al que pertenece la escultura.
     */
    
    public int[] sculptureLocation(String room){
        if (rooms.containsKey(room)){
             int [] locations = rooms.get(room).getSculptureLocation();
             locations[1] = Math.abs(locations[1] - length);
            proceso = true;
            return locations;
        } else proceso = false;
        return null;

    }
    
    /**
     * Función para saber en que posición esta el guardia de un cuarto.
     * @param room, cuarto al que pertenece el guardia.
     */
    
    public int[] guardLocation(String room){
        if (rooms.containsKey(room)){
             int [] locations = rooms.get(room).getGuardLocation();
             locations[1] = Math.abs(locations[1] - length);
            proceso = true;
            return locations;
        } else proceso = false;
        return null;
    }
    
    /**
     * Metodo para hacer visible la galeria
     */
    
    public void makeVisible(){
        for (String room : rooms.keySet()){
            rooms.get(room).makeVisible();
        }
    }
    
    
    /**
     * Metodo para hacer ocultar la galeria
     */
    
    public void makeInvisible(){
        for (String room : rooms.keySet()){
            rooms.get(room).makeInvisible();
        }
    }
    
    
     /**
     * Metodo para finalizar la simulación
     */
    
    public void finish(){
        Canvas ventana = Canvas.getCanvas();
        ventana.bloquear();
        ventana.closeCanvas();
        JOptionPane.showMessageDialog(null, "Se termino la simulación exisotsamente");
        proceso = true;
    }
    
    /**
     * Metodo para saber si la última acción se realizo con exito
     */
    
    public boolean ok(){
        return proceso;
    }
}
