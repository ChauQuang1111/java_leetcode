
// 3459. Length of Longest V-Shaped Diagonal Segment(27/08/2025)
import java.util.*;

public class b18 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int m = sc.nextInt();

        int n = sc.nextInt();

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int result = lenOfVDiagonal(grid);
        System.out.println(result);
    }

    public static final int[][] DIRS = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

    public static int lenOfVDiagonal(int[][] grid) {
        // Lấy số hàng của lưới
        int m = grid.length;

        // Nếu số hàng là 0, tức là lưới rỗng, trả về 0
        if (m == 0)
            return 0;

        // Lấy số cột của lưới từ hàng đầu tiên
        int n = grid[0].length;

        // Nếu số cột là 0, tức là lưới rỗng, trả về 0
        if (n == 0)
            return 0;

        // Bảng ghi nhớ để lưu trữ kết quả của các bài toán con.
        // Chiều thứ ba kết hợp 'k' (hướng) và 'canTurn' (cờ rẽ)
        // thành một số nguyên duy nhất để tăng hiệu quả.
        int[][][] memo = new int[m][n][1 << 3];
        int ans = 0;

        // Duyệt qua từng ô của lưới
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Hình chữ 'V' phải bắt đầu bằng một '1'
                if (grid[i][j] != 1) {
                    continue;
                }

                // Tính độ dài lý thuyết tối đa cho mỗi hướng bắt đầu.
                // Đây là một tối ưu hóa.
                int[] maxs = { m - i, j + 1, i + 1, n - j };

                // Thử tất cả bốn hướng chéo ban đầu có thể
                for (int k = 0; k < 4; k++) {
                    // Tối ưu hóa 1: Cắt tỉa tìm kiếm nếu độ dài tối đa lý thuyết
                    // không lớn hơn câu trả lời tốt nhất hiện tại.
                    if (maxs[k] > ans) {
                        // Bắt đầu DFS từ (i, j).
                        // `k`: hướng bắt đầu
                        // `1`: được phép rẽ một lần
                        // `2`: giá trị tiếp theo cần tìm là 2
                        // Chúng ta cộng 1 vào kết quả của DFS để tính cả ô bắt đầu.
                        ans = Math.max(ans, dfs(i, j, k, 1, 2, grid, memo) + 1);
                    }
                }
            }
        }
        return ans;
    }

    public static int dfs(int i, int j, int k, int canTurn, int target, int[][] grid, int[][][] memo) {
        // Di chuyển đến ô tiếp theo theo hướng hiện tại
        i += DIRS[k][0];
        j += DIRS[k][1];

        // Trường hợp cơ bản: nếu ô mới nằm ngoài giới hạn hoặc không khớp với giá trị
        // mục tiêu,
        // đường đi kết thúc ở đây.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0;
        }

        // Tạo một khóa duy nhất cho bảng ghi nhớ bằng cách sử dụng bitmask
        // `k << 1` dịch các bit của hướng sang trái, và `| canTurn` đặt bit cuối cùng.
        int mask = k << 1 | canTurn;

        // Kiểm tra bảng ghi nhớ. Nếu kết quả cho trạng thái này đã được tính,
        // trả về trực tiếp.
        if (memo[i][j][mask] > 0) {
            return memo[i][j][mask];
        }

        // Gọi đệ quy để tiếp tục đường đi theo cùng một hướng.
        // Giá trị `target` luân phiên (1 -> 2, 2 -> 1).
        int res = dfs(i, j, k, canTurn, 2 - target, grid, memo);

        // Nếu được phép rẽ, khám phá khả năng rẽ.
        if (canTurn == 1) {
            // Tính độ dài lý thuyết tối đa của một đường đi từ vị trí hiện tại
            // sau khi rẽ. Đây là một tối ưu hóa khác.
            int[] maxs = { grid.length - i - 1, j, i, grid[i].length - j - 1 };

            // Thay đổi hướng cho lần rẽ.
            k = (k + 1) % 4;

            // Tối ưu hóa 2: Cắt tỉa nếu độ dài tối đa lý thuyết cho hướng mới
            // không tốt hơn kết quả hiện tại.
            if (maxs[k] > res) {
                // Gọi đệ quy với hướng mới và `canTurn` được đặt thành 0.
                res = Math.max(res, dfs(i, j, k, 0, 2 - target, grid, memo));
            }
        }

        // Cộng 1 vào `res` (cho ô hiện tại) và lưu kết quả vào bảng ghi nhớ trước khi
        // trả về.
        return memo[i][j][mask] = res + 1;
    }
}

// Đây là
// lời giải
// cho một
// bài toán
// tìm đường
// chéo dài
// nhất có
// hình chữ"V"
// trong một lưới,
// với các
// ô trên
// đường đi
// phải luân
// phiên giữa
// hai giá

