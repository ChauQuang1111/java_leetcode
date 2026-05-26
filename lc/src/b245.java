// // Bài **“Count the Number of Special Characters I(26/05/2026)
// // ---

// // ## Đề bài

// // Cho một chuỗi `word` gồm chữ cái tiếng Anh viết thường và viết hoa.

// // Một ký tự được gọi là **special** nếu:

// // * ký tự viết thường của nó xuất hiện trong chuỗi
// // * và ký tự viết hoa tương ứng cũng xuất hiện trong chuỗi.

// // Ví dụ:

// // * `'a'` và `'A'` cùng xuất hiện → `a` là special
// // * `'b'` có nhưng `'B'` không có → không special

// // Yêu cầu:
// // Trả về số lượng ký tự special.

// // ---

// // ## Ví dụ 1

// // Input:

// // ```text
// // word = "aaAbcBC"
// // ```

// // Phân tích:

// // * `a` và `A` đều có → special
// // * `b` và `B` đều có → special
// // * `c` và `C` đều có → special

// // Kết quả:

// // ```text
// // 3
// // ```

// // ---

// // ## Ví dụ 2

// // Input:

// // ```text
// // word = "abc"
// // ```

// // * chỉ có chữ thường
// // * không có chữ hoa tương ứng

// // Kết quả:

// // ```text
// // 0
// // ```

// // ---

// // ## Ví dụ 3

// // Input:

// // ```text
// // word = "abBCab"
// // ```

// // * `a` không có `A`
// // * `b` có `B` → special
// // * `c` có `C` không? không có

// // Kết quả:

// // ```text
// // 1
// // ```

// // ---

// // ## Ý tưởng

// // Ta cần kiểm tra với mỗi chữ cái:

// // * nếu có cả:

// //   * chữ thường
// //   * chữ hoa

// // thì tăng đáp án.

// // ---

// // ## Cách làm bằng set

// // ```python
// // class Solution:
// //     def numberOfSpecialChars(self, word: str) -> int:
// //         s = set(word)
// //         ans = 0

// //         for c in 'abcdefghijklmnopqrstuvwxyz':
// //             if c in s and c.upper() in s:
// //                 ans += 1

// //         return ans
// // ```

// // ---

// // ## Giải thích code

// // ### Bước 1

// // ```python
// // s = set(word)
// // ```

// // Lưu toàn bộ ký tự vào set để tìm nhanh.

// // Ví dụ:

// // ```python
// // word = "aaAbcBC"
// // ```

// // thì:

// // ```python
// // s = {'a', 'A', 'b', 'B', 'c', 'C'}
// // ```

// // ---

// // ### Bước 2

// // Duyệt từng chữ cái từ `a -> z`

// // ```python
// // for c in 'abcdefghijklmnopqrstuvwxyz':
// // ```

// // ---

// // ### Bước 3

// // Kiểm tra:

// // ```python
// // if c in s and c.upper() in s:
// // ```

// // Ví dụ:

// // ```python
// // c = 'b'
// // ```

// // * `'b' in s` → True
// // * `'B' in s` → True

// // => special.

// // ---

// // ## Độ phức tạp

// // * Time: `O(n)`
// // * Space: `O(n)`

// // với `n` là độ dài chuỗi.


// Ý tưởng thuật toán

// Ta cần đếm xem có bao nhiêu chữ cái:



// xuất hiện ở dạng viết thường

// và cũng xuất hiện ở dạng viết hoa.

// Ví dụ:



// "aA"

// → a là special.

// Phân tích code

// Khởi tạo 2 mảng boolean

// boolean[] lower = new boolean[26];

// boolean[] upper = new boolean[26];

// lower[i]

// → lưu xem chữ thường có xuất hiện chưa.

// upper[i]

// → lưu xem chữ hoa có xuất hiện chưa.

// Mỗi vị trí tương ứng:

// IndexChữ0a / A1b / B2c / C

// ...

// | 25 | z / Z |

// Duyệt toàn bộ chuỗi

// for (char ch : word.toCharArray())

// Mỗi lần lấy 1 ký tự trong chuỗi.

// Ví dụ:



// word = "aaAbcBC"

// Ta sẽ lần lượt lấy:



// a, a, A, b, c, B, C

// Trường hợp chữ thường

// if (Character.isLowerCase(ch)) {

//     lower[ch - 'a'] = true;

// }

// Ví dụ:



// ch = 'c'

// Ta tính:



// 'c' - 'a' = 2

// → lower[2] = true

// nghĩa là:



// chữ c đã xuất hiện

// Trường hợp chữ hoa

// upper[ch - 'A'] = true;

// Ví dụ:



// ch = 'C'

// Ta có:



// 'C' - 'A' = 2

// → upper[2] = true

// nghĩa là:



// chữ C đã xuất hiện

// Sau khi duyệt xong

// Ví dụ:



// word = "aaAbcBC"

// Ta có:

// ChữlowerupperatruetruebtruetruectruetrueĐếm ký tự special

// for (int i = 0; i < 26; i++) {



//     if (lower[i] && upper[i]) {

//         count++;

//     }

// }

// Điều kiện:



// lower[i] && upper[i]

// nghĩa là:



// cả chữ thường và chữ hoa đều tồn tại

// → đây là ký tự special.

// Ví dụ chạy thực tế

// Input:



// word = "abBC"

// Sau khi duyệt:

// Chữlowerupperatruefalsebtruetruecfalsetrue

// Chỉ có b thỏa mãn.

// → kết quả:



// 1

// Độ phức tạp

// Time Complexity

// Duyệt chuỗi 1 lần:



// O(n)

// Duyệt thêm 26 ký tự:



// O(26)

// => Tổng:



// O(n)

// Space Complexity

// Dùng 2 mảng kích thước 26:



// O(1)

// vì kích thước cố định.


import java.util.*;

public class b245 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    String word = sc.nextLine();


        // Gọi hàm đếm
        int result = numberOfSpecialChars(word);

        // In kết quả
        System.out.println( result);

        sc.close();

    }
// Hàm đếm số ký tự special
    public static int numberOfSpecialChars(String word) {

        // Mảng lưu chữ thường đã xuất hiện hay chưa
        boolean[] lower = new boolean[26];

        // Mảng lưu chữ hoa đã xuất hiện hay chưa
        boolean[] upper = new boolean[26];

        // Duyệt từng ký tự trong chuỗi
        for (char ch : word.toCharArray()) {

            // Nếu là chữ thường
            if (Character.isLowerCase(ch)) {

                // Đánh dấu chữ thường đã xuất hiện
                lower[ch - 'a'] = true;
            }

            // Nếu là chữ hoa
            else {

                // Đánh dấu chữ hoa đã xuất hiện
                upper[ch - 'A'] = true;
            }
        }

        int count = 0;

        // Kiểm tra từ a -> z
        for (int i = 0; i < 26; i++) {

            // Nếu tồn tại cả chữ thường và chữ hoa
            if (lower[i] && upper[i]) {

                // Tăng số lượng ký tự special
                count++;
            }
        }

        return count;
}
}

