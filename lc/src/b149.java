// // # Maximize Area of Square Hole in Grid(15/01/2026)
// // # ## 1. Mô tả bài toán (hiểu đơn giản)
// // # Ok 👍 mình sẽ **giải thích thuật toán + thêm chú thích trực tiếp vào code** để bạn nhìn là hiểu ngay vì sao làm vậy.
// Mình **giải thích thuật toán bài 2943 – Maximize Area of Square Hole in Grid** dựa **trực tiếp trên code Java của bạn**, theo đúng tư duy khi làm LeetCode nhé.
// Dưới đây là**phiên bản Java đầy đủ**cho bài**2943. Maximize Area of Square Hole in Grid**,có:

// *✅`main`dùng`Scanner`*✅Chú thích chi tiết từng bước*✅Giữ nguyên thuật toán bạn đã dùng(chuẩn LeetCode)

// ---

// ##Code Java hoàn chỉnh(có`main`+chú thích)

// ```java

import java.util.*;

public class b149 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Nhập số hàng và cột
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Nhập số thanh ngang bị tháo
        int h = sc.nextInt();
        int[] hBars = new int[h];
        for (int i = 0; i < h; i++) {
            hBars[i] = sc.nextInt();
        }

        // Nhập số thanh dọc bị tháo
        int v = sc.nextInt();
        int[] vBars = new int[v];
        for (int i = 0; i < v; i++) {
            vBars[i] = sc.nextInt();
        }

        int result = maximizeSquareHoleArea(n, m, hBars, vBars);

        // In kết quả
        System.out.println(result);

        sc.close();

    }

    // Hàm giải bài toán
    public static int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {

        // Sắp xếp để dễ tìm các thanh bị tháo liên tiếp
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        // mxh: số thanh ngang liên tiếp bị tháo nhiều nhất
        // mxv: số thanh dọc liên tiếp bị tháo nhiều nhất
        // curr: độ dài đoạn liên tiếp hiện tại
        int mxh = 1, mxv = 1, curr = 1;

        // Tìm đoạn liên tiếp dài nhất trong hBars
        for (int i = 1; i < hBars.length; i++) {
            // Nếu hai thanh ngang bị tháo liền nhau
            if (hBars[i] == hBars[i - 1] + 1) {
                curr++;
            } else {
                curr = 1; // bị ngắt → reset
            }
            mxh = Math.max(mxh, curr);
        }

        // Reset curr để dùng cho vBars
        curr = 1;

        // Tìm đoạn liên tiếp dài nhất trong vBars
        for (int i = 1; i < vBars.length; i++) {
            // Nếu hai thanh dọc bị tháo liền nhau
            if (vBars[i] == vBars[i - 1] + 1) {
                curr++;
            } else {
                curr = 1;
            }
            mxv = Math.max(mxv, curr);
        }

        // mxh thanh ngang liên tiếp → mxh + 1 ô (chiều cao)
        // mxv thanh dọc liên tiếp → mxv + 1 ô (chiều rộng)
        // Hình vuông ⇒ lấy cạnh nhỏ hơn
        int side = Math.min(mxh + 1, mxv + 1);

        // Diện tích hình vuông
        return side * side;
    }

}

// Ví dụ
// input để test

// ```5 5 2 1 2 1 2```

// ###Output

// ```4```

// ---

// ##
// Tóm tắt

// tư duy (để nhớ khi thi)

// * 🔹 Sắp xếp mảng
// * 🔹 Tìm **dãy liên tiếp dài nhất**
// * 🔹 `k thanh → k + 1 ô`
// * 🔹 Hình vuông ⇒ `min(height, width)`
// * 🔹 Diện tích = `side²`

// ---

// Nếu bạn muốn mình:

// * ✂️ rút gọn code cho thi ICPC
// * 🧠 giải thích lại theo **tư duy mảng**
// * 🔄 viết phiên bản **không dùng sort**

