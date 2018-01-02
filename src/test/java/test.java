import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by chenhe on 2017/12/26.
 */

public class test {

    @Test
    public void test() throws IOException {
        String s = "chenhe";
        String s2 = "hello" + "world";
        String s3 = new String("你好");
        Scanner scanner = new Scanner(System.in);
        System.out.println("waitting");
        scanner.nextInt();
        System.out.println("end!");
    }
}
