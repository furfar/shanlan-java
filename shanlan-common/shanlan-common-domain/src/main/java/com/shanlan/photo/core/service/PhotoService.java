package com.shanlan.photo.core.service;

import java.util.ArrayList;
import java.util.List;

import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.PhotoCollection;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;

public class PhotoService {

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

}
