class LRUCache {
    
    class Node{
        int key, val;
        Node prev, next;
        
        Node() {
            key = val = -1;
            prev = next = null;
        }
        
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }  
    
    public void addNode(Node node) {
        Node fcn = head.next;
        head.next = node;
        node.prev = head;
        
        node.next = fcn;
        fcn.prev = node;
    }
    
    public void remove(Node node) {
        Node prev = node.prev, fwd = node.next;
        
        prev.next = fwd;
        fwd.prev = prev;
        
        node.next = null;
        node.prev = null;
    }
    
    public void makePriority(Node node) {
        remove(node);
        addNode(node);
    }
    
    HashMap<Integer, Node> cache;
    int cap;
    Node head, tail;
    
    LRUCache(int capacity) {
        this.cap = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;
        else {
            makePriority(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null) {
            Node tba = new Node(key, value);
            if(cache.size() == cap) {
                Node lru = tail.prev;
                cache.remove(lru.key);
                remove(lru);
            } 
            addNode(tba);
            cache.put(key, tba);
        } else {
            node.val = value;
            makePriority(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */