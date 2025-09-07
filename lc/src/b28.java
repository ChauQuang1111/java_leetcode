// 3495. Minimum Operations to Make Array Elements Zero(06/09/2025)

import java.util.*;

public class b28 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int q = sc.nextInt(); // số lượng queries
        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt(); // l
            queries[i][1] = sc.nextInt(); // r
        }
        long res = minOperations(queries);
        sc.close();
        System.out.println(res);
    }

    public static long minOperations(int[][] queries) {
        long res = 0;
        // Duyệt qua từng query và cộng dồn kết quả
        for (int[] query : queries)
            res += minOperationsOneQuery(query);
        return res;
    }

    public static long minOperationsOneQuery(int[] query) {
        long res0 = 0; // số lần chia cần thiết cho phần tử đầu tiên query[0]
        long x = 1; // mốc ban đầu (1, 4, 16, 64, ...) biểu diễn lũy thừa của 4

        // Tìm xem query[0] nằm trong khoảng nào của [1,4), [4,16), [16,64), ...
        // res0 = số lần chia cần thiết cho query[0]
        while (x < query[0]) {
            res0++;
            x *= 4;
        }

        long res1 = res0; // res1: số bước chia cho khoảng hiện tại
        long res = 0; // tổng số lần chia cho toàn khoảng [l, r]
        long prev = query[0]; // điểm bắt đầu của khoảng ta đang tính

        // Xử lý từng đoạn [prev, x) cho đến khi vượt quá query[1]
        while (x <= query[1] * 4L) {
            // Với đoạn [prev, min(x, r+1)), mỗi số trong đoạn này cần res1 lần chia
            res += res1 * (Math.min(x, query[1] + 1) - prev);

            // Cập nhật prev sang điểm tiếp theo
            prev = x;

            // Sang đoạn tiếp theo thì cần thêm 1 lần chia
            res1++;

            // Nhảy sang mốc tiếp theo (nhân 4)
            x *= 4;
        }

        // Vì mỗi thao tác chia được 2 số cùng lúc
        // => số thao tác = ceil(res / 2) = (res + 1) / 2
        return (res + 1) / 2;
    }

}
// Hay,

// bạn để
// ý rất kỹ👍
// mình giải
// thích rõ chỗ`4L`
// này nhé:

// Trong code:

// ```java while(x<=query[1]*4L)
// {
// ```

// ---

// ### 1. Tại sao lại nhân với `4`?

// Như mình giải thích, ta đang xét các **mốc lũy thừa của 4**:

// ```
// 1, 4, 16, 64, ...
// ```

// Khi xử lý đoạn `[l, r]`, ta muốn bao phủ hết đoạn này bằng các mốc.

// * Nếu dừng ở `x <= query[1]`, thì có thể bị thiếu đoạn cuối cùng.
// * Vì thế, nhân thêm `4` để chắc chắn là **mốc cuối cùng (lớn hơn r) đã được
// xét**, đảm bảo vòng lặp bao trùm hết `[l, r]`.

// ---

// ### 2. Tại sao viết `4L` mà không phải `4`?

// * `query[1]` là `int`.
// * Nếu viết `query[1] * 4`, kết quả là `int`, có thể bị **tràn số** khi
// `query[1]` lớn (vì `int` chỉ tối đa \~2.1 tỉ).
// * Viết `4L` ép kiểu về `long`, nên `query[1] * 4L` là `long`, an toàn khi so
// sánh với `x` (vốn là `long`).

// ---

// ✅ Tóm lại:

// ```java
// while (x <= query[1] * 4L)
// ```

// * `*4` để đảm bảo lấy đủ các mốc 4^k bao trùm đến sau r.
// * `L` để ép kiểu về `long`, tránh tràn số và đồng bộ kiểu dữ liệu với biến
// `x`.

// À mình
// giải thích
// kỹ hơn
// chỗ này nhé👌

// Trong code:

// ```java
// long x = 1; // mốc ban đầu
// ```

// Sau đó
// trong vòng while
// ta có:

// ```
// java x*=4;```

// →
// Nghĩa là
// các giá
// trị của`x`
// sẽ lần
// lượt là:

// ```1→4→16→64→256→...```

// Đây chính là**các lũy
// thừa của 4**:

