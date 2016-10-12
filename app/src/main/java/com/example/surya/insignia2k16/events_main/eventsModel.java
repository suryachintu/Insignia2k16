package com.example.surya.insignia2k16.events_main;

/**
 * Created by Surya on 06-10-2016.
 */
public class EventsModel {

    public String eventName,venue,co_od,event_time;
    int posterId,description;

    public EventsModel() {
    }


    public EventsModel(String eventName, String venue, String co_od, String event_time, int description, int posterId) {
        this.eventName = eventName;
        this.venue = venue;
        this.co_od = co_od;
        this.event_time = event_time;
        this.description = description;
        this.posterId = posterId;
    }

    public String getEvent_time() {
        return event_time;
    }

    public int getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public String getVenue() {
        return venue;
    }

    public String getCo_od() {
        return co_od;
    }

    public int getPosterId() {
        return posterId;
    }
}
