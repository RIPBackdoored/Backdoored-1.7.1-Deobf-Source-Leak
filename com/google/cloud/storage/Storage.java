package com.google.cloud.storage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Collection;
import com.google.auth.ServiceAccountSigner;
import java.util.Map;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import com.google.common.collect.Lists;
import com.google.cloud.Tuple;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.common.collect.ImmutableList;
import com.google.cloud.FieldSelector;
import com.google.cloud.Policy;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import com.google.cloud.WriteChannel;
import com.google.cloud.ReadChannel;
import com.google.api.gax.paging.Page;
import java.io.InputStream;
import com.google.cloud.Service;

public interface Storage extends Service<StorageOptions>
{
    Bucket create(final BucketInfo p0, final BucketTargetOption... p1);
    
    Blob create(final BlobInfo p0, final BlobTargetOption... p1);
    
    Blob create(final BlobInfo p0, final byte[] p1, final BlobTargetOption... p2);
    
    Blob create(final BlobInfo p0, final byte[] p1, final int p2, final int p3, final BlobTargetOption... p4);
    
    @Deprecated
    Blob create(final BlobInfo p0, final InputStream p1, final BlobWriteOption... p2);
    
    Bucket get(final String p0, final BucketGetOption... p1);
    
    Bucket lockRetentionPolicy(final BucketInfo p0, final BucketTargetOption... p1);
    
    Blob get(final String p0, final String p1, final BlobGetOption... p2);
    
    Blob get(final BlobId p0, final BlobGetOption... p1);
    
    Blob get(final BlobId p0);
    
    Page<Bucket> list(final BucketListOption... p0);
    
    Page<Blob> list(final String p0, final BlobListOption... p1);
    
    Bucket update(final BucketInfo p0, final BucketTargetOption... p1);
    
    Blob update(final BlobInfo p0, final BlobTargetOption... p1);
    
    Blob update(final BlobInfo p0);
    
    boolean delete(final String p0, final BucketSourceOption... p1);
    
    boolean delete(final String p0, final String p1, final BlobSourceOption... p2);
    
    boolean delete(final BlobId p0, final BlobSourceOption... p1);
    
    boolean delete(final BlobId p0);
    
    Blob compose(final ComposeRequest p0);
    
    CopyWriter copy(final CopyRequest p0);
    
    byte[] readAllBytes(final String p0, final String p1, final BlobSourceOption... p2);
    
    byte[] readAllBytes(final BlobId p0, final BlobSourceOption... p1);
    
    StorageBatch batch();
    
    ReadChannel reader(final String p0, final String p1, final BlobSourceOption... p2);
    
    ReadChannel reader(final BlobId p0, final BlobSourceOption... p1);
    
    WriteChannel writer(final BlobInfo p0, final BlobWriteOption... p1);
    
    WriteChannel writer(final URL p0);
    
    URL signUrl(final BlobInfo p0, final long p1, final TimeUnit p2, final SignUrlOption... p3);
    
    List<Blob> get(final BlobId... p0);
    
    List<Blob> get(final Iterable<BlobId> p0);
    
    List<Blob> update(final BlobInfo... p0);
    
    List<Blob> update(final Iterable<BlobInfo> p0);
    
    List<Boolean> delete(final BlobId... p0);
    
    List<Boolean> delete(final Iterable<BlobId> p0);
    
    Acl getAcl(final String p0, final Acl.Entity p1, final BucketSourceOption... p2);
    
    Acl getAcl(final String p0, final Acl.Entity p1);
    
    boolean deleteAcl(final String p0, final Acl.Entity p1, final BucketSourceOption... p2);
    
    boolean deleteAcl(final String p0, final Acl.Entity p1);
    
    Acl createAcl(final String p0, final Acl p1, final BucketSourceOption... p2);
    
    Acl createAcl(final String p0, final Acl p1);
    
    Acl updateAcl(final String p0, final Acl p1, final BucketSourceOption... p2);
    
    Acl updateAcl(final String p0, final Acl p1);
    
    List<Acl> listAcls(final String p0, final BucketSourceOption... p1);
    
    List<Acl> listAcls(final String p0);
    
    Acl getDefaultAcl(final String p0, final Acl.Entity p1);
    
    boolean deleteDefaultAcl(final String p0, final Acl.Entity p1);
    
    Acl createDefaultAcl(final String p0, final Acl p1);
    
    Acl updateDefaultAcl(final String p0, final Acl p1);
    
    List<Acl> listDefaultAcls(final String p0);
    
    Acl getAcl(final BlobId p0, final Acl.Entity p1);
    
    boolean deleteAcl(final BlobId p0, final Acl.Entity p1);
    
    Acl createAcl(final BlobId p0, final Acl p1);
    
    Acl updateAcl(final BlobId p0, final Acl p1);
    
    List<Acl> listAcls(final BlobId p0);
    
    HmacKey createHmacKey(final ServiceAccount p0, final CreateHmacKeyOption... p1);
    
    Page<HmacKey.HmacKeyMetadata> listHmacKeys(final ListHmacKeysOption... p0);
    
    HmacKey.HmacKeyMetadata getHmacKey(final String p0, final GetHmacKeyOption... p1);
    
    void deleteHmacKey(final HmacKey.HmacKeyMetadata p0, final DeleteHmacKeyOption... p1);
    
    HmacKey.HmacKeyMetadata updateHmacKeyState(final HmacKey.HmacKeyMetadata p0, final HmacKey.HmacKeyState p1, final UpdateHmacKeyOption... p2);
    
    Policy getIamPolicy(final String p0, final BucketSourceOption... p1);
    
    Policy setIamPolicy(final String p0, final Policy p1, final BucketSourceOption... p2);
    
    List<Boolean> testIamPermissions(final String p0, final List<String> p1, final BucketSourceOption... p2);
    
    ServiceAccount getServiceAccount(final String p0);
}
