package com.prwebsitebackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    public String uploadFile(String path) throws IOException {
        Cloudinary cloudinary = new Cloudinary("cloudinary://632846693984514:CQcLlVBmGan41W4f-1Cc9RIbBuo@rwanda-coding-academy");
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);
        Map params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );
        params1.put("resource_type", "auto");
        Map uploadResult = cloudinary.uploader().upload(path, params1);

        return (String) uploadResult.get("secure_url");
    }

}
