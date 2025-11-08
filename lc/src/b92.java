//1611. Minimum One Bit Operations to Make Integers Zero(08/11/2025)#Dưới đây là**code Python hoàn chỉnh**cho bài**1611. Minimum One Bit Operations to Make Integers Zero**,#kèm theo**giải thích chi tiết từng dòng**👇

// #---

// ####✅Code hoàn chỉnh(cách đệ quy)
import java.util.*;

public class b92 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int result = minimumOneBitOperations(n);

        System.out.println("Số bước tối thiểu để biến " + n + " thành 0 là: " + result);

        sc.close();
    }

    public static int minimumOneBitOperations(int n) {
        return dfs(n); // Gọi hàm đệ quy xử lý
    }

    // 🧠 Hàm đệ quy tính số bước tối thiểu
    public static int dfs(int n) {
        // Trường hợp cơ bản: nếu n == 0 thì không cần bước nào
        if (n == 0)
            return 0;

        // Tìm vị trí bit cao nhất (most significant bit)
        int k = 31 - Integer.numberOfLeadingZeros(n);
        // Ví dụ: n = 13 (1101) → k = 3

        // mask = 2^(k+1) - 1 (toàn bit 1 đến vị trí k)
        int mask = (1 << (k + 1)) - 1; // ví dụ: 2^4 - 1 = 15 (1111b)

        // Xóa bit cao nhất của n (n XOR với 1 << k)
        int next = n ^ (1 << k);

        // Áp dụng công thức đệ quy: f(n) = mask - f(next)
        return mask - dfs(next);
    }
}

// #```python
// class Solution:
// def minimumOneBitOperations(self, n: int) -> int:
// # Trường hợp cơ bản: nếu n = 0 thì không cần thao tác nào
// if n == 0:
// return 0

// # Tìm vị trí bit

// cao nhất (most significant bit)
// # Ví dụ: n = 13 (1101₂) -> bit_length = 4 -> k = 3
// k = n.bit_length() - 1

// # Đệ quy theo công thức:
// # f(n) = (2^(k+1) - 1) - f(n ^ (1 << k))
// # (1 << (k+1)) - 1 => tạo số có tất cả bit từ 0..k là 1
// # n ^ (1 << k) => tắt bit cao nhất của n
// return (1 << (k + 1)) - 1 - self.minimumOneBitOperations(n ^ (1 << k))
// # ```

// # ---

// # ### 🔍 Ví dụ minh họa: `n = 6 (110₂)`

// # | Bước | n | bit cao nhất `k` | Công thức | Kết quả |
// # | ---- | ---------------- | ---------------- | -------------------------- |
// ------- |
// # | 1 | 6 | 2 | (2³−1) − f(6−4) = 7 − f(2) | ? |
// # | 2 | 2 | 1 | (2²−1) − f(2−2) = 3 − f(0) | 3 |
// # | 3 | f(6) = 7 − 3 = 4 | | | ✅ |

// # 👉 Kết quả: **4 thao tác**

// # ### ⚙️ Độ phức tạp

// # * **Thời gian:**

// O(log n) — vì mỗi lần đệ quy giảm 1 bit.
// # * **Không gian:**

// O(log n) — do stack đệ quy.

// # ---

// ### ⚡ Cách ngắn hơn (phi đệ quy)

// # Đây là cách viết **dựa trên công thức Gray code** — nhanh hơn, không cần đệ
// quy:

// # ```python
// # class Solution:
// # def minimumOneBitOperations(self, n: int) -> int:
// # res = 0
// # while n:
// # res ^= n # XOR dồn với n
// # n >>= 1 # Dịch phải 1 bit
// # return res

// # 🧠 Ý tưởng:
// # `res ^= n` mô phỏng quá trình chuyển đổi **Gray code → nhị phân**,
// # và giá trị cuối cùng chính là **số thao tác cần thực hiện**.

// # ---

// # Bạn có muốn mình minh họa **chi tiết từng vòng while** trong cách thứ hai
// (phi đệ quy) không?
// # Ví dụ với `n = 13 (1101₂)` để thấy rõ cách XOR hoạt động từng bước?

// # Hãy cùng phân tích **từng bước** để bạn hiểu toàn bộ tư duy đằng sau nhé 👇

// # ---

// # ## 🎯 Đề bài

// # Cho một số nguyên `n`.
// # Mỗi lần thao tác, bạn có thể **đảo bit** ở vị trí `i` (0-based, tính từ
// phải sang trái),
// # **nhưng chỉ khi** bit ở vị trí `i+1` (bên trái ngay cạnh nó) là **1**, và
// **tất cả các bit bên phải `i`** (nhỏ hơn i) đều **là 0**.

