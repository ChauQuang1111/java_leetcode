// Bài Jump Game VII(25/05/2026)
// Đề bài nói gì?

// Cho:
// Một chuỗi nhị phân s

// '0' = có thể đứng

// '1' = không thể đứng

// Hai số:

// minJump

// maxJump

// Ban đầu đứng ở index 0.

// Từ vị trí i, bạn được nhảy tới j nếu:

// [i + minJump \le j \le i + maxJump]

// và:



// s[j] == '0'

// Mục tiêu:



// Kiểm tra xem có đi tới index cuối cùng được không.

// Ví dụ 1

// s = "011010"

// minJump = 2

// maxJump = 3

// Đánh index:



// index: 0 1 2 3 4 5

// s:     0 1 1 0 1 0

// Bắt đầu ở 0.



// Từ index 0:

// Được nhảy từ:

// [

// 0 + 2 = 2

// ]

// đến

// [

// 0 + 3 = 3

// ]

// => Có thể xét index 2 và 3.



// index 2 = '1' ❌

// index 3 = '0' ✅

// => Nhảy tới 3.

// Từ index 3:

// Được nhảy từ:

// [

// 3 + 2 = 5

// ]

// đến

// [

// 3 + 3 = 6

// ]

// nhưng chuỗi chỉ tới index 5.

// => xét index 5:



// index 5 = '0'

// => tới đích.

// Kết quả:



// true

// Ví dụ 2

// s = "01101110"

// minJump = 2

// maxJump = 3

// index: 0 1 2 3 4 5 6 7

// s:     0 1 1 0 1 1 1 0

// Từ 0:



// có thể tới 2 hoặc 3

// 2 = 1

// 3 = 0

// => tới 3.

// Từ 3:



// có thể tới 5 hoặc 6

// cả hai đều là 1

// => bị kẹt.

// Không tới cuối được.

// Kết quả:



// false

// Ý tưởng bài này

// Đây là bài:



// BFS

// DP

// Sliding Window

// vì cần kiểm tra:



// “Từ vị trí hiện tại có thể đi tiếp tới đâu?”

// Điều quan trọng nhất

// Nếu đang ở vị trí i:

// Ta chỉ được nhảy trong đoạn:

// [

// [i + minJump,\ i + maxJump]

// ]

// và chỉ nhảy tới nơi có '0'.

// Constraint quan trọng

// s.length <= 10^5

// => Không thể brute force thử mọi đường đi kiểu DFS thường.

// Cần tối ưu xuống:



// O(n)

// Tư duy đơn giản nhất

// Tạo mảng:



// reachable[i]

// true nếu tới được index i

// false nếu không

// Ban đầu:



// reachable[0] = true

// Sau đó duyệt từng vị trí để xem có thể nhảy tiếp không. (neetcode.io)



// Code này dùng:

// * `dp[i] = true` nghĩa là có thể tới vị trí `i`
// * kết hợp kỹ thuật:

//   * Greedy
//   * Sliding Window
//   * BFS style

// để tránh duyệt lặp nhiều lần.

// ---

// # Ý tưởng chính

// Nếu đang đứng ở vị trí `i`:

// và:

// ```java
// dp[i] == true
// ```

// thì ta có thể nhảy tới:

// [
// [i + minJump,\ i + maxJump]
// ]

// Nếu vị trí nào là `'0'` thì đánh dấu:

// ```java
// dp[j] = true
// ```

// ---

// # Ý nghĩa các biến

// ```java
// int start = 0, end = 0;
// ```

// * `start` = điểm bắt đầu duyệt mới
// * `end` = điểm cuối đã duyệt trước đó

// Hai biến này giúp:

// ✅ tránh duyệt lại cùng một đoạn nhiều lần.

// ---

// # Phân tích từng phần

// ---

// ## 1. Kiểm tra điều kiện không hợp lệ

// ```java
// if(len == 0 || s.charAt(0) == '1' || s.charAt(len-1) == '1') {
//     return false;
// }
// ```

// ### Ý nghĩa

// * chuỗi rỗng → không đi được
// * vị trí đầu là `'1'` → không đứng được
// * vị trí cuối là `'1'` → không tới đích được

// ---

// # 2. Mảng DP

// ```java
// boolean[] dp = new boolean[len];
// dp[0] = true;
// ```

// ## Ý nghĩa

// ```java
// dp[i]
// ```

// * `true` → tới được index `i`
// * `false` → chưa tới được

// Ban đầu:

// ```java
// dp[0] = true;
// ```

// vì bắt đầu ở vị trí 0.

// ---

// # 3. Duyệt từng vị trí

// ```java
// for(int i = 0; i < len; i++)
// ```

// Ta kiểm tra mọi vị trí.

// ---

// ## 4. Nếu không tới được i thì bỏ qua

// ```java
// if(!dp[i]) {
//     continue;
// }
// ```

// Ví dụ:

// ```txt id="cf64v1"
// dp[5] = false
// ```

// => không thể đứng ở 5

// => không cần xét nhảy từ 5.

// ---

// # 5. Tính đoạn có thể nhảy

// ```java
// start = Math.max(end + 1, i + minJump);
// end = Math.min(len-1, i + maxJump);
// ```

// Đây là phần QUAN TRỌNG NHẤT.

// ---

// ## Bình thường

// Từ `i`, ta có thể nhảy:

// [
// [i + minJump,\ i + maxJump]
// ]

