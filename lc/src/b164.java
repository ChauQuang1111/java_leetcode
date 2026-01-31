//  Minimum Cost to Convert String II(30/01/2026)
// Ok, mình **giải thích đề “Minimum Cost to Convert String II”** theo cách dễ hiểu nhất nhé 👌
// (đề này khá hay, bản chất là **đồ thị + Floyd–Warshall**)
// Ok, mình **giải thích thuật toán đúng theo code bạn đưa**, đi **từng khối một**, để bạn hiểu *vì sao code này giải được “Minimum Cost to Convert String II”* 👌
// (đây là lời giải **khó – tối ưu**, kết hợp **Trie + Floyd–Warshall + DP**)
import java.util.*;
public class b164{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
         String source = sc.next();
        String target = sc.next();

        // Nhập số lượng phép chuyển đổi
        int m = sc.nextInt();

        String[] original = new String[m];
        String[] changed = new String[m];
        int[] cost = new int[m];

        // Nhập từng phép chuyển đổi
        for (int i = 0; i < m; i++) {
            original[i] = sc.next();
            changed[i] = sc.next();
            cost[i] = sc.nextInt();
        }

        long result = minimumCost(source, target, original, changed, cost);

        System.out.println(result);
        sc.close();
    
    }
   // index dùng để đánh số cho mỗi chuỗi trong Trie
   public static int index = 0;

    public static long minimumCost(String source, String target,
                            String[] original, String[] changed, int[] cost) {

        // ===== 1. Xây Trie =====
        TrieNode root = new TrieNode();

        // Insert tất cả chuỗi vào Trie
        for (String s : original) insert(s, root);
        for (String s : changed) insert(s, root);

        // ===== 2. Tạo ma trận dist cho Floyd =====
        int[][] dist = new int[index][index];

        for (int i = 0; i < index; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0; // đổi chính nó thì cost = 0
        }

        // Gán chi phí đổi trực tiếp
        for (int i = 0; i < cost.length; i++) {
            int x = getIndex(original[i], root);
            int y = getIndex(changed[i], root);
            dist[x][y] = Math.min(dist[x][y], cost[i]);
        }

        // ===== 3. Floyd–Warshall =====
        for (int k = 0; k < index; k++) {
            for (int i = 0; i < index; i++) {
                if (dist[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 0; j < index; j++) {
                    if (dist[k][j] == Integer.MAX_VALUE) continue;
                    dist[i][j] = Math.min(dist[i][j],
                                           dist[i][k] + dist[k][j]);
                }
            }
        }

        // ===== 4. DP trên source -> target =====
        int n = source.length();
        char[] sArr = source.toCharArray();
        char[] tArr = target.toCharArray();

        // dp[i] = chi phí nhỏ nhất để đổi source[0..i-1] -> target[0..i-1]
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == Long.MAX_VALUE) continue;

            // Trường hợp ký tự giống nhau
            if (sArr[i] == tArr[i]) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            // Trường hợp đổi chuỗi con
            TrieNode node1 = root, node2 = root;
            for (int j = i; j < n; j++) {
                node1 = node1.next[sArr[j] - 'a'];
                node2 = node2.next[tArr[j] - 'a'];

                if (node1 == null || node2 == null) break;

                if (node1.index != -1 && node2.index != -1) {
                    int d = dist[node1.index][node2.index];
                    if (d != Integer.MAX_VALUE) {
                        dp[j + 1] = Math.min(dp[j + 1], dp[i] + d);
                    }
                }
            }
        }

        return dp[n] == Long.MAX_VALUE ? -1 : dp[n];
    }

    // ===== Insert chuỗi vào Trie =====
    public static void insert(String s, TrieNode root) {
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (root.next[c] == null)
                root.next[c] = new TrieNode();
            root = root.next[c];
        }
        // Gán index cho node kết thúc chuỗi
        if (root.index == -1)
            root.index = index++;
    }

    // ===== Lấy index của chuỗi trong Trie =====
    public static int getIndex(String s, TrieNode root) {
        for (int i = 0; i < s.length(); i++) {
            root = root.next[s.charAt(i) - 'a'];
        }
        return root.index;
    }

    
/*
 * TrieNode:
 *  - next[26]: con trỏ đến ký tự tiếp theo
 *  - index: đánh số chuỗi (chỉ node kết thúc chuỗi mới có index)
 */
public static class TrieNode {
    TrieNode[] next = new TrieNode[26];
    int index = -1;

}
}
    

/*
 * Giải bài: Minimum Cost to Convert String II
 * Ý tưởng:
 *  - Dùng Trie để đánh index cho các chuỗi original / changed
 *  - Dùng Floyd–Warshall để tìm chi phí đổi chuỗi tối ưu
 *  - Dùng DP để chia source -> target thành các đoạn nhỏ nhất
 */



