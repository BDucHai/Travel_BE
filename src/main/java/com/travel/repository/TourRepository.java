package com.travel.repository;

import com.travel.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {

    long countByStatus(String status);

    long countByStatusAndIsActiveTrue(String status);

    List<Tour> findByStatusAndIsActiveTrueOrderByCreatedAtDesc(String status);

    List<Tour> findByDurationDaysAndStatusAndIsActiveTrueOrderByCreatedAtDesc(
            Integer durationDays,
            String status
    );

    Optional<Tour> findBySlugEnAndStatusAndIsActiveTrue(String slugEn, String status);

    Optional<Tour> findBySlugFrAndStatusAndIsActiveTrue(String slugFr, String status);

    List<Tour> findAllByOrderByCreatedAtDesc();

    Page<Tour> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Tour> findByTitleEnContainingIgnoreCaseOrderByCreatedAtDesc(
            String titleEn,
            Pageable pageable
    );

    @Query("""
            SELECT DISTINCT t
            FROM Tour t
            JOIN t.styles s
            WHERE s.slugEn = :slug
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            ORDER BY t.createdAt DESC
            """)
    List<Tour> findPublishedByStyleSlugEn(@Param("slug") String slug);

    @Query("""
            SELECT DISTINCT t
            FROM Tour t
            JOIN t.styles s
            WHERE s.slugFr = :slug
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            ORDER BY t.createdAt DESC
            """)
    List<Tour> findPublishedByStyleSlugFr(@Param("slug") String slug);

    @Query("""
            SELECT DISTINCT t
            FROM Tour t
            JOIN t.collections c
            WHERE c.slugEn = :slug
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            ORDER BY t.createdAt DESC
            """)
    List<Tour> findPublishedByCollectionSlugEn(@Param("slug") String slug);

    @Query("""
            SELECT DISTINCT t
            FROM Tour t
            JOIN t.collections c
            WHERE c.slugFr = :slug
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            ORDER BY t.createdAt DESC
            """)
    List<Tour> findPublishedByCollectionSlugFr(@Param("slug") String slug);

    // ===== DETAIL PUBLIC =====
    @EntityGraph(attributePaths = {"destinations", "styles", "collections"})
    @Query("""
            SELECT t
            FROM Tour t
            WHERE (t.slugEn = :slug OR t.slugFr = :slug)
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            """)
    Optional<Tour> findPublishedByAnySlug(@Param("slug") String slug);

    // ===== DETAIL ADMIN =====
    @EntityGraph(attributePaths = {"destinations", "styles", "collections"})
    @Query("""
            SELECT t
            FROM Tour t
            WHERE t.id = :id
            """)
    Optional<Tour> findDetailById(@Param("id") Long id);

    @Query("""
            SELECT DISTINCT t.durationDays
            FROM Tour t
            WHERE t.durationDays IS NOT NULL
              AND t.status = 'PUBLISHED'
              AND t.isActive = true
            ORDER BY t.durationDays ASC
            """)
    List<Integer> findDistinctPublishedDurations();

    Page<Tour> findByStatusAndIsActiveTrue(
            String status,
            Pageable pageable
    );

    Page<Tour> findByDurationDaysAndStatusAndIsActiveTrue(
            Integer durationDays,
            String status,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.styles s
                    WHERE s.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.styles s
                    WHERE s.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByStyleSlugEnPage(
            @Param("slug") String slug,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.styles s
                    WHERE s.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.styles s
                    WHERE s.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByStyleSlugFrPage(
            @Param("slug") String slug,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.collections c
                    WHERE c.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.collections c
                    WHERE c.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByCollectionSlugEnPage(
            @Param("slug") String slug,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.collections c
                    WHERE c.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.collections c
                    WHERE c.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByCollectionSlugFrPage(
            @Param("slug") String slug,
            Pageable pageable
    );

    // ===== DESTINATION PAGE =====
    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.destinations d
                    WHERE d.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.destinations d
                    WHERE d.slugEn = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByDestinationSlugEnPage(
            @Param("slug") String slug,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT DISTINCT t
                    FROM Tour t
                    JOIN t.destinations d
                    WHERE d.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    ORDER BY t.createdAt DESC
                    """,
            countQuery = """
                    SELECT COUNT(DISTINCT t)
                    FROM Tour t
                    JOIN t.destinations d
                    WHERE d.slugFr = :slug
                      AND t.status = 'PUBLISHED'
                      AND t.isActive = true
                    """
    )
    Page<Tour> findPublishedByDestinationSlugFrPage(
            @Param("slug") String slug,
            Pageable pageable
    );
}