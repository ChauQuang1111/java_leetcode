
// # 3607. Power Grid Maintenance(06/11/2025)
// # Dưới đây là **giải thích đề bài** 3607. Power Grid Maintenance (LeetCode) — mình sẽ phân tích phần *mô tả*, *yêu cầu*, và *ý tưởng giải thuật* giúp bạn hiểu rõ. Nếu bạn muốn sau đó mình cũng có thể tìm code mẫu và giải thích chi tiết thuật toán.
// # Dưới đây là **bản giải thích chi tiết từng dòng** của đoạn code bạn gửi — đây là lời giải **cho bài “Power Grid Maintenance” (LeetCode 3607)** theo hướng **mô phỏng việc ngắt kết nối và xử lý truy vấn bảo trì trên hệ thống điện**, nhưng không dùng Union-Find theo cách thông thường.
// # Thuật toán này xử lý việc **duy trì các thành phần liên thông và tìm “nút nhỏ nhất còn hoạt động trong cùng cụm”**.
// Rất hay — đoạn code Java bạn gửi là một lời giải **tối ưu và thông minh** cho bài **LeetCode 3607: Power Grid Maintenance**.
// Thuật toán này xử lý **các truy vấn về việc bật/tắt và tìm node hoạt động nhỏ nhất trong cùng một thành phần liên thông** của hệ thống điện, tương tự bản Python trước đó — nhưng đây là **một bản triển khai tối ưu hoá, dùng đánh chỉ số khéo léo và Union-Find kết hợp sắp xếp tuyến tính**.
import java.util.*;

public class b90 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số lượng trạm điện (node)
        int n = sc.nextInt();
        // Nhập số lượng kết nối giữa các trạm
        int m = sc.nextInt();
        int[][] connections = new int[m][2];
        for (int i = 0; i < m; i++) {
            connections[i][0] = sc.nextInt();
            connections[i][1] = sc.nextInt();
        }

        // Nhập số lượng truy vấn
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt(); // Loại truy vấn (1 hoặc 2)
            queries[i][1] = sc.nextInt(); // Chỉ số node
        }

        // Gọi hàm xử lý truy vấn

        int[] result = processQueries(n, connections, queries);

        // In kết quả
        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    public static int[] processQueries(int n, int[][] connections, int[][] queries) {
        n++; // Tăng n vì node được đánh số từ 1 đến n

        // Mảng lưu "cha" (parent) của mỗi node trong DSU
        final int[] l = new int[n];
        for (int i = 1; i < n; i++) {
            l[i] = i; // Ban đầu, mỗi node là cha của chính nó
        }

        // Nối các node theo connections để tạo thành các cụm (thành phần liên thông)
        for (int[] as : connections) {
            l[getLabel(l, as[0])] = l[getLabel(l, as[1])];
        }

        // Đếm số node trong mỗi cụm
        final int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            counts[getLabel(l, i)]++;
        }

        // Biến đổi counts thành prefix sum (vị trí bắt đầu của mỗi cụm)
        updateCounts(counts);

        // starts: sao chép vị trí bắt đầu của mỗi cụm
        final int[] starts = counts.clone();

        // sorted: chứa tất cả các node, được gom nhóm theo cụm
        final int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[counts[l[i]]++] = i; // Ghi node vào đúng cụm
        }

        // Mảng lưu kết quả các truy vấn
        final int[] r = new int[queries.length];
        int len = 0; // Số lượng kết quả thực tế

        // offline[x] = true nếu node x bị tắt điện
        final boolean[] offline = new boolean[n];

        // Xử lý từng truy vấn
        for (var q : queries) {
            final int x = q[1];
            if (q[0] == 1) { // Truy vấn loại 1: hỏi node hoạt động nhỏ nhất trong cùng cụm
                if (offline[x]) {
                    final int label = l[x]; // Lấy gốc của cụm chứa x
                    final int end = counts[label]; // Vị trí kết thúc cụm
                    int start = starts[label]; // Vị trí bắt đầu cụm
                    // Tìm node đầu tiên trong cụm chưa bị tắt
                    while (start < end && offline[sorted[start]]) {
                        start++;
                    }
                    // Cập nhật lại chỉ số bắt đầu mới cho cụm
                    starts[label] = start;
                    // Nếu tất cả đều bị tắt, trả -1; ngược lại, trả node hoạt động nhỏ nhất
                    r[len++] = start == end ? -1 : sorted[start];
                } else {
                    // Nếu node x đang bật, trả về chính nó
                    r[len++] = x;
                }
            } else { // Truy vấn loại 2: tắt node x
                offline[x] = true;
            }
        }

        // Cắt mảng kết quả đến độ dài thực tế
        return Arrays.copyOf(r, len);
    }

    // ==================== HÀM PHỤ TRỢ ====================

    // Hàm chuyển mảng đếm thành prefix sum (vị trí bắt đầu của mỗi cụm)
    public static void updateCounts(int[] count) {
        int sum = 0;
        for (int r = 0; r < count.length; r++) {
            final int newSum = sum + count[r];
            count[r] = sum;
            sum = newSum;
        }
    }

    // DSU: tìm gốc của node với nén đường đi
    static int getLabel(final int[] labels, int idx) {
        final int current = labels[idx];
        return (current == idx || current < 0) ? idx : (labels[idx] = getLabel(labels, current));
    }

}

