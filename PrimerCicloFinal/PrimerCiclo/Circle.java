import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final float PI=3.1416f;
    
    private short diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
     /**
     * Metodo constructor de objeto Circle con parametros dados.
     * @param diametro del circulo
     * @param posicion sobre el plano x en canvas.
     * @param posicion sobre el plano y en canvas.
     * @param color del circulo.
     */
     public Circle(short diameter,int xPosition, int yPosition, String color){
        this.diameter=(short)diameter;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        this.color=color;
        isVisible=false;
    }

       
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
    }
    
     /**
     * Modifica los valores de la ubicacion de la figura sobre Canvas.
     * @param posicion en pixeles sobre el eje x.
     * @param posicion en pixeles sobre el eje y.
     */
    public void locate(int xPosition, int yPosition){
        this.yPosition = yPosition;
        this.xPosition = xPosition;
       
    }
    
    /**
     * Retorna la posicion en el eje y.
     */
    public int getYPosition(){
        return yPosition;
    }
    
    /**
     * retorna la posicion en el eje x.
     */
    public int getXPosition(){
        return xPosition;
    }

}
