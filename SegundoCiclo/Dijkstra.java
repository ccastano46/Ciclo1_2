import java.util.*;
/**
 * Write a description of class DIjkstra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Dijkstra
{
 public  float distance[]; //Distancias del origen a cada uno de sus vecinos directos
 public  float cost[][];
 
 public Dijkstra(int numNodos){
     distance = new float[numNodos + 1];
     cost = new float[numNodos + 1 ][numNodos + 1];
 }
 
 public void calc(int origen)
 {
  int flag[] = new int[distance.length+1];
  int i,minpos=1,k,c;
  float minimum;
  
  for(i=1;i<=distance.length;i++)
  {  
      flag[i]=0; 
      this.distance[i]=this.cost[origen][i]; 
  } 
  c=2;
  while(c<=distance.length)
  {
   minimum = (float) Double.POSITIVE_INFINITY;
   for(k=1;k<=distance.length;k++)
   { 
          if(this.distance[k]<minimum && flag[k]!=1)
       {
        minimum=this.distance[i];
        minpos=k;
       }
      } 
            flag[minpos]=1;
      c++;
      for(k=1;k<=distance.length;k++){
       if(this.distance[minpos]+this.cost[minpos][k] <  this.distance[k] && flag[k]!=1 )
       this.distance[k]=this.distance[minpos]+this.cost[minpos][k];
   }   
  } 
  
 }
 
 
}