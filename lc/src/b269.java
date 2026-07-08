// # Bài Concatenate Non-Zero Digits and Multiply by Sum II (08/07/2026)
// # Ý tưởng của đề bài là:



// # Cho một số nguyên (N). Thực hiện các bước sau cho đến khi số chỉ còn 1 chữ số.

// # Ở mỗi bước:



// # Lấy tất cả các chữ số khác 0 của số hiện tại.

// # Ghép (concatenate) các chữ số đó theo đúng thứ tự để tạo thành một số mới.

// # Tính tổng các chữ số của số hiện tại.

// # Nhân số ghép được với tổng đó để tạo ra số mới.

// # Lặp lại.

// # Cuối cùng, in ra chữ số cuối cùng thu được.

// # Ví dụ

// # Giả sử

// # [

// # N = 12034

// # ]

// # Bước 1

// # Các chữ số:



// # 1 2 0 3 4

// # Ghép các chữ số khác 0

// # Bỏ số 0:



// # 1 2 3 4

// # Ghép lại:



// # 1234

// # Tổng các chữ số

// # 1 + 2 + 0 + 3 + 4 = 10

// # Nhân

// # 1234 × 10 = 12340

// # Số mới là



// # 12340

// # Bước tiếp theo

// # Số hiện tại:



// # 12340

// # Ghép các chữ số khác 0:



// # 1234

// # Tổng:



// # 1+2+3+4+0=10

// # Nhân:



// # 1234×10=12340

// # Lúc này sẽ lặp lại mãi.

// # (Nếu đây đúng là đề bạn đang làm thì đề sẽ có thêm điều kiện để tránh trường hợp này, hoặc yêu cầu số bước, hoặc giới hạn khác.)

// # Ví dụ khác

// # Giả sử



// # N = 105

// # Lần 1

// # Ghép chữ số khác 0



// # 15

// # Tổng



// # 1+0+5=6

// # Nhân



// # 15×6=90

// # Lần 2

// # 90

// # Ghép chữ số khác 0



// # 9

// # Tổng



// # 9+0=9

// # Nhân



// # 9×9=81

// # Lần 3

// # 81

// # Ghép



// # 81

// # Tổng



// # 8+1=9

// # Nhân



// # 81×9=729

// # ...

// # Ý nghĩa các từ trong tên bài

// # Concatenate = ghép các chữ số lại thành một số.

// # Ví dụ:



// # 1, 4, 8 → 148

// # Non-Zero Digits = chỉ lấy các chữ số khác 0.

// # Multiply by Sum = nhân với tổng các chữ số.

// # II = phiên bản thứ hai của bài toán (thường khó hơn phiên bản I).

// # Bạn có thể gửi link đề bài hoặc ảnh đề đầy đủ được không?

// # Có khá nhiều bài có tên gần giống nhau. Nếu bạn gửi đề đầy đủ, mình sẽ giải thích từng dòng của đề và ý tưởng giải mà không làm lộ lời giải ngay.

// # Đoạn code này giải bài toán bằng Prefix Sum + Prefix Concatenation + Prefix Count, giúp trả lời mỗi query trong O(1) sau khi tiền xử lý.

// # Ý tưởng

// # Với mỗi truy vấn [l, r] cần tính



// # Tổng các chữ số trong đoạn s[l:r].

// # Ghép các chữ số khác 0 trong đoạn đó.

// # Kết quả =

// # [

// # (\text{số ghép}) \times (\text{tổng chữ số}) \bmod (10^9+7)

// # ]

// # Nếu mỗi query đều duyệt từ l đến r thì



// # Time = O(n*m) (TLE).

// # Ta phải tiền xử lý.

// # Code có chú thích
// from typing import List

// MOD = 10 ** 9 + 7

// # pow10[i] = 10^i mod MOD

// # Dùng để "dịch trái" số khi lấy đoạn con.

// pow10 = [1] * 100001

// for i in range(1, 100001):

//     pow10[i] = pow10[i - 1] * 10 % MOD

// class Solution:

//     def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:



//         n = len(s)



//         # prefix tổng chữ số

//         prefix_sum = [0] * (n + 1)



//         # prefix của số được ghép từ các chữ số khác 0

//         prefix_concat = [0] * (n + 1)



//         # prefix đếm bao nhiêu chữ số khác 0

//         prefix_count = [0] * (n + 1)



//         # -------------------------

//         # Tiền xử lý

//         # -------------------------

//         for i, c in enumerate(s):



//             d = int(c)



//             # Tổng chữ số

//             prefix_sum[i + 1] = prefix_sum[i] + d



//             # Nếu d khác 0 thì ghép thêm vào cuối

//             #

//             # VD:

//             # trước là 123

//             # gặp 4

//             #

//             # 123 -> 1234

//             #

//             if d > 0:

//                 prefix_concat[i + 1] = (

//                     prefix_concat[i] * 10 + d

//                 ) % MOD

