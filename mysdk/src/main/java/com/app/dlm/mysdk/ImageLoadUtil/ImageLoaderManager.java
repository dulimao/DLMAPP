package com.app.dlm.mysdk.ImageLoadUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.app.dlm.mysdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2017/4/28.
 * 初始化universal_imageloader，并用来加载图片
 */
public class ImageLoaderManager {

    //默认的参数设置
    private static final int THREAD_COUNT = 4;//UIL最多可达到的线程数
    private static final int PROPERTY = 2;//图片加载的优先级
    private static final int DISK_CACHE_SIZE = 50 * 1024;//磁盘缓存大小 50M
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接超时
    private static final int READ_TIME_OUT = 30 * 1000;//读取超时时间

    private static ImageLoader mImageLoader = null;

    private static volatile ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }


    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY - PROPERTY)
                .denyCacheImageMultipleSizesInMemory()//禁止缓存多套不同尺寸的图片到内存
                .memoryCache(new WeakMemoryCache())//弱引用的内存缓存
                .diskCacheSize(DISK_CACHE_SIZE)//硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.FIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())//默认的图片加载OPtions
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))//设置图片下载器
                .writeDebugLogs()//debug环境会输出日志
                .build();

        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }


    /**
     * 实现默认的options
     *
     * @return
     */
    public DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)
                .showImageOnFail(R.drawable.xadsdk_img_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//图片解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .build();
        return options;
    }


    /**
     * 加载图片API
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {
        if (mImageLoader != null) {
            mImageLoader.displayImage(url, imageView, options, listener);
        }
    }

    public void displayImage(ImageView imageView,String url,ImageLoadingListener listener){
        displayImage(imageView,url,null,listener);
    }

    public void displayImage(ImageView imageView,String url){
        displayImage(imageView,url,null);

    }
}
