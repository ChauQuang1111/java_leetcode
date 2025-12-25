
// // # 3075. Maximize Happiness of Selected Children(25/12/2025)
// // # **Giải thích đề bài – LeetCode 3075: Maximize Happiness of Selected Children**
import java.util.*;

public class b128 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] happiness = new int[n];

        // Nhập mảng happiness
        for (int i = 0; i < n; i++) {
            happiness[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        long result = maximumHappinessSum(happiness, k);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    /**
     * Hàm chính giải bài toán:
     * Chọn k đứa trẻ sao cho tổng hạnh phúc sau khi trừ dần là lớn nhất
     */
    public static long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;

        // Dùng quickselect để đảm bảo:
        // k phần tử lớn nhất nằm ở đoạn [n-k ... n-1]
        quickselect(happiness, 0, n - 1, n - k);

        long ans = 0L;

        /*
         * Nếu phần tử nhỏ nhất trong top k < (k - 1)
         * => sẽ có đứa trẻ bị trừ về <= 0
         * => cần xử lý cẩn thận, có thể dừng sớm
         */
        if (happiness[n - k] < k - 1) {

            // Sort lại các phần tử lớn nhất để chọn từ lớn đến nhỏ
            Arrays.sort(happiness, n - k + 1, n);

            for (int i = 0; i < k; ++i) {
                // Nếu hạnh phúc <= số lần bị trừ
                // => giá trị thực tế <= 0, không nên chọn nữa
                if (happiness[n - 1 - i] <= i) {
                    // Trừ tổng penalty đã áp dụng cho i đứa trước đó
                    return ans - i * (i - 1L) / 2L;
                }
                ans += happiness[n - 1 - i];
            }
        }

        /*
         * Trường hợp tất cả k đứa đều còn giá trị dương
         * => cộng trực tiếp k phần tử lớn nhất
         */
        for (int i = n - k; i < n; ++i) {
            ans += happiness[i];
        }

        // Trừ tổng penalty: 0 + 1 + ... + (k - 1)
        return ans - k * (k - 1L) / 2L;
    }

    /**
     * Quickselect:
     * Đưa phần tử thứ k (theo thứ tự tăng) về đúng vị trí
     * Các phần tử bên phải >= nó
     * Các phần tử bên trái <= nó
     */
    public static void quickselect(int[] nums, int l, int r, int k) {
        // Chọn pivot là phần tử giữa
        int pivot = nums[(l + r) >>> 1];
        int left = l, right = r;

        // Partition mảng quanh pivot
        while (left <= right) {
            if (nums[left] < pivot) {
                left++;
            } else if (nums[right] > pivot) {
                right--;
            } else {
                int temp = nums[left];
                nums[left++] = nums[right];
                nums[right--] = temp;
            }
        }

        // Chỉ đệ quy về phía chứa vị trí k
        if (right >= k) {
            quickselect(nums, l, right, k);
        } else if (left <= k) {
            quickselect(nums, left, r, k);
        }
    }

}

// // from typing import List

// // class Solution:
// // def maximumHappinessSum(self, happiness: List[int], k: int) -> int:
// // # Sắp xếp mức độ hạnh phúc theo thứ tự giảm dần
// // # để luôn chọn những đứa trẻ hạnh phúc nhất trước
// // happiness.sort(reverse=True)

// // # Biến lưu tổng hạnh phúc tối đa
// // res = 0

// // # Chọn lần lượt k đứa trẻ
// // for i in range(k):
// // # Đứa trẻ thứ i sẽ bị giảm i đơn vị hạnh phúc
// // # (đứa đầu tiên giảm 0, đứa thứ hai giảm 1, ...)
// // gain = happiness[i] - i

// // # Nếu hạnh phúc sau khi giảm <= 0
// // # thì chọn tiếp sẽ không mang lại lợi ích
// // # (các đứa sau chắc chắn còn nhỏ hơn)
// // if gain <= 0:
// // return res

// // # Cộng hạnh phúc hợp lệ vào tổng
// // res += gain

// // # Trả về tổng hạnh phúc lớn nhất có thể đạt được
// // return res

// // # ---

// // # ### Nội dung bài toán (hiểu đơn giản)

// // # * Bạn có một mảng số nguyên **`happiness`**
// // # → mỗi phần tử biểu thị **mức độ hạnh phúc ban đầu của một đứa trẻ**.
// // # * Bạn được phép **chọn đúng `k` đứa trẻ** để tham gia một hoạt động.
// // # * Khi chọn:

// // # * Đứa trẻ **đầu tiên** giữ nguyên mức hạnh phúc.
// // # * Đứa trẻ **thứ hai** bị giảm **1** đơn vị hạnh phúc.
// // # * Đứa trẻ **thứ ba** bị giảm **2** đơn vị hạnh phúc.
// // # * ...
// // # * Đứa trẻ **thứ i** bị giảm **(i − 1)** đơn vị hạnh phúc.
// // # * Nếu sau khi giảm mà giá trị âm → **tính là 0**.
// // # * Mục tiêu: **chọn k đứa trẻ sao cho tổng hạnh phúc cuối cùng là lớn
// nhất**.

