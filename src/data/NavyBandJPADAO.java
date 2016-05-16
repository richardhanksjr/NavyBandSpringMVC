package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Address;
import entities.Band;
import entities.CivilianRequest;
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
	public int createNewPointOfContact(String rank, String firstName, String lastName,
										String title, String street, String aptPoNumber, String city, 
										String state, String zip, String workPhone, String cellPhone,
										String homePhone, String email, String fax, String password){
		
		List<PointOfContact> pointsOfContact = em.createQuery("select p from PointOfContact p", PointOfContact.class).getResultList();
		
		//Check that the email entered isn't an email that already exists for another user.  All users should have
		//a unique email.
		for (PointOfContact pointOfContact : pointsOfContact) {
			if(pointOfContact.getEmail().equals(email)){
				return 1;
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
		poc.setEmail(email);
		poc.setFax(fax);
		poc.setPassword(password);
		em.persist(poc);
		return 0;
	}
}
