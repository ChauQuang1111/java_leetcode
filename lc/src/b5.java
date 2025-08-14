
// 14/08/2025
// 2264. Largest 3-Same-Digit Number in String
import java.util.*;

public class b5 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        moveZeroes(arr);
        // in ra kết quả
        for (int num : arr) {
            System.out.print(num + " ");
        }

    }

    public static void moveZeroes(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
}

// left right ở đây không phải là bên phải và bên trái của mảng mà
// là ở cùng 1 đầu phía bên trái của 1 mảng đặt vậy ấy thoi chứ đúng
// là đầu trái 1 và đầu trái 2 tại bên trái cùng 1 vị trí bên trái của mảng