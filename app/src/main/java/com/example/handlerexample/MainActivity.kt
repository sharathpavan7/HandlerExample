package com.example.handlerexample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mRandom: Random
    private lateinit var mRunable: Runnable
    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intialize a new Random instance
        mRandom = Random()

        // Intialize the handler instance
        mHandler = Handler()

        button_run.setOnClickListener {
            mRunable = Runnable {
                // Do something here
                root_layout.setBackgroundColor(randomHSVColor());
                textView.text = "Handler Example\n"+
                        "Random Number: ${mRandom.nextInt(100)}"

                // Schedule the task to repeat afterr 1 secound
                mHandler.postDelayed(
                    mRunable, // Runnable
                    1000 // Delay in millisecounds
                )
            }

            // Schedule the task to repeat afterr 1 secound
            mHandler.postDelayed(
                mRunable, // Runnable
                1000 // Delay in millisecounds
            )
        }

        // Set a click listener for stop button
        button_stop.setOnClickListener {
            // Stop the periodic task
            mHandler.removeCallbacks(mRunable)

            // Change the text view text
            textView.text = "Handler callback removed"
        }

        // Set a click listener for handler short code from button
        button_short.setOnClickListener {
            mHandler.postDelayed({
                // Change text view text
                textView.text = "Handler short code\n"+
                        "Random number: ${mRandom.nextInt(100)}"

                // Change text view text color
                textView.setTextColor(randomHSVColor());

                // Change layout background color
                root_layout.setBackgroundColor(Color.WHITE)
            }, // Runnable
                3000 // Delay in millisecounds
            )
        }
    }

    // Custom method to genarate random HSV color
    fun randomHSVColor(): Int {
        // Generate random hue value between 0 to 360
        val hue = mRandom.nextInt(361)
        // We make the color depth full
        val saturation = 1.0f
        // We make a full bright color
        val value = 1.0f
        // We avoid color transparency
        val alpha = 255
        // Finally, generate the color
        // Retrun the color
        return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))

    }
}
