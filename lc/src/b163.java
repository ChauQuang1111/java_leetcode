// đề bài “Minimum Cost to Convert String I(29/01/2026)
// ---
// Ok 👍 mình sẽ **giải thích thuật toán + thêm chú thích chi tiết trực tiếp vào code** của bạn, đúng theo cách bạn đang làm (Dijkstra theo từng ký tự).
// Ok👍mình sẽ**thêm hàm`main`dùng`Scanner`**và**chú thích chi tiết ngay trong code Java**,đúng phong cách dễ học–dễ nhớ khi thi👌

// ---

// ##✅Code hoàn chỉnh(có`main`+`Scanner`+chú thích)

// ```java

import java.util.*;

public class b163 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập chuỗi source và target
        String source = sc.next();
        String target = sc.next();

        // Số phép chuyển đổi
        int n = sc.nextInt();

        char[] original = new char[n];
        char[] changed = new char[n];
        int[] cost = new int[n];

        // Nhập các phép đổi
        for (int i = 0; i < n; i++) {
            original[i] = sc.next().charAt(0);
            changed[i] = sc.next().charAt(0);
            cost[i] = sc.nextInt();
        }

        long result = minimumCost(source, target, original, changed, cost);

        // In kết quả
        System.out.println(result);

        sc.close();
    }

    /**
     * Hàm chính giải bài toán:
     * Dùng Floyd–Warshall để tìm chi phí nhỏ nhất đổi mọi cặp ký tự
     */
    public static long minimumCost(String source, String target,
            char[] original, char[] changed, int[] cost) {

        // dis[i][j]: chi phí nhỏ nhất để đổi (char)('a'+i) -> (char)('a'+j)
        int[][] dis = new int[26][26];

        // Khởi tạo: vô cùng cho các cặp khác nhau, 0 cho chính nó
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            dis[i][i] = 0;
        }

        // Gán chi phí đổi trực tiếp từ input
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            // Nếu có nhiều cạnh u -> v, lấy cạnh rẻ nhất
            dis[u][v] = Math.min(dis[u][v], cost[i]);
        }

        // Floyd–Warshall: xét trung gian k
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dis[i][k] == Integer.MAX_VALUE)
                    continue;
                for (int j = 0; j < 26; j++) {
                    if (dis[k][j] == Integer.MAX_VALUE)
                        continue;
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        // Tính tổng chi phí đổi source -> target
        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int c1 = source.charAt(i) - 'a';
            int c2 = target.charAt(i) - 'a';

            // Không tồn tại cách đổi
            if (dis[c1][c2] == Integer.MAX_VALUE) {
                return -1;
            }
            ans += dis[c1][c2];
        }

        return ans;
    }
}

// ---

// ##🧠

// Cách nhập
// mẫu để
// test nhanh

// ###Input:

// ```
// abc bcd 3
// a b 1
// b c 2
// c d 3```

// ###Output:

// ```6```

// ---

// ##🎯
// Ghi nhớ
// nhanh khi thi

// *26
// ký tự→**Floyd–Warshall***`dis[i][j]`=
// chi phí
// nhỏ nhất đổi`i→j`*
// Không đổi được→`-1`*
// Tổng chi phí=
// tổng từng
// vị trí

// ---

// Nếu bạn muốn:

// *✂️
// Rút gọn
// code cho
// thi ICPC*🔁
// Viết lại bằng**Dijkstra***🧪
// Thêm test case
// bẫy hay gặp

// 👉
// cứ nói, mình
// hỗ trợ
// tiếp cho bạn💪✨

// // ---

// // ## 🧠 Ý tưởng tổng quát của thuật toán

// // Bài này bạn đang làm theo hướng **đồ thị + Dijkstra**, rất chuẩn 👍

// // ### Tư duy chính:

// // * Mỗi **ký tự** là một **đỉnh**
// // * Mỗi phép đổi `original[i] → changed[i]` là **cạnh có trọng số
// `cost[i]`**
// // * Với mỗi ký tự `s` trong `source`, ta chạy **Dijkstra** để tìm:

// // > chi phí nhỏ nhất để đổi `s → mọi ký tự khác`
// // * Sau đó cộng chi phí để đổi `source[i] → target[i]`

// // 👉 Có **cache kết quả Dijkstra** để không chạy lại nhiều lần cho cùng 1 ký
// tự.

// // ---

// // ## 🔎 Phân tích từng phần code (có chú thích)

// // ### 1️⃣ Hàm Dijkstra: `minimumCostFrom`

// // ```python
// // def minimumCostFrom(self, sourceChar):
// // # bests: lưu chi phí nhỏ nhất từ sourceChar đến mỗi ký tự khác
// // bests = {}

