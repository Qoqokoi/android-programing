package com.qoqokoi.myapp.databinding;
import com.qoqokoi.myapp.R;
import com.qoqokoi.myapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemInventoryBindingImpl extends ItemInventoryBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemInventoryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemInventoryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.barang == variableId) {
            setBarang((com.qoqokoi.myapp.model.Barang) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBarang(@Nullable com.qoqokoi.myapp.model.Barang Barang) {
        this.mBarang = Barang;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.barang);
        super.requestRebind();
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
        com.qoqokoi.myapp.model.Barang barang = mBarang;
        java.lang.String javaLangStringStokBarangQuantity = null;
        java.lang.String barangName = null;
        int barangPrice = 0;
        int barangQuantity = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (barang != null) {
                    // read barang.name
                    barangName = barang.getName();
                    // read barang.price
                    barangPrice = barang.getPrice();
                    // read barang.quantity
                    barangQuantity = barang.getQuantity();
                }


                // read ("Stok: ") + (barang.quantity)
                javaLangStringStokBarangQuantity = ("Stok: ") + (barangQuantity);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, barangName);
            com.qoqokoi.myapp.ui.BindingAdaptersKt.setFormattedRupiah(this.mboundView2, barangPrice);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, javaLangStringStokBarangQuantity);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): barang
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}