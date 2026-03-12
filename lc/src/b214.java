// // # ## Maximize Spanning Tree Stability with Upgrades (12/03/2026)

// Mình sẽ:

// 1 ️⃣**Giải thích thuật toán của bài**2 ️⃣**Thêm chú thích trực tiếp vào code**3 ️⃣**Thêm`main()`dùng`Scanner`để chạy thử**

// Bài này là**Graph+Maximum Spanning Tree+DSU(Union Find)+Greedy**.

// ---

// #1. Ý tưởng thuật toán

// Ta cần tạo**Spanning Tree**có:

// *kết nối**tất cả n đỉnh*****không chu trình*****n-1 cạnh**

// Nhưng mục tiêu là:

// **Maximize Stability của cây**

// Stability của cây=

// >**cạnh yếu nhất trong cây(minimum edge weight)**

// ---

// ##Hai loại cạnh

// Mỗi cạnh có dạng:

// ```[u,v,strength,type]```

// *`type=1`→**cạnh bắt buộc phải dùng***`type=0`→**cạnh optional**

// ---

// #2. Các bước thuật toán

// ###Bước 1—Thêm các cạnh bắt buộc

// Duyệt tất cả edges:

// Nếu`type=1`

// *dùng**DSU union***nếu union fail→**cycle→return-1***cập nhật`minStren`

// ---

// ###Bước 2—Maximum Spanning Tree

// Các cạnh optional:

// ```sort giảm dần theo strength```

// Sau đó dùng**Kruskal**:

// ```nếu 2 node chưa cùng component→union```

// ---

// ###Bước 3—Kiểm tra graph connected

// Nếu sau khi build mà graph**không liên thông**

// →return`-1`

// ---

// ###Bước 4—Upgrade k cạnh

// Các cạnh optional đã chọn lưu trong list:

// ```zero```

// vì đã sort giảm dần nên:

// ```zero.get(zero.size()-1)=cạnh nhỏ nhất```

// Ta**upgrade k cạnh nhỏ nhất**.

// Upgrade:

// ```strength*2```

// ---

// ###Bước 5—Stability của tree

// Tree stability=

// ```min(cạnh yếu nhất optional,cạnh yếu nhất mandatory)```

// ---

// #3. Code đã thêm chú thích+main()

// ```java

import java.util.*;

