package com.ctx.exchange.controller;


import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.ctx.exchange.model.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = "文件上传")
@Slf4j
public class FileController {

    @Autowired
    private OSS ossClient;

    @Value("${oss.bucket.name:exchange-imgs}")
    private String bucketName;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endPoint ;

    @ApiOperation(value = "文件上传")
    @PostMapping("/image/AliYunImgUpload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "我要上传的文件")
    })
    public R<String> uploadAliYunImg(@RequestParam("file") MultipartFile file) throws IOException {
        /**
         * 3 个参数：
         * 1 bucketName
         * 2 FileName
         * 3 文件的路径
         */
        String fileName = DateUtil.today().replaceAll("-","/")+"/"+file.getOriginalFilename() ;
        ossClient.putObject(bucketName, fileName, file.getInputStream()); // 文件上传

        // 上传成功后，路径为https://exchange-imgs.oss-cn-beijing.aliyuncs.com/2020/10/10/1588586643_meitu_1.jpg
        String fileUrl = "https://" + bucketName+ "."+ endPoint +"/"+ fileName;
        log.info("上传的路径为:"+fileUrl);
        return R.ok(fileUrl) ;
    }
}
