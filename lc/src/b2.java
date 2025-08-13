// 13/08/2025
// 1390. Four Divisors

import java.util.*;

public class b2 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = sumFourDivisors(nums);
        System.out.println(res);

    }

    public static int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            int sochianhonhat = 0;
            for (int sochia = 2; sochia * sochia <= num; sochia++) {
                if (num % sochia == 0) {
                    if (sochianhonhat == 0) {
                        sochianhonhat = sochia;
                    } else {
                        sochianhonhat = 0;
                        break;
                    }
                }
            }
            if (sochianhonhat > 0 && sochianhonhat != num / sochianhonhat) {
                int sochiakhac = num / sochianhonhat;
                totalSum += 1 + num + sochianhonhat + sochiakhac;

            }

        }
        return totalSum;
    }

}
// Giải thích thuật toán 1. Khởi tạo tổng
// 1. Khởi tạo tổng
// int totalSum = 0;: Khởi tạo một biến để lưu tổng cuối cùng. Biến này sẽ được
// cộng dồn sau khi xử lý mỗi số trong mảng.

// 2. Vòng lặp duyệt mảng
// for (int num : nums) { ... }: Vòng lặp này duyệt qua từng số num trong mảng
// đầu vào nums. Thuật toán sẽ xử lý mỗi số một cách độc lập.

// 3. Tìm kiếm ước số nhỏ nhất (cờ hiệu)
// int sochianhonhat = 0;: Khởi tạo một biến đặc biệt để làm cờ hiệu.
// Giá trị 0: Ban đầu, chưa tìm thấy ước số nào.
// Giá trị khác 0: Đã tìm thấy một ước số.

// 4. Vòng lặp tìm ước số
// for (int sochia = 2; sochia * sochia <= num; sochia++) { ... }: Vòng lặp này
// tìm các ước số tiềm năng của num, bắt đầu từ 2 và chỉ chạy đến
// căn của num
// if (num % sochia == 0): Kiểm tra xem sochia có phải là ước số của num hay
// không.
// if (sochianhonhat == 0): Nếu đây là lần đầu tiên tìm thấy một ước số, lưu giá
// trị đó vào sochianhonhat.
// else { sochianhonhat = 0; break; }: Nếu tìm thấy ước số thứ hai, điều đó có
// nghĩa là số đó có nhiều hơn 4 ước số. Đặt sochianhonhat về 0 để đánh dấu và
// thoát vòng lặp ngay lập tức.

// 5. Kiểm tra điều kiện và tính tổng
// if (sochianhonhat > 0 && sochianhonhat != num / sochianhonhat) { ... }: Đây
// là bước kiểm tra cuối cùng để xác định xem số có chính xác 4 ước số hay
// không.
// sochianhonhat > 0: Đảm bảo rằng đã tìm thấy một ước số trong vòng lặp.
// sochianhonhat != num / sochianhonhat: Đảm bảo số đó không phải là số chính
// phương. Nếu num là số chính phương, nó chỉ có 3 ước số
// int sochiakhac = num / sochianhonhat;: Nếu cả hai điều kiện trên đều đúng, ta
// tìm được ước số thứ tư.
// totalSum += 1 + num + sochianhonhat + sochiakhac;: Cộng tổng của 4 ước số vào
// totalSum. Bốn ước số đó là: 1, num, ước số nhỏ nhất tìm được, và ước số còn
// lại.

// 6. Trả về kết quả
// return totalSum;: Sau khi vòng lặp chính kết thúc, hàm trả về tổng cuối cùng
// của tất cả các ước số hợp lệ.