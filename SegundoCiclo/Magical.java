
/**
 * Write a description of class Magical here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Magical extends Guard
{
    public Magical(int xPos, int yPos, String color){
        super(xPos, yPos, color);
    }
    
    /**
     * Metodo que ubica al guardia en alguna parte de la habitación y
     * @param xPos, posición horizontal a la cual se quiere mover al guardia.
     * @páram yPos, posición vertical a la cual se quiere mover al guardia
     */
    public void setPos(int xPos, int yPos){
        recorrido= new Line(this.xPos+6/2,this.yPos+6/2,xPos+6/2,yPos+6/2);
        recorrido.makeVisible();
        super.setPos(xPos,yPos);
    }
        
        

    }

