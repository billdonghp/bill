package com.ait.pu.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.pu.dao.conferenceroom.ConferenceRoomDAO;


public class PuDAOFactory {
	
	private static final String conferenceroomDAO = "conferenceroomDAO";
	private static final String visiterManagementDAO = "visiterManagementDAO";
	private static final String roomSetupDAO = "RoomSetupDAO";

	private static PuDAOFactory instance = new PuDAOFactory();

	private Map daos;

	public PuDAOFactory() {
		daos = new HashMap();
		daos.put(conferenceroomDAO, new ConferenceRoomDAO());
		daos.put(visiterManagementDAO, new VisiterManagementDAO());
		daos.put(roomSetupDAO, new RoomSetupDAO());
	}

	public ConferenceRoomDAO getConferenceRoomDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.pu.dao.PuDAOFactory.ConferenceRoomDAO");
		return (ConferenceRoomDAO) daos.get(conferenceroomDAO);
	}

	public static PuDAOFactory getInstance() {
		Logger.getLogger(PuDAOFactory.class)
				.debug("get PuDAOFactory instancs ");
		return instance;
	}
	
	public VisiterManagementDAO getVisiterManagementDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.pu.dao.VisiterManagementDAO");
		return (VisiterManagementDAO) daos.get(visiterManagementDAO);
	}
	
	public RoomSetupDAO getRoomSetupDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.pu.dao.roomSetupDAO");
		return (RoomSetupDAO) daos.get(roomSetupDAO);
	}
}