//             else:

//                 # gặp số 0 thì bỏ qua

//                 prefix_concat[i + 1] = prefix_concat[i]



//             # Đếm số chữ số khác 0

//             prefix_count[i + 1] = prefix_count[i] + (d > 0)



//         ans = [0] * len(queries)



//         # -------------------------

//         # Trả lời từng query

//         # -------------------------

//         for i, (l, r) in enumerate(queries):



//             # Có bao nhiêu chữ số khác 0 trong đoạn

//             #

//             # Ví dụ

//             #

//             # s = 102304

//             #

//             # đoạn = 0230

//             #

//             # chỉ có 2 và 3

//             #

//             length = prefix_count[r + 1] - prefix_count[l]



//             # Tổng chữ số đoạn [l,r]

//             total = prefix_sum[r + 1] - prefix_sum[l]



//             # Lấy số ghép của đoạn [l,r]

//             #

//             # prefix_concat[r+1]

//             #

//             # = prefix trước l

//             # + phần cần lấy

//             #

//             # Muốn bỏ prefix trước l

//             # phải nhân với 10^(độ dài đoạn)

//             #

//             concat = (

//                 prefix_concat[r + 1]

//                 - prefix_concat[l] * pow10[length]

//             ) % MOD



//             ans[i] = concat * total % MOD



//         return ans

// # Điều khó hiểu nhất: prefix_concat

// # Ví dụ



// # s = "102304"

// # Ta xây dựng



// # i      0 1 2 3 4 5

// # digit  1 0 2 3 0 4

// # prefix_concat



// # ban đầu = 0



// # 1

// # ↓



// # 1



// # 0

// # ↓



// # 1



// # 2

// # ↓



// # 12



// # 3

// # ↓



// # 123



// # 0

// # ↓



// # 123



// # 4

// # ↓



// # 1234

// # Kết quả



// # prefix_concat



// # 0

// # 1

// # 1

// # 12

// # 123

// # 123

// # 1234

// # Giả sử query lấy



// # [2,5]



// # chuỗi = 2304



// # bỏ 0



// # => 234

// # Ta có



// # prefix_concat[6] = 1234



// # prefix_concat[2] = 1

// # Nếu chỉ lấy



// # 1234 - 1 = 1233

// # thì sai.

// # Vì số 1 đang nằm ở hàng nghìn.

// # Ta phải dịch trái:



// # 1 × 10^3 = 1000

// # (do đoạn cần lấy có 3 chữ số khác 0)

// # nên



// # 1234

// # -1000

// # ------

// # 234

// # Đó là lý do có công thức



// # concat = prefix_concat[r+1] - prefix_concat[l] * pow10[length]

// # Vì sao cần prefix_count?

// # Để biết phải nhân với 10^bao_nhiêu.

// # Ví dụ



// # 102304

// # đoạn



// # 0230

// # không phải dài 4.

// # Sau khi bỏ số 0 chỉ còn



// # 23

// # nên phải nhân



// # 10²

// # không phải



// # 10⁴

// # Do đó



// # length = prefix_count[r+1] - prefix_count[l]

// # chính là số lượng chữ số khác 0 trong đoạn, dùng để xác định số mũ của 10 khi loại bỏ phần tiền tố trong prefix_concat.

// # Độ phức tạp

// # Tiền xử lý:

// # prefix_sum: O(n)

// # prefix_concat: O(n)

// # prefix_count: O(n)

// # Mỗi truy vấn:

// # Chỉ gồm vài phép trừ, nhân và lấy modulo: O(1)

// # Tổng thời gian: O(n + m), với n là độ dài chuỗi và m là số lượng truy vấn. Bộ nhớ sử dụng: O(n).




// Đây là lời giải đầy đủ bằng Java có:



// Giải thích thuật toán.

// Thêm main() dùng Scanner.

// Chú thích chi tiết từng dòng code.

// Ý tưởng thuật toán

// Giả sử chuỗi



// s = "1023045"

// Query:



// l = 2

// r = 6

// Đoạn con là



// 23045

// Ta cần



// Tổng chữ số

// 2+3+0+4+5 = 14

// Ghép các số khác 0

// 2345

// Kết quả



// 2345 × 14

// Nếu mỗi query đều duyệt lại đoạn [l,r] thì



// O(n × q)

// sẽ bị TLE.

// Ta tiền xử lý ba mảng prefix.

// 1. prefixSum

// prefixSum[i]

// là tổng các chữ số từ đầu đến trước vị trí i.

// Ví dụ



// s = 102304

// index



// 0 1 2 3 4 5

// 1 0 2 3 0 4

// prefixSum



// 0

// 1

// 1

// 3

// 6

// 6

// 10

// Tổng đoạn



// [l,r]

// là



// prefixSum[r+1]-prefixSum[l]

// 2. preProduct

// Đây là phần khó nhất.

// Ta chỉ ghép các chữ số khác 0.

