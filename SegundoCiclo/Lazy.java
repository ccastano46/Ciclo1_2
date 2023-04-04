
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
    public void setsdPos(int xPos, int yPos){
        System.out.println(getPos()[0]+","+getPos()[1]);
        int dXPos;
        super.setPos(xPos/2, yPos);
    }
}