// String(11/09/2025)
// # ### Giải thích Đề bài: Sort Vowels in a String

// ###🔎Giải thích thuật toán:

// 1.**Khởi tạo dữ liệu**

// *`vowels`:mảng chứa tất cả nguyên âm(hoa+thường).*`stringCharCount[128]`:mảng đếm tần suất xuất hiện ký tự(dùng ASCII,128 là đủ cho ký tự chuẩn).*`sChars`:mảng ký tự từ chuỗi đầu vào để tiện xử lý.

// 2.**Đếm số lần xuất hiện của từng ký tự**

// *Với mỗi ký tự trong`sChars`,tăng`stringCharCount[ch]`.

// 3.**Kiểm tra xem chuỗi có nguyên âm không**

// *Duyệt`vowels`,nếu có ký tự nào xuất hiện trong`stringCharCount`,gán`found=true`.*Nếu**không có nguyên âm**→trả về chuỗi gốc luôn.

// 4.**Đánh dấu ký tự nào là nguyên âm**

// *Tạo`isVowels[128]`.*Nếu ký tự là nguyên âm và có xuất hiện trong chuỗi,thì`isVowels[ch]=true`.

// 5.**Thay thế nguyên âm theo thứ tự đã sắp xếp**

// *Biến`left`bắt đầu từ`0`.*Với từng nguyên âm`v`trong mảng`vowels`(đã được sắp theo alphabet):

// *Trong khi`stringCharCount[v]>0`:

// *Lấy ký tự`ch=sChars[left]`.*Nếu`ch`là nguyên âm(`isVowels[ch]==true`):

// *Giảm`stringCharCount[v]`đi 1.*Thay`sChars[left]`bằng nguyên âm`v`.*Nếu không phải nguyên âm thì giữ nguyên.*Tăng`left++`để sang vị trí tiếp theo.*Ý tưởng:đi qua chuỗi từ trái sang phải,chỗ nào nguyên âm thì lần lượt thay bằng nguyên âm nhỏ nhất còn lại→đảm bảo nguyên âm trong chuỗi sau khi thay**được sắp xếp theo alphabet**.

// 6.**Trả về chuỗi mới**từ`sChars`.

// ---

// ###📝Code có chú thích+hàm`main`

// ```java

import java.util.*;

public class b33 {
    static Scanner sc = new Scanner(System.in);

    // Hàm main để test
    public static void main(String[] args) {
        String s = sc.nextLine();

        String result = sortVowels(s);
        System.out.println(result);

        sc.close();

    }

    public static String sortVowels(String s) {
        // Danh sách nguyên âm (hoa + thường)
        char[] vowels = { 'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' };

        // Mảng đếm số lần xuất hiện của từng ký tự (dùng mã ASCII)
        int[] stringCharCount = new int[128];

        // Chuyển chuỗi sang mảng ký tự
        char[] sChars = s.toCharArray();

        // Đếm tần suất ký tự
        for (char ch : sChars) {
            stringCharCount[ch]++;
        }

        // Kiểm tra xem có nguyên âm không
        boolean found = false;
        for (char ch : vowels) {
            found |= stringCharCount[ch] > 0;
        }
        if (!found)
            return s; // Nếu không có nguyên âm thì trả về chuỗi ban đầu

        // Đánh dấu ký tự nào là nguyên âm trong chuỗi
        boolean[] isVowels = new boolean[128];
        for (char ch : vowels) {
            if (stringCharCount[ch] > 0)
                isVowels[ch] = true;
        }

        // Bắt đầu thay thế nguyên âm đã sắp xếp
        int left = 0; // con trỏ vị trí trong sChars
        for (char v : vowels) { // nguyên âm duyệt theo thứ tự alphabet
            while (stringCharCount[v] > 0) {
                char ch = sChars[left];

                // Nếu là nguyên âm thì thay bằng nguyên âm v hiện tại
                if (isVowels[ch]) {
                    stringCharCount[v]--;
                    sChars[left] = v;
                }

                // Di chuyển sang ký tự tiếp theo
                left++;
            }
        }

        // Trả về chuỗi kết quả
        return new String(sChars);
    }

}

// ###✅

// Ví dụ chạy

// ```
// Nhập chuỗi:
// leetcode
// Chuỗi
// sau khi
// sắp xếp
// nguyên âm:leetcede```

// 👉Trong`"leetcode"`
// có nguyên âm`[e,e,o,e]`→
// sắp xếp thành`[e,e,e,o]`.
// Thay dần
// vào các
// vị trí
// nguyên âm→`"leetcede"`.

