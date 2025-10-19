
// # 1625. Lexicographically Smallest String After Applying Operations(19/10/2025)
// # Rất hay — đây là một **lời giải cực tối ưu** cho bài **LeetCode 1625. Lexicographically Smallest String After Applying Operations**, được viết theo phong cách “toán học hoá” thay vì duyệt BFS như cách thông thường.
// # Dưới đây là bản **giải thích chi tiết thuật toán + chú thích từng dòng code** 👇
import java.util.*;

public class b72 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String S = sc.next();

        int a = sc.nextInt();

        int b = sc.nextInt();

        String result = findLexSmallestString(S, a, b);

        // In kết quả
        System.out.println(result);
    }

    // Hàm chính để tìm chuỗi nhỏ nhất về mặt từ điển
    public static String findLexSmallestString(String S, int a, int b) {
        char[] s = S.toCharArray(); // Chuyển chuỗi thành mảng ký tự
        int n = s.length;
        char[] t = new char[n];
        int step = gcd(b, n); // Số bước cần quay (liên quan đến chu kỳ xoay)
        int g = gcd(a, 10); // Liên quan đến phép cộng số (theo modulo 10)
        String ans = null; // Biến lưu kết quả tốt nhất (chuỗi nhỏ nhất)

        // Duyệt tất cả các cách quay chuỗi theo từng step
        for (int i = 0; i < n; i += step) {
            // Xoay chuỗi: t = s[i,n) + s[0,i)
            System.arraycopy(s, i, t, 0, n - i);
            System.arraycopy(s, 0, t, n - i, i);

            modify(t, 1, g); // Cộng giá trị a cho các vị trí lẻ (odd indices)
            if (step % 2 > 0) { // Nếu có thể tác động lên cả vị trí chẵn
                modify(t, 0, g); // Cộng giá trị a cho các vị trí chẵn (even indices)
            }

            // Chuyển mảng ký tự thành chuỗi để so sánh
            String str = new String(t);
            if (ans == null || str.compareTo(ans) < 0) {
                ans = str; // Giữ lại chuỗi nhỏ nhất (lexicographically smallest)
            }
        }

        return ans;
    }

    // Hàm cộng giá trị (theo modulo 10) cho các vị trí chẵn hoặc lẻ trong mảng
    public static void modify(char[] t, int start, int g) {
        int ch = t[start] - '0'; // Ký tự đầu tiên của nhóm này
        // Tính toán lượng cần cộng (inc) để đạt giá trị nhỏ nhất có thể
        int inc = ch % g - ch + 10; // +10 để tránh số âm
        for (int j = start; j < t.length; j += 2) {
            t[j] = (char) ('0' + (t[j] - '0' + inc) % 10);
        }
    }

    // Hàm tính ước chung lớn nhất (GCD)
    public static int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}

// # ---

// # ## 🔍 Ý tưởng tổng quát

// # Bài toán cho phép ta:

// # 1. **Cộng `a` vào tất cả chữ số ở vị trí lẻ** (vị trí 1,3,5,… — tính từ 0).
// # 2. **Xoay phải `b` ký tự**.

// # Ta có thể áp dụng hai phép này **bao nhiêu lần cũng được** — nên thay vì
// thử hết (rất tốn thời gian), ta sẽ **tận dụng tính chu kỳ** của phép quay và
// phép cộng để chỉ xét các trường hợp *đại diện*.

// # ---

// # ## ✅ Phân tích từng phần

// # ### 1️⃣ Ý tưởng chính

// # * Nếu ta xoay `b` ký tự liên tục, sau `n / gcd(n, b)` lần, chuỗi trở về vị
// trí ban đầu.
// # → Chỉ cần xét các **độ xoay cách nhau `gcd(b, n)`**.

// # * Việc cộng `a` vào vị trí lẻ có chu kỳ mod 10.
// # → Cộng `a` nhiều lần sẽ chỉ tạo ra **`10 / gcd(a, 10)` giá trị khác nhau**
// cho cùng một vị trí.

// # Nhờ đó, ta **giảm số trạng thái cần xét xuống mức cực nhỏ**.

// # ---

// # ## 💡 Code có chú thích chi tiết

// # ```python
// from math import gcd

