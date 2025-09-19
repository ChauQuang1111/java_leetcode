
// 2353. Design a Food Rating System(17/09/2025)
import java.util.*;

public class b39 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số món ăn: ");
        int n = sc.nextInt();
        sc.nextLine(); // bỏ xuống dòng

        String[] foods = new String[n];
        String[] cuisines = new String[n];
        int[] ratings = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Tên món ăn: ");
            foods[i] = sc.nextLine();
            System.out.print("Loại ẩm thực: ");
            cuisines[i] = sc.nextLine();
            System.out.print("Rating: ");
            ratings[i] = sc.nextInt();
            sc.nextLine(); // bỏ xuống dòng
        }

        // Tạo hệ thống FoodRatings
        b39 fr = new b39(foods, cuisines, ratings);

        // Test chức năng
        String queryCuisine = sc.nextLine();
        System.out.println(queryCuisine + ": " + fr.highestRated(queryCuisine));

        String foodToChange = sc.nextLine();
        int newRating = sc.nextInt();
        sc.nextLine();
        fr.changeRating(foodToChange, newRating);

        System.out.println(
                queryCuisine + ": " + fr.highestRated(queryCuisine));
    }

    // Lớp Food: lưu thông tin của một món ăn
    static class Food {
        String name; // tên món ăn
        String cuisine; // loại ẩm thực
        int rating; // điểm đánh giá

        Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    // Map ánh xạ từ tên món ăn -> đối tượng Food (luôn chứa rating MỚI NHẤT)
    public Map<String, Food> foodMap;

    // Map ánh xạ từ cuisine -> max heap các món ăn
    public Map<String, PriorityQueue<Food>> cuisineToMaxHeap;

    // Constructor
    public b39(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>();
        cuisineToMaxHeap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], food);

            cuisineToMaxHeap
                    .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(
                            (a, b) -> {
                                if (b.rating != a.rating) {
                                    return b.rating - a.rating; // rating cao hơn trước
                                }
                                return a.name.compareTo(b.name); // tie-break bằng tên
                            }))
                    .add(food);
        }
    }

    // Đổi rating cho một món ăn
    public void changeRating(String foodName, int newRating) {
        Food oldFood = foodMap.get(foodName);
        Food updatedFood = new Food(foodName, oldFood.cuisine, newRating);
        foodMap.put(foodName, updatedFood);
        cuisineToMaxHeap.get(oldFood.cuisine).add(updatedFood);
    }

    // Lấy món ăn rating cao nhất của một cuisine
    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineToMaxHeap.get(cuisine);

        while (!pq.isEmpty()) {
            Food top = pq.peek();
            Food latest = foodMap.get(top.name);

            if (top.rating == latest.rating) {
                return top.name; // đúng version mới nhất
            } else {
                pq.poll(); // bỏ version cũ
            }
        }
        return "";
    }

}

// Ok mình
// giải thích
// từng dòng constructor`b39(...)` nha 👇

// ---

// ```java

// public b39(String[] foods, String[] cuisines, int[] ratings) {
// foodMap = new HashMap<>();
// cuisineToMaxHeap = new HashMap<>();
// ```

// * `foodMap` dùng để lưu **phiên bản mới nhất** của từng

// món ăn (`tên món -> Food`).
// * `cuisineToMaxHeap` dùng để ánh xạ **một loại cuisine** → **heap
// (PriorityQueue) chứa các món ăn thuộc cuisine đó**, heap sắp xếp sao cho món
// **rating cao nhất đứng đầu**.

// ---

// ```java
// for (int i = 0; i < foods.length; i++) {
// Food food = new Food(foods[i], cuisines[i], ratings[i]);
// foodMap.put(foods[i], food);
// ```

// * Vòng lặp qua tất cả các món ăn.
// * Tạo đối tượng `Food` với tên, loại cuisine, và rating.
// * Đưa `food` vào `foodMap`, để sau này tra cứu nhanh theo tên món.

// ---

