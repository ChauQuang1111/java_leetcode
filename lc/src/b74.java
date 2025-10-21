
// 3346. Maximum Frequency of an Element After Performing Operations I(21/10/2025)
import java.util.*;

public class b74 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        int k = sc.nextInt();

        int numOps = sc.nextInt();

        int ans = maxFrequency(nums, k, numOps);
        System.out.println("Kết quả: " + ans);

        sc.close();
    }

    public static int maxFrequency(int[] nums, int k, int numOps) {
        int maxVal = Arrays.stream(nums).max().getAsInt() + k + 2;
        int[] count = new int[maxVal];

        // Đếm số lần xuất hiện của từng giá trị
        for (int v : nums)
            count[v]++;

        // Tạo prefix sum để dễ tính tổng trong đoạn [L, R]
        for (int i = 1; i < maxVal; i++)
            count[i] += count[i - 1];

        int res = 0;
        for (int i = 0; i < maxVal; i++) {
            int left = Math.max(0, i - k);
            int right = Math.min(maxVal - 1, i + k);

            int total = count[right] - (left > 0 ? count[left - 1] : 0); // số phần tử có thể đổi thành i
            int freq = count[i] - (i > 0 ? count[i - 1] : 0); // số phần tử đã bằng i

            // chọn tối đa numOps phần tử để chuyển sang i
            res = Math.max(res, freq + Math.min(numOps, total - freq));
        }

        return res;
    }
}

// Ok 👇 mình giải thích thật dễ hiểu nhé — đây là **LeetCode 3346. Maximum
// Frequency of an Element After Performing Operations I**.

// ---

// ### 🧩 **Đề bài (dịch & tóm tắt)**

// Bạn được cho:

// * Một mảng số nguyên `nums`
// * Một số nguyên `k`

// Mỗi **phép toán (operation)** cho phép bạn **chọn một phần tử `nums[i]` và
// tăng hoặc giảm nó đi bất kỳ giá trị nào trong phạm vi `[0, k]`**.

// > Nghĩa là: bạn có thể **thay đổi mỗi phần tử** thành **bất kỳ số nào trong
// đoạn `[nums[i] - k, nums[i] + k]`**.

// ---

// ### 🎯 **Mục tiêu:**

// Sau khi thực hiện *một số phép toán (có thể là 0)*, hãy tìm **tần suất lớn
// nhất (frequency)** của một phần tử trong mảng — tức là, số lượng phần tử có
// thể **trở thành cùng một giá trị**.

// ---

// ### 🧠 **Hiểu theo ví dụ:**

// #### 🔹 Ví dụ 1:

// ```
// nums = [1, 4, 5], k = 1
// ```

// * `1` có thể biến đổi thành [0, 2]
// * `4` → [3, 5]
// * `5` → [4, 6]

// Ta thấy khoảng `[4,5]` là giao nhau của `4` và `5`
// → có thể làm cho cả hai bằng `4` hoặc `5`.

// ✅ Vậy tần suất lớn nhất là **2**.

// ---

// #### 🔹 Ví dụ 2:

// ```
// nums = [1, 2, 4], k = 2
// ```

// Các đoạn có thể:

// * 1 → [−1, 3]
// * 2 → [0, 4]
// * 4 → [2, 6]

// Khoảng `[2,3]` là nơi **cả 3 phần tử** đều có thể trùng nhau (vì tất cả đoạn
// đều giao tại điểm này).
// → ✅ Tần suất lớn nhất = **3**

// ---

// ### 💡 **Cách giải (ý tưởng chính)**

// Ta có thể xem mỗi `nums[i]` như **một đoạn [nums[i] - k, nums[i] + k]**.
// Câu hỏi trở thành:

// > “Có bao nhiêu đoạn giao nhau tại cùng một điểm (giao điểm nhiều nhất)?”

// 👉 Giải bằng **sort + two pointers / sliding window**:

// 1. Sắp xếp `nums`
// 2. Với mỗi `i`, ta cần tìm vùng `j` sao cho `nums[j] ≤ nums[i] + 2*k` (vì
// chênh lệch tối đa giữa hai phần tử là `2*k` để chúng có thể cùng trở thành
// một giá trị chung).
// 3. Dùng **cửa sổ trượt (sliding window)** để đếm số phần tử thoả mãn điều
// kiện đó.

// ---

// ### 🧩 **Pseudo-code:**

// ```python
// def maxFrequency(nums, k):
// nums.sort()
// left = 0
// ans = 0
// for right in range(len(nums)):
// while nums[right] - nums[left] > 2 * k:
// left += 1
// ans = max(ans, right - left + 1)
// return ans
// ```

// ---

// ### ⚙️ **Độ phức tạp:**

// * Sắp xếp: `O(n log n)`
// * Duyệt 2 con trỏ: `O(n)`
// ➡ Tổng: `O(n log n)`

// ---

// ### 📘 **Tóm lại**

// * Mỗi số có thể thành [nums[i] - k, nums[i] + k]
// * Cần tìm **nhiều phần tử nhất có thể trùng nhau sau khi biến đổi**
// * Giải bằng **sliding window** để tìm đoạn `[nums[i], nums[j]]` có `nums[j] -
// nums[i] ≤ 2*k`
// * Trả về `j - i + 1` lớn nhất

// ---

