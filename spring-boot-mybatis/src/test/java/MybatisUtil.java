import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by WCY on 2022/9/1
 */
public class MybatisUtil {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    static {
        MybatisUtil.batchHandler(Arrays.asList("1", 2, "2"), System.out::println);
    }

    // 批量处理
    public static <T> void batchInsert(List<T> dataList, Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        batchHandler(dataList, consumer);
    }

    public static <T> void batchDelete(List<T> dataList, Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        batchHandler(dataList, consumer);
    }

    public static <T> void batchUpdate(List<T> dataList, Consumer<List<T>> consumer) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        batchHandler(dataList, consumer);
    }

    public static <T> void batchHandler(List<T> dataList, Consumer<List<T>> consumer) {
        List<T> handlerList = new ArrayList<>();
        final int SIZE = 1000;
        if (dataList.size() < SIZE) {
            consumer.accept(dataList);
        } else {
            for (T data : dataList) {
                handlerList.add(data);
                if (handlerList.size() == SIZE) {
                    consumer.accept(handlerList);
                    handlerList.clear();
                }
            }
            if (handlerList.size() > 0) {
                consumer.accept(handlerList);
            }
        }
    }

}
