/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.commons.api.domain;

/**
 * OData complex property value.
 *
 * @param <OP> The actual ODataProperty interface.
 */
public interface ODataComplexValue<OP extends CommonODataProperty> extends ODataValue, Iterable<OP> {

  /**
   * Adds field to the complex type.
   *
   * @param field field to be added.
   */
  void add(OP field);

  /**
   * Gets field.
   *
   * @param name name of the field to be retrieved.
   * @return requested field.
   */
  OP get(String name);

  /**
   * Gets number of fields.
   *
   * @return number of fields.
   */
  int size();

}
