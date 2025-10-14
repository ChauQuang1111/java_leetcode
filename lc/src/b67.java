
// 3349. Adjacent Increasing Subarrays Detection I(14/102025)
// ---
import java.util.*;

public class b67 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }

        int k = sc.nextInt();

        boolean result = hasIncreasingSubarrays(nums, k);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int need = k - 1; // cần tìm k-1 cặp tăng song song
        if (need == 0)
            return true; // nếu k = 1 thì luôn đúng

        // Duyệt từ chỉ số k+1 (vì ta sẽ so sánh nums[j] với nums[j-k])
        for (int j = k + 1; j < n; j++) {
            // Kiểm tra 2 điều kiện:
            // 1️⃣ nums[j] > nums[j-1]: phần tử hiện tại đang trong chuỗi tăng
            // 2️⃣ nums[j-k] > nums[j-k-1]: phần tử cách k vị trí cũng đang trong chuỗi tăng
            if (nums.get(j) > nums.get(j - 1) && nums.get(j - k) > nums.get(j - k - 1)) {
                need--; // thêm được 1 cặp tăng song song
            } else {
                need = k - 1; // reset lại nếu chuỗi tăng bị ngắt
            }

            // Nếu đã tìm đủ k-1 bước tăng song song → có 2 dãy tăng liền nhau
            if (need == 0)
                return true;
        }
        return false;
    }
}

// ### 🧩 **Đề bài:**

// Cho một mảng số nguyên `nums` và một số nguyên `k`.

// Một **subarray tăng dần độ dài k** là một dãy con liên tiếp gồm `k` phần tử,
// trong đó mỗi phần tử sau **lớn hơn phần tử trước**.

// Bạn cần **kiểm tra xem có tồn tại hai subarray tăng dần độ dài `k` nằm liền
// kề nhau** hay không.
// Nếu có → trả về `true`, ngược lại → `false`.

// ---

// ### 📘 **Ví dụ:**

// #### Ví dụ 1:

// ```
// Input: nums = [1, 2, 3, 4, 5, 6], k = 2
// ```

// Các subarray độ dài 2 là:

// * [1, 2] ✅ tăng
// * [2, 3] ✅ tăng
// * [3, 4] ✅ tăng
// * [4, 5] ✅ tăng
// * [5, 6] ✅ tăng

// → Có thể ghép [1, 2] và [3, 4] (chúng liền nhau trong mảng và đều tăng).
// => **Output: true**

// ---

// #### Ví dụ 2:

// ```
// Input: nums = [1, 2, 3, 2, 3, 4], k = 2
// ```

// Các subarray độ dài 2:

// * [1, 2] ✅ tăng
// * [2, 3] ✅ tăng
// * [3, 2] ❌ không tăng
// * [2, 3] ✅ tăng
// * [3, 4] ✅ tăng

// Ta thấy [1, 2] và [2, 3] là hai subarray tăng liên tiếp → ✅
// => **Output: true**

// ---

// #### Ví dụ 3:

// ```
// Input: nums = [1, 2, 3, 4], k = 3
// ```

// Các subarray độ dài 3:

// * [1, 2, 3] ✅ tăng
// * [2, 3, 4] ✅ tăng

// Hai subarray này kề nhau và đều tăng
// => **Output: true**

// ---

// #### Ví dụ 4:

// ```
// Input: nums = [1, 3, 5, 4, 6, 8], k = 2
// ```

// Các subarray độ dài 2:

// * [1, 3] ✅ tăng
// * [3, 5] ✅ tăng
// * [5, 4] ❌
// * [4, 6] ✅
// * [6, 8] ✅

// Ở đây [1,3] và [3,5] là hai dãy tăng liên tiếp → ✅
// => **Output: true**

// ---

// ### 🧠 **Tóm tắt logic cần làm:**

// 1. Duyệt qua mảng, đếm số phần tử liên tiếp thỏa `nums[i] < nums[i+1]`.
// 2. Nếu đếm được **2*k - 1** phần tử tăng liên tục → tức là có **hai subarray
// tăng độ dài k** kề nhau.
// → Trả về `true`.
// 3. Nếu duyệt hết mà không tìm thấy → trả về `false`.

// ---

// Nếu bạn muốn, mình có thể viết code Java hoặc Python minh họa cho thuật toán
// này, kèm chú thích từng dòng.
// 👉 Bạn muốn xem bằng ngôn ngữ nào?
// Rất hay — đây là bài **LeetCode 3349: Adjacent Increasing Subarrays Detection
// I**, và đoạn code Java trên là một cách giải tối ưu 💡.
// Giờ ta sẽ **phân tích toàn bộ thuật toán chi tiết từng bước** nhé 👇

