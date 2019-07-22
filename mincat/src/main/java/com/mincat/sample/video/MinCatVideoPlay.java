package com.mincat.sample.video;

import android.content.Context;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author Gin
 * @描述 播放网络视频
 */

public class MinCatVideoPlay {

    // 禁止创建此类对象
    private MinCatVideoPlay() {
    }

    /**
     * @param context  上下文对象
     * @param player   JCVideoPlayerStandard对象
     * @param videoUrl 网路视频url地址
     * @param imageUrl 视频默认加载图片url地址
     */
    public static void play(Context context, JCVideoPlayerStandard player, String videoUrl, String imageUrl) {

        boolean setUp = player.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(imageUrl).into(player.thumbImageView);
        }

        player.startFullscreen(context, JCVideoPlayerStandard.class, videoUrl, "");


        //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
        player.startButton.performClick();
    }
}
