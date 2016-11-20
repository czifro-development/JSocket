package jsocket.test.mock;

import jsocket.socket.ObjectSocketImpl;
import jsocket.socket.SocketImpl;
import jsocket.socket.StringSocketImpl;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * This class is used to create instances of objects to be tested
 * Depending on the op value used, the returned object will pass, fail, or return different data
 * @author Will Czifro
 */
public class MockGenerator {


    public static SocketImpl mockSocket(int op) {
        java.net.Socket conn = mockSocketDependency(op);
        if (conn == null) return null;
        return new SocketImpl(conn);
    }

    public static StringSocketImpl mockStringSocket(int op) {
        java.net.Socket conn = mockSocketDependency(op);
        if (conn == null) return null;
        return new StringSocketImpl(conn);
    }

    public static ObjectSocketImpl mockObjectSocket(int op) {
        java.net.Socket conn = mockSocketDependency(op);
        if (conn == null) return null;
        return new ObjectSocketImpl(conn);
    }

    private static java.net.Socket mockSocketDependency(int op) {
        try {
            java.net.Socket conn = mock(java.net.Socket.class);
            when(conn.getInputStream()).thenReturn(new MockInputStream(op));
            when(conn.getOutputStream()).thenReturn(null);
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