// ```java
// cuisineToMaxHeap
// .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(
// (a, b) -> {
// if (b.rating != a.rating) {
// return b.rating - a.rating; // rating cao hơn trước
// }
// return a.name.compareTo(b.name); // tie-break bằng tên
// }))
// .add(food);
// ```

// ### Giải thích chi tiết đoạn này:

// * `computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(...))`
// 🔹 Nếu cuisine `cuisines[i]` chưa có trong map → tạo mới 1 `PriorityQueue`
// (heap).
// 🔹 Nếu đã có thì lấy heap cũ ra.

// * PriorityQueue này dùng comparator `(a, b) -> ...`:

// 1. `if (b.rating != a.rating) return b.rating - a.rating;`
// → Món có **rating cao hơn đứng trước** (max-heap theo rating).
// 2. `return a.name.compareTo(b.name);`
// → Nếu rating bằng nhau, thì chọn món có **tên từ điển nhỏ hơn** (A < B < C).

// * `.add(food)`
// → Thêm `food` vào heap của cuisine tương ứng.

// ✅ Tóm gọn:

// Constructor này khởi tạo dữ liệu ban đầu:

// * Lưu từng món ăn vào `foodMap` để quản lý phiên bản mới nhất.
// * Thêm món ăn vào `cuisineToMaxHeap[cuisine]` để luôn lấy được món có rating
// cao

// nhất (hoặc cùng rating thì theo thứ tự tên).

// ---

// Bạn có muốn mình **vẽ sơ đồ dữ liệu** sau khi chạy xong constructor với

// ví dụ (vd: `foods = ["kimchi","ramen"], cuisines = ["Korean","Japanese"],
// ratings = [5,7]`) để bạn thấy heap và map trông như thế nào không?
// Ok mình giải thích kỹ `.computeIfAbsent(...)` + cái `(a, b)` comparator nhé
// 👇

// ### 1. `computeIfAbsent`

// Trong Java, `Map` có hàm:

// ```java

// V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
// ```

// Nó có nghĩa là:

// * Nếu `map` **đã có key** → lấy value ra luôn.
// * Nếu `map` **chưa có key** → chạy `mappingFunction` để tạo value mới, rồi
// **put(key, value)** vào map, và trả về value đó.

// 👉 Ở đây:

// ```java
// cuisineToMaxHeap.computeIfAbsent(
// cuisines[i],
// k -> new PriorityQueue<>(...)
// )
// ```

// * Nếu `cuisineToMaxHeap` chưa có cuisine `cuisines[i]` → nó sẽ tạo **1 cái
// PriorityQueue mới** bằng `new PriorityQueue<>(...)`.
// * Nếu đã có cuisine đó rồi → nó sẽ lấy cái PriorityQueue cũ ra.

// ---

// ### 2. `(a, b) -> { ... }` (Comparator)

// Đây là **lambda expression** để định nghĩa cách so sánh giữa 2 phần tử `a` và
// `b` trong `PriorityQueue`.
// Cụ thể:

// ```java
// (a, b) -> {
// if (b.rating != a.rating) {
// return b.rating - a.rating; // rating cao hơn trước
// }
// return a.name.compareTo(b.name); // nếu rating bằng nhau thì so sánh tên
// }
// ```

// * `b.rating - a.rating`:
// Nếu rating khác nhau → ai có rating cao hơn sẽ được đưa lên đầu heap (vì
// `PriorityQueue` sắp xếp theo comparator).

// * `a.name.compareTo(b.name)`:
// Nếu rating bằng nhau → so sánh

// theo tên (thứ tự từ điển, "apple" < "banana").

// ### 3. Tóm lại

// Đoạn code này có ý nghĩa:

// * Đảm bảo mỗi `cuisine` sẽ có **1 heap** trong map.
// * Heap đó được sắp xếp theo:

// 1. `rating`

// giảm dần (max-heap).
// 2. Nếu bằng nhau → tên món ăn theo alphabet.

// 👉 Ví dụ nhỏ:

// ```java
// foods = ["pho", "bun", "com"]
// cuisines = ["Vietnam", "Vietnam", "Vietnam"]
// ratings = [5, 5, 7]
// ```

// * Trong map `cuisineToMaxHeap["Vietnam"]` sẽ có heap sắp xếp:

// 1. "com" (rating 7)
// 2. "bun" (rating 5, tên nhỏ hơn "pho")
// 3. "pho" (rating 5)

// ---

// Bạn có muốn mình viết lại cái `computeIfAbsent(...)` này theo cách thủ công
// (dùng `if (!map.containsKey(...))`) để dễ hiểu hơn không?
// À mình hiểu rồi 👍
// Bạn đang hỏi cụ thể **`computeIfAbsent` là gì** đúng không?

// ---

// ### `computeIfAbsent` trong Java Map

// Đây là một **hàm có sẵn** của interface `Map` (từ Java 8 trở đi).

// **Cú pháp:**

// ```java
// map.computeIfAbsent(key, k -> new ValueType(...));
// ```

// **Ý nghĩa:**

// * Kiểm tra xem trong `map` có tồn tại `key` chưa.
// * Nếu **có rồi** → trả về value cũ trong map.
// * Nếu **chưa có** → chạy function `k -> new ValueType(...)` để tạo ra value
// mới, rồi **put vào map**, sau đó trả về value mới này.

// ---

// ### Ví dụ dễ hiểu

// ```java
// Map<String, List<String>> map = new HashMap<>();

// map.computeIfAbsent("fruit", k -> new ArrayList<>()).add("apple");
// map.computeIfAbsent("fruit", k -> new ArrayList<>()).add("banana");
// map.computeIfAbsent("drink", k -> new ArrayList<>()).add("coffee");

// System.out.println(map);
// ```

// 👉 Output:

// ```
// {fruit=[apple, banana], drink=[coffee]}
// ```

// * Lần đầu gặp `"fruit"` → chưa có trong map → tạo `new ArrayList<>()`.
// * Lần sau gặp `"fruit"` → đã có rồi → dùng lại cái list cũ để thêm
// `"banana"`.
// * Với `"drink"` → tạo list mới và thêm `"coffee"`.

// ---

// ### Trong code của bạn

// ```java
// cuisineToMaxHeap.computeIfAbsent(
// cuisines[i],
// k -> new PriorityQueue<>( (a, b) -> { ... } )
// ).add(food);
// ```

// 👉 Có nghĩa là:

// * Nếu chưa có heap cho cuisine `cuisines[i]` → tạo một cái `PriorityQueue`

// mới (với comparator theo rating).
// * Nếu đã có → dùng lại heap cũ.
// * Sau đó `.add(food)` để bỏ món ăn vào đúng heap.

// 📌 Tóm gọn:
// `computeIfAbsent` = **"nếu map chưa có key thì tạo value mới, nếu có rồi thì
// dùng lại"**.

// ---

// Bạn có muốn mình viết lại đoạn code này **không dùng `computeIfAbsent`**
// (thay bằng `if (!map.containsKey(...))`) để dễ hình dung hơn không?
// Ok 👍 mình sẽ viết lại đoạn đó, bỏ `computeIfAbsent` đi và thay bằng `if` cho
// dễ hiểu hơn:

// ---

// ###

// Bản gốc (dùng `computeIfAbsent`)

// ```java
// cuisineToMaxHeap
// .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(
// (a, b) -> {
// if (b.rating != a.rating) {
// return b.rating - a.rating; // rating cao hơn trước
// }
// return a.name.compareTo(b.name); // tie-break bằng tên
// }))
// .add(food);
// ```

// ---

// // ### Viết lại bằng `if`

// ```java
// // Nếu chưa có cuisine trong map thì tạo PriorityQueue mới
// if (!cuisineToMaxHeap.containsKey(cuisines[i])) {
// cuisineToMaxHeap.put(
// cuisines[i],
// new PriorityQueue<>((a, b) -> {
// if (b.rating != a.rating) {
// return b.rating - a.rating; // rating cao hơn trước
// }
// return a.name.compareTo(b.name); // tie-break bằng tên
// })
// );
// }

