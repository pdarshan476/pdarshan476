package com.orataro.adaapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.edusunsoft.orataro.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orataro.customeview.PanAndZoomListener;
import com.orataro.customeview.PanAndZoomListener.Anchor;
import com.orataro.services.ServiceResource;

public class ViewPagerAdapter extends PagerAdapter {

	Context mContext;
	ArrayList<String> list;
	private ImageView iv_fb;
	
	private ImageLoader mImageLoader;
	private DisplayImageOptions mOptions;
	private int mPreviousPosition;

	public ViewPagerAdapter(Context mContext,ArrayList<String> list) {
		this.mContext = mContext;
		this.list = list;
		
		

		mImageLoader = ImageLoader.getInstance();
		mOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.no_img)
		.showImageForEmptyUri(R.drawable.no_img)
		.showImageOnFail(R.drawable.no_img).cacheInMemory(true)
		.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();

		mImageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}
	
	
	@Override
	public View instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
//		 super.instantiateItem(container, position);
		
		 LayoutInflater inflater = LayoutInflater.from(mContext);
	        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.zoomimage, container, false);
	        iv_fb = (ImageView) layout.findViewById(R.id.iv_fb_zoom);
	        iv_fb.setScaleType(ImageView.ScaleType.MATRIX); 
			LinearLayout ll_fb = (LinearLayout)layout. findViewById(R.id.ll_fb);
			ll_fb.setOnTouchListener(new PanAndZoomListener(ll_fb, iv_fb,
					Anchor.TOPLEFT));
//			if (position > mPreviousPosition) {
//			final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.flip_left_in);
//			  layout.startAnimation(animAnticipateOvershoot);
//			 }else{
//				 final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.flip_right_in);
//				  layout.startAnimation(animAnticipateOvershoot);
//			 }
//		     mPreviousPosition = position;
			if(list.get(position) != null && !list.get(position).equalsIgnoreCase("")){

//				if(list.get(position).contains(ServiceResource.BASE_IMG_URL))
//				{
					mImageLoader.displayImage(
							ServiceResource.BASE_IMG_URL+"Datafiles/"+
									list.get(position).replace("//DataFiles//", "/DataFiles/")
														.replace("//DataFiles/", "/DataFiles/"), iv_fb,
							mOptions);
//				}
//				else
//				{
//					//	      				mImageLoader.displayImage(
//					//							photoModels.get(position).getPhoto(), iv_profile_pic,
//					//							mOptions);
//
//					Bitmap bm;
//					BitmapFactory.Options options = new BitmapFactory.Options();
//					options.inJustDecodeBounds = true;
//					BitmapFactory.decodeFile(list.get(position), options);
//
//					final int REQUIRED_SIZE = 200;
//					int scale = 1;
//					while (options.outWidth / scale / 2 >= REQUIRED_SIZE
//							&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
//						scale *= 2;
//					options.inSampleSize = scale;
//					options.inJustDecodeBounds = false;
//					bm = BitmapFactory.decodeFile(list.get(position), options);
//
//					iv_fb.setImageBitmap(bm);			
//				}
			}


			//			mImageLoader.displayImage(img, iv_fb,
			//					mOptions);	

			//			iv_fb.setImageBitmap(Utility.getBitmapFromAsset(mContext, img));
		
			
			
	        container.addView(layout);
			return layout;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
//		super.destroyItem(container, position, object);
		container.removeViewInLayout((View)object);
	}
}
