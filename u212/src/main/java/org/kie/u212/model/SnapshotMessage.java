/*
 * Copyright 2019 Red Hat
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

package org.kie.u212.model;

import java.io.Serializable;
import java.util.Map;

import org.kie.api.runtime.rule.FactHandle;
import org.kie.remote.RemoteFactHandle;

public class SnapshotMessage implements Serializable {
    private byte[] serializedSession;
    private Map<RemoteFactHandle, FactHandle> fhMap;
    private String lastInsertedEventkey;
    private long lastInsertedEventOffset;

    public SnapshotMessage() { }

    public SnapshotMessage( byte[] serializedSession, Map<RemoteFactHandle, FactHandle> fhMap, String lastInsertedEventkey, long lastInsertedEventOffset ) {
        this.serializedSession = serializedSession;
        this.fhMap = fhMap;
        this.lastInsertedEventkey = lastInsertedEventkey;
        this.lastInsertedEventOffset = lastInsertedEventOffset;
    }

    public byte[] getSerializedSession() {
        return serializedSession;
    }

    public void setSerializedSession( byte[] serializedSession ) {
        this.serializedSession = serializedSession;
    }

    public Map<RemoteFactHandle, FactHandle> getFhMap() {
        return fhMap;
    }

    public void setFhMap( Map<RemoteFactHandle, FactHandle> fhMap ) {
        this.fhMap = fhMap;
    }

    public String getLastInsertedEventkey() {
        return lastInsertedEventkey;
    }

    public void setLastInsertedEventkey( String lastInsertedEventkey ) {
        this.lastInsertedEventkey = lastInsertedEventkey;
    }

    public long getLastInsertedEventOffset() {
        return lastInsertedEventOffset;
    }

    public void setLastInsertedEventOffset( long lastInsertedEventOffset ) {
        this.lastInsertedEventOffset = lastInsertedEventOffset;
    }
}