// Bài **762. Prime Number of Set Bits in Binary Representation** (21/02/2026) 
// ---

// Ok Quang👌mình sẽ:

// 1.✅Giải thích thuật toán 2.✅Thêm chú thích chi tiết vào code 3.✅Viết thêm`main()`dùng`Scanner`

// ---

// #🧠Giải thích thuật toán

// Bài này làm theo 3 bước chính:

// ###🔹Bước 1:Duyệt từ`left`→`right`

// ```java for(int i=left;i<=right;i++)```

// Ta xét từng số trong khoảng.

// ---

// ###🔹Bước 2:Đếm số bit 1 của số đó

// ```java Integer.bitCount(i)```

// Ví dụ:

// *6→110→có 2 bit 1*7→111→có 3 bit 1

// Hàm`bitCount()`là hàm có sẵn trong Java,rất tối ưu.

// ---

// ###🔹Bước 3:Kiểm tra số bit 1 có phải nguyên tố không

// Ta kiểm tra:

// *Nếu<2→không phải nguyên tố*Duyệt từ 2 đến√n*Nếu chia hết→không phải nguyên tố

// ---

// ###🎯Ý tưởng tổng quát

// ```Duyệt từng số↓Đếm số bit 1↓Kiểm tra có phải số nguyên tố không↓Nếu đúng→tăng biến đếm```

// ---

// #💻Code đầy đủ có chú thích+main Scanner

// ```java

import java.util.*;

public class b186 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập left và right
        int left = sc.nextInt();
        int right = sc.nextInt();

        // Gọi hàm xử lý
        int result = countPrimeSetBits(left, right);

        // In kết quả
        System.out.println(result);
    }

    // Hàm chính của bài toán
    public static int countPrimeSetBits(int left, int right) {

        int c = 0; // Biến đếm số lượng số thỏa điều kiện

        // Duyệt từ left đến right
        for (int i = left; i <= right; i++) {

            // Đếm số bit 1 trong biểu diễn nhị phân của i
            int bits = Integer.bitCount(i);

            // Nếu số bit 1 là số nguyên tố thì tăng biến đếm
            if (isPrime(bits)) {
                c++;
            }
        }

        return c; // Trả về kết quả
    }

    // Hàm kiểm tra một số có phải số nguyên tố không
    public static boolean isPrime(int n) {

        // Nếu nhỏ hơn 2 thì không phải nguyên tố
        if (n < 2)
            return false;

        // Duyệt từ 2 đến căn bậc hai của n
        for (int i = 2; i * i <= n; i++) {

            // Nếu chia hết thì không phải nguyên tố
            if (n % i == 0)
                return false;
        }

        return true; // Nếu không chia hết cho số nào → là số nguyên tố
    }
}

// Giả sử:

// *N=right-
// left

// Ta có:

// *
// Duyệt N lần*bitCount=

// O(1)
// * isPrime kiểm tra tối đa √32 ≈ 6

// lần (vì số bit tối đa của int là 32)

// 👉 Thực tế gần như O(N)
// 👉 Rất nhanh

// ---

// Nếu bạn muốn mình giải thích:

// * Vì sao chỉ cần kiểm tra đến √n?
// * Cách tối ưu hơn nữa (hardcode prime nhỏ)
// * Hoặc cách làm dùng bit manipulation thuần

// Nói mình biết 😎

// ## 📌 Yêu cầu đề bài

// Cho hai số nguyên `left` và `right`.

// Với **mỗi số nguyên x trong đoạn [left, right]**, ta:

// 1. Chuyển x sang dạng **nhị phân**.
// 2. Đếm số lượng bit `1` trong biểu diễn nhị phân đó
// → gọi là **set bits**.
// 3. Kiểm tra xem số lượng bit `1` đó có phải là **số nguyên tố** hay không.

// 👉 Kết quả: **Trả về số lượng các số thỏa điều kiện trên.**

// ---

// ## 🧠 Giải thích khái niệm

// ### 1️⃣ Set bits là gì?

// Là **số lượng bit 1** trong biểu diễn nhị phân của một số.

// Ví dụ:

// | Số | Nhị phân | Số bit 1 |
// | -- | -------- | -------- |
// | 6 | 110 | 2 |
// | 7 | 111 | 3 |
// | 8 | 1000 | 1 |

// ---

// ### 2️⃣ Số nguyên tố là gì?

// Số nguyên tố là số:

// * Lớn hơn 1
// * Chỉ chia hết cho 1 và chính nó

// Ví dụ:
// 2, 3, 5, 7, 11, ...

// Không phải nguyên tố:
// 1, 4, 6, 8, 9, ...

// ---

// ## 📘 Ví dụ cụ thể

// ### Input:

// ```
// left = 6
// right = 10
// ```

// Ta xét từng số:

// | Số | Nhị phân | Số bit 1 | Có phải số nguyên tố? |
// | -- | -------- | -------- | -------------------------- |
// | 6 | 110 | 2 | ✅ (2 là nguyên tố) |
// | 7 | 111 | 3 | ✅ (3 là nguyên tố) |
// | 8 | 1000 | 1 | ❌ (1 không phải nguyên tố) |
// | 9 | 1001 | 2 | ✅ |
// | 10 | 1010 | 2 | ✅ |

// 👉 Có 4 số thỏa điều kiện
// → Output: `4`

// ---

// ## 🎯 Tóm lại đề bài yêu cầu

// Đếm bao nhiêu số trong đoạn `[left, right]` mà:

// ```
// số lượng bit 1 trong nhị phân của nó là số nguyên tố
// ```

// ---

// Nếu bạn muốn, mình có thể:

// * Giải thích cách làm tối ưu
// * Phân tích độ phức tạp
// * Viết code Java (theo style bạn thích: Scanner, while loop, tối ưu)
// * Hoặc hướng dẫn tư duy từng bước để bạn tự code

// Bạn muốn theo hướng nào? 🚀
