package algorithm;

public class BinarySearch {

    //<target
    private static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    // >target
    private static int binarySearch2(int[] nums, int target) {
        //[left, right) 区间
        int left = 0, right = nums.length;
        while (left < right) {
            // 避免溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                //在mid>target不会改变left
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //lowerBound, >=target
    private static int binarySearch3(int[] nums, int target) {
        //[left, right) 区间
        int left = 0, right = nums.length;
        while (left < right) {
            // 避免溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                //[0, mid] < target
                //在mid>=target之后不会改变 left
                left = mid + 1;
            } else {
                // nums[mid] >= target
                // [mid, n) >= target
                right = mid;
            }
        }
        return left;
    }

    //<=target
    private static int binarySearch4(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                //mid满足条件，但 left 指向 mid+1，这样 left-1 即拿到 upperBound
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }
}
