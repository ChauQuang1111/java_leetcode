// // # # Bài **“Special Positions in a Binary Matrix”** (04/03/2026)

// Ok Quang👍mình sẽ:

// 1.✅Thêm`main()`dùng`Scanner`2.✅Giữ đúng style for-loop như bạn hay dùng 3.✅Thêm chú thích chi tiết giải thích thuật toán 4.✅Giải thích tư duy từ chính code của bạn

// ---

// #💻Code hoàn chỉnh có`main`+chú thích

// ```java

import java.util.*;

public class b206 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số hàng và số cột
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] mat = new int[m][n];

        // Nhập ma trận
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        int result = numSpecial(mat);

        System.out.println(result);

        sc.close();
    }

    public static int numSpecial(int[][] mat) {

        // Biến đếm số special positions
        int specials = 0;

        // Duyệt từng hàng
        for (int i = 0; i < mat.length; i++) {

            // Kiểm tra hàng i có đúng 1 số 1 không
            // Nếu có -> trả về vị trí cột chứa số 1
            // Nếu không -> trả về -1
            int index = checkRow(mat, i);

            // Nếu hàng hợp lệ (index >= 0)
            // và cột tương ứng cũng hợp lệ
            if (index >= 0 && checkColumn(mat, i, index))
                specials++;
        }

        return specials;
    }

    // Kiểm tra hàng i có đúng 1 số 1 hay không
    public static int checkRow(int[][] mat, int i) {

        // Lưu vị trí cột của số 1
        int index = -1;

        for (int j = 0; j < mat[0].length; j++) {

            if (mat[i][j] == 1) {

                // Nếu đã thấy 1 trước đó rồi
                // nghĩa là hàng có >= 2 số 1 -> không hợp lệ
                if (index >= 0)
                    return -1;
                else
                    index = j; // lưu vị trí cột
            }
        }

        // Nếu không có số 1 nào -> index vẫn = -1
        // Nếu có đúng 1 số 1 -> trả về vị trí cột
        return index;
    }

    // Kiểm tra cột index có đúng 1 số 1 không
    // i là hàng đang xét
    public static boolean checkColumn(int[][] mat, int i, int index) {

        for (int j = 0; j < mat.length; j++) {

            // Nếu có số 1 khác ngoài vị trí (i, index)
            // thì cột này không hợp lệ
            if (mat[j][index] == 1 && j != i)
                return false;
        }

        return true;
    }
}

// #🧠

// Giải thích
// thuật toán
// từ chính
// code của bạn

// ##🎯
// Mục tiêu

// Tìm các ô`(i,j)`
// sao cho:

// *
// Hàng i
// chỉ có đúng 1 số 1*
// Cột j
// chỉ có đúng 1 số 1

// ---

// #🔎
// Cách bạn

// đang làm (rất sạch và rõ tư duy)

// ## Bước 1 — Duyệt từng hàng

// ```java
// for (int i = 0; i < mat.length; i++)
// ```

// Bạn xử lý từng hàng độc lập.

// ---

// ## Bước 2 — Kiểm tra hàng

// ```java
// int index = checkRow(mat, i);
// ```

// Hàm `checkRow`:

// * Nếu hàng có 0 số 1 → trả về -1
// * Nếu hàng có 2 số 1 trở lên → trả về -1
// * Nếu hàng có đúng 1 số 1 → trả về vị trí cột

// 👉 Đây là bước loại bỏ 90% trường hợp sai ngay từ đầu.

// ---

// ## Bước 3 — Kiểm tra cột

// ```java
// checkColumn(mat, i, index)
// ```

// Ta chỉ kiểm tra đúng 1 cột duy

// nhất (cột chứa số 1 đó).

// Trong cột đó:

// * Nếu tồn tại số 1 khác ở hàng khác → loại
// * Nếu không → đây là special position

// ---

// # 📊 Độ phức tạp

// Giả sử ma trận m x n:

// * checkRow → O(n)
// * checkColumn → O(m)

// Lặp m lần:

// → Tổng: **O(m × n)**

