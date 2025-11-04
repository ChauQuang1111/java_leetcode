
// 3318. Find X-Sum of All K-Long Subarrays I(04/11/2025)
// Dưới đây là **giải thích chi tiết** bằng tiếng Việt cho bài 3318. Find X‑Sum of All K‑Long Subarrays I (LeetCode) — cách hiểu đề, các khái niệm và ví dụ minh họa.
import java.util.*;

public class b88 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();
        int x = sc.nextInt();

        int[] result = findXSum(nums, k, x);

        System.out.println(Arrays.toString(result));
    }

    // Hàm chính tính X-Sum cho từng cửa sổ độ dài k
    public static int[] findXSum(int[] nums, int k, int x) {
        int[] result = new int[nums.length - k + 1];

        // Duyệt qua tất cả các subarray có độ dài k
        for (int i = 0; i < result.length; i++) {
            int left = i, right = i + k - 1;
            // Tính X-Sum của đoạn con [left, right]
            result[i] = findXSumofSubArray(nums, left, right, x);
        }

        return result;
    }

    // Hàm phụ tính X-Sum cho một subarray cụ thể
    public static int findXSumofSubArray(int[] nums, int left, int right, int x) {
        int sum = 0, distinctCount = 0;

        // freq[val] = số lần xuất hiện của giá trị val trong đoạn con
        int[] freq = new int[51]; // vì giá trị nums[i] nằm trong [1..50]

        // Bước 1: Đếm tần suất và tính tổng ban đầu
        for (int i = left; i <= right; i++) {
            sum += nums[i];
            if (freq[nums[i]] == 0) {
                distinctCount++;
            }
            freq[nums[i]]++;
        }

        // Nếu số phần tử khác nhau < x → lấy tất cả phần tử
        if (distinctCount < x) {
            return sum;
        }

        // Bước 2: Ngược lại, chọn ra x phần tử có tần suất cao nhất
        sum = 0; // reset sum để tính lại theo quy tắc X-Sum

        for (int pick = 0; pick < x; pick++) {
            int bestFreq = -1; // tần suất cao nhất hiện tại
            int bestVal = -1; // giá trị tương ứng

            // Duyệt ngược từ 50 về 1 để ưu tiên giá trị lớn khi tần suất bằng nhau
            for (int val = 50; val >= 1; val--) {
                if (freq[val] > bestFreq) {
                    bestFreq = freq[val];
                    bestVal = val;
                }
            }

            // Nếu tìm thấy giá trị hợp lệ → cộng vào tổng X-Sum
            if (bestVal != -1) {
                sum += bestVal * bestFreq;
                freq[bestVal] = 0; // loại bỏ phần tử đã chọn để chọn tiếp phần tử khác
            }
        }

        return sum; // trả về X-Sum của subarray này
    }
}

// ---

// ## 📄 Mô tả đề

// Cho:

// * Một mảng số nguyên `nums` độ dài `n`.
// * Hai số nguyên `k` và `x`.

// Định nghĩa **x-sum** của một mảng con là:

// 1. Đếm số lần xuất hiện (frequency) của mỗi phần tử trong mảng con.
// 2. Giữ lại **x phần tử có tần suất cao nhất** (nếu hai phần tử có cùng tần
// suất, phần tử có giá trị lớn hơn được ưu tiên). Nếu mảng con có ít hơn `x`
// phần tử phân biệt thì giữ tất cả.
// 3. Tính tổng các phần tử *giữ lại*, tức là mỗi phần tử *value* nhân với số
// lần nó xuất hiện trong mảng con, rồi cộng lại.

// Yêu cầu: Trả về một mảng `answer` độ dài `n − k + 1`, sao cho `answer[i]` là
// x-sum của subarray `nums[i..i + k − 1]`. ([AlgoMonster][1])

// ---

// ## 🧠 Vấn đề cần giải quyết

