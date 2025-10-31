
// # 3289. The Two Sneaky Numbers of Digitville(31/10/2025)
import java.util.*;

public class b84 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] result = getSneakyNumbers(nums);

        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] getSneakyNumbers(int[] nums) {
        int[] result = new int[2]; // Mảng lưu hai số xuất hiện hai lần
        int count = 0; // Biến đếm số lượng phần tử đã thêm vào result

        // Duyệt từng phần tử trong mảng
        for (int i = 0; i < nums.length; i++) {
            // So sánh phần tử hiện tại với các phần tử đứng sau nó
            for (int j = i + 1; j < nums.length; j++) {
                // Nếu hai phần tử bằng nhau
                if (nums[i] == nums[j]) {
                    result[count] = nums[i]; // Lưu vào mảng kết quả
                    count++; // Tăng biến đếm
                    break; // Thoát vòng lặp trong để tránh lưu trùng nhiều lần
                }
            }
        }

        return result; // Trả về mảng gồm hai số xuất hiện hai lần
    }
}

// # Dưới đây là **giải thích** cho bài 3289. The Two Sneaky Numbers of
// Digitville theo cách dễ hiểu:
// from typing import List
// from collections import Counter

// class Solution:
// def getSneakyNumbers(self, nums: List[int]) -> List[int]:
// # Đếm số lần xuất hiện của từng phần tử trong nums
// count = Counter(nums)

// # Tạo danh sách chứa các số xuất hiện 2 lần
// result = []
// for num, freq in count.items():
// if freq == 2: # Nếu số đó xuất hiện đúng 2 lần
// result.append(num)

// return result # Trả về danh sách gồm 2 số "nghịch ngợm"

// # Tất nhiên 👍
// # Mình sẽ **giải thích thuật toán của bài 3289 – The Two Sneaky Numbers of
// Digitville** thật rõ ràng cho bạn nhé.

// # ---

// # ## 🧩 Đề bài tóm tắt

// # Ta có một danh sách các số nguyên `nums` trong đó:

// # * Có **n+2** phần tử (trong khi bình thường chỉ có n số khác nhau).
// # * Hai số trong đó **xuất hiện đúng 2 lần** (gọi là “sneaky numbers”).
// # * Các số còn lại **xuất hiện đúng 1 lần**.

// # 👉 Nhiệm vụ: **Tìm hai số xuất hiện 2 lần.**

// # ---

// # ## 💡 Ý tưởng chính

// # Ta cần **đếm số lần xuất hiện của từng số** trong danh sách.
// # Sau đó, chỉ cần **chọn ra các số xuất hiện đúng 2 lần**.

// # ---

// ## 🔍 Cách làm chi tiết

// ### Bước 1: Đếm tần suất của từng số

// # * Sử dụng `Counter(nums)` từ thư viện `collections`.
// # * `Counter` sẽ trả về một từ điển (dictionary) có dạng:

// # ```python
// # Counter({số: số_lần_xuất_hiện})
// # ```

// # Ví dụ:

// # ```python
// # nums = [0, 1, 2, 1, 3, 2]
// # count = Counter(nums)
// # # count = {0: 1, 1: 2, 2: 2, 3: 1}
// # ```

// # ### Bước 2: Chọn ra các số xuất hiện 2 lần

// # * Duyệt qua từng cặp `(num, freq)` trong `count.items()`.
// # * Nếu `freq == 2` → thêm `num` vào danh sách kết quả.

// # ### Bước 3: Trả về kết quả

// # * Kết quả sẽ gồm **2 số** là hai “sneaky numbers”.

// ## 📘 Ví dụ minh họa

// ### Input:

// # ```python
// # nums = [0, 3, 2, 1, 3, 2]
// # ```

// # ### Bước 1: Đếm

// # ```
// # Counter({3: 2, 2: 2, 0: 1, 1: 1})
// # ```

// # ### Bước 2: Lọc ra các số có `freq == 2`

// # → `[3, 2]`

// ### Output:

// # [3, 2]
// # ```

// # ---

// # ## ⏱️ Độ phức tạp

// # * **Thời gian:** `O(n)` → vì ta duyệt qua mảng 1 lần.
// # * **Không gian:** `O(n)` → do cần lưu số lần xuất hiện trong `Counter`.

// # ---

// # Tóm lại:

// # > Thuật toán này chỉ đơn giản là **đếm tần suất xuất hiện của mỗi số** rồi
// **trả lại những số xuất hiện 2 lần** — nhanh, gọn và hiệu quả 💪

// # ---

// # Bạn có muốn mình vẽ **minh họa từng bước (dạng bảng hoặc sơ đồ)** cho ví dụ
// `[0,3,2,1,3,2]` không?
// # Nó sẽ giúp bạn thấy rõ cách thuật toán hoạt động từng vòng lặp.

// # ---

// # ## 📘 Đề bài

// # * Cho một mảng `nums` chứa các số nguyên từ `0` tới `n-1`. Mỗi số **người
// ta mong đợi xuất hiện đúng 1 lần**.
// # * Tuy nhiên, **có đúng hai số “nghịch ngợm”** đã xuất hiện **thêm một lần
// nữa**, nên mảng có độ dài `n + 2`.
// # * Nhiệm vụ: tìm **hai số đó** (theo bất kỳ thứ tự nào) và trả về một mảng
// chứa hai số đó. ([leetcode.com][1])
// # * Ví dụ:

// # * `nums = [0,1,1,0]` → output `[0,1]` vì 0 và 1 đều xuất hiện **hai lần**.
// ([Leetcode][2])
// # * `nums = [0,3,2,1,3,2]` → output `[2,3]` vì 2 và 3 xuất hiện hai lần.
// ([Leetcode][2])

// # ---

// # ## 🧠 Ý tưởng giải

// # * Vì chỉ có **hai số xuất hiện thêm lần nữa**, nên ta chỉ cần tìm những số
// có **tần suất = 2** trong `nums`.
// # * Cách làm:

// # 1. Duyệt `nums`, đếm số lần xuất hiện của mỗi số (`cnt[num]++`).
// # 2. Mỗi khi `cnt[num] == 2`, thêm `num` vào kết quả.
// # 3. Sau khi duyệt xong, kết quả sẽ chứa đúng hai số mong muốn.

// # ---

// # ## 📊 Phân tích độ phức tạp

// # * Time complexity: O(n) (duyệt mảng một lần) ([walkccc.me][3])
// # * Space complexity: O(n) hoặc O(maxValueRange) — trong giới hạn nhỏ nên coi
// là O(1) nếu range cố định.

// # ---

// # Nếu bạn muốn, mình có thể viết **mã Python hoặc Java** để giải bài này với
// chú thích từng dòng. Bạn có muốn không?

// Rất hay 👍 — bạn đã tự viết một cách làm **đơn giản, dễ hiểu** cho bài *“The
// Two Sneaky Numbers of Digitville”*.
// Giờ mình sẽ **giải thích chi tiết từng dòng** để bạn hiểu rõ thuật toán nhé
// 👇

// ---

// ### 🔍 **Mục tiêu đề bài:**

// Tìm **hai số** trong mảng `nums` **xuất hiện đúng hai lần**.
// Ví dụ:

// ```
// nums = [0, 3, 2, 1, 3, 2]
// → output = [3, 2]
// ```

// ---

// ### 🧠 **Thuật toán trong code của bạn:**

// ```java
// int[] result = new int[2];
// int count = 0;
// ```

// 👉 Tạo một mảng `result` kích thước 2 để lưu **hai số bị trùng**.
// `count` dùng để theo dõi số lượng phần tử đã thêm vào `result`.

// ---

// ```java
// for (int i = 0; i < nums.length; i++) {
// for (int j = i + 1; j < nums.length; j++) {
// ```

// 👉 Duyệt **từng cặp phần tử (nums[i], nums[j])** trong mảng.

// * Vòng lặp ngoài: chọn 1 phần tử `nums[i]`.
// // * Vòng lặp trong: so sánh `nums[i]` với các phần tử **đứng sau nó** (`j =
// i + 1` đến hết mảng).

// ---

// ```java
// if (nums[i] == nums[j]) {
// result[count] = nums[i];
// count++;
// break;
// }
// ```

// 👉 Nếu tìm thấy **2 phần tử bằng nhau**:

// * Lưu `nums[i]` (hoặc `nums[j]`, vì bằng nhau) vào `result`.
// * Tăng `count` lên 1 (vì đã tìm được 1 “sneaky number”).
// * `break` giúp **thoát khỏi vòng lặp trong**, để tránh lưu trùng cùng 1 số
// nhiều lần.

// Ví dụ:

// ```
// nums = [0, 3, 2, 1, 3, 2]
// ```

// * i=0 → không trùng ai.
// * i=1 (3) → trùng với j=4 (3) → lưu result[0] = 3.
// * i=2 (2) → trùng với j=5 (2) → lưu result[1] = 2.
// → xong.

// ```java
// return result;
// ```

// 👉 Trả về mảng gồm **hai số trùng nhau** `[3, 2]`.

// ---

// ### ⏱️ **Độ phức tạp:**

// * **Thời gian:** `O(n²)` vì có hai vòng lặp lồng nhau.
// * **Không gian:** `O(1)` (chỉ dùng vài biến phụ và mảng `result` kích thước
// 2).

// ---

// ### ✅ **Tóm tắt logic:**

// 1. Duyệt từng phần tử trong mảng.
// 2. Với mỗi phần tử, so sánh với các phần tử sau nó.
// 3. Nếu trùng, thêm vào `result` và tiếp tục tìm số khác.
// 4. Trả về 2 số xuất hiện hai lần.

// ---

// ### 📘 **Ví dụ chạy:**

// ```
// nums = [1, 2, 3, 4, 2, 3]

// 👉 Kết quả:

// | i | j | nums[i] | nums[j] | Kết quả |
// | - | --- | ------- | ------- | --------------- |
// | 0 | 1–5 | 1 | — | không trùng |
// | 1 | 2–5 | 2 | 2 | ✅ result[0] = 2 |
// | 2 | 3–5 | 3 | 3 | ✅ result[1] = 3 |

// **Output:** `[2, 3]`

// ---

// Nếu bạn muốn, mình có thể giúp **tối ưu code xuống O(n)** bằng cách dùng
// **HashSet** hoặc **mảng đếm**, nhanh hơn rất nhiều.
// 👉 Bạn có muốn xem phiên bản tối ưu đó không?
