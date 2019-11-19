package com.google.cloud.storage.testing;

import java.util.Iterator;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;

static final class RemoteStorageHelper$1 implements Runnable {
    final /* synthetic */ Storage val$storage;
    final /* synthetic */ long val$olderThan;
    
    RemoteStorageHelper$1(final Storage val$storage, final long val$olderThan) {
        this.val$storage = val$storage;
        this.val$olderThan = val$olderThan;
        super();
    }
    
    @Override
    public void run() {
        for (final Bucket bucket : this.val$storage.list(Storage.BucketListOption.prefix("gcloud-test-bucket-temp-")).iterateAll()) {
            if (bucket.getCreateTime() < this.val$olderThan) {
                try {
                    for (final Blob blob : bucket.list(Storage.BlobListOption.fields(Storage.BlobField.EVENT_BASED_HOLD, Storage.BlobField.TEMPORARY_HOLD)).iterateAll()) {
                        if (blob.getEventBasedHold() || blob.getTemporaryHold()) {
                            this.val$storage.update(blob.toBuilder().setTemporaryHold(false).setEventBasedHold(false).build());
                        }
                    }
                    RemoteStorageHelper.forceDelete(this.val$storage, bucket.getName());
                }
                catch (Exception ex) {}
            }
        }
    }
}