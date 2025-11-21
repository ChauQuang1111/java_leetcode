
// 1930. Unique Length-3 Palindromic Subsequences(21/11/2025)
// Dưới đây là **giải thích đề bài LeetCode 1930 – Unique Length-3 Palindromic Subsequences** thật dễ hiểu:
import java.util.*;

public class b105 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.nextLine();

        int result = countPalindromicSubsequence(s);

        System.out.println(result);
    }

    public static int countPalindromicSubsequence(String s) {
        int n = s.length();

        // charFirst[c]: vị trí xuất hiện đầu tiên của ký tự c
        int[] charFirst = new int[26];

        // posBit[i]: bitmask các ký tự xuất hiện trong đoạn hiện tại đến vị trí i
        int[] posBit = new int[n];

        // Khởi tạo tất cả = -1 (chưa xuất hiện)
        Arrays.fill(charFirst, -1);

        int bits = 0; // bitmask tạm thời cho từng đoạn

        // Bước 1: Duyệt trái → phải, tạo posBit theo từng đoạn
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            // Thêm ký tự vào bitmask hiện tại
            bits |= (1 << idx);
            posBit[i] = bits;

            // Nếu ký tự này lần đầu xuất hiện → đánh dấu đoạn mới
            if (charFirst[idx] == -1) {
                charFirst[idx] = i;
                bits = 0; // reset bitmask bắt đầu đoạn mới
            }
        }

        int res = 0;

        // Bước 2: Duyệt phải → trái tìm lần cuối của mỗi ký tự
        for (int i = n - 1; i >= 2; i--) {
            int idx = s.charAt(i) - 'a';

            // Nếu ký tự chưa có lần đầu hoặc chỉ xuất hiện 1 lần → bỏ
            if (charFirst[idx] <= -1 || charFirst[idx] == i) {
                continue;
            }

            // Lấy bitmask các ký tự nằm giữa [first+1 .. last-1]
            int mask = arrOr(charFirst[idx] + 1, i - 1, posBit);

            // Đếm số ký tự khác nhau
            res += Integer.bitCount(mask);

            // Đánh dấu ký tự này đã xử lý để tránh tính lại
            charFirst[idx] = -2;
        }

        return res;
    }

    // Hàm OR toàn bộ bitmask trong đoạn [start .. end]
    public static int arrOr(int start, int end, int[] arr) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res |= arr[i];
        }
        return res;
    }
}

// ---

// # ⭐ **Giải thích đề bài**

// Bạn được cho một chuỗi **s** chỉ gồm các chữ cái thường (`a`–`z`).

// Bạn cần **đếm số lượng xâu con (subsequence) dạng palindrome độ dài 3** mà
// **là duy nhất** (không trùng lặp).

// ---

// # 🎯 **Subsequence độ dài 3 dạng Palindrome**

// Subsequence độ dài 3 có dạng:

// ```
// a _ a
// ```

// Tức là ký tự đầu và cuối **giống nhau**, còn ký tự giữa thì có thể khác.

// Ví dụ như:

// * `"aba"` → hợp lệ
// * `"axa"` → hợp lệ
// * `"aaa"` → hợp lệ
// * `"abc"` → không hợp lệ (vì `a ≠ c`)

// **Subsequence** → không cần liên tiếp, chỉ cần giữ thứ tự.

// ---

// # 🎯 Bạn phải đếm số subsequence độ dài 3 **không trùng lặp**

// Ví dụ:

// ```
// s = "aabca"
// ```

// Các subsequence palindrome độ 3:

// ```
// aba (a ở pos 0, b ở pos 2, a ở pos 4)
// aaa (a 0, a 1, a 4)
// aca (a 0, c 3, a 4)
// ```

// → Kết quả = **3**

// ---

// # 📌 Lưu ý quan trọng

// * Hai subsequence giống nhau về **nội dung** được tính **1 lần**, dù chọn từ
// vị trí khác nhau.
// * Duyệt tất cả subsequence độ dài 3 sẽ rất chậm: `O(n³)` → không được.

// ---

// # 🎯 Bạn cần output:

// 👉 **Số lượng subsequence palindrome độ dài 3 khác nhau** trong chuỗi.

// ---

// # ✔ Ví dụ 1:

// ```
// Input: s = "bbcbaba"

// Các subsequence palindrome độ 3:
// "bbb", "bcb", "bab", "aaa", "aba"

// Output: 4
// ```

// ---

// # ✔ Ví dụ 2:

// ```
// Input: s = "adc"

// Không có subsequence độ dài 3 dạng palindrome

// Output: 0
// ```

// ---

// Nếu bạn muốn, mình sẽ giải thích thuật toán tối ưu (O(26 * n)), hoặc giải
// thích code Python/Java cụ thể.
// Dưới đây là **giải thích chi tiết và dễ hiểu** cho thuật toán trong lời giải
// của bạn cho bài **1930. Unique Length-3 Palindromic Subsequences**.

// ---

// # 🎯 **Mục tiêu bài toán**

// Cho chuỗi `s`, cần đếm số lượng **xâu con dạng palindrome độ dài 3** khác
// nhau có dạng:

// ```
// a _ a
// ```

