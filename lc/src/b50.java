
// 976. Largest Perimeter Triangle(28/09/2025)
import java.util.*;

public class b50 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Gọi hàm tính chu vi tam giác lớn nhất
        int result = largestPerimeter(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    // Hàm tính chu vi tam giác lớn nhất
    public static int largestPerimeter(int[] nums) {
        int n = nums.length;

        if (n < 3)
            return 0; // Không đủ 3 cạnh → không tạo được tam giác

        // Bước 1: tìm 2 cạnh lớn nhất, đặt vào cuối mảng
        selectAndSwapMax(nums, n - 1); // max 1 → nums[n-1]
        selectAndSwapMax(nums, n - 2); // max 2 → nums[n-2]

        // Bước 2: duyệt từ cuối mảng tới phần tử thứ 2
        for (int i = n - 1; i >= 2; i--) {
            // Bước 2a: tìm cạnh lớn thứ 3 trong nums[0..i-2] và đặt vào nums[i-2]
            selectAndSwapMax(nums, i - 2);

            // Bước 2b: kiểm tra điều kiện tam giác
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                // Nếu thỏa → trả về chu vi
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }

        // Nếu không tìm được tam giác hợp lệ
        return 0;
    }

    // Hàm chọn max trong nums[0..idx] và swap về vị trí idx
    public static void selectAndSwapMax(int[] nums, int idx) {
        if (idx < 0)
            return; // Nếu idx < 0 → mảng rỗng hoặc không hợp lệ, thoát hàm

        int maxValue = nums[0]; // Lưu giá trị lớn nhất tạm thời, khởi tạo bằng phần tử đầu tiên
        int maxIndex = 0; // Lưu chỉ số của giá trị lớn nhất tạm thời

        // Duyệt từ 1 đến idx để tìm giá trị lớn nhất trong nums[0..idx]
        for (int i = 1; i <= idx; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i]; // Cập nhật giá trị lớn nhất
                maxIndex = i; // Cập nhật vị trí của giá trị lớn nhất
            }
        }

        // Đổi chỗ giá trị lớn nhất với phần tử tại vị trí idx
        int temp = nums[idx];
        nums[idx] = maxValue;
        nums[maxIndex] = temp;
    }
}

// # ### 📌 Đề bài:

// # * Bạn được cho một mảng số nguyên `nums`, trong đó mỗi phần tử là **độ dài
// cạnh**.
// # * Nhiệm vụ: **tìm chu vi lớn nhất của một tam giác có thể tạo thành từ 3
// cạnh trong mảng**.
// # * Nếu không thể tạo được tam giác hợp lệ, trả về `0`.

// # ---

// # ### 📌 Điều kiện tạo tam giác hợp lệ:

// # Với ba cạnh `a, b, c` (sắp xếp theo `a ≤ b ≤ c`), cần thỏa:

// # ```
// # a + b > c
// # ```

// # Nếu điều kiện này không đúng thì **không tạo thành tam giác**.

// # ---

// # ### 📌 Ví dụ minh họa:

// # #### Ví dụ 1:

// # ```
// # Input: nums = [2,1,2]
// # ```

// # * Sắp xếp: [1,2,2]
// # * Kiểm tra 3 cạnh: `1 + 2 > 2` ✅ đúng → tạo được tam giác.
// # * Chu vi = 1 + 2 + 2 = **5**.

// # 👉 Output: `5`

// # ---

// # #### Ví dụ 2:

// # ```
// # Input: nums = [1,2,1]
// # ```

// # * Sắp xếp: [1,1,2]
// # * Kiểm tra: `1 + 1 > 2` ❌ sai.
// # * Không thể tạo tam giác.

// # 👉 Output: `0`

// # ---

// # #### Ví dụ 3:

// # ```
// # Input: nums = [3,6,2,3]
// # ```

// # * Sắp xếp: [2,3,3,6]
// # * Lấy 3 cạnh lớn nhất [3,3,6]: `3 + 3 > 6` ❌ sai.
// # * Lấy [2,3,3]: `2 + 3 > 3` ✅ đúng.
// # * Chu vi = 2 + 3 + 3 = **8**.

// # 👉 Output: `8`

// # ---

// # ### 📌 Ý tưởng giải:

// # 1. Sắp xếp mảng tăng dần.
// # 2. Duyệt ngược từ cuối (các cạnh lớn nhất) → kiểm tra 3 cạnh liên tiếp
// `nums[i-2], nums[i-1], nums[i]`.

// # * Nếu tạo thành tam giác, trả về chu vi luôn (vì đó là chu vi lớn nhất).
// # 3. Nếu không tìm được, trả về `0`.

// # ---

// # Bạn có muốn mình viết code Java/Python kèm chú thích để minh họa luôn
// không?
// Ok, mình sẽ giải thích thuật toán trong code Java bạn đưa ra cho **976.
// Largest Perimeter Triangle**:

// ---

// ### 🔹 Ý tưởng:

// * Muốn tìm **chu vi lớn nhất của tam giác** từ mảng `nums`.
// * Điều kiện tam giác: 3 cạnh `a ≤ b ≤ c` phải thỏa:

// ```
// a + b > c
// ```
// * Thay vì sort cả mảng (O(n log n)), thuật toán này **tìm 3 cạnh lớn nhất dần
// dần** bằng phương pháp **selection** (O(n²) worst case nhưng không sort toàn
// bộ).

