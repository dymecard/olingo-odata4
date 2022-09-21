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
package org.apache.olingo.server.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.olingo.commons.api.data.CustomNamespace;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.constants.ODataServiceVersion;
import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.commons.core.edm.EdmProviderImpl;
import org.apache.olingo.server.api.SerializerOptions;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.etag.ServiceMetadataETagSupport;

/**
 */
public class ServiceMetadataImpl implements ServiceMetadata {

  private final Edm edm;
  private final List<EdmxReference> references;
  private final ServiceMetadataETagSupport serviceMetadataETagSupport;
  private final List<CustomNamespace> customNamespaces;
  private SerializerOptions serializerOptions;
  private Supplier<ObjectMapper> jsonMapperSupplier;
  private Supplier<JsonFactory> jsonFactorySupplier;

  public ServiceMetadataImpl(final CsdlEdmProvider edmProvider, final List<EdmxReference> references,
      final ServiceMetadataETagSupport serviceMetadataETagSupport) {
    edm = new EdmProviderImpl(edmProvider);
    this.references = new ArrayList<>();
    this.references.addAll(references);
    this.serviceMetadataETagSupport = serviceMetadataETagSupport;
    this.customNamespaces = new ArrayList<>();
    this.serializerOptions = SerializerOptions.defaults();
    this.jsonMapperSupplier = ObjectMapper::new;
    this.jsonFactorySupplier = () -> new JsonFactory(this.jsonMapperSupplier.get());
  }

  @Override
  public Edm getEdm() {
    return edm;
  }

  @Override
  public ODataServiceVersion getDataServiceVersion() {
    return ODataServiceVersion.V40;
  }

  @Override
  public List<EdmxReference> getReferences() {
    return Collections.unmodifiableList(references);
  }

  @Override
  public ServiceMetadataETagSupport getServiceMetadataETagSupport() {
    return serviceMetadataETagSupport;
  }

  @Override
  public SerializerOptions getSerializerOptions() {
    return this.serializerOptions;
  }

  @Override
  public List<CustomNamespace> getCustomNamespaces() {
    return this.customNamespaces;
  }

  @Override
  public void addCustomNamespace(final CustomNamespace customNamespace) {
    this.customNamespaces.add(customNamespace);
  }

  @Override
  public void setSerializerOptions(final SerializerOptions options) {
    this.serializerOptions = options;
  }

  @Override
  public ObjectMapper getJsonMapper() {
    return this.jsonMapperSupplier.get();
  }

  @Override
  public void setJsonMapperSupplier(Supplier<ObjectMapper> mapperFactory) {
    this.jsonMapperSupplier = mapperFactory;
  }

  @Override
  public JsonFactory getJsonFactory() {
    return this.jsonFactorySupplier.get();
  }

  @Override
  public void setJsonFactorySupplier(Supplier<JsonFactory> factorySupplier) {
    this.jsonFactorySupplier = factorySupplier;
  }
}
