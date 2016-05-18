package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.Band;
import entities.BookingStatus;
import entities.CivilianRequest;
import entities.DateOfEvent;
import entities.MilitaryRequest;
import entities.PointOfContact;

@Transactional
public class NavyBandJPADAO implements NavyBandDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public Band getBandById(int id){
		Band band = em.find(Band.class, id);
		System.out.println(band.getId());
		return band;
	}

	public List<MilitaryRequest> getAllMilitaryRequests(){
		String search = "select c from MilitaryRequest c";
		List<MilitaryRequest> requests = em.createQuery(search, MilitaryRequest.class).getResultList();
		return requests;
	}
	
	public List<CivilianRequest> getAllCivilianRequests(){
		String search = "select c from CivilianRequest c";
		List<CivilianRequest> requests = em.createQuery(search, CivilianRequest.class).getResultList();
		return requests;
	}
	
	//Create a new point of contact.  Point of contact is the main user for requesting gigs.
	//In the creation of a POC, an address will also be created and assigned and the band will be assigned to the 
	//band in question (should be band with id of 1, so that is hardcoded)
	public PointOfContact createNewPointOfContact(String rank, String firstName, String lastName,
										String title, String street, String aptPoNumber, String city, 
										String state, String zip, String workPhone, String cellPhone,
										String homePhone, String email, String fax, String password){
		
		List<PointOfContact> pointsOfContact = em.createQuery("select p from PointOfContact p", PointOfContact.class).getResultList();
		
		//Check that the email entered isn't an email that already exists for another user.  All users should have
		//a unique email.
		for (PointOfContact pointOfContact : pointsOfContact) {
			if(pointOfContact.getEmail().equals(email)){
				PointOfContact pc = null;
				return pc;
			}
		}
		Address address = new Address();
		address.setName(firstName+ " " +lastName);
		address.setStreet(street);
		address.setAptPoNumber(aptPoNumber);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		address.setBand(em.find(Band.class,  1));
		em.persist(address);
		PointOfContact poc = new PointOfContact();
		poc.setRank(rank);
		poc.setFirstName(firstName);
		poc.setLastName(lastName);
		poc.setTitle(title);
		poc.setAddress(address);
		poc.setWorkPhone(workPhone);
		poc.setCellPhone(cellPhone);
		poc.setHomePhone(homePhone); 
		//Set all new email addresses to lower case so that email log-ins are case INsensitive. (The log-in also puts emails to lower case)
		poc.setEmail(email.toLowerCase());
		poc.setFax(fax);
		poc.setPassword(password);
		em.persist(poc);
		return poc;
	}
	
	public void setCivilianBookingStatusToCancelled(int bookingId){
		CivilianRequest cr = em.find(CivilianRequest.class,  bookingId);
		BookingStatus bs = em.find(BookingStatus.class,  3);
		cr.setBookingStatus(bs);
	}
	
	public void updateMilitaryRequestInfo(String street, String aptPoNumber,
			String city, String state, String zip, String year, String description,
			String month, String day, String time, int dateOfEventId, int addressId, int militaryRequestId){
		Address address = em.find(Address.class, addressId);
		DateOfEvent dateOfEvent = em.find(DateOfEvent.class, dateOfEventId);
		MilitaryRequest militaryRequest = em.find(MilitaryRequest.class,  militaryRequestId);
		address.setStreet(street);
		address.setAptPoNumber(aptPoNumber);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		dateOfEvent.setYear(year);
		dateOfEvent.setMonth(month);
		dateOfEvent.setDay(day);
		dateOfEvent.setTime(time);
		militaryRequest.setDescription(description);
		
		
	}
	public void setMilitaryBookingStatusToCancelled(int bookingId){
		MilitaryRequest mr = em.find(MilitaryRequest.class,  bookingId);
		BookingStatus bs = em.find(BookingStatus.class,  3);
		mr.setBookingStatus(bs);
	}
	public PointOfContact getPointOfContactByEmail(String email){
		String query = "Select p from PointOfContact p where email = '" + email + "'";
		System.out.println(email);
		try{
			PointOfContact pc = em.createQuery(query, PointOfContact.class).getSingleResult();
			return pc;
		}catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
		 
	}
	
	//Method for getting the band from the database.
	public Band getBandByEmail(String email){
		String query = "Select b from Band b where email = '" + email + "'";
		try{
			Band band = em.createQuery(query, Band.class).getSingleResult();
			return band;
		}catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}
	
	public CivilianRequest getCivilianRequestById(int id){
		CivilianRequest civilianRequest;
		String query = "select c from CivilianRequest c where id = '" + id + "'";
		try{
		 civilianRequest = em.createQuery(query, CivilianRequest.class).getSingleResult();
		}catch(Exception e){
			 civilianRequest = null;
		}
		return civilianRequest;
	}
	
	public MilitaryRequest getMilitaryRequestById(int id){
		MilitaryRequest militaryRequest;
		String query = "select m from MilitaryRequest m where id = '" + id + "'";
		try{
		 militaryRequest = em.createQuery(query, MilitaryRequest.class).getSingleResult();
		}catch(Exception e){
			 militaryRequest = null;
		}
		return militaryRequest;
	}
//	public Band getBandById(int id){
//		String query = "select b from band b where id = '" + id + "'";
//		Band band = em.createQuery(query, Band.class).getSingleResult();
//		return band;
//	}
}
