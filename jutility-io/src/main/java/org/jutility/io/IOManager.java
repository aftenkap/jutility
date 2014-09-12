package org.jutility.io;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public class IOManager {

    private final Map<Class<?>, Class<? extends IConverter>> converters;
    private final Set<Class<? extends ISerializer>>          serializers;


    private static IOManager                                 s_Instance;

    /**
     * Returns the singleton instance of the class.
     * 
     * @return the singleton instance.
     */
    public static IOManager Instance() {

        if (s_Instance == null) {

            s_Instance = new IOManager();
        }

        return s_Instance;
    }


    private IOManager() {

        this.converters = new LinkedHashMap<Class<?>, Class<? extends IConverter>>();
        this.serializers = new LinkedHashSet<Class<? extends ISerializer>>();
    }


    /**
     * Registers the provided {@link ISerializer serializer}.
     * 
     * @param serializer
     *            the {@link ISerializer serializer} to register.
     */
    public void registerSerializer(Class<? extends ISerializer> serializer) {

    }

    /**
     * Unregisters the provided {@link ISerializer serializer}.
     * 
     * @param serializer
     *            the {@link ISerializer serializer} to unregister.
     */
    public void unregisterSerializer(Class<? extends ISerializer> serializer) {

    }

    private Object invokeStaticMethod(String name, Class<?> clazz) {

        return this.invokeMethod(this.getMethod(name, clazz));
    }

    private Method getMethod(String name, Class<?> clazz) {

        Method method = null;
        try {
            method = clazz.getDeclaredMethod(name);

        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that does not declare static " + name
                    + "() method!");
        }
        catch (SecurityException e) {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that does not have accessible static "
                    + name + "() method!");
        }

        return method;
    }

    private Object invokeMethod(Method method) {

        Object returnValue = null;
        try {
            returnValue = method.invoke(null, (Object[]) null);
        }
        catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that cannot execute static "
                    + method.getName() + "() method!");
        }
        catch (IllegalArgumentException e) {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that cannot execute static "
                    + method.getName() + "() method!");
        }
        catch (InvocationTargetException e) {

            throw new IllegalArgumentException("Trying to register Layout "
                    + "Component class that cannot execute static "
                    + method.getName() + "() method!");
        }

        return returnValue;
    }

}
