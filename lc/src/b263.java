// Bài toán "Number of Substrings Containing All Three Characters(30/06/2026)

// Đề bài

// Cho một chuỗi s chỉ gồm 3 ký tự:



// 'a'

// 'b'

// 'c'

// Hãy trả về số lượng chuỗi con (substring) chứa ít nhất một 'a', ít nhất một 'b', và ít nhất một 'c'.

// Chuỗi con (Substring) là gì?

// Substring là một đoạn liên tiếp trong chuỗi.

// Ví dụ:



// s = "abc"

// Các substring là:



// "a"

// "b"

// "c"

// "ab"

// "bc"

// "abc"

// Ví dụ 1

// Input:

// s = "abcabc"



// Output:

// 10

// Ta không cần liệt kê hết, nhưng một vài substring hợp lệ là:



// "abc"

// "abca"

// "abcab"

// "abcabc"

// "bca"

// "bcab"

// "bcabc"

// "cab"

// "cabc"

// "abc"

// Tổng cộng có 10 substring chứa đủ a, b, c.

// Ví dụ 2

// Input:

// s = "aaacb"



// Output:

// 3

// Các substring hợp lệ:



// "aaacb"

// "aacb"

// "acb"

// Vì cả ba đều chứa:



// a ✓

// b ✓

// c ✓

// Ví dụ 3

// Input:

// s = "abc"



// Output:

// 1

// Chỉ có



// "abc"

// là chứa đủ 3 ký tự.

// Ví dụ minh họa chi tiết

// s = "abca"

// Đánh số vị trí:



// 0 1 2 3

// a b c a

// Liệt kê toàn bộ substring:

// Bắt đầu từ vị trí 0:



// "a"      ✗

// "ab"     ✗

// "abc"    ✓

// "abca"   ✓

// Bắt đầu từ vị trí 1:



// "b"      ✗

// "bc"     ✗

// "bca"    ✓

// Bắt đầu từ vị trí 2:



// "c"      ✗

// "ca"     ✗

// Bắt đầu từ vị trí 3:



// "a"      ✗

// Có tổng cộng



// "abc"

// "abca"

// "bca"

// → Đáp án là 3.

// Điều kiện cần thỏa

// Một substring hợp lệ khi:

// Có aCó bCó cHợp lệ✓✓✓✅✓✓✗❌✓✗✓❌✗✓✓❌

// Chỉ cần thiếu một ký tự là không được tính.

// Mục tiêu của bài

// Cho chuỗi dài đến khoảng 5 × 10^4, nếu liệt kê tất cả substring sẽ có khoảng O(n²) substring, nên cách làm đó quá chậm.

// Mục tiêu là tìm một thuật toán tối ưu hơn, thường là Sliding Window (hai con trỏ) với độ phức tạp O(n).

// Tóm tắt

// Đầu vào: chuỗi chỉ gồm 'a', 'b', 'c'.

// Yêu cầu: đếm mọi substring liên tiếp chứa ít nhất một a, b và c.

// Không cần substring chỉ chứa đúng 3 ký tự; chỉ cần có đủ cả ba ký tự là hợp lệ.

// Thuật toán tối ưu thường dùng: Sliding Window (Two Pointers), đạt độ phức tạp O(n).


// Ý tưởng của lời giải này rất hay vì không dùng Sliding Window, mà dùng vị trí xuất hiện gần nhất (Last Seen Position) của a, b, c.

// Ý tưởng thuật toán

// Giả sử ta đang xét vị trí right.

// Ta lưu:



// abc[0]: vị trí xuất hiện gần nhất của a

// abc[1]: vị trí xuất hiện gần nhất của b

// abc[2]: vị trí xuất hiện gần nhất của c

// Ví dụ



// s = "abcabc"



// index:

// 0 1 2 3 4 5

// a b c a b c

// Bước 1

// right = 0



// a

// abc = [0,-1,-1]

// Chưa có đủ a,b,c



// min = -1



// count += 0

// Bước 2

// right = 1



// ab

// abc = [0,1,-1]

// Vẫn chưa đủ



// min = -1



// count +=0

// Bước 3

// right = 2



