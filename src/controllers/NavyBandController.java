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
		PointOfContact addNewPOC = dao.createNewPointOfContact(rank, firstName, lastName, title, street, aptPoNumber,
				city, state, zip, workPhone, cellPhone, homePhone, email, fax, password);
		// If the creation of a new POC comes back with a non-zero value, the
		// email provided is already associated
		// with another account.
		if (addNewPOC.equals(null)) {
			String duplicateEmailError = "This email already exists for another user";
			mv.addObject("duplicateEmailError", duplicateEmailError);

		}
		// Method call to get the Band object and assign it to the session.
		assignBandToSession(session);
		session.setAttribute("user", addNewPOC);
		mv.setViewName("main.jsp");

		return mv;
	}

	@RequestMapping("loadUserEdit.do")
	public ModelAndView loadEditUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editUser.jsp");
		return mv;
	}

	@RequestMapping("loadCivilianRequest.do")
	public ModelAndView loadCivilianRequest() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("civilianRequest.jsp");
		return mv;
	}

	// Get the booking id for a gig and set it's booking status to cancelled
	@RequestMapping("setCivilianBookingStatusToCancelled.do")
	public ModelAndView setCivilianBookingStatusToCancelled(@RequestParam("bookingId") int bookingId,
			@RequestParam("userEmail") String userEmail, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		dao.setCivilianBookingStatusToCancelled(bookingId);
		// Refresh the user's information and put that updated user in the
		// session
		// so current information displays.
		refreshUserInSession(session, userEmail);
		//Check the origin of the request and forward it to the viewName so response
		//from that page goes back to original location.
		
			
			mv.setViewName("main.jsp");
		
			List<CivilianRequest> civilianRequests = dao.getAllCivilianRequests();
			mv.addObject("civilianRequests", civilianRequests);
		
		

		return mv;
	}
	
	@RequestMapping("returnToBandPage.do")
	public String returnToBandPage(){
		return "band.jsp";
	}
	@RequestMapping("displayGigSheetMilitary.do")
	public ModelAndView displayGigSheetMilitary(int requestId){
		ModelAndView mv = new ModelAndView();
		MilitaryRequest militaryRequest = dao.getMilitaryRequestById(requestId);
		mv.addObject("militaryRequest", militaryRequest);
		mv.setViewName("gigSheet.jsp");
		return mv;
		
		
	}
	//Used by the band to set the booking status of a civilian event
	@RequestMapping("setCivilianBookingStatus.do")
	public ModelAndView setCivilianBookingStatus(int bookingId, int statusId){
		ModelAndView mv = new ModelAndView();
		dao.setCivilianBookingStatus(bookingId, statusId);
		mv.setViewName("band.jsp");
		return mv;
	}
	
	//Used by the band to set the booking status of a civilian event
	@RequestMapping("setMilitaryBookingStatus.do")
	public ModelAndView setMilitaryBookingStatus(int bookingId, int statusId){
		ModelAndView mv = new ModelAndView();
		dao.setMilitaryBookingStatus(bookingId, statusId);
		mv.setViewName("band.jsp");
		return mv;
	}

	// Get the booking id for a gig and set it's booking status to cancelled
	@RequestMapping("setMilitaryBookingStatusToCancelled.do")
	public ModelAndView setMilitaryBookingStatusToCancelled(@RequestParam("bookingId") int bookingId,
			@RequestParam("userEmail") String userEmail, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		dao.setMilitaryBookingStatusToCancelled(bookingId);
		// Refresh the user's information and put that updated user in the
		// session
		// so current information displays.
		refreshUserInSession(session, userEmail);
			mv.setViewName("main.jsp");
			List<MilitaryRequest> militaryRequests = dao.getAllMilitaryRequests();
			mv.addObject("militaryRequests", militaryRequests);
		

		return mv;

		
	}

	@RequestMapping("updateMilitaryRequestInfo.do")
	public ModelAndView updateMilitaryRequestInfo(String street, String aptPoNumber,
							String city, String state, String zip, String year, String description,
							String month, String day, String time, String submit, int dateOfEventId, 
							HttpSession session, String pointOfContactEmail, int addressId, int militaryRequestId, String origin){
		ModelAndView mv = new ModelAndView();
		if(submit.equals("cancel")){
			mv.setViewName(origin);
			return mv;
		}else{
			dao.updateMilitaryRequestInfo(street, aptPoNumber, city, state, zip, year, description,
					month, day, time, dateOfEventId, addressId, militaryRequestId);
			refreshUserInSession(session, pointOfContactEmail);
			mv.setViewName(origin);
			return mv;
		}
		
		
	}
	
	//Called by the band to see all military requests.
	@RequestMapping("viewMilitaryRequests.do")
	public ModelAndView viewMilitaryRequests(){
		ModelAndView mv = new ModelAndView();
		List<MilitaryRequest> militaryRequests = dao.getAllMilitaryRequests();
		mv.addObject("militaryRequests", militaryRequests);
		mv.setViewName("band.jsp");
		return mv;
	}
	
	//Called by the band to see all military requests.
	@RequestMapping("viewCivilianRequests.do")
	public ModelAndView viewCivilianRequests(){
		ModelAndView mv = new ModelAndView();
		List<CivilianRequest> civilianRequests = dao.getAllCivilianRequests();
		mv.addObject("civilianRequests", civilianRequests);
		mv.setViewName("band.jsp");
		return mv;
	}
	@RequestMapping("updateCivilianRequestInfo.do")
	public ModelAndView updateCivilianRequestInfo(String street, String aptPoNumber,
							String city, String state, String zip, String year, String description,
							String month, String day, String time, String submit, int dateOfEventId,
							HttpSession session, String pointOfContactEmail, int addressId, int militaryRequestId, String origin){
		ModelAndView mv = new ModelAndView();
		if(submit.equals("cancel")){
			mv.setViewName(origin);
			return mv;
		}else{
			dao.updateCivilianRequestInfo(street, aptPoNumber, city, state, zip, year, description,
					month, day, time, dateOfEventId, addressId, militaryRequestId);
			mv.setViewName(origin);
			refreshUserInSession(session, pointOfContactEmail);
			return mv;
		}
		
		
	}
	@RequestMapping("loadMilitaryRequest.do")
	public ModelAndView loadMilitaryRequest() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("militaryRequest.jsp");
		return mv;
	}
