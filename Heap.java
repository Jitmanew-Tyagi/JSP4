import java.util.*;
public class Heap {
    private ArrayList<Integer> arr;

    public heap(int[] data) {
        arr = new  ArrayList<>();
        for(int i : data) arr.add(i);

        for(int i = arr.size() - 1; i >= 0; i --) 
            downheapify(i);
    }

    public boolean compareTo(int i, int j) {
        return arr.get(i) > arr.get(j);
    }

    public void downheapify(int pi) {
        int lci = 2 * pi + 1, rci = 2 * pi + 2, maxIdx = pi;

        if(lci < arr.size() && compareTo(lci, maxIdx)) maxIdx = lci;
        if(rci < arr.size() && compareTo(rci, maxIdx)) maxIdx = rci;
        if(maxIdx != pi) {
            swap(pi, maxIdx);
            downheapify(maxIdx);
        }
    }

    public int remove() {
        int ans = arr.get(0);
        swap(0, arr.size() - 1);
        arr.remove(arr.size() - 1);
        downheapify(0);
        return ans;
    }

    public void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if(pi >= 0 && compareTo(ci, pi)) {
            swap(ci, pi);
            upheapify(pi);
        }
    }

    public void swap(int i, int j) {
        int x = arr.get(i), y = arr.get(j);
        arr.set(i, y); 
        arr.set(j, x); 
    }

    public  void add(int data) {
        arr.add(data);
        upheapify(arr.size() - 1);
    }
}
