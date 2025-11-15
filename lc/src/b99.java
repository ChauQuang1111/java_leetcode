
// # 3234. Count the Number of Substrings With Dominant Ones(15/11/2025)
// # Dưới đây là **giải thích đề bài LeetCode 3234 — Count the Number of Substrings With Dominant Ones** một cách **rõ ràng – dễ hiểu**.
// # Dưới đây là **giải thích đề bài**, **giải thích thuật toán**, và **code đã được chú thích đầy đủ** để bạn hiểu rõ từng bước.
import java.util.*;

public class b99 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.next();

        int ans = numberOfSubstrings(s);
        System.out.println(ans);
    }

    public static int numberOfSubstrings(String s) {
        return prefixEnumeration(s);
    }

    public static int prefixEnumeration(String s) {
        int n = s.length();

        // zerosIdx[k] = vị trí của số 0 thứ k trong chuỗi
        // zerosIdx[0] = -1 để dễ tính toán số zero trong đoạn đầu
        int[] zerosIdx = new int[n + 1];
        zerosIdx[0] = -1;
        int nextZeroIdx = 1; // số lượng zero đã gặp + 1

        int totalOnes = 0; // tổng số 1 đã gặp tính đến vị trí right
        int res = 0; // kết quả cuối

        // Duyệt tất cả vị trí "right"
        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '0') {
                // Nếu gặp số 0 → lưu vị trí zero vào mảng
                zerosIdx[nextZeroIdx++] = right;
            } else {
                // Nếu gặp số 1 → toàn bộ substring kết thúc tại right
                // và bắt đầu sau zero gần nhất đều hợp lệ
                res += right - zerosIdx[nextZeroIdx - 1];
                totalOnes++; // cập nhật tổng số 1
            }

            // BẮT ĐẦU DUYỆT NGƯỢC QUA NHỮNG ZERO TRƯỚC ĐÓ
            // để kiểm tra những đoạn có số lượng zero tăng dần
            for (int zeroPos = nextZeroIdx - 1; zeroPos > 0 &&
                    (nextZeroIdx - zeroPos) * (nextZeroIdx - zeroPos) <= totalOnes; zeroPos--) {

                int zerosCount = nextZeroIdx - zeroPos; // số zero trong đoạn [L..right]

                // tính số one trong đoạn [zeroIdx[zeroPos]..right]
                int onesCount = (right - zerosIdx[zeroPos] + 1) - zerosCount;

                // số one bị thiếu để thỏa mãn: ones ≥ zeros²
                int onesDeficit = zerosCount * zerosCount - onesCount;

                // Khoảng số vị trí L có thể nằm trong
                int extendable = zerosIdx[zeroPos] - zerosIdx[zeroPos - 1];

                // Nếu còn thiếu one → chỉ một phần substring hợp lệ
                // Nếu không thiếu → toàn bộ đều hợp lệ
                int valid = Math.max(extendable - Math.max(onesDeficit, 0), 0);

                res += valid;
            }
        }

        return res;
    }
}

// # ---

// # # ✅ **Giải thích đề bài (LeetCode 3234 — Count the Number of Substrings
// With Dominant Ones)**

// # Một chuỗi nhị phân `s` có **substring chủ đạo bởi số 1** (dominant ones)
// nếu:

// # [
// # #ones ; \ge ; (#zeros)^2
// # ]

// # Tức là:

// # * Nếu substring có **nhiều số 0**, thì số 1 phải **nhiều hơn bình phương số
// 0**.
// # * Nếu substring không có số 0 ⇒ luôn thỏa mãn, vì `(#zeros)^2 = 0`.

// # 👉 **Nhiệm vụ:** Đếm có bao nhiêu substring thỏa điều kiện trên.

// # ---

// # # ⚠️ Bài này rất khó (Hard).

// # * Số lượng substring = O(n²) → n có thể tới 2×10⁵ → không thể duyệt brute
// force.
// # * Điều kiện `ones ≥ zeros²` là phi tuyến → không dễ dùng kỹ thuật 2 con
// trỏ.

// # Solution bạn đưa là một thuật toán tối ưu với **nhảy chỉ mục (jumping)**
// dựa trên:

// # * prefix sum của số 0
// # * danh sách vị trí các số 0 và số 1
// # * nhảy theo điều kiện toán học để bỏ qua những điểm không cần xét

// # ---

// # # 🚀 **Ý tưởng chính của thuật toán**

// # ## 1️⃣ Ta giữ:

// # * `cumZeros[i]` = số lượng số 0 trong đoạn s[0..i-1]
// # * `posZeros[]` = danh sách vị trí số 0
// # * `posOnes[]` = danh sách vị trí số 1

// # ## 2️⃣ Khi đang đứng tại vị trí `i`, thuật toán:

// # * Xét các substring kết thúc tại `i`: `[left, i]`
// # * Nhưng không xét tuần tự từ `i→0`
// # → **nhảy (jump)** để bỏ qua hàng loạt vị trí không hợp lệ.

// # ## 3️⃣ Hai hướng nhảy:

// # * **nhảy theo số 0** nếu `(zeros² <= ones)` thỏa
// # * **nhảy theo số 1** nếu chưa thỏa điều kiện

// # ## 4️⃣ Mỗi lần nhảy, ta có thể:

// # * cộng trực tiếp nhiều substring 1 lượt (không cần đếm từng cái)
// # * tối ưu về thời gian

// # ---

// # # ✅ Code Python đã được chú thích đầy đủ

// # ```python
// from math import ceil
// class Solution:
// def numberOfSubstrings(self, s: str) -> int:
// n = len(s)

// # cumZeros[i] = số lượng số 0 trong s[0..i-1]
// cumZeros = [0] * (n + 1)

// # vị trí của các số 0 và 1
// posZeros = [-1]
// posOnes = [-1]

// res = 0

// for i, c in enumerate(s):

// # --- cập nhật số 0 / số 1 hiện tại ---
// if c == '1':
// posOnes.append(i)
// res += 1 # substring (i,i) luôn hợp lệ (1 >= 0^2)
// curZeros = cumZeros[i]
// curOnes = i - curZeros + 1
// left = posZeros[curZeros - 1] # nhảy theo vị trí zero
// else:
// posZeros.append(i)
// curZeros = cumZeros[i] + 1
// curOnes = i - curZeros + 1
// left = posOnes[curOnes] # nhảy theo vị trí one

// # cập nhật prefix sum số 0
// cumZeros[i + 1] = curZeros

// right = i

// # --- Bắt đầu nhảy ngược để tìm left ---
// while left >= 0:
// countZero = curZeros - cumZeros[left] # số zero trong [left..i]
// countOne = (i - left + 1) - countZero # số one trong [left..i]
// sqZero = countZero * countZero

// # Nếu thỏa điều kiện ones >= zeros^2
// if sqZero <= countOne:

// if s[left] == "1":
// res += 1 # chỉ cộng substring [left, i]
// else:
// res += right - left # cộng nhiều substring một lần

// # Cập nhật right để tránh đếm trùng
// right = left

// # Tính toán vị trí zero tiếp theo để nhảy
// nextZero = curZeros - ceil((countOne + 1) ** 0.5) + 1
// left = posZeros[nextZero] if nextZero >= 0 else -1

// else:
// # Không thỏa điều kiện
// if s[left] == "0":
// res += right - left - 1

// right = left

// # nhảy theo số 1
// nextOne = curOnes - sqZero + 1
// left = posOnes[nextOne] if nextOne >= 0 else -1

// # Nếu cả đoạn từ 0..i đều hợp lệ thì cộng tất cả substring
// if curZeros * curZeros <= curOnes:
// res += right

// return res
// # ```

// # ---

// # # 📌 Giải thích ngắn gọn dễ hiểu

// # ### Tại mỗi `i`, ta muốn đếm số substring kết thúc tại `i`:

// # ```
// # [left, i]
// # [left+1, i]
// # ...
// # [i, i]
// # ```

// # Nhưng không duyệt từng cái.

// # Thay vào đó:

// # * dùng toán học suy ra “điểm breakpoint” mà từ đó trở đi tất cả substring
// đều thỏa hoặc đều không thỏa.
// # * nhảy bằng cách dùng chỉ số của zeros/ones trong posZeros và posOnes.

// # → Mỗi vòng lặp while chỉ chạy O(√n) lần, tổng thể O(n√n).

// # ---

// # # Nếu bạn muốn:

// # 👉 Mình có thể vẽ sơ đồ minh họa từng bước chạy thuật toán
// # 👉 Hoặc viết lại phiên bản **ngắn gọn / dễ hiểu hơn** (nhưng có thể chậm
// hơn)
// # 👉 Hoặc viết lại bằng **Java**

// # Bạn cần loại nào?

// # ---

// # # 🧩 **3234. Count the Number of Substrings With Dominant Ones**

// # Bạn được cho một chuỗi nhị phân `s` gồm `'0'` và `'1'`.