// Rất tối ưu cho bài này.

// ---

// # 🔥 Tư duy quan trọng trong code của bạn

// Thay vì:

// * Duyệt từng ô (i, j)
// * Rồi kiểm tra cả hàng + cả cột

// Bạn:

// * Chỉ quan tâm hàng có đúng 1 số 1
// * Rồi chỉ kiểm tra đúng 1 cột

// → Tránh kiểm tra dư thừa
// → Code rõ ràng, tách hàm tốt

// Đây là tư duy rất giống khi bạn làm bài DFS/BFS trước đây:
// chia nhỏ logic thành từng phần xử lý độc lập.

// ---

// Nếu bạn muốn mình có thể:

// * 🚀 Viết version tối ưu hơn dùng mảng đếm rowCount/colCount
// * 🧠 Hoặc phân tích cách này so với Python version bạn gửi trước đó
// * 🔥 Hoặc chuyển sang dùng while-loop theo style bạn thích

// Bạn muốn đào sâu kiểu nào tiếp?

// # Ok mình sẽ:

// # 1. ✅ Giải thích thuật toán từng bước
// # 2. ✅ Thêm comment trực tiếp vào code cho bạn
// # 3. ✅ Phân tích độ phức tạp

// # ---

// # # 🧠 Ý tưởng thuật toán

// # Ta chỉ quan tâm đến những hàng có **đúng 1 số 1**.

// # Vì một ô đặc biệt bắt buộc:

// # * Hàng đó có đúng 1 số 1
// # * Cột đó có đúng 1 số 1

// # Thay vì kiểm tra từng ô một cách brute-force, ta làm thông minh hơn:

// # ### 🔹 Bước 1

// # Duyệt từng hàng.

// # ### 🔹 Bước 2

// # Nếu tổng của hàng ≠ 1 → bỏ qua luôn (vì không thể có special position).

// # ### 🔹 Bước 3

// # Nếu tổng = 1 → tìm vị trí cột `j` chứa số 1.

// # ### 🔹 Bước 4

// # Kiểm tra cột `j`:

// # * Nếu tổng cột đó cũng = 1 → đây là special position.

// # ---

// # # 💻 Code có chú thích

// # ```python
// from typing import List
// class Solution:
// def numSpecial(self, mat: List[List[int]]) -> int:
// # m: số hàng, n: số cột
// m, n = len(mat), len(mat[0])

// # Biến đếm số special positions
// count = 0

// # Duyệt từng hàng (i là index hàng, row là nội dung hàng)
// for i, row in enumerate(mat):

// # Nếu hàng này không có đúng 1 số 1
// # thì chắc chắn không có special position
// if sum(row) != 1:
// continue # bỏ qua hàng này

// # Nếu hàng có đúng 1 số 1
// # tìm vị trí cột j chứa số 1 đó
// j = row.index(1)

// # Kiểm tra xem trong cột j có đúng 1 số 1 không
// # Ta duyệt tất cả các hàng k và lấy mat[k][j]
// # nếu tổng = 1 thì cột này hợp lệ
// if sum([mat[k][j] for k in range(m)]) == 1:
// count += 1 # tìm được 1 special position

// return count

// # ---

// # # 📊 Ví dụ minh họa

// # Ma trận:

// # ```
// # 1 0 0
// # 0 1 0
// # 0 0 1
// # ```

// # * Hàng 0 → sum = 1 → j = 0 → cột 0 sum = 1 → count++
// # * Hàng 1 → sum = 1 → j = 1 → cột 1 sum = 1 → count++
// # * Hàng 2 → sum = 1 → j = 2 → cột 2 sum = 1 → count++

// # → Kết quả = 3

// # ---

// # # ⏱ Độ phức tạp

// # Giả sử ma trận m x n:

// # * Duyệt m hàng
// # * Mỗi lần có thể:

// # * sum(row) → O(n)
// # * sum(cột) → O(m)

// # 👉 Tổng complexity: **O(m × n)**

// # Vì mỗi phần tử chỉ bị duyệt một số lần hữu hạn.

