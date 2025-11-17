
// 1437. Check If All 1's Are at Least Length K Places Away(17/11/2025)
import java.util.*;

public class b101 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        boolean result = kLengthApart(nums, k);
        System.out.println(result);

        sc.close();
    }

    public static boolean kLengthApart(int[] nums, int k) {
        int c = k; // đếm số lượng số 0 sau số 1 trước đó — bắt đầu bằng k để số 1 đầu tiên luôn
                   // hợp lệ
        int m = 0; // m = 0: chưa gặp số 1 nào; m = 1: đã gặp số 1

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) { // nếu gặp số 1
                if (c < k)
                    return false; // khoảng cách c < k → không đủ → sai
                m = 1; // đánh dấu rằng đã gặp số 1
                c = 0; // reset bộ đếm số 0
            }

            else { // nếu là số 0
                if (m == 1)
                    c++; // chỉ đếm số 0 sau khi đã gặp số 1
            }
        }

        return true; // duyệt hết không vi phạm → đúng
    }

}

// Dưới đây là **giải thích chi tiết – dễ hiểu** cho thuật toán bạn dùng trong
// hàm:

// ```java
// public static boolean kLengthApart(int[] nums, int k)
// ```

// ---

// # ✅ **Ý tưởng của thuật toán**

// Thuật toán dựa vào việc **đếm khoảng cách giữa hai số 1 liên tiếp**.

// Bạn dùng 2 biến:

// ### 🔹 `c` – đếm khoảng cách (số lượng số 0 xuất hiện sau 1)

// * Khi gặp `1` mới → kiểm tra xem `c` có ≥ `k` hay không
// * Nếu không đạt → return false
// * Sau đó reset `c = 0`

// ### 🔹 `m` – đánh dấu xem đã từng gặp số 1 chưa

// * `m = 0`: chưa gặp 1
// * `m = 1`: đã gặp 1 → bắt đầu đếm khoảng cách

// ---

// # 🧠 **Giải thích từng dòng thuật toán**

// ### Ban đầu:

// ```java
// int c = k, m = 0;
// ```

// * `c = k`: để đảm bảo rằng **số 1 đầu tiên** không gây lỗi (coi như trước đó
// có đủ khoảng cách)
// * `m = 0`: chưa gặp 1 nào

// ---

// ### Duyệt mảng:

// ```java
// for (int i = 0; i < nums.length; i++)
// ```

// ---

// ## Trường hợp 1️⃣: Gặp số **1**

// ```java
// if(nums[i] == 1)
// {
// if(c < k) return false; // khoảng cách không đủ
// m = 1; // đã bắt đầu gặp 1
// c = 0; // reset bộ đếm
// }
// ```

// Giải thích:

// * Nếu `c < k` → nghĩa là số 1 này quá gần số 1 trước → sai
// * Set `m = 1` → để sau đó biết rằng mình sẽ bắt đầu đếm khoảng cách
// * Reset `c = 0` để bắt đầu tính lại số 0 tiếp theo

// ---

// ## Trường hợp 2️⃣: Gặp số **0**

// ```java
// else
// {
// if(m == 1) c++;
// }
// ```

// * Chỉ đếm số 0 **sau khi đã gặp 1 đầu tiên**
// * Nếu chưa gặp số 1 (`m = 0`) thì không cần đếm

// ---

// ### Nếu duyệt xong mà không phát hiện vi phạm:

// ```java
// return true;
// ```

// ---

// # 📌 **Ví dụ minh họa**

// ### Input:

// ```
// nums = [1,0,0,1], k = 2
// ```

// Duyệt:

// | i | nums[i] | hành động | c | m |
// | - | ------- | ---------------------- | - | - |
// | 0 | 1 | c (=2) ≥ k → OK, reset | 0 | 1 |
// | 1 | 0 | m = 1 → c++ | 1 | 1 |
// | 2 | 0 | c++ | 2 | 1 |
// | 3 | 1 | c (=2) ≥ 2 → OK, reset | 0 | 1 |

// → **true**

// ---

// ### Input:

// ```
// nums = [1,0,1], k = 2
// ```

// Duyệt:

// | i | nums[i] | hành động | c | m |
// | - | ------- | ------------------------- | - | - |
// | 0 | 1 | OK | 0 | 1 |
// | 1 | 0 | c++ | 1 | 1 |
// | 2 | 1 | c (=1) < 2 → return false | | |

// → **false**

// ---

// # ✅ **Kết luận**

// Thuật toán:

// * Duyệt một lần → O(n)
// * Dùng 2 biến để theo dõi khoảng cách và trạng thái
// * Rất tối ưu và đúng ý đề bài

// Bạn cần mình **thêm chú thích vào code Java** không?

// # ✅ **📘 Đề bài yêu cầu gì?**

// Bạn được cho:

// * Một **mảng nhị phân `nums`** chỉ gồm `0` và `1`
// * Một số nguyên `k`

// Bạn phải kiểm tra xem:

// 👉 **Mọi cặp số `1` trong mảng có cách nhau ít nhất `k` vị trí hay không?**

// Nói cách khác:

// > Với mọi cặp chỉ số i < j sao cho nums[i] = nums[j] = 1
// > ⇒ phải thỏa mãn: **j - i - 1 ≥ k**

// Nếu đúng → trả về `true`
// Nếu sai → trả về `false`

// ---

// # 🔍 **💡 Hiểu theo cách dễ nhất**

// Giả sử `k = 2`. Điều kiện:

// ```
// 1 _ _ 1 ✔ hợp lệ (cách nhau 2 zeros)
// 1 _ 1 ✘ không hợp lệ (chỉ có 1 zero)
// 11 ✘ không hợp lệ (không có zero)
// ```

// Bạn phải kiểm tra khoảng cách giữa **tất cả các số 1 liên tiếp**.

// ---

// # 📌 **Ví dụ minh họa**

// ### Ví dụ 1:

// ```
// nums = [1,0,0,0,1,0,0,1], k = 2
// ```

// Các khoảng cách:

// * Giữa 1 ở vị trí 0 và 4 → cách 3 chữ số → ✔ ≥ 2
// * Giữa 1 ở vị trí 4 và 7 → cách 2 chữ số → ✔ ≥ 2

// → **Trả về true**

// ---

// ### Ví dụ 2:

// ```
// nums = [1,0,1], k = 2
// ```

// Khoảng cách:

// * Giữa 1 ở vị trí 0 và 2 → cách **1** chữ số → ✘ < 2

// → **Trả về false**

// ---

// # 🧠 **Tư duy lời giải**

// Duyệt qua mảng, ghi lại vị trí của số 1 trước đó (`prev`).
// Khi gặp 1 mới:

// * Nếu `(current_index - prev - 1) < k` → sai → trả về `false`
// * Cập nhật `prev = current_index`

// Nếu duyệt xong không vi phạm gì → `true`.

// ---

// Nếu bạn cần **code Python/Java + chú thích** thì mình viết ngay cho bạn!
