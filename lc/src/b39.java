
// 2353. Design a Food Rating System(17/09/2025)
import java.util.*;

public class b39 {
    static Scanner sc = new Scanner(System.in);

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
    private Map<String, Food> foodMap;

    // Map ánh xạ từ cuisine -> max heap các món ăn
    private Map<String, PriorityQueue<Food>> cuisineToMaxHeap;

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

    // ================== MAIN ==================
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
}
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
