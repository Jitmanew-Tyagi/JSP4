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

   //Topological Sort
   public static void topologicalOrder(int n, ArrayList<Edge>[] graph) {
      boolean[] visited = new boolean[n];
      ArrayList<Integer> ans = new ArrayList<>();
      for(int i = 0; i < n; i ++) {
         if(visited[i] == false) topoDfs(graph, i, visited, ans);
      }
      System.out.println(ans);
   }

   public static void kahns(ArrayList<Edge>[] graph) {
      int n = graph.length;
      int[] indegree = new int[n];
      ArrayList<Integer> ans = new ArrayList<>();
      for(int i = 0; i < n; i ++) {
         for(Edge e : graph[i]) indegree[e.nbr] ++;
      }
      Queue<Integer> que = new LinkedList<>();
      for(int i = 0; i < n; i ++) {
         if(indegree[i] == 0) que.add(i);
      }
      while(!que.isEmpty()) {
         int rem = que.remove();
         ans.add(rem);
         for(Edge e : graph[rem]) {
            if(--indegree[e.nbr] == 0) que.add(e.nbr);
         }
      }
      System.out.println(ans.size() == n ? ans : "cycle");
   }

   int[] par, size;

   public static int findPar(int u) {
      return par[u] == u ? u : (par[u] = findPar(par[u]));
   }

   public static void merge(int u, int v) {
      if(size[u] >= size[v]) {
         par[v] = u;
         size[u] += size[v];
      } else {
         par[u] = v;
         size[v] += size[u];
      }
   }

   public static void unionFind(int n, int[][] graph) {
      par = new int[n];
      size = new int[n];

      for(int i = 0; i < n; i ++) par[i] = i;
      for(int i = 0; i < n; i ++) size[i] = 1;

      for(int[] edge : graph) {
         int u = edge[0], v = edge[1];
         int p1 = findPar(u), p2 = findPar(v);
         if(p1 != p2) {
            merge(p1, p2);
         } else{
            System.out.println("Cycle");
         }
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
      kahns(graph);
   }

}