// abc

// abc = [0,1,2]

// Lúc này



// min = 0

// Có nghĩa là



// a gần nhất ở 0

// b gần nhất ở 1

// c gần nhất ở 2

// Substring phải bắt đầu không được lớn hơn 0.

// Có các điểm bắt đầu



// 0

// => chỉ có



// abc

// nên



// count += 1

// vì



// min+1 = 1

// Bước 4

// right = 3



// abca

// Cập nhật



// abc=[3,1,2]

// Lấy nhỏ nhất



// min=1

// Điểm bắt đầu có thể là



// 0

// 1

// Hai substring là



// abca

// bca

// Đều chứa đủ



// a,b,c

// nên



// count+=2

// Bước 5

// right=4



// abcab

// abc=[3,4,2]

// min=2

// Có thể bắt đầu ở



// 0

// 1

// 2

// Các substring



// abcab

// bcab

// cab

// Có 3 substring



// count+=3

// Bước 6

// right=5



// abcabc

// abc=[3,4,5]

// min=3

// Có thể bắt đầu



// 0

// 1

// 2

// 3

// Có



// 4 substring

// count+=4

// Tổng



// 0+0+1+2+3+4=10

// Đúng đáp án.

// Vì sao lại là minIndex + 1?

// Giả sử



// a ở 8

// b ở 6

// c ở 10

// min = 6

// Nếu substring kết thúc tại vị trí hiện tại thì vị trí bắt đầu chỉ được phép nằm trong



// 0

// 1

// 2

// 3

// 4

// 5

// 6

// Có tất cả



// 7 vị trí

// Chính là



// 6+1

// Nếu bắt đầu ở



// 7

// thì sẽ mất ký tự



// b

// vì b ở vị trí 6.

// Đó là lý do cộng



// minIndex+1

// Code có main(), Scanner và chú thích

import java.util.Scanner;

public class b263{
  
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    String s = sc.next();
    System.out.println(numberOfSubstrings(s));
    sc.close();

    }
 public static int numberOfSubstrings(String s) {



        // Chuyển chuỗi thành mảng ký tự để truy cập nhanh

        char[] ch = s.toCharArray();



        // abc[0] lưu vị trí xuất hiện gần nhất của 'a'

        // abc[1] lưu vị trí xuất hiện gần nhất của 'b'

        // abc[2] lưu vị trí xuất hiện gần nhất của 'c'

        int[] abc = new int[3];



        // Ban đầu chưa gặp ký tự nào nên gán -1

        for (int i = 0; i < 3; i++) {

            abc[i] = -1;

        }



        // Kết quả cuối cùng

        int count = 0;



        // right là vị trí kết thúc của substring

        int right = 0;



        while (right < ch.length) {



            // Cập nhật vị trí xuất hiện gần nhất

            // Ví dụ gặp 'b' tại vị trí 5 thì abc[1] = 5

            abc[ch[right] - 'a'] = right;



            // Tìm vị trí nhỏ nhất trong 3 ký tự

            int minIndex = Integer.MAX_VALUE;



            for (int i = 0; i < 3; i++) {

                minIndex = Math.min(minIndex, abc[i]);

            }



            /*

             * Nếu minIndex = -1

             * => chưa xuất hiện đủ a,b,c

             * count += 0

             *

             * Nếu minIndex = 4

             * => có thể bắt đầu từ vị trí:

             * 0,1,2,3,4

             * Có tổng cộng 5 substring hợp lệ

             */

            count += (minIndex + 1);



            right++;

        }



        return count;
    }
}


// Độ phức tạp

// Thời gian: O(n)

// Mỗi ký tự chỉ được duyệt một lần. Ở mỗi bước chỉ tìm giá trị nhỏ nhất trong mảng có 3 phần tử, nên chi phí là hằng số (O(3) = O(1)).

// Không gian: O(1)

// Chỉ sử dụng mảng abc có 3 phần tử, không phụ thuộc vào độ dài chuỗi.

// Đây là một trong những lời giải tối ưu nhất cho bài 1358 vì chỉ cần một lần duyệt chuỗi và không cần dùng hai con trỏ (sliding window).