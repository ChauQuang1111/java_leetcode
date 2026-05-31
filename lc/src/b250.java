// Destroying Asteroidsh(31/05/2026)

// Đề bài

// Bạn có:

// Một hành tinh có khối lượng ban đầu mass.

// Một mảng asteroids, trong đó asteroids[i] là khối lượng của tiểu hành tinh thứ i.

// Quy tắc:



// Bạn có thể chọn phá hủy các tiểu hành tinh theo bất kỳ thứ tự nào.

// Nếu khối lượng hiện tại của hành tinh lớn hơn hoặc bằng khối lượng của tiểu hành tinh:

// Hành tinh phá hủy được tiểu hành tinh đó.

// Khối lượng của hành tinh tăng thêm đúng bằng khối lượng tiểu hành tinh.

// mass = mass + asteroids[i]

// Nếu khối lượng hiện tại của hành tinh nhỏ hơn khối lượng tiểu hành tinh:

// Hành tinh bị phá hủy.

// Không thể tiếp tục.

// Yêu cầu:



// Trả về true nếu có thể phá hủy tất cả các tiểu hành tinh, ngược lại trả về false.

// Ví dụ 1

// mass = 10

// asteroids = [3, 9, 19, 5, 21]

// Sắp xếp tăng dần:



// [3, 5, 9, 19, 21]

// Quá trình:



// mass = 10



// 10 >= 3  => mass = 13

// 13 >= 5  => mass = 18

// 18 >= 9  => mass = 27

// 27 >= 19 => mass = 46

// 46 >= 21 => mass = 67

// Phá hủy được tất cả ⇒



// true

// Ví dụ 2

// mass = 5

// asteroids = [4, 9, 23, 4]

// Sắp xếp:



// [4, 4, 9, 23]

// Quá trình:



// 5 >= 4  => mass = 9

// 9 >= 4  => mass = 13

// 13 >= 9 => mass = 22

// 22 < 23

// Không phá được tiểu hành tinh cuối ⇒



// false

// Ý tưởng chính

// Để có cơ hội sống sót cao nhất:



// Luôn phá những tiểu hành tinh nhỏ nhất trước.

// Vì sau mỗi lần phá hủy, khối lượng của hành tinh sẽ tăng lên.

// Do đó lời giải là:



// Sắp xếp asteroids tăng dần.

// Duyệt từ nhỏ đến lớn:

// Nếu mass < asteroid ⇒ false.

// Ngược lại mass += asteroid.

// Duyệt hết ⇒ true.

// Độ phức tạp:



// Sắp xếp: O(n log n)

// Duyệt: O(n)

// Tổng cộng:



// O(n log n)

// Nếu bạn cần, mình cũng có thể giải thích vì sao chiến lược sắp xếp tăng dần là tối ưu (chứng minh greedy).



// Đây là một lời giải sử dụng Counting Sort (đếm tần suất) thay vì gọi Arrays.sort().

// Ý tưởng chính

// Trong bài toán, chiến lược tối ưu là:



// Luôn ăn các tiểu hành tinh có khối lượng nhỏ trước.

// Thông thường ta sẽ:



// Arrays.sort(asteroids);

// rồi duyệt từ nhỏ đến lớn.

// Nhưng ở đây tác giả nhận thấy:



// 1 <= asteroids[i] <= 100000

// Giá trị khối lượng bị giới hạn khá nhỏ.

// Vì vậy thay vì sắp xếp O(n log n), ta dùng mảng đếm tần suất (freq) để mô phỏng việc duyệt theo thứ tự tăng dần.

// Bước 1: Tìm min và max

// int min = 100000;

// int max = 0;



// for(int x : asteroids) {

//     max = Math.max(x, max);

//     min = Math.min(x, min);

// }

// Ví dụ:



// asteroids = [8, 2, 5, 2]

// Sau vòng lặp:



// min = 2

// max = 8

// Bước 2: Tạo mảng đếm tần suất

// int[] freq = new int[max + 1];

// Ví dụ:



// asteroids = [8, 2, 5, 2]

// Mảng:



// freq[2] = 2

// freq[5] = 1

// freq[8] = 1

// Bước 3: Ăn ngay các tiểu hành tinh nhỏ hơn hoặc bằng mass

// long val = mass;



// for(int x : asteroids) {

//     if(x > val)

//         freq[x]++;

//     else

//         val += x;

// }

// Giả sử:



// mass = 10

// asteroids = [3, 15, 2, 20]

// Duyệt

// x = 3

// 3 <= 10

// Ăn được:



// val = 13

// x = 15

// 15 > 13

// Chưa ăn được:



// freq[15]++

// x = 2

// 2 <= 13

// Ăn được:



// val = 15

// x = 20

// 20 > 15

// Chưa ăn được:



// freq[20]++

// Kết quả:



