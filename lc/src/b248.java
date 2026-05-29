// # Minimum Element After Replacement With Digit Sum(29/05/2026)

// # Đề bài

// # Cho một mảng số nguyên nums.

// # Với mỗi phần tử trong mảng:



// # thay số đó bằng tổng các chữ số của nó.

// # Sau khi thay hết:



// # tìm phần tử nhỏ nhất trong mảng mới.

// # Ví dụ

// # Ví dụ 1

// # Input:



// # nums = [10,12,13,14]

// # Bước thay thế

// # 10 → 1 + 0 = 1

// # 12 → 1 + 2 = 3

// # 13 → 1 + 3 = 4

// # 14 → 1 + 4 = 5

// # Mảng mới:



// # [1,3,4,5]

// # Phần tử nhỏ nhất:



// # 1

// # Output:



// # 1

// # “Digit Sum” là gì?

// # Là tổng các chữ số của một số.

// # Ví dụ:



// # 456 → 4 + 5 + 6 = 15

// # 98 → 9 + 8 = 17

// # Nhiệm vụ của bài

// # Với mỗi số:



// # tách từng chữ số

// # cộng các chữ số lại

// # cập nhật số đó thành tổng vừa tính

// # cuối cùng tìm số nhỏ nhất

// # Cách tách chữ số

// # Ví dụ số 538

// # Ta làm:



// # 538 % 10 = 8

// # 538 / 10 = 53



// # 53 % 10 = 3

// # 53 / 10 = 5



// # 5 % 10 = 5

// # Tổng:



// # 8 + 3 + 5 = 16

// # Ý tưởng thuật toán

// # min = rất lớn



// # duyệt từng số trong nums:

// #     tính tổng chữ số

// #     cập nhật min



// # trả về min

// # Code Java đơn giản

// # class Solution {

// #     public int minElement(int[] nums) {

// #         int min = Integer.MAX_VALUE;



// #         for (int num : nums) {

// #             int sum = 0;



// #             while (num > 0) {

// #                 sum += num % 10;

// #                 num /= 10;

// #             }



// #             min = Math.min(min, sum);

// #         }



// #         return min;

// #     }

// # }

// # Độ phức tạp

// # n phần tử

// # mỗi số có tối đa k chữ số

// # Độ phức tạp:



// # O(n * k)
// from typing import List
// class Solution:
//     def minElement(self, nums: List[int]) -> int:



//         # Khởi tạo giá trị nhỏ nhất ban đầu là vô cùng

//         min_res = float('inf')



//         # Duyệt từng số trong mảng

//         for n in nums:



//             # cur dùng để lưu tổng các chữ số của n

//             cur = 0



//             # Tách từng chữ số của n

//             while n:



//                 # Lấy chữ số cuối cùng

//                 # Ví dụ: 538 % 10 = 8

//                 cur += n % 10



//                 # Xóa chữ số cuối cùng

//                 # Ví dụ: 538 // 10 = 53

//                 n //= 10



//             # Cập nhật giá trị nhỏ nhất

//             min_res = min(min_res, cur)



//         # Trả về kết quả cuối cùng

//         return min_res

// # Giải thích thuật toán

// # Ví dụ:



// # nums = [10, 25, 38]

// # Bước 1: xử lý số 10

// # n = 10

// # cur = 0

// # Vòng lặp while

// # Lần 1:



// # 10 % 10 = 0

// # cur = 0 + 0 = 0



// # 10 // 10 = 1

// # Lần 2:



// # 1 % 10 = 1

// # cur = 0 + 1 = 1



// # 1 // 10 = 0

// # Kết thúc:



// # cur = 1

// # Cập nhật:



// # min_res = 1

// # Bước 2: xử lý số 25

// # 2 + 5 = 7

// # min(1, 7) = 1

// # Bước 3: xử lý số 38

// # 3 + 8 = 11

// # min(1, 11) = 1

// # Kết quả

// # return 1

// # Ý tưởng chính

// # Mỗi số:



// # dùng % 10 để lấy chữ số cuối

// # dùng // 10 để bỏ chữ số cuối

// # cộng các chữ số lại

// # tìm giá trị nhỏ nhất trong các tổng chữ số

