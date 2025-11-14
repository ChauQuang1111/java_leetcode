// // 2536. Increment Submatrices by One(14/11/2025)
// // Dưới đây là **giải thích đề bài LeetCode 2536 – Increment Submatrices by One** cực kỳ dễ hiểu:
// // Dưới đây là**giải thích thuật toán chi tiết+code Java đầy đủ**(**có chú thích từng dòng và có hàm main dùng Scanner**)
// Dưới đây là**giải thích thuật toán chi tiết**và**code có chú thích đầy đủ+hàm main dùng Scanner**.

// ---

// #🧠**Giải thích thuật toán**

// Bài toán yêu cầu:

// *Cho một ma trận`n x n`,ban đầu toàn số 0.*Với mỗi query`[r1,c1,r2,c2]`,ta cộng**+1**vào mọi phần tử trong hình chữ nhật`(r1..r2,c1..c2)`.

// Nếu làm kiểu brute-force(duyệt từng ô trong mỗi query),độ phức tạp sẽ:

// ```Q*n*n→10^5*10^5=10^10(TLE)```

// 👉Vì vậy phải dùng**2D Difference Array(Hiệu số hai chiều)**:

// ##📌Ý tưởng difference 2D:

// Để tăng+1 cho hình chữ nhật`(r0..r1,c0..c1)`:

// ```diff[r0][c0]+=1 diff[r0][c1+1]-=1 diff[r1+1][c0]-=1 diff[r1+1][c1+1]+=1```

// Sau khi xử lý tất cả query:

// 1. Prefix sum theo hàng(row prefix)2. Prefix sum theo cột(col prefix)

// Kết quả cuối cùng=ma trận res sau khi cộng dồn.

// ---

// #✅**Code đầy đủ+chú thích rõ ràng**

// ```java

import java.util.*;

public class b98 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        // Nhập số lượng query
        int q = sc.nextInt();
        int[][] Q = new int[q][4];

        // Nhập từng query
        for (int i = 0; i < q; i++) {
            Q[i][0] = sc.nextInt();
            Q[i][1] = sc.nextInt();
            Q[i][2] = sc.nextInt();
            Q[i][3] = sc.nextInt();
        }

        int[][] ans = rangeAddQueries(n, Q);

        // In ma trận kết quả
        for (int[] row : ans) {
            for (int x : row) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static int[][] rangeAddQueries(int n, int[][] Q) {
        // Tạo ma trận độ chênh lệch (difference matrix)
        int[][] res = new int[n][n];

        // Xử lý từng query
        for (var q : Q) {
            int r0 = q[0], c0 = q[1];
            int r1 = q[2] + 1, c1 = q[3] + 1;

            // res[r0][c0] += 1
            res[r0][c0]++;

            // res[r0][c1] -= 1
            if (c1 < n)
                res[r0][c1]--;

            // res[r1][c0] -= 1
            if (r1 < n) {
                res[r1][c0]--;

                // res[r1][c1] += 1
                if (c1 < n)
                    res[r1][c1]++;
            }
        }

        // Phép cộng dồn theo từng dòng (prefix row)
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] += res[i][j - 1];
            }
        }

        // Phép cộng dồn theo từng cột (prefix column)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] += res[i - 1][j];
            }
        }

        return res;
    }
}

// Hàm main để nhập và chạy

// thuật toán**

// *
// Dùng ma
// trận hiệu số**O(1)** để đánh dấu mỗi query.
// * Sau đó dùng prefix sum 2D để tính kết quả cuối.
// * Độ phức tạp:

// ```
// O(Q + n²)
// ```

// → Rất nhanh, phù hợp giới hạn LeetCode.

// ---

// Nếu bạn muốn **demo ví dụ từng bước**, mình có thể minh họa!

// // ---

// // #✅**Giải thích thuật toán**

// // Đây là bài toán áp dụng**2D Difference Array(hiệu số 2 chiều)**để tăng tốc
// độ cập nhật.

// // ###🔥Ý tưởng chính:

// // Thay vì tăng toàn bộ submatrix`(r0,c0)`→`(r1,c1)`,ta chỉ cần update**4
// điểm**trong mảng hiệu số:

// // Giả sử query là:

// // ```(r0,c0)→(r1,c1)```

// // Ta thực hiện:

// // ```diff[r0][c0]+=1 diff[r0][c1+1]-=1 diff[r1+1][c0]-=1
// diff[r1+1][c1+1]+=1```

// // Sau đó,ta dùng prefix sum theo:

// // *hàng*cột

// // để thu được ma trận cuối cùng.

// // 📌Lợi ích:

// // *Mỗi query update O(1)*Cuối cùng quét prefix O(n²)

// // →**Tổng:O(n²+q)**,rất nhanh.

// // ---

// // #✅CODE HOÀN CHỈNH(Java)

// // ###✔Có chú thích từng dòng

// // ###✔Có hàm`main`+Scanner

// // ```java

// import java.util.*;

// public class b98 {
// static Scanner sc = new Scanner(System.in);

// public static void main(String[] args) {
// int n = sc.nextInt();

// int q = sc.nextInt();

// int[][] queries = new int[q][4];

// for (int i = 0; i < q; i++) {
// queries[i][0] = sc.nextInt();
// queries[i][1] = sc.nextInt();
// queries[i][2] = sc.nextInt();
// queries[i][3] = sc.nextInt();
// }

