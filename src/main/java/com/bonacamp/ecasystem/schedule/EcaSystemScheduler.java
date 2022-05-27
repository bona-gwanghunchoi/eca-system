package com.bonacamp.ecasystem.schedule;

import com.bonacamp.ecasystem.domain.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EcaSystemScheduler {
    private final BatchService batchService;


}
