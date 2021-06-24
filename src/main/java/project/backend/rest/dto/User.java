package project.backend.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private Double calculations;
}
