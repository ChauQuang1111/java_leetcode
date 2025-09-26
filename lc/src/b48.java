
// 611. Valid Triangle Number(26/09/2025)
import java.util.*;

public class b48 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = triangleNumber(nums);
        System.out.println(result);

        sc.close();
    }

    // Hàm chính: đếm số tam giác hợp lệ
    public static int triangleNumber(int[] nums) {
        // Bước 1: sắp xếp mảng
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        // Bước 2: chọn nums[k] làm cạnh lớn nhất, duyệt từ cuối về
        for (int k = n - 1; k >= 2; k--) {
            int i = 0, j = k - 1; // i: nhỏ nhất, j: ngay trước k

            // Dùng 2 con trỏ để tìm cặp (i, j)
            while (i < j) {
                // Nếu nums[i] + nums[j] > nums[k]
                if (nums[i] + nums[j] > nums[k]) {
                    // Thì tất cả cặp (i..j-1, j) đều hợp lệ
                    count += (j - i);
                    j--; // giảm j để thử cặp nhỏ hơn
                } else {
                    i++; // tăng i để làm tổng lớn hơn
                }
            }
        }
        return count;
    }
}

// Ok mình sẽ giải thích thật chi tiết thuật toán trong code trên 👇

// ---

// ## 🚩 Bài toán nhắc lại

// Cho một mảng `nums`, ta cần đếm số bộ ba `(a, b, c)` sao cho chúng tạo thành
// **tam giác hợp lệ**.
// Điều kiện tam giác:

// ```
// a + b > c
// a + c > b
// b + c > a
// ```

// Nếu ta **sắp xếp mảng tăng dần**, thì với `a ≤ b ≤ c`, chỉ cần kiểm tra:

// ```
// a + b > c
// ```

// là đủ, vì 2 điều kiện còn lại chắc chắn đúng.

// ---

// ## 🚀 Thuật toán (Sort + Two Pointers)

// 1. **Sort mảng**

// * `Arrays.sort(nums);`
// * Giúp ta áp dụng quy tắc `a ≤ b ≤ c`.

// 2. **Chọn cạnh lớn nhất (c)**

// * Duyệt từ cuối mảng về đầu: `for (int k = n - 1; k >= 2; k--)`
// * `nums[k]` là cạnh lớn nhất.

// 3. **Dùng hai con trỏ tìm a và b**

// * `i = 0` (bắt đầu), `j = k - 1` (ngay trước k).
// * Xét `nums[i] + nums[j] > nums[k]`:

// * Nếu **đúng** → tất cả cặp `(i..j-1, j)` đều hợp lệ (vì mảng đã sort, nên
// bất kỳ `nums[i’]` với `i ≤ i’ < j` đều ≥ nums[i], do đó `nums[i’] + nums[j] >
// nums[k]` vẫn đúng).
// → cộng `(j - i)` vào kết quả.
// → giảm `j--` để thử cặp nhỏ hơn.
// * Nếu **sai** → nghĩa là `nums[i] + nums[j] ≤ nums[k]`, thì ta cần tăng `i++`
// để có tổng lớn hơn.

// 4. **Trả về tổng số bộ ba**.

// ---

// ## 🔎 Ví dụ minh họa

// Input:

// ```
// nums = [2, 2, 3, 4]
// ```

// * Sau sort: `[2, 2, 3, 4]`

// ### Vòng lặp 1: `k = 3`, `nums[k] = 4`

// * `i = 0, j = 2`

// * `nums[i] + nums[j] = 2 + 3 = 5 > 4` → hợp lệ
// → thêm `j - i = 2` (cặp `(2,3,4)` và `(2,3,4)` với 2 ở vị trí khác).
// → `count = 2`
// → `j-- = 1`
// * `i = 0, j = 1`

// * `2 + 2 = 4` **không > 4** → tăng `i = 1`
// * Kết thúc vòng này.

// ### Vòng lặp 2: `k = 2`, `nums[k] = 3`

// * `i = 0, j = 1`

// * `2 + 2 = 4 > 3` → hợp lệ
// → thêm `1`
// → `count = 3`
// → `j-- = 0` → dừng.

// ---

// ## ✅ Kết quả

// Có **3 tam giác hợp lệ**:

// * `(2, 3, 4)` (từ 2 đầu tiên)
// * `(2, 3, 4)` (từ 2 thứ hai)
// * `(2, 2, 3)`

// ---

// 👉 Thuật toán này chạy trong **O(n²)** thay vì **O(n³)** (duyệt 3 vòng lồng
// nhau).

// ---

// Bạn có muốn mình **vẽ sơ đồ hai con trỏ i, j di chuyển** trên mảng đã sắp xếp
// để dễ hình dung hơn không?

// Ok, mình giải thích chi tiết bài **LeetCode 611. Valid Triangle Number** nhé
// 🚀

// ---

// ### 📌 Đề bài

// Cho một mảng số nguyên dương `nums`.
// Hãy **đếm số bộ ba (i, j, k)** (với `i < j < k`) sao cho `nums[i], nums[j],
// nums[k]` có thể tạo thành **một tam giác hợp lệ**.

// ---

// ### 📐 Điều kiện tam giác hợp lệ

// Với 3 cạnh `a, b, c`, điều kiện là:

// ```
// a + b > c
// a + c > b
// b + c > a
// ```

// 👉 Nếu ta **sắp xếp mảng tăng dần** (`a ≤ b ≤ c`) thì chỉ cần kiểm tra:

// ```
// a + b > c
// ```

// vì điều này tự động đảm bảo 2 bất đẳng thức còn lại.

// ---

// ### 🔎 Ví dụ

// #### Input:

// ```
// nums = [2, 2, 3, 4]
// ```

// #### Các bộ ba:

// * (2, 2, 3) → 2 + 2 > 3 ✅
// * (2, 3, 4) → 2 + 3 > 4 ✅
// * (2, 2, 4) → 2 + 2 > 4 ❌
// * (2, 3, 4) → đã tính
// * (2, 2, 3) → đã tính

// 👉 Kết quả = **3** bộ tam giác hợp lệ.

// ---

// ### 🧠 Ý tưởng giải

// 1. **Sort mảng** trước.
// 2. Dùng **2-pointer** để đếm số cặp `(i, j)` thỏa điều kiện với mỗi `k` (là
// cạnh lớn nhất).

// Cụ thể:

// * Fix `k` (cạnh lớn nhất).
// * Dùng `i = 0`, `j = k - 1`.
// * Nếu `nums[i] + nums[j] > nums[k]` → tất cả các cặp `(i ... j-1, j)` đều hợp
// lệ (vì mảng sort rồi).
// * Ngược lại → tăng `i` để tìm cặp lớn hơn.

// ---

// ### ⏱️ Độ phức tạp

// * Sắp xếp: `O(n log n)`
// * Duyệt + 2-pointer: `O(n^2)`
// 👉 Tổng: `O(n^2)` (tốt hơn brute force `O(n^3)`).

// ---

// 👉 Bạn có muốn mình viết luôn code Python tối ưu cho bài này không?
