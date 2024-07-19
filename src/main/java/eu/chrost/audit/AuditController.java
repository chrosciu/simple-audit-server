package eu.chrost.audit;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
class AuditController {
    private final AuditRepository auditRepository;

    @Data
    public static class AuditInputDto {
        private String message;
    }

    @Data
    @Builder
    public static class AuditOutputDto {
        private String message;
        private LocalDateTime timestamp;
    }

    @GetMapping
    public List<AuditOutputDto> getAllAudits() {
        return auditRepository.findAll().stream()
                .map(audit -> AuditOutputDto.builder()
                        .message(audit.getMessage())
                        .timestamp(audit.getTimestamp())
                        .build())
                .toList();
    }

    @PostMapping
    public ResponseEntity<Void> createAudit(@RequestBody AuditInputDto dto) {
        if (dto.getMessage().isBlank()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        auditRepository.save(Audit.builder()
                .message(dto.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
