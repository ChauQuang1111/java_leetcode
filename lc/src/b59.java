
// # 778. Swim in Rising Water(06/10/2025)
import java.util.*; // Dùng cho Scanner

public class b59 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();

        int[][] grid = new int[n][n];

        // Nhập ma trận
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Tạo đối tượng Solution và gọi hàm swimInWater

        int result = swimInWater(grid);

        // In kết quả
        System.out.println(result);
    }

    static int len; // Kích thước ma trận (n)
    // 4 hướng di chuyển: xuống, phải, lên, trái
    public static int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    // Hàm chính giải bài toán Swim in Rising Water
    public static int swimInWater(int[][] grid) {
        len = grid.length;

        // Giới hạn dưới: phải cao ít nhất bằng 2 ô đầu và cuối
        int left = Math.max(grid[0][0], grid[len - 1][len - 1]);
        // Giới hạn trên: giá trị lớn nhất có thể có
        int right = len * len - 1;

        int mid, res = 0;

        // Binary Search tìm mực nước nhỏ nhất đủ để đến đích
        while (left <= right) {
            mid = (left + right) / 2;
            boolean[] seen = new boolean[len * len]; // Lưu các ô đã thăm

            if (dfs(0, 0, grid, mid, seen)) { // Nếu có thể đến đích
                res = mid; // Cập nhật kết quả
                right = mid - 1; // Thử giảm mực nước
            } else {
                left = mid + 1; // Không tới được → tăng mực nước
            }
        }

        return res;
    }

    // DFS kiểm tra có thể đi từ (0,0) → (n-1,n-1) với mực nước mid hay không
    public static boolean dfs(int x, int y, int[][] grid, int mid, boolean[] seen) {
        int idx = x * len + y; // Ánh xạ (x,y) thành chỉ số 1D
        seen[idx] = true; // Đánh dấu ô hiện tại là đã thăm

        // Nếu đã đến đích, trả về true
        if (x == len - 1 && y == len - 1)
            return true;

        // Duyệt 4 hướng
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];

            // Kiểm tra ô hợp lệ
            if (newX >= 0 && newX < len && newY >= 0 && newY < len) {
                int newIdx = newX * len + newY;
                // Nếu ô chưa thăm và có thể đi qua (độ cao <= mid)
                if (!seen[newIdx] && grid[newX][newY] <= mid) {
                    if (dfs(newX, newY, grid, mid, seen))
                        return true;
                }
            }
        }

        // Không tìm được đường đi
        return false;

    }
}
// -------------------- HÀM MAIN --------------------

// Rất hay—đây là một phiên bản**tối ưu gọn gàng hơn của thuật toán"Swim in
// Rising Water"(LeetCode 778)**,viết bằng**Java**✅Mình sẽ**giải thích chi tiết
// thuật toán và từng phần của code**cho bạn dễ hiểu nhé.

// ---

// ##🎯**Mục tiêu của bài toán**

// Cho một ma trận`grid[n][n]`,trong đó:

// *`grid[r][c]`là độ cao của ô`(r,c)`.*Ban đầu,nước dâng lên theo thời
// gian`t`.*Ở thời điểm`t`,bạn chỉ có thể đi đến những ô có độ
// cao`<=t`.→Tìm**thời gian nhỏ nhất`t`**sao cho bạn có thể**đi
// từ(0,0)**đến**(n-1,n-1)**.

// ---

// ##🧠**Ý tưởng thuật toán**

// ###🔹1. Nhận xét:

// *Nếu nước dâng đủ cao(t lớn),bạn**chắc chắn đi được**.*Nếu t quá thấp,bạn**bị
// chặn lại**.

// ⇒Ta có thể**dùng Binary Search trên giá trị t**(mức nước).

// ---

// ###🔹2. Cách làm:

// 1.**Đặt khoảng tìm kiếm**:

// *`left=max(grid[0][0],grid[n-1][n-1])`→ít nhất phải cao bằng độ cao ở 2
// đầu.*`right=n*n-1`→vì giá trị trong grid từ`0`đến`n²-1`.

// 2.**Thực hiện Binary Search**:

// *Lấy`mid=(left+right)/2`→giả định nước cao`mid`.*Dùng**DFS**kiểm tra:Có thể
// đi từ`(0,0)`đến`(n-1,n-1)`chỉ qua các ô`<=mid`không?*Nếu đi được→thử**giảm
// mực nước**(`right=mid-1`).*Nếu không→phải**tăng mực nước**(`left=mid+1`).

// 3. Khi vòng lặp kết thúc,`res`là**mực nước nhỏ nhất có thể đi được**.

// ---

// ##🧩**Giải thích từng phần code**

// ```java
// class Solution {
// int len;
// final static int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0,
// -1 } };```

// ➡️
// Khai báo
// các hướng

// di chuyển (xuống, phải, lên, trái).

// ---

// ```java

// public int swimInWater(int[][] grid) {
// len=grid.length;int left=Math.max(grid[0][0],grid[len-1][len-1]);int
// right=len*len-1;int mid,res=0;```

// ➡️ Xác định giới hạn tìm kiếm ban đầu.`res`dùng để lưu kết quả cuối cùng.

// ---

// ```java while(left<=right){mid=(left+right)/2;boolean[]seen=new
// boolean[len*len]; // lưu các ô đã thăm
// ```

// ➡️ Duyệt bằng**Binary Search**,mỗi lần thử với`mid`(mực nước).Dùng
// mảng`seen`để tránh đi lại ô cũ.Dùng 1D array thay vì 2D(`x*len+y`),tiết kiệm
// bộ nhớ.

// ---

// ```java
// if(dfs(0,0,grid,mid,seen)){res=mid;right=mid-1;}else{left=mid+1;}}return res;
// }```

// ➡️

// Nếu DFS
// cho biết**
// có đường
// đi đến đích**→
// lưu lại`res`
// và thử**giảm mực nước**.
// Ngược lại→
// tăng mực
// nước.Khi kết
// thúc vòng lặp,`res`là**
// mực nước
// nhỏ nhất
// đủ để
// bơi tới đích**.

// ---

// ###⚙️
// DFS kiểm
// tra đường đi

// ```java

// public boolean dfs(int xn, int yn, int[][] grid, int mid, boolean[] seen) {
// int idx=xn*len+yn;if(seen[idx])return true;seen[idx]=true;```

// ➡️ Mỗi ô`(xn,yn)`được ánh xạ thành chỉ số 1D`idx`.Nếu ô đã thăm→bỏ qua,ngược
// lại→đánh dấu là đã thăm.

// ---

// ```java for(int i=0;i<4;i++){int
// newx=xn+dirs[i][0],newy=yn+dirs[i][1];if(newx>=0&&newx<len&&newy>=0&&newy<len&&!seen[newx*len+newy]&&grid[newx][newy]<=mid){```

// ➡️ Duyệt 4 hướng di chuyển:

// *Trong phạm vi lưới*Chưa thăm*Độ cao≤mực nước hiện tại

// ---

// ```java if(newx==len-1&&newy==len-1){return
// true;}if(dfs(newx,newy,grid,mid,seen)){return true;}}}return false;
// }```

// ➡️

// Nếu chạm
// được đích`(len-1,len-1)`→
// thành công.
// Nếu DFS
// sâu hơn
// có thể
// đến đích→
// cũng trả về`true`.
// Nếu duyệt
// hết mà
// không tìm được→
// trả về`false`.

// ---

// ##🧮**
// Độ phức tạp**

// *
// DFS mỗi
// lần kiểm tra:

// O(n²)
// * Binary Search: O(log(n²)) ≈

// O(log n)
// ➡️ **Tổng: O(n² * log n)**
// Hiệu quả tốt cho `n ≤ 50`.

// ---

// ## ✅ **Ví dụ minh họa**

// ```
// grid = [
// [0, 2],
// [1, 3]
// ]

// Binary Search:
// t = 1 → không thể

// đi tới (1,1)
// t = 2 → vẫn không được
// t = 3 → đi được: (0,0) → (0,1) → (1,1)
// → Kết quả = 3
// ```

// ---

// ## 🔍 **Tóm lại**

