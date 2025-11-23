
// // 1262. Greatest Sum Divisible by Three(23/11/2025)
// // Dưới đây là **giải thích đề bài LeetCode 1262 – Greatest Sum Divisible by Three** theo cách **dễ hiểu nhất**:
import java.util.*;

public class b107 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = maxSumDivThree(nums);

        System.out.println(result);
    }

    // Hàm chính giải bài toán
    public static int maxSumDivThree(int[] nums) {

        int sum = 0;

        // Lưu 2 số nhỏ nhất có mod = 1
        int min1 = Integer.MAX_VALUE; // nhỏ nhất
        int min11 = Integer.MAX_VALUE; // nhỏ thứ 2

        // Lưu 2 số nhỏ nhất có mod = 2
        int min2 = Integer.MAX_VALUE;
        int min22 = Integer.MAX_VALUE;

        // Duyệt qua từng phần tử
        for (int x : nums) {
            sum += x; // cộng vào tổng
            int r = x % 3; // lấy phần dư

            // Nếu dư 1 → cập nhật 2 số nhỏ nhất mod 1
            if (r == 1) {
                if (x < min1) {
                    min11 = min1;
                    min1 = x;
                } else if (x < min11) {
                    min11 = x;
                }
            }
            // Nếu dư 2 → cập nhật 2 số nhỏ nhất mod 2
            else if (r == 2) {
                if (x < min2) {
                    min22 = min2;
                    min2 = x;
                } else if (x < min22) {
                    min22 = x;
                }
            }
        }

        int rem = sum % 3; // Kiểm tra tổng dư mấy

        // Nếu tổng chia hết cho 3 → trả về luôn
        if (rem == 0)
            return sum;

        // Nếu tổng dư 1 → có 2 cách sửa:
        // 1) bỏ 1 số mod=1
        // 2) bỏ 2 số mod=2
        if (rem == 1) {
            int remove1 = min1;
            int remove2 = (min2 == Integer.MAX_VALUE || min22 == Integer.MAX_VALUE)
                    ? Integer.MAX_VALUE
                    : min2 + min22;

            int remove = Math.min(remove1, remove2);
            return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
        }

        // Nếu tổng dư 2 → có 2 cách sửa:
        // 1) bỏ 1 số mod=2
        // 2) bỏ 2 số mod=1
        else {
            int remove1 = min2;
            int remove2 = (min1 == Integer.MAX_VALUE || min11 == Integer.MAX_VALUE)
                    ? Integer.MAX_VALUE
                    : min1 + min11;

            int remove = Math.min(remove1, remove2);
            return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
        }
    }

}

// import java.util.*;

// public class Solution {

// // Hàm chính giải bài toán
// public int maxSumDivThree(int[] nums) {

// int sum = 0;

// // Lưu 2 số nhỏ nhất có mod = 1
// int min1 = Integer.MAX_VALUE; // nhỏ nhất
// int min11 = Integer.MAX_VALUE; // nhỏ thứ 2

// // Lưu 2 số nhỏ nhất có mod = 2
// int min2 = Integer.MAX_VALUE;
// int min22 = Integer.MAX_VALUE;

// // Duyệt qua từng phần tử
// for (int x : nums) {
// sum += x; // cộng vào tổng
// int r = x % 3; // lấy phần dư

// // Nếu dư 1 → cập nhật 2 số nhỏ nhất mod 1
// if (r == 1) {
// if (x < min1) {
// min11 = min1;
// min1 = x;
// } else if (x < min11) {
// min11 = x;
// }
// }
// // Nếu dư 2 → cập nhật 2 số nhỏ nhất mod 2
// else if (r == 2) {
// if (x < min2) {
// min22 = min2;
// min2 = x;
// } else if (x < min22) {
// min22 = x;
// }
// }
// }

// int rem = sum % 3; // Kiểm tra tổng dư mấy

// // Nếu tổng chia hết cho 3 → trả về luôn
// if (rem == 0) return sum;

// // Nếu tổng dư 1 → có 2 cách sửa:
// // 1) bỏ 1 số mod=1
// // 2) bỏ 2 số mod=2
// if (rem == 1) {
// int remove1 = min1;
// int remove2 = (min2 == Integer.MAX_VALUE || min22 == Integer.MAX_VALUE)
// ? Integer.MAX_VALUE : min2 + min22;

// int remove = Math.min(remove1, remove2);
// return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
// }

