// # ## 📌 Minimum Swaps to Arrange a Binary Grid(02/03/2026)

// #📘1536. Minimum Swaps to Arrange a Binary Grid

// Mình sẽ:

// 1.✅Giải thích thuật toán rõ ràng 2.✅Thêm**chú thích chi tiết vào code**3.✅Thêm`main()`dùng`Scanner`để bạn chạy độc lập

// ---

// #🧠Ý tưởng thuật toán

// ##🎯Mục tiêu

// Với ma trận`n x n`:

// Ở hàng`i`(0-index)phải có**ít nhất`n-1-i`số 0 ở cuối hàng**.

// ---

// ##🔹Bước 1:Đếm số 0 ở cuối mỗi hàng

// Ví dụ:

// ```[1,0,0]→2[1,1,0]→1[1,1,1]→0```

// Ta tạo mảng:

// ```suffixZeros[i]=số 0 cuối của hàng i```

// ---

// ##🔹Bước 2:Duyệt từ trên xuống

// Với mỗi hàng`i`:

// *Tính`neededZeros=n-1-i`*Tìm hàng đầu tiên phía dưới có đủ số 0*Dùng swap kề nhau để đưa nó lên*Cộng số swap=`j-i`

// ---

// ##🔥Bản chất

// Đây là**bubble up**một hàng lên vị trí đúng.

// Nếu không tìm được hàng thỏa điều kiện→trả`-1`

// ---

import java.util.*;

public class b204 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        int[][] grid = new int[n][n];

        // Nhập ma trận
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        int result = minSwaps(grid);

        System.out.println(result);

        sc.close();
    }

    public static int minSwaps(int[][] grid) {
        final int n = grid.length;
        int ans = 0;

        // suffixZeros[i] = số lượng số 0 liên tiếp ở cuối hàng i
        int[] suffixZeros = new int[n];

        // Bước 1: Tính số 0 cuối mỗi hàng
        for (int i = 0; i < n; ++i)
            suffixZeros[i] = getSuffixZeroCount(grid[i]);

        // Bước 2: Duyệt từng vị trí từ trên xuống
        for (int i = 0; i < n; ++i) {

            // Hàng i cần ít nhất bao nhiêu số 0 ở cuối?
            final int neededZeros = n - 1 - i;

            // Tìm hàng đầu tiên từ i trở xuống có đủ số 0
            final int j = getFirstRowWithEnoughZeros(suffixZeros, i, neededZeros);

            // Nếu không tìm được → không thể sắp xếp
            if (j == -1)
                return -1;

            // Đưa hàng j lên vị trí i bằng swap kề nhau
            // Thực chất là dịch chuyển mảng
            for (int k = j; k > i; --k)
                suffixZeros[k] = suffixZeros[k - 1];

            // Cộng số swap cần thiết
            ans += j - i;
        }

        return ans;
    }

    // Hàm đếm số 0 liên tiếp ở cuối một hàng
    public static int getSuffixZeroCount(int[] row) {
        for (int i = row.length - 1; i >= 0; --i)
            if (row[i] == 1)
                return row.length - i - 1;

        // Nếu không có số 1 → cả hàng là 0
        return row.length;
    }

    // Tìm hàng đầu tiên từ index i trở xuống
    // có số 0 cuối >= neededZeros
    public static int getFirstRowWithEnoughZeros(int[] suffixZeros, int i, int neededZeros) {
        for (int j = i; j < suffixZeros.length; ++j)
            if (suffixZeros[j] >= neededZeros)
                return j;

        return -1;
    }
}

// ---

// #🧪

// Ví dụ
// chạy thử

// ###Input

// ```3 0 0 1 1 1 0 1 0 0```

// ###Output

// ```3```

// Phân tích
// độ phức tạp

// *
// Tính suffix zeros:

// O(n²)
// * Mỗi lần tìm hàng: O(n)
// * Tổng thể: O(n²)

// ---

// # 📌 Tóm tắt thuật toán

// 1. Đếm số 0 cuối mỗi hàng
// 2. Với mỗi vị trí i:

// * Tìm hàng đủ điều kiện
// * Tính số swap
// * Dịch chuyển hàng
// 3. Nếu không tìm được → -1

// ---

// Nếu bạn muốn mình:

// * 🔥 Vẽ mô phỏng từng bước swap
// * 💡 Giải thích tại sao greedy này luôn đúng
// * 🚀 Hoặc tối ưu theo phong cách competitive programming

