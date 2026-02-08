// // Ok, mình **giải thích đề bài “Divide an Array Into Subarrays With Minimum Cost II”** một cách **dễ hiểu, đi từ ý nghĩa → yêu cầu → ví dụ** nhé 👌
// // (Đây là bài LeetCode, bản **II** là bản nâng cao hơn bản I.)
// Ok 👍 mình **giải thích thuật toán Java này một cách có hệ thống**, đúng tinh thần **LeetCode 3013 – Divide an Array Into Subarrays With Minimum Cost II**, tập trung vào **ý tưởng + vai trò từng cấu trúc dữ liệu + luồng xử lý**.
import java.util.*;
public class b167{
       static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {
 // Nhập số phần tử
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Nhập k và dist
        int k = sc.nextInt();
        int dist = sc.nextInt();

        long result = minimumCost(nums, k, dist);

        // In kết quả
        System.out.println(result);

        sc.close();
    
}
 /**
     * Hàm giải bài toán
     */
    public static long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        // Max-heap: chứa k-1 phần tử NHỎ NHẤT đang được chọn
        // (heap lớn nhất nằm trên đỉnh)
        PriorityQueue<Integer> pq_left = new PriorityQueue<>((a, b) -> b - a);

        // Min-heap: chứa các phần tử còn lại trong cửa sổ
        PriorityQueue<Integer> pq_right = new PriorityQueue<>();

        // Map dùng cho lazy deletion
        // map[x] = số lần x cần bị xóa khỏi heap
        Map<Integer, Integer> map = new HashMap<>();

        // Số phần tử hợp lệ trong pq_left
        int valid_left = 0;

        // Tổng các phần tử trong pq_left
        long sum_left = 0;

        long res = Long.MAX_VALUE;

        // Duyệt từ nums[1] vì nums[0] bắt buộc được chọn
        for (int i = 1; i < n; i++) {

            /* =========================
               1. XÓA PHẦN TỬ CŨ
               ========================= */
            // Khi cửa sổ vượt quá dist + 1
            if (i >= dist + 2) {
                int v = nums[i - dist - 1]; // phần tử rời khỏi cửa sổ

                // Nếu chắc chắn nằm trong pq_left
                if (!pq_left.isEmpty() && v < pq_left.peek()) {
                    map.merge(v, 1, Integer::sum);
                    valid_left--;
                    sum_left -= v;
                }
                // Nếu đúng là đỉnh pq_left
                else if (!pq_left.isEmpty() && v == pq_left.peek()) {
                    pq_left.poll();
                    valid_left--;
                    sum_left -= v;
                }
                // Nếu đúng là đỉnh pq_right
                else if (!pq_right.isEmpty() && v == pq_right.peek()) {
                    pq_right.poll();
                }
                // Trường hợp khác: đánh dấu lazy delete
                else {
                    map.merge(v, 1, Integer::sum);
                }
            }

            /* =========================
               2. THÊM PHẦN TỬ MỚI
               ========================= */
            // Nếu chưa đủ k-1 phần tử
            // hoặc nums[i] nhỏ hơn phần tử lớn nhất đang chọn
            if (i <= k - 1 || pq_left.isEmpty() || nums[i] <= pq_left.peek()) {
                pq_left.offer(nums[i]);
                valid_left++;
                sum_left += nums[i];
            } else {
                pq_right.offer(nums[i]);
            }

            /* =========================
               3. CÂN BẰNG 2 HEAP
               ========================= */
            if (i > k - 1) {
                // Thiếu phần tử trong pq_left
                if (valid_left < k - 1) {
                    int v = pq_right.poll();
                    pq_left.offer(v);
                    valid_left++;
                    sum_left += v;
                }
                // Dư phần tử trong pq_left
                else if (valid_left > k - 1) {
                    int v = pq_left.poll();
                    valid_left--;
                    sum_left -= v;
                    pq_right.offer(v);
                }
            }

            /* =========================
               4. DỌN LAZY DELETION
               ========================= */
            while (!pq_left.isEmpty() && map.getOrDefault(pq_left.peek(), 0) > 0) {
                int v = pq_left.poll();
                map.merge(v, -1, Integer::sum);
            }

            while (!pq_right.isEmpty() && map.getOrDefault(pq_right.peek(), 0) > 0) {
                int v = pq_right.poll();
                map.merge(v, -1, Integer::sum);
            }

            /* =========================
               5. CẬP NHẬT KẾT QUẢ
               ========================= */
            if (i >= dist + 1) {
                res = Math.min(res, sum_left);
            }
        }

        // Cộng lại nums[0] vì bắt buộc chọn
        return res + nums[0];
    }

}


   
    

// ---

// # 🎯 Mục tiêu bài toán (nhắc nhanh)

