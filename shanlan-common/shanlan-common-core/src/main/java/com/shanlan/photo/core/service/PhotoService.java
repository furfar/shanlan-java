package com.shanlan.photo.core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.PhotoCollection;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    public static List<Photo> getPhotos(Integer photoCollectionId) throws Exception {
        List<Photo> photos = new ArrayList<Photo>();

        if (photoCollectionId == null || photoCollectionId <= 0) {
            throw new IllegalArgumentException("photoCollectionId must > 0");
        }

        PhotoCollection photoCollection = PhotoCollection.get(
                PhotoCollection.class, photoCollectionId);

        if (photoCollection != null) {
            List<RePhotoCollectionPhoto> rePhotoCollectionPhotos = RePhotoCollectionPhoto
                    .findByPhotoCollectionId(photoCollection.getId());

            for (RePhotoCollectionPhoto rePhotoCollectionPhoto : rePhotoCollectionPhotos) {

                RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn
                        .get(RePhotoUserOwn.class,
                                rePhotoCollectionPhoto.getUpoId());

                if (rePhotoUserOwn != null) {
                    Photo photo = Photo.get(Photo.class,
                            rePhotoUserOwn.getPhotoId());
                    photos.add(photo);
                }
            }

        }
        return photos;
    }


    /**
     * 处理头像
     * （1）先根据原图裁剪一个200*200的图
     * （2）再在200*200的图的基础等比例压缩两张图，分别为120*120,32*32
     * （3）删除输入的临时文件
     *
     * @param srcImageFilePath
     * @param x
     * @param y
     * @param srcShowWidth
     * @param srcShowHeight
     * @return
     * @throws Exception
     */
    public static boolean handleAvatar(String srcImageFilePath, int x, int y, int srcShowWidth, int srcShowHeight) throws Exception {


        Integer destWidth = ImageUploadUtil.IMAGE_CUT_STANDARD_200;
        Integer destHeight = ImageUploadUtil.IMAGE_CUT_STANDARD_200;

        //先根据原图裁剪一个200*200的图
        String cutImageFilePath = ImageUploadUtil.cutImage(srcImageFilePath, x, y, destWidth, destHeight, srcShowWidth, srcShowHeight);

        //再在200*200的图的基础等比例压缩两张图，分别为120*120,32*32
        ImageUploadUtil.compressImage(cutImageFilePath, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120);

        ImageUploadUtil.compressImage(cutImageFilePath, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_32, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_32);

        //删除输入的临时文件
        File srcImageFile = new File(srcImageFilePath);

        srcImageFile.delete();

        return true;
    }

}
