package org.jutility.common.reflection;



//@formatter:off
/*
 * #%L
 * jutility-common
 * %%
 * Copyright (C) 2013 - 2015 jutility.org
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
//@formatter:on


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * The {@code ReflectionUtils} class provides utility methods for
 * reflection-related tasks.
 *
 * @author Peter J. Radics
 * @version 0.1.4
 * @since 0.1.0
 */
public class ReflectionUtils {


    private static final Logger LOG = LoggerFactory
                                            .getLogger(ReflectionUtils.class);

    /**
     * Checks whether the provided {@link Field} has the provided {@link Class
     * type}.
     *
     * @param field
     *            the {@link Field}.
     * @param returnType
     *            the {@link Class return type}.
     * @return {@code true}, if the {@link Field} has the provided {@link Class
     *         return type}; {@code false} otherwise.
     */
    public static boolean hasType(final Field field, final Class<?> returnType) {

        return returnType == field.getType();
    }


    /**
     * Checks whether the provided {@link Field} has a type that can be assigned
     * to the provided {@link Class type}.
     *
     * @param field
     *            the {@link Field}.
     * @param type
     *            the {@link Class return type}.
     * @return {@code true}, if the {@link Field} can be assigned to the
     *         provided {@link Class type}; {@code false} otherwise.
     */
    public static boolean canBeAssignedToType(final Field field,
            final Class<?> type) {

        return type.isAssignableFrom(field.getType());
    }

    /**
     * Checks whether the provided {@link Field} has a type that can be assigned
     * by the provided {@link Class type}.
     *
     * @param field
     *            the {@link Field}.
     * @param type
     *            the {@link Class return type}.
     * @return {@code true}, if the {@link Field} can be assigned by objects of
     *         the provided {@link Class type}; {@code false} otherwise.
     */
    public static boolean canBeAssignedByType(final Field field,
            final Class<?> type) {

        return type.isAssignableFrom(field.getType());
    }


    /**
     * Checks whether the provided {@link Method} has the provided {@link Class
     * return type}.
     *
     * @param method
     *            the {@link Method}.
     * @param returnType
     *            the {@link Class return type}.
     * @return {@code true}, if the {@link Method} has the provided
     *         {@link Class return type}; {@code false} otherwise.
     */
    public static boolean hasReturnType(final Method method,
            final Class<?> returnType) {

        return returnType == method.getReturnType();
    }


    /**
     * Checks whether the provided {@link Method} has a return type that can be
     * assigned to the provided {@link Class type}.
     *
     * @param method
     *            the {@link Method}.
     * @param returnType
     *            the {@link Class return type}.
     * @return {@code true}, if the {@link Method} has the provided
     *         {@link Class return type}; {@code false} otherwise.
     */
    public static boolean hasCompatibleReturnType(final Method method,
            final Class<?> returnType) {

        return returnType.isAssignableFrom(method.getReturnType());
    }

