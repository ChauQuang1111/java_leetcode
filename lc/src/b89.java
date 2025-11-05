
// 3321. Find X-Sum of All K-Long Subarrays II(05/11/2025)
// Mình khimport java.util.*;
import java.util.*;

public class b89 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        // Nhập mảng nums
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        long[] result = findXSum(nums, k, x);

        // In kết quả

        for (long val : result) {
            System.out.print(val + " ");
        }
    }

    public static int x;

    // Tổng X-Sum hiện tại (chỉ tính các phần tử trong top-x)
    public static long sum = 0L;

    // Lưu tần suất xuất hiện của mỗi phần tử trong cửa sổ
    public static Map<Integer, Integer> freq;

    // Hai nhóm phần tử:
    // active: chứa top-x phần tử có tần suất cao nhất (đóng góp vào sum)
    // inactive: chứa phần còn lại
    public static final TreeSet<int[]> active = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    public static final TreeSet<int[]> inactive = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

    // Hàm chính tính X-Sum cho mỗi subarray độ dài k
    public static long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        b89.x = x;
        freq = new HashMap<>(n); // khởi tạo map lưu tần suất
        long[] ans = new long[n - k + 1]; // mảng kết quả

        // Duyệt từng phần tử của mảng
        for (int i = 0; i < n; i++) {
            // Tăng tần suất phần tử hiện tại
            int count = freq.merge(nums[i], 1, Integer::sum);

            // Cập nhật lại vị trí phần tử trong active/inactive
            remove(count - 1, nums[i]);
            add(count, nums[i]);

            // Khi cửa sổ đủ k phần tử
            if (i + 1 >= k) {
                ans[i - k + 1] = sum; // lưu tổng hiện tại (X-Sum)

                // Xóa phần tử nằm ngoài cửa sổ
                count = freq.merge(nums[i - k + 1], -1, Integer::sum);
                remove(count + 1, nums[i - k + 1]);
                add(count, nums[i - k + 1]);
            }
        }

        return ans;
    }

    // Hàm thêm phần tử (cập nhật vào active hoặc inactive)
    public static void add(int count, int num) {
        if (count == 0)
            return; // không cần xử lý nếu phần tử không còn xuất hiện

        int[] val = new int[] { count, num };

        // Nếu active chưa đủ x phần tử → thêm trực tiếp
        if (active.size() < x) {
            active.add(val);
            sum += (long) count * num; // cộng vào tổng
            return;
        }

        // Lấy phần tử yếu nhất trong active (có tần suất nhỏ nhất)
        int[] temp = active.first();

        // Nếu phần tử mới yếu hơn → cho vào inactive
        if (temp[0] > count || (temp[0] == count && temp[1] >= num)) {
            inactive.add(val);
            return;
        }

        // Nếu mạnh hơn → thay thế phần yếu nhất trong active
        sum += (long) count * num - (long) temp[0] * temp[1];
        inactive.add(active.pollFirst());
        active.add(val);
    }

    // Hàm xóa hoặc giảm tần suất phần tử
    public static void remove(int count, int num) {
        if (count == 0)
            return;

        int[] val = new int[] { count, num };

        // Nếu phần tử nằm trong inactive → chỉ cần xóa
        if (inactive.contains(val)) {
            inactive.remove(val);
            return;
        }

        // Nếu nằm trong active → xóa và cập nhật lại sum
        active.remove(val);
        sum -= (long) count * num;

        // Bổ sung phần tử mạnh nhất trong inactive để giữ top-x
        if (inactive.isEmpty())
            return;

        int[] temp = inactive.pollLast();
        sum += (long) temp[0] * temp[1];
        active.add(temp);
    }
}

// Tuy nhiên, dựa vào bản I và các gợi ý, mình có thể **phỏng đoán** đề bài phần
// II như sau:

// ---

// ## 📄 Đề bài phỏng đoán cho phần II

// Cho mảng `nums` độ dài `n`, và hai số nguyên `k` và `x`.
// Cần tính “x-sum” cho mỗi **subarray liên tiếp** độ dài `k`, nơi “x-sum” được
// định nghĩa như sau:

// 1. Đếm tần suất xuất hiện của từng phần tử trong subarray.
// 2. Giữ lại `x` phần tử **có tần suất xuất hiện cao nhất** (nếu hai phần tử có
// cùng tần suất thì chọn phần tử giá trị *lớn hơn*).
// 3. Tính tổng = ∑ (giá trị phần tử × số lần xuất hiện) của các phần tử được
// giữ lại.
// 4. Trường hợp subarray có ít hơn `x` phần tử phân biệt thì x-sum = tổng của
// toàn bộ subarray.

