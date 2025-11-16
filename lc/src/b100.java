// # 1513. Number of Substrings With Only 1s(16/11/2025)
// # Dưới đây là **giải thích đề bài LeetCode 1513 — Number of Substrings With Only 1s** thật dễ hiểu và ngắn gọn.
// # Dưới đây là **giải thích thuật toán + phiên bản code có chú thích đầy đủ**.
// Dưới đây là**phiên bản đầy đủ có`main`dùng`Scanner`+thêm chú thích chi tiết vào từng phần code**.Mình giữ nguyên logic bạn đang dùng(đếm streak‘1’liên tiếp).

// ---

// ##✅**Code đã thêm`main`+chú thích chi tiết**

// ```java

import java.util.Scanner;

class Solution {

    // Khối static để chạy thử, bạn có thể giữ hoặc xóa
    static {
        for (int i = 0; i < 500; i++) {
            numSub("111111");
        }
    }

    // Hàm chính sử dụng Scanner
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập chuỗi nhị phân
        String s = sc.next();

        // Gọi hàm xử lý và in kết quả
        System.out.println(numSub(s));

        sc.close();
    }

    // Hàm tính số substring chỉ chứa '1'
    public static int numSub(String s) {
        final int MODULO = 1000000007;

        int onesStreak = 0; // đếm số lượng '1' liên tiếp hiện tại
        int res = 0; // kết quả cuối cùng

        for (char ch : s.toCharArray()) {

            if (ch == '1') {
                // nếu gặp '1', tăng streak lên 1
                // đồng thời cộng vào kết quả
                // vì: nếu streak = k → có thêm k substring kết thúc tại vị trí này
                res = (res + (++onesStreak)) % MODULO;
            } else {
                // nếu gặp '0', reset streak
                onesStreak = 0;
            }
        }

        return res;
    }
}

// ---

// ##✅**

// Giải thích
// thuật toán**

// **Ý tưởng:**
// Các substring
// chỉ chứa`1`
// luôn nằm
// trong các
// đoạn liên
// tiếp như:

// *`"111"`→
// các substring
// hợp lệ:`"1","1","1","11","11","111"`→tổng=**6**

// Nếu một
// đoạn có`n`
// ký tự`'1'`
// liên tiếp→
// số substring
// chỉ gồm`'1'`là:

// [\frac{n(n+1)}{2}]

// ---

// ###✔
// Nhưng ta
// không cần
// tính công
// thức mỗi
// lần

// Ta
// dùng cách**
// quét một
// lần từ
// trái sang phải**:

// *Gặp`'1'`
// thì tăng`onesStreak`*
// Mỗi lần
// tăng streak→
// thêm đúng`onesStreak`
// substring mới

// **Ví dụ:**Chuỗi:`"111"`

// *i=0:`'1'`→streak=1→+1*i=1:`'1'`→streak=2→+2*i=2:`'1'`→streak=3→+3

// Tổng=**6**

// Nếu gặp`'0'`
// thì reset
// streak về 0.

// Thuật toán
// này chỉ**O(n)** — nhanh nhất có thể.

// ---

// Nếu bạn muốn mình viết thêm bản tối ưu hơn, hoặc chuyển sang C++ / Python /
// giải thích bằng hình, mình làm luôn cho bạn!

// # ---

// # # ✅ **Giải thích thuật toán**

// # Ý tưởng:

// # 1. Tách chuỗi `s` theo ký tự `'0'`
// # → Mỗi phần trong `s.split('0')` là **đoạn gồm toàn ký tự '1'**.

// # Ví dụ:
// # `"110111"` → `["11", "", "111"]`

// # 2. Với mỗi đoạn `part` có độ dài `n`, số substring toàn `'1'` là:

// # [
// # \frac{n(n+1)}{2}
// # ]

// # 3. Tác giả viết:

// # ```
// # cnt += n*(n+1)
// # ```

// # sau đó cuối cùng `cnt // 2` để hoàn tất công thức.

// # 4. Trả về kết quả theo modulo (10^9 + 7).

// # ---

// # # ✅ **Code có chú thích rõ ràng**

// # ```python
// class Solution:
// def numSub(self, s: str) -> int:
// cnt = 0

// # Tách chuỗi theo ký tự '0'
// # Mỗi phần thu được là một đoạn toàn '1'
// for part in s.split('0'):
// n = len(part) # độ dài đoạn gồm toàn '1'

// # Số substring toàn '1' của một đoạn:
// # n * (n + 1) / 2
// # Ở đây tính n*(n+1) trước, chia 2 sau.
// cnt += n * (n + 1)

// # Chia 2 theo công thức và mod 1e9+7
// return (cnt // 2) % (10**9 + 7)
// # ```

// # ---

// # # 🔍 Ví dụ minh họa

// # `s = "0110111"`

// # `s.split('0')` → `["", "11", "111"]`

// # * `"11"` → n = 2 → 2*3/2 = 3
// # * `"111"` → n = 3 → 3*4/2 = 6
// # → Tổng = **9**

// # ---

// # ---

// # # ✅ **📌 ĐỀ BÀI: Number of Substrings With Only 1s**

// # Bạn được cho một chuỗi nhị phân **s** (chỉ gồm `'0'` và `'1'`).

// # **Yêu cầu:**
// # 👉 Đếm số **substring** (chuỗi con liên tiếp) mà **chỉ chứa toàn ký tự
// '1'**, không có bất kỳ '0' nào.