// Tức là ký tự đầu và cuối giống nhau, ở giữa là ký tự bất kỳ.

// Ví dụ:
// Trong `"aabca"`, các palindrome độ dài 3 khác nhau là:

// ```
// "aba" (a _ a)
// "aca"
// "aaa"
// ```

// → Kết quả = 3.

// ---

// # 🧠 **Ý tưởng của thuật toán này**

// 1. Với **mỗi ký tự 'a'→'z'**, ta tìm:

// * **vị trí xuất hiện đầu tiên**
// * **vị trí xuất hiện cuối cùng**

// 2. Với mỗi ký tự sẽ được làm ký tự đầu & cuối của palindrome, ta đếm:

// * có bao nhiêu ký tự khác nhau nằm trong đoạn giữa
// → số palindrome = số ký tự khác nhau trong đoạn này.

// Thuật toán của bạn dùng **bitmask** để tối ưu việc đếm ký tự khác nhau.

// ---

// # 📝 **Giải thích từng đoạn code**

// ## 1️⃣ Khởi tạo

// ```java
// int n = s.length();
// int[] charFirst = new int[26];
// int[] posBit = new int[n];
// Arrays.fill(charFirst, -1);
// ```

// * `charFirst[c]` = vị trí đầu tiên xuất hiện của ký tự `c`.
// * `posBit[i]` = bitmask chứa tập các ký tự xuất hiện từ đầu đoạn cho đến `i`.
// * `charFirst` ban đầu set -1 (chưa xuất hiện).

// ---

// ## 2️⃣ Duyệt từ trái sang phải và tạo **bitmask prefix** theo từng đoạn

// ```java
// int bits = 0;
// for (int i = 0; i < n; i++) {
// int idx = s.charAt(i) - 'a';
// posBit[i] = bits |= 1 << idx;
// if (charFirst[idx] == -1) {
// charFirst[idx] = i;
// bits = 0;
// }
// }
// ```

// ### 🔍 Đây là phần quan trọng nhất:

// * `bits |= 1 << idx`
// → thêm ký tự hiện tại vào bitmask.

// * `posBit[i]` = bitmask các ký tự từ **lần xuất hiện đầu tiên của một chữ
// cái** đến vị trí hiện tại.

// * **Khi gặp lần đầu của ký tự nào**, ta reset `bits = 0` để chuẩn bị cho một
// đoạn mới.

// 👉 Tức là `posBit` được chia thành **26 đoạn**, mỗi đoạn bắt đầu từ lần xuất
// hiện đầu tiên của 1 ký tự.

// Mục đích: giúp lấy nhanh bitmask trong khoảng (first+1, last-1).

// ---

// ## 3️⃣ Duyệt từ phải sang trái để tìm lần xuất hiện cuối

// ```java
// for (int i = n - 1; i >= 2; i--) {
// int idx = s.charAt(i) - 'a';
// if (charFirst[idx] <= -1 || charFirst[idx] == i) {
// continue;
// }
// ```

// * Nếu ký tự đó chưa xuất hiện trước đó → bỏ qua.
// * Nếu vị trí đầu tiên == vị trí cuối cùng → không có đoạn ở giữa → bỏ qua.

// ### Nếu hợp lệ:

// Ta đếm số ký tự khác nhau giữa hai vị trí đó:

// ```java
// res += Integer.bitCount(arrOr(charFirst[idx] + 1, i - 1, posBit));
// charFirst[idx] = -2;
// ```

// * `arrOr` trả về bitmask OR của toàn đoạn → tập các ký tự trong đó.
// * `Integer.bitCount(mask)` → đếm số ký tự khác nhau.

// ### `charFirst[idx] = -2`

// → đánh dấu đã xử lý ký tự này để tránh xử lý lại.

// ---

// ## 4️⃣ Hàm lấy bitmask của đoạn

// ```java
// private int arrOr(int start, int end, int[] arr) {
// int res = 0;
// for (int i=start; i<=end; i++) {
// res |= arr[i];
// }
// return res;
// }
// ```

// * Gộp bitmask trong đoạn `[start .. end]`.
// * Trả về bitmask của toàn bộ ký tự xuất hiện trong đoạn.

// ---

// # ✔️ **Kết quả**

// Cách làm này đếm số ký tự khác nhau nằm giữa lần xuất hiện đầu & cuối của một
// ký tự.

// → Mỗi chữ cái (26 chữ) tính **một lần**.
// → Complexity: **O(26 × N) = O(N)**.

// ---

// # 🎉 Tóm tắt thuật toán

// 1. Tìm vị trí đầu tiên của mỗi ký tự.
// 2. Phân đoạn chuỗi bằng vị trí đầu tiên của ký tự để build bitmask prefix.
// 3. Duyệt từ phải sang để tìm vị trí cuối.
// 4. Với mỗi ký tự:

// * lấy đoạn nằm giữa (first, last)
// * đếm số ký tự khác nhau trong đó
// * cộng vào kết quả.
// 5. Trả kết quả.

// ---

// Nếu bạn muốn, mình có thể:

// ✅ vẽ hình minh họa từng bước
// ✅ viết phiên bản code tối giản hơn
// ✅ so sánh với lời giải chuẩn LeetCode

// Bạn muốn phần nào tiếp?
