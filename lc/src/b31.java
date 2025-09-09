
// 2327. Number of People Aware of a Secret(09/09/2025)
import java.util.*;

public class b31 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();

        int delay = sc.nextInt();

        int forget = sc.nextInt();

        int result = peopleAwareOfSecret(n, delay, forget);

        System.out.println(result);

        sc.close();
    }

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        // Hằng số modulo để tránh tràn số, cần thiết cho các bài toán có kết quả lớn
        int MOD = 1_000_000_007;

        // Mảng quy hoạch động: dp[i] lưu số người mới biết bí mật vào ngày i
        int[] dp = new int[n + 1];

        // Khởi tạo: Vào ngày 1, có 1 người duy nhất biết bí mật
        dp[1] = 1;

        // Biến 'sum' theo dõi tổng số người đang chia sẻ bí mật
        long sum = 0;

        // Duyệt qua từng ngày từ ngày 2 đến ngày n
        for (int day = 2; day <= n; day++) {

            // Những người bắt đầu chia sẻ vào ngày 'day'
            // Họ là những người đã biết bí mật vào ngày (day - delay)
            if (day - delay >= 1) {
                sum = (sum + dp[day - delay]) % MOD;
            }

            // Những người ngừng chia sẻ (quên) vào ngày 'day'
            // Họ là những người đã biết bí mật vào ngày (day - forget)
            if (day - forget >= 1) {
                sum = (sum - dp[day - forget] + MOD) % MOD;
            }

            // Số người mới biết bí mật vào ngày 'day'
            // Bằng tổng số người đang chia sẻ của ngày trước đó (được lưu trong 'sum')
            dp[day] = (int) sum;
        }

        // Tính tổng số người đang "aware" (chưa quên) vào ngày thứ n
        // Những người này là những người mới biết bí mật từ ngày (n - forget + 1) đến
        // n.
        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            // Kiểm tra chỉ mục có hợp lệ không
            if (i >= 1) {
                ans = (ans + dp[i]) % MOD;
            }
        }

        return (int) ans;
    }
}
// 📥Input

// Bài có 3
// tham số:

// n→
// số ngày
// cần theo
// dõi.

// Ta bắt
// đầu từ ngày 1(ban đầu có 1
// người biết
// bí mật).

// Đến hết
// ngày n, hỏi
// có bao
// nhiêu người
// còn biết.

// delay→
// số ngày
// phải chờ
// sau khi
// biết bí
// mật mới
// có thể
// chia sẻ.

// forget→
// số ngày
// sau khi
// biết bí
// mật thì

// quên mất (không biết và không thể chia sẻ nữa).

// 📤 Output

// Một số nguyên = tổng số người còn nhớ bí mật tại ngày n.

// Vì số có thể rất lớn → trả về kết quả mod 1e9+7.
// ### Phân tích Thuật toán

// Đây là một thuật toán rất hiệu quả để giải quyết bài toán. Nó dựa trên nguyên
// lý của **quy hoạch động (Dynamic Programming)**, sử dụng một mảng để theo dõi
// số lượng người mới biết bí mật vào mỗi ngày. Thuật toán này không quan tâm
// đến từng cá nhân mà chỉ quan tâm đến tổng số người ở các trạng thái khác
// nhau.

// #### Logic cốt lõi

// * `dp[i]`: Biến này lưu trữ số người **mới biết** bí mật vào ngày thứ `i`.
// * `sum`: Biến này theo dõi tổng số người **đang chia sẻ** bí mật vào cuối mỗi
// ngày.

// Thuật toán mô phỏng sự lây lan của bí mật từng ngày một, từ ngày 2 đến ngày
// `n`.

// #### Quá trình mô phỏng từng ngày

// Tại mỗi ngày `day`:

// 1. **Xác định người bắt đầu chia sẻ**:
// * Những người bắt đầu chia sẻ bí mật vào ngày `day` là những người đã biết bí
// mật vào ngày **`day - delay`**.
// * Bạn cộng số người này (`dp[day - delay]`) vào biến `sum`.

// 2. **Xác định người ngừng chia sẻ**:
// * Những người ngừng chia sẻ bí mật (vì họ đã quên) vào ngày `day` là những
// người đã biết bí mật vào ngày **`day - forget`**.
// * Bạn trừ số người này (`dp[day - forget]`) khỏi biến `sum`.

