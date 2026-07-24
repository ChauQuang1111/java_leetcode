// Bài Number of Unique XOR Triplets II (24/07/2026)
// Ý nghĩa đề bài
// Cho một mảng nums.
// Bạn chọn 3 chỉ số khác nhau i, j, k sao cho:

// 0 <= i < j < k < n
// Sau đó tính:
// [
// nums[i] \oplus nums[j] \oplus nums[k]
// ]
// Trong đó ⊕ là phép XOR.
// Cuối cùng, hãy trả về số lượng giá trị XOR khác nhau thu được từ tất cả các bộ ba.
// Ví dụ 1
// nums = [1,2,3]
// Chỉ có một bộ ba:

// (1,2,3)

// 1 ^ 2 = 3
// 3 ^ 3 = 0
// Các giá trị XOR thu được:

// {0}
// Đáp án:

// 1
// Ví dụ 2
// nums = [1,2,3,4]
// Các bộ ba là:

// (1,2,3) = 0
// (1,2,4) = 7
// (1,3,4) = 6
// (2,3,4) = 5
// Tập giá trị:

// {0,5,6,7}
// Có 4 giá trị khác nhau.
// Đáp án:

// 4
// "Unique" nghĩa là gì?
// Ví dụ

// nums = [1,1,2,2]
// Các bộ ba có thể cho kết quả

// 1^1^2 = 2
// 1^2^2 = 1
// ...
// Nếu kết quả thu được là

// 2
// 2
// 2
// 1
// 1
// thì chỉ có

// {1,2}
// => đáp án là

// 2
// Không đếm số lần xuất hiện, chỉ đếm số giá trị XOR khác nhau.
// Lưu ý
// Đề II thường có ràng buộc lớn hơn bản I, nên không thể duyệt tất cả các bộ ba.
// Nếu:

// n = 10^5
// thì số bộ ba là
// [
// \binom{10^5}{3}
// \approx 1.67 \times 10^{14}
// ]
// không thể tính trực tiếp.
// Do đó bài II yêu cầu khai thác tính chất của phép XOR hoặc dùng kỹ thuật tối ưu (ví dụ: quy hoạch động trên giá trị XOR, biến đổi Walsh–Hadamard, hoặc các tính chất đặc biệt của dữ liệu, tùy theo ràng buộc cụ thể của đề).
// Tóm tắt đề bài
// Input:

// nums = [a1,a2,...,an]
// Thực hiện:

// Chọn mọi bộ ba i < j < k.
// Tính nums[i] ^ nums[j] ^ nums[k].
// Lấy tập các kết quả khác nhau.
// Trả về kích thước của tập đó.
// Nếu bạn đang nói đến bài LeetCode "Number of Unique XOR Triplets II", mình cũng có thể giải thích ý tưởng tối ưu và vì sao lời giải không cần duyệt (O(n^3)).
// Dưới đây là phiên bản đầy đủ có main dùng Scanner, chú thích từng đoạn code, và giải thích thuật toán.

import java.util.*;

public class b276{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
     int n = sc.nextInt();

        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        

        System.out.println(uniqueXorTriplets(nums));

        sc.close(); 
    }
