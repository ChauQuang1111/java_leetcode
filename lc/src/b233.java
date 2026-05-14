// #Bài**“Check if Array is Good(14/05/2026)

import java.util.*;

public class b233 {

    // Scanner dùng để nhập dữ liệu
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Nhập số lượng phần tử
        int size = sc.nextInt();

        // Tạo mảng
        int[] nums = new int[size];

        // Nhập các phần tử
        int i = 0;

        while (i < size) {
            nums[i] = sc.nextInt();
            i++;
        }

        // Gọi hàm kiểm tra
        boolean result = isGood(nums);

        // In kết quả
        if (result) {
            System.out.println("Good Array");
        } else {
            System.out.println("Not Good Array");
        }

        sc.close();
    }

    public static boolean isGood(int[] nums) {

        // Lưu số lớn nhất trong mảng
        int n = 0;

        // Tìm phần tử lớn nhất
        for (int num : nums) {
            n = Math.max(n, num);
        }

        // Kiểm tra độ dài
        if (nums.length != n + 1) {
            return false;
        }

        // Mảng đếm tần suất
        int[] freq = new int[n + 1];

        // Đếm số lần xuất hiện
        for (int num : nums) {

            // Nếu num vượt quá n
            if (num > n) {
                return false;
            }

            freq[num]++;
        }

        // Kiểm tra các số từ 1 -> n-1
        for (int i = 1; i < n; i++) {

            // Phải xuất hiện đúng 1 lần
            if (freq[i] != 1) {
                return false;
            }
        }

        // Số lớn nhất phải xuất hiện 2 lần
        return freq[n] == 2;
    }
}
// Ý tưởng
// thuật toán

// Mảng good
// phải có dạng:

// ```text id="o7k0l6"
// [1,2,3,...,n-1,n-1]
// ```

// Ví dụ:

// ```text id="1qq1hb"
// [1,2,3,3]
// ```

// ##
// Thuật toán
// hoạt động
// như sau

// ###Bước 1—
// Tìm số
// lớn nhất

// ```java id="7nhvhv"n=

// max(nums)
// ```

// Ví dụ:

// ```text id="4tzhne"
// [1,2,3,3]
// => n = 3
// ```

// ---

// ### Bước 2 — Kiểm tra độ dài

// Mảng good phải có:

// ```text id="kwujzr"
// length = n + 1
// ```

// vì:

// * các số từ `1 -> n-1` xuất hiện 1 lần
// * số `n` xuất hiện 2 lần

// Tổng cộng:

// ```text id="yk7h4u"
// (n - 1) + 2 = n + 1
// ```

// // ---

// // ### Bước 3 — Đếm số lần xuất hiện

// // Dùng mảng `freq`.

// // Ví dụ:

// // ```text id="oqvh6o"
// // nums = [1,2,3,3]
// ```

// Sau khi đếm:

// ```text id="r8ubf8"
// freq[1] = 1
// freq[2] = 1
// freq[3] = 2
// ```

// ---

// ### Bước 4 — Kiểm tra điều kiện

// * Các số `1 -> n-1` phải xuất hiện đúng 1 lần
// * Số `n` phải xuất hiện đúng 2 lần

// Nếu tất cả đều đúng ⇒ good array.

// #```python from typing

// import List

// class Solution:
// def isGood(self, nums: List[int]) -> bool:

// # Sắp xếp mảng tăng dần
// nums.sort()

// # Lấy phần tử lớn nhất trong mảng
// # Vì sau khi sort, phần tử cuối cùng là lớn nhất
// max_ele = nums[-1]

// # Kiểm tra điều kiện của mảng good
// #
// # Điều kiện 1:
// # độ dài mảng phải = max_ele + 1
// #
// # Ví dụ:
// # [1,2,3,3]
// # max = 3
// # length = 4
// # => đúng vì 4 = 3 + 1
// #
// # Điều kiện 2:
// # số lớn nhất phải xuất hiện 2 lần
// # nên 2 phần tử cuối phải bằng nhau
// #
// # nums[-1] : phần tử cuối
// # nums[-1 - 1] : phần tử kế cuối
// #
// if len(nums) != max_ele + 1 or nums[-1] != nums[-2]:
// return False

