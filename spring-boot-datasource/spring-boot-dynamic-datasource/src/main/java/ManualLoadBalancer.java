import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ManualLoadBalancer {

    private final List<String> nodes = new ArrayList<>();
    private final AtomicInteger position = new AtomicInteger(0);
    private final RestTemplate restTemplate = new RestTemplate();

    public ManualLoadBalancer(List<String> nodes) {
        this.nodes.addAll(nodes);
    }

    private String getNextNode() {
        int index = Math.abs(position.getAndIncrement() % nodes.size());
        return nodes.get(index);
    }

    public String callWithFailover() {
        // 记录第一次尝试的节点，用于判断是否所有节点都试过了
        String firstNode = getNextNode();
        String currentNode = firstNode;
        int attemptCount = 0;

        do {
            try {
                System.out.println("尝试调用节点: " + currentNode);
                String url = "http://" + currentNode + "/api/data";
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                // 如果请求成功，返回结果
                return response.getBody();
            } catch (ResourceAccessException e) {
                // 捕获连接异常，判断为节点不可用
                System.out.println("节点 " + currentNode + " 不可用: " + e.getMessage());
                // 切换到下一个节点继续尝试
                currentNode = getNextNode();
                attemptCount++;
            }
            // 如果已经尝试了所有节点，则停止循环
        } while (!currentNode.equals(firstNode) || attemptCount == 0);
        // 这里简化了，实际应判断尝试次数是否达到节点总数

        throw new RuntimeException("所有节点均不可用");
    }

    public static void main(String[] args) {
        List<String> nodeList = Arrays.asList("192.168.1.10:8080", "192.168.1.11:8080", "192.168.1.12:8080");
        ManualLoadBalancer balancer = new ManualLoadBalancer(nodeList);
        for (int i = 0; i < 1; i++) {
            try {
                String result = balancer.callWithFailover();
                System.out.println("调用成功: " + result);
            } catch (Exception e) {
                System.out.println("最终调用失败: " + e.getMessage());
            }
        }
    }
}