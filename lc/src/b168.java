// Trionic Array I(03/02/2026)
// Mình giải thích **đề bài Trionic Array I** theo kiểu **dễ hiểu – đúng bản chất** nha 👌
// Ok 👍 mình **giải thích thuật toán từng bước – đúng logic code của bạn**, để bạn *nhìn là hiểu ngay vì sao đúng / sai*.
import java.util.*;

public class b168{
     static Scanner sc = new Scanner(System.in);
     public static void main(String[] args) {
        int n = sc.nextInt();

        // Trionic Array phải có ít nhất 3 phần tử
        if (n < 3) {
            System.out.println(false);
            return;
        }

        // Nhập mảng
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        boolean result = isTrionic(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }
     // Hàm kiểm tra mảng có phải Trionic Array hay không
    public static  boolean isTrionic(int[] nums) {
        int n = nums.length;

        // p: điểm kết thúc đoạn tăng đầu tiên
        int p = 0;

        // 1️⃣ Tìm đoạn tăng đầu tiên (nums[p] < nums[p+1])
        while (p < n - 1 && nums[p] < nums[p + 1]) {
            p++;
        }

        // Nếu p = 0 → không có đoạn tăng
        if (p == 0) {
            return false;
        }

        // q: điểm kết thúc đoạn giảm
        int q = p;

        // 2️⃣ Tìm đoạn giảm (nums[q] > nums[q+1])
        while (q < n - 1 && nums[q] > nums[q + 1]) {
            q++;
        }

        // Nếu q == p → không có đoạn giảm
        if (q == p) {
            return false;
        }

        // Nếu q ở cuối mảng → không còn đoạn tăng thứ 2
        if (q == n - 1) {
            return false;
        }

        // 3️⃣ Kiểm tra đoạn tăng cuối cùng
        for (int i = q; i < n - 1; i++) {
            // Phải tăng nghiêm ngặt
            if (nums[i] >= nums[i + 1]) {
                return false;
            }
        }

        // Qua hết các bước → là Trionic Array
        return true;
    }
  
}

    

// ---

// ## 🎯 Mục tiêu của thuật toán

// Kiểm tra mảng `nums` có thỏa **Trionic Array** hay không:

// > **Tăng → Giảm → Tăng (nghiêm ngặt)**
// > Mỗi đoạn **không rỗng**

// ---

// ## 🔢 Biến chính

// ```java
// int n = nums.length;
// int p = 0;
// ```

// * `p`: **điểm kết thúc đoạn tăng đầu tiên**
// * `q`: **điểm kết thúc đoạn giảm**

// ---

// ## 🔹 BƯỚC 1: Tìm đoạn **tăng đầu tiên**

// ```java
// while (p < n - 1 && nums[p] < nums[p + 1]) {
//     p++;
// }
// ```

// 👉 `p` chạy sang phải **chừng nào còn tăng**

// Ví dụ:

// ```
// [1, 3, 5, 4, 2, 6, 8]
//  p→1→2 (dừng ở 5)
// ```

// ---

// ### ❌ Kiểm tra đoạn tăng có tồn tại không

// ```java
// if (p == 0) {
//     return false;
// }
// ```

// * `p == 0` → **không có tăng**
// * Ví dụ: `[5, 4, 3, ...]` ❌

// ---

// ## 🔹 BƯỚC 2: Tìm đoạn **giảm**

// ```java
// int q = p;
// while (q < n - 1 && nums[q] > nums[q + 1]) {
//     q++;
// }
// ```

// 👉 Bắt đầu từ `p`, đi xuống chừng nào còn **giảm**

// Ví dụ:

// ```
// [1, 3, 5, 4, 2, 6, 8]
//         p   q→3→4 (dừng ở 2)
// ```

// ---

// ### ❌ Kiểm tra đoạn giảm có tồn tại không

// ```java
// if (q == p) {
//     return false;
// }
// ```

// * `q == p` → không hề giảm
// * Ví dụ: `[1, 2, 3, 4, 5]` ❌

// ---

// ### ❌ Không được kết thúc tại cuối mảng

// ```java
// if (q == n - 1) {
//     return false;
// }
// ```

// * Vì **còn phải có đoạn tăng thứ 2**
// * Ví dụ: `[1, 3, 5, 4, 2]` ❌

// ---

// ## 🔹 BƯỚC 3: Kiểm tra đoạn **tăng cuối**

// ```java
// for (int i = q; i < n - 1; i++) {
//     if (nums[i] >= nums[i + 1]) {
//         return false;
//     }
// }
// ```

// 👉 Từ `q` đến cuối:

// * Phải **tăng nghiêm ngặt**
// * Chỉ cần **1 chỗ sai** → ❌

// Ví dụ đúng:

// ```
// [2, 6, 8]  ✔
// ```

// Ví dụ sai:

// ```
// [2, 6, 6]  ✘
// ```

// ---

// ## ✅ Nếu qua hết → Trionic

// ```java
// return true;
// ```

// ---

// ## 🧠 Tóm tắt tư duy thuật toán

// | Giai đoạn | Điều kiện             |
// | --------- | --------------------- |
// | Tăng 1    | `nums[i] < nums[i+1]` |
// | Giảm      | `nums[i] > nums[i+1]` |
// | Tăng 2    | `nums[i] < nums[i+1]` |

// ✔ Thứ tự **bắt buộc**
// ✔ Mỗi đoạn **phải tồn tại**
// ✔ Dùng **so sánh nghiêm ngặt**

// ---

// ## ⏱️ Độ phức tạp

// * **Time:** `O(n)` (duyệt 1 lần)
// * **Space:** `O(1)`

// ---

// Nếu bạn muốn mình:

// * 🔍 **Trace từng dòng với test cụ thể**
// * ✂️ **Rút gọn code**
// * ⚠️ **Chỉ ra test case dễ WA**

// 👉 cứ gửi mảng test bạn đang thắc mắc nha 😄

// ---

// ## 🔹 Trionic Array I là gì?

// Một mảng được gọi là **Trionic** nếu nó có thể chia thành **3 đoạn liên tiếp**:

// 1️⃣ **Đoạn 1: tăng dần nghiêm ngặt**
// 2️⃣ **Đoạn 2: giảm dần nghiêm ngặt**
// 3️⃣ **Đoạn 3: tăng dần nghiêm ngặt**

// 👉 Ba đoạn này **không rỗng** (mỗi đoạn phải có ít nhất 1 phần tử)
// 👉 Thứ tự **bắt buộc**: **tăng → giảm → tăng**

// ---

// ## 🔹 Hiểu bằng hình dung

// Giá trị phần tử đi theo dạng:

// ```
//    /\
//   /  \
//  /    \
// ```

// * Leo lên (tăng)
// * Rồi đi xuống (giảm)
// * Rồi leo lên lại (tăng)

// ---

// ## 🔹 Ví dụ ĐÚNG

// ```text
// nums = [1, 3, 5, 4, 2, 6, 8]
// ```

// Chia thành:

// * `[1, 3, 5]` → tăng
// * `[5, 4, 2]` → giảm
// * `[2, 6, 8]` → tăng

// ✅ Đây là **Trionic Array**

// ---

// ## 🔹 Ví dụ SAI

// ### ❌ Không đủ 3 đoạn

// ```text
// [1, 2, 3, 4]
// ```

// → chỉ tăng, **không có giảm**

// ---

// ### ❌ Không tăng/giảm nghiêm ngặt

// ```text
// [1, 2, 2, 1, 3]
// ```

// → `2 → 2` không tăng nghiêm ngặt ❌

// ---

// ### ❌ Sai thứ tự

// ```text
// [5, 3, 1, 2, 4]
// ```

// → giảm → tăng (thiếu đoạn tăng đầu)

// ---

// ## 🔹 Nhiệm vụ của bài

// 👉 **Trả về `true` nếu mảng là Trionic**
// 👉 **Trả về `false` nếu không phải**

// ---

// ## 🔹 Điều kiện quan trọng (hay bị bẫy)

// * So sánh dùng **`>` và `<`**, không dùng `>=` hoặc `<=`
// * Mỗi đoạn phải **có ít nhất 1 bước chuyển**
// * Không được đổi thứ tự 3 đoạn

// ---

// ## 🔹 Tư duy khi làm (chưa cần code)

// 1. Duyệt từ trái sang phải
// 2. Đi lên cho đến khi không tăng nữa
// 3. Sau đó đi xuống cho đến khi không giảm nữa
// 4. Cuối cùng phải đi lên lại
// 5. Nếu duyệt hết mảng đúng thứ tự → `true`

// ---

// Nếu bạn muốn:

// * ✅ **Giải bằng Java từng dòng**
// * ✅ **So sánh với Mountain Array**
// * ✅ **Giải thích test case tricky hay bị sai**

// 👉 nói mình biết bạn đang cần **hiểu đề hay viết code** nhé 😄
