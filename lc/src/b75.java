
// # 3347. Maximum Frequency of an Element After Performing Operations II** (22/10/2025).
import java.util.*; // Dùng cho Arrays và Scanner

public class b75 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        int ops = sc.nextInt();

        int result = maxFrequency(nums, k, ops);

        System.out.println(result);

        sc.close();
    }

    public static int maxFrequency(int[] nums, int k, int ops) {
        int res = 0;
        Arrays.sort(nums); // 🔹 Bước 1: sắp xếp mảng để dễ dùng kỹ thuật hai con trỏ

        int left = 0, right = 0;
        int n = nums.length;
        int i = 0;

        // ============================
        // CASE 1: Giá trị mục tiêu (val) có trong mảng
        // ============================
        while (i < n) {
            int val = nums[i]; // Giá trị hiện tại trong mảng
            int same = 0; // Đếm số phần tử bằng val

            // Đếm số phần tử trùng với val
            while (i < n && nums[i] == val) {
                same++;
                i++;
            }

            // Dịch con trỏ phải để bao gồm tất cả số <= val + k
            while (right < n && nums[right] <= val + k) {
                right++;
            }

            // Dịch con trỏ trái để loại bỏ số < val - k
            while (left < n && nums[left] < val - k) {
                left++;
            }

            // (right - left): số phần tử nằm trong khoảng [val - k, val + k]
            // same: số phần tử bằng chính val
            // ops: số phép biến đổi cho phép
            // Tổng số phần tử có thể trở thành val là min(right - left, same + ops)
            res = Math.max(res, Math.min(same + ops, right - left));
        }

        // ============================
        // CASE 2: Giá trị mục tiêu không có trong mảng
        // ============================
        left = 0;
        right = 0;

        while (right < n) {
            // Nếu nums[right] <= nums[left] + 2k
            // thì khoảng [nums[left], nums[right]] có thể "hội tụ" tại 1 điểm
            while (right < n && (long) nums[left] + k + k >= nums[right]) {
                right++;
            }

            // Số phần tử trong khoảng này là (right - left)
            // nhưng chỉ được phép biến đổi tối đa ops phần tử
            res = Math.max(res, Math.min(right - left, ops));

            // Dịch con trỏ trái để xét đoạn tiếp theo
            left++;
        }

        return res;
    }
}

// # Rất hay 👍 — đây là **lời giải Python tối ưu** cho LeetCode **3347. Maximum
// Frequency of an Element After Performing Operations II** (dạng two-pointer +
// window).
// # Giờ mình sẽ **chèn chú thích từng dòng + giải thích thuật toán** để bạn
// hiểu rõ cách hoạt động nhé 👇

// # ---

// # ```python
// from typing import List

// class Solution:
// def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
// # ✅ B1: Sắp xếp mảng để dễ xử lý theo thứ tự giá trị
// nums.sort()
// n = len(nums)
// res = 0
// left = 0
// right = 0
// i = 0

// # ✅ B2: Duyệt từng nhóm giá trị giống nhau (theo nums[i])
// while i < n:
// x = nums[i] # giá trị hiện tại đang xét
// j = i
// cnt_x = 0 # số lượng phần tử có giá trị đúng bằng x

// # ✅ Đếm có bao nhiêu phần tử bằng x
// while j < n and nums[j] == x:
// cnt_x += 1
// j += 1

// # ✅ Mở rộng cửa sổ [left, right) sao cho phần tử nằm trong [x - k, x + k]
// # → tức là các số có thể biến đổi thành x sau khi thêm/bớt ≤ k
// while left < n and nums[left] < x - k:
// left += 1
// while right < n and nums[right] <= x + k:
// right += 1

// # ✅ Số phần tử có thể thành x là (right - left)
// # Trong đó có cnt_x phần tử vốn đã là x,
// # còn lại có thể thay đổi bằng numOperations phép.
// # → nên lấy min giữa tổng phần tử có thể biến đổi và giới hạn numOperations
// res = max(res, min(right - left, cnt_x + numOperations))

// # ✅ Chuyển sang nhóm giá trị tiếp theo
// i = j

// # ✅ Nếu đã đạt tần suất >= numOperations, trả kết quả luôn
// if res >= numOperations:
// return res