// class Solution:
// def findLexSmallestString(self, s: str, a: int, b: int) -> str:
// s = list(map(int, s)) # Chuyển chuỗi sang list số nguyên
// n = len(s)
// step = gcd(b, n) # Mỗi lần xoay b ký tự — chỉ có n / gcd(n,b) dạng khác nhau
// g = gcd(a, 10) # Cộng a vào 1 chữ số chỉ tạo 10 / gcd(a,10) giá trị khác nhau
// ans = [10] # Lưu chuỗi nhỏ nhất (khởi tạo với giá trị lớn hơn 9)

// # 🧩 Hàm phụ: điều chỉnh các vị trí chẵn/lẻ sao cho càng nhỏ càng tốt
// def modify(start: int) -> None:
// ch = t[start] # Lấy chữ số đầu tiên ở vị trí start (0 hoặc 1)

// # Chữ số này có thể giảm xuống tới ch % g (chu kỳ modulo 10)
// # Ví dụ: a=2, g=2, ch=5 => có thể giảm về 1 (5→7→9→1)
// inc = ch % g - ch # Độ thay đổi cần thêm (âm để giảm giá trị)

// # Nếu có thể giảm, cập nhật toàn bộ vị trí cùng loại (chẵn/lẻ)
// if inc:
// for j in range(start, n, 2): # nhảy 2 bước (vị trí cùng parity)
// t[j] = (t[j] + inc) % 10

// # 🌀 Xét tất cả các cách xoay khác nhau (chỉ cần duyệt step lần)
// for i in range(0, n, step):
// t = s[i:] + s[:i] # Tạo chuỗi sau khi xoay i ký tự sang phải

// modify(1) # Cộng vào vị trí lẻ sao cho nhỏ nhất
// if step % 2: # Nếu xoay khiến vị trí lẻ → chẵn được (tức n/b là lẻ)
// modify(0) # Khi đó ta cũng có thể giảm các vị trí chẵn

// ans = min(ans, t) # Cập nhật kết quả nhỏ nhất (so sánh từ điển)

// return ''.join(map(str, ans))

// # s = "5525"
// # a = 9
// # b = 2
// # ```

// # ### Bước 1:

// # `n = 4`, `step = gcd(2,4) = 2`, `g = gcd(9,10) = 1`.

// # → Ta chỉ cần xét 2 kiểu xoay: xoay 0, xoay 2.

// # ### Bước 2:

// # * Với mỗi chuỗi xoay, thử cộng `a` để làm nhỏ nhất các vị trí lẻ.
// # * Vì `g=1`, ta có thể đưa mọi chữ số lẻ về **0**.

// # ### Bước 3:

// # So sánh tất cả các chuỗi sau khi “giảm” — kết quả nhỏ nhất là `"2050"`.

// # ---

// # ## 🧠 Tóm tắt ý tưởng

// # | Bước | Mục tiêu | Cách làm |
// # | ---- | --------------------------------- |
// ------------------------------------------ |
// # | 1 | Giảm số lần xoay cần xét | Dựa trên `gcd(b, n)` |
// # | 2 | Giảm số lần cộng cần xét | Dựa trên `gcd(a, 10)` |
// # | 3 | Giảm từng vị trí sao cho nhỏ nhất | Dựa theo parity (chẵn/lẻ) |
// # | 4 | So sánh tất cả chuỗi | Giữ lại chuỗi nhỏ nhất theo thứ tự từ điển |

// # ---

// # Bạn có muốn mình **viết lại lời giải này sang Java** (có chú thích chi tiết
// tương đương) không?

// # ---

// ## 🧩 **Đề bài tóm tắt**

// # Cho một **chuỗi số** `s` (chỉ gồm các ký tự `'0'–'9'`) và hai **số nguyên**
// `a`, `b`.
// # Ta có thể thực hiện **hai loại thao tác tùy ý số lần** như sau:

// ### 🔹 Operation 1 — Add `a` to odd indices:

// # Cộng `a` vào **các ký tự ở vị trí lẻ (1, 3, 5, …)**.

// # * Nếu vượt quá `9`, thì tính theo modulo `10`.
// # (Ví dụ: `'8' + 5 → '3'` vì `(8 + 5) % 10 = 3`)