// * Bắt buộc chọn `nums[0]`
// * Chọn thêm **`k-1` phần tử**
// * Các phần tử được chọn phải nằm trong **khoảng cách `dist`**
// // * **Tổng nhỏ nhất**

// 👉 Quy về bài toán:

// > Trong **mỗi cửa sổ trượt độ dài `dist+1`**, hãy chọn **`k-1` số nhỏ nhất**

// ---

// # 🧠 Ý tưởng chính của thuật toán

// Thuật toán dùng:

// ### ✅ **Sliding Window**

// * Cửa sổ trượt từ trái sang phải
// * Mỗi cửa sổ: `dist + 1` phần tử

// ### ✅ **Two Heaps (2 priority queue)**

// * `pq_left` (max-heap):
//   👉 chứa **`k-1` phần tử nhỏ nhất đang được chọn**
// * `pq_right` (min-heap):
//   👉 chứa **các phần tử còn lại trong cửa sổ**

// ### ✅ **Lazy Deletion**

// * `map`: ghi nhận các phần tử **đã bị loại khỏi cửa sổ nhưng chưa kịp xóa trong heap**

// ---

// # 📦 Ý nghĩa từng biến

// ```java
// PriorityQueue<Integer> pq_left = new PriorityQueue<>((a, b)->b-a);
// ```

// 👉 Max-heap
// 👉 giữ `k-1` số **nhỏ nhất**
// 👉 top = **số lớn nhất trong nhóm được chọn**

// ---

// ```java
// PriorityQueue<Integer> pq_right = new PriorityQueue<>();
// ```

// 👉 Min-heap
// 👉 giữ các phần tử **không được chọn**

// ---

// ```java
// Map<Integer, Integer> map = new HashMap<>();
// ```

// 👉 Lazy deletion
// 👉 đánh dấu các phần tử **đã rời cửa sổ**

// ---

// ```java
// int valid_left = 0;
// long sum_left = 0;
// ```

// * `valid_left`: số phần tử hợp lệ trong `pq_left`
// * `sum_left`: tổng các phần tử trong `pq_left`

// ---

// # 🔁 Vòng lặp chính

// ```java
// for(int i=1; i<n; i++)
// ```

// 👉 Duyệt từ `nums[1]` vì `nums[0]` đã được chọn sẵn

// ---

// ## 1️⃣ Xóa phần tử cũ (khi cửa sổ vượt quá `dist+1`)

// ```java
// if(i >= dist+2){
//     int v = nums[i-dist-1];
// ```

// 👉 `v` là phần tử **rời khỏi cửa sổ**

// ### Phân loại vị trí của `v`

// * Nếu `v < pq_left.peek()`
//   → chắc chắn nằm trong `pq_left`
// * Nếu `v == pq_left.peek()`
//   → pop trực tiếp
// * Nếu `v == pq_right.peek()`
//   → pop trực tiếp
// * Ngược lại
//   → ghi vào `map` (lazy delete)

// 📌 Vì heap **không hỗ trợ xóa phần tử bất kỳ**, nên phải dùng `map`.

// ---

// ## 2️⃣ Thêm phần tử mới `nums[i]`

// ```java
// if(i <= k-1 || nums[i] <= pq_left.peek())
// ```

// * Nếu chưa đủ `k-1` phần tử
// * Hoặc `nums[i]` nhỏ hơn phần tử lớn nhất đang chọn

// 👉 cho vào `pq_left`

// Ngược lại 👉 `pq_right`

// ---

// ## 3️⃣ Cân bằng heap (giữ đúng `k-1` phần tử)

// ```java
// if(valid_left < k-1)
// ```

// 👉 thiếu → lấy phần tử nhỏ nhất từ `pq_right`

// ```java
// else if(valid_left > k-1)
// ```

// 👉 dư → đẩy phần tử lớn nhất sang `pq_right`

// ---

// ## 4️⃣ Dọn heap (Lazy Deletion thật sự)

// ```java
// while(map.getOrDefault(pq_left.peek(), 0) > 0)
// ```

// 👉 Nếu heap top là phần tử đã bị đánh dấu xóa
// → pop và giảm đếm trong `map`

// Áp dụng cho **cả 2 heap**

// ---

// ## 5️⃣ Cập nhật kết quả

// ```java
// if(i >= dist+1){
//     res = Math.min(res, sum_left);
// }
// ```

// 👉 Khi cửa sổ đủ dài
// 👉 `sum_left` chính là **tổng nhỏ nhất của `k-1` phần tử**

// ---

// # ✅ Kết quả cuối cùng

// ```java
// return res + nums[0];
// ```

// 👉 cộng lại `nums[0]` vì nó **bắt buộc được chọn**

// ---

// # ⚡ Độ phức tạp

