package com.shanlan.photo.core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.util.FileUtil;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.PhotoCollection;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhotoService {

    private static final Logger logger = LoggerFactory
            .getLogger(PhotoService.class);

    public static List<Photo> getPhotos(Integer photoCollectionId)
            throws Exception {
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
     * （1）先根据前台传入的裁剪规格（destWidth，destHeight）对原图进行裁剪
     * （2）再在裁剪图的基础等比例压缩两张图，分别为200*200，120*120,32*32 （3）删除输入的临时文件
     *
     * @param srcImageFilePath
     * @param x
     * @param y
     * @param destWidth
     * @param destHeight
     * @return
     * @throws Exception
     */
    public static String handleAvatar(String srcImageFilePath, int x, int y,
                                      int destWidth, int destHeight) throws Exception {

        String extensionName = FilenameUtils.getExtension(srcImageFilePath);

        //1. 先根据前台传入的裁剪规格（destWidth，destHeight）对原图进行裁剪
        //1.1 新的文件名会在原文件名的后面加上一个图像大小占位符，比如原文件名为fff.jpg则新文件名为fffX_X.jpg
        String cutImageFilePath = ImageUploadUtil.appendImageSizePostfix(srcImageFilePath,
                extensionName, destWidth, destHeight);

        //1.2 先根据前台传入的裁剪规格（destWidth，destHeight）对原图进行裁剪
        ImageUploadUtil.cutImage(srcImageFilePath, cutImageFilePath, x,
                y, destWidth, destHeight);

        //2. 在裁剪图的基础等比例压缩三张图，分别为200*200,120*120,30*30
        List<Integer> compressStandardList = new ArrayList<Integer>();
        compressStandardList.add(ImageUploadUtil.IMAGE_AVATAR_COMPRESS_STANDARD_200);
        compressStandardList.add(ImageUploadUtil.IMAGE_AVATAR_COMPRESS_STANDARD_120);
        compressStandardList.add(ImageUploadUtil.IMAGE_AVATAR_COMPRESS_STANDARD_30);

        for (Integer compressStandard : compressStandardList) {
            String newFilePath = ImageUploadUtil.appendImageSizePostfix(
                    srcImageFilePath, extensionName, compressStandard,
                    compressStandard);
            ImageUploadUtil.compressWidthHeightImageFile(cutImageFilePath,
                    newFilePath, compressStandard, compressStandard, 1f);

        }

        //3.删除裁剪的临时文件
        File cutImageFile = new File(cutImageFilePath);
        cutImageFile.delete();

        // 存储到数据库中的路径需要加上一个图片裁剪规格占位符（_X_X）,并替换掉存储前缀，
        // 这样前台取图里就可以通过替换占位符取到相应的图片，后台也省去了存储多种规格图片路径的麻烦
        String storeFilePath = ImageUploadUtil.appendImageSizePlaceHolder(
                srcImageFilePath, extensionName).replace(
                ImageUploadUtil.getImageBasePath(), "");

        return storeFilePath;
    }


    /**
     * 根据文件大小等比例压缩图片文件<br>
     *
     * @param originalStorePath 要进行压缩的文件完整路径
     * @return 返回压缩后的文件的全路径
     */
    public static void compressImageConstrainByFileSize(String originalStorePath, long fileSize)
            throws Exception {

        String extensionName = FilenameUtils.getExtension(originalStorePath);
        String compressFilePath = ImageUploadUtil.appendImageCompressPostfix(originalStorePath, extensionName);
        float compressQuality = 1f;
        if (fileSize <= ConstantNumber.FILE_SIZE_M * 1) {//如果文件小于1M,不压缩，复制一份原文件
            File originalFile = new File(originalStorePath);
            File newFile = new File(compressFilePath);
            FileUtils.copyFile(originalFile, newFile);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 2 && fileSize >= ConstantNumber.FILE_SIZE_M * 1) {//如果文件大于1M,小于2M，
            //将文件按90%比例压缩,如果图片的原来的宽、高分别为1000*800,则压缩后为(1000*0.9)*(800*0.9)
            Float compressRate = 95f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, (float) (compressRate * 0.01), compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 3 && fileSize >= ConstantNumber.FILE_SIZE_M * 2) {
            //将文件按80%比例压缩
            Float compressRate = 80f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, (float) (compressRate * 0.01), compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 4 && fileSize >= ConstantNumber.FILE_SIZE_M * 3) {
            //将文件按70%比例压缩
            Float compressRate = 70f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, (float) (compressRate * 0.01), compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 5 && fileSize >= ConstantNumber.FILE_SIZE_M * 4) {
            //将文件按60%比例压缩
            Float compressRate = 60f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, (float) (compressRate * 0.01), compressQuality);
        }

    }


    /**
     * 根据文件大小等比例压缩图片文件,只压缩图片质量不压缩图片大小<br>
     *
     * @param originalStorePath 要进行压缩的文件完整路径
     * @return 返回压缩后的文件的全路径
     */
    public static void compressImageByFileSize(String originalStorePath, long fileSize)
            throws Exception {

        String extensionName = FilenameUtils.getExtension(originalStorePath);
        String compressFilePath = ImageUploadUtil.appendImageCompressPostfix(originalStorePath, extensionName);
        float compressRate = 1.0f;
        if (fileSize <= ConstantNumber.FILE_SIZE_M * 1) {//如果文件小于1M,不压缩，复制一份原文件
            File originalFile = new File(originalStorePath);
            File newFile = new File(compressFilePath);
            FileUtils.copyFile(originalFile, newFile);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 2 && fileSize >= ConstantNumber.FILE_SIZE_M * 1) {//如果文件大于1M,小于2M，
            //将文件按90%比例压缩
            float compressQuality = 0.9f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 3 && fileSize >= ConstantNumber.FILE_SIZE_M * 2) {
            //将文件按80%比例压缩
            float compressQuality = 0.8f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 4 && fileSize >= ConstantNumber.FILE_SIZE_M * 3) {
            //将文件按70%比例压缩
            float compressQuality = 0.7f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 5 && fileSize >= ConstantNumber.FILE_SIZE_M * 4) {
            //将文件按60%比例压缩
            float compressQuality = 0.6f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 6 && fileSize >= ConstantNumber.FILE_SIZE_M * 5) {
            //将文件按60%比例压缩
            float compressQuality = 0.5f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 7 && fileSize >= ConstantNumber.FILE_SIZE_M * 6) {
            //将文件按60%比例压缩
            float compressQuality = 0.45f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 8 && fileSize >= ConstantNumber.FILE_SIZE_M * 7) {
            //将文件按60%比例压缩
            float compressQuality = 0.4f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 9 && fileSize >= ConstantNumber.FILE_SIZE_M * 8) {
            //将文件按60%比例压缩
            float compressQuality = 0.35f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        } else if (fileSize <= ConstantNumber.FILE_SIZE_M * 10 && fileSize >= ConstantNumber.FILE_SIZE_M * 9) {
            //将文件按60%比例压缩
            float compressQuality = 0.3f;
            ImageUploadUtil.compressImageConstrain(originalStorePath, compressFilePath, compressRate, compressQuality);
        }

    }


    /**
     * 创建一个正方形的缩略图
     *
     * @param srcImageFilePath  原始图片完整存储路径
     * @param thumbnailFilePath 缩略图完整存储路径
     * @param destLength        正方形的缩略图目标边长
     */
    public static void createSquareThumbnail(String srcImageFilePath, String thumbnailFilePath, int destLength) throws Exception {
        //1.先将原图裁剪成一个正方形
        ImageUploadUtil.cutSquareImage(srcImageFilePath, thumbnailFilePath);

        //2.将这个正方形压缩成目标长度的正方形缩略图
        ImageUploadUtil.compressWidthHeightImageFile(thumbnailFilePath, thumbnailFilePath, destLength, destLength, 1f);
    }

}
