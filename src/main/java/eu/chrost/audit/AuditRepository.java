package eu.chrost.audit;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuditRepository extends JpaRepository<Audit, Long> {
}
