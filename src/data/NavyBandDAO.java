package data;

import java.util.List;

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
	
}
