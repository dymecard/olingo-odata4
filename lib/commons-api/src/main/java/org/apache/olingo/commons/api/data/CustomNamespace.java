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
package org.apache.olingo.commons.api.data;

import java.util.Objects;

/**
 * Describes a custom XML namespace.
 */
public final class CustomNamespace {
    private final String prefix;
    private final String uri;

    private CustomNamespace(final String prefix, final String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    /**
     * Static factory for a custom namespace.
     * @param prefix Prefix to use for this namespace.
     * @param uri URI value to use for this namespace.
     * @return Custom namespace object.
     */
    public static CustomNamespace of(final String prefix, final String uri) {
        return new CustomNamespace(prefix, uri);
    }

    /**
     * Gets the prefix for this namespace.
     * @return prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets the URI for this namespace.
     * @return URI.
     */
    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomNamespace that = (CustomNamespace) o;
        return Objects.equals(prefix, that.prefix) && Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, uri);
    }
}
