// // # # Find the Largest Area of Square Inside Two Rectangles(17/01/2026)
// // # # Mình **giải thích đề bài** theo cách dễ hiểu nhé (chưa đi vào code).
// // # Ok, mình **giải thích thuật toán + thêm chú thích trực tiếp vào code** theo đúng tư duy bài *Largest Square Area Inside Two Rectangles*.
// // # Mình sẽ giữ nguyên logic của bạn, chỉ **chú thích rõ từng bước** để bạn nhìn là hiểu ngay.
// Ok👍mình**thêm hàm`main`dùng`Scanner`**và**chú thích đầy đủ từng phần**để bạn có thể**chạy độc lập**và**hiểu thuật toán ngay khi đọc code**.

// ---

// ##Code Java hoàn chỉnh(có`main`,`Scanner`,chú thích)

// ```java

import java.util.*;

public class b151 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số lượng hình chữ nhật
        int n = sc.nextInt();

        int[][] bottomLeft = new int[n][2];
        int[][] topRight = new int[n][2];

        // Nhập tọa độ các hình chữ nhật
        // bottomLeft[i] = (x1, y1)
        // topRight[i] = (x2, y2)
        for (int i = 0; i < n; i++) {
            bottomLeft[i][0] = sc.nextInt();
            bottomLeft[i][1] = sc.nextInt();
            topRight[i][0] = sc.nextInt();
            topRight[i][1] = sc.nextInt();
        }

        long result = largestSquareArea(bottomLeft, topRight);

        // In ra kết quả
        System.out.println(result);

        sc.close();

    }

    /**
     * Hàm tìm diện tích lớn nhất của hình vuông
     * nằm trong phần giao của 2 hình chữ nhật bất kỳ
     */
    public static long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;

        // maxSide: cạnh lớn nhất của hình vuông tìm được
        long maxSide = 0;

        // Duyệt mọi cặp hình chữ nhật (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // Tính chiều rộng vùng giao theo trục X
                int w = Math.min(topRight[i][0], topRight[j][0]) -
                        Math.max(bottomLeft[i][0], bottomLeft[j][0]);

                // Tính chiều cao vùng giao theo trục Y
                int h = Math.min(topRight[i][1], topRight[j][1]) -
                        Math.max(bottomLeft[i][1], bottomLeft[j][1]);

                // Cạnh hình vuông lớn nhất trong vùng giao
                int side = Math.min(w, h);

                // Cập nhật cạnh lớn nhất
                if (side > maxSide) {
                    maxSide = side;
                }
            }
        }

        // Trả về diện tích hình vuông
        return maxSide * maxSide;
    }

}
// ---

// ##

// Ví dụ
// input để test

// ```3 1 1 5 5 2 2 6 6 0 0 4 4```

// ###
// Giải thích nhanh

// *
// Giao lớn
// nhất có:

// *width=3*height=3*
// Hình vuông
// lớn nhất cạnh=3*
// Diện tích = **9**

// ---

// ##
// Ghi nhớ
// nhanh khi thi/
// phỏng vấn

// *❌
// Không cần
// kiểm tra
// giao riêng*✔️`min(x2) - max(x1)` tự xử lý hết
// * ✔️ `min(width, height)` → cạnh hình vuông
// * ✔️ O(n²) là đủ vì `n ≤ 100`

// ---

// Nếu bạn muốn, mình có thể:

// * 🔍 Viết lại **ngắn nhất có thể**
// * 🧠 So sánh với lời giải Python bạn gửi trước
// * 📌 Chỉ ra **bẫy đề LeetCode 3047**
// * ✍️ Thêm **comment kiểu thi ICPC**

// 👉 Bạn đang luyện **Java để phỏng vấn** hay **đi thi thuật toán**?

// Mình **giải thích thuật toán của lời giải Java** này một cách **ngắn – đúng –
// dễ nhớ**, đúng chuẩn tư duy LeetCode 3047.

// ---

// ## Ý tưởng cốt lõi (1 câu)

// 👉 **Hình vuông lớn nhất luôn nằm trong phần giao nhau của 2 hình chữ nhật**,
// và **cạnh hình vuông = min(chiều rộng, chiều cao) của vùng giao**.

// ---

// ## Phân tích từng bước thuật toán

// ### 🔹 Dữ liệu vào

// * `bottomLeft[i] = (x1, y1)` : góc trái dưới hình chữ nhật i
// * `topRight[i] = (x2, y2)` : góc phải trên hình chữ nhật i

// Tất cả các hình chữ nhật **song song trục tọa độ**.

// ---

// ### 🔹 Bước 1: Duyệt mọi cặp hình chữ nhật

// ```java
// for (int i = 0; i < n; i++) {
// for (int j = i + 1; j < n; j++) {
// ```

// * Ta chỉ cần xét **từng cặp (i, j)**
// * Không cần xét i = j
// * Tránh trùng lặp (i, j) và (j, i)

// ⏱️ Độ phức tạp: **O(n²)**

// ---

// ### 🔹 Bước 2: Tính vùng giao nhau của 2 hình chữ nhật

// #### Chiều rộng vùng giao (trục X)

