Toolbar:不仅继承了ActionBar的所有功能,而且灵活性很高,可以配合其他控件来完成一些Material Design的效果
使用:
AndroidManifest.xml中的Application的属性 android:theme="" 属性指定了了一个AppTheme的主题,这个主题在:
res/values/themes.xml中

使用Toolbar来替代ActionBar,需要指定一个不带ActionBar的主题:通常有:
Theme.AppCompat.NoActionBar 表示深色主题,它会将界面的主主体演示设成深色,陪衬颜色设成淡色
Theme.AppCompat.Light.NoActionBar  表示淡色主题,它会将界面的主体颜色设成淡色,陪衬颜色设成深色,
之前的程序都是以淡色为主的

在AndroidManifest.xml中的Activity标签中增加label属性 用于指定在Toolbar中显示文字内容 如果没有指定
的话,会默认使用application中指定的label内容,也就是我们的应用名称
１