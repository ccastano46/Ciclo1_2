
/**
 * Write a description of class Lazy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lazy extends Guard
{
    /**
     * Constructor del guardia peresozo
     * @param xPos, pos x donde se encuentra la puerta de la habitación en la cual pertenece el guardia.
     * @param yPos, pos y donde se encuentra la puerta de la habitación en la cual pertenece el guardia.
     * @param color, habitación a la que pertenece el guardia
     */
    public Lazy(int xPos, int yPos, String color){
        super(xPos, yPos, color);
    }
    
    /**
     * Sobre escritura del metodo setPos ya que este solo se mueve la mitad del recorrido
     * @param xPos, posición horizontal a la cual se espera mover al guardia.
     * @páram yPos, posición vertical a la cual se espera mover al guardia.
     */
    public void setPos(int xPos, int yPos){
        int xDelta = (int) (xPos- getPos()[0])/2;
        int yDelta = (int) (yPos- getPos()[1])/2;
        super.setPos(getPos()[0] + xDelta, getPos()[1] + yDelta);
    }
}