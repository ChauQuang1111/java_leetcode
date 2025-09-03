
// 3027. Find the Number of Ways to Place People II(03/09/2025)
import java.util.*;

public class b25 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[][] points = new int[n][2];

        // Nhập từng điểm (x, y)
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextInt(); // x
            points[i][1] = sc.nextInt(); // y
        }

        // Gọi hàm và in kết quả

        int result = numberOfPairs(points);
        System.out.println(result);

        sc.close();
    }

    public static int numberOfPairs(int[][] points) {
        // Sắp xếp các điểm: theo x tăng dần, nếu x bằng nhau thì y giảm dần
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return b[1] - a[1];
            }
        });

        int n = points.length, ans = 0;
        // Duyệt qua các điểm để chọn điểm bắt đầu (điểm "trái")
        for (int i = 0; i < n; i++) {
            // top là tọa độ y của điểm bắt đầu (points[i])
            int top = points[i][1];
            // bottom là tọa độ y của điểm "đáy", ban đầu được gán giá trị nhỏ nhất
            int bottom = Integer.MIN_VALUE;
            // Duyệt từ điểm i+1 để chọn điểm "phải"
            for (int j = i + 1; j < n; j++) {
                // Kiểm tra xem điểm j có nằm trong dải y hợp lệ không
                if (points[j][1] <= top && points[j][1] > bottom) {
                    // Cập nhật điểm "đáy" mới, vì tìm thấy một điểm hợp lệ.
                    bottom = points[j][1];
                    ans++; // Tăng biến đếm, vì tìm thấy một cặp hợp lệ
                }
                // Nếu điểm "đáy" bằng điểm "đỉnh", không cần duyệt tiếp
                if (bottom == top)
                    break;
            }
        }
        return ans;
    }
}

// Bạn được
// cho một
// mảng các
// tọa độ, gọi là`points`,
// trong đó
// mỗi phần
// tử là
// một cặp`[x,y]`
// biểu thị
// tọa độ
// của một
// người.

// Mục tiêu
// của bạn
// là tìm
// số cách
// để chọn**
// hai người**(
// gọi là
// người A
// và người B)
// thỏa mãn
// các điều
// kiện sau:

// 1.**
// Người B
// nằm ở
// phía Đông
// Bắc của
// người A:**
// Điều này
// có nghĩa
// là tọa độ`x`
// của người
// B phải
// lớn hơn
// hoặc bằng`x`
// của người

// A (`xB >= xA`), và tọa độ `y` của người B phải lớn hơn hoặc bằng `y` của
// người A (`yB >= yA`).
// 2. **Không có người nào khác ở trong "hộp" của họ:** Giả sử bạn vẽ một hình
// chữ nhật với hai đỉnh đối diện là người A và người B. Hình chữ nhật này được
// định nghĩa bởi các cạnh có tọa độ `x` từ `xA` đến `xB` và `y` từ `yA` đến
// `yB`. Điều kiện thứ hai yêu cầu không có bất kỳ người nào khác trong danh
// sách `points` nằm bên trong hình chữ nhật

// này (tức là không có người C nào có tọa độ `xC` và `yC` thỏa mãn `xA <= xC <=
// xB` và `yA <= yC <= yB`, ngoại trừ người A và người B).

// ---

// ### Ví dụ minh họa

// Hãy tưởng tượng các điểm được biểu diễn trên một mặt phẳng tọa độ.

// * Giả sử bạn có người A tại tọa độ `(1, 2)` và người B tại `(4, 5)`.
// * Vì `4 >= 1` và `5 >= 2`, nên người B nằm ở phía Đông Bắc của người A.
// * "Hộp" của họ là một hình chữ nhật với các đỉnh có tọa độ `x` từ 1 đến 4, và
// `y` từ 2 đến 5.
// * Bài toán yêu cầu bạn kiểm tra xem có người

// nào khác (ví dụ, người C tại `(2, 3)`) nằm trong hình chữ nhật này không. Nếu
// có, cặp `(A, B)` này không được tính. Nếu không, cặp `(A, B)` này được tính
// là một cách hợp lệ.

// ---

// ### Điểm mấu chốt của bài toán

// * Bạn cần phải duyệt qua tất cả các cặp người có thể có trong mảng `points`.
// * Đối với mỗi cặp, bạn phải kiểm tra hai điều kiện trên.
// * Nếu cả hai điều kiện đều được thỏa mãn, bạn tăng biến đếm lên 1.
// * Kết quả cuối cùng là tổng số cách hợp lệ mà bạn tìm được.

