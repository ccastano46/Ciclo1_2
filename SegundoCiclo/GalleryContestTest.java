import static org.junit.Assert.*;

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
    public void shouldSolve(){
        //assertEquals(1.0,new GalleryContest().solve(new int[][] {{},{}},new int[] {},new int[] {}));
        //Sample imput 1
        assertEquals(58.137767414994535,new GalleryContest().solve(new int[][] {{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}},new int[] {10,10},new int[] {70,10}));
        //Sample imput 2
        assertEquals(2.0,new GalleryContest().solve(new int[][] {{0,4,4,5,5,7,7,3,3,2,0},{0,0,1,1,0,0,2,2,1,2,2}},new int[] {1,1},new int[] {6,1}));
        //Extra Samples
        assertEquals(58.14,new GalleryContest().solve(new int[][] {{10,30,30,70,70,90,90,10},{10,10,40,40,10,10,60,60}},new int[] {20,20},new int[] {80,20}));
    }
    
    @Test
    public void shouldNotSolve(){
        assertEquals(60,new GalleryContest().solve(new int[][] {{0,20,20,60,60,80,80,0},{0,0,30,30,0,0,50,50}},new int[] {10,10},new int[] {70,10}));
        //Sample imput 2
        assertEquals(1.9,new GalleryContest().solve(new int[][] {{0,4,4,5,5,7,7,3,3,2,0},{0,0,1,1,0,0,2,2,1,2,2}},new int[] {1,1},new int[] {6,1}));
        //Extra Samples
        assertEquals(1.0,new GalleryContest().solve(new int[][] {{10,30,30,70,70,90,90,10},{10,10,40,40,10,10,60,60}},new int[] {20,20},new int[] {80,20}));
    }
    
}
