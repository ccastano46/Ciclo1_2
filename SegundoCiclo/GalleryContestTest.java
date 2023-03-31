import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of class GalleryContestTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GalleryContestTest{
    @Test
    public void shouldEpsilon(){
        GalleryContest gallery = new GalleryContest();
        assertTrue(Math.abs(gallery.solve(new int[][]{{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}},new int[] {10,10}, new int[]{70,10}) - 58.137767414994535)/58.137767414994535 <= 0.0001);
        assertTrue(Math.abs(gallery.solve(new int[][]{{0,4,4,5,5,7,7,3,3,2,0},{0,0,1,1,0,0,2,2,1,2,2}},new int[] {1,1}, new int[]{6,1}) - 2)/2 <= 0.0001);
    }
}
