package com.ucla.ShopYourLikes.repository;

import com.ucla.ShopYourLikes.model.LinkId;
import com.ucla.ShopYourLikes.model.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, LinkId> {
    Page<Link> findByLinkIdUserId(Integer userId, Pageable pageable);
    Optional<Link> findByLinkId(LinkId linkId);
    long countByLinkIdUserId(Integer userId);

}