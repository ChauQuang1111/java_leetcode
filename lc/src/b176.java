// // Longest Balanced Subarray II(11/02/2026)
// // **Longest Balanced Subarray II** là một bài toán mảng (array) khá phổ biến trong DSA, thường liên quan đến **prefix sum + hash map** để tìm đoạn con dài nhất thỏa điều kiện “cân bằng”.
// // Ok, mình sẽ **giải thích thuật toán từ chính code bạn gửi** theo kiểu đi từ tổng quan → chi tiết → ví dụ chạy tay.
// // Đây là bài **Longest Balanced Subarray II** phiên bản:
// Ok👍Mình sẽ**thêm hàm`main`dùng`Scanner`**+**chú thích chi tiết trực tiếp trong code**để bạn dễ đọc khi học thuật toán.

// Mình giữ nguyên logic của bạn,chỉ:

// *Thêm`main`*Thêm comment giải thích từng bước*Không đổi thuật toán

// ---

// ```java

import java.util.*;

public class b176 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = longestBalanced(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    // ================= SEGMENT TREE =================
    // Dùng để:
    // 1. Range update balance
    // 2. Tìm vị trí xa nhất có balance = 0
    public static class SegmentTree {
        int[] treeMin; // Lưu balance nhỏ nhất trong đoạn
        int[] treeMax; // Lưu balance lớn nhất trong đoạn
        int[] lazy; // Lazy propagation
        int n;

        public SegmentTree(int n) {
            this.n = n;
            this.treeMin = new int[4 * n];
            this.treeMax = new int[4 * n];
            this.lazy = new int[4 * n];
        }

        // Build tree từ mảng balance ban đầu
        public void build(int[] data, int node, int start, int end) {
            if (start == end) {
                treeMin[node] = data[start];
                treeMax[node] = data[start];
            } else {
                int mid = (start + end) / 2;
                build(data, 2 * node, start, mid);
                build(data, 2 * node + 1, mid + 1, end);

                treeMin[node] = Math.min(treeMin[2 * node], treeMin[2 * node + 1]);
                treeMax[node] = Math.max(treeMax[2 * node], treeMax[2 * node + 1]);
            }
        }

        // Đẩy lazy xuống con
        public void push(int node) {
            if (lazy[node] != 0) {
                int lz = lazy[node];

                // Cập nhật node trái
                treeMin[2 * node] += lz;
                treeMax[2 * node] += lz;
                lazy[2 * node] += lz;

                // Cập nhật node phải
                treeMin[2 * node + 1] += lz;
                treeMax[2 * node + 1] += lz;
                lazy[2 * node + 1] += lz;

                lazy[node] = 0;
            }
        }

        // Range update cộng thêm val vào balance
        public void update(int node, int start, int end, int l, int r, int val) {
            if (l > end || r < start)
                return;

            if (l <= start && end <= r) {
                treeMin[node] += val;
                treeMax[node] += val;
                lazy[node] += val;
                return;
            }

            push(node);
            int mid = (start + end) / 2;

            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);

            treeMin[node] = Math.min(treeMin[2 * node], treeMin[2 * node + 1]);
            treeMax[node] = Math.max(treeMax[2 * node], treeMax[2 * node + 1]);
        }

        // Tìm vị trí xa nhất >= minIdx có balance = 0
        public int findLastZero(int node, int start, int end, int minIdx) {
            if (end < minIdx)
                return -1;

            // Nếu đoạn không chứa 0
            if (treeMin[node] > 0 || treeMax[node] < 0)
                return -1;

            if (start == end) {
                return (treeMin[node] == 0) ? start : -1;
            }

            push(node);
            int mid = (start + end) / 2;

            // Tìm bên phải trước để lấy r xa nhất
            int res = findLastZero(2 * node + 1, mid + 1, end, minIdx);
            if (res != -1)
                return res;

            return findLastZero(2 * node, start, mid, minIdx);
        }
    }

    // ================= MAIN LOGIC =================
    public static int longestBalanced(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        // ===== 1. FAST CHECK DISTINCT TOÀN MẢNG =====
        Set<Integer> allE = new HashSet<>();
        Set<Integer> allO = new HashSet<>();

        for (int x : nums) {
            if ((x & 1) != 0)
                allO.add(x); // số lẻ
            else
                allE.add(x); // số chẵn
        }

        // Nếu toàn mảng đã cân bằng
        if (allE.size() == allO.size())
            return n;

        // Nếu chỉ có chẵn hoặc lẻ
        if (allE.isEmpty() || allO.isEmpty())
            return 0;

        // ===== 2. NEXT OCCURRENCE =====
        int[] nextOcc = new int[n];
        Arrays.fill(nextOcc, n);

        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            if (lastSeen.containsKey(nums[i])) {
                nextOcc[i] = lastSeen.get(nums[i]);
            }
            lastSeen.put(nums[i], i);
        }

        // ===== 3. INITIAL PREFIX BALANCE =====
        int[] initialBalance = new int[n];

        Set<Integer> currE = new HashSet<>();
        Set<Integer> currO = new HashSet<>();

        int bal = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Nếu là số lẻ distinct mới
            if ((x & 1) != 0) {
                if (currO.add(x))
                    bal--;
            }
            // Nếu là số chẵn distinct mới
            else {
                if (currE.add(x))
                    bal++;
            }

            initialBalance[i] = bal;
        }

        // ===== 4. BUILD SEGMENT TREE =====
        SegmentTree st = new SegmentTree(n);
        st.build(initialBalance, 1, 0, n - 1);

        // ===== 5. SLIDING WINDOW =====
        int maxLen = 0;

        for (int l = 0; l < n; l++) {

            // Tìm r xa nhất balance = 0
            int idx = st.findLastZero(1, 0, n - 1, l);

            if (idx != -1) {
                maxLen = Math.max(maxLen, idx - l + 1);
            }

            // ===== BỎ nums[l] KHỎI WINDOW =====
            if (l < n - 1) {
                int endRange = nextOcc[l] - 1;

                if (endRange >= l + 1) {
                    // Nếu bỏ số chẵn → balance giảm
                    // Nếu bỏ số lẻ → balance tăng
                    int delta = (nums[l] % 2 == 0) ? -1 : 1;

                    st.update(1, 0, n - 1, l + 1, endRange, delta);
                }
            }
        }

        return maxLen;
    }
}

