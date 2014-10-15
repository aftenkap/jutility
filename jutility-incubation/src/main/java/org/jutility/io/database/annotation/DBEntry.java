package org.jutility.io.database.annotation;

/*
 * #%L
 * jutility-incubation
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


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


/**
 * @author Peter J. Radics
 * @version = 0.1
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface DBEntry {

    /**
     * The name of the table column containing this DBEntry (ignored for
     * multi-column types).
     *
     * @return the name of the table column.
     */
    String column() default "";


    /**
     * Determines whether or not this DBEntry is the primary key of the DBType's
     * table (defaults to <CODE>false</CODE>).
     *
     * @return <CODE>true</CODE> if the DBElement is the foreign key;
     *         <CODE>false</CODE> otherwise.
     */
    boolean primaryKey() default false;

    /**
     * The mappings of columns of the {@link DBValueType} or
     * {@link DBElementType} to their equivalent columns in the table of the
     * containing type. Defaults to the identity.
     * <p>
     * Mappings are specified as follows:<br/>
     * <CODE>"columnNameInOtherClass=columnNameInThisClass"</CODE>
     * </p>
     * Whitespace is trimmed from the mappings.
     *
     *
     *
     * @return The name of the table column of the primary key of the container.
     */
    String[] columnMappings() default { "" };


    /**
     * Determines whether or not this DBEntry can be null.
     *
     * @return <CODE>true</CODE> if the DBEntry can be null; <CODE>false</CODE>
     *         otherwise.
     */
    boolean notNull() default false;
}