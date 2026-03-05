// // ## 🧩 Minimum Changes To Make Alternating Binary String (05/03/2026)

// Ok Quang👍mình sẽ:

// 1.✅Giải thích thuật toán 2.✅Thêm`main`dùng`Scanner`3.✅Comment chi tiết từng dòng code cho bạn

// ---

// #🧠Giải thích thuật toán

// Bài này chỉ có**2 khả năng chuỗi alternating**:

// *Bắt đầu bằng`'0'`→`010101...`*Bắt đầu bằng`'1'`→`101010...`

// ---

// ##✨Ý tưởng code của bạn

// Bạn không tạo mẫu chuẩn.

// Thay vào đó:

// ###Trường hợp 1:Giữ nguyên ký tự đầu

// *Duyệt từ trái sang phải*Nếu`ch[i]==ch[i-1]`→đổi ký tự hiện tại→tăng số lần đổi

// ---

// ###Trường hợp 2:Đổi ký tự đầu tiên

// *Đổi`ch[0]`*Sau đó làm giống helper*Vì đã đổi ký tự đầu nên+1

// ---

// 👉Cuối cùng lấy`min(ans1,ans2)`

// ⏱Độ phức tạp:

// *Time:O(n)*Space:O(n)

// ---

// #💻Code đầy đủ có main+Scanner+chú thích

// ```java

import java.util.*;

public class b207 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.nextLine();

        int result = minOperations(s);

        System.out.println(result);

        sc.close();
    }

    // Hàm này biến mảng char thành alternating string
    // và trả về số lần thay đổi cần thiết
    public static int helper(char ch[]) {

        int ans = 0; // đếm số lần đổi
        int n = ch.length;

        // duyệt từ phần tử thứ 2
        for (int i = 1; i < n; i++) {

            // nếu khác ký tự trước đó thì OK
            if (ch[i] != ch[i - 1])
                continue;

            // nếu giống nhau thì phải đổi
            // nếu là '0' thì đổi thành '1'
            // nếu là '1' thì đổi thành '0'
            ch[i] = ch[i - 1] == '0' ? '1' : '0';

            ans++; // tăng số lần đổi
        }

        return ans;
    }

    public static int minOperations(String s) {

        // Trường hợp 1: giữ nguyên ký tự đầu
        char ch1[] = s.toCharArray();
        int ans1 = helper(ch1);

        // Trường hợp 2: đổi ký tự đầu
        char ch2[] = s.toCharArray();

        // đổi ký tự đầu tiên
        ch2[0] = ch2[0] == '0' ? '1' : '0';

        // +1 vì đã đổi ký tự đầu
        int ans2 = helper(ch2) + 1;

        return Math.min(ans1, ans2);
    }
}

// ---

// #🔎

// Ví dụ
// chạy

// Input:

// ```1111```

// Output:

// ```
// So lan
// thay doi
// it nhat:2```

// ---

// #🚀
// Lưu ý
// quan trọng

// Cách này**
// modify trực
// tiếp mảng**→
// hơi tricky
// Cách tối
// ưu hơn là**không đổi mảng**,
// chỉ đếm

// mismatch (mình có thể viết version sạch hơn cho bạn nếu muốn).

// ---

// Bạn muốn mình:

// * 🔥 Viết version tối ưu hơn không cần sửa mảng?
// * 🧠 Dry run từng bước với ví dụ cụ thể?
// * ⚡ Phân tích vì sao cách này đúng về mặt logic?

// Nói mình biết bạn muốn đào sâu mức nào nhé.

// ### 📌 Đề bài yêu cầu gì?

// Bạn được cho một **chuỗi nhị phân** `s` (chỉ gồm ký tự `'0'` và `'1'`).

// Nhiệm vụ của bạn là:

// 👉 **Tìm số lần thay đổi ký tự ít nhất** (đổi `'0'` thành `'1'` hoặc `'1'`
// thành `'0'`)
// để biến chuỗi đó thành **chuỗi nhị phân xen kẽ (alternating)**.

// ---

// ### 🔁 Chuỗi nhị phân xen kẽ là gì?

// Chuỗi mà **hai ký tự liền nhau luôn khác nhau**.

// Chỉ có **2 dạng hợp lệ**:

// 1. Bắt đầu bằng `'0'`:

// ```
// 010101...
// ```

// 2. Bắt đầu bằng `'1'`:

// ```
// 101010...
// ```

// ---

// ### 📥 Ví dụ 1

// ```
// Input: s = "0100"
// ```

// So sánh với 2 mẫu chuẩn:

// * So với `"0101"` → chỉ sai 1 ký tự (vị trí cuối)
// * So với `"1010"` → sai nhiều hơn

// ✅ Kết quả: `1`

// ---

// ### 📥 Ví dụ 2

// ```
// Input: s = "10"
// ```

// Chuỗi đã là `"10"` → đúng dạng xen kẽ rồi.

// ✅ Kết quả: `0`

// ---

// ### 📥 Ví dụ 3

// ```
// Input: s = "1111"
// ```

// So với:

// * `"1010"` → sai 2 vị trí
// * `"0101"` → sai 2 vị trí

// ✅ Kết quả: `2`

// ---

// ## 🎯 Tóm lại bản chất bài

// Vì chuỗi alternating chỉ có **2 khả năng duy nhất**, nên:

// 1. Tạo mẫu bắt đầu bằng `'0'`
// 2. Tạo mẫu bắt đầu bằng `'1'`
// 3. Đếm số vị trí khác nhau giữa `s` và mỗi mẫu
// 4. Lấy giá trị nhỏ hơn

// ---

// ## 🧠 Ý tưởng tư duy nhanh

// Giả sử vị trí i:

// * Nếu bắt đầu bằng `'0'`

// * i chẵn → phải là `'0'`
// * i lẻ → phải là `'1'`

// * Nếu bắt đầu bằng `'1'`

// * i chẵn → phải là `'1'`
// * i lẻ → phải là `'0'`

// Chỉ cần duyệt 1 vòng for là xong.

// ---

// Nếu bạn muốn mình giải thích thêm bằng ví dụ cụ thể từng bước (dry run) hoặc
// code Java theo style bạn hay dùng (Scanner, while loop 😄) thì nói mình nhé.
