// Find the Prefix Common Array of Two Arrays(20/05/2026)
// # Bài yêu cầu bạn tạo ra một mảng mới dựa trên số lượng phần tử chung giữa 2 mảng tại từng prefix.

// # Đề bài nói gì?

// # Cho 2 mảng A và B:

// # Đều có độ dài n

// # Là hoán vị của các số từ 1 -> n

// # Ta cần tạo mảng C sao cho:

// # C[i] = số lượng phần tử xuất hiện đồng thời

// # trong:

// # A[0..i]

// # và B[0..i]

// # Nói đơn giản:

// # Xét từ đầu mảng tới vị trí i,

// # có bao nhiêu số xuất hiện ở cả A và B?

// # Ví dụ

// # A = [1,3,2,4]

// # B = [3,1,2,4]

// # Ta tính từng vị trí:

// # i = 0

// # Prefix:

// # A: [1]

// # B: [3]

// # Phần tử chung: không có

// # → C[0] = 0

// # i = 1

// # Prefix:

// # A: [1,3]

// # B: [3,1]

// # Phần tử chung:

// # 1, 3

// # Có 2 số

// # → C[1] = 2

// # i = 2

// # Prefix:

// # A: [1,3,2]

// # B: [3,1,2]

// # Chung:

// # 1, 2, 3

// # → C[2] = 3

// # i = 3

// # Prefix:

// # A: [1,3,2,4]

// # B: [3,1,2,4]

// # Chung:

// # 1,2,3,4

// # → C[3] = 4

// # Kết quả:

// # C = [0,2,3,4]

// # Ý tưởng chính

// # Khi duyệt tới vị trí i:

// # thêm A[i]

// # thêm B[i]

// # Ta cần biết:

// # số nào đã xuất hiện trong cả hai mảng?

// # Cách suy nghĩ trực quan

// # Dùng 2 tập hợp:

// # setA = các số đã thấy trong A

// # setB = các số đã thấy trong B

// # Mỗi bước:

// # setA thêm A[i]

// # setB thêm B[i]

// # Sau đó đếm:

// # bao nhiêu số nằm trong cả setA và setB

// # Ví dụ trực quan hơn

// # A = [2,3,1]

// # B = [3,1,2]

// # Bước 0

// # setA = {2}

// # setB = {3}

// # Chung: none

// # C[0] = 0

// # Bước 1

// # setA = {2,3}

// # setB = {3,1}

// # Chung:

// # 3

// # → C[1] = 1

// # Bước 2

// # setA = {2,3,1}

// # setB = {3,1,2}

// # Chung:

// # 1,2,3

// # → C[2] = 3

// from typing import List
// class Solution:
//     def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:

//         # n = độ dài của mảng

//         n = len(A)

//         # Mảng kết quả

//         res = [0] * n

//         # seen[x] dùng để kiểm tra:

//         # số x đã xuất hiện trước đó chưa

//         #

//         # seen[0] được dùng đặc biệt:

//         # lưu số lượng phần tử chung hiện tại

//         seen = [0] * (n + 1)

//         # Duyệt từng vị trí i

//         for i in range(n):

//             # ---------------------------------

//             # Xử lý A[i]

//             # ---------------------------------

//             # Nếu A[i] đã xuất hiện trước đó

//             # trong B hoặc A

//             # thì seen[A[i]] = 1

//             #

//             # Khi cộng vào seen[0]

//             # nghĩa là ta tìm được thêm

//             # một phần tử chung

//             seen[0] += seen[A[i]]

//             # Đánh dấu A[i] đã xuất hiện

//             seen[A[i]] = 1

//             # ---------------------------------

//             # Xử lý B[i]

//             # ---------------------------------

//             # Tương tự cho B[i]

//             #

//             # Nếu B[i] đã từng xuất hiện

//             # thì ta tăng số lượng phần tử chung

//             seen[0] += seen[B[i]]

//             # Đánh dấu B[i] đã xuất hiện

//             seen[B[i]] = 1

//             # Lưu kết quả tại vị trí i

//             res[i] = seen[0]

//         return res

// # Ý tưởng cực kỳ quan trọng

// # seen[x] nghĩa là gì?

// # seen[x] = 1

// # nghĩa là:

// # số x đã xuất hiện trước đó ở một trong hai mảng.

// # Tại sao:

// # seen[0] += seen[A[i]]

// # lại hoạt động?

// # Ví dụ:

// # A = [1,3,2]

// # B = [3,1,2]

// # i = 0

// # xử lý A[0] = 1

// # seen[1] = 0

// # nên:

// # seen[0] += 0

// # không tăng.

// # Sau đó:

// # seen[1] = 1

// # xử lý B[0] = 3

// # seen[3] = 0

// # không tăng.

// # Đánh dấu:

// # seen[3] = 1

// # Hiện tại chưa có số chung.

// # i = 1

// # xử lý A[1] = 3

// # Bây giờ:

// # seen[3] = 1

// # vì số 3 đã xuất hiện trong B trước đó.

// # Nên:

// # seen[0] += 1

// # => tìm được 1 số chung mới.

// # xử lý B[1] = 1

// # Tương tự:

// # seen[1] = 1

// # vì 1 đã xuất hiện trong A.

// # Nên tăng tiếp.

// # Kết quả:

// // # 2 số chung: 1 và 3

// // # Trick hay của bài này

// // # Thay vì dùng:

// // # 2 set

// // # intersection

// // # code này dùng:

