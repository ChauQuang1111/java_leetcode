// Bài **“Count Submatrices With Equal Frequency of X and Y”**(19/03/2026)

import java.util.*;

public class b221 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int m = sc.nextInt();
        int n = sc.nextInt();

        char[][] grid = new char[m][n];

        // Nhập ma trận
        // Ví dụ input:
        // 2 2
        // X Y
        // Y X
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }

        int result = numberOfSubmatrices(grid);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static int numberOfSubmatrices(char[][] grid) {

        int m = grid.length; // số hàng
        int n = grid[0].length; // số cột

        // Mảng lưu số lượng X và Y theo từng cột (từ hàng 0 -> i)
        int[] colX = new int[n];
        int[] colY = new int[n];

        int ans = 0; // kết quả

        // Duyệt từng hàng (coi như là hàng kết thúc của submatrix)
        for (int i = 0; i < m; i++) {

            // Cập nhật số lượng X và Y theo từng cột
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 'X')
                    colX[j]++;
                if (grid[i][j] == 'Y')
                    colY[j]++;
            }

            int x = 0, y = 0;

            // Duyệt từ trái sang phải để tạo submatrix từ (0,0) -> (i,j)
            for (int j = 0; j < n; j++) {

                x += colX[j]; // tổng X từ (0,0) -> (i,j)
                y += colY[j]; // tổng Y từ (0,0) -> (i,j)

                // Nếu số X = số Y và có ít nhất 1 ký tự
                if (x == y && x > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}

// Mình sẽ giải thích **rất rõ tư duy đằng sau đoạn code của bạn**, vì code này
// không phải brute force mà là **prefix sum theo cột + quét từng hàng**.

// ---

// ## 🔥 **Ý tưởng chính của thuật toán**

// Thay vì xét mọi submatrix (rất chậm), bạn đang:

// 👉 **Cố định hàng dưới (row i)**
// 👉 Và xét các submatrix có **điểm bắt đầu từ hàng 0 đến i**

// ---

// ## 🧱 **Ý nghĩa các biến**

// ```java
// int[] colX = new int[n];
// int[] colY = new int[n];
// ```

// 👉 Với mỗi cột `j`:

// * `colX[j]`: số lượng `'X'` từ hàng `0 → i`
// * `colY[j]`: số lượng `'Y'` từ hàng `0 → i`

// ⟹ Đây là **prefix sum theo cột**

// ---

// ## 🔄 **Vòng lặp chính**

// ```java
// for(int i = 0; i < m; i++)
// ```

// 👉 Mỗi lần lặp là:

// * Xét các submatrix **kết thúc tại hàng i**

// ---

// ## 📥 **Cập nhật prefix theo cột**

// ```java
// for(int j = 0; j < n; j++) {
// if(grid[i][j] == 'X') colX[j]++;
// if(grid[i][j] == 'Y') colY[j]++;
// }
// ```

// 👉 Sau bước này:

// * Bạn đã biết trong mỗi cột có bao nhiêu X/Y từ hàng 0 → i

// ---

// ## 📊 **Biến bài toán 2D → 1D**

// ```java
// int x = 0, y = 0;
// for(int j = 0; j < n; j++)
// ```

// 👉 Bạn đang:

// * Quét từ trái → phải
// * Cộng dồn theo cột

// ---

// ### 🔢 Cộng dồn:

// ```java
// x += colX[j];
// y += colY[j];
// ```

// 👉 Nghĩa là:

// * `x`: tổng số X trong rectangle từ:

// ```
// (0,0) → (i,j)
// ```
// * `y`: tổng số Y tương tự

// ---

// ## 🎯 **Điều kiện quan trọng**

// ```java
// if(x == y && x > 0) ans++;
// ```

// 👉 Nghĩa là:

// * Số X = số Y
// * Và có ít nhất 1 ký tự (tránh trường hợp toàn rỗng)

// ⟹ Bạn đã tìm được **1 submatrix hợp lệ**

// ---

// ## 📦 **Submatrix bạn đang đếm là gì?**

// ⚠️ RẤT QUAN TRỌNG:

// Code này **chỉ đếm các submatrix dạng:**

// ```
// (0,0) → (i,j)
// ```

// 👉 Tức là:

// * Bắt đầu từ góc trên trái (0,0)
// * Kết thúc tại (i,j)

// ⟹ Không phải tất cả submatrix!

// ---

// ## ❗ **Điểm hạn chế của code**

// Code này:

// * ❌ Không xét mọi submatrix
// * ❌ Chỉ xét submatrix có top-left = (0,0)

// ---

// ## 🧠 **Tư duy sâu hơn**

// Bạn đang làm:

// ### Bước 1:

// * Gom dữ liệu theo cột → giảm 2D thành 1D

// ### Bước 2:

// * Duyệt prefix từ trái qua
// * So sánh:

// ```
// tổng X == tổng Y
// ```

// ---

// ## 💡 **Nếu muốn full bài (tất cả submatrix)**

// Bạn cần:

// 1. Fix **2 hàng (top, bottom)**
// 2. Biến mỗi cột thành:

// ```
// +1 (X), -1 (Y)
// ```
// 3. Dùng HashMap đếm số subarray sum = 0

// ⟹ Độ phức tạp: **O(m² * n)**

// ---

// ## 🧩 **Tóm tắt ngắn gọn**

// Code của bạn:

// ✅ Ý tưởng:

// * Prefix sum theo cột
// * Quét ngang để đếm

// ❗ Nhưng:

// * Chỉ đếm submatrix từ (0,0)

// ---

// ## 🚀 Nếu bạn muốn

// Mình có thể:

// * Sửa code này thành **đúng 100% (đếm mọi submatrix)**
// * Viết lại theo style bạn thích (Scanner, while, Java)

// Chỉ cần nói: *“viết full solution”* 👍

// ## 🧠 **Đề bài nói gì?**

// Bạn được cho một **ma trận 2D** gồm các ký tự (thường là `'X'`, `'Y'`, hoặc
// có thể thêm ký tự khác).

// Nhiệm vụ của bạn là:

// 👉 **Đếm xem có bao nhiêu “submatrix” (ma trận con) mà trong đó:**

// * **Số lượng ký tự `'X'` = số lượng ký tự `'Y'`**

// ---

// ## 📦 **Submatrix (ma trận con) là gì?**

// Một **submatrix** là một phần hình chữ nhật bất kỳ trong ma trận ban đầu.

// Ví dụ:

// Ma trận:

// ```
// X Y
// Y X
// ```

// Các submatrix có thể là:

// * 1 ô: `X`, `Y`
// * 2 ô: `X Y` (1 hàng)
// * 4 ô: toàn bộ ma trận

// ---

// ## 🎯 **Điều kiện cần đếm**

// Một submatrix được tính nếu:

// ```
// Số lượng X = Số lượng Y
// ```

// Ví dụ:

// * `X Y` → 1 X, 1 Y → ✅ hợp lệ
// * `X X` → 2 X, 0 Y → ❌ không hợp lệ

// ---

// ## 🔍 **Ví dụ cụ thể**

// Ma trận:

// ```
// X Y
// Y X
// ```

// ### Các submatrix hợp lệ:

// 1. `X Y` (hàng 1) → 1 X, 1 Y ✅
// 2. `Y X` (hàng 2) → 1 X, 1 Y ✅
// 3. `X` (ô đơn) ❌
// 4. `Y` ❌
// 5. Toàn bộ ma trận:

// ```
// X Y
// Y X
// ```

// → 2 X, 2 Y ✅

// 👉 Tổng: **3 submatrix hợp lệ**

// ---

// ## ⚠️ **Lưu ý quan trọng**

// * Submatrix phải là **hình chữ nhật liên tiếp** (không được chọn ô rời rạc)
// * Không cần quan tâm vị trí X/Y, chỉ cần **đếm số lượng**

// ---

// ## 💡 **Ý tưởng tư duy (rất quan trọng)**

// Để giải bài này hiệu quả, người ta thường:

// 1. **Chuyển bài toán:**

// * `'X'` → +1
// * `'Y'` → -1

// 👉 Khi đó:

// * Submatrix có tổng = 0 ⇔ số X = số Y

// ---

// 2. **Bài toán trở thành:**
// 👉 Đếm số submatrix có tổng bằng 0

// ---

// 3. **Cách giải nâng cao:**

// * Dùng:

// * Prefix sum 2D
// * HashMap (đếm số đoạn có tổng = 0)
// * Giảm từ 2D → nhiều bài toán 1D

// ---

// ## 🧩 Tóm lại

// 👉 Bài này yêu cầu:

// * Duyệt tất cả các submatrix
// * Đếm số submatrix mà:

// ```
// count(X) == count(Y)
// ```

// 👉 Trick quan trọng:

// * Biến thành bài toán **tổng = 0**

// ---

// Nếu bạn muốn, mình có thể:

// * Giải chi tiết từng bước (từ brute force → tối ưu O(n³))
// * Hoặc code Java theo style bạn đang dùng (Scanner, while, tối ưu)

// Bạn muốn đi theo hướng nào?