// val = 15

// freq[15] = 1

// freq[20] = 1

// Bước 4: Tối ưu dừng sớm

// if(val >= max) return true;

// Nếu:



// max = 20

// val = 25

// thì:



// 25 >= mọi asteroid

// nên chắc chắn ăn được hết.

// Bước 5: Mô phỏng duyệt tăng dần bằng freq

// for(int i = min; i <= max; i++) {

// Thay vì:



// Arrays.sort(...)

// ta duyệt:



// 2,3,4,5,...,max

// Kiểm tra có ăn được không

// if(val < i) return false;

// Ví dụ:



// val = 10

// i = 15

// Có nghĩa là đang tới asteroid khối lượng 15.

// Nhưng:



// 10 < 15

// Không ăn được.

// Và vì đang duyệt từ nhỏ đến lớn:



// 15,16,17,...

// đều lớn hơn hoặc bằng 15.

// Không còn cơ hội tăng khối lượng nữa.

// => Trả về false.

// Ăn toàn bộ asteroid có cùng khối lượng

// if(freq[i] != 0)

//     val += i * freq[i];

// Ví dụ:



// freq[5] = 3

// Tức có:



// 5, 5, 5

// Nếu:



// val >= 5

// thì ăn được cả ba:



// val += 5 * 3

// Ví dụ hoàn chỉnh

// mass = 10



// asteroids = [3, 9, 19, 5, 21]

// Bước đầu

// Ăn được:



// 3 -> val = 13

// 9 -> val = 22

// 19 -> val = 41

// 5 -> val = 46

// 21 -> val = 67

// val >= max

// => trả về true ngay.

// Vì sao if(val < i) return false là đúng?

// Giả sử:



// val = 10

// Các asteroid chưa ăn được là:



// 15, 20, 25

// Khi vòng lặp tới:



// i = 15

// ta có:



// 10 < 15

// Không ăn được asteroid nhỏ nhất còn lại.

// Do không ăn được cái nhỏ nhất:



// 20,25

// càng không ăn được.

// Khối lượng sẽ không bao giờ tăng thêm.

// => Chắc chắn thất bại.

// Độ phức tạp

// Thời gian

// Duyệt mảng ban đầu: O(n)

// Duyệt từ min tới max: O(max)

// Tổng:



// O(n + maxValue)

// Trong bài:



// maxValue <= 100000

// nên rất nhanh.



// Bộ nhớ

// Mảng tần suất:



// O(maxValue)

// So sánh với cách sort

// CáchTimeSort + GreedyO(n log n)Counting Sort (code trên)O(n + 100000)

// Code này tận dụng giới hạn asteroids[i] ≤ 100000 để thay việc sắp xếp bằng mảng đếm tần suất, từ đó đạt độ phức tạp gần tuyến tính.



import java.util.*;

public class b250 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
 // Nhập khối lượng ban đầu của hành tinh
        int mass = sc.nextInt();

        // Nhập số lượng asteroid
        int n = sc.nextInt();

        int[] asteroids = new int[n];

        // Nhập danh sách asteroid
        for (int i = 0; i < n; i++) {
            asteroids[i] = sc.nextInt();
        }

        // Gọi hàm kiểm tra
        boolean result = asteroidsDestroyed(mass, asteroids);

        // In kết quả
        System.out.println(result);

        sc.close();
    }
// Hàm kiểm tra có thể phá hủy tất cả asteroid hay không
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // Tìm khối lượng nhỏ nhất và lớn nhất trong mảng
        int min = 100000;
        int max = 0;

        for (int x : asteroids) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }

        // Mảng đếm tần suất xuất hiện của từng khối lượng
        int[] freq = new int[max + 1];

        // Dùng long để tránh tràn số khi mass tăng rất lớn
        long val = mass;

        /*
         * Nếu asteroid hiện tại ăn được thì cộng ngay vào val.
         * Nếu chưa ăn được thì lưu lại vào mảng tần suất.
         */
        for (int x : asteroids) {
            if (x > val) {
                freq[x]++;
            } else {
                val += x;
            }
        }

        // Nếu đã lớn hơn hoặc bằng asteroid lớn nhất
        // thì chắc chắn ăn được tất cả phần còn lại
        if (val >= max) {
            return true;
        }

        /*
         * Duyệt theo thứ tự tăng dần giống như đã sort.
         * freq[i] cho biết có bao nhiêu asteroid có khối lượng i.
         */
        for (int i = min; i <= max; i++) {

            // Nếu không đủ khối lượng để ăn asteroid i
            // thì cũng không thể ăn bất kỳ asteroid lớn hơn nào
            if (val < i) {
                return false;
            }

            // Ăn tất cả asteroid có khối lượng i
            if (freq[i] != 0) {
                val += (long) i * freq[i];
            }
        }

        return true;
    }
}

