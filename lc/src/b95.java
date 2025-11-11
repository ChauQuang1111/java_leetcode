
// 474. Ones and Zeroes(11/11/2025)
// Rất hay — bài **LeetCode 474: “Ones and Zeroes”** là một bài **Dynamic Programming (DP)** khá kinh điển 💡
// Dưới đây là phần **giải thích chi tiết, dễ hiểu nhất** cho bạn:
import java.util.*;

public class b95 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int len = sc.nextInt();
        sc.nextLine(); // đọc bỏ dòng trống

        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = sc.nextLine();
        }

        int m = sc.nextInt();

        int n = sc.nextInt();

        int res = findMaxForm(strs, m, n);

        System.out.println(res);
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] = số lượng chuỗi tối đa có thể chọn
        // khi ta có i số 0 và j số 1
        int[][] dp = new int[m + 1][n + 1];

        // Duyệt qua từng chuỗi trong strs
        for (String s : strs) {
            // Đếm số lượng 0 và 1 trong chuỗi s
            int zeros = 0, ones = 0;
            for (char c : s.toCharArray()) {
                if (c == '0')
                    zeros++;
                else
                    ones++;
            }

            // Cập nhật dp từ lớn về nhỏ để tránh ghi đè giá trị cũ
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    // Nếu chọn chuỗi s, ta tốn zeros và ones, nhận thêm +1
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        // Kết quả là số chuỗi tối đa có thể chọn với m số 0 và n số 1
        return dp[m][n];
    }

}

// Rất hay👏—
// mình sẽ
// giải thích**thuật toán bài 474.
// Ones and Zeroes**
// thật rõ
// ràng và
// dễ hiểu nhé.

// ---

// ##🧩**
// Đề bài
// tóm tắt**

// Cho một
// danh sách
// các chuỗi
// nhị phân`strs`,
// và hai
// số nguyên`m`,`n`—
// đại diện cho**tổng số lượng 0 và 1
// tối đa
// bạn có
// thể dùng**.

// 👉
// Hãy tìm**
// số chuỗi
// tối đa
// có thể chọn**
// sao cho**tổng số
// ký tự'0'≤m**và**tổng số
// ký tự'1'≤n**.

// ---

// ##🎯**Ví dụ:**

// ```Input:strs=["10","0001","111001","1","0"]m=5 n=3

// Output:4```

// 👉
// Ta có
// thể chọn 4 chuỗi:`"10"`,`"0001"`,`"1"`,`"0"`→
// tổng số 0=5,
// tổng số 1=3→
// hợp lệ✅

// ---

// ##💡**Ý tưởng chính:

// Dynamic Programming (Knapsack 2D)**

// Đây là **bài toán “ba lô 0-1” (0-1 Knapsack)** dạng hai chiều:

// * Mỗi chuỗi `s` giống như **một món đồ** có “trọng lượng” là:

// * `zeros` = số lượng ký tự '0' trong chuỗi.
// * `ones` = số lượng ký tự '1' trong chuỗi.
// * Ta có hai “ba lô” giới hạn:

// * Ba lô chứa tối đa `m` số 0.
// * Ba lô chứa tối đa `n` số 1.

// Ta cần **chọn tối đa số chuỗi**, sao cho **không vượt quá m và n**.

// ---

// ## 🧠 **Bước triển khai thuật toán**

// ### 1️⃣ Đếm số lượng 0 và 1 trong từng chuỗi

// ```java
// for (String s : strs) {
// int zeros = 0, ones = 0;
// for (char c : s.toCharArray()) {
// if (c == '0') zeros++;
// else ones++;
// }
// ```

// ---

// ### 2️⃣ Dùng mảng DP 2 chiều

// `dp[i][j]` = số lượng chuỗi tối đa có thể chọn khi ta còn:

// * i số 0 khả dụng
// * j số 1 khả dụng

// Khởi tạo:

// ```java
// int[][] dp = new int[m + 1][n + 1];
// ```

// ---

// ### 3️⃣ Cập nhật giá trị DP cho mỗi chuỗi

// Ta **duyệt ngược** từ `m → zeros` và `n → ones`
// (để tránh dùng cùng 1 chuỗi nhiều lần — giống bài Knapsack 0/1).

// ```java
// for (int i = m; i >= zeros; i--) {
// for (int j = n; j >= ones; j--) {
// dp[i][j] = Math.max(
// dp[i][j],
// dp[i - zeros][j - ones] + 1
// );
// }
// }
// // ```

// 👉 Giải thích:

// * `dp[i - zeros][j - ones] + 1`: nếu ta **chọn** chuỗi này,
// thì ta phải “trả” `zeros` số 0 và `ones` số 1, và được +1 chuỗi.
// * `dp[i][j]`: nếu ta **không chọn** chuỗi này.

// ### 4️⃣ Kết quả cuối cùng

// Sau khi xử lý hết,
// `dp[m][n]` chính là **số chuỗi tối đa chọn được**.

// ---

// ## ⚙️ **Độ phức tạp**

// | Phần | Phân tích |
// | --------- | --------------------------------- |
// | Thời gian | `O(L * m * n)` với `L` = số chuỗi |
// | Bộ nhớ | `O(m * n)` |

