package com.travel.repository;

import com.travel.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
 
	long countByStatus(String status);
    Page<Blog> findByStatusOrderByPublishedAtDesc(String status, Pageable pageable);

    List<Blog> findTop4ByStatusAndIsMostReadTrueOrderByViewCountDesc(String status);

    Optional<Blog> findBySlugEnAndStatus(String slugEn, String status);

    Optional<Blog> findBySlugFrAndStatus(String slugFr, String status);

    List<Blog> findAllByOrderByCreatedAtDesc();
    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    @Query("""
            SELECT DISTINCT b
            FROM Blog b
            LEFT JOIN FETCH b.relatedTours rt
            WHERE (b.slugEn = :slug OR b.slugFr = :slug)
              AND b.status = 'PUBLISHED'
            """)
    Optional<Blog> findPublishedByAnySlug(@Param("slug") String slug);
}