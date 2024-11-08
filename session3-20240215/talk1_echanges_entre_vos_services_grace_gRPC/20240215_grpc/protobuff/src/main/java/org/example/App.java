package org.example;

import com.cncf.grpc.protos.Person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Person john =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();

        byte[] serialize = john.toByteArray();
        System.out.println("proto : "+serialize.length);

        String json = "{'name':'John Doe','email':'jdoe@example.com','phones':[{'number':'551-4321', 'type':'HOME}";

        System.out.println("json : "+json.length());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gos = new GZIPOutputStream(byteArrayOutputStream)){
            gos.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] jsonGziped = byteArrayOutputStream.toByteArray();

        System.out.println("jsonGz : "+jsonGziped.length);


    }
}