// cứ nói nhé 👍

// ---

// ## 1. Hiểu nhanh bài toán

// * Có một **lưới n × m**
// * Một số **thanh ngang** (`hBars`) và **thanh dọc** (`vBars`) bị tháo
// * Khi **nhiều thanh liên tiếp bị tháo**, các ô nhỏ sẽ **gộp lại thành lỗ
// lớn**
// * Yêu cầu: **tìm diện tích hình vuông lớn nhất** có thể tạo ra

// 📌 **Tháo k thanh liên tiếp → tạo k + 1 ô liền nhau**

// ---

// ## 2. Ý tưởng cốt lõi của thuật toán

// 1. **Chiều cao tối đa** của lỗ vuông:

// * Phụ thuộc vào **dãy thanh ngang liên tiếp bị tháo dài nhất**
// 2. **Chiều rộng tối đa**:

// * Phụ thuộc vào **dãy thanh dọc liên tiếp bị tháo dài nhất**
// 3. Vì là **hình vuông**:

// ```
// cạnh = min(chiều cao, chiều rộng)
// diện tích = cạnh²
// ```

// ---

// ## 3. Giải thích chi tiết từng phần code

// ### 🔹 Sắp xếp các thanh

// ```java
// Arrays.sort(hBars);
// Arrays.sort(vBars);
// ```

// 👉 Để dễ kiểm tra **các thanh bị tháo có liên tiếp nhau hay không**

// ---

// ### 🔹 Biến sử dụng

// ```java
// int mxh = 1, mxv = 1, curr = 1;
// ```

// * `curr` : độ dài đoạn liên tiếp hiện tại
// * `mxh` : số thanh ngang liên tiếp bị tháo **dài nhất**
// * `mxv` : số thanh dọc liên tiếp bị tháo **dài nhất**
// * Khởi tạo = 1 vì **một thanh tháo cũng tạo được 1 đoạn**

// ---

// ### 🔹 Tìm dãy liên tiếp dài nhất trong `hBars`

// ```java
// for (int i = 1; i < hBars.length; i++) {
// if (hBars[i] == hBars[i - 1] + 1)
// curr++;
// else
// curr = 1;

// mxh = Math.max(mxh, curr);
// }
// ```

// 📌 Ý nghĩa:

// * Nếu 2 thanh bị tháo **liền nhau** → tăng `curr`
// * Nếu bị ngắt → reset `curr`
// * Luôn cập nhật giá trị lớn nhất vào `mxh`

// 👉 Sau vòng lặp:

// ```
// mxh = số thanh ngang liên tiếp bị tháo nhiều nhất
// ```

// ---

// ### 🔹 Làm tương tự cho `vBars`

// ```java
// curr = 1;
// for (int i = 1; i < vBars.length; i++) {
// if (vBars[i] == vBars[i - 1] + 1)
// curr++;
// else
// curr = 1;

// mxv = Math.max(mxv, curr);
// }
// ```

// 👉 Kết quả:

// ```
// mxv = số thanh dọc liên tiếp bị tháo nhiều nhất
// ```

// ---

// ## 4. Tính cạnh hình vuông

// ```java
// int side = Math.min(mxh + 1, mxv + 1);
// ```

// 📌 Vì:

// * `mxh` thanh ngang liên tiếp → **mxh + 1 ô** (chiều cao)
// * `mxv` thanh dọc liên tiếp → **mxv + 1 ô** (chiều rộng)

// 👉 Hình vuông ⇒ lấy cạnh nhỏ hơn

// ---

// ## 5. Tính diện tích

// ```java
// return side * side;

// ---

// ## 6. Ví dụ minh họa nhanh

// ### Input

// ```java
// hBars = [2, 3, 4]
// vBars = [1, 2]
// ```

// ### Phân tích

