
// # Number of Ways to Paint N × 3 Grid(03/01/2026)
// # Mình **giải thích đề bài “Number of Ways to Paint N × 3 Grid” bằng tiếng Việt, theo kiểu dễ hiểu nhất**, không đi vào code vội nhé.
// # OK 👍 Mình **giải thích thuật toán + thêm chú thích trực tiếp vào code**, từng dòng một, để bạn nhìn vào là hiểu ngay bài **LeetCode 1411**.
// Mình giải thích **thuật toán Matrix Exponentiation** trong code này **từ gốc → ngọn**, gắn chặt với bài **1411. Number of Ways to Paint N × 3 Grid**, để bạn thấy vì sao viết như vậy là đúng và tối ưu.
import java.util.*;

public class b137 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int result = numOfWays(n);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    static final int mod = 1_000_000_007;

    /**
     * Nhân hai ma trận mat1 và mat2
     * mat1: a x b
     * mat2: b x c
     * Kết quả: a x c
     */
    public static long[][] matrixMultiply(long[][] mat1, long[][] mat2) {
        long[][] ans = new long[mat1.length][mat2[0].length];

        // Nhân ma trận chuẩn
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat2.length; k++) {
                    ans[i][j] = (ans[i][j] + mat1[i][k] * mat2[k][j]) % mod;
                }
            }
        }
        return ans;
    }

    /**
     * Tính lũy thừa ma trận bằng Binary Exponentiation
     * mat^n trong O(log n)
     */
    public static long[][] matrixPower(long[][] mat, int n) {
        // Ma trận đơn vị 2x2
        long[][] ans = { { 1, 0 }, { 0, 1 } };
        long[][] curr = mat;

        while (n != 0) {
            // Nếu bit cuối của n = 1 → nhân vào kết quả
            if ((n & 1) == 1) {
                ans = matrixMultiply(ans, curr);
            }
            // Bình phương ma trận
            curr = matrixMultiply(curr, curr);
            n >>= 1; // n = n / 2
        }
        return ans;
    }

    /**
     * Hàm chính giải bài toán
     */
    public static int numOfWays(int n) {
        // Ma trận chuyển trạng thái DP
        // [x] = [3 2] [x]
        // [y] [2 2] [y]
        long[][] mat = { { 3, 2 }, { 2, 2 } };

        // Tính M^(n-1)
        long[][] powerMat = matrixPower(mat, n - 1);

        // Vector trạng thái ban đầu (n = 1)
        // x = 6 (ABA), y = 6 (ABC)
        long[][] initialVector = { { 6 }, { 6 } };

        // Tính vector kết quả
        long[][] finalVector = matrixMultiply(powerMat, initialVector);

        // Tổng số cách = x + y
        return (int) ((finalVector[0][0] + finalVector[1][0]) % mod);
    }

}

// Hằng số modulo theo đề bài

// ---

// ## 1️⃣ Nhắc lại bài toán & DP gốc

// Như bạn đã biết, với mỗi hàng (3 cột) chỉ có **2 kiểu hợp lệ**:

// * **ABA** → 2 màu → gọi là `x`
// * **ABC** → 3 màu → gọi là `y`

// ### Công thức DP theo hàng:

// ```
// x[i] = 3*x[i-1] + 2*y[i-1]
// y[i] = 2*x[i-1] + 2*y[i-1]
// ```

// Viết dạng vector:

// ```
// [ x[i] ] = [3 2] [ x[i-1] ]
// [ y[i] ] [2 2] [ y[i-1] ]
// ```

// 👉 Đây chính là **nhân ma trận**

// ---

// ## 2️⃣ Ý tưởng Matrix Exponentiation

// Ta cần tính:

// ```
// [ x[n] ] = M^(n-1) × [ x[1] ]
// [ y[n] ] [ y[1] ]
// ```

// Trong đó:

// ```
// M = | 3 2 |
// | 2 2 |
// ```

// Và với `n = 1`:

// ```
// x[1] = 6
// y[1] = 6
// ```

// ---

// ## 3️⃣ Giải thích từng phần code

// ---

// ### 🔹 3.1. Nhân 2 ma trận

