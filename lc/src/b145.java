// // # **Maximal Rectangle (11/01/2026)
// // # Dưới đây là **giải thích thuật toán + chú thích chi tiết trực tiếp vào code** cho bài
// // # **85. Maximal Rectangle** (đúng theo code bạn đưa).
// // Dưới đây là **giải thích thuật toán Maximal Rectangle (không dùng stack)** cho **đoạn code Java bạn đưa**, theo cách **dễ hiểu – đúng bản chất – rất hay dùng khi phỏng vấn**.
// Dưới đây là**phiên bản hoàn chỉnh bằng Java**cho bài**85. Maximal Rectangle**,có:

// ✅`main`dùng**Scanner**✅**Chú thích chi tiết từng đoạn code**✅Giữ nguyên**thuật toán boundary(left–right–height)**như bạn đang học

// ---

// ##1. Code đầy đủ(có`main`,Scanner,chú thích)

// ```java

import java.util.*;

public class b145 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số hàng và số cột
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine(); // bỏ dòng thừa

        char[][] matrix = new char[m][n];

        // Nhập ma trận (dạng 10101 hoặc 1 0 1 0 1 đều được)
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine().replace(" ", "");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int result = maximalRectangle(matrix);

        // In kết quả
        System.out.println(result);
    }

    public static int maximalRectangle(char[][] matrix) {
        // Kiểm tra input rỗng
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length; // số hàng
        int n = matrix[0].length; // số cột

        // heights[j] = chiều cao histogram tại cột j
        int[] heights = new int[n];

        // leftBoundaries[j] = biên trái xa nhất của hình chữ nhật tại cột j
        int[] leftBoundaries = new int[n];

        // rightBoundaries[j] = biên phải xa nhất của hình chữ nhật tại cột j
        int[] rightBoundaries = new int[n];

        // ban đầu, biên phải tối đa là n
        Arrays.fill(rightBoundaries, n);

        int maxRectangle = 0;

        // Duyệt từng hàng
        for (int i = 0; i < m; i++) {
            int left = 0; // biên trái hiện tại
            int right = n; // biên phải hiện tại

            // Cập nhật heights và leftBoundaries
            updateHeightsAndLeftBoundaries(matrix[i], heights, leftBoundaries, left);

            // Cập nhật rightBoundaries
            updateRightBoundaries(matrix[i], rightBoundaries, right);

            // Tính diện tích lớn nhất tại hàng hiện tại
            maxRectangle = calculateMaxRectangle(
                    heights, leftBoundaries, rightBoundaries, maxRectangle);
        }

        return maxRectangle;
    }

    // ================== CẬP NHẬT HEIGHTS + BIÊN TRÁI ==================
    public static void updateHeightsAndLeftBoundaries(
            char[] row, int[] heights, int[] leftBoundaries, int left) {

        for (int j = 0; j < heights.length; j++) {
            if (row[j] == '1') {
                // tăng chiều cao histogram
                heights[j]++;

                // biên trái không được vượt qua cột 0 gần nhất
                leftBoundaries[j] = Math.max(leftBoundaries[j], left);
            } else {
                // gặp 0 thì reset
                heights[j] = 0;
                leftBoundaries[j] = 0;

                // cập nhật biên trái mới
                left = j + 1;
            }
        }
    }

    // ================== CẬP NHẬT BIÊN PHẢI ==================
    public static void updateRightBoundaries(
            char[] row, int[] rightBoundaries, int right) {

        // duyệt từ phải sang trái
        for (int j = rightBoundaries.length - 1; j >= 0; j--) {
            if (row[j] == '1') {
                // biên phải không được vượt qua cột 0 bên phải
                rightBoundaries[j] = Math.min(rightBoundaries[j], right);
            } else {
                // gặp 0 thì reset
                rightBoundaries[j] = right;
                right = j;
            }
        }
    }

    // ================== TÍNH DIỆN TÍCH LỚN NHẤT ==================
    public static int calculateMaxRectangle(
            int[] heights, int[] leftBoundaries,
            int[] rightBoundaries, int maxRectangle) {

        for (int j = 0; j < heights.length; j++) {
            int width = rightBoundaries[j] - leftBoundaries[j];
            int area = heights[j] * width;
            maxRectangle = Math.max(maxRectangle, area);
        }
        return maxRectangle;
    }

}

// ##2.

// Cách nhập

// mẫu (test nhanh)

// ### Input

// ```
// 4 5
// 10100
// 10111
// 11111
// 10010
// ```

