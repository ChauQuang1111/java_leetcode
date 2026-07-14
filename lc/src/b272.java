// Đề bài Find the Number of Subsequences With Equal GCD  (14/07/2026)
// Khái niệm 1: Subsequence là gì?
// Subsequence (dãy con) là dãy được tạo bằng cách xóa một số phần tử hoặc không xóa phần tử nào, nhưng giữ nguyên thứ tự.
// Ví dụ:

// nums = [2, 3, 4]
// Một số subsequence là:

// []
// [2]
// [3]
// [4]
// [2,3]
// [2,4]
// [3,4]
// [2,3,4]
// Lưu ý:

// [3,2]
// không phải subsequence vì đổi thứ tự.
// Khái niệm 2: GCD là gì?
// GCD (Greatest Common Divisor) là ước chung lớn nhất.
// Ví dụ

// GCD(6,9)=3

// GCD(8,12)=4

// GCD(2,4,6)=2
// Đề bài yêu cầu gì?
// Cho một mảng

// nums
// Hãy chia các phần tử của mảng thành hai subsequence:

// A
// B
// thỏa mãn:

// A không rỗng
// B không rỗng
// A và B không giao nhau (mỗi phần tử chỉ thuộc một trong hai)
// GCD(A) = GCD(B)
// Đếm xem có bao nhiêu cách chia như vậy.
// Ví dụ
// Giả sử

// nums = [2,4]
// Có thể chia

// Cách 1
// A = [2]

// B = [4]

// GCD(A)=2

// GCD(B)=4

// Không bằng nhau
// Không tính.
// Cách 2
// A=[2,4]

// B=[]
// B rỗng
// Không hợp lệ.
// Cách 3
// A=[]

// B=[2,4]
// A rỗng
// Không hợp lệ.
// Kết quả

// 0
// Ví dụ khác
// nums = [2,2]
// Chia

// A=[2]

// B=[2]
// GCD

// GCD(A)=2

// GCD(B)=2
// Hai GCD bằng nhau.
// Đây là 1 cách hợp lệ.
// Ví dụ nữa
// nums = [2,4,6]
// Một cách chia

// A=[2,4]

// B=[6]
// GCD

// GCD(2,4)=2

// GCD(6)=6
// Không bằng.
// Một cách khác

// A=[2]

// B=[4,6]
// GCD

// 2

// GCD(4,6)=2
// Hai GCD bằng nhau.
// Đây là một cách hợp lệ.
// Ý tưởng bài toán
// Mỗi phần tử có 3 lựa chọn:

// Đưa vào A

// Đưa vào B

// Không chọn
// Trong quá trình chọn, phải theo dõi

// GCD của A

// GCD của B
// Khi duyệt hết mảng:

// A không rỗng
// B không rỗng
// gcdA == gcdB
// thì tăng đáp án.
// Tóm tắt đề bài
// Cho mảng

// nums
// Hãy đếm số cách chọn hai subsequence không rỗng A và B (không dùng chung phần tử) sao cho:

// GCD(A) == GCD(B)
// Mỗi phần tử chỉ có thể:

// thuộc A,
// hoặc thuộc B,
// hoặc không thuộc subsequence nào.
// Đáp án là tổng số cách thỏa mãn điều kiện trên.
// Đây là một bài Hard của LeetCode. Đoạn code trên sử dụng Möbius Inversion (Hàm Möbius) + Inclusion-Exclusion (Bao hàm - Loại trừ), khá nâng cao. Vì vậy trước tiên mình sẽ giải thích ý tưởng tổng quát, sau đó mới chú thích từng phần của code.
// Ý tưởng thuật toán
// Ta cần đếm số cách chọn hai subsequence không giao nhau sao cho:

// gcd(A) = gcd(B)
// Nếu duyệt mọi cách chọn thì có:

// Mỗi phần tử có 3 lựa chọn:
// vào A
// vào B
// không chọn
// => Có

// 3^n
// trường hợp.
// Điều này quá lớn.
// Bước 1. Đếm bội số
// Ví dụ

// nums = [2,4,6,8]
// Muốn biết có bao nhiêu số chia hết cho 2.
// Không cần duyệt mỗi lần.
// Ta tính trước

// cnt[1] = 4

// cnt[2] = 4

// cnt[3] = 1

// cnt[4] = 2

// cnt[5] = 0

// cnt[6] = 1
// Trong đó

// cnt[i]
// nghĩa là

// Có bao nhiêu phần tử là bội của i.
// Bước 2. Tính số cách có gcd chia hết cho g1 và g2
// Giả sử

// g1 = 2

// g2 = 3
// Ta muốn biết

// subsequence A chỉ lấy các số chia hết cho 2
// subsequence B chỉ lấy các số chia hết cho 3
// Khi đó dùng công thức

// 3^c
// và

// 2^x
// để đếm số cách chọn.
// Bước 3. Möbius Inversion
// Đến đây ta mới biết

