
// // #  Count Partitions With Max-Min Difference at Most K(06/12/2025)
// // #  Dưới đây là **giải thích đề bài "Count Partitions With Max–Min Difference at Most K"** theo cách dễ hiểu nhất cho bạn.
// // # Dưới đây là **giải thích thuật toán của lời giải**, kèm **code đã được chú thích chi tiết từng dòng** để bạn hiểu rõ toàn bộ cách hoạt động.
// Dưới đây là **giải thích cực chi tiết** thuật toán cho code Java của bạn trong bài
// **3578. Count Partitions With Max–Min Difference at Most K**.
import java.util.*;

public class b110 {
    static Scanner sc = new Scanner(System.in);
    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int k = sc.nextInt();

        // Nhập mảng nums
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        sc.close();

        int result = countPartitions(nums, k);

        System.out.println(result); // in kết quả
    }

    public static int countPartitions(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n + 1]; // dp[i] = số cách chia đến vị trí i-1
        int[] psum = new int[n + 1]; // prefix sum của dp để tính nhanh

        dp[0] = 1; // Chưa lấy phần tử nào thì có 1 cách chia (mặc định)
        psum[0] = 1;

        // Hai deque để giữ min và max trong cửa sổ trượt
        Deque<Integer> minDq = new ArrayDeque<>();
        Deque<Integer> maxDq = new ArrayDeque<>();

        int l = 0; // left pointer của sliding window

        for (int r = 0; r < n; r++) {

            // Giữ deque min tăng dần
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[r]) {
                minDq.pollLast();
            }
            minDq.addLast(r);

            // Giữ deque max giảm dần
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[r]) {
                maxDq.pollLast();
            }
            maxDq.addLast(r);

            // Thu nhỏ cửa sổ đến khi thỏa max - min <= k
            while (nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] > k) {

                if (minDq.peekFirst() == l)
                    minDq.pollFirst();
                if (maxDq.peekFirst() == l)
                    maxDq.pollFirst();

                l++; // cửa sổ bắt đầu từ l+1
            }

            // Tính dp[r+1]
            int prev = (l - 1 >= 0) ? psum[l - 1] : 0;

            // dp[r+1] = psum[r] - psum[l-1]
            dp[r + 1] = (psum[r] - prev + mod) % mod;

            // cập nhật prefix sum
            psum[r + 1] = (psum[r] + dp[r + 1]) % mod;
        }

        return dp[n];
    }
}

// Mình sẽ giải thích theo từng bước rõ ràng, dễ hiểu.

// ---

// # 🎯 Mục tiêu bài toán

// Đếm số cách chia mảng thành các nhóm (partition) sao cho:

// ```
// Trong mỗi nhóm: max − min ≤ k
// ```

// ---

// # 🧠 Ý tưởng cốt lõi của lời giải

// Ta dùng **DP + prefix sum + sliding window (deque)**

// ## 1️⃣ Sliding window + 2 deque

// Giúp tìm đoạn `[l…r]` dài nhất sao cho:

// ```
// max(nums[l..r]) - min(nums[l..r]) ≤ k
// ```

// → Như vậy **l** là vị trí bắt đầu hợp lệ nhỏ nhất cho nhóm kết thúc tại
// **r**.

// ---

// ## 2️⃣ DP:

// ### Định nghĩa:

// ```
// dp[i] = số cách chia mảng nums[0..i-1]
// ```

// Chỉ tính partition tại **vị trí i**, tức là r+1.

// ### Chọn vị trí chia cuối:

// Ta cần số cách chia sao cho **partition cuối cùng** (tức nhóm kết thúc tại r)
// bắt đầu từ một vị trí hợp lệ `j`:

// ```
// j ∈ [l .. r]
// ```

// Vậy tổng số cách tạo partition kết thúc tại r là:

// ```
// dp[r+1] = dp[l] + dp[l+1] + ... + dp[r]
// ```

// → Đây là lý do dùng prefix sum để tính nhanh:

// ```
// psum[i] = dp[0] + dp[1] + ... + dp[i]
// ```

// ---

// # ✔️ Giải thích từng phần trong code

// ---

// ## 🔸 Khởi tạo