// ---

// ## Nhưng tại sao dùng:

// ```java
// Math.max(end + 1, ...)
// ```

// ?

// Vì:

// các đoạn trước đó có thể đã duyệt rồi.

// Ta không muốn duyệt lại.

// ---

// # Ví dụ

// Giả sử:

// ```txt id="j9oq0n"
// i = 0
// minJump = 2
// maxJump = 4
// ```

// Ta duyệt:

// ```txt id="8t15md"
// [2,4]
// ```

// Sau đó:

// ```txt id="g1r2ci"
// end = 4
// ```

// ---

// Tiếp theo:

// ```txt id="wwu1lv"
// i = 1
// ```

// Khoảng mới:

// ```txt id="vragqk"
// [3,5]
// ```

// Nhưng:

// ```txt id="xq4n1d"
// 3,4
// ```

// đã xét rồi.

// Nên chỉ cần xét:

// ```txt id="y5q9gb"
// 5
// ```

// Do đó:

// ```java
// start = Math.max(end + 1, i + minJump);
// ```

// => tránh lặp.

// ---

// # 6. Duyệt các vị trí có thể nhảy tới

// ```java
// for(int j = start; j <= end; j++)
// ```

// ---

// ## Nếu là `'0'`

// ```java
// if(s.charAt(j) == '0') {
//     dp[j] = true;
// }
// ```

// thì đánh dấu có thể tới được.

// ---

// # 7. Nếu đã tới cuối

// ```java
// if(dp[len-1]) {
//     return true;
// }
// ```

// => return sớm để tối ưu.

// ---

// # Ví dụ chạy thực tế

// ```txt id="ptgm17"
// s = "011010"
// minJump = 2
// maxJump = 3
// ```

// ---

// ## Ban đầu

// ```txt id="b84bkc"
// dp = [T,F,F,F,F,F]
// ```

// ---

// ## i = 0

// Có thể nhảy:

// ```txt id="jlwm6y"
// [2,3]
// ```

// * 2 = '1'
// * 3 = '0'

// =>:

// ```txt id="kl0nsp"
// dp[3] = true
// ```

// ---

// ## i = 1

// ```txt id="62te6g"
// dp[1] = false
// ```

// => bỏ qua.

// ---

// ## i = 3

// Có thể nhảy:

// ```txt id="jlwm6y"
// [5,5]
// ```

// * 5 = '0'

// =>:

// ```txt id="y6bfyu"
// dp[5] = true
// ```

// Đã tới cuối.

// Return `true`.

// ---

// # Tại sao thuật toán nhanh?

// Nếu không có:

// ```java
// start = Math.max(end + 1, ...)
// ```

// thì mỗi index sẽ duyệt lại rất nhiều lần.

// Độ phức tạp có thể:

// [
// O(n \times maxJump)
// ]

// ---

// Nhờ kỹ thuật này:

// mỗi vị trí chỉ được duyệt khoảng 1 lần.

// => tổng:

// [
// O(n)
// ]

// ---

// # Độ phức tạp

// ## Time

// ```txt id="p9g9fo"
// O(n)
// ```

// ---

// ## Space

// ```txt id="j6v6v7"
// O(n)
// ```

// do dùng mảng `dp`.



import java.util.*;

public class b244 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
String s = sc.nextLine();

        // Nhập minJump
        System.out.print("Nhap minJump: ");
        int minJump = sc.nextInt();

        // Nhập maxJump
        System.out.print("Nhap maxJump: ");
        int maxJump = sc.nextInt();

   

        // Gọi hàm canReach
        boolean result = canReach(s, minJump, maxJump);

        // In kết quả
        System.out.println(result);

        sc.close();
    }
// Hàm kiểm tra có thể tới cuối chuỗi hay không
    public static boolean canReach(String s, int minJump, int maxJump) {

        // start = vị trí bắt đầu duyệt mới
        // end = vị trí cuối đã duyệt
        int start = 0;
        int end = 0;

        int len = s.length();

        // Nếu:
        // - chuỗi rỗng
        // - vị trí đầu là '1'
        // - vị trí cuối là '1'
        // => không thể đi tới đích
        if (len == 0 || s.charAt(0) == '1' || s.charAt(len - 1) == '1') {
            return false;
        }

        // dp[i] = true
        // nghĩa là có thể tới vị trí i
        boolean[] dp = new boolean[len];

        // Ban đầu đứng ở index 0
        dp[0] = true;

        // Duyệt từng vị trí
        for (int i = 0; i < len; i++) {

            // Nếu không thể tới i
            // thì bỏ qua
            if (!dp[i]) {
                continue;
            }

            // Từ i có thể nhảy:
            // [i + minJump, i + maxJump]
            //
            // Nhưng dùng:
            // end + 1
            // để tránh duyệt lại
            start = Math.max(end + 1, i + minJump);

            // Không được vượt quá cuối chuỗi
            end = Math.min(len - 1, i + maxJump);

            // Duyệt các vị trí có thể nhảy tới
            for (int j = start; j <= end; j++) {

                // Nếu là '0'
                // => có thể đứng
                if (s.charAt(j) == '0') {
                    dp[j] = true;
                }
            }

            // Nếu đã tới cuối
            // return luôn để tối ưu
            if (dp[len - 1]) {
                return true;
            }
        }

        // Trả kết quả cuối cùng
        return dp[len - 1];
    }
}

