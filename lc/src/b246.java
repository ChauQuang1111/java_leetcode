// Count the Number of Special Characters II(27/05/2026)

// ---

// # Ý nghĩa “special character”

// Một chữ cái được gọi là **special** nếu:

// * nó xuất hiện ở dạng **chữ thường** và **chữ hoa**
// * và **mọi chữ thường phải xuất hiện trước chữ hoa**

// Ví dụ với chữ `a`:

// * hợp lệ: `"aA"` ✅
// * hợp lệ: `"aaAAA"` ✅
// * không hợp lệ: `"Aa"` ❌
//   vì chữ hoa xuất hiện trước chữ thường

// ---

// # Ví dụ

// ## Ví dụ 1

// ```text
// word = "aaAbcBC"
// ```

// Xét từng chữ:

// ### Chữ `a`

// * có `a`
// * có `A`
// * tất cả `a` đứng trước `A`

// → special ✅

// ---

// ### Chữ `b`

// * có `b`
// * có `B`
// * `b` đứng trước `B`

// → special ✅

// ---

// ### Chữ `c`

// * có `c`
// * có `C`
// * `c` đứng trước `C`

// → special ✅

// Kết quả:

// ```text
// 3
// ```

// ---

// # Ví dụ 2

// ```text
// word = "AbBCab"
// ```

// ### Chữ `a`

// * `A` xuất hiện trước `a`

// → không special ❌

// ### Chữ `b`

// * `B` xuất hiện trước `b`

// → không special ❌

// ### Chữ `c`

// * chỉ có `C`, không có `c`

// → không special ❌

// Kết quả:

// ```text
// 0
// ```

// ---

// # Ý tưởng của bài

// Ta cần kiểm tra cho mỗi chữ cái:

// 1. Có xuất hiện chữ thường không?
// 2. Có xuất hiện chữ hoa không?
// 3. Vị trí cuối của chữ thường có nằm trước vị trí đầu của chữ hoa không?

// Nếu:

// ```text
// last lowercase < first uppercase
// ```

// thì ký tự đó là special.

// ---

// # Ví dụ minh họa vị trí

// ```text
// word = "aabA"
// index: 0123
// ```

// * vị trí cuối của `a` = 2
// * vị trí đầu của `A` = 3

// ```text
// 2 < 3
// ```

// → hợp lệ ✅

// ---

// Nhưng:

// ```text
// word = "Aa"
// index: 01
// ```

// * vị trí cuối của `a` = 1
// * vị trí đầu của `A` = 0

// ```text
// 1 < 0  ❌
// ```

// → không hợp lệ.

// ---

// # Tóm tắt ngắn gọn

// Một ký tự special khi:

// ```text
// - có cả lowercase và uppercase
// - lowercase xuất hiện hoàn toàn trước uppercase
// ```

// Ví dụ:

// | Chuỗi    | Kết quả |
// | -------- | ------- |
// | `"aA"`   | ✅       |
// | `"aaAA"` | ✅       |
// | `"Aa"`   | ❌       |
// | `"aAa"`  | ❌       |



// Ý tưởng thuật toán

// Ta cần tìm số ký tự special sao cho:



// có cả chữ thường và chữ hoa

// tất cả chữ thường xuất hiện trước chữ hoa

// Cách làm của code

// Code dùng:



// lastLower[i] → lưu vị trí cuối cùng của chữ thường

// firstUpper[i] → lưu vị trí đầu tiên của chữ hoa

// Ví dụ:



// word = "aaAbBC"

// Ta lưu:

// Ký tựlastLowerfirstUppera/A12b/B34c/C-15

// Sau đó kiểm tra:



// lastLower[i] < firstUpper[i]

// Nếu đúng → special character.

// Giải thích từng phần

// 1. Khai báo

// int n = word.length();

// char[] chars = word.toCharArray();

// n = độ dài chuỗi

// chuyển chuỗi thành mảng ký tự để truy cập nhanh hơn

// Ví dụ:



// word = "aAb"

// thành:



// ['a', 'A', 'b']

// 2. Tạo mảng lưu vị trí

// int[] lastLower = new int[26];

// int[] firstUpper = new int[26];

// Mỗi vị trí đại diện cho 1 chữ cái:

// IndexChữ0a1b2c

// ...

// Ý nghĩa

// lastLower

// Lưu vị trí cuối cùng của chữ thường.

// Ví dụ:



// lastLower[0]

// = vị trí cuối của 'a'

// firstUpper

// Lưu vị trí đầu tiên của chữ hoa.



// firstUpper[0]

// = vị trí đầu của 'A'

// 3. Gán giá trị ban đầu

// Arrays.fill(lastLower, -1);

// Arrays.fill(firstUpper, -1);

// Ban đầu:



// -1

// nghĩa là:



// chưa xuất hiện

// 4. Duyệt chuỗi

// for (int i = 0; i < n; i++)

// Duyệt từng ký tự.

// 5. Nếu là chữ thường

// if (chars[i] >= 'a')

// Ví dụ:



// 'a' >= 'a'  → true

// 'b' >= 'a'  → true

// Lưu vị trí cuối

