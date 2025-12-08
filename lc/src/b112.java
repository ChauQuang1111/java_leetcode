// // # 1925. Count Square Sum Triples(08/12/2025)
// // # Dưới đây là **giải thích đề bài 1925. Count Square Sum Triples** (LeetCode) theo cách **dễ hiểu nhất**.
// // # Dưới đây là **giải thích thuật toán** + **code có chú thích đầy đủ**, mình viết theo cách **dễ hiểu nhất**.
// Dưới đây là**giải thích thuật toán**+**code Java đã thêm chú thích đầy đủ**+**hàm`main`dùng`Scanner`**cho bạn.

// ---

// #✅Giải thích thuật toán(ngắn–dễ hiểu)

// Bài toán yêu cầu đếm số bộ ba**(a,b,c)**sao cho:

// ```a²+b²=c²1≤a,b,c≤n```

// Một cách duyệt a,b,c là O(n³)→quá chậm.

// Ta dùng**công thức sinh bộ ba Pythagoras nguyên thủy**:

// ```a=k*(u²-v²)b=k*(2 uv)c=k*(u²+v²)```

// Điều kiện để(u,v)tạo bộ nguyên thủy:

// 1.**u và v khác parity**→(u-v)là số lẻ 2.**gcd(u,v)=1**

// Cạnh huyền nguyên thủy:

// ```c0=u²+v²```

// Tất cả cạnh huyền hợp lệ là:

// ```c=k*c0(k≥1,và k*c0≤n)```

// Số lượng k hợp lệ:`n/c0`.

// Mỗi bộ(a,b,c)tương ứng đổi chỗ a và b→**sinh ra 2 bộ**→cộng`2*(n/c0)`.

// ---

// #✅Code Java có chú thích rõ ràng

// ```java

import java.util.*;

public class b112 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();

        System.out.println(countTriples(n));
    }

    public static int countTriples(int n) {
        int res = 0;

        // u chạy từ 2 đến sqrt(n)
        for (int u = 2; u * u <= n; u++) {

            // v < u
            for (int v = 1; v < u; v++) {

                // Điều kiện sinh bộ Pythagoras nguyên thủy:
                // 1) (u - v) phải là số lẻ → khác parity
                // 2) gcd(u, v) = 1
                if (((u - v) & 1) == 0 || gcd(u, v) != 1) {
                    continue;
                }

                // Cạnh huyền nguyên thủy: c = u^2 + v^2
                int c = u * u + v * v;

                // Nếu c > n → không còn bộ nào hợp lệ
                if (c > n)
                    continue;

                // Số lượng bộ sinh ra bằng cách nhân k:
                // k * c <= n → k ≤ n / c
                // Mỗi bộ sinh ra **2 bộ** do có thể đổi chỗ a,b
                res += 2 * (n / c);
            }
        }

        return res;
    }

    // Hàm gcd (thuật toán Euclid)
    public static int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

}

// Đếm số bộ ba (a, b, c) thỏa a^2 + b^2 = c^2, 1 <= a,b,c <= n

// #🔍

// Giải thích
// từng phần
// quan trọng

// ###✔`(u-v)&1==0`

// *`(u-v)`chẵn→**
// cùng chẵn
// hoặc cùng lẻ***
// Không hợp lệ→
// vì u
// và v phải**khác parity**→Skip.

// ---

// ###✔`gcd(u, v) != 1`

// Nếu u và v không nguyên thủy → bộ sinh ra không phải bộ Pythagoras nguyên
// thủy
// → Skip.

// ---

// ### ✔ `c = u * u + v * v`

// Cạnh huyền nguyên thủy.

// ### ✔ `res += 2 * (n / c)`

// * `n / c` = số lượng giá trị k sao cho `k * c ≤ n`
// * Nhân **2** vì (a, b, c) và (b, a, c) đều tính.

// ---

// # Nếu bạn muốn

// Mình có thể viết:
// ✅ code brute-force để bạn so sánh
// ✅ hình minh họa Pythagoras
// ✅ giải thích sâu hơn vì sao công thức sinh ra toàn bộ bộ Pythagoras

// Bạn muốn không?

// # ---

// # # ✅ **Ý tưởng thuật toán (dạng tối ưu – dùng công thức sinh bộ
// Pythagoras)**

// # Thay vì duyệt a, b, c (O(n³) → rất chậm), ta dùng **công thức sinh bộ ba
// Pythagoras nguyên (a, b, c)**:

// # Bộ Pythagoras nguyên thu được bằng:

// # ```
// # a = k * (u² - v²)
// # b = k * (2uv)
// # c = k * (u² + v²)
// # ```

// # Trong đó:

// # * u > v ≥ 1
// # * u và v **khác parity** (1 chẵn – 1 lẻ)
// # * gcd(u, v) = 1 (nguyên thủy)
// # * k là số nhân (k ≥ 1)