public class b214 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] edges = new int[m][4];

        // nhập edges
        for (int i = 0; i < m; i++) {

            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
            edges[i][3] = sc.nextInt();
        }

        int result = maxStability(n, edges, k);

        System.out.println(result);
    }

    public static int maxStability(int n, int[][] edges, int k) {

        // stability nhỏ nhất trong các cạnh bắt buộc
        int minStren = (int) 1e6;

        Dsu dsu = new Dsu(n);

        List<Edge> edgs = new ArrayList<>();

        // duyệt các edges
        for (int i = 0; i < edges.length; i++) {

            // cạnh bắt buộc
            if (edges[i][3] == 1) {

                int paSrc = dsu.findParent(edges[i][0]);
                int paTgt = dsu.findParent(edges[i][1]);

                // nếu cùng component -> cycle
                if (paSrc == paTgt) {
                    return -1;
                }

                dsu.union(edges[i][0], edges[i][1]);

                minStren = Math.min(minStren, edges[i][2]);

            } else {

                // cạnh optional
                edgs.add(new Edge(edges[i][0], edges[i][1], edges[i][2]));
            }
        }

        // sort optional edges giảm dần
        Collections.sort(edgs);

        List<Integer> zero = new ArrayList<>();

        // xây Maximum Spanning Tree
        for (int e = 0; e < edgs.size(); e++) {

            int cs = edgs.get(e).getStrength();

            int paSrc = dsu.findParent(edgs.get(e).getSrc());
            int paTgt = dsu.findParent(edgs.get(e).getTgt());

            if (paSrc != paTgt) {

                zero.add(cs);

                dsu.union(paSrc, paTgt);
            }
        }

        // kiểm tra graph đã connected chưa
        for (int i = 0; i < n; i++) {

            if (dsu.findParent(i) != dsu.findParent(0)) {
                return -1;
            }
        }

        int va = (int) 1e6;

        if (zero.size() != 0) {

            va = 0;

            // không upgrade
            if (k == 0) {
                va = zero.get(zero.size() - 1);
            }

            // upgrade hết
            else if (k >= zero.size()) {
                va = zero.get(zero.size() - 1) * 2;
            }

            // upgrade k cạnh nhỏ nhất
            else {
                va = Math.min(
                        zero.get(zero.size() - 1) * 2,
                        zero.get(zero.size() - k - 1));
            }
        }

        return Math.min(minStren, va);
    }

    static class Dsu {

        private int parent[];
        private int siz[];
        private int n;

        public Dsu(int n) {
            this.n = n;
            parent = new int[n];
            siz = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                siz[i] = 1;
            }
        }

        // tìm root của node (path compression)
        public int findParent(int p) {

            if (parent[p] == p) {
                return p;
            }

            parent[p] = findParent(parent[p]);

            return parent[p];
        }

        // union hai component
        public void union(int a, int b) {

            int pa = findParent(a);
            int pb = findParent(b);

            if (pa != pb) {

                // union by size
                if (siz[pa] > siz[pb]) {
                    int temp = pa;
                    pa = pb;
                    pb = temp;
                }

                parent[pb] = pa;

                siz[pa] += siz[pb];
            }
        }

    }

    // Class Edge*
    // dùng để
    // lưu cạnh optional*/

    static class Edge implements Comparable<Edge> {

        private int src;
        private int tgt;
        private int strength;

        public Edge(int s, int t, int st) {
            src = s;
            tgt = t;
            strength = st;
        }

        public int getSrc() {
            return src;
        }

        public int getTgt() {
            return tgt;
        }

        public int getStrength() {
            return strength;
        }

        // sort giảm dần theo strength
        public int compareTo(Edge e) {
            return e.strength - this.strength;
        }

        public String toString() {
            return this.src + " " + this.tgt + " " + this.strength;
        }
    }

}

// ---

// #4.

// Độ phức tạp

// ###
// Time Complexity

// ```
// Sort edges:

// O(E log E)

// Union Find: O(E α(N))
// ```

// Tổng:

// ```

// O(E log E)
// ```

// ---

// # 5. Ví dụ input

// ```
// 4 5 1
// 0 1 5 1
// 1 2 3 0
// 2 3 4 0
// 0 3 2 0
// 1 3 6 0
// ```

// Output:

// ```
// 4
// ```

// ---

// 💡 Nếu bạn muốn, mình có thể giải thích thêm **3 trick quan trọng của bài
// 3600 (rất nhiều người không hiểu)**:

// 1️⃣ **Tại sao tree stability = cạnh nhỏ nhất**
// 2️⃣ **Tại sao phải sort

// giảm dần (Maximum MST)**
// 3️⃣ **Tại sao upgrade cạnh nhỏ nhất mới tối ưu**

// Hiểu 3 trick này bạn sẽ thấy bài này **từ Hard → Medium**.

// # Mình sẽ **giải thích thuật toán + thêm chú thích trực tiếp vào code** để
// bạn hiểu từng bước.
// # Bài này dùng **Disjoint Set Union (Union-Find) + Greedy kiểu Kruskal**.

// # Ý tưởng chính:

// # 1. Một số cạnh **bắt buộc phải dùng** (`edge[3] = True`)
// # 2. Nếu các cạnh bắt buộc **tạo chu trình → không thể tạo spanning tree →
// return -1**
// # 3. Sau đó dùng **các cạnh optional** để nối các component còn lại (giống
// Kruskal)
// # 4. Chọn cạnh **stability lớn trước**
// # 5. Khi đã tạo được spanning tree → **upgrade k cạnh có stability nhỏ nhất**
// # 6. Kết quả của bài là **stability nhỏ nhất trong tree** (vì tree stability
// = bottleneck edge)

// # ---

// # # Code đã thêm chú thích chi tiết

// # ```python
// from typing import List
// class Solution:
// def maxStability(self, n: int, edges: List[List[int]], k: int) -> int:

// # DSU initialization
// parent = list(range(n))
// size = [1] * n

// # find root của node v (path compression)
// def find_set(v):
// if parent[v] == v:
// return v
// parent[v] = find_set(parent[v])
// return parent[v]

// # union 2 tập hợp
// def union_sets(a, b):
// a = find_set(a)
// b = find_set(b)

// if a != b:
// # union by size
// if size[a] < size[b]:
// a, b = b, a
// parent[b] = a
// size[a] += size[b]
// return True # union thành công
// else:
// return False # đã cùng 1 component

// # kiểm tra hai node có cùng component không
// def same_set(a, b):
// a = find_set(a)
// b = find_set(b)
// return a != b

// components = n

// # stability nhỏ nhất của các cạnh bắt buộc
// min_stability = float("inf")

// optional = []

// # duyệt toàn bộ edges
// for index, edge in enumerate(edges):

// if edge[3]:
// # edge bắt buộc phải dùng

// if union_sets(edge[0], edge[1]):
// # nối thành công
// components -= 1

// # cập nhật stability nhỏ nhất
// min_stability = min(min_stability, edge[2])

// else:
// # nếu union fail -> tạo cycle
// # nghĩa là các cạnh bắt buộc tạo chu trình
// return -1

// else:
// # edge optional
// optional.append(index)

// # nếu các cạnh bắt buộc đã tạo thành spanning tree
// if components == 1:
// return min_stability

// # sort optional edges theo stability giảm dần
// optional.sort(key=lambda x: edges[x][2], reverse=True)

// stabilities = []

// # giống thuật toán Kruskal
// for index in optional:

// edge = edges[index]

// if union_sets(edge[0], edge[1]):

// # cạnh được thêm vào tree
// components -= 1
// stabilities.append(edge[2])

// if components == 1:
// break

// # nếu vẫn chưa connect hết graph
// if components > 1:
// return -1

// # upgrade k cạnh có stability nhỏ nhất
// for i in range(1, min(len(stabilities), k) + 1):
// stabilities[-i] *= 2

// # stability của tree = cạnh yếu nhất
// return min(min(stabilities), min_stability)
// # ```

// # ---

// # # Tóm tắt thuật toán

// # ### Bước 1 — DSU

// # Dùng **Union Find** để xây spanning tree.

// # ---

// # ### Bước 2 — Thêm cạnh bắt buộc

// # ```
// # edge[3] = True
// # ```

// # → phải dùng

// # Nếu tạo **cycle → return -1**

// # ---

// # ### Bước 3 — Thêm cạnh optional

// # Sort theo:

// # ```
// # stability giảm dần
// # ```

// # giống **Maximum Spanning Tree (Kruskal)**

// # ---

// ### Bước 4 — Khi đủ n-1 cạnh

// # Ta có **spanning tree**

// # ---

// # ### Bước 5 — Upgrade k cạnh yếu nhất

// # ```
// # stabilities[-i] *= 2
// # ```

// # vì list đang **giảm dần**

// # → cuối list = nhỏ nhất

// # ---

// # ### Bước 6 — Kết quả

// # Stability của cây =

// # ```
// # min(edge weight trong tree)
// # ```

// # vì **bottleneck edge quyết định stability**

// # ---

// # # Độ phức tạp

// # Time complexity:

// # ```
// # Sort edges: O(E log E)

// # Union Find: O(E α(N))
// # ```

// # → Tổng:

// # ```
// # O(E log E)
// # ```

// # ---

// # 💡 Nếu bạn muốn mình có thể giải thích thêm **3 phần quan trọng giúp bạn
// hiểu sâu bài này**:

// # 1️⃣ **Tại sao kết quả là cạnh nhỏ nhất của tree**
// # 2️⃣ **Vì sao phải sort giảm dần (Maximum Spanning Tree)**
// # 3️⃣ **Vì sao upgrade cạnh nhỏ nhất là tối ưu**

// # Ba ý này là **trick chính của bài**.

// # Bài này thường thuộc dạng **graph + spanning tree + tối ưu hóa**. Mình sẽ
// giải thích từng phần để bạn hiểu rõ.

