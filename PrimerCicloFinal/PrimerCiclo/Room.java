import javax.swing.JOptionPane;
import java.util.*;
import java.awt.Point;
/**
 * Una habitacion que se puede manipular y se dibuja a si mismo en Canvas.
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */
public class Room
{
    private boolean alarm;
    private int[][] vertices;
    private String color;
    private Guard guard;
    private Sculpture sculpture;
    private Polygon representacion;

    private Polygon areaVisibleSculpture;
    //Es mientras conseguimos lo del area
    private boolean watch = false;

   /**
    * Constructor del cuarto.
    * @param color, color con el cual se va a identificar el cuarto
    * @param polygon, vertices que componen el poligono del cuarto
    */
    public Room(String color, int[][] polygon){
        alarm=false;
        vertices=polygon;
        this.color=color;
        representacion = new Polygon(vertices[0],vertices[1],vertices[0].length,"white");
    }
    
    /**
     * Metodo para hacer visible en la galeria el cuarto y sus elementos 
     */
  public void makeVisible(){
        representacion.getBound();
        representacion.makeVisible();
        if (guard != null) guard.makeVisible(); 
        if (sculpture != null) sculpture.makeVisible();
    }
    
     /**
     * Metodo para hacer visible en la galeria el cuarto y sus elementos 
     */
    public void makeInvisible(){
        representacion.makeBoundInvisible();
        representacion.makeInvisible();
        if (guard != null) guard.makeInvisible();
        if (sculpture != null) sculpture.makeInvisible();
    }
    
    /**
     * Metodo para posicionar al guardia en la puerta de la galeria
     */
       
   public void arriveGuard(){
        int maxY = (int) Double.NEGATIVE_INFINITY;
        int maxX = (int) Double.NEGATIVE_INFINITY;
        for (int i = 0; i < vertices[1].length; i++){
            if (vertices[1][i] >= maxY ){
                maxY = vertices[1][i];
                maxX = vertices[0][i];
            }
            if ( vertices [1][i] == maxY && vertices[0][i] > maxX) {
                maxX = vertices[0][i];
            }
        }
        guard = new Guard(maxX, maxY, color);
        guard.makeVisible();
    }
    
    
    /**
     * Funcion para mover al guardia dentro del cuarto.
     * @param xPos, posición x del cuarto
     * @param yPos, posicion y del cuarto
     */
    public boolean moveGuard(int xPos, int yPos){
        if (representacion.contains(xPos,yPos)){
            guard.setPos(xPos, yPos);
            return true;
        } else{
            return false;
        }
    }
    
    
    /**
     * Funcion para ubicar a la escultura en una posicion especifica del cuarto
     * @param xPos, posición x del cuarto
     * @param yPos, posicion y del cuarto
     */
    public boolean displaySculpture(int xPos, int yPos){
        if(representacion.contains(xPos,yPos) && !isASculpture()){
            sculpture = new Sculpture(color, xPos, yPos);
            sculpture.makeVisible();
            return true;
        }else{
            if (isASculpture()) JOptionPane.showMessageDialog(null, "Ya existe una escultura en el cuarto");
            return false;
        }
    }
    
    /**
     * Metodo para prender la alarma del cuarto
     */
    public void setAlarm(boolean on){
        if(on){
            alarm=true;
            representacion.changeColor("gray");
            representacion.makeVisible();
            if(!Objects.isNull(guard)){
                guard.makeVisible();
            }
            if(!Objects.isNull(sculpture)){
                sculpture.makeVisible();
            }
        }else{
            alarm=false;
            representacion.changeColor("white"); 
            representacion.makeVisible();
            if(!Objects.isNull(guard)){
                guard.makeVisible();
            }
            if(!Objects.isNull(sculpture)){
                sculpture.makeVisible();
            }
        }
    }
    
    /**
     * Función que indica si la alarma de la habitación esta prendida o apagada
     */
    public boolean getAlarm(){
        return alarm;
    }
    
    /**
     * Metodo que indica en que parte de la galeria se encuentra la escultura
     */
    public int[] getSculptureLocation(){
        if (sculpture != null) return sculpture.getPos();
        return new int[] {};
    }
    /**
     * Metodo que indica en que parte de la galeria se encuentra el guardia
     */
    public int[] getGuardLocation(){
        if (guard != null) return guard.getPos();
        return new int[] {};
    }
    
    /**
     * Metodo que realiza el robo de la escultura dentro del cuarto
     */
    public void steal(){
        sculpture.makeInvisible();
        sculpture = null;
    }
    
    /**
     * Función para identificar si se encuentra una escultura dentro del cuarto
     */
    public boolean isASculpture(){
        if (sculpture != null) return true;
        else return false;
    }

    //Metodos Temporale para poder realizar las pruebas

    

    public boolean guardIsWatching(){
        return watch;
    }
    
    public void setWatch(boolean esVigilado){
        watch = esVigilado;
    }
    
    /** Funcion para saber la distancia recorrida del guardia.
       * @return ditancia recorrida
    */
    public float distanceTraveled(){
        return guard.getDistanciaRecorrida();
    }
}

    
    

