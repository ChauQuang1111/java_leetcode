
// 3484. Design Spreadsheet(19/09/2025)
import java.util.*;

public class b41 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập số hàng (rows), ở đây chưa dùng nhưng vẫn đọc
        // int rows = sc.nextInt();

        // Menu test: 1=setCell, 2=resetCell, 3=getValue, 0=thoát
        while (true) {
            int cmd = sc.nextInt();
            if (cmd == 0)
                break;

            if (cmd == 1) { // setCell
                String cell = sc.next();
                int val = sc.nextInt();
                setCell(cell, val);
                System.out.println("Set " + cell + " = " + val);
            } else if (cmd == 2) { // resetCell
                String cell = sc.next();
                resetCell(cell);
                System.out.println("Reset " + cell);
            } else if (cmd == 3) { // getValue
                String formula = sc.next();
                int result = getValue(formula);
                System.out.println("Value: " + result);
            }
        }

        sc.close();
    }

    // Lưu trữ dữ liệu ô: key = "A1", value = số nguyên
    static Map<String, Integer> map = new HashMap<>();

    // ------------------ Các hàm xử lý ------------------

    // Gán giá trị cho 1 ô
    public static void setCell(String cell, int value) {
        map.put(cell, value);
    }

    // Reset 1 ô (xóa khỏi map)
    public static void resetCell(String cell) {
        map.remove(cell);
    }

    // Lấy giá trị từ công thức =X+Y (X,Y có thể là số hoặc ô)
    public static int getValue(String formula) {
        // Tìm vị trí dấu '+'
        int io = formula.indexOf('+');

        // Cắt chuỗi: bỏ dấu '=' ở đầu
        String cell1 = formula.substring(1, io); // vế trái
        String cell2 = formula.substring(io + 1); // vế phải

        // Xử lý vế trái
        int val1;
        if (cell1.charAt(0) > '9') { // Nếu bắt đầu là chữ cái → ô
            val1 = map.getOrDefault(cell1, 0);
        } else { // Nếu là số
            val1 = Integer.parseInt(cell1);
        }

        // Xử lý vế phải
        int val2;
        if (cell2.charAt(0) > '9') { // Nếu là ô
            val2 = map.getOrDefault(cell2, 0);
        } else { // Nếu là số
            val2 = Integer.parseInt(cell2);
        }

        // Trả về tổng
        return val1 + val2;
    }
}

// Bạn đang
// hỏi về**LeetCode 3484.

// Design Spreadsheet (19/09/2025)** phải không?
// Mình sẽ giải thích yêu cầu đề bài cho bạn nhé.

// ---

// ### **Đề bài (Design Spreadsheet)**

// Bạn cần thiết kế một lớp **Spreadsheet** để mô phỏng hoạt động của một bảng
// tính (giống Excel/Google Sheets).

// Spreadsheet có

// các ô (cell) được đánh số theo **hàng** và **cột**.
// Mỗi ô có thể chứa:

// 1. Một giá trị số

// nguyên (được gán trực tiếp).
// 2. Một công thức là **tổng các ô khác** (khi đó giá trị phụ thuộc vào giá trị
// của những ô này).

// ---

// ### **Các hàm cần cài đặt**

// 1. **Constructor**

// ```java

// Spreadsheet(int rows, int cols)
// ```

// * Tạo một bảng tính có kích thước `rows x cols`.
// * Ban đầu tất cả ô đều chứa giá trị **0**.

// ---

// 2. **setCell(int row, int col, int value)**

// * Đặt **giá trị trực tiếp** cho ô `(row, col)`.
// * Nếu trước đó ô chứa công thức → xóa công thức, thay bằng giá trị mới.

// ---

// 3. **resetCell(int row, int col)**

// * Reset ô `(row, col)` về **0**.
// * Loại bỏ

// công thức (nếu có).

// ---

// 4. **sumCell(int row, int col, List<String> formulas) → int**

