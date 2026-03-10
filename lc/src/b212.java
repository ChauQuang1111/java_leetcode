// // # Bài **“Find All Possible Stable Binary Arrays II”** ( 10/03/2026)

// Mình sẽ giải thích**ý tưởng thuật toán**trước,sau đó viết lại**code Java đầy đủ có`main`,dùng`Scanner`và chú thích từng bước**để bạn dễ học.

// ---

// #1. Ý tưởng thuật toán

// Bài toán:

// Cho:

// ```zero=số lượng số 0 one=số lượng số 1 limit=tối đa phần tử giống nhau liên tiếp```

// Ta cần đếm số**binary arrays stable**:

// ```ví dụ:0 1 1 0 0 1```

// thoả:

// *có đúng`zero`số 0*có đúng`one`số 1*không có quá`limit`số giống nhau liên tiếp

// ---

// #2. Cách nhìn khác của thuật toán

// Thay vì xây mảng trực tiếp,ta nghĩ như sau:

// Mảng nhị phân có dạng:

// ```000 11 0 111 00...```

// tức là**các đoạn(block)**:

// ```block0 block1 block0 block1...```

// mỗi block có độ dài:

// ```1→limit```

// ---

// ###Ví dụ

// ```0 0|1 1 1|0```

// blocks:

// ```00 111 0```

// ---

// #3. Ý nghĩa mảng`sumlim`

// ```sumlim[c][s]```

// nghĩa là:

// ```số cách chia tổng s thành c đoạn mỗi đoạn có độ dài từ 1→limit```

// ví dụ:

// ```limit=3 s=4 c=2```

// các cách:

// ```1+3 2+2 3+1```

// →`sumlim[2][4]=3`

// ---

// #4. Prefix sum để tối ưu

// Công thức DP:

// ```dp[c][i]=dp[c-1][i-1]+dp[c-1][i-2]+...dp[c-1][i-limit]```

// để tính nhanh ta dùng:

// ```prefix sum```

// ---

// #5. Ý nghĩa phần cuối thuật toán

// Sau khi biết:

// ```c0=số cách chia zero thành i block c1=số cách chia one thành i block```

// ta ghép:

// ```0-block 1-block 0-block 1-block...```

// hoặc

// ```1-block 0-block 1-block 0-block...```

// ---

// #6. Code Java đầy đủ(có Scanner+chú thích)

// ```java

import java.util.*;

public class b212 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // nhập số lượng 0
        int zero = sc.nextInt();

        // nhập số lượng 1
        int one = sc.nextInt();

        // nhập limit
        int limit = sc.nextInt();

        int result = numberOfStableArrays(zero, one, limit);

        System.out.println(result);

        sc.close();
    }

    // MOD = 1e9 + 7 vì số kết quả rất lớn
    public static final int MODULUS = (int) 1e9 + 7;
    public static final long MOD_LONG = MODULUS;

    // cộng modulo
    public static int add(int x, int y) {
        return (x + y) % MODULUS;
    }

    // trừ modulo
    public static int subtract(int x, int y) {
        return (x + MODULUS - y) % MODULUS;
    }

    // nhân modulo
    public static int multiply(int x, int y) {
        return (int) ((long) x * y % MOD_LONG);
    }

    public static int numberOfStableArrays(int zero, int one, int limit) {

        // m = max(zero, one)
        int m = Math.max(zero, one);

        // sumlim[c][s]
        // số cách chia s thành c đoạn
        // mỗi đoạn dài từ 1 → limit
        int[][] sumlim = new int[m + 1][m + 1];

        // prefix sum array
        int[] ps = new int[m + 1];

        // sl = row hiện tại
        int[] sl = sumlim[0];

        // base case
        // chia 0 thành 0 đoạn
        sl[0] = 1;

        // tính DP cho số block
        for (int c = 1; c <= m; c++) {

            // tổng tối đa có thể đạt được
            int e = Math.min(c * limit, m);

            int s = 0;

            // tính prefix sum
            for (int i = 0; i <= e; i++) {

                ps[i] = s;
                s = add(s, sl[i]);
            }

            // chuyển sang hàng mới
            sl = sumlim[c];

            for (int i = c; i <= e; i++) {

                // nếu i < limit
                if (i < limit) {

                    sl[i] = ps[i];

                } else {

                    // trừ prefix để chỉ giữ window limit
                    sl[i] = subtract(ps[i], ps[i - limit]);
                }
            }
        }

        // result lưu tổng kết quả
        int result = m <= limit ? 2 : 0;

        // c0 = số cách chia zero thành 1 block
        int c0 = sumlim[1][zero];

        // c1 = số cách chia one thành 1 block
        int c1 = sumlim[1][one];

        // xét số block từ 2 → m
        for (int i = 2; i <= m; i++) {

            int c0p = c0;
            int c1p = c1;

            // cập nhật số cách chia
            c0 = sumlim[i][zero];
            c1 = sumlim[i][one];

            // ghép dạng:
            // 0 block trước, 1 block sau
            result = add(result, multiply(c0, c1p));

            // ghép dạng:
            // 1 block trước, 0 block sau
            result = add(result, multiply(c1, c0p));

            // trường hợp block xen kẽ
            int t = multiply(c0, c1);

            result = add(result, add(t, t));
        }

        return result;
    }
}

