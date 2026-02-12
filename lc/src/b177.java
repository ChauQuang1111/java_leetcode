// # Longest Balanced Substring I(12/02/2026)
// # Ok mình giải thích chi tiết nhé.
// Ok👍—mình sẽ:

// 1.**Thêm hàm`main`dùng`Scanner`**để nhập chuỗi 2.**Giữ nguyên thuật toán của bạn**3.**Thêm chú thích chi tiết từng dòng**để bạn học/submit dễ hiểu

// ---

// #✅Code hoàn chỉnh(có`main`+`Scanner`+chú thích)

// ```java

import java.util.*;

public class b177 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.nextLine();

        // Gọi hàm xử lý
        int ans = longestBalanced(s);

        // In kết quả
        System.out.println("Do dai Balanced Substring dai nhat: " + ans);

        sc.close();
    }

    // Hàm tìm độ dài Balanced Substring dài nhất
    public static int longestBalanced(String s) {
        int n = s.length(); // Độ dài chuỗi

        // ==============================
        // Chuyển mỗi ký tự thành số 0–25
        // Ví dụ: 'a' -> 0, 'b' -> 1 ...
        // Giúp dùng mảng đếm nhanh hơn
        // ==============================
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = s.charAt(i) - 'a';

        int result = 0; // Lưu kết quả lớn nhất

        // ==============================
        // Duyệt từng vị trí làm điểm bắt đầu
        // ==============================
        for (int l = 0; l < n; l++) {

            // --------------------------------
            // Early exit:
            // Nếu phần còn lại của chuỗi
            // không dài hơn result hiện tại
            // thì dừng để tối ưu thời gian
            // --------------------------------
            if (n - l <= result)
                break;

            // Mảng đếm số lần xuất hiện của 26 chữ cái
            int[] cnt = new int[26];

            int uniq = 0; // Số ký tự khác nhau
            int maxfreq = 0; // Tần suất lớn nhất

            // ==============================
            // Mở rộng điểm kết thúc
            // ==============================
            for (int r = l; r < n; r++) {

                int i = a[r]; // Ký tự hiện tại (dạng số)

                // Nếu ký tự này chưa xuất hiện
                // -> tăng số ký tự khác nhau
                if (cnt[i] == 0)
                    uniq++;

                // Tăng số lần xuất hiện
                cnt[i]++;

                // Cập nhật tần suất lớn nhất
                if (cnt[i] > maxfreq)
                    maxfreq = cnt[i];

                // Độ dài chuỗi con hiện tại
                int cur = r - l + 1;

                // ----------------------------------------
                // Kiểm tra balanced:
                // Nếu mọi ký tự có cùng tần suất
                // <=> uniq * maxfreq == độ dài
                // ----------------------------------------
                if (uniq * maxfreq == cur && cur > result)
                    result = cur;
            }
        }

        return result;
    }
}

// ==============================
// Hàm main dùng Scanner nhập dữ liệu
// ==============================

// ---

// #🧪

// Ví dụ
// chạy thử

// ###Input

// Nhap chuoi:aabbcc```

// ###Output

// ```
// Do dai
// Balanced Substring
// dai nhat:6```

// ---

// ###Input

// ```
// Nhap chuoi:aaabb```

// ###Output

// ```
// Do dai
// Balanced Substring
// dai nhat:4```

// (`aabb`
// là balanced
// dài nhất)

// ---

// #📌
// Tóm tắt
// lại thuật
// toán trong code

// *
// Duyệt mọi substring`O(n²)`
// * Đếm tần suất 26 chữ cái
// * Theo dõi:

// * `uniq` = số ký tự khác nhau
// *`maxfreq`=

// tần suất
// lớn nhất
// * Nếu:

// ```
// uniq × maxfreq == độ dài
// ```

// ⇒ Balanced

// ---

// Nếu bạn cần mình làm thêm:

// * Đổi sang **Scanner + nhiều test case**
// * Viết lại theo style bạn hay dùng (`while` thay `for`)
// * In luôn substring chứ không chỉ độ dài

// Cứ nói mình làm tiếp 👍

// # Trước tiên cần làm rõ: **code bạn đưa KHÔNG phải Longest Balanced Substring
// dạng 0–1** mình giải thích trước đó.

// # Code này thực chất đang giải bài:

// # > **Longest substring mà mọi ký tự xuất hiện số lần bằng nhau**
// # > (tức là “balanced” theo nghĩa tần suất ký tự bằng nhau)

// # Ví dụ hợp lệ:

// # * `"aabb"` → a=2, b=2
// # * `"abcabc"` → a=b=c=2
// # * `"zz"` → z=2

// # Không hợp lệ:

// # * `"aab"` → a=2, b=1 ❌

// # ---

// # 🧠 Ý tưởng thuật toán

// # Ta duyệt mọi chuỗi con `s[l…r]` và kiểm tra:

// # ```
// # Số ký tự khác nhau (uniq)
// # ×
// # Tần suất lớn nhất (maxfreq)
// # ==
// # Độ dài chuỗi con
// # ```

// # Nếu đúng ⇒ mọi ký tự có cùng tần suất.

// # ---

// # ## ❓ Vì sao công thức đúng?

// # Giả sử chuỗi con có:

// # * `uniq = k` ký tự khác nhau
// # * Mỗi ký tự xuất hiện `f` lần

