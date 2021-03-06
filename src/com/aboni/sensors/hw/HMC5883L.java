package com.aboni.sensors.hw;

import java.io.IOException;

import com.aboni.sensors.I2CInterface;
import com.aboni.sensors.SensorNotInititalizedException;

public class HMC5883L {

	private I2CInterface device;
	
	public HMC5883L(I2CInterface device) {
		this.device = device;
		scale = Scale.Gauss_1_30;
	}
	
    public static final int  HMC5883_I2CADDR = 0x1e;

    public static class Scale {
        private double scale;
        private int reg;

        private Scale(double s, int reg) { 
            this.scale = s;
            this.reg = reg; 
        }

        public int getReg() {
            return reg;
        }

        public double getScale() {
            return scale;
        }

        public static final Scale Gauss_0_88 = new Scale(0.73, 0x00);
        public static final Scale Gauss_1_30 = new Scale(0.92, 0x01);
        public static final Scale Gauss_1_90 = new Scale(1.22, 0x02);
        public static final Scale Gauss_2_50 = new Scale(1.52, 0x03);
        public static final Scale Gauss_4_00 = new Scale(2.27, 0x04);
        public static final Scale Gauss_4_70 = new Scale(2.56, 0x05);
        public static final Scale Gauss_5_60 = new Scale(3.03, 0x06);
        public static final Scale Gauss_8_10 = new Scale(4.65, 0x07);
    }

    public static class Constants {
        public static final int OUTPUT_RATE_0_75_Hz = 0;
        public static final int OUTPUT_RATE_1_5_Hz = 1;
        public static final int OUTPUT_RATE_3_Hz = 2;
        public static final int OUTPUT_RATE_7_5_Hz = 3;
        public static final int OUTPUT_RATE_15_Hz = 4;
        public static final int OUTPUT_RATE_30_Hz = 5;
        public static final int OUTPUT_RATE_75_Hz = 6;
        
        public static final int SAMPLES_AVERAGE_1 = 0;
        public static final int SAMPLES_AVERAGE_2 = 1;
        public static final int SAMPLES_AVERAGE_4 = 2;
        public static final int SAMPLES_AVERAGE_8 = 3;
        
        public static int ConfigurationRegisterA = 0x00;
    	public static int ConfigurationRegisterB = 0x01;
    	public static int ModeRegister = 0x02;
    	public static int AxisXDataRegisterMSB = 0x03;
		public static int AxisXDataRegisterLSB = 0x04;
    	public static int AxisZDataRegisterMSB = 0x05;
		public static int AxisZDataRegisterLSB = 0x06;
    	public static int AxisYDataRegisterMSB = 0x07;
		public static int AxisYDataRegisterLSB = 0x08;
		public static int StatusRegister = 0x09;
		public static int IdentificationRegisterA = 0x10;
		public static int IdentificationRegisterB = 0x11;
		public static int IdentificationRegisterC = 0x12;
	
    	public static int MeasurementContinuous = 0x00;
		public static int MeasurementSingleShot = 0x01;
		public static int MeasurementIdle = 0x03;

    	public static final int X = 0;
    	public static final int Y = 1;
    	public static final int Z = 2;
    }
    
    private Scale scale;

    public void setContinuousMode() throws IOException, SensorNotInititalizedException {
    	synchronized (this) {
    		device.write(Constants.ModeRegister, (byte)Constants.MeasurementContinuous);
    	}
    }
    
    public void enable() throws IOException, SensorNotInititalizedException {
        synchronized (this) {
            // set high freq 
            device.write(2, (byte)0);
            device.write(0, (byte)((Constants.SAMPLES_AVERAGE_8 << 5) + (Constants.OUTPUT_RATE_75_Hz << 2))); // # Set to 8 samples @ 15Hz
                                        
                                        
        }
    }

    public void setScale(Scale s) throws IOException, SensorNotInititalizedException {
    	synchronized (this) {
	        if (s==null) scale = Scale.Gauss_1_30;
	        else scale = s;
	        byte scale_reg = (byte) (scale.getReg() << 5);
	        device.write(Constants.ConfigurationRegisterB, scale_reg);
    	}
    }

    public int[] readMagnetometer() throws IOException {
    	synchronized (this) {
    	    
    	    byte[] data = new byte[6];
            int r = device.read(Constants.AxisXDataRegisterMSB, data, 0, 6);
            if (r != 6) {
                throw new IOException("Couldn't read compass data; r=" + r);
            }
            return new int[] { getWord(data, 0), getWord(data, 4), getWord(data, 2)}; 
    	}
    }
    
    private short getWord(byte[] b, int i) {
        short w = 0;

        byte high = (byte)(b[i + 0] & 0xFF);
        byte low  = (byte)(b[i + 1] & 0xFF);

        w = (short)(((high << 8) + low) & 0xFFFF); // Little endian

        if (w >= 0x8000)
            w = (short) -((0xFFFF - w) + 1);

        return w;        
    }
    
    public double[] getScaledMag() throws IOException {
        int[] m = readMagnetometer();
        return new double[] { 
                (m[Constants.X] != -4096)?(m[Constants.X] * scale.getScale()):0.0,
                (m[Constants.Y] != -4096)?(m[Constants.Y] * scale.getScale()):0.0,
                (m[Constants.Z] != -4096)?(m[Constants.Z] * scale.getScale()):0.0
        };
    }
}
