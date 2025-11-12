
// # 2654. Minimum Number of Operations to Make All Array Elements Equal to 1(12/11/2025)
// # Rất hay — đây là một bài **tư duy số học (gcd + greedy)**.
import java.util.*;

public class b96 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = minOperations(nums);

        // Xuất kết quả
        System.out.println("Số phép biến đổi tối thiểu: " + result);

        sc.close();
    }

    public static int minOperations(int[] nums) {
        int n = nums.length; // số phần tử trong mảng
        int num1 = 0; // đếm số phần tử = 1
        int g = 0; // GCD của toàn bộ mảng

        // 🧮 Bước 1: đếm số phần tử = 1 và tính GCD toàn mảng
        for (int x : nums) {
            if (x == 1)
                num1++;
            g = gcd(g, x);
        }

        // ✅ Nếu đã có số 1 → chỉ cần n - num1 phép để biến tất cả thành 1
        if (num1 > 0)
            return n - num1;

        // ❌ Nếu GCD của toàn mảng > 1 → không thể tạo ra 1
        if (g > 1)
            return -1;

        // 🔍 Bước 2: tìm đoạn ngắn nhất có GCD = 1
        int minLen = n;
        for (int i = 0; i < n; i++) {
            int currentGcd = 0;
            for (int j = i; j < n; j++) {
                currentGcd = gcd(currentGcd, nums[j]);
                if (currentGcd == 1) { // tìm thấy đoạn có gcd = 1
                    minLen = Math.min(minLen, j - i + 1);
                    break; // dừng vì càng kéo dài đoạn, gcd không nhỏ hơn 1
                }
            }
        }

        // 🧩 Tổng số phép biến đổi = (minLen - 1) + (n - 1) = minLen + n - 2
        return minLen + n - 2;
    }

    // ⚙️ Hàm tính GCD (Euclid)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

// # Cùng giải thích bài **LeetCode 2654 - “Minimum Number of Operations to Make
// All Array Elements Equal to 1”** 👇

// # ---

// # ### 🧩 **Đề bài:**

// # Bạn được cho một mảng số nguyên `nums`.
// # Mỗi **lần thao tác**, bạn có thể **chọn hai phần tử liền kề** `nums[i]` và
// `nums[i+1]`,
// # rồi **thay thế một trong hai số đó bằng `gcd(nums[i], nums[i+1])`**,
// # trong đó `gcd(a, b)` là **ước chung lớn nhất** của `a` và `b`.

// # 👉 Mục tiêu:
// # Tìm **số lần thao tác ít nhất** để **tất cả phần tử trong mảng đều bằng
// 1**.
// # Nếu **không thể**, trả về **-1**.

// # ---

// # ### 💡 Ví dụ:

// # **Ví dụ 1:**

// # ```
// # nums = [2, 6, 3, 4]
// # ```

// # * `gcd(2, 6) = 2`
// # * `gcd(6, 3) = 3`
// # * `gcd(3, 4) = 1`

// # → Có một cặp `(3, 4)` có gcd = 1.
// # Nếu ta thay 3 hoặc 4 thành 1, thì sau đó ta có một số 1 trong mảng.

// # Sau khi có **ít nhất một số 1**, ta có thể biến các phần tử khác thành 1
// chỉ bằng `(n - 1)` thao tác nữa.

// # Ví dụ:

// # ```
// # [2, 6, 1, 4] → [1, 1, 1, 1] (3 thao tác)
// # ```

// # => Tổng cộng: **số bước để tạo ra số 1 đầu tiên (2 bước)** + **(n - 1)** =
// 3.
// # Kết quả: **3**

// # ---

// # ### ⚙️ Tư duy thuật toán:

// # 1. **Nếu mảng đã có ít nhất 1 phần tử bằng 1**
// # → kết quả = số phần tử khác 1 (vì chỉ cần thay từng phần tử còn lại bằng
// gcd với 1).
// # → `return count_non_ones`

// # 2. **Nếu không có số 1**, ta phải tìm **đoạn ngắn nhất có gcd = 1**.

// # * Tức là tìm cặp `(i, j)` sao cho `gcd(nums[i], nums[i+1], …, nums[j]) =
// 1`.
// # * Khi tìm thấy, độ dài đoạn này (`j - i`) chính là số bước để tạo **số 1
// đầu tiên**.

// # 3. Sau khi có số 1 đầu tiên, ta cần `(n - 1)` bước để biến toàn bộ thành 1.
// # → Tổng số bước = `(j - i) + (n - 1)`

// # 4. Nếu **không tồn tại đoạn nào có gcd = 1**, trả về **-1** (vì không thể
// tạo ra 1 được).

