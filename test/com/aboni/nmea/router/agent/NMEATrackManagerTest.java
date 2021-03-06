package com.aboni.nmea.router.agent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.aboni.geo.GeoPositionT;
import com.aboni.geo.Utils;
import com.aboni.nmea.router.agent.TrackManager.TrackPoint;

import net.sf.marineapi.nmea.util.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NMEATrackManagerTest {
	
	//private static final long STATIC_DEFAULT_PERIOD = 30 * 60000; // 30 minutes;
	//private static final long DEFAULT_PERIOD = 60000; // 1 minute
	//private static final double STATIC_THRESHOLD = 15.0; // meters in the period
	//private static final double STATIC_THRESHOLD_TIME = 15 * 60000; // if static for more than x minutes set anchor mode

	private int period = 30; // seocnds
	private double lat;
	private double lon;
	private long t0;
	private long lastPosted;
	private TrackManager m;
	
	@Before
	public void setup() {
		m = new TrackManager(); 
		m.setPeriod(period * 1000);
		lat = 43.67830115349512;
		lon = 10.266444683074951;
		t0 = System.currentTimeMillis();
	}

	private TrackManager.TrackPoint postPosition(long ts, double speed) throws Exception {
		Position p = new Position(lat, lon);
		Position p1 = Utils.calcNewLL(p, -90, ((double)(ts-lastPosted)/60.0/60.0/1000.0) /*h*/ * speed /*kn*/);
		lat = p1.getLatitude();
		lon = p1.getLongitude();
		lastPosted = ts;
		return m.processPosition(new GeoPositionT(ts + t0, lat, lon), speed);
	}
	
	private List<TrackManager.TrackPoint> cruise(int seconds, double speed) throws Exception {
		int interval = 1000; // 1 second
		List<TrackManager.TrackPoint> out = new ArrayList<TrackManager.TrackPoint>();
		long start = lastPosted + interval;
		for (long t = start; t<=(seconds*1000)+start; t+=interval) {
			TrackManager.TrackPoint point = postPosition(t, speed);
			if (point!=null) {
				out.add(point);
			}
		}
		return out;
	}

	
	@Test
	public void testFirstPoint() throws Exception {
		TrackManager.TrackPoint p = postPosition(1000, 0.0);
		assertTrue(p==null); // first point is null because it is figuring out if it's anchored or not
	}
	
	@Test
	public void testSecondPointStationary() throws Exception {
		postPosition(1000, 0.0);  // first point is null because it is figuring out if it's anchored or not
		TrackManager.TrackPoint p = postPosition(2000, 0.0);
		assertTrue(p!=null);
		assertTrue(p.anchor);
	}
	
	@Test
	public void testSecondPointMoving() throws Exception {
		postPosition(1000, 0.0);  // first point is null because it is figuring out if it's anchored or not
		TrackManager.TrackPoint p = postPosition(2000, 5.0);
		assertTrue(p!=null);
		assertTrue(!p.anchor);
		assertEquals(5.0, p.averageSpeed, 0.1);
		assertEquals(0.001389, p.distance, 0.000001);
		assertEquals(43.678301, p.position.getLatitude(), 0.000001);
		assertEquals(10.266413, p.position.getLongitude(), 0.000001);
		assertEquals(2000 + t0, p.position.getTimestamp());
		
	}
	
	@Test
	public void testMaxSpeed() throws Exception {
		cruise(30 /* 30s */, 5.0);
		cruise(10 /* 10s */, 6.0);
		List<TrackPoint> l = cruise(20 /* 20s */, 5.0);
		TrackManager.TrackPoint p = l.get(0);
		assertEquals(6.00, p.maxSpeed, 0.01);
		assertEquals((5.0*20 + 6.0*(period-20))/period, p.averageSpeed, 0.01);
	}
	
	@Test
	public void testCruiseAt5Kn() throws Exception {
		List<TrackPoint> l = cruise(60 * 60 /* 1h */, 5.0);
		//System.out.println("Reported " + l.size() + "points");
		assertEquals(60/*m*/ * 60 / period, l.size());
		int counter = 0;
		for (TrackPoint p: l) {
			//System.out.println(p.period + " " + p.averageSpeed);
			assertTrue(!p.anchor);
			assertEquals(5.0, p.averageSpeed, 0.1);
			assertEquals((counter==0)?1:period, p.period);
			counter++;
		}
	}
	
	@Test
	public void testSetAnchor() throws Exception {
		cruise(10 * 60 /* 1m */, 5.0);
		List<TrackPoint> l = cruise(60 * 60 /* 1h */, 0.0);
		/*for (TrackPoint p: l) {
			System.out.println(p.period + " " + p.anchor + " " + p.averageSpeed);
		}*/
		assertTrue(l.get(l.size()-1).anchor);
		assertEquals(0.0, l.get(l.size()-1).averageSpeed, 0.1);
		assertEquals(30 * 60, l.get(l.size()-1).period);
	}
	
	
	@Test
	public void testLeaveAnchor() throws Exception {
		double s = 1.0;
		dump(cruise(10 * 60 /* 1m */, 5.0));
		dump(cruise(47 * 60 /* 1h */, 0.0)); // set anchor
		List<TrackPoint> l = cruise(10 * 60 /* 10m */, s);
		dump(l);
		int counter = 0;
		for (TrackPoint p: l) {
			assertTrue(!p.anchor);
			if (counter>0) {
				assertEquals(s, p.averageSpeed, 0.1);
				assertEquals(period, p.period); // first depends on how long it has been anchored
			}
			counter++;
		}
		assertTrue(counter>0);
	}

	private static SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
	static {
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	private void dump(List<TrackPoint> l) {
		for (TrackPoint p: l) {
			System.out.format("%d %.2f %d %s %n", 
					p.anchor?1:0, p.distance * 1852.0, p.period, fmt.format(new Date((p.position.getTimestamp() - t0))) );
		}
		
	}

	@Test
	public void testSlowDown() throws Exception {
		cruise(10 * 60 /* 10m */, 5.0);
		List<TrackPoint> l = cruise(10 * 60 /* 10m */, 0.0); //slow down for less than 15m
		assertTrue(!l.get(l.size()-1).anchor);
		assertEquals(period, l.get(l.size()-1).period);
	}

	@Test
	public void testSlowDownAndAccelerate() throws Exception {
		cruise(10 * 60 /* 10m */, 5.0);
		cruise(10 * 60 /* 10m */, 0.0); //slow down for less than 15m
		List<TrackPoint> l = cruise(60 * 60 /* 1h */, 5.0); //accelerate again
		for (TrackPoint p: l) {
			assertTrue(!p.anchor);
			assertEquals(5.0, p.averageSpeed, 0.1);
			assertEquals(period, p.period); // first depends on how long it has been anchored
		}
	}
	
	@Test
	public void testSwingAtAnchor() throws Exception {
		cruise(20 * 60 /* 20m */, 0.0); //solid anchor
		for (int i = 0; i<5*60; i++) { //swing for a few minutes with a period of 30s
			TrackPoint p = postPosition(lastPosted + 1000, 1.0 * Math.sin(2 * Math.PI * (i / 30)));
			if (p!=null) {
				assertTrue(p.anchor);
			}
		}
	}
}