// // # ---

// // # ### Ví dụ minh họa

// // # ```text
// // # happiness = [1, 2, 3]
// // # k = 2
// // # ```

// // # **Cách chọn tốt nhất**:

// // # * Chọn trẻ có happiness = 3 (không bị giảm) → 3
// // # * Chọn trẻ có happiness = 2 (bị giảm 1) → 1

// // # 👉 Tổng = 3 + 1 = **4**

// // # ---

// // # ### Ý nghĩa cốt lõi của bài toán

// // # * Mỗi lần chọn thêm một đứa trẻ, **mức phạt tăng lên**
// // # * Vì vậy:

// // # * Ta **nên chọn những đứa có happiness lớn trước**
// // # * Tránh chọn đứa có happiness nhỏ khi mức phạt đã lớn

// // # ---

// // # ### Tư duy chính (chưa cần code)

// // # 1. **Sắp xếp mảng `happiness` giảm dần**
// // # 2. Chọn lần lượt `k` phần tử lớn nhất
// // # 3. Với phần tử thứ `i` được chọn:

// // # * Giá trị đóng góp = `max(happiness[i] − i, 0)`
// // # 4. Cộng tất cả lại → kết quả

// // # ---

// // # ### Tóm tắt ngắn gọn

// // # * Bài toán = **tham lam (greedy)**
// // # * Chọn trẻ **hạnh phúc nhất trước**
// // # * Mỗi lần chọn sau bị giảm nhiều hơn
// // # * Không cho giá trị âm

// // # ---

// // # Nếu bạn muốn:

// // # * ✅ Giải thích bằng **ví dụ lớn hơn**
// // # * ✅ Viết **code Java / C++ / Python**
// // # * ✅ Phân tích **độ phức tạp**
// // # * ✅ So sánh với bài greedy tương tự

// // # 👉 Cứ nói nhé 👍
// // # Mình giải thích **đúng theo đoạn code của bạn** nhé 👇
// // # (thuật toán **tham lam – Greedy**, rất chuẩn 👍)

// // # ---

// // # ## Ý tưởng chính của code

// // # 👉 **Chọn những đứa trẻ có happiness lớn nhất trước**, vì:

// // # * Mỗi lần chọn thêm 1 đứa thì bị trừ thêm `i`
// // # * Nếu chọn đứa có happiness nhỏ ở lượt sau → dễ bị về 0 hoặc âm

// // # ---

// // # ## Giải thích từng bước trong code

// // # ```python
// // # happiness.sort(reverse = True)
// // # ```

// // # * Sắp xếp mảng `happiness` **giảm dần**
// // # * Đứa hạnh phúc nhất sẽ được chọn đầu tiên (không bị trừ)

// // # ---

// // # ```python
// // # res = 0
// // # ```

// // # * `res` dùng để lưu **tổng hạnh phúc tối đa**

// // # ---

// // # ```python
// // # for i in range(k):
// // # ```

// // # * Chọn lần lượt **k đứa trẻ**
// // # * `i` chính là **số lần đã chọn trước đó**
// // # * Theo đề bài: đứa thứ `i` sẽ bị trừ `i`

// // # ---

// // # ```python
// // # gain = happiness[i] - i
// // # ```

// // # * `gain` = mức hạnh phúc thực tế của đứa trẻ thứ `i`
// // # * Vì:

// // # * Đứa đầu tiên: trừ `0`
// // # * Đứa thứ hai: trừ `1`
// // # * Đứa thứ ba: trừ `2`
// // # * ...

// // # ---

// // # ```python
// // # if gain <= 0:
// // # return res
// // # ```

// // # * Nếu hạnh phúc ≤ 0:

// // # * Chọn thêm **không có lợi**
// // # * Vì các phần tử sau **chắc chắn còn nhỏ hơn**
// // # * 👉 Dừng sớm để tối ưu

// // # ---

// // # ```python
// // # res += gain
// // # ```

// // # * Cộng hạnh phúc hợp lệ vào tổng

// // # ---

// // # ```python
// // # return res
// // # ```

// // # * Trả về **tổng hạnh phúc lớn nhất**

// // # ---

// // # ## Ví dụ chạy code

// // # ```python
// // # happiness = [5, 3, 1]
// // # k = 3
// // # ```

// // # Sau khi sort:

// // # ```text
// // # [5, 3, 1]
// // # ```

// // # | i | gain = happiness[i] - i |
// // # | - | ----------------------- |
// // # | 0 | 5 - 0 = 5 |
// // # | 1 | 3 - 1 = 2 |
// // # | 2 | 1 - 2 = -1 ❌ |

// // # → Dừng tại i = 2
// // # → Tổng = **5 + 2 = 7**

// // # ---

// // # ## Độ phức tạp

// // # * **Sort**: `O(n log n)`
// // # * **Loop k phần tử**: `O(k)`
// // # * **Tổng**: `O(n log n)`
// // # ✔️ Đáp ứng tốt yêu cầu LeetCode

// // # ---

// // # ## Kết luận

// // # * Code của bạn:

// // # * ✅ Đúng
// // # * ✅ Gọn
// // # * ✅ Tối ưu (dừng sớm)
// // # * Comment `# 妙哉` là **rất chuẩn** 😄 (Greedy đẹp)

// // # Nếu bạn muốn mình:

// // # * Giải thích bằng **hình minh họa**
// // # * Viết lại bằng **Java / C++**
// // # * Chứng minh vì sao greedy luôn đúng

// // # 👉 Cứ nói nhé 💪
// Mình giải thích **thuật toán của đoạn Java này theo từng ý lớn → rồi đi vào
// chi tiết**, vì đây là lời giải **tối ưu hơn sort toàn bộ** 👇

// ---

// ## Ý tưởng tổng quát của thuật toán

// Bài toán gốc:

// * Chọn **k đứa trẻ có happiness lớn nhất**
// * Tổng hạnh phúc cuối cùng =
// [
// \sum selected_happiness - (0 + 1 + 2 + \dots + (k-1))
// ]

// Thông thường:

// * Sort giảm dần → O(n log n)

// 👉 **Code này dùng Quickselect** để:

// * **Chỉ tìm ra k phần tử lớn nhất**
// * Thời gian trung bình **O(n)**

// ---

// ## Bước 1: Dùng Quickselect

// ```java
// quickselect(happiness, 0, n - 1, n - k);
// ```

// ### Mục tiêu của dòng này

// Sau khi chạy:

// * `happiness[n-k ... n-1]` là **k phần tử lớn nhất**
// * Thứ tự **chưa cần đúng**

// 📌 Giống như:

// > “Đặt đúng ranh giới, bên phải là top k”

// ---

// ## Bước 2: Ý nghĩa công thức cuối cùng

// Nếu chọn k đứa trẻ (sắp theo thứ tự chọn):

// * Đứa 1 trừ 0
// * Đứa 2 trừ 1
// * …
// * Đứa k trừ (k − 1)

// 👉 Tổng bị trừ:

// ```java
// k * (k - 1) / 2
// ```

// ---

// ## Trường hợp 1 (đặc biệt): Có thể bị về 0 sớm

// ```java
// if (happiness[n - k] < k - 1)
// ```

// ### Ý nghĩa

// * Phần tử nhỏ nhất trong top k
// * Nếu nó **< (k − 1)**
// → chắc chắn có đứa bị trừ về **≤ 0**

// ➡️ Cần xử lý cẩn thận

// ---

// ### Xử lý trong trường hợp này

// ```java
// Arrays.sort(happiness, n - k + 1, n);
// ```

// * Sort **chỉ phần lớn nhất**
// * Để xét lần lượt từ lớn → nhỏ

// ```java
// for (int i = 0; i < k; ++i) {
// if (happiness[n - 1 - i] <= i)
// return ans - i * (i - 1L) / 2L;
// ans += happiness[n - 1 - i];
// }
// ```

// ### Logic:

// * Duyệt từ đứa hạnh phúc nhất
// * Nếu:

// ```java
// happiness <= i
// ```

// → bị trừ về 0 → **dừng ngay**
// * Trả về:

// * Tổng đã cộng
// * Trừ đi tổng penalty của các đứa đã chọn

// 👉 **Early stopping (tối ưu)**

// ---

// ## Trường hợp 2: Không có ai bị về 0

// ```java
// for (int i = n - k; i < n; ++i)
// ans += happiness[i];
// ```

// * Cộng toàn bộ k phần tử lớn nhất

// ```java
// return ans - k * (k - 1L) / 2L;
// ```

// * Trừ tổng penalty chuẩn

// ---

// ## Hàm `quickselect`

// ```java
// private void quickselect(int[] nums, int l, int r, int k)
// ```

// ### Chức năng

// * Đưa phần tử thứ `k` (theo thứ tự tăng) về đúng vị trí
// * Bên phải ≥ nó
// * Bên trái ≤ nó

// ⏱ Trung bình: **O(n)**

// ### Cách hoạt động

// * Chọn pivot = phần tử giữa
// * Partition mảng
// * Đệ quy **chỉ 1 phía chứa k**

// ---

// ## Tổng kết thuật toán

// | Thành phần | Ý nghĩa |
// | ------------------ | ------------------------- |
// | Quickselect | Tìm top k nhanh |
// | Không sort toàn bộ | Giảm từ O(n log n) → O(n) |
// | Early stop | Tránh cộng số âm |
// | Công thức toán học | Trừ penalty gọn |

// ---

// ## Độ phức tạp

// * **Trung bình:** `O(n)`
// * **Xấu nhất (hiếm):** `O(n²)`
// * **Bộ nhớ:** `O(1)`

// ---

// ### Kết luận

// Đây là lời giải:

// * ⭐ Rất tối ưu
// * ⭐ Phù hợp n lớn
// * ⭐ Dùng tư duy thuật toán nâng cao (Quickselect + Math)

// Nếu bạn muốn:

// * So sánh với **solution sort thường**
// * Vẽ **timeline minh họa**
// * Viết lại theo kiểu **dễ hiểu cho phỏng vấn**

// 👉 Cứ nói nhé 👍
