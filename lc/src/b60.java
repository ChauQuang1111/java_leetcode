
// 1488. Avoid Flood in The City(07/10/2025)
import java.util.*;

public class b60 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();

        int[] rains = new int[n];

        for (int i = 0; i < n; i++) {
            rains[i] = sc.nextInt();
        }

        int[] result = avoidFlood(rains);

        if (result.length == 0) {
            System.out.println("Output: []");
            System.out.println("Giải thích: Không thể tránh được ngập lụt!");
        } else {
            // In kết quả dạng mảng chuẩn
            System.out.print("Output: [");
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]);
                if (i < result.length - 1)
                    System.out.print(",");
            }
            System.out.println("]");

            // Giải thích ngắn
            System.out.println("Giải thích: -1 là ngày mưa, số dương là hồ được hút.");
        }
        sc.close();
    }

    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] fa = new int[n + 1]; // Mảng "cha" dùng cho Union-Find (DSU)
        for (int i = 0; i <= n; i++) {
            fa[i] = i; // Ban đầu, mỗi vị trí là "cha" của chính nó
        }

        int[] ans = new int[n]; // Kết quả cuối cùng
        Map<Integer, Integer> fullDay = new HashMap<>(); // Lưu ngày hồ bị đầy: lake -> day

        for (int i = 0; i < n; i++) {
            int lake = rains[i];

            // Nếu hôm nay không mưa, có thể dùng để hút nước từ 1 hồ nào đó
            if (lake == 0) {
                ans[i] = 1; // tạm đặt 1 (nếu không được dùng sau này)
                continue;
            }

            // Nếu hồ này từng bị đầy trước đó
            Integer j = fullDay.get(lake);
            if (j != null) {
                // Cần tìm một ngày khô (dryDay) sau ngày j, trước ngày i để hút nước
                int dryDay = find(j + 1, fa);
                if (dryDay >= i) {
                    // Nếu không tìm được ngày phù hợp → chắc chắn bị ngập
                    return new int[] {};
                }

                // Dùng ngày dryDay để hút nước của hồ lake
                ans[dryDay] = lake;

                // Đánh dấu rằng dryDay đã được dùng (bỏ khỏi DSU)
                fa[dryDay] = find(dryDay + 1, fa);
            }

            // Ngày mưa thì không thể hút → đặt -1
            ans[i] = -1;

            // Đánh dấu rằng ngày i không thể dùng để hút nữa
            fa[i] = i + 1;

            // Lưu ngày hồ lake bị đầy gần nhất
            fullDay.put(lake, i);
        }
        return ans;
    }

    // Hàm tìm "ngày khô tiếp theo" dùng DSU (Union-Find)
    public static int find(int x, int[] fa) {
        if (fa[x] != x) {
            fa[x] = find(fa[x], fa); // Path compression
        }
        return fa[x];
    }
}

// Okk 👇 mình sẽ giải thích **rất dễ hiểu** đề bài **LeetCode 1488 – Avoid
// Flood in The City** cho bạn nha:

// ---

// ## 🧩 **Đề bài**

// Giả sử bạn có một thành phố với nhiều **hồ nước (lakes)**.
// Mỗi ngày, trời có thể **mưa** vào một hồ nào đó, hoặc **trời khô (sunny)** để
// bạn có thể **xả nước** ra khỏi một hồ nào đó.

// Bạn được cho một mảng `rains[]`, trong đó:

// * `rains[i] = x` nghĩa là **ngày thứ i** trời **mưa vào hồ x**.
// * `rains[i] = 0` nghĩa là **ngày thứ i không mưa** → bạn **có thể chọn một hồ
// bất kỳ để xả nước** (làm khô hồ đó).

// ---

// ## ⚙️ **Luật chơi / Điều kiện**

// * Khi trời **mưa vào hồ x**, nếu hồ đó **đã đầy nước** mà lại **mưa thêm**,
// thì **bị ngập (flood)** 💧 → **FAIL** (phải tránh trường hợp này).
// * Khi **trời khô**, bạn có thể chọn **một hồ đang đầy nước** để **xả nước**
// (làm khô hồ đó).
// * Bạn phải **quyết định chọn hồ nào để xả nước** sao cho **không bao giờ xảy
// ra flood** trong suốt chuỗi ngày.

