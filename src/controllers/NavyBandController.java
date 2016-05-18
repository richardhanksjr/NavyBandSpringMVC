package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.NavyBandDAO;
import entities.Band;
import entities.CivilianRequest;
import entities.MilitaryRequest;
import entities.PointOfContact;

@Controller
public class NavyBandController {
	private final String bandEmail = "nbse.ops.fct@navy.mil";
	@Autowired
	private NavyBandDAO dao;

	@RequestMapping("test.do")
	public ModelAndView test(@RequestParam("id") int id) {
		System.out.println("here");
		ModelAndView mv = new ModelAndView();
		Band band = dao.getBandById(id);
		System.out.println(band.getEmail());
		mv.addObject("band", band);
		mv.setViewName("index.jsp");
		return mv;
	}

	@RequestMapping("getAllMilitaryRequests.do")
	public ModelAndView getAllMilitaryRequests() {
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
	public ModelAndView getAllCivilianRequests() {
		ModelAndView mv = new ModelAndView();
		List<CivilianRequest> requests = dao.getAllCivilianRequests();
		mv.addObject("requests", requests);
		for (CivilianRequest request : requests) {
			System.out.println(request.getTitle());
		}
		mv.setViewName("index.jsp");
		return mv;
	}

	// This is the method called when a new POC/User registers. Takes in values
	// for PointOfContact and Address(nessary to prevent
	// constraint violations)
	@RequestMapping("newPointOfContact.do")
	public ModelAndView newPointOfContact(String rank, String firstName, String lastName, String title, String street,
			String aptPoNumber, String city, String state, String zip, String workPhone, String cellPhone,
			String homePhone, String email, String fax, String password,
			@RequestParam("passwordConfirm") String passwordConfirm, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// Check that non-null values aren't left blank.
		// If they are, send them back to the registration page with an error
		// message to display.
		if (lastName == "" || email == "" || password == "" || firstName == "" || street == "" || city == ""
				|| state == "" || zip == "") {
			String errorMessage = "First Name, Last Name, Email, City, State, Zip, and Password are all required values.";
			mv.addObject("errorMessage", errorMessage);
			mv.setViewName("index.jsp");
			return mv;
		} else {
			// Check that the password they entered equals the confirmation
			// password.
			if (!password.equals(passwordConfirm)) {
				String passwordsDoNotMatchError = "Passwords Do Not Match.";
				mv.addObject("passwordMatchError", passwordsDoNotMatchError);
				mv.setViewName("index.jsp");
				return mv;
			}
		}
		// If the error validation works out, create a new PointOfContact
		PointOfContact addNewPOC = dao.createNewPointOfContact(rank, firstName, lastName, title, street, aptPoNumber, city, state,
				zip, workPhone, cellPhone, homePhone, email, fax, password);
		// If the creation of a new POC comes back with a non-zero value, the
		// email provided is already associated
		// with another account.
		if (addNewPOC.equals(null)) {
			String duplicateEmailError = "This email already exists for another user";
			mv.addObject("duplicateEmailError", duplicateEmailError);

		}
		//Method call to get the Band object and assign it to the session.
				assignBandToSession(session);
		session.setAttribute("user", addNewPOC);
		mv.setViewName("main.jsp");
		

		return mv;
	}

	@RequestMapping("loadUserEdit.do")
	public ModelAndView loadEditUser(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editUser.jsp");
		return mv;
	}
	@RequestMapping("loadCivilianRequest.do")
	public ModelAndView loadCivilianRequest(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("civilianRequest.jsp");
		return mv;
	}
	
	@RequestMapping("loadMilitaryRequest.do")
	public ModelAndView loadMilitaryRequest(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("militaryRequest.jsp");
		return mv;
	}
	// Method for logging a user into the system. Gets PointOfContact by email,
	// checks password, and adds
	// that POC to the session scope.
	@RequestMapping("userLogIn.do")
	public ModelAndView userLogIn(String email, String password, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//Method call to get the Band object and assign it to the session.
		assignBandToSession(session);
		// Check if the email entered is the band log-in
		if (email.equals(bandEmail)) {
			//band and pointOfContact emails are put to lower case both in the creation (DAO) and here in the check so that 
			//email log-ins are case INsensitive.
			Band band = dao.getBandByEmail(email.toLowerCase());
			// If Band comes back null, there was a database error. Send them
			// back to log-in with an error message.
			if (band.equals(null)) {
				String error = "Incorrect email/password";
				mv.addObject("error", error);
				mv.setViewName("index.jsp");
				return mv;
				// Else they are a band and everything is good. Add the band to
				// the session and send them to the band page.
			} else {
				// Check that the password entered matches that in the database
				if (!band.getPassword().equals(password)) {
					String error = "Invalid Password";
					mv.addObject("error", error);
					mv.setViewName("index.jsp");
					return mv;
				} else {
					session.setAttribute("band", band);
					mv.setViewName("band.jsp");
					return mv;
				}
			}
		} else {
			// Log-in is not associated with the band, check for a POC.
			PointOfContact pc = dao.getPointOfContactByEmail(email.toLowerCase());
			if (pc == null) {
				System.out.println("in error");
				String error = "Incorrect email/password";
				mv.addObject("error", error);
				mv.setViewName("index.jsp");
			} else {
				if (!pc.getPassword().equals(password)) {
					String error = "Invalid Password";
					mv.addObject("error", error);
					mv.setViewName("index.jsp");
					return mv;
				} else {
					session.setAttribute("user", pc);
					mv.setViewName("main.jsp");
				}
			}
			return mv;
		}
	}

		//Logs the user/band out and invalidates the session.
		@RequestMapping("logOut.do")
		public String logOut(HttpSession session){
			session.invalidate();
			return "index.jsp";
		}

		@RequestMapping("editCivilianRequest.do")
			public ModelAndView editCivilianRequest(@RequestParam("requestId") int id){
				ModelAndView mv = new ModelAndView();
				CivilianRequest civilianRequest = dao.getCivilianRequestById(id);
				//If request comes back null, there was an error in querying the database.
				//Send the user back to main.jsp
				if(civilianRequest.equals(null)){
					mv.setViewName("main.jsp");
					return mv;
			}else{
				mv.addObject("request", civilianRequest);
				mv.setViewName("editRequest.jsp");
				return mv;
			}
		}
private void assignBandToSession(HttpSession session){
	Band band = dao.getBandById(1);
	session.setAttribute("band",  band);
}
}