// * `mxh = 3` → chiều cao = 4
// * `mxv = 2` → chiều rộng = 3
// * `side = min(4, 3) = 3`
// * diện tích = `3 × 3 = 9`

// ---

// ## 7. Độ phức tạp

// | Công đoạn | Độ phức tạp |
// | ---------- | -------------- |
// | Sắp xếp | `O(k log k)` |
// | Duyệt mảng | `O(k)` |
// | Tổng | **O(k log k)** |

// ---

// ## 8. Ghi nhớ nhanh khi đi thi

// ✅ Dãy **liên tiếp dài nhất**
// ✅ **k thanh → k + 1 ô**
// ✅ **Hình vuông → min(height, width)**

// ---

// Nếu bạn muốn:

// * ✨ viết lại **phiên bản ngắn hơn**
// * 🔄 tối ưu thêm (edge case rỗng)
// * 🧠 giải thích bằng **hình vẽ**

// cứ nói nhé 👍

// # ---

// # ## 1. Ý tưởng thuật toán (tóm tắt)

// # * Khi **k thanh liên tiếp bị tháo**, ta tạo được **k + 1 ô liền nhau**
// # * Vì hình cần là **hình vuông**:

// # * cạnh = `min(chiều cao lớn nhất, chiều rộng lớn nhất)`
// # * Chiều cao ↔ thanh **ngang** (`hBars`)
// # * Chiều rộng ↔ thanh **dọc** (`vBars`)

// # ---

// # ## 2. Giải thích từng phần trong code

// # ### ✅ Code có chú thích chi tiết

// # ```python
// class Solution:
// def maximizeSquareHoleArea(self, n, m, hBars, vBars):

// # Sắp xếp để các thanh bị tháo theo thứ tự tăng dần
// hBars.sort()
// vBars.sort()

// # Hàm tìm số thanh bị tháo LIÊN TIẾP dài nhất
// def longest(arr):
// # mx: độ dài lớn nhất tìm được
// # curr: độ dài đoạn liên tiếp hiện tại
// mx = curr = 1

// # Duyệt từ phần tử thứ 2
// for i in range(1, len(arr)):
// # Nếu hai thanh liên tiếp nhau (ví dụ 3 và 4)
// if arr[i] == arr[i - 1] + 1:
// curr += 1 # tăng độ dài đoạn liên tiếp
// else:
// curr = 1 # bị ngắt → reset lại

// # Cập nhật đoạn dài nhất
// mx = max(mx, curr)

// return mx

// # longest(hBars) + 1:
// # nếu tháo k thanh ngang liên tiếp → tạo k + 1 ô theo chiều cao
// # tương tự cho chiều rộng
// side = min(
// longest(hBars) + 1, # chiều cao tối đa của hình vuông
// longest(vBars) + 1 # chiều rộng tối đa của hình vuông
// )

// # Diện tích hình vuông = cạnh × cạnh
// return side * side

// # ## 3. Ví dụ chạy thuật toán

// # ### Input

// # ```python
// # n = 5
// # m = 5
// # hBars = [1, 2]
// # vBars = [2]
// # ```

// # ### Phân tích

// # * `hBars = [1, 2]` → 2 thanh liên tiếp → chiều cao = `2 + 1 = 3`
// # * `vBars = [2]` → 1 thanh → chiều rộng = `1 + 1 = 2`
// # * cạnh hình vuông = `min(3, 2) = 2`
// # * diện tích = `2² = 4`

// # ---

// # ## 4. Độ phức tạp

// # * Sắp xếp: `O(n log n)`
// # * Duyệt tìm đoạn liên tiếp: `O(n)`
// # * Tổng: **O(n log n)** → rất ổn 👍

// # ## 5. Ghi nhớ nhanh (đi thi / phỏng vấn)

// # 📌 **Tháo k thanh liên tiếp → tạo k + 1 ô**
// # 📌 **Hình vuông → lấy min(chiều cao, chiều rộng)**
// # 📌 **Diện tích = cạnh²**

