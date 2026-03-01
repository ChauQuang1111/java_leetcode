// // // ## 📌 **Partitioning Into Minimum Number Of Deci-Binary Numbers(01/03/2026)

import java.util.*;

public class b203 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String n = sc.nextLine();

        // Gọi hàm xử lý
        int result = minPartitions(n);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    // Hàm tìm số lượng deci-binary tối thiểu
    public static int minPartitions(String n) {

        // Duyệt từ 9 xuống 1
        for (int i = 9; i > 0; i--) {

            // Kiểm tra xem chuỗi n có chứa chữ số i không
            // i + "" để chuyển int thành String
            if (n.contains(i + "")) {

                // Nếu tìm thấy, trả về ngay vì đây là chữ số lớn nhất
                return i;
            }
        }

        // Nếu toàn số 0 thì trả về 0
        return 0;
    }
}

// // ##📌Giải thích thuật toán–Partitioning Into Minimum Number Of Deci-Binary
// Numbers

// // Bài này yêu cầu:

// // >Tìm**số lượng nhỏ nhất các deci-binary numbers**(chỉ gồm 0 và 1)sao cho
// tổng của chúng bằng số`n`.

// // ---

// // ##🎯Ý tưởng cốt lõi

// // *Mỗi deci-binary chỉ có thể đóng góp**0 hoặc 1**ở mỗi vị trí chữ số.*Nếu
// tại một vị trí có chữ số là**7**,thì:

// // *Ta cần ít nhất**7 số deci-binary**để cộng lại thành 7.*Vì vậy:

// // 👉**Đáp án=chữ số lớn nhất trong chuỗi n**

// // ---

// // ##🔎Giải thích thuật toán bạn viết

// // ```java

// // class Solution {
// // public int minPartitions(String n) {
// // for (int i = 9; i > 0; i--) {
// // if (n.contains(i + ""))
// // return i;
// // }
// // return 0;
// // }
// // }```

// // ###🧠

// // Cách hoạt động:

// // 1.
// // Vòng lặp
// // chạy từ`9→1`2.
// // Mỗi lần
// // kiểm tra:

// // ```java
// // n.contains(i+"")```

// // Nghĩa là:chuỗi`n`
// // có chứa
// // chữ số`i`không?3.
// // Nếu có→
// // trả về ngay`i`4.
// // Vì duyệt từ 9
// // xuống nên
// // chữ số
// // đầu tiên
// // tìm thấy
// // chính là**
// // chữ số
// // lớn nhất**5.
// // Nếu không
// // có số
// // nào từ 1→9→
// // trả về 0

// // ---

// // ##⚡
// // Ví dụ

// // Input:

// // ```82734```

// // Chạy vòng lặp:

// // *i=9→
// // không có*i=8→có✅→return 8

// // →
// // Đáp án = 8

// // ---

// // # ✅
// // Viết lại
// // đầy đủ có`main`+`Scanner`+
// // chú thích
// // chi tiết

// // ```
// // java

// import java.util.*;

// class Solution {

// // Hàm tìm số lượng deci-binary tối thiểu
// public int minPartitions(String n) {

// // Duyệt từ 9 xuống 1
// for (int i = 9; i > 0; i--) {

// // Kiểm tra xem chuỗi n có chứa chữ số i không
// // i + "" để chuyển int thành String
// if (n.contains(i + "")) {

// // Nếu tìm thấy, trả về ngay vì đây là chữ số lớn nhất
// return i;
// }
// }

// // Nếu toàn số 0 thì trả về 0
// return 0;
// }
// }

// public class Main {
// public static void main(String[] args) {

// Scanner sc = new Scanner(System.in);

// // Nhập chuỗi số n
// System.out.print("Nhap n: ");
// String n = sc.nextLine();

// // Tạo đối tượng Solution
// Solution sol = new Solution();

// // Gọi hàm xử lý
// int result = sol.minPartitions(n);

