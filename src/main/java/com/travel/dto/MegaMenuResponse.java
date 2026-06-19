package com.travel.dto;

import java.util.List;

public class MegaMenuResponse {

    private VietnamTourMenu vietnamTour;
    private TravelInformationMenu travelInformation;

    public MegaMenuResponse() {
    }

    public MegaMenuResponse(
            VietnamTourMenu vietnamTour,
            TravelInformationMenu travelInformation
    ) {
        this.vietnamTour = vietnamTour;
        this.travelInformation = travelInformation;
    }

    public VietnamTourMenu getVietnamTour() {
        return vietnamTour;
    }

    public void setVietnamTour(VietnamTourMenu vietnamTour) {
        this.vietnamTour = vietnamTour;
    }

    public TravelInformationMenu getTravelInformation() {
        return travelInformation;
    }

    public void setTravelInformation(TravelInformationMenu travelInformation) {
        this.travelInformation = travelInformation;
    }

    public static class VietnamTourMenu {

        private List<MenuItemResponse> duration;
        private List<MenuItemResponse> styles;
        private List<MenuItemResponse> combined;

        public VietnamTourMenu() {
        }

        public VietnamTourMenu(
                List<MenuItemResponse> duration,
                List<MenuItemResponse> styles,
                List<MenuItemResponse> combined
        ) {
            this.duration = duration;
            this.styles = styles;
            this.combined = combined;
        }

        public List<MenuItemResponse> getDuration() {
            return duration;
        }

        public void setDuration(List<MenuItemResponse> duration) {
            this.duration = duration;
        }

        public List<MenuItemResponse> getStyles() {
            return styles;
        }

        public void setStyles(List<MenuItemResponse> styles) {
            this.styles = styles;
        }

        public List<MenuItemResponse> getCombined() {
            return combined;
        }

        public void setCombined(List<MenuItemResponse> combined) {
            this.combined = combined;
        }
    }

    public static class TravelInformationMenu {

        private List<MenuItemResponse> north;
        private List<MenuItemResponse> central;
        private List<MenuItemResponse> south;

        public TravelInformationMenu() {
        }

        public TravelInformationMenu(
                List<MenuItemResponse> north,
                List<MenuItemResponse> central,
                List<MenuItemResponse> south
        ) {
            this.north = north;
            this.central = central;
            this.south = south;
        }

        public List<MenuItemResponse> getNorth() {
            return north;
        }

        public void setNorth(List<MenuItemResponse> north) {
            this.north = north;
        }

        public List<MenuItemResponse> getCentral() {
            return central;
        }

        public void setCentral(List<MenuItemResponse> central) {
            this.central = central;
        }

        public List<MenuItemResponse> getSouth() {
            return south;
        }

        public void setSouth(List<MenuItemResponse> south) {
            this.south = south;
        }
    }
}