// ---

// ### 🔹 Thuật toán trong code:

// ```java
// int n = nums.length;

// // Bước 1: tìm 2 cạnh lớn nhất, đưa về cuối mảng
// selectAndSwapMax(nums, n - 1); // max đầu tiên về nums[n-1]
// selectAndSwapMax(nums, n - 2); // max thứ 2 về nums[n-2]
// ```

// * `selectAndSwapMax(nums, idx)`:

// * Duyệt mảng từ `0..idx`, tìm max, swap với vị trí `idx`.
// * Mục đích: đảm bảo `nums[n-1]` và `nums[n-2]` là 2 cạnh lớn nhất.

// ---

// ```java
// // Bước 2: duyệt từ cuối mảng tới vị trí thứ 2
// for (int i = n - 1; i >= 2; i--) {

// // Tìm cạnh lớn thứ 3 trong nums[0..i-2] và đưa về nums[i-2]
// selectAndSwapMax(nums, i - 2);

// // Kiểm tra điều kiện tam giác
// if (nums[i] < nums[i - 1] + nums[i - 2]) {
// return nums[i] + nums[i - 1] + nums[i - 2]; // Chu vi lớn nhất
// }
// }
// ```

// * Giải thích:

// * Với `i = n-1`, 3 cạnh lớn nhất hiện tại là:

// ```
// nums[i-2], nums[i-1], nums[i]
// ```
// * Nếu thỏa điều kiện tam giác: trả về chu vi.
// * Nếu không: tiếp tục duyệt `i--`, tìm cạnh thứ 3 lớn nhất trong phần còn lại
// để thử.

// ---

// ### 🔹 Hàm selectAndSwapMax:

// ```java
// private void selectAndSwapMax(int[] nums, int idx) {
// int maxValue = nums[0];
// int maxIndex = 0;

// for (int i = 1; i <= idx; i++) {
// if (nums[i] > maxValue) {
// maxValue = nums[i];
// maxIndex = i;
// }
// }

// int temp = nums[idx];
// nums[idx] = maxValue;
// nums[maxIndex] = temp;
// }
// ```

// * Chọn max trong `nums[0..idx]` và swap về vị trí `idx`.
// * Giúp 3 cạnh lớn nhất lần lượt được đặt ở cuối mảng mà không cần sort toàn
// bộ.

// ---

// ### 🔹 Tóm tắt:

// 1. Tìm cạnh lớn nhất và lớn nhì → đưa về cuối mảng.
// 2. Duyệt từ cuối mảng, mỗi bước tìm cạnh thứ 3 lớn nhất → kiểm tra tam giác.
// 3. Nếu thỏa → trả về chu vi, else → tiếp tục.
// 4. Nếu không tìm được tam giác hợp lệ → trả về 0.

// ---

// 💡 **Ưu điểm**:

// * Không cần sort toàn bộ mảng → tiết kiệm thời gian cho mảng rất lớn.
// * Luôn kiểm tra các cạnh lớn nhất trước → đảm bảo chu vi là lớn nhất.

// ---

// Nếu bạn muốn, mình có thể viết **version có Scanner và main để nhập dữ liệu
// từ người dùng** theo code này luôn.

// Bạn có muốn mình làm không?
// Ok!

// // Mình sẽ
// // viết lại
// // code Java
// // cho bài**976.
// // Largest Perimeter Triangle**
// // theo thuật toán`selectAndSwapMax`
// // của bạn, kèm**
// // hàm main
// // dùng Scanner**và**
// // chú thích
// // chi tiết
// // từng dòng**:

// Giải thích
// thuật toán:

// 1.**selectAndSwapMax**:

// *
// Chọn phần
// tử lớn
// nhất trong
// mảng con`nums[0..idx]`
// và swap
// về vị trí`idx`.*
// Giúp đảm
// bảo rằng 3
// cạnh lớn
// nhất lần
// lượt nằm
// ở cuối
// mảng mà
// không cần
// sort toàn bộ.

// 2.**
// Duyệt mảng
// từ cuối**:

// *Lấy 3
// cạnh lớn
// liên tiếp`nums[i-2],nums[i-1],nums[i]`.*
// Kiểm tra
// điều kiện
// tam giác`a+b>c`.*
// Nếu đúng→
// trả về
// chu vi
// lớn nhất.

// 3.**
// Nếu không
// có tam
// giác hợp lệ**:

// *
// Trả về`0`.

// 4.**
// Hàm main**:

// *
// Nhập số
// lượng cạnh
// và các cạnh.*
// Tạo đối
// tượng Solution.*
// Gọi hàm
// và in
// chu vi
// lớn nhất.

// ---

// Ví dụ:

// ```Input:4 3 6 2 3

// Output:8```

// *
// Sort tạm:[2,3,3,6]*Duyệt 3
// cạnh lớn:(3,3,6)→3+3>6?Không*(2,3,3)→2+3>3?Có→
// chu vi = 8✅

// ---

// Nếu bạn muốn,
// mình có
// thể viết**version tối
// ưu hơn

// O(n log n) bằng sort** với code Java ngắn gọn.

// Bạn có muốn mình viết luôn không?
