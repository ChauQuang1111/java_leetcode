// //  Find All People With Secret(19/12/2025)
// // # Dưới đây là **giải thích đề bài “Find All People With Secret” bằng tiếng Việt**, viết **dễ hiểu – đúng trọng tâm – đúng kiểu LeetCode**.
// // Dưới đây là **giải thích thuật toán (Union-Find theo thời gian)** cho lời giải Java bạn đưa ra, viết **đúng trọng tâm – dễ hiểu – đúng tinh thần LeetCode 2092**.
// Dưới đây là**phiên bản đầy đủ của lời giải Java cho LeetCode 2092**với:

// ✅**Thêm hàm`main`**✅**Dùng`Scanner`để nhập dữ liệu**✅**Chú thích chi tiết từng phần(đúng kiểu học thuật+phỏng vấn)**

// ---

// ##🧠Ý tưởng ngắn gọn(nhắc lại)

// *Dùng**Union-Find***Xử lý**theo từng thời điểm***Sau mỗi thời điểm:

// ***Chỉ giữ lại component chứa người 0***Component khác→reset(quên bí mật)

// ---

// ##✅Code Java hoàn chỉnh(có`main`+chú thích)

import java.util.*;

/*
 * LeetCode 2092 - Find All People With Secret
 * Giải bằng Union-Find theo thời gian
 */
public class b122 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * Input mẫu:
         * n = 6
         * m = 3
         * meetings:
         * 1 2 5
         * 2 3 8
         * 1 5 10
         * firstPerson = 1
         */

        int n = sc.nextInt();

        int m = sc.nextInt();

        int[][] meetings = new int[m][3];
        for (int i = 0; i < m; i++) {
            meetings[i][0] = sc.nextInt();
            meetings[i][1] = sc.nextInt();
            meetings[i][2] = sc.nextInt();
        }

        int firstPerson = sc.nextInt();

        List<Integer> result = findAllPeople(n, meetings, firstPerson);

        System.out.println(result);

        sc.close();
    }

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // =======================
        // 1. Khởi tạo Union-Find
        // =======================
        int[] parent = new int[n];

        // Ban đầu mỗi người là 1 tập riêng
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Người firstPerson biết bí mật ngay từ đầu → nối với người 0
        parent[firstPerson] = 0;

        // =======================
        // 2. Gom meetings theo thời gian
        // =======================
        int maxTime = 0;
        for (int[] meet : meetings) {
            maxTime = Math.max(maxTime, meet[2]);
        }
        @SuppressWarnings("unchecked")

        // timeArray[t] chứa danh sách các cuộc họp tại thời điểm t
        List<int[]>[] timeArray = new ArrayList[maxTime + 1];

        for (int[] meet : meetings) {
            int time = meet[2];
            if (timeArray[time] == null) {
                timeArray[time] = new ArrayList<>();
            }
            timeArray[time].add(new int[] { meet[0], meet[1] });
        }

        // =======================
        // 3. Xử lý từng thời điểm
        // =======================
        for (int t = 0; t <= maxTime; t++) {

            if (timeArray[t] == null)
                continue;

            // (1) Union tất cả các cuộc họp trong cùng thời điểm
            for (int[] meet : timeArray[t]) {
                union(meet[0], meet[1], parent);
            }

            // (2) Reset những người KHÔNG nối với người 0
            for (int[] meet : timeArray[t]) {
                int u = meet[0];
                int v = meet[1];

                if (find(u, parent) != 0) {
                    parent[u] = u;
                }
                if (find(v, parent) != 0) {
                    parent[v] = v;
                }
            }
        }

        // =======================
        // 4. Thu thập kết quả
        // =======================
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i, parent) == 0) {
                result.add(i);
            }
        }

        return result;
    }

    /*
     * Union 2 node trong Union-Find
     */
    public static void union(int u, int v, int[] parent) {
        int ru = find(u, parent);
        int rv = find(v, parent);

        if (ru != rv) {
            parent[rv] = ru;
        }
    }

    /*
     * Find với path compression
     */
    public static int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}

// Tóm tắt
// để nhớ

// nhanh (rất hợp đi thi / phỏng vấn)

// * Union-Find **không vĩnh viễn**
// * Mỗi time:

// 1. Union các cuộc họp
// 2. Reset component không chứa 0
// * Cuối cùng: ai cùng root với 0 → biết bí mật

// ---

// Nếu bạn muốn:

// * ✂️ Rút gọn code cho thi
// * 🧪 Test thêm input edge-case
// * 🔄 Viết lại theo **DFS/BFS** để so sánh

