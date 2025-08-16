
// 1323. Maximum 69 Number(16/08/2025)
import java.util.*;

public class b7 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int res = maximum69Number(n);
        System.out.println(res);

    }

    public static int maximum69Number(int num) {
        char[] s = String.valueOf(num).toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '6') {
                s[i] = '9';
                break;
            }

        }
        return Integer.parseInt(new String(s));
    }

}