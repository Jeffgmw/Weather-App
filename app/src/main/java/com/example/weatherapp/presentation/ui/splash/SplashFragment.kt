package com.example.weatherapp.presentation.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSplashBinding
import com.example.weatherapp.presentation.ui.base.BaseFragment
import com.example.weatherapp.presentation.ui.splash.SplashFragmentViewModel
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.extensions.hide
import com.example.weatherapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(
    R.layout.fragment_splash,
    SplashFragmentViewModel::class.java,
) {
    var disposable = CompositeDisposable()

    override fun init() {
        super.init()

        if (binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")
                .isNullOrEmpty()
        ) {
            binding.buttonExplore.show()
            binding.viewModel?.navigateDashboard = false
        } else {
            binding.buttonExplore.hide()
            binding.viewModel?.navigateDashboard = true
        }

        binding.viewModel?.navigateDashboard?.let { startSplashAnimation(it) }

        binding.buttonExplore.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }

        binding.rootView.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }
    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {
        val translationYAnimator = ObjectAnimator.ofFloat(binding.imageViewBottomDrawable, "translationY", 500f, 0f)
        val fadeOutAnimator = ObjectAnimator.ofFloat(binding.imageViewEllipse, "alpha", 1f, 0f)
        val fadeInAnimator = ObjectAnimator.ofFloat(binding.imageViewEllipse, "alpha", 0f, 1f)
        val translationXAnimatorBigCloud = ObjectAnimator.ofFloat(binding.imageViewBigCloud, "translationX", -500f, 0f)
        val translationXAnimatorSmallCloud = ObjectAnimator.ofFloat(binding.imageViewSmallCloud, "translationX", 500f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            AnimatorSet().apply {
                playTogether(
                    translationYAnimator,
                    fadeOutAnimator,
                    translationXAnimatorBigCloud,
                    translationXAnimatorSmallCloud
                )
            },
            fadeInAnimator
        )
        animatorSet.duration = 1000

        animatorSet.start()
        animatorSet.doOnEnd {
            findNavController().graph.setStartDestination(R.id.dashboardFragment)
            if (navigateToDashboard) {
                endSplashAnimation(navigateToDashboard)
            }
        }
    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        val translationYAnimator = ObjectAnimator.ofFloat(binding.imageViewBottomDrawable, "translationY", 0f, 100f)
        val fadeOutAnimator = ObjectAnimator.ofFloat(binding.imageViewEllipse, "alpha", 1f, 0f)
        val translationXAnimatorBigCloud = ObjectAnimator.ofFloat(binding.imageViewBigCloud, "translationX", 0f, 500f)
        val translationXAnimatorSmallCloud = ObjectAnimator.ofFloat(binding.imageViewSmallCloud, "translationX", 0f, -500f)

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(
            AnimatorSet().apply {
                playTogether(
                    translationYAnimator,
                    fadeOutAnimator,
                    translationXAnimatorBigCloud,
                    translationXAnimatorSmallCloud
                )
            }
        )
        animatorSet.duration = 300

        animatorSet.start()
        animatorSet.doOnEnd {
            findNavController().graph.setStartDestination(R.id.dashboardFragment)
            if (navigateToDashboard) {
                navigate(R.id.action_splashFragment_to_dashboardFragment)
            } else {
                navigate(R.id.action_splashFragment_to_searchFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
