import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The test class GalleryTest.
 */
public class GalleryC2Test
{
    @Test
    public void shouldCreate(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,80},new int[] {180,140});
        assertEquals(gl.rooms(),new String[]{"black"});
    }
    
    @Test
    public void shouldNotCreate(){
       Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,80},new int[] {180,140});
       assertNotEquals(gl.rooms(),new String[]{"white"});
       gl.buildRoom("orange",new int[][] {{0,0,100,100},{0,100,100,100}});
       assertFalse(gl.ok());
       assertNotEquals(gl.rooms(),new String[]{"black","orange"});
    }
    
    @Test
    public void shouldSteal(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,80},new int[] {180,140});
        gl.steal();
        assertTrue(gl.ok());
    }
    
    @Test
    public void shouldNotSteal(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,140},new int[] {180,140});
        gl.steal();
        assertFalse(gl.ok());
    }
    
    @Test
    public void shouldDistanceTraveled(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,80},new int[] {180,140});
        gl.moveGuard("black",100,100);
        assertEquals(Math.round(28.28 * 100.0) / 100.0 ,Math.round(gl.distanceTraveled("black") * 100.0) / 100.0,0);
        gl.moveGuard("black",130,140);
        assertEquals(Math.round(78.28 * 100.0) / 100.0 ,Math.round(gl.distanceTraveled("black") * 100.0) / 100.0,0);
    }
    
    @Test
    public void shouldNotDistanceTraveled(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,80},new int[] {180,140});
        gl.moveGuard("black",80,100);
        gl.moveGuard("black",80,80);
        assertNotEquals(20.0,gl.distanceTraveled("black"));
    }
    
    @Test
    public void shouldSculptureIsPresent(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,100},new int[] {180,140});
        gl.steal();
        assertTrue(gl.sculptureIsPresent("black"));
    }
    
    @Test
    public void shouldNotSculptureIsPresent(){
        Gallery gl = new Gallery(new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}},new int[] {80,100},new int[] {180,140});
        gl.steal();
        assertNotEquals(false,gl.sculptureIsPresent("black"));
    }
    
    @Test
    public void shouldRoomsWithFalseAlarm(){
        Gallery gl = new Gallery(1000,1000);
        gl.buildRoom("black",new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}});
        gl.displaySculpture("black",180,140);
        gl.arriveGuard("black");
        gl.buildRoom("blue",new int[][]{{300,300,400,450,350,350},{50,100,150,100,100,50}});
        gl.displaySculpture("blue",320,80);
        gl.arriveGuard("blue");
        gl.alarm("blue",true);
        assertEquals(new String[] {"blue"},gl.roomsWithFalseAlarm());
    }
    
    @Test
    public void shouldNotRoomsWithFalseAlarm(){
        Gallery gl = new Gallery(1000,1000);
        gl.buildRoom("black",new int[][]{{60,80,160,200,140,120,180,120,100},{40,140,200,120,120,100,100,60,80}});
        gl.displaySculpture("black",180,140);
        gl.arriveGuard("black");
        gl.buildRoom("blue",new int[][]{{300,300,400,450,350,350},{50,100,150,100,100,50}});
        gl.displaySculpture("blue",320,80);
        gl.arriveGuard("blue");
        assertEquals(new String[] {},gl.roomsWithFalseAlarm());
    }
}
