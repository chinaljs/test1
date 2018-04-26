package com.huarui.action;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huarui.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/25.
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 360)
public class TestAction {

    private Logger log = LoggerFactory.getLogger(TestAction.class);
    @Autowired
    private FastFileStorageClient fsc;
    @Value("${fdfs.prefixpath}")
    private String prefixpath;
    @Autowired
    private TestService testService;

    @RequestMapping("/test01/{name}")
    public String test01(@PathVariable String name){
        log.debug("action test01 start");
        if("admin".equals(name)==true){
            int i =1/0;
        }
        testService.test01();
        return name + ":hello";
    }

    @RequestMapping("/upload")
    public Map<String,Object> upload(MultipartFile fileup) throws Exception{
        Map<String,Object> map = new HashMap<>();
        //fileup.getInputStream()
        //fileup.getSize()
        //1 获得扩张名
        String exname = fileup.getOriginalFilename().substring(fileup.getOriginalFilename().lastIndexOf(".")+1);
        //2 参数1：上传文件的输入流
        //参数2：文件的大小
        //参数4：文件的附件信息
        //返回值：就是 整齐上传之后 在服务其上的地址
        StorePath storePath = fsc.uploadFile(fileup.getInputStream(),
                fileup.getSize(),exname,null);
        String url = prefixpath+storePath.getFullPath();
        System.out.println(storePath.getFullPath());
        System.out.println(url);
        map.put("url",url);
        return map;
    }

    @ExceptionHandler
    public String doException(Exception ex){
        log.error("error sorry",ex);
        return "error!!!";
    }
}
