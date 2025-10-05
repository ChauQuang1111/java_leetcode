
// 417. Pacific Atlantic Water FlowMedium(05/10/2025)
import java.util.*;

public class b58 {

    // Hàm chính của LeetCode: trả về danh sách các tọa độ có thể chảy đến cả Thái
    // Bình Dương và Đại Tây Dương
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        // Hai ma trận boolean để đánh dấu ô nào có thể chảy đến mỗi đại dương
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS từ các biên tiếp giáp với Thái Bình Dương (trên và trái)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, Integer.MIN_VALUE);
            dfs(heights, atlantic, i, n - 1, Integer.MIN_VALUE);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, Integer.MIN_VALUE);
            dfs(heights, atlantic, m - 1, j, Integer.MIN_VALUE);
        }

        // Kết quả lưu tọa độ (hàng, cột) các ô có thể chảy đến cả hai đại dương
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    // DFS lan truyền từ biên vào trong
    public static void dfs(int[][] h, boolean[][] visited, int i, int j, int prevHeight) {
        int m = h.length, n = h[0].length;

        // Dừng nếu ra ngoài biên hoặc đã thăm hoặc độ cao thấp hơn ô trước (nước không
        // chảy ngược)
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || h[i][j] < prevHeight)
            return;

        visited[i][j] = true; // Đánh dấu đã thăm

        // DFS 4 hướng: lên, xuống, trái, phải
        dfs(h, visited, i + 1, j, h[i][j]);
        dfs(h, visited, i - 1, j, h[i][j]);
        dfs(h, visited, i, j + 1, h[i][j]);
        dfs(h, visited, i, j - 1, h[i][j]);
    }

    static Scanner sc = new Scanner(System.in);

    // Hàm main để nhập dữ liệu và chạy chương trình
    public static void main(String[] args) {
        int m = sc.nextInt();

        int n = sc.nextInt();

        int[][] heights = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[i][j] = sc.nextInt();
            }
        }

        List<List<Integer>> result = pacificAtlantic(heights);
        for (List<Integer> cell : result) {
            System.out.println(cell);
        }

        sc.close();
    }
}

// Tốt lắm 👍 Bây giờ mình sẽ **giải thích cực dễ hiểu** đề bài **LeetCode 417 –
// Pacific Atlantic Water Flow** (💧 **Dòng nước chảy ra hai đại dương**).

// ---

// ## 🧩 **Đề bài gốc (dịch dễ hiểu)**

// Bạn được cho một **ma trận 2D `heights`**, trong đó:

// * `heights[r][c]` là **độ cao** của ô đất ở hàng `r`, cột `c`.

// ---

// ### 🌊 Có hai đại dương:

// * **Thái Bình Dương (Pacific Ocean)** nằm ở **trên và bên trái** của ma trận.
// * **Đại Tây Dương (Atlantic Ocean)** nằm ở **dưới và bên phải** của ma trận.

// ---

// ### 💧 Quy luật dòng chảy:

// Nước **chảy từ ô cao xuống ô thấp hoặc bằng** — tức là:

// > Từ một ô `(r, c)`, nước có thể chảy đến ô **láng giềng** có độ cao `≤
// heights[r][c]`.

// Láng giềng ở đây là:

// * Trên `(r-1, c)`
// * Dưới `(r+1, c)`
// * Trái `(r, c-1)`
// * Phải `(r, c+1)`

// ---

// ### 🎯 Yêu cầu:

// Trả về **danh sách tất cả các ô** `(r, c)` **mà nước từ đó có thể chảy đến cả
// hai đại dương**:

// * **Pacific** (trên hoặc trái)
// * **Atlantic** (dưới hoặc phải)

// ---

// ## 🔍 **Ví dụ minh họa**

// ### Input:

// ```python
// heights = [
// [1, 2, 2, 3, 5],
// [3, 2, 3, 4, 4],
// [2, 4, 5, 3, 1],
// [6, 7, 1, 4, 5],
// [5, 1, 1, 2, 4]
// ]
// ```

// ### Output:

// ```python
// [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
// ```

// ---

// ### 📖 Giải thích hình ảnh (tưởng tượng ma trận):

// ```
// Hàng\Cột 0 1 2 3 4
// 0 [1, 2, 2, 3, 5]
// 1 [3, 2, 3, 4, 4]
// 2 [2, 4, 5, 3, 1]
// 3 [6, 7, 1, 4, 5]
// 4 [5, 1, 1, 2, 4]
// ```

