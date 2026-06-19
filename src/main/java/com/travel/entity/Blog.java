package com.travel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sau này nếu làm User/Admin thì có thể mapping author.
    // Hiện tại để đơn giản, mình lưu authorName trực tiếp.
    @Column(name = "author_name")
    private String authorName;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "title_fr", nullable = false)
    private String titleFr;

    @Column(name = "slug_en", nullable = false, unique = true)
    private String slugEn;

    @Column(name = "slug_fr", nullable = false, unique = true)
    private String slugFr;

    @Column(name = "excerpt_en", length = 500)
    private String excerptEn;

    @Column(name = "excerpt_fr", length = 500)
    private String excerptFr;

    @Column(name = "content_en", columnDefinition = "TEXT")
    private String contentEn;

    @Column(name = "content_fr", columnDefinition = "TEXT")
    private String contentFr;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "hero_image_url")
    private String heroImageUrl;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_most_read")
    private Boolean isMostRead = false;

    private String status = "DRAFT";

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "blog_related_tours",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    private Set<Tour> relatedTours = new HashSet<>();

    public Blog() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.viewCount == null) this.viewCount = 0;
        if (this.isFeatured == null) this.isFeatured = false;
        if (this.isMostRead == null) this.isMostRead = false;
        if (this.status == null) this.status = "DRAFT";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleFr() {
        return titleFr;
    }

    public String getSlugEn() {
        return slugEn;
    }

    public String getSlugFr() {
        return slugFr;
    }

    public String getExcerptEn() {
        return excerptEn;
    }

    public String getExcerptFr() {
        return excerptFr;
    }

    public String getContentEn() {
        return contentEn;
    }

    public String getContentFr() {
        return contentFr;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public Boolean getIsMostRead() {
        return isMostRead;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public void setTitleFr(String titleFr) {
        this.titleFr = titleFr;
    }

    public void setSlugEn(String slugEn) {
        this.slugEn = slugEn;
    }

    public void setSlugFr(String slugFr) {
        this.slugFr = slugFr;
    }

    public void setExcerptEn(String excerptEn) {
        this.excerptEn = excerptEn;
    }

    public void setExcerptFr(String excerptFr) {
        this.excerptFr = excerptFr;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public void setContentFr(String contentFr) {
        this.contentFr = contentFr;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setIsFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public void setIsMostRead(Boolean mostRead) {
        isMostRead = mostRead;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Set<Tour> getRelatedTours() {
        return relatedTours;
    }

    public void setRelatedTours(Set<Tour> relatedTours) {
        this.relatedTours = relatedTours;
    }
}