package com.google.cloud.storage.spi.v1;

import java.util.Objects;
import com.google.api.services.storage.model.ServiceAccount;
import com.google.api.services.storage.model.Notification;
import com.google.api.services.storage.model.TestIamPermissionsResponse;
import com.google.api.services.storage.model.Policy;
import com.google.api.services.storage.model.HmacKeyMetadata;
import com.google.api.services.storage.model.HmacKey;
import com.google.api.services.storage.model.ObjectAccessControl;
import java.util.List;
import com.google.api.services.storage.model.BucketAccessControl;
import java.io.OutputStream;
import com.google.cloud.Tuple;
import java.io.InputStream;
import com.google.api.services.storage.model.StorageObject;
import java.util.Map;
import com.google.api.services.storage.model.Bucket;
import com.google.api.core.InternalApi;
import com.google.cloud.ServiceRpc;

@InternalApi
public interface StorageRpc extends ServiceRpc
{
    Bucket create(final Bucket p0, final Map<Option, ?> p1);
    
    StorageObject create(final StorageObject p0, final InputStream p1, final Map<Option, ?> p2);
    
    Tuple<String, Iterable<Bucket>> list(final Map<Option, ?> p0);
    
    Tuple<String, Iterable<StorageObject>> list(final String p0, final Map<Option, ?> p1);
    
    Bucket get(final Bucket p0, final Map<Option, ?> p1);
    
    StorageObject get(final StorageObject p0, final Map<Option, ?> p1);
    
    Bucket patch(final Bucket p0, final Map<Option, ?> p1);
    
    StorageObject patch(final StorageObject p0, final Map<Option, ?> p1);
    
    boolean delete(final Bucket p0, final Map<Option, ?> p1);
    
    boolean delete(final StorageObject p0, final Map<Option, ?> p1);
    
    RpcBatch createBatch();
    
    StorageObject compose(final Iterable<StorageObject> p0, final StorageObject p1, final Map<Option, ?> p2);
    
    byte[] load(final StorageObject p0, final Map<Option, ?> p1);
    
    Tuple<String, byte[]> read(final StorageObject p0, final Map<Option, ?> p1, final long p2, final int p3);
    
    long read(final StorageObject p0, final Map<Option, ?> p1, final long p2, final OutputStream p3);
    
    String open(final StorageObject p0, final Map<Option, ?> p1);
    
    String open(final String p0);
    
    void write(final String p0, final byte[] p1, final int p2, final long p3, final int p4, final boolean p5);
    
    RewriteResponse openRewrite(final RewriteRequest p0);
    
    RewriteResponse continueRewrite(final RewriteResponse p0);
    
    BucketAccessControl getAcl(final String p0, final String p1, final Map<Option, ?> p2);
    
    boolean deleteAcl(final String p0, final String p1, final Map<Option, ?> p2);
    
    BucketAccessControl createAcl(final BucketAccessControl p0, final Map<Option, ?> p1);
    
    BucketAccessControl patchAcl(final BucketAccessControl p0, final Map<Option, ?> p1);
    
    List<BucketAccessControl> listAcls(final String p0, final Map<Option, ?> p1);
    
    ObjectAccessControl getDefaultAcl(final String p0, final String p1);
    
    boolean deleteDefaultAcl(final String p0, final String p1);
    
    ObjectAccessControl createDefaultAcl(final ObjectAccessControl p0);
    
    ObjectAccessControl patchDefaultAcl(final ObjectAccessControl p0);
    
    List<ObjectAccessControl> listDefaultAcls(final String p0);
    
    ObjectAccessControl getAcl(final String p0, final String p1, final Long p2, final String p3);
    
    boolean deleteAcl(final String p0, final String p1, final Long p2, final String p3);
    
    ObjectAccessControl createAcl(final ObjectAccessControl p0);
    
    ObjectAccessControl patchAcl(final ObjectAccessControl p0);
    
    List<ObjectAccessControl> listAcls(final String p0, final String p1, final Long p2);
    
    HmacKey createHmacKey(final String p0, final Map<Option, ?> p1);
    
    Tuple<String, Iterable<HmacKeyMetadata>> listHmacKeys(final Map<Option, ?> p0);
    
    HmacKeyMetadata updateHmacKey(final HmacKeyMetadata p0, final Map<Option, ?> p1);
    
    HmacKeyMetadata getHmacKey(final String p0, final Map<Option, ?> p1);
    
    void deleteHmacKey(final HmacKeyMetadata p0, final Map<Option, ?> p1);
    
    Policy getIamPolicy(final String p0, final Map<Option, ?> p1);
    
    Policy setIamPolicy(final String p0, final Policy p1, final Map<Option, ?> p2);
    
    TestIamPermissionsResponse testIamPermissions(final String p0, final List<String> p1, final Map<Option, ?> p2);
    
    boolean deleteNotification(final String p0, final String p1);
    
    List<Notification> listNotifications(final String p0);
    
    Notification createNotification(final String p0, final Notification p1);
    
    Bucket lockRetentionPolicy(final Bucket p0, final Map<Option, ?> p1);
    
    ServiceAccount getServiceAccount(final String p0);
}
