import java.util.*;

public class sangdemuoc {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();

        }
        int res = findMaxGCD(arr);
        System.out.println(res);
    }

    public static int findMaxGCD(int[] arr) {
        // Tìm giá trị lớn nhất trong mảng
        int maxValue = 0;
        for (int x : arr) {
            if (x > maxValue) {
                maxValue = x;
            }

        }
        // Mảng counts để đánh dấu sự hiện diện của các số trong mảng ban đầu
        int[] count = new int[maxValue + 1];
        for (int p : arr) { // Lặp qua mảng arr, không phải mảng count
            count[p] = 1; // Đặt giá trị 1 tại chỉ số tương ứng với số x
        }

        // Đoạn này đang tìm ước chung lớn nhất (GCD) của ít nhất 2 số trong mảng bằng
        // cách duyệt từ số lớn xuống số nhỏ.
        // Bắt đầu từ giá trị lớn nhất maxVal và giảm dần xuống 1.
        // g là ứng viên cho ước chung lớn nhất
        for (int g = maxValue; g >= 1; g--) {
            int demboi = 0;
            // Duyệt tất cả các bội số của g từ g đến maxVal.
            // Ví dụ: nếu g = 4, sẽ duyệt 4, 8, 12, 16, ....
            for (int j = g; j <= maxValue; j += g) {
                if (count[j] == 1) {
                    demboi++;
                }
                if (demboi >= 2) {
                    return g;

                }
            }
        }
        return 1; // Trường hợp không tìm thấy (mảng chỉ có 1 phần tử hoặc tất cả các số nguyên tố
                  // cùng nhau)
    }
}