// # seenCost: chi phí tốt nhất đã biết để đi tới một ký tự
// seenCost = defaultdict(lambda: inf)
// seenCost[sourceChar] = 0

// # priority queue cho Dijkstra (cost, ký tự)
// frontier = [(0, sourceChar)]

// while len(frontier) > 0:
// reachCost, current = heappop(frontier)

// # Nếu đã xử lý ký tự này rồi thì bỏ qua
// if current in bests:
// continue

// # Đánh dấu chi phí tối ưu cho current
// bests[current] = reachCost

// # Duyệt các cạnh đi ra từ current
// for d, edgeCost in self.edges[current].items():
// totalCost = reachCost + edgeCost

// # Nếu tìm được đường rẻ hơn tới d
// if totalCost < seenCost[d]:
// heappush(frontier, (totalCost, d))
// seenCost[d] = totalCost

// return bests
// ```

// ### 📌 Ý nghĩa:

// * Đây là **Dijkstra chuẩn**
// * Trả về:

// ```python
// {
// 'b': 3,
// 'c': 5,
// 'd': 7,
// ...
// }
// ```

// ➡️ Chi phí nhỏ nhất từ `sourceChar` tới từng ký tự

// ---

// ## 2️⃣ Hàm chính: `minimumCost`

// ```python
// def minimumCost(self, source: str, target: str,
// original: List[str], changed: List[str], cost: List[int]) -> int:
// ```

// ---

// ### 2.1️⃣ Xây dựng đồ thị

// ```python
// self.edges = defaultdict(lambda: {})

// for i in range(len(original)):
// s = original[i]
// d = changed[i]
// c = cost[i]

// # Nếu có nhiều cạnh s → d, chỉ giữ cạnh rẻ nhất
// if d not in self.edges[s] or c < self.edges[s][d]:
// self.edges[s][d] = c
// ```

// 📌 `edges[a][b] = chi phí nhỏ nhất để đổi a → b`

// ---

// ### 2.2️⃣ Cache kết quả Dijkstra

// ```python
// bests = defaultdict(lambda: {})
// totalCost = 0
// ```

// * `bests[s]`: kết quả Dijkstra đã chạy từ ký tự `s`
// * Tránh chạy lại nhiều lần → **tối ưu**

// ---

// ### 2.3️⃣ Duyệt từng ký tự trong source & target

// ```python
// for s, t in zip(source, target):
// ```

// ---

// ### 2.4️⃣ Nếu giống nhau thì bỏ qua

// ```python
// if s != t:
// ```

// ---

// ### 2.5️⃣ Nếu đã có kết quả Dijkstra cho `s`

// ```python
// if t in bests[s]:
// totalCost += bests[s][t]
// ```

// ➡️ Lấy luôn chi phí, không cần tính lại

// ---

// ### 2.6️⃣ Nếu đã chạy Dijkstra nhưng không tới được `t`

// ```python
// elif len(bests[s]) > 0:
// return -1
// ```

// ➡️ Không thể chuyển đổi → **kết luận sớm**

// ---

// ### 2.7️⃣ Chưa chạy Dijkstra cho `s` → chạy

// ```python
// else:
// best = self.minimumCostFrom(s)
// bests[s] = best
// ```

// ---

// ### 2.8️⃣ Kiểm tra có đổi được sang `t` không

// ```python
// if t in best:
// totalCost += best[t]
// else:
// return -1
// ```

// ---

// ### 2.9️⃣ Trả kết quả cuối cùng

// ```python
// return totalCost
// ```

// ---

// ## ✅ Vì sao cách này đúng?

// * Xét được **đổi qua nhiều bước trung gian**
// * Dijkstra đảm bảo **chi phí nhỏ nhất**
// * Cache giúp giảm độ phức tạp

// ⏱️ Độ phức tạp:

// * Tối đa 26 lần Dijkstra
// * Mỗi lần: `O(E log V)` với `V = 26`
// → **Rất nhanh**

// ---

// ## 🧩 So sánh nhanh với Floyd–Warshall

// | Cách | Ưu điểm | Nhược điểm |
// | --------------------------- | --------------------- | ------------------ |
// | Floyd–Warshall | Code ngắn, dễ hiểu | Luôn chạy full 26³ |
// | Dijkstra + cache (bạn dùng) | Tối ưu hơn, linh hoạt | Code dài hơn |

// 👉 **Cách bạn làm là rất tốt, mang tư duy thuật toán chuẩn**

// ---

// Nếu bạn muốn:

// * 🔁 Viết lại bằng **Floyd–Warshall**
// * 🧪 Phân tích test case dễ sai
// * 📝 Viết version **siêu gọn để nhớ khi thi**

