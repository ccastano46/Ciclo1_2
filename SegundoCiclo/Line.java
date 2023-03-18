import java.awt.*;
/**
 * Una linea que se puede manipular y se dibuja a si misma en Canvas
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */
public class Line{
    private double xpointPos1;
    private double xpointPos2;
    private double ypointPos1;
    private double ypointPos2;
    private String color;
    private boolean isVisible;
    
     /**
     * Construye un linea por medio de dos puntos, tiene caracteristica
     * de color y visibilidad.
     */
    public Line(double xpointPos1 ,double ypointPos1,double xpointPos2, double ypointPos2){
        this.xpointPos1=xpointPos1;
        this.xpointPos2=xpointPos2;
        this.ypointPos1=ypointPos1;
        this.ypointPos2=ypointPos2;
        color="blue";
        isVisible=false;
    }
    
    /**
     * Make this line visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Draw the line with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.geom.Line2D.Double(xpointPos1, ypointPos1, xpointPos2, ypointPos2));
            canvas.wait(10);
        }
    }
    
    /**
     * Erase the line on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    public boolean intersectsLine(Line l){
        return new java.awt.geom.Line2D.Double(xpointPos1, ypointPos1, xpointPos2, ypointPos2).intersectsLine(l.xpointPos1,l.ypointPos1,l.xpointPos2,l.ypointPos2);
    }
    
    /**
     * Función que calcula la función de la recta.
     * Se retorna un arreglo con elementos tipo double que indican la pendiente de la recta y el "Corte de intersección con el eje y"
     */
    
    public double[] calculateFunction(){
        double[] funcion = new double[2];
        funcion[0] = (ypointPos2 - ypointPos1) / (xpointPos2 - xpointPos1);
        funcion[1] = ypointPos2 - funcion[0] * xpointPos2;
        return funcion;
    }
    
    public double getX1(){
        return xpointPos1;
    }
    
    public double getX2(){
        return xpointPos2;
    }
    
    public double getY1(){
        return ypointPos1;
    }
    
    public double getY2(){
        return ypointPos2;
    }
    
    public float longitud(){
        return (float) Math.sqrt(Math.pow(xpointPos2 - xpointPos1,2) + Math.pow(ypointPos2 - ypointPos1,2));
    }
    
}