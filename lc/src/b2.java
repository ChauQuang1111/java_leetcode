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
