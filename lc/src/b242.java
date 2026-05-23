// # Check if Array Is Sorted and Rotated(23/05/2026)

// # Đề bài là gì?

// # Cho một mảng số nguyên nums.

// # Hãy kiểm tra xem mảng này có phải là:



// # Được sắp xếp tăng dần

// # Sau đó có thể đã bị xoay vòng (rotate) một số lần.

// # Nếu đúng → trả về true, ngược lại → false.

// # Sorted and Rotated nghĩa là gì?

// # Ví dụ mảng tăng dần ban đầu:



// # [1,2,3,4,5]

// # Nếu xoay vòng:



// # [3,4,5,1,2]

// # Ta lấy phần đầu đưa xuống cuối.

// # Mảng này vẫn được xem là sorted and rotated.

// # Ví dụ

// # Ví dụ 1

// # nums = [3,4,5,1,2]

// # Đây là:



// # [1,2,3,4,5]

// # xoay vòng thành.

// # → Kết quả: true

// # Ví dụ 2

// # nums = [2,1,3,4]

// # Không thể tạo từ một mảng tăng dần chỉ bằng rotate.

// # → false

// # Ví dụ 3

// # nums = [1,2,3]

// # Đã tăng dần sẵn.

// # Rotate 0 lần cũng được chấp nhận.

// # → true

// # Ý tưởng chính

// # Trong mảng tăng dần xoay vòng:



// # Chỉ có tối đa 1 chỗ bị giảm.

// # Ví dụ:



// # [3,4,5,1,2]

// # So sánh từng cặp:



// # 3 < 4

// # 4 < 5

// # 5 > 1   ← giảm

// # 1 < 2

// # 2 < 3   (vòng lại đầu mảng)

// # Chỉ có 1 lần giảm.

// # Điều kiện kiểm tra

// # Đếm số lần:



// # nums[i] > nums[(i+1)%n]

// # Nếu số lần giảm:



// # <= 1 → true

// # > 1 → false

// # Minh họa bằng hình

// # Mảng tăng dần:



// # 1 2 3 4 5

// # Rotate:



// # 3 4 5 1 2

// #       ^

// #       điểm "gãy"

// # Chỉ có 1 điểm gãy.

// # Code Java

// # class Solution {

// #     public boolean check(int[] nums) {

// #         int count = 0;

// #         int n = nums.length;



// #         for (int i = 0; i < n; i++) {

// #             if (nums[i] > nums[(i + 1) % n]) {

// #                 count++;

// #             }

// #         }



// #         return count <= 1;

// #     }

// # }

// # Độ phức tạp

// # Time: O(n)

// # Space: O(1)

// # Ví dụ chạy tay

// # nums = [3,4,5,1,2]

// # Ta kiểm tra:



// # 3 > 4 ? no

// # 4 > 5 ? no

// # 5 > 1 ? yes  count = 1

// # 1 > 2 ? no

// # 2 > 3 ? no

// # count = 1

// # → true

// # nums = [2,1,3,4]

// # 2 > 1 ? yes count =1

// # 1 > 3 ? no

// # 3 > 4 ? no

// # 4 > 2 ? yes count =2

// # count = 2

// # → false


// # ```python
// from typing import List
// class Solution:
//     def check(self, nums: List[int]) -> bool:

//         # Đếm số lần mảng bị "giảm"
//         # tức là nums[i-1] > nums[i]
//         #
//         # Ví dụ:
//         # [3,4,5,1,2]
//         #        ^
//         # chỉ có 1 chỗ giảm: 5 > 1
//         #
//         # Nếu số lần giảm < 2
//         # => mảng là sorted + rotated

//         return sum(

//             # Kiểm tra nếu phần tử trước lớn hơn phần tử hiện tại
//             nums[i - 1] > nums[i]

//             # duyệt toàn bộ mảng
//             for i in range(len(nums))

//         ) < 2
// # ```

// # ---

// # # Giải thích chi tiết

// # ## `nums[i - 1] > nums[i]`

// # Kiểm tra xem có xảy ra “điểm gãy” không.

// # Ví dụ:

// # ```text id="8mjlwm"
// # [3,4,5,1,2]
// # ```

// # Ta so sánh:

// # | i | nums[i-1] | nums[i] | Kết quả |
// # | - | --------- | ------- | ------- |
// # | 0 | 2         | 3       | False   |
// # | 1 | 3         | 4       | False   |
// # | 2 | 4         | 5       | False   |
// # | 3 | 5         | 1       | True    |
// # | 4 | 1         | 2       | False   |

// # Có đúng 1 lần giảm.

// # ---

// # # Tại sao `i-1` vẫn đúng khi `i = 0`?

// # Trong Python:

// # ```python
// # nums[-1]
// # ```

// # là phần tử cuối mảng.

// # Nên:

// # ```python
// # nums[i - 1]
// # ```

// # khi `i = 0` sẽ là:

// # ```python
// # nums[-1]
// # ```

// # Tức là so sánh:

// # ```text id="qhqj6u"
// # phần tử cuối ↔ phần tử đầu
// # ```

// # Điều này giúp kiểm tra vòng xoay.

// # ---

// # # `sum(...)`

// # Trong Python:

// # ```python
// # True  = 1
// # False = 0
// # ```

// # Nên:

// # ```python
// # sum(condition for ...)
// # ```

