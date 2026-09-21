package com.travel.service;

import com.travel.dto.*;
import com.travel.dto.BlogRequest;
import com.travel.dto.BlogResponse;
import com.travel.dto.PaginationResponse;
import com.travel.dto.RelatedTourResponse;
import com.travel.entity.Blog;
import com.travel.entity.Tour;
import com.travel.repository.BlogRepository;
import com.travel.repository.TourRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

        private final BlogRepository blogRepository;
        private final TourRepository tourRepository;

        public BlogService(BlogRepository blogRepository, TourRepository tourRepository) {
                this.blogRepository = blogRepository;
                this.tourRepository = tourRepository;
        }

        // Public API: danh sách blog đã publish + pagination
        public PaginationResponse<BlogResponse> getBlogs(
                        String lang,
                        Integer page,
                        Integer limit) {
                int pageNumber = page == null || page < 0 ? 0 : page;
                int pageSize = limit == null || limit <= 0 ? 9 : limit;

                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                Page<Blog> blogPage = blogRepository.findByStatusOrderByPublishedAtDesc(
                                "PUBLISHED",
                                pageable);

                List<BlogResponse> data = blogPage.getContent()
                                .stream()
                                .map(blog -> mapToResponse(blog, lang, false))
                                .toList();

                return new PaginationResponse<>(
                                data,
                                blogPage.getNumber(),
                                blogPage.getSize(),
                                blogPage.getTotalElements(),
                                blogPage.getTotalPages(),
                                blogPage.isFirst(),
                                blogPage.isLast());
        }

        public List<BlogResponse> getRandomBlogs(String lang) {
                return blogRepository.findRandom4PublishedBlogs()
                                .stream()
                                .map(blog -> mapToResponse(blog, lang, false))
                                .toList();
        }

        // Public API: most read
        public List<BlogResponse> getMostReadBlogs(String lang) {
                return blogRepository.findTop4ByStatusAndIsMostReadTrueOrderByViewCountDesc("PUBLISHED")
                                .stream()
                                .map(blog -> mapToResponse(blog, lang, false))
                                .toList();
        }

        // Public API: detail + tăng view count
        public BlogResponse getBlogDetail(String slug, String lang) {
                Blog blog = blogRepository.findPublishedByAnySlug(slug)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                blog.setViewCount(blog.getViewCount() == null ? 1 : blog.getViewCount() + 1);
                blogRepository.save(blog);

                return mapToResponse(blog, lang, true);
        }

        // Public API: search all (Tour and blog)
        public GlobalSearchResponse globalSearch(
                        String keyword,
                        String lang,
                        boolean limit) {

                if (keyword == null || keyword.trim().isEmpty()) {

                        return new GlobalSearchResponse(
                                        Page.empty(),
                                        Page.empty());
                }

                String searchKeyword = keyword.trim();

                boolean isFrench = "fr".equalsIgnoreCase(lang);

                Pageable pageable = limit
                                ? PageRequest.of(0, 2)
                                : Pageable.unpaged();


                Page<Blog> blogs = isFrench
                                ? blogRepository.searchByTitleFr(
                                                searchKeyword,
                                                pageable)
                                : blogRepository.searchByTitleEn(
                                                searchKeyword,
                                                pageable);

                Page<Tour> tours = isFrench
                                ? tourRepository.searchByTitleFr(
                                                searchKeyword,
                                                pageable)
                                : tourRepository.searchByTitleEn(
                                                searchKeyword,
                                                pageable);

                Page<BlogResponse> blogResponses = blogs.map(
                                blog -> mapBlogToSearchResponse(
                                                blog,
                                                isFrench));

                Page<TourResponse> tourResponses = tours.map(
                                tour -> mapTourToSearchResponse(
                                                tour,
                                                isFrench));

                return new GlobalSearchResponse(
                                blogResponses,
                                tourResponses);
        }

        // Admin API: lấy tất cả blog
        public PaginationResponse<AdminBlogResponse> getAllBlogsForAdmin(
                        Integer page,
                        Integer limit) {
                int pageNumber = page == null || page < 0 ? 0 : page;
                int pageSize = limit == null || limit <= 0 ? 10 : limit;

                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                Page<Blog> blogPage = blogRepository.findAllByOrderByCreatedAtDesc(pageable);

                List<AdminBlogResponse> data = blogPage.getContent()
                                .stream()
                                .map(this::mapToAdminResponse)
                                .toList();

                return new PaginationResponse<>(
                                data,
                                blogPage.getNumber(),
                                blogPage.getSize(),
                                blogPage.getTotalElements(),
                                blogPage.getTotalPages(),
                                blogPage.isFirst(),
                                blogPage.isLast());
        }

        // Admin API: lấy blog theo id
        public AdminBlogResponse getBlogById(Long id) {
                Blog blog = blogRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                return mapToAdminResponse(blog);
        }

        // Admin API: tạo blog
        public BlogResponse createBlog(BlogRequest request, String lang) {
                Blog blog = new Blog();

                blog.setAuthorName(request.getAuthorName());

                blog.setTitleEn(request.getTitleEn());
                blog.setTitleFr(request.getTitleFr());

                blog.setSlugEn(request.getSlugEn());
                blog.setSlugFr(request.getSlugFr());

                blog.setExcerptEn(request.getExcerptEn());
                blog.setExcerptFr(request.getExcerptFr());

                blog.setContentEn(request.getContentEn());
                blog.setContentFr(request.getContentFr());

                blog.setThumbnailUrl(request.getThumbnailUrl());
                blog.setHeroImageUrl(request.getHeroImageUrl());

                blog.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());
                blog.setIsMostRead(request.getIsMostRead() == null ? false : request.getIsMostRead());

                blog.setStatus(
                                request.getStatus() == null || request.getStatus().isBlank()
                                                ? "DRAFT"
                                                : request.getStatus().toUpperCase());

                blog.setViewCount(request.getViewCount() == null ? 0 : request.getViewCount());

                if ("PUBLISHED".equalsIgnoreCase(blog.getStatus())) {
                        blog.setPublishedAt(
                                        request.getPublishedAt() == null
                                                        ? LocalDateTime.now()
                                                        : request.getPublishedAt());
                } else {
                        blog.setPublishedAt(request.getPublishedAt());
                }

                if (request.getRelatedTourIds() != null && !request.getRelatedTourIds().isEmpty()) {
                        List<Tour> tours = tourRepository.findAllById(request.getRelatedTourIds());

                        if (tours.size() != request.getRelatedTourIds().size()) {
                                throw new RuntimeException("Một hoặc nhiều relatedTourIds không tồn tại");
                        }

                        blog.setRelatedTours(new HashSet<>(tours));
                } else {
                        blog.setRelatedTours(new HashSet<>());
                }

                Blog saved = blogRepository.save(blog);

                return mapToResponse(saved, lang, true);
        }

        // Admin API: update blog
        public BlogResponse updateBlog(Long id, BlogRequest request, String lang) {
                Blog blog = blogRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                if (request.getAuthorName() != null) {
                        blog.setAuthorName(request.getAuthorName());
                }

                if (request.getTitleEn() != null) {
                        blog.setTitleEn(request.getTitleEn());
                }
                if (request.getTitleFr() != null) {
                        blog.setTitleFr(request.getTitleFr());
                }

                if (request.getSlugEn() != null) {
                        blog.setSlugEn(request.getSlugEn());
                }
                if (request.getSlugFr() != null) {
                        blog.setSlugFr(request.getSlugFr());
                }

                if (request.getExcerptEn() != null) {
                        blog.setExcerptEn(request.getExcerptEn());
                }
                if (request.getExcerptFr() != null) {
                        blog.setExcerptFr(request.getExcerptFr());
                }

                if (request.getContentEn() != null) {
                        blog.setContentEn(request.getContentEn());
                }
                if (request.getContentFr() != null) {
                        blog.setContentFr(request.getContentFr());
                }

                if (request.getThumbnailUrl() != null) {
                        blog.setThumbnailUrl(request.getThumbnailUrl());
                }
                if (request.getHeroImageUrl() != null) {
                        blog.setHeroImageUrl(request.getHeroImageUrl());
                }

                if (request.getIsFeatured() != null) {
                        blog.setIsFeatured(request.getIsFeatured());
                }

                if (request.getIsMostRead() != null) {
                        blog.setIsMostRead(request.getIsMostRead());
                }

                if (request.getStatus() != null && !request.getStatus().isBlank()) {
                        String oldStatus = blog.getStatus();
                        String newStatus = request.getStatus().toUpperCase();

                        blog.setStatus(newStatus);

                        if (!"PUBLISHED".equalsIgnoreCase(oldStatus)
                                        && "PUBLISHED".equalsIgnoreCase(newStatus)
                                        && blog.getPublishedAt() == null) {
                                blog.setPublishedAt(LocalDateTime.now());
                        }
                }

                if (request.getPublishedAt() != null) {
                        blog.setPublishedAt(request.getPublishedAt());
                }

                // related tours
                if (request.getRelatedTourIds() != null) {
                        if (request.getRelatedTourIds().isEmpty()) {
                                blog.setRelatedTours(new HashSet<>());
                        } else {
                                List<Tour> tours = tourRepository.findAllById(request.getRelatedTourIds());

                                if (tours.size() != request.getRelatedTourIds().size()) {
                                        throw new RuntimeException("Một hoặc nhiều relatedTourIds không tồn tại");
                                }

                                blog.setRelatedTours(new HashSet<>(tours));
                        }
                }

                Blog updated = blogRepository.save(blog);
                return mapToResponse(updated, lang, true);
        }

        // Admin API: update status DRAFT/PUBLISHED
        public BlogResponse updateBlogStatus(Long id, String status, String lang) {
                Blog blog = blogRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                String newStatus = status.toUpperCase();

                blog.setStatus(newStatus);

                if ("PUBLISHED".equals(newStatus) && blog.getPublishedAt() == null) {
                        blog.setPublishedAt(LocalDateTime.now());
                }

                Blog updated = blogRepository.save(blog);

                return mapToResponse(updated, lang, true);
        }

        // Admin API: tick/untick most read
        public BlogResponse updateMostRead(Long id, Boolean isMostRead, String lang) {
                Blog blog = blogRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                blog.setIsMostRead(isMostRead == null ? false : isMostRead);

                Blog updated = blogRepository.save(blog);

                return mapToResponse(updated, lang, true);
        }

        // Admin API: xóa blog
        public void deleteBlog(Long id) {
                Blog blog = blogRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Blog not found"));

                blogRepository.delete(blog);
        }

        private BlogResponse mapToResponse(Blog blog, String lang, boolean includeContent) {
                boolean isFrench = "fr".equalsIgnoreCase(lang);

                List<RelatedTourResponse> relatedTours = null;

                if (includeContent && blog.getRelatedTours() != null) {
                        relatedTours = blog.getRelatedTours()
                                        .stream()
                                        .filter(tour -> "PUBLISHED".equalsIgnoreCase(tour.getStatus()))
                                        .filter(tour -> Boolean.TRUE.equals(tour.getIsActive()))
                                        .limit(4)
                                        .map(tour -> new RelatedTourResponse(
                                                        tour.getId(),
                                                        isFrench ? tour.getTitleFr() : tour.getTitleEn(),
                                                        isFrench ? tour.getSlugFr() : tour.getSlugEn(),
                                                        tour.getDurationDays(),
                                                        tour.getPriceFrom(),
                                                        tour.getFeaturedImageUrl()))
                                        .toList();
                }

                return new BlogResponse(
                                blog.getId(),
                                blog.getAuthorName(),
                                isFrench ? blog.getTitleFr() : blog.getTitleEn(),
                                isFrench ? blog.getSlugFr() : blog.getSlugEn(),
                                isFrench ? blog.getExcerptFr() : blog.getExcerptEn(),
                                includeContent ? (isFrench ? blog.getContentFr() : blog.getContentEn()) : null,
                                blog.getThumbnailUrl(),
                                blog.getHeroImageUrl(),
                                blog.getViewCount(),
                                blog.getIsFeatured(),
                                blog.getIsMostRead(),
                                blog.getStatus(),
                                blog.getPublishedAt(),
                                relatedTours);
        }

        private AdminBlogResponse mapToAdminResponse(Blog blog) {
                List<Long> relatedTourIds = null;
                List<AdminBlogRelatedTourResponse> relatedTours = null;

                if (blog.getRelatedTours() != null) {
                        relatedTourIds = blog.getRelatedTours()
                                        .stream()
                                        .map(tour -> tour.getId())
                                        .toList();

                        relatedTours = blog.getRelatedTours()
                                        .stream()
                                        .map(tour -> new AdminBlogRelatedTourResponse(
                                                        tour.getId(),
                                                        tour.getTitleEn(),
                                                        tour.getTitleFr(),
                                                        tour.getSlugEn(),
                                                        tour.getSlugFr(),
                                                        tour.getDurationDays(),
                                                        tour.getPriceFrom(),
                                                        tour.getFeaturedImageUrl()))
                                        .toList();
                }

                return new AdminBlogResponse(
                                blog.getId(),
                                blog.getAuthorName(),

                                blog.getTitleEn(),
                                blog.getTitleFr(),

                                blog.getSlugEn(),
                                blog.getSlugFr(),

                                blog.getExcerptEn(),
                                blog.getExcerptFr(),

                                blog.getContentEn(),
                                blog.getContentFr(),

                                blog.getThumbnailUrl(),
                                blog.getHeroImageUrl(),

                                blog.getViewCount(),
                                blog.getIsFeatured(),
                                blog.getIsMostRead(),
                                blog.getStatus(),

                                blog.getCreatedAt(),
                                blog.getUpdatedAt(),
                                blog.getPublishedAt(),

                                relatedTourIds,
                                relatedTours);
        }

        private BlogResponse mapBlogToSearchResponse(
        Blog blog,
        boolean isFrench
) {

    return new BlogResponse(
            blog.getId(),
            blog.getAuthorName(),

            isFrench
                    ? blog.getTitleFr()
                    : blog.getTitleEn(),

            isFrench
                    ? blog.getSlugFr()
                    : blog.getSlugEn(),

            isFrench
                    ? blog.getExcerptFr()
                    : blog.getExcerptEn(),

            null, // content

            blog.getThumbnailUrl(),
            blog.getHeroImageUrl(),

            blog.getViewCount(),
            blog.getIsFeatured(),
            blog.getIsMostRead(),
            blog.getStatus(),
            blog.getPublishedAt(),

            null 
    );
}

private TourResponse mapTourToSearchResponse(
        Tour tour,
        boolean isFrench
) {

    return new TourResponse(
            tour.getId(),
            tour.getCode(),
            tour.getDurationDays(),
            tour.getPriceFrom(),
            tour.getGroupSize(),

            isFrench
                    ? tour.getTitleFr()
                    : tour.getTitleEn(),

            isFrench
                    ? tour.getSlugFr()
                    : tour.getSlugEn(),

            isFrench
                    ? tour.getShortDescriptionFr()
                    : tour.getShortDescriptionEn(),

            isFrench
                    ? tour.getOverviewFr()
                    : tour.getOverviewEn(),

            isFrench
                    ? tour.getItineraryFr()
                    : tour.getItineraryEn(),

            isFrench
                    ? tour.getInclusionFr()
                    : tour.getInclusionEn(),

            isFrench
                    ? tour.getExclusionFr()
                    : tour.getExclusionEn(),

            tour.getFeaturedImageUrl(),

            tour.getIsFeatured(),
            tour.getIsActive(),
            tour.getStatus(),
            tour.getCreatedAt(),

            null, // imageUrls
            null, // destinationNames
            null, // styleNames
            null, // collectionNames
            null  // itineraryDays
    );
}
}
