// // Separate the Digits in an Array (11/05/2026)

import java.util.*;

public class b230 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        // Tạo mảng
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {

            arr[i] = sc.nextInt();
        }

        // Gọi hàm separateDigits
        int[] result = separateDigits(arr);

        System.out.println(Arrays.toString(result));

        sc.close();
    }

    // Hàm tách từng chữ số trong mảng
    public static int[] separateDigits(int[] arr) {

        // Biến lưu tổng số chữ số của toàn bộ mảng
        int totalDigits = 0;

        // =========================
        // BƯỚC 1: Đếm tổng chữ số
        // =========================
        for (int n : arr) {

            int temp = n;

            // Nếu số là 0 thì có 1 chữ số
            if (temp == 0) {

                totalDigits++;
            } else {

                // Đếm số chữ số bằng cách chia 10 liên tục
                while (temp > 0) {

                    totalDigits++;

                    temp /= 10;
                }
            }
        }

        // Tạo mảng kết quả với đúng kích thước
        int[] ans = new int[totalDigits];

        // index bắt đầu từ cuối mảng kết quả
        int index = totalDigits - 1;

        // ====================================
        // BƯỚC 2: Tách chữ số đưa vào mảng
        // ====================================

        // Duyệt ngược mảng ban đầu
        for (int i = arr.length - 1; i >= 0; i--) {

            int num = arr[i];

            // Nếu số là 0
            if (num == 0) {

                ans[index--] = 0;
            } else {

                // Lấy từng chữ số cuối
                while (num > 0) {

                    // Lấy chữ số cuối cùng
                    ans[index--] = num % 10;

                    // Xóa chữ số cuối
                    num /= 10;
                }
            }
        }

        // Trả về mảng kết quả
        return ans;
    }
}

// ## Ý tưởng thuật toán

// Ta cần tách từng chữ số của các số trong mảng và đưa vào mảng kết quả theo
// đúng thứ tự.

// Ví dụ:

// ```text id="ps6gjc"
// arr = [13, 25, 83]
// ```

// Kết quả:

// ```text id="6jl7cm"
// [1,3,2,5,8,3]
// ```

// ---

// # Phân tích code của bạn

// Code của bạn KHÔNG dùng String mà xử lý trực tiếp bằng toán học.

// Thuật toán gồm 2 bước:

// ---

// # Bước 1: Đếm tổng số chữ số

// ```java id="5h1y2e"
// for(int n : arr)
// ```

// Duyệt từng số trong mảng.

// Ví dụ:

// ```text id="t5r2si"
// 13 -> có 2 chữ số
// 25 -> có 2 chữ số
// 83 -> có 2 chữ số
// ```

// Tổng:

// ```text id="eh1r0o"
// 6 chữ số
// ```

// => tạo được mảng kết quả có kích thước chính xác.

// ---

// ## Cách đếm chữ số

// Ví dụ:

// ```text id="zupf8n"
// 1234
// ```

// Ta liên tục chia 10:

// ```text id="z3jvll"
// 1234 /10 = 123
// 123 /10 = 12
// 12 /10 = 1
// 1 /10 = 0
// ```

// Chia được 4 lần → có 4 chữ số.

// ---

// # Bước 2: Điền chữ số vào mảng kết quả

// Bạn duyệt NGƯỢC mảng:

// ```java id="f3tn3l"
// for(int i = arr.length-1; i>=0; i--)
// ```

// và điền từ cuối mảng kết quả:

// ```java id="xy01kw"
// index = totalDigits - 1
// ```

// ---

// ## Vì sao phải duyệt ngược?

// Khi lấy chữ số bằng:

// ```java id="zwuh36"
// num % 10
// ```

// ta luôn lấy được chữ số cuối trước.

// Ví dụ:

// ```text id="qcw0ll"
// 123
// ```

// Ta lấy được:

// ```text id="z61mje"
// 3 -> 2 -> 1
// ```

// Nếu thêm trực tiếp sẽ bị ngược.

// Nên:

// * duyệt từ cuối mảng
// * điền từ cuối về đầu

// để kết quả đúng thứ tự.

// ---

// # Mô phỏng ví dụ

// ```text id="2dnz1h"
// arr = [13,25]
// ```

// Tổng chữ số:

// ```text id="6mchb6"
// 4
// ```

// Tạo:

// ```text id="yff6d6"
// ans = [ , , , ]
// index = 3
// ```

// ---

// ## Xử lý 25

// Lấy:

// ```text id="mz4kl9"
// 25 % 10 = 5
// ```

// ```text id="xk9l5r"
// ans[3] = 5
// ```

// Tiếp:

// ```text id="gc8y7s"
// 2
// ```

// ```text id="l1s0im"
// ans[2] = 2
// ```

// Mảng:

// ```text id="v4tpxk"
// [ , ,2,5]
// ```

// ---

// ## Xử lý 13

// Tương tự:

// ```text id="1b71hi"
// ans[1] = 3
// ans[0] = 1
// ```

// Kết quả:

// ```text id="ucyqf2"
// [1,3,2,5]
// ```

// ---

// # Code đầy đủ có chú thích + hàm main

// ```java
// import java.util.Arrays;