// 👉 cứ nói, mình hỗ trợ tiếp nhé 💪

// ---

// ## 🧠 Ý tưởng cốt lõi của thuật toán

// Bài này là **Union-Find nhưng KHÔNG union vĩnh viễn**.

// 👉 **Chỉ những người được nối với người 0 tại CÙNG thời điểm mới giữ được bí
// mật**
// 👉 Nếu sau time đó mà **không nối được với 0** → phải **tách ra lại**

// ---

// ## 🔑 Tư duy chính

// 1. **Người 0 là nguồn bí mật**
// 2. Ai cùng component với 0 → biết bí mật
// 3. Union **theo từng thời điểm**
// 4. Sau mỗi thời điểm:

// * Nếu component **không chứa 0** → reset lại (quên bí mật)

// ---

// ## 🧱 Phân tích từng phần code

// ---

// ### 1️⃣ Khởi tạo Union-Find

// ```java
// int[] parent = new int[n + 1];
// for (int i = 0; i <= n; i++) {
// parent[i] = i;
// }
// parent[firstPerson] = 0;
// ```

// 📌 Ý nghĩa:

// * Ban đầu mỗi người là 1 tập riêng
// * Gán `parent[firstPerson] = 0`
// → người `firstPerson` **biết bí mật ngay từ đầu**

// ---

// ### 2️⃣ Gom các cuộc họp theo thời gian

// ```java
// int maxTime = 0;
// for (int[] meet : meetings) {
// maxTime = Math.max(maxTime, meet[2]);
// }
// ```

// → Tìm thời điểm lớn nhất để tạo mảng theo time

// ```java
// List<int[]>[] timeArray = new List[maxTime + 1];
// ```

// ```java
// for (int[] meet : meetings) {
// if (timeArray[meet[2]] == null) {
// timeArray[meet[2]] = new ArrayList<>();
// }
// timeArray[meet[2]].add(new int[]{meet[0], meet[1]});
// }
// ```

// 📌 `timeArray[t]` chứa **tất cả các cuộc họp diễn ra tại thời điểm `t`**

// ---

// ### 3️⃣ Xử lý từng thời điểm (QUAN TRỌNG NHẤT)

// ```java
// for (int i = 1; i < timeArray.length; i++) {
// if (timeArray[i] != null) {
// ```

// ---

// #### 🔹 Bước 1: Union tất cả các cuộc họp tại time i

// ```java
// for (int j = 0; j < timeArray[i].size(); j++) {
// int u = timeArray[i].get(j)[0];
// int v = timeArray[i].get(j)[1];
// union(u, v, parent);
// }
// ```

// 📌 Trong **cùng thời điểm**, ai gặp nhau cũng được nối vào 1 component

// ---

// #### 🔹 Bước 2: Reset những người KHÔNG nối với 0

// ```java
// for (int j = 0; j < timeArray[i].size(); j++) {
// int u = timeArray[i].get(j)[0];
// int v = timeArray[i].get(j)[1];

// if (find(u, parent) != 0) {
// parent[u] = u;
// }
// if (find(v, parent) != 0) {
// parent[v] = v;
// }
// }
// ```

// 📌 Ý nghĩa cực kỳ quan trọng:

// | Trường hợp | Hành động |
// | ---------------------- | ------------------------ |
// | Component chứa 0 | Giữ nguyên → biết bí mật |
// | Component KHÔNG chứa 0 | Tách ra → quên bí mật |

// 👉 Điều này đảm bảo:

// * Bí mật **không truyền sang các time sau một cách sai**

// ---

// ### 4️⃣ Thu thập kết quả

// ```java
// List<Integer> results = new ArrayList<>();
// for (int i = 0; i < parent.length; i++) {
// if (parent[i] == 0) {
// results.add(i);
// }
// }
// ```

// 📌 Ai có `parent == 0` → cùng component với 0 → biết bí mật

// ---

// ## 🧩 Union & Find

// ```java
// private void union(int u, int v, int[] parent) {
// int uRoot = find(u, parent);
// int vRoot = find(v, parent);
// if (uRoot < vRoot) {
// parent[vRoot] = uRoot;
// } else {
// parent[uRoot] = vRoot;
// }
// }
// ```

// ```java
// private int find(int x, int[] parent) {
// if (parent[x] != x) {
// parent[x] = find(parent[x], parent);
// }
// return parent[x];
// }
// ```

// ✔ Union-Find chuẩn
// ✔ Có path compression

