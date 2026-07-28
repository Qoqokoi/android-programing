package com.qoqokoi.myapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/qoqokoi/myapp/data/repository/AppRepository;", "", "deviceDao", "Lcom/qoqokoi/myapp/data/local/DeviceDao;", "networkService", "Lcom/qoqokoi/myapp/data/remote/NetworkService;", "(Lcom/qoqokoi/myapp/data/local/DeviceDao;Lcom/qoqokoi/myapp/data/remote/NetworkService;)V", "allDevices", "Landroidx/lifecycle/LiveData;", "", "Lcom/qoqokoi/myapp/data/local/DeviceEntity;", "getAllDevices", "()Landroidx/lifecycle/LiveData;", "refreshData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AppRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.qoqokoi.myapp.data.local.DeviceDao deviceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.qoqokoi.myapp.data.remote.NetworkService networkService = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.qoqokoi.myapp.data.local.DeviceEntity>> allDevices = null;
    
    public AppRepository(@org.jetbrains.annotations.NotNull()
    com.qoqokoi.myapp.data.local.DeviceDao deviceDao, @org.jetbrains.annotations.NotNull()
    com.qoqokoi.myapp.data.remote.NetworkService networkService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.qoqokoi.myapp.data.local.DeviceEntity>> getAllDevices() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}