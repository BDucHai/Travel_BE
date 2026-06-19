package com.travel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tour_destinations")
public class TourDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tour nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    // Điểm đến nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(name = "day_order")
    private Integer dayOrder;

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    public TourDestination() {
    }

    public Long getId() {
        return id;
    }

    public Tour getTour() {
        return tour;
    }

    public Destination getDestination() {
        return destination;
    }

    public Integer getDayOrder() {
        return dayOrder;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setDayOrder(Integer dayOrder) {
        this.dayOrder = dayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}