// ### Output

// ```
// 6
// ```

// ---

// ## 3. Cách nhớ

// thuật toán (rất quan trọng khi thi/phỏng vấn)

// > **Mỗi hàng = đáy histogram**
// > **heights → chiều cao**
// > **left / right → biên mở rộng**
// > **area = height × (right − left)**

// ---

// Nếu bạn muốn:

// * So sánh **boundary vs stack** bằng bảng
// * Vẽ hình minh họa từng bước cho 1 test
// * Viết lại code **ngắn gọn hơn để thi ICPC / OJ**

// 👉 cứ nói, mình làm tiếp cho bạn 👌

// ---

// # 1. Ý tưởng cốt lõi của thuật toán này

// Thuật toán này **không dùng stack**, mà dùng 3 mảng:

// ```
// heights[j] : chiều cao histogram tại cột j
// leftBoundaries[j]: biên trái xa nhất có thể mở rộng
// rightBoundaries[j]: biên phải xa nhất có thể mở rộng
// ```

// 👉 Với mỗi hàng, ta coi nó là **đáy của histogram**
// 👉 Mỗi cột `j` tạo ra **một hình chữ nhật có chiều cao = heights[j]**

// Diện tích tại cột `j`:

// ```
// area = heights[j] × (rightBoundaries[j] - leftBoundaries[j])
// ```

// ---

// # 2. Ý nghĩa 3 mảng (rất quan trọng)

// Giả sử tại cột `j`:

// ```
// | 1 1 1 1 1 |
// j
// ```

// * `heights[j]`
// → số lượng `'1'` **liên tiếp theo chiều dọc**

// * `leftBoundaries[j]`
// → cột **trái xa nhất** mà hình chữ nhật có thể kéo dài

// * `rightBoundaries[j]`
// → cột **phải xa nhất** mà hình chữ nhật có thể kéo dài

// ---

// # 3. Luồng xử lý tổng quát

// ```text
// Duyệt từng hàng
// ├─ Cập nhật heights + leftBoundaries (từ trái → phải)
// ├─ Cập nhật rightBoundaries (từ phải → trái)
// └─ Tính diện tích lớn nhất tại hàng hiện tại
// ```

// ---

// # 4. Giải thích từng phần code

// ---

// ## 4.1 Hàm `maximalRectangle`

// ```java
// int[] heights = new int[n];
// int[] leftBoundaries = new int[n];
// int[] rightBoundaries = new int[n];
// Arrays.fill(rightBoundaries, n);
// ```

// ### Ý nghĩa:

// * Ban đầu:

// * `heights = 0`
// * `leftBoundaries = 0`
// * `rightBoundaries = n` (biên phải tối đa)

// ---

// ```java
// for (int i = 0; i < m; i++) {
// int left = 0;
// int right = n;
// ```

// * `left`: vị trí cột trái hợp lệ hiện tại
// * `right`: vị trí cột phải hợp lệ hiện tại

// ---

// ## 4.2 Cập nhật `heights` và `leftBoundaries`

// ```java
// private void updateHeightsAndLeftBoundaries(
// char[] row, int[] heights, int[] leftBoundaries, int left) {

// for (int j = 0; j < heights.length; j++) {
// if (row[j] == '1') {
// heights[j]++;
// leftBoundaries[j] = Math.max(leftBoundaries[j], left);
// } else {
// heights[j] = 0;
// leftBoundaries[j] = 0;
// left = j + 1;
// }
// }
// }
// ```

// ### Giải thích:

// #### Nếu gặp `'1'`

// * Chiều cao tăng:

// ```
// heights[j]++
// ```
// * Biên trái:

// ```
// leftBoundaries[j] = max(leftBoundaries[j], left)
// ```

// → đảm bảo không vượt qua cột `'0'` gần nhất bên trái

// #### Nếu gặp `'0'`

// * Reset histogram:

// ```
// heights[j] = 0
// leftBoundaries[j] = 0
// ```
// * Cập nhật biên trái mới:

// ```
// left = j + 1
// ```

// ---

// ## 4.3 Cập nhật `rightBoundaries`

// ```java
// private void updateRightBoundaries(
// char[] row, int[] rightBoundaries, int right) {

// for (int j = rightBoundaries.length - 1; j >= 0; j--) {
// if (row[j] == '1') {
// rightBoundaries[j] = Math.min(rightBoundaries[j], right);
// } else {
// rightBoundaries[j] = right;
// right = j;
// }
// }
// }
// ```

