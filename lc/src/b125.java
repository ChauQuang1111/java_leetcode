// Delete Columns to Make Sorted III(22/12/2025)
// Bài **Delete Columns to Make Sorted III** (LeetCode 960) – mình giải thích **đề bài bằng tiếng Việt, dễ hiểu**, không đi vào code trước nhé.
// Mình sẽ **giải thích thuật toán của đoạn code này từng bước**, đúng theo tinh thần bài **Delete Columns to Make Sorted III (LC 960)**, tập trung vào **ý tưởng DP + LIS trên cột**.
import java.util.*;

public class b125 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số chuỗi
        int m = sc.nextInt();
        sc.nextLine(); // bỏ dòng thừa

        String[] strs = new String[m];

        // Nhập từng chuỗi
        for (int i = 0; i < m; i++) {
            strs[i] = sc.nextLine();
        }

        // Gọi lời giải
        int result = minDeletionSize(strs);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    public static int minDeletionSize(String[] strs) {
        int n = strs[0].length(); // số cột

        // dp[i]: số cột tối đa có thể giữ lại, kết thúc tại cột i
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // mỗi cột riêng lẻ luôn hợp lệ

        // LIS trên cột
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                // nếu cột i có thể đứng sau cột j
                if (isValid(strs, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // tìm LIS lớn nhất
        int max = 0;
        for (int val : dp) {
            max = Math.max(max, val);
        }

        // số cột cần xóa = tổng cột - số cột giữ được nhiều nhất
        return n - max;
    }

    /*
     * Kiểm tra xem cột i có thể đứng sau cột j hay không
     * Điều kiện:
     * - Với mọi chuỗi s: s[j] <= s[i]
     */
    public static boolean isValid(String[] strs, int j, int i) {
        for (String s : strs) {
            if (s.charAt(j) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}

/*
 * Bài toán: Delete Columns to Make Sorted III (LeetCode 960)
 * Ý tưởng:
 *  - Xem mỗi cột là một phần tử
 *  - Tìm dãy cột dài nhất có thể giữ lại sao cho
 *    các chuỗi vẫn được sắp xếp từ trên xuống
 *  - Đây là bài toán LIS (Longest Increasing Subsequence) trên cột
 */

// ## 🎯 Ý tưởng cốt lõi

// Thay vì nghĩ “xóa cột nào”, ta nghĩ ngược lại:

// > **Giữ lại nhiều cột nhất có thể** sao cho các chuỗi vẫn được sắp xếp.

// Cuối cùng:

// ```
// số cột cần xóa = tổng số cột − số cột giữ được nhiều nhất
// ```

// ➡️ Bài toán trở thành:

// > **Tìm dãy cột dài nhất (tăng dần) thỏa điều kiện sắp xếp**
// > → giống bài **Longest Increasing Subsequence (LIS)**

// ---

// ## 📌 Ký hiệu trong code

// ```java
// n = strs[0].length(); // số cột
// m = strs.length;     // số chuỗi (số hàng)
// ```

// * Cột được đánh số từ `0 → n-1`
// * Mỗi cột là một “điểm” trong LIS

// ---

// ## 📦 Mảng DP

// ```java
// int[] dp = new int[n];
// Arrays.fill(dp, 1);
// ```

// ### 👉 Ý nghĩa:

// `dp[i]` = **số cột tối đa có thể giữ lại**, **kết thúc tại cột i**

// * Mỗi cột riêng lẻ luôn hợp lệ → khởi tạo = 1

// ---

// ## 🔁 Vòng lặp DP (LIS)

// ```java
// for (int i = 1; i < n; i++) {
//     for (int j = 0; j < i; j++) {
//         if (isValid(strs, j, i)) {
//             dp[i] = Math.max(dp[i], dp[j] + 1);
//         }
//     }
// }
// ```

// ### 🧠 Ý tưởng:

// * Ta xét cột `i`
// * Thử **nối cột i sau cột j (j < i)**
// * Nếu nối được → cập nhật LIS

// ---

// ## ✅ Hàm `isValid(strs, j, i)` kiểm tra gì?

// ```java
// private boolean isValid(String[] strs, int j, int i) {
//     for (String s : strs) {
//         if (s.charAt(j) > s.charAt(i)) return false;
//     }
//     return true;
// }
// ```

// ### 📖 Ý nghĩa:

// Kiểm tra xem:

// > **Cột i có thể đứng sau cột j không?**

// Điều kiện:

// ```
// Với MỌI chuỗi s:
// s[j] ≤ s[i]
// ```

// 💡 Vì:

// * Khi ghép các cột lại
// * Thứ tự ký tự trong **từng chuỗi** phải không giảm
// * Nếu có chuỗi nào `s[j] > s[i]` → thứ tự từ điển bị phá vỡ ❌

// ---

// ## 🧩 Ví dụ minh họa nhanh

// ```text
// strs = ["babca",
//         "bbazb"]
// ```

// So sánh cột `j=1` và `i=3`:

// | Chuỗi | s[1] | s[3] |
// | ----- | ---- | ---- |
// | babca | a    | c    |
// | bbazb | b    | z    |

// → tất cả `s[1] ≤ s[3]`
// → `isValid = true`

// ---

// ## 🏁 Tìm LIS lớn nhất

// ```java
// int max = 0;
// for (int val : dp) max = Math.max(max, val);
// ```

// * `max` = số cột **giữ lại được nhiều nhất**

// ---

// ## ❌ Tính số cột cần xóa

// ```java
// return n - max;
// ```

// * Tổng cột: `n`
// * Giữ được: `max`
// * Phải xóa: `n - max`

// ---

// ## ⏱️ Độ phức tạp

// * `dp`: O(n²)
// * `isValid`: O(m)
// * 👉 **Tổng: O(n² × m)**

// Phù hợp với ràng buộc bài LC 960.

// ---

// ## 🧠 Tóm tắt 1 dòng

// > Thuật toán dùng **Dynamic Programming + LIS trên cột**,
// > trong đó mỗi cột là một phần tử,
// > hai cột nối được nếu **tất cả các chuỗi đều không giảm** giữa hai cột đó.

// ---

// Nếu bạn muốn:

// * ✨ Vẽ hình minh họa DP
// * ✨ Giải thích lại bằng **tư duy thi ICPC**
// * ✨ So sánh với bài **Delete Columns I / II**

// 👉 nói mình biết nhé 👍

// ---

// ## 📌 Đề bài nói gì?

// Bạn được cho **một mảng các chuỗi** `strs`, trong đó:

// * Tất cả các chuỗi **có cùng độ dài**
// * Mỗi chuỗi được coi như **một hàng**
// * Các ký tự cùng vị trí tạo thành **một cột**

// 👉 Bạn **được phép xóa một số cột** (xóa ở **tất cả các chuỗi** cùng lúc).

// ### 🎯 Mục tiêu:

// Sau khi xóa cột, mảng `strs` phải thỏa mãn:

// > Các chuỗi **theo thứ tự từ trên xuống dưới** phải **không giảm theo thứ tự từ điển (lexicographically sorted)**

// Và bạn cần:

// > ❗ **Xóa ít cột nhất có thể**

// ---

// ## 📖 Hiểu bằng ví dụ

// ### Ví dụ 1:

// ```
// strs = ["babca",
//         "bbazb"]
// ```

// Viết theo dạng bảng:

// | Cột   | 0 | 1 | 2 | 3 | 4 |
// | ----- | - | - | - | - | - |
// | babca | b | a | b | c | a |
// | bbazb | b | b | a | z | b |

// So sánh 2 chuỗi:

// * Tại cột 0: `b = b` → tiếp
// * Cột 1: `a < b` → OK (chuỗi trên nhỏ hơn)

// 👉 **Không cần xóa cột nào**

// ✅ Kết quả: `0`

// ---

// ### Ví dụ 2:

// ```
// strs = ["edcba"]
// ```

// Chỉ có **1 chuỗi**
// → Luôn đã được sắp xếp

// ✅ Kết quả: `0`

// ---

// ### Ví dụ 3 (quan trọng):

// ```
// strs = ["ghi",
//         "def",
//         "abc"]
// ```

// Bảng:

// | Cột | 0 | 1 | 2 |
// | --- | - | - | - |
// | ghi | g | h | i |
// | def | d | e | f |
// | abc | a | b | c |

// So sánh từ trên xuống:

// ```
// "ghi" > "def" > "abc" ❌
// ```

// 👉 Muốn đúng thứ tự từ điển, ta **phải xóa cột**

// Nếu xóa **cột 0**:

// ```
// hi
// ef
// bc
// ```

// Vẫn sai.

// Nếu xóa **cột 0 và 1**:

// ```
// i
// f
// c
// ```

// Vẫn sai.

// Nếu xóa **cột 0,1,2**:

// ```
// ""
// ""
// ""
// ```

// ✔️ Hợp lệ

// ➡️ Phải xóa **3 cột**

// ✅ Kết quả: `3`

// ---

// ## 🧠 Bản chất bài toán

// * Mỗi **cột = 1 ký tự trong mỗi chuỗi**
// * Bạn cần chọn **một dãy cột được giữ lại**
// * Dãy cột đó phải đảm bảo:

//   * Khi ghép lại → các chuỗi **tăng dần từ trên xuống**
// * Mục tiêu:

//   ```
//   số cột bị xóa = tổng cột - số cột giữ lại (tối đa)
//   ```

// 👉 Đây là bài toán:

// > **Tìm dãy cột dài nhất sao cho các chuỗi vẫn được sắp xếp**

// 💡 Thực chất là **Longest Increasing Subsequence (LIS)** trên các cột, nhưng với điều kiện so sánh nhiều chuỗi cùng lúc.

// ---

// ## 📝 Tóm tắt ngắn gọn

// * Bạn có nhiều chuỗi cùng độ dài
// * Được xóa cột (xóa đồng thời ở mọi chuỗi)
// * Sau khi xóa, các chuỗi phải **được sắp xếp từ trên xuống**
// * Hỏi: **Xóa ít cột nhất là bao nhiêu**

// ---

// Nếu bạn muốn:

// * 🔹 Giải thích **ý tưởng DP**
// * 🔹 Code Java / Python
// * 🔹 So sánh bài này với **Delete Columns to Make Sorted I & II**

// 👉 cứ nói, mình giải tiếp từng phần cho bạn 👍
