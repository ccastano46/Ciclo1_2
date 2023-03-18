import javax.swing.JOptionPane;
import java.util.*;
import java.awt.Point;
import java.util.ArrayList; 
import java.util.HashMap;
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

    /**
     * Función que indica si el guardia de la habitación esta vigilando la escultura que esta dentro de esta misma.
     * @param guardX, posición x del guardia.
     * @param guardY, posición y del guardia.
     * @param sculptureX, posición x de la escultura.
     * @param sculptureY, posición y de la escultura.
     */
    public boolean guardIsWatching(int guardX, int guardY, int sculptureX, int sculptureY){
        double[] vector = {(sculptureX + 3) - guardX, sculptureY - 3 - guardY};
        boolean isContained = false;
        boolean veElCentro = true;
        // Se analiza si el guardia esta viendo el centro de la escultura
        for (int i = 1; i<= 50; i++){
            isContained = representacion.contains((double) vector[0] * i/50 + guardX, (double) vector[1] * i/50 + guardY);
            if(!isContained) {
                veElCentro = false;
                break;
            }
        }
        // Si el guardia esta viendo el centro, se trata de ver si ve almenos alguna de las dos mitades.
        if(isContained){
            vector[0] = (sculptureX + 6) - guardX;
            for (int i = 1; i<= 50; i++){
                isContained = representacion.contains((double) vector[0] * i/50 + guardX, (double) vector[1] * i/50 + guardY);
                if(!isContained) break;
            }
        }
        if (!isContained && veElCentro){
            vector[0] = (sculptureX ) - guardX;
            for (int i = 1; i<= 50; i++){
                isContained = representacion.contains((double) vector[0] * i/50 + guardX, (double) vector[1] * i/50 + guardY);
                if(!isContained) break;
            }
        }
        return isContained;
    }
    
    /** Funcion para saber la distancia recorrida del guardia.
       * @return ditancia recorrida
    */
    public float distanceTraveled(){
        return guard.getDistanciaRecorrida();
    }
    
    private Line[] visibleLines(){
        ArrayList<Line> lineasVisibles = new ArrayList<Line>();
        for (int i = 0; i < vertices[0].length; i++){
            if (guardIsWatching(vertices[0][i], vertices[1][i], getSculptureLocation()[0],getSculptureLocation()[1])){
                lineasVisibles.add(new Line(getSculptureLocation()[0],getSculptureLocation()[1],vertices[0][i],vertices[1][i]));
            }
        }
        Line[] lineas = new Line[lineasVisibles.size()];
       lineas = lineasVisibles.toArray(lineas);
       return lineas;
    }
    
   private Line[] vertixesWatchingGuard(){
        ArrayList<Line> lineasVisibles = new ArrayList<Line>();
        for (int i = 0; i < vertices[0].length; i++){
            if (guardIsWatching(vertices[0][i], vertices[1][i], getGuardLocation()[0],getGuardLocation()[1])){
                lineasVisibles.add(new Line(getGuardLocation()[0],getGuardLocation()[1],vertices[0][i],vertices[1][i]));
            }
        }
        Line[] lineas = new Line[lineasVisibles.size()];
       lineas = lineasVisibles.toArray(lineas);
       return lineas;
    }
    
    private Line[] puntosDeInteres(){
        Line[] lineasDeEscultura = visibleLines();
        Line[] lineasDeGuardia = vertixesWatchingGuard();
        double[] funcion = new double[2];
        double pendiente;
        double b;
        double xInt;
        double yInt;
        ArrayList<Line> puntosDeInterseccion = new ArrayList<Line>();
        for(Line linea : lineasDeEscultura){
            funcion = linea.calculateFunction();
            for(Line linea2 : lineasDeGuardia){
                pendiente = -1/funcion[0];
                b = (linea2.getY2() - pendiente*linea2.getX2());
                xInt = (funcion[1]- b)/(-funcion[0] + pendiente);
                yInt = (-funcion[0] * b +pendiente * funcion[1]) / (-funcion[0] + pendiente);
                if(representacion.contains(new Line(linea2.getX2(), linea2.getY2(), xInt, yInt))){
                    puntosDeInterseccion.add(new Line(linea2.getX2(), linea2.getY2(), xInt, yInt));
                }
            }
        }
        Line[] puntosDeInteres = new Line[puntosDeInterseccion.size()];
        puntosDeInteres = puntosDeInterseccion.toArray(puntosDeInteres);
        return puntosDeInteres;
    }
    
    public float shortestDistance(){
        int numNodos = vertixesWatchingGuard().length + puntosDeInteres().length + 1;
        ArrayList<double[]> nodos = new ArrayList<double[]>();
        HashMap<double[][], float[]> weight = new HashMap<double[][], float[]>();
        float[][] costos = new float[numNodos][numNodos];
        double[][] nuevaRelacion = new double[2][2];
        nodos.add(new double[] {vertixesWatchingGuard()[0].getX1(), vertixesWatchingGuard()[0].getY1()});
        for(Line linea : vertixesWatchingGuard()){
            nodos.add(new double[] {linea.getX2(), linea.getY2()});
            weight.put( new double[][] {{linea.getX1(),linea.getY1()},{linea.getX2(),linea.getY2()}},new float[] {linea.longitud()});
            weight.put( new double[][] {{linea.getX2(),linea.getY2()},{linea.getX1(),linea.getY1()}},new float[] {linea.longitud()});
        }
         for(Line linea : puntosDeInteres()){
            nodos.add(new double[] {linea.getX2(), linea.getY2()});
            weight.put( new double[][] {{linea.getX1(),linea.getY1()},{linea.getX2(),linea.getY2()}},new float[] {linea.longitud()});
            weight.put( new double[][] {{linea.getX2(),linea.getY2()},{linea.getX1(),linea.getY1()}},new float[] {linea.longitud()});
        }
        System.out.println(nodos.size());
        System.out.println("-----------Nodos-----------");
        for(double[] nodo : nodos){
            System.out.println(nodo[0] + " " + nodo[1]);
            System.out.println(nodos.contains(nodo));
        }
        System.out.println("------------------------Relaciones------------------");
        for (double[][] relacion : weight.keySet()){
            System.out.println(relacion[0][0] + " " + relacion[0][1] + "---->" + relacion[1][0] + " " + relacion[1][1]);
        }
        for(double[][] relacion: weight.keySet()){
            for(double[] nodo : nodos){
                if(Arrays.equals(nodo,relacion[0])){
                    for(double[] nodo1 : nodos){
                        if(Arrays.equals(nodo1,relacion[1])){
                            costos[nodos.indexOf(nodo)][nodos.indexOf(nodo1)] = weight.get(relacion)[0];
                        }
                    }
                }
            }
            
        }
        System.out.println("---------------------costos------------------");
        for(int i = 0; i < numNodos; i++){
            for(int j = 0; j < numNodos; j++){
                if(costos[i][j] == 0.0 && i != j) costos[i][j] = 500000000;
                System.out.println(i + ", " + j + ": " + costos[i][j]);
            }
        }
        return 0;
    }
}
    
    