// * Ta sẽ xét tất cả các subarray liên tiếp dài `k`.
// * Với mỗi subarray, cần tính x-sum như định nghĩa.
// * Nếu làm “naive” cho mỗi subarray một lần: đếm tần suất + sắp xếp hoặc tìm
// top x → sẽ tốn nhiều thời gian khi `n` lớn.
// * Cần tối ưu bằng cách sử dụng kỹ thuật sliding window + cấu trúc dữ liệu để
// cập nhật nhanh khi cửa sổ trượt.

// ---

// ## ✏️ Ví dụ minh họa

// Ví dụ 1:

// ```
// nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
// ```

// * Subarray đầu: `[1,1,2,2,3,4]`

// * Đếm: 1→2 lần, 2→2 lần, 3→1, 4→1
// * Top 2 tần suất: 1 và 2 (cả 2 lần) → giữ lại 1 và 2
// * x-sum = 1*2 + 2*2 = 2 + 4 = **6**
// * Subarray thứ 2: `[1,2,2,3,4,2]`

// * Đếm: 1→1, 2→3, 3→1, 4→1
// * Top 2: 2 (3 lần) và (giá trị lớn hơn trong các 1 lần) là 4 → giữ lại 2 và 4
// * x-sum = 2*3 + 4*1 = 6 + 4 = **10**
// * Subarray thứ 3: `[2,2,3,4,2,3]`

// * Đếm: 2→3, 3→2, 4→1
// * Top 2: 2 và 3 → x-sum = 2*3 + 3*2 = 6 + 6 = **12**

// ⇒ Kết quả: `[6,10,12]`. ([AlgoMonster][1])

// ---

// ## ✅ Tóm tắt đề

// * Slide cửa sổ độ dài `k` qua `nums`.
// * Với mỗi vị trí cửa sổ:

// 1. Tính tần suất từng phần tử trong cửa sổ.
// 2. Xác định `x` phần tử (distinct) có tần suất cao nhất (tie‐break: giá trị
// lớn hơn).
// 3. Tính tổng value * freq cho các phần tử đó.
// * Trả về mảng với mỗi cửa sổ một giá trị.

// ---

// Nếu bạn muốn, mình có thể **viết pseudocode** hoặc **đưa ví dụ code bằng Java
// hoặc Python** cho thuật toán tối ưu của bài này — bạn muốn định dạng nào?

// Rất hay — đây là **một lời giải gọn gàng và dễ hiểu** cho bài *3318. Find
// X-Sum of All K-Long Subarrays I*.
// Mình sẽ **giải thích thuật toán chi tiết từng phần** để bạn hiểu rõ cách hoạt
// động của đoạn code trên 👇

// ---

// ## 🧩 Ý tưởng tổng quát

// Ta cần tính **X-Sum** cho *mỗi subarray liên tiếp độ dài k* trong mảng
// `nums`.
// **X-Sum** được định nghĩa là:

// * Đếm tần suất xuất hiện của từng số trong subarray.
// * Giữ lại **x phần tử có tần suất cao nhất** (nếu có tần suất bằng nhau, chọn
// **giá trị lớn hơn** trước).
// * Tính tổng `value * freq` của các phần tử này.

// ---

// ## 🧱 Cấu trúc chương trình

// ### 1️⃣ Hàm `findXSum`

// ```java
// for (int i = 0; i < result.length; i++) {
// int left = i, right = i + k - 1;
// result[i] = findXSumofSubArray(nums, left, right, x);
// }
// ```

// * Trượt một cửa sổ dài `k` qua mảng `nums`.
// * Mỗi lần, ta xác định ranh giới trái (`left`) và phải (`right`) của cửa sổ
// con.
// * Gọi hàm phụ `findXSumofSubArray()` để tính X-Sum của đoạn con đó.
// * Kết quả được lưu vào mảng `result`.

// ---

// ### 2️⃣ Hàm `findXSumofSubArray`

// Đây là phần chính xử lý logic.

// #### Bước 1. Tính tổng và đếm tần suất

// ```java
// int sum = 0, distinctCount = 0;
// int[] freq = new int[51]; // vì giá trị nums[i] ∈ [1..50]

