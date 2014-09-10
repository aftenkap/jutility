/**
 *
 */
package org.jutility.io.database.annotation;


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
@Target(ElementType.TYPE)
public @interface DBElementType {

    /**
     * The name of the database table containing the elements of this DBType.
     *
     * @return the name of the database table.
     */
    String table();

    /**
     * The field names of the constructor parameters.
     *
     * @return the field names of the constructor parameters. Empty by default.
     */
    String[] constructorParameters() default {};
}