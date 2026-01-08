
// **Max Dot Product of Two Subsequences** (08/01/2026) 
// Mình giải thích **thuật toán DP** trong code của bạn **từng dòng – đúng trọng tâm LeetCode 1458**, vì đây là bài **DP khó do bắt buộc subsequence không rỗng + có số âm**.
import java.util.*;

public class b142 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số phần tử mảng a
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // Nhập số phần tử mảng b
        int m = sc.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        int result = maxDotProduct(a, b);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static int maxDotProduct(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        // Giá trị âm rất nhỏ để đảm bảo subsequence KHÔNG rỗng
        int NEG = (int) -1e9;

        // dp[i][j]: max dot product khi xét a[0..i-1] và b[0..j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Khởi tạo toàn bộ dp = NEG (không dùng 0 vì không cho subsequence rỗng)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = NEG;
            }
        }

        // Duyệt từng phần tử của 2 mảng
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // Lựa chọn ghép a[i-1] với b[j-1]
                // - Bắt đầu subsequence mới
                // - Hoặc nối vào subsequence cũ nếu dp[i-1][j-1] > 0
                int take = a[i - 1] * b[j - 1]
                        + Math.max(0, dp[i - 1][j - 1]);

                // Chọn phương án tốt nhất:
                // 1. Ghép cặp hiện tại
                // 2. Bỏ a[i-1]
                // 3. Bỏ b[j-1]
                dp[i][j] = Math.max(take,
                        Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        // Kết quả cuối cùng
        return dp[n][m];
    }
}

// ---

// ## 🎯 Ý tưởng cốt lõi của bài

// Ta dùng **DP 2 chiều**:

// > `dp[i][j]` = **max dot product** có thể đạt được khi xét
// > `a[0..i-1]` và `b[0..j-1]`
// > **và đã chọn ít nhất 1 cặp phần tử**

// ---

// ## 1️⃣ Khởi tạo DP

// ```java
// int NEG = (int)-1e9;
// int[][] dp = new int[n+1][m+1];

// for(int i=0;i<=n;i++)
// for(int j=0;j<=m;j++)
// dp[i][j] = NEG;
// ```

// ### ❓ Vì sao không dùng 0?

// 👉 **CỰC KỲ QUAN TRỌNG**

// * Bài toán **không cho phép subsequence rỗng**
// * Nếu khởi tạo = `0` → DP có thể “không chọn gì cả”
// * Điều đó **sai đề**

// 👉 Dùng `-∞` để **ép DP phải chọn ít nhất 1 cặp**

// ---

// ## 2️⃣ Duyệt từng cặp `(i, j)`

// ```java
// for(int i=1;i<=n;i++){
// for(int j=1;j<=m;j++){
// ```

// Xét:

// * `a[i-1]`
// * `b[j-1]`

// ---

// ## 3️⃣ Trạng thái quan trọng nhất: `take`

// ```java
// int take = a[i-1]*b[j-1] + Math.max(0, dp[i-1][j-1]);
// ```

// ### 🔍 Ý nghĩa

// Có **2 khả năng** khi ghép `a[i-1]` với `b[j-1]`:

// #### 🔹 Trường hợp 1: bắt đầu subsequence mới

// ```java
// a[i-1] * b[j-1]
// ```

// #### 🔹 Trường hợp 2: nối tiếp subsequence cũ

// ```java
// a[i-1]*b[j-1] + dp[i-1][j-1]
// ```

// ⚠️ Nhưng nếu `dp[i-1][j-1] < 0` thì **nối vào sẽ làm tệ hơn**

// ➡️ Nên:

// ```java
// Math.max(0, dp[i-1][j-1])
// ```

// 👉 Đây chính là **mấu chốt của bài**

// ---

// ## 4️⃣ Hai lựa chọn còn lại (bỏ phần tử)

// ```java
// dp[i-1][j] // bỏ a[i-1]
// dp[i][j-1] // bỏ b[j-1]
// ```

// ➡️ Giữ kết quả tốt nhất đã có trước đó

// ---

// ## 5️⃣ Công thức DP hoàn chỉnh

// ```java
// dp[i][j] = Math.max(
// take,
// Math.max(dp[i-1][j], dp[i][j-1])
// );
// ```

// ### 📌 Tóm tắt 3 lựa chọn

// | Lựa chọn | Ý nghĩa |
// | ------------ | -------------------------- |
// | `take` | Ghép `a[i-1]` với `b[j-1]` |
// | `dp[i-1][j]` | Bỏ `a[i-1]` |
// | `dp[i][j-1]` | Bỏ `b[j-1]` |

// ---

// ## 6️⃣ Trả kết quả

// ```java
// return dp[n][m];
// ```

// 👉 Max dot product từ toàn bộ 2 mảng

// ---