// Cứ nói mình nhé 😉

// # Mình sẽ:

// # 1. ✅ Giải thích **ý tưởng thuật toán**
// # 2. ✅ Thêm **chú thích chi tiết vào từng dòng code**
// # 3. ✅ Giải thích vì sao `swaps += matchIdx` là đúng

// # ---

// # # 🧠 Ý tưởng thuật toán

// # ## Bước 1: Đếm số 0 ở cuối mỗi hàng

// # Ví dụ:

// # ```
// # [1,0,0] → 2 số 0 cuối
// # [1,1,0] → 1 số 0 cuối
// # [0,0,0] → 3 số 0 cuối
// # ```

// # Ta biến grid thành:

// # ```
// # rows = [số 0 cuối của từng hàng]
// # ```

// # ---

// # ## Bước 2: Duyệt từ dưới lên trên

// # Vì:

// # | Hàng | Cần ít nhất |
// # | ---- | ----------- |
// # | n-1 | 0 |
// # | n-2 | 1 |
// # | n-3 | 2 |
// # | ... | ... |

// # Ta duyệt `zeroesNeeded` từ lớn → nhỏ.

// # ---

// ## Bước 3: Với mỗi vị trí

// # * Tìm hàng đầu tiên có đủ số 0
// # * Đưa nó lên bằng cách swap
// # * Số swap chính là vị trí của nó (`matchIdx`)
// # * Xóa nó khỏi danh sách (coi như đã đặt đúng vị trí)

// # ---

// # # 💻 Code có chú thích chi tiết

// # ```python
// from typing import List
// def trailingZeroes(row: list[int]) -> int:
// """
// Đếm số lượng số 0 liên tiếp ở cuối một hàng.
// Ví dụ:
// [1,0,0] -> 2
// [1,1,1] -> 0
// [0,0,0] -> 3
// """
// try:
// # Đảo ngược hàng lại rồi tìm vị trí đầu tiên của số 1
// # Vị trí đó chính là số lượng số 0 ở cuối ban đầu
// return row[::-1].index(1)
// except ValueError:
// # Nếu không có số 1 nào
// # Nghĩa là toàn bộ hàng là số 0
// return len(row)

// def findMatchIdx(rows: list[int], zeroesNeeded: int) -> int:
// """
// Tìm hàng đầu tiên có số 0 ở cuối >= zeroesNeeded
// Trả về index của hàng đó
// Nếu không có → trả -1
// """
// for i, zeroes in enumerate(rows):
// if zeroes >= zeroesNeeded:
// return i

// return -1

// class Solution:
// def minSwaps(self, grid: List[List[int]]) -> int:
// swaps = 0

// # Bước 1: Tính số 0 cuối của từng hàng
// rows = [trailingZeroes(row) for row in grid]

// # Ví dụ: grid 3x3
// # Hàng 0 cần 2 số 0
// # Hàng 1 cần 1 số 0
// # Hàng 2 cần 0 số 0

// # Ta duyệt từ cần nhiều số 0 nhất xuống ít nhất
// for zeroesNeeded in range(len(rows))[::-1]:

// # Tìm hàng đầu tiên đủ điều kiện
// matchIdx = findMatchIdx(rows, zeroesNeeded)

// # Nếu không tìm được → không thể sắp xếp
// if matchIdx < 0:
// return -1

// # Số swap cần để đưa hàng đó lên đúng vị trí
// # Vì chỉ swap kề nhau nên:
// # Nếu nó đang ở index = matchIdx
// # Ta cần matchIdx lần swap để đưa lên đầu
// swaps += matchIdx

// # Xóa hàng đó khỏi danh sách
// # (coi như đã đặt đúng vị trí rồi)
// del rows[matchIdx]

// return swaps
// # ```

// # ---

// # # 🔥 Tại sao `swaps += matchIdx` là đúng?

// # Ví dụ:

// # ```
// # rows = [0,1,2]
// # ```

// # Cần hàng có ≥2 số 0.

// # Ta tìm được ở index = 2.

// # Muốn đưa nó lên đầu:

// # ```
// # swap(2,1)
// # swap(1,0)
// # ```

// # 👉 Tổng 2 swap
// # 👉 Chính là `matchIdx`

// # ---

// # # 🎯 Độ phức tạp

// # * Tính trailing zeros: O(n²)
// # * Mỗi lần tìm match: O(n)
// # * Tổng: O(n²)

