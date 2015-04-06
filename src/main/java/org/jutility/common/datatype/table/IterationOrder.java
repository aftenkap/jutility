package org.jutility.common.datatype.table;


// @formatter:off
/*
 * #%L
 * jutility-common
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
// @formatter:on


/**
 * The {@code IterationOrder} enumeration determines iteration order of a
 * {@link Table}.
 *
 * @author Peter J. Radics
 * @version 0.1.2
 * @since 0.1.0
 */
public enum IterationOrder {

    /**
     * Iteration as a row-major table.
     */
    ROW_MAJOR,
    /**
     * Iteration as a column-major table.
     */
    COLUMN_MAJOR;
}