// # Bạn cần tìm **số thao tác tối thiểu** để biến `n` thành `0`.

// # ---

// # ## 💡 Ví dụ

// # ```
// # n = 3 (binary = 011)
// # ```

// # Các bước:

// # ```
// # 011 → 001 → 000
// # ```

// # Kết quả: 2 bước.

// # ---

// # ## 🧩 Nhận xét quan trọng

// # Bài này **không thể** giải bằng thao tác bit bình thường vì quy tắc bật/tắt
// bit khá phức tạp.
// # Tuy nhiên, nếu ta quan sát kết quả cho vài số nhỏ — sẽ nhận ra **một quy
// luật rất giống mã Gray (Gray code)**.

// # | n (decimal) | binary | min steps to 0 |
// # | ----------- | ------ | -------------- |
// # | 0 | 000 | 0 |
// # | 1 | 001 | 1 |
// # | 2 | 010 | 3 |
// # | 3 | 011 | 2 |
// # | 4 | 100 | 7 |
// # | 5 | 101 | 6 |
// # | 6 | 110 | 4 |
// # | 7 | 111 | 5 |

// # 🔎 Dễ thấy:
// # `f(n)` **chính là** giá trị nhị phân của **Gray code đảo ngược**.

// # Cụ thể:
// # 👉 `f(n) = n ^ (n >> 1) ^ (n >> 2) ^ ...` cho tới khi `n = 0`.
// # Hay viết gọn hơn:

// # > **Gray code → integer conversion**
// # > `grayToBinary(n) = n ^ (n >> 1) ^ (n >> 2) ^ ...`

// # ---

// # ## ✅ Thuật toán (Recursive)

// # ### Ý tưởng

// # Giả sử:

// # * `f(n)` là số bước tối thiểu để biến `n` → `0`.
// # * Nếu `n` = `0`, rõ ràng `f(0) = 0`.
// # * Nếu `n` có bit cao nhất ở vị trí `k`, nghĩa là `n` nằm trong khoảng
// `[2^k, 2^{k+1})`.

// # Công thức:

// # ```
// # f(n) = 2^{k+1} - 1 - f(n - 2^k)
// # ```

// # ### Giải thích:

// # * `2^k` là số có duy nhất bit cao nhất bật lên.
// # * `2^{k+1} - 1` là tất cả các bit từ 0..k đều bằng 1.
// # * Quy luật: để tắt bit cao nhất, bạn cần phải "đảo" toàn bộ dãy bit nhỏ hơn
// nó —
// # nên phải đi qua tất cả trạng thái trong Gray code theo thứ tự đảo ngược.

// # ---

// # ## 🧮 Ví dụ minh họa

// # Giả sử `n = 6 (110₂)`

// # * bit cao nhất ở vị trí `k = 2` (`2^2 = 4`)
// # * `n - 2^k = 6 - 4 = 2`

// # Khi đó:

// # ```
// # f(6) = (2^{3} - 1) - f(2)
// # = 7 - f(2)
// # = 7 - 3
// # = 4
// # ```

// # ✅ Kết quả đúng như bảng trên.

// # ---

// # ## 🧠 Code Python

// # ```python
// # class Solution:
// # def minimumOneBitOperations(self, n: int) -> int:
// # if n == 0:
// # return 0
// # k = n.bit_length() - 1 # vị trí bit cao nhất
// # return (1 << (k + 1)) - 1 - self.minimumOneBitOperations(n ^ (1 << k))
// # ```

// # ---

// # ## 💡 Giải thích code

// # 1️⃣ `n.bit_length() - 1` → tìm vị trí bit cao nhất (MSB).
// # 2️⃣ `(1 << (k + 1)) - 1` → tạo số có tất cả các bit từ 0..k bằng 1.
// # 3️⃣ `n ^ (1 << k)` → tắt bit cao nhất của `n`.
// # 4️⃣ Đệ quy để xử lý phần còn lại.

// # ---

// # ## ⏱️ Độ phức tạp

// # * **Thời gian:** O(log n)
// # (mỗi lần giảm một bit)
// # * **Không gian:** O(log n) (do đệ quy)

// # ---

// # ## 🧩 Cách khác (Bitwise trick)

// # Nếu bạn muốn công thức *phi đệ quy*, có thể dùng Gray-code trick:

// # ```python
// # def minimumOneBitOperations(n: int) -> int:
// # res = 0
// # while n:
// # res ^= n
// # n >>= 1
// # return res
// # ```

// # 📘 Đây là cách nhanh nhất — chỉ cần duyệt qua các bit một lần.

// # ---