// ---

// #

// Ví dụ
// chạy

// Input:

// ```
// Nhap n:7
// Nhap cac
// phan tu:2 3 4 5 2 3 6```

// Output (ví dụ):

// ```
// Do dai subarray can bang dai nhat: 6
// ```

// ---

// Nếu bạn muốn mình làm thêm:

// * In debug balance từng bước
// * Vẽ segment tree minh hoạ
// * Viết lại bản không dùng segment tree (O(n²))

// Cứ gửi yêu cầu 👍

// > Subarray cân bằng khi **số lượng giá trị chẵn DISTINCT = số lượng giá trị
// lẻ DISTINCT** trong đoạn.

// ⚠️ Lưu ý:
// Không phải đếm số phần tử — mà là **đếm số giá trị khác nhau (distinct)**.

// ---

// # 1️⃣ Ý tưởng tổng thể của code

// Thuật toán làm 4 việc lớn:

// 1. **Check nhanh** (edge cases)
// 2. **Tính next occurrence** (lần xuất hiện tiếp theo)
// 3. **Tính balance prefix ban đầu**
// 4. **Dùng Segment Tree + Lazy propagation** để:

// * Range update
// * Tìm vị trí xa nhất có balance = 0

// Mục tiêu:
// Với mỗi `l`, tìm `r` xa nhất sao cho:

// ```
// distinct_even(l..r) = distinct_odd(l..r)
// ```

// ---

// # 2️⃣ Bước 1 — Fast check

// ```java
// Set<Integer> allE = new HashSet<>();
// Set<Integer> allO = new HashSet<>();
// ```

// Đếm distinct toàn mảng:

// * `allE` = các số chẵn khác nhau
// * `allO` = các số lẻ khác nhau

// ### 2 trường hợp nhanh

// ```java
// if (allE.size() == allO.size()) return n;
// ```

// → Cả mảng balanced → trả luôn `n`.

// ```java
// if (allE.isEmpty() || allO.isEmpty()) return 0;
// ```

// → Chỉ có chẵn hoặc lẻ → không cân bằng.

// ⏱ Giúp giảm thời gian khi test lớn.

// ---

// # 3️⃣ Bước 2 — Next Occurrence

// ```java
// int[] nextOcc = new int[n];
// ```

// `nextOcc[i]` = vị trí xuất hiện tiếp theo của `nums[i]`.

// Ví dụ:

// ```
// nums = [2,3,2,5]

// i=0 → nextOcc[0] = 2
// i=1 → nextOcc[1] = n (không có)
// ```

// Dùng để biết:

// > Khi bỏ phần tử ở l, từ đâu trở đi nó không còn trong subarray nữa.

// Rất quan trọng cho range update.

// ---

