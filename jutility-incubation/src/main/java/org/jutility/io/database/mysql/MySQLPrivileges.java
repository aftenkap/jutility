package org.jutility.io.database.mysql;


/*
 * #%L
 * jutility-incubation
 * %%
 * Copyright (C) 2013 - 2014 jutility.org
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * #L%
 */


import org.jutility.io.database.ISQLPrivileges;


/**
 * @author Peter J. Radics
 * @version 0.1
 *
 */
public enum MySQLPrivileges
        implements ISQLPrivileges {
    /**
     * SELECT
     */
    SELECT("SELECT"),
    /**
     * INSERT
     */
    INSERT("INSERT"),
    /**
     * UPDATE
     */
    UPDATE("UPDATE"),
    /**
     * DELETE
     */
    DELETE("DELETE"),
    /**
     * EXECUTE
     */
    EXECUTE("EXECUTE"),
    /**
     * SHOW VIEW
     */
    SHOW_VIEW("SHOW VIEW"),
    /**
     * CREATE
     */
    CREATE("CREATE"),
    /**
     * ALTER
     */
    ALTER("ALTER"),
    /**
     * REFERENCES
     */
    REFERENCES("REFERENCES"),
    /**
     * INDEX
     */
    INDEX("INDEX"),
    /**
     * CREATE VIEW
     */
    CREATE_VIEW("CREATE VIEW"),
    /**
     * CREATE ROUTINE
     */
    CREATE_ROUTINE("CREATE ROUTINE"),
    /**
     * ALTER ROUTINE
     */
    ALTER_ROUTINE("ALTER ROUTINE"),
    /**
     * DROP
     */
    DROP("DROP"),
    /**
     * TRIGGER
     */
    TRIGGER("TRIGGER"),
    /**
     * GRANT_OPTION
     */
    GRANT_OPTION("GRANT_OPTION"),
    /**
     * CREATE TEMPORARY TABLES
     */
    CREATE_TEMPORARY_TABLES("CREATE TEMPORARY TABLES"),
    /**
     * LOCK TABLES
     */
    LOCK_TABLES("LOCK TABLES"),
    /**
     * SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW
     */
    ALL_OBJECT_RIGHTS("SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW"),
    /**
     * CREATE,ALTER,REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE, ALTER
     * ROUTINE,DROP,TRIGGER
     */
    ALL_DDL_RIGHTS("CREATE,ALTER,REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE,"
            + "ALTER ROUTINE,DROP,TRIGGER"),
    /**
     * GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES
     */
    ALL_OTHER_RIGHTS("GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES"),
    /**
     * SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW,CREATE,ALTER,"
     * REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE,ALTER ROUTINE,DROP,
     * TRIGGER,GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES
     */
    ALL("SELECT,INSERT,UPDATE,DELETE,EXECUTE,SHOW VIEW,CREATE,ALTER,"
            + "REFERENCES,INDEX,CREATE VIEW,CREATE ROUTINE,ALTER ROUTINE,DROP,"
            + "TRIGGER,GRANT_OPTION,CREATE TEMPORARY TABLES,LOCK TABLES");


    private final String name;

    MySQLPrivileges(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }
}
