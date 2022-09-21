package org.apache.olingo.commons.api.data;

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
}
