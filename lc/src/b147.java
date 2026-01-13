
// # # Dưới đây là **giải thích đúng tinh thần bài “Separate Squares I”((13/01/2026)) trên LeetCode**, bằng tiếng Việt, tập trung vào **hiểu đề – không code**.
// # Mình sẽ **giải thích thuật toán của bài 3453. Separate Squares I (LeetCode)** theo đúng đoạn code bạn đưa, rồi **thêm chú thích chi tiết vào từng dòng** để bạn nhìn là hiểu ngay.
// Mình giải thích **thuật toán của lời giải Java** bạn đưa cho bài **3453. Separate Squares I** theo đúng logic, dễ hiểu, không lan man.
import java.util.*;

public class b147 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số lượng hình vuông
        int n = sc.nextInt();
        int[][] squares = new int[n][3];

        // Nhập từng hình vuông: x y l
        for (int i = 0; i < n; i++) {
            squares[i][0] = sc.nextInt(); // x (không dùng)
            squares[i][1] = sc.nextInt(); // y
            squares[i][2] = sc.nextInt(); // cạnh
        }

        double result = separateSquares(squares);

        // In kết quả
        System.out.printf("%.5f\n", result);

        sc.close();
    }

    /**
     * Hàm chính giải bài toán
     * 
     * @param squares mảng các hình vuông [x, y, l]
     * @return tọa độ y của đường thẳng chia đôi diện tích
     */
    public static double separateSquares(int[][] squares) {
        double maxY = 0; // Y cao nhất (đỉnh trên cùng)
        double minY = Double.MAX_VALUE; // Y thấp nhất (đáy dưới cùng)
        double totalArea = 0; // Tổng diện tích tất cả hình vuông

        // Duyệt các hình vuông để xác định khoảng tìm kiếm
        for (int i = 0; i < squares.length; i++) {
            int[] sq = squares[i];
            double y = sq[1];
            double len = sq[2];

            maxY = Math.max(maxY, y + len); // cập nhật đỉnh cao nhất
            minY = Math.min(minY, y); // cập nhật đáy thấp nhất
            totalArea += len * len; // cộng diện tích hình vuông
        }

        // Binary search trên trục Y
        double lo = minY;
        double hi = maxY;
        double precision = 1e-5; // độ chính xác yêu cầu

        while (hi - lo > precision) {
            double mid = lo + (hi - lo) / 2.0;

            double topArea = getTop(squares, mid);
            double bottomArea = totalArea - topArea;

            // Nếu diện tích phía trên nhỏ hơn hoặc bằng phía dưới
            // => đường cắt đang thấp, cần nâng lên
            if (topArea <= bottomArea) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return lo; // lo ~ hi khi kết thúc
    }

    /**
     * Tính diện tích phía TRÊN đường thẳng y = line
     */
    public static double getTop(int[][] squares, double line) {
        double area = 0.0;

        for (int[] square : squares) {
            double y = square[1];
            double len = square[2];

            // Square nằm hoàn toàn phía trên đường cắt
            if (y >= line) {
                area += len * len;
            }
            // Square bị cắt bởi đường
            else if (y + len > line) {
                double height = y + len - line;
                area += height * len;
            }
            // Square nằm hoàn toàn phía dưới => không cộng
        }

        return area;
    }
}

// ---

// ## 🎯 Mục tiêu bài toán

// Tìm **tọa độ y của một đường thẳng ngang** sao cho:

// > **Diện tích các hình vuông phía trên đường thẳng = phía dưới**

// ---

// ## 🧠 Ý tưởng chính của lời giải này

// 👉 **Binary Search trên trục Y (tọa độ đường cắt)**

// Thay vì quét sweep-line, ta:

// * Đoán một giá trị `y = mid`
// * Tính:

// * `topArea`: diện tích phía **trên** đường y
// * `bottomArea = totalArea - topArea`
// * So sánh hai bên để điều chỉnh `mid`

// ---

// ## 1️⃣ Chuẩn bị dữ liệu ban đầu

// ```java
// double maxY = 0;
// double minY = Integer.MAX_VALUE;
// double totalArea = 0;
// ```

// ### Ý nghĩa

// * `minY`: đáy thấp nhất của tất cả square
// * `maxY`: đỉnh cao nhất (`y + side`)
// * `totalArea`: tổng diện tích tất cả hình vuông

// 👉 **Khoảng tìm kiếm** của đường cắt là `[minY, maxY]`

// ---

// ## 2️⃣ Binary Search trên tọa độ Y

// ```java
// double lo = minY;
// double hi = maxY;
// double precision = Math.pow(10,-5);
// ```

// * Ta cần kết quả **float**, nên không thể binary search chính xác tuyệt đối
// * Dừng khi khoảng `[lo, hi]` nhỏ hơn `1e-5`

// ---

// ### Vòng lặp chính

// ```java
// while (lo < hi) {
// if (hi - lo < precision) break;

// double mid = lo + (hi - lo) / 2.0;
// double topArea = getTop(squares, mid);
// double bottomArea = totalArea - topArea;
// ```