// ---

// #7.

// Độ phức tạp

// ###Time

// ```

// O(n²)
// ```

// n tối đa:

// ```
// 1000
// ```

// → ~1e6 operations

// ---

// ### Space

// ```
// O(n²)
// ```

// do dùng:

// ```
// sumlim[m][m]
// ```

// ---

// 💡 Nếu bạn muốn mình giải thêm, mình có thể:

// * **vẽ hình trực quan giúp hiểu thuật toán trong 30 giây**
// * **dry run từng bước với ví dụ nhỏ**
// * **giải thích vì sao công thức cuối cùng

// đúng (đây là phần khó nhất của bài)**.

// # Mình sẽ **giải thích ý tưởng thuật toán** trước, sau đó **viết lại code của
// bạn với comment chi tiết** để bạn hiểu từng dòng.

// # ---

// # # 1. Ý tưởng thuật toán

// # Ta dùng **Dynamic Programming**.

// # Định nghĩa:

// # ```
// # dp0[i][j] = số mảng stable dùng
// # i số 0
// # j số 1
// # và kết thúc bằng 0

// # dp1[i][j] = số mảng stable dùng
// # i số 0
// # j số 1
// # và kết thúc bằng 1
// # ```

// # ---

// # ## Quy tắc chuyển trạng thái

// # ### 1️⃣ Thêm số 0

// # Muốn mảng kết thúc bằng **0**

// # Ta có thể:

// # ```
// # ...1 + 0
// # ...0 + 0
// # ```

// # Nhưng **không được có hơn limit số 0 liên tiếp**.

// # Vì vậy:

// # ```
// # dp0[i][j] =
// # dp0[i-1][j] (thêm 0 vào chuỗi kết thúc bằng 0)
// # + dp1[i-1][j] (thêm 0 vào chuỗi kết thúc bằng 1)
// # - dp1[i-limit-1][j] (loại trường hợp có limit+1 số 0 liên tiếp)
// # ```

// # ---

// # ### 2️⃣ Thêm số 1

// # Tương tự:

// # ```
// # dp1[i][j] =
// # dp0[i][j-1]
// # + dp1[i][j-1]
// # - dp0[i][j-limit-1]
// # ```

// # ---

// # # 2. Vì sao dùng deque ?

// # Ta cần truy cập:

// # ```
// # dp1[i-limit-1][j]
// # ```

// # → phải nhớ **limit+1 dòng trước**

// # Nên dùng:

// # ```
// # deque
// # ```

// # để lưu **limit+1 hàng dp1 gần nhất**

// # ---

// # # 3. Tối ưu bộ nhớ

// # Thay vì dùng:

// # ```
// # dp[1000][1000]
// # ```

// # Ta chỉ giữ:

// # ```
// # dp0_prev
// # dp1_prev
// # ```

// # vì mỗi bước chỉ cần **hàng trước đó**.

// # Memory giảm từ:

// # ```
// # O(n*m)
// # → O(m)
// # ```

// # ---

// # # 4. Code với chú thích chi tiết

// # ```python
// from collections import deque

// def numberOfStableArrays(self, zero: int, one: int, limit: int) -> int:

// mod = 10**9 + 7

// # L = limit + 1
// # dùng để kiểm tra khi vượt quá số lượng phần tử liên tiếp cho phép
// L = limit + 1

// # Nếu số 1 nhiều hơn số 0
// # ta swap để giảm bộ nhớ sử dụng
// if one > zero:
// zero, one = one, zero

// # dp0_prev[j] = số mảng dùng i-1 số 0 và j số 1
// # và kết thúc bằng 0
// dp0_prev = [0] * (one + 1)

// # dp1_prev[j] = số mảng dùng i-1 số 0 và j số 1
// # và kết thúc bằng 1
// dp1_prev = [0] * (one + 1)

// # ====== Khởi tạo hàng đầu tiên (i = 0) ======
// # chỉ có số 1

// for j in range(1, min(one, limit) + 1):
// dp1_prev[j] = 1
// # ví dụ:
// # 1
// # 11
// # 111
// # miễn là <= limit

// # queue lưu L dòng dp1 gần nhất
// # để truy cập dp1[i-L][j]
// dp1q = deque([dp1_prev[:]])

// # ====== duyệt số lượng 0 ======
// for i in range(1, zero + 1):

// # dp của hàng hiện tại
// dp0 = [0] * (one + 1)
// dp1 = [0] * (one + 1)

// # trường hợp chỉ có 0
// if i <= limit:
// dp0[0] = 1

// # ===== duyệt số lượng 1 =====
// for j in range(1, one + 1):

// # tính dp0[i][j]

// dp0[j] = (
// dp0_prev[j] # thêm 0 vào chuỗi kết thúc 0
// + dp1_prev[j] # thêm 0 vào chuỗi kết thúc 1
// - (dp1q[0][j] if i >= L else 0) # loại chuỗi có limit+1 số 0
// ) % mod

