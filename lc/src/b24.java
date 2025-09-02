
// 3025. Find the Number of Ways to Place People I(02/09/2025)
import java.util.*;

public class b24 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Nhập số lượng điểm:");
        int n = sc.nextInt();

        int[][] points = new int[n][2];

        for (int i = 0; i < n; i++) {

            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }

        int result = numberOfPairs(points);
        System.out.println(result);

        sc.close();
    }

    public static int numberOfPairs(int[][] points) {
        // Bước 1: Sắp xếp các điểm
        // Sắp xếp tăng dần theo tọa độ x.
        // Nếu tọa độ x bằng nhau, sắp xếp giảm dần theo tọa độ y.
        // Việc sắp xếp này giúp chúng ta không cần phải kiểm tra điều kiện xA <= xB
        // nữa.
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });

        int count = 0;
        int n = points.length;

        // Vòng lặp ngoài: Duyệt qua từng điểm, coi nó là điểm A (trên cùng bên trái)
        for (int i = 0; i < n; i++) {
            int y0 = points[i][1];

            // "bot" (bottom): Giới hạn dưới của tọa độ y cho các điểm B hợp lệ.
            // Khởi tạo là âm vô cùng để điểm B đầu tiên luôn thỏa mãn điều kiện y > bot.
            double bot = Double.NEGATIVE_INFINITY;

            // "top": Giới hạn trên của tọa độ y cho các điểm B hợp lệ.
            // Mọi điểm B phải có y <= y0 (tọa độ y của điểm A).
            double top = y0;

            // Vòng lặp trong: Duyệt qua các điểm sau A để tìm điểm B (dưới cùng bên phải)
            for (int j = i + 1; j < n; j++) {
                int y1 = points[j][1];

                // Kiểm tra điều kiện chính:
                // 1. y1 <= top (tức y1 <= y0): Đảm bảo B nằm dưới hoặc cùng mức với A.
                // 2. y1 > bot: Đảm bảo không có điểm nào khác (đã được duyệt)
                // nằm giữa A và B theo trục y.
                if (y1 <= top && y1 > bot) {
                    // Nếu cả hai điều kiện trên đều đúng, ta tìm thấy một cặp hợp lệ.
                    count++;

                    // Cập nhật bot: Đặt giới hạn dưới mới cho các điểm B sau này.
                    // bot = y1 đảm bảo rằng bất kỳ điểm nào có y <= y1
                    // sẽ không được đếm vì chúng sẽ nằm trong hình chữ nhật đã tìm thấy.
                    bot = y1;

                    // Tối ưu hóa: Nếu y1 == top, tức A và B có cùng tọa độ y.
                    // Nhờ cách sắp xếp, mọi điểm tiếp theo (j > i) sẽ có x >= x1.
                    // Nếu y của chúng nhỏ hơn y1, chúng sẽ nằm bên trong hình chữ nhật.
                    // Do đó, không có điểm nào khác có thể làm B hợp lệ nữa, ta có thể dừng vòng
                    // lặp.
                    if (y1 == top) {
                        break;
                    }
                }
            }
        }
        return count;
    }

}

// Thuật toán này giải quyết bài toán đếm số cặp điểm tạo thành một hình chữ
// nhật "rỗng" bằng một phương pháp thông minh và hiệu quả. Thay vì thử mọi cặp
// điểm, nó tận dụng việc sắp xếp để đơn giản hóa quá trình kiểm tra.

// ### Sắp xếp thông minh

// Đầu tiên, thuật toán sắp xếp tất cả các điểm theo một quy tắc đặc biệt:
// * **Ưu tiên 1**: Sắp xếp các điểm theo thứ tự **tăng dần của tọa độ x** (từ
// trái sang phải).
// * **Ưu tiên 2**: Nếu hai điểm có cùng tọa độ x, sắp xếp chúng theo thứ tự
// **giảm dần của tọa độ y** (từ trên xuống dưới).

// Cách sắp xếp này rất quan trọng. Khi chúng ta duyệt qua các điểm theo thứ tự
// này, một điểm bất kỳ `A` luôn nằm bên trái hoặc trên cùng đường thẳng dọc với
// tất cả các điểm sau nó. Điều này đảm bảo điều kiện `x_A <= x_B` luôn đúng,
// giúp chúng ta không cần phải kiểm tra lại nó nữa.

// ### Tìm kiếm và đếm cặp

// Sau khi sắp xếp, thuật toán thực hiện hai vòng lặp lồng nhau:
// 1. **Vòng lặp ngoài**: Chọn một điểm `A` (là điểm góc trên cùng bên trái của
// hình chữ nhật).
// 2. **Vòng lặp trong**: Duyệt qua tất cả các điểm sau `A` để tìm một điểm `B`
// có thể là góc dưới cùng bên phải.

// Với mỗi cặp `(A, B)`, thuật toán sử dụng hai biến để kiểm soát các giới hạn
// theo trục y:
// * **`top` (giới hạn trên)**: Chính là tọa độ `y` của điểm `A`. Mọi điểm `B`
// hợp lệ phải có tọa độ `y` nhỏ hơn hoặc bằng `top`.
// * **`bot` (giới hạn dưới)**: Ban đầu là âm vô cùng. Biến này sẽ được cập nhật
// mỗi khi tìm thấy một điểm `B` hợp lệ.

// Để một cặp `(A, B)` được tính là hợp lệ, điểm `B` phải thỏa mãn hai điều kiện
// về tọa độ `y`:
// * **`y_B <= top`**: Đảm bảo `B` nằm dưới hoặc ngang với `A`.
// * **`y_B > bot`**: Điều kiện này đảm bảo rằng không có điểm nào khác nằm giữa
// `A` và `B` theo trục y. Khi tìm thấy một điểm `B` hợp lệ, chúng ta cập nhật
// `bot` bằng `y_B`. Điều này tạo ra một "giới hạn" mới, đảm bảo rằng tất cả các
// điểm `B` tiếp theo phải có tọa độ `y` nhỏ hơn `y_B` để không bị đếm.

// Nếu cả hai điều kiện trên đều đúng, cặp `(A, B)` được đếm là một cặp hợp lệ.

// ### Tối ưu hóa

// Có một tối ưu hóa quan trọng: nếu điểm `A` và `B` có cùng tọa độ `y`, thuật
// toán sẽ dừng vòng lặp tìm kiếm `B` và chuyển sang điểm `A` tiếp theo. Điều
// này là vì nhờ cách sắp xếp, tất cả các điểm tiếp theo đều sẽ có `y` nhỏ hơn
// `y_A` (hoặc `y_B`), và do đó, chúng sẽ nằm trong hình chữ nhật tạo bởi `A` và
// `B`, làm cho cặp đó không hợp lệ.