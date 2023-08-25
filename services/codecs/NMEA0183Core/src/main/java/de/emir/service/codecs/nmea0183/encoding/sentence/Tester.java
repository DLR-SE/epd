package de.emir.service.codecs.nmea0183.encoding.sentence;

import java.text.DecimalFormat;

import de.emir.service.codecs.nmea0183.encoding.data.Reference;
import de.emir.service.codecs.nmea0183.encoding.data.Status;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.POSSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.QDGSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TPPSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.proprietary.TVPSentence;
import de.emir.service.codecs.nmea0183.encoding.util.ParseUtils;

public class Tester {

	public static void main(String[] args) {
		SentenceFactory sentenceFactory = SentenceFactory.getInstance();
		//ROT
		ROTSentence rot = (ROTSentence) sentenceFactory.createSentence("$GPROT,35.6,A*4E");
		rot.parse();
		System.out.println(rot.toNMEA());
		System.out.println("ROT rot: " + rot.getRot());
		System.out.println("ROT status: " + rot.getStatus());
		//MMB
		MMBSentence mmb = (MMBSentence) sentenceFactory.createSentence("$WIMMB,5.5,I,6.6,B*4E");
		mmb.parse();
		System.out.println(mmb.toNMEA());
		System.out.println("MMB pressure in inches: " + mmb.getPressureInInches());
		System.out.println("MMB inches unit: " + mmb.getIncheUnit());
		System.out.println("MMB pressure in bars: " + mmb.getPressureInBars());
		System.out.println("MMB pressure bars unit: " + mmb.getBarUnit());
		//MWV
		MWVSentence mwv = new MWVSentence();
		mwv.setAngle(null);
		mwv.setReference(Reference.NULL);
		mwv.setSpeed(null);
		mwv.setSpeedUnit(de.emir.service.codecs.nmea0183.encoding.data.SpeedUnit.NULL);
		mwv.setStatus(Status.NULL);
		System.out.println(mwv.toNMEA());
		//MTA
		MTASentence mta = (MTASentence) sentenceFactory.createSentence("$WIMTA,25.5,C*4E");
		mta.parse();
		System.out.println(mta.toNMEA());
		System.out.println("MTA Temperature: " + mta.getTemperature());
		System.out.println("MMB Temperature unit: " + mta.getTemperatureUnit());
		//MHU
		MHUSentence mhu = new MHUSentence();
		System.out.println(mhu.toNMEA());
		mhu = (MHUSentence)sentenceFactory.createSentence("$WIMHU,25.5,30.2,35,C*4E");
		mhu.parse();
		System.out.println(mhu.toNMEA());
		//ParseuuTILITIES
		double x = 1.123;
		DecimalFormat df1 = new DecimalFormat("#.0");
		DecimalFormat df2 = new DecimalFormat("#.00");
		String s1 = df1.format(x);
		String s2 = df2.format(x);
		System.out.println("x: " + x);
		System.out.println("nearest to 0.1: " + s1);
		System.out.println("nearest to 0.01: " + s2);
		String s11 = ParseUtils.toString(x, 1);
		String s22 = ParseUtils.toString(x, 2);
		System.out.println("ParseUtil nearest to 0.1: " + s11);
		System.out.println("ParseUtil nearest to 0.01: " + s22);
		//MWD
		MWDSentence mwd = new MWDSentence();
		System.out.println(mwd.toNMEA());
		mwd = (MWDSentence)sentenceFactory.createSentence("$WIMWD,1.11,T,2.22,M,3.33,N,4.44,M*4E");
		mwd.parse();
		System.out.println(mwd.toNMEA());
		//TPP
		TPPSentence tpp = new TPPSentence();
		System.out.println(tpp.toNMEA());
		tpp = (TPPSentence)sentenceFactory.createSentence("$PTPP,135420.36,002111240,53.864897,6.943538,4,A*9A");
		tpp.parse();
		System.out.println(tpp.toNMEA());
		//TVP
		TVPSentence tvp = new TVPSentence();
		System.out.println(tvp.toNMEA());
		tvp = (TVPSentence)sentenceFactory.createSentence("$PTVP,100826.59,477224700,12,0.6,100,0.0003,V*1C");
		tvp.parse();
		System.out.println(tvp.toNMEA());
		//POS
		POSSentence pos = new POSSentence();
		System.out.println(pos.toNMEA());
		pos = (POSSentence)sentenceFactory.createSentence("$PPOS,112738.53,23.49406,-75.71733,51,5,A*1A");
		pos.parse();
		System.out.println(pos.toNMEA());
		//VEL
		VELSentence vel = new VELSentence();
		System.out.println(vel.toNMEA());
		vel = (VELSentence)sentenceFactory.createSentence("$PVEL,114500.01,7.2,0.2,44,1.17,A*6F");
		vel.parse();
		System.out.println(vel.toNMEA());
		//QDG
		QDGSentence qdg = new QDGSentence();
		System.out.println(qdg.toNMEA());
		qdg = (QDGSentence)sentenceFactory.createSentence("$PQDG,151420.40,270,4,3.5,0.5,A*7D");
		qdg.parse();
		System.out.println(qdg.toNMEA());
	}
}
