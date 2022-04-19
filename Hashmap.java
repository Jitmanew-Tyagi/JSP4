import java.util.*;
public class Hashmap {

    private class Node {
        Integer key = 0;
        Integer value = 0;
        Node next = null;

        Node (Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private class linkedlist {
        public Node head = null, tail = null;
        public int noe = 0;
        
        public int size() {
            return noe;
        } 

        public void addLast(Node node) {
            if(head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            noe ++;
        }

        public Node getFirst() {
            return head;
        }

        public Node removeFirst() {
            Node temp = head;
            if(noe == 0) head = tail = null;
            if(head != null) {
                head = head.next;
            }
            noe --;
            return temp;
        }
    }

    private linkedlist[] containers;
    public int sohm = 0;

    public void assignValues(int size) {
        containers = new linkedlist[size];
        sohm = 0;
        for(int i = 0; i < size; i ++) containers[i] = new linkedlist();
    }

    Hashmap() {
        assignValues(10);
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < containers.length; i ++) {
            linkedlist group = containers[i];
            
            int size = group.size();
            while(size --> 0) {
                Node node = group.removeFirst();
                sb.append("(" + node.key + "=" + node.value + ") ");
                group.addLast(node);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    private void rehash(){
        linkedlist[] backup = containers;
        assignValues(2 * backup.length);

        for(int i = 0; i < backup.length; i ++) {
            linkedlist group = backup[i];
            int size = group.size();

            while(size --> 0) {
                Node node  = group.removeFirst();
                put(node.key, node.value);
            }
        }
        System.out.println("rehash called");
    }

    public void put(Integer key, Integer value) {
        boolean isKey = containsKey(key);
        if(isKey) {
            linkedlist group = group(key);
            group.head.value = value;
        } else{
            linkedlist group = group(key);
            group.addLast(new Node(key, value));
            sohm ++;
            double lambda = group.size() / (containers.length * 1.0);
            if (lambda > 0.6) rehash();
        }
    }

    public void putIfAbsent (Integer key, Integer value) {
        if(!containsKey(key)) put(key, value);
    }

    public ArrayList<Integer> keySet() {
        ArrayList<Integer> keys = new ArrayList<>();
        for(int i = 0; i < containers.length; i ++) {
            linkedlist group = containers[i];
            int size = group.size();
            while(size --> 0) {
                Node node = group.removeFirst();
                keys.add(node.key);
                group.addLast(node);
            }
        }
        return keys;
    }

    public boolean isEmpty() {
        return sohm == 0;
    }

    public Integer remove(Integer key) {
        boolean isKey = containsKey(key);
        if(!isKey) 
            return null;
        linkedlist group = group(key);
        Node rv = group.removeFirst();
        sohm --;
        return rv.value;
    }

    public int getOrDefault(Integer key, Integer def) {
        return containsKey(key) ? get(key) : def;
    }

    public int get(Integer key) {
        boolean isKey = containsKey(key);
        linkedlist group = group(key);
        return isKey ? group.head.value : null;
    }

    public boolean containsKey(Integer key) {
        linkedlist group = group(key);
        int size = group.size();
        while(size --> 0) {
            if(group.getFirst().key == key) return true;
            group.addLast(group.removeFirst());
        }
        return false;
    }

    private linkedlist group(Integer key) { // return container alloted to a key;
        int code = hashCode(key);
        return containers[code];
    }

    private int hashCode(Integer key) {
        int val = key.hashCode();
        return val % containers.length;
    }
}