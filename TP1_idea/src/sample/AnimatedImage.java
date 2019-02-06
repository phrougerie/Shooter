package sample;

import javafx.scene.image.Image;

public class AnimatedImage
{
    private Image[] frames;
    private double duration;

    /**
     * get the image of the array "frames" corresponding to the actual time
     * @param time actual time
     */
    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * set the frame array
     * @param frames Image array which corresponding of all the fram of the animated image
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
    /**
     * set the duration of a frame before the next frame is loaded.
     * @param duration time in second
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

}