package org.yaml.snakeyaml.serializer;

import org.yaml.snakeyaml.error.YAMLException;

public class SerializerException extends YAMLException
{
    public SerializerException(final String message) {
        super(message);
    }
}