// $$
// x = 4^0,4^1,4^2,4^3,...$$

// ---

// ###
// Ý nghĩa
// của mốc`x`

// Nó dùng
// để chia
// dải số nguyên`[l,r]`
// thành từng**khoảng**
// mà trong
// mỗi khoảng,**số lần chia 4
// để về 0
// là giống nhau**.

// Ví dụ:

// *
// Các số trong**\[1,3]**
// chỉ cần**1
// lần chia**(`n/4=0`).*
// Các số trong**\[4,15]**cần**2
// lần chia**(`n/4→n/16→0`).*
// Các số trong**\[16,63]**cần**3
// lần chia**.*
// Các số trong**\[64,255]**cần**4
// lần chia**.

// 📌
// Nhận xét:
// Các khoảng
// này đúng
// bằng các đoạn\[1,4),\[4,16),\[16,64),\[64,256),…→
// chính là
// các mốc
// lũy thừa của 4.

// Vậy nên`x`
// chạy qua
// các mốc**1,4,16,64,...**
// để giúp
// ta xác
// định ranh
// giới giữa
// những đoạn
// có cùng
// số bước chia.

// Ok,
// mình giải
// thích đề bài**3495.
// Minimum Operations
// to Make
// Array Elements

// Zero (Hard)** cho bạn thật dễ hiểu nhé 👍

// ---

// ### Đề bài nói gì?

// Bạn được cho một mảng `queries`, mỗi phần tử `queries[i] = [l, r]`.
// Với mỗi truy vấn `[l, r]`, ta tạo ra một mảng `nums = [l, l+1, ..., r]`.

// Bây giờ ta cần thực hiện **các phép biến đổi** trên mảng `nums` cho đến khi
// **tất cả phần tử = 0**.

// ---

// ### Phép biến đổi là gì?

// Trong **một lần thao tác**, ta chọn **2 số bất kỳ**

// trong mảng (giả sử là `a` và `b`),
// rồi thay cả 2 số đó bằng:

// * `floor(a / 4)`
// * `floor(b / 4)`

// (Nghĩa là chia cho 4 và làm tròn xuống).

// Ta lặp lại thao tác này cho đến khi toàn bộ mảng trở thành `0`.

// ---

// ### Nhiệm vụ của bạn:

// * Với **mỗi query `[l, r]`**, tính số thao tác tối thiểu cần thực hiện để
// biến toàn bộ mảng `[l, ..., r]` thành 0.
// * Sau đó, cộng kết quả của tất cả các query lại và trả về.

// ---

// ### Ví dụ

// Giả sử:

// ```
// queries = [[1,2], [2,4]]
// ```

// #### Query 1: \[1, 2]

// Mảng ban đầu: `[1, 2]`

// * 1 chia 4 → 0
// * 2 chia 4 → 0
// → Sau **1 lần thao tác** cả mảng thành `[0, 0]`.

// 👉 Kết quả query 1 = 1

// ---

// #### Query 2: \[2, 3, 4]

// Mảng ban đầu: `[2, 3, 4]`

// * Với số 2: 2 → 0 (chỉ 1 lần chia)
// * Với số 3: 3 → 0 (1 lần chia)
// * Với số 4: 4 → 1 → 0 (cần 2 lần chia)

// Tổng cộng có **4 “lần chia” riêng lẻ**.
// Vì mỗi thao tác xử lý **2 số một lúc**, nên số thao tác tối thiểu là:

// ```
// ceil(4 / 2) = 2
// ```

// 👉 Kết quả query 2 = 2

// ---

// ### Tổng kết

// ```
// queries = [[1,2], [2,4]]
// query 1 → 1 thao tác
// query 2 → 2 thao tác
// Kết quả cuối cùng = 1 + 2 = 3
// ```

// ---

// 👉 Như vậy, đề bài yêu cầu ta:

// * Với từng `[l, r]`, đếm số lần “chia cho 4 đến khi về 0” cho tất cả các số
// trong khoảng.
// * Sau đó, vì **mỗi thao tác xử lý được 2 số cùng lúc**, nên ta lấy `(tổng số
// lần chia + 1) // 2`.
// * Cuối cùng cộng kết quả của tất cả các query lại.

// ---

