
// 1526. Minimum Number of Increments on Subarrays to Form a Target Array(30/10/2025)
import java.util.*;

public class b83 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] target = new int[n];

        for (int i = 0; i < n; i++) {
            target[i] = sc.nextInt();
        }

        int result = minNumberOperations(target);
        sc.close();

        System.out.println(result);
    }

    public static int minNumberOperations(int[] target) {
        int n = target.length; // Độ dài mảng
        int ans = target[0]; // B1: Cần ít nhất target[0] lần tăng để tạo phần tử đầu tiên

        // B2: Duyệt từ phần tử thứ 2 trở đi
        for (int i = 1; i < n; ++i) {
            // Nếu phần tử hiện tại lớn hơn phần tử trước đó
            if (target[i] > target[i - 1]) {
                // Ta phải tăng thêm phần chênh lệch target[i] - target[i-1]
                ans += target[i] - target[i - 1];
            }
            // Nếu target[i] <= target[i-1] thì không cần làm gì
        }

        return ans;
    }
}

// Rất hay — đây là một bài **tư duy khá tinh tế** trong LeetCode (mã 1526:
// *Minimum Number of Increments on Subarrays to Form a Target Array*).
// Mình sẽ giải thích dễ hiểu từng phần nhé 👇

// ---

// ## 🧩 Đề bài gốc

// Cho bạn một mảng số nguyên **target** (chỉ chứa số ≥ 0).
// Ban đầu, bạn có một mảng **arr** cùng độ dài, nhưng tất cả các phần tử trong
// `arr` đều bằng **0**.

// Trong **mỗi lần thao tác (operation)**, bạn có thể:

// * **Chọn một dãy con liên tiếp** của `arr` (subarray),
// * Và **tăng tất cả các phần tử trong dãy đó lên 1**.

// ---

// ## 🎯 Mục tiêu

// Hãy tìm **số lần thao tác nhỏ nhất** để **biến mảng `arr` (ban đầu toàn 0)**
// thành **`target`**.

// ---

// ## 🧮 Ví dụ:

// ### Ví dụ 1:

// ```
// Input: target = [1,2,3,2,1]
// Output: 3
// ```

// **Giải thích quá trình:**

// * Lần 1: tăng toàn bộ mảng `[1,2,3,2,1]` lên 1 → `arr = [1,1,1,1,1]`
// * Lần 2: tăng subarray `[1,2,3,2,1]` ở vị trí 1..3 → `arr = [1,2,2,2,1]`
// * Lần 3: tăng subarray `[2,3,2]` ở vị trí 2..2 → `arr = [1,2,3,2,1]`
// ✅ Tổng cộng **3 lần thao tác**.

// ---

// ## 💡 Tư duy thuật toán

// Giả sử bạn đi từ **trái sang phải**:

// * Nếu phần tử tiếp theo **lớn hơn** phần tử trước,
// → bạn cần **thêm đúng phần chênh lệch** số lần tăng ở vị trí đó.
// * Nếu phần tử tiếp theo **nhỏ hơn hoặc bằng**,
// → bạn **không cần thêm thao tác mới** (vì phần đó có thể được bao phủ trong
// thao tác cũ).

// 👉 Nói cách khác:

// ```
// ans = target[0] + Σ(max(0, target[i] - target[i-1])) for i = 1..n-1
// ```

// ---

// ## 🔢 Ví dụ minh họa:

// `target = [1, 2, 3, 2, 1]`

// | i | target[i-1] | target[i] | Chênh lệch (max(0, diff)) | Tổng |
// | - | ----------- | --------- | ------------------------- | ---- |
// | 0 | - | 1 | +1 | 1 |
// | 1 | 1 | 2 | +1 | 2 |
// | 2 | 2 | 3 | +1 | 3 |
// | 3 | 3 | 2 | 0 | 3 |
// | 4 | 2 | 1 | 0 | 3 |

// ✅ Kết quả = **3**

// ---

// ## 🧠 Tóm tắt ý tưởng:

// * Mỗi khi `target[i]` tăng so với `target[i-1]`,
// ta cần **thêm số thao tác bằng đúng độ tăng đó**.
// * Nếu giảm, thì phần giảm đó không cần thao tác mới vì đã được tạo trong các
// bước trước.

// ---

// Bạn có muốn mình viết luôn **mã Python hoặc Java** có chú thích dễ hiểu để
// minh họa thuật toán này không?
// class Solution {
// public int minNumberOperations(int[] target) {
// int n = target.length ;
// int ans = target[0] ;
// for( int i=1 ; i<n ; ++i ){
// if( target[i] > target[i-1] ){
// ans += target[i] - target[i-1] ;
// }
// }
// return ans ;
// }
// }