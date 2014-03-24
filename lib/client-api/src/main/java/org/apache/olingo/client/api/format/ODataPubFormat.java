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
package org.apache.olingo.client.api.format;

import java.util.EnumMap;
import org.apache.http.entity.ContentType;
import org.apache.olingo.commons.api.edm.constants.ODataServiceVersion;

/**
 * Available formats for AtomPub exchange.
 */
public enum ODataPubFormat implements Format {

  /**
   * JSON format with no metadata.
   */
  JSON_NO_METADATA(),
  /**
   * JSON format with minimal metadata (default).
   */
  JSON(),
  /**
   * JSON format with no metadata.
   */
  JSON_FULL_METADATA(),
  /**
   * Atom format.
   */
  ATOM();

  final static EnumMap<ODataServiceVersion, EnumMap<ODataPubFormat, String>> formatPerVersion =
          new EnumMap<ODataServiceVersion, EnumMap<ODataPubFormat, String>>(ODataServiceVersion.class);

  static {
    final EnumMap<ODataPubFormat, String> v3 = new EnumMap<ODataPubFormat, String>(ODataPubFormat.class);
    v3.put(JSON_NO_METADATA, ContentType.APPLICATION_JSON.getMimeType() + ";odata=nometadata");
    v3.put(JSON, ContentType.APPLICATION_JSON.getMimeType() + ";odata=minimalmetadata");
    v3.put(JSON_FULL_METADATA, ContentType.APPLICATION_JSON.getMimeType() + ";odata=fullmetadata");
    v3.put(ATOM, ContentType.APPLICATION_ATOM_XML.getMimeType());
    formatPerVersion.put(ODataServiceVersion.V30, v3);

    final EnumMap<ODataPubFormat, String> v4 = new EnumMap<ODataPubFormat, String>(ODataPubFormat.class);
    v4.put(JSON_NO_METADATA, ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=none");
    v4.put(JSON, ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=minimal");
    v4.put(JSON_FULL_METADATA, ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=full");
    v4.put(ATOM, ContentType.APPLICATION_ATOM_XML.getMimeType());
    formatPerVersion.put(ODataServiceVersion.V40, v4);
  }

  /**
   * Gets format as a string.
   *
   * @return format as a string.
   */
  @Override
  public String toString(final ODataServiceVersion version) {
    if (version.ordinal() < ODataServiceVersion.V30.ordinal()) {
      throw new IllegalArgumentException("Unsupported version " + version);
    }

    return ODataPubFormat.formatPerVersion.get(version).get(this);
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException();
  }

  /**
   * Gets OData format from its string representation.
   *
   * @param format string representation of the format.
   * @return OData format.
   */
  public static ODataPubFormat fromString(final String format) {
    ODataPubFormat result = null;

    final StringBuffer _format = new StringBuffer();

    final String[] parts = format.split(";");
    _format.append(parts[0].trim());
    if (ContentType.APPLICATION_JSON.getMimeType().equals(parts[0].trim())) {
      if (parts.length > 1 && parts[1].startsWith("odata")) {
        _format.append(';').append(parts[1].trim());
      } else {
        result = ODataPubFormat.JSON;
      }
    }

    if (result == null) {
      final String candidate = _format.toString();
      for (ODataPubFormat value : values()) {
        if (candidate.equals(value.toString(ODataServiceVersion.V30))
                || candidate.equals(value.toString(ODataServiceVersion.V40))) {
          result = value;
        }
      }
    }

    if (result == null) {
      throw new IllegalArgumentException("Unsupported format: " + format);
    }

    return result;
  }
}