// ---

// # 🧠 Ý tưởng tổng quát của code

// Bài này **không chỉ đổi từng ký tự**, mà **được đổi cả chuỗi con** nếu chuỗi đó xuất hiện trong `original` / `changed`.

// 👉 Vì vậy:

// * Ta coi **mỗi chuỗi trong original / changed** là **1 đỉnh**
// * Có **chi phí đổi chuỗi → chuỗi**
// * Sau đó dùng **DP** để ghép các đoạn trong `source` → `target` sao cho chi phí nhỏ nhất

// ---

// # 1️⃣ Trie dùng để làm gì?

// ```java
// TrieNode root = new TrieNode();
// for(String s : original) insert(s, root);
// for(String s : changed) insert(s, root);
// ```

// ### 👉 Mục đích

// * Lưu **tất cả các chuỗi** xuất hiện trong `original` và `changed`
// * Gán **mỗi chuỗi một index duy nhất**

// Ví dụ:

// ```
// "ab" -> index 0
// "bc" -> index 1
// "abc" -> index 2
// ```

// 📌 `index` chính là số đỉnh trong đồ thị

// ---

// ## 📌 insert()

// ```java
// if(root.index == -1) root.index = index++;
// ```

// ➡️ **Chỉ node kết thúc 1 chuỗi** mới được gán index
// ➡️ Tránh trùng index nếu cùng chuỗi xuất hiện nhiều lần

// ---

// # 2️⃣ Ma trận dist – đồ thị chi phí

// ```java
// int[][] dist = new int[index][index];
// ```

// 👉 `dist[u][v]` = chi phí **nhỏ nhất** để đổi chuỗi `u → v`

// ### Khởi tạo

// ```java
// dist[i][i] = 0;
// dist[i][j] = INF;
// ```

// ---

// ## Gán cạnh trực tiếp

// ```java
// int x = getIndex(original[i], root);
// int y = getIndex(changed[i], root);
// dist[x][y] = min(dist[x][y], cost[i]);
// ```

// 👉 Nếu có nhiều cách đổi cùng 1 cặp chuỗi → lấy **cost nhỏ nhất**

// ---

// # 3️⃣ Floyd–Warshall trên chuỗi (rất quan trọng)

// ```java
// for i
//   for j
//     for k
//       dist[j][k] = min(dist[j][k], dist[j][i] + dist[i][k])
// ```

// ### Ý nghĩa

// Cho phép:

// ```
// A -> B (cost 3)
// B -> C (cost 5)
// ⇒ A -> C (cost 8)
// ```

// 📌 **Đây là lý do bài này không thể greedy**

// ---

// # 4️⃣ DP trên chuỗi source → target

// ```java
// long[] dp = new long[n + 1];
// dp[i] = chi phí nhỏ nhất để đổi source[0..i-1] → target[0..i-1]
// ```

// * `dp[0] = 0`
// * Mục tiêu: `dp[n]`

// ---

// ## TH1: Ký tự giống nhau

// ```java
// if(arr1[i] == arr2[i])
//     dp[i + 1] = min(dp[i + 1], dp[i]);
// ```

// 👉 Không cần đổi, cost = 0

// ---

// ## TH2: Đổi **chuỗi con**

// ```java
// for(j = i → n-1)
// ```

// Ta mở rộng:

// ```
// source[i..j]
// target[i..j]
// ```

// Bằng cách:

// ```java
// node1 = node1.next[arr1[j]]
// node2 = node2.next[arr2[j]]
// ```

// ➡️ Nếu **cả 2 đều là chuỗi hợp lệ trong Trie**
// ➡️ Và `dist[node1.index][node2.index]` tồn tại

// ```java
// dp[j+1] = min(dp[j+1], dp[i] + dist[u][v]);
// ```

// 📌 Đây chính là **chia chuỗi thành nhiều đoạn tối ưu**

// ---

// # 5️⃣ Vì sao phải dùng Trie + DP?

// ❌ Không thể duyệt mọi substring bằng string compare → quá chậm
// ✅ Trie giúp:

// * Duyệt song song `source` & `target`
// * Dừng sớm nếu không tồn tại chuỗi

// ⏱️ Độ phức tạp:

// * Trie: O(total length)
// * Floyd: O(M³) (M = số chuỗi, nhỏ)
// * DP: O(N²) (N ≤ 1000, ok)

// ---

// # 6️⃣ Kết luận

// ### Thuật toán trong code =

// > **Trie để map chuỗi → đỉnh**
// > **Floyd–Warshall để tối ưu chi phí đổi chuỗi**
// > **DP để ghép các đoạn source → target**