// ```java
// public long[][] matrixMultiply(long[][] mat1, long[][] mat2){
// ```

// 👉 Hàm này tính:

// ```
// ans = mat1 × mat2
// ```

// ---

// ```java
// long[][] ans = new long[mat1.length][mat2[0].length];
// ```

// * Ma trận kết quả có:

// * số hàng = `mat1.length`
// * số cột = `mat2[0].length`

// ---

// ```java
// for(int i=0;i<mat1.length;i++){
// for(int j=0;j<mat2[0].length;j++){
// for(int k=0;k<mat2.length;k++){
// ans[i][j] = (ans[i][j] + mat1[i][k]*mat2[k][j]) % mod;
// }
// }
// }
// ```

// ➡ Công thức chuẩn của nhân ma trận:

// ```
// ans[i][j] = Σ mat1[i][k] × mat2[k][j]
// ```

// ✔ Có `% mod` để tránh overflow

// ---

// ### 🔹 3.2. Lũy thừa ma trận (Matrix Power)

// ```java
// public long[][] matrixPower(long[][] mat, int n){
// ```

// 👉 Tính `mat^n` bằng **binary exponentiation**

// ---

// ```java
// long[][] ans = {{1, 0},{0, 1}};
// ```

// ✔ Ma trận đơn vị
// ✔ Tương đương với `result = 1`

// ---

// ```java
// long[][] curr = mat;
// ```

// ✔ Ma trận đang xét (ban đầu là M)

// ---

// ```java
// while(n!=0){
// ```

// Chạy khi còn mũ

// ---

// ```java
// if((n&1) == 1){
// ans = matrixMultiply(ans, curr);
// }
// ```

// ✔ Nếu bit cuối của `n` là 1 → nhân vào kết quả

// ---

// ```java
// curr = matrixMultiply(curr, curr);
// n >>= 1;
// ```

// ✔ Bình phương ma trận
// ✔ Dịch phải `n` (chia 2)

// ⏱ **Độ phức tạp**: `O(log n)`

// ---

// ### 🔹 3.3. Hàm chính `numOfWays`

// ```java
// long[][] mat = {{3,2},{2,2}};
// ```

// ✔ Ma trận chuyển trạng thái DP

// ---

// ```java
// long[][] multiplyMat = matrixPower(mat, n-1);
// ```

// ✔ Tính `M^(n-1)` vì:

// * `x[1], y[1]` đã biết
// * Muốn đi từ hàng 1 → hàng n

// ---

// ```java
// long[][] initialVector = {{6}, {6}};
// ```

// ✔ Trạng thái ban đầu:

// ```
// x[1] = 6
// y[1] = 6
// ```

// ---

// ```java
// long[][] finalVector = matrixMultiply(multiplyMat, initialVector);
// ```

// ✔ Tính:

// ```
// [ x[n] ]
// [ y[n] ]
// ```

// ---

// ```java
// return (int)((finalVector[0][0] + finalVector[1][0]) % mod);
// ```

// ✔ Tổng số cách = `x[n] + y[n]`

// ---

// ## 4️⃣ Vì sao dùng Matrix Exponentiation?

// | Cách | Time |
// | ---------- | ------------ |
// | DP thường | O(n) |
// | Matrix Exp | **O(log n)** |

// ✔ Với `n` rất lớn (10⁹+)
// ✔ Chuẩn bài **Linear Recurrence**

// ---

// ## 5️⃣ Tóm tắt 1 dòng (rất quan trọng)

// > Bài này là **DP tuyến tính bậc 2**, nên có thể dùng **ma trận 2×2 + lũy
// thừa nhanh**

// ---

// Nếu bạn muốn:

// * 🔥 Chứng minh toán học vì sao ra ma trận `{3,2},{2,2}`
// * 🔥 Viết lại bản **Python Matrix**
// * 🔥 So sánh **DP thường vs Matrix**

// 👉 cứ nói tiếp nhé 👌

// # ---

// # ## 1️⃣ Ý nghĩa của `x` và `y`

// # Trong bài này, ta **không xét từng ô**, mà xét **từng hàng (3 ô)**.

// # Ta chia các cách tô **mỗi hàng** thành **2 loại**:

// # ### 🔹 `x` – số cách kiểu **ABA** (2 màu)

// # Ví dụ:

// # ```
// # Đỏ – Xanh – Đỏ
// # ```

// # ### 🔹 `y` – số cách kiểu **ABC** (3 màu khác nhau)

// # Ví dụ:

// # ```
// # Đỏ – Xanh – Vàng
// # ```

// # ---

// # ## 2️⃣ Khởi tạo ban đầu

// # ```python
// # x = 0 # số cách với kiểu ABA
// # y = 3 # số cách với kiểu ABC
// # ```

// # Tại sao `y = 3`?

// # ➡ Khi **chưa có hàng nào**, ta coi như có **3 cách khởi đầu** để xây dựng
// hàng đầu tiên
// # (sau vòng lặp đầu tiên, số lượng sẽ đúng với N = 1)

// # 👉 Đây là một **mẹo khởi tạo** thường dùng trong bài này.

// # ---

// # ## 3️⃣ Công thức chuyển trạng thái (quan trọng nhất)

// # ### 🔁 Mỗi vòng lặp = thêm **1 hàng**

// # ```python
// # for _ in range(n):
// # ```

// # ---

// # ### 🧠 Công thức:

// # ```python
// # x_new = 3*x + 2*y
// # y_new = 2*x + 2*y
// # ```

// # ---

// # ## 4️⃣ Giải thích từng công thức

// # ### 🔸 `x_new = 3*x + 2*y`

// # Số cách tạo **hàng mới kiểu ABA**:

// # #### ✔ Từ hàng cũ cũng là ABA (`x`)

// # * Có **3 cách** tô hàng dưới sao cho vẫn là ABA và không trùng màu theo cột

// # 👉 đóng góp: `3 * x`

// # ---

// # #### ✔ Từ hàng cũ là ABC (`y`)

// # * Có **2 cách** chuyển sang ABA

// # 👉 đóng góp: `2 * y`

// # ---

// # ### 🔸 `y_new = 2*x + 2*y`

// # Số cách tạo **hàng mới kiểu ABC**:

// # #### ✔ Từ ABA → ABC

// # * Có **2 cách hợp lệ**

// # 👉 `2 * x`

// # #### ✔ Từ ABC → ABC

// # * Có **2 cách hợp lệ**

// # 👉 `2 * y`

// # ---

// # ## 5️⃣ Code có chú thích đầy đủ

// # ```python
// class Solution:
// def numOfWays(self, n: int, x = 0, y = 3, mod = 1_000_000_007) -> int:
// # x: số cách tô hàng kiểu ABA (2 màu)
// # y: số cách tô hàng kiểu ABC (3 màu khác nhau)

// for _ in range(n):
// # Tính số cách mới cho hàng tiếp theo
// new_x = (3 * x + 2 * y) % mod # ABA mới
// new_y = (2 * x + 2 * y) % mod # ABC mới

// x, y = new_x, new_y # cập nhật trạng thái

// # Tổng số cách = ABA + ABC
// return (x + y) % mod

// ## 6️⃣ Ví dụ nhanh để kiểm tra

// ### 🔹 n = 1

// # Sau 1 vòng:

// # * `x = 6`
// # * `y = 6`

// # 👉 tổng = **12** ✅

// # ---

// # ### 🔹 n = 2

// # Kết quả = **54** ✅ (đúng đề)

// # ---

// # ## 7️⃣ Vì sao thuật toán này tối ưu?

// # ✔ **Time**: `O(n)`
// # ✔ **Space**: `O(1)`
// # ✔ Không dùng mảng DP
// # ✔ Chỉ giữ **2 biến**

// # ---

// # Nếu bạn muốn:

// # * 🔥 Chứng minh chi tiết vì sao là **3,2,2,2**
// # * 🔥 Vẽ bảng minh họa từng trường hợp
// # * 🔥 Viết lại bằng **Java**

// # 👉 cứ nói tiếp nhé 😄

// # ---

// # ## 1️⃣ Mô tả đề bài (hiểu bằng ngôn ngữ đời thường)

