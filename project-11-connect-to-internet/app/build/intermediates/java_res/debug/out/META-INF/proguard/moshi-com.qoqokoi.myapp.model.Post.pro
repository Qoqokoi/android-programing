-if class com.qoqokoi.myapp.model.Post
-keepnames class com.qoqokoi.myapp.model.Post
-if class com.qoqokoi.myapp.model.Post
-keep class com.qoqokoi.myapp.model.PostJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
