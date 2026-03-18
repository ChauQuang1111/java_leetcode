// # Bài **“Count Submatrices with Top-Left Element and Sum Less Than k”(18/03/2026)

import java.util.*;

public class b220 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // nhập số hàng và số cột
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n][m];

        // nhập ma trận
        int i = 0;
        while (i < n) {
            int j = 0;
            while (j < m) {
                grid[i][j] = sc.nextInt();
                j++;
            }
            i++;
        }

        // nhập k
        int k = sc.nextInt();

        // gọi hàm và in kết quả
        int result = countSubmatrices(grid, k);
        System.out.println(result);

        sc.close();
    }

    public static int countSubmatrices(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        // cols[j]: lưu tổng các phần tử từ hàng 0 → i tại cột j
        int[] cols = new int[m];

        int res = 0; // kết quả: số submatrix thỏa mãn

        // duyệt từng hàng
        for (int i = 0; i < n; i++) {

            int rows = 0;
            // rows: tổng của submatrix từ (0,0) → (i,j)

            // duyệt từng cột
            for (int j = 0; j < m; j++) {

                // cộng dồn theo cột (prefix sum theo cột)
                cols[j] += grid[i][j];

                // cộng dồn theo hàng để tạo prefix sum 2D
                rows += cols[j];

                // nếu tổng ≤ k thì đếm
                if (rows <= k) {
                    res++;
                }
            }
        }

        return res;
    }

}

// # Mình sẽ giải thích **từng dòng + tư duy thuật toán**, rồi viết lại code có
// **chú thích rõ ràng** cho bạn 👇

// Code Java này thực chất là một cách viết **tối ưu của 2D prefix sum**, nhưng
// viết theo kiểu **tính dần (on-the-fly)** thay vì tạo ma trận prefix riêng.

// Mình giải thích theo đúng tư duy để bạn “ngộ” luôn 👇

// ---

// # 🧠 Ý tưởng chính

// Ta vẫn đang làm đúng bài:

// 👉 Đếm số submatrix:

// * **bắt đầu từ (0,0)**
// * có tổng ≤ k

// ---

// # ⚙️ Ý nghĩa các biến

// ```java
// int[] cols = new int[m];
// ```

// 👉 `cols[j]` = tổng của **cột j từ hàng 0 → i**

// ---

// ```java
// int rows = 0;
// ```

// 👉 `rows` = tổng của **hình chữ nhật từ (0,0) → (i,j)**

// ---

// # 🔄 Cách thuật toán chạy

// ## 👉 Vòng ngoài: duyệt từng hàng

// ```java
// for (int i = 0; i < n; i++)
// ```

// ---

// ## 👉 Vòng trong: duyệt từng cột

// ```java
// for (int j = 0; j < m; j++)
// ```

// ---

// ## 🔹 Bước 1: cộng dồn theo cột

// ```java
// cols[j] += grid[i][j];
// ```

// 👉 Sau dòng này:

// ```text
// cols[j] = tổng từ (0,j) → (i,j)
// ```

// ---

// ## 🔹 Bước 2: cộng dồn theo hàng

// ```java
// rows += cols[j];
// ```

// 👉 Lúc này:

// ```text
// rows = tổng từ (0,0) → (i,j)
// ```

// 🔥 Đây chính là **2D prefix sum tại (i,j)**

// ---

// ## 🔹 Bước 3: kiểm tra điều kiện

// ```java
// if (rows <= k) {
// res++;
// }
// ```

// 👉 Nếu tổng submatrix ≤ k → đếm

// ---

// # 📊 Minh họa cụ thể

// ```text
// grid =
// 1 2
// 3 4
// k = 10
// ```

// ---

// ## 👉 i = 0

// ### j = 0

// * cols[0] = 1
// * rows = 1 ✅

// ### j = 1

// * cols[1] = 2
// * rows = 1 + 2 = 3 ✅

// ---

// ## 👉 i = 1

// ### j = 0

// * cols[0] = 1 + 3 = 4
// * rows = 4 ✅

// ### j = 1

// * cols[1] = 2 + 4 = 6
// * rows = 4 + 6 = 10 ✅

// ---

// 👉 Mỗi lần `rows` chính là:

// ```text
// sum của submatrix từ (0,0) → (i,j)
// ```

// ---

// # 🔥 Insight quan trọng

// 👉 Code này KHÔNG tạo prefix sum 2D riêng
// 👉 Nhưng đang **tính nó dần dần**

