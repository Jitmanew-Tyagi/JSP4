class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int start1 = 0;
        int end1 = nums1.length;
        System.out.println(nums1.length +" "+ nums2.length);        
        int medianElements = (nums1.length + nums2.length + 1) / 2;

        while(start1<=end1){
            int median = start1 + (end1-start1)/2;
            // int median = (end1 + start1) / 2;
            int median2 = medianElements - median;
            System.out.println(median2+" "+medianElements);
            if( median<end1 && nums2[median2-1] > nums1[median]) start1 = median+1;
            
            else if(median > start1 && nums1[median-1] > nums2[median2]) end1 = median-1;    
            else
            {   int result1 = 0;
                if(median == 0) result1 = nums2[median2-1];
                else if (median2 == 0) result1 = nums1[median-1];
                else result1 = Math.max(nums2[median2-1], nums1[median-1]);
                
                if ((nums1.length + nums2.length)%2 != 0)
                    return result1;
                
                int result2 = 0;
                if(median == nums1.length) result2 = nums2[median2];
                else if (median2 == nums2.length) result2 = nums1[median];
                else result2 = Math.min(nums2[median2], nums1[median]);
                
                
                return (result1 + result2)/2.0;
            }
                
        }
        return 0.0;
    }
}