// # Thì:

// # ```
// # Độ dài = k × f
// # ```

// # Trong code:

// # ```
// # maxfreq = f
// # cur = độ dài
// # ```

// # Nên:

// # ```
// # uniq * maxfreq == cur
// # ```

// # ⇒ Balanced

// # ---

// # 🔎 Giải thích từng bước code

// ## 1️⃣ Chuẩn bị dữ liệu

// # ```python
// # n = len(s)
// # s = [ord(char) - ord('a') for char in s]
// # ```

// # ### Ý nghĩa

// # * Chuyển ký tự → số 0–25
// # * Giúp index mảng nhanh hơn

// # Ví dụ:

// # ```
// # "a" → 0
// # "b" → 1
// # "z" → 25
// # ```

// # ---

// # ## 2️⃣ Biến kết quả

// # ```python
// # result = 0
// # ```

// # Lưu độ dài chuỗi cân bằng dài nhất.

// # ---

// # ## 3️⃣ Chọn điểm bắt đầu `l`

// # ```python
// # for l in range(n):
// # ```

// # Duyệt mọi vị trí làm đầu chuỗi con.

// # ---

// # ## 4️⃣ Early exit (tối ưu)

// # ```python
// # if n - l <= result:
// # break
// # ```

// # Nếu phần còn lại của chuỗi ≤ kết quả hiện tại
// # ⇒ Không thể tìm chuỗi dài hơn ⇒ dừng sớm.

// # ---

// # ## 5️⃣ Khởi tạo thống kê

// # ```python
// # cnt = [0] * 26
// # uniq = maxfreq = 0
// # ```

// # * `cnt[i]` = số lần ký tự i xuất hiện
// # * `uniq` = số ký tự khác nhau
// # * `maxfreq` = tần suất lớn nhất

// # ---

// # ## 6️⃣ Mở rộng điểm kết thúc `r`

// # ```python
// # for r in range(l, n):
// # ```

// # Xét chuỗi con `s[l…r]`.

// # ---

// # ## 7️⃣ Cập nhật tần suất

// # ```python
// # i = s[r]

// # uniq += cnt[i] == 0
// # cnt[i] += 1
// # ```

// # Giải thích:

// # * Nếu ký tự này chưa xuất hiện → tăng `uniq`
// # * Sau đó tăng số đếm

// # ---

// # ## 8️⃣ Cập nhật maxfreq

// # ```python
// # if cnt[i] > maxfreq:
// # maxfreq = cnt[i]
// # ```

// # Theo dõi tần suất lớn nhất trong chuỗi con.

// # ---

// # ## 9️⃣ Kiểm tra balanced

// # ```python
// # cur = r - l + 1

// # if uniq * maxfreq == cur and cur > result:
// # result = cur
// # ```

// # Nếu:

// # ```
// # độ dài = số ký tự khác nhau × tần suất lớn nhất
// # ```

// # ⇒ Mọi ký tự có cùng tần suất.

// # ---

// # # ✍️ Code đã thêm chú thích

// # ```python
// class Solution:
// def longestBalanced(self, s: str) -> int:
// n = len(s)

// # Chuyển mỗi ký tự thành số 0–25 để dễ đếm
// s = [ord(char) - ord('a') for char in s]

// result = 0 # Lưu độ dài chuỗi cân bằng dài nhất

// # Duyệt từng vị trí làm điểm bắt đầu
// for l in range(n):

// # Nếu phần còn lại không thể dài hơn result → dừng sớm
// if n - l <= result:
// break

// cnt = [0] * 26 # Mảng đếm tần suất 26 chữ cái
// uniq = 0 # Số ký tự khác nhau
// maxfreq = 0 # Tần suất lớn nhất trong chuỗi con

// # Mở rộng điểm kết thúc
// for r in range(l, n):
// i = s[r] # Ký tự hiện tại (dạng số)

// # Nếu ký tự này chưa xuất hiện → tăng uniq
// if cnt[i] == 0:
// uniq += 1

// # Tăng số lần xuất hiện
// cnt[i] += 1

// # Cập nhật tần suất lớn nhất
// if cnt[i] > maxfreq:
// maxfreq = cnt[i]

// # Độ dài chuỗi con hiện tại
// cur = r - l + 1

// # Nếu mọi ký tự có cùng tần suất
// # ⇔ uniq * maxfreq == độ dài
// if uniq * maxfreq == cur:
// result = max(result, cur)

// return result
// # ```

// # ---

// # # ⏱️ Độ phức tạp

// # * Hai vòng lặp: `O(n²)`
// # * Mỗi bước cập nhật O(1)

// # ⇒ **Time: O(n²)**
// # ⇒ **Space: O(26) ≈ O(1)**

// # ---

// # # 🧩 Ví dụ chạy thử

// # Chuỗi:

// # ```
// # s = "aabbcc"
// # ```

// # Tại substring `"aabbcc"`:

// # * uniq = 3
// # * maxfreq = 2
// # * cur = 6

// # ```
// # 3 × 2 = 6 ✅ balanced
// # ```

// # ---

// # Nếu bạn muốn mình:

// # * Trace từng bước với bảng đếm
// # * So sánh với sliding window
// # * Tối ưu xuống O(n)

// # Cứ gửi chuỗi ví dụ mình chạy tay cho bạn 👍
