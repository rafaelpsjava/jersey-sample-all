package br.com.company.projectname.util;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {

	private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);

	private static ClassUtil classUtil;

	private ClassUtil() {
	}

	public InputStream getResourceAsStream(String name) {
		InputStream is = null;
		try {
			is = getContextClassLoader().getResourceAsStream(name);
			if (is == null) {
				if (log.isTraceEnabled()) {
					log.trace("Resource [" + name + "] was not found via the thread context ClassLoader.  Trying the current ClassLoader...");
				}
				ClassLoader cl = getClassLoader();
				if (cl != null) {
					is = cl.getResourceAsStream(name);
				}
			}
			if (is == null) {
				if (log.isTraceEnabled()) {
					log.trace("Resource [" + name + "] was not found via the current class loader.  Trying the system/application ClassLoader...");
				}
				ClassLoader cl = getSystemClassLoader();
				if (cl != null) {
					is = cl.getResourceAsStream(name);
				}
			}
		} catch (Throwable throwable) {
		}
		if (is == null && log.isTraceEnabled()) {
			log.trace("Resource [" + name
					+ "] was not found via the thread context, current, or system/application ClassLoaders.  All heuristics have been exhausted.  Returning null.");
		}

		return is;
	}

	public Class<?> forName(String fqcn) throws RuntimeException {
		Class<?> clazz = null;
		try {
			clazz = getContextClassLoader().loadClass(fqcn);
			if (clazz == null) {
				if (log.isTraceEnabled()) {
					log.trace("Unable to load class named [" + fqcn + "] from the thread context ClassLoader.  Trying the current ClassLoader...");
				}
				clazz = getContextClassLoader().loadClass(fqcn);
			}
			if (clazz == null) {
				if (log.isTraceEnabled()) {
					log.trace("Unable to load class named [" + fqcn + "] from the current ClassLoader. Trying the system/application ClassLoader...");
				}
				clazz = getSystemClassLoader().loadClass(fqcn);
			}
		} catch (Throwable throwable) {
		}
		if (clazz == null) {
			String msg = "Unable to load class named [" + fqcn
					+ "] from the thread context, current, or system/application ClassLoaders.  All heuristics have been exhausted.  Class could not be found.";
			throw new RuntimeException(msg);
		}

		return clazz;
	}

	public ClassLoader getContextClassLoader() throws Throwable {
		return Thread.currentThread().getContextClassLoader();
	}

	public ClassLoader getClassLoader() throws Throwable {
		return ClassUtil.class.getClassLoader();
	}

	public ClassLoader getSystemClassLoader() throws Throwable {
		return ClassLoader.getSystemClassLoader();
	}

	public boolean isAvailable(String fullyQualifiedClassName) {
		try {
			forName(fullyQualifiedClassName);
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

	public Object newInstance(String fqcn) {
		return newInstance(forName(fqcn));
	}

	public Object newInstance(String fqcn, Object... args)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, RuntimeException {
		return newInstance(forName(fqcn), args);
	}

	public Object newInstance(Class<?> clazz) {
		if (clazz == null) {
			String msg = "Class method parameter cannot be null.";
			throw new IllegalArgumentException(msg);
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Unable to instantiate class [" + clazz.getName() + "]", e);
		}
	}

	public Object newInstance(Class<?> clazz, Object... args)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] argTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		Constructor<?> ctor = clazz.getConstructor(argTypes);
		return ctor.newInstance(args);
	}

	public static ClassUtil getInstance() {
		if (classUtil == null) {
			synchronized (ClassUtil.class) {
				if (classUtil == null) {
					classUtil = new ClassUtil();
				}
			}
		}

		return classUtil;
	}

	public static ClassUtil getNewInstance() {
		return new ClassUtil();
	}
}
