// Sum of GCD of Formed Pairs"(16/07/2026)
// Đề bài
// Cho một mảng gồm N số nguyên dương:
// [
// A_1, A_2, ..., A_N
// ]
// Hãy xét mọi cặp phần tử khác nhau ((i, j)) với (i < j).
// Với mỗi cặp, tính:
// [
// \gcd(A_i, A_j)
// ]
// (trong đó gcd là ước chung lớn nhất của hai số).
// Cuối cùng, tính tổng tất cả các giá trị gcd đó.
// Ví dụ
// Giả sử:

// N = 4
// A = [10, 20, 30, 40]
// Các cặp được tạo là:
// CặpGCD(10,20)10(10,30)10(10,40)10(20,30)10(20,40)20(30,40)10
// Tổng là

// 10 + 10 + 10 + 10 + 20 + 10 = 70
// Đáp án:

// 70
// "Formed Pairs" nghĩa là gì?
// "Formed Pairs" chỉ đơn giản là tạo tất cả các cặp có thể từ mảng.
// Ví dụ:

// A = [2, 3, 5]
// Các cặp là

// (2,3)
// (2,5)
// (3,5)
// Không có

// (3,2)
// (5,2)
// vì hai cặp này giống nhau.
// Input thường có dạng
// T
// N
// A1 A2 ... AN
// ...
// Trong đó:

// T: số test.
// Mỗi test gồm:
// N: số lượng phần tử.
// N số nguyên của mảng.
// Output
// Với mỗi test, in ra:

// Tổng gcd của mọi cặp.
// Ví dụ đầy đủ
// Input

// 1
// 4
// 10 20 30 40
// Output

// 70
// Nếu bạn đang nói đến bài UVA 11426 - GCD Extreme (II) hoặc LightOJ 1159 / SPOJ GCDEX, hoặc bài "Sum of GCD of Formed Pairs" trên một nền tảng cụ thể, hãy gửi link hoặc toàn bộ đề bài, mình sẽ giải thích từng câu và hướng dẫn ý tưởng giải chi tiết.


// Đầu tiên mình phải lưu ý rằng đoạn code này không phải là thuật toán đúng cho bài "Sum of GCD of Formed Pairs".
// Lý do là bài yêu cầu tính:
// [
// \sum_{i<j}\gcd(a_i,a_j)
// ]
// tức là tính GCD của mọi cặp phần tử.
// Trong khi code của bạn lại:

// Tạo mảng prefi.
// prefi[i] = gcd(max từ đầu đến i, arr[i]).
// Sắp xếp prefi.
// Ghép phần tử nhỏ nhất với lớn nhất rồi cộng GCD.
// Thuật toán này không xét tất cả các cặp, nên không cho đáp án đúng của bài toán.
// Giải thích từng phần của code
// import java.util.*;

// class Solution {

//     // Hàm chính
//     public long gcdSum(int[] arr) {

//         // Mảng lưu giá trị gcd(max từ đầu tới i, arr[i])
//         int[] prefi = new int[arr.length];

//         // Giá trị lớn nhất đã gặp
//         int mx = 0;

//         // Duyệt mảng
//         for (int i = 0; i < arr.length; i++) {

//             // Cập nhật giá trị lớn nhất
//             if (arr[i] > mx) {
//                 mx = arr[i];
//             }

//             // Lưu gcd(max hiện tại, arr[i])
//             prefi[i] = gcd(mx, arr[i]);
//         }

//         // Sắp xếp mảng prefi tăng dần
//         Arrays.sort(prefi);

//         // Hai con trỏ
//         int i = 0;
//         int j = arr.length - 1;

//         long sum = 0;

//         // Ghép số nhỏ nhất với lớn nhất
//         while (i < j) {

//             // Cộng gcd của hai đầu
//             sum += gcd(prefi[i], prefi[j]);

//             i++;
//             j--;
//         }

//         return sum;
//     }

//     // Thuật toán Euclid tìm gcd
//     public int gcd(int a, int b) {

//         while (b != 0) {

//             int temp = b;
//             b = a % b;
//             a = temp;
//         }

//         return a;
//     }

// }
// Ví dụ chạy
// Giả sử

// arr = [6, 9, 15, 12]
// Bước 1
// mx = 6

// prefi[0] = gcd(6,6)=6
// prefi = [6]
// Bước 2
// mx = 9

// prefi[1] = gcd(9,9)=9
// prefi = [6,9]
// Bước 3
// mx = 15

// prefi[2] = gcd(15,15)=15
// prefi=[6,9,15]
// Bước 4
// mx vẫn là 15

// prefi[3]=gcd(15,12)=3
// prefi=[6,9,15,3]
// Sắp xếp
// prefi=[3,6,9,15]
// Hai con trỏ
// gcd(3,15)=3

// gcd(6,9)=3
// Tổng

// 6
// Hàm main dùng Scanner
import java.util.*;

public class b274 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] arr = new int[n];

        // nhập mảng
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        

        System.out.println(gcdSum(arr));

        sc.close();
    }
 public static long gcdSum(int[] arr) {

        int[] prefi = new int[arr.length];
        int mx = 0;

        for (int i = 0; i < arr.length; i++) {

            // cập nhật số lớn nhất từ đầu tới vị trí i
            if (arr[i] > mx) {
                mx = arr[i];
            }

            // lưu gcd(max hiện tại, arr[i])
            prefi[i] = gcd(mx, arr[i]);
        }

        // sắp xếp
        Arrays.sort(prefi);

        int i = 0;
        int j = arr.length - 1;

        long sum = 0;

        // ghép hai đầu
        while (i < j) {
            sum += gcd(prefi[i], prefi[j]);
            i++;
            j--;
        }

        return sum;
    }

    // thuật toán Euclid
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}

   
// Độ phức tạp
// Duyệt mảng: O(n)
// Sắp xếp: O(n log n)
// Hai con trỏ: O(n)
// Hàm gcd(): O(log(max(arr)))
// Tổng:
// [
// \boxed{O(n\log n)}
// ]
// Lưu ý quan trọng: nếu đây là bài Sum of GCD of Formed Pairs trên GeeksforGeeks thì đoạn code này không giải đúng bài. Thuật toán đúng phải xét (hoặc đếm gián tiếp) GCD của tất cả (\frac{n(n-1)}{2}) cặp, không phải tạo mảng prefi rồi ghép hai đầu.