// Bài **Fancy Sequence (15/03/2026)

import java.util.*;
import java.util.stream.IntStream;

public class b217 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Fancy fancy = new Fancy();

        int q = sc.nextInt(); // số operations

        while (q-- > 0) {
            String op = sc.next();

            if (op.equals("append")) {
                int val = sc.nextInt();
                fancy.append(val);
            } else if (op.equals("addAll")) {
                int inc = sc.nextInt();
                fancy.addAll(inc);
            } else if (op.equals("multAll")) {
                int m = sc.nextInt();
                fancy.multAll(m);
            } else if (op.equals("getIndex")) {
                int idx = sc.nextInt();
                System.out.println(fancy.getIndex(idx));
            }
        }

        sc.close();
    }

    static class Fancy {
        public static final int MOD = 1000000007;

        // cache inverse values for 0-100
        // lưu nghịch đảo modulo của các số từ 0 -> 100
        public static final int[] INV = IntStream.range(0, 101)
                .map(Fancy::modInverse).toArray();

        // hàm tính modular inverse
        // tìm x sao cho (a * x) % MOD = 1
        public static int modInverse(int a) {
            int m = MOD, y = 0, x = 1;

            while (a > 1) {
                int q = a / m;
                int t = m;

                m = a % m;
                a = t;

                t = y;

                y = x - q * y;
                x = t;
            }

            if (x < 0)
                x += MOD;

            return x;
        }

        public long mul = 1; // hệ số nhân toàn cục
        public long add = 0; // hệ số cộng toàn cục

        public long rmul = 1; // nghịch đảo của mul

        // list lưu giá trị base (đã reverse transform)
        public final List<Integer> list = new ArrayList<>();

        // thêm phần tử vào cuối
        public void append(int val) {
            // reverse phép biến đổi:
            // stored = (val - add) * inverse(mul)

            long base = ((MOD - add + val) % MOD * rmul) % MOD;

            list.add((int) base);
        }

        // cộng toàn bộ dãy
        public void addAll(int inc) {
            add = (add + inc) % MOD;
        }

        // nhân toàn bộ dãy
        public void multAll(int m) {
            mul = (mul * m) % MOD;

            // cập nhật nghịch đảo
            rmul = (rmul * INV[m]) % MOD;

            add = (add * m) % MOD;
        }

        // lấy giá trị tại index
        public int getIndex(int idx) {
            if (idx >= list.size())
                return -1;

            long val = ((list.get(idx) * mul) % MOD + add) % MOD;

            return (int) val;
        }
    }

}

// Mình sẽ giải thích **ý tưởng thuật toán trước**, sau đó đưa **code hoàn chỉnh
// có `main` dùng `Scanner` và chú thích chi tiết**.

// ---

// # 1. Ý tưởng chính của thuật toán

// Thay vì **cập nhật toàn bộ mảng mỗi lần `addAll` hoặc `multAll`**, ta dùng
// **biến toàn cục để ghi nhớ phép biến đổi**.

// Giả sử mỗi phần tử thực tế trong dãy có dạng:

// ```
// real_value = stored_value * mul + add
// ```

// Trong đó:

// ```
// mul = hệ số nhân toàn cục
// add = hệ số cộng toàn cục
// ```

// Ví dụ:

// ```
// append(2)
// addAll(3)
// multAll(2)
// ```

// Giá trị thật:

// ```
// (2 + 3) * 2 = 10
// ```

// Nhưng trong list ta chỉ lưu **giá trị đã được "đảo ngược biến đổi"**.

// ---

// # 2. Vấn đề khi append

// Khi append một số mới, ta cần **tìm stored_value sao cho:**

// ```
// stored_value * mul + add = val
// ```

// =>

// ```
// stored_value = (val - add) / mul
// ```

// Nhưng vì bài này dùng **mod 1e9+7**, nên ta phải dùng **modular inverse** để
// chia.

// ```
// stored_value = (val - add) * inverse(mul)
// ```

// Trong code:

// ```
// rmul = inverse(mul)
// ```

// ---

// # 3. Ý nghĩa các biến

