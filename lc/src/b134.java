
// Last Day Where You Can Still Cross”** (31/12/2025)
// Mình giải thích **đề bài + thuật toán** của bài **LeetCode 1970 – Last Day Where You Can Still Cross** theo đúng hướng code bạn đưa (DSU trên **water cells**), bằng tiếng Việt nhé.
import java.util.Scanner;

public class b134 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập row, col
        int row = sc.nextInt();
        int col = sc.nextInt();

        int n = row * col;
        int[][] cells = new int[n][2];

        // Nhập danh sách cells
        for (int i = 0; i < n; i++) {
            cells[i][0] = sc.nextInt();
            cells[i][1] = sc.nextInt();
        }

        int result = latestDayToCross(row, col, cells);

        System.out.println(result);
        sc.close();

    }

    public static int[] rank; // dùng cho union by rank
    public static int[] root; // lưu cha của mỗi node

    public static int row, col;

    // 8 hướng (trên, dưới, trái, phải + chéo)
    public static final int[][] DIRECTIONS = {
            { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 },
            { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }
    };

    // 2 node đặc biệt
    public static int leftWaterId;
    public static int rightWaterId;

    // ==========================
    // HÀM CHÍNH GIẢI BÀI
    // ==========================
    public static int latestDayToCross(int r, int c, int[][] cells) {

        row = r;
        col = c;

        int total = row * col;

        // +2 cho leftWaterId và rightWaterId
        rank = new int[total + 2];
        root = new int[total + 2];

        leftWaterId = total;
        rightWaterId = total + 1;

        // Khởi tạo DSU
        for (int i = 0; i < total + 2; i++) {
            root[i] = i;
        }

        boolean[][] water = new boolean[row][col];

        // Duyệt từng ngày
        for (int day = 0; day < cells.length; day++) {

            int x = cells[day][0] - 1; // về index 0-based
            int y = cells[day][1] - 1;

            water[x][y] = true;

            int id = toId(x, y);

            // Nếu ở cột trái → nối với leftWaterId
            if (y == 0) {
                union(id, leftWaterId);
            }

            // Nếu ở cột phải → nối với rightWaterId
            if (y == col - 1) {
                union(id, rightWaterId);
            }

            // Union với các ô nước xung quanh
            for (int[] d : DIRECTIONS) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (valid(nx, ny) && water[nx][ny]) {
                    union(id, toId(nx, ny));
                }
            }

            // Nếu nước nối được trái ↔ phải
            if (find(leftWaterId) == find(rightWaterId)) {
                return day; // ngày đầu tiên không đi được
            }
        }

        return -1;
    }

    // ==========================
    // DSU FUNCTIONS
    // ==========================
    public static int toId(int x, int y) {
        return x * col + y;
    }

    public static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]); // path compression
        }
        return root[x];
    }

    public static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb)
            return;

        if (rank[ra] < rank[rb]) {
            root[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            root[rb] = ra;
        } else {
            root[rb] = ra;
            rank[ra]++;
        }
    }

    public static boolean valid(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

}

// ---

// ## 1️⃣ Giải thích đề bài

// Bạn có một **lưới row × col** ban đầu **toàn là đất (land)**.

// * Mỗi ngày, **1 ô bị ngập nước** theo thứ tự trong mảng `cells`
// * Bạn **được phép đi** từ **hàng trên cùng → hàng dưới cùng**
// * Chỉ được đi qua **ô đất (chưa bị ngập)**

// 👉 **Câu hỏi:**
// 👉 *Ngày cuối cùng (lớn nhất) mà bạn vẫn còn đi được từ trên xuống dưới là
// ngày nào?*

// ---

// ## 2️⃣ Ý tưởng chính của thuật toán (Tư duy ngược)

// Thông thường:

// * Người ta sẽ **binary search + BFS** trên đất

// Nhưng **code của bạn dùng DSU (Union Find) trên nước**, với tư duy:

// > ❗ **Không còn đi được khi nước tạo thành một bức tường nối từ trái sang
// phải**

// ### Vì sao lại là *trái → phải*?

// * Ta cần **chặn đường đi từ trên xuống**
// * Đường đi bị chặn khi **nước nối kín từ cột trái sang cột phải**
// * Khi đó, không còn đường đất nào xuyên qua được nữa

// 📌 Ý tưởng cực kỳ quan trọng:

// > **Ngày đầu tiên mà nước nối được từ trái sang phải → ngày đó là ngày KHÔNG
// CÒN đi được**
// > → Kết quả chính là **ngày đó – 1**

// ---

// ## 3️⃣ Cách DSU hoạt động trong code

// ### 🔹 Biểu diễn

// * Mỗi ô `(x, y)` được ánh xạ thành **1 id**:

// ```java
// id = x * col + y
// ```

// * Tạo **2 node đặc biệt**:

// ```java
// leftWaterId = row * col; // nước chạm cột trái
// rightWaterId = row * col + 1; // nước chạm cột phải
// ```

// ---

// ## 4️⃣ Quá trình mô phỏng theo ngày

// ```java
// for (int i = 0; i < cells.length; i++) {
// ```

// 👉 Mỗi vòng lặp tương ứng **1 ngày**

// ### Bước 1: Đánh dấu ô bị ngập

// ```java
// water[x][y] = true;
// ```

// ---

// ### Bước 2: Nếu nước chạm biên trái / phải → nối với node đặc biệt

// ```java
// if (y == 0) union(cell, leftWaterId);
// if (y == col - 1) union(cell, rightWaterId);
// ```

// ---