// // Sau đó add món ăn vào heap của cuisine tương ứng
// cuisineToMaxHeap.get(cuisines[i]).add(food);
// ```

// ---

// 👉 Hai cách này **hoàn toàn giống nhau về logic**, chỉ khác là:

// * `computeIfAbsent` gọn hơn, “tạo nếu chưa có”.
// * `if (!containsKey(...))` thì viết dài hơn nhưng dễ đọc cho người mới học.

// Bạn có muốn mình viết luôn phiên bản constructor `b39(...)` **full code chỉ
// dùng `if`**, không xài `computeIfAbsent`, để bạn copy chạy luôn không?
// Hay câu hỏi 👌 mình giải thích chi tiết:

// ---

// ### `new PriorityQueue<>((a, b) -> { ... })` nghĩa là gì?

// 1. `new PriorityQueue<>(...)` → tạo một **PriorityQueue** (hàng đợi ưu tiên).

// * Bình thường `PriorityQueue` trong Java là **min-heap** (phần tử nhỏ nhất ở
// đầu).
// * Nhưng nếu bạn muốn thay đổi cách so

// sánh (ví dụ: rating cao nhất đứng đầu) → bạn phải truyền vào **Comparator**.

// 2. `(a, b) -> { ... }` → chính là một **lambda expression** trong Java, định
// nghĩa **Comparator**.

// * Nó thay thế cho việc viết một class `Comparator<Food>` dài dòng.
// * Ở đây `a` và `b` là 2 đối tượng `Food` cần so sánh.

// ---

// ### Trong code của bạn

// ```java
// new PriorityQueue<>((a, b) -> {
// if (b.rating != a.rating) {
// return b.rating - a.rating; // so sánh theo rating, cao hơn lên trước
// }
// return a.name.compareTo(b.name); // nếu rating bằng nhau thì so sánh theo tên
// })
// ```

// 📌 Ý nghĩa:

// * Nếu `a` và `b` có rating khác nhau → ai có rating **cao hơn** sẽ được

// ưu tiên (đứng trước trong heap).
// * Nếu rating bằng nhau → so sánh

// bằng tên (`String.compareTo`) theo alphabet.

// ---

// ### Ví dụ

// Giả sử ta có các món ăn:

// ```java
// Food f1 = new Food("Pho", "Vietnam", 5);
// Food f2 = new Food("Bun", "Vietnam", 5);
// Food f3 = new Food("Com", "Vietnam", 7);
// ```

// Đưa vào `PriorityQueue` với comparator trên:

// ```java
// PriorityQueue<Food> pq = new PriorityQueue<>((a, b) -> {
// if (b.rating != a.rating) return b.rating - a.rating;
// return a.name.compareTo(b.name);
// });

// pq.add(f1);
// pq.add(f2);
// pq.add(f3);
// ```

// 👉 Thứ tự ưu tiên trong heap sẽ là:

// 1. `"Com"` (rating 7, cao nhất)
// 2. `"Bun"` (rating 5, cùng điểm với "Pho" nhưng tên nhỏ hơn)
// 3. `"Pho"` (rating 5)

// ---

// ✅ Tóm gọn:
// `new PriorityQueue<>((a, b) -> {...})` = tạo một **PriorityQueue có luật sắp
// xếp riêng** do bạn định nghĩa bằng lambda comparator.

// ---

// Bạn có muốn mình viết lại phiên bản này **không dùng lambda** mà dùng class
// `Comparator<Food>` riêng, để dễ hình dung hơn không?

// Ok👍

// mình giải
// thích chi
// tiết thuật
// toán của class`FoodRatings`
// này nhé:

// ---

// ##
// Ý tưởng
// tổng quan

// Bài toán
// yêu cầu:

// 1.
// Quản lý**món ăn**(`food`)với:

// *

