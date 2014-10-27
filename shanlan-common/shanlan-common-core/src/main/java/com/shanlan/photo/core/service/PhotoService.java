package com.shanlan.photo.core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.PhotoCollection;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import org.apache.commons.io.FilenameUtils;
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
     * @param destWidth
     * @param destHeight
     * @return
     * @throws Exception
     */
    public static boolean handleAvatar(String srcImageFilePath, int x, int y, int destWidth, int destHeight) throws Exception {

        //先根据裁剪规格（destWidth，destHeight）对原图进行裁剪
        String cutImageFilePath = ImageUploadUtil.cutImage(srcImageFilePath, x, y, destWidth, destHeight);

        String extensionName = FilenameUtils.getExtension(srcImageFilePath);
        //再在裁剪图的基础等比例压缩三张图，分别为200*200,120*120,30*30

        String newFilePath200 = ImageUploadUtil.appendImageSizePostfix(srcImageFilePath,
                extensionName, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_200, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_200);
        ImageUploadUtil.compressImageConstrain(cutImageFilePath, newFilePath200, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_200, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_200, 1f);


        String newFilePath120 = ImageUploadUtil.appendImageSizePostfix(srcImageFilePath,
                extensionName, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120);
        ImageUploadUtil.compressImageConstrain(cutImageFilePath, newFilePath120, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_120, 1f);


        String newFilePath32 = ImageUploadUtil.appendImageSizePostfix(srcImageFilePath,
                extensionName, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_30, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_30);
        ImageUploadUtil.compressImageConstrain(cutImageFilePath, newFilePath32, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_30, ImageUploadUtil.IMAGE_COMPRESS_STANDARD_30, 1f);

        //删除裁剪的临时文件
        File cutImageFile = new File(cutImageFilePath);
        cutImageFile.delete();

        return true;
    }

}
