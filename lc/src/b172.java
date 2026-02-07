// // // # Minimum Deletions to Make String Balanced(07/02/2026)
// // Ok 👍 mình sẽ **giải thích thuật toán trực tiếp từ code Java của bạn** theo cách dễ hiểu nhất (từng dòng + bản chất greedy).
// Ok 👍 mình sẽ:

// * Thêm **hàm `main` dùng `Scanner`**
// * Giữ nguyên thuật toán của bạn
// * Thêm **chú thích chi tiết từng dòng** để bạn học dễ hơn

// ---

// # 📜 Code hoàn chỉnh (có `main` + chú thích)

// ```java
import java.util.*;

public class b172{
     static Scanner sc = new Scanner(System.in);
   public static void main(String[] args) {
     String s = sc.nextLine();

    

        // gọi hàm tính kết quả
        int result = minimumDeletions(s);

        System.out.println(result);

        sc.close();
    }
     // Hàm tính số lần xóa tối thiểu
    public static int minimumDeletions(String s) {

        int ans = 0;     // lưu số lần xóa tối thiểu
        int count = 0;   // đếm số lượng 'b' đã xuất hiện bên trái

        // duyệt từng ký tự trong chuỗi
        for(char ch : s.toCharArray()) {

            // TRƯỜNG HỢP 1: gặp ký tự 'b'
            if(ch == 'b') {

                // tăng số lượng 'b' bên trái
                // vì b đứng trước hiện tại
                count += 1;
            } 

            // TRƯỜNG HỢP 2: gặp ký tự 'a'
            // và trước đó đã có 'b'
            else if(count > 0) {

                // phát hiện vi phạm: "b đứng trước a"
                // cần xóa 1 ký tự

                ans += 1;      // tăng số lần xóa

                // giả sử ta xóa 1 'b' trước đó
                // nên giảm số lượng 'b'
                count -= 1;
            }
        }

        // trả về kết quả cuối cùng
        return ans;
    }
}



   


// ```

// ---

// # 🧠 Giải thích luồng chạy

// Ví dụ bạn nhập:

// ```
// aababbab
// ```

// Chương trình chạy:

// 1. Đọc chuỗi
// 2. Duyệt từng ký tự
// 3. Đếm số `'b'` bên trái
// 4. Khi gặp `'a'` sai thứ tự → tăng `ans`
// 5. In kết quả

// Output:

// ```
// So lan xoa toi thieu: 2
// ```

// ---

// # 📌 Tóm tắt biến

// | Biến    | Vai trò           |
// | ------- | ----------------- |
// | `count` | số `'b'` bên trái |
// | `ans`   | số lần xóa        |
// | `ch`    | ký tự đang xét    |

// ---

// # 🧭 Khi nào tăng / giảm?

// | Ký tự              | Hành động          |
// | ------------------ | ------------------ |
// | `'b'`              | `count++`          |
// | `'a'` + có b trước | `ans++`, `count--` |

// ---

// Nếu bạn muốn mình làm thêm:

// * Viết bản dùng `while` thay `for`
// * In bảng chạy từng bước
// * So sánh với DP
// * Thêm nhiều test input

// Cứ nói mình nhé 👍

// ---

// # 📌 Nhắc lại mục tiêu bài toán

// Ta cần xóa ít ký tự nhất để chuỗi **balanced**:

// ```
// aaaa....bbbb
// ```

// Không được có:

// ```
// b ... a
// ```

// ---

// # 🧠 Ý tưởng chính của code

// Duyệt từ trái → phải và xử lý vi phạm ngay khi thấy.

// Giữ 2 biến:

// | Biến    | Ý nghĩa                        |
// | ------- | ------------------------------ |
// | `count` | số lượng `'b'` đã gặp bên trái |
// | `ans`   | số lần xóa tối thiểu           |

// ---

// # 📜 Code + chú thích chi tiết

// ```java
// class Solution {
//     public int minimumDeletions(String s) {

//         int ans = 0;     // lưu số lần xóa tối thiểu
//         int count = 0;   // đếm số lượng 'b' đã gặp trước đó

