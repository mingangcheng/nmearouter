package com.aboni.nmea.router.filters;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.Sentence;

public class NMEARMC2VTGProcessorTest {

	@Test
	public void test() {
		NMEARMC2VTGProcessor p = new NMEARMC2VTGProcessor(2016);
		Sentence rmc = SentenceFactory.getInstance().createParser("$GPRMC,074128.000,A,4337.837,N,01017.600,E,5.9,345.9,220117,000.0,W,S*03");
		Sentence vtg = SentenceFactory.getInstance().createParser("$GPVTG,345.9,T,343.4,M,5.90,N,10.93,K,A*1F");
		Sentence[] res = p.process(rmc, "SRC");
		assertEquals(1, res.length);
		assertEquals(vtg, res[0]);
	}

	@Test
	public void testNeg() {
		NMEARMC2VTGProcessor p = new NMEARMC2VTGProcessor(2016);
		Sentence x = SentenceFactory.getInstance().createParser("$GPMTW,28.50,C*3B");
		Sentence[] res = p.process(x, "SRC");
		assertNull(res);
	}

}