// trị (ví dụ, 1 và 2).

// ### Giải thích thuật toán

// Thuật toán sử dụng **tìm kiếm theo chiều sâu (DFS)** kết hợp với **ghi nhớ
// (memoization)** để duyệt qua tất cả các đường đi hình chữ "V" có thể và tìm
// ra đường đi dài nhất.

// #### 1. Phương thức `lenOfVDiagonal`

// Đây là hàm chính để điều phối việc tìm kiếm.

// * **Khởi tạo:**
// * Lấy kích thước của lưới `m` và `n`.
// * Tạo một mảng ghi nhớ 3 chiều `memo[m][n][1 << 3]` để lưu kết quả của các
// bài toán con. Chiều thứ ba `1 << 3` (tức là 8) được dùng để mã hóa trạng thái
// của DFS. Cụ thể:
// * Hai chiều đầu tiên, `i` và `j`, là tọa độ của ô hiện tại.
// * Chiều thứ ba `mask` mã hóa hai thông tin:
// * `k`: Hướng chéo

// hiện tại (từ 0 đến 3).
// * `canTurn`:

// Một cờ (0 hoặc 1) cho biết có được phép rẽ hay không.
// * `mask` được tính bằng `k << 1 | canTurn`.
// * Biến `ans` được khởi tạo bằng 0 để lưu trữ độ dài tối đa tìm được.

// * **Duyệt qua lưới:**
// * Thuật toán duyệt qua mọi ô `(i, j)` trong lưới.
// * Chỉ xem xét các ô có giá trị là `1` để bắt đầu một đường đi.

// * **Khám phá các hướng bắt đầu:**
// * Vòng lặp `for (int k = 0; k < 4; k++)` thử tất cả bốn hướng chéo ban đầu.
// Mảng `DIRS` chứa sự thay đổi tọa độ cho các hướng này: `(1, 1)`, `(1, -1)`,
// `(-1, -1)`, `(-1, 1)`.
// * **Tối ưu hóa 1 (`maxs[k] > ans`):** Đây là một kỹ thuật cắt tỉa thông minh.
// `maxs[k]` tính toán độ dài lý thuyết tối đa của một đường đi bắt đầu từ `(i,
// j)` và đi theo hướng `k` cho đến khi chạm biên. Nếu độ dài lý thuyết này
// không lớn hơn độ dài tốt nhất hiện tại (`ans`), thì không cần phải bắt đầu
// DFS từ đây, vì không thể tìm được đường đi dài hơn.

// * **Bắt đầu DFS:**
// * Hàm gọi `dfs(i, j, k, 1, 2, grid, memo)` để bắt đầu tìm kiếm đệ quy.
// * Các tham số bao gồm:
// * `i`, `j`: Tọa độ bắt đầu.
// * `k`: Hướng ban đầu.
// * `canTurn`: `1`, cho phép rẽ một lần.
// * `target`: `2`, ô tiếp theo phải có giá trị là `2`.
// * Kết quả của `dfs` là độ dài của đoạn đường chéo sau ô bắt đầu. Chúng ta
// cộng thêm `1` (cho ô bắt đầu) và cập nhật `ans`.

// #### 2. Phương thức `dfs`

// Đây là hàm đệ quy cốt lõi để khám phá các đường chéo.

// * **Di chuyển:**
// * Hàm trước tiên tính toán tọa độ của ô tiếp theo `(i, j)` bằng cách cộng sự
// thay đổi tọa độ của hướng

// hiện tại (`DIRS[k][0]`, `DIRS[k][1]`).

// * **Điều kiện

// dừng (Base Cases):**
// * Đệ quy dừng lại và trả về `0` nếu bất kỳ điều kiện nào sau đây được thỏa
// mãn:
// 1. Tọa độ mới nằm ngoài lưới.
// 2. Giá trị của ô mới không phải là `target` mong muốn. Điều này đảm bảo các
// giá trị 1 và 2 luân phiên nhau.

// * **Kiểm tra ghi nhớ:**
// * Tạo `mask` để biểu diễn trạng thái hiện tại (`k << 1 | canTurn`).
// * Nếu kết quả cho trạng thái này đã được tính toán (`memo[i][j][mask] > 0`),
// hàm sẽ trả về giá trị đã lưu, tránh tính toán lại.

