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
        return SerializerInclude.NOT_NULL_EMPTY_OR_DEFAULT;
    }
}