// * Gán cho ô `(row, col)` một **công thức tổng**, được định nghĩa bởi
// `formulas`.
// * Mỗi phần tử trong `formulas` là:

// * Một **ô duy nhất**: `"A1"`, `"B2"`, …
// * Một **dải ô (range)**: `"A1:B2"` → nghĩa là tất cả các ô từ A1 đến B2.
// * Sau khi gán công thức, ô `(row, col)` lưu công thức này.
// * Hàm trả về **giá trị tính toán hiện tại** của ô.

// ---

// 5. **getValue(int row, int col) → int**

// * Trả về giá trị của ô `(row, col)`.
// * Nếu ô chứa công thức → tính toán dựa trên công thức.

// ---

// ### **Ví dụ minh họa**

// ```text
// Spreadsheet sheet = new Spreadsheet(3, 3);

// // Đặt giá trị trực tiếp
// sheet.setCell(1, 1, 5); // ô A1 = 5
// sheet.setCell(2, 1, 3); // ô A2 = 3

// // Công thức tổng
// sheet.sumCell(3, 1, ["A1", "A2"]); // ô A3 = A1 + A2 = 8

// // Lấy giá trị
// sheet.getValue(3, 1); // = 8

// // Reset ô
// sheet.resetCell(2, 1); // A2 = 0
// sheet.getValue(3, 1); // = 5 (vì A3 = A1 + A2 = 5 + 0)
// ```

// ---

// 👉 Tóm lại: Đề yêu cầu bạn **thiết kế một bảng tính hỗ trợ giá trị trực tiếp
// + công thức cộng**, có thể **set, reset, sum, get value**.
// Nó giống như một phiên bản **mini Excel engine**.

// ---

// Bạn có muốn mình viết luôn **phác thảo code class `Spreadsheet` bằng Java**
// cho dễ hình dung không?

// Ok👍mình giải thích chi tiết từng phần trong code`Spreadsheet`mà bạn viết:

// ---

// ###1. Khai báo và khởi tạo

// ```java
// class Spreadsheet {
// public Spreadsheet(int rows) {
// }

// Map<String, Integer> map = new HashMap<>();```

// *`rows`
// chưa dùng, nhưng
// ý nghĩa
// là số
// hàng trong bảng.*`map`là**
// bản đồ
// lưu giá
// trị từng ô**.

// *key=

// tên ô (ví dụ `"A1"`, `"B2"`)
// * value = số nguyên trong ô.

// ---

// ### 2. Gán và reset ô

// ```java

// public void setCell(String cell, int value) {
// map.put(cell, value);
// }

// public void resetCell(String cell) {
// map.remove(cell);
// }```

// *`setCell("A1", 10)` → map = `{A1=10}`
// * `resetCell("A1")` → xóa A1 khỏi map → map rỗng.

// ---

// ### 3. Tính giá trị công thức

// ```java

// public int getValue(String formula) {
// int io = formula.indexOf('+');
// String cell1 = formula.substring(1, io);
// String cell2 = formula.substring(io + 1);
// ```

// * `formula` dạng `"=A1+6"` hoặc `"=5+7"`.
// * Bỏ qua ký tự `=` đầu tiên.
// * Tìm dấu `+`.
// * Chia công thức thành **2 phần**:

// * `cell1` =

// vế trái (sau `=` đến trước `+`)
// * `cell2` =

// vế phải (sau `+`).

// Ví dụ:

// * `"=5+7"` → `cell1="5"`, `cell2="7"`
// * `"=A1+6"` → `cell1="A1"`, `cell2="6"`

// ---

// ### 4. Xử lý

// vế trái (`cell1`)

// ```java
// int val1;
// if (cell1.charAt(0) > '9') {
// val1 = map.getOrDefault(cell1, 0);
// } else {
// val1 = Integer.parseInt(cell1);
// }
// ```

// * Nếu ký tự đầu tiên của `cell1` **không phải số** (`'A' > '9'`) → coi là tên
// ô, lấy từ `map`.
// * Ngược lại → coi là số nguyên, parse ra.

