Author of this project : Rafael Peres dos Santos - Senior Developer and Java Architect ( even not certified yet ), 10 years of experience
and has these Java certifications:  {
	Sun Certified Java Programmer,
	Web Component Developer,
	Business Component Developer
}

This is a practical tecnical article for Senior Developer of how to build an enterprise java project using the best practices
Only one layer is just the skelleton for the implementation even the other is just a sample of how to architect 
and how to make a project using layers of implementation that makes reuse of implementation(s) more easy and 
Layer = project

Ejb-embedded-container is coupled with jee server and a minimal change in maven dependencies of project can stop it working. 
Use Jax-RS web service facade Instead of ejb-embedded-container; 
and invoke the ejb method(s) and implement a test case that use a restful client api. 


Read: {
	./tecnologies-description/multiple-projects
		this ilustrate this project structure
	,
	./tecnologies-description/about-restful-providers
		this will give a few descriptions (what they offer) of the best current restful providers	
	,
	./tecnologies-description/oauth
		this will give a few instructions of what is necessary to config the oauth 
	,
	./tecnologies_used_in_this_project
		the names of tecnologies and knowleges used in this sample project, 
		i implement this sample as easy as possible (
			there are a lot of tecnologies used and a great content of knowlege envolved ...
			try make to make a Java Sênior Developer pratical article and you see... the content that i provide is the best.  
			this sample can be utilized as model to implement Jee enterprise web applications using some of best java ee practices
		)
}




Jersey do not provide ejb support by default you can implement some class like:

public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(EjbContextInjectionResolver.class).to(new TypeLiteral<InjectionResolver<EjbLookup>>() {
				}).in(Singleton.class);
			}
		});
		....
	}
}
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EjbLookup {
	String name() default "";
	String mappedName() default "";
}
@Provider
public class EjbContextInjectionResolver implements InjectionResolver<EjbLookup> {
	...
}

// @Context
// private Application application;
// if (application != null) {
// Map<String, Object> properties = application.getProperties();
// }