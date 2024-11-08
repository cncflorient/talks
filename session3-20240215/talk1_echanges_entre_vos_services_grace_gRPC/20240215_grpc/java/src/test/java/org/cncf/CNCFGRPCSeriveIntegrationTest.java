package org.cncf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.cncf.grpc.CreateSessionReply;
import org.cncf.grpc.CreateSessionRequest;
import org.cncf.grpc.GuessANumberRequest;
import org.cncf.grpc.cncfGrpc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.cncf.grpc.cncfGrpc.newBlockingStub;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CNCFGRPCSeriveIntegrationTest
{
    private final static int TEST_PORT = 15000;
    private CNCFGRPCService CNCFGRPCService = null;

    @BeforeEach
    public void startupServer() throws IOException {
        CNCFGRPCService = new CNCFGRPCService();
        CNCFGRPCService.start(TEST_PORT);
    }

    @AfterEach
    public void shutdown(){
        CNCFGRPCService.shutdownNow();
    }


    @Test
    public void tryToExecuteCncfService()
    {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:" + TEST_PORT)
                .usePlaintext()
                .build();
        cncfGrpc.cncfBlockingStub stub = newBlockingStub(channel);
        try {

            CreateSessionRequest createSessionRequest = CreateSessionRequest.newBuilder().setUserName("test").build();

            CreateSessionReply reply = stub.createSession(createSessionRequest);
            assertNotEquals(reply.getToken(),null);

            final GuessANumberRequest guessANumberRequest = GuessANumberRequest.newBuilder().setNumber(105000).setToken(reply.getToken()).build();
            assertThrows(io.grpc.StatusRuntimeException.class, () -> stub.guessANumber(guessANumberRequest));
        }finally {
            channel.shutdownNow();
        }
    }
}
