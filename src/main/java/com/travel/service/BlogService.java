package com.travel.service;

import com.travel.dto.*;
import com.travel.dto.BlogRequest;
import com.travel.dto.BlogResponse;
import com.travel.dto.PaginationResponse;
import com.travel.dto.RelatedTourResponse;
import com.travel.entity.Blog;
import com.travel.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // Public API: danh sách blog đã publish + pagination
    public PaginationResponse<BlogResponse> getBlogs(
            String lang,
            Integer page,
            Integer limit
    ) {
        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 9 : limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Blog> blogPage = blogRepository.findByStatusOrderByPublishedAtDesc(
                "PUBLISHED",
                pageable
        );

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
                blogPage.isLast()
        );
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

    // Admin API: lấy tất cả blog
    public PaginationResponse<AdminBlogResponse> getAllBlogsForAdmin(
            Integer page,
            Integer limit
    ) {
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
                blogPage.isLast()
        );
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
                        : request.getStatus().toUpperCase()
        );

        blog.setViewCount(0);

        if ("PUBLISHED".equalsIgnoreCase(blog.getStatus())) {
            blog.setPublishedAt(
                    request.getPublishedAt() == null
                            ? LocalDateTime.now()
                            : request.getPublishedAt()
            );
        } else {
            blog.setPublishedAt(request.getPublishedAt());
        }

        Blog saved = blogRepository.save(blog);

        return mapToResponse(saved, lang, true);
    }

    // Admin API: update blog
    public BlogResponse updateBlog(Long id, BlogRequest request, String lang) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

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
                            tour.getFeaturedImageUrl()
                    ))
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
                relatedTours
        );
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
                            tour.getFeaturedImageUrl()
                    ))
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
                relatedTours
        );
    }
}