// Thuật toán này:

// * Dùng **binary search** để giảm số lần DFS kiểm tra.
// * Dùng **DFS + boolean[]** để tiết kiệm bộ nhớ.
// * Là giải pháp **hiệu quả và gọn** cho bài "Swim in Rising Water".

// ---

// Bạn có muốn mình viết thêm **phiên bản tối ưu hơn dùng

// Priority Queue (Dijkstra)** — chạy nhanh hơn DFS/BFS — để bạn so sánh không?

// # Ok 👍 ta cùng **giải thích thuật toán của bài “778. Swim in Rising Water”**
// và thêm **chú thích chi tiết vào code** để bạn hiểu rõ từng phần nhé.

// # ---

// # ## 🧠 **Tư duy thuật toán**

// # ### 🎯 **Mục tiêu**

// # * Bạn có một ma trận `grid[n][n]`, trong đó `grid[r][c]` biểu thị độ cao
// của ô (r, c).
// # * Nước dâng dần theo thời gian t = 0, 1, 2, ...
// # * Tại thời điểm `t`, bạn có thể di chuyển đến các ô có độ cao `<= t`.
// # * Bạn muốn **tìm thời gian nhỏ nhất `t`** sao cho có thể đi từ `(0,0)` →
// `(n-1,n-1)`.

// # ### 💡 **Ý tưởng chính**

// # 1. Dễ thấy rằng **khi t đủ lớn**, bạn chắc chắn có thể đi được (vì tất cả ô
// đều ≤ t).

// # 2. Ngược lại, nếu t quá nhỏ, đường sẽ bị chặn.
// # 👉 Vậy ta có thể **dùng Binary Search** trên giá trị `t`.

// # 3. Với mỗi `t` (gọi là `mid` trong code), ta dùng **DFS (hoặc BFS)** để
// kiểm tra xem **có thể đi từ (0,0) → (n-1,n-1)** mà **tất cả các ô ≤ mid** hay
// không.

// # 4. Nếu có thể đi được ⇒ ta thử giảm `t` xuống (vì có thể vẫn có giá trị nhỏ
// hơn).
// # Nếu không đi được ⇒ phải tăng `t` lên.

// # ---

// # ## 🧩 **Code có chú thích chi tiết**

// # ```python
// from typing import List
// class Solution:
// def swimInWater(self, grid: List[List[int]]) -> int:
// n = len(grid) # Kích thước ma trận n x n
// dirs = [(0, 1), (1, 0), (0, -1), (-1, 0)] # 4 hướng di chuyển (phải, xuống,
// trái, lên)
// visited = set() # Tập hợp các ô đã đi qua để tránh lặp vô hạn

// # Hàm DFS kiểm tra có thể đến đích với mực nước max_time hay không
// def dfs(r, c, max_time):
// # Nếu đến được góc dưới phải (đích)
// if r == n - 1 and c == n - 1:
// return True

// visited.add((r, c)) # Đánh dấu ô hiện tại là đã thăm

// # Duyệt qua 4 hướng xung quanh
// for dr, dc in dirs:
// nr, nc = r + dr, c + dc # Tính tọa độ ô kế bên

// # Kiểm tra điều kiện:
// # - Nằm trong lưới
// # - Chưa thăm
// # - Độ cao ô ≤ max_time (nghĩa là nước đã đủ cao để qua)
// if 0 <= nr < n and 0 <= nc < n and (nr, nc) not in visited and grid[nr][nc]
// <= max_time:
// # Gọi đệ quy kiểm tra từ ô mới
// if dfs(nr, nc, max_time):
// return True

// # Không có đường hợp lệ
// return False

// # Phạm vi tìm kiếm giá trị thời gian t
// # Bắt đầu từ max(grid[0][0], grid[n-1][n-1]) (vì ít nhất phải cao bằng ô đầu
// hoặc ô cuối)
// s = max(grid[0][0], grid[n-1][n-1])
// e = n * n - 1 # Giá trị lớn nhất có thể có trong grid
// res = e # Lưu kết quả nhỏ nhất tìm được

