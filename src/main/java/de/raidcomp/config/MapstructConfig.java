package de.raidcomp.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "jsr330")
public interface MapstructConfig {
}
