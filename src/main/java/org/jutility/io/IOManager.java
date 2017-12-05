package org.jutility.io;

/*
 * #%L
 * jutility-io
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public class IOManager {

    @SuppressWarnings("unused")
    private final                             Map<Class<?>, Class<? extends
            IConverter>> converters;
    @SuppressWarnings("unused")
    private final                             Set<Class<? extends
            ISerializer>>          serializers;


    private static IOManager s_Instance;

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

        this.converters = new LinkedHashMap<>();
        this.serializers = new LinkedHashSet<>();
    }


    /**
     * Registers the provided {@link ISerializer serializer}.
     *
     * @param serializer
     *         the {@link ISerializer serializer} to register.
     */
    public void registerSerializer(Class<? extends ISerializer> serializer) {

        // TODO: implement
    }

    /**
     * Unregisters the provided {@link ISerializer serializer}.
     *
     * @param serializer
     *         the {@link ISerializer serializer} to unregister.
     */
    public void unregisterSerializer(Class<? extends ISerializer> serializer) {

        // TODO: implement
    }


    @SuppressWarnings("unused")
    private Method getMethod(String name, Class<?> clazz) {

        Method method;
        try {
            method = clazz.getDeclaredMethod(name);

        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    "Trying to register Layout " + "Component class that does "
                    + "not declare static " + name + "() method!");
        }
        catch (SecurityException e) {

            throw new IllegalArgumentException(
                    "Trying to register Layout " + "Component class that does "
                    + "not have accessible static " + name + "() method!");
        }

        return method;
    }

    @SuppressWarnings("unused")
    private Object invokeMethod(Method method) {

        try {

            return method.invoke(null, (Object[]) null);
        }
        catch (IllegalAccessException | IllegalArgumentException |
                InvocationTargetException e) {
            throw new IllegalArgumentException(
                    "Trying to register Layout " + "Component class that cannot"
                    + " execute static " + method.getName() + "() method!");
        }
    }
}