//Method for the user to book the band for a military-related event
	@RequestMapping("newMilitaryRequest.do")
	public ModelAndView newMilitaryRequest(String aptPoNumber, String city, String state,
							String zip, String year, String month, String day, String time,
							Boolean moveable, String street, String type, int pointOfContactId){
		ModelAndView mv = new ModelAndView();
		//Check if any non-null values are null.  If so, send the user back to the request screen.
		if(type.equals(null) || moveable.equals(null) || year.equals(null) || month.equals(null) || day.equals(null) || time.equals(null)){
			mv.setViewName("militaryRequest.jsp");
			String errorMessage = "Type of event, moveable date, year, month, day, and time are required values";
			mv.addObject("error", errorMessage);
			return mv;
		}
		
		dao.newMilitaryRequest(aptPoNumber, city, state, zip, year, month, day, time, moveable, street, type, pointOfContactId);
		mv.setViewName("main.jsp");
		return mv;
	}
	
	//Method for the user to book the band for a civilian-related event
		@RequestMapping("newCivilianRequest.do")
		public ModelAndView newCivilianRequest(String title, String aptPoNumber, String city, String state,
								String zip, String year, String month, String day, String time,
								Boolean moveable, String street, String type, int pointOfContactId, 
								Integer attendance, Boolean attending, String charges, Boolean governmentBacking,
								Boolean exclusive, Boolean meal, String description, String pointOfContactEmail, HttpSession session){
			ModelAndView mv = new ModelAndView();
			//Check if any non-null values are null.  If so, send the user back to the request screen.
//			if(title.equals(null) || moveable.equals(null) || year.equals(null) || month.equals(null) || day.equals(null) || time.equals(null)){
//				mv.setViewName("militaryRequest.jsp");
//				String errorMessage = "Title of event, moveable date, year, month, day, and time are required values";
//				mv.addObject("error", errorMessage);
//				return mv;
//			}
			
			dao.newCivilianRequest(title, aptPoNumber, city, state, zip, year, month, day, time, moveable, street, type, pointOfContactId,
									attendance, attending, charges, governmentBacking, exclusive, meal, description);
			mv.setViewName("main.jsp");
			refreshUserInSession(session, pointOfContactEmail);
			return mv;
		}
	// Method for logging a user into the system. Gets PointOfContact by email,
	// checks password, and adds
	// that POC to the session scope.
	@RequestMapping("userLogIn.do")
	public ModelAndView userLogIn(String email, String password, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// Method call to get the Band object and assign it to the session.

		assignBandToSession(session);
		// Check if the email entered is the band log-in
		if (email.equals(bandEmail)) {
			// band and pointOfContact emails are put to lower case both in the
			// creation (DAO) and here in the check so that
			// email log-ins are case INsensitive.
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
					// addUserToSession(session, pc);
					session.setAttribute("user", pc);
					mv.setViewName("main.jsp");
				}
			}
			return mv;
		}
	}

	private void refreshUserInSession(HttpSession session, String pointOfContactEmail) {

		session.removeAttribute("user");
		PointOfContact newUser = dao.getPointOfContactByEmail(pointOfContactEmail);
		session.setAttribute("user", newUser);

	}

	// Logs the user/band out and invalidates the session.
	@RequestMapping("logOut.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "index.jsp";
	}

	@RequestMapping("editCivilianRequest.do")
	public ModelAndView editCivilianRequest(@RequestParam("requestId") int id, String origin) {
		ModelAndView mv = new ModelAndView();
		CivilianRequest civilianRequest = dao.getCivilianRequestById(id);
		// If request comes back null, there was an error in querying the
		// database.
		// Send the user back to main.jsp
//		if (civilianRequest.equals(null)) {
//			mv.setViewName("main.jsp");
//			return mv;
//		} else {
			mv.addObject("request", civilianRequest);
			mv.addObject("origin", origin);
			mv.setViewName("editCivilianRequest.jsp");
			return mv;
//	}
	}

	@RequestMapping("assignUnitMilitary.do")
	public ModelAndView assignUnitMilitary(int unitId, int requestId){
		ModelAndView mv = new ModelAndView();
		dao.setUnitMilitary(unitId, requestId);
		mv.setViewName("band.jsp");
		return mv;
	}

	@RequestMapping("assignUnitCivilian.do")
	public ModelAndView assignUnitCivilian(int unitId, int requestId){
		ModelAndView mv = new ModelAndView();
		dao.setUnitCivilian(unitId, requestId);
		mv.setViewName("band.jsp");
		return mv;
	}
	@RequestMapping("editMilitaryRequest.do")
	public ModelAndView editMilitaryRequest(@RequestParam("requestId") int id, String origin) {
		ModelAndView mv = new ModelAndView();
		MilitaryRequest militaryRequest = dao.getMilitaryRequestById(id);
		// If request comes back null, there was an error in querying the
		// database.
		// Send the user back to main.jsp
		if (militaryRequest.equals(null)) {
			mv.setViewName(origin);
			return mv;
		} else {
			mv.addObject("origin", origin);
			mv.addObject("request", militaryRequest);
			mv.setViewName("editMilitaryRequest.jsp");
			return mv;
		}
	}

	private void assignBandToSession(HttpSession session) {
		Band band = dao.getBandById(1);
		session.setAttribute("band", band);
	}
}
