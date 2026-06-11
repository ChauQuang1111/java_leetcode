// Number of Ways to Assign Edge Weights I(11/06/2026)
// # Tóm tắt đề bài

// # Cho một cây (tree) gồm n đỉnh, gốc là đỉnh 1.

// # Mỗi cạnh ban đầu có trọng số 0, sau đó bạn phải gán cho mỗi cạnh một trọng số là 1 hoặc 2.

// # Chi phí của một đường đi = tổng trọng số các cạnh trên đường đi đó. (LeetCode)

// # Yêu cầu

// # Tìm một đỉnh x nằm ở độ sâu lớn nhất (maximum depth).

// # Chỉ xét đường đi từ gốc 1 → x.

// # Đếm xem có bao nhiêu cách gán trọng số 1 hoặc 2 cho các cạnh trên đường đi này sao cho:

// # [

// # \text{Tổng trọng số trên đường đi là số lẻ}

// # ]

// # Các cạnh không nằm trên đường đi này không cần quan tâm. (LeetCode)

// # Ví dụ 1

// # edges = [[1,2]]

// # Cây:



// # 1

// # |

// # 2

// # Đường đi 1 → 2 có 1 cạnh.

// # Các cách gán:



// # 1  => tổng = 1 (lẻ)   ✓

// # 2  => tổng = 2 (chẵn) ✗

// # => Có 1 cách.

// # Kết quả:



// # 1

// # Ví dụ 2

// # edges = [[1,2],[1,3],[3,4],[3,5]]

// # Cây:



// // #     1

// #    / \

// #   2   3

// #      / \

// #     4   5

// # Độ sâu lớn nhất là 2, các đỉnh 4 và 5 đều đạt độ sâu này.

// # Chọn đường đi:



// # 1 -> 3 -> 4

// # Có 2 cạnh.

// # Các cách gán:



// # (1,1) => 2  (chẵn)

// # (1,2) => 3  (lẻ)   ✓

// # (2,1) => 3  (lẻ)   ✓

// # (2,2) => 4  (chẵn)

// # => Có 2 cách. (LeetCode)

// # Ý tưởng quan trọng của bài

// # Giả sử đường đi từ gốc đến đỉnh sâu nhất có d cạnh.

// # Mỗi cạnh chỉ có thể là:



// # 1 (số lẻ)

// # 2 (số chẵn)

// # Tổng cuối cùng là lẻ khi và chỉ khi số lượng cạnh mang trọng số 1 là lẻ. (LeetCode)

// # Ví dụ:



// # 1 + 2 + 2 = 5  (lẻ)

// # 1 + 1 + 1 = 3  (lẻ)

// # Đều có số lượng số 1 là lẻ.

// # Bài toán thực chất trở thành

// # Tìm độ sâu lớn nhất d.

// # Đường đi có d cạnh.

// # Mỗi cạnh có 2 lựa chọn (1 hoặc 2).

// # Tổng số cách gán:

// # [

// # 2^d

// # ]

// # Một nửa trong số đó cho tổng lẻ, một nửa cho tổng chẵn.

// # Do đó đáp án là:

// # [

// # 2^{d-1}

// # ]

// # (sau đó lấy modulo (10^9+7)). (LeetCode)

// # Cần làm gì trong code?

// # Dùng DFS/BFS tìm maximum depth của cây.

// # Gọi độ sâu lớn nhất là d.

// # Trả về:

// # [

// # 2^{d-1} \bmod (10^9+7)

// # ]

// # Dưới đây là code của bạn với chú thích chi tiết hơn:


// from typing import List
// from collections import deque
// class Solution:
//     def assignEdgeWeights(self, edges: List[List[int]]) -> int:
//         # Số đỉnh của cây = số cạnh + 1
//         n = len(edges) + 1



//         # Danh sách kề

//         g = [[] for _ in range(n + 1)]



//         # Xây dựng cây

//         # Đề bài đảm bảo parent < child

//         # nên chỉ lưu cạnh từ cha -> con

//         for u, v in edges:

//             if u < v:

//                 g[u].append(v)

//             else:

//                 g[v].append(u)



//         # d[i] = độ sâu của đỉnh i

//         # -1 nghĩa là chưa được thăm

//         d = [-1] * (n + 1)



//         # Gốc là đỉnh 1 nên depth = 0

//         d[1] = 0



//         # BFS bắt đầu từ node 1

//         q = deque([1])



//         while q:

//             # Lấy đỉnh đầu hàng đợi

//             u = q.popleft()



//             # Duyệt các con của u

//             for v in g[u]:



//                 # Nếu chưa tính độ sâu

//                 if d[v] < 0:



//                     # Độ sâu của con = độ sâu cha + 1

//                     d[v] = d[u] + 1



//                     # Đưa vào queue để tiếp tục BFS

//                     q.append(v)



//         # Tìm độ sâu lớn nhất trong cây

//         m = max(d[1:])



//         MOD = 10**9 + 7



//         # Đường đi từ gốc đến node sâu nhất có m cạnh

//         #

//         # Mỗi cạnh có 2 cách gán trọng số:

//         #   1 hoặc 2

//         #

//         # Tổng số cách gán = 2^m

//         #

//         # Một nửa số cách cho tổng lẻ,

//         # một nửa cho tổng chẵn

//         #

//         # => số cách hợp lệ = 2^(m-1)

//         return pow(2, m - 1, MOD)

