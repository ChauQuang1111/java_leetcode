
// // Delete Nodes From Linked List Present (01/11/2025)
import java.util.Scanner;

class ListNode {
    int val; // Giá trị của node
    ListNode next; // Con trỏ trỏ đến node kế tiếp

    ListNode() {
    } // Constructor rỗng (dùng cho node tạm)

    ListNode(int val) { // Constructor với giá trị
        this.val = val;
    }

    ListNode(int val, ListNode next) { // Constructor với giá trị và node kế tiếp
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        if (head == null)
            return null; // Nếu danh sách rỗng thì trả về null

        // --- Bước 1: Tìm giá trị lớn nhất trong mảng nums ---
        int max = -1;
        for (int num : nums) {
            if (num > max)
                max = num;
        }

        // --- Bước 2: Tạo mảng boolean để đánh dấu các giá trị cần xóa ---
        boolean[] freq = new boolean[max + 1];
        for (int num : nums) {
            freq[num] = true; // Đánh dấu giá trị này cần bị loại bỏ
        }

        // --- Bước 3: Tạo node giả (dummy) để dễ thao tác ---
        ListNode temp = new ListNode(); // Node giả ở đầu danh sách mới
        ListNode current = temp; // Con trỏ giúp xây danh sách mới

        // --- Bước 4: Duyệt danh sách gốc ---
        while (head != null) {
            // Nếu giá trị của node hiện tại KHÔNG nằm trong nums
            if (head.val >= freq.length || !freq[head.val]) {
                current.next = head; // Nối node này vào danh sách kết quả
                current = current.next; // Di chuyển con trỏ
            }
            head = head.next; // Sang node tiếp theo
        }

        // --- Bước 5: Cắt đuôi danh sách để tránh trỏ tới node cũ ---
        current.next = null;

        // --- Bước 6: Trả về danh sách mới (bỏ qua node giả đầu tiên) ---
        return temp.next;
    }
}

public class b85 {
    // Hàm tạo danh sách liên kết từ mảng
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0)
            return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Hàm in danh sách liên kết
    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Nhập danh sách liên kết ---
        System.out.print("Nhập số lượng phần tử trong danh sách liên kết: ");
        int n = sc.nextInt();
        int[] listArr = new int[n];
        System.out.println("Nhập các giá trị của danh sách:");
        for (int i = 0; i < n; i++) {
            listArr[i] = sc.nextInt();
        }

        // --- Nhập mảng nums (các giá trị cần xóa) ---
        System.out.print("Nhập số lượng phần tử trong mảng nums: ");
        int m = sc.nextInt();
        int[] nums = new int[m];
        System.out.println("Nhập các giá trị cần xóa:");
        for (int i = 0; i < m; i++) {
            nums[i] = sc.nextInt();
        }

        // --- Tạo danh sách liên kết ---
        ListNode head = createLinkedList(listArr);

        // --- Gọi hàm xử lý ---
        Solution sol = new Solution();
        ListNode newHead = sol.modifiedList(nums, head);

        // --- In kết quả ---
        System.out.println("Danh sách sau khi loại bỏ:");
        printLinkedList(newHead);

        sc.close();
    }
}

// // ```python
// // # Định nghĩa lớp ListNode cho danh sách liên kết đơn
// class ListNode:
// def __init__(self, val=0, next=None):
// self.val = val
// self.next = next

// class Solution:
// def modifiedList(self, nums: list[int], head: Optional[ListNode]) ->
// Optional[ListNode]:
// if not head:
// return None # Nếu danh sách rỗng

// sety = set(nums)

// # Bỏ qua các node đầu tiên có giá trị nằm trong sety
// while head and head.val in sety:
// head = head.next

// # Nếu toàn bộ danh sách bị loại
// if not head:
// return None

// # Duyệt phần còn lại
// prev = head
// curr = head.next

// while curr:
// if curr.val in sety:
// prev.next = curr.next # Bỏ qua node có giá trị trong sety
// else:
// prev = curr # Giữ lại node này
// curr = curr.next # Di chuyển tiếp

// return head

// // **Giải thích thuật toán:**

// // 1. **Tạo tập hợp (`sety`)** từ `nums` để có thể kiểm tra nhanh xem một giá
// trị có cần bị xóa không (`O(1)` thời gian truy cập).
// // 2. **Bỏ qua các node đầu tiên** nếu giá trị của chúng nằm trong `nums`.
// // 3. Sau khi đã đến được node đầu tiên không bị xóa, ta bắt đầu **duyệt danh
// sách**:

// // * Nếu `head.val` **không** nằm trong `sety`, giữ lại node đó.
// // * Nếu `head.val` **nằm trong** `sety`, bỏ qua node đó.
// // 4. **Xử lý node cuối cùng**, đảm bảo rằng nếu nó cần bị xóa thì ngắt liên
// kết.
// // 5. **Trả về node đầu tiên hợp lệ (`temp`)**, là đầu của danh sách mới.

// // ⏱ **Độ phức tạp thời gian:** O(n)
// // 💾 **Độ phức tạp bộ nhớ:** O(k), với k là số phần tử trong `nums` (do dùng
// `set`).

// // ---

// // ## 🧩 **Đề bài gốc (LeetCode 3217: Delete Nodes From Linked List Present
// in Array)**

// // Bạn được cho:

// // 1. Một **mảng số nguyên `nums`**.
// // 2. Một **danh sách liên kết đơn `head`** (linked list) — mỗi node trong
// danh sách chứa một giá trị nguyên.

// // ---

// // ### 🎯 **Yêu cầu**

// // Hãy **xóa tất cả các node** trong linked list **có giá trị xuất hiện trong
// mảng `nums`**.

// // Sau khi xóa xong, **trả về con trỏ đến đầu danh sách mới (head mới)**.

// // ---

// // ### 🧠 **Ví dụ minh họa**

// // #### 📥 Input:

// // ```
// // nums = [1, 2, 3]
// // linked list: 1 -> 2 -> 3 -> 4 -> 5
// // ```

// // #### ⚙️ Quá trình xử lý:

// // * Các số cần xóa là: `1, 2, 3`
// // * Duyệt qua danh sách:

// // * Node 1 (giá trị 1) → bị xóa (vì có trong nums)
// // * Node 2 → bị xóa
// // * Node 3 → bị xóa
// // * Node 4 → giữ lại
// // * Node 5 → giữ lại

// // #### 📤 Output:

// // ```
// // 4 -> 5
// // ```

// // ---

// // ### 💡 **Ý tưởng thuật toán**

// // 1. Dùng một **HashSet** (tập hợp) để lưu tất cả các giá trị trong `nums` →
// giúp kiểm tra nhanh xem một giá trị có nằm trong `nums` không (O(1)).
// // 2. Duyệt qua danh sách liên kết:

// // * Nếu giá trị của node hiện tại **có trong HashSet** → bỏ qua node đó
// (xóa).
// // * Nếu không → giữ lại node.
// // 3. Trả về con trỏ `head` mới của danh sách sau khi đã xóa.

// // ---

// // ### ⚙️ **Ví dụ 2**

// // #### Input:

// // ```
// // nums = [2, 5, 8]
// // head = 2 -> 8 -> 3 -> 5 -> 10 -> 2
// // ```

// // #### Quá trình:

// // * 2: xóa (vì có trong nums)
// // * 8: xóa
// // * 3: giữ lại
// // * 5: xóa
// // * 10: giữ lại
// // * 2: xóa

// // #### Output:

// // ```
// // 3 -> 10
// // ```

// // ---

// // ### 🧮 **Độ phức tạp**

// // * **Thời gian:** O(n + m)
// // (với n = số phần tử trong linked list, m = độ dài nums)
// // * **Không gian:** O(m) cho HashSet.

// // ---

// // 👉 **Tóm lại:**

// // > Bài này yêu cầu xóa các node trong danh sách liên kết nếu giá trị của
// chúng nằm trong mảng `nums`.
// // > Cách nhanh nhất là dùng `HashSet` để lưu `nums`, rồi duyệt qua danh sách
// và bỏ qua những node có giá trị trùng.

// // ---

// // Bạn có muốn mình viết **code Java** (hoặc Python) có chú thích rõ từng
// bước cho bài này không?
// Rất tốt 👏
// Đoạn code bạn đưa ra là **một cách khác** để giải quyết bài toán “xóa các
// node có giá trị nằm trong mảng `nums` khỏi danh sách liên kết”.
// Mình sẽ **giải thích chi tiết thuật toán từng bước**, để bạn hiểu rõ cách
// hoạt động nhé.

// ---

// ## 🔍 Mục tiêu của bài toán

// Cho:

// * Một danh sách liên kết `head`.
// * Một mảng `nums` chứa các giá trị cần **xóa** khỏi danh sách.

