
// # ### 🧠 Giải thích đề bài: **Longest Balanced Subarray I(10/02/2026)
import java.util.*;

public class b175 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        // Khai báo mảng
        int[] nums = new int[n];

        // Nhập các phần tử
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Gọi hàm tìm subarray balanced dài nhất
        int result = longestBalanced(nums);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    /*
     * Hàm tìm độ dài subarray liên tiếp dài nhất
     * sao cho số lượng DISTINCT số chẵn = DISTINCT số lẻ
     */
    public static int longestBalanced(int[] nums) {

        // Số phần tử của mảng
        int n = nums.length;

        // Biến lưu kết quả lớn nhất
        int res = 0;

        // -----------------------------------
        // Tìm giá trị lớn nhất trong mảng
        // để tạo mảng seen có kích thước phù hợp
        // -----------------------------------
        int maxVal = 0;
        for (int x : nums)
            maxVal = Math.max(maxVal, x);

        /*
         * seen[val] dùng để kiểm tra
         * giá trị val đã xuất hiện trong subarray bắt đầu tại i chưa
         *
         * Ta không dùng boolean mà dùng short
         * để lưu "mốc i+1"
         */
        short[] seen = new short[maxVal + 1];

        // -----------------------------------
        // Duyệt từng điểm bắt đầu i
        // -----------------------------------
        for (int i = 0; i < n; i++) {

            /*
             * A[0] = số DISTINCT số chẵn
             * A[1] = số DISTINCT số lẻ
             */
            int[] A = new int[2];

            // -----------------------------------
            // Mở rộng điểm kết thúc j
            // -----------------------------------
            for (int j = i; j < n; j++) {

                int val = nums[j];

                /*
                 * Nếu seen[val] != i+1
                 * → giá trị này chưa xuất hiện
                 * trong subarray bắt đầu tại i
                 */
                if (seen[val] != i + 1) {

                    // Đánh dấu đã thấy tại mốc i
                    seen[val] = (short) (i + 1);

                    /*
                     * val & 1:
                     * = 0 → số chẵn
                     * = 1 → số lẻ
                     */
                    A[val & 1]++;
                }

                // Nếu distinct chẵn = distinct lẻ → balanced
                if (A[0] == A[1]) {

                    // Cập nhật độ dài lớn nhất
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

}

// # Đây là dạng bài rất phổ biến về **mảng + prefix sum / hash map**.
// # Ok, mình sẽ giải thích **thuật toán của bạn đang làm gì** trước → rồi
// **chèn chú thích trực tiếp vào code** để bạn học dễ hơn.

// # ---

// # # 🧠 Ý tưởng thuật toán

// # Hàm `longestBalanced` của bạn tìm:

// # > Subarray liên tiếp dài nhất sao cho
// # > số **số chẵn = số số lẻ**
// # > (balanced theo chẵn / lẻ)

// # ---

// # ## 📌 Cách làm (Brute Force + Optimization nhỏ)

// # ### Bước 1 — Duyệt điểm bắt đầu `i`

// # Bạn thử mọi subarray bắt đầu từ `i`.

// # ```
// # i = 0 → xét [0…]
// # i = 1 → xét [1…]
// # ...
// # ```

// # ---

// ### Bước 2 — Mở rộng điểm kết thúc `j`

// # Với mỗi `i`, bạn duyệt `j` từ `i → n-1`:

// # ```
// # subarray = nums[i … j]
// # ```

// # ---

// # ### Bước 3 — Cân bằng chẵn / lẻ

// # Bạn dùng biến:

// # ```
// # balance
// # ```

// # Quy ước:

// # * Số lẻ → `balance -= 1`
// # * Số chẵn → `balance += 1`

// # 👉 Nếu `balance == 0`
// # ⇒ số chẵn = số lẻ ⇒ subarray cân bằng.

// # ---

// # ### Bước 4 — Dùng `seen` để tối ưu nhẹ

// # ```
// # seen = set()
// # ```

// # Bạn chỉ tính balance **lần đầu gặp số đó** trong subarray.

// # Tức là:

// # * Nếu số xuất hiện lại → bỏ qua.
// # * Mục đích: tránh cộng trừ nhiều lần cùng 1 số.

// # ⚠️ Nghĩa là bài của bạn đang xét:

// # > Subarray cân bằng theo **các giá trị distinct** chẵn / lẻ.

// # ---

// # ### Bước 5 — Pruning (cắt sớm)

// # ```
// # if res > n - i:
// # break
// # ```

// # Nếu đoạn còn lại ngắn hơn kết quả hiện tại → dừng luôn.

// # ---

// # # ⏱️ Độ phức tạp

// # * 2 vòng for → `O(n²)`
// # * Set lookup → `O(1)`

// # # 👉 Tổng: **O(n²)**

// # ---

// # # 🧾 Code có chú thích

// # ```python
// from typing import List
// class Solution:
// def longestBalanced(self, nums: List[int]) -> int:
// # n = số phần tử mảng
// n = len(nums)

// # res = độ dài subarray cân bằng lớn nhất tìm được
// res = 0

// # Duyệt từng điểm bắt đầu i
// for i in range(n):

// # seen dùng để lưu các số đã xuất hiện trong subarray hiện tại
// seen = set()

// # balance = độ lệch giữa số chẵn và số lẻ
// # chẵn +1, lẻ -1
// balance = 0

// # Optimization:
// # Nếu đoạn còn lại ngắn hơn kết quả hiện tại → không cần xét
// if res > n - i:
// break

// # Duyệt điểm kết thúc j
// for j in range(i, n):
// num = nums[j]

// # Nếu số này chưa xuất hiện trong subarray
// if num not in seen:

// # Nếu là số lẻ
// if num % 2:
// balance -= 1 # lẻ → -1

// # Nếu là số chẵn
// else:
// balance += 1 # chẵn → +1

// # Đánh dấu đã thấy số này
// seen.add(num)

// # Nếu balance = 0 → chẵn = lẻ
// if balance == 0:

// # Cập nhật kết quả lớn nhất
// res = max(res, j - i + 1)

// return res

// # # 🔍 Ví dụ chạy thử

// # ```
// # nums = [2, 3, 4, 5]
// # ```

// # Distinct theo từng subarray:

// # | Subarray | Chẵn | Lẻ | Balance |
// # | --------- | ---- | -- | ------- |
// # | [2,3] | 1 | 1 | 0 ✅ |
// # | [3,4] | 1 | 1 | 0 ✅ |
// # | [2,3,4,5] | 2 | 2 | 0 ✅ |

// # Kết quả = 4.

// # ---

// # # ⚠️ Nhận xét quan trọng

// # Code này **không phải cách tối ưu nhất**.

// # Nếu bài chỉ yêu cầu:

// # > Đếm tất cả phần tử (không cần distinct)

// # Thì dùng:

// # * Prefix Sum
// # * HashMap
// # * O(n)

// # Nhanh hơn nhiều.

// # ---

// # # ✅ Tóm tắt

// # Thuật toán của bạn:

// # 1. Duyệt mọi subarray (brute force).
// # 2. Dùng `balance` để cân bằng chẵn / lẻ.
// # 3. Dùng `seen` để chỉ tính số distinct.
// # 4. Cập nhật độ dài lớn nhất.
// # 5. Có pruning để giảm vòng lặp.

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Viết bản **O(n)** tối ưu.
// # * So sánh brute force vs prefix sum.
// # * Vẽ bảng chạy từng bước.
// # * Sửa code để bỏ `seen`.

// # Bạn muốn mình làm phần nào tiếp?

// # ---

// # ## 📌 Ý nghĩa đề bài

// # Bạn được cho một mảng (array) gồm các phần tử thuộc **2 loại** (thường gặp
// nhất):

// # * `0` và `1`
// # hoặc
// # * Số chẵn / lẻ
// # hoặc
// # * Ký tự A / B …

// # 👉 “Balanced” = **cân bằng** = số lượng của 2 loại bằng nhau.

// # ---

// # ## 🎯 Yêu cầu

// # Tìm **độ dài lớn nhất** của một **subarray liên tiếp** sao cho:

// # > Số lượng phần tử loại 1 = số lượng phần tử loại 2

// # ---

// # ## 🧾 Ví dụ dễ hiểu

// # ### Ví dụ 1

// # ```
// # Input: [0,1]
// # ```

// # Subarray cân bằng:

// # * `[0,1]` → 1 số 0 và 1 số 1 → cân bằng

// # 👉 Độ dài lớn nhất = **2**

// # ---

// # ### Ví dụ 2

// # ```
// # Input: [0,1,0]
// # ```

// # Các subarray cân bằng:

// # * `[0,1]` → cân bằng → dài 2
// # * `[1,0]` → cân bằng → dài 2

// # Không có subarray dài 3 cân bằng.

// # 👉 Kết quả = **2**

// # ---

// # ### Ví dụ 3

// # ```
// # Input: [0,0,1,0,0,0,1,1]
// # ```

// # Một subarray cân bằng dài:

// # ```
// # [0,1,0,0,0,1,1]
// # ```

// # Số 0 = 4
// # Số 1 = 3 ❌ chưa cân bằng

// # Nhưng có đoạn khác dài hơn cân bằng (tùy bài test).

// # 👉 Bài yêu cầu tìm **dài nhất**.

// # ---

// # ## 🔍 Hiểu bản chất “balanced”

// # Ta quy ước thường dùng:

// # * `0 → -1`
// # * `1 → +1`

// # Vì:

// # Nếu tổng = 0 ⇒ số 0 = số 1 ⇒ cân bằng.

// # ---

// # ### Ví dụ chuyển đổi

// # ```
// # [0,1,0,1]
// # ↓
// # [-1,+1,-1,+1]
// # ```

// # Prefix sum:

// # ```
// # -1, 0, -1, 0
// # ```

// # Khi prefix sum lặp lại ⇒ đoạn giữa cân bằng.

// # ---

// # ## 🧩 Tóm tắt đề bài

// # * Input: mảng chỉ gồm `0` và `1`
// # * Subarray: đoạn **liên tiếp**
// # * Balanced: số 0 = số 1
// # * Output: **độ dài lớn nhất**

// # ---

// # ## ⚠️ Lưu ý dễ nhầm

// # 1. Không phải chọn phần tử rời rạc → phải liên tiếp.
// # 2. Không cần trả về subarray → chỉ cần **độ dài**.
// # 3. Nếu không có đoạn cân bằng → trả `0`.

// # ---

// # ## 🏷️ Nhận dạng nhanh khi đi thi / làm LeetCode

// # Thấy các dấu hiệu:

// # * “Equal number of 0 and 1”
// # * “Balanced subarray”
// # * “Longest subarray with same count”

// # ⇒ Nghĩ ngay:

// # > Prefix Sum + HashMap
// # > Quy ước 0 = -1

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Giải thích **intuition từng bước**
// # * Vẽ bảng prefix sum
// # * Viết code Java / C++ / Python
// # * So sánh với bài **Longest Balanced Subarray II**

// # Bạn muốn mình giải thích tiếp theo hướng nào?
// Ok 👍 — mình sẽ giải thích **thuật toán của code Java này** theo đúng logic
// trong bài **3719. Longest Balanced Subarray I**.

// Code này là bản **Brute Force nhưng tối ưu bộ nhớ + tốc độ hơn dùng
// HashSet**.

// ---

// # 🧠 Mục tiêu bài toán (theo code)

// Tìm **subarray liên tiếp dài nhất** sao cho:

// > Số lượng **giá trị distinct chẵn = distinct lẻ**

// ⚠️ Lưu ý quan trọng:

// * Không đếm số lần xuất hiện.
// * Chỉ đếm **mỗi giá trị 1 lần trong subarray**.

// ---

// # 📦 Tổng quan thuật toán

// Ý tưởng:

// 1. Duyệt mọi điểm bắt đầu `i`.
// 2. Mở rộng điểm kết thúc `j`.
// 3. Đếm số **giá trị distinct chẵn / lẻ**.
// 4. Nếu bằng nhau → cập nhật kết quả.

// ---

// # 🔍 Giải thích từng phần code

// ---

// ## 1️⃣ Khởi tạo

// ```java
// int n = nums.length;
// int res = 0;
// ```

// * `n`: số phần tử.
// * `res`: độ dài subarray cân bằng lớn nhất.

// ---

// ## 2️⃣ Tìm giá trị lớn nhất

// ```java
// int maxVal = 0;
// for (int x : nums)
// maxVal = Math.max(maxVal, x);
// ```

// Mục đích:

// 👉 Biết giá trị lớn nhất trong mảng để tạo mảng `seen`.

// Ví dụ:

// ```
// nums = [2,5,3]
// maxVal = 5
// ```

// ---

// ## 3️⃣ Mảng `seen`

// ```java
// short[] seen = new short[maxVal + 1];
// ```

// Ý nghĩa:

// * `seen[val]` cho biết:

// > Giá trị `val` đã xuất hiện trong subarray bắt đầu tại `i` chưa.

// ---

// ### ❓ Vì sao dùng `short`?

// Để tiết kiệm bộ nhớ vì chỉ lưu:

// ```
// i + 1
// ```

// ---

// # 🧭 Vòng lặp chính

// ---

// ## 4️⃣ Duyệt điểm bắt đầu `i`

// ```java
// for (int i = 0; i < n; i++) {
// ```

// Mỗi `i` là đầu của subarray.

// ---

// ## 5️⃣ Mảng đếm chẵn / lẻ

// ```java
// int[] A = new int[2];
// ```

// Ý nghĩa:

// ```
// A[0] = số distinct chẵn
// A[1] = số distinct lẻ
// ```

// ---

// ## 6️⃣ Mở rộng điểm kết thúc `j`

// ```java
// for (int j = i; j < n; j++) {
// ```

// Xét subarray:

// ```
// nums[i … j]
// ```

// ---

// ## 7️⃣ Lấy giá trị

// ```java
// int val = nums[j];
// ```

// ---

// ## 8️⃣ Kiểm tra đã thấy chưa

// ```java
// if (seen[val] != i + 1) {
// ```

// ### Ý nghĩa cực hay 👇

// Thay vì clear mảng `seen` mỗi lần `i` đổi…

// Ta dùng mẹo:

// ```
// seen[val] = i + 1
// ```

// Nếu khác `i+1` ⇒ chưa xuất hiện trong subarray này.

// 👉 Tránh phải reset mảng → nhanh hơn.

// ---

// ## 9️⃣ Đánh dấu đã thấy

// ```java
// seen[val] = (short)(i + 1);
// ```

// ---

// ## 🔟 Cập nhật chẵn / lẻ

// ```java
// A[val & 1]++;
// ```

// Giải thích:

// ```
// val & 1
// ```

// * = 0 → chẵn
// * = 1 → lẻ

// // Ví dụ:

// ```
// 4 & 1 = 0 → chẵn
// 5 & 1 = 1 → lẻ
// ```

// 👉 Tăng bộ đếm distinct.

// ---

// ## 1️⃣1️⃣ Kiểm tra cân bằng

// ```java
// if (A[0] == A[1])
// res = Math.max(res, j - i + 1);
// ```

// Nếu:

// // ```
// // distinct chẵn = distinct lẻ
// // ```

// // ⇒ Subarray balanced.

// // Cập nhật độ dài.

// ---

// # 🧾 Code có chú thích đầy đủ

// ```java
// class Solution {
// public int longestBalanced(int[] nums) {

// // Số phần tử mảng
// int n = nums.length;

// // Kết quả lớn nhất
// int res = 0;

// // Tìm giá trị lớn nhất để tạo mảng seen
// int maxVal = 0;
// for (int x : nums)
// maxVal = Math.max(maxVal, x);

// // seen[val] = i+1 nếu val đã xuất hiện trong subarray bắt đầu tại i
// short[] seen = new short[maxVal + 1];

// // Duyệt điểm bắt đầu
// for (int i = 0; i < n; i++) {

// // A[0] = distinct chẵn
// // A[1] = distinct lẻ
// int[] A = new int[2];

// // Mở rộng điểm kết thúc
// for (int j = i; j < n; j++) {
// int val = nums[j];

// // Nếu giá trị này chưa xuất hiện trong subarray hiện tại
// if (seen[val] != i + 1) {

// // Đánh dấu đã thấy
// seen[val] = (short)(i + 1);

// // Cập nhật chẵn / lẻ
// A[val & 1]++;
// }

// // Nếu distinct chẵn = distinct lẻ → balanced
// if (A[0] == A[1])
// res = Math.max(res, j - i + 1);
// }
// }

// return res;
// }
// }
// ```

// ---

// # ⏱️ Độ phức tạp

// * Hai vòng lặp → `O(n²)`
// * Không reset mảng → tối ưu hơn HashSet.

// Bộ nhớ:

// ```
// O(maxVal)

// ---

// # 🔑 Điểm hay của thuật toán

// 1. Không dùng HashSet → nhanh hơn.
// 2. Không clear mảng → dùng trick `i+1`.
// 3. Dùng bit `& 1` thay `% 2`.
// 4. Chỉ đếm distinct.

// ---

// Nếu bạn muốn, mình có thể:

// * Vẽ bảng chạy từng bước.
// * So sánh với Python code bạn gửi.
// * Viết bản O(n) tối ưu (nếu bỏ distinct).
// * Giải thích vì sao dùng `i+1` thay vì boolean.

// Bạn muốn mình đào sâu phần nào?
