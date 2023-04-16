import javax.swing.JOptionPane;
/**
 * Write a description of class AceptacionGallery here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AceptacionGallery
{
    
    public static void main(String[] args){
        Gallery galeria = new Gallery(500,600);
        galeria.buildRoom("red", new int[][]{{20,40,40,80,80,100,100,20},{0,0,30,30,0,0,50,50}});
        galeria.buildRoom("unprotected", "blue", new int[][] {{20,100,140,120,100,80,20},{580,600,560,520,540,520,540}});
        galeria.buildRoom("orange",new int[][]{{180,180,140,160,160,200,200,180},{300,320,300,280,240,240,260,280}});
        galeria.makeVisible();
        esperar(15);
        galeria.arriveGuard("magical","red");
        esperar(15);
        galeria.displaySculpture("shy","red",90,10);
        galeria.moveGuard("red",200,240);
        esperar(15);
        JOptionPane.showMessageDialog(null, "La menor distancia que debe recorrer el guardia de la habitación roja es: " + galeria.shortestDistance("red"));
        esperar(15);
        galeria.moveGuard("red",90,30);
        esperar(15);
        galeria.moveGuard("red",200,40);
        esperar(7);
        galeria.arriveGuard("orange");
        galeria.displaySculpture("orange", 185, 250);
        esperar(7);
        galeria.arriveGuard("magical","blue");
        galeria.displaySculpture("heavy","blue",80,540);
        esperar(15);
        galeria.moveGuard("red",90,30);
        galeria.moveGuard("blue",185,270);
        esperar(10);
        galeria.steal();
        
    }
    
    private static void esperar(int segundos){
        try{
            Thread.sleep(segundos*100);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
