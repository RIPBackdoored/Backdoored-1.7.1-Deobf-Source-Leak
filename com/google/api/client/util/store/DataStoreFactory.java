package com.google.api.client.util.store;

import java.io.IOException;
import java.io.Serializable;

public interface DataStoreFactory
{
     <V extends Serializable> DataStore<V> getDataStore(final String p0) throws IOException;
}