//         // duyệt từng ký tự trong chuỗi
//         for(char ch: s.toCharArray()) {

//             // nếu gặp 'b'
//             if(ch == 'b') {

//                 // tăng số lượng 'b' bên trái
//                 count += 1;

//             } 
//             // nếu gặp 'a' và trước đó đã có 'b'
//             else if(count > 0) {

//                 // phát hiện vi phạm: "b đứng trước a"

//                 // tăng số lần xóa
//                 ans += 1;

//                 // giả sử ta xóa 1 'b' trước đó
//                 // nên giảm số lượng 'b'
//                 count -= 1;
//             }
//         }

//         // trả về kết quả
//         return ans;
//     }
// }
// ```

// ---

// # 🔍 Giải thích logic từng trường hợp

// ## 1️⃣ Khi gặp `'b'`

// Ví dụ:

// ```
// ... a a b
// ```

// Không vi phạm gì cả.

// → Chỉ cần ghi nhận đã có thêm 1 `'b'`.

// ```
// count++
// ```

// ---

// ## 2️⃣ Khi gặp `'a'`

// Xét 2 tình huống:

// ### ❌ Nếu trước đó có `'b'`

// ```
// ... b ... a
// ```

// → Sai thứ tự.

// Ta phải xóa 1 ký tự.

// ---

// ## ⚖️ Có 2 cách xóa

// | Cách                 | Hậu quả                |
// | -------------------- | ---------------------- |
// | Xóa `'a'` hiện tại   | mất 1 a                |
// | Xóa 1 `'b'` trước đó | giảm vi phạm tương lai |

// Greedy chọn:

// 👉 Xóa `'b'` trước đó tốt hơn.

// Vì giữ được `'a'` cho bên trái.

// ---

// ## Thể hiện trong code

// ```java
// ans += 1;   // xóa 1 ký tự
// count -= 1; // xóa 1 'b' trước đó
// ```

// ---

// # 🧮 Chạy thử ví dụ

// ```
// s = "aababbab"
// ```

// Bảng chạy:

// | Ký tự | count | ans | Giải thích |
// | ----- | ----- | --- | ---------- |
// | a     | 0     | 0   | OK         |
// | a     | 0     | 0   | OK         |
// | b     | 1     | 0   | gặp b      |
// | a     | 0     | 1   | xóa 1 b    |
// | b     | 1     | 1   | gặp b      |
// | b     | 2     | 1   | gặp b      |
// | a     | 1     | 2   | xóa 1 b    |
// | b     | 2     | 2   | gặp b      |

// 👉 Kết quả: `2`

// ---

// # 💡 Trực giác dễ nhớ

// Hãy tưởng tượng xếp hàng:

// * `'a'` phải đứng trước
// * `'b'` phải đứng sau

// Nếu thấy:

// ```
// b đứng trước a
// ```

// Ta đuổi bớt 1 thằng `'b'`.

// ---

// # 🧭 Vì sao Greedy đúng?

// Vì mỗi vi phạm:

// * Xóa `'a'` → không giảm vi phạm cũ
// * Xóa `'b'` → giảm vi phạm cũ + tương lai

// → Luôn tối ưu hơn hoặc bằng.

// ---

// # ⏱️ Độ phức tạp

// | Loại  | Giá trị |
// | ----- | ------- |
// | Time  | O(n)    |
// | Space | O(1)    |

// Duyệt 1 lần, không dùng mảng phụ.

// ---

// Nếu bạn muốn mình làm thêm:

// * So sánh với DP solution
// * Viết bảng prefix/suffix
// * Vẽ hình timeline a/b
// * Thêm `main()` + Scanner

// Cứ nói mình biết nhé 👍


// // # Ok 👍 mình sẽ **giải thích thuật toán + thêm chú thích trực tiếp vào code** để bạn hiểu từng dòng.

// // # ---

// // # # 🧠 Ý tưởng thuật toán

// // # Ta duyệt chuỗi từ trái → phải.

// // # Giữ 2 biến:

// // # * `count` = số lượng `'b'` đã gặp ở bên trái
// // # * `res` = số lần xóa tối thiểu

// // # ---