// ```java
// dp[0] = 1;
// psum[0] = 1;
// ```

// Ý nghĩa:

// * Không có phần tử ⇒ có 1 cách chia (không chia gì cả)
// * dp[0] luôn = 1 trong bài đếm partition

// ---

// ## 🔸 Hai deque để quản lý min và max

// * `minDq`: deque tăng dần → đầu chứa giá trị nhỏ nhất
// * `maxDq`: deque giảm dần → đầu chứa giá trị lớn nhất

// ```java
// while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[r])
// minDq.pollLast();
// minDq.addLast(r);
// ```

// → loại bỏ các phần tử lớn hơn nums[r], vì chúng không thể là min sau này.

// ```java
// while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[r])
// maxDq.pollLast();
// maxDq.addLast(r);
// ```

// → loại bỏ các phần tử nhỏ hơn nums[r], vì chúng không thể là max sau này.

// ---

// ## 🔸 Điều chỉnh cửa sổ sao cho max − min ≤ k

// ```java
// while (nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] > k) {

// if (minDq.peekFirst() == l) minDq.pollFirst();
// if (maxDq.peekFirst() == l) maxDq.pollFirst();

// l++;
// }
// ```

// Nếu max-min vượt quá k → dịch cửa sổ sang phải
// → Tìm ra **l**, vị trí bắt đầu nhỏ nhất để nhóm `[l..r]` hợp lệ.

// ---

// ## 🔸 Tính dp[r+1]

// Chúng ta cần:

// ```
// dp[l] + dp[l+1] + ... + dp[r]
// ```

// Dùng prefix sum:

// ```java
// int prev = (l - 1 >= 0) ? psum[l - 1] : 0;

// dp[r + 1] = (psum[r] - prev + mod) % mod;
// ```

// Ý nghĩa:

// ```
// psum[r] = dp[0] + dp[1] + ... + dp[r]
// psum[l-1] = dp[0] + ... + dp[l-1]
// → psum[r] - psum[l-1] = dp[l] + ... + dp[r]
// ```

// Tức là số cách chia kết thúc tại r.

// ---

// ## 🔸 Cập nhật prefix sum

// ```java
// psum[r + 1] = (psum[r] + dp[r + 1]) % mod;
// ```

// ---

// ## 🔸 Kết quả

// ```java
// return dp[n];
// ```

// dp[n] = số cách chia toàn mảng.

// ---

// # 🟢 **Tóm tắt thuật toán theo từng bước**

// 1. Duyệt r từ trái sang phải.
// 2. Dùng deque để duy trì min/max trong đoạn [l…r].
// 3. Nếu max-min > k → tăng l.
// 4. Tính dp[r+1] = tổng dp của đoạn [l..r].
// 5. Dùng prefix sum để tính nhanh.
// 6. Trả về dp[n].

// ---

// # 🌟 Độ phức tạp

// * Mỗi phần tử vào/ra deque 1 lần → O(n)
// * Tính dp và psum → O(n)

// ➡️ **O(n)** tổng thể.

// ---

// Nếu bạn muốn, mình có thể:

// ✔️ Thêm chú thích vào code
// ✔️ Viết lại code cho dễ đọc
// ✔️ Làm ví dụ chạy thử từng bước (step-by-step)

// Bạn muốn phần nào tiếp theo?

// # ---

// # # 🧠 **Ý tưởng chính của thuật toán**

// # Chúng ta cần đếm **số cách chia mảng thành các nhóm liên tiếp**, sao cho
// mỗi nhóm thỏa:

// # ```
// # max(group) − min(group) ≤ k
// # ```

// # Đây là dạng bài:

// # * cần đếm số partition → dùng DP
// # * cần kiểm tra max–min trong đoạn [left…right] → dùng **deque** để giữ min
// và max dạng sliding window

// # Cách này giúp ta xử lý mọi đoạn **O(n)** thay vì O(n²).

// # ---

// # # 🎯 Giải thích từng thành phần

// # ## 1️⃣ **Dùng sliding window để đảm bảo max–min ≤ k**