// Hãy cùng **phân tích chi tiết** từng phần của thuật toán này nhé 👇

// ---

// ## 🧩 **1️⃣ Ý tưởng tổng quát**

// Mạng điện có:

// * `n` trạm điện (node),
// * Một số kết nối `connections` giữa các trạm (biểu diễn cạnh của đồ thị),
// * Một danh sách truy vấn `queries` gồm 2 loại:

// * `[1, x]`: Hỏi **trạm hoạt động nhỏ nhất trong cùng cụm (thành phần liên
// thông)** với trạm `x`.
// * `[2, x]`: Tắt trạm `x` (ngắt điện).

// Mục tiêu: Xử lý lần lượt các truy vấn, với tốc độ nhanh (O(n log n) hoặc
// O(n)).

// ---

// ## ⚙️ **2️⃣ Các thành phần chính trong code**

// ### 🧠 **Union-Find (Disjoint Set Union - DSU)**

// ```java
// for (int[] as : connections) {
// l[getLabel(l, as[0])] = l[getLabel(l, as[1])];
// }
// ```

// * Mảng `l` lưu "cha" của mỗi node (tương tự `parent` trong DSU).
// * Hàm `getLabel()` thực hiện **tìm gốc và nén đường đi (path compression)**.
// * Sau vòng này, các node cùng thành phần có cùng `label` gốc.

// ---

// ### 🧮 **Đếm số node trong từng thành phần**

// ```java
// for (int i = 0; i < n; i++) {
// counts[getLabel(l, i)]++;
// }
// ```

// * `counts[label]` = số node trong thành phần có gốc `label`.

// ---

// ### 📊 **Tính chỉ số bắt đầu của từng thành phần (prefix sum)**

// ```java
// updateCounts(counts);
// ```

// Hàm này biến `counts` thành **mảng vị trí bắt đầu (offset)** cho từng cụm khi
// ta sắp xếp tất cả node theo cụm.

// Cụ thể:

// ```java
// private static void updateCounts(int[] count) {
// int sum = 0;
// for (int r = 0; r < count.length; r++) {
// final int newSum = sum + count[r];
// count[r] = sum;
// sum = newSum;
// }
// }
// ```

// Sau khi chạy, `count[label]` trở thành **vị trí bắt đầu trong mảng sắp xếp**
// cho thành phần đó.
// → Đây là **trick quan trọng giúp xử lý theo cụm nhanh** mà không cần sort
// nặng.

// ---

// ### 📦 **Tạo mảng `sorted`**

// ```java
// final int[] starts = counts.clone();
// final int[] sorted = new int[n];
// for (int i = 0; i < n; i++) {
// sorted[counts[l[i]]++] = i;
// }
// ```

// Giải thích:

// * `sorted` chứa tất cả các node, được **gom nhóm theo thành phần liên
// thông**.
// * `starts[label]` = chỉ số bắt đầu của cụm trong `sorted`.
// * `counts[label]` sau vòng này = chỉ số kết thúc của cụm trong `sorted`.

// 👉 Tức là:
// Các node trong cùng cụm `[start, end)` của `sorted` là toàn bộ node thuộc một
// component.

// ---

// ### 🧱 **Xử lý truy vấn**