// # ---

// # ## 1. Bối cảnh bài toán

// # Bạn có một **mạng lưới gồm các thành phố / máy chủ / điểm** được nối với
// nhau bằng các **đường (edges)**.

// # * Có **N đỉnh (nodes)**
// # * Có **M cạnh (edges)**

// # Mỗi cạnh có một giá trị gọi là:

// # 👉 **stability (độ ổn định)**

// # Giá trị này thể hiện **độ tốt / độ mạnh của kết nối**.

// # ---

// # ## 2. Spanning Tree là gì?

// # **Spanning Tree** là một tập cạnh thỏa:

// # * Kết nối **tất cả N đỉnh**
// # * **Không có chu trình**
// # * Có đúng **N − 1 cạnh**

// # Ví dụ mạng 5 đỉnh → spanning tree sẽ có **4 cạnh**.

// # ---

// # ## 3. Maximum Spanning Tree

// # Thông thường ta có:

// # * **Minimum Spanning Tree (MST)** → tổng weight nhỏ nhất

// # Nhưng bài này là:

// # 👉 **Maximum Spanning Tree**

// # Tức là:

// # Chọn **N − 1 cạnh** sao cho:

// # **tổng stability lớn nhất**

// # ---

// # ## 4. Phần "Upgrades"

// # Một số cạnh có thể **nâng cấp (upgrade)**.

// # Khi nâng cấp:

// # * stability của cạnh **tăng lên**

// # Nhưng:

// # * số lần upgrade **bị giới hạn** (ví dụ: chỉ upgrade tối đa K cạnh)

// # ---

// # ## 5. Mục tiêu bài toán

// # Bạn cần:

// # 1️⃣ Chọn các cạnh để tạo **spanning tree**

// # 2️⃣ Có thể **upgrade tối đa K cạnh**

// # 3️⃣ Sau khi upgrade, **tổng stability của spanning tree phải lớn nhất**

// # ---

// # ## 6. Tóm tắt đề bài bằng ngôn ngữ đơn giản

// # Bạn có:

// # * N đỉnh
// # * M cạnh
// # * mỗi cạnh có stability
// # * có thể **upgrade tối đa K cạnh**

// # Hãy:

// # 👉 tạo **Maximum Spanning Tree**

// # sao cho:

// # **tổng stability sau khi upgrade là lớn nhất**

// # ---

// # ## 7. Input thường gặp

// # Ví dụ:

// # ```
// # N M K
// # u v w
// # u v w
// # ...
// # ```

// # Trong đó:

// # * `u v` : hai đỉnh nối với nhau
// # * `w` : stability ban đầu
// # * `K` : số cạnh có thể upgrade

// # Upgrade có thể:

// # ```
// # w -> w + x
// # ```

// # hoặc

// # ```
// # w -> w * 2
// # ```

// # (tùy đề)

// # ---

// # ## 8. Output

// # In ra:

// # ```
// # maximum total stability
// # ```

// # ---

// # ## 9. Ý tưởng thuật toán (tổng quan)

// # Thường sẽ dùng:

// # **Kruskal + Greedy**

// # Bước cơ bản:

// # 1️⃣ Sắp xếp cạnh theo **stability giảm dần**

// # 2️⃣ Dùng **Union-Find (DSU)**

// # 3️⃣ Xây **Maximum Spanning Tree**

// # 4️⃣ Tính xem **upgrade cạnh nào giúp tăng tổng nhiều nhất**

// # ---

// # ## 10. Độ khó thật sự của bài

// # Phần khó là:

// # * chọn **cạnh nào upgrade**
// # * upgrade **trước hay sau khi vào MST**

// # Một số bài cần:

// # * **Kruskal + priority queue**
// # * hoặc **DP trên cạnh**

// # ---

// # 💡 Nếu bạn muốn, mình có thể:

// # * **vẽ hình minh họa spanning tree**
// # * **giải từng bước bằng ví dụ nhỏ**
// # * **viết code Java/Python cho bài này**

// # Chỉ cần gửi **toàn bộ đề bài hoặc input sample** mình sẽ giải chi tiết.
