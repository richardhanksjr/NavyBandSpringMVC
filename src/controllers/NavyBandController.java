package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.NavyBandDAO;
import entities.Band;
import entities.CivilianRequest;
import entities.MilitaryRequest;

@Controller
public class NavyBandController {

	@Autowired
	private NavyBandDAO dao;
	
	@RequestMapping("test.do")
	public ModelAndView test(@RequestParam("id") int id){
		System.out.println("here");
		ModelAndView mv = new ModelAndView();
		Band band = dao.getBandById(id);
		System.out.println(band.getEmail());
		mv.addObject("band", band);
		mv.setViewName("index.jsp");
		return mv;
	}
	
	@RequestMapping("getAllMilitaryRequests.do")
	public ModelAndView getAllMilitaryRequests(){
		ModelAndView mv = new ModelAndView();
		List<MilitaryRequest> requests = dao.getAllMilitaryRequests();
		mv.addObject("requests", requests);
		for (MilitaryRequest request : requests) {
			System.out.println(request.getType());
		}
		mv.setViewName("index.jsp");
		return mv;
	}
	
	@RequestMapping("getAllCivilianRequests.do")
	public ModelAndView getAllCivilianRequests(){
		ModelAndView mv = new ModelAndView();
		List<CivilianRequest> requests = dao.getAllCivilianRequests();
		mv.addObject("requests", requests);
		for (CivilianRequest request : requests) {
			System.out.println(request.getTitle());
		}
		mv.setViewName("index.jsp");
		return mv;
	}
	
	//This is the method called when a new POC/User registers.  Takes in values for PointOfContact and Address(nessary to prevent
	//constraint violations)
	@RequestMapping("newPointOfContact.do")
	public ModelAndView newPointOfContact(String rank, String firstName, String lastName,
			String title, String street, String aptPoNumber, String city, 
			String state, String zip, String workPhone, String cellPhone,
			String homePhone, String email, String fax, String password, @RequestParam("passwordConfirm") String passwordConfirm){
		ModelAndView mv = new ModelAndView();
		//Check that non-null values aren't left blank.
		//If they are, send them back to the registration page with an error message to display.
		if(lastName == "" || email == "" || password == "" || firstName == "" || street == "" ||
				city == "" || state == "" || zip == ""){
			String errorMessage = "First Name, Last Name, Email, City, State, Zip, and Password are all required values.";
			mv.addObject("errorMessage", errorMessage);
			mv.setViewName("index.jsp");
			return mv;
		}else{
			//Check that the password they entered equals the confirmation password.
			if(!password.equals(passwordConfirm)){
				String passwordsDoNotMatchError = "Passwords Do Not Match.";
				mv.addObject("passwordMatchError", passwordsDoNotMatchError);
				mv.setViewName("index.jsp");
				return mv;
			}
		}
		//If the error validation works out, create a new PointOfContact
		int addNewPOC = dao.createNewPointOfContact(rank, firstName,  lastName,
										 title, street, aptPoNumber, city, 
										 state, zip, workPhone, cellPhone,
						 homePhone,  email, fax, password);
		//If the creation of a new POC comes back with a non-zero value, the email provided is already associated
		//with another account.
		if(addNewPOC != 0){
			String duplicateEmailError = "This email already exists for another user";
			mv.addObject("duplicateEmailError", duplicateEmailError);
			
		}
		mv.setViewName("index.jsp");
		
		return mv;
	}
//	@RequestMapping("getAllRequests")
//	public ModelAndView getAllRequests(){
//		ModelAndView mv = new ModelAndView();
//		List<Object> allRequests = new ArrayList<>();
//		List<CivilianRequest> civilianRequests = dao.getAllCivilianRequests();
//		List<MilitaryRequest> militaryRequests = dao.getAllMilitaryRequests();
//		allRequests.add(civilianRequests);
//		allRequests.add(militaryRequests);
//		for (Object request : allRequests) {
//			System.out.println(request.get);
//		}
//	}
	
	
}