// ```
// mul = tổng hệ số nhân
// add = tổng hệ số cộng
// rmul = nghịch đảo của mul
// ```

// Danh sách `list` lưu:

// ```
// stored_value
// ```

// Khi lấy giá trị:

// ```
// real_value = stored_value * mul + add
// ```

// ---

// # 4. Độ phức tạp

// | Operation | Time |
// | --------- | ---- |
// | append | O(1) |
// | addAll | O(1) |
// | multAll | O(1) |
// | getIndex | O(1) |

// ---

// # 5. Code hoàn chỉnh (có main + Scanner + chú thích)

// ```java
// import java.util.*;
// import java.util.stream.IntStream;

// class Fancy
// {
// private static final int MOD = 1000000007;

// // cache inverse values for 0-100
// // lưu nghịch đảo modulo của các số từ 0 -> 100
// private static final int[] INV = IntStream.range(0, 101)
// .map(Fancy::modInverse).toArray();

// // hàm tính modular inverse
// // tìm x sao cho (a * x) % MOD = 1
// private static int modInverse(int a)
// {
// int m = MOD, y = 0, x = 1;

// while (a > 1)
// {
// int q = a / m;
// int t = m;

// m = a % m;
// a = t;

// t = y;

// y = x - q * y;
// x = t;
// }

// if (x < 0)
// x += MOD;

// return x;
// }

// private long mul = 1; // hệ số nhân toàn cục
// private long add = 0; // hệ số cộng toàn cục

// private long rmul = 1; // nghịch đảo của mul

// // list lưu giá trị base (đã reverse transform)
// private final List<Integer> list = new ArrayList<>();

// // thêm phần tử vào cuối
// public void append(int val)
// {
// // reverse phép biến đổi:
// // stored = (val - add) * inverse(mul)

// long base = ((MOD - add + val) % MOD * rmul) % MOD;

// list.add((int) base);
// }

// // cộng toàn bộ dãy
// public void addAll(int inc)
// {
// add = (add + inc) % MOD;
// }

// // nhân toàn bộ dãy
// public void multAll(int m)
// {
// mul = (mul * m) % MOD;

// // cập nhật nghịch đảo
// rmul = (rmul * INV[m]) % MOD;

// add = (add * m) % MOD;
// }

// // lấy giá trị tại index
// public int getIndex(int idx)
// {
// if (idx >= list.size())
// return -1;

// long val = ((list.get(idx) * mul) % MOD + add) % MOD;

// return (int) val;
// }
// }

// public class Main
// {
// public static void main(String[] args)
// {
// Scanner sc = new Scanner(System.in);

// Fancy fancy = new Fancy();

// int q = sc.nextInt(); // số operations

// while (q-- > 0)
// {
// String op = sc.next();

// if (op.equals("append"))
// {
// int val = sc.nextInt();
// fancy.append(val);
// }
// else if (op.equals("addAll"))
// {
// int inc = sc.nextInt();
// fancy.addAll(inc);
// }
// else if (op.equals("multAll"))
// {
// int m = sc.nextInt();
// fancy.multAll(m);
// }
// else if (op.equals("getIndex"))
// {
// int idx = sc.nextInt();
// System.out.println(fancy.getIndex(idx));
// }
// }

// sc.close();
// }
// }
// ```

// ---

// # 6. Ví dụ input để test

// ```
// 7
// append 2
// addAll 3
// append 7
// multAll 2
// getIndex 0
// getIndex 1
// getIndex 2
// ```

// Output

// ```
// 10
// 14
// -1
// ```

// ---

// # 7. Trực giác cực quan trọng của bài

// Bài này thực chất dùng:

// ```
// Lazy transformation
// +
// Modular inverse
// ```

// Ta **không thay đổi từng phần tử**, mà chỉ **lưu công thức biến đổi toàn
// cục**.

// ---

// Nếu bạn muốn, mình có thể giải thích thêm **một cách cực trực quan (vì sao
// công thức `(val-add)*inverse(mul)` xuất hiện)** — hiểu xong bài này bạn sẽ
// **nhớ luôn trick này cho nhiều bài design structure khó hơn.**

// Mình sẽ **giải thích đề bài thật đơn giản**.