// Cụ thể:

// | Thành phần | Ý nghĩa |
// | ---------- | ------------------------------ |
// | `cols[j]` | prefix sum theo cột |
// | `rows` | prefix sum theo hàng (từ cols) |
// | 👉 Kết quả | prefix sum 2D |

// ---

// # 🧾 Code có chú thích

// ```java
// class Solution {

// public int countSubmatrices(int[][] grid, int k) {
// int n = grid.length;
// int m = grid[0].length;

// // cols[j]: lưu tổng từ hàng 0 đến i tại cột j
// int[] cols = new int[m];

// int res = 0;

// // duyệt từng hàng
// for (int i = 0; i < n; i++) {

// // rows: tổng từ (0,0) → (i,j)
// int rows = 0;

// // duyệt từng cột
// for (int j = 0; j < m; j++) {

// // cộng dồn theo cột
// cols[j] += grid[i][j];

// // cộng dồn theo hàng → tạo prefix sum 2D
// rows += cols[j];

// // nếu tổng submatrix ≤ k thì đếm
// if (rows <= k) {
// res++;
// }
// }
// }

// return res;
// }
// }
// ```

// ---

// # 🚀 Tóm lại 1 câu

// 👉 Code này đang:

// > **Xây dựng prefix sum 2D ngay trong lúc duyệt mà không cần mảng phụ**

// ---

// # ⚡ So sánh với Python code trước

// | Python | Java |
// | ----------------------- | ------------------ |
// | Dùng `accumulate` 2 lần | Dùng `cols + rows` |
// | Tạo prefix sum trước | Tính dần |
// | Functional | Imperative |

// 👉 Nhưng bản chất **giống nhau 100%**

// ---

// Nếu bạn muốn, mình có thể:

// * Nâng cấp bài này lên dạng **không fix (0,0)** (hard hơn nhiều 🔥)
// * Hoặc biến thành bài **LeetCode dạng interview hay gặp**

// Chỉ cần nói 👍

// # ---

// # # 🧠 Ý tưởng chính của thuật toán

// # Thay vì:

// # * duyệt từng submatrix rồi tính tổng (rất chậm ❌)

// # 👉 Ta dùng **2D Prefix Sum** để:

// # * biến mỗi ô `(i, j)` thành **tổng của submatrix từ (0,0) → (i,j)**

// # Sau đó:

// # * chỉ cần đếm bao nhiêu ô có giá trị ≤ k

// # ---

// # ⚙️ Phân tích code từng bước

// ## 🔹 Dòng 1

// # ```python
// # grid = map(accumulate, grid)
// # ```

// # 👉 `accumulate` (từ `itertools`) = tính **prefix sum theo hàng**

// # Ví dụ:

// # ```
// # [1, 2, 3] → [1, 3, 6]
// # ```

// # Áp dụng cho mỗi hàng:

// # ```
// # grid =
// # 1 2
// # 3 4

// # → sau bước này:

// # 1 3
// # 3 7
// # ```

// # ---

// # ## 🔹 Dòng 2

// # ```python
// # grid = map(accumulate, zip(*grid))
// # # ```

// # # 👉 `zip(*grid)` = transpose (đổi hàng ↔ cột)

// # # Ví dụ:

// # # ```
// # # 1 3
// # # 3 7

// # # → zip(*grid):

// # # (1,3)
// # # (3,7)
// # ```

// # 👉 Sau đó lại `accumulate` → prefix sum theo cột

// # ```
// # (1,3) → (1,4)
// # (3,7) → (3,10)
// # ```

// # ---

// # 👉 Khi transpose lại, ta có:

// # ```
// # 1 3
// # 4 10
// # ```

// # 🔥 Đây chính là:
// # 👉 **prefix sum 2D**

// # Mỗi ô = tổng từ (0,0) → (i,j)

// # ---

// # ## 🔹 Dòng 3

// # ```python
// # return sum(val <= k for row in grid for val in row)

// # 👉 Duyệt tất cả các ô:

// # * nếu `val <= k` → tính 1

// # 👉 Vì:

// # * mỗi ô đại diện cho **1 submatrix từ (0,0)**

// # ---

// # # 🎯 Tổng kết thuật toán

// # 1. Prefix sum theo hàng
// # 2. Prefix sum theo cột
// # 3. Mỗi ô = tổng submatrix (0,0 → i,j)
// # 4. Đếm số ô ≤ k

// # ⏱️ Time: `O(m * n)`
// # ⚡ Rất tối ưu

