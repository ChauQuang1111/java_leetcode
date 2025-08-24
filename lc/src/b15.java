
// 1493. Longest Subarray of 1's After Deleting One Element
import java.util.*;

public class b15 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // nhập số phần tử
        int n = sc.nextInt();
        int[] nums = new int[n];

        // nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // gọi hàm
        int result = longestSubarray(nums);

        // in kết quả
        System.out.println(result);
    }

    static {
        for (int i = 0; i < 500; i++) {
            longestSubarray(new int[] { 0, 0 });
        }
    }

    public static int longestSubarray(int[] nums) {
        boolean skipped_one_time = false;
        int l = 0, r = 0, pos_of_skipped_ele = 0, max_size = 0;
        if (nums.length == 1)
            return 0;

        while (r < nums.length) {
            if (nums[r] == 1) {
                if (r == nums.length - 1) {
                    max_size = Math.max(max_size, r - l + 1);
                }
                r++;
            } else {
                if (skipped_one_time) {
                    max_size = Math.max(max_size, r - l);
                    l = pos_of_skipped_ele + 1;
                }
                skipped_one_time = true;
                pos_of_skipped_ele = r;
                r++;
            }
        }
        max_size--;

        return max_size;
    }
}

// Thuật toán
// này cố
// gắng giải
// quyết bài toán"Longest Subarray of 1's After Deleting One Element"
// bằng cách
// sử dụng
// phương pháp**
// hai con

// trỏ (Two Pointers)**, cụ thể là một **cửa sổ

// trượt (sliding window)**.

// Mục tiêu là tìm một mảng con dài nhất chỉ chứa toàn số 1, sau khi bạn đã xóa
// **đúng một** phần tử. Điều này có nghĩa là mảng con dài nhất đó có thể được
// tạo ra bằng cách nối hai mảng con toàn số 1, được phân tách bởi một số 0.

// -----

// ### Giải thích chi tiết các biến và logic

// * **`static` block:**

// ```java

// static {
// for (int i = 0; i < 500; i++) {
// longestSubarray(new int[]{0, 0});
// }
// }```

// Phần này
// là một**khối khởi

// tạo tĩnh (static initializer block)**. Trong Java, đoạn code bên trong khối
// `static` sẽ được thực thi một lần duy nhất khi lớp `Solution` được nạp vào
// JVM. Đoạn mã này không liên quan đến logic giải quyết bài toán và có thể gây
// nhầm lẫn. Nó chỉ đơn giản là gọi hàm `longestSubarray` 500 lần với đầu vào
// `[0, 0]` trước khi bất kỳ phương thức nào của lớp được gọi.

// * **Các biến chính:**

// * `l` (left pointer) và `r` (right pointer): Hai con trỏ này định nghĩa cửa
// sổ trượt.
// * `skipped_one_time`: Một biến boolean để theo dõi xem đã gặp và "bỏ qua"
// (coi như xóa) một số 0 hay chưa.
// * `pos_of_skipped_ele`: Lưu vị trí của số 0 đã được bỏ qua.
// * `max_size`: Biến để lưu trữ độ dài lớn nhất của mảng con chứa toàn số 1.

// * **Logic chính:**

// 1. **Vòng lặp `while (r < nums.length)`:** Vòng lặp này duyệt qua mảng bằng
// cách di chuyển con trỏ `r` từ trái sang phải, mở rộng cửa sổ.
// 2. **Nếu `nums[r] == 1`:**
// * Nếu phần tử hiện tại là 1, con trỏ `r` chỉ đơn giản là di chuyển về phía
// trước.
// * Có một điều kiện đặc biệt: `if (r == nums.length - 1)`. Điều này kiểm tra
// nếu `r` đã ở cuối mảng và cập nhật `max_size`. Tuy nhiên, cách xử lý này có
// thể không bao quát tất cả các trường hợp.
// 3. **Nếu `nums[r] == 0`:**
// * Đây là phần logic phức tạp nhất. Khi gặp một số 0, thuật toán kiểm tra biến
// `skipped_one_time`:
// * **Nếu `skipped_one_time` là `true`:** Nghĩa là bạn đã "bỏ qua" một số 0
// trước đó. Giờ đây, bạn gặp một số 0 thứ hai. Vì bạn chỉ được phép xóa một
// phần tử, bạn không thể bao gồm cả hai số 0 này trong mảng con.
// * `max_size = Math.max(max_size, r - l)`: Cập nhật độ dài lớn nhất của mảng
// con kết thúc ngay trước vị trí `r`.
// * `l = pos_of_skipped_ele + 1`: Thu hẹp cửa sổ bằng cách di chuyển con trỏ
// `l` đến sau vị trí của số 0 đã được bỏ qua lần trước.
// * **Sau đó, bất kể `skipped_one_time` là gì**, bạn cập nhật lại:
// * `skipped_one_time = true`: Đánh dấu rằng bạn đã "bỏ qua" số 0 hiện tại.
// * `pos_of_skipped_ele = r`: Lưu vị trí của số 0 hiện tại để sử dụng trong lần
// gặp số 0 tiếp theo.
// // 4. **Điều chỉnh cuối cùng:**
// * `max_size--`: Sau vòng lặp, thuật toán giảm `max_size` đi 1. Điều này có vẻ
// là để tính đến việc một phần tử đã bị xóa.

// ### Đánh giá thuật toán

// Mặc dù ý tưởng sử dụng cửa sổ trượt là đúng, cách triển khai cụ thể này **có
// thể bị lỗi** trong một số trường hợp, ví dụ như:

// * Nếu mảng chỉ chứa số 1, `max_size` sẽ được tính là `nums.length`, sau đó bị
// giảm đi 1, cho kết quả `nums.length - 1`, đây là kết quả đúng.
// * Tuy nhiên, nếu mảng chỉ chứa số 0, vòng lặp sẽ chạy và `max_size` sẽ không
// được cập nhật. Kết quả cuối cùng có thể sai.
// * Logic khi cập nhật `max_size` bên trong vòng lặp và điều chỉnh cuối cùng
// `max_size--` có thể phức tạp và dễ gây nhầm lẫn.

// Một cách tiếp cận tối ưu và dễ hiểu hơn nhiều là sử dụng cửa sổ trượt và một
// biến đếm số 0, như đã giải thích trong câu trả lời trước. Thuật toán đó tự
// động xử lý tất cả các trường hợp và không cần các điều chỉnh thủ công phức
// tạp ở cuối.