// 👉 `mid` = đường cắt thử nghiệm

// ---

// ## 3️⃣ Hàm `getTop` – tính diện tích phía trên đường cắt

// ```java
// public double getTop(int[][] squares, double line) {
// ```

// ### Với mỗi square:

// #### 🔹 Case 1: Square nằm hoàn toàn trên đường cắt

// ```java
// if (y >= line) {
// area += width * width;
// }
// ```

// #### 🔹 Case 2: Square bị cắt bởi đường

// ```java
// else if (y + width >= line) {
// double actualHeight = y + width - line;
// area += actualHeight * width;
// }
// ```

// 📌 Nếu square nằm hoàn toàn dưới → không cộng gì

// ---

// ## 4️⃣ Điều kiện cập nhật Binary Search

// ```java
// if (topArea <= bottomArea) {
// hi = mid;
// } else {
// lo = mid;
// }
// ```

// ### Vì sao?

// * `topArea <= bottomArea`
// → đường cắt **đang quá thấp**
// → cần **đưa lên cao hơn** → giảm `hi`

// * `topArea > bottomArea`
// → đường cắt **quá cao**
// → hạ xuống → tăng `lo`

// 👉 Bản chất: **tìm điểm cân bằng diện tích**

// ---

// ## 5️⃣ Kết quả

// ```java
// return lo;
// ```

// Khi `[lo, hi]` đủ nhỏ → `lo` chính là tọa độ y cần tìm

// ---

// ## 📌 Đánh giá thuật toán

// ### ⏱ Độ phức tạp

// * `getTop`: **O(n)**
// * Binary Search ~ `log(range / precision)` ≈ 40–50 vòng
// * 👉 Tổng: **O(n log ε⁻¹)**

// ### ✔ Ưu điểm

// * Dễ hiểu
// * Dễ code
// * Không cần sweep-line phức tạp

// ### ❌ Nhược điểm

// * Chậm hơn sweep-line
// * Tính đi tính lại diện tích nhiều lần

// ---

// ## 🔁 So sánh nhanh 2 cách

// | Cách | Ý tưởng | Độ phức tạp |
// | ------------- | ---------------- | ----------- |
// | Binary Search | đoán y, tính lại | O(n log ε) |
// | Sweep Line | prefix + nội suy | O(n log n) |

// ---

// Nếu bạn muốn:

// * 🔄 chuyển code này sang **sweep-line Java**
// * ✍️ vẽ hình minh họa binary search
// * 🧠 so sánh chi tiết với lời giải Python trước

// 👉 cứ nói, mình hỗ trợ tiếp nhé 👍

// # ---

// # ## 1️⃣ Hiểu đúng đề bài (rất quan trọng)

// # * Cho **n hình vuông** dạng `[x, y, l]`

// # * `(x, y)` là **góc dưới bên trái**
// # * `l` là cạnh
// # * Các cạnh song song trục tọa độ
// # * Ta cần tìm **tọa độ y của 1 đường thẳng ngang** sao cho:

// # > **Diện tích các hình vuông phía trên đường thẳng = diện tích phía dưới**

// # ➡️ Trả về **tọa độ y đó (float)**

// # 📌 Lưu ý:

// # * **Không cần quan tâm đến trục X**
// # * Bài này là **chia diện tích**, không phải tách hình

// # ---

// # ## 2️⃣ Ý tưởng thuật toán (Sweep Line theo trục Y)

// # ### 🔹 Ý tưởng chính

// # * Ta **quét từ dưới lên trên theo trục Y**
// # * Tại mỗi khoảng `[y, y2)`:

// # * biết được **tổng chiều dài cạnh các square đang cắt ngang**
// # * diện tích tăng thêm = `chiều_dài * (y2 - y)`
// # * Khi **diện tích ≥ 1/2 tổng diện tích**, ta **nội suy** để tìm chính xác
// tọa độ y

// # ➡️ Đây là kỹ thuật **Difference Array + Sweep Line**

// # ---

// # ## 3️⃣ Giải thích từng biến quan trọng

// # | Biến | Ý nghĩa |
// # | ------------ | ---------------------------------------------- |
// # | `total_area` | Tổng diện tích tất cả hình vuông |
// # | `diff[y]` | Thay đổi “chiều dài cạnh đang hoạt động” tại y |
// # | `s` | Tổng chiều dài cạnh tại lát cắt hiện tại |
// # | `area` | Diện tích đã quét từ dưới lên |

// # ---

// # ## 4️⃣ Thuật toán chi tiết

// # ### Bước 1: Difference Array theo trục Y

// # * Mỗi square:

// # * Bắt đầu tại `y` → **+l**
// # * Kết thúc tại `y+l` → **-l**

// # 👉 Giống bài **line sweep + prefix sum**

// # ---

// # ### Bước 2: Quét các đoạn Y tăng dần

// # * Giữa hai mốc `y` và `y2`
// # * Diện tích tăng:

