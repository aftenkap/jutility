package org.jutility.io.database.mysql;


import org.jutility.io.database.ISQLPrivileges;

/**
 * @author Peter J. Radics
 * @version 0.1
 * 
 */
public enum MySQLPrivileges
        implements ISQLPrivileges {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    EXECUTE("EXECUTE"),
    SHOW_VIEW("SHOW VIEW"),
    CREATE("CREATE"),
    ALTER("ALTER"),
    REFERENCES("REFERENCES"),
    INDEX("INDEX"),
    CREATE_VIEW("CREATE VIEW"),
    CREATE_ROUTINE("CREATE ROUTINE"),
    ALTER_ROUTINE("ALTER ROUTINE"),
    DROP("DROP"),
    TRIGGER("TRIGGER"),
    GRANT_OPTION("GRANT_OPTION"),
    CREATE_TEMPORARY_TABLES("CREATE TEMPORARY TABLES"),
    LOCK_TABLES("LOCK TABLES"),
    ALL_OBJECT_RIGHTS("SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW"),
    ALL_DDL_RIGHTS("CREATE,ALTER,REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE,"
            + "ALTER ROUTINE,DROP,TRIGGER"),
    ALL_OTHER_RIGHTS("GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES"),
    ALL("SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW,CREATE,ALTER,"
            + "REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE,ALTER ROUTINE,DROP,"
            + "TRIGGER,GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES");


    private final String name;

    private MySQLPrivileges(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }
}
