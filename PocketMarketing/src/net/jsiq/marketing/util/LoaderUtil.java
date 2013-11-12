package net.jsiq.marketing.util;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.CatalogItem;
import net.jsiq.marketing.model.Content;
import net.jsiq.marketing.model.MenuItem;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class LoaderUtil {

	private static ImageLoader imageLoader;
	private static DisplayImageOptions options;

	public static void displayImage(Context context, String uri,
			ImageView imageView) {
		getImageLoader(context).displayImage(uri, imageView,
				getDisplayImageOptions());
	}

	public static List<MenuItem> loadMenuItems(String url) {
		String json = getRequestViaUrl(url);
		return JSONParser.JSON2MenuItems(json);
	}

	public static List<CatalogItem> loadCatalogItems(String url) {
		String json = getRequestViaUrl(url);
		return JSONParser.JSON2CatalogItems(json);
	}

	public static List<Content> loadContentItems(String url) {
		String json = getRequestViaUrl(url);
		return JSONParser.JSON2Content(json);
	}

	private static String getRequestViaUrl(String url) {
		try {
			return JsonHttpUtils.getRequest(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static synchronized ImageLoader getImageLoader(Context context) {
		if (null == imageLoader) {
			imageLoader = ImageLoader.getInstance();
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					context).threadPriority(Thread.NORM_PRIORITY - 2)
					.denyCacheImageMultipleSizesInMemory()
					.discCacheFileNameGenerator(new Md5FileNameGenerator())
					.tasksProcessingOrder(QueueProcessingType.LIFO).build();
			imageLoader.init(config);
		}
		return imageLoader;
	}

	private static synchronized DisplayImageOptions getDisplayImageOptions() {
		if (null == options) {
			options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.ic_stub)
					.showImageForEmptyUri(R.drawable.ic_empty)
					.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
					.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		}
		return options;
	}

}
