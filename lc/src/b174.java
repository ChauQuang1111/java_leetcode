
// 1382. Balance a Binary Search Tree(09/02/2026)
// Ok 👍 — mình sẽ **giải thích thuật toán trong code của bạn theo từng bước**, đúng logic LeetCode 1382.
import java.util.*;

public class b174 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        TreeNode root = null;

        // Nhập các giá trị và insert vào BST
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            root = TreeNode.insert(root, x);
        }

        // In inorder cây ban đầu
        TreeNode.printInorder(root);
        System.out.println();

        // Gọi hàm balance
        root = TreeNode.balanceBST(root);

        // In inorder sau khi cân bằng
        TreeNode.printInorder(root);
        System.out.println();
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static TreeNode insert(TreeNode root, int val) {

            // Nếu cây rỗng → tạo node mới
            if (root == null) {
                return new TreeNode(val);
            }

            // Nếu val nhỏ hơn → sang trái
            if (val < root.val) {
                root.left = insert(root.left, val);
            }
            // Nếu val lớn hơn → sang phải
            else {
                root.right = insert(root.right, val);
            }

            return root;
        }

        // Hàm inorder để in cây
        public static void printInorder(TreeNode root) {
            if (root == null)
                return;

            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }

        static ArrayList<TreeNode> res = new ArrayList<>();

        // Hàm chính để cân bằng BST
        public static TreeNode balanceBST(TreeNode root) {

            // Bước 1: Duyệt inorder để lấy node sorted
            inorder(root);

            // Bước 2: Build lại BST cân bằng
            return Build_BST(0, res.size() - 1);
        }

        // Hàm inorder traversal
        public static void inorder(TreeNode root) {
            if (root == null)
                return;

            inorder(root.left); // duyệt trái
            res.add(root); // thêm node vào list
            inorder(root.right); // duyệt phải
        }

        // Hàm build BST cân bằng từ mảng node đã sorted
        public static TreeNode Build_BST(int start, int end) {

            // Điều kiện dừng
            if (start > end)
                return null;

            // Lấy phần tử giữa làm root
            int mid = (start + end) / 2;
            TreeNode node = res.get(mid);

            // Xây cây con trái
            node.left = Build_BST(start, mid - 1);

            // Xây cây con phải
            node.right = Build_BST(mid + 1, end);

            return node;
        }

    }
}

// Definition for a binary tree node.

// ---

// # 1️⃣ Ý tưởng tổng thể của thuật toán

// Thuật toán gồm **2 giai đoạn chính**:

// | Bước | Việc làm | Mục đích |
// | ---- | --------------------- | ----------------------------- |
// | 1 | Inorder traversal | Lấy node theo thứ tự tăng dần |
// | 2 | Build lại BST từ mảng | Tạo cây cân bằng |

// ---

// # 2️⃣ Vì sao dùng Inorder ?

// Vì cây đầu vào là **BST** nên:

// 👉 Inorder (Left → Root → Right)
// → luôn cho ra dãy **tăng dần**

// Ví dụ:

// ```
// BST lệch:

// 1
// \
// 2
// \
// 3
// ```

// Inorder → `[1, 2, 3]`

// Tức là ta đã có **sorted array**.

// ---

// # 3️⃣ Phân tích code từng phần

// ---

// ## (1) Khai báo mảng lưu node

// ```java
// ArrayList<TreeNode> res = new ArrayList<TreeNode>();
// ```

// 👉 Dùng để lưu node theo thứ tự inorder.

// Lưu **node** chứ không chỉ value
// → để build lại cây luôn.

// ---

// ## (2) Hàm chính

// ```java
// public TreeNode balanceBST(TreeNode root) {
// inorder(root);
// return Build_BST(0, res.size() - 1);
// }
// ```

// Luồng chạy:

// 1. Gọi `inorder(root)`
// → đưa toàn bộ node vào `res` (sorted)

