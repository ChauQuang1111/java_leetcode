
// // # 3539. Find Sum of Array Product of Magical Sequences(12/10/2025)
import java.util.*;

public class b65 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        int k = sc.nextInt();
        sc.close();

        int result = magicalSum(m, k, nums);

        System.out.println(result);
    }

    public static final int MOD = 1_000_000_007;
    public static final int MX = 31;

    // F[i] = i! (giai thừa)
    // INV_F[i] = nghịch đảo modular của i! (tức là (i!)^-1 mod MOD)
    public static final long[] F = new long[MX];
    public static final long[] INV_F = new long[MX];

    // Khối static dùng để tiền tính giai thừa và nghịch đảo modular
    static {
        F[0] = 1;
        for (int i = 1; i < MX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }

        INV_F[MX - 1] = pow(F[MX - 1], MOD - 2);
        for (int i = MX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }

    // Hàm lũy thừa nhị phân (tính x^n mod MOD)
    public static long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1)
                res = res * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

    // Hàm chính tính tổng "magical sum"
    public static int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        // powV[i][j] = nums[i]^j mod MOD
        int[][] powV = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powV[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                powV[i][j] = (int) ((long) powV[i][j - 1] * nums[i] % MOD);
            }
        }

        // memo[i][leftM][x][leftK] lưu kết quả đã tính để tránh tính lại
        int[][][][] memo = new int[n][m + 1][m / 2 + 1][k + 1];
        for (int[][][] a : memo)
            for (int[][] b : a)
                for (int[] c : b)
                    Arrays.fill(c, -1);

        // Gọi DFS từ vị trí 0, với m phần tử, không có bit mang, và k bit 1 cần chọn
        return (int) (dfs(0, m, 0, k, powV, memo) * F[m] % MOD);
    }

    // DFS đệ quy để duyệt qua từng vị trí i trong nums
    public static long dfs(int i, int leftM, int x, int leftK, int[][] powV, int[][][][] memo) {
        int c1 = Integer.bitCount(x); // số bit 1 hiện có trong x
        if (c1 + leftM < leftK)
            return 0; // nếu số bit 1 còn lại không đủ -> dừng

        if (i == powV.length)
            return (leftM == 0 && c1 == leftK) ? 1 : 0;

        if (memo[i][leftM][x][leftK] != -1)
            return memo[i][leftM][x][leftK];

        long res = 0;
        for (int j = 0; j <= leftM; j++) {
            int bit = (x + j) & 1; // tính bit cuối sau khi cộng j
            if (bit <= leftK) {
                long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - bit, powV, memo);
                res = (res + r * powV[i][j] % MOD * INV_F[j]) % MOD;
            }
        }
        return memo[i][leftM][x][leftK] = (int) res;
    }
}
// ---

// ## 🧩 Tóm tắt ý tưởng bài toán

// > Cho `nums`, `m`, `k`.
// > Hãy tính tổng của tích của các **“magical sequences”** — tức là những **tập
// con (subset)** có đúng `m` phần tử, sao cho khi biểu diễn các chỉ số dưới
// dạng nhị phân, có đúng `k` bit bằng `1` sau một số phép cộng bit cụ thể.

// Đây là bài toán **tổ hợp nâng cao**, nên cách giải phải xét đến:

// * Tất cả các **phân bố số lượng phần tử** ở mỗi vị trí (`j` là số phần tử
// được chọn từ `nums[i]`)
// * Kết hợp lại với **đếm nhị phân (bitmask)** để thỏa điều kiện “magical”
// * Dùng **DP có nhớ** để tránh lặp lại trạng thái trùng nhau.

// ---

// ## ⚙️ Giải thích từng phần code

// ---

// ### 🧱 Phần 1: Tiền xử lý giai thừa và nghịch đảo modular

// ```java
// F[i] = i! % MOD;
// INV_F[i] = (i!)^-1 % MOD;
// ```

// → Để dùng cho tổ hợp hoặc chia modular.

// #### Tại sao cần?

// Trong quá trình đệ quy, ta tính nhiều biểu thức dạng:
// [
// \frac{a^b}{b!} \pmod{MOD}
// ]
// nên ta cần sẵn `F` và `INV_F` để chia nhanh trong modulo.

// ---

// ### ⚡ Phần 2: Mảng `powV[i][j]`

