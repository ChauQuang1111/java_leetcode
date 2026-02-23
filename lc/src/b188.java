// // ### 📘 Check If a String Contains All Binary Codes of Size K(23/02/2026)

// Ok mình sẽ:

// 1.✅Giải thích thuật toán từng bước 2.✅Thêm chú thích trực tiếp vào code 3.✅Viết thêm hàm`main`dùng`Scanner`4.✅Giải thích vì sao dùng bitmask lại tối ưu

// ---

// #🔥Ý tưởng thuật toán(Bitmask+Sliding Window)

// Thay vì dùng`substring`(tốn O(k)mỗi lần),ta:

// *Dùng**số nguyên`hash`**để biểu diễn chuỗi nhị phân độ dài k*Dùng**bit manipulation**để cập nhật cửa sổ trượt*Mỗi substring độ dài k sẽ tương ứng với 1 số từ`0→2^k-1`

// ---

// ##🎯Ý nghĩa các biến quan trọng

// |Biến|Ý nghĩa||--------------------|------------------------------------||`need=1<<k`|Tổng số chuỗi cần có=2^k||`allOnes=need-1`|Bitmask giữ lại k bit cuối||`hash`|Giá trị nhị phân hiện tại của cửa sổ||`seen[]`|Đánh dấu đã gặp substring nào||`count`|Đếm số chuỗi khác nhau đã gặp|

// ---

// #💡Giải thích từng dòng quan trọng

// ###1 ️⃣`1<<k`

// Ví dụ k=3

// ```1<<3=1000(nhị phân)=8```

// →Có 8 chuỗi nhị phân độ dài 3

// ---

// ###2 ️⃣Cách cập nhật hash

// ```java hash=((hash<<1)&allOnes)|(s.charAt(i)-'0');```

// Giả sử k=3

// Window đang là:`101`

// ```hash=101(5)```

// Thêm bit mới=1

// B1:shift trái

// ```101<<1=1010```

// B2:&allOnes(111)để giữ lại 3 bit cuối

// ```1010&111=010```

// B3:OR bit mới

// ```010|1=011```

// →window mới=011

// 👉Không cần substring→cực nhanh O(1)

// ---

// #✅Code có chú thích+main dùng Scanner

// ```java

import java.util.*;

public class b188 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.nextLine();

        // Nhập k
        int k = sc.nextInt();

        boolean result = hasAllCodes(s, k);

        System.out.println(result);

        sc.close();
    }

    // Hàm kiểm tra có đủ tất cả chuỗi nhị phân độ dài k không
    public static boolean hasAllCodes(String s, int k) {
        // need = 2^k (tổng số chuỗi nhị phân độ dài k)
        int need = 1 << k;

        int n = s.length();

        // Nếu chuỗi quá ngắn thì chắc chắn false
        // n-k+1 là số substring độ dài k có thể tạo
        if (n < k || n - k + 1 < need)
            return false;

        // Mảng đánh dấu đã gặp chuỗi nào chưa
        boolean[] seen = new boolean[need];

        int hash = 0; // giá trị nhị phân hiện tại của cửa sổ
        int allOnes = need - 1; // bitmask giữ lại k bit cuối
        int count = 0; // đếm số chuỗi khác nhau đã thấy

        for (int i = 0; i < n; i++) {

            // Dịch trái 1 bit để thêm bit mới
            // & allOnes để chỉ giữ lại k bit cuối
            // OR bit hiện tại (0 hoặc 1)
            hash = ((hash << 1) & allOnes) | (s.charAt(i) - '0');

            // Khi đã đủ k ký tự đầu tiên
            if (i >= k - 1) {

                // Nếu chuỗi này chưa từng gặp
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;

                    // Nếu đã đủ 2^k chuỗi
                    if (count == need)
                        return true;
                }
            }
        }

        return false;
    }

}

// O(n)
// ```

// Mỗi ký tự xử lý 1 lần

// ### Space Complexity:

// ```
// O(2^k)
// ```

