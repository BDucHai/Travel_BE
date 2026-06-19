package com.travel.dto;

public class DashboardSummaryResponse {

    private long totalTours;
    private long publishedTours;
    private long draftTours;

    private long totalBlogs;
    private long publishedBlogs;
    private long draftBlogs;

    private long newContactMessages;
    private long processingContactMessages;
    private long doneContactMessages;

    private long pendingTestimonials;
    private long approvedTestimonials;
    private long rejectedTestimonials;

    private long activeBanners;
    private long totalDestinations;
    private long totalCountries;

    public DashboardSummaryResponse() {
    }

    public DashboardSummaryResponse(
            long totalTours,
            long publishedTours,
            long draftTours,
            long totalBlogs,
            long publishedBlogs,
            long draftBlogs,
            long newContactMessages,
            long processingContactMessages,
            long doneContactMessages,
            long pendingTestimonials,
            long approvedTestimonials,
            long rejectedTestimonials,
            long activeBanners,
            long totalDestinations,
            long totalCountries
    ) {
        this.totalTours = totalTours;
        this.publishedTours = publishedTours;
        this.draftTours = draftTours;
        this.totalBlogs = totalBlogs;
        this.publishedBlogs = publishedBlogs;
        this.draftBlogs = draftBlogs;
        this.newContactMessages = newContactMessages;
        this.processingContactMessages = processingContactMessages;
        this.doneContactMessages = doneContactMessages;
        this.pendingTestimonials = pendingTestimonials;
        this.approvedTestimonials = approvedTestimonials;
        this.rejectedTestimonials = rejectedTestimonials;
        this.activeBanners = activeBanners;
        this.totalDestinations = totalDestinations;
        this.totalCountries = totalCountries;
    }

    public long getTotalTours() {
        return totalTours;
    }

    public long getPublishedTours() {
        return publishedTours;
    }

    public long getDraftTours() {
        return draftTours;
    }

    public long getTotalBlogs() {
        return totalBlogs;
    }

    public long getPublishedBlogs() {
        return publishedBlogs;
    }

    public long getDraftBlogs() {
        return draftBlogs;
    }

    public long getNewContactMessages() {
        return newContactMessages;
    }

    public long getProcessingContactMessages() {
        return processingContactMessages;
    }

    public long getDoneContactMessages() {
        return doneContactMessages;
    }

    public long getPendingTestimonials() {
        return pendingTestimonials;
    }

    public long getApprovedTestimonials() {
        return approvedTestimonials;
    }

    public long getRejectedTestimonials() {
        return rejectedTestimonials;
    }

    public long getActiveBanners() {
        return activeBanners;
    }

    public long getTotalDestinations() {
        return totalDestinations;
    }

    public long getTotalCountries() {
        return totalCountries;
    }

    public void setTotalTours(long totalTours) {
        this.totalTours = totalTours;
    }

    public void setPublishedTours(long publishedTours) {
        this.publishedTours = publishedTours;
    }

    public void setDraftTours(long draftTours) {
        this.draftTours = draftTours;
    }

    public void setTotalBlogs(long totalBlogs) {
        this.totalBlogs = totalBlogs;
    }

    public void setPublishedBlogs(long publishedBlogs) {
        this.publishedBlogs = publishedBlogs;
    }

    public void setDraftBlogs(long draftBlogs) {
        this.draftBlogs = draftBlogs;
    }

    public void setNewContactMessages(long newContactMessages) {
        this.newContactMessages = newContactMessages;
    }

    public void setProcessingContactMessages(long processingContactMessages) {
        this.processingContactMessages = processingContactMessages;
    }

    public void setDoneContactMessages(long doneContactMessages) {
        this.doneContactMessages = doneContactMessages;
    }

    public void setPendingTestimonials(long pendingTestimonials) {
        this.pendingTestimonials = pendingTestimonials;
    }

    public void setApprovedTestimonials(long approvedTestimonials) {
        this.approvedTestimonials = approvedTestimonials;
    }

    public void setRejectedTestimonials(long rejectedTestimonials) {
        this.rejectedTestimonials = rejectedTestimonials;
    }

    public void setActiveBanners(long activeBanners) {
        this.activeBanners = activeBanners;
    }

    public void setTotalDestinations(long totalDestinations) {
        this.totalDestinations = totalDestinations;
    }

    public void setTotalCountries(long totalCountries) {
        this.totalCountries = totalCountries;
    }
}