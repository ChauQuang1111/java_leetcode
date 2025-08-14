
// 14/08/2025
// 2264. Largest 3-Same-Digit Number in String
import java.util.*;

public class b4 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.nextLine();
        String res = largestGoodInteger(s);
        System.out.println(res);
        sc.close();
    }

    public static String largestGoodInteger(String num) {
        {
            String[] threeSameDigit = { "999", "888", "777", "666", "555", "444", "333", "222", "111", "000" };
            for (String i : threeSameDigit) {
                if (num.contains(i)) {
                    return i;
                }

            }
            return "";

        }
    }
}
