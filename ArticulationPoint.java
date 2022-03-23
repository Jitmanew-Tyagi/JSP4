import java.io.*;
import java.util.*;

public class ArticulationPoint {
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

    static int[] par, dis, low;
    static boolean[] vis, AP;
    static int time = 0;

    public static void dfs(ArrayList<Edge>[] graph, int src) {
        vis[src] = true;
        dis[src] = low[src] = time ++;
        int count = 0;
        for(Edge e : graph[src]) {
            if(e.nbr == par[src]) continue;
            if(vis[e.nbr]) {
                low[src] = Math.min(low[src], dis[e.nbr]);
            } else{
                par[e.nbr] = src;
                dfs(graph, e.nbr);
                low[src] = Math.min(low[src], low[e.nbr]);
                if(par[src] == -1) {
                    count ++;
                    if(count >= 2) AP[src] = true;
                }
                else {
                    if(dis[src] <= low[e.nbr]) AP[src] = true;
                }
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
         int v1 = Integer.parseInt(parts[0]) - 1;
         int v2 = Integer.parseInt(parts[1]) - 1;
         int wt = 0;
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }
      int N = vtces;
      par = new int[N];
      dis = new int[N];
      low = new int[N];
      vis = new boolean[N];
      AP = new boolean[N];

      par[0] = -1;
      for(int i = 0; i < N; i ++) {
          if(!vis[i]) dfs(graph, i);
      }
      int noOAP = 0;
      for(boolean b : AP) noOAP += b ? 1 : 0;
    //   System.out.println(noOAP);
      for(boolean b : AP) System.out.print(b + " ");
   }

}