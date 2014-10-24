package com.shanlan.common.util;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.shanlan.common.constant.ConstantString;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);


    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";


    public static final String IMAGE_EXTENSION_NAME_JPG = ".jpg";


//    /**
//     * 开发环境图片存储根路径
//     */
//    public static final String IMAGE_ROOT_PATH_DEVELOP = "/Users/albertliu/";
//
//    /**
//     * 生产环境图片存储根路径
//     */
//    public static final String IMAGE_ROOT_PATH_PRODUCT = "/opt/";


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


//    public static final String IMAGE_DEVELOP_AVATAR = IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_AVATAR + "/";
//
//    public static final String IMAGE_DEVELOP_PHOTO = IMAGE_BASE_PATH_DEVELOP + IMAGE_TYPE_PHOTO + "/";
//
//    public static final String IMAGE_PRODUCT_AVATAR = IMAGE_BASE_PATH_PRODUCT + IMAGE_TYPE_AVATAR + "/";
//
//    public static final String IMAGE_PRODUCT_PHOTO = IMAGE_BASE_PATH_PRODUCT + IMAGE_TYPE_PHOTO + "/";


    /**
     * 裁剪图片
     * @param input
     * @param basepath
     * @param x
     * @param y
     * @param width
     * @param height
     * @return 绝对路径
     * @throws IOException
     */
//    public static String cutImg(String input,String basepath,int x,int y,int width,int height) throws IOException{
//        String path2 = basepath+"/"+ConstantUtils.USERFACETEMPPATH;
//        String postfix = getPostfix(input);
//        String dst = path2 +"/"+UUID.randomUUID().toString()+"."+postfix;
//
//        createDir(path2);
//        imgCut(basepath+input,dst,postfix,x,y,width,height);
//
//        return dst;
//    }

    /**
     * 裁剪图片
     *
     * @param input
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws IOException
     */
    public static void imgCut(String input, String dst, String type, int x, int y, int width, int height) throws IOException {
        BufferedImage fromimg = ImageIO.read(new File(input));
        ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
        Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(fromimg.getSource(), cropFilter));
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, null); // 绘制小图
        g.dispose();
        // 输出为文件
        // dir = "d:\\temp\\cut_image_" + i + "_" + j + ".jpg";
        File f = new File(dst);
        ImageIO.write(tag, type, f);

    }

    /**
     * 上传头像文件
     * @param src
     * @param basepath
     * @param filename
     * @return
     * @throws Exception
     */
//    public static String uploadImg(File src,String basepath,String filename) throws Exception{
//        String daypath = DateTools.getYear() + "" + DateTools.getMonth() + "" + DateTools.getMonthWeek();
//
//        String temppath = ConstantUtils.BASEUPLOADPATH+"/"+ConstantUtils.ORIGINALIMGPATH+"/" + daypath;
//        String thumbnailpath = ConstantUtils.BASEUPLOADPATH+"/"+ConstantUtils.THUMBNAILIMGPATH+"/" + daypath;
//
//        String postfix = getPostfix(filename);
//        String uuid = UUID.randomUUID().toString();
//        String dsttempname = uuid+"."+postfix;
//
//        createDir(basepath +"/"+temppath);
//        createDir (basepath +"/"+ thumbnailpath);
//
//        String dstallpath = basepath +"/"+temppath+"/"+dsttempname;
//        String dstthumbnailpath = basepath +"/"+thumbnailpath+"/"+dsttempname;
//
//        copy(src,new File(dstallpath));
//
//        //对上传的文件进行 等比例 裁剪。  按照前段要展现的  height width
//        Thumbnail(dstallpath,dstthumbnailpath,350,300,100);
//
//        //返回裁剪后的路径
//
//        return thumbnailpath+"/"+dsttempname;
//    }

    /**
     * 上传文件
     * @param src
     * @param dst
     * @throws Exception
     */
