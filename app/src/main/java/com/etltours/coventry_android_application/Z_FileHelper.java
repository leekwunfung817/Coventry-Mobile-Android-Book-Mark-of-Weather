package com.etltours.coventry_android_application;

/**
 * Created by leekwunfung on 4/17/18.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author itst
 */
public class Z_FileHelper {

    public static void w(String filename, String s) {
        c(filename);
        Path file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            file = (new File(filename)).toPath();
        }
        Charset charset = Charset.forName("UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(s, 0, s.length());
            } catch (Exception x) {
                x.printStackTrace();
                System.out.println(file);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Z_FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void an(String filename, String s) {
        String readString = r(filename);

        c(filename);
        Path file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            file = (new File(filename)).toPath();
        }
        Charset charset = Charset.forName("UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                writer.write(readString.replace("  ", "\r\n"));
                //            writer.newLine();
                writer.write(s + "\r\n");
                //            writer.newLine();
            } catch (Exception x) {
                x.printStackTrace();
                System.out.println(file);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Z_FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String r(String filename) {
        c(filename);
        Path file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            file = (new File(filename)).toPath();
        }
        String result = "";
        Charset charset = Charset.forName("UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //                System.out.println(line);
                    result += line + "\r\n";
                }
            } catch (Exception x) {
                x.printStackTrace();
                System.out.println(file);
            }
        }
        return result;
    }

    public static void c(String filename) {
        Path file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            file = FileSystems.getDefault().getPath(filename);
        }
        try {
            // Create the empty file with default permissions, etc.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.createFile(file);
            }
//            System.out.println("Created File");
        } catch (Exception x) {
//            x.printStackTrace();
//            System.out.println(file);
        }
    }

    public static void w_csv(String filename, String[] arr) {
        String s = "";
        boolean begin = false;
        for (int i = 0; i < arr.length; i++) {
            if (!begin) {
                begin = true;
            } else {
                s += ",";
            }
            s += arr[i];
        }
        c(filename);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w(filename, s);
        }
//        CurrentModelFile.updateModel(s);
    }

    public static String[] r_csv(String filename) {
        c(filename);
        String result = r(filename);
        String[] arr_result = result.split(",");
        return arr_result;
    }

    public static void m(String filename, String target) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.move((new File(filename)).toPath(), (new File(target)).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            Logger.getLogger(Z_FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String[] getFileNames(String floderString) {
        Collection<File> all = new ArrayList<File>();
        addTree(new File(floderString), all);
        Object[] os = all.toArray();
        String[] arr = new String[os.length];
        for (int i = 0; i < os.length; i++) {
            arr[i] = os[i].toString();
        }
        return arr;
    }

    public static String d(String fp) {
//        Path file = (new File(fp)).toPath();
        File file = new File(fp);
        String txt = file.toString();
        file.delete();
        return txt;
    }

    public static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
}
