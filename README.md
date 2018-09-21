Design Pattern Examples
1. Factory Pattern
      
      1.1 BeanFactory
         
         Scan a directory and load all java class with @Bean annotation.
         Instantiate them with default field values.
         If there is a field with @Instance annotation, instantiate it.
         
         A JUnit test class shows how to use the factory.
	 
      1.2 ThreadPool
         
2. Proxy Pattern
      
      3.1 Dynamic Proxy
         
         using cglib to implement a before and after injection.
	 And it also support DI.
	