// # 4️⃣ Bước 3 — Initial Balance Prefix

// ```java
// bal = distinct_even - distinct_odd
// ```

// Duyệt từ trái sang phải:

// ```java
// if (currO.add(x)) bal--;
// if (currE.add(x)) bal++;
// ```

// Chỉ khi **distinct mới xuất hiện** mới đổi balance.

// ### Ví dụ

// ```
// nums = [2,3,2,5]
// ```

// | i | distinct even | distinct odd | bal |
// | - | ------------- | ------------ | --- |
// | 0 | {2} | {} | +1 |
// | 1 | {2} | {3} | 0 |
// | 2 | {2} | {3} | 0 |
// | 3 | {2} | {3,5} | -1 |

// → `initialBalance = [1,0,0,-1]`

// ---

// # 5️⃣ Ý nghĩa balance = 0

// Nếu tại vị trí r:

// ```
// balance[r] = 0
// ```

// → distinct_even(0..r) = distinct_odd(0..r)

// Nhưng ta cần subarray từ **l → r**, không phải từ 0.

// → Khi tăng l, ta phải **điều chỉnh lại balance**.

// Đây là lý do cần Segment Tree + Lazy.

// ---

// # 6️⃣ Segment Tree lưu gì?

// Mỗi node lưu:

// ```java
// treeMin // min balance trong đoạn
// treeMax // max balance trong đoạn
// lazy // giá trị cộng dồn
// ```

// Dùng để:

// * Range add nhanh O(log n)
// * Kiểm tra đoạn có chứa 0 không

// ---

// # 7️⃣ Hàm findLastZero

// ```java
// findLastZero(node, start, end, minIdx)
// ```

// Tìm vị trí **xa nhất ≥ minIdx** có balance = 0.

// Logic:

// ```java
// if (treeMin > 0 || treeMax < 0) return -1;
// ```

// → Đoạn không chứa 0.

// Ưu tiên tìm bên phải trước:

// ```java
// res = find right
// if found → return
// else → find left
// ```

// → Đảm bảo lấy r xa nhất.

// ---

// # 8️⃣ Sliding Window bằng Segment Tree

// Loop chính:

// ```java
// for (int l = 0; l < n; l++)
// ```

// ### Bước A — tìm r

// ```java
// idx = st.findLastZero(..., l)
// ```

// Nếu có → cập nhật maxLen.

// ---

// ### Bước B — bỏ phần tử l khỏi window

// Khi tăng l → phần tử `nums[l]` không còn trong subarray.

// Nhưng chỉ ảnh hưởng khi nó là **lần xuất hiện cuối cùng trong window**.

// Dùng:

// ```java
// endRange = nextOcc[l] - 1
// ```

// Đoạn bị ảnh hưởng:

// ```
// [l+1 → nextOcc[l]-1]
// ```

// ---

// # 9️⃣ Vì sao phải update đoạn này?

// Giả sử:

// ```
// nums = [2,3,2]
// ```

// Distinct even ban đầu = {2}

// Khi l = 0 bị bỏ:

// * Từ index 1 → 1: không còn số 2 nào nữa
// * Distinct even giảm 1

// → Balance giảm 1.

// ---

// # 10️⃣ Delta update

// ```java
// delta = (nums[l] % 2 == 0) ? -1 : 1;
// ```

// | Loại | Khi bỏ | Balance |
// | ---- | --------------- | ------- |
// | Even | distinct_even-- | bal -1 |
// | Odd | distinct_odd-- | bal +1 |

// Vì:

// ```
// bal = even - odd
// ```

// ---

// # 11️⃣ Lazy Range Update

// ```java
// st.update(l+1 → endRange, delta)
// ```

// Tức là:

// Cập nhật balance prefix cho tất cả r mà window mất đi distinct này.

// Nhờ Lazy → O(log n).

// ---

// # 12️⃣ Độ phức tạp

// * Build tree: O(n)
// * Mỗi l:

// * findLastZero: O(log n)
// * update: O(log n)

// → Tổng:

// ```
// O(n log n)
// ```

// Tối ưu cho n lớn (10^5, 10^6).

// ---

// # 13️⃣ Tóm tắt thuật toán (flow)

// 1. Đếm distinct chẵn/lẻ toàn mảng
// 2. Nếu cân bằng → return n
// 3. Tính next occurrence
// 4. Tính prefix balance
// 5. Build segment tree
// 6. Với mỗi l:

// * Tìm r xa nhất balance = 0
// * Update balance khi bỏ nums[l]
// 7. Lấy max length

// ---

// # 14️⃣ Hiểu bản chất bài

