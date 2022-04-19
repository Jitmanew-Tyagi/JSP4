import java.io.*;
import java.util.*;

public class Graph {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }


   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      int[][] arr = new int[vtces][vtces];
      for(int[] a : arr) Arrays.fill(Integer.MAX_VALUE);
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
        //  graph[v1].add(new Edge(v1, v2, wt));
        arr[v1][v2] = wt;
        floyd(arr);
      }
      public static void floyd(int[][] arr) {
          int n = arr.length;
          for(int k = 0; k < n; k ++) {
            for(int i = 0; i < n; i ++) {
                for(int j = 0; j < n; j ++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]); 
                }
            }
          }
      }
   }

}