// # ---

// # ### 📘 Tóm lại:

// # | Trường hợp | Cách tính kết quả |
// # | ------------------------------------------- |
// ----------------------------- |
// # | Có sẵn ít nhất một `1` | Số phần tử khác 1 |
// # | Không có `1`, nhưng tồn tại đoạn có gcd = 1 | `(độ dài đoạn - 1) + (n -
// 1)` |
// # | Không có `1` và không có đoạn có gcd = 1 | `-1` |

// # ---

// # Bạn có muốn mình viết **code Java kèm chú thích chi tiết** cho bài này
// không?
// # Dưới đây là **giải thích chi tiết** và **code Python có chú thích từng
// dòng** cho bài **LeetCode 2654 – Minimum Number of Operations to Make All
// Array Elements Equal to 1** 👇

// # ---

// # ### 💡 **Giải thích thuật toán:**

// # 1. **Kiểm tra xem có sẵn bao nhiêu phần tử bằng 1 (`num1`)**

// # * Nếu đã có ít nhất một số `1`, ta chỉ cần `n - num1` bước để biến các phần
// tử còn lại thành `1` (vì `gcd(1, x) = 1`).

// # 2. **Nếu toàn bộ mảng không có `1`, kiểm tra `gcd` toàn cục (`g`)**

// # * Nếu `g > 1` → không thể tạo ra `1` → trả về `-1`.

// # 3. **Nếu có thể tạo ra 1**, ta tìm **đoạn ngắn nhất có `gcd = 1`**

// # * Duyệt hai vòng for:

// # * `i` là vị trí bắt đầu đoạn.
// # * `j` là vị trí kết thúc đoạn.
// # * Tính `gcd` của đoạn `[i..j]`.
// # * Khi nào `gcd == 1`, ta lưu lại độ dài đoạn (`j - i + 1`), và dừng sớm.

// # 4. **Kết quả cuối cùng:**

// # * `min_len - 1`: số bước để tạo ra số `1` đầu tiên (vì mỗi lần gộp 2 phần
// tử giảm độ dài đoạn đi 1).
// # * `+ (n - 1)`: sau khi có số `1` đầu tiên, ta cần thêm `(n - 1)` bước để
// biến tất cả thành `1`.
// # * Tổng: `min_len + n - 2`

// # ---

// # ### 🧠 **Code có chú thích:**

// # ```python
// from math import gcd
// from typing import List

// class Solution:
// def minOperations(self, nums: List[int]) -> int:
// n = len(nums)
// num1 = 0 # Đếm số lượng phần tử = 1
// g = 0 # GCD toàn cục của mảng

// # Bước 1: Đếm số 1 và tính GCD toàn mảng
// for x in nums:
// if x == 1:
// num1 += 1
// g = gcd(g, x)

// # Nếu có ít nhất một phần tử bằng 1
// if num1 > 0:
// # Chỉ cần biến n - num1 phần tử còn lại thành 1
// return n - num1

// # Nếu toàn bộ mảng có GCD > 1 => không thể tạo 1
// if g > 1:
// return -1

// # Bước 2: Tìm đoạn ngắn nhất có GCD = 1
// min_len = n
// for i in range(n):
// g = 0
// for j in range(i, n):
// g = gcd(g, nums[j])
// if g == 1: # Khi đoạn [i..j] có GCD = 1
// min_len = min(min_len, j - i + 1)
// break # Không cần xét đoạn dài hơn

// # Bước 3: Tính tổng số bước
// # (min_len - 1) để tạo 1 đầu tiên + (n - 1) để biến mọi phần tử thành 1
// return min_len + n - 2
// # ```

// # ---

// # ### 🔍 **Ví dụ minh họa:**

// # ```
// # nums = [2, 6, 3, 4]

// # Bước 1:
// # num1 = 0
// # GCD toàn cục = gcd(2,6,3,4) = 1 → có thể tạo 1

// # Bước 2:
// # Đoạn ngắn nhất có gcd=1 là [3,4] → độ dài = 2

// # Bước 3:
// # Tổng số bước = 2 + 4 - 2 = 4
// # ```

// # ---

// # Bạn có muốn mình viết luôn **phiên bản Java có chú thích chi tiết tương
// tự** không?

// Rất hay 👍 — đây là lời giải **Java** cho bài **LeetCode 2654 – Minimum
// Number of Operations to Make All Array Elements Equal to 1**.
// Dưới đây là **phân tích chi tiết thuật toán** của đoạn code bạn đưa.

// ---