// gcd(A) chia hết cho g1

// gcd(B) chia hết cho g2
// Nhưng đề yêu cầu

// gcd(A)=g1

// gcd(B)=g2
// Để bỏ các trường hợp "chia hết", ta dùng

// Möbius Inversion
// để chuyển

// chia hết
// thành

// bằng đúng
// Cuối cùng cộng các trường hợp

// g1==g2
// Code có chú thích
// MOD = 1_000_000_007
// MX = 201

// # lcms[i][j] = BCNN(i,j)
// lcms = [[lcm(i, j) for j in range(MX)] for i in range(MX)]

// # pow2[i] = 2^i mod MOD
// pow2 = [1] * MX

// // # pow3[i] = 3^i mod MOD
// // pow3 = [1] * MX

// // for i in range(1, MX):
// //     pow2[i] = pow2[i - 1] * 2 % MOD
// //     pow3[i] = pow3[i - 1] * 3 % MOD

// // # Hàm Möbius
// // mu = [0] * MX
// // mu[1] = 1

// // # Tính giá trị Möbius
// // for i in range(1, MX):
// //     for j in range(i * 2, MX, i):
// //         mu[j] -= mu[i]


// // class Solution:
// //     def subsequencePairCount(self, nums: List[int]) -> int:

// //         # Giá trị lớn nhất trong mảng
// //         m = max(nums)

// //         # cnt[i] = số phần tử chia hết cho i
// //         cnt = [0] * (m + 1)

// //         # Đếm số lần xuất hiện
// //         for x in nums:
// //             cnt[x] += 1

// //         # Chuyển thành:
// //         # cnt[i] = số phần tử là bội của i
// //         for i in range(1, m + 1):
// //             for j in range(i * 2, m + 1, i):
// //                 cnt[i] += cnt[j]

// //         # f[g1][g2]
// //         # = số cách chọn hai subsequence
// //         # mà gcd(A) chia hết g1
// //         # và gcd(B) chia hết g2
// //         f = [[0] * (m + 1) for _ in range(m + 1)]

// //         for g1 in range(1, m + 1):
// //             for g2 in range(1, m + 1):

// //                 # BCNN(g1,g2)
// //                 l = lcms[g1][g2]

// //                 # Bao nhiêu số chia hết cả g1 và g2
// //                 c = cnt[l] if l <= m else 0

// //                 c1 = cnt[g1]
// //                 c2 = cnt[g2]

// //                 # Công thức đếm
// //                 f[g1][g2] = (
// //                     pow3[c] *
// //                     pow2[c1 + c2 - 2 * c]
// //                     - pow2[c1]
// //                     - pow2[c2]
// //                     + 1
// //                 ) % MOD

// //         # Möbius Inversion
// //         # Loại bỏ các trường hợp gcd chỉ là bội,
// //         # giữ lại gcd đúng bằng giá trị cần tìm
// //         ans = 0

// //         for i in range(1, m + 1):
// //             for j in range(1, m // i + 1):
// //                 for k in range(1, m // i + 1):
// //                     ans += mu[j] * mu[k] * f[j * i][k * i]

// //         return ans % MOD
// // Độ phức tạp
// // Tính cnt: O(m log m)
// // Tính f: O(m²)
// // Möbius Inversion: khoảng O(m² log m)
// // Với ràng buộc của bài (max(nums) ≤ 200), thuật toán chạy rất nhanh.
// // Kiến thức cần có để hiểu hoàn toàn lời giải
// // Để tự nghĩ ra được lời giải này, bạn nên nắm các chủ đề sau:

// // GCD và LCM.
// // Bao hàm - loại trừ (Inclusion–Exclusion).
// // Đếm số bội của một số (sieve theo bội số).
// // Hàm Möbius (μ).
// // Möbius Inversion.
// // Đây là một trong những bài thuộc nhóm Toán tổ hợp + Number Theory khó nhất trên LeetCode. Nếu bạn chưa học về Möbius Inversion, việc đọc code sẽ rất khó hiểu dù đã có chú thích, vì phần cuối của thuật toán dựa trực tiếp trên công cụ toán học này.

// Đây là bài 3336. Find the Number of Subsequences With Equal GCD thuộc mức Hard. Thuật toán sử dụng Number Theory (Lý thuyết số), cụ thể là LCM, Möbius Function và Möbius Inversion.
// Ý tưởng thuật toán
// Thay vì duyệt mọi cách chia mảng thành hai subsequence (có tới 3^n cách), thuật toán đếm thông minh theo các bước sau:

// Bước 1. Đếm số phần tử là bội của mỗi số
// Ví dụ:

// nums = [2,4,6,8]
// Sau khi xử lý:

// cnt[1] = 4
// cnt[2] = 4
// cnt[3] = 1
// cnt[4] = 2
// cnt[5] = 0
// cnt[6] = 1
// cnt[8] = 1
// cnt[i] là số phần tử trong mảng chia hết cho i.
// Bước 2. Tính số cách chọn hai subsequence
// Giả sử

