package com.onlly.soft.screencapturelib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// 截屏
public final class CaptureUtil {

    // 全屏
    public static void captureFullScreen(Activity activity, File outPath) throws IOException {
        try {
            Window var10000 = activity.getWindow();
            View view = var10000.getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap b1 = view.getDrawingCache();
            Rect frame = new Rect();
            var10000 = activity.getWindow();
            var10000.getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;
            WindowManager var12 = activity.getWindowManager();
            Display var13 = var12.getDefaultDisplay();
            int width = var13.getWidth();
            var12 = activity.getWindowManager();
            var13 = var12.getDefaultDisplay();
            int height = var13.getHeight();
            Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
            view.destroyDrawingCache();
            FileOutputStream fos = new FileOutputStream(outPath);
            b.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception var11) {
            throw new IOException(var11.getMessage(), var11.getCause());
        }
    }

    // 部分区域
    public static final void captureAreaScreen(View view,File outPath) throws IOException {
        try {
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            FileOutputStream fos = new FileOutputStream(outPath);
            bitmap.compress(CompressFormat.JPEG, 100, (OutputStream)fos);
            fos.flush();
            fos.close();
        } catch (Exception var6) {
            throw new IOException(var6.getMessage(), var6.getCause());
        }
    }

    // 指定区域
    public static void captureTextureView(TextureView tv, File outPath) throws FileNotFoundException, IOException {
        Bitmap bm = tv.getBitmap();
        if (bm == null) {
            return;
        }else {
            FileOutputStream fout = (FileOutputStream)null;
            File imageFile = outPath;
            try {
                fout = new FileOutputStream(imageFile);
                bm.compress(CompressFormat.PNG, 100, (OutputStream)fout);
                fout.flush();
                fout.close();
            } catch (FileNotFoundException var7) {
                throw new FileNotFoundException(var7.getMessage());
            } catch (IOException var8) {
                throw new IOException(var8.getMessage());
            }
        }
    }
}