// # Một **substring** (chuỗi con liên tiếp) được gọi là **DOMINANT ONES** nếu:

// # ### 👉 **Số lượng '1' trong substring ≥ (độ dài substring)²**

// # Nhiệm vụ:
// # ➡️ **Đếm xem có bao nhiêu substring thỏa điều kiện trên.**

// # ---

// # # 🔍 Ví dụ minh họa

// # Giả sử:

// # ```
// # s = "110"
// # ```

// # Tất cả substring:

// # 1. `"1"` → 1 one → length = 1 → 1 ≥ 1² = 1 ✔
// # 2. `"1"` → 1 ≥ 1 ✔
// # 3. `"0"` → 0 ≥ 1 ✘
// # 4. `"11"` → 2 ones → length = 2 → 2 ≥ 4 ✘
// # 5. `"10"` → 1 ≥ 4 ✘
// # 6. `"110"` → 2 ≥ 9 ✘

// # ➡️ Tổng: **2 substring hợp lệ**

// # ---

// # # 🎯 Điều kiện quan trọng

// # Substring `s[l..r]` (từ l đến r) có chiều dài `k = r - l + 1`.

// # Điều kiện:

// # ```
// # số lượng '1' ≥ k²
// # ```

// # Ví dụ:

// # | substring | length k | ones | k² | hợp lệ? |
// # | --------- | -------- | ---- | -- | ------- |
// # | "1" | 1 | 1 | 1 | ✔ |
// # | "11" | 2 | 2 | 4 | ✘ |
// # | "1111" | 4 | 4 | 16 | ✘ |
// # | "11111" | 5 | 5 | 25 | ✘ |

// # Bạn thấy rằng **chiều dài tăng nhanh hơn số 1**, nên chỉ substring rất ngắn
// mới có thể hợp lệ.

// # ---

// # # ⚠️ Nhận xét quan trọng

// # Vì `k²` tăng RẤT NHANH, nên khi `k > sqrt(n)`, substring gần như chắc chắn
// không hợp lệ.

// # → giải pháp sẽ tập trung vào:

// # * duyệt theo số lượng ‘1’
// # * hoặc duyệt k nhỏ

// # ---

// # # 💡 Tóm tắt đề bài → rất khó nếu duyệt brute force

// # Số substring = O(n²) → với n = 2e5, duyệt tất cả sẽ TLE.

// # Nhưng dựa vào điều kiện `ones ≥ k²` → **k rất nhỏ**, chỉ khoảng ≤ 450.

// # Nên ta chỉ duyệt k nhỏ và đếm substring hợp lệ.

// # ---

// # # 📌 Nếu bạn muốn, mình có thể:

// # ✔ Giải thích thuật toán tối ưu
// # ✔ Viết code Python / Java
// # ✔ Giải thích code dòng-từng-dòng

// # Chỉ cần bạn nói: **"giải thích thuật toán"** hoặc **"viết code Java"** nhé!
// Dưới đây là **giải thích chi tiết – dễ hiểu – trực quan** cho thuật toán Java
// trên.
// Đây là 1 lời giải **tối ưu** của bài *3234. Count the Number of Substrings
// With Dominant Ones*.

// ---

// # 🎯 **Mục tiêu bài toán**

// Đếm số substring **[L, R]** sao cho:

// ### ✔ số lượng **1** ≥ (số lượng **0**)²

// Gọi:

// * `#1 = countOne`
// * `#0 = countZero`

// Điều kiện:

// ```
// countOne ≥ countZero²
// ```

// ---

// # 🎯 Ý tưởng của lời giải

// Thay vì duyệt L–R bằng brute force (O(n²)), thuật toán dùng:

// ### **1. prefix enumeration + lưu vị trí số 0**

// Mỗi khi ta mở rộng `right`, ta sẽ:

// * cập nhật tổng số 1 đã thấy (`totalOnes`)
// * lưu vị trí số 0 vào mảng `zerosIdx`
// * đếm nhanh số substring kết thúc ở `right`

// ### **2. Liệt kê các đoạn theo vị trí zero để xác định xem đoạn nào thỏa mãn
// điều kiện**

// Nếu ta biết vị trí của từng zero:

// ```
// zerosIdx = [-1, z1, z2, z3, ...]
// ```

// Thì:

// * đoạn substring [L, R] sẽ chứa số lượng zero = k
// nếu L nằm giữa 2 zero này:

// ```
// zerosIdx[z-1] < L ≤ zerosIdx[z]
// ```

// Khi biết k, ta tính được:

// ```
// ones = length - k
// zero² = k²
// ```

// → kiểm tra điều kiện nhanh.

// ---

// # 🔍 Giải thích **từng đoạn code**

// ---

// ## ✔ Danh sách vị trí số 0

// ```java
// int[] zerosIdx = new int[n + 1];
// int nextZeroIdx = 1;
// zerosIdx[0] = -1;
// ```

// * `zerosIdx` chứa vị trí của từng số 0.
// * `zerosIdx[0] = -1` dùng để tính số zero trong đoạn đầu (không có zero).
// * `nextZeroIdx` = số lượng zero đã gặp + 1.

// ---

// ## ✔ Biến dùng để tính toán

// ```java
// int totalOnes = 0;
// int res = 0;
// ```

// * `totalOnes` = số lượng 1 đã gặp từ đầu đến `right`.
// * `res` = tổng số substring thỏa mãn điều kiện.

// ---

// # ✔ Vòng lặp qua từng `right`

// ```java
// for(int right = 0; right < n; right++) {
// ```

// ---

// ## 1️⃣ Nếu ký tự là '0'

// ```java
// if(s.charAt(right) == '0') {
// zerosIdx[nextZeroIdx++] = right;
// }
// ```

// → Lưu vị trí zero.

// ---

// ## 2️⃣ Nếu ký tự là ‘1’

// ```java
// else {
// res += right - zerosIdx[nextZeroIdx - 1];
// totalOnes++;
// }
// ```

// Nếu ta thêm 1 vào đoạn kết thúc tại `right`, thì:

// ### • mọi substring bắt đầu từ

// `zerosIdx[lastZero] + 1` → `right`
// đều thỏa `#ones >= #zero²`,
// vì đoạn không chứa thêm zero mới.

// → Cộng tất cả ngay lập tức:

// ```
// right - lastZeroPos
// ```

// ---

// # ✔ Liệt kê tất cả các vùng zero để xét điều kiện

// ```java
// for(int zeroPos = nextZeroIdx - 1;
// zeroPos > 0 && (nextZeroIdx - zeroPos) * (nextZeroIdx - zeroPos) <=
// totalOnes;
// zeroPos--) {
// ```

// ### Ý nghĩa:

// * Ta duyệt từ zero gần nhất quay lùi lại.
// * `nextZeroIdx - zeroPos` = số zero trong đoạn.
// * Nếu `k² > totalOnes` → không thể có substring nào thỏa mãn nữa → dừng.

// ### Bên trong vòng lặp:

// ### ➤ Tính số zero trong đoạn

// ```java
// int zerosCount = nextZeroIdx - zeroPos;
// ```

// ### ➤ Tính số one trong đoạn [zeroPos..right]

// ```java
// int onesCount = right - zerosIdx[zeroPos] + 1 - zerosCount;
// ```

// ### ➤ Tính “số one còn thiếu”

// ```java
// int onesDeficit = zerosCount * zerosCount - onesCount;
// ```

// Nếu `onesDeficit > 0` → ta cần thêm one → một số substring là không hợp lệ.

// ---

// ### ➤ Khoảng có thể bắt đầu substring

// ```java
// int extendable = zerosIdx[zeroPos] - zerosIdx[zeroPos - 1];
// ```

// → Đây là số vị trí L hợp lệ nằm trong khoảng đó.

// ---

// ### ➤ Số substring hợp lệ

// ```java
// res += Math.max(extendable - Math.max(onesDeficit, 0), 0);
// ```

// * Nếu dư one → mọi substring đều hợp lệ.
// * Nếu thiếu one → trừ đi những vị trí không đủ one.

// ---

// # 🎯 TÓM TẮT Ý TƯỞNG CHÍNH

// 1. Lưu vị trí zero để nhanh chóng xác định số zero trong [L, R].
// 2. Mỗi khi mở rộng `right`:

// * Nếu là 1 → thêm được một loạt substring ngay.
// * Nếu là 0 → chỉ cập nhật vị trí zero.
// 3. Dùng kỹ thuật "nhảy giữa các zero" để xét tất cả vùng [L, R] mà không phải
// duyệt từng L một.
// 4. Tính số one/zero trong đoạn thông qua vị trí zero.
// 5. Chỉ cộng vào kết quả những substring thỏa mãn #ones ≥ #zeros².

// → Thuật toán chạy O(n√n), rất nhanh cho n ≤ 10⁵.

// ---

// Nếu bạn muốn **minh họa bằng ví dụ bước từng bước**, mình làm ngay!
