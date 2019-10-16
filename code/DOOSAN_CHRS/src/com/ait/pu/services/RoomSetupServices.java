package com.ait.pu.services;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pu.dao.PuDAOFactory;
import com.ait.pu.dao.RoomSetupDAO;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class RoomSetupServices {
	
	public RoomSetupServices(){
		
	}
	public List RoomSetupList(Object parameterObject ,int currentPage , int pageSize) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		return roomSetupDAO.roomSetup(parameterObject, currentPage, pageSize);
	}
	
	public int RoomSetupCount(Object parameterObject) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		return roomSetupDAO.roomSetupCount(parameterObject);
	}
	
	public void saveRoomSetup(Object parameterObject) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		roomSetupDAO.saveRoomSetup(parameterObject);
	}
	
	public void saveUpdateRoomSetup(Object parameterObject) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		roomSetupDAO.saveUpdateRoomSetup(parameterObject);
	}
	
	public void delRoomSetup(Object parameterObject) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		roomSetupDAO.delRoomSetup(parameterObject);
	}
	
	public List getRoomSetup(Object parameterObject) throws GlRuntimeException{		
		RoomSetupDAO roomSetupDAO=(RoomSetupDAO)PuDAOFactory.getInstance().getRoomSetupDAO();
		return roomSetupDAO.getRoomSetup(parameterObject);
	}
}
