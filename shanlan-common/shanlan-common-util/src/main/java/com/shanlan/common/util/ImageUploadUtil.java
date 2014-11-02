package com.shanlan.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlan.common.constant.ConstantPunctuation;
import com.shanlan.common.exception.business.ParameterInvalidException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Created by albertliu on 14/10/26.
 */
public class ImageUploadUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(ImageUploadUtil.class);

    public static final String IMAGE_AVATAR_SIZE_PLACEHOLDER = "_X_X";

    /**
     * 图片后缀，按X%比例压缩过的图片,比如_COMPRESS_60表示按60%比例压缩过的图片
     */
    public static final String IMAGE_PHOTO_FILE_POSTFIX_COMPRESS = "_COMPRESS_";

    /**
     * 图片后缀，按固定宽、高比例压缩过的缩略图,比如_THUMBNAIL_500_500表示按宽、高500*500比例压缩过的图片
     */
    public static final String IMAGE_PHOTO_FILE_POSTFIX_THUMBNAIL = "_THUMBNAIL";

    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";

    public static final String CONTENT_TYPE_IMAGE_GIF = "image/gif";

    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";

    public static final String CONTENT_TYPE_IMAGE_BMP = "image/bmp";

    public static Integer IMAGE_AVATAR_COMPRESS_STANDARD_200 = 200;

    public static Integer IMAGE_AVATAR_COMPRESS_STANDARD_120 = 120;

    public static Integer IMAGE_AVATAR_COMPRESS_STANDARD_30 = 30;

    /**
     * 开发环境图片存储基础路径
     */
    public static final String IMAGE_BASE_PATH_DEVELOP = "/Users/albertliu";

    /**
     * 生产环境图片存储基础路径
     */
    public static final String IMAGE_BASE_PATH_PRODUCT = "/opt";

    /**
     * 图片临时存储文件路径
     */
    public static final String IMAGE_TYPE_PATH_TMP = "/img/tmp/";

    /**
     * 图片类型——头像
     */
    public static final String IMAGE_TYPE_PATH_AVATAR = "/img/avatar/";

    /**
     * 图片类型——照片
     */
    public static final String IMAGE_TYPE_PATH_TRADE_PHOTO = "/img/tradePhoto/";

    /**
     * 图片类型——自己上传照片
     */
    public static final String IMAGE_TYPE_PATH_SELF_UPLOAD = "/img/selfUpload/";

    // /**
    // * 开发环境图片存储根路径
    // */
    // public static final String IMAGE_ROOT_PATH_DEVELOP = "/Users/albertliu/";
    //
    // /**
    // * 生产环境图片存储根路径
    // */
    // public static final String IMAGE_ROOT_PATH_PRODUCT = "/opt/";

    // public static final String IMAGE_DEVELOP_AVATAR = IMAGE_BASE_PATH_DEVELOP
    // + IMAGE_TYPE_PATH_AVATAR + "/";
    //
    // public static final String IMAGE_DEVELOP_PHOTO = IMAGE_BASE_PATH_DEVELOP
    // + IMAGE_TYPE_PHOTO + "/";
    //
    // public static final String IMAGE_PRODUCT_AVATAR = IMAGE_BASE_PATH_PRODUCT
    // + IMAGE_TYPE_PATH_AVATAR + "/";
    //
    // public static final String IMAGE_PRODUCT_PHOTO = IMAGE_BASE_PATH_PRODUCT
    // + IMAGE_TYPE_PHOTO + "/";

    /**
     * @param srcImageFilePath 被裁剪源图片的存储地址
     * @param cutImageFilePath 裁剪完后图片存储地址
     * @param x                裁剪位置的x轴
     * @param y                裁剪位置的y轴
     * @param destWidth        目标图片的宽度
     * @param destHeight       目标图片的高度
     * @return
     */
    public static void cutImage(String srcImageFilePath, String cutImageFilePath, int x, int y,
                                int destWidth, int destHeight) {
        try {
            Image cutImage;
            ImageFilter cropFilter;
            // 读取源图像
            File srcFile = new File(srcImageFilePath);

            BufferedImage sourceBufferedImage = ImageIO.read(srcFile);
            int srcWidth = sourceBufferedImage.getWidth(); // 源图宽度
            int srcHeight = sourceBufferedImage.getHeight(); // 源图高度

            Image compressedImage = sourceBufferedImage.getScaledInstance(
                    srcWidth, srcHeight, Image.SCALE_DEFAULT);
            cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
            cutImage = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(compressedImage.getSource(),
                            cropFilter)
            );
            BufferedImage cutBufferedImage = new BufferedImage(destWidth,
                    destHeight, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = cutBufferedImage.getGraphics();
            graphics.drawImage(cutImage, 0, 0, null); // 绘制截取后的图
            graphics.dispose();

            File destImageFile = new File(cutImageFilePath);

            // 输出为文件
            ImageIO.write(cutBufferedImage, FilenameUtils.getExtension(srcImageFilePath), destImageFile);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 将原图片裁剪成一个正文开图片
     *
     * @param srcImageFilePath 被裁剪源图片的存储地址
     * @param cutImageFilePath 裁剪完后图片存储地址
     * @return
     */
    public static void cutSquareImage(String srcImageFilePath, String cutImageFilePath) {
        try {
            Image cutImage;
            ImageFilter cropFilter;
            // 读取源图像
            File srcFile = new File(srcImageFilePath);

            BufferedImage sourceBufferedImage = ImageIO.read(srcFile);
            int srcWidth = sourceBufferedImage.getWidth(); // 源图宽度
            int srcHeight = sourceBufferedImage.getHeight(); // 源图高度

            //正方形图片的边长
            int length = srcWidth >= srcHeight ? srcHeight : srcWidth;

            Image compressedImage = sourceBufferedImage.getScaledInstance(
                    srcWidth, srcHeight, Image.SCALE_DEFAULT);
            cropFilter = new CropImageFilter(0, 0, length, length);
            cutImage = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(compressedImage.getSource(),
                            cropFilter)
            );
            BufferedImage cutBufferedImage = new BufferedImage(length,
                    length, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = cutBufferedImage.getGraphics();
            graphics.drawImage(cutImage, 0, 0, null); // 绘制截取后的图
            graphics.dispose();

            File destImageFile = new File(cutImageFilePath);

            // 输出为文件
            ImageIO.write(cutBufferedImage, FilenameUtils.getExtension(srcImageFilePath), destImageFile);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 在输入文件名后面加上一个图像大小后缀，比如原文件名为fff.jpg规格为200，则新文件名为fff200_200.jpg
     *
     * @param srcImageFile
     * @param extensionName
     * @return
     */
    public static String appendImageSizePostfix(String srcImageFile,
                                                String extensionName, Integer destWidth, Integer destHeight) {
        String periodAndExtensionName = ConstantPunctuation.PERIOD
                + extensionName;
        String newImageFile = srcImageFile.replace(periodAndExtensionName, "")
                + (ConstantPunctuation.UNDERLINE + destWidth
                + ConstantPunctuation.UNDERLINE + destHeight)
                + periodAndExtensionName;
        return newImageFile;
    }


    /**
     * 添加图片压缩后缀，包括压缩比例
     *
     * @param srcImageFile
     * @param extensionName
     * @param compressRate  压缩比例，如果压缩比例为80%,则填写80
     * @return
     */
    public static String appendImageCompressPostfix(String srcImageFile,
                                                    String extensionName, String compressRate) {
        String periodAndExtensionName = ConstantPunctuation.PERIOD
                + extensionName;
        String newImageFile = srcImageFile.replace(periodAndExtensionName, "")
                + (IMAGE_PHOTO_FILE_POSTFIX_COMPRESS + compressRate)
                + periodAndExtensionName;
        return newImageFile;
    }


    /**
     * 添加图片压缩后缀，不包括压缩比例
     *
     * @param srcImageFile
     * @param extensionName
     * @return
     */
    public static String appendImageCompressPostfix(String srcImageFile,
                                                    String extensionName) {
        return appendImageCompressPostfix(srcImageFile, extensionName, "");
    }


    /**
     * @param srcImageFile
     * @param extensionName
     * @param destWidth     缩略图的宽
     * @param destHeight    缩略图的高
     * @return
     */
    public static String appendImageThumbnailPostfix(String srcImageFile,
                                                     String extensionName, Integer destWidth, Integer destHeight) {
        String periodAndExtensionName = ConstantPunctuation.PERIOD
                + extensionName;
        String newImageFile = srcImageFile.replace(periodAndExtensionName, "")
                + (IMAGE_PHOTO_FILE_POSTFIX_THUMBNAIL
                + ConstantPunctuation.UNDERLINE + destWidth + ConstantPunctuation.UNDERLINE + destHeight)
                + periodAndExtensionName;
        return newImageFile;
    }


    /**
     * 在输入文件名后面加上一个图像大小占位符，比如原文件名为fff.jpg则新文件名为fffX_X.jpg
     *
     * @param srcImageFile
     * @param extensionName
     * @return
     */
    public static String appendImageSizePlaceHolder(String srcImageFile,
                                                    String extensionName) {
        String periodAndExtensionName = ConstantPunctuation.PERIOD
                + extensionName;
        String newImageFile = srcImageFile.replace(periodAndExtensionName, "")
                + IMAGE_AVATAR_SIZE_PLACEHOLDER + periodAndExtensionName;
        return newImageFile;
    }


    public static String removeImageSizePlaceHolder(String srcImageFilePath) {
        return srcImageFilePath != null ? srcImageFilePath.replace(ImageUploadUtil.IMAGE_AVATAR_SIZE_PLACEHOLDER, "") : "";
    }


    public static String removeImageBasePath(String srcImageFilePath) {
        return srcImageFilePath != null ? srcImageFilePath.replace(getImageBasePath(), "") : "";
    }


    public static boolean validateImageType(String contentType)
            throws ParameterInvalidException {
        if (contentType.equals(CONTENT_TYPE_IMAGE_JPEG)
                || contentType.equals(CONTENT_TYPE_IMAGE_GIF)
                || contentType.equals(CONTENT_TYPE_IMAGE_BMP)
                || contentType.equals(CONTENT_TYPE_IMAGE_PNG)) {
            return true;
        }
        throw new ParameterInvalidException("目前暂不支持'" + contentType
                + "'这种文件的上传");
    }

//    public static String saveAvatarImage(FileItem fileItem, String extensionName) {
//        String originalFileName = fileItem.getName();// 获取上传文件自己的原始文件名
//        if (originalFileName == null || "".equals(originalFileName.trim())) {// 如果是tmp文件夹中的文件
//            return "";
//        }
//
//        File avatarImageFilePath = new File(getImageBasePath()
//                + IMAGE_TYPE_PATH_AVATAR);
//        if (!avatarImageFilePath.exists()) {
//            avatarImageFilePath.mkdirs();
//        }
//        String newFileName = UUID.randomUUID().toString()
//                + "." + extensionName;
//        String storePath = IMAGE_TYPE_PATH_AVATAR + newFileName;
//        File f2 = new File(avatarImageFilePath, newFileName);
//        try {
//            fileItem.write(f2);
//            return storePath;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return "";
//        }
//    }

    public static String saveAvatarImage(String originalFileName,
                                         byte[] imageBytes) {
        String filePath = getImageBasePath() + IMAGE_TYPE_PATH_AVATAR
                + FileUploadUtil.getYearMonthDayPath();
        return saveImage(filePath, originalFileName, imageBytes);
    }


    public static String saveSelfUploadImage(String originalFileName,
                                             byte[] imageBytes) {
        String filePath = getImageBasePath() + IMAGE_TYPE_PATH_SELF_UPLOAD
                + FileUploadUtil.getYearMonthDayPath();
        return saveImage(filePath, originalFileName, imageBytes);
    }


    private static String saveImage(String filePath, String originalFileName,
                                    byte[] imageBytes) {
        if (originalFileName == null || "".equals(originalFileName.trim())) {// 如果是tmp文件夹中的文件
            return "";
        }
        String imageName = UUID.randomUUID().toString()
                + ConstantPunctuation.PERIOD
                + FilenameUtils.getExtension(originalFileName);
        return saveFile(filePath, imageName, imageBytes);
    }

    /**
     * @param tempFileThreshold 设定阀值1M，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
     *                          利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
     * @param uploadThreshold   最大支持多少M文件上传
     * @return
     */
    public static ServletFileUpload getServletImageUpload(
            int tempFileThreshold, int uploadThreshold) {

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 设定阀值，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
        // 利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
        diskFileItemFactory.setSizeThreshold(tempFileThreshold * 1024 * 1024);

        File tempFile = new File(IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_PATH_TMP);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }

        diskFileItemFactory.setRepository(tempFile);
        ServletFileUpload servletFileUpload = new ServletFileUpload(
                diskFileItemFactory);

        servletFileUpload.setFileSizeMax(1024 * 1024 * uploadThreshold);// 限制最大上传文件的大小

        return servletFileUpload;
    }

    public static String getImageBasePath() {
        return RunningMode.isDevelop() ? IMAGE_BASE_PATH_DEVELOP
                : IMAGE_BASE_PATH_PRODUCT;
    }

    private static String saveFile(String filePath, String fileName,
                                   byte[] bytes) {

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
        String absolutePath = (filePath + fileName);
        return absolutePath;

    }


    /**
     * 等比例压缩图片文件<br>
     *
     * @param srcImageFilePath 要进行压缩的文件完整路径
     * @param newFilePath      新文件完整存储路径
     * @param compressRate     压缩比例（0-1）
     * @param quality          质量
     * @return 返回压缩后的文件的全路径
     */
    public static boolean compressImageConstrain(String srcImageFilePath,
                                                 String newFilePath, float compressRate, float quality)
            throws Exception {
        logger.info(srcImageFilePath + " " + newFilePath);
        File srcFile = new File(srcImageFilePath);
        Image srcImage = ImageIO.read(srcFile);
        Integer originalWidth = srcImage.getWidth(null);
        Integer originalHeight = srcImage.getHeight(null);
        Float compressedWidthFloat = originalWidth * compressRate;
        Float compressedHeightFloat = originalHeight * compressRate;

        /** 宽,高设定 */
        BufferedImage tag = new BufferedImage(compressedWidthFloat.intValue(), compressedHeightFloat.intValue(),
                BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(srcImage, 0, 0, compressedWidthFloat.intValue(), compressedHeightFloat.intValue(), null);

        /** 压缩之后存放位置 */
        File newFile = new File(newFilePath);
        FileOutputStream out = new FileOutputStream(newFile);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
        /** 压缩质量 */
        jep.setQuality(quality, true);
        encoder.encode(tag, jep);
        out.close();
        return true;
    }

    /**
     * 按宽度高度压缩图片文件<br>
     * 先保存原文件，再压缩、上传
     *
     * @param srcImageFilePath 要进行压缩的文件完整路径
     * @param newFilePath      新文件完整存储路径
     * @param width            宽度
     * @param height           高度
     * @param quality          质量
     * @return 返回压缩后的文件的全路径
     */
    public static boolean compressWidthHeightImageFile(String srcImageFilePath,
                                                       String newFilePath, int width, int height, float quality)
            throws Exception {

        File srcFile = new File(srcImageFilePath);
        Image srcImage = ImageIO.read(srcFile);

        /** 宽,高设定 */
        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(srcImage, 0, 0, width, height, null);

        /** 压缩之后存放位置 */
        File newFile = new File(newFilePath);
        FileOutputStream out = new FileOutputStream(newFile);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
        /** 压缩质量 */
        jep.setQuality(quality, true);
        encoder.encode(tag, jep);
        out.close();

        return true;
    }

}
