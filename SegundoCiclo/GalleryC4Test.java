
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The test class GalleryTest.
 */
public class GalleryC4Test{
    @Test
    public void shouldNotStealHeavy(){
        Gallery galeria = new Gallery(700,700);
        galeria.buildRoom("red", new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}});
        galeria.arriveGuard("red");
        galeria.moveGuard("red", 10,10);
        galeria.displaySculpture("heavy","red", 70,10);
        galeria.steal();
        assertTrue(galeria.sculptureIsPresent("red"));
        galeria.buildRoom("blue", new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}});
        galeria.arriveGuard("blue");
        galeria.moveGuard("blue", 10,10);
        galeria.displaySculpture("shy","blue", 70,10);
        galeria.steal();
        assertFalse(galeria.sculptureIsPresent("blue"));
        galeria.buildRoom("black", new int[][]{{0,4,4,5,5,7,7,3,3,2,0},{0,0,1,1,0,0,2,2,1,2,2}});
        galeria.arriveGuard("black");
        galeria.moveGuard("black",1,1);
    }
    
    @Test
    public void MagicalcouldBeOutside(){
        Gallery galeria = new Gallery(700,700);
        galeria.buildRoom("red", new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}});
        galeria.arriveGuard("magical","red");
        galeria.moveGuard("red",100,150);
        assertTrue(galeria.ok());
        //Cuarto rojo tiene el mismo poligono que el azul
        galeria.buildRoom("blue", new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}});
        galeria.arriveGuard("normal","blue");
        galeria.moveGuard("blue",100,150);
        assertFalse(galeria.ok());
        galeria.moveGuard("blue",10,10);
        assertTrue(galeria.ok());
        
        galeria.buildRoom("yellow", new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}});
        galeria.arriveGuard("magical","yellow");
        galeria.moveGuard("yellow",10,10);
        assertTrue(galeria.ok());
        ////Cuarto amarillo tiene el mismo poligono que el naranja
        galeria.buildRoom("orange", new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}});
        galeria.arriveGuard("orange");
        galeria.moveGuard("orange",10,10);
        assertFalse(galeria.ok());
        //El guardia amarillo esta dentro de la habitación azul.
        assertEquals(galeria.guardLocation("blue")[0], galeria.guardLocation("yellow")[0]);
        assertEquals(galeria.guardLocation("blue")[1], galeria.guardLocation("yellow")[1]);
    }
    
    @Test
    public void shouldDisappear(){
        Gallery galeria = new Gallery(700,700);
        galeria.buildRoom("blue", new int[][]{{1,3,7,7,10,10,15,15,13,9,2},{1,3,3,6,7,3,3,9,6,9,9}});
        galeria.arriveGuard("blue");
        galeria.moveGuard("blue",5,7);
        galeria.displaySculpture("shy","blue",4,5);
        assertTrue(galeria.guardIsWatching("blue"));
        assertFalse(galeria.sculptureVisible("blue"));
        galeria.moveGuard("blue",14,4);
        assertTrue(galeria.sculptureVisible("blue"));
    }
    
    @Test
    public void standbyShouldNotHaveSculpture(){
        Gallery galeria = new Gallery(700,700);
        galeria.buildRoom("standby","blue", new int[][]{{1,3,7,7,10,10,15,15,13,9,2},{1,3,3,6,7,3,3,9,6,9,9}});
        galeria.displaySculpture("shy","blue",4,5);
        galeria.displaySculpture("blue",5,7);
        galeria.displaySculpture("heavy,","blue",14,4);
        galeria.displaySculpture("heavy,","blue",10,10);
        assertFalse(galeria.sculptureIsPresent("blue"));
    }
    
    @Test
    public void shouldNotHaveAlarm(){
        Gallery galeria = new Gallery(700,700);
        galeria.buildRoom("unprotected","blue", new int[][]{{1,3,7,7,10,10,15,15,13,9,2},{1,3,3,6,7,3,3,9,6,9,9}});
        galeria.buildRoom("standby","yellow", new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}});
        galeria.buildRoom("red", new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}});
        galeria.alarm("yellow", true);
        galeria.alarm("blue", true);
        galeria.alarm("red", true);
        assertEquals(galeria.roomsOnAlert(),new String[] {"red","yellow"});
        galeria.alarm("blue", false);
        galeria.arriveGuard("blue");
        galeria.moveGuard("blue",14,4);
        galeria.displaySculpture("blue",4,5);
        galeria.steal();
        assertFalse(galeria.sculptureIsPresent("blue"));
        assertEquals(galeria.roomsOnAlert(),new String[] {"red","yellow"});
    }
}