// ---

// Bạn có
// muốn mình
// so sánh**
// cách làm
// Java này**với**
// cách làm
// Python trước đó**(Counter+replace)
// để dễ
// nhớ hơn không?

// # Đề bài **2785. Sort Vowels in a String** yêu cầu bạn sắp xếp lại các nguyên
// âm (vowels) trong một chuỗi (string) theo thứ tự không giảm (non-decreasing
// order).
// # Rồi 👍 mình sẽ giải thích chi tiết từng bước thuật toán trong đoạn Python
// code bạn đưa:

// # ---

// ## 📌 Code bạn viết

// # ```python
// from collections

// import Counter

// from
// collections
// import Counter

// class Solution:
// def sortVowels(self, s: str) -> str:
// # Danh sách

// nguyên âm (cả chữ hoa và thường)
// vowels = ['A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u']

// # Đếm số lần xuất hiện của từng ký tự trong chuỗi
// count_char = Counter(s)

// # Danh sách lưu các nguyên âm tìm được trong s
// s_vowels = []

// # Duyệt qua từng ký tự trong Counter
// for char in count_char.keys():
// if char in vowels:
// # Nếu là nguyên âm thì đưa vào danh sách
// s_vowels.append(char)
// # Đồng thời thay nguyên âm trong chuỗi bằng dấu '_' để giữ vị trí
// s = s.replace(char, '_')

// # Sắp xếp danh sách nguyên âm theo thứ tự alphabet
// s_vowels.sort()

// # Thay dần '_' bằng các nguyên âm đã sắp xếp
// for char in s_vowels:
// # Thay đúng số lần ký tự

// xuất hiện (dựa vào count_char[char])
// s = s.replace('_', char, count_char[char])

// # Trả về chuỗi sau khi thay thế
// return s

// # ```

// # ---

// # # 📝 Giải thích từng bước

// # ## 1.

// # ```python
// # vowels = ['A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u']
// # ```

// # Danh sách tất cả các nguyên

// âm (cả chữ hoa và thường).

// # ---

// # ## 2.

// # ```python
// # count_char = Counter(s)
// # ```

// # * Dùng `Counter` để đếm **số lần xuất hiện của mỗi ký tự** trong chuỗi `s`.
// # * Ví dụ: `s = "lEetcOde"` →

// # ```
// # Counter({'e':2, 'l':1, 'E':1, 't':1, 'c':1, 'O':1, 'd':1})
// # ```

// # ---

// # ### 3.

// # ```python
// # s_vowels = []
// # for char in count_char.keys():
// # if char in vowels:
// # s_vowels.append(char)
// # s = s.replace(char, '_')
// # ```

// # * Duyệt từng ký tự trong `s`:

// # * Nếu ký tự là nguyên âm, thêm vào `s_vowels`.
// # * Đồng thời **thay toàn bộ ký tự đó trong `s` thành dấu `_`**.

// # Ví dụ `s = "lEetcOde"` → sau bước này:

// # * `s_vowels = ['E', 'e', 'O']`
// # * `s = "l__tc_d_"`

// # ---

// # ### 4.

// # ```python
// # s_vowels.sort()
// # ```

// # * Sắp xếp danh sách nguyên âm theo thứ tự tăng dần.
// # * Ví dụ: `['E', 'e', 'O']` → `['E', 'O', 'e']`

// # ---

// # ### 5.

// # ```python
// # for char in s_vowels:
// # s = s.replace('_', char, count_char[char])
// # ```

// # * Thay lần lượt các dấu `_` trong chuỗi `s` bằng các nguyên âm đã sắp xếp.
// # * `count_char[char]` đảm bảo thay đúng số lượng ký tự ban đầu.

// # Ví dụ:

// # * `s = "l__tc_d_"`
// # * Thay `_` bằng `'E'` (1 lần) → `"lE_tc_d_"`
// # * Thay `_` bằng `'O'` (1 lần) → `"lEOtc_d_"`
// # * Thay `_` bằng `'e'` (2 lần) → `"lEOtcdee"`

// # ---

// # ### 6.

// # ```python
// # return s
// # ```

// # → Trả về chuỗi mới đã thay xong.

// # ---

// # ## 📌 Ý tưởng tổng quát của code

// # 1. Tìm tất cả nguyên âm trong chuỗi và thay chúng bằng `_`.
// # 2. Lưu lại các nguyên âm, sắp xếp chúng.
// # 3. Duyệt lại và điền từng nguyên âm đã sắp vào các dấu `_`.

// # ---

// # ⚠️ Nhược điểm:

// # * Dùng `str.replace` nhiều lần → có thể chậm khi chuỗi rất dài.
// # * Cách này khác với hướng tối ưu vì nó xử lý dựa trên **khoảng trắng `_`**
// thay vì index.

// # ---

// # #### Các quy tắc chính

// # 1. **Chỉ sắp xếp nguyên âm**: Bạn chỉ được sắp xếp các ký tự là nguyên âm.
// Các phụ âm (consonants) và các ký tự

// khác (ví dụ: khoảng trắng, dấu câu, v.v.) phải giữ nguyên vị trí ban đầu của
// chúng.
// # 2. **Nguyên âm (Vowels)**: Các nguyên âm được định nghĩa là `'a', 'e', 'i',
// 'o', 'u'` và các chữ cái viết hoa tương ứng `'A', 'E', 'I', 'O', 'U'`.
// # 3. **Thứ tự sắp xếp**: Thứ tự sắp xếp là theo bảng chữ cái. Ví dụ: `'a'` <
// `'e'`, `'E'` < `'i'`, v.v.

// # #### Ví dụ minh họa

// # Hãy xem xét chuỗi đầu vào: `"lEetcOde"`

// # 1. **Tìm và trích xuất các nguyên âm**:
// # * Duyệt qua chuỗi và xác định các nguyên âm: `'E'`, `'O'`, `'e'`.
// # * Các phụ âm giữ nguyên vị trí: `'l'`, `'t'`, `'c'`, `'d'`.

// # 2. **Sắp xếp các nguyên âm**:
// # * Sắp xếp các nguyên âm đã trích xuất theo thứ tự bảng chữ cái: `'E'`,
// `'O'`, `'e'`.
// # * Thứ tự sắp xếp sẽ là: `'E'`, `'e'`, `'O'`.

// # 3. **Ghép lại chuỗi**:
// # * Bây giờ, đặt các nguyên âm đã sắp xếp trở lại vị trí ban đầu của chúng
// trong chuỗi:
// # * `'l'` **E** `'e'` `'t'` `'c'` **O** `'d'` **e**
// # * Trở thành: `'l'` **E** `'e'` `'t'` `'c'` **O** `'d'` **e**
// # * Dựa trên chuỗi gốc, chúng ta có thể đặt chúng trở lại vị trí ban đầu:
// # * Ban đầu: `_`, `E`, `_`, `_`, `_`, `O`, `_`, `e`
// # * Mới: `_`, `E`, `_`, `_`, `_`, `O`, `_`, `e`
// # * Oops, ví dụ ban đầu của bạn hơi sai. Hãy lấy ví dụ khác để dễ hiểu hơn.
// # * Chuỗi: `"LEETCODE"`
// # * Nguyên âm: `'E', 'E', 'O', 'E'`.
// # * Vị trí của chúng: 1, 2, 5, 7.
// # * Các phụ âm giữ nguyên: `'L', 'T', 'C', 'D'`.
// # * Sắp xếp nguyên âm: `'E', 'E', 'E', 'O'`.
// # * Đặt lại vào vị trí:
// # * Chuỗi ban đầu: L, **E**, **E**, T, C, **O**, D, **E**
// # * Chuỗi mới: L, **E**, **E**, T, C, **E**, D, **O**

// # #### Tóm tắt các bước giải quyết

// # 1. **Duyệt chuỗi đầu vào** và xác định tất cả các nguyên âm.
// # 2. **Lưu trữ các nguyên âm này** vào một danh sách hoặc mảng.
// # 3. **Sắp xếp danh sách các nguyên âm** theo thứ tự từ

// điển (từ a-z, sau đó A-Z).
// # 4. **Tạo một chuỗi mới** bằng cách duyệt lại chuỗi ban đầu. Khi bạn gặp một
// nguyên âm, hãy lấy nguyên âm đầu tiên từ danh sách đã sắp xếp và đặt nó vào
// vị trí đó. Giữ nguyên các phụ âm.
// # 5. **Trả về chuỗi mới** làm kết quả.

// # Đây là một ví dụ minh họa về cách hoạt động của thuật toán **Sort Vowels in
// a String** với một chuỗi cụ thể.

// # ### Ví dụ: Chuỗi đầu vào `"lEetcOde"`

// # **Bước 1: Trích xuất và lưu trữ các nguyên âm**

// # Đầu tiên, chúng ta duyệt qua chuỗi `"lEetcOde"` để tìm các nguyên âm.

