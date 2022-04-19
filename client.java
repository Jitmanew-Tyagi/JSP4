public class client {
    public static void main(String[] args) {
        Hashmap hm = new Hashmap();

        for(int i = 0; i < 62; i ++) 
            hm.put(i, 10 * i);

        System.out.println(hm.sohm);
        hm.display();
    }
}