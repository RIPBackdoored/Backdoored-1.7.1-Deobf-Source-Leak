package com.google.cloud.storage.testing;

import java.util.List;
import java.util.Iterator;
import com.google.cloud.storage.Blob;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.BlobInfo;
import com.google.common.base.Strings;
import com.google.cloud.storage.BlobId;
import java.util.ArrayList;
import com.google.cloud.storage.Storage;
import java.util.concurrent.Callable;

private static class DeleteBucketTask implements Callable<Boolean>
{
    private final Storage storage;
    private final String bucket;
    private final String userProject;
    
    public DeleteBucketTask(final Storage storage, final String bucket) {
        super();
        this.storage = storage;
        this.bucket = bucket;
        this.userProject = "";
    }
    
    public DeleteBucketTask(final Storage storage, final String bucket, final String userProject) {
        super();
        this.storage = storage;
        this.bucket = bucket;
        this.userProject = userProject;
    }
    
    @Override
    public Boolean call() {
        while (true) {
            final ArrayList<BlobId> list = new ArrayList<BlobId>();
            Page<Blob> page;
            if (Strings.isNullOrEmpty(this.userProject)) {
                page = this.storage.list(this.bucket, Storage.BlobListOption.versions(true));
            }
            else {
                page = this.storage.list(this.bucket, Storage.BlobListOption.versions(true), Storage.BlobListOption.userProject(this.userProject));
            }
            final Iterator iterator = page.getValues().iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().getBlobId());
            }
            if (!list.isEmpty()) {
                final List<Boolean> delete = this.storage.delete(list);
                if (!Strings.isNullOrEmpty(this.userProject)) {
                    for (int i = 0; i < delete.size(); ++i) {
                        if (!delete.get(i)) {
                            this.storage.delete(this.bucket, list.get(i).getName(), Storage.BlobSourceOption.userProject(this.userProject));
                        }
                    }
                }
            }
            try {
                if (Strings.isNullOrEmpty(this.userProject)) {
                    this.storage.delete(this.bucket, new Storage.BucketSourceOption[0]);
                }
                else {
                    this.storage.delete(this.bucket, Storage.BucketSourceOption.userProject(this.userProject));
                }
                return true;
            }
            catch (StorageException ex) {
                if (ex.getCode() == 409) {
                    try {
                        Thread.sleep(500L);
                        continue;
                    }
                    catch (InterruptedException ex2) {
                        Thread.currentThread().interrupt();
                        throw ex;
                    }
                    throw ex;
                    continue;
                }
                throw ex;
            }
        }
    }
    
    @Override
    public /* bridge */ Object call() throws Exception {
        return this.call();
    }
}
