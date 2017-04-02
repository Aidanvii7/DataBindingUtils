package com.aidanvii.databinding.utils.suppliers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.aidanvii.databinding.utils.Preconditions.checkArgumentIsNotNull;
import static com.aidanvii.databinding.utils.Preconditions.checkNotNull;

/**
 * Created by aidan.vii@gmail.com on 21/11/16.
 */
public class Lazy<T> implements FunctionalSupplier<T> {

    private Supplier<T> lazySupplier = null;
    @Nullable
    private T lazyReference = null;

    Lazy(@NonNull Supplier<T> lazySupplier) {
        this.lazySupplier = checkArgumentIsNotNull(lazySupplier);
    }

    /**
     * Creates an instance of {@link Lazy}.
     */
    public static <T> Lazy<T> create(@NonNull Supplier<T> lazySupplier) {
        return new Lazy<>(lazySupplier);
    }

    /**
     * Creates a thread-safe instance of {@link Lazy}.
     */
    public static <T> Lazy<T> createSynchronised(@NonNull Supplier<T> lazySupplier) {
        return new LazyInitSynchronised<>(lazySupplier);
    }

    /**
     * Gets the reference from the {@link Supplier} passed to {@link #create(Supplier)} or {@link #createSynchronised(Supplier)} .
     * <p>This will throw an exception if the {@link Supplier} returns null.</p>
     */
    @NonNull
    public T get() {
        if (this.lazyReference == null) {
            lazyReference = checkNotNull(lazySupplier.get());
            lazySupplier = null;
        }
        return lazyReference;
    }

    @Override
    public <R> R executeWithResult(Function<T, R> function) {
        return function.apply(get());
    }

    @Override
    public void execute(VoidFunction<T> function) {
        function.apply(get());
    }


    private static final class LateInitException extends RuntimeException {
        LateInitException(String s) {
            super(s);
        }
    }

    static final class LazyInitSynchronised<T> extends Lazy<T> {

        LazyInitSynchronised(@NonNull Supplier<T> lazySupplier) {
            super(lazySupplier);
        }

        @NonNull
        @Override
        public final synchronized T get() {
            return super.get();
        }

        @Override
        public final synchronized <R> R executeWithResult(Function<T, R> function) {
            return super.executeWithResult(function);
        }

        @Override
        public final synchronized void execute(VoidFunction<T> function) {
            super.execute(function);
        }
    }
}
