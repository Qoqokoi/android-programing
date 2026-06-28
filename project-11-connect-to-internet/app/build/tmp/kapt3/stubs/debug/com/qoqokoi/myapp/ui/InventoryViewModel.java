package com.qoqokoi.myapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/qoqokoi/myapp/ui/InventoryViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_postsState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/qoqokoi/myapp/model/DataItem;", "postsState", "Lkotlinx/coroutines/flow/StateFlow;", "getPostsState", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchNetworkData", "", "app_debug"})
public final class InventoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.qoqokoi.myapp.model.DataItem>> _postsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.qoqokoi.myapp.model.DataItem>> postsState = null;
    
    public InventoryViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.qoqokoi.myapp.model.DataItem>> getPostsState() {
        return null;
    }
    
    private final void fetchNetworkData() {
    }
}