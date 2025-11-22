// #3190–Find Minimum Operations to Make All Elements Divisible by Three(22/12/2025)#Dưới đây là**giải thích thuật toán**+**code đã được chú thích đầy đủ**,cực dễ hiểu.Dưới đây là**giải thích thuật toán**,**chú thích chi tiết trong code Java**,và**thêm hàm`main`dùng`Scanner`**đúng với phong cách bạn hay dùng.

// ---

// #🧠**Giải thích thuật toán**

// Đề bài yêu cầu bạn biến tất cả phần tử`nums[i]`thành**số chia hết cho 3**bằng cách:

// *Mỗi lần được**+1**hoặc**-1**cho một số.

// Ta xét từng phần tử`num`:

// ###✔Nếu`num%3==0`

// →Nó**đã**chia hết cho 3→**0 thao tác**.

// ###✔Nếu`num%3==1`

// →Ta chỉ cần làm:

// *`num-1`(1 bước)→Vậy cần**1 thao tác**.

// ###✔Nếu`num%3==2`

// →Ta chỉ cần làm:

// *`num+1`(1 bước)→Vậy cũng cần**1 thao tác**.

// ---

// ##⭐Kết luận thuật toán:

// 👉**Số thao tác tối thiểu chính là số lượng phần tử không chia hết cho 3.**Vì mỗi số đó chỉ cần đúng**1 thao tác**mà thôi.

// ---

// #✅**Code Java đã chú thích đầy đủ**

// ```java

// class Solution {
//     public int minimumOperations(int[] nums) {
//         // Kết quả (tổng số thao tác)
//         int res = 0;

//         // Duyệt từng số trong mảng
//         for (int num : nums) {
//             // Nếu số không chia hết cho 3, cần đúng 1 thao tác
//             if (num % 3 != 0) {
//                 res++;
//             }
//         }

//         // Trả về tổng thao tác tối thiểu
//         return res;
//     }
// }```

// ---

// #🏁**Thêm hàm
// main dùng

// Scanner (bạn thích kiểu này)**

// ```java

import java.util.*;

public class b106 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        int[] nums = new int[n];

        // Nhập mảng
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = minimumOperations(nums);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    public static int minimumOperations(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 3 != 0)
                res++;
        }
        return res;
    }
}

// ```

// ---

// # 📌 Ví dụ chạy thử

// **Input:**

// ```
// 4
// 1 3 5 9
// ```

// **Giải thích:**

// * 1 → 1 bước
// * 3 → 0 bước
// * 5 → 1 bước
// * 9 → 0 bước

// **Output:**

// ```
// 2
// ```

// ---

// Nếu bạn cần mình viết phiên bản tối ưu nhất, hoặc muốn chuyển về Python / C++
// mình cũng làm được!

// # ---

// # # 🧠 **Giải thích thuật toán**

// # Đề bài yêu cầu:
// # 👉 Với mỗi phần tử `num` trong `nums`, muốn biến nó thành số **chia hết cho
// 3** bằng phép tăng/giảm 1.

// # Ta xem xét từng phần tử theo `num % 3`:

// # * Nếu `num % 3 == 0`:
// # → Số này **đã chia hết** cho 3 → **0 bước**.

// # * Nếu `num % 3 == 1`:
// # → Chỉ cần 1 bước: `num - 1` → divisible by 3.

// # * Nếu `num % 3 == 2`:
// # → Chỉ cần 1 bước: `num + 1` → divisible by 3.

// # 💡 **Kết luận quan trọng:**

// # * Mỗi số có `num % 3 != 0` cần **chính xác 1 bước**.
// # * Mỗi số có `num % 3 == 0` cần **0 bước**.

// # Do đó:
// # → Số thao tác tối thiểu = **đếm số phần tử mà `num % 3 != 0`**.

// # Trong Python, biểu thức:

// # ```python
// # num % 3 > 0
// # ```

// # trả về `True` nếu số **không chia hết cho 3**, mà `True` = 1 khi cộng vào
// sum.
// # → Vì vậy, ta chỉ cần **tổng của num % 3 > 0 cho tất cả phần tử**.

// # ---

// # # ✅ **Code có chú thích rõ ràng**

// # ```python
// from typing import List

// class Solution:
// def minimumOperations(self, nums: List[int]) -> int:
// # Nếu num % 3 > 0 → num không chia hết cho 3 → cần 1 thao tác
// # Nếu num % 3 == 0 → đã chia hết → cần 0 thao tác

// # sum(True for ...) sẽ đếm số phần tử True (True = 1)
// return sum(num % 3 > 0 for num in nums)

// # 🔍 Ví dụ

// # `nums = [4, 9, 10]`

// # * 4 % 3 = 1 → 1 bước
// # * 9 % 3 = 0 → 0 bước
// # * 10 % 3 = 1 → 1 bước

// # kết quả = 2

// # ---

// # Nếu bạn muốn, mình có thể viết phiên bản Java hoặc phân tích theo từng giá
// trị cụ thể của bạn.

// # # 🧩 **Đề bài nói gì?**

// # Bạn có một **mảng số nguyên** `nums`.

// # Bạn được phép thực hiện **một loại thao tác duy nhất**:

// # ### 👉 Mỗi thao tác:

// # * Chọn **một phần tử bất kỳ** trong mảng
// # * **Tăng hoặc giảm giá trị của nó đi 1**
// # (tức là: +1 hoặc -1)

// # ---

// # # 🎯 **Mục tiêu**

// # Làm sao để **tất cả** các phần tử trong `nums` đều **chia hết cho 3** với
// **số thao tác ít nhất**.

// # Bạn phải trả về **tổng số bước tối thiểu** cần thiết.

// # ---

// # # 🔍 Ví dụ dễ hiểu

// # Giả sử:

// # ```
// # nums = [4, 9, 10]
// # ```

// # Ta xét từng số:

// # ### 4

// # 4 % 3 = 1 → không chia hết
// # Chọn hướng thay đổi tối ưu:

// # * 4 → 3: 1 bước
// # * 4 → 6: 2 bước
// # → Chọn 1 bước

// # ### 9

// # 9 % 3 = 0 → đã chia hết → 0 bước

// # ### 10

// # 10 % 3 = 1 → không chia hết

// # * 10 → 9: 1 bước
// # → chọn 1

// # ➡ Tổng = **1 + 0 + 1 = 2**

// # 📌 **Quy tắc quan trọng**

// # Với **một số bất kỳ**, ta xét nó theo mod 3:

// # | x % 3 | Có nghĩa là gì? | Cần bao nhiêu bước tối ưu để trở thành
// divisible by 3? |
// # | ----- | --------------- |
// ------------------------------------------------------ |
// # | 0 | đã chia hết | 0 bước |
// # | 1 | dư 1 | 1 bước (x → x-1 hoặc x → x+2) |
// # | 2 | dư 2 | 1 bước (x → x+1 hoặc x → x-2) |

// # 💡 Do mỗi số dư 1 hoặc 2 đều chỉ cần **1 bước** để trở thành số chia hết
// cho 3.

// # ---

// # # ⭐ Kết luận

// # 💡 **Đề bài yêu cầu tính tổng số phần tử có `nums[i] % 3 != 0` → vì mỗi
// phần tử đó cần đúng 1 bước.**

// # ---

// # Nếu bạn muốn, mình có thể giải thích thêm bằng code Python/Java hoặc đưa
// thêm ví dụ để bạn hiểu sâu hơn.