// ---

// ## 🧠 Tóm tắt tư duy (rất hay dùng khi giải thích miệng)

// > “Tôi xử lý các cuộc họp theo từng thời điểm.
// > Trong mỗi thời điểm, tôi union tất cả những người gặp nhau.
// > Sau đó, tôi chỉ giữ lại những component có chứa người 0,
// > còn lại thì reset vì họ không biết bí mật.”

// ---

// ## ⚠️ Vì sao cách này đúng?

// * Không truyền bí mật qua thời gian
// * Không cần DFS/BFS
// * Union-Find giúp xử lý nhanh khi nhiều cuộc họp

// ---

// ## ⏱ Độ phức tạp

// * **Time:** `O(m α(n))`
// * **Space:** `O(n + m)`

// ---

// Nếu bạn muốn:

// * 🔄 So sánh với **DFS solution**
// * ❌ Chỉ ra bug tiềm ẩn trong code
// * ✍️ Viết lại version dễ nhớ khi phỏng vấn

// 👉 cứ nói, mình hỗ trợ tiếp nhé 💪

// # ---

// # ## 📌 Mô tả đề bài (Diễn giải)

// # Có **n người**, được đánh số từ `0` đến `n - 1`.

// # * **Ban đầu**:

// # * Người `0` biết một **bí mật**
// # * Người `firstPerson` cũng biết bí mật này

// # * Bạn được cho một danh sách `meetings`

// # ```
// # meetings[i] = [xi, yi, timei]
// # ```

// # nghĩa là:

// # * người `xi` gặp người `yi`
// # * tại thời điểm `timei`

// # ---

// # ## 🔁 Quy tắc truyền bí mật

// # * Nếu **một người biết bí mật** gặp **người khác**
// # → người kia sẽ biết bí mật
// # * **Chỉ truyền trong cùng thời điểm**

// # * Bí mật **không được “ghi nhớ” qua các cuộc họp ở thời điểm khác**
// # * Một người **chỉ có thể truyền bí mật nếu họ đã biết bí mật trước hoặc tại
// thời điểm đó**

// # ---

// # ## 🎯 Mục tiêu

// # 👉 Hãy **trả về danh sách tất cả những người biết bí mật sau khi tất cả các
// cuộc họp kết thúc**

// # * Thứ tự trả về **không quan trọng**

// # ---

// # ## 🧠 Ví dụ minh họa

// # ### Ví dụ 1

// # ```python
// # n = 6
// # firstPerson = 1
// # meetings = [
// # [1, 2, 5],
// # [2, 3, 8],
// # [1, 5, 10]
// # ]
// # ```

// # ### Phân tích:

// # * Ban đầu: `{0, 1}` biết bí mật
// # * Thời điểm 5:

// # * 1 gặp 2 → 2 biết bí mật
// # * Thời điểm 8:

// # * 2 gặp 3 → 3 biết bí mật
// # * Thời điểm 10:

// # * 1 gặp 5 → 5 biết bí mật

// # ### ✅ Kết quả:

// # ```python
// # [0, 1, 2, 3, 5]
// # ```

// # ---

// # ## ⚠️ Điểm quan trọng dễ sai

// # ### ❌ Sai lầm thường gặp

// # * Cho rằng chỉ cần gặp nhau là truyền được bí mật
// # * Không xét **thời gian**
// # * Dùng DFS/BFS cho toàn bộ graph (sai vì mỗi thời điểm là độc lập)

// # ### ✅ Điều cần nhớ

// # | Điều kiện | Giải thích |
// # | ------------------------ | ------------------------------- |
// # | Thời gian rất quan trọng | Chỉ truyền trong cùng `time` |
// # | Graph tạm | Mỗi time block là 1 graph riêng |
// # | Biết trước mới truyền | Không “quay ngược thời gian” |

// # ---

// # ## 🧩 Vì sao bài này khó?

// # * Không phải graph thông thường
// # * Là **graph theo thời gian**
// # * Phải:

// # * sort theo time
// # * xử lý từng nhóm thời gian
// # * reset trạng thái sau mỗi time block

// # ---

// # ## 🧠 Cách tư duy khi gặp bài này

// # > “Tại mỗi thời điểm, chỉ những người đã biết bí mật mới có quyền lan
// truyền nó cho những người họ gặp **cùng thời điểm**.”

// # ---

// # Nếu bạn muốn:

// # * 🔍 Ví dụ chi tiết hơn từng bước
// # * 🧠 So sánh **DFS vs Union-Find**
// # * ✍️ Gợi ý cách trình bày khi phỏng vấn