// Ví dụ:

// * `"A1"` → lấy `map.get("A1")`, mặc định = 0 nếu chưa có.
// * `"5"` → `Integer.parseInt("5") = 5`.

// ---

// ### 5. Xử lý

// vế phải (`cell2`) (tương tự)

// ```java
// int val2;
// if (cell2.charAt(0) > '9') {
// val2 = map.getOrDefault(cell2, 0);
// } else {
// val2 = Integer.parseInt(cell2);
// }
// ```

// ---

// ### 6. Trả kết quả

// ```java
// return val1 + val2;
// ```

// * Trả về tổng của

// hai vế (có thể là số hoặc giá trị ô).

// ---

// ### 🔑 Ý tưởng thuật toán

// 1. Công thức **chỉ hỗ trợ dạng `=X+Y`** (X, Y có thể là số hoặc tên ô).
// 2. Cắt chuỗi thành 2 phần quanh dấu `+`.
// 3. Nếu phần là số → parse số, nếu phần là ô → lấy từ `map`.
// 4. Trả về tổng 2 phần.

// ---

// ### Ví dụ minh họa

// ```java
// Spreadsheet sheet = new Spreadsheet(3);

// System.out.println(sheet.getValue("=5+7")); // 12

// sheet.setCell("A1", 10);
// System.out.println(sheet.getValue("=A1+6")); // 16

// sheet.setCell("B2", 15);
// System.out.println(sheet.getValue("=A1+B2")); // 25

// sheet.resetCell("A1");
// System.out.println(sheet.getValue("=A1+B2")); // 15 (A1=0)
// ```

// ---

// 👉 Tóm gọn:

// * Dùng `map` lưu giá trị ô.
// * Khi gặp công thức `=X+Y`, tách làm hai, xác định số hay ô, lấy giá trị, rồi
// cộng lại.

// ---

// Bạn có muốn mình viết thêm **pseudo-code ngắn gọn** để dễ nhớ không?

// OK — mình sẽ giải **ngắn gọn, rõ ràng** về bài *Design Spreadsheet* (kiểu bài
// “thiết kế bảng tính” có `set/get/sum`/công thức), nêu rõ **đề bài thường
// gặp**, **đầu vào / yêu cầu**, **cách lưu dữ liệu**, **thuật toán tính toán
// công thức** và **những lưu ý/edge cases**. Bạn có thể dùng phần này làm
// checklist khi cài đặt.

// ---

// ## 🔎 Mô tả

// đề bài (phiên bản phổ biến)

// Thiết kế một bảng tính (spreadsheet) hỗ trợ các thao tác chính:

// * `set(r, c, val)` — gán ô ở hàng `r`, cột `c` = giá trị nguyên `val`. (Ở một
// số đề: hàng/cột bắt đầu từ 1; cột được kí hiệu A, B, C…)
// * `get(r, c)` — trả về **giá trị hiện thời** của ô `(r,c)`. Nếu ô chứa công
// thức thì trả giá trị đã tính.
// * `sum(r, c, refs)` — đặt ô `(r,c)` bằng **tổng** các tham chiếu `refs`, nơi
// mỗi tham chiếu là:

// * một ô đơn `A1`, hoặc
// * một range `A1:B2` (tức tất cả ô trong vùng đó),
// * or một list kết

// hợp (ví dụ `["A1","A1:B2","C3"]`).
// * Sau gọi `sum`, ô `(r,c)` chứa **công thức** thay vì một số cố định — và giá
// trị của nó là tổng các ô tham chiếu (động: nếu ô tham chiếu thay đổi thì giá
// trị phải cập nhật).
// * Khi `set` một ô mà ô đó trước đó có công thức thì công thức bị xóa (trở
// thành giá trị cố định).
// * Trả về kết quả cho mỗi `get`.

// Mục tiêu: thiết kế cấu trúc dữ liệu + thuật toán để thực hiện các thao tác
// đúng (và hiệu quả).

// ---

