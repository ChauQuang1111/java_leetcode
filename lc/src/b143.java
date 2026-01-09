// # Smallest Subtree with all the Deepest Nodes(09/01/2026)
// # Dưới đây là **giải thích dễ hiểu đề bài “Smallest Subtree with all the Deepest Nodes”** (LeetCode 865), bằng tiếng Việt 👇
// # Mình sẽ **giải thích thuật toán từng bước** và **thêm chú thích trực tiếp vào code** cho bạn, theo đúng đoạn code bạn đưa 👍
// # (giải thích theo tư duy DFS – đúng kiểu hay gặp khi phỏng vấn)
// Mình sẽ**thêm hàm`main`dùng`Scanner`**và**chú thích đầy đủ vào toàn bộ code**,theo phong cách**dễ học–dễ trình bày khi thi/phỏng vấn**👍

// ⚠️ Lưu ý trước:

// *Trên LeetCode**không cần`main`**,nhưng mình thêm để bạn**chạy local/học thuật toán**.*Ví dụ input mình dùng là**tạo cây thủ công**(vì nhập cây nhị phân bằng Scanner rất dài và không chuẩn trong đề này).

// ---

// ##Code hoàn chỉnh(có`main`,dùng`Scanner`,chú thích chi tiết)

// ```java

import java.util.*;

public class b143 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Tạo cây thủ công (đúng format LeetCode)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        TreeNode ans = subtreeWithAllDeepest(root);

        // In kết quả
        System.out.println(ans.val);

        sc.close();
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
    }

    static TreeNode res; // Lưu root của subtree nhỏ nhất cần tìm
    static int maxDepth = -1; // Độ sâu lớn nhất của toàn bộ cây

    /**
     * DFS hậu tự (post-order)
     * 
     * @param root  node hiện tại
     * @param depth độ sâu của node hiện tại
     * @return độ sâu lớn nhất của subtree tại node root
     */
    public static int dfs(TreeNode root, int depth) {

        // Nếu gặp null → đã đi hết một nhánh
        if (root == null) {
            // Cập nhật độ sâu lớn nhất toàn cây
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }

        // DFS sang trái và phải
        int left = dfs(root.left, depth + 1);
        int right = dfs(root.right, depth + 1);

        // Nếu hai nhánh có cùng độ sâu
        // và độ sâu đó là lớn nhất toàn cây
        // → root là LCA của các deepest nodes
        if (left == right && left == maxDepth) {
            res = root;
        }

        // Trả về độ sâu lớn nhất của subtree hiện tại
        return Math.max(left, right);
    }

    /**
     * Hàm chính giải bài toán
     */
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        dfs(root, 0); // Bắt đầu DFS từ root với depth = 0
        return res;
    }

}

// Tóm tắt
// tư duy

// thuật toán (1 lần đọc là nhớ)

// * DFS hậu tự
// * Mỗi node trả về **độ sâu lớn nhất của subtree**
// * Khi:

// * `leftDepth == rightDepth`
// * và `== maxDepth`
// ➡️ node hiện tại là **smallest subtree chứa tất cả deepest nodes**

// ---

// ## Gợi ý nếu đi thi / phỏng vấn

// Bạn có thể nói ngắn gọn:

// > *“I use post-order DFS to compute subtree depth.
// > When both left and right subtrees reach the global maximum depth,
// > the current node is the lowest common ancestor of all deepest nodes.”*

// ---

// Nếu bạn muốn:

// * 🔹 Nhập cây **từ Scanner dạng level-order**
// * 🔹 Viết lại **phiên bản không dùng biến global**
// * 🔹 So sánh với cách **return pair (node, depth)**

// 👉 cứ nói, mình làm tiếp cho bạn 👌

// # ---

// # ## 1. Ý tưởng chính của thuật toán

// # Thuật toán dùng **DFS (Depth-First Search)** để làm **2 việc cùng lúc**:

// # ### (1) Tìm độ sâu lớn nhất của cây (`maxDepth`)

// # * Khi DFS chạm tới `None` → tức là **đi hết 1 nhánh**
// # * Cập nhật `maxDepth` = độ sâu lớn nhất từng đạt được

// # ### (2) Tìm node thấp nhất bao trùm toàn bộ các node sâu nhất

// # * Nếu:

// # * `leftDepth == rightDepth`
// # * **và** độ sâu đó chính là `maxDepth`
// # * ⇒ node hiện tại chính là **root của subtree nhỏ nhất chứa tất cả node sâu
// nhất**

// # ---

// # ## 2. Vì sao điều kiện `left == right == maxDepth` đúng?

// # * `left == right`
// # 👉 Các node sâu nhất **xuất hiện ở cả 2 nhánh trái và phải**

// # * `== maxDepth`
// # 👉 Các node đó **thực sự là sâu nhất trong toàn cây**

// # ➡️ Node hiện tại là **Lowest Common Ancestor (LCA)** của tất cả node sâu
// nhất
// # ➡️ Đây chính là **smallest subtree**

// # ---

// # ## 3. Giải thích chi tiết từng bước DFS

// # Giả sử gọi:

// # ```python
// # dfs(root, 0)
// # ```

// # ### DFS trả về cái gì?

// # 👉 **Độ sâu lớn nhất của subtree hiện tại**

// # ---

// # ### Trường hợp 1: `root == None`

// # ```python
// # if not root:
// # self.maxDepth = max(self.maxDepth, depth)
// # return depth
// # ```

// # * Ta đã đi tới cuối một nhánh
// # * Cập nhật `maxDepth`
// # * Trả về độ sâu hiện tại

// # ---

// # ### Trường hợp 2: node bình thường

// # ```python
// # left = self.dfs(root.left, depth + 1)
// # right = self.dfs(root.right, depth + 1)
// # ```

// # * DFS trái → lấy độ sâu sâu nhất bên trái
// # * DFS phải → lấy độ sâu sâu nhất bên phải

// # ---

// # ### Kiểm tra điều kiện cập nhật kết quả

// # ```python
// # if left == right and left == self.maxDepth:
// # self.res = root
// # ```

// # * Hai bên sâu bằng nhau
// # * Đúng bằng độ sâu lớn nhất toàn cây
// # ➡️ `root` là câu trả lời (hoặc ghi đè kết quả cũ vì node này thấp hơn)

// # ---

// # ### Trả về độ sâu lớn nhất của subtree

// # ```python
// # return max(left, right)
// # ```

// # ---

// # ## 4. Code có chú thích chi tiết

// # ```python
// # Definition for a binary tree node.
// class TreeNode:
// def __init__(self, val=0, left=None, right=None):
// self.val = val
// self.left = left
// self.right = right

// class Solution:
// def __init__(self):
// self.res = None # Lưu root của subtree nhỏ nhất cần tìm
// self.maxDepth = -1 # Độ sâu lớn nhất của toàn bộ cây

// def dfs(self, root, depth):
// # Nếu gặp node None → đã đi đến cuối một nhánh
// if not root:
// # Cập nhật độ sâu lớn nhất
// self.maxDepth = max(self.maxDepth, depth)
// return depth

// # DFS sang trái và phải
// left = self.dfs(root.left, depth + 1)
// right = self.dfs(root.right, depth + 1)

// # Nếu hai bên có cùng độ sâu
// # và độ sâu đó là lớn nhất toàn cây
// # => root là subtree nhỏ nhất chứa tất cả node sâu nhất
// if left == right and left == self.maxDepth:
// self.res = root

// # Trả về độ sâu lớn nhất của subtree hiện tại
// return max(left, right)

// def subtreeWithAllDeepest(self, root: TreeNode) -> TreeNode:
// # Bắt đầu DFS từ root với depth = 0
// self.dfs(root, 0)
// return self.res
// # ```

// # ---

// # ## 5. Độ phức tạp

// # * **Time Complexity:** `O(n)`
// # → Mỗi node duyệt đúng 1 lần

// # * **Space Complexity:** `O(h)`
// # → Stack đệ quy, `h` là chiều cao cây

// # ---

// ## 6. Một câu tóm tắt để nhớ lâu (đi thi/phỏng vấn)

// # > **DFS trả về độ sâu sâu nhất của mỗi subtree;
// # > khi trái và phải chạm maxDepth cùng lúc, node hiện tại chính là smallest
// subtree chứa tất cả deepest nodes.**

// # ---

// # Nếu bạn muốn mình:

// # * 🔹 Vẽ **flow DFS từng bước**
// # * 🔹 Viết lại theo **kiểu bottom-up dễ hiểu hơn**
// # * 🔹 So sánh với cách dùng **LCA**

