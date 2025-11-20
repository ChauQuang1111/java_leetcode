
// # 757. Set Intersection Size At Least Two
// # Dưới đây là **giải thích đề bài LeetCode 757 – Set Intersection Size At Least Two** một cách **dễ hiểu và chi tiết**.
// # Dưới đây là **giải thích thuật toán** + **code có chú thích đầy đủ** cho lời giải bạn đưa ra.
import java.util.*;

public class b104 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[][] intervals = new int[n][2];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt(); // start
            intervals[i][1] = sc.nextInt(); // end
        }

        int result = intersectionSizeTwo(intervals);

        System.out.println(result);
    }

    public static int intersectionSizeTwo(int[][] intervals) {
        int n = 0;

        // Mảng long để encode (end, start) vào chung 1 số để sort nhanh
        long[] endStartPairs = new long[intervals.length];

        for (int[] interval : intervals) {
            // Lấy start và end
            int start = interval[0];
            int end = interval[1];

            // encode: 32 bit cao = end, 32 bit thấp = -start
            // dùng -start để sort start giảm dần khi end bằng nhau
            endStartPairs[n] = (-start & 0xFFFFFFFFL);
            endStartPairs[n] |= ((long) end << 32);
            n++;
        }

        // Sort theo end tăng dần, nếu end bằng nhau thì start giảm dần
        Arrays.sort(endStartPairs);

        int min = -2; // số nhỏ hơn trong 2 số đã chọn
        int max = -1; // số lớn hơn trong 2 số đã chọn
        int res = 0; // tổng số phần tử cần chọn

        // Duyệt từng interval đã sort
        for (long pair : endStartPairs) {

            int curStart = -(int) pair; // decode start
            int curEnd = (int) (pair >> 32); // decode end

            // CASE 1: interval chứa cả min và max → không cần thêm số
            if (curStart <= min) {
                continue;
            }

            // CASE 2: interval chứa 1 số (max) → cần thêm 1 số
            if (curStart <= max) {
                res += 1;
                min = max; // cập nhật cặp số mới
            }

            // CASE 3: interval không chứa số nào → cần thêm 2 số
            else {
                res += 2;
                min = curEnd - 1; // chọn số tối ưu nhất tại cuối interval
            }

            max = curEnd; // cập nhật số lớn nhất
        }

        return res;
    }
}

// # ---

// # # 🧠 **Giải thích thuật toán**

// # Ta muốn chọn **ít nhất 2 số** trong mỗi interval. Thuật toán dùng greedy:

// # 1. **Sort intervals theo điểm kết thúc tăng dần (b)**
// # → Greedy kinh điển: đặt điểm càng về cuối càng dễ “ăn” được nhiều segment.

// # 2. Duy trì hai điểm:

// # * `prev1`: điểm nhỏ hơn
// # * `prev2`: điểm lớn hơn
// # Đây là **2 điểm cuối cùng** ta đã chọn sao cho thỏa mãn tất cả các interval
// trước đó.

// # 3. Xét từng interval `cur = [L, R]`:

// # ---

// # ## 🎯 **TH1: Không giao với 1 trong 2 điểm → Không đủ 2 điểm**

// # Nếu:

// # ```
// # prev2 < L
// # ```

// # ⇒ Cả hai điểm hiện tại (`prev1`, `prev2`) đều nằm ngoài đoạn mới.

// # 👉 Ta phải **thêm 2 điểm mới**:
// # Chọn hai điểm gần R nhất:

// # ```
// # prev1 = R - 1
// # prev2 = R
// # ```

// # Vì chọn điểm càng lớn càng “ăn” được nhiều đoạn sau.

// # ---

// # ## 🎯 **TH2: Chỉ có 1 điểm nằm trong đoạn**

// # Nếu:

// # ```
// # prev1 < L ≤ prev2
// # ```

// # ⇒ `prev2` nằm trong interval, nhưng `prev1` không nằm trong.

// # 👉 Ta cần thêm **1 điểm**, ưu tiên đặt ở điểm cuối của đoạn để có lợi nhất.

// # * Nếu `R == prev2` → prev2 đã là điểm cuối → ta chọn `prev1 = R - 1`
// # * Ngược lại → chọn `prev1 = R`

// # Sau đó sắp lại prev1, prev2 để prev1 luôn nhỏ hơn prev2.

// # ---

// # ## 🎯 **TH3: Cả hai điểm đã nằm trong đoạn**

// # Không cần làm gì.

// # ---

// # # 🧮 **Độ phức tạp**

// # * Sắp xếp: **O(n log n)**
// # * Duyệt 1 lần: **O(n)**
// # → Tổng: **O(n log n)**

// # ---

// # # ✅ **Code có chú thích đầy đủ**

// # ```python
// from typing import List
// class Solution:
// def intersectionSizeTwo(self, intervals: List[List[int]]) -> int:
// n = len(intervals)