// ---

// ## 🎯 Mục tiêu đề bài

// Ta được cho:

// * Một danh sách số nguyên `nums`
// * Một số nguyên `k`

// 👉 Nhiệm vụ:
// **Kiểm tra xem trong `nums` có tồn tại hai dãy con tăng liên tiếp (không
// chồng nhau), mỗi dãy có độ dài ít nhất `k` hay không.**

// ---

// ## 🧩 Ví dụ minh họa

// ```
// nums = [2,5,7,8,9,2,3,4,3,1], k = 3
// ```

// Ta có:

// * Dãy tăng đầu tiên: [2,5,7,8,9]
// * Dãy tăng tiếp theo (liền sau, không chồng lên): [2,3,4]
// ✅ Kết quả: true

// ---

// ## 🧠 Ý tưởng thuật toán

// ### 🔹 1. Biến `need = k - 1`

// Ta cần tìm **k - 1 lần liên tiếp** mà:

// * Cặp `(nums[j], nums[j-1])` là tăng
// * Và cặp `(nums[j-k], nums[j-k-1])` cũng là tăng.

// → Vì để có **2 dãy tăng độ dài `k`**, ta cần **(k−1) cặp tăng liền nhau song
// song** giữa 2 vùng cách nhau `k` phần tử.

// ---

// ### 🔹 2. Duyệt từ `j = k + 1` đến hết mảng

// Ta bắt đầu tại `j = k + 1` để tránh bị truy cập ngoài biên (`j - k - 1` tồn
// tại).

// Ở mỗi vị trí `j`, ta kiểm tra **2 điều kiện tăng song song**:

// ```java
// nums.get(j) > nums.get(j - 1) // dãy sau vẫn tăng
// nums.get(j - k) > nums.get(j - k - 1) // dãy trước (cách k phần tử) cũng tăng
// ```

// Nếu **cả hai đúng** → tức là phần tử ở dãy sau và phần tử tương ứng ở dãy
// trước **cùng tiếp tục tăng song song**, ta giảm `need--`.

// Nếu **một trong hai sai** → chuỗi tăng song song bị ngắt, reset lại `need = k
// - 1`.

// ---

// ### 🔹 3. Khi nào trả về `true`?

// Khi `need == 0`, tức là đã tìm đủ **k−1 cặp tăng song song liên tiếp**,
// → đồng nghĩa tồn tại 2 dãy tăng độ dài `k` liền nhau
// → ta trả về `true`.

// ---

// ## 🧩 Diễn biến ví dụ

// ```
// nums = [2,5,7,8,9,2,3,4,3,1]
// k = 3
// need = 2
// ```

// * `j = 4 → nums[4]=9`

// * `nums[4] > nums[3]` ✅
// * `nums[1] > nums[0]` ✅
// → `need = 1`

// * `j = 5 → nums[5]=2`

// * `nums[5] > nums[4]` ❌ → reset `need = 2`

// * `j = 8 → nums[8]=3`

// * `nums[8] > nums[7]` ❌
// * reset `need = 2`

// * `j = 7 → nums[7]=4`

// * `nums[7] > nums[6]` ✅
// * `nums[4] > nums[3]` ✅
// → `need = 1`

// * `j = 8`

// * `nums[8] > nums[7]` ❌
// → reset `need = 2`

// Nhưng ở giai đoạn `j=7`, ta đã tìm được 2 chuỗi tăng độ dài `k` liền nhau.
// ✅ Trả về `true`.

// ---

// ## 🧩 Độ phức tạp

// * **Thời gian:** O(n) (chạy 1 vòng qua danh sách)
// * **Không gian:** O(1)

// ---

// ## ✅ Tóm tắt lại ý nghĩa

// | Biến | Ý nghĩa |
// | ------------------------- | ------------------------------------ |
// | `k` | độ dài mỗi chuỗi tăng cần tìm |
// | `need = k - 1` | số cặp tăng song song cần tích lũy |
// | `j` | chỉ số đang xét hiện tại |
// | `nums[j] > nums[j-1]` | chuỗi sau đang tăng |
// | `nums[j-k] > nums[j-k-1]` | chuỗi trước đang tăng |
// | `need == 0` | đủ điều kiện có 2 dãy tăng liền nhau |

// ---

// Bạn có muốn mình minh họa thêm **bằng hình vẽ 2 vùng tăng song song** để dễ
// hình dung hơn không? (nó rất trực quan).