// # ```
// # area += s * (y2 - y)
// # ```

// # ---

// # ### Bước 3: Khi đạt 1/2 diện tích

// # * Nội suy tuyến tính:

// # ```
// # y_answer = y2 - (area*2 - total_area) / (2*s)
// # ```

// # ---

// # ## 5️⃣ Code có chú thích chi tiết

// # ```python
// from typing import List
// from collections import defaultdict
// from itertools import pairwise

// class Solution:
// def separateSquares(self, squares: List[List[int]]) -> float:
// total_area = 0 # Tổng diện tích tất cả hình vuông
// diff = defaultdict(int) # Difference array theo trục Y

// # Xây diff array
// for _, y, l in squares:
// total_area += l * l # Cộng diện tích hình vuông
// diff[y] += l # Square bắt đầu tại y
// diff[y + l] -= l # Square kết thúc tại y + l

// area = 0 # Diện tích đã quét từ dưới lên
// s = 0 # Tổng chiều dài cạnh đang "active"

// # Quét theo trục Y
// for y, y2 in pairwise(sorted(diff)):
// s += diff[y] # Cập nhật chiều dài tại mức y
// area += s * (y2 - y) # Diện tích lát cắt [y, y2)

// # Nếu đã đạt >= 1/2 tổng diện tích
// if area * 2 >= total_area:
// excess = area * 2 - total_area
// # Nội suy để tìm chính xác tọa độ y
// return y2 - excess / (2 * s)

// # ```

// # ---

// # ## 6️⃣ Bản chất toán học

// # * Đây là bài:

// # > **Tìm median theo diện tích trên trục Y**

// # * Giống:

// # * Chia bánh
// # * Cân diện tích
// # * Prefix sum + binary cut

// # ---

// # Nếu bạn muốn:

// # * ✍️ mình **vẽ hình minh họa sweep line**
// # * 🧠 giải thích lại theo kiểu **“dân thường”**
// # * 🔁 so sánh với **Separate Squares II**

// # 👉 nói mình biết nhé 👍

// # ---

// # ## 🧩 Separate Squares I – Giải thích đề bài

// # ### 📌 Mô tả

// # * Bạn được cho **2 hình vuông** trên mặt phẳng tọa độ.
// # * Mỗi hình vuông được mô tả bởi:

// # ```
// # [x, y, len]
// # ```

// # trong đó:

// # * `(x, y)` là **tọa độ góc dưới bên trái**
// # * `len` là **độ dài cạnh** của hình vuông
// # * Các cạnh **song song với trục Ox và Oy**.

// # ---

// # ### 🎯 Yêu cầu

// # Xác định xem **có tồn tại một đường thẳng song song với trục Ox hoặc Oy**
// # (**đường thẳng ngang hoặc dọc**) sao cho:

// # 👉 **hai hình vuông nằm hoàn toàn ở hai phía khác nhau của đường thẳng đó**
// # (không được cắt qua hình vuông nào).

// # Trả về:

// # * `true` nếu **tách được**
// # * `false` nếu **không tách được**

// # ---

// # ## 🧠 Cách hiểu đơn giản

// # Bạn chỉ cần tự hỏi:

// # > *Có vẽ được **1 đường thẳng dọc hoặc ngang** để ngăn cách hoàn toàn 2
// hình vuông không?*

// # ---

// # ## 🔍 Phân tích tư duy

// # ### 1️⃣ Xét khả năng tách theo **trục X** (đường thẳng **dọc**)

// # * Mỗi hình vuông chiếm một đoạn trên trục X:

// # * Square A:

// # ```
// # [x1, x1 + len1]
// # ```
// # * Square B:

// # ```
// # [x2, x2 + len2]
// # ```
// # * Nếu **hai đoạn này không chồng lên nhau**
// # ⇒ có thể vẽ **đường thẳng dọc** để tách.

// # 📌 Điều kiện:

// # ```
// # x1 + len1 < x2 hoặc x2 + len2 < x1
// # ```

// # ---

// # ### 2️⃣ Nếu không tách được theo trục X → xét **trục Y** (đường thẳng
// ngang)

// # * Tương tự, xét:

// # ```
// # [y1, y1 + len1]
// # [y2, y2 + len2]
// # ```
// # * Nếu **không chồng nhau**
// # ⇒ tách được bằng đường ngang.

// # ---

// # ### 3️⃣ Kết luận

// # * Nếu **tách được theo trục X hoặc trục Y** → `true`
// # * Nếu **cả hai trục đều chồng nhau** → `false`

// # ---

// # ## 🧠 Bản chất bài toán

// # 👉 **Không phải hình học phức tạp**
// # 👉 Chỉ là **kiểm tra khoảng (interval overlap)** trên **trục X và Y**

// # ---

// # Nếu bạn muốn:

// # * mình **vẽ hình minh họa từng case**
// # * hoặc **giải thích bằng test ví dụ LeetCode**
// # * hoặc **so sánh với Separate Squares II**

// # 👉 cứ nói nhé 👍