// ;
// int[][] res = rangeAddQueries(n, queries);

// for (int[] row : res) {
// for (int x : row) {
// System.out.print(x + " ");
// }
// System.out.println();
// }
// }

// public static int[][] rangeAddQueries(int n, int[][] Q) {

// int[][] res = new int[n][n];

// // 1️⃣ Áp dụng 2D Difference Array
// for (int[] q : Q) {
// int r0 = q[0], c0 = q[1];
// int r1 = q[2] + 1, c1 = q[3] + 1; // +1 để đánh dấu điểm "kết thúc"

// // Tăng góc trái trên
// res[r0][c0]++;

// // Giảm tại cột sau khi hết phạm vi
// if (c1 < n)
// res[r0][c1]--;

// // Giảm tại hàng sau khi hết phạm vi
// if (r1 < n) {
// res[r1][c0]--;

// // Tăng lại tại vị trí bù trừ (r1,c1)
// if (c1 < n)
// res[r1][c1]++;
// }
// }

// // 2️⃣ Prefix sum theo chiều ngang (cộng dồn theo hàng)
// for (int i = 0; i < n; i++)
// for (int j = 1; j < n; j++)
// res[i][j] += res[i][j - 1];

// // 3️⃣ Prefix sum theo chiều dọc (cộng dồn theo cột)
// for (int i = 1; i < n; i++)
// for (int j = 0; j < n; j++)
// res[i][j] += res[i - 1][j];

// return res;
// }

// // Hàm gcd không dùng ở đây nhưng chỉ có main
// public static int gcd(int a, int b) {
// while (b != 0) {
// int t = b;
// b = a % b;
// a = t;
// }
// return a;
// }
// }

// // #✅

// // Giải thích
// // nhanh theo
// // luồng chạy

// // ###**1.
// // Update hiệu số**

// // *
// // Mỗi query
// // chỉ tác
// // động lên 4

// // điểm (O(1))
// // * Không cập nhật từng ô (tránh O(n² * q))

// // ### **2. Prefix theo hàng**

// // Tính tổng dồn từ trái sang phải.

// // ### **3. Prefix theo cột**

// // Tính tổng dồn từ trên xuống dưới.

// // ### **4. Thu được ma trận cuối cùng**

// // ---

// // Nếu bạn muốn mình có thể vẽ **hình minh hoạ từng bước difference array**,
// chỉ cần bảo mình nhé!

// // ---

// // # ✅ **Giải thích đề bài**

// // Bạn được cho:

// // * Một số nguyên **n** – kích thước của ma trận vuông `n x n`.
// // * Một danh sách `queries`, mỗi query có dạng:

// // ```
// // [r1, c1, r2, c2]
// // ```

// // Tức là mô tả một **hình chữ nhật con** trong ma trận:

// // * Góc trái trên: `(r1, c1)`
// // * Góc phải dưới: `(r2, c2)`

// // 📌 **Nhiệm vụ của bạn:**

// // 👉 Với MỖI query, bạn phải **tăng 1** cho tất cả phần tử nằm trong
// submatrix từ
// // `(r1, c1)` đến `(r2, c2)`.

// // Cuối cùng, bạn phải trả về **ma trận sau tất cả các thao tác**.

// // ---

// // # ❗ **Điểm quan trọng**

// // ### ❌ Làm trực tiếp từng query bằng cách duyệt hết các ô sẽ bị TLE

// // Vì:

// // * `n` có thể lên đến `500`
// // * `queries` có thể lên đến `10^5`

// // → Nếu mỗi query bạn update `O(n^2)` thì sẽ bị quá chậm.

// // ---

// // # ✅ Cách giải đúng là dùng **2D Prefix Difference Array**

// // Chỉ update **4 điểm** cho mỗi query để đánh dấu sự thay đổi, rồi cuối cùng
// quét prefix để xây lại ma trận.

// // ---

// // # 🔍 **Ví dụ minh họa**

// // ### Input:

// // ```
// // n = 3
// // queries = [
// // [1, 1, 2, 2],
// // [0, 0, 1, 0]
// // ]
// // ```

// // ### Xử lý từng query:

// // #### Query 1: tăng 1 tại submatrix (1,1) → (2,2)

// // Các ô tăng:

// // ```
// // (1,1), (1,2)
// // (2,1), (2,2)
// // ```

// // #### Query 2: tăng 1 tại submatrix (0,0) → (1,0)

// // Các ô tăng:

// // ```
// // (0,0)
// // (1,0)
// // ```

// // ### Kết quả cuối cùng:

// // ```
// // 1 0 0
// // 1 1 1
// // 0 1 1
// // ```

// // ---

// // # 📌 Tóm tắt

// // | Bạn có | Phải làm |
// // | ---------------------------- |
// ------------------------------------------------ |
// // | Ma trận `n x n` | Ban đầu tất cả là 0 |
// // | `queries = [r1, c1, r2, c2]` | Mỗi query: tăng +1 cho các ô trong hình
// chữ nhật |
// // | Mục tiêu | Trả về ma trận sau tất cả updates |

// // ---

// // Nếu bạn muốn, mình có thể viết cho bạn:

// // * 💡 Lời giải tối ưu bằng Java (đã có main + Scanner)
// // * 💡 Lời giải tối ưu bằng Python
// // * 💡 Giải thích thuật toán "difference array 2D" bằng hình minh họa

// // Bạn muốn loại nào?