// # ---

// # # # 📌 Tóm lại thuật toán

// # # 1. Đếm số 0 cuối mỗi hàng
// # # 2. Duyệt từ yêu cầu lớn → nhỏ
// # # 3. Tìm hàng phù hợp gần nhất
// # # 4. Cộng số swap
// # 5. Xóa hàng đó
// # 6. Nếu không tìm được → -1

// # ---

// # Nếu bạn muốn mình:

// # * 🚀 Giải thích trực quan bằng ví dụ cụ thể từng bước
// # * 🔎 So sánh với cách dùng bubble swap thật sự
// # * 💡 Hoặc viết lại version Java

// # Cứ nói mình nhé 😊

// # # Đây là một bài trên **LeetCode** có tên:
// # # **Minimum Swaps to Arrange a Binary Grid**

// # ---

// # ## 🔎 1. Đề bài yêu cầu gì?

// # Bạn được cho một **ma trận nhị phân (binary grid)** kích thước `n x n` gồm
// toàn số `0` và `1`.

// # Bạn **chỉ được phép hoán đổi hai hàng kề nhau (swap adjacent rows)**.

// # 🎯 Mục tiêu:
// # Sắp xếp lại các hàng sao cho:

// # > Ở hàng thứ `i` (0-index), phải có **ít nhất `n - i - 1` số 0 liên tiếp ở
// cuối hàng** (trailing zeros).

// # Nếu làm được → trả về **số lần swap ít nhất**
// # Nếu không thể → trả về `-1`

// # ---

// # ## 🧠 2. Hiểu điều kiện quan trọng

// # Với ma trận kích thước `n`:

// # | Hàng (index i) | Cần ít nhất bao nhiêu số 0 ở cuối? |
// # | -------------- | ---------------------------------- |
// # | 0 | n - 1 |
// # | 1 | n - 2 |
// # | 2 | n - 3 |
// # | ... | ... |
// # | n-1 | 0 |

// # 👉 Hàng trên càng cao thì càng cần nhiều số 0 ở cuối.

// # ---

// # ## 📌 3. Ví dụ minh họa

// # ### Ví dụ 1

// # ```
// # Input:
// # grid = [
// # [0,0,1],
// # [1,1,0],
// # [1,0,0]
// # ]
// # ```

// # ### Bước 1: Đếm số 0 ở cuối mỗi hàng

// # | Hàng | Dãy | Số 0 cuối |
// # | ---- | ------- | --------- |
// # | 0 | [0,0,1] | 0 |
// # | 1 | [1,1,0] | 1 |
// # | 2 | [1,0,0] | 2 |

// # Ta có mảng trailing zeros:

// # ```
// # [0, 1, 2]
// # ```

// # ---

// # ### Bước 2: Kiểm tra từng vị trí

// # * Hàng 0 cần ≥ 2 số 0
// # → Hàng 2 có 2 số 0 → swap lên

// # * Hàng 1 cần ≥ 1 số 0
// # → OK

// # * Hàng 2 cần ≥ 0 số 0
// # → OK

// # 👉 Tổng số swap tối thiểu = **3**

// # ---

// # ## 🎯 4. Bản chất bài toán là gì?

// # Thực chất bài này là:

// # * Đếm số 0 ở cuối mỗi hàng
// # * Với từng vị trí từ trên xuống:

// # * Tìm hàng bên dưới thỏa điều kiện
// # * Dùng swap kề nhau để đưa nó lên
// # * Đếm số bước

// # 💡 Giống như "bubble up" một phần tử lên phía trên.

// # ---

// # ## ❌ Khi nào trả về -1?

// # Nếu tại vị trí i:

// # * Không có hàng nào phía dưới có đủ số 0 cần thiết

// # → Không thể sắp xếp → trả về `-1`

// # ---

// # ## 🧩 5. Tóm tắt lại bài

// # ✔ Input: Ma trận 0–1
// # ✔ Chỉ swap được **2 hàng kề nhau**
// # ✔ Mỗi hàng phải có đủ số 0 ở cuối theo thứ tự
// # ✔ Trả về số swap nhỏ nhất
// # ✔ Không làm được → trả `-1`

// # ---

// # Nếu bạn muốn mình:

// # * Vẽ mô phỏng từng bước swap
// # * Giải thích thuật toán chi tiết hơn
// # * Hoặc viết code Java/Python có chú thích

// # Cứ nói mình biết nhé 🚀