// # Bạn có muốn mình minh họa từng bước chạy **với ví dụ `n = 13 (1101₂)`** để
// thấy cách đệ quy hoạt động không?
// Rất hay — mình sẽ giải thích chi tiết **từng bước hoạt động** của thuật toán
// này 👇

// ---

// ## 🎯 Mục tiêu bài toán

// Ta cần **biến số `n` thành 0**, nhưng **không được flip tùy ý**.
// Quy tắc cho phép giống **Gray Code**:

// * Chỉ được **đổi 1 bit tại một thời điểm**.
// * Sau khi đổi, trạng thái mới phải **hợp lệ theo thứ tự Gray code**.

// Bài toán yêu cầu:
// 👉 Tìm **số lần đổi bit tối thiểu** để biến `n` → `0`.

// ---

// ## 🧩 Ý tưởng chính

// Khi ta nhìn dãy Gray code (ví dụ 3 bit):

// | Thứ tự | Gray Code | Giá trị nhị phân |
// | :----- | :-------- | :--------------- |
// | 0 | 000 | 0 |
// | 1 | 001 | 1 |
// | 2 | 011 | 3 |
// | 3 | 010 | 2 |
// | 4 | 110 | 6 |
// | 5 | 111 | 7 |
// | 6 | 101 | 5 |
// | 7 | 100 | 4 |

// Nếu muốn đi từ `n` → `0`, ta chỉ việc **đếm vị trí của `n` trong chuỗi Gray
// code**.
// Ví dụ: `n = 6` nằm ở vị trí 4 → cần **4 bước**.

// ---

// ## 🧠 Công thức đệ quy quan trọng

// Giá trị số bước có thể tính **đệ quy** như sau:

// [
// f(n) = (2^{k+1} - 1) - f(n \oplus 2^k)
// ]

// **Giải thích công thức:**

// * `k` là **vị trí bit cao nhất của n** (bit có giá trị 1 lớn nhất).
// * `(1 << (k + 1)) - 1` là số có `k+1` bit đều bằng 1 (ví dụ `1111₂` = 15).
// * `n ⊕ (1 << k)` nghĩa là **tắt bit cao nhất** của `n`.

// ### 💡 Trực giác:

// 1. Nếu `n` có bit cao nhất ở vị trí `k`,
// thì để biến `n` → 0, ta **đi ngược** qua toàn bộ dãy Gray code có `k+1` bit
// (gồm `2^(k+1)` phần tử).
// 2. Nhưng vì `n` không phải là toàn bit 1, nên ta **loại bỏ** phần đã đi rồi —
// chính là `f(n ⊕ (1 << k))`.

// ---

// ## 🔍 Ví dụ minh họa chi tiết

// Giả sử `n = 6 (110₂)`:

// ### Bước 1️⃣

// * Bit cao nhất `k = 2`
// * `mask = (1 << (2 + 1)) - 1 = 7 (111₂)`
// * `next = 6 ⊕ 4 = 2`

// ⟹ `f(6) = 7 - f(2)`

// ---

// ### Bước 2️⃣ (gọi đệ quy `f(2)`)

// * `k = 1`
// * `mask = (1 << (1 + 1)) - 1 = 3 (11₂)`
// * `next = 2 ⊕ 2 = 0`

// ⟹ `f(2) = 3 - f(0) = 3`

// ---

// ### Bước 3️⃣

// Quay lại `f(6)`:
// [
// f(6) = 7 - 3 = 4
// ]

// ✅ Kết quả: **4 bước**

// ---

// ## ⚙️ Giải thích code

// ```java
// public int minimumOneBitOperations(int n) {
// return dfs(n); // Gọi hàm đệ quy chính
// }

// private int dfs(int n) {
// if (n == 0) return 0; // Nếu đã là 0, không cần thao tác nào

// int k = 31 - Integer.numberOfLeadingZeros(n);
// // Tìm vị trí bit cao nhất có giá trị 1

// int mask = (1 << (k + 1)) - 1;
// // mask = 2^(k+1) - 1, ví dụ n=6 → mask=7 (111)

// int next = n ^ (1 << k);
// // Xóa bit cao nhất trong n

// return mask - dfs(next);
// // Công thức đệ quy: f(n) = mask - f(next)
// }
// ```

// ---

// ## 🧮 Độ phức tạp

// * **Thời gian:** `O(log n)` — mỗi lần xử lý một bit.
// * **Không gian:** `O(log n)` — do độ sâu đệ quy.

// ---

// Nếu bạn muốn, mình có thể viết **phiên bản không đệ quy (iterative)** dùng
// bit manipulation thuần túy, dễ hiểu hơn và chạy nhanh hơn — bạn muốn xem
// không?