// // # ## ⚖️ Nguyên tắc xử lý

// // # Khi gặp:

// // # ### 1️⃣ Ký tự `'b'`

// // # → Không sai thứ tự (vì b đứng trước b/a sau này chưa biết)

// // # 👉 Tăng `count`

// // # ---

// // # ### 2️⃣ Ký tự `'a'`

// // # Nếu bên trái **đã có `'b'`** (`count > 0`)
// // # → Sai thứ tự vì:

// // # ```
// // # ... b ... a
// // # ```

// // # Ta có 2 lựa chọn:

// // # | Cách | Xóa gì               |
// // # | ---- | -------------------- |
// // # | 1    | Xóa `'a'` hiện tại   |
// // # | 2    | Xóa 1 `'b'` trước đó |

// // # Thuật toán greedy chọn:

// // # 👉 Xóa `'b'` trước đó (giảm `count`)
// // # Và tăng số lần xóa `res`

// // # Vì làm vậy giữ được nhiều `'a'` phía sau hơn.

// // # ---

// // # # 📜 Code có chú thích

// // # ```python
// // class Solution:
// //     def minimumDeletions(self, s: str) -> int:
// //         res = 0      # số lần xóa tối thiểu
// //         count = 0    # số lượng 'b' đã gặp bên trái

// //         # duyệt từng ký tự trong chuỗi
// //         for ch in s:

// //             # nếu là 'b'
// //             if ch == 'b':
// //                 # tăng số lượng 'b' bên trái
// //                 count += 1

// //             # nếu là 'a'
// //             # và trước đó đã có 'b'
// //             elif count:
// //                 # cần xử lý vi phạm "b đứng trước a"

// //                 # tăng số lần xóa
// //                 res += 1

// //                 # giả sử ta xóa 1 'b' trước đó
// //                 # nên giảm count
// //                 count -= 1
        
// //         # trả về kết quả
// //         return res


// // # # 🔍 Chạy thử từng bước

// // # Ví dụ:

// // # ```
// // # s = "aababbab"
// // # ```

// // # Ta lập bảng:

// // # | Ký tự | count(b bên trái) | res | Giải thích    |
// // # | ----- | ----------------- | --- | ------------- |
// // # | a     | 0                 | 0   | OK            |
// // # | a     | 0                 | 0   | OK            |
// // # | b     | 1                 | 0   | gặp b         |
// // # | a     | 0                 | 1   | xóa 1 b trước |
// // # | b     | 1                 | 1   | gặp b         |
// // # | b     | 2                 | 1   | gặp b         |
// // # | a     | 1                 | 2   | xóa 1 b trước |
// // # | b     | 2                 | 2   | gặp b         |

// // # 👉 Kết quả = **2**

// // # ---

// // # # 💡 Vì sao giảm `count`?

// // # Đoạn này quan trọng nhất:

// // # ```python
// // # res += 1
// // # count -= 1
// // # ```

// // # Ý nghĩa:

// // # * Ta quyết định xóa 1 `'b'` trước đó
// // # * Nên số `'b'` còn lại giảm

// // # Nếu không giảm → sẽ đếm sai.

// // # ---

// // # # 🧭 Trực giác dễ hiểu

// // # Hãy tưởng tượng:

// // # * `'b'` = người đứng sai hàng
// // # * `'a'` = người đúng hàng

// // # Khi thấy `'a'` đứng sau `'b'`:

// // # * Hoặc đuổi `'a'`
// // # * Hoặc đuổi 1 `'b'` trước

// // # Thuật toán chọn cách tối ưu hơn.

// // # ---

// // # ⏱️ Độ phức tạp

// // # * Time: **O(n)** (duyệt 1 lần)
// // # * Space: **O(1)** (chỉ 2 biến)

// // # → Rất tối ưu.

// // # ---

// // # Nếu bạn muốn mình giải thích thêm:

// // # * So sánh với DP solution
// // # * Vẽ hình minh họa trực quan
// // # * Viết bản Java
// // # * Giải thích vì sao greedy đúng

// // # Cứ nói mình nhé 👍





// // ## 📖 Đề bài yêu cầu gì?