// # 👉 cứ nói, mình làm tiếp cho bạn 👌

// # ---

// # ## 1. Đề bài nói gì?

// # Bạn được cho **một cây nhị phân (binary tree)**.

// # 👉 Nhiệm vụ:
// # Tìm **cây con nhỏ nhất** (smallest subtree) sao cho **cây con đó chứa TẤT
// CẢ các node sâu nhất** trong cây.

// # 👉 Trả về **node gốc (root)** của cây con đó.

// # ---

// # ## 2. Giải thích các khái niệm quan trọng

// # ### 🔹 Node sâu nhất là gì?

// # * **Độ sâu (depth)** của một node = số cạnh từ **root** đến node đó.
// # * **Node sâu nhất** là node có **độ sâu lớn nhất** trong cây.

// # Ví dụ:

// # ```
// # 3
// # / \
// # 5 1
// # / \
// # 6 2
// # \
// # 7
// # ```

// # * Node `7` có độ sâu lớn nhất → là node sâu nhất.

// # ---

// # ### 🔹 Cây con (subtree) là gì?

// # * Một cây con gồm **một node bất kỳ** và **toàn bộ các node con của nó**.

// # Ví dụ:
// # Nếu chọn node `5` làm root thì cây con là:

// # ```
// # 5
// # / \
// # 6 2
// # \
// # 7
// # ```

// # ---

// # ### 🔹 “Smallest subtree” nghĩa là gì?

// # * Trong tất cả các cây con **chứa đủ các node sâu nhất**,
// # → chọn **cây con có ít node nhất**
// # → tương đương với **node thấp nhất (deepest) nhưng vẫn bao trùm hết các
// node sâu nhất**.

// # ---

// # ## 3. Ví dụ minh họa

// # ### Ví dụ 1:

// # ```
// # 3
// # / \
// # 5 1
// # / \
// # 6 2
// # ```

// # * Node sâu nhất: `6` và `2` (cùng độ sâu)
// # * Cây con nhỏ nhất chứa **cả 6 và 2** là cây có root = `5`

// # 👉 **Kết quả: node 5**

// # ---

// # ### Ví dụ 2:

// # ```
// # 1
// # / \
// # 2 3
// # ```

// # * Node sâu nhất: `2` và `3`
// # * Cây con nhỏ nhất chứa cả hai là toàn bộ cây

// # 👉 **Kết quả: node 1**

// # ---

// # ### Ví dụ 3:

// # ```
// # 1
// # \
// # 2
// # \
// # 3
// # ```

// # * Node sâu nhất: `3`
// # * Cây con nhỏ nhất chứa node đó chính là node `3`

// # 👉 **Kết quả: node 3**

// # ---

// # ## 4. Tóm tắt đề bài bằng 1 câu

// # > **Hãy tìm node thấp nhất trong cây sao cho toàn bộ các node sâu nhất đều
// nằm trong cây con của node đó.**

// # ---

// # ## 5. Gợi ý tư duy (chưa phải lời giải code)

// # * Nếu **các node sâu nhất nằm ở cả trái và phải** → kết quả là **node hiện
// tại**
// # * Nếu **chỉ nằm một phía** → đi xuống phía đó
// # * Thường giải bằng **DFS + tính chiều cao cây**

// # ---

// # Nếu bạn muốn:

// # * ✅ Giải thích **bằng hình vẽ từng bước**
// # * ✅ Viết **code Java / Python**
// # * ✅ Giải theo **tư duy phỏng vấn**

// # 👉 cứ nói mình sẽ làm tiếp nhé 👍
// Mình giải thích **thuật toán của đoạn Java code này một cách hệ thống – đúng
// bản chất**, để bạn có thể **tự trình bày khi đi thi / phỏng vấn** 👍

// ---

// ## 1. Bài toán đang giải là gì?

// 👉 **Tìm cây con nhỏ nhất (smallest subtree)** sao cho cây con đó **chứa tất
// cả các node sâu nhất** trong cây nhị phân.

// ➡️ Thực chất:
// **Tìm LCA (Lowest Common Ancestor) của tất cả các node có độ sâu lớn nhất**

// ---

// ## 2. Ý tưởng cốt lõi của thuật toán

