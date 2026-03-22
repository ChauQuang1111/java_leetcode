// // # Bài **“Determine Whether Matrix Can Be Obtained By Rotation”*(22/03/2026)

import java.util.*;

public class b224 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập kích thước ma trận
        int n = sc.nextInt();

        int[][] mat = new int[n][n];
        int[][] target = new int[n][n];

        // Nhập ma trận mat
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        // Nhập ma trận target
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                target[i][j] = sc.nextInt();
            }
        }

        boolean result = findRotation(mat, target);

        System.out.println(result);
    }

    // -------------------------
    // Hoán đổi 2 phần tử qua đường chéo chính (transpose)
    // -------------------------
    public static void swap(int[][] arr, int i, int j) {
        int temp = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = temp;
    }

    // -------------------------
    // Đảo ngược 1 hàng (reverse row)
    // -------------------------
    public static void reverse(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
    }

    // -------------------------
    // Xoay ma trận 90 độ (clockwise)
    // -------------------------
    public static void rotate(int[][] mat) {
        int n = mat.length;

        // Bước 1: transpose (đổi qua đường chéo)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(mat, i, j);
            }
        }

        // Bước 2: đảo từng hàng
        for (int i = 0; i < n; i++) {
            reverse(mat[i]);
        }
    }

    // -------------------------
    // So sánh 2 ma trận có giống nhau không
    // -------------------------
    public static boolean isEqual(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // -------------------------
    // Hàm chính của bài toán
    // -------------------------
    public static boolean findRotation(int[][] mat, int[][] target) {

        // Thử tối đa 4 lần xoay
        for (int k = 0; k < 4; k++) {

            // Nếu bằng nhau → trả về true
            if (isEqual(mat, target))
                return true;

            // Xoay 90 độ để thử tiếp
            rotate(mat);
        }

        return false;
    }
}

// Mình sẽ giải thích rõ**thuật toán**,rồi viết lại code Java của bạn có**chú
// thích chi tiết+hàm`main`dùng`Scanner`**để bạn chạy luôn.

// ---

// #🧠Ý tưởng thuật toán

// Cách bạn đang làm là cách“chuẩn dễ hiểu”:

// ##🔄Quy trình

// 1. So sánh`mat`với`target`2. Nếu giống→`true`3. Nếu không→xoay`mat`90°4. Lặp
// tối đa**4 lần**(0°,90°,180°,270°)

// ---

// ##🔁Cách xoay 90°(quan trọng)

// Bạn dùng kỹ thuật:

// ###Bước 1:Transpose(đổi hàng↔cột)

// ```mat[i][j]↔mat[j][i]```

// ###Bước 2:Reverse từng hàng

// →tạo ra xoay 90°theo chiều kim đồng hồ

// ---

// #✅Code đầy đủ+chú thích

// ```java

// import java.util.*;

// class Solution {

// // -------------------------
// // Hoán đổi 2 phần tử qua đường chéo chính (transpose)
// // -------------------------
// public void swap(int[][] arr, int i, int j) {
// int temp = arr[i][j];
// arr[i][j] = arr[j][i];
// arr[j][i] = temp;
// }

// // -------------------------
// // Đảo ngược 1 hàng (reverse row)
// // -------------------------
// public void reverse(int[] arr) {
// int n = arr.length;
// for (int i = 0; i < n / 2; i++) {
// int temp = arr[i];
// arr[i] = arr[n - 1 - i];
// arr[n - 1 - i] = temp;
// }
// }

// // -------------------------
// // Xoay ma trận 90 độ (clockwise)
// // -------------------------
// public void rotate(int[][] mat) {
// int n = mat.length;

// // Bước 1: transpose (đổi qua đường chéo)
// for (int i = 0; i < n - 1; i++) {
// for (int j = i + 1; j < n; j++) {
// swap(mat, i, j);
// }
// }

// // Bước 2: đảo từng hàng
// for (int i = 0; i < n; i++) {
// reverse(mat[i]);
// }
// }

// // -------------------------
// // So sánh 2 ma trận có giống nhau không
// // -------------------------
// public boolean isEqual(int[][] mat, int[][] target) {
// int n = mat.length;
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// if (mat[i][j] != target[i][j]) {
// return false;
// }
// }
// }
// return true;
// }

// // -------------------------
// // Hàm chính của bài toán
// // -------------------------
// public boolean findRotation(int[][] mat, int[][] target) {

// // Thử tối đa 4 lần xoay
// for (int k = 0; k < 4; k++) {

