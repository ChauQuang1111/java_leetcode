// 3021. Alice and Bob Playing Flower Game(29/08/2025)

import java.util.*;

public class b20 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        long n = sc.nextLong();
        long m = sc.nextLong();
        long res = flowerGame(n, m);
        sc.close();
        System.out.println(res);
    }

    /**
     * @param n Số hoa của Alice
     * @param m Số hoa của Bob
     * @return Tổng số cách để Alice và Bob chọn hoa sao cho tổng số hoa là số lẻ
     */
    public static long flowerGame(long n, long m) {
        // Chuyển đổi n và m sang kiểu long để tránh tràn số (overflow)
        // trong quá trình tính toán.
        long longN = n;
        long longM = m;

        // Đếm số lượng số lẻ và số chẵn trong khoảng [1, n].
        long oddN = (longN + 1) / 2;
        long evenN = longN / 2;

        // Đếm số lượng số lẻ và số chẵn trong khoảng [1, m].
        long oddM = (longM + 1) / 2;
        long evenM = longM / 2;

        // Trường hợp 1: Alice chọn lẻ, Bob chọn chẵn.
        long ways1 = oddN * evenM;

        // Trường hợp 2: Alice chọn chẵn, Bob chọn lẻ.
        long ways2 = evenN * oddM;

        // Tổng số cách là tổng của hai trường hợp.
        return ways1 + ways2;
    }
}

// Hãy lấy
// một ví
// dụ cụ
// thể để
// bạn dễ
// hình dung
// hơn.

// Giả sử`n=5`và`m=4`.

// Theo bài
// toán gốc, chúng
// ta cần
// tìm số
// cách để

// Alice (`x` bông hoa, $1 \le x \le 5$)

// và Bob (`y` bông hoa, $1 \le y \le 4$) chọn hoa sao cho `x + y` là số lẻ.

// ### Bước 1: Phân tích các số chẵn và lẻ

// * **Alice (`n = 5`):**
// * Các số lẻ: 1, 3, 5. Số lượng: **3**
// * Các số chẵn: 2, 4. Số lượng: **2**

// * **Bob (`m = 4`):**
// * Các số lẻ: 1, 3. Số lượng: **2**
// * Các số chẵn: 2, 4. Số lượng: **2**

// ### Bước 2: Áp dụng công thức

// Bây giờ, chúng ta áp dụng công thức từ đoạn mã:

// * **Số lẻ của Alice:** `oddN = (5 + 1) / 2 = 3`
// * **Số chẵn của Alice:** `evenN = 5 / 2 = 2` (phép chia nguyên)

// * **Số lẻ của Bob:** `oddM = (4 + 1) / 2 = 2` (phép chia nguyên)
// * **Số chẵn của Bob:** `evenM = 4 / 2 = 2`

// ### Bước 3: Tính toán các trường hợp

// * **Trường hợp 1:** Alice chọn số lẻ, Bob chọn số chẵn.
// * Số cách = (số lẻ của Alice) * (số chẵn của Bob)
// * `ways1 = oddN * evenM = 3 * 2 = 6`
// * Các cặp `(x, y)` có thể: `(1, 2), (1, 4), (3, 2), (3, 4), (5, 2), (5, 4)`

// * **Trường hợp 2:** Alice chọn số chẵn, Bob chọn số lẻ.
// * Số cách = (số chẵn của Alice) * (số lẻ của Bob)
// * `ways2 = evenN * oddM = 2 * 2 = 4`
// * Các cặp `(x, y)` có thể: `(2, 1), (2, 3), (4, 1), (4, 3)`

// ### Bước 4: Tổng hợp kết quả

// Tổng số cách để `x + y` là số lẻ là tổng của hai trường hợp:

// `6 + 4 = 10`

// Vậy, có 10 cách để Alice và Bob chọn hoa sao cho tổng số hoa là số lẻ. Công
// thức trong mã nguồn đã tính toán chính xác điều này một cách hiệu quả.
// Trong bài
// toán này, việc
// chia thành
// hai khoảng`1-n`và`1-m`là**
// bắt buộc**
// vì hai lý do chính:

// 1.**
// Nguồn lựa
// chọn khác nhau:***
// Alice chỉ
// có thể
// chọn số hoa`x`
// trong phạm
// vi từ`1`đến`n`.*
// Bob chỉ
// có thể
// chọn số hoa`y`
// trong phạm
// vi từ`1`đến`m`.

// Số lượng
// hoa tối
// đa mà
// mỗi người
// có thể
// chọn là

// khác nhau (`n` có thể không bằng `m`). Do đó, số lượng các số lẻ và chẵn có
// sẵn cho Alice và Bob là khác nhau.

// 2. **Độc lập trong việc chọn:**
// * Lựa chọn

// của Alice (`x`) không ảnh hưởng đến lựa chọn

// của Bob (`y`), và ngược lại.
// * Chúng ta cần tìm tất cả các cặp `(x, y)` có thể. Theo quy tắc nhân trong tổ
// hợp, để tìm tổng số cặp này, chúng ta phải tính số cách chọn của Alice và số
// cách chọn của Bob một cách độc lập, sau đó nhân chúng lại.

// ### Ví dụ minh họa

// Hãy xem lại ví dụ `n = 5` và `m = 4`:

// * **Alice** có 5 bông hoa, vì vậy cô ấy có thể chọn `1, 2, 3, 4, 5`. Số lựa
// chọn của cô ấy là 5.
// * **Bob** có 4 bông hoa, vì vậy anh ấy có thể chọn `1, 2, 3, 4`. Số lựa chọn
// của anh ấy là 4.

// Nếu không chia thành hai khoảng riêng biệt, chúng ta sẽ không thể xác định
// chính xác số lượng các số lẻ và chẵn cho mỗi người.

// * **Số lẻ của Alice:** 3 cách (`1, 3, 5`)
// * **Số chẵn của Alice:** 2 cách (`2, 4`)
// * **Số lẻ của Bob:** 2 cách (`1, 3`)
// * **Số chẵn của Bob:** 2 cách (`2, 4`)

// Vì thế, việc chia bài toán thành hai khoảng riêng biệt giúp ta áp dụng công
// thức một cách chính xác cho từng người chơi, sau đó kết hợp các lựa chọn lại
// để có được tổng số cách hợp lệ
// Lý do

// chúng ta
// không thể
// gộp hai khoảng`1-n`và`1-m`
// lại thành
// một là vì**
// Alice và
// Bob có
// các lựa
// chọn độc
// lập và
// số lượng
// lựa chọn
// của họ
// có thể
// khác nhau.**

// Hãy tưởng
// tượng một
// ví dụ
// để hiểu
// rõ hơn:

// *
// Alice có**5**

// bông hoa (`n = 5`). Cô ấy có thể chọn `1, 2, 3, 4, 5` bông.
// * Bob có **4**

// bông hoa (`m = 4`). Anh ấy chỉ có thể chọn `1, 2, 3, 4` bông.

// Nếu chúng ta gộp lại và chỉ xét một khoảng, chẳng hạn là `1-5`, chúng ta sẽ
// gặp vấn đề:

// * **Chúng ta sẽ tính sai số cách của Bob.** Trong khoảng `1-5`, có 3

// số lẻ (`1, 3, 5`) và 2

// số chẵn (`2, 4`). Nhưng Bob không thể chọn 5 bông hoa. Nếu chúng ta dùng số
// liệu này để tính cho Bob, kết quả sẽ sai.

// Để tìm tổng số cặp `(x, y)` thỏa mãn điều kiện, chúng ta cần biết chính xác:

// 1. Có bao nhiêu cách để Alice chọn một số lẻ từ `1-n`?
// 2. Có bao nhiêu cách để Bob chọn một số chẵn từ `1-m`?
// 3. Có bao nhiêu cách để Alice chọn một số chẵn từ `1-n`?
// 4. Có bao nhiêu cách để Bob chọn một số lẻ từ `1-m`?

// Việc chia thành hai khoảng riêng biệt `1-n` và `1-m` giúp chúng ta trả lời
// chính xác từng câu hỏi trên. Sau khi đã có các con số này, chúng ta chỉ cần
// nhân chúng lại và cộng các trường hợp để ra kết quả cuối cùng.

// Một trong
// những điểm
// hay nhất
// mà bạn
// có thể
// học từ
// đoạn mã
// và thuật
// toán này
// là cách
// tiếp cận**
// toán học
// và tư
// duy tổ hợp**
// để giải
// quyết một
// bài toán
// lập trình.

// Thay vì
// sử dụng
// một phương
// pháp duyệt
// hoặc lặp

// lại (ví dụ: vòng lặp `for` để kiểm tra từng cặp `x` và `y`), thuật toán đã
// chọn một giải pháp hiệu quả hơn nhiều. Bằng cách nhận ra rằng vấn đề có thể
// được quy về việc đếm số lượng các số chẵn và lẻ trong hai tập hợp riêng biệt,
// nó biến một bài toán phức tạp thành một phép tính đơn giản.

// ### Những điểm đáng học hỏi:

// * **Tư duy Toán học**: Thuật toán đã nhận ra một quy tắc toán học cơ bản: `lẻ
// + chẵn = lẻ` và `chẵn + lẻ = lẻ`. Từ đó, nó chia bài toán lớn thành các bài
// toán con dễ giải quyết hơn.
// * **Hiệu quả về thời gian**: Cách tiếp cận này có độ phức tạp thời gian là
// **O(1)**. Cho dù `n` và `m` có lớn đến hàng tỷ, chương trình cũng chỉ mất một
// vài phép tính cơ bản để tìm ra kết quả. Đây là một cải tiến vượt trội so với
// các giải pháp lặp lại, vốn có thể tốn rất nhiều thời gian và tài nguyên.
// * **Sự thanh lịch và gọn gàng**: Mã nguồn cực kỳ ngắn gọn và dễ hiểu. Nó thể
// hiện cách một giải pháp toán học tốt có thể làm cho code trở nên sạch và hiệu
// quả hơn.

// Bài học rút ra là: Khi đối mặt với một bài toán lập trình, đừng chỉ nghĩ về
// các cấu trúc dữ liệu hay thuật toán phổ biến ngay lập tức. Hãy dành thời gian
// phân tích xem liệu có một quy luật hoặc công thức toán học nào có thể giải
// quyết vấn đề một cách trực tiếp hay không. Điều này thường dẫn đến những giải
// pháp tối ưu và hiệu quả nhất.