// 2. Gọi `Build_BST()`
// → dựng cây cân bằng

// ---

// # 4️⃣ Bước 1 — Inorder traversal

// ```java
// public void inorder(TreeNode root ){
// if(root == null) return;

// inorder(root.left);
// res.add(root);
// inorder(root.right);
// }
// ```

// ## Luồng hoạt động

// Giả sử cây:

// ```
// 2
// / \
// 1 3
// ```

// Chạy:

// 1. Đi trái → 1 → add
// 2. Add 2
// 3. Đi phải → 3 → add

// 👉 `res = [1, 2, 3]`

// ---

// ## Ý nghĩa

// Ta đã biến:

// ```
// Cây BST → Mảng sorted node
// ```

// Đây là bước quan trọng nhất.

// ---

// # 5️⃣ Bước 2 — Build lại BST cân bằng

// ```java
// public TreeNode Build_BST(int start, int end){
// if(start > end) return null;

// int mid = (start + end) / 2;
// TreeNode node = res.get(mid);

// node.left = Build_BST(start, mid - 1);
// node.right = Build_BST(mid + 1, end);

// return node;
// }
// ```

// ---

// ## Ý tưởng

// Luôn chọn **phần tử giữa** làm root.

// Vì:

// * Bên trái ≈ bên phải
// * → cây cân bằng

// ---

// ## Ví dụ minh họa

// `res = [1, 2, 3, 4, 5]`

// ### Lần 1:

// ```
// mid = 2 → node = 3 (root)
// ```

// Chia:

// * Trái: `[1, 2]`
// * Phải: `[4, 5]`

// ---

// ### Lần 2 — build trái

// ```
// mid = 0 → node = 1
// ```

// Phải của 1 → 2

// ---

// ### Lần 3 — build phải

// ```
// mid = 3 → node = 4
// ```

// Phải của 4 → 5

// ---

// ## Cây kết quả

// ```
// 3
// / \
// 1 4
// \ \
// 2 5
// ```

// 👉 Balanced.

// ---

// # 6️⃣ Vì sao code này đúng?

// Vì nó đảm bảo:

// ### ✔ Vẫn là BST

// Do mảng inorder đã sorted:

// ```
// left < root < right
// ```

// ---

// ### ✔ Cân bằng

// Vì mỗi lần chọn **mid**:

// ```
// Số node trái ≈ số node phải
// ```

// → Height tối thiểu.

// ---

// # 7️⃣ Độ phức tạp

// ## Time Complexity

// | Bước | Độ phức tạp |
// | --------- | ----------- |
// | Inorder | O(n) |
// | Build BST | O(n) |
// | Tổng | **O(n)** |

// ---

// ## Space Complexity

// | Thành phần | Bộ nhớ |
// | --------------- | -------- |
// | ArrayList | O(n) |
// | Recursion stack | O(log n) |

// ---

// # 8️⃣ Điểm hay trong code của bạn

// Bạn làm đúng chuẩn LeetCode solution:

// ✔ Không tạo node mới
// ✔ Tái sử dụng node cũ
// ✔ Không cần sort
// ✔ Chỉ cần inorder

// ---

// # 9️⃣ Lưu ý nhỏ (optimization)

// Mid nên viết:

// ```java
// int mid = start + (end - start) / 2;
// ```

// Tránh overflow (dù bài này ít xảy ra).

// ---

// # 🔟 Tóm tắt thuật toán

// ```
// 1. Inorder BST → mảng sorted node
// 2. Chọn mid làm root
// 3. Đệ quy build trái
// 4. Đệ quy build phải
// 5. Trả về cây balanced
// ```

// ---

// Nếu bạn muốn mình giải thích thêm:

// * Dry run full cây lớn
// * So sánh với AVL / Red-Black Tree
// * Viết lại bằng value thay vì node
// * Code iterative thay vì recursion

// Bạn muốn đào sâu phần nào?