// ## 🧠 Ý tưởng chính / yêu cầu kỹ thuật

// * **Phải hỗ trợ công thức phụ thuộc (dependencies)**: khi ô A tham chiếu đến
// B và C, và B thay đổi → A phải được cập nhật.
// * **Lưu công thức** thay vì tính cứng, để khi ô nguồn thay đổi ta có thể tái
// tính.
// * **Tránh tính thừa** bằng memoization / tái tính có kiểm soát (DFS + cache)
// hoặc duy trì đồ thị phụ thuộc (topo order / incremental updates).
// * Giả sử **không có vòng phụ

// thuộc (no cycles)** — đa số đề cho vậy; nếu có cycles cần xử lý tách phát
// hiện vòng.

// ---

// ## 🧾 Cấu trúc dữ liệu đề xuất

// 1. `value[r][c]` — lưu **giá trị hiện tại** (int)

// của ô (nếu ô có formula thì vẫn lưu giá trị sau khi tính).
// 2. `formula[r][c]` — lưu công thức của ô nếu

// có (ví dụ danh sách các tham chiếu gốc, hoặc map cell→count).
// 3. `dependents` (graph): cho mỗi ô `x`, lưu danh sách ô `y` phụ thuộc vào `x`
// (x → y). Dùng để cập nhật khi `x` thay đổi.
// 4. (Tuỳ chọn) `refsMap[r][c]` — cho ô có công thức, lưu map {cell -> times}
// nghĩa là ô được tham chiếu bao nhiêu lần (để dễ tính tổng mà không phải parse
// range nhiều lần).

// ---

// ## 🔁 Hai cách cập nhật khi ô thay đổi

// ### A. Tính on-demand (DFS + memo)

// * Khi `get(r,c)` hoặc khi cần trả giá trị sau `sum` gọi, ta **tính đệ quy**
// dựa vào `formula`:

// * Nếu ô có giá

// trị tĩnh (no formula) → trả `value`.
// * Nếu có formula → tính tổng `sum( compute(cell) * count )` cho từng tham
// chiếu.
// * Dùng memo trong lần tính để không tính lại cùng ô nhiều lần.
// * Khi `set` một ô → cập nhật `value`, xóa `formula` nếu có; nhưng để `get`
// đúng cho ô phụ thuộc, có thể cần xóa cache hoặc recompute khi truy vấn tiếp.
// * Ưu: đơn giản; nhược: nếu nhiều `get`/`sum` lặp lại và bảng lớn có thể chậm.

// ###

// B. Incremental update (propagate)

// * Khi `set(r,c,val)` hoặc khi gán công thức mới:

// *

// Tính delta (sự thay đổi của ô `x`): `delta = newValue - oldValue`.
// * Duyệt BFS/DFS trên `dependents` để cộng `delta` vào từng ô phụ

// thuộc (vì A = sum(...) sẽ tăng giảm theo).
// * Để làm được, khi tạo công thức cho một ô ta phải:

// * Tính trước giá trị của công thức dựa trên giá trị hiện có.
// * Với mỗi ô nguồn `s` trong công thức, thêm `target` vào `dependents[s]`.
// * Khi `set` giá trị, xóa các liên

// kết cũ (nếu ô trước đó có công thức) → cập nhật `dependents` tương ứng.
// * Ưu: `get` O(1) (chỉ đọc `value`); nhược: phức tạp hơn khi xử lý nhiều thay
// đổi, phải quản lý dependents chính xác.

// ---

// ## 🔧 Parsing tham chiếu (refs)

// * 2 loại ref:

// * `A1` — cột ký tự(s) + hàng số. Cần hàm chuyển cột chữ (`"A"->0, "Z"->25,
// "AA"->26, ...`) và hàng -1 nếu index base 1.
// * `A1:B2` — vùng: parse 2 ô, lặp rows & cols trong phạm vi để thêm tham
// chiếu.
// * Khi tạo `refsMap`, nếu `A1:B2` chứa nhiều ô thì mỗi ô