// ## 🧠 Vì sao thuật toán này xử lý đúng **case toàn số âm**?

// Ví dụ:

// ```
// a = [-1, -2]
// b = [-3, -4]
// ```

// * `a[i]*b[j]` → **dương**
// * `Math.max(0, dp[i-1][j-1])` cho phép:

// * bắt đầu subsequence mới
// * hoặc nối nếu có lợi

// ➡️ DP **không bao giờ trả 0 sai**
// ➡️ luôn chọn **ít nhất 1 cặp**

// ---

// ## ⏱️ Độ phức tạp

// * **Time**: `O(n * m)`
// * **Space**: `O(n * m)`
// * Có thể tối ưu xuống `O(m)` nếu cần

// ---

// ## 🧩 Một câu chốt để nhớ bài này

// > **“DP này giống LCS, nhưng mỗi lần match thì cộng `a[i]*b[j]`,
// > và phải dùng `max(0, dp[i-1][j-1])` để tránh nối subsequence xấu.”**

// ---

// Nếu bạn muốn, mình có thể:

// * 🔥 Trace bảng DP bằng tay (rất dễ hiểu)
// * 🔥 So sánh với LCS / Max Subarray
// * 🔥 Viết lại code **1D DP tối ưu bộ nhớ**
// * 🔥 Giải thích vì sao **không dùng Kadane**

// 👉 Bạn muốn tiếp phần nào?

// ### 📌 Đề bài nói gì?

// Bạn được cho **2 mảng số nguyên**:

// * `nums1` có độ dài `n`
// * `nums2` có độ dài `m`

// 👉 Nhiệm vụ của bạn là:

// > **Chọn ra 2 dãy con KHÔNG RỖNG**
// > – một dãy con từ `nums1`
// > – một dãy con từ `nums2`
// > sao cho **tích vô hướng (dot product)** của chúng là **LỚN NHẤT**.

// ---

// ### 📘 Thế nào là *subsequence* (dãy con)?

// * Giữ **thứ tự ban đầu**
// * Có thể **bỏ bớt phần tử**
// * Không cần liên tiếp

// Ví dụ:

// ```
// nums1 = [2, 1, -2, 5]
// → subsequence có thể là: [2, -2, 5] hoặc [1, 5]
// ```

// ---

// ### 📐 Dot product (tích vô hướng) là gì?

// Giả sử ta chọn:

// ```
// A = [a1, a2, ..., ak]
// B = [b1, b2, ..., bk]
// ```

// ➡️ Dot product:

// ```
// a1*b1 + a2*b2 + ... + ak*bk
// ```

// ⚠️ **Hai dãy con phải có cùng độ dài**.

// ---

// ### 🔍 Ví dụ minh họa

// **Ví dụ 1**

// ```
// nums1 = [2,1,-2,5]
// nums2 = [3,0,-6]
// ```

// Một cách chọn:

// ```
// nums1 subsequence: [2, -2]
// nums2 subsequence: [3, -6]

// Dot product = 2*3 + (-2)*(-6) = 6 + 12 = 18
// ```

// ➡️ Kết quả: **18**

// ---

// ### ⚠️ Điều kiện quan trọng (rất hay bẫy)

// 1. **BẮT BUỘC chọn ít nhất 1 phần tử** từ mỗi mảng
// ❌ Không được chọn dãy rỗng
// 2. Mảng có thể chứa:

// * số âm
// * số dương
// * số 0
// 3. Vì có số âm →
// 👉 Không thể dùng cách “nếu âm thì bỏ hết”
// 👉 DP phải **xử lý cả trường hợp kết quả âm**

// ---

// ### 🧠 Trực giác bài toán

// Khi đứng tại:

// * `nums1[i]`
// * `nums2[j]`

// Bạn có **4 lựa chọn**:

// 1. Ghép `nums1[i]` với `nums2[j]`
// 2. Bỏ `nums1[i]`
// 3. Bỏ `nums2[j]`
// 4. Bắt đầu **một subsequence mới** từ cặp `(i, j)`

// 👉 Vì vậy bài này **bắt buộc dùng Dynamic Programming**

// ---

// ### 🧩 Tóm tắt ngắn gọn

// * Chọn 2 subsequence **không rỗng**
// * Giữ nguyên thứ tự
// * Độ dài 2 subsequence phải bằng nhau
// * Tối đa hóa:

// ```
// ∑ ai * bi
// ```

// ---

// Nếu bạn muốn, mình có thể:

// * ✅ Giải thích **ý tưởng DP từng bước**
// * ✅ Vẽ **bảng DP** cho dễ hiểu
// * ✅ Phân tích **case toàn số âm (case khó nhất)**
// * ✅ Viết code Java / Python + giải thích từng dòng

// 👉 Bạn muốn tiếp theo phần nào?
