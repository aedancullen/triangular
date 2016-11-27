package io.github.aedancullen.triangular.samples;

import android.app.Activity;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import io.github.aedancullen.triangular.system.NativePart;
import io.github.aedancullen.triangular.system.VisionProcessor;


@TeleOp(name="triangular: Detection Demo", group="Triangular")
//@Disabled
public class TriangularDetectionDemo extends OpMode {
/**
    VisionProcessor visionProcessor;

    public void init() {
        visionProcessor = VisionProcessor.beginOnUiThread(hardwareMap.appContext);
        visionProcessor.start(0);
    }

    public void loop() {}

    public void stop() {
        visionProcessor.stop();
    }**/

private BaseLoaderCallback loaderCallback = new BaseLoaderCallback(hardwareMap.appContext) {
    @Override
    public void onManagerConnected(int status) {
        switch (status) {
            case LoaderCallbackInterface.SUCCESS:
            {
                System.loadLibrary("triangular-native");
                nativePart = new NativePart();
                int input = 8;
                int result = nativePart.test(input);
                telemetry.addData("JNI", "input " + input + ", output " + result);
            } break;
            default:
            {
                super.onManagerConnected(status);
            } break;
        }
    }
};

    NativePart nativePart;

    public void init() {
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, hardwareMap.appContext, loaderCallback);
    }

    public void loop() {}

}
