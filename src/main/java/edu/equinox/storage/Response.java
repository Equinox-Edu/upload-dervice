package edu.equinox.storage;

import java.util.Map;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record Response(Map<String, Object> message) {
}
