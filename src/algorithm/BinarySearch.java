package algorithm;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch2(new int[]{1,2,3,3,5}, 4));
    }

    //<target
    //找到最后一个小于target的下标
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) { //最后 left 一定是大于等于 target 的
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    //找到第一个大于target的下标
    public static int binarySearch2(int[] nums, int target) {
        //[left, right) 区间
        int left = 0, right = nums.length;
        while (left < right) {
            // 避免溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                //在mid>target不会改变left，最后 left 是大于 target 的
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //lowerBound, >=target
    //找到第一个 target 的下标，比如target 3, nums [1,2,3,3,5] 会返回 2，即[1,2,*3*,3,5]
    //如果 target 不存在，比如 target 4，则是[1,2,3,*3*,5]
    public static int binarySearch3(int[] nums, int target) {
        //[left, right) 区间
        int left = 0, right = nums.length;
        while (left < right) {
            // 避免溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                //[0, mid] < target
                //在mid>=target之后不会改变 left，最后 left 是大于等于 target
                left = mid + 1;
            } else {
                // nums[mid] >= target
                // [mid, n) >= target
                right = mid;
            }
        }
        return left;
    }

    //upperBound，最后一个等于 target 的下标，比如target 3, nums [1,2,3,3,5] 会返回 3，即[1,2,3,*3*,5]
    //如果 target 不存在，比如 target 4，则是[1,2,3,3,*5*]
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
     * int key) {
     * int low = fromIndex;
     * int high = toIndex - 1;
     * while (low <= high) {
     * int mid = (low + high) >>> 1;
     * int midVal = a[mid];
     * if (midVal < key)
     * low = mid + 1;
     * else if (midVal > key)
     * high = mid - 1;
     * else
     * return mid; // key found
     * }
     * return -(low + 1);  // key not found.
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