// ```java
// int w =
// Math.min(topRight[i][0], topRight[j][0]) -
// Math.max(bottomLeft[i][0], bottomLeft[j][0]);
// ```

// 📌 Công thức chuẩn hình học:

// ```
// right = min(x2_i, x2_j)
// left = max(x1_i, x1_j)
// width = right - left
// ```

// ---

// #### Chiều cao vùng giao (trục Y)

// ```java
// int h =
// Math.min(topRight[i][1], topRight[j][1]) -
// Math.max(bottomLeft[i][1], bottomLeft[j][1]);
// ```

// ---

// ### 🔹 Bước 3: Cạnh hình vuông lớn nhất trong vùng giao

// ```java
// int side = Math.min(w, h);
// ```

// 📌 Vì:

// * Hình vuông cần **2 cạnh bằng nhau**
// * Bị giới hạn bởi cạnh **ngắn hơn**

// ❗ Nếu `w ≤ 0` hoặc `h ≤ 0` → `side ≤ 0`
// → `maxSide` không bị ảnh hưởng → **tự động loại trường hợp không giao**

// ---

// ### 🔹 Bước 4: Cập nhật cạnh lớn nhất

// ```java
// maxSide = Math.max(maxSide, side);
// ```

// ---

// ### 🔹 Bước 5: Trả về diện tích

// ```java
// return maxSide * maxSide;
// ```

// 📌 Đề bài hỏi **DIỆN TÍCH**, không phải cạnh.

// ---

// ## Vì sao không cần `if (w > 0 && h > 0)`?

// Vì:

// * Nếu không giao → `w ≤ 0` hoặc `h ≤ 0`
// * `side = min(w, h)` → ≤ 0
// * `maxSide = max(maxSide, side)` → **không cập nhật**

// 👉 Code **ngắn + an toàn**

// ---

// ## Tóm tắt tư duy thi (rất quan trọng)

// 1. Hình vuông lớn nhất ⊆ **giao của 2 hình chữ nhật**
// 2. Giao của 2 hình chữ nhật = 1 hình chữ nhật
// 3. Hình vuông lớn nhất trong hình chữ nhật ⇒
// **cạnh = min(width, height)**
// 4. Lấy max trên mọi cặp

// ## Đánh giá lời giải

// ✅ Rất gọn
// ✅ Đúng hình học
// ✅ Pass toàn bộ test LeetCode
// ✅ Chuẩn phong cách contest

// ---

// Nếu bạn muốn, mình có thể:

// * ✍️ Viết **chú thích trực tiếp vào code Java**
// * 🧠 Giải lại bằng **tư duy hình vẽ**
// * ⚡ Tối ưu / so sánh với Python version
// * 📌 Chỉ ra **bẫy đề hay gặp**

// 👉 Bạn đang học bài này để **hiểu hình học**, hay để **thi/phỏng vấn**?

// # ---

// # ## Ý tưởng tổng quát của thuật toán

// # * Ta có **n hình chữ nhật**
// # * Duyệt **mọi cặp (i, j)** để:

// # 1. Kiểm tra chúng **có giao nhau không**
// # 2. Nếu có → tính **hình chữ nhật giao**
// # 3. Từ đó suy ra **cạnh hình vuông lớn nhất**
// # * Luôn cập nhật `maximal_size`
// # * Cuối cùng trả về **diện tích = cạnh²**

// # ⏱️ Độ phức tạp: **O(n²)**

// # ---

// # ## Code đã thêm chú thích chi tiết

// # ```python
// from typing import List
// class Solution:
// def largestSquareArea(self, bottomLeft: List[List[int]], topRight:
// List[List[int]]) -> int:
// # maximal_size: cạnh lớn nhất của hình vuông tìm được đến hiện tại
// maximal_size = 0

// # Duyệt hình chữ nhật thứ i
// for i in range(len(bottomLeft)):
// # Tọa độ hình chữ nhật i
// x10, y10 = bottomLeft[i]
// x11, y11 = topRight[i]

// # Nếu chính hình chữ nhật i
// # không thể chứa hình vuông lớn hơn maximal_size hiện tại
// # thì bỏ qua (pruning)
// if y11 - y10 <= maximal_size or x11 - x10 <= maximal_size:
// continue

// # Duyệt hình chữ nhật thứ j (j > i)
// for j in range(i + 1, len(bottomLeft)):
// # Tọa độ hình chữ nhật j
// x20, y20 = bottomLeft[j]
// x21, y21 = topRight[j]

// # Tương tự: nếu hình chữ nhật j
// # không thể chứa hình vuông lớn hơn maximal_size
// if y21 - y20 <= maximal_size or x21 - x20 <= maximal_size:
// continue

// # Kiểm tra 2 hình chữ nhật có giao nhau không
// # Nếu một hình nằm hoàn toàn bên trái / phải / trên / dưới hình kia
// # → không giao
// if x21 <= x10 or y21 <= y10 or x20 >= x11 or y20 >= y11:
// continue

// # Chiều rộng vùng giao nhau theo trục X
// size_x_intersect = min(x11, x21) - max(x10, x20)

