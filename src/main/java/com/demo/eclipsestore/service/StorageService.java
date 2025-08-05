package com.demo.eclipsestore.service;

import com.demo.eclipsestore.model.Library;
import lombok.Getter;
import org.eclipse.store.storage.embedded.types.EmbeddedStorage;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

public class StorageService {

    @Getter private Library root;

    private EmbeddedStorageManager storageManager;

    public StorageService() {
        initializeStorage();
    }

    private void initializeStorage() {
        storageManager = EmbeddedStorage.start();
        root = (Library) storageManager.root();

        if (root == null) {
            root = new Library();
            storageManager.setRoot(root);
            storageManager.storeRoot();
        }
    }

    public void saveLibrary(final Library library) {
        storageManager.store(library);
    }

    public void saveBooks() {
        storageManager.store(root.getBooks());
    }

    public void shutdown() {
        storageManager.shutdown();
    }
}