// Thuật toán dùng **DFS hậu tự (post-order traversal)** để làm **2 việc trong 1
// lần duyệt**:

// ### (1) Xác định độ sâu lớn nhất của toàn cây (`maxDepth`)

// * Khi DFS chạm `null` → kết thúc một nhánh
// * Cập nhật `maxDepth`

// ### (2) Xác định node là root của subtree nhỏ nhất

// * Nếu:

// * Subtree trái và phải **có cùng độ sâu lớn nhất**
// * Và độ sâu đó **chính là maxDepth**
// * ⇒ Node hiện tại là **node thấp nhất bao trùm tất cả deepest nodes**

// ---

// ## 3. Ý nghĩa của hàm `dfs`

// ```java
// public int dfs(TreeNode root, int depth)
// ```

// ### 📌 Tham số

// * `root`: node hiện tại
// * `depth`: độ sâu của node hiện tại (root bắt đầu từ 0)

// ### 📌 Giá trị trả về

// 👉 **Độ sâu lớn nhất của subtree có root là `root`**

// ---

// ## 4. Phân tích từng phần của DFS

// ### 4.1. Trường hợp base case

// ```java
// if (root == null) {
// maxDepth = Math.max(maxDepth, depth);
// return depth;
// }
// ```

// 🔹 Khi gặp `null`:

// * Ta đã đi đến **cuối một nhánh**
// * `depth` lúc này chính là độ sâu của node sâu nhất trong nhánh đó
// * Cập nhật `maxDepth`
// * Trả về độ sâu này cho node cha

// ---

// ### 4.2. Duyệt trái và phải (post-order)

// ```java
// int left = dfs(root.left, depth + 1);
// int right = dfs(root.right, depth + 1);
// ```

// 🔹 Lấy:

// * `left`: độ sâu sâu nhất của subtree trái
// * `right`: độ sâu sâu nhất của subtree phải

// ---

// ### 4.3. Điều kiện cập nhật kết quả

// ```java
// if (left == right && left == maxDepth)
// res = root;
// ```

// 🔹 Giải thích:

// * `left == right`
// → Các node sâu nhất **xuất hiện ở cả 2 nhánh**
// * `left == maxDepth`
// → Đây đúng là **deepest nodes của toàn cây**

// ➡️ Node `root` là:

// * **LCA của tất cả deepest nodes**
// * **Root của smallest subtree cần tìm**

// > Vì DFS đi từ dưới lên, node **thấp nhất** thỏa điều kiện sẽ được gán cuối
// cùng → đúng yêu cầu “smallest”

// ---

// ### 4.4. Trả về độ sâu lớn nhất của subtree hiện tại

// ```java
// return Math.max(left, right);
// ```

// ➡️ Để node cha biết subtree này sâu tới đâu

// ---

// ## 5. Hàm chính

// ```java
// public TreeNode subtreeWithAllDeepest(TreeNode root) {
// dfs(root, 0);
// return res;
// }
// ```

// * Gọi DFS từ root với độ sâu 0
// * Sau khi DFS xong:

// * `maxDepth` đã biết
// * `res` chính là đáp án

// ---

// ## 6. Ví dụ tư duy nhanh

// ```
// 3
// / \
// 5 1
// / \
// 6 2
// ```

// * `maxDepth = 3`
// * Node `6` và `2` sâu nhất
// * Khi DFS lên node `5`:

// * left = 3, right = 3
// * left == right == maxDepth
// ➡️ `res = 5`

// ---

// ## 7. Độ phức tạp

// | Loại | Giá trị |
// | ----- | ------------------------------------ |
// | Time | **O(n)** – duyệt mỗi node đúng 1 lần |
// | Space | **O(h)** – stack đệ quy |

// ---

// ## 8. Một câu chốt để nhớ

// > **DFS trả về độ sâu lớn nhất của mỗi subtree;
// > khi trái và phải cùng chạm maxDepth, node hiện tại chính là smallest
// subtree chứa tất cả deepest nodes.**

// ---

// Nếu bạn muốn mình:

// * 🔹 Viết **phiên bản dễ nhớ hơn**
// * 🔹 So sánh với cách dùng **pair (node, depth)**
// * 🔹 Tóm gọn thành **3 dòng tư duy phỏng vấn**

// 👉 nói mình biết nhé 👌
