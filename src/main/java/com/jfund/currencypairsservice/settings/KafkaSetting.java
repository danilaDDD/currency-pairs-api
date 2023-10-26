package com.jfund.currencypairsservice.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaSetting {
    @Value("${kafka.server}")
    private String serverLink;

    @Value("${kafka.topic-name}")
    private String topicName;
}
