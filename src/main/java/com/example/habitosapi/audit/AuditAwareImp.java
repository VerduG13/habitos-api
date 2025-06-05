package com.example.habitosapi.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImp implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("FITNESS_AUDITOR");
    }
}
