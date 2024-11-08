package org.example;

import com.cncf.grpc.protos.MessageCNCF;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Hello world!
 *
 */
public class Simple
{
    public static void main( String[] args )
    {

        MessageCNCF cncf = MessageCNCF.newBuilder()
                .setGreen(150)
                .setIt("yes")
                .setIsBeautiful(-2).build();

        try {
            FileChannel fileChannel = FileChannel.open(Paths.get("test.bin"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            fileChannel.write(ByteBuffer.wrap(cncf.toByteArray()));
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("proto : "+cncf.toByteArray().length);


    }
}
