package com.google.cloud.storage;

import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.List;
import com.google.common.collect.Lists;
import java.util.Arrays;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import com.google.cloud.Tuple;
import com.google.cloud.storage.spi.v1.StorageRpc;
import com.google.common.base.Function;

public static class BlobTargetOption extends Option
{
    private static final Function<BlobTargetOption, StorageRpc.Option> TO_ENUM;
    private static final long serialVersionUID = 8345296337342509425L;
    
    private BlobTargetOption(final StorageRpc.Option option, final Object o) {
        super(option, o);
    }
    
    private Tuple<BlobInfo, Storage.BlobTargetOption> toTargetOption(final BlobInfo blobInfo) {
        final BlobId blobId = blobInfo.getBlobId();
        switch (this.getRpcOption()) {
            case PREDEFINED_ACL:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobTargetOption.predefinedAcl((Storage.PredefinedAcl)this.getValue()));
            case IF_GENERATION_MATCH:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo.toBuilder().setBlobId(BlobId.of(blobId.getBucket(), blobId.getName(), (Long)this.getValue())).build(), (Object)Storage.BlobTargetOption.generationMatch());
            case IF_GENERATION_NOT_MATCH:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo.toBuilder().setBlobId(BlobId.of(blobId.getBucket(), blobId.getName(), (Long)this.getValue())).build(), (Object)Storage.BlobTargetOption.generationNotMatch());
            case IF_METAGENERATION_MATCH:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo.toBuilder().setMetageneration((Long)this.getValue()).build(), (Object)Storage.BlobTargetOption.metagenerationMatch());
            case IF_METAGENERATION_NOT_MATCH:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo.toBuilder().setMetageneration((Long)this.getValue()).build(), (Object)Storage.BlobTargetOption.metagenerationNotMatch());
            case CUSTOMER_SUPPLIED_KEY:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobTargetOption.encryptionKey((String)this.getValue()));
            case KMS_KEY_NAME:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobTargetOption.kmsKeyName((String)this.getValue()));
            case USER_PROJECT:
                return (Tuple<BlobInfo, Storage.BlobTargetOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobTargetOption.userProject((String)this.getValue()));
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    public static BlobTargetOption predefinedAcl(final Storage.PredefinedAcl predefinedAcl) {
        return new BlobTargetOption(StorageRpc.Option.PREDEFINED_ACL, predefinedAcl);
    }
    
    public static BlobTargetOption doesNotExist() {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_MATCH, 0L);
    }
    
    public static BlobTargetOption generationMatch(final long n) {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_MATCH, n);
    }
    
    public static BlobTargetOption generationNotMatch(final long n) {
        return new BlobTargetOption(StorageRpc.Option.IF_GENERATION_NOT_MATCH, n);
    }
    
    public static BlobTargetOption metagenerationMatch(final long n) {
        return new BlobTargetOption(StorageRpc.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BlobTargetOption metagenerationNotMatch(final long n) {
        return new BlobTargetOption(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BlobTargetOption encryptionKey(final Key key) {
        return new BlobTargetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobTargetOption encryptionKey(final String s) {
        return new BlobTargetOption(StorageRpc.Option.CUSTOMER_SUPPLIED_KEY, s);
    }
    
    public static BlobTargetOption kmsKeyName(final String s) {
        return new BlobTargetOption(StorageRpc.Option.KMS_KEY_NAME, s);
    }
    
    public static BlobTargetOption userProject(final String s) {
        return new BlobTargetOption(StorageRpc.Option.USER_PROJECT, s);
    }
    
    static Tuple<BlobInfo, Storage.BlobTargetOption[]> toTargetOptions(final BlobInfo blobInfo, final BlobTargetOption... array) {
        final ImmutableSet immutableEnumSet = Sets.immutableEnumSet((Iterable)Lists.<Object, Object>transform((List<Object>)Arrays.<F>asList((F[])array), (Function<? super Object, ?>)BlobTargetOption.TO_ENUM));
        Preconditions.checkArgument(!immutableEnumSet.contains(StorageRpc.Option.IF_METAGENERATION_NOT_MATCH) || !immutableEnumSet.contains(StorageRpc.Option.IF_METAGENERATION_MATCH), (Object)"metagenerationMatch and metagenerationNotMatch options can not be both provided");
        Preconditions.checkArgument(!immutableEnumSet.contains(StorageRpc.Option.IF_GENERATION_NOT_MATCH) || !immutableEnumSet.contains(StorageRpc.Option.IF_GENERATION_MATCH), (Object)"Only one option of generationMatch, doesNotExist or generationNotMatch can be provided");
        final Storage.BlobTargetOption[] array2 = new Storage.BlobTargetOption[array.length];
        BlobInfo blobInfo2 = blobInfo;
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            final Tuple<BlobInfo, Storage.BlobTargetOption> targetOption = array[i].toTargetOption(blobInfo2);
            blobInfo2 = (BlobInfo)targetOption.x();
            array2[n++] = (Storage.BlobTargetOption)targetOption.y();
        }
        return (Tuple<BlobInfo, Storage.BlobTargetOption[]>)Tuple.of((Object)blobInfo2, (Object)array2);
    }
    
    static {
        TO_ENUM = new Function<BlobTargetOption, StorageRpc.Option>() {
            Bucket$BlobTargetOption$1() {
                super();
            }
            
            @Override
            public StorageRpc.Option apply(final BlobTargetOption blobTargetOption) {
                return blobTargetOption.getRpcOption();
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((BlobTargetOption)o);
            }
        };
    }
}