// Yêu cầu:
// Trả về danh sách liên kết mới **sau khi loại bỏ** tất cả các node có giá trị
// nằm trong `nums`.

// ---

// ## ⚙️ Ý tưởng chính

// Thay vì dùng `HashSet` (như cách phổ biến), đoạn code này dùng **mảng boolean
// `freq`** để đánh dấu giá trị nào trong `nums` cần bị loại bỏ.

// ---

// ## 🧠 Giải thích từng phần code

// ```java
// int max = -1;
// for(int num : nums ){
// max = num > max ? num : max;
// }
// ```

// 👉 **Tìm giá trị lớn nhất trong `nums`**, vì ta cần biết **kích thước tối
// đa** của mảng đánh dấu `freq`.
// Nếu `nums = [2, 4, 5]` → `max = 5`.

// ---

// ```java
// boolean[] freq = new boolean[max + 1];
// ```

// 👉 Tạo mảng boolean có độ dài `max + 1`

// * Mỗi chỉ số `i` đại diện cho giá trị `i`.
// * `freq[i] = true` nghĩa là giá trị `i` nằm trong `nums` → cần bị xóa.

// ---

// ```java
// for(int num : nums) freq[num] = true;
// ```

// 👉 Đánh dấu tất cả các giá trị trong `nums` là `true`.

// Ví dụ:
// `nums = [2, 4, 5]`
// → `freq[2] = true, freq[4] = true, freq[5] = true`.

// ---

// ```java
// ListNode temp = new ListNode();
// ListNode current = temp;
// ```

// 👉 Tạo **node giả (dummy node)** `temp` để dễ dàng xây dựng danh sách mới.

// * `current` là con trỏ giúp ta nối các node hợp lệ vào danh sách kết quả.

// ---

// ```java
// while(head != null){
// if(head.val >= freq.length || freq[head.val] == false){
// current.next = head;
// current = current.next;
// }
// head = head.next;
// }
// ```

// 👉 Duyệt qua danh sách ban đầu:

// * Nếu `head.val` **nằm ngoài phạm vi `freq`** hoặc **chưa bị đánh dấu
// (`freq[val] == false`)**,
// → giữ lại node này, nối vào danh sách mới.
// * Ngược lại, nếu `freq[val] == true` → **bỏ qua node này**.

// 💡 Ví dụ:

// ```
// Danh sách ban đầu: 1 -> 2 -> 3 -> 4 -> 5
// nums = [2, 4]
// freq = [false, false, true, false, true]

// Khi duyệt:
// - 1 → giữ lại
// - 2 → bỏ qua (freq[2] = true)
// - 3 → giữ lại
// - 4 → bỏ qua
// - 5 → giữ lại
// ```

// ---

// ```java
// current.next = null;
// return temp.next;
// ```

// 👉 Cắt đuôi danh sách (tránh trỏ tới node cũ bị loại).
// → Trả về danh sách mới, **bắt đầu từ `temp.next`** (vì `temp` là node giả).

// ---

// ## ✅ Độ phức tạp

// * **Thời gian:** `O(n + m)`

// * `n` là số node trong danh sách.
// * `m` là số phần tử trong `nums`.
// * **Bộ nhớ:** `O(max(nums))` do dùng mảng boolean.

// ---

// ## ⚠️ Lưu ý

// * Cách này **chỉ hiệu quả khi giá trị node là số nguyên nhỏ** (vì tạo mảng
// theo giá trị lớn nhất).
// Nếu `nums` chứa giá trị lớn (VD: 1_000_000_000), thì nên dùng `HashSet` thay
// vì mảng boolean.

// ---

// ## 🧩 Tóm tắt thuật toán

// | Bước | Mô tả |
// | ---- | ------------------------------------------------------------- |
// | 1 | Tìm giá trị lớn nhất trong `nums` |
// | 2 | Tạo mảng boolean `freq` có kích thước `max + 1` |
// | 3 | Đánh dấu các giá trị cần xóa trong `freq` |
// | 4 | Duyệt danh sách, chỉ giữ lại các node có `freq[val] == false` |
// | 5 | Trả về danh sách kết quả mới |

// ---

// Bạn có muốn mình giúp **viết lại phiên bản dùng `HashSet` thay cho mảng
// boolean**, để xử lý cả trường hợp giá trị `val` rất lớn không (an toàn hơn
// trong thực tế)?