// # ---

// # # 🔍 Ví dụ

// # ### **Ví dụ 1**

// # ```
// # Input: s = "0110111"
// # Output: 9
// # ```

// # Giải thích:

// # Các đoạn gồm toàn `1` là:

// # | Đoạn | Số substring chỉ chứa '1' |
// # | ----- | ---------------------------------- |
// # | "11" | 3 -> ("1", "1", "11") |
// # | "111" | 6 -> ("1","1","1","11","11","111") |

// # Tổng cộng: **3 + 6 = 9**

// # ---

// # # 🧠 Ý TƯỞNG GIẢI

// # Chuỗi liên tiếp gồm `k` ký tự '1':

// # ```
// # "111...1" (k lần)
// # ```

// # sẽ tạo được:

// # 👉 **k*(k+1)/2** substrings toàn '1'.

// # Ví dụ "111" → 3*(3+1)/2 = 6.

// # ---

// # # ✨ CÁCH LÀM

// # 1. Duyệt chuỗi
// # 2. Mỗi khi gặp chuỗi liên tiếp gồm `k` ký tự '1', cộng vào kết quả:

// # ```
// # k*(k+1)/2
// # ```
// # 3. Reset đếm nếu gặp '0'.

// # ---

// # # ✔️ Code mẫu Python

// # ```python
// # class Solution:
// # def numSub(self, s: str) -> int:
// # res = 0
// # count = 0
// # mod = 10**9 + 7

// # for c in s:
// # if c == '1':
// # count += 1
// # else:
// # res += count * (count + 1) // 2
// # count = 0

// # res += count * (count + 1) // 2
// # return res % mod
// # ```

// # ---

// # # 📌 Tổng kết

// # * Tách chuỗi thành các đoạn liên tiếp `111…1`
// # * Với độ dài k → có `k*(k+1)/2` substring toàn ‘1’
// # * Lặp lại cho tất cả các đoạn

// # ---

// # Nếu bạn muốn, mình có thể:

// # ✅ minh họa bằng hình
// # ✅ viết code Java
// # ✅ giải thích thêm về độ phức tạp

// # Bạn muốn tiếp phần nào?
// Dưới đây là **giải thích thuật toán cực rõ ràng** cho code Java bạn đưa.

// ---

// # ✅ **Mục tiêu bài toán (LeetCode 1513)**

// Đếm số **substring liên tiếp toàn '1'** trong chuỗi `s`.
// Ví dụ: `"111"` → có 6 substring:
// `"1","1","1","11","11","111"`

// Cách đếm nhanh mà không cần liệt kê.

// ---

// # ✅ **Giải thích thuật toán trong code**

// ## 📌 **Biến dùng trong thuật toán:**

// * `onesStreak`: độ dài đoạn liên tiếp '1' tính tới hiện tại.
// * `res`: tổng số substring gồm toàn '1'.

// ---

// ## 🔥 **Logic chính:**

// Duyệt từng ký tự `ch` trong chuỗi:

// ### ✔️ Nếu `ch == '1'`

// Ta tăng độ dài chuỗi '1' liên tiếp:

// ```
// ++onesStreak
// ```

// Số substring mới tạo ra khi thêm 1 ký tự '1' là **bằng chính onesStreak**.

// Ví dụ đang có chuỗi `"111"`:

// * Thêm `'1'` mới → streak = 4
// * Substring mới: `"1","11","111","1111"` → 4 cái

// → Cộng vào kết quả:

// ```
// res = (res + onesStreak)
// ```

// ### ✔️ Nếu `ch == '0'`

// Đoạn '1' kết thúc → reset:

// ```
// onesStreak = 0
// ```

// ---

// # ✨ **Vì sao công thức này đúng?**

// Giả sử gặp đoạn `'1111'`:

// * i=1 → streak=1 → +1
// * i=2 → streak=2 → +2
// * i=3 → streak=3 → +3
// * i=4 → streak=4 → +4

// Tổng =
// [
// 1 + 2 + 3 + 4 = \frac{4\cdot5}{2} = 10
// ]

// Giống công thức toán học đếm substring.

// ---

// # ⚠️ Static block để làm gì?

// ```java
// static {
// for (int i = 0; i < 500; i++) {
// numSub("111111");
// }
// }
// ```

// Static block chạy khi class được load.
// Ở đây có thể dùng để **warm-up JVM** (tăng hiệu suất), làm bài benchmark,
// hoặc kiểm tra hàm chạy mà không cần gọi từ main.

// Không ảnh hưởng thuật toán chính.

// ---

// # ✅ **Tóm tắt thuật toán**

// 1. Duyệt từng ký tự.
// 2. Đếm độ dài chuỗi '1' liên tiếp.
// 3. Mỗi lần gặp '1', cộng số substring mới = độ dài hiện tại.
// 4. Mỗi lần gặp '0', reset.
// 5. Lấy kết quả mod (10^9+7).

// ---

// # 🧠 **Độ phức tạp**

// * Thời gian: **O(n)**
// * Bộ nhớ: **O(1)**

// Thuật toán tối ưu nhất.

// ---

// Bạn có muốn mình **thêm hàm main**, hoặc **chuyển sang Python**, hoặc **minh
// họa bằng ví dụ cụ thể** không?
