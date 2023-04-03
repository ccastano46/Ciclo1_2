import java.util.Objects;
import javax.swing.JOptionPane;
import java.lang.Math;
/**
 * Un guardian que se puede manipular y se dibuja a si mismo en Canvas.
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */
public class Guard
{
    private int xPos;
    private int yPos;
    private String color;
    private Rectangle representacion;
    private Line recorrido;
    private float distanciaRecorrida;
    private boolean firstMove = true;
    
    /**
     * Constructor del guardia
     * @param xPos, pos x donde se encuentra la puerta de la habitación en la cual pertenece el guardia.
     * @param yPos, pos y donde se encuentra la puerta de la habitación en la cual pertenece el guardia.
     * @param color, habitación a la que pertenece el guardia
     */
    public Guard(int xPos, int yPos, String color){
        this.xPos=xPos;
        this.yPos=yPos;
        this.color=color;
        representacion=new Rectangle(6,6,xPos,yPos,color);
        distanciaRecorrida = 0;
    }
    
    /**
     * Metodo que indica la representación del guardia en el Canvas
     */
    public Rectangle getRepresentacion(){
        return representacion;
    }
    
    /**
     * Función que indica la posición del guardia.
     */
    public int[] getPos(){
        int[] posiciones = {xPos,yPos};
        return posiciones ;
    }
    
    /**
     * Metodo que ubica al guardia en alguna parte de la habitación y retorna la distancia recorrida por el guardía para llegar a esa posición
     * @param xPos, posición horizontal a la cual se quiere mover al guardia.
     * @páram yPos, posición vertical a la cual se quiere mover al guardia
     */
    public void setPos(int xPos, int yPos){
        if(firstMove){
            firstMove = false;
        }else{
            distanciaRecorrida += (float) Math.sqrt(Math.pow(xPos - this.xPos,2) + Math.pow(yPos - this.yPos,2));
        }
        recorrido=null;
        recorrido= new Line(this.xPos+6/2,this.yPos+6/2,xPos+6/2,yPos+6/2);
        this.xPos=xPos;
        this.yPos=yPos;
        representacion.makeInvisible();
        representacion.locate(xPos,yPos);
        representacion.makeVisible();
        recorrido.makeVisible();
    }
    
    /**
     * Metodo que muestra la representación del guardia en el canvas
     */
    
    public void makeVisible(){
        representacion.makeVisible();
    }
    
      /**
     * Metodo que oculta la representación del guardia en el canvas
     */
    public void makeInvisible(){
        representacion.makeInvisible();
    }
    
    public float getDistanciaRecorrida(){
        return distanciaRecorrida;
    }
  
}
