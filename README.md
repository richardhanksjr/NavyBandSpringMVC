# NavyBandSpringMVC

This is a prototype of a website for US Navy bands to use in booking events.  The idea is for those seeking a Navy band for either a 
military-related or civilian-related gig to be able to create an account, request an event with the requisite information, view events
they've already requested as well as update the information on those events, cancel a request, and view the status of their 
request.  On the Navy Band/Admin side, they can sort requests by type, date, ensemble, etc.  In addition, they can assign ensembles/units
to an event, change the status, view/print "gig sheets", view the contact information for an event. They can manage the lifecycle
of the event from user request, through various stages of the request process, to assigning members of the organization to 
perform at the event, to completing after-action writeups. 

This program was written by Richard Hanks, including database design, Java JPA/Hibernate mapping of Java POJO (Plain Old Java Object) classes
to map to the database, a Spring MVC controller on the middle tier, and jsp/jstl on the front end as well as bootstrap css.

** Entities (POJO) can be found here: https://github.com/richardhanksjr/NavyBandSpring/tree/master/target/classes/entities