// ---

// ### 🌊 Dòng nước chảy:

// * **Pacific Ocean** tiếp xúc hàng đầu và cột đầu
// → hàng 0 và cột 0
// * **Atlantic Ocean** tiếp xúc hàng cuối và cột cuối
// → hàng 4 và cột 4

// ---

// Ví dụ:

// * Ô `(2,2)` có giá trị 5
// → nước có thể chảy **xuống dưới** 4, rồi 5, ra Atlantic
// → nước có thể **lên trên** 2 → 3 → 1 → Pacific
// 👉 nên `(2,2)` là hợp lệ.

// ---

// ## 💡 Cách giải (tư duy ngược rất hay)

// Thay vì thử cho **nước chảy từ mỗi ô ra đại dương** (rất tốn công 💥),
// ta làm **ngược lại**:

// ### 🔄 Ý tưởng:

// * Bắt đầu **từ Pacific** (biên trên + biên trái),
// tìm tất cả ô **có thể đi ngược lên** (tức là có độ cao ≥ ô trước đó).
// * Làm tương tự với **Atlantic** (biên dưới + biên phải).

// => Cuối cùng, **giao hai tập hợp** các ô đó
// chính là **những ô nước có thể chảy đến cả hai đại dương**.

// ---

// ## 🚀 Giải thuật:

// 1. Duyệt DFS hoặc BFS từ các biên của mỗi đại dương.
// 2. Tạo 2 ma trận `pacific` và `atlantic` (kiểu boolean),
// đánh dấu những ô mà nước có thể đến.
// 3. Cuối cùng, duyệt toàn bộ ma trận:
// nếu ô `(r, c)` được đánh dấu **cả trong pacific và atlantic**,
// thêm vào kết quả.

// ---

// ## 🧠 Độ phức tạp:

// | Thành phần | Phân tích |
// | ---------- | ----------------------------------------- |
// | Thời gian | `O(m * n)` (mỗi ô được thăm tối đa 2 lần) |
// | Bộ nhớ | `O(m * n)` để lưu visited |

// ---

// ## ✅ Tóm tắt dễ hiểu

// | Đại dương | Bắt đầu từ | Điều kiện chảy ngược |
// | --------- | ----------------- | ---------------------------------------- |
// | Pacific | hàng 0, cột 0 | chảy ngược nếu cao hơn hoặc bằng ô trước |
// | Atlantic | hàng m-1, cột n-1 | chảy ngược nếu cao hơn hoặc bằng ô trước |

// → Giao hai vùng nước này = kết quả.

// ---

// Bạn có muốn mình viết **code Python hoàn chỉnh kèm chú thích từng dòng** (DFS
// hoặc BFS cách dễ hiểu nhất) cho bài này không?
// Tốt lắm 👍 — đây là một **cách giải bài 417. Pacific Atlantic Water Flow**
// bằng **DFS (Depth-First Search)** khá thú vị và tối ưu, vì nó dùng **chung
// một mảng `visited` cho cả hai đại dương (Pacific & Atlantic)** để tiết kiệm
// bộ nhớ.
// Giờ mình sẽ **giải thích chi tiết thuật toán + từng phần trong code** nhé 👇

// ---

// ## 🧩 1️⃣ Ý tưởng bài toán:

// Bài **Pacific Atlantic Water Flow** yêu cầu:

// * Nước **chảy từ một ô** có thể **đi xuống, đi lên, sang trái, sang phải**,
// **chỉ khi ô bên cạnh có độ cao thấp hơn hoặc bằng**.
// * Pacific ở **trên và trái**, Atlantic ở **dưới và phải**.
// * Tìm **tất cả các ô** mà nước **có thể chảy đến cả hai đại dương**.

// 👉 Cách tiếp cận hay nhất:
// **Thay vì mô phỏng nước chảy xuống đại dương**, ta sẽ **“đảo ngược” dòng
// chảy**:

// * Bắt đầu từ **biên của Pacific**, xem ô nào có thể **chảy ngược lên cao hơn
// hoặc bằng**.
// * Làm tương tự với **Atlantic**.
// * Cuối cùng, **ô nào được thăm trong cả 2 lần DFS** chính là **đáp án**.

// ---

// ## 🧠 2️⃣ Giải thích tổng quát code

// ```java
// List<List<Integer>> pacificAtlantic(int[][] matrix)
// ```

