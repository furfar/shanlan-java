package com.shanlan.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.shanlan.common.constant.ConstantPunctuation;
import com.shanlan.common.exception.business.ParameterInvalidException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by albertliu on 14/10/26.
 */
public class ImageUploadUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(ImageUploadUtil.class);

    private static final String IMAGE_SIZE_PLACEHOLDER = "X_X";


    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";

    public static final String CONTENT_TYPE_IMAGE_GIF = "image/gif";

    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";

    public static final String CONTENT_TYPE_IMAGE_BMP = "image/bmp";

    public static final String IMAGE_EXTENSION_NAME_JPG = ".jpg";

    /**
     * 开发环境图片存储基础路径
     */
    public static final String IMAGE_BASE_PATH_DEVELOP = "/Users/albertliu/images/";

    /**
     * 生产环境图片存储基础路径
     */
    public static final String IMAGE_BASE_PATH_PRODUCT = "/opt/images/";

    /**
     * 图片临时存储文件路径
     */
    public static final String IMAGE_TYPE_TMP = "tmp";

    /**
     * 图片类型——头像
     */
    public static final String IMAGE_TYPE_AVATAR = "avatar";

    /**
     * 图片类型——照片
     */
    public static final String IMAGE_TYPE_TRADE_PHOTO = "trade_photo";


    /**
     * 图片类型——自己上传照片
     */
    public static final String IMAGE_TYPE_SELF_UPLOAD = "self_upload";


    //    /**
//     * 开发环境图片存储根路径
//     */
//    public static final String IMAGE_ROOT_PATH_DEVELOP = "/Users/albertliu/";
//
//    /**
//     * 生产环境图片存储根路径
//     */
//    public static final String IMAGE_ROOT_PATH_PRODUCT = "/opt/";