// # 👉 cứ nói, mình giải thích tiếp nhé 💪

// # Dưới đây là **giải thích thuật toán bằng tiếng Việt** + **phiên bản code đã
// được thêm chú thích chi tiết từng bước** để bạn dễ học và dễ trình bày khi
// phỏng vấn / làm bài LeetCode.

// # ---

// # ## 🧠 Ý tưởng & Thuật toán

// # **Bài toán:**
// # Có `n` người, ban đầu **người 0** và `firstPerson` biết bí mật.
// # Các cuộc họp diễn ra tại những **thời điểm khác nhau**.
// # → Nếu một người biết bí mật và **gặp người khác tại cùng thời điểm**, họ sẽ
// truyền bí mật.

// # ---

// ### 🔑 Chiến lược chính

// # 1. **Sắp xếp meetings theo thời gian**

// # * Để xử lý các cuộc họp theo đúng thứ tự xảy ra.

// # 2. **Xử lý theo từng “khối thời gian”**

// # * Tại mỗi thời điểm `t`, tạo **graph tạm thời** gồm các cuộc họp diễn ra
// đúng thời điểm đó.
// # * Chỉ những người đã biết bí mật **trước hoặc tại thời điểm t** mới có thể
// truyền bí mật.

// # 3. **DFS trong từng khối thời gian**

// # * Nếu một người biết bí mật xuất hiện trong graph tại thời điểm đó
// # → lan truyền bí mật cho tất cả người kết nối với họ trong graph này.

// # 4. **Sau khi sang thời điểm mới**

// # * Xóa graph cũ
// # * Reset visited (vì mỗi time block là độc lập)

// # ---

// # ### ⏱ Độ phức tạp

// # * **Time:** `O(m log m)` (do sort meetings)
// # * **Space:** `O(n + m)`

// # ---

// # ## ✅ Code đã được chú thích đầy đủ

// # ```python
// from typing import List
// class Solution:
// def findAllPeople(self, n: int, meetings: List[List[int]], firstPerson: int)
// -> List[int]:
// """
// Ý tưởng:
// - Sắp xếp các cuộc họp theo thời gian
// - Với mỗi thời điểm, xây dựng graph tạm
// - Dùng DFS để lan truyền bí mật trong cùng thời điểm
// """

// # 1. Sắp xếp meetings theo thời gian tăng dần
// meetings.sort(key=lambda x: x[2])

// # 2. Những người hiện đang biết bí mật
// secretPeople = set([0, firstPerson])

// # Graph tạm cho mỗi khối thời gian
// person2people = {}

// # Đánh dấu các node đã DFS trong cùng time block
// visit = set()

// # DFS để lan truyền bí mật
// def dfs(person):
// if person in visit:
// return
// visit.add(person)
// secretPeople.add(person)

// for nei in person2people.get(person, []):
// dfs(nei)

// prev_time = -1

// # 3. Duyệt từng cuộc họp
// for p1, p2, t in meetings:

// # Nếu sang thời điểm mới → xử lý xong time block cũ
// if t != prev_time:
// # Lan truyền bí mật trong graph của time block trước
// for person in person2people:
// if person in secretPeople and person not in visit:
// dfs(person)

// # Reset graph & visited cho time block mới
// person2people = {}
// visit = set()
// prev_time = t

// # 4. Xây graph cho time block hiện tại
// person2people.setdefault(p1, []).append(p2)
// person2people.setdefault(p2, []).append(p1)

// # 5. Xử lý time block cuối cùng
// for person in person2people:
// if person in secretPeople and person not in visit:
// dfs(person)

// return list(secretPeople)
// # ```

// # ---

// # ## 📌 Tóm tắt ngắn gọn (rất hay dùng khi giải thích miệng)

// # > “Ta xử lý các cuộc họp theo thứ tự thời gian.
// # > Với mỗi thời điểm, ta tạo một graph tạm thời.
// # > Nếu trong graph đó có người đã biết bí mật, ta dùng DFS để lan truyền bí
// mật cho tất cả người kết nối với họ trong cùng thời điểm.”

// # ---

// # Nếu bạn muốn:

// # * 🔄 Viết lại bằng **Union-Find**
// # * 🧪 Giải thích bằng **ví dụ minh họa từng bước**
// # * 🎯 Tối ưu hoặc rút gọn code cho phỏng vấn

// # 👉 cứ nói, mình hỗ trợ tiếp nhé 👍
