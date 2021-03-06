package com.aboni.nmea.router.agent;

import com.aboni.nmea.router.Startable;

public interface NMEAAgent extends Startable {
	
	String getName();
	String getDescription();
	
	boolean isBuiltIn();
	boolean isUserCanStartAndStop();
	
	void setStatusListener(NMEAAgentStatusListener listener);
	void unsetStatusListener();
    
	NMEASource getSource();
	NMEATarget getTarget();

	
}
