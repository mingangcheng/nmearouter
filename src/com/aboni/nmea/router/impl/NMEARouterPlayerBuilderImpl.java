package com.aboni.nmea.router.impl;

import com.aboni.nmea.router.NMEACache;
import com.aboni.nmea.router.NMEARouter;
import com.aboni.nmea.router.NMEARouterBuilder;
import com.aboni.nmea.router.NMEAStream;
import com.aboni.nmea.router.agent.NMEAAgent;
import com.aboni.nmea.router.agent.NMEAConsoleTarget;
import com.aboni.nmea.router.agent.NMEAPlayer;
import com.aboni.nmea.router.agent.NMEASocketTarget;
import com.google.inject.Injector;

public class NMEARouterPlayerBuilderImpl implements NMEARouterBuilder {

    private NMEARouter router;
    private String playFile;
    private Injector injector;
    
    public NMEARouterPlayerBuilderImpl(Injector injector, String playFile) {
    	this.playFile = playFile;
    	this.injector = injector;
    }
    

	@Override
	public NMEARouter getRouter() {
		return router;
	}

	@Override
	public NMEARouterBuilder init() {
        router = injector.getInstance(NMEARouter.class);
        
        NMEAAgent sock = new NMEASocketTarget(
        		injector.getInstance(NMEACache.class), 
        		injector.getInstance(NMEAStream.class), 
        		"TCP", 1111, null);
        router.addAgent(sock);
        sock.start();

        NMEAConsoleTarget console = new NMEAConsoleTarget(
        		injector.getInstance(NMEACache.class), 
        		injector.getInstance(NMEAStream.class), 
        		"CONSOLE", null);
        router.addAgent(console);
        console.start();
        
        NMEAPlayer play = new NMEAPlayer(
        		injector.getInstance(NMEACache.class), 
        		injector.getInstance(NMEAStream.class), 
        		"PLAYER", null);
        play.setFile(playFile);
        router.addAgent(play);
        play.start();
        
        return this;
	}


}
