
// # 2125. Number of Laser Beams in a Bank(27/10/2025)
import java.util.*;

public class b80 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        sc.nextLine(); // đọc bỏ ký tự xuống dòng sau nextInt()

        String[] bank = new String[n];
        for (int i = 0; i < n; i++) {
            bank[i] = sc.nextLine();
        }

        int result = numberOfBeams(bank);
        System.out.println(result);

        sc.close();
    }

    // ✅ Hàm chính tính số tia laser giữa các hàng có thiết bị
    public static int numberOfBeams(String[] bank) {
        int number = 0; // Tổng số tia laser
        int last = mun(bank[0]); // Số thiết bị ('1') trong hàng đầu tiên

        // Duyệt qua các hàng tiếp theo
        for (int i = 1; i < bank.length; i++) {
            int current = mun(bank[i]); // Số thiết bị của hàng hiện tại

            if (current == 0)
                continue; // Nếu hàng này không có thiết bị, bỏ qua

            // Tính số tia laser giữa hàng trước và hàng hiện tại:
            // last * current, đồng thời cập nhật last = current
            number += (last * (last = current));
        }

        return number; // Trả về tổng số tia laser
    }

    // ✅ Hàm đếm số '1' trong chuỗi (tức là số thiết bị trong hàng)
    static int mun(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += (s.charAt(i) - '0'); // '1' - '0' = 1, '0' - '0' = 0
        }
        return res;
    }
}

// Rất hay — đây là cách viết ngắn gọn, tối ưu của bài **LeetCode 2125 – Number
// of Laser Beams in a Bank** bằng **Java**.
// 👉 Mình sẽ **giải thích chi tiết từng phần thuật toán** để bạn hiểu hoàn toàn
// cách nó hoạt động.

// ---

// ### 🧠 Ý tưởng chính

// Mục tiêu là **đếm số tia laser giữa các hàng trong ngân hàng (bank)**.
// Mỗi hàng là một chuỗi nhị phân (`'0'` hoặc `'1'`):

// * `'1'` = có thiết bị bảo mật.
// * `'0'` = không có thiết bị.

// Một tia laser được tạo **giữa 2 hàng liên tiếp có thiết bị**,
// và số tia giữa 2 hàng đó bằng:
// [
// \text{(số thiết bị hàng trước)} \times \text{(số thiết bị hàng sau)}
// ]

// ---

// ### 🧩 Giải thích code chi tiết

// ```java
// public int numberOfBeams(String[] bank) {
// int number = 0, last = mun(bank[0]);
// ```

// * `number`: tổng số tia laser.
// * `last`: số lượng thiết bị ('1') ở hàng **trước đó**.
// * `mun(bank[0])`: đếm số `'1'` ở hàng đầu tiên.

// ---

// ```java
// for (int i = 1; i < bank.length; i++) {
// int current = mun(bank[i]);
// ```

// * Duyệt qua từng hàng từ **hàng thứ 2 trở đi**.
// * `current`: số thiết bị ở hàng hiện tại (đếm bằng hàm `mun`).

// ---

// ```java
// if (current == 0) continue;
// ```

// * Nếu hàng hiện tại **không có thiết bị**, thì bỏ qua (không có tia laser nào
// được tạo).

// ---

// ```java
// number += (last * (last = current));
// ```

// * Biểu thức gộp:
// 1️⃣ `last * current`: số tia laser giữa 2 hàng có thiết bị.
// 2️⃣ `(last = current)`: sau khi tính xong, cập nhật `last` thành `current` để
// chuẩn bị cho hàng kế tiếp.

// Ví dụ:
// Nếu hàng trước có `3` thiết bị, hàng hiện tại có `2`,
// → thêm `3 * 2 = 6` tia laser vào `number`.

// ---

// ```java
// return number;
// ```

// * Trả về tổng số tia laser.

// ---

// ### 🔢 Hàm `mun(String s)`

// ```java
// int mun(String s) {
// int res = 0;
// for (int i = 0; i < s.length(); i++)
// res += (s.charAt(i) - '0');
// // return res;
// }
// ```