// ## 🧩 **Tóm tắt bài toán**

// Ta có một mảng `nums`, và trong **mỗi bước**, ta có thể:

// * Chọn hai phần tử kề nhau `nums[i]` và `nums[i + 1]`,
// * Thay **phần tử thứ hai** bằng `gcd(nums[i], nums[i + 1])`.

// Mục tiêu: **biến tất cả các phần tử trong mảng thành 1**
// → Tìm **số bước tối thiểu** để làm được điều đó.

// ---

// ## 🚀 **Giải thích thuật toán**

// ### 🥇 Bước 1: Kiểm tra có bao nhiêu phần tử bằng 1

// ```java
// int num1 = 0;
// for (int x : nums) {
// if (x == 1) num1++;
// g = gcd(g, x);
// }
// ```

// * `num1`: đếm xem trong mảng có bao nhiêu số `1`.
// * `g`: tính **GCD của toàn bộ mảng**.

// 👉 Nếu trong mảng **đã có sẵn số 1**, ta không cần biến đổi gì phức tạp.
// Vì `gcd(1, x) = 1` cho mọi `x`.
// → Chỉ cần “kéo” các phần tử khác về 1 bằng `(n - num1)` phép gộp.

// ```java
// if (num1 > 0) return n - num1;
// ```

// ---

// ### ❌ Bước 2: Nếu không có số 1 nào, kiểm tra GCD toàn mảng

// ```java
// if (g > 1) return -1;
// ```

// * Nếu `GCD(nums)` > 1 → nghĩa là tất cả các phần tử đều có ước chung > 1.
// * Khi đó **không thể nào tạo ra 1 được**, vì `gcd(a, b)` không bao giờ nhỏ
// hơn `gcd(nums)`.

// 👉 Trả về `-1` (không thể làm được).

// ---

// ### 🔍 Bước 3: Tìm đoạn ngắn nhất có GCD = 1

// ```java
// int minLen = n;
// for (int i = 0; i < n; i++) {
// int currentGcd = 0;
// for (int j = i; j < n; j++) {
// currentGcd = gcd(currentGcd, nums[j]);
// if (currentGcd == 1) {
// minLen = Math.min(minLen, j - i + 1);
// break;
// }
// }
// }
// ```

// * Duyệt qua mọi **đoạn con `[i..j]`** của mảng.
// * Tính `gcd` của đoạn đó.
// * Nếu đoạn đó có `gcd == 1`, nghĩa là ta có thể tạo ra **ít nhất một số 1**
// bằng `(j - i)` phép gộp.
// * Giữ lại đoạn **ngắn nhất** có `gcd == 1` → lưu vào `minLen`.

// 👉 Mục đích: tìm **đoạn tối thiểu** cần gộp để lần đầu tiên tạo ra được số
// `1`.

// ---

// ### 🧮 Bước 4: Tính tổng số phép biến đổi

// ```java
// return minLen + n - 2;
// ```

// Giải thích:

// * `minLen - 1`: số bước cần để tạo ra **số 1 đầu tiên**.
// (vì mỗi bước gộp làm giảm độ dài đoạn đi 1)
// * `n - 1`: sau khi đã có 1 số 1, ta cần thêm `(n - 1)` bước để biến tất cả
// còn lại thành 1.
// Vì `gcd(1, x) = 1`, ta chỉ cần gộp lần lượt từng phần tử còn lại với số 1.

// → Tổng = `(minLen - 1) + (n - 1) = minLen + n - 2`

// ---

// ## 📘 **Ví dụ minh họa**

// ```
// nums = [2, 6, 3, 4]

// GCD toàn mảng = 1 → có thể tạo 1

// Không có số 1 ban đầu.

// Tìm đoạn ngắn nhất có GCD = 1:
// [2,6] -> gcd=2
// [2,6,3] -> gcd=1 → độ dài = 3 → minLen = 3

// Tổng số bước = minLen + n - 2 = 3 + 4 - 2 = 5
// ```

// ---

// ## ✅ **Tóm tắt ý tưởng**

// | Bước | Mục tiêu | Kết quả |
// | ---- | ------------------------------- | ------------------- |
// | 1 | Có sẵn số 1? | → Trả về `n - num1` |
// | 2 | `GCD(nums) > 1`? | → Trả về `-1` |
// | 3 | Tìm đoạn ngắn nhất có `GCD = 1` | → Lưu `minLen` |
// | 4 | Tính tổng bước | `minLen + n - 2` |

// ---

// Bạn có muốn mình viết **phiên bản có chú thích đầy đủ trong code Java**
// (giống các bài trước bạn học) không?