// // # Bạn được cho một chuỗi `s` chỉ gồm **2 ký tự**:

// // # * `'a'`
// // # * `'b'`

// // # Bạn được phép **xóa (delete)** bất kỳ ký tự nào trong chuỗi.

// // # 👉 Mục tiêu:
// // # Làm sao để sau khi xóa ít ký tự nhất, chuỗi trở thành **balanced**.

// // # ---

// // # ## 🔎 Balanced là gì?

// // # Chuỗi được gọi là **balanced** nếu:

// // # > Không có ký tự `'b'` nào đứng **trước** ký tự `'a'`.

// // # Nói cách khác:

// // # * Tất cả `'a'` phải đứng **bên trái**
// // # * Tất cả `'b'` phải đứng **bên phải**

// // # Dạng chuẩn của chuỗi balanced:

// // # ```
// // # aaaa....bbbb
// // # ```

// // # Cho phép:

// // # * Chỉ có `a` → OK
// // # * Chỉ có `b` → OK
// // # * Rỗng → OK

// // # ---

// // # ## 🧠 Ví dụ minh họa

// // # ### Ví dụ 1

// // # ```
// // # Input:  s = "aababbab"
// // # ```

// // # Chuỗi này chưa balanced vì có `'b'` đứng trước `'a'`.

// // # Ta có thể xóa:

// // # * Xóa 1 số `'a'` phía sau
// // # * Hoặc xóa 1 số `'b'` phía trước

// // # Kết quả tối ưu:

// // # ```
// // # "aabbbb"   (xóa 2 ký tự)
// // # ```

// // # 👉 Output: `2`

// // # ---

// // # ### Ví dụ 2

// // # ```
// // # Input: "bbaaaaabb"
// // # ```

// // # Có nhiều `'b'` ở đầu → vi phạm.

// // # Cách tốt nhất:

// // # * Xóa 2 ký tự `'b'` đầu

// // # Chuỗi thành:

// // # ```
// // # "aaaaabb"
// // # ```

// // # 👉 Output: `2`

// // # ---

// // # ## 💡 Hiểu bản chất bài toán

// // # Ta cần chọn **1 điểm chia** chuỗi thành 2 phần:

// // # ```
// // # [ bên trái ] | [ bên phải ]
// // # ```

// // # Điều kiện balanced:

// // # * Bên trái: chỉ nên có `'a'`
// // # * Bên phải: chỉ nên có `'b'`

// // # Những ký tự sai vị trí phải xóa:

// // # * `'b'` ở bên trái → xóa
// // # * `'a'` ở bên phải → xóa

// // # ---

// // # ## 🧮 Minh họa cách tính

// // # Ví dụ:

// // # ```
// // # s = "aababbab"
// // #         ^
// // #       điểm chia
// // # ```

// // # Ta đếm:

// // # * Số `'b'` bên trái
// // # * Số `'a'` bên phải

// // # Tổng = số ký tự cần xóa nếu chia tại đó.

// // # Làm với mọi điểm chia → lấy nhỏ nhất.

// // # ---

// // # ## 📌 Tóm tắt đề bài

// // # * Input: Chuỗi chỉ gồm `'a'` và `'b'`
// // # * Được phép xóa ký tự
// // # * Mục tiêu: Chuỗi không có `'b'` đứng trước `'a'`
// // # * Output: Số lần xóa ít nhất

// // # ---

// // # ## 🧭 Nhận diện nhanh khi làm bài

// // # Dấu hiệu nhận ra dạng này:

// // # * Chuỗi chỉ có 2 loại ký tự
// // # * Có thứ tự đúng/sai
// // # * Cho phép **delete**
// // # * Tối thiểu hóa số lần xóa

// // # → Thường dùng:

// // # * Prefix count
// // # * Dynamic Programming
// // # * Greedy

// // # ---

// // # Nếu bạn muốn, mình có thể:

// // # * Giải thích thuật toán O(n) dễ hiểu
// // # * Viết code Java / Python
// // # * Vẽ bảng chạy tay từng bước

// // # Bạn muốn mình giải thích theo cách nào tiếp theo?