// # ---

// # Nếu bạn muốn mình:

// # * ✍️ viết lại theo **phong cách LeetCode clean**
// # * 🔁 đổi sang **Java**
// # * 🧠 giải thích bằng **hình vẽ ASCII**

// # cứ nói nhé 😄

// # * Bạn có **một cái lưới hình chữ nhật** gồm:

// # * `n` **hàng** (horizontal)
// # * `m` **cột** (vertical)

// # * Ban đầu, lưới được chia bởi:

// # * Các **thanh ngang** (horizontal bars)
// # * Các **thanh dọc** (vertical bars)

// # * Sau đó:

// # * Một số **thanh ngang bị tháo bỏ** → được cho trong mảng `hBars`
// # * Một số **thanh dọc bị tháo bỏ** → được cho trong mảng `vBars`

// # 👉 Khi các thanh bị tháo, **những ô nhỏ sẽ dính lại với nhau**, tạo thành
// **lỗ trống (hole)** lớn hơn.

// # ---

// # ## 2. Nhiệm vụ của bạn

// # 👉 **Tìm diện tích lớn nhất của một lỗ hình vuông** có thể tạo ra sau khi
// tháo các thanh.

// # * Lỗ đó **bắt buộc là hình vuông**
// # * Diện tích = `cạnh × cạnh`

// # ---

// # ## 3. Ý nghĩa của `hBars` và `vBars`

// # * `hBars[i]` = **chỉ số thanh ngang bị tháo**
// # * `vBars[i]` = **chỉ số thanh dọc bị tháo**

// # ⚠️ Các chỉ số này cho biết **khoảng cách giữa các đường kẻ**, không phải ô.

// # ---

// # ## 4. Tư duy trực quan

// # ### 🔹 Thanh ngang

// # * Nếu **nhiều thanh ngang liên tiếp bị tháo**
// # → chiều **cao** của lỗ tăng lên

// # ### 🔹 Thanh dọc

// # * Nếu **nhiều thanh dọc liên tiếp bị tháo**
// # → chiều **rộng** của lỗ tăng lên

// # ---

// # ## 5. Vì sao phải là hình vuông?

// # * Giả sử:

// # * Chiều cao lớn nhất tạo được = `H`
// # * Chiều rộng lớn nhất tạo được = `W`

// # 👉 Hình vuông lớn nhất có cạnh:

// # ```
// # min(H, W)
// # ```

// # 👉 Diện tích:

// # ```
// # min(H, W)²
// # ```

// # ---

// # ## 6. Ví dụ minh họa (hiểu đề)

// # Giả sử:

// # ```
// # n = 5, m = 5
// # hBars = [1, 2]
// # vBars = [2]
// # ```

// # * `hBars = [1,2]`
// # → tháo **2 thanh ngang liên tiếp**
// # → tạo chiều cao = **3 ô**

// # * `vBars = [2]`
// # → chỉ tháo 1 thanh
// # → chiều rộng = **2 ô**

// # 👉 Hình vuông lớn nhất:

// # ```
// # cạnh = min(3, 2) = 2
// # diện tích = 2 × 2 = 4
// # ```

// # ---

// # ## 7. Tóm tắt ngắn gọn

// # 📌 Bài toán yêu cầu:

// # 1. Tìm **đoạn dài nhất liên tiếp** trong `hBars` → chiều cao
// # 2. Tìm **đoạn dài nhất liên tiếp** trong `vBars` → chiều rộng
// # 3. Lấy **min(chiều cao, chiều rộng)**
// # 4. Bình phương lên → **diện tích hình vuông lớn nhất**

// # ---

// # Nếu bạn muốn:

// # * 👉 mình **vẽ hình minh họa**
// # * 👉 hoặc **giải từng test case**
// # * 👉 hoặc **viết code Java / C++ / Python**

// # chỉ cần nói 👍
