package com.mincat.sample.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mincat.sample.R;


/**
 * @author Gin
 *
 * @描述 让图片旋转起来
 */
public class RefreshImage {

	public static void startRotateImage(Context context, ImageView refreshImage) {

		// 显示刷新动画
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.image_refresh);
		// 设置重复模式
		animation.setRepeatMode(Animation.RESTART);
		// 设置重复次数
		animation.setRepeatCount(Animation.INFINITE);
		// 使用ImageView 显示旋转动画
		refreshImage.startAnimation(animation);
	}

	public static void endRotateImage(ImageView refreshImage, int resourceId) {

		refreshImage.clearAnimation();
		refreshImage.setImageResource(resourceId);
	}

}
