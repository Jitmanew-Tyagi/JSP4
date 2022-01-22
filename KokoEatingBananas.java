class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = (int)1e9;
        int aspeed = (int) 1e9;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(checker(piles, mid, h) == true) {
                aspeed = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }
        return aspeed;
    }
    
    public boolean checker(int[] piles, int speed, int h) {
        int time = 0;
        for(int nob : piles) {
            time += Math.ceil(nob/(1.0*speed));
        }
        // if(time <= h) return true;
        // else return false;
        return time <= h;
    }
}