// // Nếu tổng dư 2 → có 2 cách sửa:
// // 1) bỏ 1 số mod=2
// // 2) bỏ 2 số mod=1
// else {
// int remove1 = min2;
// int remove2 = (min1 == Integer.MAX_VALUE || min11 == Integer.MAX_VALUE)
// ? Integer.MAX_VALUE : min1 + min11;

// int remove = Math.min(remove1, remove2);
// return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
// }
// }

// // Hàm main dùng Scanner
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);

// // Nhập số lượng phần tử
// int n = sc.nextInt();
// int[] nums = new int[n];

// // Nhập mảng
// for (int i = 0; i < n; i++) {
// nums[i] = sc.nextInt();
// }

// // Gọi hàm giải bài và in kết quả
// Solution sol = new Solution();
// int result = sol.maxSumDivThree(nums);

// System.out.println(result);
// }
// }

// // Dưới đây là **giải thích thuật toán** của bạn theo cách **ngắn – dễ hiểu –
// đúng ý LeetCode 1262**.

// // ---

// // # 🎯 **Mục tiêu**

// // Tìm tổng lớn nhất của một tập con các số trong `nums` sao cho **tổng chia
// hết cho 3**.

// // ---

// // # 🔥 **Ý tưởng chính của thuật toán**

// // ### ✔ 1. Tính tổng tất cả các số

// // Nếu tổng này **chia hết cho 3** → trả về luôn vì đó là tổng lớn nhất có
// thể.

// // ### ✔ 2. Nếu tổng **không chia hết cho 3**

// // * Tổng có thể dư **1 hoặc 2**
// // * Để khử phần dư này, ta phải **loại bỏ số nhỏ nhất phù hợp**

// // 👉 Điều quan trọng:

// // * Dư **1** → bỏ **(1 số dư 1)** hoặc **(2 số dư 2)**
// // * Dư **2** → bỏ **(1 số dư 2)** hoặc **(2 số dư 1)**

// // Ta chọn phương án nào **giảm tổng ít nhất**.

// ---

// # 🧠 **Tại sao phải lưu nhiều biến như `min1, min11, min2, min22`?**

// // ### Vì:

// // * Nếu cần bỏ **1 số mod 1**, ta dùng `min1`
// // * Nếu cần bỏ **2 số mod 1**, ta dùng `min1 + min11` (2 số nhỏ nhất mod1)
// // * Nếu cần bỏ **2 số mod 2**, ta dùng `min2 + min22`
// // * Nếu cần bỏ **1 số mod 2**, ta dùng `min2`

// // // 👉 Mục tiêu: luôn có giá trị **nhỏ nhất** để bỏ → giữ tổng lớn nhất.

// // // ---

// // // # 🧩 **Giải thích code theo từng phần**

// // // ## ### 🔸 1. Khởi tạo

// // // ```java
// // // int min1 = Integer.MAX_VALUE;
// // // int min11 = Integer.MAX_VALUE;
// // // int min2 = Integer.MAX_VALUE;
// // // int min22 = Integer.MAX_VALUE;
// // // ```

// // // * `min1`, `min11`: 2 số nhỏ nhất có **mod = 1**
// // // * `min2`, `min22`: 2 số nhỏ nhất có **mod = 2**

// // ---

// // ## ### 🔸 2. Duyệt mảng để tìm tổng và 2 số nhỏ nhất theo từng loại

// // ```java
// // for (int x : nums) {
// // sum += x;
// // int r = x % 3;

// // if (r == 1) {
// // if (x < min1) { min11 = min1; min1 = x; }
// // else if (x < min11) min11 = x;
// // }
// // else if (r == 2) {
// // if (x < min2) { min22 = min2; min2 = x; }
// // else if (x < min22) min22 = x;
// // }
// // }
// // ```

// // Ví dụ mod = 1:

// // | Số gặp | min1 | min11 |
// // | ------ | ---- | ----- |
// // | 7 | 7 | INF |
// // | 4 | 4 | 7 |
// // | 10 | 4 | 7 |

// // ---

// // ## ### 🔸 3. Xử lý theo phần dư của tổng

// // ### **Nếu sum % 3 == 0** → không cần làm gì:

// // ```java
// // if (rem == 0) return sum;

// // ---

// // ## ### 🔸 4. Nếu sum % 3 == 1

// // Ta có 2 cách:

// // * **Bỏ 1 số mod=1** → giá trị là `min1`
// // * **Bỏ 2 số mod=2** → giá trị là `min2 + min22`