// g1 = 2
// g2 = 3
// Ta tính số cách chọn:

// Subsequence A gồm các số chia hết cho 2.
// Subsequence B gồm các số chia hết cho 3.
// Kết quả được lưu vào

// f[g1][g2]
// Bước 3. Möbius Inversion
// f[g1][g2] mới chỉ đếm:

// gcd(A) chia hết cho g1
// gcd(B) chia hết cho g2
// Đề bài cần:

// gcd(A) = g1
// gcd(B) = g2
// Möbius Inversion giúp loại bỏ các trường hợp là bội, chỉ giữ lại đúng giá trị GCD cần tìm.
// Code có chú thích và main
import java.util.*;

public class b272{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

       

        for (int i = 0; i < n; i++) {

            nums[i] = sc.nextInt();

        }

     

        int ans = subsequencePairCount(nums);

        System.out.println(ans);

        sc.close();
    }
 // Số dùng để lấy modulo
    public static final int MOD = 1_000_000_007;

    // Giá trị lớn nhất của nums theo đề bài
    public static final int MX = 201;

    // Lưu BCNN của mọi cặp số
    public static final int[][] lcms = new int[MX][MX];

    // pow2[i] = 2^i % MOD
    public static final int[] pow2 = new int[MX];

    // pow3[i] = 3^i % MOD
    public static final int[] pow3 = new int[MX];

    // Hàm Möbius
    public static final int[] mu = new int[MX];

    // Chỉ khởi tạo một lần
    public static boolean initialized = false;

    // Constructor
    public static void init() {

        if (initialized) return;

        initialized = true;

        // Tính trước BCNN của mọi cặp số
        for (int i = 1; i < MX; i++) {
            for (int j = 1; j < MX; j++) {
                lcms[i][j] = lcm(i, j);
            }
        }

        // Tính trước 2^i và 3^i
        pow2[0] = 1;
        pow3[0] = 1;

        for (int i = 1; i < MX; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
            pow3[i] = (int)((long)pow3[i - 1] * 3 % MOD);
        }

        // Tính hàm Möbius
        mu[1] = 1;

        for (int i = 1; i < MX; i++) {
            for (int j = i * 2; j < MX; j += i) {
                mu[j] -= mu[i];
            }
        }
    }

    public static int subsequencePairCount(int[] nums) {

        // Giá trị lớn nhất trong mảng
        int m = 0;

        for (int x : nums)
            m = Math.max(m, x);

        // cnt[i] = số phần tử chia hết cho i
        int[] cnt = new int[m + 1];

        // Đếm số lần xuất hiện
        for (int x : nums)
            cnt[x]++;

        // Chuyển thành số lượng bội của i
        for (int i = 1; i <= m; i++) {
            for (int j = i * 2; j <= m; j += i) {
                cnt[i] += cnt[j];
            }
        }

        // f[g1][g2]
        int[][] f = new int[m + 1][m + 1];

        for (int g1 = 1; g1 <= m; g1++) {

            for (int g2 = 1; g2 <= m; g2++) {

                // BCNN(g1,g2)
                int l = lcms[g1][g2];

                // Có bao nhiêu số chia hết cho cả g1 và g2
                int c = l <= m ? cnt[l] : 0;

                int c1 = cnt[g1];
                int c2 = cnt[g2];

                // Công thức đếm số cách
                long ways =
                        (long)pow3[c] *
                        pow2[c1 + c2 - 2 * c]
                        - pow2[c1]
                        - pow2[c2]
                        + 1;

                f[g1][g2] = (int)(ways % MOD);
            }
        }

        // Möbius Inversion
        long ans = 0;

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= m / i; j++) {

                for (int k = 1; k <= m / i; k++) {

                    ans += (long)mu[j] * mu[k] * f[j * i][k * i];

                }
            }
        }

        return (int)((ans % MOD + MOD) % MOD);
    }

    // Hàm tìm GCD
    private static int gcd(int a, int b) {

        while (a != 0) {

            int temp = a;

            a = b % a;

            b = temp;

        }

        return b;
    }

    // Hàm tìm BCNN
    private static int lcm(int a, int b) {

        return a / gcd(a, b) * b;

    }
}




   

// Độ phức tạp
// Tính cnt: O(m log m)
// Tính bảng f: O(m²)
// Möbius Inversion: O(m² log m)
// Trong bài này m ≤ 200, nên tổng thời gian chạy rất nhanh.

// Lưu ý: Phần khó nhất của lời giải là công thức tính f[g1][g2] và bước Möbius Inversion. Đây là kỹ thuật toán học chuyên sâu; nếu bạn chưa học về hàm Möbius và bao hàm–loại trừ, phần cuối của thuật toán sẽ khó tự suy luận chỉ từ code.