// # 👉 Ví dụ:
// # `s = "3456"`, `a = 7`
// # → cộng `a` vào vị trí 1 và 3
// # → `"3456"` → `"3153"`

// # ---

// ### 🔹 Operation 2 — Rotate right by `b` positions:

// # Dịch vòng chuỗi sang phải **`b` ký tự**.
// # (Ví dụ `b = 2`: `"123456"` → `"561234"`)

// # ---

// ## 🎯 **Mục tiêu:**

// # Sau khi thực hiện **bất kỳ số lần các phép trên (theo thứ tự tự do)**,
// # hãy tìm **chuỗi nhỏ nhất theo thứ tự từ điển (lexicographically smallest)**
// có thể đạt được.

// ## 📘 Ví dụ:

// # **Input:**

// # ```
// # s = "5525"
// # a = 9
// # b = 2
// # ```

// # **Các bước có thể xảy ra:**

// # ```
// # "5525" (ban đầu)
// # rotate(2) → "2555"
// # add(odd) → "2454"
// # rotate(2) → "5424"
// # add(odd) → "5313"
// # ...
// # ```

// # Sau nhiều bước, chuỗi nhỏ nhất có thể đạt được là `"2050"`.

// # **Output:** `"2050"`

// # ## 💡 **Trực giác thuật toán:**

// # * Vì mỗi phép có thể được lặp lại **vô hạn lần**,
// # ta nên nghĩ đến **duyệt toàn bộ các trạng thái có thể đạt được**.
// # * Mỗi trạng thái là một chuỗi khác nhau.
// # * Do đó, ta dùng **BFS hoặc DFS + visited set** để:

// # * Tránh lặp vô hạn.
// # * Khám phá tất cả các chuỗi có thể đạt được từ `s`.
// # * Cập nhật chuỗi nhỏ nhất gặp được.

// ## 🧠 **Tóm tắt ý tưởng giải:**

// # 1. Sử dụng `queue` để BFS từ chuỗi ban đầu `s`.
// # 2. Tại mỗi bước:

// # * Sinh chuỗi mới sau khi **thêm `a` vào các vị trí lẻ**.
// # * Sinh chuỗi mới sau khi **xoay phải `b` ký tự**.
// # 3. Nếu chuỗi mới **chưa xuất hiện trước đó**, thêm vào `queue`.
// # 4. Cập nhật **chuỗi nhỏ nhất theo thứ tự từ điển** khi gặp chuỗi mới nhỏ
// hơn.
// # 5. Khi BFS xong, kết quả là chuỗi nhỏ nhất.

// # ---

// # ## ✅ **Tóm lại:**

// # | Thành phần | Mô tả |
// # | ------------ | -------------------------------------- |
// # | **Input** | `s` (string gồm chữ số), `a`, `b` |
// # | **Phép 1** | Cộng `a` (mod 10) vào các vị trí lẻ |
// # | **Phép 2** | Xoay phải `b` ký tự |
// # | **Mục tiêu** | Tìm chuỗi nhỏ nhất theo thứ tự từ điển |
// # | **Kỹ thuật** | BFS (hoặc DFS) + Set tránh lặp |

// Rất hay — đây là **phiên bản tối ưu hóa (O(n))** của bài **1625.
// Lexicographically Smallest String After Applying Operations**.
// Thuật toán này không cần BFS mà khai thác **chu kỳ (cycle)** của phép quay và
// **chu kỳ cộng mod 10** để tính trực tiếp chuỗi nhỏ nhất.

// ---

// ## 🔍 Ý tưởng tổng quát

// Bài toán cho phép thực hiện hai phép biến đổi lặp lại nhiều lần:

// 1. **Add Operation** — cộng `a` vào tất cả **chữ số ở vị trí lẻ** (chỉ số
// 1,3,5,...), mỗi lần cộng mod 10.
// 2. **Rotate Operation** — xoay chuỗi sang phải `b` ký tự.

// Mục tiêu: tìm ra **chuỗi nhỏ nhất theo thứ tự từ điển** có thể đạt được sau
// bất kỳ số lần áp dụng hai phép biến đổi.

// Thay vì thử tất cả các khả

// năng (rất nhiều), thuật toán này chỉ xét những vị trí **xoay hợp lệ** (bội
// của `gcd(b, n)`) và những cách cộng có thể sinh ra **giá trị nhỏ nhất có chu
// kỳ**.

