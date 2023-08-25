package de.emir.service.codecs.nmea0183.encoding.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.emir.service.codecs.nmea0183.encoding.sentence.ZDASentence;



public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				//.createEntityManagerFactory("persistenceUnitPGDrop");
				.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

//		test.loadXML();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createTracks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listMessages();

		System.out.println(".. done");
	}

	private void createTracks() {
		int numOfTrackItems = manager.createNativeQuery("Select a ZDASentence ", ZDASentence.class)
				.getResultList().size();
		if (numOfTrackItems == 0) {
			ZDASentence zda = new ZDASentence();
			zda.setTime(ParseUtils.parseTime("100911.111"));
			zda.setDay(24);
			zda.setMonth(03);
			zda.setYear(2015);
			zda.setLzdHours(00);
			zda.setLzdMinutes(00);
			
			manager.persist(zda);

		}
		
		if (numOfTrackItems == 0) {
			ZDASentence zda = new ZDASentence();
			zda.setTime(ParseUtils.parseTime("152512.123"));
			zda.setDay(12);
			zda.setMonth(8);
			zda.setYear(1991);
			zda.setLzdHours(00);
			zda.setLzdMinutes(00);
			
			manager.persist(zda);

		}
	}

	

	private void listMessages() {
		List<ZDASentence> resultList = manager.createNativeQuery("Select a From Track a", ZDASentence.class).getResultList();
		System.out.println("num of tracks:" + resultList.size());
		for (ZDASentence next : resultList) {
			System.out.println("next zda: " + next.toNMEA());
			System.out.println("Time: " + next.getTime());
			System.out.println("Day: " + next.getDay());
			System.out.println("Month: " + next.getMonth());
			System.out.println("Day: " + next.getYear());
			System.out.println("Day: " + next.getLzdHours());
			System.out.println("Day: " + next.getLzdMinutes());
		}
	}

}
