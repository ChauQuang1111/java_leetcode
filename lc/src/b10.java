
// 2348. Number of Zero-Filled Subarrays (19/08/2025)
import java.util.*;

public class b10 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        long ans = zeroFilledSubarray(arr);
        System.out.println(ans);
    }

    /**
     * Hàm đếm số lượng subarray toàn số 0
     * 
     * @param nums mảng đầu vào
     * @return số lượng subarray toàn số 0
     */
    public static long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long result = 0;
        for (int num : nums) {
            if (num == 0) {
                count++;
                result += count; // cộng số subarray kết thúc tại đây
            } else {
                count = 0; // reset khi gặp số khác 0
            }
        }
        return result;
    }
}

/*
 * ================ Giải thích thuật toán ================
 * 
 * 🔎 Ý tưởng chính:
 * - Ta cần đếm tất cả subarray liên tiếp toàn số 0.
 * - Khi duyệt mảng:
 * + Nếu gặp số 0:
 * "count" giữ số lượng số 0 liên tiếp tính đến vị trí hiện tại.
 * Mỗi lần gặp số 0, ta cộng thêm "count" vào kết quả,
 * vì số 0 mới tạo thêm đúng "count" mảng con mới kết thúc tại vị trí đó.
 * + Nếu gặp số khác 0 → reset count = 0.
 * 
 * 📝 Ví dụ nums = [0, 0, 0]
 * - i=0: num=0 → count=1 → result=1 (subarray: [0])
 * - i=1: num=0 → count=2 → result=3 (subarrays: [0], [0,0])
 * - i=2: num=0 → count=3 → result=6 (subarrays: [0], [0,0], [0,0,0])
 * 
 * 👉 Kết quả = 6 subarray toàn số 0.
 * 
 * 📊 Công thức tổng quát:
 * Nếu có 1 đoạn liên tiếp k số 0, thì số subarray toàn số 0 trong đoạn này là:
 * k * (k+1) / 2
 * Thuật toán này chính là cách tính công thức đó theo kiểu "cộng dồn online".
 * 
 * ⚡ Độ phức tạp:
 * - Thời gian: O(n) (chỉ duyệt 1 lần mảng)
 * - Không gian: O(1) (chỉ dùng 2 biến đếm)
 * 
 * =======================================================
 */
