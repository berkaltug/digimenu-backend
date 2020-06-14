package com.digimenu.main.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.digimenu.main.service.CloudinaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

   @Autowired
   private Cloudinary cloudinary;
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            File uploading = convertMultiPartToFile(file);
            Map uploadResult= cloudinary.uploader().upload(uploading, ObjectUtils.emptyMap());
            return uploadResult.get("public_id").toString();
        }catch (IOException e){
            logger.warn(e.toString());
            return null;
        }catch(Exception e){
            logger.warn(e.toString());
            return null;
        }
    }

    @Override
    public String updateFile(MultipartFile newFile,String oldPublicId){
        try{
            if(oldPublicId!=null){
                cloudinary.uploader().destroy(oldPublicId,ObjectUtils.emptyMap());
            }
            File updating = convertMultiPartToFile(newFile);
            Map uploadResult= cloudinary.uploader().upload(updating, ObjectUtils.emptyMap());
            return uploadResult.get("public_id").toString();
        }catch (IOException e){
            logger.warn(oldPublicId + " id'si ile ilgili şu exception" + e.toString());
            return null;
        }catch(Exception e){
            logger.warn(e.toString());
            return null;
        }
    }

    @Override
    public void deleteFile(String publicId) {
        try{
            cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
        }catch (IOException e){
           logger.warn(publicId + "id'si ile ilgili şu exception " + e);
        }catch (Exception e){
            logger.warn(e.toString());
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