// // In kết quả
// System.out.println("So luong deci-binary toi thieu: " + result);

// sc.close();
// }}

// // #🚀

// // Cách tối

// // ưu hơn (O(n) thay vì contains nhiều lần)

// // Cách trên mỗi lần `contains()` là duyệt lại chuỗi → hơi tốn thời gian.

// // Ta có thể làm nhanh hơn:

// // ```java

// public int minPartitions(String n) {

// int max = 0;

// // Duyệt từng ký tự
// for(int i = 0; i < n.length(); i++) {

// // Chuyển ký tự sang số
// int digit = n.charAt(i) - '0';

// // Cập nhật max
// if(digit > max) {
// max = digit;
// }
// }

// return max;
// }

// // ###

// // Vì sao
// // tốt hơn?

// // *
// // Chỉ duyệt chuỗi**1 lần***
// // Độ phức tạp:**O(n)**
// // * Không cần gọi `contains()` nhiều lần

// // ---

// // # 🎯 Tóm lại

// // Thuật toán rất đơn giản:

// // ```
// // Đáp án = chữ số lớn nhất trong n
// // ```

// // ---

// // Nếu bạn muốn mình phân tích độ phức tạp Big-O chi tiết theo kiểu thi thuật
// toán thì mình giải tiếp cho bạn nhé 💡

// // Đây là một bài trên LeetCode.

// // ---

// // ### 🔎 1️⃣ Deci-binary number là gì?

// // **Deci-binary number** là một số thập phân nhưng:

// // * Chỉ gồm **chữ số 0 và 1**
// // * Không có chữ số nào khác (không có 2,3,4,...9)

// // Ví dụ:

// // * ✅ 1
// // * ✅ 10
// // * ✅ 101
// // * ❌ 112 (vì có số 2)

// // ---

// // ### 🔎 2️⃣ Đề bài yêu cầu gì?/

// // Bạn được cho một **chuỗi số thập phân n** (rất dài).

// // 👉 Hãy tìm **số lượng nhỏ nhất các deci-binary numbers** sao cho tổng của
// chúng bằng đúng n.

// // ---

// // ### 🔎 3️⃣ Ví dụ

// // #### Ví dụ 1:

// // ```
// // Input: n = "32"
// // Output: 3
// // ```

// // Tại sao?

// // Ta có thể tách:

// // ```
// // 32 =
// // 11
// // 11
// // 10
// // ```

// // Cộng lại:

// // ```
// // 11
// // 11
// // 10
// // ---
// // 32
// // ```

// // → Tổng cộng dùng **3 số deci-binary**
// // → Không thể ít hơn 3

// // ---

// // #### Ví dụ 2:

// // ```
// // Input: n = "82734"
// // Output: 8
// // ```

// // Vì trong số này có chữ số lớn nhất là **8**
// // → Bạn cần ít nhất 8 số deci-binary để tạo ra chữ số 8 đó.

// // ---

// // ### 🔥 Ý tưởng quan trọng nhất của bài

// // 💡 **Đáp án chính là chữ số lớn nhất trong n**

// // Vì:

// // * Mỗi deci-binary chỉ đóng góp tối đa **1** cho mỗi hàng (vì chỉ có 0 hoặc
// 1)
// // * Nếu một vị trí có số 7 → bạn cần ít nhất 7 số deci-binary để cộng lại
// thành 7

// // ---

// // ### 🧠 Kết luận

// // Bài này thực chất rất đơn giản:

// // 👉 **Tìm chữ số lớn nhất trong chuỗi n**

// // Ví dụ:

// // * "32" → max digit = 3 → đáp án = 3
// // * "82734" → max digit = 8 → đáp án = 8
// // * "1111" → max digit = 1 → đáp án = 1

// // ---

// // Nếu bạn muốn mình giải thích tại sao tư duy này đúng theo kiểu toán học
// logic từng bước, mình có thể phân tích sâu hơn nhé 🚀