//    public static final String IMAGE_DEVELOP_AVATAR = IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_AVATAR + "/";
//
//    public static final String IMAGE_DEVELOP_PHOTO = IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_PHOTO + "/";
//
//    public static final String IMAGE_PRODUCT_AVATAR = IMAGE_BASE_PATH_PRODUCT + IMAGE_TYPE_AVATAR + "/";
//
//    public static final String IMAGE_PRODUCT_PHOTO = IMAGE_BASE_PATH_PRODUCT + IMAGE_TYPE_PHOTO + "/";


    /**
     * @param srcImageFile
     * @param x
     * @param y
     * @param destWidth
     * @param destHeight
     * @param srcShowWidth
     * @param srcShowHeight
     * @return
     */
    public static String cutImage(String srcImageFile, int x, int y,
                                  int destWidth, int destHeight, int srcShowWidth, int srcShowHeight) {
        try {
            Image cutImage;
            ImageFilter cropFilter;
            // 读取源图像
            File srcFile = new File(srcImageFile);

            BufferedImage sourceBufferedImage = ImageIO.read(srcFile);
            // 前端页面显示的并非原图大小，而是经过了一定的压缩，所以不能使用原图的宽高来进行裁剪，需要使用前端显示的图片宽高
            int srcWidth = sourceBufferedImage.getWidth(); // 源图宽度
            int srcHeight = sourceBufferedImage.getHeight(); // 源图高度
            if (srcShowWidth == 0)
                srcShowWidth = srcWidth;
            if (srcShowHeight == 0)
                srcShowHeight = srcHeight;

            if (srcShowWidth >= destWidth && srcShowHeight >= destHeight) {
                Image compressedImage = sourceBufferedImage.getScaledInstance(srcShowWidth,
                        srcShowHeight, Image.SCALE_DEFAULT);// 获取缩放后的图片大小
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                cutImage = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(compressedImage.getSource(), cropFilter));
                BufferedImage cutBufferedImage = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics graphics = cutBufferedImage.getGraphics();
                graphics.drawImage(cutImage, 0, 0, null); // 绘制截取后的图
                graphics.dispose();

                String extensionName = FilenameUtils.getExtension(srcImageFile);

                // 新的文件名会在原文件名的后面加上一个图像大小占位符，比如原文件名为fff.jpg则新文件名为fffX_X.jpg
                String newImageFile = appendImageSizePlaceHolder(srcImageFile,
                        extensionName);

                File destImageFile = new File(newImageFile);

                // 输出为文件
                ImageIO.write(cutBufferedImage, extensionName, destImageFile);

                // 删除原临时文件
                srcFile.delete();

                return newImageFile;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 在输入文件名后面加上一个图像大小占位符，比如原文件名为fff.jpg则新文件名为fffX_X.jpg
     *
     * @param srcImageFile
     * @param extensionName
     * @return
     */
    private static String appendImageSizePlaceHolder(String srcImageFile,
                                                     String extensionName) {
        String periodAndExtensionName = ConstantPunctuation.PERIOD
                + extensionName;
        String newImageFile = srcImageFile.replace(periodAndExtensionName, "")
                + IMAGE_SIZE_PLACEHOLDER + periodAndExtensionName;
        return newImageFile;
    }

    public static boolean validateImageType(String contentType) throws ParameterInvalidException {
        if (contentType.equals(CONTENT_TYPE_IMAGE_JPEG) || contentType.equals(CONTENT_TYPE_IMAGE_GIF)
                || contentType.equals(CONTENT_TYPE_IMAGE_BMP) || contentType.equals(CONTENT_TYPE_IMAGE_PNG)) {
            return true;
        }
        throw new ParameterInvalidException("目前暂不支持'" + contentType + "'这种文件的上传");
    }


    public static String saveAvatarImage(FileItem fileItem) {
        String originalFileName = fileItem.getName();//获取上传文件自己的原始文件名
        if (originalFileName == null || "".equals(originalFileName.trim())) {//如果是tmp文件夹中的文件
            return "";
        }

        File avatarImageFilePath = new File(getImageBasePath() + IMAGE_TYPE_AVATAR);
        if (!avatarImageFilePath.exists()) {
            avatarImageFilePath.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() + IMAGE_EXTENSION_NAME_JPG;
        String storePath = IMAGE_TYPE_AVATAR + "/" + newFileName;
        File f2 = new File(avatarImageFilePath, newFileName);
        try {
            fileItem.write(f2);
            return storePath;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    public static String saveAvatarImage(String originalFileName, byte[] imageBytes) {
        String filePath = getImageBasePath() + IMAGE_TYPE_AVATAR + "/" + FileUploadUtil.getYearMonthDayPath();
        return saveImage(filePath, originalFileName, imageBytes);
    }


    private static String saveImage(String filePath, String originalFileName, byte[] imageBytes) {
        if (originalFileName == null || "".equals(originalFileName.trim())) {//如果是tmp文件夹中的文件
            return "";
        }
        String imageName = UUID.randomUUID().toString() + ConstantPunctuation.PERIOD + FilenameUtils.getExtension(originalFileName);
        return saveFile(filePath, imageName, imageBytes);
    }


    /**
     * @param tempFileThreshold 设定阀值1M，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
     *                          利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
     * @param uploadThreshold   最大支持多少M文件上传
     * @return
     */
    public static ServletFileUpload getServletImageUpload(int tempFileThreshold, int uploadThreshold) {

        List<FileItem> fileItemList = new ArrayList<FileItem>();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //设定阀值1M，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
        //利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
        diskFileItemFactory.setSizeThreshold(tempFileThreshold * 1024 * 1024);


        File tempFile = new File(IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_TMP);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }

        diskFileItemFactory.setRepository(tempFile);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        servletFileUpload.setFileSizeMax(1024 * 1024 * uploadThreshold);//限制最大上传文件的大小

        return servletFileUpload;
    }


    public static String getImageBasePath() {
        return RunningMode.isDevelop() ? IMAGE_BASE_PATH_DEVELOP : IMAGE_BASE_PATH_PRODUCT;
    }


    private static String saveFile(String filePath, String fileName, byte[] bytes) {

        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdir();
        }
        File file = new File(path, fileName);
        try {
            FileUtils.writeByteArrayToFile(file, bytes);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "";
        }
        String relativePath = (filePath + fileName).replace(getImageBasePath(), "");
        return relativePath;

    }

}