// # ✅ Trường hợp đặc biệt: khi không tập trung vào 1 giá trị cố định (x),
// # mà chỉ xem có thể gom nhóm được bao nhiêu phần tử liên tiếp
// # nằm trong khoảng cách ≤ 2*k
// res_no_x = 0
// left = 0

// # ✅ Duyệt tất cả phần tử bằng hai con trỏ (left, right)
// for right, x in enumerate(nums):
// # Dịch con trỏ left sao cho nums[right] - nums[left] ≤ 2*k
// # vì nếu khoảng cách > 2*k thì 2 phần tử đó không thể gặp nhau
// while nums[left] < x - k * 2:
// left += 1
// # Độ dài cửa sổ là số phần tử có thể gom lại thành một giá trị chung
// res_no_x = max(res_no_x, right - left + 1)

// # ✅ Số phần tử tối đa có thể dùng chỉ là numOperations
// res_no_x = min(res_no_x, numOperations)

// # ✅ Kết quả cuối cùng là giá trị lớn nhất giữa hai cách tính
// return max(res, res_no_x)

// ### 🧠 Giải thích thuật toán tổng quát:

// # | Bước | Mục đích | Ý tưởng chính |
// # | -------------------------------- |
// ------------------------------------------- |
// ---------------------------------------------------- |
// # | **1. Sort mảng** | Để có thể dùng two-pointer | Giúp dễ mở rộng khoảng
// giá trị |
// # | **2. Duyệt từng nhóm giá trị x** | Xét xem có bao nhiêu số có thể biến
// thành x | Dựa trên phạm vi `[x - k, x + k]` |
// # | **3. Cửa sổ [left, right)** | Giữ các số nằm trong khoảng này | Mỗi số
// trong khoảng có thể thành x |
// # | **4. Cập nhật res** | `min(right - left, cnt_x + numOperations)` | Không
// thể vượt quá số phép đổi hoặc số phần tử gần x |
// # | **5. Trường hợp “res_no_x”** | Khi không cố định giá trị đích | Gom nhóm
// số gần nhau trong phạm vi `2k` |
// # | **6. Trả max(res, res_no_x)** | Đảm bảo chọn phương án tối ưu nhất | |

// # ---

// # ### 💬 Ví dụ nhanh:

// # ```
// # nums = [1, 4, 5], k = 2, numOperations = 2
// # ```

// # * Xét x = 4:

// # * Các phần tử trong [2, 6] là [4, 5]
// # * cnt_x = 1, right - left = 3
// # * ⇒ res = min(3, 1 + 2) = 3
// # → Tần suất tối đa = **3** (vì có thể biến 1→3, 5→4).

// # ---

// # ### 🧩 Kết quả test bạn đưa (nums = [999999997, 999999999, 999999999], k =
// 999999999, numOps = 2):

// # Tất cả phần tử nằm trong phạm vi ±k của nhau → có thể biến thành cùng giá
// trị.
// # ✅ Kết quả cuối cùng: `3`.

// # ---

// # Bạn có muốn mình viết lại lời giải Java tương đương với cùng chú thích từng
// dòng không (giống cấu trúc này)?

// # Nếu bạn muốn, mình cũng có thể gửi bản dịch đề tiếng Việt + ý tưởng lời
// giải chi tiết.

// # ---

// # ### 📜 Đề bài tóm tắt

// # Bạn được cho:

// # * Một mảng số nguyên `nums`
// # * Hai số nguyên: `k`, và `numOperations`

// # Bạn phải thực hiện **chính xác** `numOperations` phép biến đổi. Mỗi phép
// biến đổi bạn chọn một phần tử tại chỉ số `i` (chỉ được chọn mỗi chỉ số tối đa
// một lần) và cộng **một số trong khoảng `[-k, k]`** vào `nums[i]`.

// # Sau khi thực hiện hết `numOperations` phép, bạn muốn **tối đa hóa tần suất
// (frequency)** của một phần tử — tức là có bao nhiêu phần tử trong mảng bằng
// cùng một giá trị. Trả về giá trị tần suất lớn nhất có thể đạt được.

// # **Ví dụ:**

// # ```
// # nums = [1, 4, 5], k = 2, numOperations = 2
// # ```