// // # seen[0]

// // # để lưu trực tiếp số lượng phần tử chung.

// // # Và:

// // # seen[x]

// // # để biết số x đã xuất hiện chưa.

// // # Độ phức tạp

// // # Time

// // # Duyệt 1 lần:

// // # O(n)

// // # Space

// // # Mảng seen:

// // # O(n)

// Ý tưởng của thuật toán

// Ta cần tìm:

// res[i] = số lượng phần tử xuất hiện

// đồng thời trong:

// A[0..i] và B[0..i]

// Ý tưởng chính

// Dùng mảng seen để đánh dấu:

// một số đã xuất hiện trước đó chưa

// Nếu một số xuất hiện lần thứ hai

// (trong A hoặc B),

// => nghĩa là số đó đã xuất hiện ở mảng còn lại

// => ta tìm được 1 phần tử chung.

// Phân tích code từng dòng

// class Solution {

//     public int[] findThePrefixCommonArray(int[] A, int[] B) {

// Hàm trả về mảng kết quả.

// int n = A.length, count = 0;

// n: độ dài mảng

// count: số lượng phần tử chung hiện tại

// int[] res = new int[n];

// Mảng kết quả.

// boolean[] seen = new boolean[n];

// Mảng đánh dấu.

// Ví dụ:

// seen[2] = true

// nghĩa là:

// số 3 đã xuất hiện

// (vì index bắt đầu từ 0 nên dùng x - 1)

// Vòng lặp chính

// for (int i = 0; i < n; i++) {

// Duyệt từng prefix.

// Xử lý A[i]

// if (seen[A[i] - 1]) count++;

// else seen[A[i] - 1] = true;

// Trường hợp 1

// seen[A[i] - 1] == false

// Nghĩa là:

// A[i] xuất hiện lần đầu

// => đánh dấu đã thấy:

// seen[A[i] - 1] = true;

// Trường hợp 2

// seen[A[i] - 1] == true

// Nghĩa là:

// số này đã xuất hiện trước đó

// mà vì:

// A và B đều là permutation

// mỗi số chỉ xuất hiện 1 lần trong mỗi mảng

// => nếu gặp lại lần 2

// thì chắc chắn số đó đã xuất hiện ở mảng còn lại.

// => tìm được 1 phần tử chung:

// count++;

// Xử lý B[i]

// if (seen[B[i] - 1]) count++;

// else seen[B[i] - 1] = true;

// Tương tự như A[i].

// Lưu kết quả

// res[i] = count;

// Tại vị trí i,

// ta đã biết có bao nhiêu phần tử chung.

// Ví dụ minh họa

// A = [1,3,2,4]

// B = [3,1,2,4]

// i = 0

// A[0] = 1

// seen[0] = false

// => đánh dấu:

// seen[0] = true

// B[0] = 3

// seen[2] = false

// => đánh dấu.

// count = 0

// i = 1

// A[1] = 3

// seen[2] = true

// => số 3 đã xuất hiện trước đó trong B.

// => tăng:

// count = 1

// B[1] = 1

// seen[0] = true

// => số 1 đã xuất hiện trong A.

// => tăng tiếp:

// count = 2

// res[1] = 2

// Vì sao thuật toán đúng?

// Mỗi số:

// xuất hiện đúng 1 lần trong A

// xuất hiện đúng 1 lần trong B

// Nên:

// lần đầu thấy -> đánh dấu

// lần thứ hai thấy -> đây là phần tử chung

// Độ phức tạp

// Time Complexity

// Duyệt 1 lần:

// O(n)

// Space Complexity

// Mảng seen:
// O(n)

import java.util.Scanner;

public class b239 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số lượng phần tử
        int n = sc.nextInt();

        // Khai báo 2 mảng
        int[] A = new int[n];
        int[] B = new int[n];

        // =========================
        // Nhập mảng A
        // =========================
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // =========================
        // Nhập mảng B
        // =========================
        for (int i = 0; i < n; i++) {
            B[i] = sc.nextInt();
        }

        // Gọi hàm xử lý
        int[] result = findThePrefixCommonArray(A, B);

        // =========================
        // In kết quả
        // =========================
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

        sc.close();
    }

    // Hàm tìm Prefix Common Array
    public static int[] findThePrefixCommonArray(int[] A, int[] B) {

        // Độ dài mảng
        int n = A.length;

        // count = số lượng phần tử chung hiện tại
        int count = 0;

        // Mảng kết quả
        int[] res = new int[n];

        // Mảng đánh dấu
        //
        // seen[x] = true
        // nghĩa là số (x + 1) đã xuất hiện trước đó
        boolean[] seen = new boolean[n];

        // Duyệt từng vị trí
        for (int i = 0; i < n; i++) {

            // =========================
            // Xử lý phần tử A[i]
            // =========================

            // Nếu số này đã xuất hiện trước đó
            // => đây là lần xuất hiện thứ 2
            // => số này có trong cả A và B
            if (seen[A[i] - 1]) {
                count++;
            }

            // Nếu chưa xuất hiện
            // thì đánh dấu đã thấy
            else {
                seen[A[i] - 1] = true;
            }

            // =========================
            // Xử lý phần tử B[i]
            // =========================

            // Tương tự như A[i]
            if (seen[B[i] - 1]) {
                count++;
            } else {
                seen[B[i] - 1] = true;
            }

            // Lưu kết quả tại vị trí i
            res[i] = count;
        }

        return res;
    }
}