// ```java
// powV[i][j] = (nums[i])^j % MOD;
// ```

// → Tức là tại vị trí `i`, nếu chọn `j` phần tử đó, **đóng góp** là
// `nums[i]^j`.

// Mục tiêu:

// * Giúp tránh việc phải gọi `Math.pow()` nhiều lần.
// * Tăng tốc đệ quy.

// ---

// ### 🧮 Phần 3: DP 4 chiều – memo hóa

// ```java
// int[][][][] memo = new int[n][m + 1][m / 2 + 1][k + 1];
// ```

// Ý nghĩa của từng chiều:

// | Biến | Ý nghĩa |
// | ------- | --------------------------------------- |
// | `i` | Đang xét đến phần tử thứ i trong `nums` |
// | `leftM` | Còn lại bao nhiêu phần tử cần chọn |
// | `x` | Giá trị bit pattern hiện tại |
// | `leftK` | Còn lại bao nhiêu bit 1 cần đạt |

// → Dùng memo để lưu kết quả `dfs(i, leftM, x, leftK)` đã tính, giúp tránh tính
// lại.

// ---

// ### 🔁 Phần 4: Hàm đệ quy `dfs`

// ```java
// dfs(i, leftM, x, leftK)
// ```

// Ý tưởng:

// * Mỗi bước `i` → thử chọn **0 đến leftM** phần tử (`j`).
// * Cập nhật trạng thái nhị phân (`x`), và số bit cần (`leftK`).

// ---

// ### 🧠 Bên trong vòng lặp chính:

// ```java
// for (int j = 0; j <= leftM; j++) {
// int bit = (x + j) & 1; // bit thấp nhất sau cộng
// if (bit <= leftK) {
// long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - bit, powV, memo);
// res = (res + r * powV[i][j] % MOD * INV_F[j]) % MOD;
// }
// }
// ```

// 🔹 Giải thích từng dòng:

// | Dòng | Ý nghĩa |
// | -------------- |
// ---------------------------------------------------------------------------------
// |
// | `(x + j) & 1` | Lấy **bit thấp nhất** của phép cộng nhị phân giữa `x` và
// `j` (tức là tổng mod 2). |
// | `(x + j) >> 1` | Dịch sang bit kế tiếp (mô phỏng cộng nhị phân có nhớ). |
// | `leftK - bit` | Giảm số bit 1 cần đạt nếu bit hiện tại = 1. |
// | `powV[i][j]` | Đóng góp giá trị `nums[i]^j`. |
// | `INV_F[j]` | Chia cho `j!` để tránh tính trùng tổ hợp. |
// | `r` | Kết quả đệ quy cho phần còn lại. |
// | `res += ...` | Cộng dồn kết quả hợp lệ vào tổng. |

// ---

// ### ⚖️ Phần 5: Cắt nhánh (pruning)

// ```java
// if (c1 + leftM < leftK) return 0;
// ```

// → Nếu số bit 1 hiện tại (`c1`) + số phần tử còn lại (`leftM`) vẫn < số bit
// cần (`leftK`)
// ⇒ Không thể đạt được → dừng sớm (tối ưu).

// ---

// ### ✅ Phần 6: Điều kiện dừng

// ```java
// if (i == powV.length) {
// return leftM == 0 && c1 == leftK ? 1 : 0;
// }
// ```

// → Khi đã xét hết các phần tử:

// * Nếu đã chọn đúng `m` phần tử (`leftM == 0`)
// * Và có đúng `k` bit 1 (`c1 == leftK`)
// ⇒ Trả về 1 (đếm được 1 cấu hình hợp lệ).

// ---

// ### 🎯 Phần 7: Kết quả cuối cùng

// ```java
// return (int) (dfs(0, m, 0, k, powV, memo) * F[m] % MOD);
// ```

// → Sau khi hoàn tất DFS, nhân thêm `m!` (`F[m]`)
// để quy đổi lại từ “tổ hợp chia factorial” sang kết quả đầy đủ.

// ---

// ## 🧩 Tóm tắt trực quan:

// ```
// DFS(i, leftM, x, leftK):
// │
// ├─ Thử chọn j phần tử từ nums[i]
// │ ├─ Cập nhật bit cộng (x+j)
// │ ├─ Giảm leftM và leftK tương ứng
// │ ├─ Gọi DFS(i+1, …)
// │ └─ Cộng kết quả vào res
// │
// └─ Dừng khi hết phần tử:
// Nếu leftM=0 && bitCount(x)=k → +1 cấu hình hợp lệ
// ```

