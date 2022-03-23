    class TestClass {
        public static void main(String args[] ) throws Exception {

            Scanner scn = new Scanner(System.in);
            int N = scn.nextInt();
            int M = scn.nextInt();
            long K = scn.nextLong();
            ArrayList<int[]>  edges = new ArrayList<>();
            for(int i = 0; i < M; i ++) {
                int u = scn.nrxtInt(), v = scn.nextInt(), w = scn.nextInt();
                edges.add(new int[]{u, v, w});
            }
            Collections.sort(edges, (a, b) -> {
                return a[2] - b[2];
            });

            int[] par = new int[N + 1];
            for(int i = 0; i < N; i ++) par[i] = i;
            int totalCost = 0, components = N, conversion = 0;
            List<Integer> roads = new ArrayList<>();
            for(int[] e : edges) {
                int u = e[0], v= e[1], w = e[2];
                int p1 = findPar(u), p2 = findPar(v);
                if(p1 != p2) {
                    par[p1] = p2;
                    totalCost += w;
                    roads.add(w);
                    components --;
                }
            }
            if(components != 1) return -1;
            for(int i = roads.size() - 1; i >= 0; i --) {
                if(totalCost > K) {
                    totalCost -= roads.get(i);
                    totalCost += 1;
                    conversion ++;
                } else break;
            }
            return totalCost > K ? -1 : conversion;
        }
    }
















