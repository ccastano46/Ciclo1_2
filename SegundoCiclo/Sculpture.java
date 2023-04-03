
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
    private String type;
    private boolean visible;
    

    /**
     * Constructor de la clase Sculpture
     * @param color con el cual se va a representar la escultura.
     * @param xPosInicial, posición x (latitud) inicial de la escultura.
     * @param yPosInicial, posición y (longitud) inicial de la escultura.
     * @param tipo, tipo de escultura que se desea crear.
     */
    public Sculpture(String color, int xPosInicial, int yPosInicial, String tipo)
    {
        this.color = color;
        xPosition = xPosInicial;
        yPosition = yPosInicial;
        representacion = new Circle((short)6, xPosition, yPosition, color);
        setType(tipo);
        visible = false;
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
    
    /**
     * Metodo que asigna el tipo de escultura
     * @param tipo de la escultura ("normal", "shy" o "heavy"). El tipo por defecto es normal
     */
    
    private void setType(String tipo){
        if(tipo.equals("shy")) type = tipo;
        else if(tipo.equals("heavy")) type = tipo;
        else type = "normal";
    }
    /**
     * Función que indica el tipo de la escultura
     */
    
    public String getType() throws RoomException{
        if(this == null) throw new RoomException(RoomException.SIN_ESCULTURA);
        return type;
    }
    /**
     * Función que indica si la escultura aparece o no en el canvas.
     */
    public boolean isVisible(){
        return visible;
    }
    
    public void makeVisible(){
        representacion.makeVisible();
        visible = true;
    }
    
    public void makeInvisible(){
        representacion.makeInvisible();
        visible = false;
    }
}
