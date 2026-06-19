package com.travel.service;

import com.travel.dto.PageRequest;
import com.travel.dto.PageResponse;
import com.travel.entity.Page;
import com.travel.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    // Public API: get page by key
    public PageResponse getPageByKey(String pageKey, String lang) {
        Page page = pageRepository.findByPageKeyAndStatus(pageKey.toUpperCase(), "PUBLISHED")
                .orElseThrow(() -> new RuntimeException("Page not found"));

        return mapToResponse(page, lang);
    }

    // Public API: get page by slug
    public PageResponse getPageBySlug(String slug, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        Page page = isFrench
                ? pageRepository.findBySlugFrAndStatus(slug, "PUBLISHED")
                    .orElseThrow(() -> new RuntimeException("Page not found"))
                : pageRepository.findBySlugEnAndStatus(slug, "PUBLISHED")
                    .orElseThrow(() -> new RuntimeException("Page not found"));

        return mapToResponse(page, lang);
    }

    // Admin API: get all pages
    public List<PageResponse> getAllPagesForAdmin(String lang) {
        return pageRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(page -> mapToResponse(page, lang))
                .toList();
    }

    // Admin API: get page by id
    public PageResponse getPageById(Long id, String lang) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        return mapToResponse(page, lang);
    }

    // Admin API: create page
    public PageResponse createPage(PageRequest request, String lang) {
        Page page = new Page();

        page.setPageKey(request.getPageKey().toUpperCase());

        page.setTitleEn(request.getTitleEn());
        page.setTitleFr(request.getTitleFr());

        page.setSlugEn(request.getSlugEn());
        page.setSlugFr(request.getSlugFr());

        page.setContentEn(request.getContentEn());
        page.setContentFr(request.getContentFr());

        page.setStatus(
                request.getStatus() == null || request.getStatus().isBlank()
                        ? "PUBLISHED"
                        : request.getStatus().toUpperCase()
        );

        Page saved = pageRepository.save(page);

        return mapToResponse(saved, lang);
    }

    // Admin API: update page
    public PageResponse updatePage(Long id, PageRequest request, String lang) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        page.setPageKey(request.getPageKey().toUpperCase());

        page.setTitleEn(request.getTitleEn());
        page.setTitleFr(request.getTitleFr());

        page.setSlugEn(request.getSlugEn());
        page.setSlugFr(request.getSlugFr());

        page.setContentEn(request.getContentEn());
        page.setContentFr(request.getContentFr());

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            page.setStatus(request.getStatus().toUpperCase());
        }

        Page updated = pageRepository.save(page);

        return mapToResponse(updated, lang);
    }

    // Admin API: update status
    public PageResponse updatePageStatus(Long id, String status, String lang) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        page.setStatus(status.toUpperCase());

        Page updated = pageRepository.save(page);

        return mapToResponse(updated, lang);
    }

    // Admin API: delete page
    public void deletePage(Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        pageRepository.delete(page);
    }

    private PageResponse mapToResponse(Page page, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        return new PageResponse(
                page.getId(),
                page.getPageKey(),
                isFrench ? page.getTitleFr() : page.getTitleEn(),
                isFrench ? page.getSlugFr() : page.getSlugEn(),
                isFrench ? page.getContentFr() : page.getContentEn(),
                page.getStatus()
        );
    }
}