// # ---

// # # 🎯 Tại sao cách này thông minh?

// # Ta không kiểm tra từng ô (i, j) rồi quét cả hàng + cột (sẽ thành O(m × n ×
// (m+n))).

// # Thay vào đó:

// # * Chỉ kiểm tra những hàng có đúng 1 số 1
// # * Và chỉ kiểm tra đúng 1 cột tương ứng

// # → Giảm rất nhiều kiểm tra dư thừa.

// # ---

// # Nếu bạn muốn mình có thể:

// # * 🔥 Viết lại version tối ưu hơn (precompute rowCount + colCount)
// # * 🚀 Hoặc chuyển sang Java theo style bạn đang học
// # * 🧩 Hoặc vẽ flow tư duy để bạn hiểu sâu hơn

// # Bạn muốn đào sâu theo hướng nào?

// # ---

// # ## 🧠 Đề bài nói gì?

// # Bạn được cho một ma trận nhị phân (binary matrix) `mat` kích thước `m x n`.

// # * Mỗi phần tử chỉ có thể là **0 hoặc 1**.
// # * Bạn cần **đếm số lượng vị trí đặc biệt (special positions)** trong ma
// trận.

// # ---

// # ## 📌 Vị trí đặc biệt là gì?

// # Một ô `(i, j)` được gọi là **special position** nếu:

// # 1. `mat[i][j] == 1`
// # 2. Tất cả các phần tử khác trong **hàng i** đều bằng 0
// # 3. Tất cả các phần tử khác trong **cột j** đều bằng 0

// # 👉 Nói cách khác:

// # * Hàng đó chỉ có đúng **1 số 1**
// # * Cột đó cũng chỉ có đúng **1 số 1**
// # * Và đó chính là ô `(i, j)`

// # ---

// # ## 🔎 Ví dụ 1

// # Ma trận:

// # ```
// # 1 0 0
// # 0 0 1
// # 1 0 0
// # ```

// # Phân tích:

// # * Ô (0,0) = 1

// # * Hàng 0 chỉ có 1 số 1 ✅
// # * Nhưng cột 0 có thêm 1 ở (2,0) ❌
// # → Không phải special

// # * Ô (1,2) = 1

// # * Hàng 1 chỉ có 1 số 1 ✅
// # * Cột 2 chỉ có 1 số 1 ✅
// # → ✅ Đây là special position

// # * Ô (2,0) = 1

// # * Hàng 2 chỉ có 1 số 1 ✅
// # * Nhưng cột 0 có thêm 1 ở (0,0) ❌

// # 👉 Kết quả = **1**

// # ---

// # ## 🔎 Ví dụ 2

// # ```
// # 1 0 0
// # 0 1 0
// # 0 0 1
// # ```

// # Ở đây:

// # * Mỗi hàng có đúng 1 số 1
// # * Mỗi cột cũng có đúng 1 số 1

// # 👉 Có 3 special positions

// # Kết quả = **3**

// # ---

// # ## 💡 Tóm lại bản chất bài toán

// # Bạn cần tìm những ô có giá trị 1 sao cho:

// # ```
// # Tổng số 1 trong hàng đó = 1
// # Tổng số 1 trong cột đó = 1
// # ```

// # ---

// # ## 🎯 Cách nghĩ để làm bài

// # Bước 1: Đếm số lượng số 1 trong mỗi hàng
// # Bước 2: Đếm số lượng số 1 trong mỗi cột
// # Bước 3: Duyệt lại ma trận:

// # * Nếu `mat[i][j] == 1`
// # * Và `row[i] == 1`
// # * Và `col[j] == 1`
// # → tăng biến đếm

// # ---

// # Nếu bạn muốn, mình có thể:

// # * Giải bằng Java (theo style bạn hay dùng Scanner/while)
// # * Hoặc giải thích tối ưu độ phức tạp
// # * Hoặc minh hoạ từng bước bằng ví dụ cụ thể

// # Bạn muốn theo hướng nào? 🚀
