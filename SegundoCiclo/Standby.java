import javax.swing.JOptionPane;
/**
 * Habitación que no tiene una escultura
 * 
 * @author Camargo-Castaño
 * @version 2/04/2023
 */
public class Standby extends Room
{
    /**
     * Constructor del cuarto standby
     * @param color, color con el cual se va a identificar el cuarto
     * @param polygon, vertices que componen el poligono del cuarto
     */
    public Standby(String color, int[][] polygon){
        super(color, polygon);
    }
    
    /**
     * Sobre escritrura del metodo displaySculpture ya que, en este tipo de habitación no tiene escultura.
     * @param xPos, posición x del cuarto
     * @param yPos, posicion y del cuarto
     */
    public boolean displaySculpture(int xPos, int yPos){
        JOptionPane.showMessageDialog(null, "En una habitación Standby no se pueden colocar esculturas.");
        return false;
    }
}