// # tính dp1[i][j]

// dp1[j] = (
// dp0[j - 1] # thêm 1 vào chuỗi kết thúc 0
// + dp1[j - 1] # thêm 1 vào chuỗi kết thúc 1
// - (dp0[j - L] if j >= L else 0) # loại chuỗi có limit+1 số 1
// ) % mod

// # lưu dòng dp1 vào queue
// dp1q.append(dp1[:])

// # nếu queue dài hơn limit+1
// # bỏ dòng cũ nhất
// if len(dp1q) > L:
// dp1q.popleft()

// # cập nhật prev cho vòng sau
// dp0_prev = dp0
// dp1_prev = dp1

// # kết quả = kết thúc bằng 0 hoặc 1
// return (dp0_prev[one] + dp1_prev[one]) % mod

// # 5. Độ phức tạp

// ### Time

// # ```
// # O(zero × one)
// # ```

// # max:

// # ```
// # 1000 × 1000 = 10^6
// # ```

// # → chạy nhanh

// # ---

// # ### Space

// # ```
// # O(one)
// # ```

// # vì chỉ lưu **1 hàng**

// # ---

// # 💡 Nếu bạn muốn, mình có thể giải thích thêm:

// # * **intuition cực dễ hiểu (tại sao phải trừ dp[i-limit-1])**
// # * **dry run từng bước với ví dụ nhỏ**
// # * **so sánh bài I vs II**
// # * **cách nghĩ ra thuật toán này khi đi phỏng vấn** (rất hay hỏi).

// # ---

// # ## 1. Input của bài

// # Bạn được cho **3 số nguyên**:

// # * `zero` : số lượng **số 0** phải dùng trong mảng
// # * `one` : số lượng **số 1** phải dùng trong mảng
// # * `limit` : **không được có quá `limit` số giống nhau liên tiếp**

// # ---

// # ## 2. Stable Binary Array là gì?

// # Một **binary array** là mảng chỉ gồm:

// # ```
// # 0 và 1
// # ```

// # Một **stable binary array** trong bài này là mảng thỏa:

// # 1. Có đúng `zero` số **0**
// # 2. Có đúng `one` số **1**
// # 3. Không có **hơn `limit` số giống nhau liên tiếp**

// # ---

// # ## 3. Ví dụ dễ hiểu

// # ### Ví dụ 1

// # ```
// # zero = 1
// # one = 1
// # limit = 2
// # ```

// # Ta cần mảng dài:

// # ```
// # 1 + 1 = 2
// # ```

// # Các mảng có thể tạo:

// # ```
// # [0,1]
// # [1,0]
// # ```

// # Không có mảng nào vi phạm limit.

// # → **Kết quả = 2**

// # ---

// # ### Ví dụ 2

// # ```
// # zero = 3
// # one = 1
// # limit = 2
// # ```

// # Tổng độ dài:

// # ```
// # 4
// # ```

// # Ta phải dùng:

// # ```
// # 0,0,0,1
// # ```

// # Nhưng **không được có hơn 2 số giống nhau liên tiếp**

// # Xét các cách:

// # ```
// # 0001 ❌ (3 số 0 liên tiếp > limit)
// # 0010 ❌
// # 0100 ❌
// # 1000 ❌
// # ```

// # Không có cách hợp lệ.

// # → **Kết quả = 0**

// # ---

// # ### Ví dụ 3

// # ```
// # zero = 2
// # one = 1
// # limit = 2
// # ```

// # Các mảng có thể:

// # ```
// # 001 (2 số 0 liên tiếp OK)
// # 010
// # 100
// # ```

// # → **3 mảng hợp lệ**

// # ---

// # ## 4. Tóm tắt đề bài

// # Bạn phải:

// # 1️⃣ Tạo tất cả mảng gồm `zero` số 0 và `one` số 1
// # 2️⃣ Không được có quá `limit` số giống nhau liên tiếp
// # 3️⃣ **Đếm số lượng mảng hợp lệ**
// # 4️⃣ Vì số có thể rất lớn → trả về **mod 1e9 + 7**

// # ---

// # ## 5. Vì sao bài này khó (II)?

// # Phiên bản **II** khó vì:

// # * `zero` và `one` có thể **rất lớn (~1000)**
// # * Không thể **brute force sinh tất cả mảng**

// # → phải dùng:

// # * **Dynamic Programming**
// # * hoặc **DP + prefix sum optimization**

// # ---

// # ✅ **Ý tưởng chính của bài:**

// # DP theo:

// # ```
// # dp[i][j][last]
// # ```

// # * `i` = số 0 đã dùng
// # * `j` = số 1 đã dùng
// # * `last` = số cuối là 0 hay 1

// # ---

// # 💡 Nếu bạn muốn mình giải tiếp:

// # * **intuition của DP**
// # * **cách nghĩ để ra công thức**
// # * **code Java / Python**
// # * **tối ưu từ bài I → II**

// # Mình có thể giải **từng bước rất dễ hiểu (giống cách phỏng vấn FAANG)**.
