package cn.alex.mybatis.controller;

import cn.alex.mybatis.domain.ResearchTopic;
import cn.alex.mybatis.service.ResearchTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WCY on 2021/10/22
 */
@RestController
public class ResearchTopicController {

    @Autowired
    private ResearchTopicService researchTopicService;

    @PostMapping("insertTopic")
    public void insertTopic(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        // show VARIABLES like '%max_allowed_packet%';
        ResearchTopic researchTopic = new ResearchTopic();
        byte[] bs = null;
        if (multipartFile != null) {
            bs = multipartFile.getBytes();
        }
        researchTopic.setSort(1);
        researchTopic.setIsvalid(1);
        researchTopic.setPdfcontent(bs);
        researchTopic.setCreatetime(new Date());
        researchTopicService.insert(researchTopic);
    }

    @GetMapping("selectTopic")
    public Object selectTopic(@RequestParam("id") long id) {
        ResearchTopic researchTopic = researchTopicService.selectByPrimaryKey(id);
        byte[] bs = researchTopic.getPdfcontent();

        try (FileOutputStream fileOutputStream = new FileOutputStream("d:\\img.jpg")) {
            fileOutputStream.write(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pdfContent = new String(bs, StandardCharsets.ISO_8859_1);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pdfContent", pdfContent);
        return resultMap;
    }
}
