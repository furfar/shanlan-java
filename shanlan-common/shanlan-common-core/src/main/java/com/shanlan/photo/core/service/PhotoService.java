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

		// 先根据前台传入的裁剪规格（destWidth，destHeight）对原图进行裁剪
		String cutImageFilePath = ImageUploadUtil.cutImage(srcImageFilePath, x,
				y, destWidth, destHeight);

		// 再在裁剪图的基础等比例压缩三张图，分别为200*200,120*120,30*30
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

		// 删除裁剪的临时文件
		File cutImageFile = new File(cutImageFilePath);
		cutImageFile.delete();

		// 存储到数据库中的路径需要加上一个图片裁剪规格占位符（_X_X）,并替换掉存储前缀，
		// 这样前台取图里就可以通过替换占位符取到相应的图片，后台也省去了存储多种规格图片路径的麻烦
		String storeFilePath = ImageUploadUtil.appendImageSizePlaceHolder(
				srcImageFilePath, extensionName).replace(
				ImageUploadUtil.getImageBasePath(), "");

		return storeFilePath;
	}

}