// # * Bạn có **một cái bảng gồm N hàng và 3 cột** (N × 3).
// # * Mỗi ô được sơn **1 trong 3 màu khác nhau** (thường là: đỏ – vàng – xanh).
// # * **Luật sơn quan trọng**:

// # * ❌ Hai ô **kề nhau theo chiều ngang hoặc chiều dọc** **không được cùng
// màu**.
// # * Nhiệm vụ của bạn là:

// # > 👉 **Đếm số cách sơn hợp lệ** cho cả bảng.

// # Kết quả thường yêu cầu **lấy modulo 1e9 + 7** (vì số rất lớn).

// # ---

// # ## 2️⃣ Ví dụ để dễ hình dung

// # ### 🔹 Khi N = 1 (1 hàng, 3 cột)

// # Chỉ có **1 hàng** như thế này:

// # ```
// # [ ] [ ] [ ]
// # ```

// # Điều kiện:

// # * Ô 1 ≠ ô 2
// # * Ô 2 ≠ ô 3

// # 👉 Ta chỉ cần chọn 3 màu sao cho **3 ô liên tiếp không trùng màu**.

// # ---

// # ## 3️⃣ Ý tưởng cốt lõi của bài này

// # Thay vì xét từng ô (rất phức tạp), người ta **xét theo từng hàng**.

// # ### Với 1 hàng (3 ô), chỉ có **2 kiểu hợp lệ**:

// # #### 🔸 Kiểu 1: **ABA** (2 màu)

// # Ví dụ:

// # ```
// # Đỏ – Xanh – Đỏ
// # ```

// # * Ô 1 = ô 3
// # * Ô 2 khác ô 1

// # 👉 Gọi là **type A**

// # ---

// # #### 🔸 Kiểu 2: **ABC** (3 màu khác nhau)

// # Ví dụ:

// # ```
// # Đỏ – Xanh – Vàng
// # ```

// # 👉 Gọi là **type B**

// # ---

// # ## 4️⃣ Đếm số cách cho 1 hàng

// # ### 🧮 Type A (ABA):

// # * Chọn màu cho A: **3 cách**
// # * Chọn màu cho B (khác A): **2 cách**

// # 👉 **3 × 2 = 6 cách**

// # ---

// # ### 🧮 Type B (ABC):

// # * Chọn A: **3**
// # * Chọn B (≠ A): **2**
// # * Chọn C (≠ A, ≠ B): **1**

// # 👉 **3 × 2 × 1 = 6 cách**

// # ---

// # ➡️ **Tổng cho N = 1 là: 12 cách**

// # ---

// # ## 5️⃣ Khi N ≥ 2 thì sao?

// # Lúc này phải để ý thêm:

// # * Ô **trên – dưới** cũng **không được cùng màu**.

// # 👉 Vì vậy:

// # * Một hàng **type A** ở trên sẽ **chuyển sang** type A hoặc B ở hàng dưới
// theo **quy luật nhất định**
// # * Tương tự với **type B**

// # 👉 Đây là lý do bài này dùng **Dynamic Programming (Quy hoạch động)**.

// # ---

// # ## 6️⃣ Trạng thái DP (chỉ cần hiểu, chưa cần code)

// # Ta chỉ cần theo dõi:

// # * `a[i]` = số cách sơn **i hàng**, hàng cuối là **type A**
// # * `b[i]` = số cách sơn **i hàng**, hàng cuối là **type B**

// # Sau đó:

// # * Dựa vào hàng trước → tính hàng sau
// # * Lặp từ 1 → N

// # ---

// # ## 7️⃣ Tóm tắt ngắn gọn (để nhớ nhanh)

// # ✔ Bảng N × 3
// # ✔ 3 màu
// # ✔ Ô kề nhau không trùng màu
// # ✔ Mỗi hàng chỉ có **2 kiểu hợp lệ**:

// # * **ABA (2 màu)**
// # * **ABC (3 màu)**
// # ✔ Dùng **DP theo từng hàng**

// # ---

// # Nếu bạn muốn:

// # * 👉 mình **giải tiếp phần công thức chuyển DP**
// # * 👉 hoặc **viết code Java / Python**
// # * 👉 hoặc **giải thích bằng ví dụ N = 2, 3**

// # chỉ cần nói 👍
