package com.oekrem.mikroservices.dto;

import lombok.Builder;

@Builder
public record CategoryResponse (
     Integer id,
     String name,
     String description
) {
}