// ---

// ## 💡 Ý nghĩa thực tế

// Thuật toán này là sự kết hợp của:

// * **Combinatorics** (giai thừa, nghịch đảo)
// * **Bit manipulation**
// * **Dynamic programming (memo hóa đệ quy)**
// * **Modular arithmetic**

// => Dùng để **đếm và cộng các cấu hình hợp lệ** cực lớn mà không thể duyệt
// brute-force.

// ---

// Nếu bạn muốn, mình có thể thêm **phiên bản có `main()` + `Scanner` + ví dụ
// input/output cụ thể** để bạn dễ test và hiểu luồng chạy hơn — bạn có muốn
// mình thêm không?

// // # Dưới đây là **giải thích đề bài 3539 — Find Sum of Array Product of
// Magical Sequences** (trích từ LeetCode) cùng các khái niệm và cách hiểu. Nếu
// bạn muốn, mình có thể tiếp tục hướng dẫn cách giải (thuật toán) sau khi bạn
// nắm đề nhé.
// // # Rất hay — đoạn code bạn đưa là **lời giải nâng cao** (rất khác so với
// cách brute-force thông thường).
// // # Nó dùng **lập trình động (DFS có memoization)**, **tổ hợp**, **phép toán
// trên bit**, và **modular arithmetic**.
// // # Đây là một bài toán “biến thể mở rộng” của *Find Sum of Array Product of
// Magical Sequences* — chứ không phải chỉ duyệt từng dãy con đơn giản.
// // # Mình sẽ **giải thích chi tiết từng phần + thêm chú thích đầy đủ vào
// code** cho bạn 👇

// // # ---

// // # ### 🧠 Ý tưởng tổng quát

// // # Bài toán này không còn là duyệt dãy con nữa, mà trở thành **bài toán
// đếm/tính tổng dựa trên cấu trúc bit và tổ hợp**.

// // # * `m`: tổng số phần tử cần chọn
// // # * `k`: số phần tử “thỏa điều kiện đặc biệt” (ví dụ bit bằng 1)
// // # * `nums`: danh sách các giá trị
// // # * Thuật toán sử dụng **DFS có cache** để duyệt mọi cách phân phối `m`
// phần tử vào `n` vị trí.

// // # Vì số lượng tổ hợp rất lớn, nên code:

// // # * Dùng `fac` (factorial) và `inv_f` (modular inverse factorial)
// // # * Dùng `pow_v[i][j]` để lưu `nums[i]**j % MOD`
// // # → giúp giảm thời gian tính toán lặp lại.

// // # ---

// // # ### 🧩 Giải thích từng đoạn code

// // # ```python
// // # MOD = 1_000_000_007 # Số nguyên lớn để tránh tràn số khi tính modulo
// // # MX = 31 # Giới hạn factorial (tối đa m = 30)
// // # ```

// // # #### 1️⃣ Tính giai thừa và nghịch đảo modular

// // # ```python
// // # fac = [0] * MX
// // # fac[0] = 1
// // # for i in range(1, MX):
// // # fac[i] = fac[i - 1] * i % MOD # fac[i] = i! mod MOD

// // # inv_f = [0] * MX
// // # inv_f[-1] = pow(fac[-1], -1, MOD) # Nghịch đảo modular của fac[MX-1]
// // # for i in range(MX - 1, 0, -1):
// // # inv_f[i - 1] = inv_f[i] * i % MOD # inv_f[i] = 1 / fac[i]
// // # ```

// // # → Đây là kỹ thuật tổ hợp chuẩn:
// // # ( inv_f[i] = (fac[i])^{-1} \mod MOD )

// // # ---

// // # #### 2️⃣ Tiền tính mũ của từng phần tử

// // # ```python
// // # pow_v = [[1] * (m + 1) for _ in range(n)]
// // # for i in range(n):
// // # for j in range(1, m + 1):
// // # pow_v[i][j] = pow_v[i][j - 1] * nums[i] % MOD
// // # ```

// // # → `pow_v[i][j]` = `nums[i] ** j % MOD`
// // # Giúp tính nhanh khi cần nhân nhiều lần cùng một số.

// // # ---

// // # #### 3️⃣ DFS có memoization

