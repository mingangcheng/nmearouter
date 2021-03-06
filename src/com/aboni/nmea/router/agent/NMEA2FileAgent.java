package com.aboni.nmea.router.agent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.aboni.nmea.router.NMEACache;
import com.aboni.nmea.router.NMEAStream;
import com.aboni.nmea.router.impl.NMEAAgentImpl;
import com.aboni.nmea.sentences.NMEASentenceItem;
import com.aboni.utils.ServerLog;

import net.sf.marineapi.nmea.sentence.Sentence;

public class NMEA2FileAgent extends NMEAAgentImpl {

	private static final long DUMP_PERIOD = 10*1000;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	private long lastDump = 0;
	private List<NMEASentenceItem> queue = new LinkedList<>();
	
	public NMEA2FileAgent(NMEACache cache, NMEAStream stream, String n, QOS q) {
		super(cache, stream, n, q);
		setSourceTarget(false, true);
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	protected void doWithSentence(Sentence s, NMEAAgent source) {
		NMEASentenceItem e = new NMEASentenceItem(s, System.currentTimeMillis(), "  ");
		synchronized (queue) {
			if (isStarted()) {
				queue.add(e);
				try {
					dump();
				} catch (IOException e1) {
					ServerLog.getLogger().Error("Error dumping NMEA stream", e1);
				}
			}
		}
	}

	private void dump() throws IOException {
		long t = System.currentTimeMillis();
		if (t-lastDump > DUMP_PERIOD) {
			lastDump = t;
			File f = new File("nmea" + df.format(new Date()) + ".log");
			FileWriter w = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(w);
			for (NMEASentenceItem e: queue) {
				bw.write(e.toString());
			}
			queue.clear();
			bw.flush();
			bw.close();
			w.close();
		}
	}
	
}
