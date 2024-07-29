Steps to be Followed while building an Industry Standard StudentRegistrationAppliction

1.We divide the application programming in 3 layers
   a.Controller layer
   b.Service layer
   c.Database Access layer(Persistence layer / Data Access Layer(DAO layer)

2.Each layer is represented by separate packages.
   a. in.ineuron.controller
   b. in.ineuron.service
   c. in.ineuron.persistence 
   
3.In every layer we write a program in form of Interface ---> Its Implementing class

4.Beside 3 layer package we create several different package also

  a. in.ineuron.dto(data transfer object) --->
  
     To transfer the state of object form one layer to other.
     Since ResultSet we get from DAO layer is non-Serializable
     we need to copy all the content of ResultSet to User created 
     Serialized  DTO object.  
    
  b. in.ineuron.daofactory ---> 
     
     To access the DAO object from service layer we need DAO object.
     The service layer programmer need's to have complete info about 
     the implementation class with in the DAO layer and this is not 
     at all excepted as a good programming practice.So we created the 
     DAO factory.Whenever the service layer required the object it can 
     use the factory class to generate the object.Thus he without knowing 
     about the implementing class it can use the DAO method(Abstraction).
     
     eg :- Connection con = DriverManager.getConnection(url,user,pass);
           DriverManger is the factory to create the connection obj
           we don't know who has impl the connection Interface but
           still we get the object to use.
           This principle is called Abstraction.
           
   c. in.ineuron.servicefactory ---> 
      
      Reason same as above.    
      To get Service obj in controller layer.    