// tên (`name`)
// * loại ẩm thực (`cuisine`)
// * điểm đánh giá (`rating`).
// 2. Hỗ trợ:

// * **changeRating(food, newRating)** → cập nhật điểm của món ăn.
// * **highestRated(cuisine)** → trả về tên món ăn có rating cao nhất của 1
// cuisine, nếu trùng thì chọn tên **từ điển nhỏ nhất**.

// \=> Giải pháp dùng:

// * HashMap để tra cứu nhanh món ăn.
// * PriorityQueue (max-heap) để lấy nhanh nhất món ăn có rating cao nhất theo
// từng cuisine.

// ---

// ## Các cấu trúc dữ liệu

// 1. **`foodMap`**:
// `Map<String, Food>`

// * key = tên món ăn
// * value = đối tượng `Food` (lưu tên, cuisine, rating hiện tại)
// → giúp tìm nhanh thông tin món ăn theo tên.

// 2. **`cuisineToMaxHeap`**:
// `Map<String, PriorityQueue<Food>>`

// * key = tên cuisine
// * value = max-heap (ưu tiên rating cao nhất, nếu bằng nhau thì theo tên từ
// điển nhỏ nhất).
// → giúp lấy nhanh món ăn cao điểm nhất trong cuisine.

// ---

// ## Thuật toán từng hàm

// ### 1. **Constructor**

// ```java
// for (int i = 0; i < foods.length; i++) {
// Food food = new Food(foods[i], cuisines[i], ratings[i]);
// foodMap.put(foods[i], food);

// cuisineToMaxHeap
// .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(...))
// .add(food);
// }
// ```

// * Tạo `Food` cho mỗi món.
// * Lưu vào `foodMap`.
// * Thêm vào heap ứng với cuisine.
// Heap sắp xếp theo:

// * `rating` giảm dần
// * nếu bằng thì `name`

// tăng dần (so sánh chuỗi).

// ---

// ### 2. **changeRating(foodName, newRating)**

// ```java
// Food oldFood = foodMap.get(foodName);
// Food updatedFood = new Food(foodName, oldFood.cuisine, newRating);

// foodMap.put(foodName, updatedFood);
// cuisineToMaxHeap.get(oldFood.cuisine).add(updatedFood);
// ```

// * Lấy món ăn cũ ra.
// * Tạo `Food` mới với rating mới.
// * Cập nhật vào `foodMap`.
// * Đưa bản ghi mới vào heap.

// 👉 **Lazy deletion**: không xóa bản cũ

// khỏi heap (vì `PriorityQueue` không hỗ trợ remove nhanh). Thay vào đó, giữ
// nguyên. Khi gọi `highestRated`, mình sẽ bỏ qua bản ghi cũ.

// ---

// ### 3. **highestRated(cuisine)**

// ```java
// PriorityQueue<Food> pq = cuisineToMaxHeap.get(cuisine);

// while (!pq.isEmpty()) {
// Food top = pq.peek();
// Food latest = foodMap.get(top.name);

// if (top.rating == latest.rating) {
// return top.name;
// } else {
// pq.poll(); // bỏ bản cũ (rating không khớp)
// }
// }
// return "";
// ```

// * Lấy top

// của heap (rating cao nhất).
// * Kiểm tra xem nó có phải bản mới nhất trong `foodMap` không.

// * Nếu `rating` khớp → trả về tên món.
// * Nếu không → bỏ bản cũ (poll) và tiếp tục.
// * Vì mỗi lần `changeRating` thêm bản mới, heap có thể chứa nhiều bản cũ. Vòng
// lặp sẽ dọn dần khi cần.

// ## Độ phức tạp

// * **changeRating**: `O(log n)` (thêm vào heap).
// * **highestRated**: trung bình `O(log n)` (bỏ các bản cũ), worst-case có thể
// nhiều stale nhưng vẫn chấp nhận được vì mỗi bản stale chỉ bị bỏ **một lần duy
// nhất**.
// // * Bộ nhớ: `O(n)`.

// ---

