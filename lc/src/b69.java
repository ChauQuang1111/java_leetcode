
// 2598. Smallest Missing Non-negative Integer After Operations(16/10/2025)
import java.util.*;

public class b69 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int value = sc.nextInt();

        int result = findSmallestInteger(nums, value);
        sc.close();
        System.out.println(result);
    }

    public static int findSmallestInteger(int[] nums, int value) {
        // Mảng đếm số lượng phần tử có cùng phần dư mod value
        int[] modularDivisionRes = new int[value];

        // Duyệt qua từng phần tử trong mảng nums
        for (int n : nums) {
            // Lấy phần dư của n chia cho value
            int modDivValue = n % value;

            // Nếu phần dư âm, cộng thêm value để chuẩn hóa về dương
            if (modDivValue < 0) {
                modDivValue += value;
            }

            // Tăng số lượng phần tử có cùng phần dư đó lên 1
            modularDivisionRes[modDivValue]++;
        }

        // Giả sử phần dư 0 có tần suất nhỏ nhất ban đầu
        int min = modularDivisionRes[0];
        int position = 0;

        // Tìm phần dư có tần suất nhỏ nhất
        for (int i = 0; i < value; i++) {
            if (modularDivisionRes[i] < min) {
                // Cập nhật phần dư có ít phần tử nhất
                position = i;
                min = modularDivisionRes[i];
            }
        }

        // Số nhỏ nhất không thể tạo ra = value * min + position
        return value * min + position;
    }
}

// Rất hay 👍 — cùng mình giải thích chi tiết đề bài **LeetCode 2598 – Smallest
// Missing Non-negative Integer After Operations** nhé 👇

// ---

// ## 🧩 **Đề bài gốc (tóm tắt)**

// Cho bạn:

// * Một mảng `nums` gồm **số nguyên (có thể âm)**.
// * Một số nguyên `value`.

// 👉 Bạn có thể **thực hiện bao nhiêu lần tùy ý (kể cả 0 lần)** các **phép cộng
// hoặc trừ `value`** lên **bất kỳ phần tử nào** trong mảng `nums`.

// Sau khi bạn đã thực hiện các phép toán tùy ý, bạn phải tìm ra:

// > **Số nguyên không âm nhỏ nhất (≥ 0)** mà **không xuất hiện** trong mảng
// `nums` sau khi thực hiện các phép toán.

// ---

// ## 🧠 **Hiểu đơn giản hơn**

// Mỗi phần tử `x` trong `nums` có thể được biến đổi thành **bất kỳ số nào có
// cùng phần dư với `x (mod value)`**.
// Ví dụ:

// * Nếu `value = 3`
// thì `x = 2` có thể biến thành `…, -4, -1, 2, 5, 8, 11, …`
// (tức là mọi số ≡ 2 (mod 3)).

// → Vì vậy, chỉ phần **dư khi chia cho `value`** của các phần tử mới quan
// trọng!

// ---

// ## 🎯 **Mục tiêu**

// Tìm **số nguyên không âm nhỏ nhất** mà **không thể được tạo ra** bằng cách
// cộng/trừ `value` tùy ý với các phần tử trong `nums`.

// ---

// ## 📘 **Ví dụ 1**

// ```text
// Input: nums = [1, -10, 7, 13, 6, 8], value = 5
// ```

// ### Bước 1️⃣: Lấy phần dư (mod 5)

// ```
// nums mod 5 → [1, 0, 2, 3, 1, 3]
// ```

// Tức là ta có các phần dư xuất hiện:

// ```
// {0, 1, 2, 3}
// ```

// ### Bước 2️⃣: Nhóm theo phần dư

// Ta có 4 phần dư khác nhau, mỗi phần dư có thể “tạo ra” các số 0, 1, 2, 3,...
// thuộc cùng nhóm đó.

// ### Bước 3️⃣: Số nhỏ nhất không thể tạo ra

// Ta xét các số nguyên 0, 1, 2, 3, 4, 5, ...

// * 0 → có thể tạo (phần dư 0)
// * 1 → có thể tạo (phần dư 1)
// * 2 → có thể tạo (phần dư 2)
// * 3 → có thể tạo (phần dư 3)
// * 4 → ❌ không có phần dư 4 trong nums ⇒ không thể tạo ra

// ✅ **Kết quả = 4**

// ---

// ## 📘 **Ví dụ 2**

// ```text
// Input: nums = [1,2,3,4,5], value = 1
// ```

// * Mọi số mod 1 đều = 0
// → ta có thể tạo **mọi số nguyên không âm**
// → Số nhỏ nhất không thể tạo ra = 5 (vì chỉ có 5 phần tử ban đầu)

// ✅ **Output: 5**

// ---

// ## 🔍 **Ý tưởng thuật toán**

// 1. Với mỗi `num` trong `nums`, tính `r = ((num % value) + value) % value`
// (để xử lý cả số âm).
// 2. Đếm tần suất xuất hiện của mỗi phần dư `r`.
// 3. Sau đó, ta lần lượt thử từ `i = 0, 1, 2, 3, ...`,
// xem phần dư `i % value` còn tần suất không.