// Hàm này **đếm số ký tự `'1'`** trong chuỗi `s`.

// * `s.charAt(i) - '0'` → chuyển ký tự `'0'` hoặc `'1'` thành số 0 hoặc 1.
// * Ví dụ: `'1' - '0' = 1`, `'0' - '0' = 0`.
// * Cộng dồn vào `res` để ra tổng số thiết bị trong hàng.

// ---

// ### 🧮 Ví dụ minh họa

// ```java
// bank = ["011001", "000000", "010100", "001000"]
// ```

// | Hàng | Số thiết bị (`mun`) | Tính toán | `number` | `last` |
// | ----------- | ------------------- | --------- | -------- | ------ |
// | 0: "011001" | 3 | — | 0 | 3 |
// | 1: "000000" | 0 | Bỏ qua | 0 | 3 |
// | 2: "010100" | 2 | +3×2=6 | 6 | 2 |
// | 3: "001000" | 1 | +2×1=2 | 8 | 1 |

// ✅ **Kết quả:** `number = 8`

// ---

// ### ⚙️ Độ phức tạp

// * **Thời gian:** `O(n * m)`
// (n = số hàng, m = độ dài mỗi chuỗi)
// * **Không gian:** `O(1)`
// (chỉ dùng vài biến đếm, không mảng phụ)

// ---

// Tóm lại:

// * `mun()` đếm số `'1'` trong mỗi hàng.
// * `numberOfBeams()` nhân số thiết bị giữa hai hàng có thiết bị liên tiếp để
// tính tổng tia laser.
// * Câu lệnh `number += (last * (last = current));` là cách viết ngắn gọn thay
// vì:

// ```java
// number += last * current;
// last = current;

// from typing import List

// class Solution:
// def numberOfBeams(self, bank: List[str]) -> int:
// ans = 0 # Biến lưu tổng số tia laser trong ngân hàng
// prev = 0 # Lưu số lượng thiết bị ('1') ở hàng trước có thiết bị

// # Duyệt qua từng hàng trong ngân hàng
// for row in bank:
// cnt = row.count('1') # Đếm số thiết bị ('1') trong hàng hiện tại

// if cnt > 0: # Nếu hàng này có thiết bị
// # Tính số tia laser giữa hàng trước (prev) và hàng hiện tại (cnt)
// ans += prev * cnt

// # Cập nhật prev cho lần lặp tiếp theo (vì chỉ hàng có thiết bị mới tính)
// prev = cnt

// # Trả về tổng số tia laser
// return ans

// ### 🧩 **Đề bài**

// # Một **ngân hàng** có hệ thống **bảo mật bằng laser** được biểu diễn dưới
// dạng **mảng chuỗi nhị phân (binary strings)**.

// # * Mỗi phần tử trong mảng `bank[i]` là **một hàng (row)** trong tòa nhà.
// # * Mỗi ký tự `'1'` trong chuỗi đại diện cho **một thiết bị bảo mật (security
// device)**.
// # * Mỗi `'0'` là **khoảng trống** (không có thiết bị).

// # ---

// # ### 💡 **Quy tắc tạo tia laser**

// # * **Tia laser chỉ xuất hiện giữa hai hàng có thiết bị** (tức là 2 hàng đều
// có ít nhất một `'1'`).
// # * **Tia chỉ đi giữa các hàng không liền kề cũng được**, **miễn là hàng ở
// giữa không có thiết bị** (toàn là `'0'`).
// # * **Số tia giữa hai hàng** =
// # `(số thiết bị ở hàng thứ nhất) × (số thiết bị ở hàng thứ hai)`

// # ---

// # ### 🎯 **Nhiệm vụ**

// # Tính **tổng số tia laser** trong ngân hàng.

// # ---

// # ### 🧮 **Ví dụ minh họa**

// # #### Ví dụ 1:

// # ```python
// # bank = ["011001", "000000", "010100", "001000"]
// # ```

// # Biểu diễn:

