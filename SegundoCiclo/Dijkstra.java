import java.util.*;
/**
 * Write a description of class Dijkstra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dijkstra
{
 public  float distance[] = new float[10]; //Vector para almacenar las distancias
 public  float cost[][] = new float[10][10]; // Matriz cuadrada
 /**
 public Dijkstra(int numV, float[][] costos){
     distance = new float[numV];
     cost = costos;
 }
 */
 public void calc(int n, int s)
 {
  int flag[] = new int[n];
  int i,minpos=0,k,c;
  float minimum;
  
  for(i=0;i<n;i++)
  {  
      flag[i]=0; 
      this.distance[i]=this.cost[s][i]; 
  }
  c=1;
  while(1<n)
  {
   minimum=500000000;
   for(k=0;k<n;k++)
   { 
          if(this.distance[k]<minimum && flag[k]!=1)
       {
        minimum=this.distance[i];
        minpos=k;
       }
      } 
            flag[minpos]=1;
      c++;
      for(k=0;k<n;k++){
       if(this.distance[minpos]+this.cost[minpos][k] <  this.distance[k] && flag[k]!=1 )
       this.distance[k]=this.distance[minpos]+this.cost[minpos][k];
   }   
  } 
  
 }
 public static void main(String args[])
 {
  int nodes,source,i,j;
  Scanner in = new Scanner(System.in);
  System.out.println("Enter the Number of Nodes \n");
  nodes = in.nextInt();
  Dijkstra d = new Dijkstra();
  System.out.println("Enter the Cost Matrix Weights: \n");
        for(i=0;i<nodes;i++)
          for(j=1;j<=nodes;j++)
          {
            d.cost[i][j]=in.nextInt();
            if(d.cost[i][j]==0)
              d.cost[i][j]=500000000;
          }
  System.out.println("Enter the Source Vertex :\n");
  source=in.nextInt();
  
  d.calc(nodes, source);
  System.out.println("The Shortest Path from Source \t"+source+"\t to all other vertices are : \n");
        for(i=0;i<nodes;i++)
          if(i!=source)
  System.out.println("source :"+source+"\t destination :"+i+"\t MinCost is :"+d.distance[i]+"\t");
        
  
 } 
}