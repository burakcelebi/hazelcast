/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.spi;

import com.hazelcast.client.connection.nio.ClientConnection;
import com.hazelcast.client.spi.impl.ClientInvocation;
import com.hazelcast.nio.Address;
import com.hazelcast.nio.Packet;

import java.io.IOException;

/**
 * @author mdogan 5/16/13
 */
public interface ClientInvocationService {

    void invokeOnConnection(ClientInvocation invocation, ClientConnection connection) throws IOException;

    void invokeOnPartitionOwner(ClientInvocation invocation, int partitionId) throws IOException;

    void invokeOnRandomTarget(ClientInvocation invocation) throws IOException;

    void invokeOnTarget(ClientInvocation invocation, Address target) throws IOException;

    boolean isRedoOperation();

    /**
     * Removes event handler corresponding to callId
     *
     * @param callId of event handler registration request
     * @return true if found and removed, false otherwise
     */
    boolean removeEventHandler(Integer callId);

    void shutdown();

    void handlePacket(Packet packet);

    EventHandler getEventHandler(int callId);
}
