package com.aboni.sensors;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aboni.utils.Constants;
import com.aboni.utils.ServerLog;

public class SensorProperties {

	private Properties prop;
	private long lastProp;
	
	public Properties getProperties() {
		return readConf();
	}
	
    protected Properties readConf() {
        try {
            File f = new File(Constants.SENSOR);
            if (f.exists() && f.lastModified() > lastProp) {
                ServerLog.getLogger().Info("Reading sensor configuration file");
                FileInputStream propInput = new FileInputStream(f);
                Properties p = new Properties();
                p.load(propInput);
                propInput.close();
                prop = p;
                lastProp = f.lastModified();
            }
            return prop;
        } catch (Exception e) {
            ServerLog.getLogger().Error("Cannot read sensors configuration!", e);
            return null;
        }
    }
}