// ---

// ## 🎯 **Nhiệm vụ**

// Trả về **một mảng kết quả** cùng kích thước với `rains`:

// * Nếu `rains[i] > 0` (mưa): bạn **phải để kết quả là -1** (vì bạn không làm
// gì được hôm đó).
// * Nếu `rains[i] == 0` (khô): bạn **phải chọn một hồ để xả nước** (ví dụ hồ số
// `x`), nên `res[i] = x`.

// Nếu **không có cách nào tránh được flood**, trả về mảng rỗng `[]`.

// ---

// ## 🧠 **Ví dụ 1**

// ### Input:

// ```
// rains = [1, 2, 0, 1, 2]
// ```

// ### Giải thích từng ngày:

// | Ngày | rains[i] | Hành động | Trạng thái hồ |
// | ---- | -------- | ------------------------------------ | ------------------
// |
// | 1 | 1 | Mưa vào hồ 1 | hồ 1 đầy |
// | 2 | 2 | Mưa vào hồ 2 | hồ 1,2 đều đầy |
// | 3 | 0 | Trời khô, ta chọn xả hồ 1 (ví dụ) | hồ 1 khô, hồ 2 đầy |
// | 4 | 1 | Mưa lại vào hồ 1 (được vì đã khô) | hồ 1,2 đầy |
// | 5 | 2 | Mưa vào hồ 2 (nhưng hồ 2 đang đầy ❌) | → flood! |

// ❌ Vậy cách chọn hồ 1 ở ngày 3 sai.

// ---

// ### Cách đúng:

// Ngày 3 nên **xả hồ 2** thay vì hồ 1.
// → Vì hồ 2 sẽ bị mưa lại sớm hơn.

// | Ngày | rains[i] | Hành động | Trạng thái hồ |
// | ---- | -------- | ------------ | ------------------- |
// | 1 | 1 | Mưa vào hồ 1 | hồ 1 đầy |
// | 2 | 2 | Mưa vào hồ 2 | hồ 1,2 đầy |
// | 3 | 0 | Xả hồ 2 | hồ 1 đầy, hồ 2 khô |
// | 4 | 1 | Mưa lại hồ 1 | hồ 1 đầy (đã khô) |
// | 5 | 2 | Mưa lại hồ 2 | hồ 2 đầy (đã khô) ✅ |

// ### Output:

// ```
// [-1, -1, 2, -1, -1]
// ```

// ---

// ## 🧩 **Ví dụ 2**

// ### Input:

// ```
// rains = [1, 2, 3, 4]
// ```

// → Toàn mưa, không có ngày khô → flood không xảy ra.

// ✅ Output:

// ```
// [-1, -1, -1, -1]
// ```

// ---

// ## 🧩 **Ví dụ 3**

// ### Input:

// ```
// rains = [1, 2, 0, 0, 2, 1]
// ```

// Kết quả hợp lệ (một trong nhiều cách):

// ```
// [-1, -1, 2, 1, -1, -1]
// ```

// * Ngày 3: xả hồ 2
// * Ngày 4: xả hồ 1
// → Sau đó các hồ đều an toàn.

// ---

// ## 🧮 **Ý tưởng thuật toán (tóm tắt)**

// 1. Dùng một `set` để lưu các **hồ đang đầy nước**.
// 2. Dùng `map` để lưu **ngày kế tiếp mà hồ đó sẽ bị mưa lại**.
// 3. Duyệt từng ngày:

// * Nếu là **mưa (rains[i] > 0)**:

// * Nếu hồ đó **đã đầy**, mà **chưa được xả trước khi mưa lại**, → flood ❌
// * Ngược lại, **thêm vào set** là hồ đầy.
// * Nếu là **ngày khô (rains[i] == 0)**:

// * Chọn **hồ nào cần xả sớm nhất** (sẽ mưa lại sớm nhất).
// * Nếu không có hồ nào cần xả, thì xả đại hồ nào (ví dụ 1).

// ---

// ## 🧾 **Tóm lại**