// * Trả về danh sách các tọa độ `[row, col]` nước có thể chảy tới **cả Pacific
// và Atlantic**.
// * Dùng một **class ẩn danh (Anonymous Inner Class)** để **chỉ thực hiện tính
// toán khi cần** (lazy initialization) — hơi advanced Java, không ảnh hưởng
// thuật toán.

// ---

// ## ⚙️ 3️⃣ Hàm `solve()` — thực hiện chính

// ```java
// int m = matrix.length;
// int n = matrix[0].length;
// char[][] visited = new char[m][n];
// ```

// * `m`, `n`: kích thước lưới.
// * `visited`: lưu trạng thái của mỗi ô:

// * `'P'`: nước từ **Pacific** có thể đến đây.
// * `'A'`: nước từ **Atlantic** có thể đến đây.
// * `0` (mặc định): chưa thăm.

// ---

// ### 🌊 Pacific DFS:

// ```java
// for (int col = 0; col < n; col++) {
// dfs(matrix, 0, col, visited, 'P', res); // hàng trên cùng
// }
// for (int row = 0; row < m; row++) {
// dfs(matrix, row, 0, visited, 'P', res); // cột bên trái
// }
// ```

// → Bắt đầu DFS từ **các biên giáp Thái Bình Dương**.

// ---

// ### 🌊 Atlantic DFS:

// ```java
// for (int col = 0; col < n; col++) {
// dfs(matrix, m - 1, col, visited, 'A', res); // hàng dưới cùng
// }
// for (int row = 0; row < m; row++) {
// dfs(matrix, row, n - 1, visited, 'A', res); // cột bên phải
// }
// ```

// → Bắt đầu DFS từ **các biên giáp Đại Tây Dương**.

// ---

// ## 🧭 4️⃣ Hàm `dfs()` chi tiết

// ```java
// private void dfs(int[][] matrix, int row, int col, char[][] visited, char ch,
// List<List<Integer>> res)
// ```

// ### 🔹 Dòng này:

// ```java
// if (visited[row][col]=='P' && ch=='A') {
// res.add(new ArrayList<Integer>() {{
// add(row);
// add(col);
// }});
// }
// ```

// → Nếu đang **chạy DFS cho Atlantic (`ch == 'A'`)**,
// và **ô này trước đó đã được thăm bởi Pacific (`visited[row][col] == 'P'`)**,
// 👉 thì ô này **nước chảy được đến cả hai đại dương**, thêm vào kết quả `res`.

// ---

// ### 🔹 Đánh dấu ô đã thăm:

// ```java
// visited[row][col] = ch;
// ```

// → Ghi nhớ ô này đã thuộc đại dương hiện tại (`'P'` hoặc `'A'`).

// ---

// ### 🔹 Duyệt 4 hướng:

// ```java
// for (int[] dir : directions) {
// int x = row + dir[0];
// int y = col + dir[1];
// if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length
// && matrix[row][col] <= matrix[x][y] && visited[x][y] != ch) {
// dfs(matrix, x, y, visited, ch, res);
// }
// }
// ```

// * Điều kiện `matrix[row][col] <= matrix[x][y]`:
// → ta **chỉ di chuyển từ thấp lên cao** (ngược hướng nước chảy thật).
// → Điều này đảm bảo: nếu nước có thể chảy **từ cao xuống thấp**,
// thì khi đi ngược lại, DFS đi **từ thấp lên cao hoặc bằng** là hợp lệ.

// * `visited[x][y] != ch`: tránh lặp vô hạn trong DFS cùng đại dương.

// ---

// ## 🧩 5️⃣ Kết quả

// Sau khi chạy xong:

// * `res` chứa tất cả tọa độ `[i, j]` mà nước **có thể chảy đến Pacific &
// Atlantic**.

// ---

// ## 💡 Tóm tắt ngắn:

// | Giai đoạn | Hành động | Mục tiêu |
// | --------- | ------------------------ |
// -------------------------------------- |
// | 1️⃣ | DFS từ các biên Pacific | Đánh dấu vùng có thể chảy tới Pacific |
// | 2️⃣ | DFS từ các biên Atlantic | Đánh dấu vùng có thể chảy tới Atlantic |
// | 3️⃣ | So sánh 2 vùng | Ô nào thuộc cả hai → thêm vào kết quả |

// ---

// Nếu bạn muốn, mình có thể **thêm hàm `main` để nhập mảng và in ra kết quả**
// cho code này (giống các bài trước) — bạn có muốn mình thêm luôn không?