// Phần II có thể có thêm ràng buộc như: `n` rất lớn (ví dụ lên đến 10⁵), phần
// tử trong `nums` có thể lớn hơn, hoặc `k`, `x` lớn hơn, do đó cần **giải pháp
// tối ưu hơn** (ví dụ sử dụng heap, Balanced BST, hoặc hai cấu trúc dữ liệu như
// “top x” và “phần còn lại” để trượt cửa sổ) thay vì giải đơn giản như bản I.

// ---

// ## 🧠 Ý tưởng thuật toán phần II (nâng cao)

// * Duy trì sliding window độ dài `k` qua `nums`.
// * Dùng `cnt` để lưu tần suất hiện tại của từng phần tử trong window.
// * Dùng hai cấu trúc dữ liệu (ví dụ multiset, TreeSet, heap) để phân chia:

// * `top` chứa `x` phần tử tốt nhất (theo tần suất và giá trị) — đóng góp vào
// tổng x-sum.
// * `bot` chứa phần còn lại.
// * Mỗi khi trượt cửa sổ:

// * Loại phần tử `out_val = nums[i−k]`: cập nhật tần suất, di chuyển nếu cần
// giữa `top` và `bot`.
// * Thêm phần tử `in_val = nums[i]`: cập nhật tần suất, di chuyển giữa `bot` và
// `top`.
// * Cân bằng hai cấu trúc để đảm bảo `top` có đúng `x` phần tử hoặc ít hơn nếu
// không đủ.
// * Cập nhật tổng x-sum dựa trên `top` — tức là tổng tần suất × giá trị của các
// phần tử trong `top`.
// * Trả mảng kết quả có chiều dài `n − k + 1`.

// ---

// ## ✅ Tóm tắt

// Mặc dù mình không tìm được **đề chính xác** cho phần II, nhưng về cơ bản nó
// mở rộng bản I thành phiên bản tối ưu khi `n`, `k`, `x` lớn hơn.
// Nếu bạn muốn, mình có thể tìm lại và gửi **đề bài chính thức + code mẫu tối
// ưu cho phần II**. Bạn muốn mình tìm không?
// Rất hay — đây là lời giải **tối ưu (O(n log n))** cho bài **LeetCode 3321:
// Find X-Sum of All K-Long Subarrays II** 🔥.
// Mình sẽ **giải thích chi tiết từng phần** của thuật toán, giúp bạn hiểu rõ
// cách hoạt động của từng biến, hàm và ý tưởng đằng sau.

// ---

// ## 🧩 Mục tiêu của bài toán

// Cho mảng `nums`, hai số nguyên `k` và `x`.

// 👉 Với mỗi **subarray liên tiếp độ dài k**, cần tính:

// > Tổng của **x phần tử có tần suất cao nhất** (nếu bằng nhau thì lấy phần tử
// có giá trị lớn hơn trước).
// > Nếu có ít hơn `x` phần tử khác nhau thì lấy tổng toàn bộ.

// Ta cần trả về mảng kết quả `ans`, trong đó:

// * `ans[i]` là “X-Sum” của cửa sổ `nums[i .. i+k−1]`.

// ---

// ## ⚙️ Ý tưởng chính của thuật toán

// Thay vì duyệt lại từ đầu cho mỗi cửa sổ con (O(n × k)), thuật toán này dùng
// **sliding window + TreeSet (cấu trúc cân bằng)** để:

// * Duy trì tần suất hiện tại của các số trong cửa sổ (`freq`),
// * Duy trì **hai nhóm phần tử**:

// * `active`: top-x phần tử hiện tại (đóng góp vào `sum`),
// * `inactive`: phần còn lại (các phần tử không nằm trong top-x),
// * Mỗi khi trượt cửa sổ, ta:

// 1. Cập nhật tần suất phần tử vừa thêm vào cửa sổ,
// 2. Xử lý phần tử bị loại ra khỏi cửa sổ,
// 3. Cập nhật `active`, `inactive`, và `sum` sao cho luôn chính xác.

// ---

// ## 🧠 Phân tích từng thành phần

// ### 1️⃣ Biến toàn cục & cấu trúc dữ liệu

// ```java
// private int x;
// private long sum = 0L;
// private Map<Integer, Integer> freq;
// private final TreeSet<int[]> active = new TreeSet<>((a, b) -> a[0] == b[0] ?
// a[1] - b[1] : a[0] - b[0]),
// inactive = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
// ```

// * `x`: số phần tử top cần chọn.
// * `sum`: tổng X-Sum hiện tại (của nhóm `active`).
// * `freq`: tần suất xuất hiện của từng số trong cửa sổ hiện tại.
// * `active`: lưu `x` phần tử có tần suất cao nhất (mỗi phần tử lưu `[freq,
// value]`).
// * `inactive`: lưu phần còn lại.
// * `TreeSet` được sắp xếp tăng dần theo `(freq, value)` để dễ tìm phần tử “nhỏ
// nhất trong active” hoặc “lớn nhất trong inactive”.