// for (int i = left; i <= right; i++) {
// sum += nums[i];
// if (freq[nums[i]] == 0) distinctCount++;
// freq[nums[i]]++;
// }
// ```

// * `freq[val]` lưu số lần xuất hiện của giá trị `val`.
// * `sum` ban đầu tính tổng tất cả phần tử trong cửa sổ.
// * `distinctCount` đếm số phần tử khác nhau.

// #### Bước 2. Nếu số phần tử khác nhau < x

// ```java
// if (distinctCount < x) return sum;
// ```

// → Nếu số phần tử phân biệt ít hơn `x`, ta lấy tất cả, nên **X-Sum = tổng toàn
// bộ phần tử trong subarray**.

// ---

// #### Bước 3. Ngược lại, chọn ra x phần tử tần suất cao nhất

// ```java
// sum = 0;
// for (int pick = 0; pick < x; pick++) {
// int bestFreq = -1;
// int bestVal = -1;

// for (int val = 50; val >= 1; val--) {
// if (freq[val] > bestFreq) {
// bestFreq = freq[val];
// bestVal = val;
// }
// }

// if (bestVal != -1) {
// sum += bestVal * bestFreq;
// freq[bestVal] = 0; // loại bỏ phần tử đã chọn
// }
// }
// ```

// 🧠 **Giải thích logic bên trong vòng lặp:**

// * Với mỗi lần chọn (`pick`):

// * Quét qua tất cả các giá trị `1 → 50`.
// * Tìm phần tử có `freq` cao nhất (nếu tần suất bằng nhau, vì duyệt từ `50 →
// 1`, phần tử lớn hơn được ưu tiên).
// * Tính `sum += bestVal * bestFreq`.
// * Đặt `freq[bestVal] = 0` để loại bỏ phần tử này, chuẩn bị tìm phần tử tiếp
// theo.
// * Lặp lại `x` lần → lấy ra top `x` phần tử theo tần suất.

// ---

// ### 3️⃣ Trả kết quả

// Khi hoàn thành `x` lần chọn, `sum` chính là **X-Sum của subarray hiện tại**,
// được trả về cho hàm chính.

// ---

// ## 🧮 Ví dụ minh họa

// ```
// nums = [1, 1, 2, 2, 3, 4, 2, 3]
// k = 6, x = 2
// ```

// Cửa sổ đầu tiên: `[1,1,2,2,3,4]`

// * Tần suất:

// ```
// 1 → 2
// 2 → 2
// 3 → 1
// 4 → 1
// ```
// * distinctCount = 4 ≥ 2 → cần chọn top 2 phần tử có tần suất cao nhất.

// Chọn:

// * `bestVal = 2, bestFreq = 2 → sum = 4`
// * `bestVal = 1, bestFreq = 2 → sum = 4 + 2 = 6`

// ✅ X-Sum = 6 → `result[0] = 6`

// ---

// ## ⏱️ Độ phức tạp

// * Với mỗi cửa sổ:

// * Đếm tần suất: `O(k)`
// * Tìm top x phần tử: mỗi lần chọn quét 50 giá trị → `O(50 * x)`
// * Tổng: `O(n * (k + 50x))`, đủ nhanh vì `n, k ≤ 100` trong phiên bản I.

// ---

// ## ✅ Tóm tắt lại

// | Bước | Mục đích | Ghi chú |
// | ------------------ | ----------------------------------- |
// ---------------------- |
// | Đếm tần suất | Biết mỗi số xuất hiện bao nhiêu lần | `freq[val]` |
// | Kiểm tra distinct | Nếu ít hơn x → lấy hết | `return sum` |
// | Chọn top x phần tử | Ưu tiên tần suất cao → giá trị lớn | Duyệt ngược từ 50
// về 1 |
// | Cập nhật tổng | `sum += value * freq` | Kết quả X-Sum |

// ---

// Bạn có muốn mình thêm **phiên bản tối ưu hơn (sliding window + cập nhật tần
// suất khi trượt)** cho phần II của bài này không (bài 3319)?
