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
    protected Guard guard;
    protected Sculpture sculpture;
    protected Polygon representacion;

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
     * @param type, tipo de guardia que se quiere colocar en la habitación ("normal", "lazy", "magical"). El guardia por defecto es normal
     */
       
   public void arriveGuard(String type){
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
        if(type.equals("lazy"))guard = new Lazy(maxX, maxY, color);
        else if(type.equals("magical"))guard = new Magical(maxX, maxY, color);
        else guard = new Guard(maxX, maxY, color);
        guard.makeVisible();
        desaparecerShy();
    }
    
    
    /**
     * Funcion para mover al guardia dentro del cuarto.
     * @param xPos, posición x del cuarto
     * @param yPos, posicion y del cuarto
     */
    public boolean moveGuard(int xPos, int yPos){
        if (representacion.contains(xPos,yPos) || guard instanceof Magical){
            guard.setPos(xPos, yPos);
            desaparecerShy();
            return true;
        }else{
            
            return false;
        }
    }
    
    
    /**
     * Funcion para ubicar a la escultura en una posicion especifica del cuarto
     * @param type, tipo de escultura que se desea colocar
     * @param xPos, posición x del cuarto
     * @param yPos, posicion y del cuarto
     */
    public boolean displaySculpture(String type,int xPos, int yPos){
        boolean sePudo = false;
        if(representacion.contains(xPos,yPos) && !isASculpture()){
            sculpture = new Sculpture(color, xPos, yPos, type);
            sculpture.makeVisible();
            sePudo = true;
        }else{
            if (isASculpture()) JOptionPane.showMessageDialog(null, "Ya existe una escultura en el cuarto o no se puede colocar la escultura en esa pos");
        }
        desaparecerShy();
        return sePudo;
    }
    
    /**
     * Metodo para hacer invisible una escultura tipo shy cuando el guardia la esta viendo
     */
    protected void desaparecerShy(){
        try{
            if(guardIsWatching(getGuardLocation()[0],getGuardLocation()[1], getSculptureLocation()[0], getSculptureLocation()[1]) && sculpture.getType().equals("shy")){
            sculpture.makeInvisible();
            }else sculpture.makeVisible();
        }catch(RoomException e){
            System.out.println(RoomException.SIN_ESCULTURA + " o " + RoomException.SIN_GUARDIA);
        }catch( IndexOutOfBoundsException e){
            System.out.println(RoomException.SIN_ESCULTURA + " o " + RoomException.SIN_GUARDIA);
        }
    }
    
    /**
     * Metodo para prender o apagar la alarma del cuarto
     * @param, on parametro que indica si la alarma se prende o se apaga.
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
     * @throws SIN_ESCULTURA si no existe un guardia en la habitación
     */
    public int[] getSculptureLocation() throws RoomException{
        if (sculpture == null) throw new RoomException(RoomException.SIN_ESCULTURA);
        return sculpture.getPos();
    }
    
    /**
     * Metodo que indica en que parte de la galeria se encuentra el guardia
     * @throws SIN_GUARDIA si no existe un guardia en la habitación 
     */
    public int[] getGuardLocation() throws RoomException{
        if (guard == null) throw new RoomException(RoomException.SIN_GUARDIA);
        return guard.getPos();
    }
    
    /**
     * Metodo que realiza el robo de la escultura dentro del cuarto
     */
    public void steal() throws RoomException{
        if(sculpture.getType().equals("heavy")) throw new RoomException(RoomException.ESCULTURA_PESADA);
        if(sculpture == null) throw new NullPointerException(RoomException.SIN_ESCULTURA);
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
     * 
     */
    public boolean guardIsWatching(int guardX, int guardY, int sculptureX, int sculptureY){
        double[] vector = {(sculptureX + 0.5) - guardX, sculptureY - 0.5 - guardY};
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
            vector[0] = (sculptureX + 1) - guardX;
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
    
    /**
     * Función para identificar los vertices del poligono que ven a la escultura o al guardia.
     * @param objetivo, elemento del cuarto del cual se desea realizar el estudio.
     * @return lista de todas las lineas desde la escultura/guardia a los vertices que l@ ven.
     * @throws SIN_GUARDIA si objetivo.equals("guard") y el cuarto no tiene algun guardia,
     * @throws SIN_ESCULTURA si !objetivo.equals("guard") y el cuarto no tiene alguna escultura.
     */
    private Line[] visibleLines(String objetivo) throws RoomException{
        ArrayList<Line> lineasVisibles = new ArrayList<Line>();
        int[] destino;
        if(objetivo.equals("guard")){
            destino = getGuardLocation();
        }else{
            destino = getSculptureLocation();
        }
        for (int i = 0; i < vertices[0].length; i++){
            if (guardIsWatching(vertices[0][i], vertices[1][i], destino[0],destino[1])){
                lineasVisibles.add(new Line(destino[0],destino[1],vertices[0][i],vertices[1][i]));
            }
        }
        Line[] lineas = new Line[lineasVisibles.size()];
       lineas = lineasVisibles.toArray(lineas);
       return lineas;
    }
    
   
    
    /**
     * Función para identificar los puntos de intersección entre las lineas de visión de la escultura y su recta perpendicular desde cada uno de 
     los vertices que ve al guardia.
     * @return lista de todas las lineas desde los vertices que ven al guardia hasta los puntos de intersección en la linea de visión.
     * @throws SIN_GUARDIA si el cuarto no tiene algun guardia,
     * @throws SIN_ESCULTURA si  cuarto no tiene alguna escultura.
     */
    
    private Line[] puntosDeInteres() throws RoomException{
        Line[] lineasDeEscultura = visibleLines("sculpture");
        Line[] lineasDeGuardia = visibleLines("guard");
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
    /**
     * Función para identificar la distancia más corta que debe recorrer el guardia para poder ver a la escultura.
     * @return distancia más corta.
     */
    public float shortestDistance(){
        float caminoRegreso = 0;
        try{
            if(guard instanceof Magical && !representacion.contains(getGuardLocation()[0], getGuardLocation()[1])) caminoRegreso = devolverMagical();
        }catch(RoomException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        float minimo = 500000000;
        try{
            int[] origen = getGuardLocation();
            ArrayList<double[]> destinos1 = new ArrayList<double[]>();
            ArrayList<double[]> destinos2 = new ArrayList<double[]>();
            for(Line linea : visibleLines("guard")){
                destinos1.add(new double[] {linea.getX2(),linea.getY2()});
            }
            for(Line linea : puntosDeInteres()){
                destinos2.add(new double[] {linea.getX2(),linea.getY2()});
            }
            float camino;
            if(guardIsWatching(getGuardLocation()[0], getGuardLocation()[1], getSculptureLocation()[0],getSculptureLocation()[1])) minimo = 0;
            else{
                for(double[] punto1 : destinos1){
                    camino = new Line(origen[0], origen[1], punto1[0], punto1[1]).longitud();
                    if(guardIsWatching((int) Math.round(punto1[0]), (int) Math.round(punto1[1]), getSculptureLocation()[0], getSculptureLocation()[1]) && camino < minimo){
                        minimo = camino;
                        
                    }
                    else{
                        for(double[] punto2 : destinos2){
                            camino = new Line(origen[0], origen[1], punto1[0], punto1[1]).longitud() + new Line(punto1[0], punto1[1], punto2[0], punto2[1]).longitud();
                            if(camino < minimo && guardIsWatching((int) Math.round(punto2[0]), (int) Math.round(punto2[1]), getSculptureLocation()[0], getSculptureLocation()[1])){
                                minimo = camino;
                                
                            }
                        }
                    }
                }
            }
        }catch(RoomException e){
            JOptionPane.showMessageDialog(null, RoomException.SIN_ESCULTURA + " o " + RoomException.SIN_GUARDIA);
        }
        return minimo + caminoRegreso;
    }
    /**
     * Metodo que devuelve a un guardia tipo Magical al vertice de su habitación más cercano
     * @throws SIN_GUARDIA en caso de que el guardia no contenga ningun guardia.
     */
    private float devolverMagical() throws RoomException{
        float minimo = 500000000;
        int[] cercano = getGuardLocation();
        float camino = 0;
        for(int i = 0; i < vertices[0].length; i++){
            camino = new Line(getGuardLocation()[0], getGuardLocation()[1], vertices[0][i], vertices[1][i]).longitud();
            if(camino < minimo){
                cercano[0] = vertices[0][i];
                cercano[1] = vertices[1][i];
            }
        }
        if(this.representacion.contains(cercano[0],cercano[1] + 1)) cercano[1] +=1;
        else if(this.representacion.contains(cercano[0],cercano[1] - 1)) cercano[1] -=1;
        else if(this.representacion.contains(cercano[0] +1,cercano[1])) cercano[0] += 1;
        else if(this.representacion.contains(cercano[0] -1,cercano[1])) cercano[0] -= 1;
        moveGuard(cercano[0], cercano[1]);
        return camino;
    }
    /**
     * Función que identifica si la escultura  aparece o no en el canvas
     */
    public boolean sculptureVisible(){
        return sculpture.isVisible();
    }
    
    public Guard getGuard(){
        return guard;
    }
}
    
    

