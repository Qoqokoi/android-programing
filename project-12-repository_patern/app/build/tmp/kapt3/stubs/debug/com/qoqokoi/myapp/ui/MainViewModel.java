package com.qoqokoi.myapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/qoqokoi/myapp/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/qoqokoi/myapp/data/repository/AppRepository;", "(Lcom/qoqokoi/myapp/data/repository/AppRepository;)V", "devices", "Landroidx/lifecycle/LiveData;", "", "Lcom/qoqokoi/myapp/data/local/DeviceEntity;", "getDevices", "()Landroidx/lifecycle/LiveData;", "manualRefresh", "", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.qoqokoi.myapp.data.repository.AppRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.qoqokoi.myapp.data.local.DeviceEntity>> devices = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.qoqokoi.myapp.data.repository.AppRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.qoqokoi.myapp.data.local.DeviceEntity>> getDevices() {
        return null;
    }
    
    public final void manualRefresh() {
    }
}