// class Solution {

// public int[] separateDigits(int[] arr) {

// // Biến lưu tổng số chữ số của toàn bộ mảng
// int totalDigits = 0;

// // =========================
// // BƯỚC 1: Đếm tổng chữ số
// // =========================
// for (int n : arr) {

// int temp = n;

// // Trường hợp số = 0
// // vì 0 có 1 chữ số
// if (temp == 0) {
// totalDigits++;
// }
// else {

// // Đếm số chữ số bằng cách chia 10 liên tục
// while (temp > 0) {

// totalDigits++;

// temp /= 10;
// }
// }
// }

// // Tạo mảng kết quả với đúng kích thước
// int[] ans = new int[totalDigits];

// // index bắt đầu từ cuối mảng
// int index = totalDigits - 1;

// // ======================================
// // BƯỚC 2: Tách chữ số và đưa vào mảng
// // ======================================

// // Duyệt ngược mảng ban đầu
// for (int i = arr.length - 1; i >= 0; i--) {

// int num = arr[i];

// // Nếu số là 0
// if (num == 0) {

// ans[index--] = 0;
// }
// else {

// // Lấy từng chữ số cuối
// while (num > 0) {

// // num % 10 lấy chữ số cuối
// ans[index--] = num % 10;

// // Bỏ chữ số cuối
// num /= 10;
// }
// }
// }

// return ans;
// }

// // =====================
// // Hàm main để chạy thử
// // =====================
// public static void main(String[] args) {

// Solution s = new Solution();

// int[] arr = {13, 25, 83, 77};

// int[] result = s.separateDigits(arr);

// // In kết quả
// System.out.println(Arrays.toString(result));
// }
// }
// ```

// ---

// # Độ phức tạp

// ## Time Complexity

// Mỗi chữ số được xử lý đúng 1 lần:

// ```text id="h3yfru"
// O(total digits)
// ```

// ---

// ## Space Complexity

// Mảng kết quả chứa toàn bộ chữ số:

// ```text id="4kavzl"
// O(total digits)
// ```

// // Cho một mảng số nguyên `nums`.
// // Hãy tách từng chữ số của mỗi phần tử trong mảng theo đúng thứ tự xuất hiện
// và tạo thành một mảng mới.

// // Ví dụ:

// // ```text
// // nums = [13, 25, 83, 77]
// // ```

// // * `13` → tách thành `1, 3`
// // * `25` → tách thành `2, 5`
// // * `83` → tách thành `8, 3`
// // * `77` → tách thành `7, 7`

// // Kết quả:

// // ```text
// // [1,3,2,5,8,3,7,7]
// // ```

// // ---

// // ## Ý nghĩa đề bài

// // Ta duyệt từng số trong mảng:

// // Ví dụ số:

// // ```text
// // 409
// // ```

// // Ta phải lấy ra:

// // ```text
// // 4, 0, 9
// // ```

// // rồi thêm vào mảng kết quả.

// // ---

// // ## Lưu ý quan trọng

// // ### 1. Giữ nguyên thứ tự

// // Ví dụ:

// // ```text
// // nums = [12, 34]
// // ```

// // Kết quả phải là:

// // ```text
// // [1,2,3,4]
// // ```

// // KHÔNG phải:

// // ```text
// // [2,1,4,3]
// // ```

// // ---

// // ### 2. Tách từng chữ số

// // Một số có nhiều chữ số phải được tách riêng từng số.

// // Ví dụ:

// // ```text
// // 507
// // ```

// // →

// // ```text
// // 5,0,7
// // ```

// // ---

// // ## Cách làm đơn giản

// // ### Cách dễ hiểu nhất

// // * Chuyển số thành chuỗi
// // * Duyệt từng ký tự
// // * Đổi lại thành số nguyên
// // * Thêm vào kết quả

// // ---

// // ## Ví dụ từng bước

// // ```text
// // nums = [13,25]
// // ```

// // ### Bước 1:

// // Lấy `13`

// // ```text
// // "13"
// // ```

// // Duyệt:

// // * `'1'` → 1
// // * `'3'` → 3

// // Kết quả hiện tại:

// // ```text
// // [1,3]
// // ```

// // ---

// // ### Bước 2:

// // Lấy `25`

// // ```text
// // "25"
// // ```

// // Duyệt:

// // * `'2'` → 2
// // * `'5'` → 5

// // Kết quả cuối:

// // ```text
// // [1,3,2,5]
// // ```

// // ---

// // ## Độ khó

// // Đây là bài dễ (Easy) trên LeetCode, chủ yếu luyện:

// // * duyệt mảng
// // * xử lý chuỗi
// // * thao tác với chữ số

// // ---

// // ## Java code mẫu

// // ```java
// // class Solution {
// // public int[] separateDigits(int[] nums) {

// // ArrayList<Integer> list = new ArrayList<>();

// // for (int num : nums) {

// // String s = String.valueOf(num);

// // for (char c : s.toCharArray()) {
// // list.add(c - '0');
// // }
// // }

// // int[] result = new int[list.size()];

// // for (int i = 0; i < list.size(); i++) {
// // result[i] = list.get(i);
// // }

// // return result;
// // }
// // }
// // ```
