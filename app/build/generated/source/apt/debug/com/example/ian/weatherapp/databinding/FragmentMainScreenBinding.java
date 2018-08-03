package com.example.ian.weatherapp.databinding;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMainScreenBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    public final android.widget.Button btnRefresh;
    @NonNull
    public final android.support.v7.widget.RecyclerView list;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.ProgressBar progreessBar;
    // variables
    @Nullable
    private com.example.ian.weatherapp.Home.RefreshButtonClickCallback mCallback;
    @Nullable
    private boolean mIsLoading;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMainScreenBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.btnRefresh = (android.widget.Button) bindings[2];
        this.btnRefresh.setTag(null);
        this.list = (android.support.v7.widget.RecyclerView) bindings[1];
        this.list.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progreessBar = (android.widget.ProgressBar) bindings[3];
        this.progreessBar.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.callback == variableId) {
            setCallback((com.example.ian.weatherapp.Home.RefreshButtonClickCallback) variable);
        }
        else if (BR.isLoading == variableId) {
            setIsLoading((boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCallback(@Nullable com.example.ian.weatherapp.Home.RefreshButtonClickCallback Callback) {
        this.mCallback = Callback;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.callback);
        super.requestRebind();
    }
    @Nullable
    public com.example.ian.weatherapp.Home.RefreshButtonClickCallback getCallback() {
        return mCallback;
    }
    public void setIsLoading(boolean IsLoading) {
        this.mIsLoading = IsLoading;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.isLoading);
        super.requestRebind();
    }
    public boolean getIsLoading() {
        return mIsLoading;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.example.ian.weatherapp.Home.RefreshButtonClickCallback callback = mCallback;
        boolean isLoading = mIsLoading;
        boolean IsLoading1 = false;

        if ((dirtyFlags & 0x6L) != 0) {



                // read !isLoading
                IsLoading1 = !isLoading;
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.btnRefresh.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.example.ian.weatherapp.Home.BindingAdapters.showHide(this.btnRefresh, IsLoading1);
            com.example.ian.weatherapp.Home.BindingAdapters.showHide(this.list, IsLoading1);
            com.example.ian.weatherapp.Home.BindingAdapters.showHide(this.progreessBar, isLoading);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // callback
        com.example.ian.weatherapp.Home.RefreshButtonClickCallback callback = mCallback;
        // callback != null
        boolean callbackJavaLangObjectNull = false;



        callbackJavaLangObjectNull = (callback) != (null);
        if (callbackJavaLangObjectNull) {


            callback.onButtonRefreshClick();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentMainScreenBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMainScreenBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentMainScreenBinding>inflate(inflater, com.example.ian.weatherapp.R.layout.fragment_main_screen, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentMainScreenBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMainScreenBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.ian.weatherapp.R.layout.fragment_main_screen, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentMainScreenBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentMainScreenBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_main_screen_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentMainScreenBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): callback
        flag 1 (0x2L): isLoading
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}