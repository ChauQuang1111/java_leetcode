// 165. Compare Version Numbers(23/09/2025)

import java.util.*;

public class b45 {

    // Hàm main để test bằng Scanner
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String v1 = sc.nextLine(); // nhập version1
        String v2 = sc.nextLine(); // nhập version2

        int result = compareVersion(v1, v2);

        // In ra đúng format LeetCode (-1, 0, 1)
        System.out.println(result);

        sc.close();
    }

    public static int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int n = version1.length(), m = version2.length();

        // Duyệt song song cả 2 chuỗi version
        while (i < n || j < m) {
            long num1 = 0, num2 = 0; // long để tránh tràn số nếu version rất lớn

            // Đọc 1 segment từ version1 cho đến khi gặp '.' hoặc hết chuỗi
            while (i < n && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }

            // Đọc 1 segment từ version2 cho đến khi gặp '.' hoặc hết chuỗi
            while (j < m && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }

            // So sánh 2 segment
            if (num1 > num2)
                return 1;
            if (num1 < num2)
                return -1;

            // Bỏ qua dấu '.'
            if (i < n && version1.charAt(i) == '.')
                i++;
            if (j < m && version2.charAt(j) == '.')
                j++;
        }

        // Nếu đi hết mà không khác biệt -> bằng nhau
        return 0;
    }
}

// Rồi 👍 mình sẽ giải thích chi tiết thuật toán trong code bạn đưa ra:

// ---

// ### 📌 Ý tưởng chính

// Thay vì tách `version1` và `version2` thành mảng (như cách dùng `split`), ta
// **duyệt trực tiếp từng ký tự** trong chuỗi và **xây dựng số** của mỗi phần
// (segment) giữa các dấu `.`.

// Ví dụ:

// * `version1 = "1.01"`,
// * `version2 = "1.001"`.

// Thuật toán sẽ lần lượt lấy:

// * `num1 = 1`, `num2 = 1` → bằng nhau → bỏ qua.
// * `num1 = 1`, `num2 = 1` (phần sau) → bằng nhau → bỏ qua.
// * Hết chuỗi → trả về `0`.

// ---

// ### 📌 Giải thích từng bước

// 1. **Khởi tạo con trỏ**

// ```java
// int i = 0, j = 0;
// int n = version1.length(), m = version2.length();
// ```

// * `i` duyệt chuỗi `version1`, `j` duyệt chuỗi `version2`.
// * `n, m` là độ dài hai chuỗi.

// ---

// 2. **Duyệt song song**

// ```java
// while (i < n || j < m) {
// long num1 = 0, num2 = 0;
// ```

// * Dùng vòng `while` vì độ dài hai chuỗi có thể khác nhau.
// * Mỗi lần lặp lấy ra **một phần số** (tách bằng dấu chấm).

// ---

// 3. **Đọc số từ version1**

// ```java
// while (i < n && version1.charAt(i) != '.') {
// num1 = num1 * 10 + (version1.charAt(i) - '0');
// i++;
// }
// ```

// * Duyệt liên tục cho đến khi gặp dấu `.` hoặc hết chuỗi.
// * `num1 = num1 * 10 + (digit)` để ghép số từ các ký tự.
// * Ví dụ `"123"` → `num1 = 123`.

// Tương tự với `version2`:

// ```java
// while (j < m && version2.charAt(j) != '.') {
// num2 = num2 * 10 + (version2.charAt(j) - '0');
// j++;
// }
// ```

// ---

// 4. **So sánh hai số vừa đọc**

// ```java
// if (num1 > num2) return 1;
// if (num1 < num2) return -1;
// ```

// * Nếu khác nhau → trả kết quả ngay.
// * Nếu bằng nhau → tiếp tục vòng lặp.

// ---

// 5. **Bỏ qua dấu chấm**

// ```java
// if (i < n && version1.charAt(i) == '.') i++;
// if (j < m && version2.charAt(j) == '.') j++;
// ```

// * Sau khi xử lý xong một segment, nhảy qua dấu `.` để bắt đầu phần tiếp theo.

// ---

// 6. **Kết quả cuối cùng**

// ```java
// return 0;
// ```

// * Nếu duyệt hết cả hai chuỗi mà không tìm thấy khác biệt → hai version bằng
// nhau.

// ---

// ### 📌 Ưu điểm của cách này

// * Không cần dùng `split`, tiết kiệm bộ nhớ.
// * Xử lý trực tiếp từng ký tự → nhanh hơn.
// * Dùng `long` để tránh tràn số nếu version quá lớn.

// ---

// 👉 Tóm lại: Thuật toán sẽ duyệt từng segment của 2 phiên bản, so sánh số
// nguyên của từng segment. Nếu có sự khác biệt → trả kết quả ngay. Nếu không →
// tiếp tục đến hết chuỗi. Nếu không phát hiện khác biệt nào → hai phiên bản
// bằng nhau.

// ---

// Bạn có muốn mình viết thêm **hàm main dùng Scanner** để nhập vào 2 version và
// so sánh kết quả không?

// mình giải
// thích đề**LeetCode 165.
// Compare Version Numbers**nhé🚀

// ---

// ###📌
// Đề bài

// Bạn được cho**
// hai chuỗi
// phiên bản**`version1`và`version2`.

// *
// Mỗi chuỗi
// phiên bản
// được chia
// thành nhiều

// phần (revision), cách nhau bởi dấu `"."`.
// * Mỗi phần là một số nguyên **không âm** (có thể có số 0 ở đầu).

// 👉 Nhiệm vụ:
// So sánh `version1` và `version2`.

// * Nếu `version1 > version2` → trả về `1`.
// * Nếu `version1 < version2` → trả về `-1`.
// * Nếu bằng nhau → trả về `0`.

// ---

// ### 📌 Ví dụ

// 1.

// ```
// version1 = "1.01", version2 = "1.001"
// → Output: 0
// ```

// Vì `01 == 1` và `001 == 1`.

// ---

// 2.

// ```
// version1 = "1.0", version2 = "1.0.0"
// → Output: 0
// ```

// Vì `"1.0"` thực ra là `[1, 0]` còn `"1.0.0"` là `[1, 0, 0]` → hai mảng bằng
// nhau.

// ---

// 3.

// ```
// version1 = "0.1", version2 = "1.1"
// → Output: -1
// ```

// Vì so sánh từ trái sang phải:

// * `0 < 1` → version1 nhỏ hơn.

// ---

// 4.

// ```
// version1 = "1.2", version2 = "1.10"
// → Output: -1
// ```

// Vì `2 < 10`.

// ---

// ### 📌 Ý tưởng giải

// 1. Tách 2 chuỗi `version1` và `version2` thành các phần bằng `"."`.
// → ta được 2 mảng số nguyên.

// 2. So sánh từng cặp phần tử từ trái sang phải:

// * Nếu một bên lớn hơn → return ngay `1` hoặc `-1`.
// * Nếu bằng → tiếp tục.

// 3. Nếu một chuỗi ngắn hơn, coi các phần thiếu là `0`.

// 4. Nếu hết vòng lặp mà không tìm được sự khác biệt → return `0`.

// ---

// 👉 Bản chất là **so sánh 2 mảng số nguyên theo thứ tự từ trái sang phải**,
// trong đó các phần thiếu coi như `0`.

// ---

// Bạn có muốn mình viết luôn code **Python** và **Java** cho bài này không?