// lastLower[chars[i] - 'a'] = i;

// Ví dụ:



// chars[i] = 'c'

// 'c' - 'a' = 2

// → lưu vào:



// lastLower[2]

// Vì luôn gán đè:



// lastLower[...] = i;

// nên cuối cùng nó giữ vị trí cuối cùng.

// 6. Nếu là chữ hoa

// else if (firstUpper[chars[i] - 'A'] == -1)

// Chỉ lưu lần đầu tiên xuất hiện.

// Ví dụ:



// "AABC"

// gặp A đầu tiên → lưu

// gặp A tiếp theo → bỏ qua

// Lưu vị trí đầu tiên

// firstUpper[chars[i] - 'A'] = i;

// 7. Đếm special character

// int ans = 0;

// Duyệt 26 chữ cái

// for (int i = 0; i < 26; i++)

// Điều kiện

// if (lastLower[i] != -1 && lastLower[i] < firstUpper[i])

// Ý nghĩa

// lastLower[i] != -1

// Có chữ thường.

// lastLower[i] < firstUpper[i]

// Vị trí cuối của lowercase nằm trước vị trí đầu của uppercase.

// Ví dụ

// word = "aaA"

// Ta có:

// ký tựlastLowerfirstUppera/A12

// 1 < 2

// → special ✅

// Ví dụ không hợp lệ

// word = "Aa"

// ký tựlastLowerfirstUppera/A10

// 1 < 0

// → false ❌

// Code có chú thích

// class Solution {

//     public int numberOfSpecialChars(String word) {



//         int n = word.length();



//         // chuyển chuỗi thành mảng char

//         char[] chars = word.toCharArray();



//         // lưu vị trí cuối của chữ thường

//         int[] lastLower = new int[26];



//         // lưu vị trí đầu của chữ hoa

//         int[] firstUpper = new int[26];



//         // khởi tạo = -1 nghĩa là chưa xuất hiện

//         Arrays.fill(lastLower, -1);

//         Arrays.fill(firstUpper, -1);



//         // duyệt từng ký tự

//         for (int i = 0; i < n; i++) {



//             // nếu là chữ thường

//             if (chars[i] >= 'a') {



//                 // cập nhật vị trí cuối

//                 lastLower[chars[i] - 'a'] = i;



//             }



//             // nếu là chữ hoa

//             else if (firstUpper[chars[i] - 'A'] == -1) {



//                 // chỉ lưu lần xuất hiện đầu tiên

//                 firstUpper[chars[i] - 'A'] = i;

//             }

//         }



//         int ans = 0;



//         // kiểm tra 26 chữ cái

//         for (int i = 0; i < 26; i++) {



//             // có lowercase

//             // và lowercase nằm trước uppercase

//             if (lastLower[i] != -1 &&

//                 lastLower[i] < firstUpper[i]) {



//                 ans++;

//             }

//         }



//         return ans;

//     }

// }





import java.util.*;

public class b246 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
 // Nhập chuỗi
        String word = sc.nextLine();

        

        // Gọi hàm và in kết quả
        System.out.println(numberOfSpecialChars(word));

        sc.close();
    
    }

    public static int numberOfSpecialChars(String word) {

        // Độ dài chuỗi
        int n = word.length();

        // Chuyển chuỗi thành mảng ký tự
        char[] chars = word.toCharArray();

        // Mảng lưu vị trí cuối cùng của chữ thường
        // Ví dụ:
        // lastLower[0] -> vị trí cuối của 'a'
        // lastLower[1] -> vị trí cuối của 'b'
        int[] lastLower = new int[26];

        // Mảng lưu vị trí đầu tiên của chữ hoa
        // Ví dụ:
        // firstUpper[0] -> vị trí đầu của 'A'
        // firstUpper[1] -> vị trí đầu của 'B'
        int[] firstUpper = new int[26];

        // Gán tất cả phần tử = -1
        // nghĩa là ký tự chưa xuất hiện
        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);

        // Duyệt từng ký tự trong chuỗi
        for (int i = 0; i < n; i++) {

            // Nếu là chữ thường
            if (chars[i] >= 'a' && chars[i] <= 'z') {

                // Lưu vị trí cuối cùng của chữ thường
                // Ví dụ:
                // 'c' - 'a' = 2
                lastLower[chars[i] - 'a'] = i;
            }

            // Nếu là chữ hoa
            else if (chars[i] >= 'A' && chars[i] <= 'Z') {

                // Chỉ lưu vị trí đầu tiên của chữ hoa
                if (firstUpper[chars[i] - 'A'] == -1) {

                    firstUpper[chars[i] - 'A'] = i;
                }
            }
        }

        // Biến đếm số ký tự special
        int ans = 0;

        // Duyệt 26 chữ cái
        for (int i = 0; i < 26; i++) {

            // Điều kiện:
            // 1. Có chữ thường
            // 2. Vị trí cuối của chữ thường
            //    nằm trước vị trí đầu của chữ hoa
            if (lastLower[i] != -1 &&
                lastLower[i] < firstUpper[i]) {

                ans++;
            }
        }

        return ans;
    }
}