// # * Bạn có thể chọn `nums[0] = 1`, cộng +2 → trở thành 3
// # * Chọn `nums[2] = 5`, cộng −2 → trở thành 3
// # * Mảng sau biến đổi: [3, 4, 3] → giá trị 3 xuất hiện 2 lần
// # → Tần suất tối đa = 2.

// # ---

// # ### 🧠 Điểm then chốt & khác biệt so với phiên bản I

// # * Khác với phiên bản I (3346) là **“có thể thực hiện **tối đa**
// numOperations phép”**, ở phiên bản II đây là **phải thực hiện đúng
// numOperations** (mỗi phép chọn chỉ số khác nhau).
// # * Mỗi phần tử có thể biến thành bất kỳ số trong `[nums[i] – k, nums[i] +
// k]`.
// # * Ta cần tìm một giá trị đích `x` sao cho **nhiều phần tử nhất** có thể
// biến thành `x`, nhưng bị giới hạn bởi số phép `numOperations`.

// # 👉 Tóm: Gần giống 3346 nhưng thêm ràng buộc **chính xác số phép** (và mỗi
// chỉ số chọn tối đa một lần) → dẫn đến cách giải hơi khác.

// # ---

// # ### ✅ Mục tiêu

// # Tìm giá trị `x` sao cho:

// # * Có `cnt[x]` phần tử ban đầu = `x` (không cần đổi)
// # * Có `adj` phần tử có thể biến thành `x` (nằm trong khoảng ±k)
// # * Bạn chỉ có `numOperations` phép đổi ⇒ chỉ đổi được tối đa `numOperations`
// phần tử
// # → tần suất có thể đạt = `cnt[x] + min(numOperations, adj)`

// # Bạn muốn giá trị này lớn nhất.

// # ---

// # ### 🎯 Ý tưởng lời giải nhanh

// # 1. Đếm tần suất ban đầu `cnt[num]` cho mỗi `num` trong `nums`.
// # 2. Với mỗi phần tử `nums[i]`, nó “nguyên” có thể đưa đến khoảng giá trị
// `[nums[i] – k, nums[i] + k]`.
// # 3. Dùng kỹ thuật “sweep line” hoặc “difference array” để tính cho mỗi giá
// trị đích `x`, có bao nhiêu phần tử **có khả năng** biến thành `x`.
// # 4. Với mỗi `x`, ta có:

// # * `cnt[x]`: số phần tử đã là `x`
// # * `reachable[x]`: số phần tử có thể biến thành `x`
// # * Tần suất tối đa tại `x` = `min(reachable[x], cnt[x] + numOperations)`
// # 5. Kết quả = max qua mọi `x`.

// # ---

// # ### 📝 Kết luận

// # Bài này là biến thể nâng cao của 3346 — hơi “khó” hơn vì bạn phải đổi
// **chính xác** `numOperations` và mỗi chỉ số chỉ đổi một lần. Nhưng ý tưởng
// lõi vẫn là: tìm giá trị đích tốt nhất, dùng phép đổi ở phạm vi ±k để nhiều
// phần tử có thể trở thành giá trị đó.

// # ---

// # Nếu bạn muốn, mình có thể gửi **ví dụ chi tiết từng bước** (với bảng biến)
// cho một trường hợp cụ thể + **mã Python/Java có chú thích** để bạn dễ hiểu
// hơn. Bạn muốn như vậy không?
// Rất hay 👍 — bạn đang xem **phiên bản tối ưu bằng hai

// con trỏ (two pointers)** cho bài **LeetCode 3347 – Maximum Frequency of an
// Element After Performing Operations II**.
// Cùng mình **phân tích chi tiết toàn bộ thuật toán và các bước xử lý trong
// code này nhé.**

// ---

// ## 🧩 Mục tiêu đề bài:

// Cho:

// * Một mảng số nguyên `nums`.
// * Mỗi phần tử có thể **tăng hoặc giảm tối đa `k` đơn vị**.
// * Bạn có **tối đa `ops` phép biến đổi**.

// 👉 Hỏi: sau khi thực hiện tối đa `ops` phép biến đổi, **số lượng phần tử
// giống nhau nhiều nhất** (tức là tần suất lớn nhất của một số) là bao nhiêu?

// ---