// # ---

// # 🧾 Code có chú thích đầy đủ

// from itertools import accumulate
// from typing import List

// class Solution:
// def countSubmatrices(self, grid: List[List[int]], k: int) -> int:

// # Bước 1: Tính prefix sum theo từng hàng
// # Ví dụ: [1,2,3] -> [1,3,6]
// grid = list(map(accumulate, grid))

// # Bước 2: Transpose ma trận rồi tính prefix sum theo cột
// # zip(*grid) dùng để chuyển hàng thành cột
// # Sau đó accumulate sẽ cộng dồn theo cột
// grid = list(map(accumulate, zip(*grid)))

// # Bước 3: Đếm số phần tử <= k
// # Mỗi phần tử (i,j) bây giờ là tổng của submatrix từ (0,0) đến (i,j)
// return sum(
// val <= k # nếu tổng <= k thì tính 1
// for row in grid
// for val in row
// )
// # ```

// # ---

// # # 🔥 Insight quan trọng (cực đáng nhớ)

// # 👉 Sau 2 lần accumulate:

// # ```
// # grid[i][j] = sum của hình chữ nhật từ (0,0) → (i,j)
// # ```

// # 👉 Vì đề **fix top-left = (0,0)**
// # → chỉ cần prefix sum là giải được ngay

// # ---

// # # 🚀 Nếu bạn muốn nâng level

// # Mình có thể giúp bạn:

// # * Viết lại cách này bằng Java (chuẩn interview 💼)
// # * Hoặc mở rộng sang bài khó hơn: submatrix bất kỳ (không fix (0,0))

// # Chỉ cần nói 👍

// # ---

// # ## 🧠 Ý nghĩa đề bài

// # Bạn được cho:

// # * Một ma trận số nguyên `grid` kích thước `m x n`
// # * Một số nguyên `k`

// # Nhiệm vụ:
// # 👉 Đếm **bao nhiêu ma trận con (submatrix)** thỏa mãn:

// # 1. **Bắt đầu từ góc trên bên trái (top-left)** của ma trận (tức là luôn bắt
// đầu tại `(0,0)`)
// # 2. Tổng các phần tử trong ma trận con đó **< k**

// # ---

// # ## 📦 Submatrix là gì?

// # Submatrix = một phần hình chữ nhật con của ma trận.

// # Ví dụ:

// # ```
// # grid =
// # 1 2
// # 3 4
// # ```

// # Các submatrix bắt đầu từ (0,0):

// # 1. Chỉ lấy (0,0):

// # ```
// # 1
// # ```

// # 2. Lấy hàng đầu:

// # ```
// # 1 2
// # ```

// # 3. Lấy cột đầu:

// # ```
// # 1
// # 3
// # ```

// # 4. Lấy toàn bộ:

// # ```
// # 1 2
// # 3 4
// # ```

// # ---

// # ## 🎯 Điều kiện quan trọng

// # 👉 **Chỉ xét các submatrix có góc trên trái cố định tại (0,0)**
// # → Không xét submatrix bắt đầu từ chỗ khác

// # 👉 Tính tổng từng submatrix rồi:

// # * Nếu tổng < k → tính vào kết quả

// # ---

// # ## 🔍 Ví dụ

// # ```
// # grid =
// # 1 1
// # 1 1
// # k = 3
// # ```

// # Các submatrix từ (0,0):

// # 1. `[1]` → sum = 1 < 3 ✅
// # 2. `[1 1]` → sum = 2 < 3 ✅
// # 3. `[1; 1]` → sum = 2 < 3 ✅
// # 4. `[1 1; 1 1]` → sum = 4 ≥ 3 ❌

// # 👉 Kết quả: **3**

// # ---

// # ## 💡 Tóm lại cách hiểu

// # * Fix điểm bắt đầu là `(0,0)`
// # * Mở rộng xuống dưới và sang phải
// # * Với mỗi hình chữ nhật tạo được:

// # * Tính tổng
// # * Nếu < k → đếm

// # ---

// # ## 🚀 Hint cách làm (ngắn gọn)

// # * Dùng **prefix sum 2D** để tính tổng nhanh
// # * Duyệt tất cả `(i, j)` là góc dưới phải
// # * Tính tổng từ `(0,0)` → `(i,j)`

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Giải chi tiết bằng code Java (đúng style bạn đang học)
// # * Hoặc vẽ hình giúp bạn hiểu trực quan hơn 👍