// // # ```python
// // # @cache
// // # def dfs(i: int, left_m: int, x: int, left_k: int) -> int:
// // # c1 = x.bit_count() # đếm số bit 1 trong x

// // # # Nếu số bit 1 hiện tại + số phần tử còn lại < số k cần đạt → không thể
// thỏa
// // # if c1 + left_m < left_k:
// // # return 0

// // # # Nếu đã xét hết n phần tử
// // # if i == n:
// // # # Chỉ hợp lệ nếu đã chọn đủ m phần tử và số bit 1 == k
// // # return 1 if left_m == 0 and c1 == left_k else 0
// // # ```

// // # ---

// // # #### 4️⃣ Duyệt tất cả cách chọn j phần tử tại vị trí i

// // # ```python
// // # res = 0
// // # for j in range(left_m + 1):
// // # bit = (x + j) & 1 # bit mới sinh ra khi cộng x+j
// // # if bit <= left_k:
// // # r = dfs(i + 1, left_m - j, (x + j) >> 1, left_k - bit)
// // # res += r * pow_v[i][j] * inv_f[j]
// // # return res % MOD
// // # ```

// // # 🔍 Giải thích:

// // # * `j`: số phần tử chọn tại vị trí `i`
// // # * `(x + j) & 1`: kiểm tra bit mới sinh ra
// // # * `(x + j) >> 1`: dịch bit để tính tiếp ở cấp tiếp theo
// // # * `r * pow_v[i][j] * inv_f[j]`:

// // # * `r`: số cách từ phần còn lại
// // # * `pow_v[i][j]`: nhân giá trị `nums[i]**j`
// // # * `inv_f[j]`: chia cho hoán vị trùng lặp (tổ hợp)

// // # ---

// // # #### 5️⃣ Kết quả cuối cùng

// // # ```python
// // # return dfs(0, m, 0, k) * fac[m] % MOD
// // # ```

// // # → Nhân `fac[m]` để hoàn tất nhân hoán vị (đảo lại chia trước đó).
// // # Kết quả cuối cùng được tính modulo `1e9+7`.

// // # ---

// // # ### 💡 Tóm tắt ý tưởng

// // # | Thành phần | Ý nghĩa |
// // # | --------------------------- |
// ------------------------------------------------------ |
// // # | `fac`, `inv_f` | Dùng để tính toán tổ hợp nhanh |
// // # | `pow_v` | Lưu giá trị `nums[i]^j` để tránh tính lại |
// // # | `dfs(i, left_m, x, left_k)` | Hàm đệ quy tính số cách chọn phần tử để
// thỏa điều kiện |
// // # | `x` | Trạng thái bit để kiểm tra điều kiện “magical” |
// // # | `bit_count` | Số lượng bit 1 đã sinh ra |
// // # | `MOD` | Dùng để tránh tràn số nguyên |

// // # ---

// // # ### 📘 Code có chú thích đầy đủ

// // # ```python
// // # MOD = 1_000_000_007
// // # MX = 31

// // # # Tiền tính giai thừa và nghịch đảo modular
// // # fac = [0] * MX
// // # fac[0] = 1
// // # for i in range(1, MX):
// // # fac[i] = fac[i - 1] * i % MOD

// // # inv_f = [0] * MX
// // # inv_f[-1] = pow(fac[-1], -1, MOD)
// // # for i in range(MX - 1, 0, -1):
// // # inv_f[i - 1] = inv_f[i] * i % MOD

// // MOD = 1_000_000_007
// // MX = 31

// // # Tiền tính giai thừa và nghịch đảo modular
// // fac = [0] * MX
// // fac[0] = 1
// // for i in range(1, MX):
// // fac[i] = fac[i - 1] * i % MOD

// // inv_f = [0] * MX
// // inv_f[-1] = pow(fac[-1], -1, MOD)
// // for i in range(MX - 1, 0, -1):
// // inv_f[i - 1] = inv_f[i] * i % MOD

// // from functools import cache

// // class Solution:
// // def magicalSum(self, m: int, k: int, nums: list[int]) -> int:
// // n = len(nums)

// // # pow_v[i][j] = nums[i]^j mod MOD
// // pow_v = [[1] * (m + 1) for _ in range(n)]
// // for i in range(n):
// // for j in range(1, m + 1):
// // pow_v[i][j] = pow_v[i][j - 1] * nums[i] % MOD