//    public static void copy(File src, File dst) throws Exception {
//        try {
//            InputStream in = null;
//            OutputStream out = null;
//            try {
//                in = new BufferedInputStream(new FileInputStream(src), ConstantUtils.BUFFER_SIZE);
//                out = new BufferedOutputStream(new FileOutputStream(dst), ConstantUtils.BUFFER_SIZE);
//                byte[] buffer = new byte[ConstantUtils.BUFFER_SIZE];
//                while (in.read(buffer) > 0) {
//                    out.write(buffer);
//                }
//            } finally {
//                if (null != in) {
//                    in.close();
//                }
//                if (null != out) {
//                    out.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    /**
     * 得到文件后缀  jpg
     *
     * @param fileName
     * @return
     */
    public static String getPostfix(String fileName) {
        if (fileName.equals(""))
            return "";
        int pos = fileName.lastIndexOf(".");
        if (pos < 0) {
            return fileName.substring(fileName.length() - 3).toLowerCase();
        } else {
            return fileName.substring(pos + 1).toLowerCase();
        }
    }

    /**
     * 创建目录
     *
     * @param filePath
     */
    public static void createDir(String filePath) {
        File myFile = new File(filePath);
        if (!myFile.exists()) {
            if (!myFile.mkdirs())
                System.out.println("创建目录 fail");
            else
                System.out.println("创建目录 success");
        }
        myFile = null;
    }

    /**
     * 等比例缩放图片
     *
     * @param infile
     * @param outfile
     * @param width
     * @param height
     * @param quality
     * @throws IOException
     * @throws InterruptedException
     */
    public static void Thumbnail(String infile, String outfile, int width, int height, int quality) throws IOException, InterruptedException {
        // save thumbnail image to OUTFILE
        //System.out.println("infile:" + infile);
        BufferedImage thumbImage = null;
        BufferedOutputStream out = null;
        Image image = null;
        image = Toolkit.getDefaultToolkit().createImage(infile);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForID(0);
        int thumbWidth = width;
        int thumbHeight = height;
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }
        thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
        out = new BufferedOutputStream(new FileOutputStream(outfile));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(thumbImage);
        out.close();
        thumbImage = null;
        out = null;
        image = null;
    }


    public static boolean validateImageType(String contentType) {
        if (!contentType.equals(CONTENT_TYPE_IMAGE_JPEG)) {
            return false;
        }
        return true;
    }


    public static String saveTradePhotoImage(String userName, byte[] imageBytes) {
        String baseFilePath = RunningMode.isDevelop() ? IMAGE_BASE_PATH_DEVELOP : IMAGE_BASE_PATH_PRODUCT;
        String filePath = baseFilePath + userName + "/" + IMAGE_TYPE_TRADE_PHOTO + "/";
        return saveImage(filePath, imageBytes);
    }


    public static String saveSelfUploadImage(String userName, byte[] imageBytes) {
        String baseFilePath = RunningMode.isDevelop() ? IMAGE_BASE_PATH_DEVELOP : IMAGE_BASE_PATH_PRODUCT;
        String filePath = baseFilePath + userName + "/" + IMAGE_TYPE_SELF_UPLOAD + "/";
        return saveImage(filePath, imageBytes);
    }


    public static String saveAvatarImage(String userName, byte[] imageBytes) {
        String baseFilePath = RunningMode.isDevelop() ? IMAGE_BASE_PATH_DEVELOP : IMAGE_BASE_PATH_PRODUCT;
        String filePath = baseFilePath + userName + "/" + IMAGE_TYPE_AVATAR + "/";
        return saveImage(filePath, imageBytes);
    }


    private static String saveImage(String filePath, byte[] imageBytes) {
        String imageName = UUID.randomUUID().toString() + IMAGE_EXTENSION_NAME_JPG;
        return saveFile(filePath, imageName, imageBytes);
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
        String storePath = IMAGE_TYPE_AVATAR +"/"+ newFileName;
        File f2 = new File(avatarImageFilePath, newFileName);
        try {
            fileItem.write(f2);
            return storePath;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
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
        return path + fileName;

    }


    /**
     * @param tempFileThreshold 设定阀值1M，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
     *                          利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
     * @param uploadThreshold   最大支持多少M文件上传
     * @return
     */
    public static ServletFileUpload getServletFileUpload(int tempFileThreshold, int uploadThreshold) {

        List<FileItem> fileItemList = new ArrayList<FileItem>();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //设定阀值1M，如果超过这个值，则文件就直接写到临时文件，不会一直占用内存
        //利用这个特性可在上传大文件的时候不会占用大量内存，可以提高并发使用量。
        diskFileItemFactory.setSizeThreshold(tempFileThreshold * 1024 * 1024);


        File tempFile = new File(FileUploadUtil.IMAGE_BASE_PATH_DEVELOP + FileUploadUtil.IMAGE_TYPE_TMP);
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

}