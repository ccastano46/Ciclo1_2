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
    
    public Trap(String color, int[][] polygon){
        super(color,polygon);
    }
    
    public boolean displayFakeSculpture(String type,int xPos, int yPos){
        boolean proceso = false;
        if(representacion.contains(xPos,yPos)){
            fakeSculpture = new Sculpture("cyan", xPos, yPos, type);
            fakeSculpture.makeVisible();
            proceso = true;
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe una escultura en el cuarto");
        }
        desaparecerShy();
        return proceso;
    }
    
    public void steal() throws RoomException {
        if(fakeSculpture == null){
            super.steal();
        }else{
          if(fakeSculpture.getType().equals("heavy")) throw new RoomException(RoomException.ESCULTURA_PESADA);
          fakeSculpture.makeInvisible();
          fakeSculpture = null;  
        }
    }
    
    public int[] getSculptureLocation(){
        if(fakeSculpture == null){
            System.out.println("in2");
            return super.getSculptureLocation();
        }
        return fakeSculpture.getPos();
    }
}