// tăng count (n lần nếu range lặp).

// ---

// ## 🔁 Các thao tác cần

// cài đặt (tóm tắt)

// * `set(r, c, val)`:

// * Nếu trước đó `(r,c)` có formula: remove các liên kết dependents từ những ô
// nguồn → (để tránh cập nhật sai).
// * Gán `value[r][c] = val`, `formula[r][c] = null`.
// * Nếu dùng incremental: tính `delta = val - oldValue` rồi propagate đến
// dependents.
// * `sum(r, c, refs)`:

// * Parse `refs` thành `refsMap` (cell→count).
// * Nếu trước đó ô có formula: remove liên kết cũ.
// * For each source cell `s` in refsMap: add `(r,c)` vào `dependents[s]`.
// * Tính `value[r][c] = sum( value[s] * count )`.
// * Lưu `formula[r][c] = refsMap`.
// * (Nếu incremental, sau thay đổi refs cần propagate delta đến dependents).
// * `get(r, c)`:

// * Nếu incremental → trả `value[r][c]`.
// * Nếu on-demand → nếu formula: compute recursively; else trả `value`.

// ---

// ## 🔍 Ví dụ

// minh họa (incremental)

// * wordlist style:

// sử dụng (r,c) numeric; giả sử A1 → (0,0)

// 1. `set(0,0,5)` → value\[A1]=5
// 2. `set(0,1,3)` → value\[B1]=3
// 3. `sum(0,2, ["A1","B1"])` → C1 = A1 + B1 = 8; đồng thời thêm dependents:
// A1→C1, B1→C1
// 4. `set(0,0,7)` → A1 tăng 2 → propagate: C1 += 2 → C1 = 10

// ---

// ## ⚠️ Edge cases & lưu ý

// * **Indexing**: input có thể dùng 1-based index; phải chuẩn hóa.
// * **Cột nhiều chữ**: `"AA"`, `"AB"` cần chuyển đổi base-26.
// * **Vòng lặp (cyclic)**: nếu problem cho phép, cần phát hiện và xử lý; thường
// đề sẽ loại trừ.
// * **Nhiều tham chiếu lặp**: `sum(A1,A1,A1)` (đếm nhiều lần) — phải xử lý cách
// lưu count.
// * **Hiệu năng**: nếu bảng lớn và nhiều updates, incremental update là tốt
// hơn; nếu ít update/get thì on-demand đơn giản hơn.
// * **Chuỗi/ràng buộc**: Kiểm soát memory cho maps.

// ---

// ## ⏱️ Độ phức

// tạp (ước lượng)

// * Parsing refs of a sum: `O(k + totalCellsInRanges)` (k = số refs).
// * Incremental `set`:

// * worst-case: propagate tới tất cả dependents (có thể O(N) with N cells) —
// nhưng amortized thường nhỏ nếu DAG không quá lớn.
// * `get`:

// * incremental: O(1)
// * on-demand: O(size of dependency subtree) (với memo O(subtree) mỗi lần)

// ---

// ## ✅ Tóm tắt

// dễ nhớ (flash)

// * Lưu `value`, `formula` (map cell→count), `dependents`.
// * `set` = gán giá trị, xóa formula cũ, propagate delta.
// * `sum` = parse refs → lưu formula, register dependents cho từng source →
// compute value.
// * `get` = đọc `value` (hoặc compute nếu on-demand).
// * Xử lý ref range & column-string → index conversion.
// * Tránh vòng lặp; nếu có, cần phát hiện.

// ---

// Nếu bạn muốn, mình có thể:

// * Viết 1 **cài đặt ví dụ bằng Java** (incremental hoặc on-demand) với đầy đủ
// hàm `set/get/sum` + parser `A1` ↔ indices; hoặc
// * Viết **bản Python** tương đương; hoặc
// * Viết **flowchart + pseudo-code Java** ngắn gọn cho implementation
// incremental.

// Bạn muốn mình

// xuất code (Java/Python) hay pseudo-code tiếp theo?