// # sẽ đếm số lần condition đúng.

// # ---

// # Ví dụ chạy tay

// ## nums = [3,4,5,1,2]

// # Các kết quả:

// # ```python
// # False
// # False
// # False
// # True
// # False
// # ```

// # Tổng:

// # ```python
// # 1
// # ```

// # ```python
// 1 < 2
// # ```

// # → `True`

// # ---

// # # Ví dụ sai

// # ## nums = [2,1,3,4]

// # So sánh:

// # ```text id="wy0kkh"
// # 2 > 1   True
// # 1 > 3   False
// # 3 > 4   False
// # 4 > 2   True
// # ```

// # Có 2 điểm giảm.

// # ```python
// # 2 < 2
// # ```

// # → `False`

// # ---

// # # Ý tưởng thuật toán

// # Một mảng:

// # * tăng dần bình thường
// #   hoặc
// # * tăng dần rồi rotate

// # sẽ chỉ có tối đa **1 điểm giảm**.

// # Nếu có từ 2 điểm giảm trở lên → không hợp lệ.



// import java.util.Scanner;



// class Solution {



//     // Hàm kiểm tra mảng có phải sorted and rotated hay không

//     public boolean check(int[] nums) {



//         // fault = true nghĩa là đã gặp 1 điểm giảm rồi

//         boolean fault = false;



//         int n = nums.length;



//         // Duyệt toàn bộ mảng

//         for (int i = 0; i < n; i++) {



//             // So sánh phần tử hiện tại với phần tử kế tiếp

//             //

//             // (i + 1) % n dùng để:

//             // - nếu i đang ở cuối mảng

//             // - sẽ quay lại phần tử đầu tiên

//             //

//             // Ví dụ:

//             // i = n - 1

//             // (i + 1) % n = 0



//             if (nums[i] > nums[(i + 1) % n]) {



//                 // Nếu đã từng gặp điểm giảm trước đó

//                 // mà bây giờ lại gặp thêm lần nữa

//                 // => không phải sorted and rotated

//                 if (fault) {

//                     return false;

//                 }



//                 // Đánh dấu đã gặp 1 điểm giảm

//                 fault = true;

//             }

//         }



//         // Nếu chỉ có tối đa 1 điểm giảm

//         // => mảng hợp lệ

//         return true;

//     }



//     public static void main(String[] args) {



//         Scanner sc = new Scanner(System.in);



//         // Nhập số lượng phần tử

//         int n = sc.nextInt();



//         int[] nums = new int[n];



//         // Nhập các phần tử của mảng

//         for (int i = 0; i < n; i++) {

//             nums[i] = sc.nextInt();

//         }



//         // Tạo object Solution

//         Solution sol = new Solution();



//         // Gọi hàm check

//         boolean result = sol.check(nums);



//         // In kết quả

//         System.out.println(result);



//         sc.close();

//     }

// }

// Giải thích thuật toán

// Ta cần kiểm tra xem:



// mảng có tăng dần hay không

// và có thể đã rotate hay chưa

// Ý tưởng chính

// Một mảng tăng dần sau khi rotate sẽ chỉ có:



// tối đa 1 điểm giảm

// Ví dụ đúng

// [3,4,5,1,2]

// So sánh:



// 3 < 4

// 4 < 5

// 5 > 1  ← điểm giảm

// 1 < 2

// 2 < 3

// Chỉ có 1 lần giảm → hợp lệ.

// Ví dụ sai

// [2,1,3,4]

// So sánh:



// 2 > 1  ← giảm

// 1 < 3

// 3 < 4

// 4 > 2  ← giảm lần 2

// Có 2 điểm giảm → không hợp lệ.

// (i + 1) % n dùng để làm gì?

// Để nối vòng mảng.

// Ví dụ:



// n = 5

// i = 4

// Ta cần so sánh:



// nums[4] với nums[0]

// Vì:



// (4 + 1) % 5 = 0

// Ví dụ Input/Output

// Input

// 5

// 3 4 5 1 2

// Output

// true


import java.util.*;

public class b242 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
 // Nhập số lượng phần tử
        int n = sc.nextInt();

        int[] nums = new int[n];

        // Nhập các phần tử của mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        

        // Gọi hàm check
        boolean result = check(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }
 // Hàm kiểm tra mảng có phải sorted and rotated hay không
    public static boolean check(int[] nums) {

        // fault = true nghĩa là đã gặp 1 điểm giảm rồi
        boolean fault = false;

        int n = nums.length;

        // Duyệt toàn bộ mảng
        for (int i = 0; i < n; i++) {

            // So sánh phần tử hiện tại với phần tử kế tiếp
            //
            // (i + 1) % n dùng để:
            // - nếu i đang ở cuối mảng
            // - sẽ quay lại phần tử đầu tiên
            //
            // Ví dụ:
            // i = n - 1
            // (i + 1) % n = 0

            if (nums[i] > nums[(i + 1) % n]) {

                // Nếu đã từng gặp điểm giảm trước đó
                // mà bây giờ lại gặp thêm lần nữa
                // => không phải sorted and rotated
                if (fault) {
                    return false;
                }

                // Đánh dấu đã gặp 1 điểm giảm
                fault = true;
            }
        }

        // Nếu chỉ có tối đa 1 điểm giảm
        // => mảng hợp lệ
        return true;
    }
}

