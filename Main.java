class Solution {
  public int search(int[] nums, int target) {
      if (nums[0] > target || nums[nums.length - 1] < target) {
          return -1;
      }
      int low = 0; int high = nums.length - 1;
      while (low <= high) {
          int mid = (low + high) >> 1;
          if (nums[mid] == target) {
              return mid;
          } else if (nums[mid] > target) {
              high = mid - 1;
          } else {
              low = mid + 1;
          }
      }
      return -1;
  }

  public static void main(String[] args) {
    int[] nums = {-1,0,3,5,9,12};
    Solution obj = new Solution();
    System.out.println(obj.search(nums, 9));
  }
}