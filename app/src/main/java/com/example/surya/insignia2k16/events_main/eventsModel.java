package com.example.surya.insignia2k16.events_main;

/**
 * Created by Surya on 06-10-2016.
 */
public class EventsModel {

    public String eventName,venue,co_od,event_time;
    public int posterId,description,count;

    public EventsModel() {
    }


    public EventsModel(String eventName, String venue, String co_od, String event_time, int description, int posterId,int count) {
        this.eventName = eventName;
        this.venue = venue;
        this.co_od = co_od;
        this.event_time = event_time;
        this.description = description;
        this.posterId = posterId;
        this.count = count;
    }

}
