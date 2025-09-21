// 1912. Design Movie Rental System
import java.util.*;
import java.io.*;

public class b43 {
    // Node đại diện cho một bản copy phim trong shop
    private static class Node {
        final int shop;
        final int movie;
        final int price;
        Node(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
    }

    // Comparator: sắp xếp theo price ↑, shop ↑, movie ↑
    private static final Comparator<Node> CMP =
        (a, b) -> {
            int c = Integer.compare(a.price, b.price);
            if (c != 0) return c;
            c = Integer.compare(a.shop, b.shop);
            if (c != 0) return c;
            return Integer.compare(a.movie, b.movie);
        };

    // Map: movie -> TreeSet (các bản copy đang available)
    private final Map<Integer, TreeSet<Node>> availableByMovie = new HashMap<>();
    // TreeSet lưu toàn bộ copy đang thuê (rented)
    private final TreeSet<Node> rentedSet = new TreeSet<>(CMP);
    // Map (shop,movie) -> Node (để lookup nhanh)
    private final Map<Long, Node> byPair = new HashMap<>();

    // Encode (shop,movie) thành 1 long duy nhất
    private static long key(int shop, int movie) {
        return (((long) shop) << 32) ^ (movie & 0xffffffffL);
    }

    // Khởi tạo hệ thống
    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            Node node = new Node(shop, movie, price);
            byPair.put(key(shop, movie), node);
            availableByMovie
                .computeIfAbsent(movie, k -> new TreeSet<>(CMP))
                .add(node);
        }
    }

    // Trả về tối đa 5 shop rẻ nhất cho movie
    public List<Integer> search(int movie) {
        List<Integer> ans = new ArrayList<>(5);
        TreeSet<Node> set = availableByMovie.get(movie);
        if (set == null || set.isEmpty()) return ans;
        Iterator<Node> it = set.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            ans.add(it.next().shop);
        }
        return ans;
    }

    // Thuê 1 copy: chuyển từ available -> rented
    public void rent(int shop, int movie) {
        long k = key(shop, movie);
        Node node = byPair.get(k);
        if (node == null) return;
        TreeSet<Node> set = availableByMovie.get(movie);
        if (set != null) set.remove(node);
        rentedSet.add(node);
    }

    // Trả phim: chuyển từ rented -> available
    public void drop(int shop, int movie) {
        long k = key(shop, movie);
        Node node = byPair.get(k);
        if (node == null) return;
        rentedSet.remove(node);
        availableByMovie
            .computeIfAbsent(movie, x -> new TreeSet<>(CMP))
            .add(node);
    }

    // Báo cáo 5 copy đang thuê rẻ nhất (shop,movie)
    public List<List<Integer>> report() {
        List<List<Integer>> ans = new ArrayList<>(5);
        Iterator<Node> it = rentedSet.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            Node n = it.next();
            ans.add(Arrays.asList(n.shop, n.movie));
        }
        return ans;
    }

    // ================== MAIN TEST ==================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ví dụ nhập:
        // 3 5   -> n=3 shops, 5 entries
        // 0 1 5
        // 0 2 6
        // 0 3 7
        // 1 1 4
        // 2 1 5
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] entries = new int[m][3];
        for (int i = 0; i < m; i++) {
            entries[i][0] = sc.nextInt(); // shop
            entries[i][1] = sc.nextInt(); // movie
            entries[i][2] = sc.nextInt(); // price
        }

        MovieRentingSystem obj = new MovieRentingSystem(n, entries);

        // Thực hiện một số lệnh test cơ bản
        System.out.println("Search movie 1: " + obj.search(1)); // tìm movie 1
        obj.rent(0, 1); // shop 0 thuê movie 1
        System.out.println("Report rented: " + obj.report());   // in danh sách đang thuê
        obj.drop(0, 1); // shop 0 trả movie 1
        System.out.println("Search movie 1 again: " + obj.search(1)); 
    }
}

// Ok, mình sẽ giải thích chi tiết đề **LeetCode 1912. Design Movie Rental System** cho bạn nhé 👇

// ---

// ## 🎬 Đề bài:

// Bạn cần thiết kế **Movie Rental System** (hệ thống cho thuê phim).