// | Trạng thái | Ý nghĩa |
// | ------------------------- |
// ---------------------------------------------------- |
// | `rains[i] > 0` | mưa vào hồ đó → kết quả là `-1` |
// | `rains[i] == 0` | trời khô → chọn hồ nào để xả nước (giá trị là số hồ) |
// | Nếu không thể tránh flood | trả về `[]` |

// ---

// Bạn có muốn mình giải thích luôn **cách cài thuật toán bằng Python hoặc
// Java** để bạn hiểu logic sâu hơn không?
// Rất hay! 👏
// Đoạn code bạn đưa là **một lời giải nâng cao và cực kỳ tối ưu** cho bài
// **LeetCode 1488 – Avoid Flood in The City**, sử dụng **Disjoint Set Union
// (DSU)** hay còn gọi là **Union-Find**, để tìm **ngày khô hợp lệ gần nhất**
// một cách nhanh chóng.

// Bây giờ mình sẽ **giải thích chi tiết từng phần**, kèm **tư duy thuật toán**
// phía sau.

// ---

// ## 🧩 **Mục tiêu của bài**

// Ta có mảng `rains[]`:

// * `rains[i] > 0` → ngày i **mưa vào hồ** `rains[i]`
// * `rains[i] == 0` → ngày i **khô**, ta có thể **xả nước một hồ** tùy chọn

// Ta phải sắp xếp việc **xả nước hợp lý**, để **không có hồ nào bị mưa hai lần
// mà chưa kịp xả**.

// ---

// ## ⚙️ **Ý tưởng chính của thuật toán**

// Khi mưa vào hồ `lake`:

// * Nếu hồ `lake` đã **đầy trước đó (full)**, ta **phải tìm một ngày khô sau
// ngày đó nhưng trước ngày hiện tại** để **xả hồ này**.
// * Nếu **không tìm được ngày khô nào** trong khoảng đó → flood ❌.

// ---

// ## 💡 **Cách tiếp cận (DSU / Union-Find)**

// Bình thường, người ta dùng `TreeSet` để tìm "ngày khô gần nhất".
// Ở đây, dùng **Union-Find** (hay còn gọi là **Disjoint Set**) để tăng tốc:
// → tìm nhanh **ngày khô tiếp theo chưa được dùng**.

// ---

// ## 🧠 **Giải thích từng phần code**

// ### 1️⃣ Khởi tạo mảng `fa[]` (Union-Find)

// ```java
// int[] fa = new int[n + 1];
// for (int i = 0; i <= n; i++) {
// fa[i] = i;
// }
// ```

// * `fa[i]` là **cha (parent)** của node i.
// * Nếu `fa[i] == i` nghĩa là ngày `i` **chưa bị dùng để xả nước**.
// * Sau khi dùng ngày `i` (đã xả nước), ta **hợp nhất nó với ngày i+1**:

// ```java
// fa[i] = find(i + 1, fa);
// ```

// → Điều này nghĩa là: “ngày i đã được dùng rồi, hãy nhảy đến ngày kế tiếp còn
// trống.”

// ---

// ### 2️⃣ Kết quả và bảng lưu

// ```java
// int[] ans = new int[n];
// Map<Integer, Integer> fullDay = new HashMap<>(); // lake -> ngày nó đầy nước
// ```

// * `ans[i]` là kết quả (ngày i làm gì)

// * `-1` → ngày mưa
// * `x` → ngày khô, xả hồ `x`
// * `1` → nếu ngày khô chưa dùng (tạm)
// * `fullDay` ghi nhớ hồ nào đã đầy, và ngày nó bị mưa lần trước.

// ---

// ### 3️⃣ Vòng lặp chính

// ```java
// for (int i = 0; i < n; i++) {
// int lake = rains[i];
// if (lake == 0) {
// ans[i] = 1;
// continue;
// }
// ```

// * Nếu là ngày khô → tạm điền `1`, sẽ thay bằng hồ thật sau.
// * Nếu là ngày mưa (`lake > 0`) → xử lý logic tiếp theo.

// ---

// ### 4️⃣ Khi gặp hồ **đã từng đầy**

