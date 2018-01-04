import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by chenhe on 2017/12/26.
 */

public class test {

    @Test
    public void testPaths() throws IOException {
        String pathStr = "upload-dir";
        Path path = Paths.get(pathStr);
        path.resolve("index.html");
        System.out.println("文件是否存在:"+Files.exists(path));
        if (!Files.exists(path)){
            path = Files.createFile(path);
        }
        System.out.println("文件真实路径:"+path.toFile().getPath());
        System.out.println(path.getRoot());
    }

    /**
     * lambada 测试
     */
    @Test
    public void testLambada(){
        List list = Arrays.asList("1","2","3","4","5")
                .stream()//使用流处理
                .filter(item->Integer.parseInt(item)>2)//过滤掉指定元素
                .collect(Collectors.toList());//剩余的元素转换成list
        list.forEach(item->System.out.println(item));//遍历list并输出
    }
}
