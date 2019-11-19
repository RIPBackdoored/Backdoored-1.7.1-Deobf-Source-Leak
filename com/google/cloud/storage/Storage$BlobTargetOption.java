package com.google.cloud.storage;

import java.util.ArrayList;
import com.google.common.collect.Lists;
import com.google.cloud.Tuple;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.cloud.storage.spi.v1.StorageRpc;

public static class BlobTargetOption extends Option
{
    private static final long serialVersionUID = 214616862061934846L;
    
    private BlobTargetOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    private BlobTargetOption(final StorageRpc.Option option) {
        this(option, null);
    }
    
    public static BlobTargetOption predefinedAcl(final PredefinedAcl predefinedAcl) {
        return new BlobTargetOption(StorageRpc.Option.PREDEFINED_ACL, predefinedAcl.getEntry());
    }
    
    public static BlobTargetOption doesNotExist() {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_MATCH, 0L);
    }
    
    public static BlobTargetOption generationMatch() {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_MATCH);
    }
    
    public static BlobTargetOption generationNotMatch() {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH);
    }
    
    public static BlobTargetOption metagenerationMatch() {
        return new BlobTargetOption(StorageRpc.Option.IF_METAGENERATION_MATCH);
    }
    
    public static BlobTargetOption metagenerationNotMatch() {
        return new BlobTargetOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH);
    }
    
    public static BlobTargetOption disableGzipContent() {
        return new BlobTargetOption(StorageRpc.Option.IF_DISABLE_GZIP_CONTENT, true);
    }
    
    public static BlobTargetOption encryptionKey(final Key key) {
        return new BlobTargetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobTargetOption userProject(final String s) {
        return new BlobTargetOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    public static BlobTargetOption encryptionKey(final String s) {
        return new BlobTargetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, s);
    }
    
    public static BlobTargetOption kmsKeyName(final String s) {
        return new BlobTargetOption(StorageRpc.Option.KMS_KEY_NAME, s);
    }
    
    static Tuple<BlobInfo, BlobTargetOption[]> convert(final BlobInfo blobInfo, final BlobWriteOption... array) {
        final BlobInfo.Builder setMd5 = blobInfo.toBuilder().setCrc32c(null).setMd5(null);
        final ArrayList arrayListWithCapacity = Lists.newArrayListWithCapacity(array.length);
        for (final BlobWriteOption blobWriteOption : array) {
            switch (blobWriteOption.option) {
                case IF_CRC32C_MATCH:
                    setMd5.setCrc32c(blobInfo.getCrc32c());
                    break;
                case IF_MD5_MATCH:
                    setMd5.setMd5(blobInfo.getMd5());
                    break;
                default:
                    arrayListWithCapacity.add(blobWriteOption.toTargetOption());
                    break;
            }
        }
        return (Tuple<BlobInfo, BlobTargetOption[]>)Tuple.of((Object)setMd5.build(), (Object)arrayListWithCapacity.<BlobTargetOption>toArray(new BlobTargetOption[arrayListWithCapacity.size()]));
    }
    
    BlobTargetOption(final StorageRpc.Option option, final Object o, final Storage$1 object) {
        this(option, o);
    }
}