// ```java
// Integer j = fullDay.get(lake);
// if (j != null) {
// int dryDay = find(j + 1, fa);
// if (dryDay >= i) {
// return new int[]{}; // không tìm được ngày khô hợp lệ
// }
// ans[dryDay] = lake;
// fa[dryDay] = find(dryDay + 1, fa); // đánh dấu dryDay đã dùng
// }
// ```

// 🧩 Diễn giải:

// * `j` = ngày **trước đó** hồ `lake` bị mưa (đầy).
// * Ta cần **tìm một ngày khô sau j nhưng trước i** để xả hồ này.

// → Dùng `find(j + 1, fa)` để **tìm ngày khô đầu tiên ≥ j+1 mà chưa bị dùng**.

// * Nếu `dryDay >= i` → tức không có ngày khô nào giữa hai lần mưa → flood ❌.
// * Ngược lại:

// * `ans[dryDay] = lake` → tại ngày khô đó, ta xả hồ này.
// * `fa[dryDay] = find(dryDay + 1, fa)` → đánh dấu ngày khô này **đã dùng
// rồi**, nhảy tới ngày khô kế tiếp.

// ---

// ### 5️⃣ Cập nhật sau khi mưa

// ```java
// ans[i] = -1; // mưa ngày i
// fa[i] = i + 1; // ngày i bị "xóa" (đã dùng)
// fullDay.put(lake, i); // lưu hồ đã đầy ở ngày i
// ```

// ---

// ### 6️⃣ **Hàm `find()`**

// ```java
// private int find(int x, int[] fa) {
// if (fa[x] != x) {
// fa[x] = find(fa[x], fa); // nén đường (path compression)
// }
// return fa[x];
// }
// ```

// Đây là thuật toán **DSU chuẩn**:

// * Nếu `fa[x] != x`, thì truy ngược cha đến khi gặp gốc.
// * Áp dụng **path compression** để tăng tốc độ truy vấn.

// ---

// ## 📊 Ví dụ minh họa

// ### Input:

// ```
// rains = [1, 2, 0, 1, 2]
// ```

// ### Diễn giải từng bước:

// | i | rains[i] | fullDay | fa | Hành động | ans |
// | - | -------- | --------- | ----- |
// ------------------------------------------------------------- | ------------
// |
// | 0 | 1 | {1→0} | [0→1] | mưa hồ 1 | [-1] |
// | 1 | 2 | {1→0,2→1} | [1→2] | mưa hồ 2 | [-1,-1] |
// | 2 | 0 | {1→0,2→1} | [2→2] | ngày khô (tạm 1) | [-1,-1,1] |
// | 3 | 1 | {1→0,2→1} | | hồ 1 đã đầy → tìm dryDay sau 0: dryDay=2 → xả hồ 1
// tại ngày 2 | [-1,-1,1,-1] |
// | 4 | 2 | {1→3,2→1} | | hồ 2 đã đầy → không còn ngày khô sau 1 → flood ❌ | |

// → Không tránh được flood → trả về `[]`.

// ---

// ## ⚡ **Độ phức tạp**

// | Loại | Độ phức tạp |
// | --------- | ------------------------------------- |
// | Thời gian | **O(n α(n))** ≈ O(n) (gần tuyến tính) |
// | Bộ nhớ | O(n) |

// > α(n): hàm nghịch đảo Ackermann, cực nhỏ (≈1)

// ---

// ## ✅ **Tóm tắt logic**

// | Bước | Mô tả |
// | ---- | ----------------------------------------------------- |
// | 1 | Duyệt từng ngày |
// | 2 | Nếu mưa hồ `x`: kiểm tra hồ đó đã đầy chưa |
// | 3 | Nếu đã đầy → tìm ngày khô hợp lệ gần nhất (DSU) để xả |
// | 4 | Nếu không có ngày khô → flood |
// | 5 | Nếu trời khô → ghi tạm 1 |
// | 6 | Cập nhật Union-Find sau mỗi thao tác |

// ---

// Nếu bạn muốn, mình có thể **vẽ sơ đồ luồng dữ liệu của DSU** (fa[]) để thấy
// rõ cách nó “nhảy” qua các ngày đã dùng — giúp bạn hiểu cơ chế “tìm ngày khô
// tiếp theo” nhanh như thế nào.
// Bạn muốn mình vẽ không?
