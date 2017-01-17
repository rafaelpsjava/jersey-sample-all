## Author of this project : Rafael Peres dos Santos - Senior Developer and Java Architect ( even not certified yet ), 10 years of experience and has these Java certifications:  <br />
Sun Certified Java Programmer, <br />
Web Component Developer, <br />
Business Component Developer <br />


## This is a practical tecnical article for Senior Developer of how to build an enterprise java project using the best practices, was created in less than week. 
Only one layer is just the skelleton for the implementation even the other is just a sample of how to architect <br />
and how to make a project using layers of implementation that makes reuse of implementation(s) more easy and <br />
Layer = project<br />

Ejb-embedded-container is coupled with jee server and a minimal change in maven dependencies of project can stop it working. <br />
Use Jax-RS web service facade Instead of ejb-embedded-container; <br />
and invoke the ejb method(s) and implement a test case that use a restful client api. <br />


## Read: 
./tecnologies-description/multiple-projects <br />
this ilustrate this project structure <br />
./tecnologies-description/about-restful-providers <br />
this will give a few descriptions (what they offer) of the best current restful providers <br />	
./tecnologies-description/oauth <br />
this will give a few instructions of what is necessary to config the oauth <br />
./tecnologies_used_in_this_project <br />
the names of tecnologies and knowleges used in this sample project, <br />
this sample was implemented as easy as possible ( <br />
there are a lot of tecnologies used and a great content of knowlege envolved ... <br />
try make to make a Java Sênior Developer pratical article and you see... the content that i provide is the best.  <br />
this sample can be utilized as model to implement Jee enterprise web applications using some of best java ee practices <br />

Jersey do not provide ejb support by default you can implement some class like: <br />

public class ApplicationConfig extends ResourceConfig { <br />
	public ApplicationConfig() { <br />
		register(new AbstractBinder() { <br />
			@Override <br />
			protected void configure() { <br />
				bind(EjbContextInjectionResolver.class).to(new TypeLiteral<InjectionResolver<EjbLookup>>() { <br />
				}).in(Singleton.class); <br />
			} <br />
		}); <br />
		.... <br />
	} <br />
}<br />
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD }) <br />
@Retention(RetentionPolicy.RUNTIME) <br />
public @interface EjbLookup { <br />
	String name() default ""; <br />
	String mappedName() default ""; <br />
}
@Provider <br />
public class EjbContextInjectionResolver implements InjectionResolver<EjbLookup> { <br />
	... <br />
} <br />

// @Context <br />
// private Application application; <br />
// if (application != null) { <br />
// Map<String, Object> properties = application.getProperties(); <br />
// } <br />
