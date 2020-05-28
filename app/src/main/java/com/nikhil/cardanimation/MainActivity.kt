package com.nikhil.cardanimation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    companion object {
        val TAG = MainActivity::class.simpleName;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        motionChangeListner()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isFront.observe(this, Observer {
            // Todo with card swaped
            Log.d(TAG, "Is Front Page : $it")
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Implement Maintained the state of card
        if (viewModel.isFront.value!!) {
            motion_layout.setTransition(R.id.end, R.id.start)
        } else {
            motion_layout.setTransition(R.id.start, R.id.end)
        }
    }


    //Implement the motionChangeListner method to observe the motion of thumb
    private fun motionChangeListner() {
        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int,
                                            progress: Float) {}

            override fun onTransitionCompleted(
                    motionLayout: MotionLayout?,
                    currentId: Int) {
                viewModel.isFront.value = currentId == R.id.end
            }
        })
    }
}
