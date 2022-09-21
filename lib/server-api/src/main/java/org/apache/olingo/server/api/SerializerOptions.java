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
 * Options which may be applied generically to serializer operations.
 */
public interface SerializerOptions {
    /**
     * Get the current include option or the default if none is specified.
     *
     * @return Include option.
     */
    default SerializerInclude getInclude() {
        return SerializerInclude.ALL;
    }

    /**
     * Return a default set of serializer options.
     *
     * @return Default serializer options.
     */
    static SerializerOptions defaults() {
        return new SerializerOptions() {};
    }
}