// # Sắp xếp intervals theo điểm kết thúc tăng dần
// intervals.sort(key=lambda x: x[1])

// # Chọn hai điểm cho interval đầu tiên:
// # prev1 là điểm nhỏ hơn, prev2 là điểm lớn hơn
// prev1 = intervals[0][1] - 1
// prev2 = intervals[0][1]
// count = 2 # Đã chọn 2 điểm

// # Duyệt các interval tiếp theo
// for i in range(1, n):
// L, R = intervals[i]

// # CASE 1: Không có điểm nào nằm trong đoạn này → cần chọn 2 điểm mới
// if prev2 < L:
// # Chọn 2 điểm gần R nhất (R-1 và R)
// prev1 = R - 1
// prev2 = R
// count += 2

// # CASE 2: Chỉ có 1 điểm nằm trong đoạn → cần chọn thêm 1 điểm
// elif prev1 < L:
// # prev2 ăn được khoảng, prev1 không ăn được
// # Ta phải chọn 1 điểm mới trong đoạn này

// if R == prev2:
// # Nếu prev2 chính là điểm cuối, ta đành lấy R-1
// prev1 = R - 1
// else:
// # Nếu prev2 chưa phải điểm cuối, ta lấy R
// prev1 = R

// # Đảm bảo prev1 < prev2
// prev1, prev2 = min(prev1, prev2), max(prev1, prev2)

// count += 1

// # CASE 3: prev1 và prev2 đều đã nằm trong interval → không cần làm gì

// return count

// # # 🎉 Nếu bạn muốn, mình có thể viết thêm:

// # ✔ Phiên bản Java
// # ✔ Thêm hàm `main()` + chú thích
// # ✔ Giải thích bằng hình minh họa từng bước

// # Chỉ cần nói “viết Java” hoặc “giải thích bằng hình”.

// # ---

// # # 📝 **📌 Đề bài nói gì?**

// # Bạn được cho một danh sách các **interval** (đoạn), mỗi đoạn có dạng:

// # ```
// # [a, b]
// # ```

// # Bạn cần chọn ra **một tập hợp các số nguyên** sao cho:

// # 👉 **Mỗi đoạn phải giao với tập hợp ít nhất 2 phần tử.**
// # Tức là với mỗi đoạn `[a, b]`, trong tập bạn chọn phải có **ít nhất 2 số nằm
// trong khoảng từ a đến b** (bao gồm cả a và b).

// # 🎯 **Mục tiêu:**
// # Chọn **ít nhất bao nhiêu số** để đảm bảo **tất cả các khoảng đều chứa ≥ 2
// số từ tập bạn chọn**.

// # ---

// # # 📌 Ví dụ

// # ## Ví dụ 1:

// # Input:

// # ```
// # intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
// # ```

// # Output:

// # ```
// # 3
// # ```

// # Giải thích:
// # Bạn có thể chọn tập `{2, 3, 5}`:

// # * `[1, 3]` chứa 2 và 3 → đủ
// # * `[1, 4]` chứa 2 và 3 → đủ
// # * `[2, 5]` chứa 2, 3, 5 → đủ
// # * `[3, 5]` chứa 3 và 5 → đủ

// # Tập có **3 số**, là tối thiểu.

// # ---

// # # 📌 Hiểu đơn giản hơn

// # Bạn có các đoạn, và bạn cần “bắn” các điểm sao cho **mỗi đoạn bị bắn trúng
// ít nhất 2 viên đạn**.

// # Đặt ít đạn nhất có thể.

// # ---

// # # 🧠 Ý tưởng giải greedy (tham lam)

// # 1. **Sort (sắp xếp)** các đoạn theo:

// # * **Tăng dần theo điểm kết thúc b**
// # * Nếu bằng nhau thì giảm dần điểm bắt đầu a

// # 2. Duy trì **hai điểm lớn nhất** mà bạn đã chọn cho vùng giao.

// # 3. Với mỗi đoạn:

// # * Nếu đoạn đã chứa đủ 2 điểm → bỏ qua
// # * Nếu chứa 1 điểm → chọn thêm 1 điểm (b)
// # * Nếu chứa 0 điểm → chọn 2 điểm (b-1, b)

// # 4. Mỗi lần luôn ưu tiên **chọn điểm lớn nhất có thể** (điểm cuối của đoạn),
// để tăng khả năng “khớp” với các đoạn sau.

// # Đây là greedy kinh điển cho bài toán “đảm bảo intersection size ≥ 2”.

// # ---

// # # 📌 Tại sao phải sắp xếp theo điểm kết thúc b?

// # Vì khi ta đặt một số ở gần **b (điểm kết thúc)**, nó có khả năng nằm trong
// **nhiều đoạn nhất**, giúp giảm số lượng số phải đặt.

// # ---

// # # 📌 Độ khó?

