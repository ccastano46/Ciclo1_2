
/**
 * Una escultura que se puede manipular y se dibuja a si mismo en Canvas.
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */
public class Sculpture
{
    // instance variables - replace the example below with your own
    private String color;
    private int xPosition;
    private int yPosition;
    private Circle representacion;
    

    /**
     * Constructor de la clase Sculpture
     * @param color con el cual se va a representar la escultura.
     * @param xPosInicial, posición x (latitud) inicial de la escultura.
     * @param yPosInicial, posición y (longitud) inicial de la escultura.
     */
    public Sculpture(String color, int xPosInicial, int yPosInicial)
    {
        this.color = color;
        xPosition = xPosInicial;
        yPosition = yPosInicial;
        representacion = new Circle((short)6, xPosition, yPosition, color);
        
    }
    
    /**
     * Función para retornar la representación de la escultura a las otras clases
     */
    
    public Circle getRepresentation(){
        return representacion;
    }
    
    /**
     * Metodo que cambia la posicion de la escultura.
     * @param xPos, nueva posición x de la escultura
     * @param yPos, nueva posición y de la escultura.
     */
    public void setPos(int x, int y){
        xPosition = x;
        yPosition = y;
        representacion.locate(x,y);
        
    }
    
    /**
     * Función para retornar la ubicación de la escultura a las otras clases.
     */
    public int[] getPos(){
        int [] positions = {xPosition, yPosition};
        return positions;
    }
    
    public void makeVisible(){
        representacion.makeVisible();
    }
    
    public void makeInvisible(){
        representacion.makeInvisible();
    }
}