// ---

// ### 2️⃣ Hàm `findXSum`

// ```java
// for (int i = 0; i < n; i++) {
// int count = freq.merge(nums[i], 1, Integer::sum);
// remove(count - 1, nums[i]);
// add(count, nums[i]);
// ```

// #### 🟩 Khi thêm phần tử mới (`nums[i]`):

// * Tăng tần suất (`count`).
// * Xóa bản ghi cũ `(count−1, num)` khỏi active/inactive.
// * Thêm bản ghi mới `(count, num)` vào active/inactive sao cho vẫn đúng quy
// tắc “top-x”.

// ---

// ```java
// if (i + 1 >= k) {
// ans[i - k + 1] = sum;
// count = freq.merge(nums[i - k + 1], -1, Integer::sum);
// remove(count + 1, nums[i - k + 1]);
// add(count, nums[i - k + 1]);
// }
// ```

// #### 🟦 Khi cửa sổ đạt độ dài `k`:

// * Lưu X-Sum hiện tại (`sum`) vào `ans`.
// * Loại bỏ phần tử **ra khỏi cửa sổ** (`nums[i - k + 1]`):

// * Giảm tần suất đi 1.
// * Cập nhật lại vị trí phần tử trong `active`/`inactive` (qua `remove` và
// `add`).

// ---

// ### 3️⃣ Hàm `add(int count, int num)`

// ```java
// if (count == 0) return;
// int[] val = new int[] {count, num};
// ```

// * Nếu phần tử không còn xuất hiện thì bỏ qua.

// ---

// #### ⚙️ TH1: Nếu `active` chưa đủ `x` phần tử:

// ```java
// if (active.size() < x) {
// active.add(val);
// sum += (long)count * num;
// return;
// }
// ```

// → Thêm trực tiếp vào `active`, cập nhật `sum`.

// ---

// #### ⚙️ TH2: Nếu `active` đã đủ `x` phần tử:

// ```java
// int[] temp = active.first(); // phần tử "yếu" nhất trong active
// if (temp[0] > count || temp[0] == count && temp[1] >= num) {
// inactive.add(val); // val yếu hơn → cho vào inactive
// return;
// }
// ```

// → Nếu phần tử mới không mạnh hơn phần yếu nhất trong active, thêm vào
// inactive.

// Ngược lại:

// ```java
// sum += (long)count * num - (long)temp[0] * temp[1];
// inactive.add(active.pollFirst()); // di chuyển phần yếu nhất sang inactive
// active.add(val); // thêm phần mới vào top-x
// ```

// → Nếu mạnh hơn:

// * Thay thế phần yếu nhất,
// * Cập nhật `sum`.

// ---

// ### 4️⃣ Hàm `remove(int count, int num)`

// Khi một phần tử bị giảm tần suất (do rời khỏi cửa sổ):

// ```java
// if (count == 0) return;
// int[] val = new int[] {count, num};
// if (inactive.contains(val)) {
// inactive.remove(val);
// return;
// }
// ```

// → Nếu nằm trong `inactive`, chỉ cần xóa.

// Ngược lại (nằm trong `active`):

// ```java
// active.remove(val);
// sum -= (long)count * num;
// if (inactive.isEmpty()) return;

// int[] temp = inactive.pollLast(); // phần mạnh nhất trong inactive
// sum += (long)temp[0] * temp[1];
// active.add(temp);
// ```

// → Xóa khỏi active, trừ giá trị đóng góp trong `sum`,
// → Rồi chuyển phần tử mạnh nhất trong inactive lên active để duy trì top-x.

// ---

// ## 📊 Độ phức tạp

// | Bước | Độ phức tạp |
// | -------------------- | ------------------------------ |
// | Cập nhật mỗi phần tử | O(log n) (do thao tác TreeSet) |
// | Tổng thể | O(n log n) |
// | Bộ nhớ | O(n) |

// ---

// ## 🧩 Tóm tắt toàn bộ ý tưởng

// | Bước | Hành động | Cấu trúc bị ảnh hưởng |
// | ----------------- | ----------------------------------------------- |
// ---------------------- |
// | Thêm phần tử mới | Tăng freq, thêm vào active/inactive | active, inactive,
// freq |
// | Khi cửa sổ đủ k | Ghi sum vào ans | ans |
// | Loại phần tử cũ | Giảm freq, loại bỏ hoặc cập nhật | active, inactive, freq
// |
// | Giữ top-x phần tử | Luôn đảm bảo active có đúng x phần tử mạnh nhất |
// active |
// | Tính tổng nhanh | sum luôn lưu tổng đóng góp của active | sum |

// ---

// Nếu bạn muốn, mình có thể **thêm comment chi tiết từng dòng vào code trên**
// để bạn học dễ hơn — bạn có muốn mình làm vậy không?