    /**
     * Determines whether the provided {@link Method} has the provided
     * {@link Class parameter types}.
     *
     * @param method
     *            the {@link Method}.
     * @param parameterTypes
     *            the {@link Class parameter types}.
     * @return {@code true}, if the {@link Method} has the provided
     *         {@link Class parameter types}; {@code false} otherwise.
     */
    public static boolean hasParameterTypes(final Method method,
            final Collection<Class<?>> parameterTypes) {

        final Class<?>[] methodParameterTypes = method.getParameterTypes();

        if (parameterTypes == null) {

            return (methodParameterTypes == null)
                    || (methodParameterTypes.length == 0);
        }
        else if (methodParameterTypes != null) {

            final boolean sameSize = parameterTypes.size() == methodParameterTypes.length;

            if (sameSize) {
                int i = 0;
                for (final Class<?> parameterType : parameterTypes) {

                    if (parameterType != methodParameterTypes[i]) {

                        return false;
                    }
                    i++;
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Determines whether {@link Class parameter types} provided are compatible
     * to those of the provided {@link Method}.
     *
     * @param method
     *            the {@link Method}.
     * @param parameterTypes
     *            the {@link Class parameter types}.
     * @return {@code true}, if the {@link Class parameter types} provided are
     *         compatible to those of the provided {@link Method}; {@code false}
     *         otherwise.
     */
    public static boolean hasCompatibleParameterTypes(final Method method,
            final Collection<Class<?>> parameterTypes) {

        final Class<?>[] methodParameterTypes = method.getParameterTypes();

        if (parameterTypes == null) {

            return methodParameterTypes == null;
        }
        else if (methodParameterTypes != null) {

            final boolean sameSize = parameterTypes.size() == methodParameterTypes.length;

            if (sameSize) {
                int i = 0;
                for (final Class<?> parameterType : parameterTypes) {

                    if (!methodParameterTypes[i]
                            .isAssignableFrom(parameterType)) {

                        return false;
                    }
                    i++;
                }
                return true;
            }
        }

        return false;
    }



    /**
     * Attempts to find a {@link Field} with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param fieldName
     *            the name of the {@link Field}.
     * @return the {@link Field} with the provided name.
     * @throws ReflectionException
     *             if no {@link Field} with that name exists in the
     *             {@link Class}.
     */
    public static Field getField(final Class<?> type, final String fieldName)
            throws ReflectionException {

        Field field = null;

        try {

            field = type.getDeclaredField(fieldName);
        }
        catch (final NoSuchFieldException e) {

            ReflectionUtils.LOG.error("No field with signature "
                    + ReflectionUtils.buildSignature(null, type, Object.class,
                            fieldName, null) + " exists!");
            throw new ReflectionException("No field with signature "
                    + ReflectionUtils.buildSignature(null, type, Object.class,
                            fieldName, null) + " exists!");
        }
        catch (final SecurityException e) {

            ReflectionUtils.LOG.error("Could not retrieve field "
                    + ReflectionUtils.buildSignature(null, type, Object.class,
                            fieldName, null)
                    + " due to insufficient accessibility!");
            throw new ReflectionException("Could not retrieve field "
                    + ReflectionUtils.buildSignature(null, type, Object.class,
                            fieldName, null)
                    + " due to insufficient accessibility!");
        }

        return field;
    }



    /**
     * Attempts to find a {@link Constructor} with the provided parameter types.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param parameterTypes
     *            the parameter types of the {@link Constructor}.
     * @return the {@link Constructor} with the provided parameter types.
     * @throws ReflectionException
     *             if a matching constructor was not found.
     */
    public static Constructor<?> getConstructor(final Class<?> type,
            final Collection<Class<?>> parameterTypes)
            throws ReflectionException {


        final Constructor<?>[] constructors = type.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {

            final Class<?>[] constructorParameterTypes = constructor
                    .getParameterTypes();

            int size = 0;

            if (parameterTypes != null) {

                size = parameterTypes.size();
            }

            if (size == constructorParameterTypes.length) {

                boolean parametersMatch = true;

                if (size > 0) {

                    int i = 0;
                    for (final Class<?> parameterType : parameterTypes) {

                        if (parameterType != constructorParameterTypes[i]) {

                            if (constructorParameterTypes[i].isPrimitive()) {

                                if (!ReflectionUtils
                                        .isAssignablePrimitiveToBoxed(
                                                constructorParameterTypes[i],
                                                parameterType)) {


                                    parametersMatch = false;
                                    break;
                                }
                            }
                            else {
                                
                                parametersMatch = false;
                                break;
                            }
                        }

                        i++;
                    }
                }
                else {

                    parametersMatch = true;
                }

                if (parametersMatch) {

                    return constructor;
                }
            }
        }

        ReflectionUtils.LOG.error("Could not find constructor "
                + ReflectionUtils.buildSignature(null, null, null,
                        type.getCanonicalName(), parameterTypes) + "!");
        throw new ReflectionException("Could not find constructor "
                + ReflectionUtils.buildSignature(null, null, null,
                        type.getCanonicalName(), parameterTypes) + "!");
    }


    private static boolean isAssignablePrimitiveToBoxed(
            final Class<?> primitive, final Class<?> boxed) {

        if (primitive.equals(java.lang.Boolean.TYPE)) {

            return (boxed.equals(java.lang.Boolean.class));
        }
        if (primitive.equals(java.lang.Byte.TYPE)) {

            return (boxed.equals(java.lang.Byte.class));
        }
        if (primitive.equals(java.lang.Character.TYPE)) {

            return (boxed.equals(java.lang.Character.class));
        }
        if (primitive.equals(java.lang.Double.TYPE)) {

            return (boxed.equals(java.lang.Double.class));
        }
        if (primitive.equals(java.lang.Float.TYPE)) {

            return (boxed.equals(java.lang.Float.class));
        }
        if (primitive.equals(java.lang.Integer.TYPE)) {

            return (boxed.equals(java.lang.Integer.class));
        }
        if (primitive.equals(java.lang.Long.TYPE)) {

            return (boxed.equals(java.lang.Long.class));
        }
        if (primitive.equals(java.lang.Short.TYPE)) {

            return (boxed.equals(java.lang.Short.class));
        }

        return false;
    }

    /**
     * Attempts to find a {@link Method} with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param methodName
     *            the name of the {@link Method}.
     * @param parameterTypes
     *            the parameter types of the method.
     * @return the {@link Method} with the provided name or {@code null}, if no
     *         {@link Method} with that name exists in the {@link Class}.
     * @throws ReflectionException
     *             if no method with th
     */
    public static Method getMethod(final Class<?> type,
            final String methodName, final Collection<Class<?>> parameterTypes)
            throws ReflectionException {

        return ReflectionUtils
                .getMethod(type, null, methodName, parameterTypes);
    }

    /**
     * Attempts to find a {@link Method} with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param returnType
     *            the desired return type of the class.
     * @param methodName
     *            the name of the {@link Method}.
     * @param parameterTypes
     *            the parameter types of the method.
     * @return the {@link Method} with the provided name.
     * @throws ReflectionException
     *             if no method with the provided signature exists in the
     *             provided type.
     */
    public static Method getMethod(final Class<?> type,
            final Class<?> returnType, final String methodName,
            final Collection<Class<?>> parameterTypes)
            throws ReflectionException {

        if (methodName == null) {
            final List<Method> methods = ReflectionUtils.getMethods(type,
                    returnType, parameterTypes);

            return methods.get(0);
        }

        Method method = null;
        Method method2 = null;

        try {

            if (parameterTypes == null) {
                method2 = type.getMethod(methodName, new Class<?>[0]);
            }
            else {
                method2 = type.getMethod(methodName,
                        parameterTypes.toArray(new Class<?>[0]));
            }
            method = type.getMethod(methodName);


        }
        catch (final NoSuchMethodException e) {

            ReflectionUtils.LOG.error("No method with signature "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            methodName, parameterTypes) + " exists!");
            throw new ReflectionException("No method with signature "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            methodName, parameterTypes) + " exists!");
        }
        catch (final SecurityException e) {

            ReflectionUtils.LOG.error("Could not retrieve method "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            methodName, parameterTypes)
                    + " due to insufficient accessibility!");
            throw new ReflectionException("Could not retrieve method "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            methodName, parameterTypes)
                    + " due to insufficient accessibility!");
        }


        if (!ReflectionUtils.hasCompatibleReturnType(method, returnType)) {

            ReflectionUtils.LOG.error("Method "
                    + ReflectionUtils.getSignature(method)
                    + " has incompatible return type!");
            throw new ReflectionException("Method "
                    + ReflectionUtils.getSignature(method)
                    + " has incompatible return type!");
        }
        if (!ReflectionUtils.hasParameterTypes(method, parameterTypes)) {

            ReflectionUtils.LOG.error("Method "
                    + ReflectionUtils.getSignature(method)
                    + " has incompatible parameter types!");
            throw new ReflectionException("Method "
                    + ReflectionUtils.getSignature(method)
                    + " has incompatible parameter types!");
        }

        return method2;
    }