// * **Bước đệ quy:**
// * **Tiếp tục đi thẳng:**
// * Gọi đệ quy `dfs(i, j, k, canTurn, 2 - target, grid, memo)`.
// * Tiếp tục đi theo cùng hướng `k`, nhưng giá trị `target` được đảo lại (`2 -
// target`). Cờ `canTurn` vẫn giữ nguyên.
// * Kết quả của lời gọi này được lưu trong `res`.
// * **Thực hiện rẽ:**
// * Phần logic này chỉ được thực hiện nếu `canTurn == 1` (được phép rẽ).
// * Hướng `k` được đổi sang hướng chéo tiếp theo `(k + 1) % 4`.
// * Cờ `canTurn` được đặt thành `0` để không cho phép rẽ thêm lần nữa.
// * **Tối ưu hóa 2 (`maxs[k] > res`):** Tương tự như tối ưu hóa đầu tiên, nó
// tính toán độ dài lý thuyết tối đa của đường đi tiếp tục từ điểm này theo
// hướng mới. Nếu độ dài này không lớn hơn `res` (độ dài của đường đi thẳng),
// thì không cần khám phá đường đi rẽ này.
// * Gọi đệ quy `dfs(i, j, k, 0, 2 - target, grid, memo)` để khám phá đường đi
// sau khi rẽ.
// * Lấy giá trị lớn nhất giữa đường đi thẳng (`res`) và đường đi sau khi rẽ.

// * **Lưu và trả về kết quả:**
// * Hàm cộng `1` vào `res` (cho ô hiện tại `(i, j)`) và lưu nó vào mảng `memo`
// trước khi trả về. Điều này đảm bảo lần sau khi cùng trạng thái này được gọi,
// kết quả có thể được tra cứu ngay lập tức.

// ### Tóm tắt

// Thuật toán này khám phá một cách có hệ thống mọi đường đi hình chữ "V" có thể
// trong lưới bằng cách sử dụng phương pháp DFS đệ quy. Nó tận dụng **ghi nhớ**
// để tránh tính toán lại các bài toán con, điều này rất quan trọng cho hiệu
// suất. Các tối

// ưu hóa (`maxs[k] > ans` và `maxs[k] > res`) giúp cắt tỉa không gian tìm kiếm,
// làm cho giải pháp hiệu quả hơn. Việc sử

// dụng bitmask (`k << 1 | canTurn`) là một cách tiết kiệm không gian để mã hóa
// trạng thái cho bảng ghi nhớ.

// **Độ phức tạp thời gian và không gian** đều là `O(mn)` vì DFS duyệt qua mỗi ô
// `(i, j)` trong lưới cho mỗi trạng thái

// có thể (hướng và trạng thái rẽ). Vì số lượng trạng thái là

// hằng số (8), độ phức tạp chủ yếu phụ thuộc vào kích thước của lưới.

// hiện (instance) cụ thể của lớp. Tất cả các đối tượng của lớp đều chia sẻ cùng
// một bản sao của biến này, giúp tiết kiệm bộ nhớ.
// * **`final`**: Từ khóa này chỉ ra rằng giá trị của biến không thể bị thay đổi
// sau khi được khởi tạo. Đây là một hằng số.
// * **`int[][] DIRS`**: Đây là khai báo một mảng hai chiều của các số nguyên có
// tên là `DIRS`.

// Mỗi phần tử trong mảng `DIRS` là một mảng con có hai phần tử, đại diện cho sự
// thay đổi về tọa độ `(hàng, cột)` khi di chuyển trên lưới:

// 1. `{1, 1}`: Di chuyển **xuống và sang phải**. Hàng tăng 1, cột tăng 1.
// 2. `{1, -1}`: Di chuyển **xuống và sang trái**. Hàng tăng 1, cột giảm 1.
// 3. `{-1, -1}`: Di chuyển **lên và sang trái**. Hàng giảm 1, cột giảm 1.
// 4. `{-1, 1}`: Di chuyển **lên và sang phải**. Hàng giảm 1, cột tăng 1.

// **Chức năng chính của `DIRS` là để đơn giản hóa việc di chuyển trong các
// thuật toán

// duyệt lưới (như DFS hoặc BFS)**. Thay vì viết các câu lệnh `if-else` dài dòng
// để kiểm tra từng hướng, lập trình viên có thể lặp qua mảng `DIRS` để khám phá
// tất cả các hướng chéo một cách hiệu quả.

// Ví dụ, nếu bạn đang ở tọa độ `(i, j)` và muốn di chuyển theo hướng chéo
// xuống-phải, bạn chỉ cần thực hiện:

// `new_i = i + DIRS[0][0];`
// `new_j = j + DIRS[0][1];`

// Mã này giúp cho thuật toán trở nên gọn gàng, dễ đọc và dễ bảo trì hơn.
// nếu viết if else
// for(// int k = 0;k<4;k++)
// {
// int newI = i;
// int newJ = j;
// if (k == 0) { // Hướng xuống-phải
// newI = i + 1;
// newJ = j + 1;
// } else if (k == 1) { // Hướng xuống-trái
// newI = i + 1;
// newJ = j - 1;
// } else if (k == 2) { // Hướng lên-trái
// newI = i - 1;
// newJ = j - 1;
// } else if (k == 3) { // Hướng lên-phải
// newI = i - 1;
// newJ = j + 1;
// }
// }
