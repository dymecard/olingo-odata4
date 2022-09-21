package org.apache.olingo.server.api;

/**
 * Enumerates options for value inclusion during serialization.
 */
public enum SerializerInclude {
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
    NOT_NULL_EMPTY_OR_DEFAULT,
}