// ## ⚙️ Ý tưởng chính:

// ### Có 2 tình huống cần xét:

// 1. **Trường hợp 1**: số mà bạn muốn

// đạt được (`val`) **đã tồn tại trong mảng**.
// → Ta chỉ cần biến đổi các số trong khoảng `[val - k, val + k]` thành `val`.

// 2. **Trường hợp 2**: số mà bạn muốn đạt được **không có trong mảng**,
// → Khi đó, ta chỉ cần xét **khoảng liên tiếp dài nhất mà mọi số đều có thể gặp
// nhau ở một điểm giữa** (nằm giữa 2 số xa nhất ≤ `2k`).

// ---

// ## 🧠 Phân tích chi tiết code

// ```java
// class Solution {
// public int maxFrequency(int[] nums, int k, int ops) {
// int res = 0;
// Arrays.sort(nums); // Sắp xếp để dễ dùng hai con trỏ
// int left = 0, right = 0;
// int n = nums.length;
// int i = 0;

// // ===== CASE 1: giá trị mục tiêu (val) có trong mảng =====
// while (i < n) {
// int val = nums[i]; // Giá trị hiện tại trong mảng
// int same = 0; // Đếm xem có bao nhiêu phần tử bằng val

// // Đếm các phần tử bằng val
// while (i < n && nums[i] == val) {
// same++;
// i++;
// }

// // Dịch con trỏ phải để bao phủ tất cả số ≤ val + k
// while (right < n && nums[right] <= val + k) {
// right++;
// }

// // Dịch con trỏ trái để loại bỏ số < val - k
// while (left < n && nums[left] < val - k) {
// left++;
// }

// // Tổng số phần tử có thể biến thành val là (right - left)
// // Nhưng chỉ được phép thực hiện tối đa ops phép biến đổi
// res = Math.max(res, Math.min(same + ops, right - left));
// }

// // ===== CASE 2: giá trị mục tiêu không có trong mảng =====
// left = 0;
// right = 0;
// while (right < n) {
// // Nếu khoảng giữa nums[left] và nums[right] <= 2k
// // thì tất cả số trong khoảng này có thể hội tụ ở giữa
// while (right < n && (long) nums[left] + k + k >= nums[right]) {
// right++;
// }

// // Số phần tử có thể hội tụ là (right - left)
// // nhưng bị giới hạn bởi số phép biến đổi ops
// res = Math.max(res, Math.min(right - left, ops));

// // Dịch trái để tiếp tục xét khoảng tiếp theo
// left++;
// }

// return res;
// }
// }
// ```

// ---

// ## 📘 Giải thích bằng ví dụ:

// ### Input:

// ```
// nums = [999999997, 999999999, 999999999]
// k = 999999999
// ops = 2
// ```

// **Bước 1:**
// `nums` đã sắp xếp: `[999999997, 999999999, 999999999]`

// * Chọn `val = 999999999`
// * Các phần tử trong `[val - k, val + k] = [2, 1999999998]`
// → Tất cả 3 phần tử đều nằm trong khoảng này.
// * `same = 2`, `right - left = 3`, `ops = 2`
// ⇒ `res = max(0, min(2 + 2, 3)) = 3`

// **Kết quả cuối cùng:** ✅ `res = 3`

// ---

// ## 🧮 Tổng kết ý tưởng:

// | Phần | Mục đích | Cách làm |
// | ----------- | ----------------------------------------- |
// -----------------------------------------------------------------------------
// |
// | **Case 1** | Khi giá trị mục tiêu có trong mảng | Xét mỗi giá trị `val` và
// tìm bao nhiêu phần tử nằm trong `[val - k, val + k]` |
// | **Case 2** | Khi giá trị mục tiêu không có trong mảng | Dùng hai con trỏ
// tìm khoảng dài nhất mà `max - min ≤ 2k` |
// | **Kết hợp** | Lấy giá trị lớn nhất trong hai trường hợp | `max(res_case1,
// res_case2)` |

// ---

// Bạn có muốn mình **vẽ hình minh họa** về cách hai con trỏ `left` và `right`
// di chuyển để tính các khoảng `[val - k, val + k]` không?
// (Hình này giúp dễ hiểu hơn rất nhiều nếu bạn học bằng trực quan 👀)