// Ví dụ



// 102304

// quá trình



// 1



// ↓



// 1



// ↓



// 12



// ↓



// 123



// ↓



// 123



// ↓



// 1234

// nên



// preProduct



// 0

// 1

// 1

// 12

// 123

// 123

// 1234

// 3. nonZeroCnt

// Đếm bao nhiêu số khác 0.



// 102304

// 0

// 1

// 1

// 2

// 3

// 3

// 4

// Tại sao cần nonZeroCnt?

// Giả sử query



// [2,5]

// 2304

// Sau khi bỏ 0



// 234

// độ dài là



// 3

// không phải



// 4

// Do đó phải nhân



// 10³

// chứ không phải



// 10⁴

// Tại sao có công thức

// preProduct[r+1]

// -

// preProduct[l] * 10^cnt

// Ví dụ



// 102304

// preProduct



// 1234

// Muốn lấy



// 234

// ta có



// 1234

// -

// 1000

// =

// 234

// Trong đó



// 1000



// =



// 1 × 10³

// Code đầy đủ có main và chú thích

import java.util.Scanner;

public class b269{

     static final long MOD = 1_000_000_007;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
       // Nhập chuỗi số

        String s = sc.next();
        

        // Nhập số lượng query

        int q = sc.nextInt();

        int[][] queries = new int[q][2];

        // Nhập từng query

        for (int i = 0; i < q; i++) {

            queries[i][0] = sc.nextInt(); // left

            queries[i][1] = sc.nextInt(); // right

        }
        int[] ans = sumAndMultiply(s, queries);

        for (int x : ans)

            System.out.println(x);



        sc.close();
    }
public static int[] sumAndMultiply(String s, int[][] queries) {



        int n = s.length();



        // -------------------------------------------------

        // prefixSum[i]

        // Tổng các chữ số từ đầu đến trước vị trí i

        // -------------------------------------------------

        long[] prefixSum = new long[n + 1];



        // -------------------------------------------------

        // prefixConcat[i]

        // Số được ghép từ các chữ số khác 0

        //

        // Ví dụ

        // 102304

        //

        // 1

        // 1

        // 12

        // 123

        // 123

        // 1234

        // -------------------------------------------------

        long[] prefixConcat = new long[n + 1];



        // -------------------------------------------------

        // prefixCount[i]

        // Có bao nhiêu chữ số khác 0

        // -------------------------------------------------

        int[] prefixCount = new int[n + 1];



        // -------------------------------------------------

        // p10[i] = 10^i mod MOD

        // -------------------------------------------------

        long[] p10 = new long[n + 1];



        p10[0] = 1;



        // Tiền xử lý

        for (int i = 0; i < n; i++) {



            p10[i + 1] = (p10[i] * 10) % MOD;



            int digit = s.charAt(i) - '0';



            // prefix tổng

            prefixSum[i + 1] = prefixSum[i] + digit;



            if (digit == 0) {



                // gặp 0 thì bỏ qua

                prefixConcat[i + 1] = prefixConcat[i];



                prefixCount[i + 1] = prefixCount[i];



            } else {



                // Ghép thêm chữ số mới

                //

                // ví dụ

                //

                // 123

                //

                // gặp 4

                //

                // 1234

                //

                prefixConcat[i + 1] =

                        (prefixConcat[i] * 10 + digit) % MOD;



                prefixCount[i + 1] = prefixCount[i] + 1;

            }

        }



        int[] ans = new int[queries.length];



        // Trả lời từng query

        for (int i = 0; i < queries.length; i++) {



            int l = queries[i][0];

            int r = queries[i][1];



            // Tổng chữ số đoạn [l,r]

            long sum = prefixSum[r + 1] - prefixSum[l];



            // Có bao nhiêu số khác 0

            int cnt = prefixCount[r + 1] - prefixCount[l];



            // Bỏ phần prefix phía trước

            //

            // prefixConcat[r+1]

            // -

            // prefixConcat[l] * 10^cnt

            //

            long remove = (prefixConcat[l] * p10[cnt]) % MOD;



            long concat =

                    (prefixConcat[r + 1] - remove + MOD) % MOD;



            ans[i] = (int) ((concat * sum) % MOD);

        }



        return ans;

    }
}





    








    



// Độ phức tạp

// Tiền xử lý:

// Xây dựng prefixSum: O(n)

// Xây dựng prefixConcat: O(n)

// Xây dựng prefixCount: O(n)

// Xây dựng p10: O(n)

// ⇒ Tổng tiền xử lý: O(n)

// Mỗi truy vấn:

// Chỉ thực hiện một vài phép trừ, nhân và lấy modulo.

// ⇒ O(1)

// Tổng cộng:

// [

// O(n + q)

// ]

// với n là độ dài chuỗi và q là số lượng truy vấn.



// Bộ nhớ: sử dụng 4 mảng tiền xử lý kích thước n + 1, nên là O(n).