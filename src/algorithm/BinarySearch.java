package algorithm;

import java.util.Arrays;

public class BinarySearch {

    //<target
    public static int binarySearch(int[] nums, int target) {
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
    public static int binarySearch2(int[] nums, int target) {
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
    public static int binarySearch3(int[] nums, int target) {
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
    public static int binarySearch4(int[] nums, int target) {
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

    /**
     * 如果找到，直接返回下标（多个相同随机返回）
     * 否则返回插入点（第一个大于target的下标）
     * 底层实现，也就是左闭右闭区间查找
     * private static int binarySearch0(int[] a, int fromIndex, int toIndex,
     *                                  int key) {
     *     int low = fromIndex;
     *     int high = toIndex - 1;
     *
     *     while (low <= high) {
     *         int mid = (low + high) >>> 1;
     *         int midVal = a[mid];
     *
     *         if (midVal < key)
     *             low = mid + 1;
     *         else if (midVal > key)
     *             high = mid - 1;
     *         else
     *             return mid; // key found
     *     }
     *     return -(low + 1);  // key not found.
     * }
     */
    public static int binarySearchFromJDK(int[] nums, int target) {
        int idx = Arrays.binarySearch(nums, target);
        //如果没找到，也就是负数，返回插入点
        //插入点是第一个大于target的下标
        if (idx < 0) {
            //转换
            return -idx - 1;
        }
        return idx;
    }
}
