package com.springboot.jpa.controller.dto;

import java.util.Set;
import java.util.UUID;

public record BookRecordDto(String title, UUID publisherId, Set<UUID> authorsIds, String reviewComment) {

}
