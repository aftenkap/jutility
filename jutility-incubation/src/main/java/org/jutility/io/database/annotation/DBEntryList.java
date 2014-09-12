package org.jutility.io.database.annotation;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


/**
 * The <CODE>DBListElement</CODE> annotation represents a 1-n relationship in
 * the database.
 * <p>
 * Thus, the elements are <em>dependent</em> on their container. Both the class
 * containing the DBListElement and the class contained in the list need to be
 * valid DBTypes or primitives.
 *
 * @author Peter J. Radics
 * @version = 0.1
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface DBEntryList {


    /**
     * The name of the database table containing entries of this DBListElement.
     *
     * @return the name of the database table.
     */
    String table();


    /**
     * The mappings of primary key columns of the DBElementType containing the
     * list to their equivalent foreign key columns in the table of this
     * DBElementList. If not mapping is provided, the original column names are
     * used.
     * <p>
     * Mappings are specified as follows:<br/>
     * <CODE>"originalPKColumnName=columnNameInList"</CODE>
     * </p>
     * Whitespace is trimmed from the mappings.
     *
     * @return The name of the table column of the primary key of the container.
     */
    String[] containerClassAliasMap() default { "" };


    /**
     * The mappings of columns of the contained element to their equivalents in
     * the table of this DBElementList. If the contained element type is a
     * {@link DBElementType}, all primary keys of that class need to be mapped.
     * If the contained element type is a {@link DBValueType}, all columns of
     * that class need to be mapped. If not mapping is provided, the original
     * column names are used.
     * <p>
     * Mappings are specified as follows:<br/>
     * <CODE>"originalColumnName=columnNameInList"</CODE>
     * </p>
     * Whitespace is trimmed from the mappings.
     *
     * @return The name of the table column of the primary key of the contained
     *         elements.
     */
    String[] listTypeAliasMap() default { "" };
}