/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.server.api;

/**
 * Enumerates options for value inclusion during serialization.
 */
public enum SerializerInclude {
    /**
     * `ALL`: Include all values unconditionally.
     */
    ALL,

    /**
     * `NOT_NULL`: Includes all values which are non-null.
     */
    NOT_NULL,

    /**
     * `NOT_NULL_OR_EMPTY`: Includes all values which are non-null and non-empty (applies mostly to strings).
     */
    NOT_NULL_AND_NOT_EMPTY,

    /**
     * `NOT_NULL_OR_DEFAULT`: Includes all values which are non-null, non-empty (applying mostly to strings), and
     * are not equal to their default value, as applicable.
     */
    NOT_NULL_EMPTY_OR_DEFAULT;

    /**
     * Decide whether to include the provided value
     *
     * @param isNull should be `true` if the value to be enforced is `null` or equivalent.
     * @param isEmpty should be `true` if the value to be enforced is an empty string or equivalent.
     * @param isDefault should be `true` if the value to be enforced is equal to its default value.
     * @return Whether the value should be excluded (`true` for yes, `false` for no).
     */
    public boolean shouldExclude(boolean isNull, boolean isEmpty, boolean isDefault) {
        if (this == ALL) {
            return false;
        }
        if (isNull) {
            return true;
        }
        if (this != NOT_NULL) {
            if (this == NOT_NULL_AND_NOT_EMPTY) {
                return isEmpty;
            } else {
                return isEmpty || isDefault;
            }
        }
        return false;
    }
}