// Dùng mảng boolean

// ---

// # 🧠 Vì sao cách này mạnh hơn substring?

// | Cách | Độ phức tạp |
// | --------- | ----------- |
// | substring | O(n * k) |
// | bitmask | O(n) |

// Khi k lớn → substring sẽ chậm rõ rệt

// ---

// Nếu bạn muốn mình:

// * Vẽ sơ đồ bit shifting cho bạn dễ hình dung hơn
// * Viết lại theo style bạn thích dùng while thay for
// * Giải thích lại phần `& allOnes` theo cách trực quan hơn

// Nói mình biết nhé 🔥

// (LeetCode 1461)

// ---

// ## 🧠 Đề bài nói gì?

// Bạn được cho:

// * Một **chuỗi nhị phân** `s` (chỉ gồm `'0'` và `'1'`)
// * Một số nguyên `k`

// 👉 Nhiệm vụ:
// Kiểm tra xem **tất cả các chuỗi nhị phân có độ dài k** có xuất hiện trong `s`
// hay không.

// Nếu **có đủ tất cả** → trả về `true`
// Nếu **thiếu ít nhất một chuỗi** → trả về `false`

// ---

// ## 🔍 Ví dụ 1

// ```
// Input: s = "00110110", k = 2
// Output: true
// ```

// ### Vì sao?

// Với `k = 2`, tất cả chuỗi nhị phân độ dài 2 là:

// ```
// 00
// 01
// 10
// 11
// ```

// Bây giờ kiểm tra trong `s = "00110110"`:

// | Vị trí | Substring độ dài 2 |
// | ------ | ------------------ |
// | 0-1 | 00 |
// | 1-2 | 01 |
// | 2-3 | 11 |
// | 3-4 | 10 |
// | 4-5 | 01 |
// | 5-6 | 11 |
// | 6-7 | 10 |

// ✅ Ta thấy đủ: `00, 01, 10, 11`
// → Trả về **true**

// ---

// ## 🔍 Ví dụ 2

// ```
// Input: s = "0110", k = 2
// Output: false
// ```

// Các chuỗi cần có vẫn là:

// ```
// 00
// 01
// 10
// 11
// ```

// Substring trong `"0110"`:

// ```
// 01
// 11
// 10
// ```

// ❌ Thiếu `00`
// → Trả về **false**

// ---

// ## 🎯 Ý nghĩa bài toán

// Với độ dài `k`, sẽ có:

// [
// 2^k
// ]

// chuỗi nhị phân khác nhau.

// Ví dụ:

// * k = 1 → 2 chuỗi (0,1)
// * k = 2 → 4 chuỗi
// * k = 3 → 8 chuỗi
// * k = 4 → 16 chuỗi

// 👉 Bạn cần kiểm tra xem chuỗi `s` có chứa đủ **2^k** substring khác nhau hay
// không.

// ---

// ## ⚠ Điều quan trọng cần hiểu

// Nếu:

// ```
// s.length() < k
// ```

// → chắc chắn trả về `false`
// (vì không thể tạo substring độ dài k)

// ---

// ## 💡 Tư duy đơn giản

// 1. Duyệt từ i = 0 đến s.length - k
// 2. Lấy substring độ dài k
// 3. Lưu vào HashSet
// 4. Nếu size của HashSet == 2^k → true
// 5. Ngược lại → false

// ---

// ## 🧠 Tóm lại

// Bài này kiểm tra:

// > Chuỗi s có chứa đủ tất cả các chuỗi nhị phân độ dài k hay không?

// Nó thuộc dạng:

// * Sliding window
// * HashSet
// * Bit manipulation (cách tối ưu hơn)

// ---

// Nếu bạn muốn mình giải thích thêm:

// * 🚀 Cách tối ưu O(n)
// * 🚀 Cách dùng bitmask thay vì substring
// * 🚀 Code Java (theo style bạn thích dùng Scanner + while)

// Bạn muốn mình đi theo hướng nào?