// Bạn có muốn mình vẽ **sơ đồ trực quan** cho ví

// dụ này (kiểu cây chia số → 0) để dễ hình dung hơn không?
// À 👌 đúng rồi, để mình chỉnh lại cho bạn.

// Bạn đưa **`queries = [[1,2], [2,4]]`**.
// Nghĩa là:

// * Query 1 = `[1,2]` → mảng = `[1, 2]`
// * Query 2 = `[2,4]` → mảng = `[2, 3, 4]`

// 👉 Số **3** xuất hiện là vì đề bài định nghĩa: với query `[l, r]` thì ta lấy
// **tất cả các số nguyên từ l đến

// r (bao gồm cả l và r)**.

// Ví dụ:

// * `[1,2]` → `[1,2]`
// * `[2,4]` → `[2,3,4]`

// Nên số 3 là do khoảng `[2,4]` chứa luôn nó, chứ không phải mình tự thêm.

// ---

// Ok 👍 mình giải thích rõ ràng và chậm rãi đoạn **query 1 = \[1,2]** cho bạn
// nhé.

// ---

// ### Query 1: `[1, 2]`

// Từ query này, ta tạo mảng:

// ```
// nums = [1, 2]
// ```

// ---

// ### Quy tắc thao tác

// * Mỗi thao tác: chọn 2 số `a, b` trong mảng, thay chúng bằng `floor(a/4)` và
// `floor(b/4)`.
// * Mục tiêu: đưa toàn bộ mảng về `[0,0]`.

// ---

// ### Thực hiện

// Mảng hiện tại: `[1, 2]`

// 👉 Ta chọn luôn cả 2 số (vì chỉ có 2 phần tử thôi):

// * Với số `1`:
// `floor(1 / 4) = 0`

// * Với số `2`:
// `floor(2 / 4) = 0`

// Sau 1 thao tác, mảng biến thành:

// ```
// [0, 0]
// ```

// ---

// ### Kết luận

// * Chỉ cần **1 lần thao tác** là toàn bộ mảng thành 0.
// 👉 Kết quả cho query `[1,2]` là **1**.

// ---

// Bạn có muốn mình giải thích thêm **tại sao không thể ít hơn 1 thao tác**
// không (tức là bắt buộc phải làm ít nhất 1 lần)?
// Rồi 👍 mình sẽ diễn giải lại đoạn này thật chặt chẽ để bạn dễ hình dung.

// ---

// ### Query 2: `[2, 4]`

// → Mảng tạo ra là:

// ```
// nums = [2, 3, 4]
// ```

// ---

// ## 🔹 Bước 1: Đếm số lần **chia cho 4** để mỗi số về 0

// * **Số 2**
// 2 ÷ 4 = 0 → chỉ cần **1 lần chia**

// * **Số 3**
// 3 ÷ 4 = 0 → cũng chỉ cần **1 lần chia**

// * **Số 4**
// 4 ÷ 4 = 1
// 1 ÷ 4 = 0
// → cần **2 lần chia**

// 👉 Tổng cộng: `1 + 1 + 2 = 4 lần chia riêng lẻ`

// ---

// ## 🔹 Bước 2: Liên hệ với “một thao tác” của đề bài

// * Đề bài không cho ta **chia từng số riêng lẻ**, mà bắt buộc phải chọn **2 số
// một lúc** để xử lý.
// * Một thao tác tương đương với **2 lần chia** (vì xử lý 2 số).

// Vậy, muốn thực hiện đủ **4 lần chia riêng lẻ** cho `[2,3,4]`, ta cần:

// ```
// số thao tác tối thiểu = ceil(4 / 2) = 2
// ```

// ---

// ## ✅ Kết quả cho Query `[2,4]` = 2

// ---

// 📌 Cách hiểu:

// * **Bước 1**: Đếm xem mỗi số phải chia bao nhiêu lần mới về 0.
// * **Bước 2**: Vì mỗi thao tác xử lý được 2 số, nên số thao tác = tổng số lần
// chia riêng lẻ / 2 (làm tròn lên).

// ---

// Bạn có muốn mình **mô phỏng cụ thể 2 thao tác** cho `[2,3,4]` (chọn số nào ở
// thao tác 1, thao tác 2) để thấy rõ hơn không?
