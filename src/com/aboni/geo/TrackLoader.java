package com.aboni.geo;

import java.util.Calendar;

public interface TrackLoader {

	boolean load(Calendar from, Calendar to);

	PositionHistory getTrack();

}