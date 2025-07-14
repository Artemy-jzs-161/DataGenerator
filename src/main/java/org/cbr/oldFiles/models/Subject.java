package org.cbr.oldFiles.models;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Subject {
    private UUID id;
    private LocalDateTime createdAt;
    private DataSource source;
    private Map<String, String> metadata;
}
