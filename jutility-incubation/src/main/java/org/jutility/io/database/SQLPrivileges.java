package org.jutility.io.database;


/**
 * Common SQL object privileges.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public enum SQLPrivileges
        implements ISQLPrivileges {
    // Object Rights
    /**
     * Privilege to SELECT object.
     */
    SELECT("SELECT"),
    /**
     * Privilege to INSERT object.
     */
    INSERT("INSERT"),
    /**
     * Privilege to UPDATE object.
     */
    UPDATE("UPDATE"),
    /**
     * Privilege to DELETE object.
     */
    DELETE("DELETE");


    private final String name;

    private SQLPrivileges(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }
}