// ### Giải thích:

// * Duyệt **từ phải sang trái**
// * Nếu gặp `'1'`

// ```
// rightBoundaries[j] = min(rightBoundaries[j], right)
// ```
// * Nếu gặp `'0'`

// ```
// rightBoundaries[j] = right
// right = j
// ```

// 👉 Đảm bảo hình chữ nhật **không vượt qua cột 0 bên phải**

// ---

// ## 4.4 Tính diện tích lớn nhất

// ```java
// private int calculateMaxRectangle(
// int[] heights, int[] leftBoundaries,
// int[] rightBoundaries, int maxRectangle) {

// for (int j = 0; j < heights.length; j++) {
// int width = rightBoundaries[j] - leftBoundaries[j];
// int area = heights[j] * width;
// maxRectangle = Math.max(maxRectangle, area);
// }
// return maxRectangle;
// }
// ```

// ### Công thức:

// ```
// area = height × width
// width = rightBoundaries[j] - leftBoundaries[j]
// ```

// ---

// # 5. Ví dụ nhanh

// Matrix:

// ```
// 1 1 0
// 1 1 1
// ```

// Sau hàng 1:

// ```
// heights = [2,2,1]
// leftBoundaries = [0,0,2]
// rightBoundaries= [2,2,3]
// ```

// Diện tích tại j=0:

// ```
// 2 × (2 - 0) = 4
// ```

// ---

// # 6. Độ phức tạp

// | Thành phần | Độ phức tạp |
// | ---------- | ----------- |
// | Time | O(m × n) |
// | Space | O(n) |

// 👉 Nhanh, không dùng stack, rất **được ưa chuộng trong phỏng vấn**

// ---

// # 7. So sánh nhanh

// | Cách | Đặc điểm |
// | -------- | --------------------------------- |
// | Stack | Dễ liên hệ Histogram |
// | Boundary | Code dài hơn nhưng **rất tối ưu** |

// ---

// Nếu bạn muốn:

// * Mô phỏng **từng hàng bằng bảng**
// * So sánh 2 cách **stack vs boundary**
// * Viết lại code kèm comment tiếng Việt gọn gàng

// 👉 cứ nói nhé, mình làm tiếp cho bạn 👌

// # ---

// # ## 1. Ý tưởng tổng quát (rất quan trọng)

// # Bài **Maximal Rectangle** được giải bằng cách **chuyển về bài con**:

// # > 👉 **Largest Rectangle in Histogram**

// # ### Cách làm:

// # * Duyệt **từng hàng** của ma trận
// # * Với mỗi cột `j`, ta xây dựng mảng `hist[j]`:

// # * `hist[j]` = số lượng `'1'` **liên tiếp theo chiều dọc** tính tới hàng
// hiện tại
// # * Mỗi hàng → coi `hist` như **một histogram**
// # * Tính **diện tích hình chữ nhật lớn nhất trong histogram**
// # * Lấy max qua tất cả các hàng

// # ---

// # ## 2. Minh họa nhanh

// # Matrix:

// # ```
// # 1 0 1
// # 1 1 1
// # ```

// # Duyệt từng hàng:

// # * Hàng 0 → hist = `[1,0,1]`
// # * Hàng 1 → hist = `[2,1,2]`

// # 👉 Với mỗi `hist`, ta giải bài **Largest Rectangle in Histogram**

// # ---

// # ## 3. Giải thích hàm `area()` (Histogram)

// # ```python
// from typing import List
// def area(self, heights: List[int]) -> int:
// stack = [] # stack lưu chỉ số cột, đảm bảo heights tăng dần
// maxArea = 0
// n = len(heights)

// # duyệt thêm 1 bước (i == n) để "xả stack"
// for i in range(n + 1):
// # khi i == n, ta coi chiều cao = 0 để ép tính hết diện tích
// h = 0 if i == n else heights[i]

// # nếu chiều cao hiện tại nhỏ hơn đỉnh stack
// while stack and h < heights[stack[-1]]:
// height = heights[stack.pop()] # chiều cao cột bị pop

// # nếu stack rỗng => hình chữ nhật kéo từ 0 → i-1
// if not stack:
// width = i
// else:
// # chiều rộng = khoảng cách giữa 2 cột nhỏ hơn gần nhất
// width = i - stack[-1] - 1

// maxArea = max(maxArea, height * width)

// stack.append(i)

// return maxArea
// # ```

// # ### Nguyên lý stack:

