package Network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    private Network network;

    @BeforeEach
    void setUp(){
        try {
            network = new Network();
        } catch (IOException e) {
            System.out.println("Something is messed up with IO's");
        }
    }
    @AfterEach
    void tearDown(){
    }

    void testConnection(){}
}