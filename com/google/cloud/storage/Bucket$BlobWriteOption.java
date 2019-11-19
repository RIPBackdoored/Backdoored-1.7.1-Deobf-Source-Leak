package com.google.cloud.storage;

import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.List;
import com.google.common.collect.Lists;
import java.util.Arrays;
import com.google.common.io.BaseEncoding;
import java.security.Key;
import java.util.Objects;
import com.google.cloud.Tuple;
import com.google.common.base.Function;
import java.io.Serializable;

public static class BlobWriteOption implements Serializable
{
    private static final Function<BlobWriteOption, Storage.BlobWriteOption.Option> TO_ENUM;
    private static final long serialVersionUID = 4722190734541993114L;
    private final Storage.BlobWriteOption.Option option;
    private final Object value;
    
    private Tuple<BlobInfo, Storage.BlobWriteOption> toWriteOption(final BlobInfo blobInfo) {
        final BlobId blobId = blobInfo.getBlobId();
        switch (this.option) {
            case PREDEFINED_ACL:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobWriteOption.predefinedAcl((Storage.PredefinedAcl)this.value));
            case IF_GENERATION_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setBlobId(BlobId.of(blobId.getBucket(), blobId.getName(), (Long)this.value)).build(), (Object)Storage.BlobWriteOption.generationMatch());
            case IF_GENERATION_NOT_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setBlobId(BlobId.of(blobId.getBucket(), blobId.getName(), (Long)this.value)).build(), (Object)Storage.BlobWriteOption.generationNotMatch());
            case IF_METAGENERATION_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setMetageneration((Long)this.value).build(), (Object)Storage.BlobWriteOption.metagenerationMatch());
            case IF_METAGENERATION_NOT_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setMetageneration((Long)this.value).build(), (Object)Storage.BlobWriteOption.metagenerationNotMatch());
            case IF_MD5_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setMd5((String)this.value).build(), (Object)Storage.BlobWriteOption.md5Match());
            case IF_CRC32C_MATCH:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo.toBuilder().setCrc32c((String)this.value).build(), (Object)Storage.BlobWriteOption.crc32cMatch());
            case CUSTOMER_SUPPLIED_KEY:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobWriteOption.encryptionKey((String)this.value));
            case KMS_KEY_NAME:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobWriteOption.kmsKeyName((String)this.value));
            case USER_PROJECT:
                return (Tuple<BlobInfo, Storage.BlobWriteOption>)Tuple.of((Object)blobInfo, (Object)Storage.BlobWriteOption.userProject((String)this.value));
            default:
                throw new AssertionError((Object)"Unexpected enum value");
        }
    }
    
    private BlobWriteOption(final Storage.BlobWriteOption.Option option, final Object value) {
        super();
        this.option = option;
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.option, this.value);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BlobWriteOption)) {
            return false;
        }
        final BlobWriteOption blobWriteOption = (BlobWriteOption)o;
        return this.option == blobWriteOption.option && Objects.equals(this.value, blobWriteOption.value);
    }
    
    public static BlobWriteOption predefinedAcl(final Storage.PredefinedAcl predefinedAcl) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.PREDEFINED_ACL, predefinedAcl);
    }
    
    public static BlobWriteOption doesNotExist() {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_GENERATION_MATCH, 0L);
    }
    
    public static BlobWriteOption generationMatch(final long n) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_GENERATION_MATCH, n);
    }
    
    public static BlobWriteOption generationNotMatch(final long n) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_GENERATION_NOT_MATCH, n);
    }
    
    public static BlobWriteOption metagenerationMatch(final long n) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_METAGENERATION_MATCH, n);
    }
    
    public static BlobWriteOption metagenerationNotMatch(final long n) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_METAGENERATION_NOT_MATCH, n);
    }
    
    public static BlobWriteOption md5Match(final String s) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_MD5_MATCH, s);
    }
    
    public static BlobWriteOption crc32cMatch(final String s) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.IF_CRC32C_MATCH, s);
    }
    
    public static BlobWriteOption encryptionKey(final Key key) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.CUSTOMER_SUPPLIED_KEY, BaseEncoding.base64().encode(key.getEncoded()));
    }
    
    public static BlobWriteOption encryptionKey(final String s) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.CUSTOMER_SUPPLIED_KEY, s);
    }
    
    public static BlobWriteOption userProject(final String s) {
        return new BlobWriteOption(Storage.BlobWriteOption.Option.USER_PROJECT, s);
    }
    
    static Tuple<BlobInfo, Storage.BlobWriteOption[]> toWriteOptions(final BlobInfo blobInfo, final BlobWriteOption... array) {
        final ImmutableSet immutableEnumSet = Sets.immutableEnumSet((Iterable)Lists.<Object, Object>transform((List<Object>)Arrays.<F>asList((F[])array), (Function<? super Object, ?>)BlobWriteOption.TO_ENUM));
        Preconditions.checkArgument(!immutableEnumSet.contains(Storage.BlobWriteOption.Option.IF_METAGENERATION_NOT_MATCH) || !immutableEnumSet.contains(Storage.BlobWriteOption.Option.IF_METAGENERATION_MATCH), (Object)"metagenerationMatch and metagenerationNotMatch options can not be both provided");
        Preconditions.checkArgument(!immutableEnumSet.contains(Storage.BlobWriteOption.Option.IF_GENERATION_NOT_MATCH) || !immutableEnumSet.contains(Storage.BlobWriteOption.Option.IF_GENERATION_MATCH), (Object)"Only one option of generationMatch, doesNotExist or generationNotMatch can be provided");
        final Storage.BlobWriteOption[] array2 = new Storage.BlobWriteOption[array.length];
        BlobInfo blobInfo2 = blobInfo;
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            final Tuple<BlobInfo, Storage.BlobWriteOption> writeOption = array[i].toWriteOption(blobInfo2);
            blobInfo2 = (BlobInfo)writeOption.x();
            array2[n++] = (Storage.BlobWriteOption)writeOption.y();
        }
        return (Tuple<BlobInfo, Storage.BlobWriteOption[]>)Tuple.of((Object)blobInfo2, (Object)array2);
    }
    
    static /* synthetic */ Storage.BlobWriteOption.Option access$000(final BlobWriteOption blobWriteOption) {
        return blobWriteOption.option;
    }
    
    static {
        TO_ENUM = new Function<BlobWriteOption, Storage.BlobWriteOption.Option>() {
            Bucket$BlobWriteOption$1() {
                super();
            }
            
            @Override
            public Storage.BlobWriteOption.Option apply(final BlobWriteOption blobWriteOption) {
                return blobWriteOption.option;
            }
            
            @Override
            public /* bridge */ Object apply(final Object o) {
                return this.apply((BlobWriteOption)o);
            }
        };
    }
}
