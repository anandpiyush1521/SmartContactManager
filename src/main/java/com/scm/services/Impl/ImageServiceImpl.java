package com.scm.services.Impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helpers.AppConstants;
import com.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
        //code to upload the imahe on server
        // return image url
 

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];

            contactImage.getInputStream().read(data);

            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename
            ));

            return this.getUrlFromPublicId(filename);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
            .url()
            .transformation(
                new Transformation<>()
                    .width(AppConstants.CONTACT_IMAGE_WIDTH)
                    .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                    .crop(AppConstants.CONTACT_IMAGE_CROP)
            )
            .generate(publicId);
    }

    

}
