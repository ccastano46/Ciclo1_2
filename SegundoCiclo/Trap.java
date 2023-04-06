import javax.swing.JOptionPane;
/**
 * Write a description of class Trap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trap extends Room
{
    private Sculpture fakeSculpture;
    /**
     * Metodo Constructor de la clase Trap usando la el metodo de su clase padre
     * @param color, color con el cual se va a identificar el cuarto
       * @param polygon, vertices que componen el poligono del cuarto
    **/
    public Trap(String color, int[][] polygon){
        super(color,polygon);
    }
    /**
     * Metodo Constructor de la clase Trap usando la el metodo de su clase padre
     * @param type , tipo de la nueva escultura.
     * @param xPos, posicion en x de la escultura.
     * @param yPos, posicion en y de la escultura.
    **/
    public boolean displayFakeSculpture(int xPos, int yPos){
        boolean proceso = false;
        if(representacion.contains(xPos,yPos)){
            fakeSculpture = new Sculpture("cyan", xPos, yPos, "normal");
            fakeSculpture.makeVisible();
            proceso = true;
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe una escultura en el cuarto");
        }
        return proceso;
    }
    
    /**
     * Metodo sobrecargado de la clase padre donde se roba una escultura, pero tendra la proriedad de robar
     * la esculutura falsa si existe una.
    **/
    public void steal() throws RoomException {
        if(fakeSculpture == null){
            super.steal();
        }else{
          fakeSculpture.makeInvisible();
          fakeSculpture = null;  
        }
    }
    
    /**
     * Metodo sobrecargado de la clase padre, ahora tiene la proriedad de
     * retornar la posicion de la escultura falsa si existe
     * @return las posiciones de x y en y de la escultura falsa
    **/
    public int[] getSculptureLocation(){
        if(fakeSculpture == null){
            System.out.println("in2");
            return super.getSculptureLocation();
        }
        return fakeSculpture.getPos();
    }
}