// ---

// ## 🧮 Giải thích từng phần

// ### 1️⃣ Biến khởi tạo

// ```java
// char[] s = S.toCharArray();
// int n = s.length;
// char[] t = new char[n];
// int step = gcd(b, n);
// int g = gcd(a, 10);
// String ans = null;
// ```

// * `s`: mảng ký tự của chuỗi đầu vào.
// * `n`: độ dài chuỗi.
// * `step = gcd(b, n)` → vì phép xoay `b` ký tự có chu kỳ lặp lại sau mỗi
// `gcd(b, n)` lần, nên ta chỉ cần xét các vị trí đó.
// * `g = gcd(a, 10)` → phép cộng mod 10 chỉ có 10 giá trị, nên việc cộng `a`
// lặp lại cũng tạo chu kỳ theo `g`.

// ---

// ### 2️⃣ Duyệt các vị trí xoay khả thi

// ```java
// for (int i = 0; i < n; i += step) {
// System.arraycopy(s, i, t, 0, n - i);
// System.arraycopy(s, 0, t, n - i, i);
// ```

// → Tạo chuỗi `t` bằng cách xoay `s` sang phải `i` ký tự.
// (Thực ra là `s[i:n] + s[0:i]` — phép xoay trái).

// ---

// ### 3️⃣ Thực hiện

// phép cộng (Add Operation)

// ```java
// modify(t, 1, g);
// if (step % 2 > 0) {
// modify(t, 0, g);
// }
// ```

// * Luôn cộng với **vị trí lẻ (1, 3, 5, …)**.
// * Nếu độ

// dài xoay (`step`) là **lẻ**, thì vị trí chẵn và lẻ hoán đổi sau một lần quay,
// → ta có thể thực hiện cộng cho cả **vị trí chẵn** nữa.

// ---

// ### 4️⃣ Hàm `modify(t, start, g)`

// ```java
// int ch = t[start] - '0';
// int inc = ch % g - ch + 10;
// for (int j = start; j < t.length; j += 2) {
// t[j] = (char) ('0' + (t[j] - '0' + inc) % 10);
// }
// ```

// * `ch` là chữ số đầu

// tiên (ở vị trí `start`) — càng nhỏ càng tốt.
// * Vì phép cộng `a` lặp lại có chu kỳ mod 10, ta có thể giảm số đó xuống **`ch
// % g`** (nhỏ nhất có thể đạt được trong chu kỳ đó).
// * `inc` là lượng

// cần cộng (có thể âm, nên +10 để luôn dương).
// * Sau đó cộng `inc` cho tất cả chữ số ở cùng loại

// chỉ số (chẵn hoặc lẻ).

// ---

// ### 5️⃣ Cập nhật kết quả

// ```java
// String str = new String(t);
// if (ans == null || str.compareTo(ans) < 0) {
// ans = str;
// }
// ```

// → Mỗi lần xét 1 kiểu xoay và cộng, ta so sánh với chuỗi nhỏ nhất `ans` để cập
// nhật.

// ---

// ### 6️⃣ Kết quả cuối

// ```java
// return ans;
// ```

// → Trả về chuỗi có **thứ tự từ điển nhỏ nhất**.

// ---

// ## 💡 Ví dụ minh họa

// **Input:**

// ```
// s = "5525", a = 9, b = 2
// ```

// **Giải thích:**

// * Có thể xoay 2 hoặc 4

// ký tự (`gcd(2, 4) = 2`) → chỉ cần xét 2 vị trí xoay.
// * `a = 9, gcd(9,10)=1` → có thể biến mọi số 0-9 thành bất kỳ số nào khác → tự
// do chọn nhỏ nhất.
// * Kết quả nhỏ nhất là `"2050"`.

// ---

// ## ✅ Độ phức tạp

// * **Thời gian:** `O(n)` vì chỉ xét `n / gcd(b, n)` phép xoay, mỗi phép sửa
// `O(n)`.
// * **Không gian:** `O(n)` cho các mảng tạm.

// ---

// Nếu bạn muốn, mình có thể thêm **phiên bản Java có chú thích chi tiết từng
// dòng** để dễ hiểu hơn không?