// # Ví dụ

// # edges = [[1,2],[1,3],[3,4],[3,5]]

// # Cây:



// #     1

// #    / \

// #   2   3

// #      / \

// #     4   5

// # BFS tính được:



// # d[1] = 0

// # d[2] = 1

// # d[3] = 1

// # d[4] = 2

// # d[5] = 2

// # m = 2

// # Đường đi dài nhất có 2 cạnh.

// # Mỗi cạnh có 2 lựa chọn:



// # 2^2 = 4 cách

// # Trong 4 cách đó:



// # (1,1) -> chẵn

// # (1,2) -> lẻ

// # (2,1) -> lẻ

// # (2,2) -> chẵn

// # Có đúng:



// # 2^(2-1) = 2

// # cách cho tổng trọng số là số lẻ.



// # Độ phức tạp

// # Xây cây: O(n)

// # BFS: O(n)

// # Tìm max depth: O(n)

// # Tổng:



// # Time:  O(n)

// # Space: O(n)


// Dưới đây là phiên bản có hàm main dùng Scanner và chú thích từng phần của thuật toán.



import java.util.*;
public class b256 {
  
    static Scanner sc = new Scanner(System.in);
    static long MOD = 1_000_000_007L;

    public static void main(String[] args) {
          // Nhập số cạnh của cây
        int m = sc.nextInt();

        int[][] edges = new int[m][2];

        // Nhập các cạnh
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }


        System.out.println(assignEdgeWeights(edges));

        sc.close();
    }
     public static int assignEdgeWeights(int[][] edges) {

            // Số đỉnh = số cạnh + 1
            int n = edges.length + 1;

            // deg[i] = bậc của đỉnh i
            int[] deg = new int[n + 1];

            // Đếm số đỉnh kề của mỗi đỉnh
            for (int[] e : edges) {
                deg[e[0]]++;
                deg[e[1]]++;
            }

            // Tạo danh sách kề
            int[][] g = new int[n + 1][];

            // Mỗi đỉnh có deg[i] hàng xóm
            for (int i = 1; i <= n; i++) {
                g[i] = new int[deg[i]];
            }

            // idx[i] dùng để xác định vị trí chèn tiếp theo
            int[] idx = new int[n + 1];

            // Xây dựng đồ thị vô hướng
            for (int[] e : edges) {

                int u = e[0];
                int v = e[1];

                g[u][idx[u]++] = v;
                g[v][idx[v]++] = u;
            }

            // depth[i] = độ sâu của đỉnh i tính từ gốc 1
            int[] depth = new int[n + 1];

            // Đánh dấu đã thăm
            boolean[] vis = new boolean[n + 1];

            // Hàng đợi BFS tự cài đặt bằng mảng
            int[] q = new int[n];

            int left = 0;
            int right = 0;

            // Bắt đầu BFS từ đỉnh 1
            q[right++] = 1;
            vis[1] = true;

            int maxDepth = 0;

            while (left < right) {

                // Lấy đỉnh đầu hàng đợi
                int u = q[left++];

                // Cập nhật độ sâu lớn nhất
                maxDepth = Math.max(maxDepth, depth[u]);

                // Duyệt các đỉnh kề
                for (int v : g[u]) {

                    if (!vis[v]) {

                        vis[v] = true;

                        // Độ sâu của con = độ sâu cha + 1
                        depth[v] = depth[u] + 1;

                        q[right++] = v;
                    }
                }
            }

            /*
             * Nếu đường đi dài nhất có maxDepth cạnh:
             *
             * Mỗi cạnh có 2 cách gán trọng số:
             * 1 hoặc 2
             *
             * Tổng số cách = 2^maxDepth
             *
             * Một nửa cho tổng lẻ,
             * một nửa cho tổng chẵn
             *
             * => Đáp án = 2^(maxDepth - 1)
             */
            return (int) modPow(2, maxDepth - 1);
        }

        // Tính a^b mod MOD bằng Binary Exponentiation
        public static long modPow(long a, int b) {

            long res = 1;

            while (b > 0) {

                // Nếu bit cuối của b = 1
                if ((b & 1) == 1) {
                    res = res * a % MOD;
                }

                // Bình phương cơ số
                a = a * a % MOD;

                // Dịch phải 1 bit
                b >>= 1;
            }

            return res;
        }
    
}



// Ví dụ nhập

// 4

// 1 2

// 1 3

// 3 4

// 3 5

// BFS tính độ sâu

// depth[1] = 0

// depth[2] = 1

// depth[3] = 1

// depth[4] = 2

// depth[5] = 2

// maxDepth = 2

// Tính kết quả

// 2^(2 - 1) = 2

// Ý tưởng thuật toán

// Xây dựng cây bằng danh sách kề.

// BFS từ đỉnh 1 để tìm độ sâu của tất cả đỉnh.

// Lấy độ sâu lớn nhất maxDepth.

// Đường đi dài nhất có maxDepth cạnh.

// Mỗi cạnh có 2 lựa chọn trọng số {1, 2}.

// Trong 2^maxDepth cách gán, đúng một nửa cho tổng trọng số lẻ.

// Đáp án:

// [

// 2^{maxDepth-1} \bmod (10^9+7)

// ]

// Độ phức tạp

// Xây đồ thị: O(n)

// BFS: O(n)

// Lũy thừa nhị phân: O(log n)

// Tổng:



// Time:  O(n)

// Space: O(n)