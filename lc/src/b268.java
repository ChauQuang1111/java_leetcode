// Bài "Concatenate Non-Zero Digits and Multiply by Sum I" (07/07/2026)

// Đề bài

// Cho một số nguyên n.

// Thực hiện các bước:



// Lấy tất cả các chữ số khác 0 của n.

// Ghép (concatenate) các chữ số này lại theo đúng thứ tự xuất hiện để tạo thành một số mới.

// Tính tổng tất cả các chữ số của n (bao gồm cả số 0 nếu có, nhưng số 0 cộng vào tổng không ảnh hưởng).

// Kết quả là:

// [

// (\text{số vừa ghép}) \times (\text{tổng các chữ số})

// ]

// Ví dụ 1

// Input:

// n = 10502

// Bước 1: Tách chữ số

// 1 0 5 0 2

// Bước 2: Lấy chữ số khác 0

// 1 5 2

// Bước 3: Ghép lại

// 152

// Bước 4: Tính tổng chữ số

// 1 + 0 + 5 + 0 + 2 = 8

// Bước 5: Nhân

// 152 × 8 = 1216

// Output



// 1216

// Ví dụ 2

// Input:

// n = 9004

// Các chữ số:



// 9 0 0 4

// Ghép các chữ số khác 0:



// 94

// Tổng chữ số:



// 9 + 0 + 0 + 4 = 13

// Kết quả:



// 94 × 13 = 1222

// Ví dụ 3

// Input:

// n = 7000

// Các chữ số:



// 7 0 0 0

// Ghép:



// 7

// Tổng:



// 7

// Kết quả:



// 7 × 7 = 49

// Ví dụ 4

// Input:

// n = 1002003

// Các chữ số:



// 1 0 0 2 0 0 3

// Ghép:



// 123

// Tổng:



// 1 + 0 + 0 + 2 + 0 + 0 + 3 = 6

// Kết quả:



// 123 × 6 = 738

// Ý nghĩa của "Concatenate"

// "Concatenate" nghĩa là ghép nối các chữ số lại với nhau, không phải cộng.

// Ví dụ:



// 1, 5, 2

// Ghép thành



// 152

// chứ không phải



// 1 + 5 + 2 = 8

// Tóm tắt thuật toán

// Duyệt từng chữ số của n:



// Nếu chữ số ≠ 0:

// Ghép vào số mới.

// Đồng thời cộng chữ số đó (và cả các số 0 nếu muốn) vào tổng.

// Cuối cùng trả về:



// (số ghép từ các chữ số khác 0) × (tổng các chữ số)

// Độ phức tạp của thuật toán là O(k), với k là số chữ số của n.




// Đây là lời giải thích từng bước của thuật toán, kèm theo main() sử dụng Scanner và chú thích đầy đủ.



// Ý tưởng thuật toán

// Ta cần thực hiện hai việc cùng lúc:



// Ghép các chữ số khác 0 thành một số mới.

// Tính tổng các chữ số khác 0 (vì số 0 cộng vào tổng cũng không thay đổi kết quả).

// Ví dụ:



// n = 10502

// Các chữ số lần lượt là:



// 1 0 5 0 2

// Duyệt từng chữ số:

// Chữ sốx (số ghép)s (tổng)1110115156015621528

// Cuối cùng:



// 152 × 8 = 1216

// Giải thích từng dòng code

// class Solution {



//     public long sumAndMultiply(int n) {



//         // x: số được tạo bằng cách ghép các chữ số khác 0

//         // s: tổng các chữ số khác 0

//         long x = 0, s = 0;



//         // Chuyển số n thành chuỗi rồi thành mảng ký tự

//         for (char c : String.valueOf(n).toCharArray())



//             // Nếu ký tự không phải '0'

//             if (c != '0') {



//                 // Ghép thêm chữ số vào cuối số x

//                 // Ví dụ:

//                 // x = 15, c = '2'

//                 // x = 15 * 10 + 2 = 152

//                 x = x * 10 + c - '0';



//                 // Cộng giá trị của chữ số vào tổng

//                 s += c - '0';

//             }



//         // Trả về tích của số ghép và tổng

//         return x * s;

//     }

// }

// Chương trình hoàn chỉnh

import java.util.Scanner;

public class b268{
  
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
      int n = sc.nextInt();


        System.out.println(sumAndMultiply(n));

        sc.close();

    }

    public static long sumAndMultiply(int n) {



        // x: số được ghép từ các chữ số khác 0

        long x = 0;



        // s: tổng các chữ số khác 0

        long s = 0;



        // Chuyển số thành chuỗi rồi duyệt từng ký tự

        for (char c : String.valueOf(n).toCharArray()) {



            // Bỏ qua chữ số 0

            if (c != '0') {



                // Chuyển ký tự thành số

                int digit = c - '0';



                // Ghép chữ số vào cuối x

                x = x * 10 + digit;



                // Cộng vào tổng

                s += digit;

            }

        }



        // Kết quả

        return x * s;

    }

}




// Minh họa với n = 304050

// Vòng lặp sẽ hoạt động như sau:

// Ký tự digitx trướcx saus330330-3334433470-343475534345120-34534512

// Sau vòng lặp:



// x = 345

// s = 12



// Kết quả = 345 × 12 = 4140

// Độ phức tạp

// Thời gian: O(k), với k là số lượng chữ số của n (chỉ duyệt một lần).

// Không gian: O(k) do sử dụng String.valueOf(n) để tạo chuỗi chứa các chữ số. Nếu xử lý trực tiếp bằng phép chia và lấy dư (% 10, / 10), có thể giảm xuống O(1) không gian phụ.