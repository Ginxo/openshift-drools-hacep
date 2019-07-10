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
package org.kie.hacep.core.infra.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;

public interface Producer{

    void start(Properties properties);

    void stop();

    void produceFireAndForget(String topicName, String key, Object object);

    long produceSync(String topicName, String key, Object object);

    void produceAsync(String topicName, String key, Object object, Callback callback);
}