// // Nếu bằng nhau → trả về true
// if (isEqual(mat, target))
// return true;

// // Xoay 90 độ để thử tiếp
// rotate(mat);
// }

// return false;
// }

// // -------------------------
// // Hàm main để test bằng Scanner
// // -------------------------
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);

// // Nhập kích thước ma trận
// int n = sc.nextInt();

// int[][] mat = new int[n][n];
// int[][] target = new int[n][n];

// // Nhập ma trận mat
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// mat[i][j] = sc.nextInt();
// }
// }

// // Nhập ma trận target
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// target[i][j] = sc.nextInt();
// }
// }

// Solution sol = new Solution();

// boolean result = sol.findRotation(mat, target);

// System.out.println(result);
// }}```

// ---

// #⚠️

// Lỗi quan
// trọng bạn
// đang gặp

// Trong code
// gốc của bạn:

// ```java for(
// int i = 0;i<n;i++)
// {
// for (int j = 0; j < n; j++) {
// if (isEqual(mat, target))
// return true;
// rotate(mat);
// }
// }```

// ❌
// Sai vì:

// *
// // Bạn đang xoay**n×
// // n lần**(
// // quá nhiều)*
// // Trong khi
// // chỉ cần**4 lần**

// // ---

// // #✅
// // Sửa đúng

// // ```java for(int k = 0;k<4;k++)
// // {
// // if (isEqual(mat, target))
// // return true;
// // rotate(mat);
// // }```

// // ---

// // #💡
// // Tóm lại

// // *✔
// // Chỉ cần
// // xoay tối đa 4 lần*✔
// // Mỗi lần
// // so sánh*✔Rotate=transpose+reverse*❌
// // Không cần
// // loop i, j trong`findRotation`

// // ---

// // Nếu bạn muốn,
// // mình có thể:

// // *
// // Vẽ animation
// // từng bước

// // xoay (giúp bạn nhớ cực lâu)
// // * Hoặc chuyển sang cách **không cần

// // rotate (index mapping)** như Python

// // bên trên (xịn hơn)

// // # Mình sẽ giải thích **ý tưởng thuật toán** trước, rồi viết lại code có
// **chú thích chi tiết từng dòng** để bạn hiểu rõ.

// // # ---

// // # # 🧠 Ý tưởng chính

// // # Thay vì:

// // # * Xoay `mat` 4 lần rồi so sánh

// // # 👉 Ta làm ngược lại:

// // # * **Giữ nguyên `mat`**
// // # * Với mỗi phần tử `mat[i][j]`, kiểm tra nó có khớp với `target` ở vị trí
// tương ứng sau khi xoay không

// // # ---

// // # ## 🔄 4 trường hợp xoay

// // # Với mỗi `(i, j)` trong `mat`:

// // # # | Góc xoay | Vị trí tương ứng trong `target` |
// // # | -------- | ------------------------------- |
// // # | 0° | `target[i][j]` |
// // # | 90° | `target[j][n-1-i]` |
// // # | 180° | `target[n-1-i][n-1-j]` |
// // # | 270° | `target[n-1-j][i]` |

// // # ---

// // # ## ⚡ Tối ưu

// // # * Dùng mảng `possible = [True, True, True, True]`
// // # * Mỗi phần tử tương ứng với 1 góc xoay
// // # * Nếu sai → đánh dấu `False`
// // # * Nếu cả 4 đều `False` → dừng sớm (early exit)

// // # ---

// // # # ✅ Code + chú thích chi tiết

// // # ```python
// // from typing import List
// // class Solution:
// // def findRotation(self, mat: List[List[int]], target: List[List[int]]) ->
// bool:
// // n = len(mat)

// // # possible[0] → kiểm tra xoay 0 độ
// // # possible[1] → kiểm tra xoay 90 độ
// // # possible[2] → kiểm tra xoay 180 độ
// // # possible[3] → kiểm tra xoay 270 độ
// // possible = [True, True, True, True]

// // # duyệt toàn bộ phần tử của ma trận
// // for i in range(n):
// // for j in range(n):

// // # -------------------------
// // # 0 độ: giữ nguyên
// // # mat[i][j] phải == target[i][j]
// // # -------------------------
// // if mat[i][j] != target[i][j]:
// // possible[0] = False

// // # -------------------------
// // # 90 độ:
// // # (i, j) → (j, n-1-i)
// // # -------------------------
// // if mat[i][j] != target[j][n - 1 - i]:
// // possible[1] = False