// // @cache
// // def dfs(i: int, left_m: int, x: int, left_k: int) -> int:
// // c1 = x.bit_count()
// // if c1 + left_m < left_k:
// // return 0
// // if i == n:
// // return 1 if left_m == 0 and c1 == left_k else 0

// // res = 0
// // for j in range(left_m + 1):
// // bit = (x + j) & 1
// // if bit <= left_k:
// // r = dfs(i + 1, left_m - j, (x + j) >> 1, left_k - bit)
// // res += r * pow_v[i][j] * inv_f[j]
// // return res % MOD

// // # Kết quả cuối cùng
// // return dfs(0, m, 0, k) * fac[m] % MOD

// // # ```

// // # ---

// // # Nếu bạn muốn, mình có thể **vẽ sơ đồ đệ quy (DFS tree)** để bạn thấy rõ
// cách các trạng thái `(i, left_m, x, left_k)` được mở rộng như thế nào — bạn
// có muốn không?

// // # ---

// // # ## 📄 Đề bài (tóm lược)

// // # Bạn được cho:

// // # * Một số nguyên **m** — độ dài của mỗi **sequence** (chuỗi) bạn sẽ tạo.
// // # * Một số nguyên **k**.
// // # * Một mảng `nums` (có độ dài ≥ m).

// // # Bạn định nghĩa:

// // # * Một **magical sequence** (chuỗi ma thuật) là một sequence `seq` của độ
// dài **m**, trong đó mỗi phần tử `seq[i]` là một chỉ số trong `nums` (tức chọn
// các vị trí).

// // # * Với mỗi sequence `seq`, bạn tính **array product**:
// // # [
// // # \text{prod(seq)} = nums[,seq[0],] \times nums[,seq[1],] \times \cdots
// \times nums[,seq[m-1],]
// // # ]

// // # * Ngoài ra, sequence được gọi là **magical** nếu nó thỏa điều kiện nào
// đó liên quan đến **k** (theo mô tả đề).
// // # (Trong mô tả mở rộng, có nói về “set bit” của tổng 2^seq[i] và đếm số
// bit set, nhưng tóm lại là có một điều kiện để sequence được xem là magical.)

// // # Yêu cầu:

// // # > Tính tổng (sum) của tất cả các **array product** của mọi sequence
// `seq` hợp lệ (magical).
// // # > Vì kết quả có thể rất lớn, trả kết quả mod (10^9 + 7). ([Hello, World!
// System Design Newsletter][1])

// // # Ví dụ:

// // # * Nếu `m = 5, k = 5, nums = [1, 10, 100, 10000, 1000000]`, output là
// `991600007`. ([Hello, World! System Design Newsletter][1])
// // # * Nếu `m = 2, k = 2, nums = [5,4,3,2,1]`, output là `170`. ([Hello,
// World! System Design Newsletter][1])

// // # ---

// // # ## 🧩 Phân tích và cách hiểu chi tiết

// // # Để hiểu đề, ta cần làm rõ các phần:

// // # 1. **Sequence & array product**

// // # * `seq` là một dãy độ dài **m** chứa các chỉ số từ `0` đến `nums.length
// - 1`.
// // # * `prod(seq)` = tích các `nums[seq[i]]`.

// // # 2. **Điều kiện magical**

// // # * Có liên quan đến việc tính (2^{seq[0]} + 2^{seq[1]} + \cdots +
// 2^{seq[m-1]}).
// // # * Đếm số bit set (số bit 1) trong tổng đó.
// // # * Nếu số bit set = k thì sequence đó là magical. ([Hello, World! System
// Design Newsletter][1])

// // # 3. **Tổng các sản phẩm**

// // # * Với mỗi sequence hợp lệ, tính `prod(seq)`, sau đó cộng vào tổng (mod
// (10^9 + 7)).
// // # * Cần xét **mọi sequence** nhưng chỉ lấy những cái “magical”.

// // # 4. **Tính toán lớn**

// // # * Vì số sequence có thể rất nhiều (combinatorial), bạn không thể liệt kê
// tất cả. Phải tìm công thức, tối ưu bằng DP / toán tổ hợp / bitmasking / kỹ
// thuật nhúng mod.
// // # * Đề nhấn là “return modulo (10^9 + 7)”. ([LeetCode][2])

// // # ---
