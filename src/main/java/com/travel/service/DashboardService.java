package com.travel.service;

import com.travel.dto.DashboardSummaryResponse;
import com.travel.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final TourRepository tourRepository;
    private final BlogRepository blogRepository;
    private final ContactMessageRepository contactMessageRepository;
    private final TestimonialRepository testimonialRepository;
    private final BannerRepository bannerRepository;
    private final DestinationRepository destinationRepository;
    private final CountryRepository countryRepository;

    public DashboardService(
            TourRepository tourRepository,
            BlogRepository blogRepository,
            ContactMessageRepository contactMessageRepository,
            TestimonialRepository testimonialRepository,
            BannerRepository bannerRepository,
            DestinationRepository destinationRepository,
            CountryRepository countryRepository
    ) {
        this.tourRepository = tourRepository;
        this.blogRepository = blogRepository;
        this.contactMessageRepository = contactMessageRepository;
        this.testimonialRepository = testimonialRepository;
        this.bannerRepository = bannerRepository;
        this.destinationRepository = destinationRepository;
        this.countryRepository = countryRepository;
    }

    public DashboardSummaryResponse getSummary() {
        long totalTours = tourRepository.count();
        long publishedTours = tourRepository.countByStatusAndIsActiveTrue("PUBLISHED");
        long draftTours = tourRepository.countByStatus("DRAFT");

        long totalBlogs = blogRepository.count();
        long publishedBlogs = blogRepository.countByStatus("PUBLISHED");
        long draftBlogs = blogRepository.countByStatus("DRAFT");

        long newContactMessages = contactMessageRepository.countByStatus("NEW");
        long processingContactMessages = contactMessageRepository.countByStatus("PROCESSING");
        long doneContactMessages = contactMessageRepository.countByStatus("DONE");

        long pendingTestimonials = testimonialRepository.countByStatus("PENDING");
        long approvedTestimonials = testimonialRepository.countByStatus("APPROVED");
        long rejectedTestimonials = testimonialRepository.countByStatus("REJECTED");

        long activeBanners = bannerRepository.countByIsActiveTrue();

        long totalDestinations = destinationRepository.count();
        long totalCountries = countryRepository.count();

        return new DashboardSummaryResponse(
                totalTours,
                publishedTours,
                draftTours,
                totalBlogs,
                publishedBlogs,
                draftBlogs,
                newContactMessages,
                processingContactMessages,
                doneContactMessages,
                pendingTestimonials,
                approvedTestimonials,
                rejectedTestimonials,
                activeBanners,
                totalDestinations,
                totalCountries
        );
    }
}