    /**
     * Attempts to find a {@link Method} with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param returnType
     *            the desired return type of the class.
     * @param parameterTypes
     *            the parameter types of the method.
     * @return a {@link Method} with the provided signature.
     * @throws ReflectionException
     *             if no method with the provided signature exists in the
     *             provided type.
     */
    public static List<Method> getMethods(final Class<?> type,
            final Class<?> returnType, final Collection<Class<?>> parameterTypes)
            throws ReflectionException {

        final List<Method> methods = new LinkedList<Method>();

        final Method[] classMethods = type.getMethods();

        for (final Method method : classMethods) {

            if (ReflectionUtils.hasCompatibleReturnType(method, returnType)
                    && ReflectionUtils.hasCompatibleParameterTypes(method,
                            parameterTypes)) {

                methods.add(method);
            }
        }

        if (!methods.isEmpty()) {

            return methods;
        }
        else {

            ReflectionUtils.LOG.error("Could not find method "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            null, parameterTypes) + "!");
            throw new ReflectionException("Could not find method "
                    + ReflectionUtils.buildSignature(null, type, returnType,
                            null, parameterTypes) + "!");
        }
    }

    /**
     * Attempts to find a {@link Method} or accessor with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param methodOrPropertyName
     *            the name of the {@link Method} or property.
     * @return the {@link Method} with the provided name or {@code null}, if no
     *         {@link Method} or property with that name exist in the
     *         {@link Class}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Method getMethodOrAccessor(final Class<?> type,
            final String methodOrPropertyName)
            throws ReflectionException {

        try {

            return ReflectionUtils.getMethod(type, Object.class,
                    methodOrPropertyName, null);
        }
        catch (final ReflectionException e) {

            ReflectionUtils.LOG.debug(
                    "Is not a method " + methodOrPropertyName, e);
            return ReflectionUtils.getAccessor(type, methodOrPropertyName);
        }
    }

    /**
     * Attempts to find a {@link Method} or mutator with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param methodOrPropertyName
     *            the name of the {@link Method} or property.
     * @return the {@link Method} with the provided name or {@code null}, if no
     *         {@link Method} or property with that name exist in the
     *         {@link Class}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Method getMethodOrMutator(final Class<?> type,
            final String methodOrPropertyName)
            throws ReflectionException {

        final Collection<Class<?>> parameterTypes = new LinkedList<Class<?>>();
        parameterTypes.add(Object.class);

        try {

            return ReflectionUtils.getMethod(type, Object.class,
                    methodOrPropertyName, parameterTypes);

        }
        catch (final ReflectionException e) {

            return ReflectionUtils.getMutator(type, methodOrPropertyName);
        }
    }

    /**
     * Attempts to find the name of the accessor (getter) for the {@link Field}
     * with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param fieldName
     *            the name of the {@link Field}.
     * @return the name of the accessor for the {@link Field} with the provided
     *         name.
     * @throws ReflectionException
     *             if an exception occurs during introspection.
     */
    public static String getAccessorName(final Class<?> type,
            final String fieldName)
            throws ReflectionException {

        final Method accessor = ReflectionUtils.getAccessor(type, fieldName);

        return accessor.getName();
    }

    /**
     * Attempts to find the name of the accessor (getter) for the provided
     * {@link Field}.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param field
     *            the {@link Field}.
     * @return the name of the accessor for the {@link Field} with the provided
     *         name.
     * @throws ReflectionException
     *             if an exception occurs during introspection.
     */
    public static String getAccessorName(final Class<?> type, final Field field)
            throws ReflectionException {

        return ReflectionUtils.getAccessorName(type, field.getName());
    }


    /**
     * Attempts to find the accessor (getter) for the {@link Field} with the
     * provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param fieldName
     *            the name of the {@link Field}.
     * @return the accessor for the {@link Field} with the provided name.
     * @throws ReflectionException
     *             if an exception occurs during introspection.
     */
    public static Method getAccessor(final Class<?> type, final String fieldName)
            throws ReflectionException {

        final String beanFieldName = Introspector.decapitalize(fieldName);

        BeanInfo info = null;
        try {

            info = Introspector.getBeanInfo(type);
        }
        catch (final IntrospectionException e) {

            ReflectionUtils.LOG.error(
                    "Could not find Java Bean information for type " + type, e);
            throw new ReflectionException(
                    "Could not find Java Bean information for type " + type, e);
        }

        for (final PropertyDescriptor pd : info.getPropertyDescriptors()) {

            Method getter = null;
            if (pd.getName().equals(beanFieldName)) {

                getter = pd.getReadMethod();
                if (getter != null) {

                    return getter;
                }
            }
        }

        final String capitalizedFieldName = Character.toUpperCase(beanFieldName
                .charAt(0)) + beanFieldName.substring(1);
        String getterName = "get" + capitalizedFieldName;

        try {

            return type.getDeclaredMethod(getterName, (Class<?>) null);
        }
        catch (final NoSuchMethodException e) {

            // Nothing to do here.
        }
        catch (final SecurityException e) {

            ReflectionUtils.LOG.error("Could not access method " + getterName
                    + " in class " + type + "!", e);
            throw new ReflectionException("Could not access method "
                    + getterName + " in class " + type + "!", e);
        }

        getterName = "is" + capitalizedFieldName;

        try {

            return type.getDeclaredMethod(getterName, (Class<?>) null);
        }
        catch (final NoSuchMethodException e) {

            // Nothing to do here.
        }
        catch (final SecurityException e) {

            ReflectionUtils.LOG.error("Could not access method " + getterName
                    + " in class " + type + "!", e);
            throw new ReflectionException("Could not access method "
                    + getterName + " in class " + type + "!", e);
        }

        ReflectionUtils.LOG.error("Could not find accessor for field "
                + fieldName);
        throw new ReflectionException("Could not find accessor for field "
                + fieldName);
    }

    /**
     * Attempts to find the accessor (getter) for the provided {@link Field}.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param field
     *            the {@link Field}.
     * @return the accessor for the provided {@link Field} or {@code null}, if
     *         no accessor exists.
     * @throws ReflectionException
     *             if an exception occurs during introspection.
     */
    public static Method getAccessor(final Class<?> type, final Field field)
            throws ReflectionException {

        return ReflectionUtils.getAccessor(type, field.getName());
    }

    /**
     * Attempts to find the name of the mutator (setter) for the {@link Field}
     * with the provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param fieldName
     *            the name of the {@link Field}.
     * @return the name of the mutator for the {@link Field} with the provided
     *         name or {@code null}, if no mutator exists.
     * @throws ReflectionException
     *             if an exception occurs during introspection.
     */
    public static String getMutatorName(final Class<?> type,
            final String fieldName)
            throws ReflectionException {

        final Method mutator = ReflectionUtils.getMutator(type, fieldName);

        return mutator.getName();
    }


    /**
     * Attempts to find the name of the mutator (setter) for the provided
     * {@link Field}.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param field
     *            the {@link Field}.
     * @return the name of the mutator for the {@link Field} with the provided
     *         name.
     * @throws ReflectionException
     *             if an exception occurs during retrieval.
     */
    public static String getMutatorName(final Class<?> type, final Field field)
            throws ReflectionException {

        return ReflectionUtils.getMutatorName(type, field.getName());
    }


    /**
     * Attempts to find the mutator (setter) for the {@link Field} with the
     * provided name.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param fieldName
     *            the name of the {@link Field}.
     * @return the mutator for the {@link Field} with the provided name.
     * @throws ReflectionException
     *             if an exception occurs during retrieval.
     */
    public static Method getMutator(final Class<?> type, final String fieldName)
            throws ReflectionException {

        final String beanFieldName = Introspector.decapitalize(fieldName);

        BeanInfo info;
        try {

            info = Introspector.getBeanInfo(type);
        }
        catch (final IntrospectionException e) {

            ReflectionUtils.LOG.error(
                    "Could not find Java Bean information for type " + type, e);
            throw new ReflectionException(
                    "Could not find Java Bean information for type " + type, e);
        }

        Method setter = null;
        for (final PropertyDescriptor pd : info.getPropertyDescriptors()) {

            if (pd.getName().equals(beanFieldName)) {

                setter = pd.getWriteMethod();
                if (setter != null) {

                    return setter;
                }
            }
        }


        final String capitalizedFieldName = Character.toUpperCase(beanFieldName
                .charAt(0)) + beanFieldName.substring(1);
        final String setterName = "set" + capitalizedFieldName;

        try {

            for (final Method method : type.getDeclaredMethods()) {

                if (method.getName().equals(setterName)
                        && (method.getParameterTypes().length == 1)) {

                    return method;
                }
            }
        }
        catch (final SecurityException e) {

            ReflectionUtils.LOG.error("Could not access method " + setterName
                    + " in class " + type + "!", e);
            throw new ReflectionException("Could not access method "
                    + setterName + " in class " + type + "!", e);
        }


        ReflectionUtils.LOG.error("Could not find mutator for field "
                + fieldName);
        throw new ReflectionException("Could not find mutator for field "
                + fieldName);
    }

    /**
     * Attempts to find the mutator (setter) for the provided {@link Field}.
     *
     * @param type
     *            the {@link Class} to examine.
     * @param field
     *            the {@link Field}.
     * @return the mutator for the {@link Field}.
     * @throws ReflectionException
     *             if an exception occurs during retrieval.
     */
    public static Method getMutator(final Class<?> type, final Field field)
            throws ReflectionException {

        return ReflectionUtils.getMutator(type, field.getName());
    }



    /**
     * Attempts to cast the provided value to the provided {@link Class return
     * type}.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param returnType
     *            the desired {@link Class return type}.
     * @param value
     *            the value to cast.
     * @return the value cast to the {@link Class return type}.
     * @throws ReflectionException
     *             if the cast was not successful.
     */
    public static <T> T castValue(final Class<T> returnType, final Object value)
            throws ReflectionException {

        if (returnType.isAssignableFrom(value.getClass())) {

            return returnType.cast(value);
        }

        ReflectionUtils.LOG.error("Could not cast value of type "
                + value.getClass().getCanonicalName() + " to type "
                + returnType.getCanonicalName() + "!");
        throw new ReflectionException("Could not cast value of type "
                + value.getClass().getCanonicalName() + " to type "
                + returnType.getCanonicalName() + "!");
    }

    /**
     * Attempts to retrieve the value of a field, property, or method of a
     * provided object.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param source
     *            the source object.
     * @param fieldOrAccessorOrMethod
     *            the field, accessor, or method name.
     * @param returnType
     *            the desired return type.
     * @return the value of the field, accessor, or method.
     * @throws ReflectionException
     *             if the return type of the field, accessor, or method is not
     *             compatible with the desired return type.
     */
    public static <T> T getValue(final Object source,
            final String fieldOrAccessorOrMethod,
            final Class<? extends T> returnType)
            throws ReflectionException {


        final Object returnValue = ReflectionUtils.getValue(source,
                fieldOrAccessorOrMethod);

        if (returnValue == null) {

            return null;
        }

        if (returnType.isAssignableFrom(returnValue.getClass())) {

            return returnType.cast(returnValue);
        }
        else {

            ReflectionUtils.LOG
                    .error("Return type "
                            + returnValue.getClass()
                            + " of provided field or method not compatible with desired return type "
                            + returnType + "!");
            throw new ReflectionException(
                    "Return type "
                            + returnValue.getClass()
                            + " of provided field or method not compatible with desired return type "
                            + returnType + "!");
        }

    }

    /**
     * Attempts to retrieve the value of a field, property, or method of a
     * provided object.
     *
     * @param source
     *            the source object.
     * @param fieldOrAccessorOrMethod
     *            the field, accessor, or method name.
     * @return the value of the field, accessor, or method.
     * @throws ReflectionException
     *             if the return type of the field, accessor, or method is not
     *             compatible with the desired return type.
     */
    public static Object getValue(final Object source,
            final String fieldOrAccessorOrMethod)
            throws ReflectionException {

        Method method = null;
        Field field = null;
        try {

            method = ReflectionUtils.getMethodOrAccessor(source.getClass(),
                    fieldOrAccessorOrMethod);

        }
        catch (final ReflectionException e) {

            ReflectionUtils.LOG.info(
                    "Couldn't get Methor or accessor with name "
                            + fieldOrAccessorOrMethod, e);
        }

        try {

            field = ReflectionUtils.getField(source.getClass(),
                    fieldOrAccessorOrMethod);

        }
        catch (final ReflectionException e) {

            ReflectionUtils.LOG.info("Couldn't get Field with name "
                    + fieldOrAccessorOrMethod, e);
        }


        return ReflectionUtils.getValue(source, field, method);

    }

    /**
     * Attempts to retrieve the value of the provided {@link Field} in the
     * provided {@link Object}. If the {@link Field} is not accessible, the
     * method attempts to override its accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @return the value of the {@link Field}.
     * @throws ReflectionException
     *             if retrieval fails.
     */
    public static Object getValue(final Object source, final Field field)
            throws ReflectionException {

        return ReflectionUtils.getValue(source, field, true);
    }

    /**
     * Attempts to retrieve the value of the provided {@link Field} in the
     * provided {@link Object}.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Field}.
     * @return the value of the {@link Field}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Object getValue(final Object source, final Field field,
            final boolean overrideAccessibility)
            throws ReflectionException {

        Object returnValue = null;

        final boolean accessibilityChanged = false;

        if (overrideAccessibility) {

            ReflectionUtils.setAccessibility(field, true);
        }

        try {

            returnValue = field.get(source);
        }
        catch (final NullPointerException e) {

            throw new ReflectionException(
                    "Could not retrieve value of non-static field "
                            + ReflectionUtils.getSignature(field)
                            + " without an instance!");
        }
        catch (final IllegalArgumentException e) {

            throw new ReflectionException("Could not retrieve value of field "
                    + ReflectionUtils.getSignature(field)
                    + " from object with type " + source.getClass() + "!");
        }
        catch (final IllegalAccessException e) {

            throw new ReflectionException("Could not retrieve value of field "
                    + ReflectionUtils.getSignature(field)
                    + " due to insufficient accessibility!");
        }

        if (accessibilityChanged) {

            ReflectionUtils.setAccessibility(field, false);
        }

        return returnValue;
    }

    /**
     * Attempts to retrieve the value of the provided {@link Field} in the
     * provided {@link Object}. If the {@link Field} is not accessible, the
     * method attempts to override its accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @return the value of the {@link Field}.
     * @throws ReflectionException
     *             if retrieval fails.
     */
    public static Object getValue(final Object source, final Field field,
            final Method accessor)
            throws ReflectionException {

        return ReflectionUtils.getValue(source, field, accessor, true);
    }


    /**
     * Attempts to retrieve the value of the provided {@link Field} in the
     * provided {@link Object}. If the {@link Field} or {@link Method accessor}
     * are not accessible, the method attempts to override the accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @param preferAccessor
     *            whether or not to prefer retrieving the value through the
     *            {@link Method accessor} as opposed to through the
     *            {@link Field}.
     * @return the value of the {@link Field}.
     * @throws ReflectionException
     *             if retrieval fails.
     */
    public static Object getValue(final Object source, final Field field,
            final Method accessor, final boolean preferAccessor)
            throws ReflectionException {

        return ReflectionUtils.getValue(source, field, accessor,
                preferAccessor, true);
    }


    /**
     * Attempts to retrieve the value of the provided {@link Field} in the
     * provided {@link Object}.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @param preferAccessor
     *            whether or not to prefer retrieving the value through the
     *            {@link Method accessor} as opposed to through the
     *            {@link Field}.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Method accessor} and {@link Field}.
     * @return the value of the {@link Field}.
     * @throws ReflectionException
     *             if retrieval fails.
     */
    public static Object getValue(final Object source, final Field field,
            final Method accessor, final boolean preferAccessor,
            final boolean overrideAccessibility)
            throws ReflectionException {

        if ((field == null) && (accessor == null)) {

            throw new ReflectionException(
                    "Cannot retrieve value without a field or accessor!");
        }

        Object returnValue = null;

        if (preferAccessor) {

            if (accessor != null) {

                returnValue = ReflectionUtils.invokeMethod(source, accessor,
                        null, overrideAccessibility);
            }
            else if (field != null) {

                returnValue = ReflectionUtils.getValue(source, field,
                        overrideAccessibility);
            }
        }
        else {

            if (field != null) {

                returnValue = ReflectionUtils.getValue(source, field,
                        overrideAccessibility);
            }
            else if (accessor != null) {

                returnValue = ReflectionUtils.invokeMethod(source, accessor,
                        null, overrideAccessibility);
            }
        }

        return returnValue;
    }


    /**
     * Attempts to retrieve the value collection of the provided {@link Field}
     * in the provided {@link Object}. If the {@link Field} or {@link Method
     * accessor} are not accessible, the method attempts to override the
     * accessibility. By default, use of the {@link Method accessor} is
     * preferred.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @return the value collection of the {@link Field}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Collection<?> getValueCollection(final Object source,
            final Field field, final Method accessor)
            throws ReflectionException {

        return ReflectionUtils.getValueCollection(source, field, accessor,
                true, true);
    }

    /**
     * Attempts to retrieve the value collection of the provided {@link Field}
     * in the provided {@link Object}. If the {@link Field} or {@link Method
     * accessor} are not accessible, the method attempts to override the
     * accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @param preferAccessor
     *            whether or not to prefer retrieving the value through the
     *            {@link Method accessor} as opposed to through the
     *            {@link Field}.
     * @return the value collection of the {@link Field}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Collection<?> getValueCollection(final Object source,
            final Field field, final Method accessor,
            final boolean preferAccessor)
            throws ReflectionException {

        return ReflectionUtils.getValueCollection(source, field, accessor,
                preferAccessor, true);
    }

    /**
     * Attempts to retrieve the value collection of the provided {@link Field}
     * in the provided {@link Object}.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param accessor
     *            the {@link Method accessor}.
     * @param preferAccessor
     *            whether or not to prefer retrieving the value through the
     *            {@link Method accessor} as opposed to through the
     *            {@link Field}.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Method accessor} and {@link Field}.
     * @return the value collection of the {@link Field}.
     * @throws ReflectionException
     *             if the retrieval fails.
     */
    public static Collection<?> getValueCollection(final Object source,
            final Field field, final Method accessor,
            final boolean preferAccessor, final boolean overrideAccessibility)
            throws ReflectionException {

        final Object fieldValue = ReflectionUtils.getValue(source, field,
                accessor, preferAccessor, overrideAccessibility);

        if (fieldValue instanceof Collection<?>) {

            return (Collection<?>) fieldValue;
        }

        throw new ReflectionException("Field "
                + ReflectionUtils.getSignature(field) + " and accessor "
                + ReflectionUtils.getSignature(accessor)
                + " do not return a collection of values!");
    }


    /**
     * Attempts to set the value of the provided {@link Field} in the provided
     * {@link Object}. If the {@link Field} is not accessible, the method
     * attempts to override its accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param value
     *            the desired value of the {@link Field}.
     * @throws ReflectionException
     *             if setting the field fails.
     */
    public static void setValue(final Object source, final Field field,
            final Object value)
            throws ReflectionException {

        ReflectionUtils.setValue(source, field, value, true);
    }


    /**
     * Attempts to set the value of the provided {@link Field} in the provided
     * {@link Object}.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param value
     *            the desired value of the {@link Field}.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Field}.
     * @throws ReflectionException
     *             if setting the field fails.
     */
    public static void setValue(final Object source, final Field field,
            final Object value, final boolean overrideAccessibility)
            throws ReflectionException {


        boolean accessibilityChanged = false;

        if (overrideAccessibility) {

            accessibilityChanged = ReflectionUtils
                    .setAccessibility(field, true);
        }

        try {

            field.set(source, value);
        }
        catch (final NullPointerException e) {

            throw new ReflectionException(
                    "Could not set value of non-static field "
                            + ReflectionUtils.getSignature(field)
                            + " without an instance!");
        }
        catch (final IllegalArgumentException e) {

            throw new ReflectionException("Could not set value of field "
                    + ReflectionUtils.getSignature(field)
                    + " from object with type " + value.getClass() + "!");
        }
        catch (final IllegalAccessException e) {

            throw new ReflectionException("Could not set value of field "
                    + ReflectionUtils.getSignature(field)
                    + " due to insufficient accessibility!");
        }
        catch (final ExceptionInInitializerError e) {

            throw new ReflectionException(
                    "Initialization caused by setting value of field "
                            + ReflectionUtils.getSignature(field)
                            + " caused an exception!");
        }

        if (accessibilityChanged) {

            ReflectionUtils.setAccessibility(field, false);
        }

    }

    /**
     * Attempts to set the value of the provided {@link Field} in the provided
     * {@link Object}. If the {@link Field} or {@link Method mutator} are not
     * accessible, the method attempts to override the accessibility. By
     * default, use of the {@link Method mutator} is preferred.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param mutator
     *            the {@link Method mutator}.
     * @param value
     *            the desired value of the {@link Field}.
     * @throws ReflectionException
     *             if setting the field fails.
     */
    public static void setValue(final Object source, final Field field,
            final Method mutator, final Object value)
            throws ReflectionException {

        ReflectionUtils.setValue(source, field, mutator, value, true);
    }

    /**
     * Attempts to set the value of the provided {@link Field} in the provided
     * {@link Object}. If the {@link Method mutator} and/or {@link Field} are
     * not accessible, the method attempts to override the accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param mutator
     *            the {@link Method mutator}.
     * @param value
     *            the desired value of the {@link Field}.
     * @param preferMutator
     *            whether or not to prefer setting the value through the
     *            {@link Method mutator} as opposed to through the {@link Field}
     *            .
     * @throws ReflectionException
     *             if setting the field fails.
     */
    public static void setValue(final Object source, final Field field,
            final Method mutator, final Object value,
            final boolean preferMutator)
            throws ReflectionException {

        ReflectionUtils.setValue(source, field, mutator, value, preferMutator,
                true);
    }

    /**
     * Attempts to set the value of the provided {@link Field} in the provided
     * {@link Object}. If the {@link Field} is not accessible, the method
     * attempts to override its accessibility.
     *
     * @param source
     *            the {@link Object} to modify.
     * @param field
     *            the {@link Field} to set.
     * @param mutator
     *            the {@link Method mutator}.
     * @param value
     *            the desired value of the {@link Field}.
     * @param preferMutator
     *            whether or not to prefer setting the value through the
     *            {@link Method mutator} as opposed to through the {@link Field}
     *            .
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Method mutator} and {@link Field}.
     * @throws ReflectionException
     *             if setting the value fails.
     */
    public static void setValue(final Object source, final Field field,
            final Method mutator, final Object value,
            final boolean preferMutator, final boolean overrideAccessibility)
            throws ReflectionException {

        if ((field == null) && (mutator == null)) {

            throw new ReflectionException(
                    "Cannot set value without a field or accessor!");
        }

        if (preferMutator) {

            if (mutator != null) {

                final List<Object> parameterList = new LinkedList<Object>();
                parameterList.add(value);
                ReflectionUtils.invokeMethod(source, mutator, parameterList,
                        overrideAccessibility);
            }
            else if (field != null) {

                ReflectionUtils.setValue(source, field, value,
                        overrideAccessibility);
            }
        }
        else {

            if (field != null) {

                ReflectionUtils.setValue(source, field, value,
                        overrideAccessibility);
            }
            else if (mutator != null) {

                final List<Object> parameterList = new LinkedList<Object>();
                parameterList.add(value);
                ReflectionUtils.invokeMethod(source, mutator, parameterList,
                        overrideAccessibility);
            }
        }
    }

    /**
     * Attempts to invoke the provided {@link Method} on the provided object.
     *
     * @param source
     *            the object on which the {@link Method} is to be invoked.
     * @param method
     *            the {@link Method} to invoke.
     * @param methodParameters
     *            the {@link Method Method's} parameters.
     * @return the result of the method invocation.
     * @throws ReflectionException
     *             if the {@link Method} invocation fails.
     */
    public static Object invokeMethod(final Object source, final Method method,
            final Collection<?> methodParameters)
            throws ReflectionException {

        return ReflectionUtils.invokeMethod(source, method, methodParameters,
                true);
    }

    /**
     * Attempts to invoke the provided {@link Method} on the provided object.
     *
     * @param source
     *            the object on which the {@link Method} is to be invoked.
     * @param method
     *            the {@link Method} to invoke.
     * @param methodParameters
     *            the {@link Method Method's} parameters.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Field}.
     * @return the result of the method invocation.
     * @throws ReflectionException
     *             if the {@link Method} invocation fails.
     */
    public static Object invokeMethod(final Object source, final Method method,
            final Collection<?> methodParameters,
            final boolean overrideAccessibility)
            throws ReflectionException {

        Object returnValue = null;

        boolean accessibilityChanged = false;

        if (overrideAccessibility) {

            accessibilityChanged = ReflectionUtils.setAccessibility(method,
                    true);
        }

        try {

            if (methodParameters != null) {

                returnValue = method.invoke(source,
                        methodParameters.toArray(new Object[0]));
            }
            else {

                returnValue = method.invoke(source);
            }
        }
        catch (final IllegalAccessException e) {

            throw new ReflectionException("Could not invoke method "
                    + ReflectionUtils.getSignature(method)
                    + " due to insufficient accessibility!", e);
        }
        catch (final IllegalArgumentException e) {

            throw new ReflectionException("Method "
                    + ReflectionUtils.getSignature(method)
                    + " could not be executed with parameter(s) "
                    + methodParameters + "!", e);
        }
        catch (final InvocationTargetException e) {

            throw new ReflectionException("Invocation of method "
                    + ReflectionUtils.getSignature(method)
                    + " resulted in an exception!", e);
        }


        if (accessibilityChanged) {

            ReflectionUtils.setAccessibility(method, true);
        }
        return returnValue;
    }

    /**
     * Attempts to create a new instance of the provided {@link Class type}.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param type
     *            the {@link Class type}
     * @param constructorParameters
     *            the {@link Constructor} parameters.
     * @return a new instance of the {@link Class type}.
     * @throws ReflectionException
     *             if a new instance could not be initialized.
     */
    public static <T> T createInstance(final Class<? extends T> type,
            final Object[] constructorParameters)
            throws ReflectionException {

        return ReflectionUtils
                .createInstance(type, constructorParameters, true);
    }

    /**
     * Attempts to create a new instance of the provided {@link Class type}.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param type
     *            the {@link Class type}
     * @param constructorParameters
     *            the {@link Constructor} parameters.
     * @param overrideAccessibility
     *            whether or not to attempt overriding the accessibility of the
     *            {@link Constructor}.
     * @return a new instance of the {@link Class type}.
     * @throws ReflectionException
     *             if a new instance could not be initialized.
     */
    public static <T> T createInstance(final Class<? extends T> type,
            final Object[] constructorParameters,
            final boolean overrideAccessibility)
            throws ReflectionException {

        final Collection<Class<?>> parameterTypes = new LinkedList<Class<?>>();

        Object[] parameterList = constructorParameters;
        if (parameterList == null) {

            parameterList = new Object[0];
        }

        for (final Object object : parameterList) {

            parameterTypes.add(object.getClass());
        }

        final Constructor<?> constructor = ReflectionUtils.getConstructor(type,
                parameterTypes);

        T retrievedInstance = null;

        boolean accessibilityChanged = false;
        if (overrideAccessibility) {

            accessibilityChanged = ReflectionUtils.setAccessibility(
                    constructor, true);
        }

        Object newObject = null;

        try {

            newObject = constructor.newInstance(parameterList);
        }
        catch (final InstantiationException e) {

            throw new ReflectionException("Invocation of "
                    + ReflectionUtils.getSignature(constructor)
                    + " failed because " + type.getCanonicalName()
                    + " is abstract!", e);
        }
        catch (final IllegalAccessException e) {

            throw new ReflectionException("Invocation of "
                    + ReflectionUtils.getSignature(constructor)
                    + " failed because of insufficient accessibility!", e);
        }
        catch (final IllegalArgumentException e) {

            throw new ReflectionException("Invocation of "
                    + ReflectionUtils.getSignature(constructor)
                    + " failed because of illegal arguments!", e);
        }
        catch (final InvocationTargetException e) {

            throw new ReflectionException("Failed to invoke "
                    + ReflectionUtils.getSignature(constructor) + "!", e);
        }


        retrievedInstance = ReflectionUtils.castValue(type, newObject);


        if (accessibilityChanged) {

            ReflectionUtils.setAccessibility(constructor, false);
        }

        return retrievedInstance;
    }



    /**
     * Attempts to set the accessibility of the provided
     * {@link AccessibleObject} to the provided value.
     *
     * @param accessibleObject
     *            the {@link AccessibleObject} to modify.
     * @param accessibility
     *            the desired accessibility.
     * @return {@code true}, if the {@link AccessibleObject} was modified;
     *         {@code false} otherwise.
     * @throws ReflectionException
     *             it the accessibility of the {@link AccessibleObject} could
     *             not be changed.
     */
    public static boolean setAccessibility(
            final AccessibleObject accessibleObject, final boolean accessibility)
            throws ReflectionException {

        boolean accessibilityChanged = false;
        if (!accessibleObject.isAccessible() == accessibility) {

            try {

                accessibleObject.setAccessible(accessibility);

                accessibilityChanged = true;
            }
            catch (final SecurityException e) {

                String name = accessibleObject.toString();

                if (accessibleObject instanceof Member) {

                    name = ((Member) accessibleObject).getName();
                }

                throw new ReflectionException(
                        "Could not change accessibility of " + name + " to "
                                + accessibility + "!", e);
            }
        }

        return accessibilityChanged;
    }



    /**
     * Establishes the class hierarchy for this class (all super classes in
     * reverse order, starting with {@link java.lang.Object} and ending with the
     * class to be parsed itself).
     *
     * @param type
     *            the type for which to generate the class hierarchy.
     * @return the class hierarchy.
     */
    public static List<Class<?>> getClassHierarchy(final Class<?> type) {

        final LinkedList<Class<?>> classHierarchy = new LinkedList<Class<?>>();

        Class<?> current = type;

        // Establishing the class hierarchy for this class (all super
        // classes in reverse order, starting with java.lang.Object and
        // ending with the class to be parsed itself).
        while (current != null) {
            classHierarchy.addFirst(current);
            current = current.getSuperclass();
        }
        return classHierarchy;
    }


    /**
     * Returns the interface hierarchy for this class.
     *
     * @param type
     *            the type for which to generate the interface hierarchy.
     * @return the interface hierarchy.
     */
    public static Set<Class<?>> getInterfaceHierarchy(final Class<?> type) {


        final Set<Class<?>> interfaceHierarchy = new LinkedHashSet<Class<?>>();

        final LinkedList<Class<?>> interfaceQueue = new LinkedList<Class<?>>();

        interfaceQueue.addAll(Arrays.asList(type.getInterfaces()));

        while (!interfaceQueue.isEmpty()) {

            final Class<?> current = interfaceQueue.pop();
            interfaceHierarchy.add(current);
            interfaceQueue.addAll(Arrays.asList(current.getInterfaces()));
        }

        return interfaceHierarchy;
    }

    /**
     * Returns the shared ancestor class of the provided classes.
     *
     * @param lhs
     *            the left-hand side class.
     * @param rhs
     *            the right-hand side class.
     * @return the shared ancestor class.
     */
    public static Class<?> getSharedAncestorClass(final Class<?> lhs,
            final Class<?> rhs) {

        if (lhs == rhs) {

            return rhs;
        }
        final List<Class<?>> lhsClassHierarchy = ReflectionUtils
                .getClassHierarchy(lhs);
        final List<Class<?>> rhsClassHierarchy = ReflectionUtils
                .getClassHierarchy(rhs);

        int max = lhsClassHierarchy.size();
        if (rhsClassHierarchy.size() < max) {

            max = rhsClassHierarchy.size();
        }

        Class<?> superClass = null;

        for (int i = 0; i < max; i++) {

            final Class<?> lhsSuperClass = lhsClassHierarchy.get(i);
            final Class<?> rhsSuperClass = rhsClassHierarchy.get(i);

            if (lhsSuperClass == rhsSuperClass) {

                superClass = lhsSuperClass;
            }
            else {
                break;
            }
        }


        return superClass;
    }

    /**
     * Returns the interfaces shared by the provided classes.
     *
     * @param lhs
     *            the left-hand side class.
     * @param rhs
     *            the right-hand side class.
     * @return a set of shared interfaces.
     */
    public static Set<Class<?>> getSharedInterfaces(final Class<?> lhs,
            final Class<?> rhs) {

        final Set<Class<?>> lhsInterfaces = ReflectionUtils
                .getInterfaceHierarchy(lhs);
        final Set<Class<?>> rhsInterfaces = ReflectionUtils
                .getInterfaceHierarchy(rhs);

        lhsInterfaces.retainAll(rhsInterfaces);

        return lhsInterfaces;
    }


    /**
     * Attempts to return the actual generic type arguments of a field.
     * <p>
     * <strong> Note:</strong> Requires the type argument be explicitly declared
     * in the class definition:
     * </p>
     * <p>
     * {@code  class Foo extends Bar}&lt;{@code String}&gt;{@code ...} or
     * {@code class
     * Foo implements Baz}&lt;{@code String}&gt;{@code ...}
     * </p>
     *
     * @param field
     *            the field of the desired class.
     * @return A list of types or an empty list, if the actual type could not be
     *         determined.
     */
    public static List<Class<?>> getGenericTypeArguments(final Field field) {

        final List<Class<?>> genericTypeArguments = new ArrayList<Class<?>>();
        if (field.getGenericType() instanceof ParameterizedType) {

            final ParameterizedType parameterizedType = (ParameterizedType) field
                    .getGenericType();

            for (final Type type : parameterizedType.getActualTypeArguments()) {

                if (type instanceof Class) {
                    final Class<?> genericTypeArgument = (Class<?>) type;

                    genericTypeArguments.add(genericTypeArgument);
                }
            }

        }

        return genericTypeArguments;
    }



    /**
     * Attempts to return the actual generic type arguments of an object.
     * <p>
     * <strong> Note:</strong> Requires the type argument be explicitly declared
     * in the class definition:
     * </p>
     * <p>
     * {@code  class Foo extends Bar}&lt;{@code String}&gt;{@code ...} or
     * {@code class
     * Foo implements Baz}&lt;{@code String}&gt;{@code ...}
     * </p>
     *
     * @param object
     *            the object instance of the desired class.
     * @return A list of types or an empty list, if the actual type could not be
     *         determined.
     */
    public static List<Class<?>> getGenericTypeArguments(final Object object) {

        final Class<?> objectType = object.getClass();
        final List<Class<?>> genericTypeArguments = new ArrayList<Class<?>>();

        if (objectType.getGenericSuperclass() instanceof ParameterizedType) {

            final ParameterizedType parameterizedType = (ParameterizedType) objectType
                    .getGenericSuperclass();

            for (final Type type : parameterizedType.getActualTypeArguments()) {

                if (type instanceof Class) {
                    final Class<?> genericTypeArgument = (Class<?>) type;

                    genericTypeArguments.add(genericTypeArgument);
                }
            }
        }

        for (final Type genericInterfaceType : objectType
                .getGenericInterfaces()) {

            if (genericInterfaceType instanceof ParameterizedType) {

                final ParameterizedType parameterizedType = (ParameterizedType) genericInterfaceType;

                for (final Type type : parameterizedType
                        .getActualTypeArguments()) {

                    if (type instanceof Class) {
                        final Class<?> genericTypeArgument = (Class<?>) type;

                        if (!genericTypeArguments.contains(genericTypeArgument)) {

                            genericTypeArguments.add(genericTypeArgument);
                        }
                    }
                }
            }

        }

        return genericTypeArguments;
    }


    /**
     * Attempts to return the actual generic type arguments of the chosen
     * inherited type
     * <p>
     * <strong> Note:</strong> Requires the type argument be explicitly declared
     * in the class definition:
     * </p>
     * <p>
     * {@code  class Foo extends Bar}&lt;{@code String}&gt;{@code ...} or
     * {@code class Foo implements Baz}&lt;{@code String}&gt;{@code ...}
     * </p>
     *
     * @param object
     *            the object instance of the desired class.
     * @param inheritedType
     *            the inherited type (Class or Interface).
     * @return A list of types or an empty list, if the actual type could not be
     *         determined.
     */
    public static List<Class<?>> getGenericTypeArgumentsOfInheritedType(
            final Object object, final Class<?> inheritedType) {

        final Class<?> objectType = object.getClass();
        final List<Class<?>> genericTypeArguments = new ArrayList<Class<?>>();


        boolean found = false;
        if (objectType.getGenericSuperclass() instanceof ParameterizedType) {

            final ParameterizedType parameterizedType = (ParameterizedType) objectType
                    .getGenericSuperclass();

            if (parameterizedType.getRawType() == inheritedType) {
                found = true;
                for (final Type type : parameterizedType
                        .getActualTypeArguments()) {

                    if (type instanceof Class) {
                        final Class<?> genericTypeArgument = (Class<?>) type;

                        genericTypeArguments.add(genericTypeArgument);
                    }
                }
            }
        }
        if (!found) {

            for (final Type genericInterfaceType : objectType
                    .getGenericInterfaces()) {

                if (genericInterfaceType instanceof ParameterizedType) {


                    final ParameterizedType parameterizedType = (ParameterizedType) genericInterfaceType;

                    if (parameterizedType.getRawType() == inheritedType) {
                        for (final Type type : parameterizedType
                                .getActualTypeArguments()) {


                            if (type instanceof Class) {
                                final Class<?> genericTypeArgument = (Class<?>) type;

                                if (!genericTypeArguments
                                        .contains(genericTypeArgument)) {

                                    genericTypeArguments
                                            .add(genericTypeArgument);
                                }
                            }
                        }
                    }
                    break;
                }

            }
        }

        return genericTypeArguments;
    }



    /**
     * Returns the signature of the provided {@link Method}.
     *
     * @param method
     *            the {@link Method}.
     * @return the {@link Method Method's} signature.
     */
    public static String getSignature(final Method method) {

        return ReflectionUtils.buildSignature(method.getModifiers(),
                method.getDeclaringClass(), method.getReturnType(),
                method.getName(), Arrays.asList(method.getParameterTypes()));
    }


    /**
     * Returns the signature of the provided {@link Constructor}.
     *
     * @param constructor
     *            the {@link Constructor}.
     * @return the {@link Constructor Constructor's} signature.
     */
    public static String getSignature(final Constructor<?> constructor) {

        return ReflectionUtils.buildSignature(constructor.getModifiers(),
                constructor.getDeclaringClass(), null, constructor.getName(),
                Arrays.asList(constructor.getParameterTypes()));
    }


    /**
     * Returns the signature of the provided {@link Field}.
     *
     * @param field
     *            the {@link Field}.
     * @return the {@link Field Field's} signature.
     */
    public static String getSignature(final Field field) {

        return ReflectionUtils.buildSignature(field.getModifiers(),
                field.getDeclaringClass(), field.getType(), field.getName(),
                null);
    }


    /**
     * Builds a signature with the provided values.
     *
     * @param modifiers
     *            the {@link Modifier modifiers}.
     * @param type
     *            the defining {@link Class}.
     * @param returnType
     *            the {@link Class return type}.
     * @param identifier
     *            the identifier.
     * @param parameterTypes
     *            the {@link Class parameter types}.
     * @return the signature.
     */
    public static String buildSignature(final Integer modifiers,
            final Class<?> type, final Class<?> returnType,
            final String identifier, final Collection<Class<?>> parameterTypes) {

        final StringBuilder signature = new StringBuilder();

        if (modifiers != null) {

            signature.append(Modifier.toString(modifiers) + " ");
        }

        if (returnType != null) {

            signature.append(returnType.getCanonicalName() + " ");
        }

        if (type != null) {

            signature.append(type.getCanonicalName() + ".");
        }

        if ((identifier != null) && !"".equals(identifier)) {

            signature.append(identifier);
        }
        else {

            signature.append("???");
        }

        if (parameterTypes != null) {
            signature.append("(");

            int i = 0;
            for (final Class<?> parameterType : parameterTypes) {

                if (i > 0) {

                    signature.append(", ");
                }
                i++;
                signature.append(parameterType.getCanonicalName());
            }

            signature.append(")");
        }
        return signature.toString();
    }

    /**
     * Creates a new {@link LinkedList} of the provided {@link Class type}.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param type
     *            the desired {@link Class type}.
     * @return a new {@link LinkedList} of the provided {@link Class type}.
     */
    public static <T> LinkedList<T> createLinkedList(
            final Class<? extends T> type) {

        return new LinkedList<T>();
    }

    /**
     * Creates a new {@link LinkedHashSet} of the provided {@link Class type}.
     *
     * @param <T>
     *            the desired return type.
     *
     * @param type
     *            the desired {@link Class type}.
     * @return a new {@link LinkedHashSet} of the provided {@link Class type}.
     */
    public static <T> LinkedHashSet<T> createLinkedHashSet(
            final Class<? extends T> type) {

        return new LinkedHashSet<T>();
    }
}