// 👉 nói mình biết nhé 😄

// ## 1️⃣ Mô tả bài toán (hiểu đề)

// Bạn được cho:

// * `source`: một chuỗi ban đầu
// * `target`: một chuỗi đích (cùng độ dài với `source`)
// * `original[i]` → `changed[i]` với **chi phí** `cost[i]`

// 👉 Mỗi dòng `(original[i], changed[i], cost[i])` nghĩa là:
// **có thể đổi ký tự `original[i]` thành `changed[i]` với chi phí `cost[i]`**

// 📌 Bạn **có thể đổi nhiều lần trung gian**, không nhất thiết đổi trực tiếp.

// ---

// ## 2️⃣ Mục tiêu

// 👉 Tính **chi phí nhỏ nhất** để biến `source` thành `target`.

// * Nếu **không thể đổi được**, trả về `-1`.

// ---

// ## 3️⃣ Ví dụ đơn giản

// ```text
// source = "abc"
// target = "bcd"

// original = ['a','b','c']
// changed = ['b','c','d']
// cost = [1, 2, 3]
// ```

// ### Phân tích:

// * a → b : tốn 1
// * b → c : tốn 2
// * c → d : tốn 3

// 👉 Tổng chi phí = **1 + 2 + 3 = 6**

// ---

// ## 4️⃣ Điều quan trọng nhất của bài này 🔑

// ### ❗ Một ký tự có thể:

// * đổi **trực tiếp**
// * hoặc đổi **qua nhiều bước trung gian** (rẻ hơn)

// Ví dụ:

// ```text
// a → b : 10
// a → c : 2
// c → b : 3
// ```

// 👉 Đổi `a → b` tốt nhất là:

// ```
// a → c → b = 2 + 3 = 5
// ```

// (chứ không phải 10)

// ➡️ Vì vậy ta phải tìm **đường đi ngắn nhất giữa các ký tự**

// ---

// ## 5️⃣ Tư duy giải bài

// ### 🧠 Mô hình hóa bài toán

// * Mỗi **ký tự** là một **đỉnh (node)**
// * Mỗi phép đổi là một **cạnh có trọng số (cost)**

// 👉 Bài toán trở thành:

// > **Tìm chi phí nhỏ nhất để đổi từng ký tự `source[i]` → `target[i]`**

// ---

// ## 6️⃣ Chiến lược chuẩn (Level I)

// Vì:

// * Chỉ có **26 chữ cái**
// * Cần tính đường đi ngắn nhất giữa mọi cặp chữ

// 👉 Dùng **Floyd–Warshall** (All-Pairs Shortest Path)

// ### Ý tưởng:

// 1. Tạo ma trận `dist[26][26]`

// * `dist[x][y]` = chi phí nhỏ nhất để đổi `x → y`
// 2. Ban đầu:

// * `dist[x][x] = 0`
// * các cặp khác = ∞
// 3. Cập nhật từ `original`, `changed`, `cost`
// 4. Chạy Floyd-Warshall để tối ưu chi phí
// 5. Với mỗi vị trí `i`:

// * cộng `dist[source[i]][target[i]]`
// * nếu = ∞ → return `-1`

// ---

// ## 7️⃣ Vì sao bài này không khó nhưng dễ sai? ⚠️

// * ❌ Chỉ xét đổi trực tiếp → **sai**
// * ❌ Không tối ưu qua trung gian → **sai**
// * ❌ Quên trường hợp `source[i] == target[i]` → **mất điểm**

// ---

// ## 8️⃣ Tóm tắt ngắn gọn

// ✅ Đây là bài:

// * **Đồ thị**
// * **Đường đi ngắn nhất**
// * **Floyd–Warshall trên 26 ký tự**
// * Sau đó cộng chi phí theo từng ký tự của chuỗi

// ---

// Nếu bạn muốn:

// * mình **vẽ sơ đồ tư duy**
// * hoặc **giải thích từng dòng code Java / Python**
// * hoặc **so sánh Floyd vs Dijkstra cho bài này**

// 👉 cứ nói nhé, mình làm tiếp cho bạn 😄
// Ok 👍 mình giải thích **thuật toán trong code Java này** một cách **rõ ràng –
// đúng tư duy thi/phỏng vấn**, bám sát từng bước bạn viết.

// ---

// ## 🧠 Ý tưởng cốt lõi của thuật toán

// Bài **2976. Minimum Cost to Convert String I** được giải bằng:

// > **Floyd–Warshall (All-Pairs Shortest Path)** trên **26 ký tự (‘a’ → ‘z’)**

// 📌 Vì:

// * Số đỉnh rất nhỏ (26)
// * Có thể đổi ký tự **qua nhiều bước trung gian**
// * Cần chi phí **nhỏ nhất giữa mọi cặp ký tự**