public  static int uniqueXorTriplets(int[] nums) {

        // pairsPossible[x] = true nếu tồn tại một cặp có XOR bằng x
        // 2048 = 2^11 vì nums[i] <= 1023 (10 bit),
        // XOR lớn nhất chỉ đến 2047.
        boolean[] pairsPossible = new boolean[2048];

        // ans[x] = true nếu x là kết quả XOR của một bộ ba.
        boolean[] ans = new boolean[2048];

        int n = nums.length;

        //-------------------------------------------------------
        // Bước 1: Tính XOR của mọi cặp phần tử
        //-------------------------------------------------------
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // Lưu rằng giá trị XOR này có thể tạo được
                pairsPossible[nums[i] ^ nums[j]] = true;
            }
        }

        //-------------------------------------------------------
        // Bước 2:
        // Ban đầu thêm chính các phần tử của mảng vào ans.
        //
        // Vì:
        // 0 ^ x = x
        //
        // Khi chưa có cặp (coi như XOR = 0),
        // các giá trị này cũng được tính.
        //-------------------------------------------------------
        for (int e : nums) {
            ans[e] = true;
        }

        //-------------------------------------------------------
        // Bước 3:
        // Ghép từng XOR của cặp với từng phần tử
        //
        // Nếu pair = a ^ b
        // thì
        // pair ^ c = a ^ b ^ c
        //
        // chính là XOR của bộ ba.
        //-------------------------------------------------------
        for (int i = 0; i < 2048; i++) {

            // Chỉ xét các XOR của cặp đã tồn tại
            if (pairsPossible[i]) {

                for (int e : nums) {

                    // Tạo XOR của bộ ba
                    ans[i ^ e] = true;
                }
            }
        }

        //-------------------------------------------------------
        // Bước 4: Đếm số giá trị XOR khác nhau
        //-------------------------------------------------------
        int ret = 0;

        for (boolean e : ans) {
            if (e)
                ret++;
        }

        return ret;
    }
}




// Giải thích thuật toán
// Giả sử

// nums = [1,2,3,4]
// Bước 1: Tính XOR của mọi cặp
// Hai vòng lặp

// for (int i=0;i<n;i++)
//     for (int j=i+1;j<n;j++)
// tạo tất cả các cặp.
// Ví dụ
// CặpXOR1,231,321,452,312,463,47
// Ta đánh dấu

// pairsPossible[1] = true
// pairsPossible[2] = true
// pairsPossible[3] = true
// pairsPossible[5] = true
// pairsPossible[6] = true
// pairsPossible[7] = true
// Thay vì lưu từng cặp, ta chỉ cần biết giá trị XOR nào xuất hiện.
// Bước 2: Khởi tạo đáp án
// for (int e : nums)
//     ans[e] = true;
// Sau bước này

// ans = {1,2,3,4}
// Bước 3: Sinh XOR của bộ ba
// Giả sử đã biết

// pair = a ^ b
// thì

// pair ^ c

// = (a ^ b) ^ c

// = a ^ b ^ c
// chính là XOR của bộ ba.
// Ví dụ

// pair = 6

// (2 ^ 4)
// Ghép với

// 3
// ta được

// 6 ^ 3

// = (2 ^ 4) ^ 3

// = 2 ^ 3 ^ 4
// Đó chính là XOR của bộ ba (2,3,4).
// Thuật toán lặp

// for (int i=0;i<2048;i++)
// Nếu

// pairsPossible[i]
// thì thử

// i ^ nums[0]

// i ^ nums[1]

// ...

// i ^ nums[n-1]
// Mỗi kết quả đều là XOR của một bộ ba.
// Ví dụ
// nums = [1,2,3]
// XOR các cặp
// 1^2 = 3

// 1^3 = 2

// 2^3 = 1
// pairsPossible

// 1
// 2
// 3
// Ghép với từng phần tử

// 1^1 = 0

// 1^2 = 3

// 1^3 = 2

// 2^1 = 3

// 2^2 = 0

// 2^3 = 1

// 3^1 = 2

// 3^2 = 1

// 3^3 = 0
// Các giá trị thu được

// 0
// 1
// 2
// 3
// Sau khi loại trùng

// {0,1,2,3}
// Độ phức tạp
// Bước 1
// Duyệt mọi cặp:
// [
// O(n^2)
// ]
// Bước 2
// Khởi tạo:
// [
// O(n)
// ]
// Bước 3
// Có tối đa 2048 giá trị XOR khác nhau.
// Với mỗi giá trị, thử ghép với n phần tử:
// [
// O(2048 \times n)
// ]
// Do 2048 là hằng số, nên bước này coi như:
// [
// O(n)
// ]
// Tổng độ phức tạp
// Thời gian: (O(n^2 + 2048 \times n) \approx O(n^2))
// Bộ nhớ: (O(2048)), tức là (O(1)) vì kích thước mảng pairsPossible và ans là hằng số.