// # * `'l'`: phụ âm
// # * `'E'`: **nguyên âm**. Lưu lại.
// # * `'e'`: **nguyên âm**. Lưu lại.
// # * `'t'`: phụ âm
// # * `'c'`: phụ âm
// # * `'O'`: **nguyên âm**. Lưu lại.
// # * `'d'`: phụ âm
// # * `'e'`: **nguyên âm**. Lưu lại.

// # Sau khi duyệt xong, chúng ta có một danh sách các nguyên âm theo thứ tự
// xuất hiện: `['E', 'e', 'O', 'e']`.

// # ---

// # **Bước 2: Sắp xếp các nguyên âm đã trích xuất**

// # Bây giờ, chúng ta sắp xếp danh sách `['E', 'e', 'O', 'e']` theo thứ tự bảng
// chữ cái, không phân biệt chữ hoa hay chữ thường (giá trị ASCII của chúng):

// # * Giá trị ASCII của `'E'` là 69.
// # * Giá trị ASCII của `'O'` là 79.
// # * Giá trị ASCII của `'e'` là 101.

// # Danh sách đã sắp xếp sẽ là: `['E', 'O', 'e', 'e']`.

// # ---

// # **Bước 3: Ghép lại chuỗi mới**

// # Cuối cùng, chúng ta xây dựng chuỗi kết quả bằng cách duyệt lại chuỗi gốc và
// thay thế các nguyên âm bằng các nguyên âm đã được sắp xếp.

// # * **`l`**: Phụ âm, giữ nguyên. Chuỗi kết quả: `"l"`
// # * **`E`**: Nguyên âm thứ nhất. Thay thế bằng nguyên âm đầu tiên trong danh
// sách đã sắp xếp (`'E'`). Chuỗi kết quả: `"lE"`
// # * **`e`**: Nguyên âm thứ hai. Thay thế bằng nguyên âm thứ hai trong danh
// sách đã sắp xếp (`'O'`). Chuỗi kết quả: `"lEO"`
// # * **`t`**: Phụ âm, giữ nguyên. Chuỗi kết quả: `"lEOt"`
// # * **`c`**: Phụ âm, giữ nguyên. Chuỗi kết quả: `"lEOtc"`
// # * **`O`**: Nguyên âm thứ ba. Thay thế bằng nguyên âm thứ ba trong danh sách
// đã sắp xếp (`'e'`). Chuỗi kết quả: `"lEOtce"`
// # * **`d`**: Phụ âm, giữ nguyên. Chuỗi kết quả: `"lEOtced"`
// # * **`e`**: Nguyên âm thứ tư. Thay thế bằng nguyên âm thứ tư trong danh sách
// đã sắp xếp (`'e'`). Chuỗi kết quả: `"lEOtcede"`

// # # Kết quả cuối cùng là chuỗi `"lEOtcede"`.

// # Ok mình giải thích đề **LeetCode 2785 – Sort Vowels in a String** cho bạn
// 👇

// # ---

// # # 📌 Đề bài

// # Cho một chuỗi `s`. Nhiệm vụ:

// # * Lấy tất cả **nguyên âm (vowels)**

// trong chuỗi (`a, e, i, o, u, A, E, I, O, U`).
// # * **Sắp xếp các nguyên âm này theo thứ tự tăng dần** (theo bảng chữ cái).
// # * Giữ nguyên vị trí của các phụ âm, chỉ thay đổi nguyên âm theo thứ tự đã
// sắp.

// # 👉 Trả về chuỗi mới sau khi sắp xếp.

// # ---

// # ## 📌 Ví dụ

// # ### Ví dụ 1

// # ```
// # Input: s = "lEetcOde"
// # Vowels: ['E', 'e', 'O', 'e']
// # Sorted: ['E', 'O', 'e', 'e']
// # Output: "lEOtcdee"
// # ```

// # Giải thích:

// # * Nguyên âm xuất hiện ở vị trí 1,2,5,7.
// # * Thay bằng dãy đã sắp xếp `['E','O','e','e']`.

// # ---

// # ### Ví dụ 2

// # ```
// # Input: s = "lYmpH"
// # Vowels: []
// # Output: "lYmpH"
// # ```

// # Giải thích:

// # * Không có nguyên âm nên chuỗi giữ nguyên.

// # ---

// # ## 📌 Yêu cầu chính

// # * Chỉ sắp xếp **các nguyên âm**, không động đến phụ âm.
// # * Bảo toàn vị trí tương đối của phụ âm.

// # ---

// # 👉 Tóm gọn:
// # **Tách nguyên âm → sort → gắn lại vào đúng chỗ → trả chuỗi kết quả.**