// # Độ phức tạp

// # Giả sử:



// # có n phần tử

// # mỗi số có tối đa k chữ số

// # Thì:



// # Time Complexity: O(n * k)

// # Space Complexity: O(1)
// Code này dùng một mẹo toán học để tính tổng chữ số mà không cần dùng vòng lặp tách từng chữ số.



// class Solution {

//     public int minElement(int[] nums) {

//         int res = 36;



//         for (int n : nums)

//             res = Math.min(

//                 res,

//                 n - 9 * ((n/10) + (n/100) + (n/1000) + (n/10000))

//             );



//         return res;

//     }

// }

// Ý tưởng chính

// Ta biết:



// Với số có 2 chữ số

// Ví dụ:



// n = 57

// Ta có:



// 57 / 10 = 5

// Công thức:



// 57 - 9 * 5

// = 57 - 45

// = 12

// Mà:



// 1 + 2 = 12

// → chính là tổng chữ số.

// Vì sao lại đúng?

// Giả sử:



// abc

// Ví dụ:



// 538

// Ta có:



// 538 / 10 = 53

// 538 / 100 = 5

// Tổng:



// 53 + 5 = 58

// Nhân 9:



// 58 * 9 = 522

// Lấy:



// 538 - 522 = 16

// Mà:



// 5 + 3 + 8 = 16

// Công thức tổng quát

// digit_sum(n)

// =

// n - 9 * (

//     n/10 +

//     n/100 +

//     n/1000 +

//     ...

// )

// Trong Java:



// / với int là chia lấy phần nguyên.

// Giải thích từng phần

// Khởi tạo

// int res = 36;

// Vì:



// số lớn nhất có 4 chữ số là 9999

// tổng chữ số lớn nhất:

// 9 + 9 + 9 + 9 = 36

// Nên dùng 36 làm giá trị ban đầu.

// Duyệt từng số

// for (int n : nums)

// Tính tổng chữ số bằng công thức

// n - 9 * ((n/10) + (n/100) + (n/1000) + (n/10000))

// Ví dụ:



// n = 538

// 538/10 = 53

// 538/100 = 5

// 538/1000 = 0

// 538/10000 = 0

// Tổng:



// 53 + 5 = 58

// 9 * 58 = 522

// 538 - 522 = 16

// → chính là tổng chữ số.

// Cập nhật giá trị nhỏ nhất

// res = Math.min(res, ...)

// Ví dụ hoàn chỉnh

// nums = [10, 25, 38]

// 10

// 10 - 9*(1) = 1

// 25

// 25 - 9*(2) = 7

// 38

// 38 - 9*(3) = 11

// Min:



// 1

// Độ phức tạp

// Không dùng vòng lặp tách chữ số.

// Mỗi số chỉ tính vài phép toán:



// Time Complexity: O(n)

// Space Complexity: O(1)

// Lưu ý

// Code này chỉ đúng khi:



// số có tối đa 5 chữ số (do dùng đến n/10000)

// và nums[i] > 0

// Nếu số lớn hơn nhiều chữ số thì phải thêm:



// n/100000

// n/1000000

// ...


import java.util.*;

public class b248 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
     int n = sc.nextInt();

        // Khai báo mảng
        int[] nums = new int[n];

        

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = minElement(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }
// Hàm tính phần tử nhỏ nhất
    // sau khi thay mỗi số bằng tổng chữ số của nó
    public static int minElement(int[] nums) {

        // Tổng chữ số lớn nhất của số 9999 là 36
        // nên dùng 36 làm giá trị khởi tạo
        int res = 36;

        // Duyệt từng phần tử trong mảng
        for (int n : nums) {

            /*
             * Công thức tính tổng chữ số:
             *
             * digitSum =
             * n - 9 * (
             *      n/10 +
             *      n/100 +
             *      n/1000 +
             *      n/10000
             * )
             */

            int digitSum =
                    n - 9 * (
                            (n / 10) +
                            (n / 100) +
                            (n / 1000) +
                            (n / 10000)
                    );

            // Cập nhật giá trị nhỏ nhất
            res = Math.min(res, digitSum);
        }

        // Trả về kết quả cuối cùng
        return res;
    }

}

