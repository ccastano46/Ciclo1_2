import java.awt.*;
/**
 * Un poligono que se puede manipular y se dibuja a si mismo en Canvas.
 * @Camargo - Castaño
 * @version 1.0. (18 Febrero 2023)
 */
public class Polygon{
    
    private int[] xPoints;
    private int[] yPoints;
    private int vertices;
    private String color;
    private boolean isVisible;
    
    
    private Rectangle finalBound;
    

    /**
     * Construye un poligono dado sus vertices, tiene caracteristicas
     * de color y visibilidad.
     */
    public Polygon(int xPoints[], int yPoints[], int vertices, String color){
        this.xPoints=xPoints ;
        this.yPoints=yPoints;
        this.vertices=vertices;
        this.color = color;
        isVisible = false;
    }
    
    /**
     * Make this polygon visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        finalBound.changeColor("black");
        draw();
    }
    
    /**
     * Make this polygon invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta", "black", "orange", "pink" and "cyan".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Draw the polygon with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new java.awt.Polygon(xPoints, yPoints, vertices));
            canvas.wait(10);
        }
    }
    
    /**
     * Dibuja un rectangulo que abarca el espacio donde esta posicionado 
     * el poligono con un ligero aumento de 2 px.
     */
    public void getBound(){
            Canvas canvas = Canvas.getCanvas();
            java.awt.Rectangle boundR= new java.awt.Polygon(xPoints, yPoints, vertices).getBounds();
            boundR.grow(2,2);
            finalBound = new Rectangle((int)boundR.getHeight(),(int)boundR.getWidth(),(int)boundR.getX(),(int)boundR.getY(),"black");
            finalBound.makeVisible();
    }
    
    /**
     * Dibuja un rectangulo que abarca el espacio donde esta posicionado 
     * el poligono con un ligero aumento de 2 px, pero haciendo la
     * figura Polygon imperceptible.
     */
    public void makeBoundInvisible(){
        finalBound.changeColor("white");
    }
    
    /**
     * Verifica un punto de enteros se encuentra dentro de la figura Polygon.
     * @param xPos Posicion en x del punto
     * @param yPos Posicion en y del punto
     * @return boolean true si se encunetra dentro, false si se encuentra afuera 
     */
    public boolean contains(int xPos, int yPos){
        return new java.awt.Polygon(xPoints, yPoints, vertices).contains(xPos,yPos);
    }
    
     public boolean inside(double xPos, double yPos){
        return new java.awt.Polygon(xPoints, yPoints, vertices).inside((int)xPos,(int)yPos);
    }
    
    /**
     * Verifica un punto con valores decimales se encuentra dentro de la figura Polygon.
     * @param xPos Posicion en x del punto
     * @param yPos Posicion en y del punto
     * @return boolean true si se encunetra dentro, false si se encuentra afuera 
     */
    public boolean contains(double xPos, double yPos){
        return new java.awt.Polygon(xPoints, yPoints, vertices).contains(xPos,yPos);
    }
    
     
    /**
     * Erase the polygon on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    
}