// // Lấy cách **loại bỏ ít nhất**:

// // ```java
// // int remove1 = min1;
// // int remove2 = (min2 == Integer.MAX_VALUE || min22 == Integer.MAX_VALUE)
// // ? Integer.MAX_VALUE : min2 + min22;
// // int remove = Math.min(remove1, remove2);
// // return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
// ```

// ---

// ## ### 🔸 5. Nếu sum % 3 == 2

// Tương tự:

// * **Bỏ 1 số mod=2** → `min2`
// * **Bỏ 2 số mod=1** → `min1 + min11`

// ```java
// int remove1 = min2;
// int remove2 = (min1 == Integer.MAX_VALUE || min11 == Integer.MAX_VALUE)
// ? Integer.MAX_VALUE : min1 + min11;
// int remove = Math.min(remove1, remove2);
// return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
// ```

// ---

// # ✅ **Tại sao thuật toán đúng và tối ưu?**

// * Chỉ cần sửa phần dư 1 hoặc 2 để tổng chia hết cho 3.
// * Muốn tổng lớn nhất → **bỏ đi số nhỏ nhất có mod phù hợp**.
// * Việc lưu 2 số nhỏ nhất bảo đảm ta luôn có lựa chọn tối ưu nhất.

// ⏱ **Độ phức tạp: O(n)**
// ➡ Nhanh nhất cho bài này.

// ---

// Nếu bạn muốn, mình có thể **viết phiên bản code ngắn hơn**, hoặc **giải thích
// bằng hình minh họa**, hoặc **debug code của bạn**!

// // ---

// // # 🎯 **Yêu cầu bài toán**

// // Bạn được cho một mảng số nguyên `nums`.
// // Bạn cần **chọn ra một số phần tử bất kỳ** trong mảng (có thể là không chọn
// tất cả), sao cho:

// // 1. **Tổng các phần tử được chọn chia hết cho 3**
// // 2. **Tổng đó là lớn nhất có thể**

// // → Đây là một bài toán **tối ưu hóa**: vừa phải chia hết cho 3, vừa phải
// lớn nhất.

// // ---

// // # 🔍 **Điều quan trọng cần hiểu**

// // ### ✔ Mỗi số khi chia cho 3 sẽ có 3 loại:

// // * `mod 0` → chia hết cho 3
// // * `mod 1`
// // * `mod 2`

// // ### ✔ Tổng của bạn cũng có mod theo cách tương tự:

// // * Nếu tổng cuối cùng `mod 0` → hợp lệ.

// // ---

// // # 🧠 **Ý tưởng của bài**

// // ### B1: Tính tổng tất cả các số

// // Nếu **tổng chia hết cho 3** → trả về luôn, vì lấy tất cả sẽ là lớn nhất.

// // ### B2: Nếu tổng **mod = 1**

// // Để làm tổng chia hết cho 3, bạn có 2 cách:

// // * **Bỏ đi 1 số nhỏ nhất có mod = 1**
// // * **Hoặc bỏ đi 2 số nhỏ nhất có mod = 2**

// // Chọn cách nào làm **mất ít giá trị nhất** → giữ tổng lớn nhất.

// // ### B3: Nếu tổng **mod = 2**

// // Ngược lại:

// // * **Bỏ 1 số nhỏ nhất có mod = 2**
// // * Hoặc **bỏ 2 số nhỏ nhất có mod = 1**

// // ---

// // # 📌 Ví dụ minh họa

// // ```
// // nums = [3, 6, 5, 1, 8]
// // ```

// // Tổng = 23 → `23 % 3 = 2`

// // Mod 2 → cần làm giảm tổng xuống mod 0.

// // Có 2 cách:

// // * Bỏ 1 số mod 2 → số nhỏ nhất mod 2 là `5` → tổng = 18
// // * Bỏ 2 số mod 1 → `1 + 1 (không có số mod1 thứ 2)` → không làm được

// // 👉 Kết quả: **18**

// // ---

// // # 🚀 **Kết luận**

// // Bài này yêu cầu:

// // * Tính tổng
// // * Nếu không chia hết cho 3 → loại bỏ số nhỏ nhất phù hợp để biến tổng
// thành chia hết cho 3
// // * Giữ tổng càng lớn càng tốt

// // ---

// // Nếu bạn muốn mình viết **code Java / C++ / Python** + giải thích từng
// dòng, bạn nhắn mình nhé!