// // # -------------------------
// // # 180 độ:
// // # (i, j) → (n-1-i, n-1-j)
// // # -------------------------
// // if mat[i][j] != target[n - 1 - i][n - 1 - j]:
// // possible[2] = False

// // # -------------------------
// // # 270 độ:
// // # (i, j) → (n-1-j, i)
// // # -------------------------
// // if mat[i][j] != target[n - 1 - j][i]:
// // possible[3] = False

// // # -------------------------
// // # Nếu cả 4 trường hợp đều sai
// // # → không cần kiểm tra nữa
// // # -------------------------
// // if not any(possible):
// // return False

// // # nếu còn ít nhất 1 trường hợp đúng → return True
// // return any(possible)
// // # ```

// // # ---

// // # # 💡 Hiểu sâu hơn (rất quan trọng)

// // # ## ❓ Vì sao cách này nhanh?

// // # * Không cần tạo ma trận mới (không cần rotate thật)
// // # * Chỉ check index → **O(n²)**, tối ưu nhất

// // # ---

// // ## 🔥 Tư duy quan trọng bạn cần nhớ

// // # 👉 Thay vì:

// // # > "Làm biến đổi dữ liệu"

// // # 👉 Hãy nghĩ:

// // # > "Mapping index sang vị trí mới"

// // # ---

// // # ## 🧠 Mẹo nhớ nhanh

// // # * 90°: `(i, j) → (j, n-1-i)`
// // # * 180°: đảo cả hàng và cột
// // # * 270°: ngược lại 90°

// // # ---

// // # Nếu bạn muốn, mình có thể:

// // # * Vẽ hình minh họa từng phép xoay (cực dễ hiểu)
// // # * Hoặc cho bạn bài tương tự để luyện tư duy index 💪

// // # ## 🧠 Ý nghĩa đề bài

// // # Bạn được cho:

// // # * Một ma trận vuông `mat` (k × k)
// // # * Một ma trận vuông `target` (k × k)

// // # 👉 Nhiệm vụ:
// // # Kiểm tra xem có thể **xoay ma trận `mat`** (theo góc 90°, 180°, 270°
// hoặc 0°) để **trở thành `target` hay không**.

// // # ---

// // # ## 🔄 Các phép xoay hợp lệ

// // # Bạn có thể xoay `mat` theo chiều kim đồng hồ:

// // # * 0° (giữ nguyên)
// // # * 90°
// // # * 180°
// // # * 270°

// // # 👉 Sau mỗi lần xoay, so sánh với `target`.

// // # ---

// // # ## 📌 Ví dụ dễ hiểu

// // # ### Ví dụ 1:

// // # ```
// // # mat =
// // # 1 2
// // # 3 4

// // # target =
// // # 3 1
// // # 4 2
// // # ```

// // # 👉 Nếu xoay `mat` 90°:

// // # ```
// // # 3 1
// // # 4 2
// // # ```

// // # → trùng với `target` ✅
// // # => Kết quả: **True**

// // # ---

// // # ### Ví dụ 2:

// // # ```
// // # mat =
// // # 1 2
// // # 3 4

// // # target =
// // # 1 3
// // # 2 4
// // # ```

// // # 👉 Thử xoay mọi kiểu:

// // # * 0° ❌
// // # * 90° ❌
// // # * 180° ❌
// // # * 270° ❌

// // # => Không trùng
// // # => Kết quả: **False**

// // # ---

// // # ## ⚙️ Tóm tắt cách làm

// // # 1. Lặp tối đa 4 lần (ứng với 4 góc xoay)
// // # 2. Mỗi lần:

// // # * So sánh `mat` với `target`
// // # * Nếu giống → return True
// // # * Nếu không → xoay tiếp 90°
// // # 3. Nếu thử hết mà không giống → return False

// // # ---

// // # ## 🔁 Công thức xoay 90° (quan trọng)

// // # Khi xoay 90°:

// // # ```
// // # mat[i][j] → mat[j][n - 1 - i]
// // # ```

// // # Hoặc cách dễ code hơn:

// // # * Transpose (đổi hàng ↔ cột)
// // # * Reverse từng hàng

// // # ---

// // # ## 💡 Hiểu bản chất

// // # Bài này không phải toán khó, mà là:

// // # * Hiểu **cách xoay ma trận**
// // # * Biết **so sánh 2 ma trận**

// // # ---

// // # Nếu bạn muốn, mình có thể:

// // # * Viết code Java / Python tối ưu
// // # * Hoặc vẽ hình từng bước xoay để bạn hiểu cực rõ 👀
