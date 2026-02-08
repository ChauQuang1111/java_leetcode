// Minimum Removals to Balance Array(06/02/2026)
// ### 🧠 Giải thích đề bài: **Minimum Removals to Balance Array**
// Ok 👍 bài **3634. Minimum Removals to Balance Array** (theo code bạn gửi) thực chất không phải “chẵn = lẻ” nữa, mà là dạng:
import java.util.*;   

public class b171{
     static Scanner sc = new Scanner(System.in);
   public static void main(String[] args) {
     int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập các phần tử
        System.out.println("Nhap cac phan tu:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

     
        int k = sc.nextInt();

        
        // Gọi hàm và in kết quả
        int result = minRemoval(nums, k);

        System.out.println( result);

        sc.close();
    }
 // Hàm chính giải bài toán
    public static  int minRemoval(int[] nums, int k) {

        // B1: Sắp xếp mảng tăng dần
        Arrays.sort(nums);

        int i = 0;          // Con trỏ trái của sliding window
        int maxLen = 0;    // Độ dài dãy con hợp lệ dài nhất

        // j là con trỏ phải
        for (int j = 0; j < nums.length; j++) {

            // Nếu phần tử lớn nhất > nhỏ nhất * k → không cân bằng
            while ((long) nums[j] > (long) nums[i] * k) {
                i++;   // Thu hẹp window từ bên trái
            }

            // Cập nhật độ dài window hợp lệ lớn nhất
            maxLen = Math.max(maxLen, j - i + 1);
        }

        // Số phần tử cần xóa = tổng - giữ lại dài nhất
        return nums.length - maxLen;
    }


}


// ---

// ## 📌 Ý nghĩa đề (theo thuật toán trong code)

// Cho mảng `nums` và số nguyên `k`.

// Một mảng được gọi là **balanced** nếu:

// ```
// max(nums_subarray) ≤ min(nums_subarray) * k
// ```

// Tức là:

// > Phần tử lớn nhất không được lớn hơn k lần phần tử nhỏ nhất.

// Bạn được phép **xóa phần tử bất kỳ**.

// 👉 Mục tiêu:
// Xóa ít nhất phần tử để mảng còn lại thỏa điều kiện trên.

// ---

// ## 🧠 Ý tưởng chính

// Thay vì nghĩ “xóa gì”, ta nghĩ ngược lại:

// > Tìm **dãy con dài nhất** thỏa điều kiện balanced.

// Vì:

// ```
// Min removals = n - độ dài dãy giữ lại dài nhất
// ```

// ---

// ## 🔧 Bước 1 — Sort mảng

// ```java
// Arrays.sort(nums);
// ```

// Tại sao cần sort?

// Vì khi sort:

// * `nums[i]` = nhỏ nhất trong window
// * `nums[j]` = lớn nhất trong window

// Ta chỉ cần check:

// ```
// nums[j] ≤ nums[i] * k
// ```

// ---

// ## 🔁 Bước 2 — Sliding Window (2 con trỏ)

// ```java
// int i = 0;
// for (int j = 0; j < nums.length; j++)
// ```

// * `i` = đầu window
// * `j` = cuối window

// Window = đoạn `[i … j]` đang xét.

// ---

// ## ⚠️ Điều kiện phá vỡ cân bằng

// ```java
// while (nums[j] > nums[i] * k) {
//     i++;
// }
// ```

// Nếu:

// ```
// max > min * k
// ```

// → Window không hợp lệ
// → Phải **dịch i sang phải** để tăng min.

// ---

// ## 📊 Minh họa ví dụ

// ```
// nums = [1, 2, 4, 8]
// k = 2
// ```

// Sau sort vẫn vậy.

// ---

// ### j = 0

// Window: [1]

// ```
// 1 ≤ 1*2 → OK
// len = 1
// ```

// ---

// ### j = 1

// Window: [1,2]

// ```
// 2 ≤ 1*2 → OK
// len = 2
// ```

// ---

// ### j = 2

// Window: [1,2,4]

// ```
// 4 ≤ 1*2 = 2 ❌
// ```

// Phải tăng i:

// * i = 1 → min = 2

// Check lại:

// ```
// 4 ≤ 2*2 = 4 → OK
// ```

// Window mới: [2,4]
// len = 2

// ---

// ### j = 3

// Window: [2,4,8]

// ```
// 8 ≤ 2*2 = 4 ❌
// ```

// Tăng i:

// * i = 2 → min = 4

// ```
// 8 ≤ 4*2 = 8 → OK
// ```

// Window: [4,8]
// len = 2

// ---

// ## 🧮 Cập nhật độ dài lớn nhất

// ```java
// maxLen = Math.max(maxLen, j - i + 1);
// ```

// Giữ window dài nhất hợp lệ.

// ---

// ## 📤 Kết quả cuối

// ```java
// return nums.length - maxLen;
// ```

// Vì:

// * `maxLen` = số phần tử giữ lại nhiều nhất
// * Xóa ít nhất = tổng − giữ lại

// ---

// ## 🔍 Phân tích độ phức tạp

// * Sort: `O(n log n)`
// * Sliding window: `O(n)`
// * Tổng: `O(n log n)`

// Không có vòng lặp lồng nhau thực sự vì `i` chỉ tăng.

// ---

// ## 🧠 Insight quan trọng

// 1. Balanced phụ thuộc **min và max**.
// 2. Sort → window tự đảm bảo min ở trái, max ở phải.
// 3. Khi vi phạm → chỉ cần tăng `i`.
// 4. Bản chất: **Longest Valid Subarray**.

// ---

// ## 🧩 Tóm tắt thuật toán

// 1. Sort mảng.
// 2. Dùng 2 con trỏ tạo window.
// 3. Nếu `max > min*k` → tăng `i`.
// 4. Lưu độ dài lớn nhất.
// 5. Kết quả = `n - maxLen`.

// ---

// Nếu bạn muốn mình:

// * Vẽ timeline con trỏ i/j.
// * Dry run chi tiết từng dòng code.
// * Viết lại bằng Python / giải thích dễ hơn.

// Cứ nói mình biết 👍

// Bài này thường được hiểu như sau (theo LeetCode / DSA chuẩn):

// ---

// ## 📌 Đề bài (dịch nghĩa)

// Cho một mảng số nguyên `nums`.

// Một mảng được gọi là **balanced (cân bằng)** nếu:

// > **Tổng các phần tử ở vị trí chẵn = Tổng các phần tử ở vị trí lẻ**
// > (đếm theo index 0-based).

// Nhiệm vụ của bạn:

// 👉 **Xóa ít phần tử nhất** (có thể xóa ở bất kỳ vị trí nào) để mảng sau khi xóa trở thành **balanced**.

// Trả về **số phần tử tối thiểu cần xóa**.

// ---

// ## 📊 Ví dụ minh họa

// ### Ví dụ 1

// ```
// nums = [2, 1, 6, 4]
// ```

// Index:   0  1  2  3
// Value:   2  1  6  4

// * Tổng chẵn = 2 + 6 = 8
// * Tổng lẻ   = 1 + 4 = 5
//   → Không cân bằng

// Thử xóa từng phần tử:

// * Xóa 2 → [1,6,4]

//   * chẵn = 1 + 4 = 5
//   * lẻ = 6 = 6 ❌

// * Xóa 1 → [2,6,4]

//   * chẵn = 2 + 4 = 6
//   * lẻ = 6 = 6 ✅ Balanced

// Chỉ cần xóa **1 phần tử** → Kết quả = **1**

// ---

// ### Ví dụ 2

// ```
// nums = [1,1,1]
// ```

// * chẵn = 1 + 1 = 2
// * lẻ = 1 = 1 ❌

// Thử xóa:

// * Xóa index 0 → [1,1] → chẵn = 1, lẻ = 1 ✅

// → Kết quả = **1**

// ---

// ## ⚠️ Điểm quan trọng dễ nhầm

// Khi bạn **xóa 1 phần tử**:

// ➡️ Các phần tử phía sau **dịch trái**
// ➡️ Index chẵn/lẻ **đổi lại**

// Ví dụ:

// ```
// nums = [2, 1, 6, 4]

// Xóa index 1 (giá trị 1)

// Mảng mới: [2, 6, 4]
// Index mới: 0  1  2
// ```

// Nên:

// * 6 từ index 2 → thành index 1
// * 4 từ index 3 → thành index 2

// 👉 Vì vậy khi tính tổng phải tính theo **index mới**, không phải index cũ.

// ---

// ## 🎯 Tóm tắt yêu cầu

// Bạn cần:

// 1. Thử xóa từng vị trí (hoặc tính toán thông minh).
// 2. Sau khi xóa:

//    * Tính tổng vị trí chẵn.
//    * Tính tổng vị trí lẻ.
// 3. Nếu bằng nhau → hợp lệ.
// 4. Tìm số lần xóa **ít nhất**.

// ---

// ## 🧩 Bản chất thuật toán

// Để tối ưu (O(n)):

// * Dùng **prefix sum**:

//   * Tổng chẵn bên trái.
//   * Tổng lẻ bên trái.
//   * Tổng chẵn bên phải.
//   * Tổng lẻ bên phải.
// * Khi xóa 1 phần tử:

//   * Phần bên phải bị **đảo chẵn ↔ lẻ**.

// Đây là key insight của bài.

// ---

// Nếu bạn muốn, mình có thể:

// * Giải từng bước bằng hình minh họa.
// * Viết code Python/Java.
// * Giải thích prefix sum đoạn này chi tiết.

// Bạn muốn mình giải theo hướng nào tiếp? 🚀