// # * `mxQueue`: deque lưu các phần tử theo thứ tự giảm dần → phần tử đầu là
// **max**.
// # * `mnQueue`: deque lưu theo thứ tự tăng dần → phần tử đầu là **min**.

// # Mỗi lần thêm phần tử mới:

// # * Loại bỏ các phần tử không còn phù hợp ở cuối deque.
// # * Thêm phần tử vào cuối.

// # → Từ đó ta lấy được `max = mxQueue[0]`, `min = mnQueue[0]`.

// # Nếu:

// # ```
// # max - min > k
// # ```

// # → Ta phải dịch `left++`, đồng thời loại phần tử đó khỏi deque nếu cần.

// # ---

// # ## 2️⃣ **DP để đếm số cách**

// # * `dp[i]` = số cách chia **nums[0..i]**

// # Ý tưởng:

// # Tại mỗi vị trí `r`, nếu ta có `left` là biên trái **nhỏ nhất** sao cho đoạn
// [left…r] hợp lệ, thì:

// # → Ta có thể chọn chia hoặc không chia trước r
// # → Khi thêm 1 phần tử → số cách tăng gấp đôi
// # Nhưng phải đảm bảo không tính các đoạn invalid → dùng biến `cnt`.

// # `cnt` = tổng số cách chia của tất cả dp thuộc vùng cửa sổ hợp lệ.

// # ---

// # ## 3️⃣ **Pourquoi gấp đôi?**

// # Khi thêm nums[r]:

// # * Mỗi cách chia cũ → vẫn tồn tại
// # * Và ta có thể chọn thêm một partition mới kết thúc ở r → tạo thêm số cách
// bằng chính dp[r]

// # Nhưng để đúng, ta chỉ được phép nhân đôi khi **window hợp lệ**.
// # Khi window bị nới vì max-min > k → ta phải loại bỏ số cách bắt đầu từ
// `left`, vì chúng không còn hợp lệ.

// # ---

// # # 🟢 **Code đã chú thích đầy đủ**

// # ```python
// from typing import List
// from collections import deque
// class Solution:
// def countPartitions(self, nums: List[int], k: int) -> int:

// left = 0 # biên trái của sliding window
// cnt = 1 # số cách hợp lệ hiện tại cho cửa sổ
// mod_ = 1_000_000_007 # modulo
// mnQueue, mxQueue = deque(), deque()
// dp = [cnt] # dp[0] = 1 (mặc định có 1 cách: 1 partition đầu tiên)

// for rght, num in enumerate(nums):

// # ---- cập nhật max deque ----
// # loại bỏ phần tử nhỏ hơn num ở cuối vì chúng không thể là max
// while mxQueue and num > mxQueue[-1]:
// mxQueue.pop()
// mxQueue.append(num)

// # ---- cập nhật min deque ----
// # loại bỏ phần tử lớn hơn num ở cuối vì chúng không thể là min
// while mnQueue and num < mnQueue[-1]:
// mnQueue.pop()
// mnQueue.append(num)

// # ---- kiểm tra điều kiện max - min > k ----
// # nếu vượt quá k, ta phải dịch cửa sổ sang phải
// while mxQueue[0] - mnQueue[0] > k:
// # loại dp[left] khỏi cnt vì left không còn nằm trong cửa sổ hợp lệ
// cnt -= dp[left]

// # nếu phần tử rời đi là max hoặc min thì pop khỏi deque
// if nums[left] == mxQueue[0]:
// mxQueue.popleft()
// if nums[left] == mnQueue[0]:
// mnQueue.popleft()

// left += 1 # dịch cửa sổ sang phải

// # ---- thêm số cách mới ----
// dp.append(cnt) # dp[r] = số cách hợp lệ trước khi nhân đôi
// cnt *= 2 # gấp đôi số cách vì thêm nums[r] → có thể chia hoặc không chia tại
// đây
// cnt %= mod_ # mod để tránh tràn số

// # dp[-1] = tổng số cách chia toàn mảng
// return dp[-1] % mod_

// # 📌 **Tóm tắt logic**

// # 1. Dùng hai deque để giữ max và min trong cửa sổ.
// # 2. Nếu max−min > k → đẩy left lên.
// # 3. `dp[r] = cnt` là số cách hợp lệ hiện tại.
// # 4. `cnt *= 2` vì mỗi cách cũ có thêm lựa chọn:

// # * tiếp tục không chia
// # * chia tại r
// # 5. Khi left tăng → bỏ đi dp[left] khỏi cnt để tránh tính cách chia invalid.

// # ---

// # Nếu bạn muốn, mình có thể:

// # ✔️ làm ví dụ minh họa từng bước
// # ✔️ vẽ bảng dp
// # ✔️ giải thích bằng hình sliding window

// # Bạn muốn phần nào?

// # ---

// # # 🧠 **Đề bài nói gì?**

// # Bạn được cho:

// # * Một mảng số nguyên `nums`
// # * Một số `k`

// # Bạn phải **chia mảng thành nhiều nhóm (partitions)** sao cho:

// # 👉 **Trong mỗi nhóm, hiệu giữa phần tử lớn nhất và nhỏ nhất của nhóm ≤ k**

// # Và câu hỏi là:

// # 👉 **Có bao nhiêu cách chia mảng thỏa mãn điều kiện đó?**

// # ---

// # # 📌 Quan trọng: Các nhóm phải theo thứ tự ban đầu

// # Tức là bạn không được đảo vị trí phần tử.

// # Ví dụ:

// # ```
// # nums = [1, 3, 6]
// # ```

// # # Chỉ được chia như:

// # * `[1] | [3] | [6]`
// # * `[1, 3] | [6]`
// # * `[1] | [3, 6]`
// # # * `[1, 3, 6]`

// # Không được chia kiểu:

// # ```
// # [1, 6] | [3] ❌ không đúng
// # ```

// # vì không giữ thứ tự ban đầu.

// # ---

// # # 🎯 Điều kiện của mỗi nhóm:

// # Giả sử nhóm đó là:

// # ```
// # [ a, b, c, d ]
// # ```

// # thì nhóm hợp lệ nếu:

// # ```
// # max(group) - min(group) ≤ k
// # ```

// # ---

// # # 📘 Ví dụ đơn giản

// # ### Ví dụ 1

// # ```
// # nums = [1, 2, 3]
// # k = 2
// # ```

// # Tất cả nhóm đều có max-min ≤ 2 nên mọi cách chia đều hợp lệ:

// # Các cách chia:

// # 1. `[1] [2] [3]`
// # 2. `[1, 2] [3]`
// # 3. `[1] [2, 3]`
// # 4. `[1, 2, 3]`

// # → **4 cách**

// # ---

// # ### Ví dụ 2

// # ```
// # nums = [1, 5, 6]
// # k = 3
// # ```

// # Kiểm tra từng nhóm:

// # * `[1]` → ok
// # * `[1,5]` → max-min = 5−1 = 4 > 3 ❌
// # * `[1,5,6]` → 6−1 = 5 > 3 ❌
// # * `[5,6]` → 6−5 = 1 ≤ 3 ✔

// # Các cách chia hợp lệ:

// # 1. `[1] [5] [6]`
// # 2. `[1] [5, 6]`

// # → **2 cách**

// # ---

// # # 🧠 Cần kỹ thuật gì để giải?

// # Để giải bài này thường dùng:

// # ### ✔️ DP (Dynamic Programming)

// # Ở mỗi vị trí i, ta xét xem có thể ghép bao nhiêu phần tử về bên trái để tạo
// 1 nhóm hợp lệ.

// # ### ✔️ Hai con trỏ / Sliding window

// # Để tìm đoạn [l…r] sao cho max-min ≤ k.

// # ---

// # # 📝 Tóm tắt đề bài (ngắn gọn)

// # > Đếm số cách chia mảng thành các nhóm liên tiếp sao cho:
// # > Trong mỗi nhóm, hiệu giữa phần tử lớn nhất và nhỏ nhất không vượt quá
// `k`.

// # ---

// # Nếu bạn muốn, mình có thể:

// # ✅ Viết lời giải DP (Python / Java)
// # ✅ Giải thích từng bước bằng ví dụ
// # ✅ Thêm code có chú thích đầy đủ

// # Bạn muốn loại nào?
