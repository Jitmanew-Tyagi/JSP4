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
   //rmwa
   public static void bfs(ArrayList<Edge>[] graph, int src, boolean[] visited) {
      Queue<Integer> que = new LinkedList<>();
      que.add(src);
      int level = 0;
      while(!que.isEmpty()) {
         int size = que.size();
         System.out.print("Level: " + level +" --> " );
         while(size --> 0) {
            int rem = que.remove();
            if(visited[rem]) {
               System.out.print("cycle ");
               continue;
            }
            visited[rem] = true;
            System.out.print(rem + " ");
            for(Edge e : graph[rem]) if(!visited[e.nbr]) que.add(e.nbr);
         }
         level ++;
         System.out.println();
      }
   } 

   public static void topoDfs(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> ans) {
      visited[src] = true;
      for(Edge e : graph[src]) if(!visited[e.nbr]) topoDfs(graph, e.nbr, visited, ans);
      ans.add(src);
   }

   //Tolological Sort
   public static void topologicalOrder(int n, ArrayList<Edge>[] graph) {
      boolean[] visited = new boolean[n];
      ArrayList<Integer> ans = new ArrayList<>();
      for(int i = 0; i < n; i ++) {
         if(visited[i] == false) topoDfs(graph, i, visited, ans);
      }

      System.out.println(ans);
   }


   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         // graph[v2].add(new Edge(v2, v1, wt));
      }
	//   boolean[] visited = new boolean[vtces];
   //   for(int i = 0; i < vtces; i ++) {
   //      if(!visited[i]) bfs(graph, i, visited); 
   //   }
      // write your code here
      topologicalOrder(vtces, graph);
   }
}