
// # 2221. Find Triangular Sum of an Array(30/09/2025)
import java.util.*;

class b52 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int fastAns = triangularSum(nums);

        System.out.println(fastAns);

        sc.close();
    }

    // ================== THUẬT TOÁN TỐI ƯU ==================

    // Precompute tam giác Pascal nhỏ mod 5 cho Lucas theorem
    private static final int[][] C5 = buildC5();

    /**
     * Hàm tính triangular sum bằng công thức tổ hợp mod 10
     * Complexity: O(n log n)
     */
    public static int triangularSum(int[] nums) {
        int n = nums.length;
        int N = n - 1; // Tổng số bước (chiều cao tam giác - 1)
        int ans = 0; // Kết quả cuối cùng

        // Duyệt qua từng phần tử nums[i]
        for (int i = 0; i < n; i++) {
            // Tính C(N,i) mod 2 (hệ số tổ hợp mod 2)
            int c2 = combMod2(N, i);

            // Tính C(N,i) mod 5 (dùng Lucas theorem)
            int c5 = combMod5(N, i);

            // Kết hợp kết quả mod 2 và mod 5 thành mod 10 (CRT)
            int c10 = crt10(c2, c5);

            // Cộng dồn vào đáp án, nhân với nums[i] và mod 10
            ans = (ans + c10 * nums[i]) % 10;
        }

        return ans; // Trả về kết quả
    }

    // Tính C(N,i) mod 2:
    // Tổ hợp C(N,i) là số lẻ khi trong phép cộng i + (N-i) không phát sinh "carry"
    // nhị phân
    private static int combMod2(int N, int i) {
        return ((i & (N - i)) == 0) ? 1 : 0;
    }

    // Tính C(N,i) mod 5 bằng Lucas theorem
    private static int combMod5(int N, int i) {
        int res = 1; // Kết quả ban đầu
        int n = N, k = i; // Lấy 2 số N và i
        while (n > 0 || k > 0) { // Duyệt từng chữ số ở hệ cơ số 5
            int nd = n % 5; // Lấy chữ số cuối của n
            int kd = k % 5; // Lấy chữ số cuối của k
            if (kd > nd)
                return 0; // Nếu kd > nd thì tổ hợp = 0
            res = (res * C5[nd][kd]) % 5; // Nhân với giá trị Pascal mod 5
            n /= 5; // Bỏ chữ số cuối
            k /= 5; // Bỏ chữ số cuối
        }
        return res; // Trả về C(N,i) mod 5
    }

    // Kết hợp CRT: tìm số r sao cho
    // r ≡ r2 (mod 2) và r ≡ r5 (mod 5)
    private static int crt10(int r2, int r5) {
        int r = r5; // r trong {0,1,2,3,4}
        if ((r & 1) != r2)
            r += 5; // Nếu không khớp parity thì cộng thêm 5
        return r; // Kết quả trong khoảng 0..9
    }

    // Xây dựng Pascal nhỏ mod 5 (tam giác Pascal với mod 5)
    private static int[][] buildC5() {
        int[][] c = new int[5][5]; // Bảng 5x5
        for (int a = 0; a < 5; a++) {
            c[a][0] = c[a][a] = 1; // C(a,0) = 1, C(a,a) = 1
            for (int b = 1; b < a; b++) {
                // Công thức Pascal: C(a,b) = C(a-1,b-1) + C(a-1,b), sau đó mod 5
                c[a][b] = (c[a - 1][b - 1] + c[a - 1][b]) % 5;
            }
        }
        return c;
    }

    // ================== HÀM CHẬM ĐỂ KIỂM TRA ==================
    static int triangularSumSlow(int[] nums) {
        int[] a = Arrays.copyOf(nums, nums.length); // Copy mảng gốc
        for (int len = a.length; len > 1; len--) { // Lặp đến khi còn 1 phần tử
            for (int j = 0; j < len - 1; j++) {
                // Cập nhật từng phần tử = (a[j] + a[j+1]) % 10
                a[j] = (a[j] + a[j + 1]) % 10;
            }
        }
        return a[0]; // Phần tử đầu tiên chính là kết quả
    }
}

// Hay lắm 👍. Đoạn code bạn gửi là một **thuật toán tối ưu** để tính
// `triangularSum` trong **O(n log n)** thay vì mô phỏng `O(n²)` như cách cơ
// bản.
// Mình sẽ giải thích chi tiết:

// ---

// ## 1. Nhắc lại đề bài

// Ta có mảng `nums`, mỗi lần thay mảng bằng tổng các cặp phần tử liên tiếp `mod
// 10`.
// Ví dụ:

// ```
// [1,2,3,4,5]
// → [3,5,7,9]
// → [8,2,6]
// → [0,8]
// → [8]
// ```

// Kết quả = 8.

