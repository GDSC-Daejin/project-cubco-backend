package org.cubco.image.repository;

import org.cubco.curation.domain.Curation;
import org.cubco.image.domain.CurationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurationImageRepository extends JpaRepository<CurationImage, Long> {
    List<CurationImage> findAllByCuration(Curation curation);
    void deleteByCuration(Curation curation);
}
