package com.transactionexample.service;

import com.transactionexample.entity.AuditLog;
import com.transactionexample.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailure(String customerName, String reason) {
        AuditLog log = new AuditLog(customerName, reason);
        auditLogRepository.save(log);
    }
}