// Ta đang làm:

// ```
// Sliding window + Distinct counting
// ```

// Nhưng distinct không thể O(1) khi shrink window.

// → Biến thành prefix balance.

// → Dùng segment tree để:

// * Update hàng loạt
// * Query nhanh vị trí balance = 0

// ---

// Nếu bạn muốn, mình có thể:

// * Chạy tay 1 ví dụ cụ thể từng bước trên tree
// * Vẽ sơ đồ balance update
// * So sánh với cách HashMap O(n²)

// Chỉ cần gửi input mẫu 👍

// ---

// ## 1️⃣ Ý nghĩa đề bài (giải thích dễ hiểu)

// Bạn được cho một mảng (array) gồm các phần tử thuộc **2 hoặc nhiều loại**
// (tuỳ phiên bản đề).

// Nhiệm vụ:
// Tìm **độ dài lớn nhất của một subarray (đoạn con liên tiếp)** sao cho nó
// **balanced (cân bằng)** theo điều kiện đề bài.

// ---

// ## 2️⃣ Balanced nghĩa là gì?

// Trong bài **Longest Balanced Subarray II**, “balanced” thường được định nghĩa
// là:

// > Số lượng phần tử của các loại trong subarray **bằng nhau**.

// ### Ví dụ phổ biến nhất

// Mảng chỉ gồm **0 và 1**:

// Balanced ⇔ số lượng `0` = số lượng `1`.

// #### Example

// ```
// arr = [0,1,0,1,1,0,0]
// ```

// Một subarray balanced:

// ```
// [0,1,0,1] → 2 số 0, 2 số 1 → balanced
// ```

// Ta cần tìm subarray balanced **dài nhất**.

// ---

// ## 3️⃣ Vì sao có “II” ?

// “II” nghĩa là **phiên bản nâng cao** so với bản I.

// Tuỳ nền tảng (LeetCode, Codeforces, Hackerrank…), bản II thường mở rộng:

// ### Các biến thể hay gặp

// 1. **Không chỉ 0 và 1**

// * Có thể là:

// * 0,1,2
// * chữ cái
// * nhiều loại giá trị

// 2. Balanced = số lượng mỗi loại bằng nhau
// Ví dụ:

// ```
// [0,1,2,0,1,2] → balanced (mỗi loại 2)
// ```

// 3. Hoặc:

// * Số lượng positive = negative
// * Vowel = consonant
// * Nam = nữ
// → cùng bản chất: đếm tần suất để cân bằng.

// ---

// ## 4️⃣ Ví dụ minh hoạ (bản II – 3 loại)

// ```
// arr = [0,1,2,0,1,2,0]
// ```

// Các subarray balanced:

// * `[0,1,2]` → mỗi loại 1
// * `[0,1,2,0,1,2]` → mỗi loại 2 ✅ dài nhất = 6

// ---

// ## 5️⃣ Ý tưởng giải (trực quan)

// ### Bản 0–1 (dễ hiểu nhất)

// Đổi:

// ```
// 0 → -1
// 1 → +1
// ```

// Balanced ⇔ tổng = 0.

// → Tìm subarray dài nhất có **prefix sum trùng nhau**.

// ---

// ### Bản II (nhiều loại)

// Ta không dùng 1 prefix sum nữa mà dùng **vector chênh lệch**.

// Ví dụ 3 loại (0,1,2):

// Ta lưu:

// ```
// diff1 = count(1) - count(0)
// diff2 = count(2) - count(0)
// ```

// Nếu tại 2 vị trí có cùng `(diff1, diff2)`
// → đoạn giữa chúng balanced.

// ---

// ## 6️⃣ Tóm tắt đề bài (ngắn gọn để đi thi)

// **Input:**
// Mảng gồm nhiều loại phần tử.

// **Output:**
// Độ dài lớn nhất của subarray liên tiếp sao cho số lượng các loại phần tử
// trong subarray bằng nhau.

// ---

// ## 7️⃣ Nhận diện bài này khi đi thi / làm LeetCode

// Dấu hiệu:

// * “Longest subarray”
// * “Equal number”
// * “Balanced”
// * “Same frequency”
// * “0 and 1”
// * “Multiple categories”

// → Nghĩ ngay:

// ```
// Prefix sum + HashMap
// ```

// ---

// Nếu bạn muốn, gửi mình:

// * Link đề
// * Hoặc input/output mẫu

// Mình sẽ giải thích đúng **phiên bản Longest Balanced Subarray II** bạn đang
// học (vì mỗi platform sửa đề hơi khác).