// ```java
// for (var q : queries) {
// final int x = q[1];
// if (q[0] == 1) { // Loại 1: hỏi
// ...
// } else { // Loại 2: tắt
// offline[x] = true;
// }
// }
// ```

// #### ✅ Nếu truy vấn loại 1 (tìm node hoạt động nhỏ nhất cùng cụm):

// ```java
// if (offline[x]) {
// final int label = l[x];
// final int end = counts[label];
// int start = starts[label];
// while (start < end && offline[sorted[start]]) {
// start++;
// }
// starts[label] = start;
// r[len++] = start == end ? -1 : sorted[start];
// } else {
// r[len++] = x;
// }
// ```

// Diễn giải:

// * Nếu node `x` **đang hoạt động**, trả ngay `x`.
// * Nếu node `x` **đã tắt**, tìm trong cùng cụm (`label = l[x]`) node nhỏ nhất
// `sorted[start]` chưa tắt.
// * Dòng `starts[label] = start` giúp **ghi nhớ vị trí mới**, để lần sau không
// phải quét lại từ đầu → tối ưu O(1) trung bình mỗi truy vấn.

// ---

// #### ❌ Nếu truy vấn loại 2 (tắt node):

// ```java
// offline[x] = true;
// ```

// Đơn giản chỉ đánh dấu node đó là **đã tắt điện**.

// ---

// ### 🧾 **Kết quả**

// ```java
// return Arrays.copyOf(r, len);
// ```

// Trả về mảng chứa tất cả kết quả của các truy vấn loại 1.

// ---

// ## 🧩 **3️⃣ Tổng kết thuật toán**

// | Thành phần | Vai trò |
// | ---------------- |
// ------------------------------------------------------------- |
// | `l[]` | DSU để nhóm các node theo thành phần liên thông |
// | `counts[]` | Số node trong từng thành phần (sau đó dùng làm vị trí offset)
// |
// | `updateCounts()` | Chuyển số lượng thành vị trí bắt đầu trong `sorted` |
// | `sorted[]` | Node sắp xếp theo cụm (liền kề nhau trong mảng) |
// | `starts[]` | Vị trí node đầu tiên còn hoạt động trong mỗi cụm |
// | `offline[]` | Đánh dấu node nào bị tắt |
// | `queries[]` | Danh sách truy vấn: hỏi (1) hoặc tắt (2) |
// | `getLabel()` | Hàm tìm gốc của node (với nén đường đi) |

// ---

// ## ⚡ **4️⃣ Độ phức tạp**

// * **Union-Find + Sắp xếp tuyến tính:** `O(n α(n))` ~ `O(n)`
// * **Mỗi truy vấn:** trung bình `O(1)` do có “ghi nhớ” chỉ số `starts[label]`
// * **Tổng:** `O(n + q)`, rất nhanh.

// ---

// ## 💡 **5️⃣ Tóm tắt bằng lời**

// Thuật toán này:

// 1. Gom tất cả node thành các cụm qua Union-Find.
// 2. Gom node mỗi cụm liền nhau trong mảng `sorted`.
// 3. Khi hỏi, chỉ cần **quét một lần duy nhất** trong phạm vi cụm và **ghi nhớ
// vị trí mới** → không bao giờ duyệt lại phần tử cũ.
// 4. Do đó, thuật toán cực nhanh và không cần cấu trúc dữ liệu phức tạp như
// heap.

// ---

// Bạn có muốn mình **viết lại phiên bản Java này có chú thích chi tiết từng
// dòng (comment trong code)** để bạn dễ hiểu hơn không?

// # ---

// # ### 🔍 **Giải thích tổng quan ý tưởng**

// # * Ban đầu, bạn có `c` trạm điện và các kết nối giữa chúng (`connections`).
// # * Mỗi truy vấn trong `queries` có dạng:

// # * `t = 1, x`: Hỏi **nút hoạt động nhỏ nhất trong cùng thành phần** với nút
// `x`.
// # * `t = 2, x`: Ngắt (tắt) trạm điện `x`.
// # * Ta cần mô phỏng trạng thái của toàn mạng điện sau mỗi thao tác.

// # Thuật toán này:

// # * Dùng **Union-Find** để nhóm các node thuộc cùng thành phần.
// # * Dùng **next_node[]** và **comp_min[]** để **duy trì danh sách liên kết
// giữa các node còn hoạt động trong mỗi thành phần**.
// # * Dùng **offline[]** để đánh dấu node nào đã bị tắt.