// ### Bước 3: Nối với các ô nước xung quanh

// ```java
// for (int[] dir : DIRECTIONS) {
// if (neighbor là water) {
// union(current, neighbor);
// }
// }
// ```

// ⚠️ Ở đây dùng **8 hướng** (kể cả chéo)
// → để đảm bảo nước tạo thành “bức tường kín”

// ---

// ### Bước 4: Kiểm tra điều kiện thất bại

// ```java
// if (find(leftWaterId) == find(rightWaterId)) {
// return i;
// }
// ```

// 👉 Nghĩa là:

// * Nước đã nối từ **trái → phải**
// * Không còn đường nào từ **trên → dưới**

// ⏱ `i` chính là **ngày đầu tiên KHÔNG thể đi được**

// ---

// ## 5️⃣ Vì sao trả về `i` là đúng?

// * Ngày `i` → **không còn đi được**
// * Ngày cuối cùng còn đi được = **i – 1**

// ⚠️ Trong bài LeetCode:

// * `i` là **0-based**
// * LeetCode yêu cầu **trả về số ngày**

// → Code này **đúng theo yêu cầu đề**

// ---

// ## 6️⃣ Độ phức tạp

// * DSU gần như **O(1)** mỗi union/find
// * Tổng số ô: `row × col`

// 👉 **Time:** `O(row × col × α(n))`
// 👉 **Space:** `O(row × col)`

// Rất tối ưu 👍

// ---

// ## 7️⃣ Tóm tắt 1 câu dễ nhớ

// > **Ta không tìm đường cho người đi, mà tìm ngày nước tạo thành bức tường
// trái–phải. Ngày đó là lúc không thể đi được nữa.**

// ---

// Nếu bạn muốn:

// * So sánh với **Binary Search + BFS**
// * Vẽ hình minh họa
// * Hoặc giải thích vì sao **8 directions** là cần thiết

// 👉 cứ hỏi tiếp nhé 👍

// ---

// ## 1. Mô tả bài toán (hiểu theo ngôn ngữ thường)

// * Bạn có một **lưới hình chữ nhật** kích thước `row x col`
// * Ban đầu (**ngày 0**) tất cả các ô đều là **đất khô** → có thể đi qua
// * Mỗi ngày, **một số ô sẽ bị ngập nước** theo thứ tự cho sẵn trong mảng
// `cells`
// * Khi một ô bị ngập → **không thể đi qua ô đó nữa**

// 👉 Bạn được phép **bắt đầu từ bất kỳ ô nào ở hàng trên cùng**
// 👉 Mục tiêu là **đi tới bất kỳ ô nào ở hàng dưới cùng**

// ---

// ## 2. Câu hỏi của đề

// ❓ **Ngày cuối cùng (lớn nhất) mà bạn vẫn còn có thể đi từ trên xuống dưới là
// ngày nào?**

// * Sau ngày đó → **không còn đường nào đi được nữa**

// ---

// ## 3. Ví dụ trực quan (ý tưởng)

// Giả sử:

// ```
// row = 3, col = 3
// cells = [
// [1,2],
// [2,1],
// [3,3],
// [2,2],
// [1,1],
// [1,3],
// [2,3],
// [3,2],
// [3,1]
// ]
// ```

// * Ngày 1: ô (1,2) bị ngập
// * Ngày 2: thêm ô (2,1)
// * Ngày 3: thêm ô (3,3)
// * ...

// Có thể:

// * Những ngày đầu → vẫn còn đường từ hàng 1 xuống hàng 3
// * Đến một ngày nào đó → **nước chặn kín**, không còn đường

// 👉 **Cần tìm ngày cuối cùng trước khi bị chặn hoàn toàn**

// ---

// ## 4. Những điều quan trọng cần hiểu

// ### ✔️ Bạn được đi:

// * Lên, xuống, trái, phải (4 hướng)
// * Không được đi chéo

// ### ✔️ Chỉ cần:

// * **Tồn tại ít nhất 1 đường đi**
// * Không cần đường ngắn nhất

// ### ❌ Không cần:

// * In ra đường đi
// * Đếm số đường

// ---

// ## 5. Bản chất bài toán

// Đây là bài:

// > **“Tìm thời điểm cuối cùng mà đồ thị còn liên thông từ hàng trên xuống hàng
// dưới”**

// Nên thường dùng:

// * **Binary Search + BFS/DFS**
// * hoặc **Union-Find (DSU) ngược thời gian**

// ---

// ## 6. Cách nghĩ phổ biến (chưa cần code)

// ### Cách 1: Binary Search (dễ hiểu)

// * Giả sử ngày `mid`
// * Tạo bản đồ:

// * Ô bị ngập từ ngày `1 → mid` → nước
// * Còn lại → đất
// * Dùng **BFS/DFS** kiểm tra:

// * Có đi được từ hàng 1 → hàng `row` không?
// * Nếu **đi được** → thử ngày lớn hơn
// * Nếu **không đi được** → giảm ngày

// ⏱️ Hiệu quả và dễ implement

// ---

// ## 7. Tóm tắt ngắn gọn

// * Lưới bị ngập **dần theo ngày**
// * Bạn cần tìm **ngày cuối cùng còn đường đi**
// * Chỉ cần **tồn tại 1 đường**
// * Bài này kiểm tra **tư duy đồ thị + thời điểm**

// ---

// Nếu bạn muốn:

// * Mình giải **bằng ví dụ hình vẽ**
// * Hoặc **code Java / Python từng bước**
// * Hoặc **giải theo Union-Find**

// → cứ nói rõ nhé.
