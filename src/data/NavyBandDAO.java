package data;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import entities.Band;
import entities.CivilianRequest;
import entities.MilitaryRequest;
import entities.PointOfContact;

public interface NavyBandDAO {
	public Band getBandById(int id);
	
	public List<MilitaryRequest> getAllMilitaryRequests();
	public List<CivilianRequest> getAllCivilianRequests();
	public PointOfContact createNewPointOfContact(String rank, String firstName, String lastName,
			String title, String street, String aptPoNumber, String city, 
			String state, String zip, String workPhone, String cellPhone,
			String homePhone, String email, String fax, String password);
	public PointOfContact getPointOfContactByEmail(String email);
	public Band getBandByEmail(String email);
	public CivilianRequest getCivilianRequestById(int id);
	public MilitaryRequest getMilitaryRequestById(int id);
	public void setCivilianBookingStatusToCancelled(int bookingId);
	public void setMilitaryBookingStatusToCancelled(int bookingId);
	public void updateMilitaryRequestInfo(String street, String aptPoNumber,
			String city, String state, String zip, String year, String description,
			String month, String day, String time, int dateOfEventId, int addressId, int militaryRequestId);
	public void updateCivilianRequestInfo(String street, String aptPoNumber,
			String city, String state, String zip, String year, String description,
			String month, String day, String time, int dateOfEventId, int addressId, int civilianRequestId);
	public void newMilitaryRequest(String aptPoNumber, String city, String state, String zip, String year, String month,
			String day, String time, Boolean moveable, String street, String type, int pointOfContactId);
	public void newCivilianRequest(String title, String aptPoNumber, String city, String state,
			String zip, String year, String month, String day, String time,
			Boolean moveable, String street, String type, int pointOfContactId, 
			Integer attendance, Boolean attending, String charges, Boolean governmentBacking,
			Boolean exclusive, Boolean meal, String description);
	public void setCivilianBookingStatus(int bookingId, int statusId);
	public void setMilitaryBookingStatus(int bookingId,  int statusId);
	public void setUnitMilitary(int unitId, int requestId);
	public void setUnitCivilian(int unitId, int requestId);
	
	
}