// ---

// # 1. Ý tưởng của đề bài

// Bạn có một **dãy số ban đầu rỗng**.

// Bạn phải xây dựng class **Fancy** với các thao tác sau:

// ### 1️⃣ append(val)

// Thêm số `val` vào **cuối dãy**.

// Ví dụ

// ```
// append(2)
// ```

// Dãy:

// ```
// [2]
// ```

// ---

// ### 2️⃣ addAll(inc)

// Cộng `inc` cho **tất cả phần tử trong dãy**.

// Ví dụ

// ```
// [2,5,7]
// addAll(3)
// ```

// Dãy mới:

// ```
// [5,8,10]
// ```

// ---

// ### 3️⃣ multAll(m)

// Nhân **tất cả phần tử** với `m`.

// Ví dụ

// ```
// [5,8,10]
// multAll(2)
// ```

// Dãy mới:

// ```
// [10,16,20]
// ```

// ---

// ### 4️⃣ getIndex(idx)

// Lấy **giá trị tại vị trí idx** (0-based).

// Nếu `idx` vượt quá độ dài dãy → trả `-1`.

// Ví dụ

// ```
// [10,16,20]

// getIndex(1)
// ```

// Kết quả

// ```
// 16
// ```

// ---

// # 2. Ví dụ trong đề

// Chuỗi thao tác:

// ```
// Fancy()
// append(2)
// addAll(3)
// append(7)
// multAll(2)
// getIndex(0)
// getIndex(1)
// getIndex(2)
// ```

// ### Bước 1

// ```
// append(2)
// ```

// ```
// [2]
// ```

// ---

// ### Bước 2

// ```
// addAll(3)
// ```

// ```
// [5]
// ```

// ---

// ### Bước 3

// ```
// append(7)
// ```

// ```
// [5,7]
// ```

// ---

// ### Bước 4

// ```
// multAll(2)
// ```

// ```
// [10,14]
// ```

// ---

// ### Query

// ```
// getIndex(0) → 10
// getIndex(1) → 14
// getIndex(2) → -1
// ```

// ---

// # 3. Điều khó của bài

// Nếu làm **naive**:

// ```
// addAll
// multAll
// ```

// → phải cập nhật **toàn bộ mảng**

// Giả sử:

// ```
// 100000 phần tử
// 100000 operations
// ```

// Thì:

// ```
// O(n²) → TLE
// ```

// Nên bài này yêu cầu:

// ```
// append
// addAll
// multAll
// getIndex
// ```

// đều phải gần **O(1)**. ([Walkccc][2])

// ---

// # 4. Bản chất toán học của bài

// Mọi số trong dãy luôn có dạng:

// ```
// value = a * x + b
// ```

// Trong đó:

// ```
// a = hệ số nhân toàn cục
// b = hệ số cộng toàn cục
// ```

// Khi:

// ```
// addAll(inc)
// ```

// →

// ```
// b = b + inc
// ```

// Khi:

// ```
// multAll(m)
// ```

// →

// ```
// a = a * m
// b = b * m
// ```

// ---

// # 5. Tóm lại đề bài yêu cầu

// Thiết kế một cấu trúc dữ liệu hỗ trợ:

// ```
// append(val)
// addAll(inc)
// multAll(m)
// getIndex(idx)
// ```

// với:

// ```
// n ≤ 10^5
// ```

// và phải chạy **rất nhanh**.

// ---

// ✅ Nếu bạn muốn, mình có thể giải thích tiếp:

// * **Ý tưởng lời giải chuẩn (O(1))**
// * **Cách suy nghĩ để ra công thức**
// * **Code Java / C++ / Python**
// * **Trực giác để hiểu bài này trong 3 phút**

// Bài này thực ra là **một trong những bài design + math trick rất nổi tiếng**.

// [1]:
// https://www.tutorialspoint.com/practice/fancy-sequence.htm?utm_source=chatgpt.com
// "Fancy Sequence"
// [2]: https://walkccc.me/LeetCode/problems/1622/?utm_source=chatgpt.com "1622.
// Fancy Sequence - LeetCode Solutions"
