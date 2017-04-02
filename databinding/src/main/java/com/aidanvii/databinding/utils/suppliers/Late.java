package com.aidanvii.databinding.utils.suppliers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.aidanvii.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 21/11/16.
 * <p>Holds a reference to an object that should be initialised via {@link #set(Object)}.</p>
 */
public class Late<T> implements FunctionalSupplier<T> {

    @Nullable
    private T lateReference = null;

    Late() {

    }

    /**
     * Creates an instance of {@link Late}.
     */
    public static <T> Late<T> create() {
        return new Late<>();
    }

    /**
     * Creates a thread-safe instance of {@link Late}.
     */
    public static <T> Late<T> createSynchronised() {
        return new LateInitSynchronised<>();
    }

    /**
     * Gets the reference given to {@link #set(T lateReference)}.
     * <p>This will throw an exception if not set. See {@link #set(T lateReference)}.</p>
     */
    @NonNull
    public T get() {
        if (this.lateReference != null) {
            return lateReference;
        } else {
            throw new LateInitException("property marked as late initDelegate was not initialised");
        }
    }

    @Override
    public <R> R executeWithResult(final Function<T, R> function) {
        return function.apply(get());
    }

    @Override
    public void execute(final VoidFunction<T> function) {
        function.apply(get());
    }

    /**
     * Sets the reference.
     * <p>This will throw an exception if {@link #set(T lateReference)} has already been called.</p>
     *
     * @param lateReference
     */
    public void set(@NonNull T lateReference) {
        checkArgumentIsNotNull(lateReference);
        if (this.lateReference == null) {
            this.lateReference = lateReference;
        } else {
            throw new LateInitException("late initialised property has already been initialised");
        }
    }

    private static final class LateInitException extends RuntimeException {
        LateInitException(String s) {
            super(s);
        }
    }

    static final class LateInitSynchronised<T> extends Late<T> {

        @NonNull
        @Override
        public final synchronized T get() {
            return super.get();
        }

        @Override
        public final synchronized void set(@NonNull T lateReference) {
            super.set(lateReference);
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
