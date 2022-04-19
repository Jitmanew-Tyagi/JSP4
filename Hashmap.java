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
            if(tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = tail.next;
            }
            noe ++;
        }

        public Node getFirst() {
            return head;
        }

        public Node removeFirst() {
            Node temp = head;
            if(head != null) {
                head = head.next;
                noe --;
            }
            return temp;
        }
    }

    private linkedlist[] containers;
    private int sohm = 0;

    public void assignValues(int size) {
        containers = new linkedlist[size];
        for(int i = 0; i < size; i ++) containers[i] = new linkedlist();
    }

    Hashmap() {
        assignValues(10);
    }

    public void put(Integer key, Integer value) {

    }

    public void putIfAbsent (Integer key, Integer value) {

    }

    public ArrayList<Integer> keySet() {

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
            group.addLast(group.removeFirst())
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