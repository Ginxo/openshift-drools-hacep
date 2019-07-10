/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.hacep.core.infra.consumer;

import org.kie.hacep.Config;
import org.kie.hacep.EnvConfig;
import org.kie.hacep.consumer.DroolsConsumerHandler;
import org.kie.hacep.core.infra.election.LeadershipCallback;
import org.kie.hacep.core.infra.producer.EventProducer;
import org.kie.hacep.core.infra.utils.Printer;

public class ConsumerController {

    private DefaultKafkaConsumer consumer;
    private InfraCallback callback;
    private Thread thread;

    public ConsumerController( Printer printer, EnvConfig envConfig, EventProducer<?> eventProducer) {
        this.callback = new InfraCallback();
        this.consumer = new DefaultKafkaConsumer(printer, envConfig);
        this.callback.setConsumer(consumer);
        this.consumer.createConsumer(new DroolsConsumerHandler(eventProducer, envConfig));
    }

    public void start() {
        consumeEvents();
    }

    public void stop() {
        consumer.stop();
        stopConsumeEvents();
    }

    public DefaultKafkaConsumer getConsumer() {
        return consumer;
    }

    public LeadershipCallback getCallback() {
        return callback;
    }

    private void consumeEvents() {
        thread = new Thread(
                new ConsumerThread(Config.DEFAULT_POLL_TIMEOUT_MS, this));
        thread.start();
    }

    private void stopConsumeEvents(){
        if ( thread != null) {
            try {
                thread.join();
            }catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }
    }

}