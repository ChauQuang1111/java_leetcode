// // // Balanced Binary Tree(08/02/2026)
// // // ## 🌳 Balanced Binary Tree – Giải thích đề bài (dễ hiểu)
// // Ok mình giải thích **thuật toán + từng dòng code** theo kiểu dễ hiểu nhất nhé 👇
// Ok mình sẽ**thêm hàm`main`dùng`Scanner`**để:

// *Nhập cây nhị phân*Gọi`isBalanced`*In kết quả*Có**chú thích từng dòng**để bạn học dễ hiểu👍

// ---

// #📌Quy ước input

// Để nhập nhanh,mình dùng dạng**Level Order(BFS)**:

// Ví dụ:

// ```Input:3 9 20 null null 15 7```

// Cây:

// ```3/\9 20/\15 7```

// `null`=không có node.

// ---

// #✅Code hoàn chỉnh(có main+Scanner+chú thích)

// ```java

import java.util.*;

public class b173 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String line = sc.nextLine();

        // Tách input thành mảng
        String[] arr = line.split(" ");

        // Build cây
        TreeNode root = buildTree(arr);

        // Kiểm tra balanced
        boolean result = isBalanced(root);

        // In kết quả
        if (result)
            System.out.println("Cay CAN BANG");
        else
            System.out.println("Cay KHONG CAN BANG");

        sc.close();

    }

    static class TreeNode {
        int val; // Giá trị node
        TreeNode left; // Con trái
        TreeNode right; // Con phải

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // ================== HÀM KIỂM TRA BALANCED ==================
    public static boolean isBalanced(TreeNode root) {

        // Nếu cây rỗng → cân bằng
        if (root == null)
            return true;

        // Tính chiều cao trái và phải
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        // Kiểm tra độ lệch + đệ quy xuống 2 cây con
        return Math.abs(leftHeight - rightHeight) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    // ================== HÀM TÍNH CHIỀU CAO ==================
    public static int maxDepth(TreeNode root) {

        // Nếu node null → cao = 0
        if (root == null)
            return 0;

        // 1 + chiều cao lớn nhất của 2 bên
        return 1 + Math.max(maxDepth(root.left),
                maxDepth(root.right));
    }

    // ================== HÀM BUILD CÂY TỪ INPUT ==================
    public static TreeNode buildTree(String[] arr) {

        // Nếu rỗng hoặc root = null
        if (arr.length == 0 || arr[0].equals("null"))
            return null;

        // Tạo root
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        // Queue để build theo level order
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;

        // Duyệt mảng để gán con trái/phải
        while (!queue.isEmpty() && i < arr.length) {

            TreeNode current = queue.poll();

            // ----- Con trái -----
            if (!arr[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.add(current.left);
            }
            i++;

            if (i >= arr.length)
                break;

            // ----- Con phải -----
            if (!arr[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }
}

// ###Input

// ```3 9 20 null null 15 7```

// ###Output

// ```
// Cay CAN BANG```

// ---

// ###Input

// ```1 2 null 3 null 4```

// ###Output

// ```
// Cay KHONG
// CAN BANG```

// ---

// #📊
// Luồng chạy
// chương trình

// ```
// Nhập chuỗi→split→buildTree↓
// tạo cây↓isBalanced↙↘
// maxDepth đệ
// quy con↓
// in kết quả```

// ---

// #
// Nếu bạn
// muốn nâng
// cấp thêm

// Mình có
// thể viết
// tiếp cho bạn:

// *🔹
// Nhập bằng
// số lượng node+cạnh*🔹
// Nhập dạng DFS*🔹
// In cây
// ra màn hình*🔹
// Bản tối

// ưu O(n)
// * 🔹 Viết theo style bạn

// hay dùng (while, Scanner…)

// Cứ gửi yêu cầu mình code lại đúng form bạn học 👍

// ---

// # 1️⃣ Ý tưởng chính của thuật toán

// Mục tiêu:
// Kiểm tra cây nhị phân có **Balanced** hay không.

// Điều kiện Balanced:

// ```
// | chiều cao trái - chiều cao phải | ≤ 1
// ```

// và **2 cây con cũng phải balanced**.

// ---

// # 2️⃣ Phân tích hàm `isBalanced`

// ```java
// public boolean isBalanced(TreeNode root) {
// ```

// Hàm này dùng để kiểm tra cây có cân bằng không.

// ---

// ## (1) Base case

// ```java
// if (root == null)
// return true;
// ```

// 👉 Nếu cây rỗng → coi như cân bằng.

// Vì:

// * Không có node nào lệch cả
// * Height = 0

// ---

// ## (2) Kiểm tra độ lệch chiều cao

// ```java
// Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
// ```

// Ý nghĩa:

// 1. Tính chiều cao cây con trái
// 2. Tính chiều cao cây con phải
// 3. Lấy hiệu tuyệt đối
// 4. Nếu ≤ 1 → đạt điều kiện cân bằng tại node này

// ---

// ## (3) Kiểm tra đệ quy 2 bên

// ```java
// isBalanced(root.left) &&
// isBalanced(root.right);
// ```

// 👉 Không chỉ root cân bằng là đủ.

// Phải đảm bảo:

// * Cây con trái balanced
// * Cây con phải balanced

// ---

// ## (4) Ghép điều kiện

// Toàn bộ dòng:

// ```java
// return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 &&
// isBalanced(root.left) &&
// isBalanced(root.right);
// ```

// Nghĩa là:

// ```
// Node hiện tại cân bằng
// AND
// Cây trái cân bằng
// AND
// Cây phải cân bằng
// ```

// → Thiếu 1 cái là `false`.

// ---

// # 3️⃣ Hàm `maxDepth` (tính chiều cao)

// ```java
// private int maxDepth(TreeNode root) {
// ```

// Hàm này tính **chiều cao của cây**.

// ---

// ## (1) Base case

// ```java
// if (root == null)
// return 0;
// ```

// Cây rỗng → cao = 0.

// ---

// ## (2) Công thức đệ quy

// ```java
// return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
// ```

// Giải thích:

// * Lấy chiều cao trái
// * Lấy chiều cao phải
// * Chọn cái lớn hơn
// * +1 (node hiện tại)

// ---

// # 4️⃣ Minh hoạ cách chạy thuật toán

// Ví dụ cây:

// ```
// 1
// / \
// 2 3
// /
// 4
// ```

// ---

// ## Bước 1 — tại node 1

// Tính:

// ```
// height(left) = ?
// height(right) = ?
// ```

// ---

// ## Bước 2 — tính height trái

// Node 2:

// ```
// height = 1 + max(height(4), 0)
// ```

// Node 4:

// ```
// height = 1
// ```

// → Node 2 = 2

// ---

// ## Bước 3 — height phải

// Node 3 = 1

// ---

// ## Bước 4 — so sánh

// ```
// |2 - 1| = 1 → OK
// ```

// Tiếp tục kiểm tra node 2, 3…

// ---

// # 5️⃣ Cây lệch thì sao?

// Ví dụ:

// ```
// 1
// /
// 2
// /
// 3
// ```

// Tại node 1:

// ```
// left = 2
// right = 0
// |2 - 0| = 2 ❌ > 1
// ```

// → return false ngay.

// ---

// # 6️⃣ Độ phức tạp thuật toán

// ## Time complexity

// Hàm `maxDepth` bị gọi lại nhiều lần.

// Mỗi node:

// * Gọi depth trái
// * Gọi depth phải

// → Tổng:

// ```
// O(n²)
// ```

// Worst case (cây lệch).

// ---

// ## Space complexity

// Do recursion stack:

// ```
// O(h)
// ```

// h = chiều cao cây.

// ---

// # 7️⃣ Tóm tắt luồng chạy

// ```
// isBalanced(root)
// │
// ├─ tính depth trái
// ├─ tính depth phải
// ├─ so sánh lệch
// │
// ├─ gọi isBalanced(trái)
// └─ gọi isBalanced(phải)
// ```

// Lặp lại đến lá.

// ---

// # 8️⃣ Điểm mạnh & yếu

// ### ✅ Dễ hiểu

// * Tách riêng height
// * Logic rõ ràng

// ### ❌ Chậm

// * Tính height lặp lại nhiều lần

// → Có bản tối ưu O(n) (DFS trả về -1 nếu lệch).

// ---

// Nếu bạn muốn mình giải tiếp:

// * Cách tối ưu O(n)
// * Dry run từng dòng theo stack
// * Viết lại bằng C++ / Python
// * So sánh với AVL Tree

// Cứ gửi mình nhé 👍

// // ### 1️⃣ Khái niệm cơ bản

// // **Balanced Binary Tree (Cây nhị phân cân bằng)** là cây nhị phân mà tại
// **mọi node**, độ cao của cây con bên trái và bên phải **không chênh lệch quá
// 1**.

// // Nói đơn giản:
// // Cây không bị “lệch” quá về một bên → giúp tìm kiếm, thêm, xoá nhanh hơn.

// // ---

// // ### 2️⃣ Minh hoạ trực quan

// // #### ✅ Ví dụ cây cân bằng

// // 👉 Ở mỗi node:

// // | Node | Height Left | Height Right | Chênh lệch |
// // | ------------- | ----------- | ------------ | ---------- |
// // | Root | 2 | 2 | 0 |
// // | Các node khác | ≤ 1 | ≤ 1 | ≤ 1 |

// // → Thỏa điều kiện **Balanced**

// // ---

// // #### ❌ Ví dụ cây không cân bằng

// // 👉 Có node:

// // * Cây trái cao 3
// // * Cây phải cao 0
// // → Chênh lệch = 3 > 1 ❌ → Không cân bằng

// // ---

// // ### 3️⃣ Điều kiện của đề bài (thường gặp)

// // Đề “Balanced Binary Tree” trên LeetCode / bài tập DS&A thường yêu cầu:

// // > **Cho một cây nhị phân, hãy kiểm tra xem nó có phải là cây cân bằng hay
// không.**

// // ---

// // ### 4️⃣ Định nghĩa chính xác (dùng trong code)

// // Một cây là **Balanced** nếu:

// // ```
// // | height(left) - height(right) | ≤ 1
// // ```

// // và

// // ```
// // left subtree cũng balanced
// // right subtree cũng balanced
// // ```

// // → Tức là phải đúng **ở mọi node**, không chỉ mỗi root.

// // ---

// // ### 5️⃣ Ví dụ input / output

// // #### Ví dụ 1

// // ```
// // 3
// // / \
// // 9 20
// // / \
// // 15 7
// // ```

// // Output: `true` ✅
// // → Vì mọi node lệch ≤ 1

// // ---

// // #### Ví dụ 2

// // ```
// // 1
// // /
// // 2
// // /
// // 3
// // /
// // 4
// // ```

// // Output: `false` ❌
// // → Lệch quá nhiều về bên trái

// // ---

// // ### 6️⃣ Ý tưởng giải (tư duy thuật toán)

// // Cách làm chuẩn:

// // 1. Tính **height** của cây con trái
// // 2. Tính **height** của cây con phải
// // 3. Nếu lệch > 1 → false
// // 4. Đệ quy xuống kiểm tra tiếp

// // ---

// // ### 7️⃣ Pseudocode ngắn gọn

// // ```java
// // boolean isBalanced(TreeNode root) {
// // if (root == null) return true;

// // int left = height(root.left);
// // int right = height(root.right);

// // if (Math.abs(left - right) > 1) return false;

// // return isBalanced(root.left) && isBalanced(root.right);
// // }
// // ```

// // ---

// // ### 8️⃣ Độ phức tạp

// // * Cách cơ bản: `O(n²)` (do tính height nhiều lần)
// // * Cách tối ưu (DFS 1 lần): `O(n)`

// // (Nếu bạn cần mình giải thích bản tối ưu mình viết code luôn cho bạn 👍)

// // ---

// // ## Tóm tắt dễ nhớ

// // * Balanced = cân bằng chiều cao
// // * Mọi node: lệch ≤ 1
// // * Kiểm tra bằng height + đệ quy
// // * Giúp tối ưu tìm kiếm (BST, AVL, Red-Black Tree…)

// // ---

// // Nếu bạn muốn mình:

// // * Giải bằng **Java / C++ / Python**
// // * Vẽ cây theo input đề bạn đang làm
// // * Giải thích cách tối ưu O(n)

// // → Gửi đề hoặc code bạn đang làm mình giải chi tiết luôn nhé.