// * Có `n` cửa hàng (`shops`), mỗi cửa hàng có một số bộ phim (`movies`) với giá thuê (`price`).
// * Người dùng có thể **search** phim, **rent** phim, **drop** phim (trả lại), và xem danh sách phim đang được thuê (**report**).

// ---

// ## 🔧 Các thao tác yêu cầu cài đặt:

// 1. **Constructor**

//    * `MovieRentingSystem(int n, int[][] entries)`
//    * `n`: số lượng cửa hàng.
//    * `entries[i] = [shop, movie, price]`: cửa hàng `shop` có bộ phim `movie` với giá `price`.

// ---

// 2. **search(int movie)**

//    * Trả về **tối đa 5 cửa hàng** có bộ phim `movie` chưa bị thuê, sắp xếp theo:

//      1. Giá **rẻ hơn** trước
//      2. Nếu giá bằng nhau → cửa hàng có **shop id nhỏ hơn** trước
//    * Nếu < 5 kết quả thì trả về tất cả.

// ---

// 3. **rent(int shop, int movie)**

//    * Thuê bộ phim `movie` từ cửa hàng `shop`.
//    * Bộ phim này sẽ không còn xuất hiện trong `search`.
//    * Nhưng nó sẽ được đưa vào danh sách `report`.

// ---

// 4. **drop(int shop, int movie)**

//    * Trả lại bộ phim `movie` về cửa hàng `shop`.
//    * Sau đó bộ phim này lại có thể xuất hiện trong `search`.

// ---

// 5. **report()**

//    * Trả về **tối đa 5 phim đang được thuê**, sắp xếp theo:

//      1. Giá **rẻ hơn** trước
//      2. Nếu giá bằng nhau → `shop id` nhỏ hơn trước
//      3. Nếu vẫn bằng → `movie id` nhỏ hơn trước

// ---

// ## 🔑 Yêu cầu chính:

// * Quản lý trạng thái phim (còn trong shop hay đã thuê).
// * Hỗ trợ tìm kiếm (`search`) và thống kê (`report`) theo nhiều tiêu chí sắp xếp.
// * Trả về kết quả đúng format (list các shop/movie).

// ---

// ## 📝 Ví dụ:

// ```text
// MovieRentingSystem obj = new MovieRentingSystem(3, [[0,1,5],[0,2,6],[0,3,7],
//                                                    [1,1,4],[2,1,5]]);
// ```

// * Hệ thống có 3 cửa hàng.
// * Shop 0 có phim (1: giá 5), (2: giá 6), (3: giá 7).
// * Shop 1 có phim (1: giá 4).
// * Shop 2 có phim (1: giá 5).

// ---

// ```text
// search(1) → [1,0,2]   
// ```

// Giải thích: phim `1` có trong shop 1 (giá 4), shop 0 (giá 5), shop 2 (giá 5).
// Sắp xếp theo giá + shopId → `[1,0,2]`.

// ---

// ```text
// rent(0,1) 
// ```

// Thuê phim `1` từ shop 0.
// → Shop 0 không còn cho thuê phim `1`.

// ---

// ```text
// search(1) → [1,2]  
// ```

// Vì phim `1` ở shop 0 đã bị thuê.

// ---

// ```text
// report() → [[0,1]]  
// ```

// Vì chỉ có shop 0 đang cho thuê phim `1`.

// ---

// ```text
// drop(0,1)  
// ```

// Trả lại phim `1` cho shop 0 → lại có thể `search`.

// ---

// ```text
// search(1) → [1,0,2]  
// ```

// Lại giống ban đầu.

// ---

// ✅ Như vậy, bài này bản chất là **thiết kế dữ liệu**:

// * Dùng **map** để quản lý phim trong shop.
// * Dùng **set/heap** để hỗ trợ `search` và `report` với tiêu chí sắp xếp.

// ---

// Bạn có muốn mình viết luôn **thuật toán + cấu trúc dữ liệu phù hợp (Java/Python)** để implement bài này không?
// Bạn đang dùng một cách triển khai rất tối ưu 👍. Mình giải thích chi tiết **thuật toán và cấu trúc dữ liệu** trong code trên nhé:

// ---

// ## 🎯 Ý tưởng chính