// # **Hard** (LeetCode đánh giá Hard), nhưng ý tưởng greedy lại rất đẹp.

// # ---

// # # 📌 Nếu bạn muốn mình viết code Java + chú thích chi tiết, mình có thể
// viết ngay.
// Dưới đây là **giải thích đầy đủ và cực dễ hiểu** cho thuật toán Java của bạn
// — đây là **một cách triển khai rất tối ưu** và có phần “compact”, nên mình sẽ
// phân tích kỹ từng bước.

// ---

// # 🌟 **Mục tiêu bài toán**

// Cho danh sách interval `[start, end]`.
// Ta cần chọn **tập nhỏ nhất các số nguyên** sao cho **mỗi interval chứa ít
// nhất 2 số**.

// → Đây là phiên bản khó hơn của greedy interval scheduling.

// ---

// # 🧠 **Ý tưởng thuật toán**

// 1. **Sort interval theo**:

// * `end` tăng dần
// * nếu bằng thì `start` giảm dần

// Nhưng code dùng **trick nén 2 số start và end vào 1 long** rồi sort:

// ```
// long = (end << 32) | (-start)
// ```

// → Sort long sẽ tự động sort theo end trước, rồi đến start giảm dần.

// 2. Duy trì 2 số cuối cùng đã chọn:

// * `min`: số nhỏ hơn
// * `max`: số lớn hơn

// Đây là **2 số đảm bảo thỏa mãn tất cả các interval trước đó**.

// 3. Duyệt từng interval `(curStart, curEnd)` theo thứ tự đã sort.

// ---

// # 🎯 **Cách xử lý từng interval**

// ## **CASE 1 — Interval đã được bao phủ bởi 2 số**

// Nếu:

// ```
// curStart <= min
// ```

// ⇒ interval hiện tại chứa cả `min` và `max`
// → Không cần thêm số
// → continue

// ---

// ## **CASE 2 — Interval chứa đúng 1 trong 2 số**

// Nếu:

// ```
// min < curStart <= max
// ```

// ⇒ interval chứa `max` nhưng không chứa `min`
// → Cần thêm **1 số** để đủ 2

// Thuật toán:

// ```
// res += 1;
// min = max; // số nhỏ hơn trở thành số lớn hiện tại
// ```

// ---

// ## **CASE 3 — Interval không chứa số nào**

// Nếu:

// ```
// curStart > max
// ```

// → Cần thêm **2 số mới**

// Ta chọn 2 số tối ưu nhất nằm ở cuối interval:

// ```
// min = curEnd - 1;
// max = curEnd;
// res += 2;
// ```

// ---

// # 🧠 **Tại sao luôn chọn gần curEnd nhất?**

// Greedy interval:

// * Chọn số càng lớn, càng dùng lại được cho các interval sau.
// * Chọn `curEnd` và `curEnd - 1` là tối ưu nhất.

// ---

// # ✨ **Giải thích code chi tiết**

// ```java
// long[] endStartPairs = new long[intervals.length];
// for (int[] interval : intervals) {
// endStartPairs[n] = -interval[0] & 0xFFFFFFFFL; // encode start âm
// endStartPairs[n++] |= (long) (interval[1]) << 32; // encode end vào 32 bit
// cao
// }
// Arrays.sort(endStartPairs);
// ```

// 📌 Dòng này biến mỗi interval thành một số 64-bit để sort nhanh:

// * 32 bit cao = end
// * 32 bit thấp = -start

// Sort tăng dần theo end, nếu end bằng nhau thì theo **-start**, tức là start
// giảm dần.

// ---

// ## **Duyệt interval**

// ```java
// int min = -2;
// int max = -1;
// int res = 0;
// ```

// Khởi tạo 2 giá trị không thuộc interval nào.

// ---

// ### Decode lại interval

// ```java
// curStart = -(int) endStartPair;
// curEnd = (int) (endStartPair >> 32);
// ```

// ---

// ### CASE 1: interval chứa cả 2 số

// ```java
// if (curStart <= min) {
// continue;
// }
// ```

// ---

// ### CASE 2: interval chứa 1 số (`max`) → thêm 1 số

// ```java
// if (curStart <= max) {
// res += 1;
// min = max;
// }
// ```

// ---

// ### CASE 3: interval không chứa số nào → thêm 2 số

// ```java
// else {
// res += 2;
// min = curEnd - 1; // chọn số tối ưu
// }
// max = curEnd;
// ```

// ---

// # ✅ **Kết quả**

// `res` = tổng số phần tử cần chọn.

// ---

// # 🎉 **Bạn muốn mình thêm không?**

// Muốn mình viết:

// * phiên bản code Java có **Scanner + main**
// * phiên bản Python
// * phiên bản dễ đọc hơn (không dùng long encoding)
// * hoặc minh họa bằng hình?

// Chỉ cần nói: **“viết bản đơn giản hơn”** hoặc **“thêm hàm main”**.