// | Thành phần      | Độ phức tạp      |
// | --------------- | ---------------- |
// | Sliding window  | `O(n)`           |
// | Heap operations | `O(log n)`       |
// | Tổng            | **`O(n log n)`** |

// 👉 Đủ nhanh cho **bản II**

// ---

// # 🧠 Tóm tắt một câu (rất quan trọng)

// > Thuật toán dùng **sliding window + 2 heap + lazy deletion** để luôn duy trì **`k-1` phần tử nhỏ nhất trong phạm vi `dist`**, từ đó tìm **minimum cost** hiệu quả.

// ---

// Nếu bạn muốn:

// * 🔁 **So sánh Java heap vs Python SortedList**
// * 🧪 **Trace từng bước bằng ví dụ**
// * 🧠 **Giải thích tại sao điều kiện `v < pq_left.peek()` là đúng**
// * 🎯 **Giải thích kiểu phỏng vấn**

// 👉 cứ nói, mình đào sâu tiếp cho bạn nhé 😄

// ---

// ## 1️⃣ Ý nghĩa chung của đề bài

// Bạn được cho:

// * Một mảng số nguyên `nums`
// * Hai số nguyên `k` và `dist`

// 👉 Nhiệm vụ của bạn là **chia mảng thành các subarray (nhóm con)** sao cho **tổng chi phí (cost) là nhỏ nhất**, theo một **quy tắc chọn phần tử** cụ thể.

// ---

// ## 2️⃣ Luật chia mảng (rất quan trọng)

// * **Phần tử đầu tiên của mảng (`nums[0]`) BẮT BUỘC phải được chọn**
// * Ngoài `nums[0]`, bạn phải chọn thêm **`k - 1` phần tử nữa**
// * Các phần tử được chọn phải thỏa mãn điều kiện **khoảng cách**

// ### 📏 Điều kiện khoảng cách `dist`

// Nếu bạn chọn một phần tử ở vị trí `i`
// → thì **phần tử tiếp theo được chọn** phải nằm trong đoạn:

// ```
// i < j ≤ i + dist
// ```

// 👉 Nói đơn giản:
// Bạn **không được chọn các phần tử cách nhau quá xa**.

// ---

// ## 3️⃣ Cost (chi phí) được tính như thế nào?

// 👉 **Cost = tổng giá trị của `k` phần tử được chọn**

// Mục tiêu cuối cùng:

// > **Chọn đúng `k` phần tử (bao gồm `nums[0]`) sao cho tổng của chúng là NHỎ NHẤT**

// ---

// ## 4️⃣ Ví dụ minh họa

// ```text
// nums = [1, 3, 2, 6, 4]
// k = 3
// dist = 2
// ```

// ### Bước 1: Bắt buộc chọn

// ```
// 1 (nums[0])
// ```

// ### Bước 2: Chọn thêm 2 phần tử nữa

// * Từ vị trí `0`, bạn chỉ được chọn trong `[1, 2]`
//   → các giá trị là `3` và `2`

// * Giả sử chọn `2` (nhỏ hơn)

// * Từ vị trí của `2`, tiếp tục chọn trong phạm vi `dist`

// 👉 Một cách chọn hợp lệ:

// ```
// 1 + 2 + 4 = 7
// ```

// 👉 Các cách khác có thể lớn hơn

// ➡️ **Kết quả nhỏ nhất = 7**

// ---

// ## 5️⃣ Điểm KHÓ của bản II

// So với bản I:

// | Điểm            | Version I                 | Version II                       |
// | --------------- | ------------------------- | -------------------------------- |
// | Kích thước mảng | Nhỏ                       | Rất lớn                          |
// | Cách làm        | Brute force / DP đơn giản | Sliding window + Heap / Multiset |
// | Độ khó          | Trung bình                | Khó                              |

// 👉 **Không thể thử tất cả cách chọn**, vì sẽ **TLE**
// 👉 Phải dùng **cấu trúc dữ liệu** để:

// * Luôn giữ `k-1` số **nhỏ nhất**
// * Trong phạm vi `dist`

// ---

// ## 6️⃣ Tóm tắt ngắn gọn (để nhớ nhanh)

// 📌 Bài toán yêu cầu:

// * Chọn **k phần tử**
// * **Bắt buộc có `nums[0]`**
// * Khoảng cách giữa các phần tử ≤ `dist`
// * **Tổng nhỏ nhất có thể**

// 📌 Bản II:

// * Không hỏi cách chia
// * **Chỉ hỏi giá trị cost nhỏ nhất**

// ---

// Nếu bạn muốn:

// * ✅ Giải thích **ý tưởng thuật toán**
// * ✅ So sánh **bản I và bản II**
// * ✅ Giải thích **theo code Java / C++ / Python**
// * ✅ Giải thích kiểu **phỏng vấn – dễ nhớ**

// 👉 cứ nói mình biết nhé 😄