// 3. **Cập nhật số người mới biết**:
// * Số người mới biết bí mật vào ngày `day` bằng với tổng số người đang chia sẻ
// bí mật vào ngày `day-1`.
// * Biến `sum` đã được cập nhật ở hai bước trên, do đó, bạn gán `dp[day] =
// sum`.

// #### Tính toán kết quả cuối cùng

// Mục tiêu là tìm tổng số người biết bí mật tại ngày `n`. Tuy nhiên, những
// người đã biết bí mật từ lâu và đã quên sẽ không được tính.

// * Một người biết bí mật vào ngày `i` sẽ quên nó vào ngày **`i + forget`**.
// * Do đó, tại ngày `n`, những người **chưa quên** là những người đã biết bí
// mật trong khoảng thời gian từ ngày `n - forget + 1` đến `n`.
// * Thuật toán duyệt qua mảng `dp` từ chỉ số `n - forget + 1` đến `n` và cộng
// dồn các giá trị lại. Tổng này chính là số người biết bí mật tại ngày `n`.

// ### Tổng kết

// Thuật toán của bạn rất thông minh vì nó sử dụng một cách tiếp cận **chênh
// lệch (delta)**. Thay vì tính lại tổng số người đang chia sẻ mỗi ngày, nó chỉ
// cộng thêm những người mới bắt đầu chia sẻ và trừ đi những người đã ngừng chia
// sẻ. Điều này giúp giảm thiểu các phép tính và duy trì hiệu suất cao.

// Độ phức tạp thời gian của thuật toán này là **O(n)** vì nó chỉ cần một vòng
// lặp duy nhất qua các ngày từ 2 đến `n`. Đây là một giải pháp tối ưu cho bài
// toán này.
// Ok👍

// mình sẽ
// giải thích**2327.
// Number of
// People Aware
// of a Secret**
// theo cách**đơn giản, dễ hiểu**nhé.

// ---

// ##🎯
// Đề bài
// nói gì?

// *Ngày 1 có**1
// người biết
// bí mật**.*Sau**delay**ngày→
// người đó**
// bắt đầu
// chia sẻ
// bí mật**
// cho người khác.*Sau**forget**ngày→
// người đó**quên bí mật**(
// không biết nữa,
// cũng không
// chia sẻ nữa).

// 👉Hỏi:**
// Đến ngày
// thứ n, có
// bao nhiêu
// người vẫn
// còn biết
// bí mật?**

// ---

// ##🔎
// Cách hiểu
// qua ví dụ

// ###
// Ví dụ 1

// ```n=6,delay=2,forget=4```

// ***Ngày 1**:
// A biết
// bí mật.***Ngày 2**:
// A chưa
// thể chia

// sẻ (chưa đủ delay=2).
// * **Ngày 3**: A bắt đầu chia sẻ → B biết bí mật.
// * **Ngày 4**:

// * A vẫn còn nhớ → chia sẻ tiếp → C biết.
// * B chưa thể chia

// sẻ (vừa mới biết).
// * **Ngày 5**:

// *

// A quên (forget=4 → quên sau 4 ngày).
// * B bắt đầu chia sẻ → D biết.
// * C chưa thể chia sẻ.
// * **Ngày 6**:

// * B còn nhớ → chia sẻ tiếp → E biết.
// * C bắt đầu chia sẻ → F biết.

// 👉 Đến **ngày 6**, người biết bí mật: B, C, D, E, F = **5 người**.

// ---

// ### Ví dụ 2

// ```
// n = 4, delay = 1, forget = 3
// ```

// * **Ngày 1**: A biết bí mật.
// * **Ngày 2**: A chia

// sẻ ngay (delay=1) → B biết.
// * **Ngày 3**:

// *

// A quên (forget=3 → sau 3 ngày).
// * B chia sẻ → C biết.
// * **Ngày 4**:

// * B quên.
// * C chia sẻ → D biết.

// 👉 Đến ngày 4, người biết bí mật: C, D = **2 người**.

// ---

// ## ✅ Tóm gọn

// * Mỗi người có **hai mốc thời gian** sau khi biết bí mật:

// * Sau `delay` ngày: bắt đầu chia sẻ.
// * Sau `forget` ngày: quên luôn.
// * Cần đếm số người **còn nhớ** đến ngày `n`.

// ---

// Bạn có muốn mình minh họa thêm bằng **timeline bảng (ngày 1 → ngày n)** để
// nhìn trực quan hơn không?
