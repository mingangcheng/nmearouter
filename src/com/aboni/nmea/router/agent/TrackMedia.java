package com.aboni.nmea.router.agent;

import com.aboni.geo.GeoPositionT;

public interface TrackMedia {
    void writePoint(GeoPositionT p, boolean anchor, double dist, double speed, double maxSpeed, int interval);
    boolean init();
    void dispose();
}
