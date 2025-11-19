
// 2154. Keep Multiplying Found Values by Two(19/11/2025)
// Dưới đây là **giải thích đề bài LeetCode 2154 – Keep Multiplying Found Values by Two**, thật rõ ràng và dễ hiểu:
import java.util.*;

public class b103 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int original = sc.nextInt();

        // Gọi hàm và in kết quả
        int result = findFinalValue(nums, original);
        System.out.println(result);

        sc.close();
    }

    public static int findFinalValue(int[] nums, int original) {

        boolean x = true; // cờ để điều khiển while-loop

        // Lặp cho đến khi original KHÔNG còn xuất hiện trong nums
        while (x) {
            x = found(nums, original); // kiểm tra original có nằm trong nums không
            original *= 2; // nếu có thì nhân đôi
        }

        // Vì lần cuối while đã nhân đôi thêm 1 lần không cần thiết,
        // nên phải chia 2 để lấy lại giá trị đúng
        return original / 2;
    }

    // Hàm kiểm tra một số có xuất hiện trong mảng hay không
    public static boolean found(int[] nums, int var) {

        // duyệt từng phần tử của mảng
        for (int ele : nums) {

            // nếu tìm thấy var trong nums → trả về true
            if (ele == var) {
                return true;
            }
        }

        // nếu duyệt hết mà không có → trả về false
        return false;
    }
}

// ---

// # 🧩 **2154. Keep Multiplying Found Values by Two — Giải thích đề bài**

// Bạn được cho:

// * Một mảng số nguyên **`nums`**
// * Một số nguyên **`original`**

// Nhiệm vụ:

// 👉 **Trong khi `original` còn xuất hiện trong `nums`, bạn phải nhân
// `original` lên 2.**

// Khi nào `original` không còn nằm trong `nums` nữa → **trả về giá trị của
// `original`** lúc đó.

// ---

// # 🔍 **Ví dụ để hiểu ngay lập tức**

// ### **Ví dụ 1**

// ```
// nums = [5,3,6,1,12]
// original = 3
// ```

// * 3 có trong nums → nhân 2 → original = 6
// * 6 có trong nums → nhân 2 → original = 12
// * 12 có trong nums → nhân 2 → original = 24
// * 24 **không có** trong nums → dừng → trả về **24**

// ---

// ### **Ví dụ 2**

// ```
// nums = [2,7,9]
// original = 4
// ```

// 4 **không xuất hiện** trong mảng → trả về **4** ngay lập tức.

// ---

// # 🎯 **Tóm tắt yêu cầu**

// * Lặp lại nhiều lần:

// * Nếu `original` nằm trong `nums`, biến nó thành `original * 2`
// * Ngược lại, dừng và trả về giá trị hiện tại của `original`

// Đó là toàn bộ bài toán — rất đơn giản và tuyến tính.

// ---

// Nếu bạn muốn tiếp tục, mình có thể:

// ✅ giải thích thuật toán
// ✅ viết code Python/Java
// ✅ thêm comment từng dòng
// Chỉ cần bạn nói tiếp nhé!
// Dưới đây là **giải thích thuật toán** rõ ràng, dễ hiểu cho đoạn code Java bạn
// đưa:

// ---

// # 🔍 **Giải thích thuật toán**

// ### **Ý tưởng chung**

// * Kiểm tra xem giá trị `original` có trong mảng `nums` hay không.
// * Nếu có → nhân `original` lên 2 → tiếp tục kiểm tra.
// * Nếu không có → dừng → trả về giá trị cuối cùng.

// ---

// # 🧠 **Giải thích code chi tiết**

// ## **Hàm chính**

// ```java
// public int findFinalValue(int[] nums, int original) {
// boolean x = true;
// while (x) {
// x = found(nums, original);
// original *= 2;
// }
// return original / 2;
// }
// ```

// ### 🔹 **`boolean x = true;`**

// Dùng biến cờ để điều khiển vòng lặp.

// ### 🔹 **`while (x)`**

// Tiếp tục lặp cho đến khi không tìm thấy `original` trong `nums`.

// ### 🔹 **`x = found(nums, original);`**

// Gọi hàm `found()`:

// * Nếu `original` có trong `nums` → trả về `true`
// * Nếu không có → trả về `false`

// ### 🔹 **`original *= 2;`**

// Sau mỗi lần kiểm tra, bạn nhân original lên 2.

// 📌 **Lưu ý:** Khi `found()` trả về `false`, bạn vẫn nhân `original` lên 2
// thêm một lần nữa → vì vậy sau vòng lặp phải **chia lại 2** để trả về đúng kết
// quả.

// ### 🔹 **`return original / 2;`**

// Do lần cuối bạn nhân original lên 2 không cần thiết → trả lại giá trị trước
// đó.

// ---

// ## **Hàm phụ `found()`**

// ```java
// public boolean found(int [] nums, int var){
// for(int ele : nums){
// if(ele == var){
// return true;
// }
// }
// return false;
// }
// ```

// ### **Giải thích**

// * Duyệt toàn bộ mảng `nums`
// * Nếu có phần tử bằng `var` → trả về `true`
// * Nếu duyệt hết mảng không tìm được → trả về `false`

// Đây là **tìm kiếm tuyến tính** (linear search).

// ---

// # ✔️ **Mô phỏng chạy thử**

// ```
// nums = [5,3,6,1,12]
// original = 3
// ```

// 1. found(3) → true → original = 6
// 2. found(6) → true → original = 12
// 3. found(12) → true → original = 24
// 4. found(24) → false → original = 48
// → return 48/2 = **24**

// ---

// # ⭐ **Kết luận thuật toán**

// * Thuật toán dùng vòng lặp while để kiểm tra liên tục
// * Mỗi lần tìm thấy giá trị thì nhân đôi
// * Kết thúc khi không còn trong mảng
// * Dùng tìm kiếm tuyến tính O(n)
// * Tổng thời gian: O(n * số_lần_nhân_đôi)

// ---

// Nếu bạn muốn mình thêm **comment vào code**, hoặc viết lại cách tối ưu hơn,
// mình có thể làm ngay!
