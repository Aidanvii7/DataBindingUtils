package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 18/10/16.
 */
public final class RecyclerViewBindingAdapters {

    interface BinderDelegate<AdapterItem extends ResourceIdProvider, Binder extends RecyclerViewBinder<AdapterItem, ?, ?, ?>, BindableCollection> {
        void bind(RecyclerView recyclerView, Binder binder, BindableCollection bindableCollection);
    }

    private static final String BIND_BINDER = "bind_binder";
    private static final String BIND_COLLECTION = "bind_collection";
    private static final HashMap<Pair<Class<? extends RecyclerViewBinder>, Class>, BinderDelegate> delegateMap = new HashMap<>();

    private RecyclerViewBindingAdapters() {
    }

    static void addDelegateMapping(@NonNull Class<? extends RecyclerViewBinder> binderClass, @NonNull Class collectionClass, @NonNull BinderDelegate binderDelegate) {
        checkArgumentIsNotNull(binderClass);
        checkArgumentIsNotNull(collectionClass);
        checkArgumentIsNotNull(binderDelegate);
        delegateMap.put(delegateKeyFor(binderClass, collectionClass), binderDelegate);
    }

    @BindingAdapter({BIND_BINDER, BIND_COLLECTION})
    public static <AdapterItem extends ResourceIdProvider, BindableCollection> void bindBinder(RecyclerView recyclerView, @NonNull RecyclerViewBinder<AdapterItem, ?, ?, ?> binder, @NonNull BindableCollection bindableCollection) {
        checkArgumentIsNotNull(binder);
        checkArgumentIsNotNull(bindableCollection);
        recyclerView.setAdapter(binder.getAdapter());
        bindUnknownCollection(recyclerView, binder, bindableCollection);
        bindLayoutManager(recyclerView, binder.getLayoutManager());
    }

    @SuppressWarnings("unchecked")
    private static <AdapterItem extends ResourceIdProvider, BindableCollection> void bindUnknownCollection(final RecyclerView recyclerView, final @NonNull RecyclerViewBinder<AdapterItem, ?, ?, ?> binder, final @NonNull BindableCollection bindableCollection) {
        final Pair<Class<? extends RecyclerViewBinder>, Class> key = delegateKeyFor(binder.getClass(), binder.getCollectionClass());

        final BinderDelegate delegate = delegateMap.get(key);
        if (delegate != null) {
            delegate.bind(recyclerView, binder, bindableCollection);
        } else {
            throw new RuntimeException("no class mapping was installed for : " + binder.getCollectionClass());
        }
    }

    private static void bindLayoutManager(RecyclerView recyclerView, @NonNull RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @NonNull
    private static Pair<Class<? extends RecyclerViewBinder>, Class> delegateKeyFor(final @NonNull Class<? extends RecyclerViewBinder> binderClass, final @NonNull Class collectionClass) {
        return new Pair<Class<? extends RecyclerViewBinder>, Class>(binderClass, collectionClass);
    }

    @SuppressWarnings("unchecked")
    static <T> T cast(Object uncast) {
        return (T) uncast;
    }
}