// ### 🧠 **Code có chú thích chi tiết**

// from typing import List

// class Solution:
// def processQueries(self, c: int, connections: List[List[int]], queries:
// List[List[int]]) -> List[int]:
// # Khởi tạo mảng parent cho DSU (Union-Find)
// parent = list(range(c + 1))

// # Hàm find() tìm gốc của 1 node (với nén đường đi)
// def find(x):
// while parent[x] != x:
// parent[x] = parent[parent[x]] # nén đường đi để tối ưu
// x = parent[x]
// return x

// # Bước 1: Union các node có kết nối ban đầu
// for a, b in connections:
// ra, rb = find(a), find(b)
// if ra != rb:
// parent[rb] = ra # nối 2 thành phần

// # Bước 2: Chuẩn bị dữ liệu cho từng thành phần
// next_node = [0] * (c + 1) # con trỏ trỏ đến node kế tiếp trong cùng thành
// phần
// comp_min = [0] * (c + 1) # node nhỏ nhất còn hoạt động trong thành phần
// last = {} # lưu node cuối cùng trong mỗi thành phần

// # Duyệt qua từng node
// for i in range(1, c + 1):
// r = find(i) # tìm gốc (đại diện của thành phần)
// if comp_min[r] == 0:
// comp_min[r] = i # i là node nhỏ nhất trong cụm ban đầu
// else:
// next_node[last[r]] = i # liên kết node trước đó với node i
// last[r] = i # cập nhật node cuối của thành phần r

// # Bước 3: Xử lý truy vấn
// offline = [False] * (c + 1) # trạng thái hoạt động của node
// res = [] # lưu kết quả các truy vấn loại 1

// for t, x in queries:
// if t == 1:
// # Truy vấn loại 1: hỏi node nhỏ nhất còn hoạt động trong cùng thành phần với
// x
// if not offline[x]:
// res.append(x) # nếu x còn hoạt động → chính nó là câu trả lời
// else:
// r = find(x)
// m = comp_min[r] # node nhỏ nhất còn hoạt động
// res.append(m if m else -1) # nếu không có node nào thì trả về -1
// else:
// # Truy vấn loại 2: tắt node x
// if offline[x]:
// continue # nếu đã tắt rồi thì bỏ qua
// offline[x] = True # đánh dấu là tắt

// r = find(x)
// # Nếu node x là node nhỏ nhất đang hoạt động trong thành phần
// if comp_min[r] == x:
// y = next_node[x] # tìm node tiếp theo
// # Bỏ qua các node đã tắt liên tục phía sau
// while y and offline[y]:
// y = next_node[y]
// # Cập nhật node nhỏ nhất mới
// comp_min[r] = y

// return res
// # ```

// # ---

// # ### 🧩 **Ví dụ minh họa**

// # ```python
// # sol = Solution()
// # c = 5
// # connections = [[1, 2], [2, 3], [4, 5]]
// # queries = [
// # [1, 1], # hỏi node nhỏ nhất trong thành phần chứa 1
// # [2, 1], # tắt node 1
// # [1, 2], # hỏi node nhỏ nhất trong thành phần chứa 2
// # [2, 2], # tắt node 2
// # [1, 3] # hỏi node nhỏ nhất trong thành phần chứa 3
// # ]
// # print(sol.processQueries(c, connections, queries))
// # ```

// # **Kết quả:**

// # ```
// # [1, 2, 3]
// # ```

// # ---

// ### ⚙️ **Tóm tắt hoạt động của từng cấu trúc:**

// # | Tên biến | Vai trò |
// # | ------------- |
// ----------------------------------------------------------- |
// # | `parent[]` | DSU để xác định thành phần liên thông |
// # | `next_node[]` | Con trỏ sang node tiếp theo trong cùng thành phần |
// # | `comp_min[]` | Node nhỏ nhất còn hoạt động trong thành phần |
// # | `offline[]` | Đánh dấu node nào đã tắt |
// # | `res[]` | Kết quả các truy vấn loại 1 |
// # | `last` | Ghi nhớ node cuối cùng của mỗi cụm để xây chuỗi `next_node` |

// # ---