// Hệ thống cần hỗ trợ 4 thao tác rất nhanh (nhiều lần gọi, tới `10^5` lệnh):

// 1. `search(movie)` → tìm 5 shop rẻ nhất cho một movie.
// 2. `rent(shop, movie)` → đánh dấu phim đã được thuê.
// 3. `drop(shop, movie)` → trả phim về trạng thái available.
// 4. `report()` → liệt kê 5 phim đang thuê rẻ nhất.

// Nếu dùng `List` và duyệt tuyến tính thì quá chậm. Vì vậy ta dùng **TreeSet + HashMap** để thao tác nhanh O(logN).

// ---

// ## 🔑 Các cấu trúc dữ liệu

// ### 1. `Node`

// ```java
// private static class Node {
//     final int shop, movie, price;
// }
// ```

// Đại diện một bản sao của movie trong một shop, kèm giá thuê.

// ---

// ### 2. Comparator `CMP`

// ```java
// // Sắp xếp Node: theo price ↑, rồi shop ↑, rồi movie ↑
// private static final Comparator<Node> CMP = ...
// ```

// Dùng để TreeSet luôn sắp xếp theo **giá, shop, movie**.
// → Khi duyệt TreeSet, phần tử đầu tiên luôn là copy "rẻ nhất, shop nhỏ nhất".

// ---

// ### 3. `availableByMovie`

// ```java
// private final Map<Integer, TreeSet<Node>> availableByMovie;
// ```

// * Key: movie
// * Value: TreeSet các Node `(shop, movie, price)` còn **chưa thuê**
// * Nhờ TreeSet → khi gọi `search(movie)` chỉ cần duyệt 5 phần tử đầu (O(5)).

// ---

// ### 4. `rentedSet`

// ```java
// private final TreeSet<Node> rentedSet;
// ```

// * Lưu tất cả Node đang thuê.
// * Sắp xếp toàn cục theo `CMP` (price ↑, shop ↑, movie ↑).
// * Nhờ TreeSet → khi gọi `report()` chỉ cần duyệt 5 phần tử đầu.

// ---

// ### 5. `byPair`

// ```java
// private final Map<Long, Node> byPair;
// ```

// * Key = `(shop, movie)` (được encode thành long để unique).
// * Value = `Node` tương ứng.
// * Dùng để **truy cập trực tiếp** một bản copy khi cần `rent` hoặc `drop`, thay vì phải tìm kiếm trong TreeSet.

// ---

// ## ⚙️ Các thao tác

// ### 1. `search(movie)`

// * Lấy `TreeSet<Node>` từ `availableByMovie[movie]`.
// * Duyệt 5 phần tử đầu tiên → trả về danh sách shop.
// * Độ phức tạp: **O(5) = O(1)**.

// ---

// ### 2. `rent(shop, movie)`

// * Dùng `byPair` để lấy Node trong O(1).
// * Xóa Node khỏi `availableByMovie[movie]` (O(logN)).
// * Thêm Node vào `rentedSet` (O(logN)).

// ---

// ### 3. `drop(shop, movie)`

// * Dùng `byPair` lấy Node trong O(1).
// * Xóa Node khỏi `rentedSet` (O(logN)).
// * Thêm lại Node vào `availableByMovie[movie]` (O(logN)).

// ---

// ### 4. `report()`

// * Duyệt 5 phần tử đầu tiên trong `rentedSet`.
// * Trả về cặp `[shop, movie]`.
// * Độ phức tạp: **O(5) = O(1)**.

// ---

// ## ⏱️ Độ phức tạp

// * `search`: O(1)
// * `rent`: O(logN)
// * `drop`: O(logN)
// * `report`: O(1)

// Với N ≤ 10^5, tổng số thao tác ≤ 10^5 → hoàn toàn chạy trong giới hạn thời gian.

// ---

// 👉 Tóm lại, giải pháp này **sử dụng TreeSet + HashMap** để đạt tốc độ tối ưu cho cả `search` và `report`, đồng thời quản lý nhanh trạng thái phim (`rent`, `drop`).

// ---

// Bạn có muốn mình thêm **hàm `main` + Scanner** để test trực tiếp với input mẫu LeetCode không?