// * Nếu còn, ta “dùng” một phần tử thuộc nhóm đó.
// * Nếu không còn, **i chính là đáp án**.

// ---

// ## ⚡ **Độ phức tạp**

// * `O(n)` thời gian
// * `O(value)` bộ nhớ

// ---

// ## ✅ **Kết luận**

// > Đề bài yêu cầu tìm **số nguyên không âm nhỏ nhất** không thể tạo ra,
// > khi mỗi số trong `nums` có thể được tăng hoặc giảm bất kỳ bội số của
// `value`.

// ---

// Bạn có muốn mình minh họa lại **cách tìm đáp án từng bước bằng code Python +
// mô phỏng ví dụ [1, -10, 7, 13, 6, 8], value=5** để bạn thấy cách `mod` và
// `count` hoạt động không?
// Rất hay — bạn đang xem một **phiên bản tối ưu hơn** của thuật toán bài
// **2598. Smallest Missing Non-negative Integer After Operations**,
// dưới đây là **giải thích chi tiết từng bước** và **ý tưởng logic đằng sau
// đoạn code** 👇

// ---

// ## 🎯 Mục tiêu bài toán

// Tìm **số nguyên không âm nhỏ nhất** mà **không thể tạo ra** sau khi cộng/trừ
// `value` tùy ý cho các phần tử trong `nums`.

// ---

// ## 💡 Ý tưởng chính

// Nếu ta chỉ có thể cộng hoặc trừ `value` cho mỗi phần tử,
// → thì **chỉ phần dư (mod value)** của mỗi phần tử là quan trọng.

// Mọi số có cùng phần dư mod `value` có thể chuyển hóa lẫn nhau bằng cách
// cộng/trừ `value`.

// Vì vậy:

// * Ta **gom các phần tử theo phần dư mod value**.
// * Đếm xem **mỗi phần dư xuất hiện bao nhiêu lần**.
// * Số nhỏ nhất không tạo được sẽ **phụ thuộc vào phần dư có tần suất nhỏ
// nhất**.

// ---

// ## 🧱 Giải thích từng dòng code

// ```java
// int[] modularDivisionRes = new int[value];
// ```

// 👉 Mảng `modularDivisionRes[i]` lưu số lượng phần tử có **phần dư = i** khi
// chia cho `value`.

// ---

// ```java
// for (int n : nums) {
// int modDivValue = n % value;
// if (modDivValue < 0) {
// modDivValue += value;
// }
// modularDivisionRes[modDivValue]++;
// }
// ```

// 👉 Duyệt từng phần tử trong `nums`:

// * Lấy phần dư `modDivValue = n % value`
// * Nếu phần dư âm, cộng thêm `value` để đưa về dương
// (VD: `-2 % 5 = -2` ⇒ `(-2 + 5) = 3`)
// * Tăng bộ đếm `modularDivisionRes[modDivValue]` lên 1.

// 📊 Sau vòng lặp, bạn có số lượng phần tử thuộc từng nhóm dư.

// ---

// ```java
// int min = modularDivisionRes[0];
// int position = 0;
// for (int i = 0; i < value; i++) {
// if (modularDivisionRes[i] < min) {
// position = i;
// min = modularDivisionRes[i];
// }
// }
// ```

// 👉 Tìm phần dư (`position`) có **tần suất nhỏ nhất** (`min`).

// 💡 Tại sao?

// * Mỗi phần dư `r` có thể tạo ra các số:

// ```
// r, r + value, r + 2*value, r + 3*value, ...
// ```
// * Nếu phần dư `r` xuất hiện `k` lần, bạn có thể tạo được `k` số đầu tiên
// trong dãy trên.
// Số tiếp theo (thứ `k`) **sẽ bị thiếu**.

// ---

// ```java
// return value * min + position;
// ```

// 👉 Số nhỏ nhất bị thiếu chính là:

// ```
// min * value + position
// ```

// 📘 Giải thích:

// * `min` là số lần bạn đã “đầy” đủ phần dư này.
// * `position` là phần dư của con số tiếp theo chưa có.

// ---

// ## 🧠 Ví dụ minh họa

// ### Input:

// ```
// nums = [1, -10, 7, 13, 6, 8]
// value = 5
// ```

// | num | num % 5 | chuẩn hóa | |
// |------|----------|-----------|
// | 1 | 1 | 1 |
// | -10 | 0 | 0 |
// | 7 | 2 | 2 |
// | 13 | 3 | 3 |
// | 6 | 1 | 1 |
// | 8 | 3 | 3 |

// → `modularDivisionRes = [1, 2, 1, 2, 0]`

// * Min = 0
// * Position = 4

// Kết quả:

// ```
// value * min + position = 5 * 0 + 4 = 4
// ```

// ✅ Output: **4**

// ---

// ## ⚙️ Độ phức tạp

// * Thời gian: `O(n + value)`
// * Bộ nhớ: `O(value)`
// → rất nhanh và tối ưu hơn phiên bản HashMap trước đó.

// ---

// Bạn có muốn mình thêm **phiên bản có `main()` dùng `Scanner` và chú thích
// từng dòng chi tiết trong code** không?
