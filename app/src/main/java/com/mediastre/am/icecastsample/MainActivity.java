package com.mediastre.am.icecastsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import org.json.JSONObject;

import am.mediastre.mediastreamplatformsdkandroid.MediastreamMiniPlayerConfig;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayer;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerCallback;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerConfig;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerService;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private MediastreamPlayer player;
    private MediastreamPlayerConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new MediastreamPlayerConfig();
        config.id = "5faee885eb830d06cf22ed4a";
        config.accountID = "5faaeb72f92d7b07dfe10181";
        config.type = MediastreamPlayerConfig.VideoTypes.LIVE;
        config.videoFormat = MediastreamPlayerConfig.AudioVideoFormat.ICECAST;
        config.playerType = MediastreamPlayerConfig.PlayerType.AUDIO;
        config.appName = "Sample-RPP-SDK-Icecast";
        config.appVersion = "1.0.0";
        container = findViewById(R.id.main_media_frame);
        MediastreamPlayerService.setupService(config, this, container, new MediastreamPlayerCallback() {
            @Override
            public void onPlay() {
                System.out.println("Sample is Playing");
            }

            @Override
            public void onPause() {
                System.out.println("Sample is Paused");
            }

            @Override
            public void onReady() {
                System.out.println("Sample is Ready");
            }

            @Override
            public void onEnd() {
                System.out.println("Sample End");
            }

            @Override
            public void onBuffering() {
                System.out.println("Sample Buffering");
            }

            @Override
            public void onError(String error) {
                System.out.println("Sample Error: " + error);
            }

            @Override
            public void onNext() {

            }

            @Override
            public void onPrevious() {

            }

            @Override
            public void onFullscreen() {

            }

            @Override
            public void offFullscreen() {

            }

            @Override
            public void onNewSourceAdded() {

            }

            @Override
            public void onLocalSourceAdded() {

            }

            @Override
            public void onAdPlay() {

            }

            @Override
            public void onAdPause() {

            }

            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdResume() {

            }

            @Override
            public void onAdSkipped() {

            }

            @Override
            public void onAdSkippableStateChanged() {

            }

            @Override
            public void onAdEnded() {

            }

            @Override
            public void onAdError() {

            }

            @Override
            public void onAdBuffering() {

            }

            @Override
            public void onConfigChange(MediastreamMiniPlayerConfig config) {

            }

            @Override
            public void onCastAvailable(Boolean state) {

            }

            @Override
            public void onCastSessionStarting() {

            }

            @Override
            public void onCastSessionStarted() {

            }

            @Override
            public void onCastSessionStartFailed() {

            }

            @Override
            public void onCastSessionEnding() {

            }

            @Override
            public void onCastSessionEnded() {

            }

            @Override
            public void onCastSessionResuming() {

            }

            @Override
            public void onCastSessionResumed() {

            }

            @Override
            public void onCastSessionResumeFailed() {

            }

            @Override
            public void onCastSessionSuspended() {

            }

            @Override
            public void onPlaybackErrors(JSONObject error) {

            }

            @Override
            public void onEmbedErrors(JSONObject error) {

            }

            @Override
            public void onLiveAudioCurrentSongChanged(JSONObject data) {

            }
        });
        Intent serviceIntent = new Intent(this, MediastreamPlayerService.class);
        serviceIntent.setAction(getPackageName()+".action.startforeground");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // player.releasePlayer();
        Intent serviceIntent = new Intent(this, MediastreamPlayerService.class);
        serviceIntent.setAction(getPackageName()+".action.stopforeground");
        try {
            ContextCompat.startForegroundService(this, serviceIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        // player.releasePlayer();
        Intent serviceIntent = new Intent(this, MediastreamPlayerService.class);
        serviceIntent.setAction(getPackageName()+".action.stopforeground");
        try {
            ContextCompat.startForegroundService(this, serviceIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}