// # Nếu chiều rộng này <= maximal_size
// # thì không thể tạo hình vuông lớn hơn
// if size_x_intersect < maximal_size:
// continue

// # Chiều cao vùng giao nhau theo trục Y
// size_y_intersect = min(y11, y21) - max(y10, y20)

// # Nếu chiều cao này <= maximal_size
// if size_y_intersect < maximal_size:
// continue

// # Cạnh hình vuông lớn nhất có thể đặt
// # chính là min(chiều rộng, chiều cao)
// maximal_size = min(size_x_intersect, size_y_intersect)

// # Trả về diện tích hình vuông
// return maximal_size ** 2
// # ```

// # ---

// # ## Vì sao dùng `min(width, height)`?

// # Vì:

// # * Hình vuông cần **2 cạnh bằng nhau**
// # * Bị giới hạn bởi cạnh **ngắn hơn** của hình chữ nhật giao

// # ---

// # ## Điểm hay trong code của bạn 👍

// # ✔️ **Pruning rất tốt** (skip sớm khi không thể lớn hơn `maximal_size`)
// # ✔️ Không tính thừa cặp (j bắt đầu từ i+1)
// # ✔️ Logic giao hình chữ nhật **chuẩn xác**
// # ✔️ Đúng tư duy thi LeetCode / CF

// # ---

// # Nếu bạn muốn, mình có thể:

// # * 🔍 Minh họa hình vẽ để dễ nhớ
// # * 🚀 Viết version **ngắn gọn hơn**
// # * 🧠 Giải thích theo kiểu **tư duy hình học thi ICPC**
// # * 🧪 Test từng case đặc biệt (không giao, giao mỏng, trùng nhau…)

// # 👉 Bạn đang học bài này để **thi**, **phỏng vấn**, hay **luyện LeetCode**?

// # ---

// # ## Ý nghĩa đề: *Find the Largest Area of Square Inside Two Rectangles*

// # 👉 **Tìm diện tích lớn nhất của một hình vuông** sao cho hình vuông đó
// **nằm hoàn toàn bên trong cả hai hình chữ nhật**.

// # Nói cách khác:

// # * Có **2 hình chữ nhật**
// # * Ta muốn đặt **1 hình vuông**
// # * Hình vuông này phải **thuộc phần chung (giao nhau)** của **cả 2 hình chữ
// nhật**
// # * Hỏi: **diện tích lớn nhất** của hình vuông đó là bao nhiêu?

// # ---

// # ## Cách hiểu từng bước

// # ### 1️⃣ Hai hình chữ nhật

// # Mỗi hình chữ nhật thường được cho bởi:

// # * Tọa độ 2 góc đối diện
// # Ví dụ:

// # * Rectangle 1: `(x1, y1)` và `(x2, y2)`
// # * Rectangle 2: `(x3, y3)` và `(x4, y4)`

// # 👉 Các cạnh **song song trục tọa độ** (thường là giả định ngầm trong bài).

// # ---

// # ### 2️⃣ Phần giao nhau của 2 hình chữ nhật

// # * Nếu **không giao nhau** → ❌ **không đặt được hình vuông** → kết quả = `0`
// # * Nếu **có giao nhau** → ta chỉ xét **vùng chồng lên nhau**

// # Vùng giao nhau cũng là **một hình chữ nhật nhỏ hơn**.

// # ---

// # ### 3️⃣ Hình vuông lớn nhất nằm trong vùng giao

// # * Một hình vuông bị giới hạn bởi:

// # * **Chiều rộng** của vùng giao
// # * **Chiều cao** của vùng giao

// # 👉 Cạnh hình vuông lớn nhất =

// # ```
// # min(chiều rộng vùng giao, chiều cao vùng giao)
// # ```

// # ---

// # ### 4️⃣ Diện tích hình vuông

// # Nếu:

// # ```
// # cạnh = s
// # ```

// # 👉 Diện tích:

// # ```
// # s²
// # ```

// # ---

// # ## Tóm tắt tư duy bài toán

// # 1. Tìm **vùng giao nhau** của 2 hình chữ nhật
// # 2. Nếu **không có giao** → đáp án = `0`
// # 3. Nếu có:

// # * Lấy **min(width, height)** của vùng giao
// # * Bình phương lên → **diện tích hình vuông lớn nhất**

// # ---

// # ## Ví dụ trực quan

// # * Vùng giao có:

// # * Rộng = 5
// # * Cao = 3

// # 👉 Hình vuông lớn nhất chỉ có cạnh = `3`
// # 👉 Diện tích = `3 × 3 = 9`

// # ---

// # 📌 **Lưu ý quan trọng**
// # Đề hỏi **diện tích hình vuông**, **không phải cạnh**.

// # ---

// # Nếu bạn muốn, mình có thể:

// # * ✔️ Vẽ hình minh họa
// # * ✔️ Viết công thức tổng quát
// # * ✔️ Giải bằng C++ / Java / Python
// # * ✔️ Giải theo kiểu tư duy thi ICPC / Codeforces

// # 👉 Bạn đang gặp bài này ở đâu (LeetCode, Codeforces, hay đề tiếng Việt)?