// # ```
// # Row 0: 0 1 1 0 0 1 → có 3 thiết bị
// # Row 1: 0 0 0 0 0 0 → 0 thiết bị (bỏ qua)
// # Row 2: 0 1 0 1 0 0 → có 2 thiết bị
// # Row 3: 0 0 1 0 0 0 → có 1 thiết bị
// # ```

// # → Chỉ tính **tia giữa các hàng có thiết bị**:

// # * Giữa hàng 0 (3 thiết bị) và hàng 2 (2 thiết bị):
// # `3 × 2 = 6 tia`
// # * Giữa hàng 2 (2 thiết bị) và hàng 3 (1 thiết bị):
// # # `2 × 1 = 2 tia`

// # # ✅ Tổng: `6 + 2 = 8`

// # **Output:** `8`

// # ---

// # ### 🔢 **Tư duy thuật toán**

// # 1. Đếm số thiết bị (`count_1s`) ở mỗi hàng.
// # 2. Bỏ qua các hàng có 0 thiết bị.
// # 3. Với mỗi cặp **liền kề (có thiết bị)**, tính số tia:
// # `ans += prev_count * curr_count`
// # 4. Cập nhật `prev_count = curr_count`.

// # ---

// # ### 💻 **Code mẫu (Python)**

// # ```python
// # class Solution:
// # def numberOfBeams(self, bank: List[str]) -> int:
// # prev = 0
// # ans = 0

// # for row in bank:
// # curr = row.count('1')
// # if curr > 0:
// # ans += prev * curr
// # prev = curr

// # return ans
// # ```

// # ---

// # ### ⚙️ **Phân tích ví dụ**

// # | Hàng | Thiết bị ('1') | Tia sinh ra | Tổng |
// # | ------ | -------------- | ----------- | ---- |
// # | 011001 | 3 | - | - |
// # | 000000 | 0 | - | - |
// # | 010100 | 2 | 3×2 = 6 | 6 |
// # | 001000 | 1 | 2×1 = 2 | 8 |

// # ✅ Kết quả cuối: **8**

// # ---

// ### 🔍 Giải thích thuật toán:

// # 1. **Khởi tạo:**

// # * `ans = 0`: tổng số tia laser.
// # * `prev = 0`: số thiết bị ở hàng trước (nếu hàng trước không có thiết bị,
// bỏ qua).

// # 2. **Duyệt từng hàng:**

// # * Dùng `row.count('1')` để đếm số lượng thiết bị (`cnt`) trong hàng đó.
// # * Nếu hàng không có thiết bị (`cnt == 0`), bỏ qua hàng đó.

// # 3. **Khi gặp hàng có thiết bị:**

// # * Nếu `prev > 0`, nghĩa là có một hàng trước đó có thiết bị.
// # * Khi đó, **tia laser giữa hai hàng** được tính bằng:
// # [
// # prev \times cnt
// # ]
// # vì **mỗi thiết bị ở hàng trên** sẽ tạo tia laser với **mỗi thiết bị ở hàng
// dưới**.

// # 4. **Cập nhật `prev`:**

// # * Đặt `prev = cnt` để chuẩn bị tính cho hàng có thiết bị kế tiếp.

// # 5. **Trả kết quả cuối cùng:** `ans`.

// # ---

// # ### 🧮 Ví dụ minh họa

// # ```python
// # bank = ["011001", "000000", "010100", "001000"]
// # ```

// # | Hàng | Số thiết bị (`cnt`) | Tính toán | `ans` | `prev` |
// # | -------- | ------------------- | ---------------------------- | ----- |
// ------ |
// # | "011001" | 3 | Không có hàng trước ⇒ bỏ qua | 0 | 3 |
// # | "000000" | 0 | Bỏ qua vì không có thiết bị | 0 | 3 |
// # | "010100" | 2 | +3×2 = 6 | 6 | 2 |
// # | "001000" | 1 | +2×1 = 2 | 8 | 1 |

// # ✅ **Kết quả cuối cùng:** `ans = 8`

// # ---

// # 👉 Tóm lại:
// # Thuật toán chỉ cần duyệt **một lần qua mảng** → **O(n)**,
// # rất hiệu quả cho bài này.