// # Binary Search trên khoảng [s, e]
// while s <= e:
// m = (s + e) // 2 # Lấy giá trị t giữa (mid)
// visited = set() # Reset visited mỗi lần kiểm tra

// # Nếu có thể đến đích với mực nước m
// if dfs(0, 0, m):
// res = m # Cập nhật kết quả
// e = m - 1 # Thử giảm mực nước xuống
// else:
// s = m + 1 # Không đến được → tăng mực nước lên

// return res # Trả về mực nước nhỏ nhất đủ để đi từ (0,0) → (n-1,n-1)

// # ---

// # ## ⚙️ **Độ phức tạp**

// # * **DFS mỗi lần**: O(n²)
// # * **Binary Search**: O(log(max_height)) ≈ O(log(n²)) = O(log n)

// # ➡️ **Tổng thể**: O(n² * log n)

// # ---

// # ## ✅ **Ví dụ minh họa**

// # ```
// # grid = [
// # [0, 2],
// # [1, 3]
// # ]

// # - t = 0 → chỉ có thể đứng ở (0,0)
// # - t = 1 → có thể đi (0,0) → (1,0)
// # - t = 2 → đi được (0,0) → (0,1) → (1,1)
// # → Kết quả = 3 (nhỏ nhất sao cho có đường tới đích)
// # ```

// # ---

// # Bạn có muốn mình vẽ thêm **sơ đồ minh họa luồng đi DFS + binary search**
// cho ví dụ trên không? (rất dễ hiểu bằng hình 👀)

// # # 778. Swim in Rising Water - Giải thích đề bài

// # ## Đề bài
// # Cho một lưới **n x n** chứa các số nguyên không âm, mỗi ô `grid[i][j]` đại
// diện cho **độ cao** (elevation) tại vị trí đó.

// # Ban đầu, bạn đang ở ô **góc trên cùng bên trái** `(0, 0)` và muốn đến ô
// **góc dưới cùng bên phải** `(n-1, n-1)`.

// # Mỗi giây, mực nước tăng lên 1 đơn vị. Tại thời điểm `t`:
// # - Bạn có thể **bơi** (di chuyển) giữa 2 ô kề nhau (lên/xuống/trái/phải)
// # - **Điều kiện**: độ cao của cả 2 ô phải **≤ t** (nước đã ngập đến đủ cao)

// # ## Yêu cầu
// # Tìm **thời gian nhỏ nhất** để có thể bơi từ `(0,0)` đến `(n-1, n-1)`.

// # ## Ví dụ minh họa

// # **Ví dụ 1:**
// # ```
// # Input: grid = [[0,2],
// # [1,3]]
// # Output: 3
// # ```

// # **Giải thích:**
// # - t=0: Ở (0,0), độ cao = 0
// # - t=1: Có thể đi sang (0,1) không? Không, vì độ cao (0,1) = 2 > 1
// # - t=2: Vẫn chưa thể đi (0,1) vì 2 > 2 là sai
// # - t=3: Bây giờ có thể đi (0,0)→(0,1)→(1,1) vì tất cả độ cao ≤ 3

// # **Ví dụ 2:**
// # ```
// # Input: grid = [[0,1,2,3,4],
// # [24,23,22,21,5],
// # [12,13,14,15,16],
// # [11,17,18,19,20],
// # [10,9,8,7,6]]
// # Output: 16
// # ```

// # ## Ý nghĩa
// # - Về bản chất, bạn cần tìm **đường đi** từ góc trên-trái đến góc dưới-phải
// # - Sao cho **giá trị lớn nhất** trên đường đi đó là **nhỏ nhất có thể**
// # - Đây là bài toán **minimax path** (minimize the maximum)

// # ## Gợi ý cách giải
// # - **Binary Search** + BFS/DFS: Tìm kiếm nhị phân thời gian t, kiểm tra xem
// có đường đi nào với tất cả ô ≤ t không
// # - **Dijkstra's Algorithm**: Coi như tìm đường đi với "chi phí" là giá trị
// max trên đường đi
// # - **Union-Find**: Tăng dần ngưỡng độ cao và kiểm tra khi nào 2 góc được kết
// nối