// 👉 Nói ngắn gọn:

// * Dùng **HashMap** để lưu bản mới nhất.
// * Dùng **PriorityQueue** để tìm nhanh nhất món cao điểm.
// * Dùng **lazy deletion** để xử lý khi rating thay đổi.

// ---

// Bạn có muốn mình vẽ sơ đồ luồng

// dữ liệu (foods → foodMap → heaps) để dễ hình dung hơn không?

// Ok,
// mình giải
// thích bài**LeetCode 2353–
// Design a
// Food Rating System**
// cho bạn nha👍

// ---

// ###**
// Đề bài**

// Bạn cần
// thiết kế
// một hệ
// thống đánh
// giá món

// ăn (**Food Rating System**) với các chức năng sau:

// * Có một danh sách các món ăn, mỗi món ăn thuộc về **một loại ẩm thực**
// (cuisine) và có một **điểm đánh giá** (rating).
// * Hệ thống phải hỗ trợ 3 thao tác:

// 1. **`FoodRatings(String[] foods, String[] cuisines, int[] ratings)`**

// * Khởi tạo hệ thống với:

// * `foods[i]` là tên món ăn thứ `i`.
// * `cuisines[i]` là loại ẩm thực của `foods[i]`.
// * `ratings[i]` là điểm đánh giá của `foods[i]`.

// 2. **`void changeRating(String food, int newRating)`**

// * Cập nhật lại điểm số (`rating`) của món ăn `food` thành `newRating`.

// 3. **`String highestRated(String cuisine)`**

// * Trả về **tên món ăn có điểm đánh giá cao nhất** trong loại ẩm thực
// `cuisine`.
// * Nếu có nhiều món ăn có cùng điểm cao nhất → trả về **tên món ăn nhỏ nhất
// theo thứ tự từ điển** (lexicographically smallest).

// ---

// ### **Ví dụ**

// Input:

// ```text
// ["FoodRatings", "highestRated", "highestRated", "changeRating",
// "highestRated", "changeRating", "highestRated"]
// [[["kimchi","miso","sushi","moussaka","ramen","bulgogi"],
// ["korean","japanese","japanese","greek","japanese","korean"],
// [9,12,8,15,14,7]],
// ["korean"], ["japanese"], ["sushi",16], ["japanese"], ["ramen",16],
// ["japanese"]]
// ```

// Output:

// ```text
// [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
// ```

// **Giải thích:**

// * Ban đầu:

// * `kimchi (korean, 9)`
// * `miso (japanese, 12)`
// * `sushi (japanese, 8)`
// * `moussaka (greek, 15)`
// * `ramen (japanese, 14)`
// * `bulgogi (korean, 7)`

// 1. `highestRated("korean")` → **kimchi** (9 cao nhất trong korean).
// 2. `highestRated("japanese")` → **ramen** (14 > 12 > 8).
// 3. `changeRating("sushi", 16)` → sushi update lên 16.
// 4. `highestRated("japanese")` → **sushi** (16 cao nhất).
// 5. `changeRating("ramen", 16)` → ramen update lên 16.
// 6. `highestRated("japanese")` → sushi và ramen đều 16 → **ramen** thắng vì từ
// điển nhỏ hơn.

// ---

// ### **Ý tưởng giải**

// * Cần lưu trữ mối quan hệ:

// * `food → (cuisine, rating)`
// * `cuisine → danh sách các món ăn`
// * Khi `changeRating`, cập nhật rating cho món ăn.
// * Khi `highestRated`, tìm món ăn có rating cao nhất trong cuisine đó.
// Để tối ưu:

// * Dùng **Heap (PriorityQueue)** hoặc **TreeSet** để quản lý món ăn trong mỗi
// cuisine.
// * Ưu tiên `(-rating, name)` để vừa đảm bảo rating giảm dần, vừa lấy từ điển
// nhỏ nhất.

// ---

// 👉 Bạn có muốn mình viết code Java/Python mẫu và chú thích chi tiết cách cài
// đặt không?