// ---

// ## 📘 **Ví dụ minh họa:**

// Giả sử:

// strs = ["10", "0", "1"]
// m = 1
// n = 1
// ```

// Ta có:

// | Chuỗi | zeros | ones |
// | ----- | ----- | ---- |
// | "10" | 1 | 1 |
// | "0" | 1 | 0 |
// | "1" | 0 | 1 |

// Bắt đầu DP:

// 1️⃣ Chọn `"10"` → tốn 1 zero, 1 one → dp[1][1] = 1
// 2️⃣ Chọn `"0"` → dp[1][0] = 1
// 3️⃣ Chọn `"1"` → dp[0][1] = 1

// Cuối cùng dp[1][1] = 2 ✅
// (vì ta có thể chọn `"0"` và `"1"` cùng lúc)

// ---

// Tóm lại:

// > ✅ Thuật toán này là **Knapsack 2D**,
// > trong đó ta dùng DP để chọn **số chuỗi tối đa**
// > thỏa điều kiện về số lượng 0 và 1.

// ---

// Bạn có muốn mình minh họa từng bước chạy **với bảng DP cụ thể** (cho ví dụ
// `"10", "0", "1"`) không?
// Nó giúp bạn hình dung cách giá trị dp thay đổi sau mỗi vòng lặp.

// ## 🧩 Đề bài:

// Bạn được cho:

// * Một **mảng các chuỗi nhị phân** `strs` (chỉ gồm `'0'` và `'1'`).
// * Hai số nguyên `m` và `n`.

// 👉 **Mỗi chuỗi** trong `strs` có thể được xem như một “món đồ” với:

// * “trọng lượng” là số lượng `'0'`,
// * “giá trị” là 1,
// * “ràng buộc” là: bạn có **không quá m số 0** và **không quá n số 1** để
// chọn.

// ---

// ## 🎯 Yêu cầu:

// Hãy chọn **số lượng lớn nhất các chuỗi** trong `strs` sao cho:

// * Tổng số `'0'` của các chuỗi được chọn ≤ `m`
// * Tổng số `'1'` của các chuỗi được chọn ≤ `n`

// ---

// ## 🧠 Ví dụ:

// ### Input:

// ```python
// strs = ["10","0001","111001","1","0"]
// m = 5
// n = 3
// ```

// ### Output:

// ```
// 4
// ```

// ### Giải thích:

// Ta có thể chọn 4 chuỗi:
// `["10", "0001", "1", "0"]`

// Tổng số 0 = 1 + 3 + 0 + 1 = 5 ✅
// Tổng số 1 = 1 + 1 + 1 + 0 = 3 ✅
// → Không vượt giới hạn, và số chuỗi chọn được là **4**.

// ---

// ## ⚙️ Ý tưởng thuật toán:

// Bài này là một biến thể của **Balo (Knapsack Problem)** hai chiều.

// * “Khối lượng” thứ nhất là số lượng **0** (`m`)
// * “Khối lượng” thứ hai là số lượng **1** (`n`)
// * “Giá trị” mỗi chuỗi là **1** (chọn được 1 chuỗi)

// → Dùng **Dynamic Programming 2D** để tối ưu.

// ---

// ## 🔢 Trạng thái DP:

// Giả sử `dp[i][j]` = **số lượng chuỗi tối đa có thể chọn**
// với **i số 0** và **j số 1** khả dụng.

// ---

// ## 🔁 Công thức chuyển trạng thái:

// Với mỗi chuỗi `s` trong `strs`, ta đếm:

// * `zero = s.count('0')`
// * `one = s.count('1')`

// Sau đó cập nhật từ **lớn về nhỏ** (tránh ghi đè):

// ```
// for i from m down to zero:
// for j from n down to one:
// dp[i][j] = max(dp[i][j], dp[i-zero][j-one] + 1)
// ```

// ---

// ## 💡 Trực giác:

// * Nếu ta **không chọn** chuỗi `s` → `dp[i][j]` giữ nguyên.
// * Nếu ta **chọn** `s` → ta tốn `zero` và `one`, và nhận thêm `+1` giá trị.

// ---

// ## 🧮 Ví dụ nhỏ:

// `strs = ["10","0001","1","0"], m = 5, n = 3`

// Khởi tạo:

// ```
// dp = [[0]*(n+1) for _ in range(m+1)]
// ```

// Cập nhật từng chuỗi:

// 1. "10" → 1 zero, 1 one
// 2. "0001" → 3 zero, 1 one
// 3. "1" → 0 zero, 1 one
// 4. "0" → 1 zero, 0 one

// → Cập nhật ngược (tránh ghi đè giá trị cũ).

// ---

// ## 🏁 Kết quả:

// `dp[m][n]` = **số chuỗi tối đa có thể chọn**.

// ---

// ## 🔹 Độ phức tạp:

// * Thời gian: `O(len(strs) * m * n)`
// * Không gian: `O(m * n)`

// ---

// Bạn có muốn mình viết luôn **mã Python hoặc Java có chú thích chi tiết** cho
// bài này không (kèm `Scanner` hoặc `input()` tuỳ ngôn ngữ bạn muốn)?