// Đây là một bài toán khá điển hình về việc duyệt qua các cặp điểm và kiểm tra
// một điều kiện hình học. Việc tối ưu thuật toán duyệt sẽ là chìa khóa để giải
// bài toán một cách hiệu quả.

// ## Mô tả bài toán

// Cho một mảng `points` kích thước *n x 2*, đại diện các điểm `(x_i, y_i)`. Bạn
// cần đếm số cặp `(i, j)` sao cho:

// * $x_i \le x_j$,
// * $y_i \ge y_j$,
// * Và giữa các điểm có cùng `x`, chỉ chọn những điểm `j` có `y_j` lớn nhất
// trong phần còn lại (theo từng `i` duyệt).

// Bản chất, bạn sắp xếp các điểm theo tăng dần `x`, và giảm dần `y` để đảm bảo
// chọn việc đếm đúng các cặp thỏa điều kiện, đồng thời tránh đếm trùng với cùng
// `x` – `y`.

// ---

// ## Ý tưởng & giải thuật

// 1. **Sắp xếp** mảng `points` theo:

// * `x` tăng dần,
// * nếu cùng `x`, `y` giảm dần.

// 2. **Duyệt từng điểm `i`**:

// * Khởi tạo `maxY = -∞`,
// * Với mỗi điểm `j > i`:

// * Nếu `y_i >= y_j` và `y_j > maxY`, tăng biến đếm `ans++`, cập nhật `maxY =
// y_j`.

// 3. Kết quả là số cặp thỏa được đếm.

// Độ phức tạp: **O(n²)** thời gian, O(log n) hoặc O(1) không gian thêm (tuỳ
// triển khai). ([walkccc.me][1], [AlgoMonster][2])

// ---

// ## Giải thích bằng tiếng Việt

// * **Sắp xếp** sao cho, với các điểm cùng `x`, bạn bắt đầu xét từ điểm có `y`
// cao nhất → tránh đếm lặp.
// * **maxY** giúp tracking giá trị `y` lớn nhất từng được đếm cho i nào đó, đảm
// bảo chỉ đếm lần đầu với mỗi `y_j` thỏa điều kiện.
// * Khi `points[i][1] >= points[j][1]` (điểm trước cao hơn hoặc bằng), và `y_j`
// chưa bị đếm (lớn hơn `maxY`): ta đếm nó.

// ## Mục tiêu đếm cặp

// Ta cần đếm số cặp điểm $(i, j)$ sao cho:

// * $x_i \le x_j$ (điểm $j$ ở bên phải hoặc cùng trục dọc với $i$),
// * $y_i \ge y_j$ (điểm $j$ không cao hơn $i$),
// * Và với **mỗi** $i$, mỗi “mức cao độ” $y$ ở bên phải chỉ được đếm **một
// lần** (tránh trùng lặp khi có nhiều điểm có cùng $y$).

// Hình học: mỗi cặp hợp lệ tạo thành một “góc chữ L ngược” với $i$ ở trên–trái
// và $j$ ở dưới–phải (hoặc cùng hàng/cột).

// ---

// ## Bước 1 — Sắp xếp để dễ duyệt và tránh đếm trùng

// * Sắp xếp tất cả điểm theo **x tăng dần**.
// * Nếu cùng $x$, sắp xếp theo **y giảm dần**.

// Ý nghĩa:

// * Khi duyệt tăng dần theo chỉ số (sau sắp xếp), mọi $j>i$ đều bảo đảm $x_j
// \ge x_i$.
// * Với các điểm cùng $x$, điểm **cao hơn** (y lớn hơn) luôn đứng **trước**.
// Nhờ vậy, khi xét $i$ trong một cột, ta không bị đếm sai do thứ tự cùng cột.

// ---

// ## Bước 2 — Cố định một điểm gốc $i$

// * Lấy **top** = $y_i$ (độ cao của điểm gốc).
// * Khởi tạo **bottom** = $-\infty$ (chưa chọn mức nào ở bên phải).

// 直感: ta sẽ “quét” sang phải, và mỗi khi gặp một **mức cao độ mới** nằm dưới
// $top$ nhưng **cao hơn** mọi mức đã chọn

// trước đó (tức $> bottom$), ta đếm thêm 1.

// ---

// ## Bước 3 — Quét các điểm bên

// phải $i$ (tăng dần theo chỉ số)

// Với từng điểm $j>i$:

// 1. Kiểm tra $y_j \le \text{top}$: $j$ không được cao hơn $i$.
// 2. Kiểm tra $y_j > \text{bottom}$: $j$ phải **cao hơn** tất cả các điểm ta đã
// chọn trước đó cho cùng $i$.

// Nếu **cả hai** đúng:

// * Đếm thêm 1

// cặp $(i, j)$.
// * Cập nhật **bottom = y\_j** (đánh dấu “mức cao độ cao nhất đã chọn ở dưới
// $i$”).

// Tác dụng của `bottom`:

// * Đảm bảo **mỗi mức $y$** chỉ được đếm **một lần** cho $i$.
// * Nếu có nhiều điểm bên phải có cùng $y$, chỉ điểm gặp đầu

// tiên (theo thứ tự đã sắp) được đếm; các điểm cùng mức đó sau này bị loại vì
// $y_j \le \text{bottom}$.

// ---

// ## Bước 4 — Dừng sớm khi chạm trần

// Nếu bất kỳ lúc nào **bottom == top**:

// * Nghĩa là bạn vừa chọn được một $j$ có $y_j = y_i$.
// * Mọi điểm còn lại đều có $y \le \text{top} = \text{bottom}$, nên **không
// thể** thỏa $y_j > \text{bottom}$ nữa.
// → **Dừng luôn** vòng quét cho $i$ để tiết kiệm thời gian.

// ---

// ## Vì sao sắp xếp “x↑, y↓ (khi cùng x)” là then chốt?

// * Bảo đảm điều kiện $x_i \le x_j$ tự nhiên khi duyệt $j>i$.
// * Với các điểm cùng $x$, điểm có $y$ lớn hơn đứng trước:

// * Khi $i$ là điểm cao hơn, mọi $j$ cùng cột sau nó sẽ có $y_j \le y_i$, nên
// đủ điều kiện chiều cao.
// * Tránh trường hợp một điểm thấp hơn đứng trước làm “ăn mất lượt” của điểm
// cao hơn cùng cột.

// ---

// ## Trực giác tổng thể

// Với mỗi $i$, bạn đang **kẻ một đường ngang** tại $y = y_i$ rồi nhìn sang
// phải:

// * Mỗi lần bạn thấy một “bậc thang” mới (một giá trị $y$ **lớn hơn** mọi $y$
// đã chọn trước đó nhưng vẫn **không vượt quá** $y_i$), bạn đếm một lần.
// * Con trỏ **bottom** ghi nhớ bậc cao nhất đã chọn, buộc dãy các $y$ được đếm
// cho $i$ phải **giảm dần nghiêm ngặt** (strictly decreasing), không bao giờ
// lặp lại mức.

// ---

// ## Độ phức tạp

// * Sắp xếp: $O(n \log n)$.
// * Hai vòng

// lặp lồng (trong trường hợp xấu nhất): $O(n^2)$.
// * Bộ nhớ phụ: $O(1)$.
// * Nhờ điều kiện dừng sớm khi **bottom == top**, nhiều trường hợp thực tế chạy
// nhanh hơn $O(n^2)$.

// ---

// ## Ví dụ

// nhỏ (minh họa ý tưởng)

// Giả sử sau sắp xếp ta có các điểm theo thứ tự:
// $(1,3), (1,1), (2,2), (2,1)$

// * Chọn $i=(1,3)$:

// * Gặp $y=1$ → $1 \le 3$ và $1 > -\infty$ ⇒ đếm, bottom=1
// * Gặp $y=2$ → $2 \le 3$ và $2 > 1$ ⇒ đếm, bottom=2
// * Gặp $y=1$ → $1 \le 3$ nhưng $1 \le 2$ ⇒ bỏ
// * Chọn $i=(1,1)$:

// * Gặp $y=2$ → $2 \nleq 1$ ⇒ bỏ
// * Gặp $y=1$ → $1 \le 1$ và $1 > -\infty$ ⇒ đếm, bottom=1 ⇒ **bottom==top** ⇒
// dừng
// * Chọn $i=(2,2)$:

// * Gặp $y=1$ → $1 \le 2$ và $1 > -\infty$ ⇒ đếm

// Tổng cộng: 4 cặp.

// ---

// Nếu bạn muốn, mình có thể vẽ

// sơ đồ (trục tọa độ/điểm) hoặc lập bảng $i, j, top, bottom$ cho một test cụ
// thể để thấy rõ từng lần tăng đếm.
