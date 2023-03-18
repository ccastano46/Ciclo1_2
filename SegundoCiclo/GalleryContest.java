
/**
 * Write a description of class GalleryContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GalleryContest
{
    public GalleryContest(){}
    
    public float solve(int[][] polygon,int[] guard,int[] sculpture){
        Gallery galleryContest = new Gallery(polygon,guard,sculpture);
        galleryContest.solveProblem();
        //Creo que solve problem deberia retornar los movimientos del guardia
        //mover el guardia en el cuarto (for...)
        return galleryContest.distanceTraveled("black");
    }
    
    public void simulate(int[][] polygon,int[] guard,int[] sculpture){
        Gallery galleryContest = new Gallery(polygon,guard,sculpture);
        galleryContest.solveProblem();
        //mover el guardia en el cuarto (for...)
        
    }
}