// ---

// ## 1 câu nhớ nhanh khi đi thi / phỏng vấn

// > *“Chuyển bài toán thành đồ thị trên chuỗi, dùng Floyd tối ưu chi phí, rồi DP chia đoạn chuỗi.”*

// ---

// Nếu bạn muốn:

// * 🔍 Mình **trace code bằng 1 test cụ thể**
// * ✍️ Vẽ sơ đồ DP từng bước
// * 🧩 So sánh với cách giải chậm / sai thường gặp

// 👉 Cứ nói, mình đi tiếp cho bạn 👍

// ---

// ## 1️⃣ Bài toán nói gì?

// Bạn được cho:

// * `source`: chuỗi ban đầu
// * `target`: chuỗi cần biến đổi
// * Hai mảng:

//   * `original[i]` → ký tự ban đầu
//   * `changed[i]` → ký tự có thể đổi sang
//   * `cost[i]` → chi phí cho **1 lần đổi**

// 👉 Mỗi phép đổi là **đổi 1 ký tự thành 1 ký tự khác**, có thể làm **nhiều bước trung gian**.

// ---

// ## 2️⃣ Mục tiêu

// Biến `source` thành `target` sao cho:

// * Độ dài hai chuỗi **bằng nhau**
// * Từng vị trí `i`, ta biến `source[i]` → `target[i]`
// * **Tổng chi phí nhỏ nhất**
// * Nếu có vị trí **không thể đổi được**, trả về `-1`

// ---

// ## 3️⃣ Điều quan trọng nhất (rất hay bị hiểu nhầm)

// ❗ **Không chỉ đổi trực tiếp**
// Bạn **được phép đổi gián tiếp**, ví dụ:

// ```
// a -> b (cost 2)
// b -> c (cost 3)
// ```

// Thì:

// ```
// a -> c (cost 5)
// ```

// 👉 Vì thế phải tìm **chi phí nhỏ nhất giữa mọi cặp ký tự**.

// ---

// ## 4️⃣ Tư duy giải bài

// ### 🔹 Bước 1: Mô hình hóa thành đồ thị

// * Mỗi ký tự `'a' → 'z'` là **1 đỉnh** (26 đỉnh)
// * Mỗi phép đổi `original[i] -> changed[i]` là **1 cạnh có trọng số `cost[i]`**

// ---

// ### 🔹 Bước 2: Tìm đường đi ngắn nhất giữa mọi cặp ký tự

// Dùng **Floyd–Warshall**:

// ```
// dis[x][y] = chi phí nhỏ nhất để đổi x → y
// ```

// * Ban đầu:

//   * `dis[i][i] = 0`
//   * `dis[x][y] = cost` nếu có phép đổi trực tiếp
//   * Không có thì = `INF`

// ---

// ### 🔹 Bước 3: Tính tổng chi phí

// Với mỗi vị trí `i` trong chuỗi:

// * Nếu `source[i] == target[i]` → cost = 0
// * Ngược lại:

//   * tra `dis[source[i]][target[i]]`
//   * nếu = `INF` → **return -1**
//   * cộng vào tổng

// ---

// ## 5️⃣ Ví dụ minh họa

// ### Input

// ```
// source = "abcd"
// target = "bcde"

// original = ['a','b','c','d']
// changed  = ['b','c','d','e']
// cost     = [1,1,1,1]
// ```

// ### Phân tích

// ```
// a -> b : 1
// b -> c : 1
// c -> d : 1
// d -> e : 1
// ```

// Tổng = **4**

// ---

// ### Ví dụ không đổi được

// ```
// source = "a"
// target = "z"
// ```

// Không có đường đi từ `a -> z`
// 👉 **Kết quả: -1**

// ---

// ## 6️⃣ Tại sao phải dùng Floyd–Warshall?

// * Chỉ có **26 ký tự** → 26³ = rất nhỏ
// * Cần **mọi cặp ngắn nhất**, không chỉ 1 nguồn
// * BFS/Dijkstra cho từng ký tự cũng được nhưng phức tạp hơn

// ---

// ## 7️⃣ Tóm tắt 1 câu (để nhớ nhanh)

// > **Bài này = xây đồ thị ký tự → Floyd–Warshall → cộng chi phí từng vị trí**

// ---

// Nếu bạn muốn, mình có thể:

// * ✔️ Giải thích lại bằng **ví dụ cụ thể hơn**
// * ✔️ Walk-through **code Java từng dòng**
// * ✔️ So sánh với **Minimum Cost to Convert String I**

// 👉 Bạn muốn đi theo hướng nào? 😄