// # Kiểm tra các số từ 1 -> max_ele - 1
// #
// # Ví dụ:
// # [1,2,3,3]
// #
// # cần kiểm tra:
// # nums[0] = 1
// # nums[1] = 2
// #
// # vì số

// lớn nhất (3) đã được kiểm tra ở trên
// #
// for i in range(max_ele - 1):

// # Sau khi sort:
// # vị trí i phải chứa số i + 1
// #
// # i = 0 -> cần số 1
// # i = 1 -> cần số 2
// # ...
// #
// if nums[i] != i + 1:
// return False

// # Nếu vượt qua tất cả kiểm tra
// # => đây là mảng good
// return True
// # ```

// # ## Ý tưởng thuật toán

// # Sau khi sort, mảng good luôn có dạng:

// # ```text id="x0s3nm"
// # [1,2,3,...,n-1,n-1]
// # ```

// # Ví dụ:

// # ```text id="5l1sww"
// # [1,2,3,3]
// # [1,2,2]
// # [1,1]
// # ```

// # Thuật toán kiểm tra:

// # 1. Phần tử lớn nhất có xuất hiện 2 lần không
// # 2. Các số trước đó có đúng thứ tự `1 -> max-1` không

// # Nếu sai bất kỳ chỗ nào ⇒ không phải good array.

// # ## Ý tưởng của đề

// # Cho một mảng `nums`.

// # Mảng được gọi là **good** nếu:

// # * Nó chứa tất cả các số từ `1` đến `n - 1` đúng **1 lần**
// # * Và số `n - 1` xuất hiện đúng **2 lần**

// # Trong đó:

// # * `n = nums.length`

// # ---

// # ## Ví dụ

// # ### Ví dụ 1

// # ```text
// # nums = [1,2,3,3]
// # ```

// # * Độ dài mảng = 4
// # * Vậy cần có:

// # ```text
// # 1,2,3
// # ```

// # Trong đó số lớn nhất `3` phải xuất hiện **2 lần**

// # Mảng hiện tại:

// # ```text
// # 1 xuất hiện 1 lần
// # 2 xuất hiện 1 lần
// # 3 xuất hiện 2 lần
// # ```

// # => Đây là mảng good.

// # ---

// # ### Ví dụ 2

// # ```text
// # nums = [1,1]
// # ```

// # * Độ dài = 2
// # * Cần:

// # ```text
// # 1 xuất hiện 2 lần
// # ```

// # Mảng đúng yêu cầu.

// # => good.

// # ---

// # ### Ví dụ 3

// # ```text
// # nums = [1,2,2,4]
// # ```

// # * Độ dài = 4
// # * Cần:

// # ```text
// # 1,2,3
// # ```

// # và `3` phải xuất hiện 2 lần.

// # Nhưng mảng lại có `4`.

// # => không good.

// # ---

// # # Quy luật tổng quát

// # Nếu mảng có độ dài `n`:

// # Thì sau khi sort phải thành:

// # ```text
// # [1, 2, 3, ..., n-1, n-1]
// # ```

// # ---

// # # Cách làm đơn giản

// # ## Bước 1

// # Sort mảng.

// # ## Bước 2

// # Kiểm tra:

// # * `nums[i] == i + 1` với mọi `i < n-1`
// # * phần tử cuối cùng phải bằng `n-1`

// # ---

// # # Java Code

// # ```java
// # class Solution {
// # public boolean isGood(int[] nums) {
// # Arrays.sort(nums);

// # int n = nums.length;

// # for (int i = 0; i < n - 1; i++) {
// # if (nums[i] != i + 1) {
// # return false;
// # }
// # }

// # return nums[n - 1] == n - 1;
// # }
// # }
// # ```

// # ---

// # # Vì sao code đúng?

// # Ví dụ:

// # ```text
// # [1,2,3,3]
// # ```

// # Sau sort:

// # ```text
// # [1,2,3,3]
// # ```

// # Loop kiểm tra:

// # ```text
// # nums[0] = 1
// # nums[1] = 2
// # nums[2] = 3
// # ```

// # đều đúng với `i + 1`.

// # Sau đó kiểm tra:

// # ```text
// # nums[3] == 3
// # ```

// # => true.

// # ---

// # # Độ phức tạp

// # * Sort: `O(n log n)`
// # * Kiểm tra: `O(n)`

// # Tổng:

// # ```text
// # O(n log n)
// # ```
