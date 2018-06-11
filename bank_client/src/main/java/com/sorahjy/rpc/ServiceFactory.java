package com.sorahjy.rpc;

import java.io.IOException;

public interface ServiceFactory {
    public  <T> T call(final Class<T> interfaceClass, final String host, final int port) throws Exception;

    public void startService(final Object service, int port) throws IOException;
}
