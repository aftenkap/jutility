package org.jutility.io.database;

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

    SQLPrivileges(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return this.name;
    }
}
