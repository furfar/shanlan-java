package com.shanlan.opf.application.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.opf.application.PhotoApplication;
import com.shanlan.opf.application.dto.PhotoCollectionDTO;
import com.shanlan.opf.application.dto.PhotoDTO;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.PhotoCollection;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;

@Named
@Transactional
public class PhotoApplicationImpl implements PhotoApplication {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoCollectionDTO> getPhotoCollections(String userName)
			throws Exception {

		List<PhotoCollectionDTO> photoCollectionDTOs = new ArrayList<PhotoCollectionDTO>();

		if (StringUtils.isNotBlank(userName)) {
			List<PhotoCollection> photoCollections = PhotoCollection
					.findByCreator(userName);
			for (PhotoCollection photoCollection : photoCollections) {

				PhotoCollectionDTO photoCollectionDTO = new PhotoCollectionDTO();
				BeanUtils.copyProperties(photoCollectionDTO, photoCollection);

				List<RePhotoCollectionPhoto> rePhotoCollectionPhotos = RePhotoCollectionPhoto
						.findByPhotoCollectionId(photoCollection.getId());
				List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();
				for (RePhotoCollectionPhoto rePhotoCollectionPhoto : rePhotoCollectionPhotos) {

					RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn.get(
							RePhotoUserOwn.class,
							rePhotoCollectionPhoto.getUpoId());

					if (rePhotoUserOwn != null) {
						PhotoDTO photoDTO = new PhotoDTO(
								rePhotoUserOwn.getPhotoId(),
								rePhotoUserOwn.getPhotoPath());
						photoDTOs.add(photoDTO);
					}

				}
				photoCollectionDTO.setPhotoDTOList(photoDTOs);
				photoCollectionDTOs.add(photoCollectionDTO);
			}
		}

		return photoCollectionDTOs;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PhotoDTO> getPhotos(Integer photoCollectionId) throws Exception {
		List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();

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
					PhotoDTO photoDTO = new PhotoDTO();
					Photo photo = Photo.get(Photo.class,
							rePhotoUserOwn.getPhotoId());
					BeanUtils.copyProperties(photoDTO, photo);
					photoDTOs.add(photoDTO);
				}
			}

		}
		return photoDTOs;
	}

}