// # * Stack **luôn tăng dần theo chiều cao**
// # * Khi gặp cột thấp hơn → ta tính diện tích với:

// # * `height` = cột vừa bị pop
// # * `width` = khoảng mà cột đó có thể mở rộng

// # ---

// # ## 4. Giải thích hàm `maximalRectangle()`

// # ```python
// # def maximalRectangle(self, matrix: List[List[str]]) -> int:
// # if not matrix:
// # return 0

// # m, n = len(matrix), len(matrix[0])
// # hist = [0] * n # histogram chiều cao cho mỗi cột
// # ans = 0

// # # duyệt từng hàng
// # for i in range(m):
// # for j in range(n):
// # if matrix[i][j] == '1':
// # hist[j] += 1 # tăng chiều cao nếu là '1'
// # else:
// # hist[j] = 0 # reset nếu gặp '0'

// # # với mỗi hàng, tính histogram lớn nhất
// # ans = max(ans, self.area(hist))

// # return ans
// # ```

// # ### Ý nghĩa `hist`:

// # * `hist[j]` = số `'1'` liên tiếp **từ trên xuống** tại cột `j`
// # * Mỗi hàng → một histogram mới

// # ---

// # ## 5. Độ phức tạp

// # * **Time:** `O(m × n)`

// # * Mỗi phần tử vào stack đúng **1 lần**
// # * **Space:** `O(n)` cho histogram + stack

// # ---

// # ## 6. Tóm tắt 1 câu để nhớ bài

// # > **Mỗi hàng → histogram → Largest Rectangle in Histogram → lấy max**

// # ---

// # Nếu bạn muốn:

// # * Mô phỏng stack **từng bước bằng hình**
// # * Giải thích riêng **vì sao width = i - stack[-1] - 1**
// # * Viết lại code theo style **dễ học cho phỏng vấn**

// # 👉 nói mình biết nhé 👍

// # ---

// # ### 1. Mô tả bài toán

// # Bạn được cho một **ma trận nhị phân** (chỉ gồm ký tự `'0'` và `'1'`), kích
// thước `m x n`.

// # **Nhiệm vụ:**
// # 👉 Tìm **diện tích hình chữ nhật lớn nhất** chỉ gồm toàn `'1'` trong ma
// trận.

// # * Hình chữ nhật phải **liền kề**, các ô nằm cạnh nhau theo hàng – cột
// # * Không được xoay (chỉ song song với trục)

// # ---

// # ### 2. Ví dụ minh họa

// # Ví dụ ma trận:

// # ```
// # [
// # ["1","0","1","0","0"],
// # ["1","0","1","1","1"],
// # ["1","1","1","1","1"],
// # ["1","0","0","1","0"]
// # ]
// # ```

// # Một hình chữ nhật lớn nhất toàn `'1'` có dạng:

// # ```
// # 1 1 1
// # 1 1 1
// # ```

// # * Rộng = 3
// # * Cao = 2
// # ➡️ **Diện tích = 3 × 2 = 6**

// # **Kết quả trả về: `6`**

// # ---

// # ### 3. Input – Output

// # * **Input:**

// # * `matrix`: mảng 2 chiều các ký tự `'0'` và `'1'`
// # * **Output:**

// # * Một số nguyên: **diện tích lớn nhất** của hình chữ nhật toàn `'1'`

// # ---

// # ### 4. Những hiểu nhầm thường gặp

// # ❌ Không phải tìm hình vuông
// # ❌ Không phải đếm số lượng `'1'`
// # ❌ Không được lấy các ô `'1'` rời rạc

// # ✔️ Phải là **hình chữ nhật liên tục**

// # ---

// # ### 5. Ý tưởng cốt lõi (chưa đi vào code)

// # Cách nghĩ phổ biến khi giải bài này:

// # * Duyệt từng **hàng**
// # * Coi mỗi hàng như **đáy của một histogram**
// # * Với mỗi cột, đếm xem từ hàng hiện tại **liên tiếp bao nhiêu số 1 ở trên**
// # * Sau đó áp dụng bài toán con:
// # 👉 **Largest Rectangle in Histogram**

// # (Đây là lý do bài này được xếp mức **Hard**)

// # ---

// # Nếu bạn muốn:

// # * Giải thích **ý tưởng chi tiết từng bước**
// # * So sánh với bài **Largest Rectangle in Histogram**
// # * Hoặc **code Java / C++ / Python** kèm chú thích

// # 👉 cứ nói mình biết nhé 👍