// Bạn có muốn mình minh hoạ thêm **bằng hình vẽ đoạn giao nhau** (để thấy rõ
// hơn vì sao điều kiện là `2*k`) không?
// Rất hay — đoạn code bạn đưa là lời giải khác cho **LeetCode 3346 – Maximum
// Frequency of an Element After Performing Operations I**, và nó **không dùng
// sort + two pointers** mà dùng **prefix sum + counting array**.
// Mình sẽ giải thích **từng bước, trực quan + logic** cho bạn 👇

// ---

// ## 🎯 **Mục tiêu bài toán**

// Cho:

// * Mảng `nums`
// * Hai số `k` và `numOps` (số lần thao tác tối đa).

// Mỗi thao tác bạn có thể **chọn 1 phần tử `nums[i]`** rồi **tăng hoặc giảm giá
// trị của nó trong phạm vi `[0, k]`**.

// → Nghĩa là, sau khi thực hiện tối đa `numOps` phép, bạn muốn **tạo ra nhiều
// phần tử giống nhau nhất có thể**.

// ---

// ## ⚙️ **Ý tưởng thuật toán**

// ### 1️⃣ Xây dựng **mảng đếm (count array)**

// ```java
// int maxVal = Arrays.stream(nums).max().getAsInt() + k + 2;
// int[] count = new int[maxVal];
// for (int v : nums)
// count[v]++;
// ```

// 👉 `count[x]` lưu **số phần tử có giá trị x**.
// Ta thêm `k + 2` để tránh out of range khi xét vùng `[i - k, i + k]`.

// ---

// ### 2️⃣ Dùng **prefix sum** để nhanh chóng tính tổng trong đoạn

// ```java
// for (int i = 1; i < maxVal; i++)
// count[i] += count[i - 1];
// ```

// Sau bước này:

// * `count[i]` = **tổng số phần tử ≤ i** trong mảng gốc.
// → Giúp ta dễ dàng đếm số phần tử trong đoạn `[L, R]`.

// ---

// ### 3️⃣ Xét từng giá trị `i` như một **ứng viên đích (target value)**

// ```java
// for (int i = 0; i < maxVal; i++) {
// int left = Math.max(0, i - k);
// int right = Math.min(maxVal - 1, i + k);
// ```

// Ta coi `i` là **giá trị mà ta muốn nhiều phần tử trở thành** sau khi thao
// tác.
// Khi đó, những phần tử ban đầu nằm trong đoạn `[i - k, i + k]` **có thể biến
// thành i** chỉ bằng ≤ k đơn vị.

// ---

// ### 4️⃣ Đếm xem có bao nhiêu phần tử trong đoạn `[i - k, i + k]`

// ```java
// int total = count[right] - (left > 0 ? count[left - 1] : 0);
// ```

// → `total` = số phần tử **có thể biến đổi** thành `i`.

// ---

// ### 5️⃣ Tính số phần tử hiện **đang là i**

// ```java
// int freq = count[i] - (i > 0 ? count[i - 1] : 0);
// ```

// → `freq` = số phần tử **đã đúng bằng i** ngay từ đầu.

// ---

// ### 6️⃣ Dùng tối đa `numOps` phép để tăng thêm số phần tử bằng `i`

// ```java
// res = Math.max(res, freq + Math.min(numOps, total - freq));
// ```

// Giải thích:

// * Có `freq` phần tử đã bằng `i`.
// * Có `total - freq` phần tử **có thể trở thành `i` nếu ta chỉnh sửa**.
// * Ta chỉ có thể thực hiện tối đa `numOps` phép, nên chỉ chọn được
// `min(numOps, total - freq)` phần tử để chuyển sang `i`.

// → Tổng số phần tử bằng `i` sau thao tác = `freq + min(numOps, total - freq)`.

// ---

// ### 7️⃣ Giữ kết quả lớn nhất

// Cuối cùng ta chọn giá trị `i` nào cho ra tần suất cao nhất.

// ---

// ## 🧠 **Tóm tắt trực quan**

// | Bước | Ý nghĩa |
// | ---- |
// ------------------------------------------------------------------------ |
// | 1 | Đếm tần suất từng giá trị |
// | 2 | Dùng prefix sum để dễ đếm số phần tử trong đoạn |
// | 3 | Xét từng giá trị `i` làm "mục tiêu" |
// | 4 | Tính bao nhiêu phần tử có thể biến thành `i` (vì nằm trong `[i−k,
// i+k]`) |
// | 5 | Tính bao nhiêu phần tử đã bằng `i` |
// | 6 | Dùng tối đa `numOps` để biến đổi thêm |
// | 7 | Ghi nhận kết quả tốt nhất |

// ---

// ## ⏱️ **Độ phức tạp**

// * `O(max(nums) + k)` thời gian
// * `O(max(nums) + k)` bộ nhớ

// → Nhanh hơn so với cách sort nếu `max(nums)` không quá lớn.

// ---

// ## 🧩 **Ví dụ minh họa**

// ```
// nums = [1, 4, 5], k = 1, numOps = 1
// ```

// * Với `i = 4`:

// * `[i - k, i + k] = [3, 5]`
// * `total = 2` (có 4 và 5 trong khoảng này)
// * `freq = 1` (có 1 phần tử = 4)
// * `res = freq + min(numOps, total - freq) = 1 + min(1, 1) = 2`

// → ✅ Kết quả = 2.

// ---

// Bạn muốn mình vẽ sơ đồ hoặc viết lại phiên bản **tối giản + có chú thích chi
// tiết** trong Java để dễ hiểu hơn không?