// ---

// ## 1️⃣ Khởi tạo ma trận khoảng cách `dis`

// ```java
// int[][] dis = new int[26][26];
// for (int i = 0; i < 26; i++) {
// Arrays.fill(dis[i], Integer.MAX_VALUE);
// dis[i][i] = 0;
// }
// ```

// ### Ý nghĩa:

// * `dis[i][j]` = chi phí nhỏ nhất để đổi ký tự `(char)('a'+i)` →
// `(char)('a'+j)`
// * Ban đầu:

// * Không biết đường đi → `∞` (`Integer.MAX_VALUE`)
// * Đổi **chính nó** → chi phí `0`

// 📌 Đây là bước **chuẩn bị đồ thị**

// ---

// ## 2️⃣ Gán chi phí đổi trực tiếp từ đề bài

// ```java
// for (int i = 0; i < cost.length; i++) {
// dis[original[i] - 'a'][changed[i] - 'a'] =
// Math.min(dis[original[i] - 'a'][changed[i] - 'a'], cost[i]);
// }
// ```

// ### Ý nghĩa:

// * Mỗi phép đổi `original[i] → changed[i]` là **1 cạnh**
// * Nếu có **nhiều cạnh trùng**, giữ cạnh **rẻ nhất**

// 📌 Sau bước này:

// * `dis[x][y]` = chi phí đổi **trực tiếp** từ `x → y`

// ---

// ## 3️⃣ Floyd–Warshall: tối ưu chi phí qua trung gian

// ```java
// for (int k = 0; k < 26; k++) {
// for (int i = 0; i < 26; i++)
// if (dis[i][k] < Integer.MAX_VALUE) {
// for (int j = 0; j < 26; j++) {
// if (dis[k][j] < Integer.MAX_VALUE) {
// dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
// }
// }
// }
// }
// ```

// ### Ý nghĩa cực kỳ quan trọng 🔥

// Đây là công thức Floyd–Warshall:

// ```
// dis[i][j] = min(
// dis[i][j],
// dis[i][k] + dis[k][j]
// )
// ```

// 📌 Nghĩa là:

// > Đổi `i → j` **có thể rẻ hơn** nếu đi qua `k`

// ### Ví dụ:

// ```
// a → b = 10
// a → c = 2
// c → b = 3
// ```

// Sau Floyd:

// ```
// a → b = 5 (qua c)
// ```

// ---

// ## 4️⃣ Tính tổng chi phí đổi `source → target`

// ```java
// long ans = 0L;
// for (int i = 0; i < source.length(); i++) {
// int c1 = source.charAt(i) - 'a';
// int c2 = target.charAt(i) - 'a';

// if (dis[c1][c2] == Integer.MAX_VALUE) {
// return -1L;
// } else {
// ans += (long) dis[c1][c2];
// }
// }
// ```

// ### Ý nghĩa:

// * Duyệt từng vị trí `i`
// * Cộng chi phí đổi:

// ```
// source[i] → target[i]
// ```
// * Nếu **không tồn tại đường đi** → trả `-1`

// 📌 Ép kiểu `long` để tránh **overflow**

// ---

// ## 5️⃣ Trả kết quả

// ```java
// return ans;
// ```

// ---

// ## ✅ Vì sao thuật toán này đúng?

// ✔ Xét được:

// * Đổi trực tiếp
// * Đổi qua nhiều trung gian
// * Chi phí nhỏ nhất

// ✔ Floyd–Warshall đảm bảo:

// * **Tối ưu cho mọi cặp ký tự**

// ✔ Với 26 ký tự:

// * `26³ = 17576` → **rất nhỏ**

// ---

// ## ⏱️ Độ phức tạp

// | Phần | Độ phức tạp |
// | -------------- | ----------- |
// | Khởi tạo | O(26²) |
// | Floyd–Warshall | O(26³) |
// | Duyệt chuỗi | O(n) |

// 👉 Tổng: **O(n + 26³)** ≈ **O(n)**

// ---

// ## 🎯 Tóm tắt 1 câu (rất hay để nhớ khi thi)

// > Bài toán được mô hình hóa thành đồ thị 26 đỉnh, dùng Floyd–Warshall để tìm
// chi phí nhỏ nhất giữa mọi cặp ký tự, rồi cộng chi phí chuyển từng ký tự của
// `source` sang `target`.

// ---

// Nếu bạn muốn:

// * ✍️ Viết **phiên bản chú thích ngay trong code**
// * 🧪 Giải thích **test case dễ sai**
// * 📌 So sánh với **Dijkstra version**

// 👉 cứ nói, mình làm tiếp cho bạn 💪