// # Mỗi c = u² + v² → là cạnh huyền cơ bản
// # → Nếu nhân thêm k, cạnh huyền trở thành:

// # ```
// # c' = k * c
// # ```

// # Chỉ lấy khi c' ≤ n.

// # ---

// # # ⭐ Ý chính trong thuật toán

// # 1. Duyệt u từ 2 → √n
// # 2. Duyệt v từ 1 → u - 1
// # 3. Loại những cặp (u, v) không tạo ra bộ nguyên thủy

// # * (u - v) chẵn → bỏ
// # * gcd(u, v) != 1 → bỏ
// # 4. Tính c = u² + v²

// # * Nếu c > n → bỏ
// # 5. Mỗi bộ nguyên thủy tạo ra **n // c** bộ bằng cách nhân k
// # 6. Mỗi bộ (a, b, c) có thể đổi chỗ a, b → thành **2 bộ khác nhau**
// # → nhân 2

// # ---

// # # 🧠 **Code Python có chú thích (đầy đủ nhất)**

// # ```python
// from math import sqrt, gcd

// class Solution:
// def countTriples(self, n: int) -> int:
// res = 0

// # u^2 + v^2 = c (cạnh huyền nguyên thủy)
// for u in range(2, int(sqrt(n)) + 1):
// for v in range(1, u):

// # Điều kiện để (u, v) tạo ra bộ Pythagoras nguyên thủy:
// # 1) u và v khác parity → (u - v) là số lẻ
// # 2) gcd(u, v) = 1
// if (u - v) & 1 == 0 or gcd(u, v) != 1:
// continue

// # Cạnh huyền nguyên thủy (k = 1)
// c = u * u + v * v

// # Nếu cạnh huyền > n → không cần xét nữa
// if c > n:
// continue

// # Mỗi bộ nguyên thủy sinh ra thêm các bộ nhân k
// # k*c <= n → có (n // c) giá trị k hợp lệ
// # Mỗi bộ có thể đổi chỗ a,b → sinh ra 2 bộ
// res += 2 * (n // c)

// return res

// # 📌 Ví dụ nhỏ dễ hiểu

// # Giả sử n = 10

// # Ta có 2 bộ Pythagoras chuẩn:

// # 1. (3,4,5)
// # 2. (6,8,10) ← nhân đôi (k=2) của (3,4,5)

// # Cả hai đều ≤ 10 → ta tính:

// # * Bộ (3,4,5) → sinh ra 2 bộ (3,4,5) và (4,3,5) → 2 bộ
// # * Bộ (6,8,10) → sinh ra 2 bộ (6,8,10) và (8,6,10) → 2 bộ

// # → tổng = **4**

// # Nếu bạn muốn

// # Mình có thể:

// # ✅ Vẽ hình minh họa
// # ✅ Viết bản brute-force và so sánh với bản tối ưu
// # ✅ Đưa thêm ví dụ trực quan hơn

// # Bạn muốn tiếp tục phần nào?

// # ---

// # # 🎯 **Đề bài yêu cầu gì?**

// # Cho một số nguyên dương **n**, bạn cần **đếm số lượng bộ ba (a, b, c)** sao
// cho:

// # 1. **1 ≤ a ≤ n**
// # 2. **1 ≤ b ≤ n**
// # 3. **1 ≤ c ≤ n**
// # 4. **a² + b² = c²**

// # Đây chính là **số lượng bộ Pythagoras** (tam giác vuông) có cạnh góc vuông
// là a, b và cạnh huyền là c, trong khoảng từ 1 đến n.

// # ---

// # # ✔ Ví dụ minh họa

// # Nếu **n = 5**, ta xét tất cả a, b, c từ 1 đến 5.

// # Tìm bộ nào thỏa mãn:

// # 🔍 **a² + b² = c²**

// # Ta có:

// # * a = 3, b = 4 → 3² + 4² = 9 + 16 = **25 = 5²**
// # → hợp lệ
// # * a = 4, b = 3 → 4² + 3² = **25 = 5²**
// # → hợp lệ (đề tính cả 2)

// # 👉 Vậy **kết quả = 2**

// # ---

// # # 💡 Lưu ý quan trọng

// # * **a và b phân biệt theo thứ tự**, nên (3,4,5) và (4,3,5) là **hai bộ khác
// nhau**
// # * Chỉ tính những bộ có **c ≤ n**

// # ---

// # # ⭐ Tóm tắt ngắn gọn

// # * Đếm số bộ `(a, b, c)`
// # * Trong đó a² + b² = c²
// # * Và tất cả đều ≤ n
// # * a và b có thứ tự, nên hoán đổi được tính là khác bộ.

// # ---

// # Nếu bạn muốn, mình có thể:

// # ✅ Hướng dẫn giải brute force
// # ✅ Giải tối ưu hơn
// # ✅ Viết code Python hoàn chỉnh và chú thích
// # Bạn muốn phần nào?
