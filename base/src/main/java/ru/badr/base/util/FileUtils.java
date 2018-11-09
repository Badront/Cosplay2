package ru.badr.base.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Created by ABadretdinov
 * 24.06.2015
 * 16:12
 */
public class FileUtils {

    public static String baseFilePath(Context context, String baseUrl) {
        return FileUtils.getFilesPath(context)
                + File.separator + baseUrl.replaceAll("\\*|\\\\|\\/|\"|:|\\?|\\||<|>", "_");
    }

    public static String getFilesPath(Context context) {
        if (DeviceInfo.isExternalStorageWritable()) {
            File dir = context.getExternalFilesDir(null);
            return dir != null ? dir.getAbsolutePath() : null;
        } else {
            return context.getFilesDir().getAbsolutePath();
        }
    }

    public static void writeToFile(String fileName, byte[] body) {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            fOut.write(body);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String getMimeType(Context context, Uri uri) {
        ContentResolver resolver = context.getContentResolver();
        return resolver.getType(uri);
    }

    public static String getDisplayName(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            try {
                // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
                // "if there's anything to look at, look at it" conditionals.
                if (cursor.moveToFirst()) {

                    // Note it's called "Display Name".  This is
                    // provider-specific, and might not necessarily be the file name.
                    return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                /*int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.
                String size = null;
                if (!cursor.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    size = cursor.getString(sizeIndex);
                } else {
                    size = "Unknown";
                }
                Log.i(TAG, "Size: " + size);*/
                }
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    public static String getPath(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            try {
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception ignored) {
            } finally {
                cursor.close();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getTextFromRaw(Context context, String fileName) {
        InputStream inputStream = context
                .getResources()
                .openRawResource(
                        context.getResources().getIdentifier(fileName, "raw", context.getApplicationContext().getPackageName())
                );
        String text;
        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
            text = byteArrayOutputStream.toString();

        } catch (IOException e) {
            e.printStackTrace();
            text = null;
        }
        return text;
    }

    public static String getTextFromAsset(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        String text;
        InputStream inputStream;
        try {
            inputStream = assetManager.open(fileName);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
            text = byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
            text = null;
        }

        return text;
    }
}
