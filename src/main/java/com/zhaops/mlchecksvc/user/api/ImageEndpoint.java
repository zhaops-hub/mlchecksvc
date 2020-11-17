package com.zhaops.mlchecksvc.user.api;


import com.zhaops.mlchecksvc.user.common.ImgBase64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SoYuan
 * 自己测试的controller
 */
@RestController
@RequestMapping("/images")
public class ImageEndpoint {
    @RequestMapping(method = RequestMethod.GET)
    public List<FaceModel> getImages() {
        List<FaceModel> list = new ArrayList<>();
        String result = ImgBase64.getImgStr("/opt/img1.jpg");
        FaceModel face = new FaceModel();
        FaceUrls urls = new FaceUrls();
        urls.setFaceUrl(result);
        urls.setDefault(true);
        face.setFaceUrls(urls);
        face.setName("测试人脸");
        list.add(face);

        result = ImgBase64.getImgStr("/opt/img2.jpg");
        face = new FaceModel();
        urls = new FaceUrls();
        urls.setFaceUrl(result);
        urls.setDefault(true);
        face.setFaceUrls(urls);
        face.setName("赵培胜");
        list.add(face);

        return list;
    }
}


class FaceModel {
    private FaceUrls faceUrls;
    private String name;
    private String attribute;
    private int id;

    public FaceUrls getFaceUrls() {
        return faceUrls;
    }

    public void setFaceUrls(FaceUrls faceUrls) {
        this.faceUrls = faceUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class FaceUrls {
    private String faceUrl;
    private boolean isDefault;

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}