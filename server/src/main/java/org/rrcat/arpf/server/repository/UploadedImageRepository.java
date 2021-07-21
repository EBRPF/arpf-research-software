package org.rrcat.arpf.server.repository;

import org.rrcat.arpf.server.entity.UploadedImage;
import org.rrcat.arpf.server.entity.auth.RrcatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, Long> {
    UploadedImage findUploadedImageById(final Integer id);
    UploadedImage findUploadedImageByTagAndName(final String tag, final String name);
}