// # Bạn có muốn mình **vẽ sơ đồ minh họa cấu trúc `next_node` và cách cập nhật
// khi tắt node** để hiểu rõ hơn không?

// # ---

// # ## 📄 Mô tả đề bài

// # * Có `c` trạm phát điện (power stations) được đánh số từ `1` đến `c`.

// # * Có `n` đường cáp hai chiều (bidirectional cables) kết nối giữ các trạm
// bạn với nhau — tức tạo thành một mạng lưới (graph) giữa các trạm.

// # * Bạn sẽ có một dãy truy vấn (queries). Mỗi truy vấn là hai phần:

// # 1. `[1, x]` — tức là “kiểm tra bảo trì” cho trạm `x`.
// # 2. `[2, x]` — tức là trạm `x` **ngộp đi** (offline) — nghĩa là trạm đó
// ngừng hoạt động.

// # * Khi thực hiện truy vấn kiểu `[1, x]` (bảo trì trạm x):

// # * Nếu trạm `x` *đang online* (hoạt động) → thì trả về `x`.
// # * Nếu trạm `x` *đang offline* → thì bạn phải trả về **trạm online có số nhỏ
// nhất** trong cùng **thành phần liên thông** (connected component) với `x`.

// # * Nếu trong thành phần đó **không còn trạm nào online** → trả về `-1`.

// # * Mỗi khi có truy vấn kiểu `[2, x]`, nghĩa là trạm `x` chuyển sang trạng
// thái offline, ảnh hưởng đến liên thông và khả năng trả về trong các truy vấn
// sau.

// # ---

// # ## ✅ Yêu cầu của bài

// # * Quản lý mạng lưới trạm & cáp, theo thời gian (với các truy vấn bật/tắt
// offline và bảo trì).
// # * Cần trả lời mỗi truy vấn kiểm tra bảo trì một cách **hiệu quả** (không
// chạy lại toàn bộ mạng lưới mỗi lần).
// # * Vì số lượng trạm, đường nối, truy vấn có thể lớn, nên cần giải thuật tối
// ưu (ví dụ DSU/Union-Find, heap/priority queue, hoặc lưu trữ thông tin theo
// components).

// # ---

// # ## 🧠 Ý tưởng giải thuật

// # Đây là một mô hình điển hình: **graph + dynamic state (online/offline) +
// queries**. Sau đây là cách giải phổ biến:

// # 1. **Xây dựng graph** từ các đường cáp: mỗi trạm là một nút, mỗi cáp là
// cạnh nối hai trạm.

// # * Dùng DSU (disjoint set union) để biết mỗi nút thuộc thành phần nào.
// # * Khi offline hoặc online thay đổi, thành phần có thể bị ảnh hưởng — nhưng
// trong bài này, “offline” chỉ là trạng thái của trạm, mạng lưới cáp không bị
// thay đổi.

// # 2. **Lưu trữ cho mỗi component** một cấu trúc dữ liệu để biết được trạm
// *nhỏ nhất đang online* trong thành phần đó.

// # * Ví dụ: mỗi component có một min-heap hoặc TreeSet chứa các trạm đang
// online.
// # * Khi trạm bị offline → loại bỏ khỏi heap/TreeSet.
// # * Khi có truy vấn bảo trì `[1, x]`:

// # * xác định component của `x`,
// # * nếu `x` online → trả `x`;
// # * ngược lại → kiểm tra heap/TreeSet của component đó, nếu còn phần tử → lấy
// phần tử nhỏ nhất trả về, nếu không → `-1`.

// # 3. **Cập nhật dynamic**: khi một trạm chuyển offline (`[2, x]`), ta cập
// nhật trạng thái và cấu trúc dữ liệu của component tương ứng.

// # 4. **Phức tạp thời gian**: N trạm, M cáp, Q truy vấn.

// # * Việc tìm component là O(α(N)) với DSU.
// # * Việc truy vấn hoặc cập nhật min-heap/TreeSet là O(log N).
// # → Tổng O((N + M + Q) log N) hoặc tương đương.

// # ---

// # Nếu bạn muốn, mình có thể tìm và gửi **mã mẫu (Java hoặc Python)** cho bài
// này, và **giải thích chi tiết code từng dòng** để bạn nắm vững thuật toán.
// Bạn có muốn mình làm vậy không?