// Thực chất, **kết quả cuối cùng** là:
// [
// \text{ans} = \sum_{i=0}^{n-1} \binom{n-1}{i} \cdot nums[i] \pmod{10}
// ]

// ---

// ## 2. Vấn đề

// * `C(n-1,i)` (tổ hợp nhị thức) rất lớn, dễ tràn số khi `n` lớn (đến 1000).
// * Ta chỉ cần `mod 10`.
// * Giải pháp: **tính C(n-1, i) mod 10 trực tiếp** mà không cần tính cả số to.

// ---

// ## 3. Ý tưởng chính

// * Dùng **Định lý Lucas** để tính tổ hợp modulo một số nguyên tố.
// * Vì `10 = 2 * 5`, ta sẽ tính:

// * `C(n-1, i) mod 2`
// * `C(n-1, i) mod 5`
// * Sau đó, ghép lại bằng **Chinese Remainder Theorem (CRT)** để tìm `C(n-1, i)
// mod 10`.

// ---

// ## 4. Các hàm trong code

// ### 🔹 `combMod2(N, i)`

// * Công thức: `C(N, i)` là **lẻ** (≡1 mod 2) **khi và chỉ khi** không có
// “carry” khi cộng `i` và `N-i` trong nhị phân.
// * Check: `(i & (N - i)) == 0`
// * Nếu đúng → `return 1`, ngược lại → `return 0`.

// ---

// ### 🔹 `combMod5(N, i)` (Lucas mod 5)

// * Định lý Lucas:
// [
// C(n, k) \equiv \prod C(n_d, k_d) \pmod{p}
// ]
// với `n_d, k_d` là chữ số của `n,k` trong cơ số `p`.
// * Ở đây `p = 5`.
// * `C5` là bảng Pascal nhỏ mod 5 cho `0..4`.
// * Ta tách `N` và `i` theo từng chữ số trong base-5, nhân lại kết quả.

// ---

// ### 🔹 `crt10(r2, r5)`

// * Có kết quả:

// * `C(N,i) ≡ r2 (mod 2)`
// * `C(N,i) ≡ r5 (mod 5)`
// * Ta tìm `r ≡ ? (mod 10)`.
// * Cách đơn giản: bắt đầu với `r = r5`, nếu parity không khớp với `r2` thì
// cộng thêm 5.

// ---

// ### 🔹 `triangularSum(nums)`

// * Tính cho từng `i`:
// [
// c10 = C(N,i) \pmod{10}
// ]
// [
// ans = \sum (c10 * nums[i]) \pmod{10}
// ]
// * Đây là công thức nhị thức Newton tối ưu.

// ---

// ### 🔹 `triangularSumSlow(nums)`

// * Là phiên bản **O(n²)** mô phỏng trực tiếp, để check kết quả.

// ---

// ## 5. Tóm gọn thuật toán

// 1. Bài toán thực chất là **linear combination** với trọng số `C(n-1,i)`.
// 2. Ta chỉ cần kết quả mod 10.
// 3. Tách thành mod 2 và mod 5, rồi ghép lại bằng CRT.
// 4. Độ phức tạp O(n log n), không bị overflow.

// ---

// 👉 Đây là một cách cực kỳ hay để tránh tràn số trong Java.

// Bạn có muốn mình vẽ thêm một ví dụ cụ thể (ví dụ mảng `nums = [1,2,3,4]`) rồi
// chứng minh công thức này bằng nhị thức Newton không?

// from typing import List
// class Solution:
// def triangularSum(self, nums: List[int]) -> int:
// n = len(nums) - 1 # Bậc của tam giác (vì sau n lần thì còn 1 số)
// ans, A = nums[0], 1 # ans: kết quả cuối cùng, ban đầu = nums[0]*C(n,0)
// # A: hệ số tổ hợp C(n, k), khởi tạo C(n,0) = 1

// for k in range(1, n+1):
// # Cập nhật hệ số tổ hợp C(n,k) từ C(n,k-1) bằng công thức:
// # C(n,k) = C(n,k-1) * (n-k+1) / k
// A = A * (n - k + 1) // k

// # Cộng thêm nums[k] * C(n,k) vào kết quả
// # Vì đề yêu cầu %10, nên lấy ans mod 10 để không bị tràn số
// ans = (ans + nums[k] * A) % 10

// return ans

// # Ok 👍 mình giải thích đề **LeetCode 2221 – Find Triangular Sum of an
// Array** cho bạn:

// # ---

// # ### 📝 Đề bài:

// # Bạn được cho một mảng `nums` gồm **n số nguyên (0–9)**.
// # Ta thực hiện quá trình **giảm dần mảng** cho đến khi còn **1 phần tử duy
// nhất**:

// # 1. Ở mỗi bước, tạo ra một mảng mới ngắn hơn 1 phần tử.
// # 2. Phần tử mới ở vị trí `i` được tính bằng:

// # [
// # new[i] = (nums[i] + nums[i+1]) \mod 10
// # ]

// # 3. Thay `nums` bằng mảng mới vừa tạo.
// # 4. Tiếp tục cho đến khi chỉ còn 1 phần tử → đó là **triangular sum**.

// # ---

// # ### 🔍 Ví dụ:

// # **Input:**

// # ```
// # nums = [1,2,3,4,5]
// # ```

// # 👉 Quá trình:

// # * Bước 1: `[ (1+2)%10, (2+3)%10, (3+4)%10, (4+5)%10 ] = [3,5,7,9]`
// # * Bước 2: `[ (3+5)%10, (5+7)%10, (7+9)%10 ] = [8,2,6]`
// # * Bước 3: `[ (8+2)%10, (2+6)%10 ] = [0,8]`
// # * Bước 4: `[ (0+8)%10 ] = [8]`

// # ✅ Output = `8`

// # ---

// # ### 🎯 Yêu cầu:

// # Trả về **phần tử cuối cùng** sau khi thực hiện hết quá trình.

// # ---

// # 👉 Đây thực chất giống như việc **xây dựng một tam giác số** từ trên xuống,
// giống Pascal Triangle nhưng lấy `% 10`.

// # ---

// # Bạn muốn mình giải thích cách **giải brute force (mô phỏng)** hay cách
// **tối ưu bằng tổ hợp (binomial coefficient)**?

// ### 📌 Đề bài (LeetCode 2221)

// # * Bạn có một mảng `nums`.
// # * Tạo một **tam giác số**:

// # * Ở mỗi hàng mới, phần tử thứ `i` được tính = `(nums[i] + nums[i+1]) % 10`.
// # * Làm đến khi còn đúng **1 số** → đó là đáp án.

// # Ví dụ:

// # ```
// # nums = [1,2,3,4,5]

// # [1,2,3,4,5]
// # [3,5,7,9]
// # [8,2,6]
// # [0,8]
// # [8] ← kết quả
// # ```

// # ---

// # ### 📌 Ý tưởng thuật toán

// # Thay vì mô phỏng từng bước (O(n²)), ta có thể nhận ra:

// # * Mỗi số ở dòng cuối cùng thực chất là **tổ hợp tuyến tính** của các số ban
// đầu `nums[k]`.
// # * Cụ thể, kết quả cuối cùng chính là:
// # [
// # \text{Result} = \sum_{k=0}^{n} C(n, k) \cdot nums[k] ;;; \pmod{10}
// # ]

// # Trong đó:

// # * `n = len(nums) - 1` (số lần giảm mảng).
// # * `C(n,k)` là hệ số **tổ hợp** (binomial coefficient).

// # 👉 Đây chính là **Định lý nhị thức Newton**:
// # [
// # (x + y)^n = \sum_{k=0}^n C(n,k) \cdot x^{n-k} y^k
// # ]
// # Ở đây, ta coi việc cộng dồn các phần tử giống như đang khai triển nhị thức.

// # ---

// # ### 📌 Giải thích từng bước trong code

// # ```python
// # n = len(nums) - 1
// # ```

// # * Cần giảm mảng `n` lần để còn 1 số.

// # ```python
// # ans, A = nums[0], 1
// # ```

// # * `ans` = đóng góp của `nums[0] * C(n,0)` (ban đầu hệ số = 1).
// # * `A` = giá trị hiện tại của `C(n,k)` (tổ hợp).

// # ```python
// # for k in range(1, n+1):
// # A = A * (n - k + 1) // k
// # ```

// # * Tính lần lượt các hệ số tổ hợp `C(n,k)` từ `C(n,k-1)`:
// # [
// # C(n,k) = C(n,k-1) \cdot \frac{n-k+1}{k}
// # ]

// # ```python
// # ans = (ans + nums[k] * A) % 10
// # ```

// # * Cộng đóng góp của `nums[k]` với hệ số tổ hợp `C(n,k)`.
// # * Lấy `% 10` vì chỉ cần chữ số cuối.

// # ```python
// # return ans
// # ```

// # * Trả về kết quả cuối cùng.

// # ---

// # ### 📌 Độ phức tạp

// # * Thời gian: **O(n)** (chỉ duyệt 1 vòng).
// # * Không gian: **O(1)** (chỉ dùng vài biến).
// # * Nhanh hơn nhiều so với cách mô phỏng tam giác (**O(n²)**).

// # ---

// # 👉 Tóm lại:
// # Thuật toán dùng **công thức tổ hợp** để tính trực tiếp kết quả cuối cùng
// của quá trình "tam giác hóa", tránh phải mô phỏng từng bước.

// # ---

// # Bạn có muốn mình vẽ thử một ví dụ nhỏ (vd: `nums = [2,5,3]`) theo cả **cách
// mô phỏng** và **cách tổ hợp** để thấy chúng ra kết quả giống nhau không?
